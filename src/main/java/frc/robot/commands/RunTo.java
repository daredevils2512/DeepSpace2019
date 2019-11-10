package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public abstract class RunTo extends Command {
    protected boolean overrideManualControl;
    protected final double manualControlOverrideTolerance = 0.5;

    /**
     * Base class for running the lift to a specified height
     * @param overrideManualControl Take priority over joystick controls
     */
    public RunTo(boolean overrideManualControl) {
        requires(Robot.m_lift);
        this.overrideManualControl = overrideManualControl;
    }
}