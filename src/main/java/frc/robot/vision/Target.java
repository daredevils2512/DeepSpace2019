package frc.robot.vision;

import frc.robot.vision.DevilVision.DevilVisionTargetType;

public interface Target {

    public Double getHeading();
    public Double getDistance();
    public DevilVisionTargetType getDevilVisionTargetType();
    

}