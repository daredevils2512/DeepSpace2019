package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.constants.*;
import frc.robot.constants.Constants.Lift;
import frc.robot.lib.SpeedRamp;

public class RunToPosition extends RunTo {
    private Constants.LiftPosition position;
    private final double speedRampTolerance = 1;
    private final double speedRampStartDist = 10;

    /**
     * Run lift to height
     * @param position Desired height
     * @param overrideManualControl Take priority over joystick contols
     */
    public RunToPosition(Constants.LiftPosition position, boolean overrideManualControl) {
        super(overrideManualControl);
        this.position = position;
    }

    @Override
    protected void execute() {
        double distance = position.getPosition() - Robot.m_lift.getLiftHeight();
        double speed = SpeedRamp.speedRamp(speedRampTolerance, distance, speedRampStartDist, distance < 0 ? Robot.m_lift.MAX_DOWN_SPEED : Robot.m_lift.MAX_UP_SPEED);
        Robot.m_lift.setSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        boolean result = false;

        if(!overrideManualControl && Math.abs(Robot.m_oi.liftControl()) > manualControlOverrideTolerance) {
            result = true; // Allows joystick to override RunToPosition
        } else {
            result = Math.abs(position.getPosition() - Robot.m_lift.getLiftHeight()) < speedRampTolerance;
        }

        return result;
    }

    @Override
    protected void end() {
        Robot.m_lift.setSpeed(Lift.BACKDRIVE);
    }
}