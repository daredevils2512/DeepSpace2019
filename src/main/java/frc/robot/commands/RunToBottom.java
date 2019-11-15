/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.Supplier;

import frc.robot.constants.Constants;
import frc.robot.lib.SpeedRamp;
import frc.robot.subsystems.Lift;

public final class RunToBottom extends RunTo {
    /**
     * Run lift to lowest height
     * @param allowManualOverride can be manually overriden
     */
    public RunToBottom(Lift lift, Supplier<Double> getLiftControl, boolean allowManualOverride) {
        super(lift, getLiftControl, allowManualOverride);
    }

    @Override
    protected void execute() {
        double distance = -lift.getLiftHeight();
        double speed = SpeedRamp.speedRamp(0, distance, 10, Constants.Lift.MAX_DOWN_SPEED);
        lift.setSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        boolean result = false;

        if(allowManualOverride && Math.abs(getLiftControl.get()) > Constants.Lift.MANUAL_OVERRIDE_TOLERANCE) {
            result = true; // Allows joystick to override RunToPosition
        } else {
            result = lift.getLimitSwitchBottom();
        }

        return result;
    }

    @Override
    protected void end() {
        lift.setSpeed(0.0);
    }
}
