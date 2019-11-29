package divinerpg.objects.entities.entity.vethea;

import javax.annotation.Nullable;


import divinerpg.api.java.divinerpg.api.Reference;
import divinerpg.registry.DRPGLootTables;
import net.minecraft.block.Block;
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
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class Lorga extends VetheaMob {

    private int spawnTick;
    private boolean canSpawnMinions;

    public Lorga(World worldIn) {
		this(worldIn, true);
	}

	private Lorga(World worldIn, boolean canSpawnMinions) {
        super(worldIn);
        this.setSize(1F, 2.4f);
        this.canSpawnMinions = canSpawnMinions;
    }

    @Override
	protected ResourceLocation getLootTable()
	{
		return DRPGLootTables.ENTITIES_LORGA;
	}

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.32D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAIAttackMelee(this, 1, true));
        this.tasks.addTask(8, new EntityAIFollow(this, 1, 1, 1));
        this.applyEntityAI();
    }

    private void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityPigZombie.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        List<Lorga> nearby = this.world.getEntitiesWithinAABB(Lorga.class, this.getEntityBoundingBox().expand(10, 10, 10));
        if(!this.canSpawnMinions) {
            return;
        }
        else {
            if (!this.world.isRemote && this.spawnTick == 0 && nearby.size() < 12) {
                this.spawnTick = 260;
                Lorga var2 = new Lorga(this.world, false);
                var2.setLocationAndAngles(this.posX + 1, this.posY, this.posZ + 1, this.rotationYaw, this.rotationPitch);
                this.world.spawnEntity(var2);
            }
            else if (this.spawnTick > 0) {
                this.spawnTick--;
            }
        }
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 3;
    }

    @Override
    public int getSpawnLayer() {
        return 1;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return super.getAmbientSound();
    }
}
