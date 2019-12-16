package divinerpg.entities.vanilla.wildfire;

import divinerpg.entities.base.DivineArcher;
import divinerpg.items.DivineBowItem;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Wildfire extends DivineArcher {
    public Wildfire(EntityType<? extends MonsterEntity> type, World world) {
        this(world);
    }

    public Wildfire(World world) {
        super(EntitiesRegistry.wildfire, world, SoundRegistry.WILDFIRE_HURT, SoundRegistry.WILDFIRE,
                1.75F, (DivineBowItem) ItemRegistry.inferno_bow);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        registerAttackAI(0.25F, 15, 10.0F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
    }

    @Override
    protected AbstractArrowEntity createArrow(ItemStack arrow, float distance) {
        AbstractArrowEntity result = super.createArrow(arrow, distance);
        result.setFire(100);
        return result;
    }
}