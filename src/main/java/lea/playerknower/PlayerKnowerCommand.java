package lea.playerknower;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import lea.playerknower.util.DescData;
import lea.playerknower.util.IEntityDataSaver;
import net.minecraft.command.argument.ColorArgumentType;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.message.MessageType;
import net.minecraft.server.ServerMetadata;
import net.minecraft.server.command.ServerCommandSource;

import static com.mojang.brigadier.arguments.StringArgumentType.*;
import static net.minecraft.command.argument.ColorArgumentType.getColor;
import static net.minecraft.server.command.CommandManager.*;
import static net.minecraft.server.command.CommandManager.argument;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Objects;

public final class PlayerKnowerCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(literal("peopleknower")
                .then(literal("display")
                        .then(literal("long")
                                .then(argument("player", EntityArgumentType.player()).executes((context -> {
                                                    context.getSource().getWorld();
                                                    return 1;
                                                }))
                                                .executes(ctx -> display(ctx.getSource(), EntityArgumentType.getPlayer(ctx, "player")))
                                )
                        )
                        .then(literal("short")
                                .then(argument("player", EntityArgumentType.player()).executes((context -> {
                                                    context.getSource().getWorld();
                                                    return 1;
                                                }))
                                                .executes(ctx -> ShortDisplay(ctx.getSource(), EntityArgumentType.getPlayer(ctx, "player")))
                                )
                        )


                )
                .then(literal("set")
                        .then(literal("long")
                                .then(argument("description", greedyString())
                                        .executes(ctx -> {
                                            DescData.setDesc((IEntityDataSaver) ctx.getSource().getPlayer(), getString(ctx, "description"));
                                            ctx.getSource().sendMessage(Text.translatable("commands.peopleknower.set", getString(ctx, "description")));
                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        )
                        .then(literal("short")
                                .then(argument("description", greedyString())
                                        .executes(ctx -> {
                                            DescData.setShortDesc((IEntityDataSaver) ctx.getSource().getPlayer(), getString(ctx, "description"));
                                            ctx.getSource().sendMessage(Text.translatable("commands.peopleknower.set.short", getString(ctx, "description")));
                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        )
                )
                .then(literal("delete")
                                .executes(ctx -> {
                                    DescData.setDesc((IEntityDataSaver) ctx.getSource().getPlayer(), "");
                                    DescData.setShortDesc((IEntityDataSaver) ctx.getSource().getPlayer(), "");
                                    ctx.getSource().sendMessage(Text.translatable("commands.peopleknower.delete"));
                                    return Command.SINGLE_SUCCESS;
                                })
                )
        );
    }

    public static int display(ServerCommandSource source, ServerPlayerEntity player ) {
        String desc = DescData.readDesc((IEntityDataSaver) player);
        if (desc == null || desc == "") {
            source.sendMessage(Text.translatable("commands.peopleknower.display.none", player.getDisplayName()));
        }
        else {
            source.sendMessage(Text.translatable("commands.peopleknower.display", player.getDisplayName(), DescData.readDesc((IEntityDataSaver) player)));
        }
        return Command.SINGLE_SUCCESS;
    }

    public static int ShortDisplay(ServerCommandSource source, ServerPlayerEntity player ) {
        String desc = DescData.readShortDesc((IEntityDataSaver) player);
        if (desc == null || desc == "") {
            source.sendMessage(Text.translatable("commands.peopleknower.display.none.short", player.getDisplayName()));
        }
        else {
            source.sendMessage(Text.translatable("commands.peopleknower.display.short", player.getDisplayName(), DescData.readShortDesc((IEntityDataSaver) player)));
        }
        return Command.SINGLE_SUCCESS;
    }
}