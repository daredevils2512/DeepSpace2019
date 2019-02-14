/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import java.util.function.Supplier;

import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class ArcadeDrive extends Drive {

  public ArcadeDrive(Supplier<Double> getLeft, Supplier<Double> getRight) {
   super(getLeft, getRight);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.m_sensors.hatchUltrasonicInches() >= 5 && getLeft.get() > 0) { // im not done with this yet
      Robot.m_Drivetrain.arcadeDrive(getLeft.get() * slowify, getRight.get() * slowify);
    } else {
      Robot.m_Drivetrain.arcadeDrive(0, 0);
    }
  }
}
