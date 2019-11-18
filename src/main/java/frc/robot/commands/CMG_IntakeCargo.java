/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.CargoExtake;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Lift;

public final class CMG_IntakeCargo extends CommandGroup {
    public CMG_IntakeCargo(Lift lift, CargoExtake cargoExtake, CargoIntake cargoIntake) {
        if (!lift.getLimitSwitchBottom()) {
            addSequential(new RunToBottom(lift, false));
        }
        if(!CargoExtake.getBallOccupancy()) {
            addSequential(new SetCargoIntakeExtended(cargoIntake, true));
            addParallel(new RunCargoIntake(cargoIntake, -1.0, -1.0, false));
            addSequential(new RunCargoExtake(cargoExtake, -1.0, false));
        }
        addSequential(new CMG_LiftCargo(lift, cargoIntake));
    }
}