package lance5057.tDefense.core.workstations;

import lance5057.tDefense.Reference;
import lance5057.tDefense.TinkersCompendium;
import lance5057.tDefense.core.workstations.blocks.FinishingAnvilBlock;
import lance5057.tDefense.core.workstations.blocks.HammeringTableBlock;
import lance5057.tDefense.core.workstations.gui.armorstation.ArmorStationContainer;
import lance5057.tDefense.core.workstations.gui.armorstation.ArmorStationGui;
import lance5057.tDefense.core.workstations.gui.finishinganvil.FinishingAnvilContainer;
import lance5057.tDefense.core.workstations.gui.finishinganvil.FinishingAnvilGui;
import lance5057.tDefense.core.workstations.registries.hammeringtable.HammeringTableRecipeRegistry;
import lance5057.tDefense.core.workstations.renderers.HammeringTableRenderer;
import lance5057.tDefense.core.workstations.tileentities.ArmorStationTile;
import lance5057.tDefense.core.workstations.tileentities.FinishingAnvilTile;
import lance5057.tDefense.core.workstations.tileentities.GuilessManualWorkstationTileEntity;
import lance5057.tDefense.core.workstations.tileentities.HammeringTableTileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class CompendiumWorkstations {

	public static final int ArmorStationID = 0;
	public static final int FinishingAnvilID = 1;

	public static FinishingAnvilBlock finishingAnvil;
	private ItemBlock FinishingAnvilItem;
	
	public static HammeringTableBlock hammeringtable;
	private ItemBlock HammeringTableItem;

	public void preInit(FMLPreInitializationEvent e) {
		registerRecipes();
	}

	
	public void init(FMLInitializationEvent e) {

	}

	public void postInit(FMLPostInitializationEvent e) {

	}

	public void registerItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry registry = event.getRegistry();
		
		FinishingAnvilItem = (ItemBlock) new ItemBlock(finishingAnvil).setRegistryName(finishingAnvil.getRegistryName());
		HammeringTableItem = (ItemBlock) new ItemBlock(hammeringtable).setRegistryName(hammeringtable.getRegistryName());
		
		registry.register(FinishingAnvilItem);
		registry.register(HammeringTableItem);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ClientRegistry.bindTileEntitySpecialRenderer(HammeringTableTileEntity.class, new HammeringTableRenderer());
		
		TinkersCompendium.proxy.registerItemBlockRenderer(finishingAnvil, 0, "finishinganvil");
		TinkersCompendium.proxy.registerItemBlockRenderer(hammeringtable, 0, "hammeringtable");
	}

	//@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		
		finishingAnvil = new FinishingAnvilBlock();
		hammeringtable = new HammeringTableBlock();

		registry.register(finishingAnvil);
		registry.register(hammeringtable);
		
		GameRegistry.registerTileEntity(FinishingAnvilTile.class, "finishinganviltile");
		GameRegistry.registerTileEntity(HammeringTableTileEntity.class, "hammeringtabletile");
	}

	public static Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		
		switch (ID) {
		case ArmorStationID:
			return new ArmorStationContainer(player.inventory, (ArmorStationTile) world.getTileEntity(pos));
		case FinishingAnvilID:
			return new FinishingAnvilContainer(player.inventory, (FinishingAnvilTile) world.getTileEntity(pos));
		}
		return null;
	}

	public static Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		
		switch (ID) {
		case ArmorStationID:
			return new ArmorStationGui(player.inventory, world, pos,
					(ArmorStationTile) world.getTileEntity(pos));
		case FinishingAnvilID:
			return new FinishingAnvilGui(player.inventory, world, pos,
					(FinishingAnvilTile) world.getTileEntity(pos));
		}
		return null;
	}

	private void registerRecipes() {
		registerHammeringTableRecipes();
	}


	private void registerHammeringTableRecipes() {
		HammeringTableRecipeRegistry.instance().addHammeringRecipe(new ItemStack(Blocks.STONE), new ItemStack(Blocks.COBBLESTONE));
		HammeringTableRecipeRegistry.instance().addHammeringRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.GRAVEL));
		HammeringTableRecipeRegistry.instance().addHammeringRecipe(new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.SAND));
		
		HammeringTableRecipeRegistry.instance().addHammeringRecipe(new ItemStack(Blocks.STONEBRICK), new ItemStack(Blocks.STONEBRICK,1,1));
		HammeringTableRecipeRegistry.instance().addHammeringRecipe(new ItemStack(Blocks.STONEBRICK,1,1), new ItemStack(Blocks.COBBLESTONE));
		
		HammeringTableRecipeRegistry.instance().addHammeringRecipe(new ItemStack(Blocks.SANDSTONE), new ItemStack(Blocks.SAND,4));
		HammeringTableRecipeRegistry.instance().addHammeringRecipe(new ItemStack(Blocks.SANDSTONE,1,1), new ItemStack(Blocks.SAND,4));
		HammeringTableRecipeRegistry.instance().addHammeringRecipe(new ItemStack(Blocks.SANDSTONE,1,2), new ItemStack(Blocks.SAND,4));
		HammeringTableRecipeRegistry.instance().addHammeringRecipe(new ItemStack(Blocks.SANDSTONE,1,3), new ItemStack(Blocks.SAND,4));
		
		HammeringTableRecipeRegistry.instance().addHammeringRecipe(new ItemStack(Blocks.COBBLESTONE_WALL), new ItemStack(Blocks.COBBLESTONE,6));
	}

}
