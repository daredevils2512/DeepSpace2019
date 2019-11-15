/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
 
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
// import com.sun.tools.javac.comp.Todo;

import edu.wpi.first.wpilibj.*;
import frc.robot.commands.ManualLift;
import frc.robot.constants.Constants;
import frc.robot.lib.SpeedRamp;

public final class Lift extends Subsystem {
    private WPI_TalonSRX liftTalon1;
    private WPI_TalonSRX liftTalon2;
    public static DigitalInput limitSwitchBottom;
    public static DigitalInput limitSwitchTop;

    private static final int magEncPulsesPerRev = 4096;
    private static final double inchesPerRev = (4 + (3/4)) + (5 + (3/8)); // This will change
    // public static double liftEncoderPulseToInches = inchesPerRev / magEncPulsesPerRev;
    public static double liftEncoderPulseToInches = 0.00244125;

    public double maxDownSpeed = -0.55;
    private double tolerance = 1;
    
    private Supplier<Double> liftControlSupplier;

    public Lift(Supplier<Double> liftControlSupplier) {
        this.liftControlSupplier = liftControlSupplier;

        liftTalon1 = new WPI_TalonSRX(RobotMap.liftTalon1Id);
        liftTalon2 = new WPI_TalonSRX(RobotMap.liftTalon2Id);

        liftTalon1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        liftTalon2.set(ControlMode.Follower, liftTalon1.getDeviceID());

        limitSwitchBottom = new DigitalInput(RobotMap.limitSwitchBottomPort);
        limitSwitchTop = new DigitalInput(RobotMap.limitSwitchTopPort);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ManualLift(this, liftControlSupplier));
    }

    public double getLiftEncoderPosition() {
        return liftTalon1.getSelectedSensorPosition();
    }

    public double getLiftHeight() {
        return (liftTalon1.getSelectedSensorPosition() * liftEncoderPulseToInches); // this might seem like a random number but it is needed (I will find out math)
    } 

    public static DigitalInput getLimitSwitch() {
        return limitSwitchBottom;
    }

    public boolean getLimitSwitchBottom() {
        if (limitSwitchBottom.get()) {
            this.resetLiftEncoder();
        }
        return limitSwitchBottom.get();
    }

    public boolean getLimitSwitchTop() {
        return limitSwitchTop.get();
    }

    public boolean isAtLowerLimit() {
        return getLimitSwitchBottom();
    }

    public boolean isAtUpperLimit() {
        double currentHeight = getLiftHeight();
        return currentHeight >= Constants.Lift.MAX_HEIGHT || getLimitSwitchTop();
    }

    public void resetLiftEncoder() {
        liftTalon1.setSelectedSensorPosition(0);
    }

    public void setSpeed(double speed) {
        liftTalon1.set(speed);
    }

    private double m_runTo;
    public void runTo(double runTo) {
        m_runTo = runTo;

        double defaultLiftSpeed = 1.0;
        double difference = runTo - this.getLiftHeight();
        double rampStart = 10;

        // if the distance from the runTo to the current height
        // is more than the ramping start it goes at full
        // if isn't it will ramp down
        // it is the same for going down just opposite
        setSpeed(SpeedRamp.speedRamp(tolerance, difference, rampStart, defaultLiftSpeed));
    }

    public boolean isFinishedRunTo() {
        // needs to change based off the height
        // higher the height, lower the percent

        // 10 in window centered on the desired height
        return (this.getLiftHeight() >= (m_runTo - (tolerance)) 
        && this.getLiftHeight() <= (m_runTo + (tolerance)));
    }

}
