package team.yogurt.common.interfaces;

import org.bukkit.entity.Player;
import team.yogurt.common.enums.IGameState;

import java.util.List;

public interface IGameManager {

    IArenaManager getArenaManager();
    void setArenaManager(IArenaManager arenaManager);
    List<Player> getPlayers();
    List<Player> getSpectators();
    List<Player> getTops();

    List<Player> getTnts();

    int getPoints(Player player);
    void addPoints(Player player);

    int getWins(Player player);
    void addWins(Player player);

    int getGameDuration();
    void setGameDuration(int gameDuration);

    IGameState getGameState();
    void setGameState(IGameState state);

    void reloadTnts();
    void start();
    void giveRandomTnt();

    int getRound();
    void setRound(int round);

    void addPlayer(Player player);
    void removePlayer(Player player);

    void sendMessageToAll(String message);

}
