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
import frc.robot.lib.DistanceSensor;

import com.kauailabs.navx.frc.AHRS;
// import sun.nio.ch.Net;

import org.opencv.core.*;
// import java.util.ArrayList;
// import frc.robot.GripWhiteLine;

import edu.wpi.first.wpilibj.AnalogGyro;
import frc.robot.vision.*;

import frc.robot.commands.*;
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

  public static CargoIntake m_cargoIntake;
  public static Drivetrain m_Drivetrain;
  // public static Spotlight m_Spotlight = new Spotlight();
  public static Compressorsorus m_Compressorsorus;
  public static OI m_oi;

  public static NavX m_navX;
  public static Boolean dv0Online = false;
  public static Boolean dv1Online = false;

  // public static Vision m_vision = new Vision();
  //THESE SHOULD BE PULLED FROM TEH IMAGE ITSELF.
  public final int IMG_width = 320;
  public final int IMG_height = 240;
  public static Mat source = new Mat();

  public static Double cameraSize;

  //Don't USE 999.00
  //SET THESE TO NULL!!!
  // public static ArrayList<MatOfPoint> convexHullsOutput = new ArrayList<MatOfPoint>();

  public static boolean log = false;
  public static boolean aligned = false;
  public static Double diff = 0.0;
  public static char dir = 'n';
  public static final String NTserver = "frcvision.local";

  public static Lift m_lift;
  public static BallXtake m_ballXtake;
  public static Flower m_flower;

  //public static ColorSensor ballCs, hatchCs;
  //public static UltrasonicSensor ballUltra, hatchUltra;

  public static DistanceSensor m_ballDistanceSensor, m_hatchDistanceSensor;

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
    Utils.convexHullsFinal.startServer();
    Utils.convexHullsTable = Utils.convexHullsFinal.getTable("White Line Tracking");
    Utils.ballTable = Utils.convexHullsTable.getSubTable("Ball Table");
    Utils.hatchTable = Utils.convexHullsTable.getSubTable("Hatch Table");
    Utils.dumpNetworkTable(Utils.convexHullsTable);
    
    NavX.navX.reset();
    Robot.m_Drivetrain.resetYaw();
    // Utils.startDriverVision(0, 320, 240, dv0Online);
    m_ballDistanceSensor = new DistanceSensor(RobotMap.ballUltrasonicPort, RobotMap.ballColorPort, RobotMap.ballSensorsOffsetFromFrame);
    m_hatchDistanceSensor = new DistanceSensor(RobotMap.hatchUltrasonicPort, RobotMap.hatchColorPort, RobotMap.hatchSensorsOffsetFromFrame);

    m_Drivetrain = new Drivetrain();
    m_Compressorsorus = new Compressorsorus();
    m_lift = new Lift();
    m_cargoIntake = new CargoIntake();
    m_ballXtake = new BallXtake();
    m_flower = new Flower();
    m_oi = new OI();
    // m_chooser.setDefaultOption("Default Auto", new LiftCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);

  }

    /*ballCs = new ColorSensor(RobotMap.ballColorPort, RobotMap.ballSensorsOffsetFromFrame);
    hatchCs = new ColorSensor(RobotMap.hatchColorPort, RobotMap.hatchSensorsOffsetFromFrame);

    ballUltra = new UltrasonicSensor(RobotMap.ballUltrasonicPort, RobotMap.ballSensorsOffsetFromFrame, RobotMap.suppliedUltraVoltage);
    hatchUltra = new UltrasonicSensor(RobotMap.hatchUltrasonicPort, RobotMap.hatchSensorsOffsetFromFrame, RobotMap.suppliedUltraVoltage);
    */


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
    if (log) {
      Utils.getConnInfo();
    }
    try{
    Utils.updateNTData();
    m_Drivetrain.updateYPRData();
    /*
    ballCs.read();
    hatchCs.read();

    SmartDashboard.putNumberArray("Robo Proximity", ballCs.proxData);
    SmartDashboard.putNumberArray("MXP Proximity", hatchCs.proxData);

    SmartDashboard.putNumber("Hatch Voltage", hatchUltra.getAvgVoltage());
    SmartDashboard.putNumber("Ball Voltage", ballUltra.getAvgVoltage());

    SmartDashboard.putNumber("Hatch Ultra", hatchUltra.getDistInInch());
    SmartDashboard.putNumber("Ball Ultra", ballUltra.getDistInInch());
    */

    m_hatchDistanceSensor.update();
    m_ballDistanceSensor.update();

    SmartDashboard.putNumber("Hatch Distance", m_hatchDistanceSensor.getDistance());
    SmartDashboard.putNumber("Ball Distance", m_ballDistanceSensor.getDistance());

    SmartDashboard.putNumber("lift control", m_oi.liftControl().doubleValue());
    SmartDashboard.putNumber("lift pos", m_lift.getLiftPosition());
    SmartDashboard.putNumber("lift hieght", m_lift.getLiftHeight());
    // System.out.println(" lift pos: " + m_lift.getLiftHeight());

    SmartDashboard.putNumber("left clicks", m_Drivetrain.getLeftEncoderValue());
    SmartDashboard.putNumber("right clicks", m_Drivetrain.getRightEncoderValue());
    SmartDashboard.putNumber("left distance", m_Drivetrain.getLeftEncoderDistance());
    SmartDashboard.putNumber("right distance", m_Drivetrain.getRightEncoderDistance());
    SmartDashboard.putNumber("centerXBall", LineFind.centerXBall  == null ? 999.00 : LineFind.centerXBall);
    SmartDashboard.putNumber("widthBall", LineFind.widthBall == null ? 999.00 : LineFind.widthBall);
    SmartDashboard.putNumber("heightBall", LineFind.heightBall == null ? 999.00 : LineFind.heightBall);
    SmartDashboard.putNumber("BottomBall", LineFind.bottomBall == null ? 999.00 : LineFind.bottomBall);
    SmartDashboard.putNumber("topBall", LineFind.topBall == null ? 999.00 : LineFind.topBall);
    SmartDashboard.putNumber("centerYBall", LineFind.centerYBall == null ? 999.00 : LineFind.centerYBall);
    SmartDashboard.putNumber("areaBall", LineFind.areaBall == null ? 999.00 : LineFind.areaBall);
    SmartDashboard.putNumber("widthPosBall", LineFind.widthPosBall == null ? 999.00 : LineFind.widthPosBall);
    SmartDashboard.putNumber("centerXHatch", LineFind.centerXHatch  == null ? 999.00 : LineFind.centerXHatch);
    SmartDashboard.putNumber("widthHatch", LineFind.widthHatch == null ? 999.00 : LineFind.widthHatch);
    SmartDashboard.putNumber("heightHatch", LineFind.heightHatch == null ? 999.00 : LineFind.heightHatch);
    SmartDashboard.putNumber("BottomHatch", LineFind.bottomHatch == null ? 999.00 : LineFind.bottomHatch);
    SmartDashboard.putNumber("topHatch", LineFind.topHatch == null ? 999.00 : LineFind.topHatch);
    SmartDashboard.putNumber("centerYHatch", LineFind.centerYHatch == null ? 999.00 : LineFind.centerYHatch);
    SmartDashboard.putNumber("areaHatch", LineFind.areaHatch == null ? 999.00 : LineFind.areaHatch);
    SmartDashboard.putNumber("widthPosHatch", LineFind.widthPosHatch == null ? 999.00 : LineFind.widthPosHatch);
    SmartDashboard.putNumber("navX yaw", m_navX.getYaw());
    SmartDashboard.putNumber("pidgine compass", m_Drivetrain.getNonCummulativeYaw());
    SmartDashboard.putNumber("Left Front", m_Drivetrain.leftFrontSpeed());
    SmartDashboard.putNumber("Left Rear", m_Drivetrain.leftRearSpeed());
    SmartDashboard.putNumber("Right Front", m_Drivetrain.rightFrontSpeed());
    SmartDashboard.putNumber("Right Rear", m_Drivetrain.rightRearSpeed());
    SmartDashboard.putNumber("Move COntrol", m_oi.getMove());

  
    
    SmartDashboard.putNumber("Yaw", m_Drivetrain.getYaw());
    SmartDashboard.putNumber("Pitch", m_Drivetrain.getPitch());
    SmartDashboard.putNumber("Roll", m_Drivetrain.getRoll());

    SmartDashboard.putBoolean("Lift Switch", m_lift.getLimitSwitchBottom());
    SmartDashboard.putBoolean("Extake Swqitch", m_ballXtake.getBallOccupancy());
    
    } catch (Exception e) {
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
    m_lift.resetEncoder();
    m_Compressorsorus.compressorOn();
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
}