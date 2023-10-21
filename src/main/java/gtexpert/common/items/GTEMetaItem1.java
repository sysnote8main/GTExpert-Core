package gtexpert.common.items;

import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.items.behaviors.TooltipBehavior;

import gtexpert.common.GTEConfigHolder;

import net.minecraft.client.resources.I18n;

import static gregtech.api.GTValues.M;
import static gtexpert.common.items.GTEMetaItems.*;

public class GTEMetaItem1 extends StandardMetaItem {

    public GTEMetaItem1() {
        super();
    }

    @Override
    public void registerSubItems() {
        GTE_ME_FAKE_COMPONENT = addItem(0, "gte_me_fake_component");
        MATRIX_CORE = addItem(1, "matrix_core");

        // Primitive modules: ID 2-6
        if (GTEConfigHolder.ceuOverride.enablePrimitiveCovers) {
            PRIMITIVE_MOTOR = addItem(2, "primitive.motor");
            PRIMITIVE_PUMP = addItem(3, "primitive.pump").addComponents(new TooltipBehavior(lines -> {
                lines.add(I18n.format("metaitem.electric.pump.tooltip"));
                lines.add(I18n.format("gregtech.universal.tooltip.fluid_transfer_rate", 1280 / 4));
            }));
            PRIMITIVE_CONVEYOR = addItem(4, "primitive.conveyor").addComponents(new TooltipBehavior(lines -> {
                lines.add(I18n.format("metaitem.conveyor.module.tooltip"));
                lines.add(I18n.format("gregtech.universal.tooltip.item_transfer_rate", 2));
            }));
            PRIMITIVE_PISTON = addItem(5, "primitive.piston");
            PRIMITIVE_ROBOT_ARM = addItem(6, "primitive.robot.arm").addComponents(new TooltipBehavior(lines -> {
                lines.add(I18n.format("metaitem.robot.arm.tooltip"));
                lines.add(I18n.format("gregtech.universal.tooltip.item_transfer_rate", 2));
            }));
        }

        // Shapes: ID 101-110
        if (GTEConfigHolder.ae2Integration.moveSteelShape) {
            GTE_SHAPE_MOLDS[0] = SHAPE_MOLD_PRINTED_SILICON = addItem(101, "shape.mold.printed_silicon")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
            GTE_SHAPE_MOLDS[1] = SHAPE_MOLD_LOGIC_PROCESSOR = addItem(102, "shape.mold.logic_processor")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
            GTE_SHAPE_MOLDS[2] = SHAPE_MOLD_CALCULATION_PROCESSOR = addItem(103, "shape.mold.calculation_processor")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
            GTE_SHAPE_MOLDS[3] = SHAPE_MOLD_ENGINEERING_PROCESSOR = addItem(104, "shape.mold.engineering_processor")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));

            GTE_SHAPE_EXTRUDERS[0] = SHAPE_EXTRUDER_PRINTED_SILICON = addItem(105, "shape.extruder.printed_silicon")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
            GTE_SHAPE_EXTRUDERS[1] = SHAPE_EXTRUDER_LOGIC_PROCESSOR = addItem(106, "shape.extruder.logic_processor")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
            GTE_SHAPE_EXTRUDERS[2] = SHAPE_EXTRUDER_CALCULATION_PROCESSOR = addItem(107,
                    "shape.extruder.calculation_processor")
                            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
            GTE_SHAPE_EXTRUDERS[3] = SHAPE_EXTRUDER_ENGINEERING_PROCESSOR = addItem(108,
                    "shape.extruder.engineering_processor")
                            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        }
    }
}
