/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;
import frc.robot.constants.Constants;

import java.util.function.Supplier;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public final class Drivetrain extends Subsystem {
    private CANSparkMax rightSpark;
    private CANSparkMax leftSpark;
    private CANSparkMax leftRearSpark;
    private CANSparkMax rightRearSpark;
    private DifferentialDrive drivetrain;
    private Encoder leftEncoder;
    private Encoder rightEncoder;
    private DoubleSolenoid shifter;

    public static PigeonIMU gyro;
    private double[] yprData = {0.0, 0.0, 0.0}; //[Yaw, Pitch, Roll]

    private static final DoubleSolenoid.Value high = DoubleSolenoid.Value.kForward;
    private static final DoubleSolenoid.Value low = DoubleSolenoid.Value.kReverse;

    private final Supplier<Double> moveSupplier, turnSupplier;

    private boolean inverted = false;

    public Drivetrain(Supplier<Double> moveSupplier, Supplier<Double> turnSupplier) {
        this.moveSupplier = moveSupplier;
        this.turnSupplier = turnSupplier;

        leftSpark = new CANSparkMax(RobotMap.leftSparkID, MotorType.kBrushless);    
        rightSpark = new CANSparkMax(RobotMap.rightSparkID, MotorType.kBrushless);
        leftRearSpark = new CANSparkMax(RobotMap.leftRearSparkID, MotorType.kBrushless);
        rightRearSpark = new CANSparkMax(RobotMap.rightRearSparkID, MotorType.kBrushless);

        leftRearSpark.follow(leftSpark);
        rightRearSpark.follow(rightSpark);

        leftSpark.setIdleMode(IdleMode.kCoast);
        rightSpark.setIdleMode(IdleMode.kCoast);
        leftRearSpark.setIdleMode(IdleMode.kCoast);
        rightRearSpark.setIdleMode(IdleMode.kCoast);

        leftSpark.setOpenLoopRampRate(0.25);
        rightSpark.setOpenLoopRampRate(0.25);
        leftRearSpark.setOpenLoopRampRate(0.25);
        rightRearSpark.setOpenLoopRampRate(0.25);

        leftSpark.setSmartCurrentLimit(65, 10);
        rightSpark.setSmartCurrentLimit(65, 10);
        leftRearSpark.setSmartCurrentLimit(65, 10);
        rightRearSpark.setSmartCurrentLimit(65, 10);
            
        leftEncoder = new Encoder(RobotMap.leftEncoderChannelA, RobotMap.leftEncoderChannelB, false, CounterBase.EncodingType.k4X);
        rightEncoder = new Encoder(RobotMap.rightEncoderChannelA, RobotMap.rightEncoderChannelB, true, CounterBase.EncodingType.k4X);

        drivetrain = new DifferentialDrive(leftSpark, rightSpark);

        leftEncoder.setDistancePerPulse(RobotMap.driveEncoderDistancePerPulse);
        rightEncoder.setDistancePerPulse(RobotMap.driveEncoderDistancePerPulse);

        shifter = new DoubleSolenoid(RobotMap.shifterForwardChannel, RobotMap.shifterReverseChannel);
        shifter.set(Constants.Drivetrain.HIGH_GEAR_VALUE);

        gyro = new PigeonIMU(0);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive(this));
    }

    public double getMove() {
        return moveSupplier.get();
    }

    public double getTurn() {
        return turnSupplier.get();
    }

    public void leftSpeed(double speed) {
        leftSpark.set(speed);
    }

    public void rightSpeed(double speed) {
        rightSpark.set(speed);
    }

    public void arcadeDrive(double move, double turn) {
        if(inverted) {
            move = -move;
        }
        drivetrain.arcadeDrive(move, turn);
    }

    public void driveRobotTank(double leftSpeed, double rightSpeed) {
        if(inverted) {
            leftSpeed = -leftSpeed;
            rightSpeed = -rightSpeed;
        }
        drivetrain.tankDrive(leftSpeed, rightSpeed);
    }

    public double getLeftEncoderDistance() {
        return leftEncoder.getDistance();
    }

    public double getRightEncoderDistance() {
        return rightEncoder.getDistance();
    }

    public int getLeftEncoderValue() {
        return leftEncoder.get();
    }

    public int getRightEncoderValue() {
        return rightEncoder.get();
    }

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public double getAverageEncVal() {
        return (this.getLeftEncoderValue() + this.getRightEncoderValue()) / 2;
    }

    public double getAverageEncDist() {
        return (this.getLeftEncoderDistance() + this.getRightEncoderDistance()) / 2;
    }

    public boolean getLowGear() {
        return shifter.get() == Constants.Drivetrain.LOW_GEAR_VALUE;
    }

    public void setLowGear(boolean wantsLowGear) {
        shifter.set(wantsLowGear ? Constants.Drivetrain.LOW_GEAR_VALUE : Constants.Drivetrain.HIGH_GEAR_VALUE);
    }

    public void toggleLowGear() {
        setLowGear(!getLowGear());
    }

    public double leftFrontSpeed() {
        return this.leftSpark.get();
    }

    public double leftRearSpeed() {
        return this.leftRearSpark.get();
    }

    public double rightFrontSpeed() {
        return this.rightSpark.get();
    }

    public double rightRearSpeed() {
        return this.rightRearSpark.get();
    }

    public void updateYPRData() {
        gyro.getYawPitchRoll(this.yprData);
    }

    public double getNonCummulativeYaw() {
        return getYaw() % 360;
    }

    public double getYaw() {
        this.updateYPRData();
        return this.yprData[0];
    }

    public double getPitch() {
        this.updateYPRData();
        return this.yprData[1];
    }

    public double getRoll() {
        this.updateYPRData();
        return this.yprData[2];
    }

    public boolean getInverted() {
        return inverted;
    }

    public void setInverted(boolean wantsInverted) {
        inverted = wantsInverted;
    }

    public void toggleInverted() {
        setInverted(!inverted);
    }

    public void updateDashboard() {
        SmartDashboard.putBoolean("Low Gear", getLowGear());
        SmartDashboard.putBoolean("Inverted Driving", getInverted());

        SmartDashboard.putNumber("D1 Temp", leftSpark.getMotorTemperature());
        SmartDashboard.putNumber("D2 Temp", leftRearSpark.getMotorTemperature());
        SmartDashboard.putNumber("D3 Temp", rightSpark.getMotorTemperature());
        SmartDashboard.putNumber("D4 Temp", rightRearSpark.getMotorTemperature());

        // SmartDashboard.putNumber("D1 Out Current", leftSpark.getOutputCurrent());
        // SmartDashboard.putNumber("D2 Out Current", leftRearSpark.getOutputCurrent());
        // SmartDashboard.putNumber("D3 Out Current", rightSpark.getOutputCurrent());
        // SmartDashboard.putNumber("D4 Out Current", rightRearSpark.getOutputCurrent());

        // SmartDashboard.putNumber("D1 Set Speed", leftSpark.get());
        // SmartDashboard.putNumber("D2 Set Speed", leftRearSpark.get());
        // SmartDashboard.putNumber("D3 Set Speed", rightSpark.get());
        // SmartDashboard.putNumber("D4 Set Speed", rightRearSpark.get());
    }
}