package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AlignChooserChooser extends Command {

    public AlignChooserChooser() {
    }

    @Override
    protected void execute() {
        Robot.alignChooser.getSelected();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}