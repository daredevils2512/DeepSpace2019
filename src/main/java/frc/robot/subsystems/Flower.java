package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Flower extends Subsystem {

    private DoubleSolenoid flowerSolenoid;
    private DoubleSolenoid flowerSlide;

    private DoubleSolenoid.Value position = Value.kForward;

    public Flower() {

        this.flowerSolenoid = new DoubleSolenoid(RobotMap.flowerSolenoidForwardChannel, RobotMap.flowerSolenoidReverseChannel);
        this.flowerSlide = new DoubleSolenoid(RobotMap.flowerSlideForwardChannel, RobotMap.flowerSlideReverseChannel);

    }



    @Override
    public void initDefaultCommand() {

    }

    public void toggleFlower() {
        if (position == Value.kForward) {
            position = Value.kReverse;
        } else {
            position = Value.kForward;
        }
        flowerSolenoid.set(position);
    }

    public void toggleFlowerSlide() {
        if (position == Value.kForward) {
            position = Value.kReverse;
        } else {
            position = Value.kForward;
        }
        flowerSlide.set(position);
    }

}