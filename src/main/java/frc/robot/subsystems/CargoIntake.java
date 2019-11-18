/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public final class CargoIntake extends Subsystem {
    private final WPI_TalonSRX infinityMotor;
    private final WPI_TalonSRX inMotor;
    private final DoubleSolenoid extender;

    private final Value retractedValue = Value.kForward;
    private final Value extendedValue = Value.kReverse;

    public CargoIntake() {
        infinityMotor = new WPI_TalonSRX(RobotMap.cargoInfinityPort);
        inMotor = new WPI_TalonSRX(RobotMap.cargoInMotorPort);
        extender = new DoubleSolenoid(RobotMap.cargoUpDownAPort, RobotMap.cargoUpDownBPort);
        extender.set(retractedValue);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(null);
    }

    /**
     * Get whether the cargo intake is extended
     * @return true if extended, false if retracted
     */
    public boolean getExtended() {
        return extender.get() == extendedValue;
    }

    /**
     * Set whether the cargo intake is extended
     * @param extended true to extend, false to retract
     */
    public void setExtended(boolean extended) {
        extender.set(extended ? extendedValue : retractedValue);
    }

    /**
     * Extend the cargo intake if retracted and vice versa
     */
    public void toggleExtended() {
        setExtended(!getExtended());
    }

    /**
     * Run cargo intake
     * @param infinitySpeed speed to turn the bands that center cargo (negative values move towards center, positive values move away from center)
     * @param inSpeed speed to run the bands that intake/extake cargo (negative values intake, positive values extake)
     */
    public void setSpeed(double infinitySpeed, double inSpeed) {
        if(extender.get() == extendedValue) {
            infinityMotor.set(infinitySpeed);
            inMotor.set(inSpeed);
        }
    }

}