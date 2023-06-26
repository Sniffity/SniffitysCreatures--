package com.github.sniffity.registry;

import com.github.sniffity.SniffitysCreatures;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class SCItems {
    public static CreativeModeTab SC_TAB;

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SniffitysCreatures.MODID);

    public static final RegistryObject<Item> SC_ICON = ITEMS.register("sc_icon",
            () -> new Item(new Item.Properties()));

    public static final List<RegistryObject<? extends Item>> SC_TAB_ITEMS = List.of(
            //SC_ICON
    );
}