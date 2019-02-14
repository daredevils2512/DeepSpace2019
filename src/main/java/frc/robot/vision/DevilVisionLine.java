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
}