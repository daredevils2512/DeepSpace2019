/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;

public final class Compressorsorus extends Subsystem {
    public Compressor sorus;

    private static Compressorsorus instance;

    private Compressorsorus() {
        sorus = new Compressor();
    }

    @Override
    public void initDefaultCommand() {
        
    }

    public static Compressorsorus getInstance() {
        if(instance == null) {
            instance = new Compressorsorus();
        }
        return instance;
    }

    public boolean isEnabled() {
        return sorus.getClosedLoopControl();
    }

    public boolean isOn() {
        return sorus.enabled();
    }

    public boolean isPressureLow() {
        return sorus.getPressureSwitchValue();
    }

    public void enable() {
        sorus.setClosedLoopControl(true);
    }

    public void disable() {
        sorus.setClosedLoopControl(false);
    }

    public void toggle() {
        if(isEnabled()) {
            disable();
            System.out.println("Compressor disabled");
        } else {
            enable();
            System.out.println("Compressor enabled");
        }
    }
}