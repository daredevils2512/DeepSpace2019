/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
// import sun.nio.ch.Net;

import org.opencv.core.*;
import java.util.ArrayList;
import frc.robot.GripWhiteLine;
import edu.wpi.first.networktables.*;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // public static GripWhiteLine m_grippipeline = new GripWhiteLine();
  public static Drivetrain m_Drivetrain = new Drivetrain();
  public static Spotlight m_Spotlight = new Spotlight();
  public static OI m_oi;
  // public static Vision m_vision = new Vision();
  public final int IMG_width = 320;
  public final int IMG_height = 240;
  public static Mat source = new Mat();
  // public static ArrayList<MatOfPoint> convexHullsOutput = new ArrayList<MatOfPoint>();
  public double centerX = 999.00;
  public final String NTserver = "frcvision.local";

  NetworkTableInstance convexHullsFinal = NetworkTableInstance.create();
  
  // NetworkTableInstance convexHullsFinal = NetworkTableInstance.getDefault();

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();

    // Robot.m_vision.init(320 , 240);
    // Robot.m_vision.view(Robot.source);

    // start clientside table
    convexHullsFinal.startClient();
    convexHullsFinal.setServer(NTserver);
    
    // convexHullsFinal.startServer();
    
  }

  NetworkTable convexHullsTable = convexHullsFinal.getTable("White Line Tracking");
  NetworkTableEntry centerXEntry = convexHullsTable.getEntry("centerX");

  public double centerXGetter() {
    
    System.out.println(convexHullsFinal.isConnected());
    if (convexHullsFinal.isConnected() == true) {
      NetworkTableValue centerXValue = this.centerXEntry.getValue();
      // System.out.println("connected to " + convexHullsFinal.getConnections());
      // System.out.println("connection is valid: " + convexHullsFinal.isValid());
      // System.out.println("centerXValue info: " + convexHullsFinal.getEntryInfo("centerX", 1));
      if (centerXValue.getType() == NetworkTableType.kDouble) {
        System.out.println("detcted white line centerX at " + centerXValue.getDouble());
        centerX = centerXValue.getDouble();
        return centerXValue.getDouble();
      } else {
        System.out.println("entry not a double; entry is a " + centerXValue.getType());
        return 999.00;
      } 
    } else {
      return 999.00; 
    }
    
  }  
  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("left clicks", m_Drivetrain.getLeftEncoderValue());
    SmartDashboard.putNumber("right clicks", m_Drivetrain.getRightEncoderValue());
    SmartDashboard.putNumber("left distance", m_Drivetrain.getLeftEncoderDistance());
    SmartDashboard.putNumber("right distance", m_Drivetrain.getRightEncoderDistance());
    SmartDashboard.putNumber("centerX", centerXGetter());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    m_Drivetrain.resetEncoders();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
