package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DriveDistance;
import frc.robot.subsystems.Drivetrain;
import frc.robot.vision.Utils;
import frc.robot.Robot;


public class LineFind extends Subsystem {

    static double s;
    
    public LineFind() {
      
    }

    static boolean centered = false;

    public static Double centerXBall = null;
    public static Double widthBall = null;
    public static Double heightBall = null;
    public static Double bottomBall = null;
    public static Double topBall = null;
    public static Double widthPosBall = null;
    public static Double centerYBall = null;
    public static Double areaBall = null;
  
    public static Double centerXHatch = null;
    public static Double widthHatch = null;
    public static Double heightHatch = null;
    public static Double bottomHatch = null;
    public static Double topHatch = null;
    public static Double widthPosHatch = null;
    public static Double centerYHatch = null;
    public static Double areaHatch = null;

    /*
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
    */

    public void pointToLine(double tolerance, Double centerX) {
      if (centered == false && centerX != null) {
        if (centerX >= 0 + tolerance) {
          if (centerX >= 20) {
            s = 0.75;
          } else {
            s = 0.6;
          }
          Drivetrain.staticArcadeDrive(0.0, -s);
        }
        if (centerX <= 0 - tolerance) {
          if (centerX <= -20) {
            s = 0.75;
          } else {
            s = 0.6;
          }
          Drivetrain.staticArcadeDrive(0.0, s);
        }
        if (centerX <= 0 + tolerance && centerX > 0 - tolerance) {
          Drivetrain.staticArcadeDrive(0.0, 0.0);
          centered = true;
        }
      } else if (centered == true) {
        System.out.println("already centered");
      } else {
        System.out.println("centerX is null");
      }
    }

    public void pointToLineY(double tolerance, Double centerY) {
      centerY = centerY - 15;
      if (centered == false && centerY != null) {
        if (centerY >= 0 + tolerance) {
          if (centerY >= 20) {
            s = 0.75;
          } else {
            s = 0.6;
          }
          Drivetrain.staticArcadeDrive(s, 0.0);
        }
        if (centerY <= 0 - tolerance) {
          if (centerY <= -20) {
            s = 0.75;
          } else {
            s = 0.6;
          }
          Drivetrain.staticArcadeDrive(-s, 0.0);
        }
        if (centerY <= 0 + tolerance && centerY > 0 - tolerance) {
          Drivetrain.staticArcadeDrive(0.0, 0.0);
          centered = true;
        }
      } else if (centered == true) {
        System.out.println("already centered");
      } else {
        System.out.println("centerY is null");
      }
    }

    public void alignByGyro(float desiredYaw, double tolerance) {
      if (Robot.m_Drivetrain.getNonCummulativeYaw() >= desiredYaw + tolerance) {
        Robot.m_Drivetrain.arcadeDrive(0.0, -0.75);
      } else if (Robot.m_Drivetrain.getYaw() <= desiredYaw - tolerance) {
        Robot.m_Drivetrain.arcadeDrive(0.0, 0.75);
      } else {
        System.out.println("Robot is aligned");
      }
    }


    public void resetVars() {
      centered = false;
      Robot.aligned = false;
      Robot.diff = 0.0;
      Robot.dir = 'n';
    }

    public void resetTable(NetworkTableInstance inst, String server) {
      // Utils.resetTables(inst, 2512);
    }

    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      // setDefaultCommand(new MySpecialCommand());
    }
  
}