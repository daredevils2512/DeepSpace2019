package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Sensors extends Subsystem {

    private AnalogInput ballUltrasonic;
    private AnalogInput hatchUltrasonic;

    private double suppliedVoltage = 5.0;
    private double voltagePerCm = this.suppliedVoltage / 1024;

    public Sensors() {

        ballUltrasonic = new AnalogInput(RobotMap.ballUltrasonicPort);
        hatchUltrasonic = new AnalogInput(RobotMap.hatchUltrasonicPort);

    }

    @Override
    public void initDefaultCommand() {

    }

    public double ballUltrasonicInches() {
        return this.ballUltrasonic.getAverageVoltage() / this.voltagePerCm;
    }

    public double hatchUltrasonicInches() {
        return this.hatchUltrasonic.getAverageVoltage() / this.voltagePerCm;
    }
}