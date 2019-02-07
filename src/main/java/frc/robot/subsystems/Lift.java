/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
 
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import frc.robot.commands.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX liftTalon1;
  private WPI_TalonSRX liftTalon2;
  public Encoder liftEncoder;
  public static DigitalInput limitSwitchBottom;
  public static DigitalInput limitSwitchTop; 

  public Lift() {

    liftTalon1 = new WPI_TalonSRX(RobotMap.liftTalon1Id);
    liftTalon2 = new WPI_TalonSRX(RobotMap.liftTalon2Id);

    liftEncoder = new Encoder(RobotMap.liftEncoderChannelA, RobotMap.liftEncoderChannelB, false, CounterBase.EncodingType.k4X);


    limitSwitchBottom = new DigitalInput(RobotMap.limitSwitchBottomPort);
    limitSwitchTop = new DigitalInput(RobotMap.limitSwitchTopPort);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public double getLiftHeight() {
    return liftTalon1.getSelectedSensorVelocity(0) * RobotMap.liftEncoderPulseToFeet;
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
    System.out.print(speed);
    liftTalon1.set(speed);
    liftTalon2.set(speed);
  }
}