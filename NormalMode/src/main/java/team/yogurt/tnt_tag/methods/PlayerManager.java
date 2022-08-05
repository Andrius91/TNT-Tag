package team.yogurt.tnt_tag.methods;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import team.yogurt.common.enums.IGameState;
import team.yogurt.common.interfaces.IGameManager;
import team.yogurt.common.interfaces.IPlayerManager;

import static team.yogurt.tnt_tag.Main.getGames;

public class PlayerManager implements IPlayerManager {
    private Player player;

    @Override
    public IPlayerManager setPlayer(Player player) {
        this.player = player;

        return this;
    }

    @Override
    public void setTnt() {
        player.getInventory().setHelmet(new ItemStack(Material.TNT));
    }

    @Override
    public void blewUp() {
        World world = player.getWorld();
        world.createExplosion(player.getLocation().getX(), player.getLocation().getY()+1.2,
                player.getLocation().getZ(), 1.5F, false, false);

    }

    @Override
    public void sendToGame(String arenaName) {
        for(IGameManager games : getGames()){
            if(games.getArenaManager().getNameArena().equalsIgnoreCase(arenaName)){
                if(games.getGameState() == IGameState.WAITING){
                    games.getPlayers().add(player);
                    player.teleport(games.getArenaManager().getSpawn());
                }else{
                    player.sendMessage("El juego ya ha iniciado");
                }
                break;
            }else{
                player.sendMessage("Mapa no encontrado");
            }
        }
    }

    @Override
    public void sendToRandomGame() {
        IGameManager game = getGames().stream()
                .filter(x -> x.getGameState() == IGameState.WAITING)
                .findAny()
                .get();

        if(game != null){
            game.getPlayers().add(player);
            player.teleport(game.getArenaManager().getSpawn());
        }else{
            player.sendMessage("No se ha encontrado un juego disponible");
        }
    }

    @Override
    public IGameManager getGame() {
        for(IGameManager game : getGames()){
            if(game.getPlayers().contains(player)){
                return game;
            }
        }
        return null;
    }

    @Override
    public boolean isPlaying() {
        return this.getGame() != null;
    }
}
