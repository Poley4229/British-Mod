package net.poley.shake.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.poley.shake.ShakeMod;
import net.poley.shake.effects.ModEffects;
import net.poley.shake.item.ModItems;

// Define a custom tea item class
public class TeaItem extends Item {
    public TeaItem(Settings settings) {
        super(settings);
    }

    // Override the use method to define what happens when the item is used
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        // Check if the world is client-side
        if (!world.isClient) {
            // Apply the custom Britishness status effect for 30 seconds with level 1
            player.addStatusEffect(new StatusEffectInstance(ModEffects.BRITISHNESS, 600, 0));

            // Optionally, you can add other effects or restore hunger points
            player.getHungerManager().add(3, 0.3F); // Adds 3 hunger and 0.3 saturation
        }

        // Play a drinking sound
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 1.0F, 1.0F);

        // Decrement the item stack size by 1 (consume the item)
        if (!player.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        // Return the result of using the item
        return TypedActionResult.success(itemStack, world.isClient());
    }
}