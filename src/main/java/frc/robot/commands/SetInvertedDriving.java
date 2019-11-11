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
    private boolean inverted;

    public SetInvertedDriving(boolean inverted) {
        this.inverted = inverted;
    }

    @Override
    protected void initialize() {
        Drivetrain.getInstance().setInverted(inverted);
    }
}
