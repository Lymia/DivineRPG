package divinerpg.registry;

import divinerpg.api.Reference;
import divinerpg.enchant.DoubleDealDamageEnchant;
import divinerpg.enchant.WorldBreakEnchant;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder(Reference.MODID)
public class EnchantRegister {
    @GameRegistry.ObjectHolder("world_break")
    public static final Enchantment world_break = null;
    @GameRegistry.ObjectHolder("double_dealing_damage")
    public static final Enchantment double_dealing_damage = null;

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> e) {
        IForgeRegistry<Enchantment> registry = e.getRegistry();

        registry.register(new WorldBreakEnchant().setRegistryName(Reference.MODID, "world_break"));
        registry.register(new DoubleDealDamageEnchant().setRegistryName(Reference.MODID, "double_dealing_damage"));
    }
}
