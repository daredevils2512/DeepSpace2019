package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CMG_LiftCargo extends CommandGroup {

    public CMG_LiftCargo() {
        addSequential(new CargoFoldUp());
        // addSequential(new RunToHeight) 
        //TODO make run to height command for lift
    }
}