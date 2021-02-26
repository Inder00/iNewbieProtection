package pl.inder00.newbieprotection.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import pl.inder00.newbieprotection.storage.Config;

public class GlobalPrefix {

    protected Config cfg;
    private Scoreboard scoreboard;
    private Team protectedTeam;

    public GlobalPrefix() {
        //CONFIG
        this.cfg = Config.getInst();
        //REGISTER SCOREBOARD
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        //REGISTER TEAM
        this.protectedTeam = this.scoreboard.registerNewTeam("protection");
        //SET VALUES OF TEAM
        this.protectedTeam.setPrefix(cfg.prefix$name);
        this.protectedTeam.setAllowFriendlyFire(true);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void addPlayer(OfflinePlayer offlinePlayer){
        this.protectedTeam.addPlayer(offlinePlayer);
    }
    public void delPlayer(OfflinePlayer offlinePlayer){
        if(!this.protectedTeam.hasPlayer(offlinePlayer)) return;
        this.protectedTeam.removePlayer(offlinePlayer);
    }

}
