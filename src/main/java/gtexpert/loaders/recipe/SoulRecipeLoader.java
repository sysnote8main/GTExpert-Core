package gtexpert.loaders.recipe;


import crazypants.enderio.base.init.ModObject;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.ingredients.nbtmatch.NBTTagType;
import gtexpert.api.recipes.GTERecipeMaps;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class SoulRecipeLoader {

    // This code was created under the advice of miozune.

    private static final Fluid xp = FluidRegistry.getFluid("xpjuice");
    public static void init(){
        recipeAll();
        recipeBlaze();
        recipeCreeper();
        recipeEnderman();
        recipeSkeleton();
        recipeSlime();
        recipeSpider();
        recipePigman();
        recipeVillager();
        recipeWitch();
        recipeWitherSkeleton();
        recipeZombie();
    }

    public static void recipeAll() {
        for (ResourceLocation name : EntityList.getEntityNameList()) {
            if (!EntityList.ENTITY_EGGS.containsKey(name)) {
                continue;
            }
            ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("entityId", name.toString());
            stack.setTagCompound(tag);

            // SoulBinder - Soul Filter
            GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                    .input(GTRecipeItemInput.getOrCreate(stack)
                            .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                    NBTCondition.create(NBTTagType.STRING,"entityId", name.toString())))
                    .input(ModObject.itemBasicItemFilter.getItem())
                    .fluidInputs(new FluidStack(xp, 4320))
                    .output(ModObject.itemSoulVial.getItemNN())
                    .output(ModObject.itemSoulFilterNormal.getItemNN())
                    .duration(10000).EUt(100).buildAndRegister();

            // SoulBinder - Big Soul Filter
            GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                    .input(GTRecipeItemInput.getOrCreate(stack)
                            .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                    NBTCondition.create(NBTTagType.STRING,"entityId", name.toString())))
                    .input(ModObject.itemBigItemFilter.getItem())
                    .fluidInputs(new FluidStack(xp, 4320))
                    .output(ModObject.itemSoulVial.getItemNN())
                    .output(ModObject.itemSoulFilterBig.getItemNN())
                    .duration(10000).EUt(100).buildAndRegister();

            // SoulBinder - Broken Spawner
            ItemStack output = new ItemStack(ModObject.itemBrokenSpawner.getItemNN());
            output.setTagCompound(stack.getTagCompound());
            GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                    .input(GTRecipeItemInput.getOrCreate(stack)
                            .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                    NBTCondition.create(NBTTagType.STRING,"entityId", name.toString())))
                    .inputNBT(ModObject.itemBrokenSpawner.getItemNN(), NBTMatcher.ANY, NBTCondition.ANY)
                    .fluidInputs(new FluidStack(xp, 4320))
                    .output(ModObject.itemSoulVial.getItemNN())
                    .outputs(output)
                    .duration(10000).EUt(100).buildAndRegister();


        }
    }

    public static void recipeBlaze() {
        ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("entityId", "minecraft:blaze");
        stack.setTagCompound(tag);

        //Vial Extractor
        GTERecipeMaps.VIAL_EXTRACTOR_RECIPES.recipeBuilder()
                .input(GTRecipeItemInput.getOrCreate(stack)
                        .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                NBTCondition.create(NBTTagType.STRING, "entityId", "minecraft:blaze")))
                .output(ModObject.itemSoulVial.getItemNN())
                .output(Items.BLAZE_ROD, 10)
                .output(Items.ROTTEN_FLESH, 10)
                .fluidOutputs(new FluidStack(xp, 1000))
                .duration(600).EUt(30).buildAndRegister();
    }

    public static void recipeCreeper() {
        ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("entityId", "minecraft:creeper");
        stack.setTagCompound(tag);

        //Vial Extractor
        GTERecipeMaps.VIAL_EXTRACTOR_RECIPES.recipeBuilder()
                .input(GTRecipeItemInput.getOrCreate(stack)
                        .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                NBTCondition.create(NBTTagType.STRING, "entityId", "minecraft:creeper")))
                .output(ModObject.itemSoulVial.getItemNN())
                .output(Items.SKULL, 10, 4)
                .output(Items.GUNPOWDER, 10)
                .fluidOutputs(new FluidStack(xp, 1000))
                .duration(600).EUt(30).buildAndRegister();
    }

    public static void recipeEnderman() {
            ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("entityId", "minecraft:enderman");
            stack.setTagCompound(tag);

             //Vial Extractor
            GTERecipeMaps.VIAL_EXTRACTOR_RECIPES.recipeBuilder()
                    .input(GTRecipeItemInput.getOrCreate(stack)
                            .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                                     NBTCondition.create(NBTTagType.STRING, "entityId", "minecraft:enderman")))
                    .output(ModObject.itemSoulVial.getItemNN())
                    .output(ModObject.blockEndermanSkull.getItemNN(), 10, 0)
                    .output(Items.ENDER_PEARL, 10)
                    .fluidOutputs(new FluidStack(xp, 1000))
                    .duration(600).EUt(30).buildAndRegister();
    }

    public static void recipeSkeleton() {
        ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("entityId", "minecraft:skeleton");
        stack.setTagCompound(tag);

        //Vial Extractor
        GTERecipeMaps.VIAL_EXTRACTOR_RECIPES.recipeBuilder()
                .input(GTRecipeItemInput.getOrCreate(stack)
                        .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                NBTCondition.create(NBTTagType.STRING, "entityId", "minecraft:skeleton")))
                .output(ModObject.itemSoulVial.getItemNN())
                .output(Items.SKULL, 10, 0)
                .output(Items.BONE, 10)
                .fluidOutputs(new FluidStack(xp, 1000))
                .duration(600).EUt(30).buildAndRegister();
    }

    public static void recipeSlime() {
            List<String> slime = new ArrayList<>();
            slime.add("minecraft:slime");
            slime.add("minecraft:magma_cube");
            for (String Slime : slime) {
                ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
                NBTTagCompound tag = new NBTTagCompound();
                tag.setString("entityId", Slime);
                stack.setTagCompound(tag);

            //Vial Extractor
                GTERecipeMaps.VIAL_EXTRACTOR_RECIPES.recipeBuilder()
                        .input(GTRecipeItemInput.getOrCreate(stack)
                                .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                        NBTCondition.create(NBTTagType.STRING, "entityId", Slime)))
                        .output(ModObject.itemSoulVial.getItemNN())
                        .output(Items.SLIME_BALL, 10)
                        .output(Items.ROTTEN_FLESH, 10)
                        .fluidOutputs(new FluidStack(xp, 2000))
                        .duration(600).EUt(30).buildAndRegister();
            }
    }

    public static void recipeSpider() {
            List<String> spider = new ArrayList<>();
            spider.add("minecraft:spider");
            //spider.add("minecraft:cave_spider");
            for (String Spider : spider) {
                ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
                NBTTagCompound tag = new NBTTagCompound();
                tag.setString("entityId", Spider);
                stack.setTagCompound(tag);

                //Vial Extractor
                GTERecipeMaps.VIAL_EXTRACTOR_RECIPES.recipeBuilder()
                        .input(GTRecipeItemInput.getOrCreate(stack)
                                .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                        NBTCondition.create(NBTTagType.STRING, "entityId", Spider)))
                        .output(ModObject.itemSoulVial.getItemNN())
                        .output(Items.SPIDER_EYE, 10)
                        .output(Items.STRING, 10)
                        .fluidOutputs(new FluidStack(xp, 1000))
                        .duration(600).EUt(30).buildAndRegister();
            }
    }

    public static void recipePigman() {
        ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("entityId", "minecraft:zombie_pigman");
        stack.setTagCompound(tag);

        //Vial Extractor
        GTERecipeMaps.VIAL_EXTRACTOR_RECIPES.recipeBuilder()
                .input(GTRecipeItemInput.getOrCreate(stack)
                        .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                NBTCondition.create(NBTTagType.STRING, "entityId", "minecraft:zombie_pigman")))
                .output(ModObject.itemSoulVial.getItemNN())
                .output(Items.ROTTEN_FLESH, 10)
                .chancedOutput(ingot, Gold, 250, 250)
                .fluidOutputs(new FluidStack(xp, 1000))
                .duration(600).EUt(30).buildAndRegister();
    }

    public static void recipeVillager() {
        ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("entityId", "minecraft:villager");
        stack.setTagCompound(tag);

        // Soul Binder - Enticing Crystal
        GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                .input(GTRecipeItemInput.getOrCreate(stack)
                        .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                NBTCondition.create(NBTTagType.STRING,"entityId", "minecraft:villager")))
                .input(gem, Emerald)
                .fluidInputs(new FluidStack(xp, 800))
                .output(ModObject.itemSoulVial.getItemNN())
                .output(ModObject.itemMaterial.getItemNN(), 1, 17)
                .duration(10000).EUt(100).buildAndRegister();
    }

    public static void recipeWitch() {
            List<String> witch = new ArrayList<>();
            witch.add("minecraft:witch");
            if (Loader.isModLoaded("enderzoo")) {witch.add("enderzoo:witherwitch");}
            if (Loader.isModLoaded("enderiozoo")) {witch.add("enderiozoo:witherwitch");}
            for (String Witch : witch) {
                ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
                NBTTagCompound tag = new NBTTagCompound();
                tag.setString("entityId", Witch);
                stack.setTagCompound(tag);

                // Soul Binder - Sentient Ender
                GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                        .input(GTRecipeItemInput.getOrCreate(stack)
                                .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                        NBTCondition.create(NBTTagType.STRING, "entityId", Witch)))
                        .input(ModObject.itemMaterial.getItemNN(), 1, 43)
                        .fluidInputs(new FluidStack(xp, 800))
                        .output(ModObject.itemSoulVial.getItemNN())
                        .output(ModObject.itemMaterial.getItemNN(), 1, 44)
                        .duration(10000).EUt(100).buildAndRegister();
            }
    }

    public static void recipeWitherSkeleton() {
        ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("entityId", "minecraft:wither_skeleton");
        stack.setTagCompound(tag);

        // Vial Extractor
        GTERecipeMaps.VIAL_EXTRACTOR_RECIPES.recipeBuilder()
                .input(GTRecipeItemInput.getOrCreate(stack)
                        .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                NBTCondition.create(NBTTagType.STRING, "entityId", "minecraft:wither_skeleton")))
                .output(ModObject.itemSoulVial.getItemNN())
                .output(Items.SKULL, 5, 1)
                .output(Items.BONE, 5)
                .chancedOutput(new ItemStack(Items.COAL), 250, 250)
                .fluidOutputs(new FluidStack(xp, 1000))
                .duration(600).EUt(30).buildAndRegister();
    }

    public static void recipeZombie() {
        List<String> zombie = new ArrayList<>();
        zombie.add("minecraft:zombie");
        zombie.add("minecraft:husk");
        zombie.add("minecraft:zombie_villager");
        for (String Zombie : zombie) {
            ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("entityId", Zombie);
            stack.setTagCompound(tag);

            // Soul Binder - Frank'N'Zombie
            GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                    .input(GTRecipeItemInput.getOrCreate(stack)
                            .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                    NBTCondition.create(NBTTagType.STRING, "entityId", Zombie)))
                    .input(ModObject.itemMaterial.getItemNN(), 1, 41)
                    .fluidInputs(new FluidStack(xp, 800))
                    .output(ModObject.itemSoulVial.getItemNN())
                    .output(ModObject.itemMaterial.getItemNN(), 1, 42)
                    .duration(10000).EUt(100).buildAndRegister();

            // Vial Extractor
            GTERecipeMaps.VIAL_EXTRACTOR_RECIPES.recipeBuilder()
                    .input(GTRecipeItemInput.getOrCreate(stack)
                            .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                    NBTCondition.create(NBTTagType.STRING, "entityId", Zombie)))
                    .output(ModObject.itemSoulVial.getItemNN())
                    .output(Items.SKULL, 10, 2)
                    .output(Items.ROTTEN_FLESH, 10)
                    .chancedOutput(ingot, Iron, 100, 0)
                    .fluidOutputs(new FluidStack(xp, 1000))
                    .duration(600).EUt(30).buildAndRegister();
        }
    }
}
