package tk.funnytopia.funny;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class PrivateChat extends JavaPlugin {
	public String combineSplit(int startIndex, String[] string, String seperator) {
        	final StringBuilder builder = new StringBuilder();
        	for (int i = startIndex; i < string.length; i++) {
            		builder.append(string[i]);
            		builder.append(seperator);
        	}
        	builder.deleteCharAt(builder.length() - seperator.length());
        	return builder.toString();
    	}
	
	public void logInfo(String message) {
    		log.info("[PrivateChat] " + message);
    	}
	
	public void adminChatMsg(String message, String playername) {
        	for (final Player player : this.getServer().getOnlinePlayers()) {
            		if (player.hasPermission("privatechat.chat")) {
                		player.sendMessage(ChatColor.GOLD + "[Private]" + ChatColor.WHITE + "<" + ChatColor.LIGHT_PURPLE + playername + ChatColor.WHITE + "> " + ChatColor.WHITE + message);
            		}
        	}
        	this.logInfo(playername + ": " + message); // Should only be sent once
    	}
	
	Logger log;
	
	@Override
	public void onDisable() {
		this.logInfo("Disabled");
	}

	@Override
	public void onEnable() {
		this.log = getServer().getLogger();
		
		this.getCommand("chat").setExecutor(new ChatCommand(this));
		
		this.logInfo(this.getDescription().getVersion() + " enabled");
	}
	
	

}
