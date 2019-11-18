/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.HatchIntake;

public final class SetHatchIntakeExtended extends InstantCommand {
    protected final HatchIntake hatchIntake;
    protected final boolean wantsExtended;

    public SetHatchIntakeExtended(HatchIntake hatchIntake, boolean wantsExtended) {
        this.hatchIntake = hatchIntake;
        this.wantsExtended = wantsExtended;
        requires(hatchIntake);
    }

    @Override
    protected void initialize() {
        hatchIntake.setExtended(wantsExtended);
    }
}