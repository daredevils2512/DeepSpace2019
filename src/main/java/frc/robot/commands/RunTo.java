package frc.robot.commands;
import frc.robot.subsystems.Lift;

public abstract class RunTo extends LiftCommand {
    protected final boolean overrideManualControl;
    protected final double speedRampTolerance = 1;
    protected final double speedRampStartDist = 10;

    /**
     * Base class for running the lift to specified heights
     * @param overrideManualControl override joystick controls
     */
    public RunTo(Lift lift, boolean overrideManualControl) {
        super(lift);
        this.overrideManualControl = overrideManualControl;
    }
}