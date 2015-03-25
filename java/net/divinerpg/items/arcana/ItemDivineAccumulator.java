package net.divinerpg.items.arcana;

import java.util.List;

import net.divinerpg.DivineRPG;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDivineAccumulator extends ItemMod {

	public ItemDivineAccumulator() {
		super("divineAccumulator", DivineRPGTabs.utility);
		setMaxStackSize(1);
		this.setFull3D();
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		int x = (int)player.posX, y = (int)player.posY, z = (int)player.posZ;
		if(ArcanaHelper.getProperties(player).useBar(80)) {
			for(double r = 0; r < 4; r += 0.1) {
				for(double theta = 0; theta < 2*Math.PI; theta += (Math.PI/24)) {
					DivineRPG.proxy.spawnParticle(world, x+(r*Math.cos(theta)), y, z+(r*Math.sin(theta)), "eden", true);
				}
			}
			player.motionY = 2;
		}
		return stack;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add("Lauches you into the air");
		list.add("Does not prevent fall damage");
		list.add(TooltipLocalizer.arcanaConsumed(80));
		list.add(TooltipLocalizer.infiniteUses());
	}

}
