package com.nick.statisticleaderboards.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ModCommands {
    public static int topCommand(CommandContext<CommandSourceStack> context) {
        /*  Distance Walked 1.72m
			Damage Dealt 0.00
			Damage Taken 0.00
			Jumps
			Mob Kills
			Number of Deaths
		 */
        String stat = StringArgumentType.getString(context, "stat");
        switch (stat) {
            case "walked":
                double distanceWalked = 0.00;
                Level level = context.getSource().getLevel();
                MinecraftServer server = level.getServer();
                List<UUID> uuids = new ArrayList<>();


                break;
            case "damagedealt":

                break;
            case "damagetaken":

                break;
            case "jumps":

                break;
            case "mobkills":

                break;
            case "deaths":

                break;
        }
        return 1;
    }
}
