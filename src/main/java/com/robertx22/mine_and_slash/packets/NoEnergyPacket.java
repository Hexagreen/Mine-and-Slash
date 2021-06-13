package com.robertx22.mine_and_slash.packets;

import com.robertx22.mine_and_slash.config.forge.ClientContainer;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class NoEnergyPacket {

    public enum MessageTypes {
        NoEnergy,
        NoMana
    }

    public NoEnergyPacket() {
    }

    public static NoEnergyPacket decode(PacketBuffer buf) {

        NoEnergyPacket newpkt = new NoEnergyPacket();

        return newpkt;

    }

    public static void encode(NoEnergyPacket packet, PacketBuffer tag) {

    }

    public static void handle(final NoEnergyPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                if (ClientContainer.INSTANCE.SHOW_LOW_ENERGY_MANA_WARNING.get()) {

                    PlayerEntity player = MMORPG.proxy.getPlayerEntityFromContext(ctx);
                    player.playSound(SoundEvents.ENTITY_VILLAGER_AMBIENT, 0.8F, 1.1F);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}