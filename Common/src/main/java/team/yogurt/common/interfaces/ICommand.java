package team.yogurt.common.interfaces;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public interface ICommand {
    String getCommand();
    String getDescription();
    String getPermission();
    String getSyntax();
    void perform(CommandSender sender, String[] args);
}
