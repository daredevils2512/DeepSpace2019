/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;

public final class Compressorsorus extends Subsystem {
    private Compressor sorus;

    public Compressorsorus() {
        sorus = new Compressor();
    }

    @Override
    public void initDefaultCommand() {
        
    }

    public boolean isEnabled() {
        return sorus.enabled();
    }

    public boolean isPressureLow() {
        return sorus.getPressureSwitchValue();
    }

    public boolean getClosedLoopControl() {
        return sorus.getClosedLoopControl();
    }

    public void setClosedLoopControl(boolean wantsClosedLoopControl) {
        sorus.setClosedLoopControl(wantsClosedLoopControl);
    }

    public void toggle() {
        if(getClosedLoopControl()) {
            setClosedLoopControl(false);
        } else {
            setClosedLoopControl(true);
        }
    }

    public void updateDashboard() {
        SmartDashboard.putBoolean("Compressor Low", isPressureLow());
        SmartDashboard.putBoolean("Compressor in Closed Loop Control", getClosedLoopControl());
        SmartDashboard.putBoolean("Compressor Enabled", isEnabled());
    }
}