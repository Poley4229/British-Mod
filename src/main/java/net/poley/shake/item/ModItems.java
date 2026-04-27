package net.poley.shake.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.poley.shake.ShakeMod;
import net.poley.shake.item.custom.TeaItem;

public class ModItems {

    //public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()));
    public static final Item DORKSHIRE_TEA = registerItem("dorkshire_tea", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item DORKSHIRE_TEA_BAG = registerItem("dorkshire_tea_bag", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item BOILED_WATER_BOTTLE = registerItem("boiled_water_bottle", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item TEA = registerItem("tea", new TeaItem(new FabricItemSettings().maxCount(16)));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        //entries.add(RUBY);
        entries.add(DORKSHIRE_TEA);
        entries.add(DORKSHIRE_TEA_BAG);
        entries.add(BOILED_WATER_BOTTLE);
        entries.add(TEA);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ShakeMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ShakeMod.LOGGER.info("Registering Mod Items for " + ShakeMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
