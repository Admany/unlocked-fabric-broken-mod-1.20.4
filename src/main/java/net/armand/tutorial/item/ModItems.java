package net.armand.tutorial.item;

import net.armand.tutorial.TutorialMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item VIENAS_EUR = registerItem("vienas_eur", new Item(new FabricItemSettings()));
public static final Item AETHERIUMAS = registerItem("aetheriumas", new Item(new FabricItemSettings()));

    public static final Item AETHERIUMAS_HELMET = registerItem("aetheriumo_helmet",
    new ArmorItem(ModArmorMaterials.AETHERIUMAS, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item AETHERIUMAS_CHESTPLATE = registerItem("aetheriumo_chestplate",
            new ArmorItem(ModArmorMaterials.AETHERIUMAS, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item AETHERIUMAS_LEGGINGS = registerItem("aetheriumo_leggings",
            new ArmorItem(ModArmorMaterials.AETHERIUMAS, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item AETHERIUMAS_BOOTS = registerItem("aetheriumo_boots",
            new ArmorItem(ModArmorMaterials.AETHERIUMAS, ArmorItem.Type.BOOTS, new FabricItemSettings()));

private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(VIENAS_EUR);
        entries.add(AETHERIUMAS);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TutorialMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
