/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.HatchIntake;

public class ToggleHatchIntakeLatch extends ActuateHatchPanelLatch {
    public ToggleHatchIntakeLatch() {
        super();
    }

    @Override
    protected void initialize() {
        HatchIntake.LatchPosition currentLatchPosition = HatchIntake.getInstance().getLatchPosition();
        if(currentLatchPosition == HatchIntake.LatchPosition.CLOSED) {
            HatchIntake.getInstance().openLatch();
        } else if(currentLatchPosition == HatchIntake.LatchPosition.OPEN) {
            HatchIntake.getInstance().closeLatch();
        }
    }
}
