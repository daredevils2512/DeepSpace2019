package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Lift;

public abstract class RunTo extends Command {
    protected final boolean allowManualOverride;
    protected final double speedRampTolerance = 1;
    protected final double speedRampStartDist = 10;

    /**
     * Base class for running the lift to a specified height
     * @param allowManualOverride can be manually overriden
     */
    public RunTo(boolean allowManualOverride) {
        requires(Lift.getInstance());
        this.allowManualOverride = allowManualOverride;
    }
}