package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeCargo extends CommandGroup {
    public IntakeCargo() {
        addParallel(new RunCargoIntake(-1.0, -1.0, true));
        addParallel(new RunCargoExtake(-1.0, true));
    }
}