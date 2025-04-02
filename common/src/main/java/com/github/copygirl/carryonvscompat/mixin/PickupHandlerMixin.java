package com.github.copygirl.carryonvscompat.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import tschipp.carryon.common.carry.PickupHandler;

@Mixin(PickupHandler.class)
public abstract class PickupHandlerMixin {
    @Redirect(method = "canCarryGeneral", at = @At(value = "INVOKE",
              target = "Lnet/minecraft/world/phys/Vec3;distanceTo(Lnet/minecraft/world/phys/Vec3;)D"))
    private static double CarryOnVSCompat$distanceTo(Vec3 instance, Vec3 target, ServerPlayer player) {
        // We can ignore "instance" because it's actually the player's position. (And we need the level anyway.)
        return Math.sqrt(VSGameUtilsKt.squaredDistanceToInclShips(player, target.x, target.y, target.z));
    }

    // This nuclear option would've worked too.
    // Keeping the code around just because.

    // @Overwrite
    // public static boolean canCarryGeneral(ServerPlayer player, Vec3 pos)
    // {
    //     if (!player.getMainHandItem().isEmpty() || !player.getOffhandItem().isEmpty()) return false;
    //     if (CarryOnVSCompat$distanceTo(pos, player) > Constants.COMMON_CONFIG.settings.maxDistance) return false;
    //
    //     CarryOnData carry = CarryOnDataManager.getCarryData(player);
    //     if (carry.isCarrying()) return false;
    //     if (!carry.isKeyPressed()) return false;
    //     if (player.tickCount == carry.getTick()) return false;
    //
    //     GameType gamemode = player.gameMode.getGameModeForPlayer();
    //     if (gamemode == GameType.SPECTATOR || gamemode == GameType.ADVENTURE) return false;
    //
    //     return true;
    // }
}
