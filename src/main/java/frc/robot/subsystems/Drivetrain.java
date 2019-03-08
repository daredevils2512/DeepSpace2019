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
import edu.wpi.first.wpilibj.*;

import frc.robot.Robot;
import frc.robot.RobotMap;
// import frc.robot.commands.ArcadeDrive;
import frc.robot.constants.Constants.YPRSelect;

// import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.sensors.PigeonIMU;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private CANSparkMax leftSpark;
  private CANSparkMax rightSpark;
  private CANSparkMax leftRearSpark;
  private CANSparkMax rightRearSpark;
  private DifferentialDrive drivetrain;
  private SpeedControllerGroup leftSparkGroup;
  private SpeedControllerGroup rightSparkGroup;
  private Encoder leftEncoder;
  private Encoder rightEncoder;
  private DoubleSolenoid shifter;

  public PigeonIMU gyro;
  private double[] yprData = {0.0, 0.0, 0.0}; //[Yaw, Pitch, Roll]

  private static final DoubleSolenoid.Value high = DoubleSolenoid.Value.kForward;
  private static final DoubleSolenoid.Value low = DoubleSolenoid.Value.kReverse;
  
  private static double wheelDiameter = 6; // inches
  private static double pulsePerRotation = 128;
  private static double gearRatio = 1/3; //wheel-encoder
  private static double driveEncoderPulsePerRotation = gearRatio * pulsePerRotation; // 42.6666666666
  private static double driveEncoderDistancePerTick = (Math.PI * wheelDiameter) / driveEncoderPulsePerRotation; // 0.4416315049
  


  private static int inverted = 1;

  public Drivetrain() {

    leftSpark = new CANSparkMax(RobotMap.leftSparkID, MotorType.kBrushless);    
    rightSpark = new CANSparkMax(RobotMap.rightSparkID, MotorType.kBrushless);
    leftRearSpark = new CANSparkMax(RobotMap.leftRearSparkID, MotorType.kBrushless);
    rightRearSpark = new CANSparkMax(RobotMap.rightRearSparkID, MotorType.kBrushless);

    leftSpark.setIdleMode(IdleMode.kCoast);
    rightSpark.setIdleMode(IdleMode.kCoast);
    leftRearSpark.setIdleMode(IdleMode.kCoast);
    rightRearSpark.setIdleMode(IdleMode.kCoast);
        
    leftEncoder = new Encoder(RobotMap.leftEncoderChannelA, RobotMap.leftEncoderChannelB, false, CounterBase.EncodingType.k4X);
    rightEncoder = new Encoder(RobotMap.rightEncoderChannelA, RobotMap.rightEncoderChannelB, true, CounterBase.EncodingType.k4X);

    leftSparkGroup = new SpeedControllerGroup(leftSpark, leftRearSpark);
    rightSparkGroup = new SpeedControllerGroup(rightSpark, rightRearSpark);
    drivetrain = new DifferentialDrive(leftSparkGroup, rightSparkGroup);
    // addChild("Differential Drive 1",drivetrain);

    leftEncoder.setDistancePerPulse(driveEncoderDistancePerTick);
    rightEncoder.setDistancePerPulse(driveEncoderDistancePerTick);
    
    // this.rumblely = RumbleType.kLeftRumble;
    // this.rumblely = RumbleType.kRightRumble;

    shifter = new DoubleSolenoid(RobotMap.shifterForwardChannel, RobotMap.shifterReverseChannel);

    gyro = new PigeonIMU(0);
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //  setDefaultCommand(new ArcadeDrive(Robot.m_oi::getMove, Robot.m_oi::getTurn));
  }

  public void arcadeDrive(double move, double turn) {
    drivetrain.arcadeDrive(move * inverted, turn *inverted);
  }

  public void driveRobotTank(double leftSpeed, double rightSpeed) {
    drivetrain.tankDrive(leftSpeed *inverted, rightSpeed * inverted);
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
    return this.leftSpark.get();
  }

  public double leftRearSpeed() {
    return this.leftRearSpark.get();
  }

  public double rightFrontSpeed() {
    return this.rightSpark.get();
  }

  public double rightRearSpeed() {
    return this.rightRearSpark.get();
  }

  public void updateYPRData() {
    this.gyro.getYawPitchRoll(this.yprData);
  }

  public double getNonCummulativeYaw() {
    return getYaw() % 360;
  }

  private double getSelectedYPR(YPRSelect selectedYPR) {
    this.updateYPRData();
    double selectedYPRData = 0.0;
    switch (selectedYPR) {
      case YAW:
        selectedYPRData = this.yprData[0];
        break;
      case PITCH:
        selectedYPRData = this.yprData[1];
        break;
      case ROLL:
        selectedYPRData = this.yprData[0];
        break;
    }
    return selectedYPRData;
  }

  public double getYaw() {
    return this.getSelectedYPR(YPRSelect.YAW);
  }

  public double getPitch() {
    return this.getSelectedYPR(YPRSelect.PITCH);
  }

  public double getRoll() {
    return this.getSelectedYPR(YPRSelect.ROLL);
  }

  public double convertToCummulative(double yaw) {
    yaw = ((int) (this.getYaw() / 360)) * 360 + yaw;
    return yaw;
  }

  public void resetYaw() {
    gyro.setYaw(0.0);
  }

  public void alignByGyro(float desiredYaw, double tolerance) {
    if (Robot.m_Drivetrain.getYaw() >= Robot.m_Drivetrain.convertToCummulative(desiredYaw) + tolerance) {
      Robot.m_Drivetrain.arcadeDrive(0.0, -0.75);
    } else if (Robot.m_Drivetrain.getYaw() <= Robot.m_Drivetrain.convertToCummulative(desiredYaw) - tolerance) {
      Robot.m_Drivetrain.arcadeDrive(0.0, 0.75);
    } else {
      System.out.println("Robot is aligned");
    }
  }

  public void toggleInverted() {
    inverted = -inverted;
  }
}
