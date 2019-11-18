/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Drivetrain;

public abstract class DriveCommand extends Command {
    protected final Drivetrain drivetrain;

    public DriveCommand(Drivetrain drivetrain) {
        requires(drivetrain);
        this.drivetrain = drivetrain;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        drivetrain.leftSpeed(0.0);
        drivetrain.rightSpeed(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
