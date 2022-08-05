package team.yogurt.tnt_tag.commands.SubCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.yogurt.common.interfaces.ICommand;
import team.yogurt.tnt_tag.Main;

import static team.yogurt.tnt_tag.Main.getPlayerManager;

public class ForceStart implements ICommand {
    @Override
    public String getCommand() {
        return "forcestart";
    }

    @Override
    public String getDescription() {
        return "Inicia forzosamente un juego";
    }

    @Override
    public String getPermission() {
        return "tnt.tag.forcestart";
    }

    @Override
    public String getSyntax() {
        return "/tnttag forcestart";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(args.length == 1){
            Player player = (Player) sender;
            getPlayerManager().setPlayer(player);
            if(getPlayerManager().isPlaying()){
                getPlayerManager().getGame().start();
            }else{
                sender.sendMessage("No estás en ninguna partida");
            }
        }
    }
}
