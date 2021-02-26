package pl.inder00.newbieprotection.tasks;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import pl.inder00.newbieprotection.NewbieProtection;
import pl.inder00.newbieprotection.basic.User;
import pl.inder00.newbieprotection.basic.utils.UserUtils;
import pl.inder00.newbieprotection.storage.Config;
import pl.inder00.newbieprotection.utils.TimeUtil;

public class ProtectionTask extends BukkitRunnable {

	HashMap<User, Boolean> notified = new HashMap<User, Boolean>();
	
	Config cfg;
	
	public ProtectionTask(){
		
		this.cfg = Config.getInst();
		
	}
	
	@Override
	public void run() {
		
		for(User u : UserUtils.getUsers()){
			
			Player g = Bukkit.getPlayer(u.getUUID());
			if(g != null){
				
				int r = TimeUtil.fromTime(u.getProtection());
				
				if((r <= 0) && (!notified.containsKey(u))){
					
					notified.put(u, true);
					g.sendMessage(cfg.protection$ended);
					NewbieProtection.getInstance().getGlobalPrefix().delPlayer(g);
					
				}
				
			}
			
		}
		
	}

}
