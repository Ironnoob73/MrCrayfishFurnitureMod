package com.mrcrayfish.furniture.tileentity;

import com.mrcrayfish.furniture.api.RecipeAPI;
import com.mrcrayfish.furniture.gui.containers.ContainerPrinter;
import com.mrcrayfish.furniture.init.FurnitureItems;
import com.mrcrayfish.furniture.util.TileEntityUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.ArrayUtils;

public class TileEntityPrinter extends TileEntityFurniture implements ISidedInventory, ITickable
{
    private static final int[] slots_top = new int[]{3};
    private static final int[] slots_bottom = new int[]{2, 1};
    private static final int[] slots_sides = new int[]{1};

    public int printerPrintTime;
    public int currentItemPrintTime;
    public int printingTime;
    public int totalCookTime;

    public TileEntityPrinter()
    {
        super("printer", 4);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);
        this.printerPrintTime = tagCompound.getShort("BurnTime");
        this.printingTime = tagCompound.getShort("CookTime");
        this.totalCookTime = tagCompound.getShort("CookTimeTotal");
        this.currentItemPrintTime = tagCompound.getInteger("CurrentTimePrintTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        tagCompound.setShort("BurnTime", (short) this.printerPrintTime);
        tagCompound.setShort("CookTime", (short) this.printingTime);
        tagCompound.setShort("CookTimeTotal", (short) this.totalCookTime);
        tagCompound.setInteger("CurrentTimePrintTime", currentItemPrintTime);
        return tagCompound;
    }

    public boolean isPrinting()
    {
        return this.printerPrintTime > 0;
    }

    @Override
    public void update()
    {
        boolean flag = this.printingTime > 0;

        boolean flag1 = false;

        if(this.isPrinting() && this.canPrint() && this.printingTime > 0)
        {
            --this.printerPrintTime;
        }
//Slot0=Origin    Slot1=Ink    Slot2=Output    Slot3=BlankBook
        if(!this.isPrinting() && (getStackInSlot(1).isEmpty() || getStackInSlot(0).isEmpty()))
        {
            if(!this.isPrinting() && this.printingTime > 0)
                if (getStackInSlot(2).isEmpty()){//When ink out, print Waste
                    this.printingTime = 0;
                    setInventorySlotContents(2, new ItemStack(FurnitureItems.WASTED_BOOK));
                }
        }
        else
        {
            if(!this.isPrinting() && this.canPrint())
            {
                this.currentItemPrintTime = this.printerPrintTime = getItemPrintTime(getStackInSlot(1));
                this.totalCookTime = this.getPrintTime(getStackInSlot(0));

                if(this.isPrinting())
                {
                    flag1 = true;

                    if(!getStackInSlot(1).isEmpty())
                    {
                        getStackInSlot(1).shrink(1);

                        if(getStackInSlot(1).getCount() == 0)
                        {
                            setInventorySlotContents(1, getStackInSlot(1).getItem().getContainerItem(getStackInSlot(1)));
                        }
                    }
                }
            }

            if(this.isPrinting() && this.canPrint())
            {
                if (this.printingTime==0 && !getStackInSlot(3).isEmpty()){//Consume book when start
                    getStackInSlot(3).shrink(1);
                    ++this.printingTime;
                    this.totalCookTime = this.getPrintTime(getStackInSlot(0));
                }
                else if (this.printingTime>0){
                    ++this.printingTime;
                    this.totalCookTime = this.getPrintTime(getStackInSlot(0));
                }
                if(!flag)
                {
                    TileEntityUtil.markBlockForUpdate(world, pos);
                }

                if(this.printingTime >= this.totalCookTime)
                {
                    this.printingTime = 0;
                    this.printItem();
                    --this.printerPrintTime;//Consume 1 ink
                    flag1 = true;
                }
            }
            else
            {
                this.printingTime = 0;
                if(!(getStackInSlot(3).isEmpty())&&(getStackInSlot(2).isEmpty())) {//Let it broken?
                    getStackInSlot(3).shrink(1);
                    setInventorySlotContents(2, new ItemStack(FurnitureItems.WASTED_BOOK));
                }
            }
        }

        if(flag1)
        {
            this.markDirty();
        }

        if(flag && printingTime == 0)
        {
            world.updateComparatorOutputLevel(pos, blockType);
        }
    }

    public int getPrintTime(ItemStack stack)
    {
        if(stack != null && stack.getItem() == Items.ENCHANTED_BOOK)
        {
            return 10000;
        }
        return 1000;
    }

    private boolean canPrint()
    {
        if(getStackInSlot(0).isEmpty() && getStackInSlot(1).isEmpty())
        {
            return false;
        }
        return RecipeAPI.getPrinterRecipeFromInput(getStackInSlot(0)) != null && getStackInSlot(2).isEmpty();
    }

    public void printItem()
    {
        if(this.canPrint())
        {
            ItemStack stack = getStackInSlot(0);
            if(getStackInSlot(2).isEmpty())
            {
                setInventorySlotContents(2, stack.copy());
            }
        }
    }

    public static int getItemPrintTime(ItemStack stack)
    {
        if(stack == null)
        {
            return 0;
        }
        Item i = stack.getItem();
        if(stack.getItemDamage() == 0)
        {
            if(i == Items.DYE) return 1000;
            if(i == FurnitureItems.INK_CARTRIDGE) return 5000;
        }
        return 0;
    }
    public static boolean isBookPop(ItemStack stack)
    {
        if(stack == null)
        {
            return false;
        }
        if(ArrayUtils.contains(OreDictionary.getOreIDs(stack),OreDictionary.getOreID("book"))) return true;
        return stack.getItem() == Items.BOOK;
    }

    public static boolean isItemFuel(ItemStack stack)
    {
        return getItemPrintTime(stack) > 0;
    }

    @Override
    public int getField(int id)
    {
        switch(id)
        {
            case 0:
                return this.printerPrintTime;
            case 1:
                return this.currentItemPrintTime;
            case 2:
                return this.printingTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value)
    {
        switch(id)
        {
            case 0:
                this.printerPrintTime = value;
                break;
            case 1:
                this.currentItemPrintTime = value;
                break;
            case 2:
                this.printingTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }

    @Override
    public int getFieldCount()
    {
        return 4;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return index != 2 && (index != 1 || (isItemFuel(stack) ))&& (index != 3 || (isBookPop(stack) ));
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? slots_bottom : (side == EnumFacing.UP ? slots_top : slots_sides);
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if(direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if(item != Items.WATER_BUCKET )
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        this.fillWithLoot(playerIn);
        return new ContainerPrinter(playerInventory, this);
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    @javax.annotation.Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
}
