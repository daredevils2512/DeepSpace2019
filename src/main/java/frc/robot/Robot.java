/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilderImpl;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private PowerDistributionPanel pdp;
    private SendableBuilder pdpBuilder;

    private Compressorsorus compressor;
    private Drivetrain drivetrain;
    private Lift lift;
    private CargoExtake cargoExtake;
    private CargoIntake cargoIntake;
    private HatchIntake hatchIntake;

    private OI oi;

    private static SendableChooser<Double> slowifyChooser;
    private static SendableChooser<Double> driveToWallChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        driveToWallChooser = new SendableChooser<Double>();
        driveToWallChooser.addOption("6", 6.0);
        driveToWallChooser.setDefaultOption("12", 12.0);
        driveToWallChooser.addOption("24", 24.0);
        driveToWallChooser.addOption("36", 36.0);
        SmartDashboard.putData("driveToWall", driveToWallChooser);

        slowifyChooser = new SendableChooser<Double>();
        slowifyChooser.setDefaultOption("100%", 1.0);
        slowifyChooser.addOption("85%", 0.85);
        slowifyChooser.addOption("70%", 0.7);
        slowifyChooser.addOption("55%", 0.55);
        slowifyChooser.addOption("40%", 0.4);
        slowifyChooser.addOption("25%", 0.25);
        SmartDashboard.putData("Slowify", slowifyChooser);

        pdp = new PowerDistributionPanel();
        pdpBuilder = new SendableBuilderImpl();
        
        oi = new OI();
        compressor = new Compressorsorus();
        drivetrain = new Drivetrain(oi::getMove, oi::getTurn);
        lift = new Lift(oi::liftControl);
        cargoExtake = new CargoExtake();
        cargoIntake = new CargoIntake();
        hatchIntake = new HatchIntake();

        oi.initCommands(compressor, drivetrain, lift, cargoExtake, cargoIntake, hatchIntake);
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
        lift.updateDashboard();

        t.stop();
        if (t.get() >= 0.015)
            System.out.println("robotPeriodic Took: "+t.get());
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
        lift.resetEncoder();
        compressor.setClosedLoopControl(true);
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        drivetrain.resetEncoders();
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

    public static double getSlowify() {
        Double slowify = slowifyChooser.getSelected();
        if(slowify == null) {
            slowify = 1.0;
        }
        return slowify.doubleValue();
    }

    public static double getTargetDriveToWallDistance() {
        Double targetDriveToWallDistance = driveToWallChooser.getSelected();
        if(targetDriveToWallDistance == null) {
            targetDriveToWallDistance = 12.0;
        }
        return targetDriveToWallDistance.doubleValue();
    }
}