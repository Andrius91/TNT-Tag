package team.yogurt.tnt_tag.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import team.yogurt.common.enums.IGameState;
import team.yogurt.common.interfaces.IGameManager;

import static team.yogurt.tnt_tag.Main.getPlayerManager;

public class StartTask extends BukkitRunnable {
    private final IGameManager gameManager;
    private int countdown = 35; //In seconds
    public StartTask(IGameManager gameManager){
        this.gameManager = gameManager;
    }
    @Override
    public void run() {
        //Logica
        if(countdown == 5){
            System.out.println("StartTask time="+countdown);
            for(Player p : getGameManager().getTnts()){
                getPlayerManager().setPlayer(p);
                getPlayerManager().blewUp();
            }
        }
        if (countdown <= 0) {
            this.cancel();
            getGameManager().setGameState(IGameState.STARTED);
        }
        System.out.println(countdown);
        countdown--;
    }

    public IGameManager getGameManager() {
        return gameManager;
    }

}
