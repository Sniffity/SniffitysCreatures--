package com.github.sniffity;

import com.github.sniffity.registry.SCCreativeTab;
import com.github.sniffity.registry.SCItems;
import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import org.slf4j.Logger;

@Mod(SniffitysCreatures.MODID)
public class SniffitysCreatures
{
    public static final String MODID = "sniffityscreatures";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SniffitysCreatures()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        SCItems.ITEMS.register(modEventBus);
        SCCreativeTab.CREATIVE_MODE_TABS.register(modEventBus);
    }
}
