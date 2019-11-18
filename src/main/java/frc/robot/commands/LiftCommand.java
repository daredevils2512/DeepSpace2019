/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Lift;

public abstract class LiftCommand extends Command {
    protected final Lift lift;

    public LiftCommand(Lift lift) {
        requires(lift);
        this.lift = lift;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        lift.setSpeed(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
