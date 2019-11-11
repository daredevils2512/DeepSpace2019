package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CargoExtake;

public class RunCargoExtake extends Command {
    private double speed;
    private boolean override;

    /**
     * Run cargo extake
     * @param speed speed at which to run (negative values intake, positive values extake)
     * @param override override cargo limit switch
     */
    public RunCargoExtake(double speed, boolean override) {
        super(2);
        requires(CargoExtake.getInstance());
        this.speed = speed;
        this.override = override;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        CargoExtake.getInstance().setSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        // If intaking, stop when limit switch is tripped
        // else, just keep spinning 
        boolean result = true;
        if (!override && speed < 0) {
            result = CargoExtake.getBallOccupancy();
        } else {
            result = false;
        }

        if (result && speed > 0) {
            CMG_IntakeCargo.ballOut();
        }
        return result;
    }

    @Override
    protected void end() {
        CargoExtake.getInstance().setSpeed(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }

}