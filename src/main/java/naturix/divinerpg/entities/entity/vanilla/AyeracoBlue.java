package naturix.divinerpg.entities.entity.vanilla;

import javax.annotation.Nullable;

import naturix.divinerpg.DivineRPG;
import naturix.divinerpg.entities.entity.EntityDivineRPGBoss;
import naturix.divinerpg.registry.ModSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraft.world.BossInfo.Color;

public class AyeracoBlue extends EntityDivineRPGBoss {
    public static final ResourceLocation LOOT = new ResourceLocation(DivineRPG.modId, "entities/ayeraco_blue");

    public AyeracoBlue(World worldIn) {
		super(worldIn);
		this.setSize(2.8F, 1.2F);
		this.setHealth(this.getMaxHealth());
	}

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(600.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    @Override
    protected void initEntityAI()
    {
    	super.initEntityAI();
    	addAttackingAI();
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
	public Color getBarColor()
	{
		return Color.BLUE;
	}

    @Override
    protected SoundEvent getAmbientSound() {
		return ModSounds.AYERACO;
    }
    
    @Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.AYERACO_HURT;
	}

    @Override
	protected SoundEvent getDeathSound() {
		return ModSounds.AYERACO_HURT;
    }

    @Override
	protected ResourceLocation getLootTable()
	{
		return this.LOOT;
	}
}
