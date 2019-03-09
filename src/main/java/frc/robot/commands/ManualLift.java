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
public class ManualLift extends LiftCommand {

  public ManualLift(Supplier<Double> liftControlDirection) {
    super(liftControlDirection);
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Contains both limit switch and encoder top
  private boolean upperLimit() {
    double currentClicks = Robot.m_lift.getLiftPosition();
    double maxEncClicks = 31500;
    return (currentClicks >= maxEncClicks);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed = 0.0;
    // if((this.liftControlDirection.get() > 0 && !Robot.m_lift.getLimitSwitchBottom())) {
    //   speed = this.liftControlDirection.get();

    // } else {
    //   speed = 0.0;
      
    // }
    double control = liftControlDirection.get();
    if (control > 0.0) {
      if (!upperLimit()) {
        Robot.m_lift.setSpeed(control);
      } else {
        Robot.m_lift.setSpeed(0.0);
      }
    } else if (control < 0.0) {
      if (!Robot.m_lift.getLimitSwitchBottom()) {
        if (control < Robot.m_lift.maxDownSpeed) {
          Robot.m_lift.setSpeed(Robot.m_lift.maxDownSpeed);
        } else {
          Robot.m_lift.setSpeed(control);
        }
      } else {
        Robot.m_lift.resetLiftEncoder();
        Robot.m_lift.setSpeed(0.0);
      }
    } else {
      Robot.m_lift.setSpeed(0.0);
    }

    /*

      switch (joystick)
        case +joystick
          check topLimit
            dont
          check !topLimit
            drive
        case -joystick
          check bottomLimit
            dont
          else
            drive
        default
          dont

    */
  }
}
