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

  public double maxDownSpeed = -0.4;

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

  public static DigitalInput getLimitSwitch() {
    return limitSwitchBottom;
  }

  public boolean getLimitSwitchBottom() {
    return limitSwitchBottom.get();
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

  /** 
  *       @param tolerance  the distance room for error
  *
  *       @param distDiffrence the distance between the robot and the point you are going to
  *
  *       @param rampStartDist the distance from where it will start to deccelerate
  *
  *       @param defaultSpeed the speed it will go while not ramping
  *
  *       @return a double from -1 to 1
  *
  */

  public static double speedRamp(double tolerance, double distDiffrence, double rampStartDist, double defaultSpeed) {
    if (distDiffrence > tolerance) {
      return Math.min(defaultSpeed, (distDiffrence / rampStartDist));
    } else if (distDiffrence < -tolerance) {
      return Math.max(-defaultSpeed, (distDiffrence / rampStartDist));
    } else {
      return 0;
    }
  }

  private double m_runTo;
  public void runTo(double runTo) {
    m_runTo = runTo;

    double liftSpeed;
    double diffrence = runTo - this.getLiftHeight();
    double tolerance = 4;
    double rampStart = 12;
    // double distance = Math.abs(diffrence);
    // double sign = Math.signum(diffrence);

    // if the distance from the runTo to the current height
    // is more than the ramping start it goes at full
    // if isn't it will ramp down
    // it is the same for going down just opposite
    setSpeed(speedRamp(4, diffrence, 12, 1));
    // if (diffrence > tolerance) {
    //   liftSpeed = Math.min(1.0, (diffrence / rampStart));
    // } else if (diffrence < -tolerance) {
    //   liftSpeed = Math.max(-1.0, (diffrence / rampStart));
    // } else {
    //   liftSpeed = 0;
    // }
    
  }


  public boolean isFinishedRunTo() {
    // System.out.println("Lift height: " + this.getLiftHeight() + " Run to: " + m_runTo);
    // 0.15 is percent allowed error of distance
    // needs to change based off the height
    // higher the height, lower the percent

    // 10 in window centered on the desired height
    return (this.getLiftHeight() >= (m_runTo - (5)) 
    && this.getLiftHeight() <= (m_runTo + (5)));

  }


  
}
