package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ExtakeCargo extends CommandGroup {
    public ExtakeCargo() {
        addSequential(new RunToBottom(false));
        addParallel(new RunCargoIntake(1.0, 1.0, true));
        addParallel(new RunCargoExtake(1.0, true));
    }
}