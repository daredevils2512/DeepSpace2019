/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  //LEFT DRIVETRAIN STUFF
  public static int leftTalonPort = 1;
  public static int leftRearTalonPort = 2;
  public static int leftEncoderChannelA = 0;
  public static int leftEncoderChannelB = 1;

  //RIGHT DRIVETRAIN STUFF
  public static int rightTalonPort = 3;
  public static int rightRearTalonPort = 4;
  public static int rightEncoderChannelA = 2;
  public static int rightEncoderChannelB = 3;
  
  public static int shifterForwardChannel = 4;
  public static int shifterReverseChannel = 5;

  public static double wheelDiameter = 6; // inches
  public static double wheelCircumfrence = wheelDiameter * Math.PI; // 18.84
  public static double ticksPerInch = 360 / wheelCircumfrence; // 19.1082802548
  // public static double encoderDistancePerPulse = 0.0236065636;

  public static int spotlightRelayPort = 0;

  public static int climberTalon1ID = -1;
  public static int climberTalon2ID = -1;
}
