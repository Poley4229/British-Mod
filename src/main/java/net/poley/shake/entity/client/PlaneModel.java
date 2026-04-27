package net.poley.shake.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.poley.shake.entity.custom.PlaneEntity;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class PlaneModel<T extends PlaneEntity> extends SinglePartEntityModel<T> {
	private final ModelPart body;
	private final ModelPart front;
	private final ModelPart wings;
	private final ModelPart wingL;
	private final ModelPart wingR;
	private final ModelPart wheels;
	private final ModelPart frontW;
	private final ModelPart LbackW;
	private final ModelPart RbackW;
	private final ModelPart back;
	private final ModelPart tWing;
	private final ModelPart lWing;
	private final ModelPart rWing;
	private final float numbF;
	private final float numbL;
	private final float numbR;
	public PlaneModel(ModelPart root) {
		this.body = root.getChild("body");
		this.front = root.getChild("body").getChild("front");
		this.wings = root.getChild("body").getChild("wings");
		this.wingL = root.getChild("body").getChild("wings").getChild("wingL");
		this.wingR = root.getChild("body").getChild("wings").getChild("wingR");
		this.wheels = root.getChild("body").getChild("wheels");
		this.frontW = root.getChild("body").getChild("wheels").getChild("frontW");
		this.LbackW = root.getChild("body").getChild("wheels").getChild("LbackW");
		this.RbackW = root.getChild("body").getChild("wheels").getChild("RbackW");
		this.back = root.getChild("body").getChild("back");
		this.tWing = root.getChild("body").getChild("back").getChild("tWing");
		this.lWing = root.getChild("body").getChild("back").getChild("lWing");
		this.rWing = root.getChild("body").getChild("back").getChild("rWing");
        this.numbF = this.frontW.pivotY;
        this.numbL = this.LbackW.pivotY;
        this.numbR = this.RbackW.pivotY;
    }
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(36, 75).cuboid(-3.0F, -6.0F, -16.0F, 6.0F, 6.0F, 32.0F, new Dilation(0.0F))
		.uv(39, 67).cuboid(-2.0F, -7.0F, -16.0F, 4.0F, 1.0F, 32.0F, new Dilation(0.0F))
		.uv(37, 75).cuboid(-2.0F, 0.0F, -16.0F, 4.0F, 1.0F, 32.0F, new Dilation(0.0F))
		.uv(60, 8).cuboid(-4.0F, -5.0F, -16.0F, 1.0F, 4.0F, 32.0F, new Dilation(0.0F))
		.uv(59, 4).cuboid(3.0F, -5.0F, -16.0F, 1.0F, 4.0F, 32.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 23.0F, 0.0F));

		ModelPartData front = body.addChild("front", ModelPartBuilder.create().uv(6, 45).cuboid(-3.0F, -6.0F, -18.0F, 6.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(43, 39).cuboid(-3.0F, -5.0F, -20.0F, 6.0F, 5.0F, 2.0F, new Dilation(0.0F))
		.uv(31, 30).cuboid(-2.0F, -4.0F, -22.0F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(102, 21).cuboid(-2.0F, -3.0F, -24.0F, 4.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(35, 47).cuboid(-1.0F, -2.0F, -25.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData wings = body.addChild("wings", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData wingL = wings.addChild("wingL", ModelPartBuilder.create().uv(60, 93).cuboid(3.0F, -2.0F, -4.0F, 2.0F, 2.0F, 14.0F, new Dilation(0.0F))
		.uv(63, 94).cuboid(5.0F, -2.0F, -3.0F, 1.0F, 2.0F, 13.0F, new Dilation(0.0F))
		.uv(65, 95).cuboid(6.0F, -2.0F, -2.0F, 1.0F, 2.0F, 12.0F, new Dilation(0.0F))
		.uv(67, 96).cuboid(7.0F, -2.0F, -1.0F, 2.0F, 2.0F, 11.0F, new Dilation(0.0F))
		.uv(70, 97).cuboid(9.0F, -2.0F, 0.0F, 2.0F, 2.0F, 10.0F, new Dilation(0.0F))
		.uv(74, 99).cuboid(11.0F, -2.0F, 2.0F, 2.0F, 2.0F, 8.0F, new Dilation(0.0F))
		.uv(42, 26).cuboid(13.0F, -2.0F, 4.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F))
		.uv(46, 28).cuboid(15.0F, -2.0F, 6.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData body_r1 = wingL.addChild("body_r1", ModelPartBuilder.create().uv(52, 30).cuboid(2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(49, 29).cuboid(0.0F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(17.0F, -2.0F, 8.0F, 0.0F, 0.0F, -1.5708F));

		ModelPartData wingR = wings.addChild("wingR", ModelPartBuilder.create(), ModelTransform.pivot(-26.0F, 0.0F, 0.0F));

		ModelPartData body_r2 = wingR.addChild("body_r2", ModelPartBuilder.create().uv(52, 30).cuboid(2.0F, 0.0F, 1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(49, 29).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(9.0F, -2.0F, 7.0F, 0.0F, 0.0F, -1.5708F));

		ModelPartData body_r3 = wingR.addChild("body_r3", ModelPartBuilder.create().uv(46, 28).cuboid(15.0F, -4.0F, 6.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F))
		.uv(42, 26).cuboid(13.0F, -4.0F, 4.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F))
		.uv(74, 99).cuboid(11.0F, -4.0F, 2.0F, 2.0F, 2.0F, 8.0F, new Dilation(0.0F))
		.uv(70, 97).cuboid(9.0F, -4.0F, 0.0F, 2.0F, 2.0F, 10.0F, new Dilation(0.0F))
		.uv(67, 96).cuboid(7.0F, -4.0F, -1.0F, 2.0F, 2.0F, 11.0F, new Dilation(0.0F))
		.uv(65, 95).cuboid(6.0F, -4.0F, -2.0F, 1.0F, 2.0F, 12.0F, new Dilation(0.0F))
		.uv(63, 94).cuboid(5.0F, -4.0F, -3.0F, 1.0F, 2.0F, 13.0F, new Dilation(0.0F))
		.uv(60, 93).cuboid(3.0F, -4.0F, -4.0F, 2.0F, 2.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(26.0F, -4.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		ModelPartData wheels = body.addChild("wheels", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 17.0F, 0.0F));

		ModelPartData frontW = wheels.addChild("frontW", ModelPartBuilder.create().uv(104, 122).cuboid(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(110, 122).cuboid(-1.0F, 2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(119, 125).cuboid(-1.0F, 3.0F, 1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(122, 119).cuboid(-1.0F, 3.0F, -2.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -16.0F, -8.0F));

		ModelPartData LbackW = wheels.addChild("LbackW", ModelPartBuilder.create().uv(104, 122).cuboid(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(110, 122).cuboid(-1.0F, 2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(119, 125).cuboid(-1.0F, 3.0F, 1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(122, 119).cuboid(-1.0F, 3.0F, -2.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -16.0F, 10.0F));

		ModelPartData RbackW = wheels.addChild("RbackW", ModelPartBuilder.create().uv(104, 122).cuboid(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(110, 122).cuboid(-1.0F, 2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(119, 125).cuboid(-1.0F, 3.0F, 1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(122, 119).cuboid(-1.0F, 3.0F, -2.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -16.0F, 10.0F));

		ModelPartData back = body.addChild("back", ModelPartBuilder.create().uv(52, 96).cuboid(-3.0F, -7.0F, 15.0F, 6.0F, 7.0F, 1.0F, new Dilation(0.0F))
		.uv(59, 77).cuboid(-3.0F, -7.0F, 16.0F, 6.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(106, 102).cuboid(-3.0F, -7.0F, 17.0F, 6.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(36, 110).cuboid(-3.0F, -7.0F, 18.0F, 6.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 78).cuboid(-3.0F, -7.0F, 19.0F, 6.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(111, 79).cuboid(-2.0F, -7.0F, 20.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(100, 118).cuboid(-1.0F, -7.0F, 21.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

		ModelPartData tWing = back.addChild("tWing", ModelPartBuilder.create().uv(60, 118).cuboid(-1.0F, -8.0F, 8.0F, 2.0F, 1.0F, 7.0F, new Dilation(0.0F))
		.uv(25, 45).cuboid(-1.0F, -9.0F, 9.0F, 2.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(26, 46).cuboid(-1.0F, -10.0F, 10.0F, 2.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(27, 47).cuboid(-1.0F, -12.0F, 11.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F))
		.uv(28, 48).cuboid(-1.0F, -14.0F, 12.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(29, 49).cuboid(-1.0F, -15.0F, 13.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 6.0F));

		ModelPartData lWing = back.addChild("lWing", ModelPartBuilder.create().uv(61, 119).cuboid(-1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(26, 46).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(27, 47).cuboid(-1.0F, -3.0F, -2.0F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(28, 48).cuboid(-1.0F, -5.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(29, 49).cuboid(-1.0F, -7.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(30, 50).cuboid(-1.0F, -8.0F, 1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -5.0F, 17.0F, 0.0F, 0.0F, 1.5708F));

		ModelPartData rWing = back.addChild("rWing", ModelPartBuilder.create().uv(59, 118).cuboid(-1.0F, -4.0F, -4.0F, 2.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(16, 68).cuboid(-1.0F, -5.0F, -3.0F, 2.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(17, 69).cuboid(-1.0F, -6.0F, -2.0F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(18, 70).cuboid(-1.0F, -8.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(19, 71).cuboid(-1.0F, -10.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(20, 72).cuboid(-1.0F, -11.0F, 1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.0F, 17.0F, 0.0F, 0.0F, -1.5708F));
		return TexturedModelData.of(modelData, 128, 128);
	}



	@Override
	public void setAngles(PlaneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//this.getPart().traverse().forEach(ModelPart::resetTransform);
		
		this.frontW.pivotY = numbF;
		this.LbackW.pivotY = numbL;
		this.RbackW.pivotY = numbR;

		if (entity.isWheelsUp()) {
			this.frontW.pivotY -= 7;
			this.LbackW.pivotY -= 7;
			this.RbackW.pivotY -= 7;
		} else {
			this.frontW.pivotY = numbF;
			this.LbackW.pivotY = numbL;
			this.RbackW.pivotY = numbR;
		}

	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return body;
	}
}