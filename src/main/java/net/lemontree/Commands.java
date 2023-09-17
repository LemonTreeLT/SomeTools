package net.lemontree;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.lemontree.model.ClearDrops;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class Commands {
    public static void regClsCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                dispatcher.register(literal("cls")
                        .requires(source -> source.hasPermissionLevel(0))
                        .then(argument("time", integer())
                                .executes(ctx -> ClearDrops.ClsDrops(getInteger(ctx, "time"), ctx.getSource())))
                        .executes(source -> ClearDrops.ClsDrops(-1, source.getSource()))
                ));
    }
}
