package team.yogurt.tnt_tag.commands.SubCommands.ArenaCmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.yogurt.common.interfaces.ICommand;
import team.yogurt.common.interfaces.IGameManager;
import team.yogurt.tnt_tag.Main;

public class SetSpawn implements ICommand {
    @Override
    public String getCommand() {
        return "setspawn";
    }

    @Override
    public String getDescription() {
        return "Establece el spawn de la arena";
    }

    @Override
    public String getPermission() {
        return "tnt.tag.arena.setspawn";
    }

    @Override
    public String getSyntax() {
        return "/tnttag arena setspawn <arenaName>";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(args.length == 3){
            String nombre = args[2];
            IGameManager game = Main.getGames().stream()
                    .filter(x -> x.getArenaManager().getNameArena().equalsIgnoreCase(nombre))
                    .findFirst()
                    .get();
            if(game != null){
                game.getArenaManager().setSpawn(((Player) sender ).getLocation());
                sender.sendMessage("Has establecido el spawn para el mapa: " + nombre);
            }else{
                sender.sendMessage("No se ha encontrado el mapa");
            }
        }else{
            sender.sendMessage(getSyntax());
        }
    }
}
