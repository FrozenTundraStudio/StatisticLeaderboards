package com.nick.statisticleaderboards.data;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stat;

import java.util.UUID;

public class PlayerStatData {
    public UUID uuid;
    public String playerName;
    public GameProfile gameProfile;
    public MinecraftServer minecraftServer;
    public ServerStatsCounter statsCounter;

    public PlayerStatData(ServerPlayer serverPlayer) {
        this.uuid = serverPlayer.getUUID();
        this.playerName = serverPlayer.getName().toString();
    }
}
