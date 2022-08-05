package team.yogurt.tnt_tag.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import team.yogurt.common.interfaces.ICommand;
import team.yogurt.tnt_tag.commands.SubCommands.*;

import java.util.ArrayList;
import java.util.List;

public class TNTTag implements CommandExecutor {

    private final List<ICommand> commands = new ArrayList<>();

    public TNTTag() {
        commands.add(new Join());
        commands.add(new Leave());
        commands.add(new ForceStart());
        commands.add(new Admin());
        commands.add(new Arena());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("tnt.tag.game")){
            if (args.length > 0) {
                for (ICommand cmd : getCommands()) {
                    if (args[0].equalsIgnoreCase(cmd.getCommand())) {
                        cmd.perform(sender, args);
                        return true;
                    }
                }
            }else{
                for(ICommand cmd : getCommands()) {
                    if(sender.hasPermission(cmd.getPermission())){
                        sender.sendMessage(cmd.getSyntax() + " - " + cmd.getDescription());
                    }
                }
            }
        }else{
            sender.sendMessage("No tienes permisos pa");
        }
        return true;
    }

    public List<ICommand> getCommands() {
        return commands;
    }



}

