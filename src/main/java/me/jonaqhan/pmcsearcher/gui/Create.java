package me.jonaqhan.pmcsearcher.gui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.jonaqhan.pmcsearcher.utils.Chat;
import me.jonaqhan.pmcsearcher.website.Data;

public class Create {

	public ItemStack createItem(Data data, String[] colors) {

		ItemStack item = new ItemStack(Material.PLAYER_HEAD);

		item.setItemMeta(getSkull(item));

		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(Chat.color("#1CD010information"));
		meta.setLore(getLore(data, colors));

		item.setItemMeta(meta);

		return item;
	}

	public Inventory createGUI(Player p, Data data) {

		String[] colors = getColor();
		Inventory inv = Bukkit.createInventory(null, 27, Chat.color(colors[0] + "User " + colors[1] + data.getUser()));

		inv.setItem(13, createItem(data, colors));

		return inv;

	}

	public SkullMeta getSkull(ItemStack item) {
		SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		profile.getProperties().put("textures", new Property("textures", new String(
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=")));

		Field profileField = null;

		try {
			profileField = skullMeta.getClass().getDeclaredField("profile");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		profileField.setAccessible(true);

		try {
			profileField.set(skullMeta, profile);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return skullMeta;

	}

	public String[] getColor() {
		Map<String, String> colors = new HashMap<>();
		colors.put("#DC01DC", "#FF5957");
		colors.put("#279FF1", "#2EF650");
		colors.put("#FFA600", "#EFEB24");
		colors.put("#00FFDB", "#1DD9E7");

		Random r = new Random();

		int number = r.nextInt(4 + 0) - 0;

		int index = -1;

		for (Entry<String, String> entry : colors.entrySet()) {
			index++;

			if (index == number) {

				return new String[] { entry.getKey(), entry.getValue() };

			}

		}

		return null;
	}

	public List<String> getLore(Data data, String[] colors) {

		List<String> lore = new ArrayList<>();
		lore.add(Chat.color(colors[0] + "user: " + colors[1] + data.getUser()));
		lore.add(Chat.color(colors[0] + "level: " + colors[1] + data.getLevel().replace("Level ", "")));
		lore.add(Chat.color(colors[0] + "title: " + colors[1] + data.getTitle()));
		lore.add(Chat.color(colors[0] + "status: " + colors[1] + data.getStatus()));
		lore.add(Chat.color(colors[0] + "subscribers: " + colors[1] + String.valueOf(data.getSubscribers())));
		lore.add(Chat.color(colors[0] + "profile views: " + colors[1] + String.valueOf(data.getProfileViews())));
		lore.add(Chat.color(colors[0] + "xp: " + colors[1] + String.valueOf(data.getXp())));
		lore.add(Chat.color(colors[0] + "submissions: " + colors[1] + String.valueOf(data.getSubmissions())));
		lore.add(Chat.color(colors[0] + "submission views: " + colors[1] + String.valueOf(data.getSubmissionViews())));
		lore.add(Chat.color(colors[0] + "diamonds: " + colors[1] + String.valueOf(data.getDiamonds())));
		lore.add(Chat.color(colors[0] + "downloads: " + colors[1] + String.valueOf(data.getDownloads())));
		lore.add(Chat.color(colors[0] + "favorites: " + colors[1] + String.valueOf(data.getFavorites())));
		lore.add(Chat.color(colors[0] + "comments: " + colors[1] + String.valueOf(data.getComments())));
		lore.add(Chat.color(colors[0] + "forum threads: " + colors[1] + String.valueOf(data.getForumThreads())));
		lore.add(Chat.color(colors[0] + "discussion posts: " + colors[1] + String.valueOf(data.getDiscussionPosts())));
		lore.add(Chat.color(colors[0] + "emeralds: " + colors[1] + String.valueOf(data.getEmeralds())));
		lore.add(Chat.color(colors[0] + "join date: " + colors[1] + data.getJoinDate()));

		return lore;

	}
}
