package com.github.sniffity.block;

import com.github.sniffity.registry.SCBlockEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.List;

public class BlockEntityWolfStatue extends BlockEntity {
    //When placed, BlockEntity will NOT be able to create a Werewolf Villager
    //This ensures no further Werewolf Villagers will be created upon manual placement of the Wolf Statue
    //The canWerewolfVillager NBT data is (manually) set to true only on the jigsaw pieces
    //Thus, the WolfStatue will only create Werewolf Villagers if it is placed by worldgen
    public boolean canCreateWerewolfVillager = false;

    public BlockEntityWolfStatue(BlockPos pos, BlockState state) {
        super(SCBlockEntities.WOLF_STATUE_BE.get(), pos, state);
    }


    @Override
    public void load(CompoundTag compoundNBT) {
        super.load(compoundNBT);
        if(compoundNBT.contains("canCreateWerewolfVillager"))
            this.canCreateWerewolfVillager = compoundNBT.getBoolean("canCreateWerewolfVillager");
    }

    @Override
    public void saveAdditional(CompoundTag compoundNBT) {
        super.saveAdditional(compoundNBT);
        compoundNBT.putBoolean("canCreateWerewolfVillager", canCreateWerewolfVillager);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, BlockEntityWolfStatue pBlockEntity) {
        if (pBlockEntity.canCreateWerewolfVillager) {
            if (pBlockEntity.getLevel() != null) {
                //get nearby villagers
                List<Villager> villagerList = pBlockEntity.getLevel().getEntitiesOfClass(Villager.class,pBlockEntity.getRenderBoundingBox().inflate(20));
                if (!(villagerList).isEmpty()) {
                    //remove Villager children from the list
                    villagerList.removeIf(testVillager -> testVillager.getAge() < 0);
                    //add the Werewolf tag to a random Villager
                    Villager werewolfVillager = villagerList.get(pBlockEntity.getLevel().getRandom().nextInt(villagerList.size()));{
                        werewolfVillager.addTag("Werewolf");
                        pBlockEntity.canCreateWerewolfVillager = false;
                        setChanged(pLevel,pPos,pState);
                        debug(werewolfVillager);
                    }
                }
            }
        }
    }

    //TODO: Remove debug method
    public static void debug(Villager werewolfVillager){
        werewolfVillager.setCustomName(Component.literal("WEREWOLF").withStyle(ChatFormatting.AQUA));
        ServerLifecycleHooks.getCurrentServer().getPlayerList().broadcastSystemMessage(Component.literal(
                "Created a WEREWOLF at: "+
                        "X: "+werewolfVillager.position().x+
                        " Y: "+werewolfVillager.position().y+
                        " Z: " + werewolfVillager.position().z),true);
    }
}