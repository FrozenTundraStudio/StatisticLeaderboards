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
        String statArg = StringArgumentType.getString(context, "stat");
        switch (statArg) {
            case "walked":
                try {
                    Stat stat = Utilities.getStat(Stats.WALK_ONE_CM, Stats.CUSTOM);
                    Utilities.displayLeaderboardDouble(context, "| Top Distance Walked |", stat, 100, "m");
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "damagedealt":
                try {
                    Stat stat = Utilities.getStat(Stats.DAMAGE_DEALT, Stats.CUSTOM);
                    Utilities.displayLeaderboardDouble(context, "| Top Damage Dealt |", stat, 10, "");
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "damagetaken":
                try {
                    Stat stat = Utilities.getStat(Stats.DAMAGE_TAKEN, Stats.CUSTOM);
                    Utilities.displayLeaderboardDouble(context, "| Top Damage Taken |", stat, 10, "");
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "jumps":
                try {
                    Stat stat = Utilities.getStat(Stats.JUMP, Stats.CUSTOM);
                    Utilities.displayLeaderboardInt(context, "| Top Jumps |", stat);
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "mobkills":
                try {
                    Stat stat = Utilities.getStat(Stats.MOB_KILLS, Stats.CUSTOM);
                    Utilities.displayLeaderboardInt(context, "| Top Mob Kills |", stat);
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "deaths":
                try {
                    Stat stat = Utilities.getStat(Stats.DEATHS, Stats.CUSTOM);
                    Utilities.displayLeaderboardInt(context, "| Top Deaths |", stat);
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
