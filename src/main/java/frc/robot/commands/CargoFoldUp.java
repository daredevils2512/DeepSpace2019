/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CargoFoldUp extends CargoFoldIntake {
    public CargoFoldUp() {
        super(RobotMap.cargoUpPos);
    }

    public void execute() {
        if (!Robot.m_cargoIntake.getCurrentPos().equals(this.m_foldDir)) {
            Robot.m_cargoIntake.foldUp();
        }
    }
}
