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
import frc.robot.vision.Utils;

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

  public static int teamNumber = 2512;

  // public static GripWhiteLine m_grippipeline = new GripWhiteLine();
  public static LineFind m_LineFind;
  public static Drivetrain m_Drivetrain;
  // public static Spotlight m_Spotlight = new Spotlight();
  public static Compressorsorus m_Compressorsorus;
  public static OI m_oi;
  public static NavX m_navX;

  // public static Vision m_vision = new Vision();
  //THESE SHOULD BE PULLED FROM TEH IMAGE ITSELF.
  public final int IMG_width = 320;
  public final int IMG_height = 240;
  public static Mat source = new Mat();

  //Don't USE 999.00
  //SET THESE TO NULL!!!
  // public static ArrayList<MatOfPoint> convexHullsOutput = new ArrayList<MatOfPoint>();
  public static Double centerX = null;
  public static Double width = null;
  public static Double height = null;
  public static Double bottom = null;
  public static Double top = null;
  public static Double widthPos = null;

  public static boolean centered = false;
  public static boolean aligned = false;
  public static Double diff = 0.0;
  public static char dir = 'n';
  public static final String NTserver = "frcvision.local";

  public static NetworkTableInstance convexHullsFinal = NetworkTableInstance.getDefault();
  public static NetworkTable convexHullsTable = null;

  // static NetworkTableEntry centerXEntry = convexHullsTable.getEntry("centerX");
  // NetworkTableEntry widthEntry = convexHullsTable.getEntry("width");
  // NetworkTableEntry heightEntry = convexHullsTable.getEntry("height");
  // NetworkTableEntry bottomEntry = convexHullsTable.getEntry("bottom");
  // NetworkTableEntry topEntry = convexHullsTable.getEntry("top");
  // NetworkTableEntry widthPosEntry = convexHullsTable.getEntry("widthPos");

  // NetworkTableInstance convexHullsFinal = NetworkTableInstance.getDefault();

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_LineFind = new LineFind();
    m_navX = new NavX();
    m_Drivetrain = new Drivetrain();
    m_Compressorsorus = new Compressorsorus();
    m_oi = new OI();
    // Robot.m_vision.init(320 , 240);
    // Robot.m_vision.view(Robot.source);

    // start clientside table
    // Utils.resetTables(convexHullsFinal, 2512);
    convexHullsTable= convexHullsFinal.getTable("White Line Tracking");
    Utils.dumpNetworkTable(convexHullsTable);
    
    NavX.navX.reset();
  }

  /***
   * 
   * DO MUCH BETTER. NO WRITE SO MUCH CODE.
   * 
   * 
   */

  public static Double getCenterX() {
    return Utils.getNetworkTableDouble(convexHullsTable, "centerX");
     /*
    // System.out.println(convexHullsFinal.isConnected());
    if (convexHullsFinal.isConnected()) {
      NetworkTableValue centerXValue = Robot.centerXEntry.getValue();
      // System.out.println("connected to " + convexHullsFinal.getConnections());
      // System.out.println("connection is valid: " + convexHullsFinal.isValid());
      // System.out.println("centerXValue info: " + convexHullsFinal.getEntryInfo("centerX", 1));
      if (centerXValue.getType() == NetworkTableType.kDouble) {
        System.out.println("detcted white line centerX at " + centerXValue.getDouble());
        centerX = centerXValue.getDouble();
      } else {
        System.out.println("centerXentry not a double; entry is a " + centerXValue.getType());
        centerX = 999.00;
      } 
    } 
     
    return centerX;
*/
  } 
  

  public static Double getWidth() {
    return Utils.getNetworkTableDouble(convexHullsTable, "width");
     /*
     System.out.println(convexHullsFinal.isConnected());
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
    */
  }

  public static Double getHeight() {
     return Utils.getNetworkTableDouble(convexHullsTable, "height");

     /*
     System.out.println(convexHullsFinal.isConnected());
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
    */
  }

  public static Double getBottom() {
     return Utils.getNetworkTableDouble(convexHullsTable, "bottom");
     /*
    
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
    */
  }

  public static Double getTop() {
    return Utils.getNetworkTableDouble(convexHullsTable, "top");
    /*
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
    */
  }

  public static Double getWidthPos() {
     return Utils.getNetworkTableDouble(convexHullsTable, "widthPos");
     /*
     System.out.println(convexHullsFinal.isConnected());
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
    */
  }


  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  public void updateNTData() {
    centerX = getCenterX();
    width = getWidth();
    bottom = getBottom();
    height =  getHeight();
    top = getTop();
    widthPos = getWidthPos();
  }

  @Override
  public void robotPeriodic() {
    for (ConnectionInfo conninfo : convexHullsFinal.getConnections()){
      System.out.println("Remoteid: "+conninfo.remote_id+", Remote IP: "+conninfo.remote_ip+", Port: "+conninfo.remote_port+", LastUpddate: "+conninfo.last_update);
    }
    try{
    updateNTData();
    m_Drivetrain.updateYPRData();
    SmartDashboard.putNumber("left clicks", m_Drivetrain.getLeftEncoderValue());
    SmartDashboard.putNumber("right clicks", m_Drivetrain.getRightEncoderValue());
    SmartDashboard.putNumber("left distance", m_Drivetrain.getLeftEncoderDistance());
    SmartDashboard.putNumber("right distance", m_Drivetrain.getRightEncoderDistance());
    SmartDashboard.putNumber("centerX", Robot.centerX  == null ? 999.00 : Robot.centerX);
    SmartDashboard.putNumber("width", Robot.width == null ? 999.00 : Robot.width);
    SmartDashboard.putNumber("height", Robot.height == null ? 999.00 : Robot.height);
    SmartDashboard.putNumber("Bottom", Robot.bottom == null ? 999.00 : Robot.bottom);
    SmartDashboard.putNumber("top", Robot.top == null ? 999.00 : Robot.top);
    SmartDashboard.putNumber("widthPos", Robot.widthPos == null ? 999.00 : Robot.widthPos);
    SmartDashboard.putNumber("navX yaw", m_navX.getYaw());
    SmartDashboard.putNumber("pidgine yaw", m_Drivetrain.getYaw());
    SmartDashboard.putNumber("Left Front", m_Drivetrain.leftFrontSpeed());
    SmartDashboard.putNumber("Left Rear", m_Drivetrain.leftRearSpeed());
    SmartDashboard.putNumber("Right Front", m_Drivetrain.rightFrontSpeed());
    SmartDashboard.putNumber("Right Rear", m_Drivetrain.rightRearSpeed());
    SmartDashboard.putNumber("Move COntrol", m_oi.getMove());
    }catch(Exception e){
      e.printStackTrace();
    }
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
