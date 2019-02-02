/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import frc.robot.commands.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX lift1;
  private WPI_TalonSRX lift2;
  public Encoder liftEncoder;
  public static DigitalInput limitSwitch;

  static double pulseToFeet = 1 / 3944;  

  public Lift() {

    lift1 = new WPI_TalonSRX(5);
    lift2 = new WPI_TalonSRX(8);

    liftEncoder = new Encoder(0, 1, false, CounterBase.EncodingType.k4X);

    limitSwitch = new DigitalInput(4);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new MannualLift());
  }

  public double liftEncoderDistance() {
    return liftEncoder.getDistance();
  }

  public int liftEncoderValue() {
    return liftEncoder.get();
  }

  public double getLiftHeight() {
    return lift1.getSelectedSensorVelocity(0) * pulseToFeet;
  }

  public boolean getLimitSwitch() {
    return limitSwitch.get();
  }

  public void setSpeed(double speed) {
    // System.out.printf("Set speed: {0}", speed);
    System.out.print(speed);
    lift1.set(speed);
    lift2.set(speed);
  }
}