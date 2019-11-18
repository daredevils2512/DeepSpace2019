package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.CargoIntake;
import frc.robot.constants.Constants;
import frc.robot.subsystems.Lift;

public final class CMG_LiftCargo extends CommandGroup {
    public CMG_LiftCargo(Lift lift, CargoIntake cargoIntake) {
        addSequential(new SetCargoIntakeExtended(cargoIntake, false));
        addSequential(new RunToHeight(lift, Constants.Lift.Height.IDLE, true));
    }
}