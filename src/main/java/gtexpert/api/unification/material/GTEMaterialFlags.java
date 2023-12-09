package gtexpert.api.unification.material;

import static gregtech.api.unification.material.info.MaterialFlags.*;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.*;
import gregtech.api.unification.ore.OrePrefix;

public class GTEMaterialFlags {

    public static void init() {
        // Iron
        Materials.Iron.addFlags(GENERATE_DOUBLE_PLATE);

        // Ender Peral
        Materials.EnderPearl.setProperty(PropertyKey.FLUID, new FluidProperty());

        // Ender Eye
        Materials.EnderEye.setProperty(PropertyKey.FLUID, new FluidProperty());
        Materials.EnderEye.setFormula("(BeK4N5)(CS)", true);

        // Nether Quartz
        Materials.NetherQuartz.setProperty(PropertyKey.FLUID, new FluidProperty());
        Materials.NetherQuartz.addFlags(GENERATE_LENS, GENERATE_ROD);
        OrePrefix.block.modifyMaterialAmount(Materials.NetherQuartz, 4);

        // Certus Quartz
        Materials.CertusQuartz.setProperty(PropertyKey.FLUID, new FluidProperty());
        Materials.CertusQuartz.addFlags(GENERATE_LENS, GENERATE_ROD);
        OrePrefix.block.modifyMaterialAmount(Materials.CertusQuartz, 4);

        // Quartzite
        Materials.Quartzite.addFlags(GENERATE_ROD);
        OrePrefix.block.modifyMaterialAmount(Materials.Quartzite, 4);

        // Red Alloy
        Materials.RedAlloy.addFlags(MORTAR_GRINDABLE);

        // Glowstone
        Materials.Glowstone.setFormula("Au(Si(FeS2)5(CrAl2O3)Hg3)", true);

        // Darmstadtium
        Materials.Darmstadtium.addFlags(GENERATE_GEAR, GENERATE_FRAME);

        // Osmium
        Materials.Osmium.setProperty(PropertyKey.ORE, new OreProperty());
        Materials.Osmium.getProperty(PropertyKey.ORE).setOreByProducts(Materials.Iridium);

        // Iridium
        Materials.Iridium.setProperty(PropertyKey.ORE, new OreProperty());
        Materials.Iridium.getProperty(PropertyKey.ORE).setOreByProducts(Materials.Platinum, Materials.Osmium);
    }
}
