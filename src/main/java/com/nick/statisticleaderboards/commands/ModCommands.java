package com.nick.statisticleaderboards.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.nick.statisticleaderboards.StatisticLeaderboards;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
                    Identifier walked = Stats.WALK_ONE_CM;
                    StatType statType = Stats.CUSTOM;
                    Stat walkedStat = statType.get(walked);

                    Map<String, Double> playerMap = new HashMap<>();
                    playerMap.put("Test1", 1000000.00);
                    playerMap.put("Test2", 1.00);
                    playerMap.put("Test3", 400000000.00);

                    for(ServerPlayer player : players) {
                        playerMap.put(player.getName().getString(), (double) (player.getStats().getValue(walkedStat) / 100));
                    }
                    List<Map.Entry<String, Double>> list = new ArrayList<>(playerMap.entrySet());
                    list.sort(Map.Entry.<String, Double> comparingByValue().reversed());

                    context.getSource().sendSuccess(() -> Component.literal("| Top Distance Walked |"), false);
                    list.forEach((player) -> context.getSource().sendSuccess(() -> Component.literal(player.getKey() + ": " + player.getValue() + "m"), false));
                } catch (Exception exception) {
                    context.getSource().sendSuccess(() -> Component.literal("Something went wrong."), false);
                    StatisticLeaderboards.LOGGER.info(exception.toString());
                }
                break;
            case "damagedealt":
                try {
                    Identifier damageDealt = Stats.DAMAGE_DEALT;
                    StatType statType = Stats.CUSTOM;
                    Stat damageDealtStat = statType.get(damageDealt);

                    Map<String, Double> playerMap = new HashMap<>();
                    playerMap.put("Test1", 1000000.00);
                    playerMap.put("Test2", 1.00);
                    playerMap.put("Test3", 400000000.00);

                    for(ServerPlayer player : players) {
                        playerMap.put(player.getName().getString(), (double) (player.getStats().getValue(damageDealtStat) / 10));
                    }
                    List<Map.Entry<String, Double>> list = new ArrayList<>(playerMap.entrySet());
                    list.sort(Map.Entry.<String, Double> comparingByValue().reversed());

                    context.getSource().sendSuccess(() -> Component.literal("| Top Damage Dealt |"), false);
                    list.forEach((player) -> context.getSource().sendSuccess(() -> Component.literal(player.getKey() + ": " + player.getValue()), false));
                } catch (Exception exception) {
                    context.getSource().sendSuccess(() -> Component.literal("Something went wrong."), false);
                    StatisticLeaderboards.LOGGER.info(exception.toString());
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
//            default:
//                context.getSource().sendSuccess(() -> Component.literal("Please specify a stat."), false);
//                break;
        }
        return 1;
    }
}
