package frc.robot.commands;

import frc.robot.constants.Constants;
import frc.robot.lib.SpeedRamp;
import frc.robot.subsystems.Lift;

import java.util.function.Supplier;

public class RunToHeight extends RunTo {
    protected Constants.LiftHeight position;

    /**
     * Run lift to height
     * @param position desired height
     * @param allowManualOverride can be manually overriden
     */
    public RunToHeight(Lift lift, Supplier<Double> getLiftControl, Constants.LiftHeight position, boolean allowManualOverride) {
        super(lift, getLiftControl, allowManualOverride);
        this.position = position;
    }

    @Override
    protected void execute() {
        double distance = position.getHeight() - lift.getLiftHeight();
        double defaultSpeed = distance < 0 ? Constants.Lift.MAX_DOWN_SPEED : Constants.Lift.MAX_UP_SPEED;
        double speed = SpeedRamp.speedRamp(speedRampTolerance, distance, speedRampStartDist, defaultSpeed);
        lift.setSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        boolean result = false;

        if(allowManualOverride && Math.abs(getLiftControl.get()) > Constants.Lift.MANUAL_OVERRIDE_TOLERANCE) {
            result = true; // Allow joystick to override RunToPosition
        } else {
            result = Math.abs(position.getHeight() - lift.getLiftHeight()) < speedRampTolerance;
        }

        return result;
    }

    @Override
    protected void end() {
        lift.setSpeed(Constants.Lift.BACKDRIVE);
    }
}