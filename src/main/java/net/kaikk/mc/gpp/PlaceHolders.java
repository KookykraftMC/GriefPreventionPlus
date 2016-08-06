package net.kaikk.mc.gpp;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import org.bukkit.entity.Player;

/**
 * Created by TimeTheCat on 8/6/2016.
 */
class PlaceHolders implements PlaceholderReplacer {
    private GriefPreventionPlus gpp;

    PlaceHolders (GriefPreventionPlus gpp) {
        this.gpp = gpp;
    }

    void initPlaceHolders() {
        PlaceholderAPI.registerPlaceholder(gpp, "gpp_claim_owner", this);
        PlaceholderAPI.registerPlaceholder(gpp, "gpp_accrued_blocks", this);
        PlaceholderAPI.registerPlaceholder(gpp, "gpp_bonus_blocks", this);
        PlaceholderAPI.registerPlaceholder(gpp, "gpp_remaining_claim_blocks", this);
        PlaceholderAPI.registerPlaceholder(gpp, "gpp_claim_amount", this);
    }

    @Override
    public String onPlaceholderReplace(PlaceholderReplaceEvent placeholderReplaceEvent) {
        //if it wants the owner, get the claim owner. if there is no claim return wilderness
        if (placeholderReplaceEvent.getPlaceholder().equals("gpp_claim_owner")) {
            Player player = placeholderReplaceEvent.getPlayer();
            Claim claim = gpp.getDataStore().getClaimAt(player.getLocation());
            if (claim != null)
                return claim.getOwnerName();
            else
                return "Wilderness";
        }
        //get the amount of claim blocks the player has accrued
        else if (placeholderReplaceEvent.getPlaceholder().equals("gpp_accrued_Blocks")) {
            Player player = placeholderReplaceEvent.getPlayer();
            PlayerData playerData = gpp.getDataStore().getPlayerData(player.getUniqueId());
            return String.valueOf(playerData.getAccruedClaimBlocks());
        }
        //get the amount of bonus claim blocks a player has
        else if (placeholderReplaceEvent.getPlaceholder().equals("gpp_bonus_blocks")) {
            Player player = placeholderReplaceEvent.getPlayer();
            PlayerData playerData = gpp.getDataStore().getPlayerData(player.getUniqueId());
            return String.valueOf(playerData.getBonusClaimBlocks());
        }
        //get the amount of remaining claim blocks a player has
        else if (placeholderReplaceEvent.getPlaceholder().equals("gpp_remaining_claim_blocks")) {
            Player player = placeholderReplaceEvent.getPlayer();
            PlayerData playerData = gpp.getDataStore().getPlayerData(player.getUniqueId());
            return String.valueOf(playerData.getRemainingClaimBlocks());
        }
        //get the amount of claims the player has
        else if (placeholderReplaceEvent.getPlaceholder().equals("gpp_claim_amount")) {
            Player player = placeholderReplaceEvent.getPlayer();
            PlayerData playerData = gpp.getDataStore().getPlayerData(player.getUniqueId());
            return String.valueOf(playerData.getClaims().size());
        }
        return null;
    }
}
