/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Drivetrain;

public abstract class Drive extends Command {
    protected final Drivetrain drivetrain;
    protected final Supplier<Double> getLeft, getRight;

    public Drive(Drivetrain drivetrain, Supplier<Double> getLeft, Supplier<Double> getRight) {
        requires(drivetrain);
        this.drivetrain = drivetrain;
        this.getLeft = getLeft;
        this.getRight = getRight;
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
