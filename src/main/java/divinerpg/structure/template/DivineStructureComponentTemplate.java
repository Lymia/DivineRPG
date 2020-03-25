package divinerpg.structure.template;

import divinerpg.objects.entities.entity.vethea.EntityTempleGuardian;
import divinerpg.registry.DRPGLootTables;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponentTemplate;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.Random;

public class DivineStructureComponentTemplate extends StructureComponentTemplate {

    private ResourceLocation location;

    /**
     * NBT ctor
     */
    public DivineStructureComponentTemplate() {

    }

    public DivineStructureComponentTemplate(TemplateManager manager, ResourceLocation location, BlockPos start) {
        this(manager, location, start, null);
    }

    public DivineStructureComponentTemplate(TemplateManager manager, ResourceLocation location, BlockPos start, @Nullable PlacementSettings settings) {
        super(0);
        this.location = location;

        if (settings == null)
            settings = this.placeSettings;

        Template template = manager.getTemplate(null, location);
        setup(template, start, settings);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager manager) {
        super.readStructureFromNBT(tagCompound, manager);

        location = new ResourceLocation(tagCompound.getString("TmpRes"));
        setup(manager.getTemplate(null, location), templatePosition, placeSettings);
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {
        super.writeStructureToNBT(tagCompound);

        tagCompound.setString("TmpRes", location.toString());
    }

    protected void handleDataMarker(String function, BlockPos pos, World worldIn, Random rand, StructureBoundingBox sbb) {

        // TODO check if we really need it!
        //Temple data markers
        if (function.startsWith("TempleLoot")) {
            BlockPos blockpos = pos.down();
            if (sbb.isVecInside(blockpos)) {
                TileEntity tileentity = worldIn.getTileEntity(blockpos);
                if (tileentity instanceof TileEntityChest) {
                    if (function.equals("TempleLootBottom")) {
                        ((TileEntityChest) tileentity).setLootTable(DRPGLootTables.TEMPLE_LOOT_BOTTOM, rand.nextLong());
                    } else if (function.equals("TempleLootMiddle")) {
                        ((TileEntityChest) tileentity).setLootTable(DRPGLootTables.TEMPLE_LOOT_MIDDLE, rand.nextLong());
                    } else if (function.equals("TempleLootTop")) {
                        ((TileEntityChest) tileentity).setLootTable(DRPGLootTables.TEMPLE_LOOT_TOP, rand.nextLong());
                    }
                }
            }
        } else if (function.equals("TempleGuardian")) {
            EntityTempleGuardian templeGuardian = new EntityTempleGuardian(worldIn);
            templeGuardian.enablePersistence();
            templeGuardian.moveToBlockPosAndAngles(pos, 0.0F, 0.0F);
            worldIn.spawnEntity(templeGuardian);
            //worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
        }
    }
}