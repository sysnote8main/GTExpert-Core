package gtexpert.loaders.recipe;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.items.MetaItems;

import gtexpert.api.GTEValues;
import gtexpert.common.GTEConfigHolder;
import gtexpert.integration.ae.AEHelper;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import crazypants.enderio.base.init.ModObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.common.items.GTEMetaItems.MATRIX_CORE;

public class AERecipeLoader {

    private static final Material[] tierMaterials = new Material[] {
            WroughtIron,
            Steel,
            Aluminium,
            StainlessSteel,
            Titanium,
            TungstenSteel,
            RhodiumPlatedPalladium,
            NaquadahAlloy,
            Darmstadtium,
            Neutronium
    };

    public static void init() {
        // craftStickQuartz
        OreDictionary.registerOre("craftStickQuartz", OreDictUnifier.get(stick, NetherQuartz));
        OreDictionary.registerOre("craftStickQuartz", OreDictUnifier.get(stick, CertusQuartz));
        OreDictionary.registerOre("craftStickQuartz", OreDictUnifier.get(stick, Quartzite));

        // craftNetherQuartz
        OreDictionary.registerOre("craftNetherQuartz", OreDictUnifier.get(gem, NetherQuartz));
        OreDictionary.registerOre("craftNetherQuartz",
                AEHelper.aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get());

        // craftCertusQuartz
        OreDictionary.registerOre("craftCertusQuartz", OreDictUnifier.get(gem, CertusQuartz));
        OreDictionary.registerOre("craftCertusQuartz", AEHelper.aeMaterials.certusQuartzCrystal().maybeStack(1).get());
        OreDictionary.registerOre("craftCertusQuartz",
                AEHelper.aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get());

        // craftFluix
        OreDictionary.registerOre("craftFluix", AEHelper.aeMaterials.fluixCrystal().maybeStack(1).get());
        OreDictionary.registerOre("craftFluix", AEHelper.aeMaterials.purifiedFluixCrystal().maybeStack(1).get());

        // craftGlassCable
        List<ItemStack> craftGlassCable = new LinkedList<>();
        IntStream.rangeClosed(0, 16).forEach(i -> {
            craftGlassCable.add(getModItem(GTEValues.MODID_AE, "part", 1, i));
            ModHandler.removeRecipeByOutput(getModItem(GTEValues.MODID_AE, "part", 8, i));
        });
        {
            Iterator<ItemStack> iterator = craftGlassCable.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                OreDictionary.registerOre("craftGlassCable", stack);

                if (iterator.hasNext()) OreDictionary.registerOre("craftGlassCableColors", stack);
            }
        }

