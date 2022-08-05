package team.yogurt.tnt_tag.commands.SubCommands.ArenaCmds;

import org.bukkit.command.CommandSender;
import team.yogurt.common.enums.IGameState;
import team.yogurt.common.interfaces.IArenaManager;
import team.yogurt.common.interfaces.ICommand;
import team.yogurt.common.interfaces.IGameManager;
import team.yogurt.tnt_tag.Main;
import team.yogurt.tnt_tag.methods.ArenaManager;
import team.yogurt.tnt_tag.methods.GameManager;

public class Create implements ICommand {
    @Override
    public String getCommand() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Crea una arena";
    }

    @Override
    public String getPermission() {
        return "tnt.tag.arena.create";
    }

    @Override
    public String getSyntax() {
        return "/tnttag arena create <nombre> <minPlayers> <maxPlayers>";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(args.length == 5){
            String nombre = args[2];
            for(IGameManager game : Main.getGames()){
                if(game.getArenaManager().getNameArena().equalsIgnoreCase(nombre)){
                    sender.sendMessage("Ya existe un mapa con ese nombre.");
                    return;
                }
            }
            int min = Integer.parseInt(args[3]), max = Integer.parseInt(args[4]);
            IArenaManager arenaManager = new ArenaManager();
            IGameManager gameManager = new GameManager(Main.getInstance());
            arenaManager.setNameArena(nombre);
            arenaManager.setMinPlayers(min);
            arenaManager.setMaxPlayers(max);
            gameManager.setArenaManager(arenaManager);
            gameManager.setGameState(IGameState.WAITING);
            Main.getGames().add(gameManager);
            sender.sendMessage("Has creado satisfactoriamente la arena: " + nombre);
        }else{
            sender.sendMessage(getSyntax());
        }
    }
}
