/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
 
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
// import com.sun.tools.javac.comp.Todo;

import edu.wpi.first.wpilibj.*;
import frc.robot.commands.ManualLift;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX liftTalon1;
  private WPI_TalonSRX liftTalon2;
  public static DigitalInput limitSwitchBottom;
  public static DigitalInput limitSwitchTop; 

  private static final int magEncPulsesPerRev = 4096;
  private static final double inchesPerRev = (4 + (3/4)) + (5 + (3/8)); // This will change
  // public static double liftEncoderPulseToInches = inchesPerRev / magEncPulsesPerRev;
  public static double liftEncoderPulseToInches = 0.00244125;

  public double maxDownSpeed = -0.1;

  public Lift() {  

    liftTalon1 = new WPI_TalonSRX(RobotMap.liftTalon1Id);
    liftTalon2 = new WPI_TalonSRX(RobotMap.liftTalon2Id);

    liftTalon1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    liftTalon2.set(ControlMode.Follower, liftTalon1.getDeviceID());

    limitSwitchBottom = new DigitalInput(RobotMap.limitSwitchBottomPort);
    limitSwitchTop = new DigitalInput(RobotMap.limitSwitchTopPort);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ManualLift(Robot.m_oi::liftControl));
  }

  public double getLiftPosition() {
    return liftTalon1.getSelectedSensorPosition();
  }

  public double getLiftHeight() {
    return (liftTalon1.getSelectedSensorPosition() * liftEncoderPulseToInches); // this might seem like a random number but it is needed (I will find out math)
  } 

  public boolean getLimitSwitchBottom() {
    boolean pressed = limitSwitchBottom.get();
    if (pressed) {
      this.resetLiftEncoder();
    }
    return pressed;
  }

  public boolean getLimitSwitchTop() {
    return limitSwitchTop.get();
  }

  public void resetLiftEncoder() {
    liftTalon1.setSelectedSensorPosition(0);
  }

  public void setSpeed(double speed) {
    liftTalon1.set(speed);
  }

  public boolean runTo(double runTo) {

    double liftSpeed = 0;
    double diffrence = runTo - this.getLiftHeight();
    double distance = Math.abs(diffrence);
    double sign = Math.signum(diffrence);

    System.out.println("Lift speed:: " + liftSpeed);
    System.out.println("difference:: " + diffrence);
    System.out.println("distance:: " + distance);
    System.out.println("sign:: " + sign);
    if (distance > 75) {
      liftSpeed = sign * 1;

    } else if (distance > 0) {
      liftSpeed = sign * 0.5;

    // } else if (distance > 25) {
    //   liftSpeed = sign * 0.25;

    // } else if (distance > 0) {
    //   liftSpeed = sign * 0.15;
      
    } else {
      liftSpeed = 0;
    }
    setSpeed(liftSpeed);
    System.out.println("Lift height: " + this.getLiftHeight() + " Run to: " + runTo);
    return (this.getLiftHeight() >= (runTo - (runTo * 0.15)) && this.getLiftHeight() <= (runTo + (runTo * 0.15)));
  }


  
}
