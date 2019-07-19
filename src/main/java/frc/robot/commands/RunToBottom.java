/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunToBottom extends Command {
  private boolean overrideManualControl;
  private final double manualControlOverrideTolerance = 0.1;

  /**
   * Run lift to lowest height
   * @param overrideManualControl Take priority over joystick controls
   */
  public RunToBottom(boolean overrideManualControl) {
    requires(Robot.m_lift);
    this.overrideManualControl = overrideManualControl;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_lift.setSpeed(-Robot.m_lift.MAX_DOWN_SPEED);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //Allows joystick to override RunToPosition
    if(!overrideManualControl) {
      if(Math.abs(Robot.m_oi.liftControl()) > manualControlOverrideTolerance) {
        return true;
      }
    }

    return Robot.m_lift.getLimitSwitchBottom();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_lift.setSpeed(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
