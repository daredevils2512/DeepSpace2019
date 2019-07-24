package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.vision.Utils;

public class DriverVisionStart extends Command {

    int m_port;
    int m_imageWidth;
    int m_imageHeight;
    Boolean m_dvBoolean;

    public DriverVisionStart(int port, int imageWidth, int imageHeight, Boolean dvBoolean) {
        m_port = port;
        m_imageWidth = imageWidth;
        m_imageHeight = imageHeight;
        m_dvBoolean = dvBoolean;
    }

    @Override
    protected void execute() {
        Utils.startDriverVision(m_port, m_imageWidth, m_imageHeight, m_dvBoolean);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}