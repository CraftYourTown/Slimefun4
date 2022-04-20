package io.github.thebusybiscuit.slimefun4.api.items.settings;

import be.seeseemelk.mockbukkit.MockBukkit;
import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.test.TestUtilities;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

class TestMaterialTagSetting {

    private static Slimefun plugin;
    private static Tag<Material> tag;

    @BeforeAll
    public static void load() {
        MockBukkit.mock();
        plugin = MockBukkit.load(Slimefun.class);
        tag = Tag.LOGS;
    }

    @AfterAll
    public static void unload() {
        MockBukkit.unmock();
    }

    @Test
    @DisplayName("Test Constructor")
    void testConstructorValidation() {
        SlimefunItem item = TestUtilities.mockSlimefunItem(plugin, "MATERIAL_SETTING_TEST_0", new CustomItemStack(Material.DIAMOND, "&cTest"));
        MaterialTagSetting setting = new MaterialTagSetting(item, "test", tag);
        Assertions.assertEquals(tag, setting.getDefaultTag());
    }

    @Test
    @DisplayName("Test illegal values")
    void testIllegalValues() {
        SlimefunItem item = TestUtilities.mockSlimefunItem(plugin, "MATERIAL_SETTING_TEST", new CustomItemStack(Material.DIAMOND, "&cTest"));
        MaterialTagSetting setting = new MaterialTagSetting(item, "test", tag);

        item.addItemSetting(setting);
        item.register(plugin);

        List<String> materials = Arrays.asList("I_DO_NOT_EXIST");
        Assertions.assertThrows(IllegalArgumentException.class, () -> setting.update(materials));
        Assertions.assertThrows(IllegalArgumentException.class, () -> setting.update(null));
    }

    @Test
    @DisplayName("Test allowed value")
    void testAllowedValue() {
        SlimefunItem item = TestUtilities.mockSlimefunItem(plugin, "MATERIAL_SETTING_TEST_2", new CustomItemStack(Material.DIAMOND, "&cTest"));
        MaterialTagSetting setting = new MaterialTagSetting(item, "test", tag);

        item.addItemSetting(setting);
        item.register(plugin);

        List<String> tagContents = tag.getValues().stream().map(Material::name).collect(Collectors.toList());
        Assertions.assertTrue(new HashSet<>(tagContents).equals(new HashSet<>(setting.getValue())));

        List<String> materials = Arrays.asList(Material.REDSTONE.name(), Material.DIAMOND.name());
        setting.update(materials);
        Assertions.assertIterableEquals(materials, setting.getValue());
    }
}