        // craftCoveredCable
        List<ItemStack> craftCoveredCable = new LinkedList<>();
        IntStream.rangeClosed(20, 36).forEach(i -> {
            craftCoveredCable.add(getModItem(GTEValues.MODID_AE, "part", 1, i));
            ModHandler.removeRecipeByOutput(getModItem(GTEValues.MODID_AE, "part", 8, i));
        });
        {
            Iterator<ItemStack> iterator = craftCoveredCable.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                OreDictionary.registerOre("craftCoveredCable", stack);

                if (iterator.hasNext()) OreDictionary.registerOre("craftCoveredCableColors", stack);
            }
        }

        // craftSmartCable
        List<ItemStack> craftSmartCable = new LinkedList<>();
        IntStream.rangeClosed(40, 56).forEach(i -> {
            craftSmartCable.add(getModItem(GTEValues.MODID_AE, "part", 1, i));
            ModHandler.removeRecipeByOutput(getModItem(GTEValues.MODID_AE, "part", 8, i));
        });
        {
            Iterator<ItemStack> iterator = craftSmartCable.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                OreDictionary.registerOre("craftSmartCable", stack);

                if (iterator.hasNext()) OreDictionary.registerOre("craftSmartCableColors", stack);
            }
        }

        // craftDenseCoveredCable
        List<ItemStack> craftDenseCoveredCable = new LinkedList<>();
        IntStream.rangeClosed(500, 516).forEach(i -> {
            craftDenseCoveredCable.add(getModItem(GTEValues.MODID_AE, "part", 1, i));
            ModHandler.removeRecipeByOutput(getModItem(GTEValues.MODID_AE, "part", 8, i));
        });
        {
            Iterator<ItemStack> iterator = craftDenseCoveredCable.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                OreDictionary.registerOre("craftDenseCoveredCable", stack);

                if (iterator.hasNext()) OreDictionary.registerOre("craftDenseCoveredCableColors", stack);
            }
        }

        // craftDenseSmartCable
        List<ItemStack> craftDenseSmartCable = new LinkedList<>();
        IntStream.rangeClosed(60, 76).forEach(i -> {
            craftDenseSmartCable.add(getModItem(GTEValues.MODID_AE, "part", 1, i));
            ModHandler.removeRecipeByOutput(getModItem(GTEValues.MODID_AE, "part", 8, i));
        });
        Iterator<ItemStack> iterator = craftDenseSmartCable.iterator();
        while (iterator.hasNext()) {
            ItemStack stack = iterator.next();
            OreDictionary.registerOre("craftDenseSmartCable", stack);

            if (iterator.hasNext()) OreDictionary.registerOre("craftDenseSmartCableColors", stack);
        }

        materials();
        items();
        blocks();
        tools();
    }

    private static void materials() {
        // Iron Ingot
        ModHandler.removeFurnaceSmelting(AEHelper.aeMaterials.ironDust().maybeStack(1).get());

        // Gold Ingot
        ModHandler.removeFurnaceSmelting(AEHelper.aeMaterials.goldDust().maybeStack(1).get());


        // ########################################
        // Sky Stone
        // ########################################
        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.skyStoneBlock().maybeStack(1).get())
                .outputs(AEHelper.aeMaterials.skyDust().maybeStack(1).get())
                .duration(500).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();

        // Block
        ModHandler.removeFurnaceSmelting(AEHelper.aeBlocks.skyStoneBlock().maybeStack(1).get());
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .input(Item.getItemFromBlock(ModObject.block_infinity.getBlockNN()), 4, 2)
                .outputs(AEHelper.aeBlocks.skyStoneBlock().maybeStack(1).get())
                .duration(500).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.skyStoneBlock().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.smoothSkyStoneBlock().maybeStack(1).get())
                .duration(100).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .blastFurnaceTemp(2700)
                .buildAndRegister();
        RecipeMaps.ROCK_BREAKER_RECIPES.recipeBuilder()
                .notConsumable(AEHelper.aeBlocks.skyStoneBlock().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.skyStoneBlock().maybeStack(1).get())
                .duration(100).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();


        // ########################################
        // Nether Quartz
        // ########################################
        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
                .fluidOutputs(NetherQuartz.getFluid(72))
                .duration(14).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
                .output(dustSmall, NetherQuartz, 2)
                .duration(14).EUt(2)
                .buildAndRegister();

        // Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/quartz_block_pure"));
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedNetherQuartzCrystal().maybeStack(8).get())
                .output(block, NetherQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Rod
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
                .output(stick, NetherQuartz, 1)
                .duration(40).EUt(VH[LV])
                .buildAndRegister();


        // ########################################
        // Certus Quartz
        // ########################################
        OreDictionary.registerOre("gemCertusQuartz", AEHelper.aeMaterials.certusQuartzCrystal().maybeStack(1).get());
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES, OreDictUnifier.get(block, CertusQuartz, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.COMPRESSOR_RECIPES, OreDictUnifier.get(gem, CertusQuartz, 9));

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .fluidOutputs(CertusQuartz.getFluid(72))
                .duration(14).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .output(dustSmall, CertusQuartz, 2)
                .duration(14).EUt(2)
                .buildAndRegister();

        // Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/certus_quartz_block"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/certus_quartz_block_pure"));
        ModHandler.removeRecipeByOutput(AEHelper.aeMaterials.certusQuartzCrystal().maybeStack(4).get());
        ModHandler.addMirroredShapedRecipe("ae2_certus_quartz_block",
                AEHelper.aeBlocks.quartzBlock().maybeStack(1).get(), "B", 'B',
                new UnificationEntry(block, CertusQuartz));
        ModHandler.addMirroredShapedRecipe("ceu_certus_quartz_block", OreDictUnifier.get(block, CertusQuartz), "B", 'B',
                AEHelper.aeBlocks.quartzBlock().maybeStack(1).get());
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedCertusQuartzCrystal().maybeStack(8).get())
                .output(block, CertusQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Rod
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .output(stick, CertusQuartz, 1)
                .duration(40).EUt(VH[LV])
                .buildAndRegister();


        // ########################################
        // Charged Certus Quartz
        // ########################################
        OreDictionary.registerOre("gemChargedCertusQuartz",
                AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get());
        OreDictionary.registerOre("crystalChargedCertusQuartz",
                AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get());

        // Gem
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(gem, CertusQuartz, 1)
                .outputs(AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get())
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, CertusQuartz, 1)
                .output(dust, ChargedCertusQuartz, 1)
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, ChargedCertusQuartz, 1)
                .fluidInputs(DistilledWater.getFluid(50))
                .outputs(AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get())
                .duration(600).EUt(24)
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, ChargedCertusQuartz, 1)
                .fluidInputs(Water.getFluid(250))
                .chancedOutput(AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(24)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, ChargedCertusQuartz, 4)
                .explosivesAmount(2)
                .outputs(AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(3).get())
                .output(dustSmall, DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, ChargedCertusQuartz, 4)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .outputs(AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(3).get())
                .output(dustSmall, DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Lens
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .input(plate, ChargedCertusQuartz, 1)
                .output(lens, ChargedCertusQuartz, 1)
                .output(dustSmall, ChargedCertusQuartz, 1)
                .duration(1200).EUt(VA[MV])
                .buildAndRegister();


        // ########################################
        // Fluix
        // ########################################
        OreDictionary.registerOre("blockFluix", AEHelper.aeBlocks.fluixBlock().maybeStack(1).get());
        OreDictionary.registerOre("gemFluix", AEHelper.aeMaterials.fluixCrystal().maybeStack(1).get());

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedFluixCrystal().maybeStack(1).get())
                .fluidOutputs(Fluix.getFluid(72))
                .duration(14).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, ChargedCertusQuartz, 1)
                .input(dust, Redstone, 1)
                .input(dust, NetherQuartz, 1)
                .output(dust, Fluix, 3)
                .duration(200).EUt(VA[3])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedFluixCrystal().maybeStack(1).get())
                .output(dustSmall, Fluix, 2)
                .duration(14).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, Fluix, 1)
                .fluidInputs(DistilledWater.getFluid(50))
                .outputs(AEHelper.aeMaterials.fluixCrystal().maybeStack(1).get())
                .duration(600).EUt(24)
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, Fluix, 1)
                .fluidInputs(Water.getFluid(250))
                .chancedOutput(AEHelper.aeMaterials.fluixCrystal().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(24)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, Fluix, 4)
                .explosivesAmount(2)
                .outputs(AEHelper.aeMaterials.fluixCrystal().maybeStack(3).get())
                .output(dustSmall, DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, Fluix, 4)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .outputs(AEHelper.aeMaterials.fluixCrystal().maybeStack(3).get())
                .output(dustSmall, DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/fluix_block"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/fluix_block_pure"));
        ModHandler.removeRecipeByOutput(AEHelper.aeMaterials.fluixCrystal().maybeStack(4).get());
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.fluixCrystal().maybeStack(4).get())
                .outputs(AEHelper.aeBlocks.fluixBlock().maybeStack(1).get())
                .duration(300).EUt(2)
                .buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedFluixCrystal().maybeStack(8).get())
                .outputs(AEHelper.aeBlocks.fluixBlock().maybeStack(1).get())
                .duration(300).EUt(2)
                .buildAndRegister();

        // Lens
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .input(plate, Fluix, 1)
                .output(lens, Fluix, 1)
                .output(dustSmall, Fluix, 1)
                .duration(1200).EUt(VA[MV])
                .buildAndRegister();


        // ########################################
        // Fluix Alloy
        // ########################################
        // Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(AEHelper.aeMaterials.skyDust().maybeStack(2).get())
                .input(dust, Fluix, 2)
                .input(dust, Carbon, 2)
                .input(dust, Silicon, 1)
                .input(dust, Iron, 1)
                .output(dust, FluixAlloy, 8)
                .duration(200).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();
    }

    private static void blocks() {
        // Quartz Fiber
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/parts/quartz_fiber_part"));
        ModHandler.addMirroredShapedRecipe("nether_quartz_cutter_wire",
                AEHelper.aeParts.quartzFiber().maybeStack(1).get(), "Px", 'P', OreDictUnifier.get(plate, NetherQuartz));
        ModHandler.addMirroredShapedRecipe("certus_quartz_cutter_wire",
                AEHelper.aeParts.quartzFiber().maybeStack(1).get(), "Px", 'P', OreDictUnifier.get(plate, CertusQuartz));
        ModHandler.addMirroredShapedRecipe("quartzite_cutter_wire", AEHelper.aeParts.quartzFiber().maybeStack(1).get(),
                "Px", 'P', OreDictUnifier.get(plate, Quartzite));
        RecipeMaps.WIREMILL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input("craftStickQuartz", 1)
                .outputs(AEHelper.aeParts.quartzFiber().maybeStack(2).get())
                .duration(20).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();

        // Glass Cable
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cables/glass_fluix_clean"));
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftGlassCableColors", 1)
                .fluidInputs(Chlorine.getFluid(25))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 16))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftGlassCable")
                .fluidInputs(CHEMICAL_DYES[i].getFluid(18))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());

        // Covered Cable
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cables/covered_fluix_clean"));
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftCoveredCableColors", 1)
                .fluidInputs(Chlorine.getFluid(25))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 36))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftCoveredCable")
                .fluidInputs(CHEMICAL_DYES[i].getFluid(18))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 20 + i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());

        // Smart Cable
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cables/smart_fluix_clean"));
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftSmartCableColors", 1)
                .fluidInputs(Chlorine.getFluid(25))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 56))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftSmartCable")
                .fluidInputs(CHEMICAL_DYES[i].getFluid(18))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 40 + i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());

        // Dense Covered Cable
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cables/dense_covered_fluix_clean"));
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseCoveredCableColors", 1)
                .fluidInputs(Chlorine.getFluid(25))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 516))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseCoveredCable")
                .fluidInputs(CHEMICAL_DYES[i].getFluid(18))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 500 + i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());

        // Dense Smart Cable
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cables/dense_smart_fluix_clean"));
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseSmartCableColors", 1)
                .fluidInputs(Chlorine.getFluid(25))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 76))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseSmartCable")
                .fluidInputs(CHEMICAL_DYES[i].getFluid(18))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 60 + i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());

        // Crafting Monitor
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_monitor"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                .inputs(AEHelper.aeParts.storageMonitor().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.craftingMonitor().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.craftingMonitor().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                .outputs(AEHelper.aeParts.storageMonitor().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1k Crafting Storage
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_1k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.cell1kPart().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.craftingStorage1k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.craftingStorage1k().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                .outputs(AEHelper.aeMaterials.cell1kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4k Crafting Storage
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_4k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.cell4kPart().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.craftingStorage4k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.craftingStorage4k().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                .outputs(AEHelper.aeMaterials.cell4kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16k Crafting Storage
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_16k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.cell16kPart().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.craftingStorage16k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.craftingStorage16k().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                .outputs(AEHelper.aeMaterials.cell16kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 64k Crafting Storage
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_64k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.cell64kPart().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.craftingStorage64k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.craftingStorage64k().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                .outputs(AEHelper.aeMaterials.cell64kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        if (Loader.isModLoaded(GTEValues.MODID_AEA) && Loader.isModLoaded(GTEValues.MODID_EXCPU)) {
            // 256k Crafting Storage
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPU, "crafting_storage_256k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 0))
                    .outputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_256k", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_256k", 1, 0))
                    .outputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 1024k Crafting Storage
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPU, "crafting_storage_1024k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                    .outputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_1024k", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_1024k", 1, 0))
                    .outputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 4096k Crafting Storage
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPU, "crafting_storage_4096k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                    .outputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_4096k", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_4096k", 1, 0))
                    .outputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 16384k Crafting Storage
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPU, "crafting_storage_16384k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                    .outputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_16384k", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_16384k", 1, 0))
                    .outputs(AEHelper.aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
        }
    }

    private static void items() {
        // 1k Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_1k"));
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_1k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.cell1kPart().maybeStack(1).get())
                .outputs(AEHelper.aeItems.cell1k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(AEHelper.aeItems.cell1k().maybeStack(1).get().getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(AEHelper.aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(AEHelper.aeMaterials.cell1kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4k Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_4k"));
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_4k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.cell4kPart().maybeStack(1).get())
                .outputs(AEHelper.aeItems.cell4k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(AEHelper.aeItems.cell4k().maybeStack(1).get().getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(AEHelper.aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(AEHelper.aeMaterials.cell4kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16k Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_16k"));
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_16k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.cell16kPart().maybeStack(1).get())
                .outputs(AEHelper.aeItems.cell16k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(AEHelper.aeItems.cell16k().maybeStack(1).get().getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(AEHelper.aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(AEHelper.aeMaterials.cell16kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 64k Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_64k"));
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_64k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.cell64kPart().maybeStack(1).get())
                .outputs(AEHelper.aeItems.cell64k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(AEHelper.aeItems.cell64k().maybeStack(1).get().getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(AEHelper.aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(AEHelper.aeMaterials.cell64kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1k Fluid Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_1k"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_1k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .inputs(AEHelper.aeMaterials.fluidCell1kPart().maybeStack(1).get())
                .outputs(AEHelper.aeItems.fluidCell1k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(AEHelper.aeItems.fluidCell1k().maybeStack(1).get().getItem(), NBTMatcher.ANY,
                        NBTCondition.ANY)
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .outputs(AEHelper.aeMaterials.fluidCell1kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4k Fluid Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_4k"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_4k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .inputs(AEHelper.aeMaterials.fluidCell4kPart().maybeStack(1).get())
                .outputs(AEHelper.aeItems.fluidCell4k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(AEHelper.aeItems.fluidCell4k().maybeStack(1).get().getItem(), NBTMatcher.ANY,
                        NBTCondition.ANY)
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .outputs(AEHelper.aeMaterials.fluidCell4kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16k Fluid Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_16k"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_16k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .inputs(AEHelper.aeMaterials.fluidCell16kPart().maybeStack(1).get())
                .outputs(AEHelper.aeItems.fluidCell16k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(AEHelper.aeItems.fluidCell16k().maybeStack(1).get().getItem(), NBTMatcher.ANY,
                        NBTCondition.ANY)
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .outputs(AEHelper.aeMaterials.fluidCell16kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 64k Fluid Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_64k"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_64k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .inputs(AEHelper.aeMaterials.fluidCell64kPart().maybeStack(1).get())
                .outputs(AEHelper.aeItems.fluidCell64k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(AEHelper.aeItems.fluidCell64k().maybeStack(1).get().getItem(), NBTMatcher.ANY,
                        NBTCondition.ANY)
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .outputs(AEHelper.aeMaterials.fluidCell64kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 2³ Spatial Storage Cell
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/spatial_storage_cell_2_cubed"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/spatial_storage_cell_2_cubed_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .inputs(AEHelper.aeMaterials.cell2SpatialPart().maybeStack(1).get())
                .outputs(AEHelper.aeItems.spatialCell2().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(AEHelper.aeItems.spatialCell2().maybeStack(1).get().getItem(), NBTMatcher.ANY,
                        NBTCondition.ANY)
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .outputs(AEHelper.aeMaterials.cell2SpatialPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16³ Spatial Cell
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/spatial_storage_cell_16_cubed"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/spatial_storage_cell_16_cubed_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .inputs(AEHelper.aeMaterials.cell16SpatialPart().maybeStack(1).get())
                .outputs(AEHelper.aeItems.spatialCell16().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(AEHelper.aeItems.spatialCell16().maybeStack(1).get().getItem(), NBTMatcher.ANY,
                        NBTCondition.ANY)
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .outputs(AEHelper.aeMaterials.cell16SpatialPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 128³ Spatial Cell
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/spatial_storage_cell_128_cubed"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/spatial_storage_cell_128_cubed_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .inputs(AEHelper.aeMaterials.cell128SpatialPart().maybeStack(1).get())
                .outputs(AEHelper.aeItems.spatialCell128().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(AEHelper.aeItems.spatialCell128().maybeStack(1).get().getItem(), NBTMatcher.ANY,
                        NBTCondition.ANY)
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .outputs(AEHelper.aeMaterials.cell128SpatialPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 256k Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/owncasing/256k"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/extracasing/256k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 0))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 0))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 0))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 0))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1024k Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/owncasing/1024k"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/extracasing/1024k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 1))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 1))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4096k Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/owncasing/4096k"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/extracasing/4096k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 2))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 2))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16384k Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/owncasing/16384k"));
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/extracasing/16384k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 3))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 3))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 256k Fluid Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/owncasing/256k"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/extracasing/256k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 4))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 0))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 0))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 4))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1024k Fluid Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/owncasing/1024k"));
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/extracasing/1024k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 5))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 1))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 1))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 5))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4096k Fluid Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/owncasing/4096k"));
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/extracasing/4096k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 6))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 2))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 2))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 6))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // Recycle - Storage Housing
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/empty_storage_cell"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/e2acasing"));
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.emptyStorageCell().maybeStack(1).get())
                .output(dust, Steel, 2)
                .output(dustTiny, Steel, 2)
                .duration(100).EUt(VH[LV])
                .buildAndRegister();
        RecipeMaps.ARC_FURNACE_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.emptyStorageCell().maybeStack(1).get())
                .fluidInputs(Oxygen.getFluid(56))
                .output(ingot, Steel, 2)
                .output(nugget, Steel, 2)
                .duration(56).EUt(VA[LV])
                .buildAndRegister();

        // Recycle - Fluid Storage Housing
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/case/fluid"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/a2ecasing"));
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .output(dust, StainlessSteel, 2)
                .output(dustTiny, StainlessSteel, 2)
                .duration(100).EUt(VH[LV])
                .buildAndRegister();
        RecipeMaps.ARC_FURNACE_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .fluidInputs(Oxygen.getFluid(56))
                .output(ingot, StainlessSteel, 2)
                .output(nugget, StainlessSteel, 2)
                .duration(56).EUt(VA[LV])
                .buildAndRegister();

        // Recycle - Advanced Storage Housing
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/case/item"));
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .output(dust, TungstenSteel, 2)
                .output(dustTiny, TungstenSteel, 2)
                .duration(100).EUt(VH[LV])
                .buildAndRegister();
        RecipeMaps.ARC_FURNACE_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                .fluidInputs(Oxygen.getFluid(56))
                .output(ingot, TungstenSteel, 2)
                .output(nugget, TungstenSteel, 2)
                .duration(56).EUt(VA[LV])
                .buildAndRegister();

        // Formation Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "materials/formationcore"));
        ModHandler.addShapedRecipe("formation_core", AEHelper.aeMaterials.formationCore().maybeStack(1).get(),
                "SES", "LQL", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEConfigHolder.ae2Integration.voltageTier - 1]),
                'Q', "gemNetherQuartz",
                'E', AEHelper.aeMaterials.engProcessor().maybeStack(1).get(),
                'L', AEHelper.aeMaterials.logicProcessor().maybeStack(1).get());
        ModHandler.addShapedRecipe("formation_core_pure", AEHelper.aeMaterials.formationCore().maybeStack(2).get(),
                "SES", "LQL", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEConfigHolder.ae2Integration.voltageTier - 1]),
                'Q', AEHelper.aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get(),
                'E', AEHelper.aeMaterials.engProcessor().maybeStack(1).get(),
                'L', AEHelper.aeMaterials.logicProcessor().maybeStack(1).get());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stick, tierMaterials[GTEConfigHolder.ae2Integration.voltageTier - 1], 2)
                .inputs(AEHelper.aeMaterials.engProcessor().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.logicProcessor().maybeStack(1).get())
                .input("craftNetherQuartz", 1)
                .outputs(AEHelper.aeMaterials.formationCore().maybeStack(4).get())
                .duration(20).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier + 1])
                .buildAndRegister();

        // Annihilation Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "materials/annihilationcore"));
        ModHandler.addShapedRecipe("annihilation_core", AEHelper.aeMaterials.annihilationCore().maybeStack(1).get(),
                "SES", "CQC", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEConfigHolder.ae2Integration.voltageTier - 1]),
                'Q', "gemCertusQuartz",
                'E', AEHelper.aeMaterials.engProcessor().maybeStack(1).get(),
                'C', AEHelper.aeMaterials.calcProcessor().maybeStack(1).get());
        ModHandler.addShapedRecipe("annihilation_core_pure",
                AEHelper.aeMaterials.annihilationCore().maybeStack(2).get(),
                "SES", "CQC", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEConfigHolder.ae2Integration.voltageTier - 1]),
                'Q', AEHelper.aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get(),
                'E', AEHelper.aeMaterials.engProcessor().maybeStack(1).get(),
                'C', AEHelper.aeMaterials.calcProcessor().maybeStack(1).get());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stick, tierMaterials[GTEConfigHolder.ae2Integration.voltageTier - 1], 2)
                .inputs(AEHelper.aeMaterials.engProcessor().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.calcProcessor().maybeStack(1).get())
                .input("craftCertusQuartz", 1)
                .outputs(AEHelper.aeMaterials.annihilationCore().maybeStack(4).get())
                .duration(20).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier + 1])
                .buildAndRegister();

        // Matrix Core
        ModHandler.addShapedRecipe("matrix_core", MATRIX_CORE.getStackForm(),
                "SAS", "FQF", "SAS",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEConfigHolder.ae2Integration.voltageTier - 1]),
                'Q', AEHelper.aeMaterials.fluixCrystal().maybeStack(1).get(),
                'A', AEHelper.aeMaterials.annihilationCore().maybeStack(1).get(),
                'F', AEHelper.aeMaterials.formationCore().maybeStack(1).get());
        ModHandler.addShapedRecipe("matrix_core_pure", MATRIX_CORE.getStackForm(2),
                "SAS", "FQF", "SAS",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEConfigHolder.ae2Integration.voltageTier - 1]),
                'Q', AEHelper.aeMaterials.purifiedFluixCrystal().maybeStack(1).get(),
                'A', AEHelper.aeMaterials.annihilationCore().maybeStack(1).get(),
                'F', AEHelper.aeMaterials.formationCore().maybeStack(1).get());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(stick, tierMaterials[GTEConfigHolder.ae2Integration.voltageTier - 1], 2)
                .inputs(AEHelper.aeMaterials.annihilationCore().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.formationCore().maybeStack(1).get())
                .input("craftFluix", 1)
                .output(MATRIX_CORE, 4)
                .duration(20).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier + 1])
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(stick, tierMaterials[GTEConfigHolder.ae2Integration.voltageTier - 1], 4)
                .inputs(AEHelper.aeMaterials.engProcessor().maybeStack(2).get())
                .inputs(AEHelper.aeMaterials.logicProcessor().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.calcProcessor().maybeStack(1).get())
                .input("craftNetherQuartz", 1)
                .input("craftCertusQuartz", 1)
                .input("craftFluix", 1)
                .output(MATRIX_CORE, 4)
                .duration(100).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier + 1])
                .buildAndRegister();

        // Silicon Processor Press
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, NetherQuartz)
                .input(block, Iron, 1)
                .outputs(AEHelper.aeMaterials.siliconPress().maybeStack(1).get())
                .duration(2000).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();

        // Logic Processor Press
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, ChargedCertusQuartz)
                .input(block, Iron, 1)
                .outputs(AEHelper.aeMaterials.logicProcessorPress().maybeStack(1).get())
                .duration(2000).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();

        // Calc Processor Press
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, CertusQuartz)
                .input(block, Iron, 1)
                .outputs(AEHelper.aeMaterials.calcProcessorPress().maybeStack(1).get())
                .duration(2000).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();

        // Engineer Processor Press
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, Fluix)
                .input(block, Iron, 1)
                .outputs(AEHelper.aeMaterials.engProcessorPress().maybeStack(1).get())
                .duration(2000).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();

        // Silicon Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(AEHelper.aeMaterials.siliconPress().maybeStack(1).get())
                .input(plate, Silicon, 1)
                .outputs(AEHelper.aeMaterials.siliconPrint().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();

        // Logic Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(AEHelper.aeMaterials.logicProcessorPress().maybeStack(1).get())
                .input(plate, Gold, 1)
                .outputs(AEHelper.aeMaterials.logicProcessorPrint().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();

        // Calc Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(AEHelper.aeMaterials.calcProcessorPress().maybeStack(1).get())
                .input(plate, CertusQuartz, 1)
                .outputs(AEHelper.aeMaterials.calcProcessorPrint().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();

        // Engineer Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(AEHelper.aeMaterials.engProcessorPress().maybeStack(1).get())
                .input(plate, Diamond, 1)
                .outputs(AEHelper.aeMaterials.engProcessorPrint().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();

        // Logic Processor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.siliconPrint().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.logicProcessorPrint().maybeStack(1).get())
                .fluidInputs(Redstone.getFluid(144))
                .outputs(AEHelper.aeMaterials.logicProcessor().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();

        // Calc Processor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.siliconPrint().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.calcProcessorPrint().maybeStack(1).get())
                .fluidInputs(Redstone.getFluid(144))
                .outputs(AEHelper.aeMaterials.calcProcessor().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();

        // Engineer Processor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.siliconPrint().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.engProcessorPrint().maybeStack(1).get())
                .fluidInputs(Redstone.getFluid(144))
                .outputs(AEHelper.aeMaterials.engProcessor().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                .buildAndRegister();
    }

    private static void tools() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && GTEConfigHolder.ae2Integration.hardToolRecipes) {
            // Nether Quartz Axe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_axe"));
            ModHandler.addShapedRecipe("nether_quartz_axe", AEHelper.aeItems.netherQuartzAxe().maybeStack(1).get(),
                    "PQf", "PS ", "hS ",
                    'P', OreDictUnifier.get(plate, NetherQuartz),
                    'Q', OreDictUnifier.get(gem, NetherQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Nether Quartz Hoe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_hoe"));
            ModHandler.addShapedRecipe("nether_quartz_hoe", AEHelper.aeItems.netherQuartzHoe().maybeStack(1).get(),
                    "PQf", "hS ", " S ",
                    'P', OreDictUnifier.get(plate, NetherQuartz),
                    'Q', OreDictUnifier.get(gem, NetherQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Nether Quartz Pickaxe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_pickaxe"));
            ModHandler.addShapedRecipe("nether_quartz_pickaxe", AEHelper.aeItems.netherQuartzPick().maybeStack(1).get(),
                    "PQQ", "hSf", " S ",
                    'P', OreDictUnifier.get(plate, NetherQuartz),
                    'Q', OreDictUnifier.get(gem, NetherQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Nether Quartz Shovel
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_spade"));
            ModHandler.addShapedRecipe("nether_quartz_spade", AEHelper.aeItems.netherQuartzShovel().maybeStack(1).get(),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, NetherQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Nether Quartz Sword
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_sword"));
            ModHandler.addShapedRecipe("nether_quartz_sword", AEHelper.aeItems.netherQuartzSword().maybeStack(1).get(),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, NetherQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Nether Quartz Cutting Knife
            ModHandler
                    .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_cutting_knife"));
            ModHandler.addShapedRecipe("nether_quartz_cutting_knife",
                    AEHelper.aeItems.netherQuartzKnife().maybeStack(1).get(),
                    "fPh", "QSQ", " S ",
                    'Q', new ItemStack(Items.QUARTZ),
                    'P', OreDictUnifier.get(plate, NetherQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Nether Quartz Wrench
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_wrench"));
            ModHandler.addShapedRecipe("ether_quartz_wrench", AEHelper.aeItems.netherQuartzWrench().maybeStack(1).get(),
                    "PhP", " P ", " P ",
                    'P', OreDictUnifier.get(plate, NetherQuartz));

            // Certus Quartz Axe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_axe"));
            ModHandler.addShapedRecipe("certus_quartz_axe", AEHelper.aeItems.certusQuartzAxe().maybeStack(1).get(),
                    "PQf", "PS ", "hS ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, CertusQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Certus Quartz Hoe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_hoe"));
            ModHandler.addShapedRecipe("certus_quartz_hoe", AEHelper.aeItems.certusQuartzHoe().maybeStack(1).get(),
                    "PQf", "hS ", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, CertusQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Certus Quartz Pickaxe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_pickaxe"));
            ModHandler.addShapedRecipe("certus_quartz_pickaxe", AEHelper.aeItems.certusQuartzPick().maybeStack(1).get(),
                    "PQQ", "hSf", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, CertusQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Certus Quartz Shovel
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_spade"));
            ModHandler.addShapedRecipe("certus_quartz_spade", AEHelper.aeItems.certusQuartzShovel().maybeStack(1).get(),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, CertusQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Certus Quartz Sword
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_sword"));
            ModHandler.addShapedRecipe("certus_quartz_sword", AEHelper.aeItems.certusQuartzSword().maybeStack(1).get(),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, CertusQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Certus Quartz Cutting Knife
            ModHandler
                    .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_cutting_knife"));
            ModHandler.addShapedRecipe("certus_quartz_cutting_knife",
                    AEHelper.aeItems.certusQuartzKnife().maybeStack(1).get(),
                    "fPh", "QSQ", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, CertusQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Certus Quartz Wrench
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_wrench"));
            ModHandler.addShapedRecipe("certus_quartz_wrench",
                    AEHelper.aeItems.certusQuartzWrench().maybeStack(1).get(),
                    "PhP", " P ", " P ",
                    'P', OreDictUnifier.get(plate, CertusQuartz));
        }
    }
}
