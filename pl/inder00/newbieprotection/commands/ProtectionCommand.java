package pl.inder00.newbieprotection.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.newbieprotection.NewbieProtection;
import pl.inder00.newbieprotection.basic.User;
import pl.inder00.newbieprotection.basic.utils.UserUtils;
import pl.inder00.newbieprotection.storage.Config;
import pl.inder00.newbieprotection.utils.TimeUtil;

public class ProtectionCommand implements CommandExecutor {

	NewbieProtection plugin;
	
	public ProtectionCommand(NewbieProtection plugin){
		this.plugin = plugin;
		this.plugin.getCommand("protection").setExecutor(this);;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED+"Komenda nie moze byc wykonana jako konsola");
			return false;
		}
		Player player = (Player) sender;
		Config cfg = Config.getInst();
		if(player.hasPermission("protection.command")){
			
			User u = UserUtils.get(player.getUniqueId());
			
			if(u == null){
				player.sendMessage(ChatColor.RED+"java.lang.NullPointerException");
				return false;
				
			}
			
			player.sendMessage(cfg.protection$command.replace("{TIME}", u.getProtection() > TimeUtil.getTime() ? TimeUtil.convertTime(TimeUtil.fromTime(u.getProtection())) : "brak"));
			return true;
			
		} else {
			player.sendMessage(cfg.no$permission);
			return false;
		}
	}

}
