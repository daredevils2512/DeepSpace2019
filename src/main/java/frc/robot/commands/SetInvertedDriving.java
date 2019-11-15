/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;

public class SetInvertedDriving extends InstantCommand {
    Drivetrain drivetrain;
    private boolean inverted;

    public SetInvertedDriving(Drivetrain drivetrain, boolean inverted) {
        requires(drivetrain);
        this.drivetrain = drivetrain;
        this.inverted = inverted;
    }

    @Override
    protected void initialize() {
        drivetrain.setInverted(inverted);
    }
}
