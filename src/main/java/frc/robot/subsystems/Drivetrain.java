/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX leftTalon;
  private WPI_TalonSRX rightTalon;
  private WPI_TalonSRX leftRearTalon;
  private WPI_TalonSRX rightRearTalon;
  private DifferentialDrive drivetrain;
  private SpeedControllerGroup leftSpeedControllerGroup;
  private SpeedControllerGroup rightSpeedControllerGroup;

  public Drivetrain() {

    leftTalon = new WPI_TalonSRX(RobotMap.leftTalon);    
    rightTalon = new WPI_TalonSRX(RobotMap.rightTalon);
    leftRearTalon = new WPI_TalonSRX(RobotMap.leftRearTalon);
    rightRearTalon = new WPI_TalonSRX(RobotMap.rightRearTalon);

    leftSpeedControllerGroup = new SpeedControllerGroup(leftTalon, leftRearTalon);
    rightSpeedControllerGroup = new SpeedControllerGroup(rightTalon, rightRearTalon);    
        
        
    drivetrain = new DifferentialDrive(leftSpeedControllerGroup, rightSpeedControllerGroup);
    addChild("Differential Drive 1",drivetrain);



  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

    

  }
}
