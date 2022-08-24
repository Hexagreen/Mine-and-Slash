package com.robertx22.mine_and_slash.mmorpg.registers.server;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.commands.dev.LogDungeonRoom;
import com.robertx22.mine_and_slash.commands.dev.TpOut;
import com.robertx22.mine_and_slash.commands.dev.TpRandomDungeon;
import com.robertx22.mine_and_slash.commands.entity.GiveExp;
import com.robertx22.mine_and_slash.commands.entity.SetEntityLevel;
import com.robertx22.mine_and_slash.commands.entity.SetEntityRarity;
import com.robertx22.mine_and_slash.commands.entity.SetLevel;
import com.robertx22.mine_and_slash.commands.giveitems.*;
import com.robertx22.mine_and_slash.commands.misc.*;
import com.robertx22.mine_and_slash.commands.open_gui.OpenHub;
import com.robertx22.mine_and_slash.commands.party.PartyCommand;
import com.robertx22.mine_and_slash.commands.reset.*;
import com.robertx22.mine_and_slash.commands.stats.*;
import net.minecraft.command.CommandSource;
import net.minecraft.server.MinecraftServer;

public class CommandRegister {

    public static void Register(MinecraftServer server) {
        System.out.println("Registering Mine and Slash Commands.");

        CommandDispatcher<CommandSource> dispatcher = server.getCommandManager()
            .getDispatcher();

        SetLevel.register(dispatcher);
        RestoreLevel.register(dispatcher);
        GiveExp.register(dispatcher);
        GiveAwakenRuneword.register(dispatcher);
        GiveAbilityLevels.register(dispatcher);
        GiveExactUnique.register(dispatcher);
        GiveGear.register(dispatcher);
        GiveMap.register(dispatcher);
        GiveRune.register(dispatcher);
        GiveRunedGear.register(dispatcher);
        GiveUniqueGear.register(dispatcher);
        SetEntityLevel.register(dispatcher);
        SetEntityRarity.register(dispatcher);

        TpOut.register(dispatcher);
        LogDungeonRoom.register(dispatcher);
        TpRandomDungeon.register(dispatcher);

        GiveStatMod.register(dispatcher);
        RemoveStatMod.register(dispatcher);
        ClearStatMods.register(dispatcher);
        ResetSpells.register(dispatcher);
        ResetAll.register(dispatcher);
        ResetSpellCooldowns.register(dispatcher);

        GiveStat.register(dispatcher);
        RemoveStat.register(dispatcher);
        ClearStats.register(dispatcher);
        GiveAllStats.register(dispatcher);

        ReloadConfigs.register(dispatcher);
        ModifyItem.register(dispatcher);
        GiveUniqueRune.register(dispatcher);
        ResetTalents.register(dispatcher);
        ResetStats.register(dispatcher);
        OpenHub.register(dispatcher);

        GenDefaultCompItemsOfMod.register(dispatcher);

        GiveCrate.register(dispatcher);

        PartyCommand.register(dispatcher);

        ConvertCompItemsToNewFormat.register(dispatcher);

    }
}