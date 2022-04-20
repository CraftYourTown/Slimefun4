package io.github.thebusybiscuit.slimefun4.implementation.items.multiblocks.miner;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

/**
 * Our {@link OreDictionary} implementation for MC 1.16 or higher.
 *
 * @author TheBusyBiscuit
 */
class OreDictionary16 extends OreDictionary14 {

    @Override
    @ParametersAreNonnullByDefault
    public @Nonnull
    ItemStack getDrops(Material material, Random random) {
        switch (material) {
            case NETHER_GOLD_ORE:
                // In 1.16, breaking nether gold ores should get gold nuggets
                return new ItemStack(Material.GOLD_NUGGET, 2 + random.nextInt(4));
            case ANCIENT_DEBRIS:
                return new ItemStack(Material.ANCIENT_DEBRIS);
            default:
                return super.getDrops(material, random);
        }
    }

}
