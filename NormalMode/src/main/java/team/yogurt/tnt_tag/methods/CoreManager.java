package team.yogurt.tnt_tag.methods;

import org.bukkit.Location;
import team.yogurt.common.interfaces.IArenaManager;
import team.yogurt.common.interfaces.ICore;
import team.yogurt.common.interfaces.IGameManager;
import team.yogurt.tnt_tag.Main;

public class CoreManager implements ICore {
    private Location location;

    @Override
    public void setSpawn(Location location) {
        this.location = location;
    }

    @Override
    public Location getSpawn() {
        return location;
    }

    @Override
    public IArenaManager getArenaByName(String arenaName) {
        for(IGameManager game : Main.getGames()){
            if(game.getArenaManager().getNameArena().equalsIgnoreCase(arenaName)){
                return game.getArenaManager();
            }
        }
        return null;
    }

    @Override
    public boolean arenaExist(String arenaName) {
        return getArenaByName(arenaName) != null;
    }

}
