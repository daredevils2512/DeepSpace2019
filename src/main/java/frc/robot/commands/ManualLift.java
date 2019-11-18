/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.constants.Constants;
import frc.robot.subsystems.Lift;

public final class ManualLift extends LiftCommand {
    public ManualLift(Lift lift) {
        super(lift);
    }

    @Override
    protected void execute() {
        double control = lift.getControl();
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
