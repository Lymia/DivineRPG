package naturix.divinerpg.entities.entity.vanilla;

import javax.annotation.Nullable;

import naturix.divinerpg.DivineRPG;
import naturix.divinerpg.entities.entity.EntityDivineRPGMob;
import naturix.divinerpg.entities.entity.projectiles.EntityCaveRock;
import naturix.divinerpg.registry.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class Caveclops extends EntityDivineRPGMob implements IRangedAttackMob {
    public static final ResourceLocation LOOT = new ResourceLocation(DivineRPG.modId, "entities/caveclops");

    public Caveclops(World worldIn) {
		super(worldIn);
		this.setSize(1.0F, 2.9F);
		this.setHealth(this.getMaxHealth());
	}

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
    }

    protected void initEntityAI()
    {
    	super.initEntityAI();
    	addAttackingAI();
        this.tasks.addTask(0, new EntityAIAttackRanged(this, 0.27F, 30, 10.0F));
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
        EntityCaveRock rock = new EntityCaveRock(this.world, this);
	    double d0 = target.posX - this.posX;
	    double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - rock.posY;
	    double d2 = target.posZ - this.posZ;
	    double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
	    rock.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, 12.0F);
	    this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
	    this.world.spawnEntity(rock);
    }

    @Override
	public void setSwingingArms(boolean swingingArms) { }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
		return ModSounds.CYCLOPS;
    }
    
    @Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.CYCLOPS_HURT;
	}

    @Override
	protected SoundEvent getDeathSound() {
		return ModSounds.CYCLOPS_HURT;
    }

    @Override
	protected ResourceLocation getLootTable()
	{
		return this.LOOT;
	}

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 20.0D && super.getCanSpawnHere();
    }
}
