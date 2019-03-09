package frc.robot.lib;

import java.util.Date;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.RobotMap;

/**
 * This class contains 1 acoustic and 1 color sensor 
 * ti also contains 1 method to get the distance
 */
public class DistanceSensor {

    private ColorSensor colorSensor;
    private UltrasonicSensor ultraSonicSensor;

    //offset
    private double offset = 0.0;

    private double maxColorDist = 30 / 2.54; // 25cm to in    
    
    public DistanceSensor(int ultraSonicSensorAnalogInputPort, I2C.Port colorSensorI2cPort, double offset){
        this.colorSensor = new ColorSensor(colorSensorI2cPort);
        this.ultraSonicSensor = new UltrasonicSensor(ultraSonicSensorAnalogInputPort, RobotMap.suppliedUltraVoltage);
        this.offset = offset;
    }

    private void update(){
        this.colorSensor.read();
    }

    public double getDistance(){
        double distance = 0.0;
        //pick the correct distance sensor to return the value from and return it.
        //also perform the calcs on it
        this.update();
        double ultraDist = this.ultraSonicSensor.getDistInInch();
        double colorDist = this.colorSensor.getDistance();

        distance = (colorDist >= this.maxColorDist) ? ultraDist : colorDist;
      

        return distance - offset;
    }

    public double getColorDist() {
        return this.colorSensor.getDistance();
    }

    public double getUltraDist() {
        return this.ultraSonicSensor.getDistInInch();
    }

    public double getUltraVoltage(){
        return this.ultraSonicSensor.getAvgVoltage();
    }
}