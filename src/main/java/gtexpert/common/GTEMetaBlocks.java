package gtexpert.common;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static gregtech.common.blocks.MetaBlocks.statePropertiesToString;

public class GTEMetaBlocks {

    public static final GTEBlockMetalCasing GTE_BLOCK_METAL_CASING = new GTEBlockMetalCasing();
    public static final BlockSawmillConveyor GTE_BLOCK_SAWMILL_CONVEYOR = new BlockSawmillConveyor();

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        registerItemModel(GTE_BLOCK_METAL_CASING);
        registerItemModel(GTE_BLOCK_SAWMILL_CONVEYOR);
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            //noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            statePropertiesToString(state.getProperties())));
        }
    }
}
