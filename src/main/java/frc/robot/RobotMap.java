/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;

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
  public static I2C.Port ballColorPort = I2C.Port.kOnboard;
  public static I2C.Port hatchColorPort = I2C.Port.kMXP;
    // MISC
  public static double suppliedUltraVoltage = 5.0;

  // DRIVETRAIN STUFF
    // LEFT DRIVETRAIN STUFF
  public static int leftTalonPort = 1;
  public static int leftRearTalonPort = 2;
  public static int leftEncoderChannelA = 0;
  public static int leftEncoderChannelB = 1;
    // RIGHT DRIVETRAIN STUFF
  public static int rightTalonPort = 3;
  public static int rightRearTalonPort = 4;
  public static int rightEncoderChannelA = 2;
  public static int rightEncoderChannelB = 3;
    // SHIFTING
  public static int shifterForwardChannel = 4;
  public static int shifterReverseChannel = 5;
    // WHEEL GEOMETRY
  public static double wheelDiameter = 6; // inches
  public static double wheelCircumfrence = wheelDiameter * Math.PI; // 18.84
  public static double ticksPerInch = 360 / wheelCircumfrence; // 19.1082802548
  // public static double encoderDistancePerPulse = 0.0236065636;

  public static int spotlightRelayPort = 0;
}
