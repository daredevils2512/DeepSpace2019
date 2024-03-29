package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FlowerControlToggle extends Command {

    public FlowerControlToggle() {
        requires(Robot.m_flower);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.m_flower.toggleFlower();
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