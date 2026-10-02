package com.nick.statisticleaderboards.commands;

import com.mojang.brigadier.context.CommandContext;
import com.nick.statisticleaderboards.StatisticLeaderboards;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utilities {
    public static Stat getStat(Identifier identifier, StatType statType) {
        Stat stat = statType.get(identifier);
        return stat;
    }
    public static void sendError(CommandContext<CommandSourceStack> context, Exception exception) {
        context.getSource().sendSuccess(() -> Component.literal("Something went wrong."), false);
        StatisticLeaderboards.LOGGER.info(exception.toString());
    }

    public static void displayLeaderboardDouble(CommandContext<CommandSourceStack> context, String title, List<Map.Entry<String, Double>> list) {
        context.getSource().sendSuccess(() -> Component.literal(title), false);
        list.forEach((player) -> context.getSource().sendSuccess(() -> Component.literal(player.getKey() + ": " + player.getValue()), false));
    }

    public static void displayLeaderboardInt(CommandContext<CommandSourceStack> context, String title, Stat stat) {
        MinecraftServer server = context.getSource().getServer();
        List<ServerPlayer> players = server.getPlayerList().getPlayers();
        Map<String, Integer> playerMapInt = new HashMap<>();
        Utilities.addFakePlayersInt(playerMapInt);
        for(ServerPlayer player : players) {
            playerMapInt.put(player.getName().getString(), player.getStats().getValue(stat));
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(playerMapInt.entrySet());
        list.sort(Map.Entry.<String, Integer> comparingByValue().reversed());
        context.getSource().sendSuccess(() -> Component.literal(title), false);
        list.forEach((player) -> context.getSource().sendSuccess(() -> Component.literal(player.getKey() + ": " + player.getValue()), false));
    }

    public static void addFakePlayersDouble(Map playerMapDouble) {
        playerMapDouble.put("Test1", 1000000.00);
        playerMapDouble.put("Test2", 1.00);
        playerMapDouble.put("Test3", 40000.00);
    }

    public static void addFakePlayersInt(Map playerMapInt) {
        playerMapInt.put("Test1", 1000000);
        playerMapInt.put("Test2", 1);
        playerMapInt.put("Test3", 40000);
    }
}
