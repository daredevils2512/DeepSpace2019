/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CargoFoldDown extends CargoFoldIntake {
    public CargoFoldDown() {
        super(RobotMap.cargoDownPos);
    }

    public void execute() {
        // Not on robot
    //     System.out.println("Ball Distance: " + Robot.m_ballDistanceSensor.getDistance());
        if (!Robot.m_cargoIntake.getCurrentPos().equals(this.m_foldDir)/* && Robot.m_ballDistanceSensor.getDistance() >= 18*/) {
            Robot.m_cargoIntake.foldDown();
        }
    }
}
