package divinerpg.objects.items.arcana;

import divinerpg.enums.BulletType;
import divinerpg.objects.entities.entity.projectiles.EntityStar;
import divinerpg.objects.items.base.RangedWeaponBase;
import divinerpg.registry.ModSounds;
import divinerpg.utils.LocalizeUtils;
import divinerpg.utils.PositionHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemStaffStarlight extends RangedWeaponBase {

    /**
     * Amount of spawned entities
     */
    private final int count;

    private final ITextComponent starsInfo;

    /**
     * @param name   name of ite,
     * @param arcana - arcana consuming amount
     * @param count  - count of spawning entities
     */
    public ItemStaffStarlight(String name, int arcana, int count) {
        super(name, EntityStar.class, null, ModSounds.STARLIGHT, SoundCategory.MASTER, -1, 0, null, arcana);
        this.count = count;
        this.setFull3D();

        starsInfo = new TextComponentTranslation(count > 1 ? "tooltip.staff_of_starlight" : "tooltip.starlight");
    }

    @Override
    protected boolean canUseRangedWeapon(EntityPlayer player, ItemStack stack) {
        if (!player.world.isRemote) {
            RayTraceResult pos = PositionHelper.rayTrace(player, 32, 1);
            if (pos.typeOfHit != RayTraceResult.Type.BLOCK) {
                return false;
            }
        }

        return super.canUseRangedWeapon(player, stack);
    }

    @Override
    protected void spawnEntity(World world, EntityPlayer player, ItemStack stack, BulletType bulletType,
                               Class<? extends EntityThrowable> clazz) {
        RayTraceResult pos = PositionHelper.rayTrace(player, 32, 1);

        if (pos.typeOfHit == RayTraceResult.Type.BLOCK) {
            int blockX = pos.getBlockPos().getX();
            int blockY = pos.getBlockPos().getY();
            int blockZ = pos.getBlockPos().getZ();
            EnumFacing side = pos.sideHit;

            if (side == EnumFacing.DOWN)
                --blockY;
            if (side == EnumFacing.UP)
                ++blockY;
            if (side == EnumFacing.EAST)
                --blockZ;
            if (side == EnumFacing.WEST)
                ++blockZ;
            if (side == EnumFacing.SOUTH)
                --blockX;
            if (side == EnumFacing.NORTH)
                ++blockX;

            if (!world.isRemote) {
                for (int i = 0; i < count; i++) {
                    world.spawnEntity(new EntityStar(world, (double) blockX + 0.5D, (double) blockY + 25D,
                            (double) blockZ + 0.5D));
                }
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(LocalizeUtils.rangedDam(20));
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(starsInfo.getFormattedText());
    }

}