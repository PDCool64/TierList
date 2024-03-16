package de.pdcool.tierlist.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.google.gson.Gson;
import de.pdcool.tierlist.SendStats;

@Mixin(ChatHud.class)
public class OnGameMessage {

    Gson gson = new Gson();

    @Inject(method = "addMessage", at = @At("RETURN"), cancellable = true)
    public void addMessage(Text message, CallbackInfo ci) {
        if (message.getString().contains("Red Team: ") || message.getString().contains("Blue Team: ")) {
            String name = MinecraftClient.getInstance().player.getName().getString();
            if (!message.getString().contains(name)) {
                SendStats.sendStatsOf(message.getString().replace("Red Team: ", "").replace("Blue Team: ", ""));
            }
        }
    }
}
