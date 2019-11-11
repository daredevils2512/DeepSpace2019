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

public class CMG_ExtakeCargoBottom extends CommandGroup {
    public CMG_ExtakeCargoBottom() {
        addParallel(new RunCargoExtake(0.5, false));
        addParallel(new RunCargoIntake(0.0, 1.0, false));
    }
}
