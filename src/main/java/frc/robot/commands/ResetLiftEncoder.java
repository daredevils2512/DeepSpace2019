/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Lift;

public final class ResetLiftEncoder extends InstantCommand {
    protected Lift lift;

    public ResetLiftEncoder(Lift lift) {
        requires(lift);
        this.lift = lift;
    }

    @Override
    protected void initialize() {
        lift.resetLiftEncoder();
    }
}
