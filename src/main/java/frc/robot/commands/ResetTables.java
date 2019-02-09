package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.networktables.NetworkTableInstance;

import frc.robot.Robot;

public class ResetTables extends Command {

    NetworkTableInstance m_inst;
    String m_server;

    public ResetTables(NetworkTableInstance inst, String server) {
        m_inst = inst;
        m_server = server;
        requires(Robot.m_LineFind);
    }

    @Override
    protected void execute() {
        Robot.m_LineFind.resetTable(m_inst, m_server);
    }

    @Override
    protected boolean isFinished() {
      return true;
    }
}