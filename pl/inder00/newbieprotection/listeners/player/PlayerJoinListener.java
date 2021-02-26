package pl.inder00.newbieprotection.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pl.inder00.newbieprotection.NewbieProtection;
import pl.inder00.newbieprotection.basic.User;
import pl.inder00.newbieprotection.basic.utils.UserUtils;
import pl.inder00.newbieprotection.storage.Config;
import pl.inder00.newbieprotection.utils.TimeUtil;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		
		Player player = event.getPlayer();
		User u = UserUtils.get(player.getUniqueId());
		Config cfg = Config.getInst();
		
		if(u == null){
			u = new User(player.getUniqueId(), player.getName(), TimeUtil.addTime(cfg.protection$time));
			player.sendMessage(cfg.protection$added.replace("{TIME}", TimeUtil.convertTime(cfg.protection$time)));
			NewbieProtection.getInstance().getGlobalPrefix().addPlayer(player);
		}
		player.setScoreboard(NewbieProtection.getInstance().getGlobalPrefix().getScoreboard());
		
	}

}
