package team.yogurt.common.interfaces;

import org.bukkit.entity.Player;

public interface IPlayerManager {

    IPlayerManager setPlayer(Player player);
    void setTnt();
    void blewUp();
    void sendToGame(String arenaName);
    void sendToRandomGame();

    IGameManager getGame();
    boolean isPlaying();

}
