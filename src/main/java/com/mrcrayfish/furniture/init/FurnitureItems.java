package com.mrcrayfish.furniture.init;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.items.*;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;

public class FurnitureItems
{
    /**
     * Initial Furniture
     */
    public static final Item FLESH, COOKED_FLESH, COOL_PACK;

    /**
     * Garden Update
     */
    public static final Item HAMMER;
    public static final Item ENVELOPE, ENVELOPE_SIGNED;
    public static final Item PACKAGE, PACKAGE_SIGNED;

    /**
     * Electronic Update
     */
    public static final Item INK_CARTRIDGE;

    /**
     * Kitchen Update
     */
    public static final Item KNIFE, CUP, DRINK, SOAP, SOAPY_WATER, SUPER_SOAPY_WATER;

    /**
     * Christmas Update
     */
    public static final Item LOG;

    /**
     * Outdoor Update
     */
    public static final Item SPATULA, SAUSAGE, SAUSAGE_COOKED, KEBAB, KEBAB_COOKED, CROWBAR;

    /**
     * Modern Update
     */
    public static final Item TV_REMOTE;

    /**
     * Food
     */
    public static final Item BREAD_SLICE, TOAST;

    /**
     * Misc
     */
    public static final Item RECIPE_BOOK;
    public static final Item CRAYFISH;
    public static final Item CEILING_FAN_FANS;

    /**
     * Hail Patch
     */
    public static final Item WASTED_BOOK;

    static
    {
        COOL_PACK = new ItemGeneric().setTranslationKey("item_cool_pack").setRegistryName("item_cool_pack");
        INK_CARTRIDGE = new ItemGeneric().setTranslationKey("item_ink_cartridge").setRegistryName("item_ink_cartridge");
        FLESH = new ItemFood(1, 2, false).setTranslationKey("item_flesh").setRegistryName("item_flesh").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        COOKED_FLESH = new ItemFood(4, 4, false).setTranslationKey("item_flesh_cooked").setRegistryName("item_flesh_cooked").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        ENVELOPE = new ItemEnvelope().setTranslationKey("item_envelope").setRegistryName("item_envelope");
        ENVELOPE_SIGNED = new ItemEnvelopeSigned().setTranslationKey("item_envelope_signed").setRegistryName("item_envelope_signed");
        PACKAGE = new ItemPackage().setTranslationKey("item_package").setRegistryName("item_package");
        PACKAGE_SIGNED = new ItemPackageSigned().setTranslationKey("item_package_signed").setRegistryName("item_package_signed");
        HAMMER = new Item().setTranslationKey("item_hammer").setRegistryName("item_hammer");
        BREAD_SLICE = new ItemFood(2, false).setTranslationKey("item_bread_slice").setRegistryName("item_bread_slice").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        TOAST = new ItemFood(4, false).setTranslationKey("item_toast").setRegistryName("item_toast").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        KNIFE = new ItemKnife().setMaxDamage(100).setTranslationKey("item_knife").setRegistryName("item_knife");
        CUP = new ItemCup(false).setTranslationKey("item_cup").setRegistryName("item_cup");
        DRINK = new ItemCup(true).setTranslationKey("item_drink").setRegistryName("item_drink");
        SOAP = new ItemGeneric().setTranslationKey("item_soap").setRegistryName("item_soap");
        SOAPY_WATER = new ItemGeneric().setTranslationKey("item_soap_water").setRegistryName("item_soap_water").setContainerItem(Items.BUCKET).setMaxStackSize(1);
        SUPER_SOAPY_WATER = new ItemGeneric().setTranslationKey("item_super_soap_water").setRegistryName("item_super_soap_water").setContainerItem(Items.BUCKET).setMaxStackSize(1);
        RECIPE_BOOK = new ItemRecipeBook().setTranslationKey("item_recipe_book").setRegistryName("item_recipe_book");
        CRAYFISH = new Item().setTranslationKey("item_crayfish").setRegistryName("item_crayfish").setMaxStackSize(1);
        LOG = new ItemLog(FurnitureBlocks.FIRE_PIT_OFF).setTranslationKey("item_log").setRegistryName("item_log").setMaxStackSize(16);
        SPATULA = new Item().setTranslationKey("item_spatula").setRegistryName("item_spatula").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        SAUSAGE = new ItemFood(1, false).setTranslationKey("item_sausage").setRegistryName("item_sausage").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        SAUSAGE_COOKED = new ItemFood(4, false).setTranslationKey("item_sausage_cooked").setRegistryName("item_sausage_cooked").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        KEBAB = new ItemFood(1, false).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0), 0.3F).setTranslationKey("item_kebab").setRegistryName("item_kebab").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        KEBAB_COOKED = new ItemFood(4, false).setTranslationKey("item_kebab_cooked").setRegistryName("item_kebab_cooked").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        CROWBAR = new Item().setTranslationKey("item_crow_bar").setRegistryName("item_crow_bar").setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        CEILING_FAN_FANS = new Item().setTranslationKey("ceiling_fan_fans").setRegistryName("ceiling_fan_fans");
        TV_REMOTE = new ItemTVRemote();

        WASTED_BOOK = new ItemGeneric().setTranslationKey("item_wasted_book").setRegistryName("item_wasted_book");
    }

    public static void register()
    {
        registerItem(FLESH);
        registerItem(COOKED_FLESH);
        registerItem(COOL_PACK);
        registerItem(HAMMER);
        registerItem(ENVELOPE);
        registerItem(ENVELOPE_SIGNED);
        registerItem(PACKAGE);
        registerItem(PACKAGE_SIGNED);
        registerItem(INK_CARTRIDGE);
        registerItem(BREAD_SLICE);
        registerItem(TOAST);
        registerItem(KNIFE);
        registerItem(CUP);
        registerItem(DRINK);
        registerItem(SOAP);
        registerItem(SOAPY_WATER);
        registerItem(SUPER_SOAPY_WATER);
        registerItem(RECIPE_BOOK);
        registerItem(CRAYFISH);
        registerItem(LOG);
        registerItem(SPATULA);
        registerItem(SAUSAGE);
        registerItem(SAUSAGE_COOKED);
        registerItem(KEBAB);
        registerItem(KEBAB_COOKED);
        registerItem(CROWBAR);
        registerItem(CEILING_FAN_FANS);
        registerItem(TV_REMOTE);

        registerItem(WASTED_BOOK);
    }

    private static void registerItem(Item item)
    {
        RegistrationHandler.Items.add(item);
    }
}
