package com.mrcrayfish.furniture.gui.slots;

import com.mrcrayfish.furniture.api.RecipeAPI;
import com.mrcrayfish.furniture.tileentity.TileEntityPrinter;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class SlotPrinterPaperSup extends Slot
{
    public SlotPrinterPaperSup(IInventory inventory, int slotIndex, int x, int y)
    {
        super(inventory, slotIndex, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return TileEntityPrinter.isBookPop(stack) ;
    }

}