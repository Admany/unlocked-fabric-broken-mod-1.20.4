package net.armand.tutorial.item;

import net.armand.tutorial.TutorialMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup ADMANY_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TutorialMod.MOD_ID, "admany"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.admany"))
                    .icon(() -> new ItemStack(ModItems.VIENAS_EUR)).entries((displayContext, entries) -> {
                        entries.add(ModItems.VIENAS_EUR);
                        entries.add(ModItems.AETHERIUMAS);


                        entries.add(ModItems.AETHERIUMAS_HELMET);
                        entries.add(ModItems.AETHERIUMAS_CHESTPLATE);
                        entries.add(ModItems.AETHERIUMAS_LEGGINGS);
                        entries.add(ModItems.AETHERIUMAS_BOOTS);
                    }).build());


    public static void registerItemGroups() {
        TutorialMod.LOGGER.info("Registering Item Groups for" + TutorialMod.MOD_ID);
    }
}
