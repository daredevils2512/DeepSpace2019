/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.Drive;
import com.ctre.phoenix.motorcontrol.can.*;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.*;

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
  private Encoder leftEncoder;
  private Encoder rightEncoder;
  private DoubleSolenoid shifter;


  public Drivetrain() {

    leftTalon = new WPI_TalonSRX(RobotMap.leftTalonPort);    
    rightTalon = new WPI_TalonSRX(RobotMap.rightTalonPort);
    leftRearTalon = new WPI_TalonSRX(RobotMap.leftRearTalonPort);
    rightRearTalon = new WPI_TalonSRX(RobotMap.rightRearTalonPort);
        
    leftEncoder = new Encoder(0, 1, false, CounterBase.EncodingType.k4X);
    rightEncoder = new Encoder(2, 3, true, CounterBase.EncodingType.k4X);

    leftTalonGroup = new SpeedControllerGroup(leftTalon, leftRearTalon);
    rightTalonGroup = new SpeedControllerGroup(rightTalon, rightRearTalon);
    drivetrain = new DifferentialDrive(leftTalonGroup, rightTalonGroup);
    // addChild("Differential Drive 1",drivetrain);

    leftEncoder.setDistancePerPulse(RobotMap.encoderDistancePerPulse);
    rightEncoder.setDistancePerPulse(RobotMap.encoderDistancePerPulse);

    shifter = new DoubleSolenoid(RobotMap.forwardChannelPort, RobotMap.reverseChannelPort);

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

  public void driveRobotTank(double leftSpeed, double rightSpeed) {
    drivetrain.tankDrive(leftSpeed, rightSpeed);
  }

  public double getLeftEncoderDistance() {
    return leftEncoder.getDistance();
  }

  public double getRightEncoderDistance() {
    return rightEncoder.getDistance();
  }

  public int getLeftEncoderValue() {
    return leftEncoder.get();
  }
  
  public int getRightEncoderValue() {
    return rightEncoder.get();
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public DoubleSolenoid.Value getShifterPos() {
    return shifter.get();
  }

  public void shift(DoubleSolenoid.Value shiftPos) {
    shifter.set(shiftPos);
  }


}
