package team.yogurt.tnt_tag.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import team.yogurt.common.enums.IGameState;
import team.yogurt.common.interfaces.IGameManager;

public class WaitingTask extends BukkitRunnable {

    private IGameManager gameManager;
    private int countdown = 300;
    private boolean changed = false;
    public WaitingTask(IGameManager gameManager){
        this.gameManager = gameManager;
    }


    @Override
    public void run() {
        int minPlayers = gameManager.getArenaManager().getMinPlayers();
        int players = gameManager.getPlayers().size();
        System.out.println("minPlayers=" + minPlayers + " players=" +players + " state=" + getGameManager().getGameState().toString());
        if(gameManager.getGameState() != IGameState.WAITING){
            this.cancel();
        }
        if(players >= minPlayers){
            if(players > 5 && countdown > 204){
                System.out.println(1);
                this.countdown = 204;
                changed = false;
            }else if(players > 10 && countdown > 78){
                System.out.println(2);
                this.countdown = 78;
                changed = false;
            }else if(players > 20 && countdown > 25){
                System.out.println(3);
                this.countdown = 20;
                changed = false;
            }else if (players < 5 && !changed ){
                System.out.println(4);
                this.countdown = 300;
                changed = true;
            }
            if(countdown <= 0){
                this.cancel(); //Cancela el runnable
                System.out.println("Juego iniciado");
                getGameManager().getPlayers().forEach(p -> {
                    p.sendMessage("El juego ha iniciado.");
                });
                //getGameManager().setGameState(IGameState.STARTED);
            }
            countdown--;
        }else{
            countdown = 300;
        }


        //ARREGLAR ESTA MIERDA
        System.out.println("time="+countdown);
        System.out.printf(getGameManager().getArenaManager().getNameArena()
                + " - " + getGameManager().getPlayers().size());


    }

    public IGameManager getGameManager(){
        return gameManager;
    }
}
