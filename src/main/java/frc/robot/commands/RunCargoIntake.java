/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CargoExtake;
import frc.robot.subsystems.CargoIntake;

public final class RunCargoIntake extends Command {
    protected CargoIntake cargoIntake;
    protected double m_infinitySpeed;
    protected double m_inSpeed;
    protected boolean m_override;

    /**
     * Run cargo intake
     * @param infinitySpeed speed to turn the bands that center cargo (negative values move towards center, positive values move away from center)
     * @param inSpeed speed to run the bands that intake/extake cargo (negative values intake, positive values extake)
     * @param override override cargo limit switch (when intaking)
     */
    public RunCargoIntake(CargoIntake cargoIntake, double infinitySpeed, double inSpeed, boolean override) {
        requires(cargoIntake);
        this.cargoIntake= cargoIntake;
        this.m_infinitySpeed = infinitySpeed;
        this.m_inSpeed = inSpeed;
        this.m_override = override;
    }

    @Override
    protected void execute() {
        cargoIntake.setSpeed(m_infinitySpeed, m_inSpeed);
    }

    @Override
    protected boolean isFinished() {
        // Stop if the cargo intake is intaking, the cargo extake contains cargo, and override is off
        boolean result = false;
        if(isIntaking() && CargoExtake.getBallOccupancy() && !m_override) {
            result = true;
        }
        return result;
    }

    @Override
    protected void end() {
        cargoIntake.setSpeed(0.0, 0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }

    private boolean isIntaking() {
        return m_inSpeed < 0.0;
    }
}
