package team.yogurt.tnt_tag.methods;

import org.bukkit.Location;
import team.yogurt.common.interfaces.IArenaManager;

public class ArenaManager implements IArenaManager {

    private String nameArena;
    private int maxPlayers, minPlayers;
    private Location spawn;

    @Override
    public String getNameArena() {
        return nameArena;
    }

    @Override
    public void setNameArena(String name) {
        this.nameArena = name;
    }

    @Override
    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    @Override
    public int getMinPlayers() {
        return minPlayers;
    }

    @Override
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    @Override
    public Location getSpawn() {
        return spawn;
    }

    @Override
    public void setSpawn(Location location) {
        this.spawn = location;
    }
}
