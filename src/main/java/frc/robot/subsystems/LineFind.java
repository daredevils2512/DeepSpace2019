package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.vision.Utils;
import frc.robot.Robot;


public class LineFind extends Subsystem {
    
    public LineFind() {
    }




    public void pointToLine(double m) {
      System.out.println("lineFind activated");
      if (Robot.centerX == null || Robot.widthPos == null) {
        System.out.println("centerX or widthPos are null");
      } else {
        if (Robot.centerX + (Robot.widthPos / 2) >= m && Robot.centerX != null && Robot.centered) {
            System.out.println("motor ran left");
            Drivetrain.staticArcadeDrive(0.0, -0.5);
        }
        if (Robot.centerX + (Robot.widthPos / 2) <= -m && Robot.centerX != null) {
            System.out.println("motor ran right");
            Drivetrain.staticArcadeDrive(0.0, 0.5);
        }
        if (Robot.centerX <= m && Robot.centerX >= -m && Robot.centerX != null && Robot.centered == false) {
          if (Robot.bottom >= m && Robot.bottom != null) {
            System.out.println("motor ran backward");
            Drivetrain.staticArcadeDrive(0.5, 0);
          } else if (Robot.bottom >= -m && Robot.centerX != null) {
            System.out.println("motor ran foreward");
            Drivetrain.staticArcadeDrive(-0.5, 0);
          } else if (Robot.bottom <= m && Robot.bottom >= -m && Robot.bottom != null) {
            //here there be the rest of the auto bit
            System.out.println("centered on bottom of line");
            Robot.centered = true;
          } else {
            System.out.println("no bottom found");
          }
        }
      }
    }

    public void alignByGyro(float desiredYaw, double tolerance) {
      if (Robot.m_Drivetrain.getYaw() >= desiredYaw + tolerance) {
        Robot.m_Drivetrain.arcadeDrive(0.0, 0.5);
      } else if (Robot.m_Drivetrain.getYaw() <= desiredYaw - tolerance) {
        Robot.m_Drivetrain.arcadeDrive(0.0, -0.5);
      } else {
        System.out.println("Robot is aligned");
      }
    }

    

    public void resetVars() {
      Robot.centered = false;
      Robot.aligned = false;
      Robot.diff = 0.0;
      Robot.dir = 'n';
    }

    public void resetTable(NetworkTableInstance inst, String server) {
      Utils.resetTables(inst, 2512);
    }

    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      // setDefaultCommand(new MySpecialCommand());
    }
  
}