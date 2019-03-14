/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  // DISTANCE SENSOR STUFF
    // OFFSETS
  public final static double hatchSensorsOffsetFromFrame = 6.1852; // in inches
  public final static double ballSensorsOffsetFromFrame = 10.11;    // in inches
    // PORTS
  public static int ballUltrasonicPort = 0;
  public static int hatchUltrasonicPort = 1;
  public static I2C.Port ballColorPort = I2C.Port.kMXP;
  public static I2C.Port hatchColorPort = I2C.Port.kOnboard;
    // MISC
  public static double suppliedUltraVoltage = 5.0;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  public static double driveEncoderDistancePerPulse = 0.0236065636;
    
  public static int liftTalon1Id = 5;
  public static int liftTalon2Id = 6;
  public static int limitSwitchBottomPort = 8;
  public static int limitSwitchTopPort = 13;
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

  public static double wheelDiameter = 6; // inches
  public static double wheelCircumfrence = wheelDiameter * Math.PI; // 18.84
  public static double ticksPerInch = 360 / wheelCircumfrence; // 19.1082802548
  public static double encoderDistancePerPulse = 0.0236065636;
  // CARGO STUFF
  public static int cargoInfinityPort = 11;
  public static int cargoInMotorPort = 10;
  public static int cargoUpDownAPort = 2;
  public static int cargoUpDownBPort = 3;
  public static Value cargoUpPos = Value.kForward;
  public static Value cargoDownPos = Value.kReverse;

  public static int ballXtake1ID = 7;
  public static int ballXtake2ID = 8;
  public static int ballOccupancy = 9;

  // HATCH STUFF
  public static int flowerSolenoidForwardChannel = 6;
  public static int flowerSolenoidReverseChannel = 7;
  public static int flowerSlideForwardChannel = 4;  // down
  public static int flowerSlideReverseChannel = 5;  // up

  public static Value flowerOutPos = Value.kForward;
  public static Value flowerInPos = Value.kReverse;
  public static Value flowerOpenPos = Value.kForward;
  public static Value flowerClosedPos = Value.kReverse;

  public static int spotlightRelayPort = 0;
}
