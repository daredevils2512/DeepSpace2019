/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.lib;

import edu.wpi.first.wpilibj.AnalogInput;

public class UltrasonicSensor {
    private int m_port;
    private double m_suppliedVoltage, voltagePerCm, voltagePerIn;

    private AnalogInput input;

    public UltrasonicSensor(int port, double suppliedVoltage) {
        this.m_port = port;
        this.m_suppliedVoltage = suppliedVoltage;

        this.voltagePerCm = this.m_suppliedVoltage / 1024;
        this.voltagePerIn = this.voltagePerCm * 2.54;
        
        this.input = new AnalogInput(this.m_port);
 
        System.out.print("V/In: " + this.voltagePerIn + ", V/Cm: " + this.voltagePerCm + "\n");
    }

    public double getAvgVoltage() {
        return this.input.getAverageVoltage();
    }

    public double getDistInCm() {
        return (this.getAvgVoltage() / this.voltagePerIn);
    }

    public double getDistInInch() {
        return (this.getAvgVoltage() / this.voltagePerCm);
    }
}