package team.yogurt.tnt_tag.commands.SubCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.yogurt.common.interfaces.ICommand;
import team.yogurt.tnt_tag.Main;

import static team.yogurt.tnt_tag.Main.getPlayerManager;

public class Leave implements ICommand {

    @Override
    public String getCommand() {
        return "leave";
    }

    @Override
    public String getDescription() {
        return "Salir de un juego";
    }

    @Override
    public String getPermission() {
        return "tnt.tag.leave";
    }

    @Override
    public String getSyntax() {
        return "/tnttag leave";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(args.length == 1){
            getPlayerManager().setPlayer((Player) sender);
            if(getPlayerManager().isPlaying()){
                getPlayerManager().getGame().getPlayers().remove((Player) sender);
                ((Player) sender).teleport(Main.getCore().getSpawn());
            }else{
                sender.sendMessage("No estás en ninguna partida");
            }
        }
    }
}
