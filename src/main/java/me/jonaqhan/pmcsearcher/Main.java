package me.jonaqhan.pmcsearcher;

import org.bukkit.plugin.java.JavaPlugin;

import me.jonaqhan.pmcsearcher.commands.PlayerSearcher;

public class Main extends JavaPlugin {

	public void onEnable() {

		getCommand("pmu").setExecutor(new PlayerSearcher());

	}

	public void onDisable() {

	}
}
