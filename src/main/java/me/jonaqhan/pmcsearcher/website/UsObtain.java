package me.jonaqhan.pmcsearcher.website;

import org.bukkit.entity.Player;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import me.jonaqhan.pmcsearcher.utils.Chat;

public class UsObtain {

	public UsData getData(String name, Player p) {

		@SuppressWarnings("resource")
		WebClient client = new WebClient();
		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);

		HtmlPage page = null;
		try {
			page = client.getPage("https://www.planetminecraft.com/member/" + name.toLowerCase());

		} catch (Exception e) {
			p.sendMessage(Chat.color("#42D633[#1C8EDBPMCS#42D633] &cplayer not found!"));
			return null;
		}

		HtmlElement user = ((HtmlElement) page.getFirstByXPath("//*[@id=\"member-title-primary\"]/a/h1"));
		HtmlElement level = ((HtmlElement) page.getFirstByXPath("//*[@id=\"member-main-stats\"]/span[1]"));
		HtmlElement title = ((HtmlElement) page.getFirstByXPath("//*[@id=\"member-main-stats\"]/span[2]"));
		HtmlElement status = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[1]/td[2]"));
		HtmlElement subscribers = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[2]/td[1]/span"));
		HtmlElement profileViews = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[3]/td[1]/span"));
		HtmlElement xp = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[4]/td[1]/span"));
		HtmlElement submissions = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[5]/td[1]/span"));
		HtmlElement submissionViews = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[6]/td[1]/span"));
		HtmlElement diamonds = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[7]/td[1]/span"));
		HtmlElement downloads = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[8]/td[1]/span"));
		HtmlElement favorites = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[10]/td[1]/span"));
		HtmlElement comments = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[10]/td[1]/span"));
		HtmlElement forumThreads = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[11]/td[1]/span"));
		HtmlElement discussionPosts = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[12]/td[1]/span"));
		HtmlElement emeralds = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[13]/td[1]/span"));
		HtmlElement joinDate = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[14]/td[1]/span"));
		HtmlElement minecraftName = ((HtmlElement) page
				.getFirstByXPath("//*[@id=\"member-main-stats\"]/div/div/table/tbody/tr[15]/td[1]/span"));

		UsData data = new UsData(user.asText(), level.asText(), title.asText(), status.asText(),
				Integer.valueOf(subscribers.asText().replace(",", "")),
				Integer.valueOf(profileViews.asText().replace(",", "")), Integer.valueOf(xp.asText().replace(",", "")),
				Integer.valueOf(submissions.asText().replace(",", "")),
				Integer.valueOf(submissionViews.asText().replace(",", "")),
				Integer.valueOf(diamonds.asText().replace(",", "")),
				Integer.valueOf(downloads.asText().replace(",", "")),
				Integer.valueOf(favorites.asText().replace(",", "")),
				Integer.valueOf(comments.asText().replace(",", "")),
				Integer.valueOf(forumThreads.asText().replace(",", "")),
				Integer.valueOf(discussionPosts.asText().replace(",", "")),
				Integer.valueOf(emeralds.asText().replace(",", "")), joinDate.asText(), minecraftName.asText());

		return data;

	}

}
