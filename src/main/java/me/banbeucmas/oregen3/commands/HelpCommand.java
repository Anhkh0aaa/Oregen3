package me.banbeucmas.oregen3.commands;

import me.banbeucmas.oregen3.Oregen3;
import me.banbeucmas.oregen3.utils.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.banbeucmas.oregen3.utils.StringUtils.LABEL;

public class HelpCommand extends AbstractCommand {
    HelpCommand(final CommandSender sender, final String label) {
        super("oregen3.help", sender, label, null);
    }

    @Override
    protected ExecutionResult now() {
        if (!(getSender() instanceof Player)) {
            return ExecutionResult.NOT_PLAYER;
        }

        sendHelp(getSender());
        return ExecutionResult.DONT_CARE;
    }

    private void sendHelp(final CommandSender sender) {
        sender.sendMessage(StringUtils.getPrefixString(LABEL.matcher(Oregen3.getPlugin().getConfig().getString("messages.commands.help")).replaceAll(getLabel()), getPlayer()));
        if (sender.hasPermission("oregen3.reload")) {
            sender.sendMessage(StringUtils.getPrefixString(LABEL.matcher(Oregen3.getPlugin().getConfig().getString("messages.commands.reload")).replaceAll(getLabel()), getPlayer()));
        }
        if (sender.hasPermission("oregen3.information")) {
            sender.sendMessage(StringUtils.getPrefixString(LABEL.matcher(Oregen3.getPlugin().getConfig().getString("messages.commands.info")).replaceAll(getLabel()), getPlayer()));
        }
        if (sender.hasPermission("oregen3.debug")) {
            sender.sendMessage(StringUtils.getPrefixString(LABEL.matcher(Oregen3.getPlugin().getConfig().getString("messages.commands.debug")).replaceAll(getLabel()), getPlayer()));
        }
        if (sender.hasPermission("oregen3.edit")) {
            sender.sendMessage(StringUtils.getPrefixString(LABEL.matcher(Oregen3.getPlugin().getConfig().getString("messages.commands.edit")).replaceAll(getLabel()), getPlayer()));
        }
    }
}
