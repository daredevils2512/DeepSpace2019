package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.CargoExtake;
import frc.robot.subsystems.CargoIntake;

public final class IntakeCargo extends CommandGroup {
    public IntakeCargo(CargoIntake cargoIntake, CargoExtake cargoExtake) {
        addParallel(new RunCargoIntake(cargoIntake, -1.0, -1.0, true));
        addParallel(new RunCargoExtake(cargoExtake, -1.0, true));
    }
}