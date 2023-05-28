package me.efekos.simpler.blocks;

import me.efekos.simpler.items.CustomItem;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public abstract class CustomBlock extends CustomItem {
    public abstract void onPlace(BlockPlaceEvent event);
    public abstract void onBreak(BlockBreakEvent event);
}
