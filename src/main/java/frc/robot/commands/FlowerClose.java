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
public class FlowerClose extends FlowerActuate {
    public FlowerClose() {
        super(RobotMap.flowerClosedPos);
    }

    public void execute() {
        if (!Robot.m_flower.getOpeningPos().equals(this.m_actuateDir)) {
            Robot.m_flower.close();
        }
    }
}
