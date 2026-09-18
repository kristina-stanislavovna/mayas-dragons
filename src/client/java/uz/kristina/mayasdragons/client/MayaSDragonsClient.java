package uz.kristina.mayasdragons.client;

import net.fabricmc.api.ClientModInitializer;
import uz.kristina.mayasdragons.client.entity.model.ModEntityModelLayers;import net.minecraft.client.renderer.entity.EntityRenderers;
import uz.kristina.mayasdragons.client.entity.renderer.DragonEntityRenderer;
import uz.kristina.mayasdragons.entity.ModEntityTypes;

public class MayaSDragonsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ModEntityModelLayers.registerModelLayers();
		EntityRenderers.register(
				ModEntityTypes.DRAGON,
				DragonEntityRenderer::new
		);
	}


}