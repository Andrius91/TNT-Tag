package team.yogurt.tnt_tag.commands.SubCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.yogurt.common.interfaces.ICommand;


import static team.yogurt.tnt_tag.Main.getPlayerManager;

public class Join implements ICommand {

    @Override
    public String getCommand() {
        return "join";
    }

    @Override
    public String getDescription() {
        return "Unete a un juego";
    }

    @Override
    public String getPermission() {
        return "tnt.tag.join";
    }

    @Override
    public String getSyntax() {
        return "/tnttag join <arena>";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        getPlayerManager().setPlayer((Player) sender);
        if(!getPlayerManager().isPlaying()){
            if(args.length == 1){
                getPlayerManager().sendToRandomGame();
            }
            if(args.length == 2){
                getPlayerManager().sendToGame(args[1]);
            }
        }else{
            sender.sendMessage("Ya estás en un juego, usa /tnttag leave para salir.");
        }

    }
}
