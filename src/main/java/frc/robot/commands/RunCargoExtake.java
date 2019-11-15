package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CargoExtake;

public final class RunCargoExtake extends Command {
    protected CargoExtake cargoExtake;
    protected double speed;
    protected boolean override;

    /**
     * Run cargo extake
     * @param speed speed at which to run (negative values intake, positive values extake)
     * @param override override cargo limit switch
     */
    public RunCargoExtake(CargoExtake cargoExtake, double speed, boolean override) {
        super(2);
        requires(cargoExtake);
        this.cargoExtake = cargoExtake;
        this.speed = speed;
        this.override = override;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        cargoExtake.setSpeed(speed);
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
        
        return result;
    }

    @Override
    protected void end() {
        cargoExtake.setSpeed(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}