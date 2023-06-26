package com.github.sniffity.registry;

import com.github.sniffity.SniffitysCreatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid= SniffitysCreatures.MODID)
public class SCCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SniffitysCreatures.MODID);

    public static final RegistryObject<CreativeModeTab> SNIFFITYS_CREATURES_TAB = CREATIVE_MODE_TABS.register("sniffitys_creatures_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.OP_BLOCKS)
            .icon(() -> SCItems.SC_ICON.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.acceptAll(SCItems.SC_TAB_ITEMS.stream().map(item -> item.get().getDefaultInstance()).toList());
                //ADD BLOCK ITEMS TOO!
            }).build());

    @SubscribeEvent
    public void buildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            //ADD CORRESPONDING ITEMS
            //event.acceptAll(SCItems.SC_TAB_ITEMS.stream().map(item -> item.get().getDefaultInstance()).toList());
        }
        //Building Blocks
        //Colored Blocks
        //Natural Blocks
        //Redstone Blocks
        //Hotbar
        //Search
        //Tools and Utilities
        //Combat
        //Food and Drinks
        //Ingredients
        //Spawn Eggs
        //OP Blocks
        //Inventory
    }
}