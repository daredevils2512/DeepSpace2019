/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.HatchIntake;

/**
 * Add your docs here.
 */
public class FoldDownHatchIntake extends FoldHatchIntake {
    public FoldDownHatchIntake() {
        super();
    }

    @Override
    public void initialize() {
        HatchIntake.getInstance().foldDown();
    }
}
