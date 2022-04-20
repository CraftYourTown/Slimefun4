package io.github.thebusybiscuit.slimefun4.utils;

import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.apache.commons.lang.Validate;
import org.bukkit.World;

import javax.annotation.Nonnull;

/**
 * This class holds utilities for {@link World}. This will become especially useful with the changes
 * in the "Cliffs and Caves" update.
 *
 * @author Walshy
 */
public final class WorldUtils {

    private WorldUtils() {
    }

    /**
     * Get the minimum Y of the given {@link World}. This is a feature introduced in Minecraft 1.17
     * and introduced into the Bukkit API in Minecraft 1.16.
     *
     * @param world The world of which to get minimum Y in.
     * @return The minimum Y of the given world.
     */
    public static int getMinHeight(@Nonnull World world) {
        Validate.notNull(world, "World cannot be null!");

        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_16)) {
            return world.getMinHeight();
        } else {
            // Default to zero for pre-1.16 worlds
            return 0;
        }
    }
}
