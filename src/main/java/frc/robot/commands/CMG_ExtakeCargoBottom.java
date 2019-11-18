/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.commands.RunCargoExtake;
import frc.robot.commands.RunCargoIntake;
import frc.robot.subsystems.CargoExtake;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Lift;

public final class CMG_ExtakeCargoBottom extends CommandGroup {
    public CMG_ExtakeCargoBottom(Lift lift, CargoExtake cargoExtake, CargoIntake cargoIntake) {
        addSequential(new RunToBottom(lift, true));
        addParallel(new RunCargoExtake(cargoExtake, 0.5, false));
        addParallel(new RunCargoIntake(cargoIntake, 1.0, 1.0, false));
    }
}
