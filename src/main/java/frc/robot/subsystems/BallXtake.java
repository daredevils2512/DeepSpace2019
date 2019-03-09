package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

import frc.robot.RobotMap;

public class BallXtake extends Subsystem {

    private WPI_TalonSRX ballXtake1;
    private WPI_TalonSRX ballXtake2;

    private static DigitalInput ballOccupancy;

    public BallXtake() {
        ballXtake1 = new WPI_TalonSRX(RobotMap.ballXtake1ID);
        ballXtake2 = new WPI_TalonSRX(RobotMap.ballXtake2ID);
        ballOccupancy = new DigitalInput(RobotMap.ballOccupancy);

        ballXtake2.setInverted(true);
    }

    @Override
    public void initDefaultCommand() {

    }

    public void setBallXtakeSpeed(double XtakeSpeed) {
        // Positive speed is out
        ballXtake1.set(XtakeSpeed);
        ballXtake2.set(XtakeSpeed);
    }

    public static boolean getBallOccupancy() {
        return ballOccupancy.get();
    }

    public static DigitalInput getBallOccupancySwitch() {
        return ballOccupancy;
    }

}