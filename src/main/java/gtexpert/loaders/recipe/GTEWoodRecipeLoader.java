package gtexpert.loaders.recipe;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.wood.BlockGregPlanks;

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEConfigHolder;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.api.util.GTEUtility.getModItem;

public class GTEWoodRecipeLoader {

    public static void init() {
        sticks();
        planks();
    }

    private static void sticks() {
        if (ConfigHolder.recipes.moreNerfStickCrafting) {
            ModHandler.removeRecipeByName(new ResourceLocation(GTValues.MODID, "stick_normal"));
            ModHandler.addMirroredShapedRecipe("stick_normal", GTEConfigHolder.ceuOverride.moreNerfStickCrafting ?
                    new ItemStack(Items.STICK, 1) : new ItemStack(Items.STICK, 2),
                    "P", "P", 'P', new UnificationEntry(plank, Wood));
            ModHandler.removeRecipeByName(new ResourceLocation(GTValues.MODID, "stick_saw"));
            ModHandler.addMirroredShapedRecipe("stick_saw", GTEConfigHolder.ceuOverride.moreNerfStickCrafting ?
                    new ItemStack(Items.STICK, 2) : new ItemStack(Items.STICK, 4),
                    "s", "P", "P", 'P', new UnificationEntry(plank, Wood));

            ModHandler.removeRecipeByName(new ResourceLocation(GTValues.MODID, "treated_wood_stick"));
            ModHandler.addMirroredShapedRecipe("treated_wood_stick", GTEConfigHolder.ceuOverride.moreNerfStickCrafting ?
                    OreDictUnifier.get(stick, TreatedWood, 1) : OreDictUnifier.get(stick, TreatedWood, 2),
                    "P", "P", 'P', MetaBlocks.PLANKS.getItemVariant(BlockGregPlanks.BlockType.TREATED_PLANK));
            ModHandler.removeRecipeByName(new ResourceLocation(GTValues.MODID, "treated_wood_stick_saw"));
            ModHandler.addMirroredShapedRecipe("treated_wood_stick_saw",
                    GTEConfigHolder.ceuOverride.moreNerfStickCrafting ?
                            OreDictUnifier.get(stick, TreatedWood, 2) : OreDictUnifier.get(stick, TreatedWood, 4),
                    "s", "P", "P", 'P', MetaBlocks.PLANKS.getItemVariant(BlockGregPlanks.BlockType.TREATED_PLANK));
        }
    }

    private static void planks() {
        List<ItemStack> allWoodLogs = new LinkedList<>();
        for (ItemStack stack : OreDictionary.getOres("logWood")) {
            allWoodLogs.addAll(stack.getItemDamage() != 32767 ? Collections.singleton(stack) :
                    GTUtility.getAllSubItems(stack.getItem()));
        }
        IntStream.range(0, allWoodLogs.size()).forEach(i -> {
            Pair<IRecipe, ItemStack> outputPair = ModHandler.getRecipeOutput(null, allWoodLogs.get(i));
            ItemStack plankStack = outputPair.getValue();
            if (plankStack.isEmpty()) return;
            ModHandler.removeRecipeByOutput(GTUtility.copy(ConfigHolder.recipes.nerfWoodCrafting ? 2 : 4, plankStack));
            ModHandler.removeRecipeByOutput(GTUtility.copy(ConfigHolder.recipes.nerfWoodCrafting ? 4 : 6, plankStack));
            ModHandler.addShapelessRecipe("plank_" + i, GTUtility.copy(
                    ConfigHolder.recipes.nerfWoodCrafting ? GTEConfigHolder.ceuOverride.moreNerfPlankCrafting ? 1 : 2 :
                            4,
                    plankStack), allWoodLogs.get(i));
            ModHandler.addMirroredShapedRecipe("plank_saw_" + i, GTUtility.copy(
                    ConfigHolder.recipes.nerfWoodCrafting ? GTEConfigHolder.ceuOverride.moreNerfPlankCrafting ? 2 : 4 :
                            6,
                    plankStack), "s", "P", 'P', allWoodLogs.get(i));
            recipeSawmill(allWoodLogs.get(i), plankStack);
            recipeCutter(allWoodLogs.get(i), plankStack);
        });
    }

    private static void recipeSawmill(ItemStack input, ItemStack output) {
        GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .inputs(GTUtility.copy(6, input))
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTUtility.copy(48, output))
                .output(dust, Wood, 12)
                .duration(600).EUt(VA[LV])
                .buildAndRegister();
        GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(GTUtility.copy(6, input))
                .fluidInputs(Water.getFluid(2500))
                .outputs(GTUtility.copy(60, output))
                .duration(800).EUt(VA[LV])
                .buildAndRegister();
    }

    private static void recipeCutter(ItemStack input, ItemStack output) {
        if (input.equals(new ItemStack(Blocks.LOG, 1, 0)) ||
                input.equals(new ItemStack(Blocks.LOG, 1, 1)) ||
                input.equals(new ItemStack(Blocks.LOG, 1, 2)) ||
                input.equals(new ItemStack(Blocks.LOG, 1, 3)) ||
                input.equals(new ItemStack(Blocks.LOG2, 1, 0)) ||
                input.equals(new ItemStack(Blocks.LOG2, 1, 1)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_0", 1, 0)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_0", 1, 4)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_0", 1, 8)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_0", 1, 12)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_1", 1, 0)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_1", 1, 4)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_1", 1, 8)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_1", 1, 12)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_2", 1, 0)))
            return;

        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(input)
                .fluidInputs(Lubricant.getFluid(1))
                .outputs(GTUtility.copy(6, output))
                .output(dust, Wood, 2)
                .duration(200).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(input)
                .fluidInputs(DistilledWater.getFluid(3))
                .outputs(GTUtility.copy(6, output))
                .output(dust, Wood, 2)
                .duration(300).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(input)
                .fluidInputs(Water.getFluid(4))
                .outputs(GTUtility.copy(6, output))
                .output(dust, Wood, 2)
                .duration(400).EUt(VA[ULV])
                .buildAndRegister();
    }
}
