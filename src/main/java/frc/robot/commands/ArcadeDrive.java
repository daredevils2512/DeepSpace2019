/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public final class ArcadeDrive extends DriveCommand {
    public ArcadeDrive(Drivetrain drivetrain) {
        super(drivetrain);
    }

    @Override
    protected void execute() {
        drivetrain.arcadeDrive(drivetrain.getMove() * Robot.getSlowify(), drivetrain.getTurn() * Robot.getSlowify());
    }
}