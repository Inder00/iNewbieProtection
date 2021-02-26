package pl.inder00.newbieprotection.basic.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import pl.inder00.newbieprotection.NewbieProtection;
import pl.inder00.newbieprotection.basic.User;
import pl.inder00.newbieprotection.storage.Config;
import pl.inder00.newbieprotection.utils.TimeUtil;

public class UserUtils {
	
	public static void load(){		
		for(File f : NewbieProtection.getInstance().getUsersFolder().listFiles()){
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			UUID uuid = UUID.fromString(yml.getString("uuid"));
			String name = yml.getString("name");
			long protection = yml.getLong("protection");
			new User(uuid,name,protection);
			if(protection > System.currentTimeMillis()){
				NewbieProtection.getInstance().getGlobalPrefix().addPlayer(Bukkit.getOfflinePlayer(name));
			}
		}
		
	}
	
	public static void save(){
		for(User u : users){
			File f = new File(NewbieProtection.getInstance().getUsersFolder(), u.getUUID().toString() + ".yml");
			if(!f.exists()){
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			yml.set("uuid", u.getUUID().toString());
			yml.set("name", u.getName());
			yml.set("protection", u.getProtection());
			try {
				yml.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void checkPlayers() {
		
		int time = Config.getInst().protection$time;
		String msg = Config.getInst().protection$added;
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			
			if(get(p.getUniqueId()) == null) {
				new User(p.getUniqueId(), p.getName(), TimeUtil.addTime(time));
				p.sendMessage(msg.replace("{TIME}", TimeUtil.convertTime(time)));
			}
			
		}
		
	}
		
	private static List<User> users = new ArrayList<>();

	public static List<User> getUsers() {
		return users;
	}
	
	public static User get(UUID uuid){
		for(User u : users){
			if(u.getUUID().equals(uuid)){
				return u;
			}
		}
		return null;
	}
	public static User get(String name){
		for(User u : users){
			if(u.getName().equalsIgnoreCase(name)){
				return u;
			}
		}
		return null;
	}
	
	public static void addUser(User u){
		if(get(u.getUUID()) == null){
			users.add(u);
		}
	}
	public static void delUser(User u){
		if(get(u.getUUID()) != null){
			users.remove(u);
		}
	}

}
