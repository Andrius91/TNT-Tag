package team.yogurt.common.interfaces;

import org.bukkit.Location;

public interface ICore {
    void setSpawn(Location location);
    Location getSpawn();

    IArenaManager getArenaByName(String arenaName);
    boolean arenaExist(String arenaName);
}
