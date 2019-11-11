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
    private double m_infinitySpeed;
    private double m_inSpeed;

    private boolean m_ovveride;

    public RunCargoIntake(double infinitySpeed, double inSpeed, boolean ovveride) {
        requires(CargoIntake.getInstance());
        this.m_infinitySpeed = infinitySpeed;
        this.m_inSpeed = inSpeed;
        this.m_ovveride = ovveride;
    }

    @Override
        protected void initialize() {
    }

    @Override
    protected void execute() {
        CargoIntake.getInstance().setSpeed(this.m_infinitySpeed, this.m_inSpeed);
    }

    @Override
    protected boolean isFinished() {
        return (!this.m_ovveride && this.m_inSpeed < 0) ? CargoExtake.getBallOccupancy() : false;
        // return false;
    }

    @Override
    protected void end() {
        CargoIntake.getInstance().setSpeed(0.0, 0.0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
