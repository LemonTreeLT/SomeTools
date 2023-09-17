package net.lemontree.mixin.client;

import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class SomeToolsClientMixin {
	@Inject(at = @At("HEAD"), method = "addMessage(Lnet/minecraft/text/Text;)V")
	public void addMessage(Text message, CallbackInfo ci) {
	}
}