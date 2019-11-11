package frc.robot.commands;

import frc.robot.constants.Constants;

public class MaintainHeight extends RunToHeight {
    public MaintainHeight(Constants.LiftHeight height, boolean allowManualOverride) {
        super(height, allowManualOverride);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}