package frc.robot.lib;

import edu.wpi.first.wpilibj.I2C;
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

    public void update(){
        this.colorSensor.read();
    }

    public double getDistance(){
        double distance = 0.0;
        //pick the correct distance sensor to return the value from and return it.
        //also perform the calcs on it
        double ultraDist = this.ultraSonicSensor.getDistInCm();
        double colorDist = this.colorSensor.getDistance();

        distance = (colorDist >= this.maxColorDist) ? ultraDist : colorDist;

        return distance - offset;
    }

    private double getColorDist() {
        return this.colorSensor.getDistance();
    }

    private double getUltraDist() {
        return this.ultraSonicSensor.getDistInCm();
    }
}