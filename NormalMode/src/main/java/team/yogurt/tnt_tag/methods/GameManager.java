package team.yogurt.tnt_tag.methods;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import team.yogurt.common.enums.IGameState;
import team.yogurt.common.interfaces.IArenaManager;
import team.yogurt.common.interfaces.IGameManager;
import team.yogurt.tnt_tag.tasks.StartTask;
import team.yogurt.tnt_tag.tasks.WaitingTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameManager implements IGameManager {

    private final Plugin plugin;
    private final Random random = new Random();
    private final List<Player> players = new ArrayList<>();
    private final List<Player> spectators = new ArrayList<>();
    private final List<Player> tops = new ArrayList<>(2);
    private final List<Player> tnts = new ArrayList<>(10);

    private IGameState state;
    private IArenaManager arenaManager;
    private int gameDuration, round;

    public GameManager(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public IArenaManager getArenaManager() {
        return arenaManager;
    }

    @Override
    public void setArenaManager(IArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public List<Player> getSpectators() {
        return spectators;
    }

    @Override
    public List<Player> getTops() {
        return tops;
    }

    @Override
    public List<Player> getTnts() {
        return tnts;
    }

    @Override
    public int getPoints(Player player) {
        return 0;
    }

    @Override
    public void addPoints(Player player) {

    }

    @Override
    public int getWins(Player player) {
        return 0;
    }

    @Override
    public void addWins(Player player) {

    }

    @Override
    public int getGameDuration() {
        return gameDuration;
    }

    @Override
    public void setGameDuration(int gameDuration) {
        this.gameDuration = gameDuration;
    }


    @Override
    public IGameState getGameState() {
        return state;
    }

    @Override
    public void setGameState(IGameState state) {
        this.state = state;
        switch (state){
            case WAITING:
                setRound(0);
                WaitingTask waitingTask = new WaitingTask(this);
                waitingTask.runTaskTimer(plugin, 0, 20);
                break;
            case STARTED:
                System.out.println("Game started.");
                getPlayers().forEach(x -> x.teleport(getArenaManager().getSpawn()));
                StartTask startTask = new StartTask(this);
                startTask.runTaskTimer(plugin, 0, 20);
                break;
            case RESTARTING:
            case FINISHING:
        }
    }

    @Override
    public void reloadTnts() {
        tnts.clear();
    }

    @Override
    public void start(){
        setGameState(IGameState.STARTED);
        getPlayers().forEach(p -> p.teleport(getArenaManager().getSpawn())); //Envair jugadores al spawn
        giveRandomTnt();
    }

    @Override
    public void giveRandomTnt() {
        for(int i = 0; i < 1 || i < getPlayers().size()/4; i++){
            Player player = getPlayers().get(random.nextInt(getPlayers().size()));
            player.getInventory().setHelmet(new ItemStack(Material.TNT));
        }
    }

    @Override
    public int getRound() {
        return this.round;
    }

    @Override
    public void setRound(int round) {
        this.round = round;
    }

    @Override
    public void addPlayer(Player player) {
        getPlayers().add(player);
        sendMessageToAll(player.getName() + " ha entrado. (" + getPlayers().size() + "/"
                + getArenaManager().getMaxPlayers() + ")");
    }

    @Override
    public void removePlayer(Player player) {
        getPlayers().remove(player);
        sendMessageToAll(player.getName() + " ha salido. (" + getPlayers().size() + "/"
                + getArenaManager().getMaxPlayers() + ")");

    }

    @Override
    public void sendMessageToAll(String message) {
        List<Player> allPlayers = Stream.concat(getPlayers().stream(), getSpectators().stream())
                .collect(Collectors.toList());

        allPlayers.forEach(p -> p.sendMessage(message));
    }

    @Override
    public String toString() {
        return "GameManager{" +
                "plugin=" + plugin +
                ", random=" + random +
                ", players=" + players +
                ", spectators=" + spectators +
                ", tops=" + tops +
                ", tnts=" + tnts +
                ", state=" + state +
                ", arenaManager=" + arenaManager +
                ", gameDuration=" + gameDuration +
                ", minPlayers=" + getArenaManager().getMinPlayers() +
                ", maxPlayers=" + getArenaManager().getMaxPlayers() +
                ", arenaName=" + getArenaManager().getNameArena() +
                ", round=" + round +
                '}';
    }
}
