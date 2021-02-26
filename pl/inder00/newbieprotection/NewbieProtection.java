package pl.inder00.newbieprotection;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import pl.inder00.newbieprotection.basic.utils.UserUtils;
import pl.inder00.newbieprotection.commands.ProtectionCommand;
import pl.inder00.newbieprotection.listeners.entity.EntityDamageListener;
import pl.inder00.newbieprotection.listeners.player.PlayerJoinListener;
import pl.inder00.newbieprotection.storage.Config;
import pl.inder00.newbieprotection.tasks.ProtectionTask;
import pl.inder00.newbieprotection.utils.GlobalPrefix;

public class NewbieProtection extends JavaPlugin {
	
	private static NewbieProtection instance;
	
	private File mainDir;
	private File users;
	private File data;
	private File cfgFile;

	private GlobalPrefix globalPrefix;
	
	public void onEnable(){
		
		instance = this;
		
		//FOLDERY
		this.mainDir = this.getDataFolder();
		this.data = new File(this.mainDir, "data");
		this.users = new File(this.data, "users");
		this.cfgFile = new File(this.mainDir, "config.yml");
		if(!this.mainDir.exists()){
			this.mainDir.mkdir();
		}
		if(!this.data.exists()){
			this.data.mkdir();
		}
		if(!this.users.exists()){
			this.users.mkdir();
		}
		if(!this.cfgFile.exists()){
			this.saveDefaultConfig();
		}
		
		//CONFIG
		Config cfg = new Config();
		cfg.load();
		
		//LISTENERY
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new EntityDamageListener(), this);
		
		//KOMENDY
		new ProtectionCommand(this);

		//SCOREBOARD, TAGI NAD GLOWA
		this.globalPrefix = new GlobalPrefix();

		//WCZYTYWANIE
		UserUtils.load();
		UserUtils.checkPlayers();
		
		//TASK
		new ProtectionTask().runTaskTimerAsynchronously(this, 0, 20);
		
	}
	
	public void onDisable(){
		
		//ZAPISYWANIE
		UserUtils.save();
		
	}

	public GlobalPrefix getGlobalPrefix() {
		return globalPrefix;
	}

	public static NewbieProtection getInstance() {
		return instance;
	}

	public File getMainFolder() {
		return mainDir;
	}

	public File getUsersFolder() {
		return users;
	}
}
