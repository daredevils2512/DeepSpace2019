/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.HatchIntake;

public class ToggleHatchIntakeFoldPosition extends FoldHatchIntake {
    public ToggleHatchIntakeFoldPosition() {
        super();
    }

    @Override
    protected void initialize() {
        HatchIntake.FoldPosition currentFoldPosition = HatchIntake.getInstance().getFoldPosition();
        if(currentFoldPosition == HatchIntake.FoldPosition.UP) {
            HatchIntake.getInstance().foldDown();
        } else if(currentFoldPosition == HatchIntake.FoldPosition.DOWN) {
            HatchIntake.getInstance().foldUp();
        }
    }
}
