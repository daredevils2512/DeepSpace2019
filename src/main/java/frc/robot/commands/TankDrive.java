/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import java.util.function.Supplier;

import frc.robot.subsystems.Drivetrain;

/**
 * An example command.  You can replace me with your own command.
 */
public class TankDrive extends Drive {
    public TankDrive(Drivetrain drivetrain, Supplier<Double> getLeft, Supplier<Double> getRight) {
        super(drivetrain, getLeft, getRight);
    }

    @Override
    protected void execute() {
        drivetrain.driveRobotTank(getLeft.get(), getRight.get());
    }
}