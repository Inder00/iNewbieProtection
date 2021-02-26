package pl.inder00.newbieprotection.listeners.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import pl.inder00.newbieprotection.basic.User;
import pl.inder00.newbieprotection.basic.utils.UserUtils;
import pl.inder00.newbieprotection.storage.Config;
import pl.inder00.newbieprotection.utils.TimeUtil;

public class EntityDamageListener implements Listener {
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void onDamage(EntityDamageByEntityEvent e){

		if(e.isCancelled()){
			return;
		}

		Entity entity = e.getEntity();
		Entity entity2 = e.getDamager();
		
		if((entity instanceof Player && entity2 instanceof Player) && entity != entity2){
			
			Player p = (Player) entity;
			Player damager = (Player) entity2;
			
			User u = UserUtils.get(p.getUniqueId());
			User b = UserUtils.get(damager.getUniqueId());
			if(((u != null) && (u.getProtection() > TimeUtil.getTime())) || (((b != null) && (b.getProtection() > TimeUtil.getTime())))){
				
				damager.sendMessage(Config.getInst().has$protection);
				e.setCancelled(true);
				return;
				
			}
			
		}
		
	}

}
