 /*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;

public final class SetDrivetrainLowGear extends InstantCommand {
    protected Drivetrain drivetrain;
    protected boolean wantsLowGear;

    public SetDrivetrainLowGear(Drivetrain drivetrain, boolean wantsLowGear) {
        this.drivetrain = drivetrain;
        this.wantsLowGear = wantsLowGear;
        requires(drivetrain);
    }

    @Override
    protected void initialize() {
        drivetrain.setLowGear(wantsLowGear);
    }
}