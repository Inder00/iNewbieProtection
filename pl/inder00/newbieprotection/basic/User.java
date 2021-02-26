package pl.inder00.newbieprotection.basic;

import java.util.UUID;

import pl.inder00.newbieprotection.basic.utils.UserUtils;

public class User {
	
	private UUID uuid;
	private String name;
	private long protection;
	
	public User(UUID uuid, String name, long protection){
		this.uuid = uuid;
		this.name = name;
		this.protection = protection;
		UserUtils.addUser(this);
	}
	
	public UUID getUUID() {
		return uuid;
	}
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getProtection() {
		return protection;
	}
	public void setProtection(long protection) {
		this.protection = protection;
	}

}
