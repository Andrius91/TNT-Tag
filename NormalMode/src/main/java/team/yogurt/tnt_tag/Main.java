package team.yogurt.tnt_tag;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import team.yogurt.common.interfaces.ICore;
import team.yogurt.common.interfaces.IGameManager;
import team.yogurt.common.interfaces.IPlayerManager;
import team.yogurt.tnt_tag.commands.TNTTag;
import team.yogurt.tnt_tag.methods.CoreManager;
import team.yogurt.tnt_tag.methods.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    private static Main instance;
    private static IPlayerManager playerManager;
    private static ICore coreManager;

    private static List<IGameManager> gameManager;
    @Override
    public void onEnable() {
        instance = this;
        playerManager = new PlayerManager();
        coreManager = new CoreManager();
        gameManager = new ArrayList<>();
        registerCommands();
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void registerCommands(){
        getCommand("tnttag").setExecutor(new TNTTag());
    }
    public static Main getInstance(){
        return instance;
    }
    public static List<IGameManager> getGames(){
        return gameManager;
    }
    public static IPlayerManager getPlayerManager(){
        return playerManager;
    }
    public static ICore getCore(){
        return coreManager;
    }

}
