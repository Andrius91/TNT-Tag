package team.yogurt.tnt_tag.commands.SubCommands;

import org.bukkit.command.CommandSender;
import team.yogurt.common.interfaces.ICommand;
import team.yogurt.tnt_tag.commands.SubCommands.ArenaCmds.Create;
import team.yogurt.tnt_tag.commands.SubCommands.ArenaCmds.SetSpawn;

import java.util.ArrayList;
import java.util.List;

public class Arena implements ICommand {

    List<ICommand> commands = new ArrayList<>();

    public Arena(){
        commands.add(new Create());
        commands.add(new SetSpawn());
    }
    @Override
    public String getCommand() {
        return "arena";
    }

    @Override
    public String getDescription() {
        return "Crea/Modifica una arena";
    }

    @Override
    public String getPermission() {
        return "tnt.tag.arena";
    }

    @Override
    public String getSyntax() {
        return "/tnttag arena";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(args.length > 1){
            for (ICommand cmd : this.getCommands()) {
                if (args[1].equalsIgnoreCase(cmd.getCommand())) {
                    cmd.perform(sender, args);
                    return;
                }
            }
        }else{
            for(ICommand cmd : getCommands()) {
                if(sender.hasPermission(cmd.getPermission())){
                    sender.sendMessage("&d" + cmd.getSyntax() + "&f - &7" + cmd.getDescription());
                }
            }
        }
    }

    public List<ICommand> getCommands(){
        return commands;
    }
}
