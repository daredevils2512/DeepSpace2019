 /*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

public class ShiftDrivetrainDown extends ShiftDrivetrain {
    public ShiftDrivetrainDown(Drivetrain drivetrain) {
        super(drivetrain);
    }

    @Override
    protected void initialize() {
        drivetrain.setLowGear(true);
    }
}