/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.Lift;

public class CMG_IntakeCargo extends CommandGroup {
    private static boolean ballIn = false;

    public CMG_IntakeCargo() {
        if (!Lift.getInstance().getLimitSwitchBottom()) {
            addSequential(new RunToBottom(false));
        }
    // if (!ballIn) {
        addSequential(new FoldCargoIntakeDown());
        addParallel(new RunCargoIntake(-1.0, -1.0, false));
        addSequential(new RunCargoExtake(-1.0, false));
        // ballIn = true;
        addSequential(new CMG_LiftCargo());
    // }
    }

    public static void ballOut() {
        ballIn = false;
    }

}