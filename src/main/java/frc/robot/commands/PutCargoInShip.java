/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.constants.Constants.LiftHeight;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PutCargoInShip extends CommandGroup {
    public PutCargoInShip() {
        addSequential(new RunToHeight(LiftHeight.CARGO_SHIP_CARGO, false));
        addSequential(new RunCargoExtake(1, false));
        addSequential(new WaitCommand(1));
        addSequential(new RunToBottom(true));
    }
}
