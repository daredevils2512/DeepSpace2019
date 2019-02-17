/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Sensors;

import edu.wpi.first.wpilibj.AnalogInput;
/**
 * Add your docs here.
 */
public class UltrasonicSensor {

    private int m_port;
    private double m_distInInchOffset, m_distInCmOffset, m_suppliedVoltage;

    private final double voltagePerCm = this.m_suppliedVoltage / 1024;
    private final double voltagePerIn = voltagePerCm * 2.54;

    private AnalogInput input;

    public UltrasonicSensor(int port, double distInInchOffset, double suppliedVoltage) {
        this.m_port = port;
        this.m_distInInchOffset = distInInchOffset;
        this.m_distInCmOffset = this.m_distInInchOffset * 2.54;
        this.m_suppliedVoltage = suppliedVoltage;

        this.input = new AnalogInput(this.m_port);
    }

    public double getAvgVoltage() {
        return this.input.getAverageVoltage();
    }

    public double getDistInCm() {
        return (this.getAvgVoltage() / this.voltagePerCm) - this.m_distInCmOffset;
    }

    public double getDistInInch() {
        return (this.getAvgVoltage() / this.voltagePerIn) - this.m_distInCmOffset;
    }
}
