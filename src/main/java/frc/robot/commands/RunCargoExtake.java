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
     * @param override override cargo limit switch (when intaking)
     */
    public RunCargoExtake(CargoExtake cargoExtake, double speed, boolean override) {
        requires(cargoExtake);
        this.cargoExtake = cargoExtake;
        this.speed = speed;
        this.override = override;
    }

    public RunCargoExtake(CargoExtake cargoExtake, double speed, boolean override, double timeout) {
        super(timeout);
        requires(cargoExtake);
        this.cargoExtake = cargoExtake;
        this.speed = speed;
        this.override = override;
    }

    @Override
    protected void execute() {
        if(isIntaking() && CargoExtake.getBallOccupancy() && !override) {
            cargoExtake.setSpeed(0.0);
        } else {
            cargoExtake.setSpeed(speed);
        }
    }

    @Override
    protected boolean isFinished() {
        // Stop if the cargo extake is intaking, it contains cargo, and override is off
        boolean result = false;
        if (isIntaking() && CargoExtake.getBallOccupancy() && !override) {
            result = true;
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

    private boolean isIntaking() {
        return speed < 0.0;
    }
}