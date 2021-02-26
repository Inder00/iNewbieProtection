package pl.inder00.newbieprotection.storage;

import org.bukkit.configuration.file.FileConfiguration;

import net.md_5.bungee.api.ChatColor;
import pl.inder00.newbieprotection.NewbieProtection;

public class Config {
	
	//=========================================================================
	private static Config inst;
	public FileConfiguration cfg = NewbieProtection.getInstance().getConfig();
	
	public int protection$time;
	public String has$protection;
	public String protection$command;
	public String no$permission;
	public String protection$added;
	public String protection$ended;
	public String prefix$name;
	public boolean use$prefix;
	
	//=========================================================================
	
	public Config() {
		if(inst == null) inst = this;
	}
	
	//=========================================================================
	//Load
	public void load(){

		this.protection$time = cfg.getInt("protection-time");
		this.has$protection = fix("has-protection");
		this.protection$command = fix("protection-command");
		this.no$permission = fix("no-permission");
		this.protection$added = fix("protection-added");
		this.protection$ended = fix("protection-ended");
		this.prefix$name = fix("prefix-name");
		this.use$prefix = cfg.getBoolean("use-prefix");
	}
	//=========================================================================
	
	private String fix(String path){
		return ChatColor.translateAlternateColorCodes('&', cfg.getString(path)).replace("�", "");
	}
	
	//=========================================================================
	//Instance
	public static Config getInst(){
		return inst;
	}
	//=========================================================================

}
