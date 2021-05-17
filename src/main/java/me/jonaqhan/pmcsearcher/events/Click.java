package me.jonaqhan.pmcsearcher.events;

import java.awt.Desktop;
import java.net.URI;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

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

	@EventHandler
	public void onClickDatapack(InventoryClickEvent e) {
		if (!e.getView().getTitle().contains("Recent datapacks"))
			return;

		if (e.getCurrentItem() == null)
			return;

		if (e.getCurrentItem().getItemMeta() == null)
			return;

		String title = e.getCurrentItem().getItemMeta().getDisplayName().substring(14);

		@SuppressWarnings("resource")
		WebClient client = new WebClient();
		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);

		Player p = (Player) e.getWhoClicked();
		HtmlPage page = null;
		try {
			page = client.getPage("https://www.planetminecraft.com/data-packs/");

		} catch (Exception ex) {
			p.sendMessage(Chat.color("#42D633[#1C8EDBPMCS#42D633] &ca error has showed up"));
			return;
		}

		@SuppressWarnings("unchecked")
		List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//*[@id=\"full_screen\"]/div[2]/div[4]/ul/li");

		for (HtmlElement item : items) {

			HtmlElement header = item.getFirstByXPath("./div[2]/a");
			System.out.println(title);

			if (header != null) {
				System.out.println(header.asText());

				if (header.asText().contains(title)) {

					HtmlAnchor urlElement = (HtmlAnchor) page.getFirstByXPath("./div[2]/a");
					String url = urlElement.getHrefAttribute();

					System.out.println(url);

					try {
						openURL(url);
					} catch (Exception e1) {
					}
				}

			}
		}

		e.setCancelled(true);
	}

	public static void openURL(String title) throws Exception {

		Desktop d = Desktop.getDesktop();

		d.browse(new URI("https://www.planetminecraft.com/data-pack/" + title));

	}

}
