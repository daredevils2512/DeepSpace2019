/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoRunIntake extends Command {

    private double m_infinitySpeed;
    private double m_inSpeed;

    private boolean m_ovveride;

  public CargoRunIntake(double infinitySpeed, double inSpeed, boolean ovveride) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_cargoIntake);
    this.m_infinitySpeed = infinitySpeed;
    this.m_inSpeed = inSpeed;
    this.m_ovveride = ovveride;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      Robot.m_cargoIntake.runIntake(this.m_infinitySpeed, this.m_inSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (!this.m_ovveride && this.m_inSpeed < 0) ? Robot.m_ballXtake.getBallOccupancy() : false;
    // return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
      Robot.m_cargoIntake.runIntake(0.0, 0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
      this.end();
  }
}
