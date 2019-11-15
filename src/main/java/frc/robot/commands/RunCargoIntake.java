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

public class RunCargoIntake extends Command {
    private CargoIntake cargoIntake;
    private double m_infinitySpeed;
    private double m_inSpeed;

    private boolean m_override;

    /**
     * Run cargo intake
     * @param infinitySpeed speed to turn the bands that center cargo (negative values move towards center, positive values move away from center)
     * @param inSpeed speed to run the bands that intake/extake cargo (negative values intake, positive values extake)
     * @param override override cargo limit switch
     */
    public RunCargoIntake(CargoIntake cargoIntake, double infinitySpeed, double inSpeed, boolean override) {
        requires(cargoIntake);
        this.cargoIntake= cargoIntake;
        this.m_infinitySpeed = infinitySpeed;
        this.m_inSpeed = inSpeed;
        this.m_override = override;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        cargoIntake.setSpeed(m_infinitySpeed, m_inSpeed);
    }

    @Override
    protected boolean isFinished() {
        return (!m_override && m_inSpeed < 0) ? CargoExtake.getBallOccupancy() : false;
    }

    @Override
    protected void end() {
        cargoIntake.setSpeed(0.0, 0.0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
