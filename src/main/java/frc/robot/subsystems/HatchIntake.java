/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;



/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class HatchIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //hatch not done needs ports in RobotMap
  private DoubleSolenoid wheelGrab;
  static WPI_TalonSRX roller = new WPI_TalonSRX(RobotMap.hatchIntakeMotor);

  public HatchIntake() {
  }

  public void flipFlap(DoubleSolenoid.Value value) {
    wheelGrab.set(value);
  }

  public static void runIntake(double speed) {
    roller.set(speed);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}