package frc.robot.commands;

import frc.robot.constants.Constants;
import frc.robot.lib.SpeedRamp;
import frc.robot.subsystems.Lift;

public final class RunToHeight extends RunTo {
    protected Constants.Lift.Height position;

    /**
     * Run lift to height (determined by encoder value)
     * @param position desired height
     * @param overrideManualControl override joystick controls
     */
    public RunToHeight(Lift lift, Constants.Lift.Height position, boolean overrideManualControl) {
        super(lift, overrideManualControl);
        this.position = position;
    }

    @Override
    protected void execute() {
        double distance = position.getHeight() - lift.getHeight();
        double defaultSpeed = distance < 0 ? Constants.Lift.MAX_DOWN_SPEED : Constants.Lift.MAX_UP_SPEED;
        double speed = SpeedRamp.speedRamp(speedRampTolerance, distance, speedRampStartDist, defaultSpeed);
        lift.setSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        boolean result = false;
        if(!overrideManualControl && Math.abs(lift.getControl()) > Constants.Lift.MANUAL_OVERRIDE_TOLERANCE) {
            result = true; // Allow joystick to override RunTo
        } else if(Math.abs(position.getHeight() - lift.getHeight()) <= speedRampTolerance) {
            result = true;
        }
        return result;
    }

    @Override
    protected void end() {
        lift.setSpeed(Constants.Lift.BACKDRIVE);
    }

    @Override
    protected void interrupted() {
        end();
    }
}