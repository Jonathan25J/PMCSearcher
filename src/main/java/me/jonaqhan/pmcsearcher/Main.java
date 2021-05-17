package me.jonaqhan.pmcsearcher;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.jonaqhan.pmcsearcher.commands.DatapackSearcher;
import me.jonaqhan.pmcsearcher.commands.UserSearcher;
import me.jonaqhan.pmcsearcher.events.Click;

public class Main extends JavaPlugin {

	public void onEnable() {

		getCommand("pmu").setExecutor(new UserSearcher());
		getCommand("pmd").setExecutor(new DatapackSearcher());
		Bukkit.getPluginManager().registerEvents(new Click(), this);

	}

	public void onDisable() {

	}
}
