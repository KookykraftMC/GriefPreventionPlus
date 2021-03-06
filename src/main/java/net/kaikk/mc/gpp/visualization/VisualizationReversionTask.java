/*
    GriefPrevention Server Plugin for Minecraft
    Copyright (C) 2015 Ryan Hamshire

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.kaikk.mc.gpp.visualization;

import net.kaikk.mc.gpp.PlayerData;

import org.bukkit.entity.Player;

//applies a visualization for a player by sending him block change packets
class VisualizationReversionTask implements Runnable {
	private final Visualization visualization;
	private final Player player;
	private final PlayerData playerData;

	public VisualizationReversionTask(Player player, PlayerData playerData, Visualization visualization) {
		this.visualization = visualization;
		this.playerData = playerData;
		this.player = player;
	}

	@Override
	public void run() {
		// don't do anything if the player's current visualization is different
		// from the one scheduled to revert
		if (this.playerData.currentVisualization != this.visualization) {
			return;
		}

		Visualization.Revert(this.player);
	}
}
