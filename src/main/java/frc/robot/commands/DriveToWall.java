package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
<<<<<<< HEAD
import frc.robot.lib.DistanceSensor;
import frc.robot.lib.SpeedRamp;
=======
import frc.robot.constants.Constants.DistanceSensorSide;
>>>>>>> vision

public class DriveToWall extends Command {

    private double m_distToWall;
    private double tolerance = 4;
    private double defaultSpeed = 1;
    private DistanceSensor m_sensor;

    public DriveToWall(DistanceSensor sensor) {
        requires(Robot.m_Drivetrain);
        m_distToWall = Robot.driveToWallChooser.getSelected();
        m_sensor = sensor;
    }

    @Override
    public void execute() {
<<<<<<< HEAD
        double dist = m_sensor.getDistance();
        Robot.m_Drivetrain.arcadeDrive(-SpeedRamp.speedRamp(tolerance, dist, m_distToWall + 12, defaultSpeed), 0);
=======
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
>>>>>>> vision
    }

    @Override
    public boolean isFinished() {
        return m_distToWall < m_sensor.getDistance();
    }

    @Override
    public void interrupted() {
        Robot.m_Drivetrain.arcadeDrive(0, 0);
    }
}