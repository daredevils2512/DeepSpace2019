/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.Supplier;

import frc.robot.constants.Constants;
import frc.robot.subsystems.Lift;

/**
 * An example command.  You can replace me with your own command.
 */
public final class ManualLift extends LiftCommand {
    public ManualLift(Lift lift, Supplier<Double> liftControlDirection) {
        super(lift, liftControlDirection);
    }

    @Override
    protected void execute() {
        double control = liftControlDirection.get();
        double liftSpeed = 0.0;

        if (control > 0.0) {
            if(lift.isAtUpperLimit()) {
                liftSpeed = Constants.Lift.BACKDRIVE;
            } else {
                liftSpeed = control * Constants.Lift.MAX_UP_SPEED;
            }
        } else if (control < 0.0) {
            if(lift.isAtLowerLimit()) {
                liftSpeed = 0.0;
            } else {
                liftSpeed = control * Constants.Lift.MAX_DOWN_SPEED;
            }
        } else {
            if(!lift.isAtLowerLimit()) {
                liftSpeed = Constants.Lift.BACKDRIVE;
            }
        }

        lift.setSpeed(liftSpeed);
    }
}
