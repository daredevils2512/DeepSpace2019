/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class FlowerSlideIn extends FlowerSlide {
    public FlowerSlideIn() {
        super(RobotMap.flowerOutPos);
    }

    public void execute() {
        // if (!Robot.m_flower.getSlidePos().equals(this.m_actuateDir)) {
        //     Robot.m_flower.slideIn();
        // }
        Robot.m_flower.slideIn();
    }
}
