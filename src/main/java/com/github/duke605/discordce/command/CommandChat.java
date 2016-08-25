package com.github.duke605.discordce.command;

import com.github.duke605.discordce.DiscordCE;
import com.github.duke605.discordce.lib.Preferences;
import com.github.duke605.discordce.util.Arrays;
import com.github.duke605.discordce.util.MCHelper;
import net.dv8tion.jda.client.JDAClient;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.exceptions.PermissionException;
import net.dv8tion.jda.exceptions.RateLimitedException;
import net.dv8tion.jda.exceptions.VerificationLevelException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandChat extends CommandBase
{
    @Override
    public String getCommandName() {
        return "/";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "// <message>";
    }

    @Override
    public boolean checkPermission(MinecraftServer s, ICommandSender sender) {
        return true;
    }

    @Override
    public void execute(MinecraftServer s, ICommandSender sender, String[] args) throws CommandException {
        String message = Arrays.join(args, " ");
        String channelId = Preferences.i.usingChannel;
        JDAClient c = DiscordCE.client;
        User self = c.getUserById(c.getSelfInfo().getId());

        // No channel
        if (channelId == null) {
            MCHelper.sendMessage("§cYou must specify a channel to send messages to in the configuration GUI.");
            return;
        }

        // Checking if channel still exists
        if (DiscordCE.client.getTextChannelById(channelId) == null) {
            MCHelper.sendMessage("§cThe channel you specified to send messages to no longer exists.");
            return;
        }

        // Checking if user can send messages to the channel
        try {
            c.getTextChannelById(channelId).sendMessageAsync(message, null);
        }
        catch (RateLimitedException e)
        {
            MCHelper.sendMessage("§cYou have reached the rate limit. Please try again in a moment.");
        }
        catch (PermissionException e)
        {
            MCHelper.sendMessage("§cYou do not have permission to send messages to this channel.");
        }
        catch (VerificationLevelException e)
        {
            MCHelper.sendMessage("§c" + e.getMessage());
        }
    }
}
