package divinerpg.objects.entities.entity.twilight.mage;

import divinerpg.enums.BulletType;
import divinerpg.registry.DRPGLootTables;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntitySpellbinder extends EntityMageBase {

    public EntitySpellbinder(World worldIn) {
        super(worldIn, BulletType.SPELLBINDER_SHOT);
    }


    @Override
    protected ResourceLocation getLootTable() {
        return DRPGLootTables.ENTITIES_SPELLBINDER;
    }
}