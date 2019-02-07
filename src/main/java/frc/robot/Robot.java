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
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import com.kauailabs.navx.frc.AHRS;
// import sun.nio.ch.Net;

import org.opencv.core.*;
// import java.util.ArrayList;
// import frc.robot.GripWhiteLine;
import edu.wpi.first.networktables.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogGyro;

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
  public static LineFind m_LineFind = new LineFind();
  public static OI m_oi;
  public static NavX m_navX;
  // public static Vision m_vision = new Vision();
  public final int IMG_width = 320;
  public final int IMG_height = 240;
  public static Mat source = new Mat();
  // public static ArrayList<MatOfPoint> convexHullsOutput = new ArrayList<MatOfPoint>();
  public static double centerX = 999.00;
  public static double width = 999.00;
  public static double height = 999.00;
  public static double bottom = 999.00;
  public static double top = 999.00;
  public static double widthPos = 999.00;
  public static boolean centered = false;
  public static boolean aligned = false;
  public static double diff = 0.0;
  public static char dir = 'n';
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
    m_navX = new NavX();

    // Robot.m_vision.init(320 , 240);
    // Robot.m_vision.view(Robot.source);

    // start clientside table
    convexHullsFinal.startClient();
    convexHullsFinal.setServer(NTserver);
    
    // convexHullsFinal.startServer();
    
    NavX.navX.reset();
  }

  NetworkTable convexHullsTable = convexHullsFinal.getTable("White Line Tracking");
  NetworkTableEntry centerXEntry = convexHullsTable.getEntry("centerX");
  NetworkTableEntry widthEntry = convexHullsTable.getEntry("width");
  NetworkTableEntry heightEntry = convexHullsTable.getEntry("height");
  NetworkTableEntry bottomEntry = convexHullsTable.getEntry("bottom");
  NetworkTableEntry topEntry = convexHullsTable.getEntry("top");
  NetworkTableEntry widthPosEntry = convexHullsTable.getEntry("widthPos");

  public double centerXGetter() {
    
    // System.out.println(convexHullsFinal.isConnected());
    if (convexHullsFinal.isConnected()) {
      NetworkTableValue centerXValue = this.centerXEntry.getValue();
      // System.out.println("connected to " + convexHullsFinal.getConnections());
      // System.out.println("connection is valid: " + convexHullsFinal.isValid());
      // System.out.println("centerXValue info: " + convexHullsFinal.getEntryInfo("centerX", 1));
      if (centerXValue.getType() == NetworkTableType.kDouble) {
        System.out.println("detcted white line centerX at " + centerXValue.getDouble());
        centerX = centerXValue.getDouble();
        return centerXValue.getDouble();
      } else {
        System.out.println("centerXentry not a double; entry is a " + centerXValue.getType());
        return 999.00;
      } 
    } else {
      return 999.00; 
    }
    
  } 
  

  public double widthGetter() {
    
    // System.out.println(convexHullsFinal.isConnected());
    if (convexHullsFinal.isConnected()) {
      NetworkTableValue widthValue = this.widthEntry.getValue();
      // System.out.println("connected to " + convexHullsFinal.getConnections());
      // System.out.println("connection is valid: " + convexHullsFinal.isValid());
      // System.out.println("widthValue info: " + convexHullsFinal.getEntryInfo("width", 1));
      if (widthValue.getType() == NetworkTableType.kDouble) {
        System.out.println("detcted white line width of " + widthValue.getDouble());
        width = widthValue.getDouble();
        return widthValue.getDouble();
      } else {
        System.out.println("width entry not a double; entry is a " + widthValue.getType());
        return 999.00;
      } 
    } else {
      return 999.00; 
    }
  }

  public double heightGetter() {
    
    // System.out.println(convexHullsFinal.isConnected());
    if (convexHullsFinal.isConnected()) {
      NetworkTableValue heightValue = this.heightEntry.getValue();
      // System.out.println("connected to " + convexHullsFinal.getConnections());
      // System.out.println("connection is valid: " + convexHullsFinal.isValid());
      // System.out.println("heightValue info: " + convexHullsFinal.getEntryInfo("height", 1));
      if (heightValue.getType() == NetworkTableType.kDouble) {
        System.out.println("detcted white line height of " + heightValue.getDouble());
        height = heightValue.getDouble();
        return heightValue.getDouble();
      } else {
        System.out.println("height entry not a double; entry is a " + heightValue.getType());
        return 999.00;
      } 
    } else {
      return 999.00; 
    }
  }

  public double bottomGetter() {
    
    // System.out.println(convexHullsFinal.isConnected());
    if (convexHullsFinal.isConnected()) {
      NetworkTableValue bottomValue = this.bottomEntry.getValue();
      // System.out.println("connected to " + convexHullsFinal.getConnections());
      // System.out.println("connection is valid: " + convexHullsFinal.isValid());
      // System.out.println("bottomValue info: " + convexHullsFinal.getEntryInfo("bottom", 1));
      if (bottomValue.getType() == NetworkTableType.kDouble) {
        System.out.println("detcted white line bottom at " + bottomValue.getDouble());
        bottom = bottomValue.getDouble();
        return bottomValue.getDouble();
      } else {
        System.out.println("bottom entry not a double; entry is a " + bottomValue.getType());
        return 999.00;
      } 
    } else {
      return 999.00; 
    }
  }

  public double topGetter() {
    
    // System.out.println(convexHullsFinal.isConnected());
    if (convexHullsFinal.isConnected()) {
      NetworkTableValue topValue = this.topEntry.getValue();
      // System.out.println("connected to " + convexHullsFinal.getConnections());
      // System.out.println("connection is valid: " + convexHullsFinal.isValid());
      // System.out.println("topValue info: " + convexHullsFinal.getEntryInfo("top", 1));
      if (topValue.getType() == NetworkTableType.kDouble) {
        System.out.println("detcted white line top at " + topValue.getDouble());
        top = topValue.getDouble();
        return topValue.getDouble();
      } else {
        System.out.println("top entry not a double; entry is a " + topValue.getType());
        return 999.00;
      } 
    } else {
      return 999.00; 
    }
  }

  public double widthPosGetter() {
    
    // System.out.println(convexHullsFinal.isConnected());
    if (convexHullsFinal.isConnected()) {
      NetworkTableValue widthPosValue = this.widthPosEntry.getValue();
      // System.out.println("connected to " + convexHullsFinal.getConnections());
      // System.out.println("connection is valid: " + convexHullsFinal.isValid());
      // System.out.println("widthPosValue info: " + convexHullsFinal.getEntryInfo("widthPos", 1));
      if (widthPosValue.getType() == NetworkTableType.kDouble) {
        System.out.println("detcted white line widthPos at " + widthPosValue.getDouble());
        widthPos = widthPosValue.getDouble();
        return widthPosValue.getDouble();
      } else {
        System.out.println("widthPos entry not a double; entry is a " + widthPosValue.getType());
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
    SmartDashboard.putNumber("width", widthGetter());
    SmartDashboard.putNumber("height", heightGetter());
    SmartDashboard.putNumber("bottom", bottomGetter());
    SmartDashboard.putNumber("top", topGetter());
    SmartDashboard.putNumber("widthPos", widthPosGetter());
    SmartDashboard.putNumber("yaw", m_navX.getYaw());
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
