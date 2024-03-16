package de.pdcool.tierlist.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;

public class TierListClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("Hello from the client side!");
    }
}
