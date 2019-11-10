/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import java.util.function.Supplier;

import frc.robot.Robot;
import frc.robot.constants.Constants;

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
  private boolean isAtUpperLimit() {
    double currentHeight = Robot.m_lift.getLiftHeight();
    return (currentHeight >= Constants.Lift.MAXHEIGHT);
  }



  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double control = liftControlDirection.get();
    double liftSpeed = 0.0;
    if (control > 0.0) {
      liftSpeed = isAtUpperLimit()
          ? Constants.Lift.BACKDRIVE : control * Robot.m_lift.MAX_UP_SPEED;
    } else if (control < 0.0) {
      liftSpeed = Robot.m_lift.getLimitSwitchBottom()
          ? 0.0 : control * Robot.m_lift.MAX_DOWN_SPEED;
    } else {
      liftSpeed = Constants.Lift.BACKDRIVE;
    }
    Robot.m_lift.setSpeed(liftSpeed);

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
