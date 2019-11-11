/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.HatchIntake;

public class OpenHatchPanelLatch extends ActuateHatchPanelLatch {
    public OpenHatchPanelLatch() {
        super();
    }

    @Override
    public void execute() {
        HatchIntake.getInstance().openLatch();
    }
}
