package net.lemontree.model;

import net.lemontree.Constant;
import net.lemontree.SomeTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.network.packet.s2c.play.SubtitleS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;

import java.util.List;

public class ClearDrops {
    private static boolean IsRunning = false;
    public static int ClsDrops(int time, ServerCommandSource source) {

        MinecraftServer server = source.getServer();

        ServerPlayerEntity player = source.getPlayer();
        if (player == null) return -1;

        if (IsRunning) {
            player.sendMessage(
                    Text.literal("Cancel cleaning")
                            .formatted(Formatting.RED)
            );
            IsRunning = false;
            return -1;
        }

        if (time <= 0){
            ClsDrops(source.getWorld(), player);
            IsRunning = false;
        } else {
            IsRunning = true;
        }
        return 1;
    }

    private static void ClsDrops(World world ,ServerPlayerEntity player) {
        int EntityCount = 0;

        List<ItemEntity> dropEntities = world.getEntitiesByType(EntityType.ITEM,
                getWorldBoundBox(world),
                itemEntity -> true
        );

        for (ItemEntity entity : dropEntities) {
            entity.remove(Entity.RemovalReason.KILLED);
            EntityCount++;
        }

        Text text = Text.literal("DROPS")
                .formatted(Formatting.GREEN).append(
                        Text.literal(" CLEARLY!")
                                .formatted(Formatting.DARK_GREEN));

        SomeTools.SomeToolsMessage(player,
                Text.literal(
                        String.valueOf(EntityCount)
                                .formatted(Formatting.GOLD)
                ).append(Text.literal(" Drops cleared!").
                        formatted(Formatting.YELLOW)
                ));
        player.networkHandler.sendPacket(new TitleS2CPacket(Text.of("")));
        player.networkHandler.sendPacket(new SubtitleS2CPacket(text));
    }

    private static Box getWorldBoundBox(World world) {
        WorldBorder worldBorder = world.getWorldBorder();
        double minX = worldBorder.getBoundWest();
        double minY = worldBorder.getBoundNorth();
        double minZ = Constant.minZ;
        double maxX = worldBorder.getBoundEast();
        double maxY = worldBorder.getBoundSouth();
        double maxZ = Constant.maxZ;
        return new Box(minX, minY, minZ, maxX, maxY, maxZ);
    }
}
