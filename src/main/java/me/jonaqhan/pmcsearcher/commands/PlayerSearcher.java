package me.jonaqhan.pmcsearcher.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonaqhan.pmcsearcher.gui.Create;
import me.jonaqhan.pmcsearcher.utils.Chat;
import me.jonaqhan.pmcsearcher.website.Data;
import me.jonaqhan.pmcsearcher.website.Obtain;

public class PlayerSearcher implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player))
			return false;

		Player p = (Player) sender;

		if (args.length == 0) {
			p.sendMessage(Chat.color("#42D633[#1C8EDBPMCS#42D633] /pmu <user>"));
			return false;
		}

		if (args.length >= 2) {
			p.sendMessage(Chat.color("#42D633[#1C8EDBPMCS#42D633] /pmu <user>"));
			return false;
		}

		Obtain manager = new Obtain();
		Data data = manager.getData(args[0], p);
		
		if (data == null) return false;

		Create gui = new Create();
		p.openInventory(gui.createGUI(p, data));

		return false;
	}

}
