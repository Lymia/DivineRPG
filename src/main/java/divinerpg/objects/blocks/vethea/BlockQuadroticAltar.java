package divinerpg.objects.blocks.vethea;

import divinerpg.objects.entities.entity.vethea.EntityQuadro;
import divinerpg.registry.ModItems;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockQuadroticAltar extends BlockVetheaAltar {
    public BlockQuadroticAltar(String name) {
        super(name);
    }

    protected Item acceptedItem() {
        return ModItems.dreamFlint;
    }

    protected EntityLiving getBoss(World world) {
        return new EntityQuadro(world);
    }

    protected void onFailure() {
    }
}
