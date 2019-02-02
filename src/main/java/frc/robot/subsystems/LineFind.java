package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Robot;

public class LineFind extends Subsystem {
    
    public LineFind() {

    }

    public void pointToLine(double m) {
      System.out.println("lineFind activated");
      if (Robot.centerX >= m && Robot.centerX != 999.00) {
          System.out.println("motor ran left");
          Drivetrain.arcadeDrive(-0.25, -0.5);
      }
      if (Robot.centerX <= -m && Robot.centerX != 999.00) {
          System.out.println("motor ran right");
          Drivetrain.arcadeDrive(-0.25, 0.5);
      }
      if (Robot.centerX <= m && Robot.centerX >= -m && Robot.centerX != 999.00) {
          System.out.println("motor ran foreward");
          Drivetrain.arcadeDrive(-0.5, 0);
      } 
    }

    public void center(double m, double w) {
      
    }
    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      // setDefaultCommand(new MySpecialCommand());
    }
  
}