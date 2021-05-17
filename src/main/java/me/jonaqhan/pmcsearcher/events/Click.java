package me.jonaqhan.pmcsearcher.events;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import me.jonaqhan.pmcsearcher.commands.DatapackSearcher;
import me.jonaqhan.pmcsearcher.utils.Chat;

public class Click implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!e.getView().getTitle().contains("User"))
			return;

		if (e.getCurrentItem() == null)
			return;

		if (e.getCurrentItem().getItemMeta() == null)
			return;

		e.setCancelled(true);
	}

	@SuppressWarnings("unchecked")
	@EventHandler
	public void onClickDatapack(InventoryClickEvent e) throws InterruptedException {
		if (!e.getView().getTitle().contains("Recent datapacks"))
			return;

		if (e.getCurrentItem() == null)
			return;

		if (e.getCurrentItem().getItemMeta() == null)
			return;

		String title = e.getCurrentItem().getItemMeta().getDisplayName().substring(14);

		if (!title.contains("Forward") && !title.contains("Backward")) {

			@SuppressWarnings("resource")
			WebClient client = new WebClient();
			client.getOptions().setJavaScriptEnabled(false);
			client.getOptions().setCssEnabled(false);

			Player p = (Player) e.getWhoClicked();
			HtmlPage page = null;
			try {
				page = client.getPage(
						"https://www.planetminecraft.com/data-packs/?p=" + e.getView().getTitle().substring(32));

			} catch (Exception ex) {
				p.sendMessage(Chat.color("#42D633[#1C8EDBPMCS#42D633] &ca error has showed up"));
				return;
			}

			List<HtmlElement> items = null;

			if (Integer.valueOf(e.getView().getTitle().substring(32)) == 1) {
				items = (List<HtmlElement>) page.getByXPath("//*[@id=\"full_screen\"]/div[2]/div[4]/ul/li");
			}

			if (Integer.valueOf(e.getView().getTitle().substring(32)) >= 2) {
				items = (List<HtmlElement>) page.getByXPath("//*[@id=\"full_screen\"]/div/div[4]/ul/li");
			}

			for (HtmlElement item : items) {

				HtmlElement header = item.getFirstByXPath("./div[2]/a");

				if (header != null) {
					if (header.asText().contains(title)) {

						HtmlAnchor urlElement = (HtmlAnchor) item.getFirstByXPath("./div[2]/a");
						String url = "https://www.planetminecraft.com" + urlElement.getHrefAttribute();

						p.sendMessage(Chat.color("#42D633[#1C8EDBPMCS#42D633]#3b94ed " + url));

					}

				}
			}
		}

		if (title.contains("Forward")) {
			DatapackSearcher searcher = new DatapackSearcher();
			e.getWhoClicked().closeInventory();

			int number = Integer.valueOf(e.getView().getTitle().substring(32)) + 1;

			searcher.createPage(e.getWhoClicked(), number);

		}

		if (title.contains("Backward")) {

			if (Integer.valueOf(e.getView().getTitle().substring(32)) == 1) {
				e.setCancelled(true);
				return;
			}
			DatapackSearcher searcher = new DatapackSearcher();

			e.getWhoClicked().closeInventory();
			searcher.createPage(e.getWhoClicked(), Integer.valueOf(e.getView().getTitle().substring(32)) - 1);

		}

		e.setCancelled(true);
	}

}
