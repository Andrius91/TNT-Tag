package team.yogurt.common.interfaces;


import org.bukkit.Location;

public interface IArenaManager {

    String getNameArena();

    void setNameArena(String name);

    int getMaxPlayers();
    void setMaxPlayers(int maxPlayers);

    int getMinPlayers();
    void setMinPlayers(int minPlayers);

    Location getSpawn();
    void setSpawn(Location location);
}
