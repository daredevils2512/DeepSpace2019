/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class MannualLift extends Command {
  public MannualLift() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    Robot.m_lift.setSpeed(Robot.m_oi.liftControl());

    if (Robot.m_oi.liftControl() == 0 && Robot.m_lift.getLiftHeight() >= 0.0) { // stuff so it dont fall

      Robot.m_lift.setSpeed(0.08);

    } else if ((Robot.m_lift.getLiftHeight() >= 7.2) && (Robot.m_oi.liftControl() < 0)) { // so it can only drive down when at the top

      Robot.m_lift.setSpeed(Robot.m_oi.liftControl());

    } else if ((Robot.m_lift.getLiftHeight() >= 7.2)) { // sets max height

      Robot.m_lift.setSpeed(0.0);

    }  else if (Robot.m_lift.getLimitSwitch()) { 

      if (Robot.m_oi.liftControl() > 0) {
        Robot.m_lift.setSpeed(Robot.m_oi.liftControl());
      } else {
        Robot.m_lift.setSpeed(0.0);
      }

    } else if (Robot.m_oi.liftControl() < -0.8) { // slows it down

      Robot.m_lift.setSpeed(Robot.m_oi.liftControl() * 0.8);

    } else if (Robot.m_oi.liftControl() != 0) { // so it actually drives

      Robot.m_lift.setSpeed(Robot.m_oi.liftControl());

    }


  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
