package net.lemontree.model;

import net.lemontree.Constant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.network.packet.s2c.play.SubtitleS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;

import java.util.List;

public class ClearDrops {
    public static int ClsDrops(int time, ServerCommandSource source) {
        World world = source.getWorld();
        ServerPlayerEntity player = source.getPlayer();

        TypeFilter<Entity, ItemEntity> filter = EntityType.ITEM;
        Box worldBounds = getWorldBoundBox(world);

        List<ItemEntity> dropEntities = world.getEntitiesByType(filter, worldBounds, itemEntity -> true);

        for (ItemEntity entity : dropEntities) {
            entity.remove(Entity.RemovalReason.KILLED);
        }

        if(player != null) {
            TitleS2CPacket titleS2CPacket = new TitleS2CPacket(Text.of(""));
            SubtitleS2CPacket subtitle = new SubtitleS2CPacket(Text.of("DROPS CLEARLY!"));
            player.networkHandler.sendPacket(titleS2CPacket);
            player.networkHandler.sendPacket(subtitle);
        }

        return 1;
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
