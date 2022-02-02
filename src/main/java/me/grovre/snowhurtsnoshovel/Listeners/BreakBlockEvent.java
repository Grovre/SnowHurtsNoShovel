package me.grovre.snowhurtsnoshovel.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Snow;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BreakBlockEvent implements Listener {

    @EventHandler
    public void OnBreakBlock(BlockBreakEvent event) {

        // Collects block info
        Block block = event.getBlock();
        Material blockType = block.getType();
        if(blockType != Material.SNOW &&
        blockType != Material.SNOW_BLOCK &&
        blockType != Material.POWDER_SNOW) {
            return;
        }
        Location blockLocation = block.getLocation();

        // Collects player info and tool info
        Player player = event.getPlayer();
        ItemStack tool = player.getInventory().getItemInMainHand();
        // In case tool wasn't in main hand
        if(tool.getType() != Material.WOODEN_SHOVEL &&
        tool.getType() != Material.STONE_SHOVEL &&
        tool.getType() != Material.IRON_SHOVEL &&
        tool.getType() != Material.DIAMOND_SHOVEL &&
        tool.getType() != Material.NETHERITE_SHOVEL &&
        tool.getType() != Material.GOLDEN_SHOVEL) {
            tool = event.getPlayer().getInventory().getItemInOffHand();
        }
        ItemMeta toolMeta = tool.getItemMeta();
        if(toolMeta == null) return;
        event.setCancelled(true);
        boolean hasSilkTouch = toolMeta.hasEnchant(Enchantment.SILK_TOUCH);

        // Will only be like this for powdered snow, so this is reasonable
        ItemStack drop = new ItemStack(Material.SNOWBALL, 3);
        
        // For mining snow block
        if(block.getType() == Material.SNOW_BLOCK) {
            if(hasSilkTouch) {
                drop = new ItemStack(Material.SNOW_BLOCK, 1);
            } else {
                drop = new ItemStack(Material.SNOWBALL, 4);
            }
        }

        // For mining snow, the flat one
        if(block.getType() == Material.SNOW) {
            int layers = ((Snow) block.getBlockData()).getLayers();
            if(hasSilkTouch) {
                drop = new ItemStack(Material.SNOW, layers);
            } else {
                drop = new ItemStack(Material.SNOWBALL, layers);
            }
        }

        block.setType(Material.AIR);
        block.getWorld().dropItemNaturally(blockLocation, drop);

    }

}
