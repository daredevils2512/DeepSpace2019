package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.constants.Constants.LiftHeight;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Lift;

public final class CMG_LiftCargo extends CommandGroup {
    public CMG_LiftCargo(Lift lift, CargoIntake cargoIntake, Supplier<Double> getLiftControl) {
        System.out.println("lift cargo did");
        addSequential(new RetractCargoIntake(cargoIntake));
        addSequential(new RunToHeight(lift, getLiftControl, LiftHeight.ROCKET_HATCH_BOTTOM, true)); 
    }
}