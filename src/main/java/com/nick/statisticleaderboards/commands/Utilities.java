package com.nick.statisticleaderboards.commands;

import com.mojang.brigadier.context.CommandContext;
import com.nick.statisticleaderboards.StatisticLeaderboards;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utilities {
    public static void sendError(CommandContext<CommandSourceStack> context, Exception exception) {
        context.getSource().sendSuccess(() -> Component.literal("Something went wrong."), false);
        StatisticLeaderboards.LOGGER.info(exception.toString());
    }

    public static void displayLeaderboardDouble(CommandContext<CommandSourceStack> context, String title, Identifier identifier, StatType statType, int divisor, String unit) {
        ServerLevel level = context.getSource().getLevel();
        Map<String, Double> playerMapDouble = new HashMap<>();
        playerMapDouble.put("Test1", 1000000.11);
        playerMapDouble.put("Test2", 1.35);
        playerMapDouble.put("Test3", 40000.45);
        Stat stat = statType.get(identifier);
        for(ServerPlayer player : PlayerLookup.level(level)) {
            playerMapDouble.put(player.getName().getString(), (double) (player.getStats().getValue(stat) / divisor));
        }
        List<Map.Entry<String, Double>> list = new ArrayList<>(playerMapDouble.entrySet());
        list.sort(Map.Entry.<String, Double> comparingByValue().reversed());
        MutableComponent leaderboardTitle = Component.literal(title)
                        .withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD);
        context.getSource().sendSuccess(() -> leaderboardTitle, false);
        int position = 0;
        for(Map.Entry<String, Double> player : list) {
            position++;
            MutableComponent leaderboardPlayer = Component.literal(position + ". " +  player.getKey() + ": " + player.getValue() + unit)
                    .withStyle(ChatFormatting.GOLD);
            context.getSource().sendSuccess(() -> leaderboardPlayer, false);
        }
    }

    public static void displayLeaderboardInt(CommandContext<CommandSourceStack> context, String title, Identifier identifier, StatType statType) {
        ServerLevel level = context.getSource().getLevel();
        Map<String, Integer> playerMapInt = new HashMap<>();
        playerMapInt.put("Test1", 1000000);
        playerMapInt.put("Test2", 1);
        playerMapInt.put("Test3", 40000);
        Stat stat = statType.get(identifier);
        for(ServerPlayer player : PlayerLookup.level(level)) {
            playerMapInt.put(player.getName().getString(), player.getStats().getValue(stat));
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(playerMapInt.entrySet());
        list.sort(Map.Entry.<String, Integer> comparingByValue().reversed());
        MutableComponent leaderboardTitle = Component.literal(title)
                .withStyle(ChatFormatting.AQUA, ChatFormatting.BOLD);
        context.getSource().sendSuccess(() -> leaderboardTitle, false);
        int position = 0;
        for(Map.Entry<String, Integer> player : list) {
            position++;
            MutableComponent leaderboardPlayer = Component.literal(position + ". " +  player.getKey() + ": " + player.getValue())
                    .withStyle(ChatFormatting.GOLD);
            context.getSource().sendSuccess(() -> leaderboardPlayer, false);
        }
    }
}
