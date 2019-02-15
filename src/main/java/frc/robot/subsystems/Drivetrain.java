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
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.sensors.PigeonIMU;
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

  // private PigeonIMU gyro;
  private double[] yprData = {0.0, 0.0, 0.0}; //[Yaw, Pitch, Roll]

  private static final DoubleSolenoid.Value high = DoubleSolenoid.Value.kForward;
  private static final DoubleSolenoid.Value low = DoubleSolenoid.Value.kReverse;
  
  private static double wheelDiameter = 6; // inches
  private static double pulsePerRotation = 128;
  private static double gearRatio = 1/3; //wheel-encoder
  private static double driveEncoderPulsePerRotation = gearRatio * pulsePerRotation; // 42.6666666666
  private static double driveEncoderDistancePerTick = (Math.PI * wheelDiameter) / driveEncoderPulsePerRotation; // 0.4416315049

  public Drivetrain() {

    leftTalon = new WPI_TalonSRX(RobotMap.leftTalonPort);    
    rightTalon = new WPI_TalonSRX(RobotMap.rightTalonPort);
    leftRearTalon = new WPI_TalonSRX(RobotMap.leftRearTalonPort);
    rightRearTalon = new WPI_TalonSRX(RobotMap.rightRearTalonPort);

    /*
    * These configs because second drive motor in each gearbox is now a 775
    * due to weight. 775s will burn out if too high of a current is applied
    * for to long so hopefully this will prevent that.
    * COMPLETELY UNTESTED
    */
    leftRearTalon.configPeakCurrentLimit(70);
    leftRearTalon.configContinuousCurrentLimit(50);
    leftRearTalon.configPeakCurrentDuration(300);

    rightRearTalon.configPeakCurrentLimit(70);
    rightRearTalon.configContinuousCurrentLimit(50);
    rightRearTalon.configPeakCurrentDuration(300);
        
    leftEncoder = new Encoder(RobotMap.leftEncoderChannelA, RobotMap.leftEncoderChannelB, false, CounterBase.EncodingType.k4X);
    rightEncoder = new Encoder(RobotMap.rightEncoderChannelA, RobotMap.rightEncoderChannelB, true, CounterBase.EncodingType.k4X);

    leftTalonGroup = new SpeedControllerGroup(leftTalon, leftRearTalon);
    rightTalonGroup = new SpeedControllerGroup(rightTalon, rightRearTalon);
    drivetrain = new DifferentialDrive(leftTalonGroup, rightTalonGroup);
    // addChild("Differential Drive 1",drivetrain);

    leftEncoder.setDistancePerPulse(driveEncoderDistancePerTick);
    rightEncoder.setDistancePerPulse(driveEncoderDistancePerTick);
    
    // this.rumblely = RumbleType.kLeftRumble;
    // this.rumblely = RumbleType.kRightRumble;

    shifter = new DoubleSolenoid(RobotMap.shifterForwardChannel, RobotMap.shifterReverseChannel);

    // gyro = new PigeonIMU(0);
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ArcadeDrive(Robot.m_oi::getMove, Robot.m_oi::getTurn));
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

  private void shift(DoubleSolenoid.Value shiftPos) {
    shifter.set(shiftPos);
  }

  public void shiftUp() {
    this.shift(high);
  }

  public void shiftDown() {
    this.shift(low);
  }

  public double leftFrontSpeed() {
    return this.leftTalon.get();
  }

  public double leftRearSpeed() {
    return this.leftRearTalon.get();
  }

  public double rightFrontSpeed() {
    return this.rightTalon.get();
  }

  public double rightRearSpeed() {
    return this.rightRearTalon.get();
  }

  // public void updateYPRData() {
  //   this.gyro.getYawPitchRoll(this.yprData);
  // }

  public double getYaw() {
    // this.updateYPRData();
    return this.yprData[0];
  }

  public double getPitch() {
    // this.updateYPRData();
    return this.yprData[1];
  }

  public double getRoll() {
    // this.updateYPRData();
    return this.yprData[2];
  }

}
