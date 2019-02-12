package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class BallXtake extends Subsystem {

    private WPI_TalonSRX ballXtake1;
    private WPI_TalonSRX ballXtake2;

    public BallXtake() {
        ballXtake1 = new WPI_TalonSRX(RobotMap.ballXtake1ID);
        ballXtake2 = new WPI_TalonSRX(RobotMap.ballXtake2ID);
    }

    @Override
    public void initDefaultCommand() {

    }

    public void setBallXtakeSpeed(double XtakeSpeed) {
        ballXtake1.set(XtakeSpeed);
        ballXtake2.set(XtakeSpeed);
    }

}