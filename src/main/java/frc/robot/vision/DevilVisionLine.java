package frc.robot.vision;

public class DevilVisionLine extends DevilVision {

    public DevilVisionLine(){
        super();
    }

    protected String getNetworkTableName(){
        return "White Line Tracking";
    }

    protected DevilVisionTargetType getDevilVisionTargetType(){
        return DevilVisionTargetType.LINE;
    }

    //DO YOUR WORK HERE!!
    protected Target processImage(){
        return new DevilVisionTarget(this.getDevilVisionTargetType(), 0.0, 0.0);
    }
}