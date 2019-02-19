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

  public static double driveEncoderDistancePerPulse = 0.0236065636;
    
  public static int liftTalon1Id = 5;
  public static int liftTalon2Id = 6;
  public static int limitSwitchBottomPort = 4;
  public static int limitSwitchTopPort = 5;
  // a negative implies we dont know the port yet
  //LEFT DRIVETRAIN STUFF
  public static int leftSparkID = 1;
  public static int leftRearSparkID = 2;
  public static int leftEncoderChannelA = 0;
  public static int leftEncoderChannelB = 1;

  //RIGHT DRIVETRAIN STUFF
  public static int rightSparkID = 3;
  public static int rightRearSparkID = 4;
  public static int rightEncoderChannelA = 2;
  public static int rightEncoderChannelB = 3;
  
  public static int shifterForwardChannel = 0;
  public static int shifterReverseChannel = 1;

  public static int ballXtake1ID = 7;
  public static int ballXtake2ID = 8;

  public static int flowerSolenoidForwardChannel = 2;
  public static int flowerSolenoidReverseChannel = 3;
  public static int flowerSlideForwardChannel = 4;
  public static int flowerSlideReverseChannel = 5;

  public static int spotlightRelayPort = 0;
}
