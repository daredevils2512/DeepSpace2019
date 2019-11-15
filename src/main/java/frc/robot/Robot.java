/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilderImpl;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.lib.Limelight;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Drivetrain drivetrain;
    private Lift lift;
    private Compressorsorus compressor;
    private CargoIntake cargoIntake;
    private CargoExtake cargoExtake;
    private HatchIntake hatchIntake;

    private OI oi;

    public Limelight m_limelight;

    public static double slowify = 1.0;
    public static SendableChooser<Double> driveToWallChooser;
    SendableChooser<Double> slowifyChooser = new SendableChooser<>();

    public static PowerDistributionPanel m_PDP;
    public static SendableBuilder m_PDPBuilder;

    Command m_autonomousCommand;
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        driveToWallChooser = new SendableChooser<>();
        driveToWallChooser.addOption("6", 6.0);
        driveToWallChooser.setDefaultOption("12", 12.0);
        driveToWallChooser.addOption("24", 24.0);
        driveToWallChooser.addOption("36", 36.0);
        SmartDashboard.putData("driveToWall", driveToWallChooser);

        slowifyChooser.addOption("100%", 1.0);
        slowifyChooser.addOption("85%", 0.85);
        slowifyChooser.addOption("70%", 0.7);
        slowifyChooser.addOption("55%", 0.55);
        slowifyChooser.addOption("40%", 0.4);
        slowifyChooser.addOption("25%", 0.25);
        slowifyChooser.setDefaultOption("Default", 1.0);
        SmartDashboard.putData("Slowify", slowifyChooser);

        m_PDP = new PowerDistributionPanel();
        m_PDPBuilder = new SendableBuilderImpl();
        
        drivetrain = new Drivetrain(oi::getMove, oi::getTurn);
        lift = new Lift(oi::liftControl);
        compressor = new Compressorsorus();
        cargoIntake = new CargoIntake();
        cargoExtake = new CargoExtake();
        hatchIntake = new HatchIntake();
        m_limelight = new Limelight();

        oi = new OI(drivetrain, lift, compressor, cargoIntake, cargoExtake, hatchIntake);
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
        Timer t = new Timer();
        t.start();
        drivetrain.updateDashboard();
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

        SmartDashboard.putNumber("lift control", oi.liftControl().doubleValue());
        SmartDashboard.putNumber("lift pos", lift.getLiftEncoderPosition());
        SmartDashboard.putNumber("lift hieght", lift.getLiftHeight());
        SmartDashboard.putBoolean("lift switch", lift.getLimitSwitchBottom());

        SmartDashboard.putBoolean("Ball lmit switch", CargoExtake.getBallOccupancy());
        // SmartDashboard.putBoolean("Driver vision enabled", m_drivervision.getIsEnabled());
        SmartDashboard.putBoolean("Low Gear", drivetrain.getLowGear());
        // System.out.println(" lift pos: " + m_lift.getLiftHeight());

        slowify = slowifyChooser.getSelected() == null ? 1.0 : slowifyChooser.getSelected();

        // SmartDashboard.putNumber("PDP 01", m_PDP.getCurrent(1));
        // SmartDashboard.putNumber("PDP 00", m_PDP.getCurrent(0));
        // SmartDashboard.putNumber("PDP 13", m_PDP.getCurrent(13));
        // SmartDashboard.putNumber("PDP 14", m_PDP.getCurrent(14));

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

        SmartDashboard.putBoolean("Compressor on", compressor.isOn());
        t.stop();
        if (t.get() >= 0.015)
            System.out.println("robotPeriodic TooK: "+t.get());
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
        lift.resetLiftEncoder();
        // m_autonomousCommand = m_chooser.getSelected();
        compressor.enable();
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

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        hatchIntake.setExtended(false);
        lift.resetLiftEncoder();
        compressor.enable();
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        drivetrain.resetEncoders();
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

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