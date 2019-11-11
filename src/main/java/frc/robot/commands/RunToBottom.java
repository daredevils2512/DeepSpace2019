/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.OI;
import frc.robot.constants.Constants;
import frc.robot.lib.SpeedRamp;
import frc.robot.subsystems.Lift;

public class RunToBottom extends RunTo {
    /**
     * Run lift to lowest height
     * @param overrideManualControl can be manually overriden
     */
    public RunToBottom(boolean allowManualOverride) {
        super(allowManualOverride);
    }

    @Override
    protected void execute() {
        double distance = -Lift.getInstance().getLiftHeight();
        double speed = SpeedRamp.speedRamp(0, distance, 10, Constants.Lift.MAX_DOWN_SPEED);
        Lift.getInstance().setSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        boolean result = false;

        if(allowManualOverride && Math.abs(OI.getInstance().liftControl()) > Constants.Lift.MANUAL_OVERRIDE_TOLERANCE) {
            result = true; // Allows joystick to override RunToPosition
        } else {
            result = Lift.getInstance().getLimitSwitchBottom();
        }

        return result;
    }

    @Override
    protected void end() {
        Lift.getInstance().setSpeed(0.0);
    }
}
