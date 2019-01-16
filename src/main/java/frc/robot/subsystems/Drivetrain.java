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
import frc.robot.commands.Drive;
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
  private SpeedControllerGroup leftTalonGroup;
  private SpeedControllerGroup rightTalonGroup;


  public Drivetrain() {

    leftTalon = new WPI_TalonSRX(RobotMap.leftTalonPort);    
    rightTalon = new WPI_TalonSRX(RobotMap.rightTalonPort);
    leftRearTalon = new WPI_TalonSRX(RobotMap.leftRearTalonPort);
    rightRearTalon = new WPI_TalonSRX(RobotMap.rightRearTalonPort);

    leftTalonGroup = new SpeedControllerGroup(leftTalon, leftRearTalon);
    rightTalonGroup = new SpeedControllerGroup(rightTalon, rightRearTalon);
        
        
        
    drivetrain = new DifferentialDrive(leftTalonGroup, rightTalonGroup);
    addChild("Differential Drive 1",drivetrain);

  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Drive());
  }

  public void arcadeDrive(double move, double turn) {
    drivetrain.arcadeDrive(move, turn);
  }



}
