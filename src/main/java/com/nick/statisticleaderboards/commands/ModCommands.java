package com.nick.statisticleaderboards.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
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
        String statArg = StringArgumentType.getString(context, "stat");
        switch (statArg) {
            case "walked":
                try {
                    Stat stat = Utilities.getStat(Stats.WALK_ONE_CM, Stats.CUSTOM);
                    String title = "| Top Distance Walked |";
                    int divisor = 100;
                    String unit = "m";
                    Utilities.displayLeaderboardDouble(context, title, stat, divisor, unit);
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "damagedealt":
                try {
                    Stat stat = Utilities.getStat(Stats.DAMAGE_DEALT, Stats.CUSTOM);
                    String title = "| Top Damage Dealt |";
                    int divisor = 10;
                    String unit = "";
                    Utilities.displayLeaderboardDouble(context, title, stat, divisor, unit);
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "damagetaken":
                try {
                    Stat stat = Utilities.getStat(Stats.DAMAGE_TAKEN, Stats.CUSTOM);
                    String title = "| Top Damage Taken |";
                    int divisor = 10;
                    String unit = "";
                    Utilities.displayLeaderboardDouble(context, title, stat, divisor, unit);
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "jumps":
                try {
                    Stat stat = Utilities.getStat(Stats.JUMP, Stats.CUSTOM);
                    String title = "| Top Jumps |";
                    Utilities.displayLeaderboardInt(context, title, stat);
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "mobkills":
                try {
                    Stat stat = Utilities.getStat(Stats.MOB_KILLS, Stats.CUSTOM);
                    String title = "| Top Mob Kills |";
                    Utilities.displayLeaderboardInt(context, title, stat);
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "deaths":
                try {
                    Stat stat = Utilities.getStat(Stats.DEATHS, Stats.CUSTOM);
                    String title = "| Top Deaths |";
                    Utilities.displayLeaderboardInt(context, title, stat);
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            default:
                context.getSource().sendSuccess(() -> Component.literal("Please specify a stat."), false);
                break;
        }
        return 1;
    }
}
