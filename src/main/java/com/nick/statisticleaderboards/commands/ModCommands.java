package com.nick.statisticleaderboards.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.*;
import java.util.*;

public class ModCommands {
    public static int topCommand(CommandContext<CommandSourceStack> context) {
        /*  Distance Walked 0.00m
			Damage Dealt 0.00
			Damage Taken 0.00
			Jumps
			Mob Kills
			Number of Deaths
		 */
        String stat = StringArgumentType.getString(context, "stat");
        MinecraftServer server = context.getSource().getServer();
        List<ServerPlayer> players = server.getPlayerList().getPlayers();

        switch (stat) {
            case "walked":
                try {
                    Stat walkedStat = Utilities.getStat(Stats.WALK_ONE_CM, Stats.CUSTOM);
                    Map<String, Double> playerMapDouble = new HashMap<>();
                    Utilities.addFakePlayers(playerMapDouble);

                    for(ServerPlayer player : players) {
                        playerMapDouble.put(player.getName().getString(), (double) (player.getStats().getValue(walkedStat) / 100));
                    }
                    List<Map.Entry<String, Double>> list = new ArrayList<>(playerMapDouble.entrySet());
                    list.sort(Map.Entry.<String, Double> comparingByValue().reversed());

                    String title = "| Top Distance Walked |";
                    Utilities.displayLeaderboard(context, title, list);
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "damagedealt":
                try {
                    Stat damageDealtStat = Utilities.getStat(Stats.DAMAGE_DEALT, Stats.CUSTOM);
                    Map<String, Double> playerMapDouble = new HashMap<>();
                    Utilities.addFakePlayers(playerMapDouble);

                    for(ServerPlayer player : players) {
                        playerMapDouble.put(player.getName().getString(), (double) (player.getStats().getValue(damageDealtStat) / 10));
                    }
                    List<Map.Entry<String, Double>> list = new ArrayList<>(playerMapDouble.entrySet());
                    list.sort(Map.Entry.<String, Double> comparingByValue().reversed());

                    String title = "| Top Damage Dealt |";
                    Utilities.displayLeaderboard(context, title, list);
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "damagetaken":

                break;
            case "jumps":

                break;
            case "mobkills":

                break;
            case "deaths":

                break;
            default:
                context.getSource().sendSuccess(() -> Component.literal("Please specify a stat."), false);
                break;
        }
        return 1;
    }
}
