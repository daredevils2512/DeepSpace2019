/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
 
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
// import com.sun.tools.javac.comp.Todo;

import edu.wpi.first.wpilibj.*;
import frc.robot.commands.ManualLift;
import frc.robot.constants.Constants;

public final class Lift extends Subsystem {
    private WPI_TalonSRX master;
    private WPI_TalonSRX slave;
    public static DigitalInput limitSwitchBottom;
    public static DigitalInput limitSwitchTop;

    private static final int magEncPulsesPerRev = 4096;
    private static final double inchesPerRev = (4 + (3/4)) + (5 + (3/8)); // This will change
    // public static double liftEncoderPulseToInches = inchesPerRev / magEncPulsesPerRev;
    public static double liftEncoderPulseToInches = 0.00244125;
    
    private final Supplier<Double> liftControlSupplier;

    public Lift(Supplier<Double> liftControlSupplier) {
        this.liftControlSupplier = liftControlSupplier;

        master = new WPI_TalonSRX(RobotMap.liftTalon1Id);
        slave = new WPI_TalonSRX(RobotMap.liftTalon2Id);

        master.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        slave.set(ControlMode.Follower, master.getDeviceID());

        limitSwitchBottom = new DigitalInput(RobotMap.limitSwitchBottomPort);
        limitSwitchTop = new DigitalInput(RobotMap.limitSwitchTopPort);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ManualLift(this));
    }

    @Override
    public void periodic() {
        if(limitSwitchBottom.get() && getEncoderValue() != 0.0) {
            resetEncoder();
        }
    }

    public double getControl() {
        return liftControlSupplier.get();
    }

    public int getEncoderValue() {
        return master.getSelectedSensorPosition();
    }

    public double getHeight() {
        return (master.getSelectedSensorPosition() * liftEncoderPulseToInches); // this might seem like a random number but it is needed (I will find out math)
    } 

    public static DigitalInput getLimitSwitch() {
        return limitSwitchBottom;
    }

    public boolean getLimitSwitchBottom() {
        return limitSwitchBottom.get();
    }

    public boolean getLimitSwitchTop() {
        return limitSwitchTop.get();
    }

    public boolean isAtLowerLimit() {
        return getLimitSwitchBottom();
    }

    public boolean isAtUpperLimit() {
        return getHeight() >= Constants.Lift.MAX_HEIGHT;
    }

    public void resetEncoder() {
        master.setSelectedSensorPosition(0);
    }

    public void setSpeed(double speed) {
        master.set(speed);
    }

    public void updateDashboard() {
        SmartDashboard.putBoolean("Lift Limit Switch Bottom", getLimitSwitchBottom());
        SmartDashboard.putNumber("Lift Height", getHeight());
        SmartDashboard.putNumber("Lift Encoder Value", getEncoderValue());

        SmartDashboard.putNumber("L1 Temp", master.getTemperature());
        SmartDashboard.putNumber("L2 Temp", master.getTemperature());
    }
}
