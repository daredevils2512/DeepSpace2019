/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.lib.SpeedRamp;

public class RunToBottom extends RunTo {
  /**
   * Run lift to lowest height
   * @param overrideManualControl Take priority over joystick controls
   */
  public RunToBottom(boolean overrideManualControl) {
    super(overrideManualControl);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double distance = -Robot.m_lift.getLiftHeight();
    double speed = SpeedRamp.speedRamp(0, distance, 10, Robot.m_lift.MAX_DOWN_SPEED);
    Robot.m_lift.setSpeed(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    boolean result = false;

    if(!overrideManualControl && Math.abs(Robot.m_oi.liftControl()) > manualControlOverrideTolerance) {
      result = true; // Allows joystick to override RunToPosition
    } else {
      result = Robot.m_lift.getLimitSwitchBottom();
    }

    return result;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_lift.setSpeed(0.0);
  }
}
