/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilderImpl;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

import frc.robot.subsystems.*;
import frc.robot.commands.CMG_LiftCargo;
import frc.robot.lib.DistanceSensor;

// import com.kauailabs.navx.frc.AHRS;
// import sun.nio.ch.Net;

import org.opencv.core.*;
// import java.util.ArrayList;
// import frc.robot.GripWhiteLine;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static int teamNumber = 2512;

  PowerDistributionPanel pdp = new PowerDistributionPanel();
  SendableBuilder pdpBuilder = new SendableBuilderImpl();

  // public static GripWhiteLine m_grippipeline = new GripWhiteLine();
  public static LineFind m_LineFind;

  public static CargoIntake m_cargoIntake;
  public static Drivetrain m_Drivetrain;
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

  public static SendableChooser<Double> driveToWallChooser;
  
  public static PowerDistributionPanel m_PDP;
  public static SendableBuilder m_PDPBuilder;

  //public static ColorSensor ballCs, hatchCs;
  //public static UltrasonicSensor ballUltra, hatchUltra;

  public static DistanceSensor m_ballDistanceSensor, m_hatchDistanceSensor;

  Command m_autonomousCommand;
  public static SendableChooser<Command> alignChooser = new SendableChooser<Command>();
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

    driveToWallChooser = new SendableChooser<>();
    driveToWallChooser.addOption("6", 6.0);
    driveToWallChooser.setDefaultOption("12", 12.0);
    driveToWallChooser.addOption("24", 24.0);
    driveToWallChooser.addOption("36", 36.0);
    SmartDashboard.putData("driveToWall", driveToWallChooser);
    
    // m_PDP = new PowerDistributionPanel();
    // m_PDPBuilder = new SendableBuilderImpl();
    // m_chooser.setDefaultOption("Default Auto", new LiftCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    
    // ALWAYS LAST
    m_oi = new OI();

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

    Timer t = new Timer();
    t.start();
    m_Drivetrain.updateDashboard();
    // m_hatchDistanceSensor.update();
    // m_ballDistanceSensor.update();

    // SmartDashboard.putNumber("Hatch Distance", m_hatchDistanceSensor.getDistance());
    // SmartDashboard.putNumber("Hatch ultra volt", m_hatchDistanceSensor.getUltraVoltage());
    // SmartDashboard.putNumber("Hatch Ultra", m_hatchDistanceSensor.getUltraDist());
    // SmartDashboard.putNumber("Hatch Color", m_hatchDistanceSensor.getColorDist());

    // SmartDashboard.putNumber("ball ultra volt", m_ballDistanceSensor.getUltraVoltage());
    // SmartDashboard.putNumber("Ball Ultra", m_ballDistanceSensor.getUltraDist());
    // SmartDashboard.putNumber("Ball Color", m_ballDistanceSensor.getColorDist());
    // SmartDashboard.putNumber("Ball Distance", m_ballDistanceSensor.getDistance());

    // SmartDashboard.putNumber("lift control", m_oi.liftControl().doubleValue());
    SmartDashboard.putNumber("lift pos", m_lift.getLiftPosition());
    SmartDashboard.putNumber("lift hieght", m_lift.getLiftHeight());
    SmartDashboard.putBoolean("lift switch", m_lift.getLimitSwitchBottom());

    SmartDashboard.putBoolean("Ball lmit switch", m_ballXtake.getBallOccupancy());

    // SmartDashboard.putBoolean("High Gear", m_Drivetrain.getHighState());
    // System.out.println(" lift pos: " + m_lift.getLiftHeight());

    // SmartDashboard.putNumber("PDP 01", m_PDP.getCurrent(1));
    // SmartDashboard.putNumber("PDP 00", m_PDP.getCurrent(0));
    // SmartDashboard.putNumber("PDP 13", m_PDP.getCurrent(13));
    // SmartDashboard.putNumber("PDP 14", m_PDP.getCurrent(14));

    SmartDashboard.putBoolean("A Button State", m_oi.aButton.get());

    /*
    x
    */

    // SmartDashboard.putNumber("left clicks", m_Drivetrain.getLeftEncoderValue());
    // SmartDashboard.putNumber("right clicks", m_Drivetrain.getRightEncoderValue());
    // SmartDashboard.putNumber("left distance", m_Drivetrain.getLeftEncoderDistance());
    // SmartDashboard.putNumber("right distance", m_Drivetrain.getRightEncoderDistance());

    // SmartDashboard.putNumber("Left Front", m_Drivetrain.leftFrontSpeed());
    // SmartDashboard.putNumber("Left Rear", m_Drivetrain.leftRearSpeed());
    // SmartDashboard.putNumber("Right Front", m_Drivetrain.rightFrontSpeed());
    // SmartDashboard.putNumber("Right Rear", m_Drivetrain.rightRearSpeed());
    // SmartDashboard.putNumber("Move COntrol", m_oi.getMove());
    
    // SmartDashboard.putNumber("Yaw", m_Drivetrain.getYaw());
    // SmartDashboard.putNumber("Pitch", m_Drivetrain.getPitch());
    // SmartDashboard.putNumber("Roll", m_Drivetrain.getRoll());

    SmartDashboard.putBoolean("Compressor on", m_Compressorsorus.isOn());
    t.stop();
    if (t.get() >= 0.015)
     System.out.println("robotPeriodic TooK: "+t.get());
  
    }

    SmartDashboard.putData("Alignment Control", alignChooser);

    
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
    m_lift.resetLiftEncoder();
    // m_autonomousCommand = m_chooser.getSelected();
    m_Compressorsorus.compressorOn();
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
    m_flower.slideIn();
    m_lift.resetLiftEncoder();
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
    Timer t = new Timer();
    t.start();
    Scheduler.getInstance().run();
    t.stop();
    if (t.get() >= 0.015){
      System.out.println("teleopPerodic (scheduler.run took) "+t.get());
    }
  }
  
}