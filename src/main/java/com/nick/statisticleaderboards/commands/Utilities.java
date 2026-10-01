package com.nick.statisticleaderboards.commands;

import com.mojang.brigadier.context.CommandContext;
import com.nick.statisticleaderboards.StatisticLeaderboards;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatType;
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

    public static void displayLeaderboard(CommandContext<CommandSourceStack> context, String title, List<Map.Entry<String, Double>> list) {
        context.getSource().sendSuccess(() -> Component.literal(title), false);
        list.forEach((player) -> context.getSource().sendSuccess(() -> Component.literal(player.getKey() + ": " + player.getValue()), false));
    }

    public static void addFakePlayers(Map playerMapDouble) {
        playerMapDouble.put("Test1", 1000000.00);
        playerMapDouble.put("Test2", 1.00);
        playerMapDouble.put("Test3", 40000.00);
    }
}
