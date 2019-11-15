package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Lift;

public abstract class RunTo extends Command {
    protected final Lift lift;
    protected final Supplier<Double> getLiftControl;
    protected final boolean allowManualOverride;
    protected final double speedRampTolerance = 1;
    protected final double speedRampStartDist = 10;

    /**
     * Base class for running the lift to a specified height
     * @param allowManualOverride can be manually overriden
     */
    public RunTo(Lift lift, Supplier<Double> getLiftControl, boolean allowManualOverride) {
        requires(lift);
        this.lift = lift;
        this.getLiftControl = getLiftControl;
        this.allowManualOverride = allowManualOverride;
    }
}