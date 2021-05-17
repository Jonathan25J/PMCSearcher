package me.jonaqhan.pmcsearcher.commands;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.jonaqhan.pmcsearcher.utils.Chat;

public class DatapackSearcher implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player))
			return false;

		createPage(sender, 1);

		return false;
	}

	@SuppressWarnings("unchecked")
	public void createPage(CommandSender sender, int number) {
		Inventory inv = Bukkit.createInventory(null, 36, Chat.color("#533bedRecent datapacks #" + number));
		@SuppressWarnings("resource")
		WebClient client = new WebClient();
		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);

		Player p = (Player) sender;
		HtmlPage page = null;
		try {
			page = client.getPage("https://www.planetminecraft.com/data-packs/?p=" + number);

		} catch (Exception e) {
			p.sendMessage(Chat.color("#42D633[#1C8EDBPMCS#42D633] &ca error has showed up"));
			return;
		}

		List<HtmlElement> items = null;

		if (number == 1) {
			items = (List<HtmlElement>) page.getByXPath("//*[@id=\"full_screen\"]/div[2]/div[4]/ul/li");
		}

		if (number >= 2) {
			items = (List<HtmlElement>) page.getByXPath("//*[@id=\"full_screen\"]/div/div[4]/ul/li");
		}

		int index = 0;

		for (HtmlElement item : items) {

			HtmlElement title = item.getFirstByXPath("./div[2]/a");
			HtmlElement type = item.getFirstByXPath("./div[2]/div[1]/div");
			HtmlElement mcVersion = item.getFirstByXPath("./div[2]/div[3]/div");
			HtmlElement creator = item.getFirstByXPath("./div[2]/div[4]/a");
			HtmlElement time = item.getFirstByXPath("./div[2]/div[4]/abbr");
			HtmlElement downloads = item.getFirstByXPath("./div[2]/div[5]/div[1]/span[2]");
			HtmlElement comments = item.getFirstByXPath("./div[2]/div[5]/div[1]/span[3]");
			HtmlElement diamonds = item.getFirstByXPath("./div[2]/div[2]/div[1]/span");
			HtmlElement favorites = item.getFirstByXPath("./div[2]/div[2]/div[2]/span");

			List<String> lore = new ArrayList<>();

			try {
				lore.add(Chat.color("#a8b3a6" + type.asText()));
			} catch (Exception e) {
				lore.add(Chat.color("#a8b3a6" + "none"));
			}

			try {
				lore.add(Chat.color("#a8b3a6" + mcVersion.asText()));
			} catch (Exception e) {
				lore.add(Chat.color("#a8b3a6" + "none"));
			}

			try {
				lore.add(Chat.color("#ffd621creator:#58e8da " + creator.asText()));
			} catch (Exception e) {
				lore.add(Chat.color("#ffd621creator:#58e8da " + "none"));
			}

			try {
				lore.add(Chat.color("#21ff7atime:#58e8da " + time.asText()));
			} catch (Exception e) {
				lore.add(Chat.color("#21ff7atime:#58e8da " + "none"));
			}

			try {
				lore.add(Chat.color("#18b1dbdownloads:#58e8da " + downloads.asText()));
			} catch (Exception e) {
				lore.add(Chat.color("#18b1dbdownloads:#58e8da " + "none"));
			}
			try {
				lore.add(Chat.color("#f5fdffcomments:#58e8da " + comments.asText()));
			} catch (Exception e) {
				lore.add(Chat.color("#f5fdffcomments:#58e8da " + "none"));
			}
			try {
				lore.add(Chat.color("#19d1cbdiamonds:#58e8da " + diamonds.asText()));
			} catch (Exception e) {
				lore.add(Chat.color("#19d1cbdiamonds:#58e8da " + "none"));
			}

			try {
				lore.add(Chat.color("#f03541favorites:#58e8da " + favorites.asText()));
			} catch (Exception e) {
				lore.add(Chat.color("#f03541favorites:#58e8da " + "none"));
			}

			String tl = "none";
			try {
				tl = title.asText();
			} catch (Exception e) {
			}

			ItemStack itemstack = getItem(lore, tl);

			if (!tl.contains("none")) {
				inv.setItem(index, itemstack);
				index++;
			}

		}

		ItemStack[] elements = getElements();

		inv.setItem(35, elements[0]);
		inv.setItem(27, elements[1]);

		p.openInventory(inv);

		return;
	}

	public ItemStack getItem(List<String> lore, String title) {

		ItemStack item = new ItemStack(Material.PLAYER_HEAD);

		item.setItemMeta(getSkull(item));

		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(Chat.color("#3b94ed" + title));
		meta.setLore(lore);

		item.setItemMeta(meta);

		return item;

	}

	public SkullMeta getSkull(ItemStack item) {

		List<String> textures = new ArrayList<>();
		textures.add(
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTc2NmEwZDIxZDI2MWM4Nzg3NjNjYTgyNjRjZDJkZDBiMTdlY2ZhYzM0MjQxMWRiMDc4ODZiMDc4ZTZjYjAxMyJ9fX0=");
		textures.add(
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTEwZjIwYTU1YjZlMTg4ZWJlNzU3ODQ1OWI2NGE2ZmJkODI1MDY3YmM0OTdiOTI1Y2E0M2MyNjQzZDA1OTAyNSJ9fX0=");
		textures.add(
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTdiYzFiNjRjYmEzZGM0Y2VmZTRlMTIxYzNjZGJiYjBmYTk5YWJhMGUxMTNiNWM5MTY4MTVmYzliMzA0ZTYzNiJ9fX0=");
		textures.add(
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjQzZjUyMGM4NTZiYzdhMDM2NGUwZWRlNzI5ZmI0OWQ2ZGMwNDZjZjdkOGY1NjQ5NGQwYzJmMjg2YjYyMzUzMSJ9fX0=");
		textures.add(
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY0NTliZTA5OTk4ZTUwYWJkMmNjZjRjZDM4M2U2YjM4YWI1YmM5MDVmYWNiNjZkY2UwZTE0ZTAzOGJhMTk2OCJ9fX0=");
		textures.add(
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmRhNDgyNjcwYWQ3NDQ2NjA4MTg4M2ZlN2VkZDQ4ZGVjMjdhNjk4YTlhNTJjNGY4NzAzMTBiYTAzNWFjZjY5NiJ9fX0=");
		textures.add(
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2VkNGFjZThmYTUwN2M0NWIxMWI4OTMzNjJlNDViZWJmNDhjYjIyMDMxNTM1MzJlZmY0MjBhN2Y1Y2Y3NzEwYiJ9fX0=");
		textures.add(
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FkMTEzZmRhNjQzNTZmZDYxYzcyOTU0NjdmMDRkMzgwMDc2MjE2MDE1Nzg1MDMxYjQ4YzNmYTE2MTg4OGU4MyJ9fX0=");
		textures.add(
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWI2NmI2NjkzYjJlOTRkZGVkNTc0MmU0MmFmNzA2MjUwNjlmOTM4NTRmODI1YjU5Y2E4N2EwOGY1ODIwZjliMyJ9fX0=");
		textures.add(
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzllMTA2Njk2YWEzMjMzNjdjN2JlYWNjMDc1ODM4NzAzYTY3NmY4ZTVjNDY0MDA5ZDUzNWMzMGI0NTJhMTlhIn19fQ==");

		Random r = new Random();

		int number = r.nextInt(9 - 0) - 0;

		SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		profile.getProperties().put("textures", new Property("textures", new String(textures.get(number))));

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

	public ItemStack[] getElements() {

		ItemStack forward = new ItemStack(Material.PLAYER_HEAD);

		SkullMeta metaForward = (SkullMeta) forward.getItemMeta();
		metaForward.setDisplayName(Chat.color("#15D43BForward"));
		GameProfile profileForward = new GameProfile(UUID.randomUUID(), null);
		profileForward.getProperties().put("textures", new Property("textures", new String(
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjhmNjgxZGFhZDhiZjQzNmNhZThkYTNmZTgxMzFmNjJhMTYyYWI4MWFmNjM5YzNlMDY0NGFhNmFiYWMyZiJ9fX0=")));

		Field profileForwardField = null;

		try {
			profileForwardField = metaForward.getClass().getDeclaredField("profile");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		profileForwardField.setAccessible(true);

		try {
			profileForwardField.set(metaForward, profileForward);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		forward.setItemMeta(metaForward);

		ItemStack backward = new ItemStack(Material.PLAYER_HEAD);

		SkullMeta metaBackward = (SkullMeta) backward.getItemMeta();
		metaBackward.setDisplayName(Chat.color("#15D43BBackward"));
		GameProfile profileBackward = new GameProfile(UUID.randomUUID(), null);
		profileBackward.getProperties().put("textures", new Property("textures", new String(
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0=")));

		Field profileBackwardField = null;

		try {
			profileBackwardField = metaBackward.getClass().getDeclaredField("profile");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		profileBackwardField.setAccessible(true);

		try {
			profileBackwardField.set(metaBackward, profileBackward);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		backward.setItemMeta(metaBackward);

		return new ItemStack[] { forward, backward };

	}
}
