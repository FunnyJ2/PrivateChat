package tk.funnytopia.funny;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand extends PrivateChat {
	
private PrivateChat plugin;
	
	public ChatCommand(PrivateChat instance) {
		this.plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if(sender instanceof Player) {
			//player
			final Player player = (Player) sender;
			if(args.length < 1) {
				//too short args
				sender.sendMessage(ChatColor.RED + "Too few arguments! usage: /chat <message>");
			} else {
				//long enough args
				if(sender.isOp() || sender.hasPermission("privatechat.chat")) {
					//player is op
					String playerSender = player.getName();
					final String message = plugin.combineSplit(0, args, " ");
					plugin.adminChatMsg(message, playerSender);
				} else {
					//player isn't op
					sender.sendMessage(ChatColor.RED+"You don't have permission to do this!");
				}
			}
			
		}  else {
			//console
			if(args.length < 1) {
				//too few args
				plugin.log.info("Too few arguments! /" + commandLabel + " <message>");
			} else {
				//console - enough args
				final String message = plugin.combineSplit(0, args, " ");
				plugin.adminChatMsg(message, "*CONSOLE*");
			}
		}
		return true;
	}

}
