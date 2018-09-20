package naturix.divinerpg.proxy;

import javax.annotation.Nullable;

import naturix.divinerpg.Config;
import naturix.divinerpg.DivineRPG;
import naturix.divinerpg.entities.assets.model.ModelHat;
import naturix.divinerpg.registry.ModEntities;
import naturix.divinerpg.utils.Utils;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        ModEntities.initModels();

    }
	@Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        Utils.setupCapes();
        Utils.updateCapeList();
    }
	
    @Override
	public EntityPlayer getPlayer() {
		return FMLClientHandler.instance().getClientPlayerEntity();
	}

    public void registerItemRenderer(Item item, int meta, String id) {
    	if (Config.debug = true) {
        DivineRPG.logger.info(id.toLowerCase() + "'s model has just been registered");
        }
    	ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(DivineRPG.modId + ":" + id, "inventory"));
    }
    
 
}
