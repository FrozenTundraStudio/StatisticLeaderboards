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
                    Utilities.displayLeaderboardDouble(context, "| Top Distance Walked |", Stats.WALK_ONE_CM, Stats.CUSTOM, 100, "m");
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "damagedealt":
                try {
                    Utilities.displayLeaderboardDouble(context, "| Top Damage Dealt |", Stats.DAMAGE_DEALT, Stats.CUSTOM, 10, "");
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "damagetaken":
                try {
                    Utilities.displayLeaderboardDouble(context, "| Top Damage Taken |", Stats.DAMAGE_TAKEN, Stats.CUSTOM, 10, "");
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "jumps":
                try {
                    Utilities.displayLeaderboardInt(context, "| Top Jumps |", Stats.JUMP, Stats.CUSTOM);
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "mobkills":
                try {
                    Utilities.displayLeaderboardInt(context, "| Top Mob Kills |", Stats.MOB_KILLS, Stats.CUSTOM);
                } catch (Exception exception) {
                    Utilities.sendError(context, exception);
                }
                break;
            case "deaths":
                try {
                    Utilities.displayLeaderboardInt(context, "| Top Deaths |", Stats.DEATHS, Stats.CUSTOM);
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
