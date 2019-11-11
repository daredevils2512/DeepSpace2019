/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.CargoIntake;

/**
 * Add your docs here.
 */
public class FoldCargoIntakeDown extends FoldCargoIntake {
    public FoldCargoIntakeDown() {
        super();
    }

    public void execute() {
        CargoIntake.getInstance().foldDown();
    }
}
