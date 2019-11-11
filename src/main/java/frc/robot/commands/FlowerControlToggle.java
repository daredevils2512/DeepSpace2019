package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchIntake;

public class FlowerControlToggle extends Command {

    public FlowerControlToggle() {
        requires(HatchIntake.getInstance());
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}