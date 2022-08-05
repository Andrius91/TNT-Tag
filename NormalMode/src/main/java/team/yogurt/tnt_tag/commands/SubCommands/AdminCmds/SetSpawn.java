package team.yogurt.tnt_tag.commands.SubCommands.AdminCmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.yogurt.common.interfaces.ICommand;
import team.yogurt.tnt_tag.Main;

public class SetSpawn implements ICommand {
    @Override
    public String getCommand() {
        return "setspawn";
    }

    @Override
    public String getDescription() {
        return "Set spawn";
    }

    @Override
    public String getPermission() {
        return "tnt.tag.admin.setspawn";
    }

    @Override
    public String getSyntax() {
        return "/tnttag admin setspawn";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(args.length == 2){
            Player player = (Player) sender;
            Main.getCore().setSpawn(player.getLocation());
            player.sendMessage("Has establecido el spawn satisfactoriamente.");

        }
    }
}
