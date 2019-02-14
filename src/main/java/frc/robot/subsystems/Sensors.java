package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Sensors extends Subsystem {

    private AnalogInput ballUltrasonic;
    private AnalogInput hatchUltrasonic;

    public Sensors() {

        ballUltrasonic = new AnalogInput(RobotMap.ballUltrasonicPort);
        hatchUltrasonic = new AnalogInput(RobotMap.hatchUltrasonicPort);

    }

    @Override
    public void initDefaultCommand() {

    }

    public double ballUltrasonicInches() {
        return this.ballUltrasonic.getAverageVoltage()/1024; // I dont know if these nums are real or not, will be changed
    }

    public double hatchUltrasonicInches() {
        return this.hatchUltrasonic.getAverageVoltage()/1024;
    }
}