/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.CargoIntake;

public final class RetractCargoIntake extends InstantCommand {
    protected CargoIntake cargoIntake;

    public RetractCargoIntake(CargoIntake cargoIntake) {
        this.cargoIntake = cargoIntake;
        requires(cargoIntake);
    }

    @Override
    protected void execute() {
        cargoIntake.setExtended(false);
    }
}
