package me.libraryaddict.disguise.commands;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import me.libraryaddict.disguise.disguisetypes.TargetedDisguise;
import me.libraryaddict.disguise.utilities.DisguiseUtilities;
import me.libraryaddict.disguise.utilities.LibsMsg;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DisguiseListCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("libsdisguises.seecmd.disguiselist")) {
            String players = "";
            int numPlayers = 0;
            int numOther = 0;

            // Go through all disguises
            for (UUID uuid: DisguiseUtilities.getDisguises().keySet()) {
                Entity entity = Bukkit.getEntity(uuid);
                TargetedDisguise disguise = DisguiseUtilities.getMainDisguise(uuid);
                if (!(entity instanceof Player)) {
                    // Assume this is a non-player entity
                    numOther++;
                }
                else {
                    // This is a player
                    Player player = (Player) entity;
                    numPlayers++;
                    players += " " + ChatColor.AQUA + player.getName() + ChatColor.GRAY + "("
                        + disguise.getType().toReadable();

                    // Special treatment if the disguise is a player
                    if (disguise.getType() == DisguiseType.PLAYER && disguise instanceof PlayerDisguise) {
                        players += ":" + ((PlayerDisguise) disguise).getName();
                    }

                    players += ")";
                }
            }

            sender.sendMessage(LibsMsg.DLIST_PLAYERS.get(String.valueOf(numPlayers), players));
            sender.sendMessage(LibsMsg.DLIST_ENTITIES.get(String.valueOf(numOther)));
        } else {
            sender.sendMessage(LibsMsg.NO_PERM.get());
        }

        return true;
    }
}
