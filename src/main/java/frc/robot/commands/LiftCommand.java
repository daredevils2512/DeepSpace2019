/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Lift;

/**
 * An example command.  You can replace me with your own command.
 */
public abstract class LiftCommand extends Command {
    protected Lift lift;
    protected Supplier<Double> liftControlDirection = null;

    public LiftCommand(Lift lift, Supplier<Double> liftControlDirection) {
    requires(lift);
    this.lift = lift;
    this.liftControlDirection = liftControlDirection;
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
        this.end();
    }
}
