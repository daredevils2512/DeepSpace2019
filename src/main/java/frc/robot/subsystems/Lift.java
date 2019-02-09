/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
 
import edu.wpi.first.wpilibj.command.Subsystem;
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

  public double getLiftHeight() {
    return (liftTalon1.getSelectedSensorPosition() * RobotMap.liftEncoderPulseToFeet);
  }

  public void resetEncoder() {
    liftTalon1.setSelectedSensorPosition(0);
  }  

  public boolean getLimitSwitchBottom() {
    return limitSwitchBottom.get();
  }

  public boolean getLimitSwitchTop() {
    return limitSwitchTop.get();
  }

  public void resetLiftEncoder() {
    // liftEncoder.reset();
  }

  public void setSpeed(double speed) {
    // System.out.print(speed);
    liftTalon1.set(speed);
    // liftTalon2.set(speed);
  }

  // these heights are not acurate, change them when we find out
  public void runToBottom() {
    
    if (this.getLiftHeight() > 0) {
      setSpeed(-1);
    } else {
      setSpeed(0);
    }

  }

  public void runToMid() {

    if (this.getLiftHeight() < 1) {
      setSpeed(1);
    } else if (this.getLiftHeight() > 1) {
      setSpeed(-1);
    } else {
      setSpeed(0);
    }

  }

  public void runToTop() {

    if (this.getLiftHeight() < 2) {
      setSpeed(1);
    } else {
      setSpeed(0);
    }

  }

  public void runToHatchBottom() {

    if (this.getLiftHeight() < 0.5) {
      setSpeed(1);
    } else if (this.getLiftHeight() > 0.5) {
      setSpeed(-1);
    } else {
      setSpeed(0);
    }

  }

  public void runToHatchMid() {

    if (this.getLiftHeight() < 1.5) {
      setSpeed(1);
    } else if (this.getLiftHeight() > 1.5) {
      setSpeed(-1);
    } else {
      setSpeed(0);
    }

  }

  public void runToHatchTop() {

    if (this.getLiftHeight() < 2.5) {
      setSpeed(1);
    } else if (this.getLiftHeight() > 2.5) {
      setSpeed(-1);
    } else {
      setSpeed(0);
    }

  }
  
}