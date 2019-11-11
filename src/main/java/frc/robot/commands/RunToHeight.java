package frc.robot.commands;

import frc.robot.constants.Constants;
import frc.robot.lib.SpeedRamp;
import frc.robot.subsystems.Lift;
import frc.robot.OI;

public class RunToHeight extends RunTo {
    protected Constants.LiftHeight position;

    /**
     * Run lift to height
     * @param position desired height
     * @param allowManualOverride can be manually overriden
     */
    public RunToHeight(Constants.LiftHeight position, boolean allowManualOverride) {
        super(allowManualOverride);
        this.position = position;
    }

    @Override
    protected void execute() {
        double distance = position.getHeight() - Lift.getInstance().getLiftHeight();
        double defaultSpeed = distance < 0 ? Constants.Lift.MAX_DOWN_SPEED : Constants.Lift.MAX_UP_SPEED;
        double speed = SpeedRamp.speedRamp(speedRampTolerance, distance, speedRampStartDist, defaultSpeed);
        Lift.getInstance().setSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        boolean result = false;

        if(allowManualOverride && Math.abs(OI.getInstance().liftControl()) > Constants.Lift.MANUAL_OVERRIDE_TOLERANCE) {
            result = true; // Allow joystick to override RunToPosition
        } else {
            result = Math.abs(position.getHeight() - Lift.getInstance().getLiftHeight()) < speedRampTolerance;
        }

        return result;
    }

    @Override
    protected void end() {
        Lift.getInstance().setSpeed(Constants.Lift.BACKDRIVE);
    }
}