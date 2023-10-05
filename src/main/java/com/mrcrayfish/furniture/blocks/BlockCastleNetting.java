package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;

/**
 * Author: MrCrayfish
 */
public class BlockCastleNetting extends BlockPane
{
    public BlockCastleNetting()
    {
        super(Material.CLOTH, true);
        this.setTranslationKey("castle_netting");
        this.setRegistryName("castle_netting");
        this.setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
    }
}
