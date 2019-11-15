/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.HatchIntake;

public final class ExtendHatchIntake extends InstantCommand {
    protected final HatchIntake hatchIntake;

    public ExtendHatchIntake(HatchIntake hatchIntake) {
        this.hatchIntake = hatchIntake;
        requires(hatchIntake);
    }

    @Override
    protected void initialize() {
        hatchIntake.setExtended(true);
    }
}
