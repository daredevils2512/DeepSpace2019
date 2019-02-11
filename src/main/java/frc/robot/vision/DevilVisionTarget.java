package frc.robot.vision;

import frc.robot.vision.DevilVision.DevilVisionTargetType;

public class DevilVisionTarget implements Target {
    private Double distance;
    private Double heading;
    private DevilVisionTargetType devilVisionTargetType;

    public DevilVisionTarget(DevilVisionTargetType type,  Double distance, Double heading){
        this.devilVisionTargetType = type;
        this.distance = distance;
        this.heading = heading;
    }
   
    public  Double getHeading(){
       return heading;
    }

    public Double getDistance(){
       return distance;
    }

    public DevilVisionTargetType getDevilVisionTargetType(){
        return devilVisionTargetType;
    }
}