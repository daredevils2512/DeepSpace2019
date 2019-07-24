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
public class TankDrive extends Drive {

  public TankDrive(Supplier<Double> getLeft, Supplier<Double> getRight) {
   super(getLeft, getRight);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Robot.m_Drivetrain.driveRobotTank(getLeft.get(), getRight.get());
  }
}
