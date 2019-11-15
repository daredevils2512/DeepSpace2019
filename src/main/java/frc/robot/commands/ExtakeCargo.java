package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.CargoExtake;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Lift;

public final class ExtakeCargo extends CommandGroup {
    public ExtakeCargo(Lift lift, CargoIntake cargoIntake, CargoExtake CargoExtake, Supplier<Double> getLiftControl) {
        addSequential(new RunToBottom(lift, getLiftControl, false));
        addParallel(new RunCargoIntake(cargoIntake, 1.0, 1.0, true));
        addParallel(new RunCargoExtake(CargoExtake, 1.0, true));
    }
}