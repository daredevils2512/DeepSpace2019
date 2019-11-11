/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import java.util.function.Supplier;

import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends Drive {
    public ArcadeDrive(Supplier<Double> getLeft, Supplier<Double> getRight) {
        super(getLeft, getRight);
    }

    @Override
    protected void initialize() {
        Drivetrain.getInstance().arcadeDrive(getLeft.get() * Robot.slowify, getRight.get() * Robot.slowify);
    }
}