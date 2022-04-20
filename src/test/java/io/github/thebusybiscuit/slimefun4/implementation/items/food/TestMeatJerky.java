package io.github.thebusybiscuit.slimefun4.implementation.items.food;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.test.TestUtilities;
import io.github.thebusybiscuit.slimefun4.test.presets.SlimefunItemTest;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.*;

class TestMeatJerky implements SlimefunItemTest<MeatJerky> {

    private static ServerMock server;
    private static Slimefun plugin;

    @BeforeAll
    public static void load() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(Slimefun.class);
    }

    @AfterAll
    public static void unload() {
        MockBukkit.unmock();
    }

    @Override
    public MeatJerky registerSlimefunItem(Slimefun plugin, String id) {
        SlimefunItemStack item = new SlimefunItemStack(id, Material.COOKED_BEEF, "&5Test Jerky");
        MeatJerky meat = new MeatJerky(TestUtilities.getItemGroup(plugin, "test_jerky"), item, RecipeType.NULL, new ItemStack[9]);
        meat.register(plugin);
        return meat;
    }

    @Test
    @DisplayName("Test Meat Jerky giving extra saturation")
    void testConsumptionBehaviour() {
        PlayerMock player = server.addPlayer();
        MeatJerky jerky = registerSlimefunItem(plugin, "TEST_MEAT_JERKY");
        float saturation = player.getSaturation();

        simulateConsumption(player, jerky);

        // Saturation should have increased
        Assertions.assertTrue(player.getSaturation() > saturation);
    }

}
