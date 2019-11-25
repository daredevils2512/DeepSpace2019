/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * The subsystem responsible for grabbing hatch panels from  feeder stations and placing them on the cargo ship or rockets
 */
public final class HatchIntake extends Subsystem {
    private final DoubleSolenoid extender;
    private final DoubleSolenoid opener;
    private final Value retractedValue = Value.kReverse;
    private final Value extendedValue = Value.kForward;
    private final Value closedValue = Value.kReverse;
    private final Value openValue = Value.kForward;

    public HatchIntake() {
        opener = new DoubleSolenoid(RobotMap.flowerSlideForwardChannel, RobotMap.flowerSlideReverseChannel);
        extender = new DoubleSolenoid(RobotMap.flowerSolenoidForwardChannel, RobotMap.flowerSolenoidReverseChannel);

        setExtended(false);
        setOpen(false);
    }

    @Override
    protected void initDefaultCommand() {
        
    }

    /**
     * Get whether the hatch intake is extended
     * @return true if extended, false if retracted
     */
    public boolean getExtended() {
        // Return true if the solenoid is set to off
        return extender.get() != retractedValue;
    }

    /**
     * Set whether the hatch intake is extended
     * @param extended true to extend, false to retract
     */
    public void setExtended(boolean extended) {
        // If the solenoid is set to off, retract
        extender.set(extended ? extendedValue : retractedValue);
    }

    /**
     * Get whether the hatch intake is open (securing hatch panel)
     * @return true if open, false if closed
     */
    public boolean getOpen() {
        // Return true if the solenoid is set to off
        return opener.get() != closedValue;
    }

    public void setOpen(boolean open) {
        // If the solenoid is set to off, close
        opener.set(open ? openValue : closedValue);
    }

    /**
     * Extend the hatch intake if it is retracted and vice versa
     */
    public void toggleExtended() {
        setExtended(!getExtended());
    }

    /**
     * Open the hatch intake if it is closed and vice versa
     */
    public void toggleOpen() {
        setOpen(!getOpen());
    }
}