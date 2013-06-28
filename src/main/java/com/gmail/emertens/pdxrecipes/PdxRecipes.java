package com.gmail.emertens.pdxrecipes;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class PdxRecipes extends JavaPlugin {

	Set<String> playersNotified = new HashSet<String>();

	@Override
	public void onDisable() {
		getServer().resetRecipes();
	}

	@Override
	public void onEnable() {
		final Server server = getServer();
		server.addRecipe(chiseledStoneRecipe());
		server.addRecipe(chiseledSandstoneRecipe());
		server.addRecipe(lilypadRecipe());
		server.addRecipe(crackedStoneRecipe());
		server.addRecipe(secretMusicDisk());

		final Listener listener = new Listener() {
			@EventHandler(ignoreCancelled = true)
			public void onInteract(final PlayerInteractEvent event) {
				final Player player = event.getPlayer();

				if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
						&& event.getClickedBlock().getType().equals(Material.WORKBENCH)
						&& !playersNotified.contains(player.getName())) {
					playersNotified.add(event.getPlayer().getName());
					player.sendMessage(ChatColor.GREEN + "Learn about PDXMC's custom recipes on the Second Floor of the hub.");
				}
			}
		};

		server.getPluginManager().registerEvents(listener, this);
	}

	private static Recipe chiseledStoneRecipe() {
		final ItemStack craft = new ItemStack(Material.SMOOTH_BRICK, 2);
		craft.setDurability((byte)3);

		final ShapedRecipe recipe = new ShapedRecipe(craft);
		recipe.shape("sss","s s","sss")
		      .setIngredient('s', Material.STONE);

		return recipe;
	}

	private static Recipe chiseledSandstoneRecipe() {
		final ItemStack craft = new ItemStack(Material.SANDSTONE, 4);
		craft.setDurability((byte)1);

		final ShapedRecipe recipe = new ShapedRecipe(craft);
		recipe.shape("sss","s s","sss")
		      .setIngredient('s', Material.SANDSTONE);

		return recipe;
	}

	private static Recipe lilypadRecipe() {
		final ItemStack craft = new ItemStack(Material.WATER_LILY);

		final ShapedRecipe recipe = new ShapedRecipe(craft);
		recipe.shape("LLL")
		      .setIngredient('L', Material.LEAVES);

		return recipe;
	}

	private static Recipe crackedStoneRecipe() {
		final ItemStack craft = new ItemStack(Material.SMOOTH_BRICK, 2);
		craft.setDurability((byte)2);

		final ShapedRecipe recipe = new ShapedRecipe(craft);
		recipe.shape("ss","sc")
		      .setIngredient('s', Material.SMOOTH_BRICK)
		      .setIngredient('c', Material.COBBLESTONE);

		return recipe;
	}

	private static Recipe secretMusicDisk() {
		final ItemStack craft = new ItemStack(Material.RECORD_11);

		final ShapelessRecipe recipe = new ShapelessRecipe(craft);
		recipe.addIngredient(Material.RECORD_3)
		      .addIngredient(Material.RECORD_4)
		      .addIngredient(Material.RECORD_5)
		      .addIngredient(Material.RECORD_6)
		      .addIngredient(Material.RECORD_7)
		      .addIngredient(Material.RECORD_8)
		      .addIngredient(Material.RECORD_9)
		      .addIngredient(Material.RECORD_10)
		      .addIngredient(Material.RECORD_12);

		return recipe;
	}
}
