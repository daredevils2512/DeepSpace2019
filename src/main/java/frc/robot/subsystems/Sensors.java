package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Sensors extends Subsystem {

    private AnalogInput ballUltrasonic;
    private AnalogInput hatchUltrasonic;

    private static final double suppliedVoltage = 5.0;
    private static final double voltagePerCm = suppliedVoltage / 1024;
    private static final double voltagePerIn = voltagePerCm * 2.54;

    public Sensors() {

        ballUltrasonic = new AnalogInput(RobotMap.ballUltrasonicPort);
        hatchUltrasonic = new AnalogInput(RobotMap.hatchUltrasonicPort);

    }

    @Override
    public void initDefaultCommand() {

    }

    public double ballUltrasonicInches() {
        return this.ballUltrasonic.getAverageVoltage() / voltagePerIn;
    }

    public double hatchUltrasonicInches() {
        return this.hatchUltrasonic.getAverageVoltage() / voltagePerIn;
    }
}