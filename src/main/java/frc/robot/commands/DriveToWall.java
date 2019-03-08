package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.constants.Constants.DistanceSensorSide;

public class DriveToWall extends Command {

    private double m_distance;
    private double currentDist;
    private DistanceSensorSide m_sensor;

    public DriveToWall(double distance, DistanceSensorSide sensor) {
        requires(Robot.m_Drivetrain);
        m_distance = distance;
        m_sensor = sensor;

    }

    @Override
    public void execute() {
      // 0% power at 0in
      // 100% power at 24in
      switch (this.m_sensor) {
        case BALL:
          this.currentDist = Robot.m_ballDistanceSensor.getDistance();
          break;
        case HATCH:
          this.currentDist = Robot.m_hatchDistanceSensor.getDistance();
      }
      double diff = this.currentDist - this.m_distance;
      double speed;

        if (diff > 0) {
          speed = Math.min(1.0, (diff / 24));
            Robot.m_Drivetrain.arcadeDrive(-speed, 0); //TODO test -toby said it should be negative on atlas at least
        } else {
            Robot.m_Drivetrain.arcadeDrive(0, 0);
        }
    }

    public boolean isFinished() {
        return true;
    }
}