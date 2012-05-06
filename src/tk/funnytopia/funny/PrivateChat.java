package tk.funnytopia.funny;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class PrivateChat extends JavaPlugin {

	public Logger log;
	
	public String combineSplit(int startIndex, String[] string, String seperator) {
        final StringBuilder builder = new StringBuilder();
        for (int i = startIndex; i < string.length; i++) {
            builder.append(string[i]);
            builder.append(seperator);
        }
        builder.deleteCharAt(builder.length() - seperator.length());
        return builder.toString();
    }
	
	public String replaceWithChatColor(String toFix) {
		/*
		 * IMPORTANT
		 * DARK COLORS HAS TO BE FIRST OR BAD THINGS WILL HAPPEN
		 */
		toFix = toFix.replace("*darkgreen", ChatColor.DARK_GREEN.toString());
		toFix = toFix.replace("*darkred", ChatColor.DARK_RED.toString());
		toFix = toFix.replace("*darkaqua", ChatColor.DARK_AQUA.toString());
		toFix = toFix.replace("*darkblue", ChatColor.DARK_BLUE.toString());
		toFix = toFix.replace("*darkgray", ChatColor.DARK_GRAY.toString());
		toFix = toFix.replace("*darkpurple", ChatColor.DARK_PURPLE.toString());
		
		toFix = toFix.replace("*red", ChatColor.RED.toString());
		toFix = toFix.replace("*gold", ChatColor.GOLD.toString());
		toFix = toFix.replace("*orange", ChatColor.GOLD.toString());
		toFix = toFix.replace("*green", ChatColor.GREEN.toString());
		toFix = toFix.replace("*aqua", ChatColor.AQUA.toString());
		toFix = toFix.replace("*white", ChatColor.WHITE.toString());
		toFix = toFix.replace("*blue", ChatColor.BLUE.toString());
		toFix = toFix.replace("*black", ChatColor.BLACK.toString());
		toFix = toFix.replace("*gray", ChatColor.GRAY.toString());
		toFix = toFix.replace("*purple", ChatColor.LIGHT_PURPLE.toString());
		toFix = toFix.replace("*yellow", ChatColor.YELLOW.toString());
		
		return toFix;
	}
	
	public String getFormattedMessage(String playername, String message) {
		String configStr = this.getConfig().getString("format");
		configStr = configStr.replace("*name", playername);
		configStr = configStr.replace("*playername", playername);
		configStr = configStr.replace("*message", message);
		
		//colors
		configStr = this.replaceWithChatColor(configStr);
		
		//"easter egg"
		configStr = configStr.replace("*magic", ChatColor.MAGIC.toString());
		
		return configStr;
	}
	
	public void adminChatMsg(String message, String playername) {
		String finalMsg = this.getFormattedMessage(playername, message);
        for (final Player player : this.getServer().getOnlinePlayers()) {
            if (player.isOp() || player.hasPermission("privatechat.chat")) {
                //player.sendMessage(ChatColor.GOLD + "[Private]" + ChatColor.WHITE + "<" + ChatColor.LIGHT_PURPLE + playername + ChatColor.WHITE + "> " + ChatColor.WHITE + message);
            	player.sendMessage(finalMsg);
            }
        }
        this.log.info(playername + ": " + message);
    }
	
	@Override
	public void onDisable() {
		this.log.info("Disabled");
	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.log = this.getLogger();
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		this.getCommand("chat").setExecutor(new ChatCommand(this));
		
		this.log.info(pdfFile.getVersion() + " enabled");
	}
	
	

}
