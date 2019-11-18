/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.constants.Constants;
import frc.robot.lib.SpeedRamp;
import frc.robot.subsystems.Lift;

public final class RunToBottom extends RunTo {
    /**
     * Run lift to its lowest height (determined by limit switch)
     * @param overrideManualControl override joystick controls
     */
    public RunToBottom(Lift lift, boolean overrideManualControl) {
        super(lift, overrideManualControl);
    }

    @Override
    protected void execute() {
        double distance = -lift.getHeight();
        double speed = SpeedRamp.speedRamp(0, distance, 10, Constants.Lift.MAX_DOWN_SPEED);
        lift.setSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        boolean result = false;
        if(!overrideManualControl && Math.abs(lift.getControl()) >= Constants.Lift.MANUAL_OVERRIDE_TOLERANCE) {
            result = true; // Allow joystick to override RunTo
        } else if(lift.getLimitSwitchBottom()) {
            result = true;
        }
        return result;
    }

    @Override
    protected void end() {
        lift.setSpeed(0.0);
    }
}
