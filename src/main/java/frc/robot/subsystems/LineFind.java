package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Robot;


public class LineFind extends Subsystem {
    
    public LineFind() {
    }




    public void pointToLine(double m) {
      System.out.println("lineFind activated");
      if (Robot.centerX + (Robot.widthPos / 2) >= m && Robot.centerX != 999.00) {
          System.out.println("motor ran left");
          Drivetrain.arcadeDrive(0.0, -0.5);
      }
      if (Robot.centerX + (Robot.widthPos / 2) <= -m && Robot.centerX != 999.00) {
          System.out.println("motor ran right");
          Drivetrain.arcadeDrive(0.0, 0.5);
      }
      if (Robot.centerX <= m && Robot.centerX >= -m && Robot.centerX != 999.00 && Robot.centered == false) {
          if (Robot.bottom >= m && Robot.bottom != 999.00) {
            System.out.println("motor ran backward");
            Drivetrain.arcadeDrive(0.5, 0);
          } else if (Robot.bottom >= -m && Robot.centerX != 999.00) {
            System.out.println("motor ran foreward");
            Drivetrain.arcadeDrive(-0.5, 0);
          } else if (Robot.bottom <= m && Robot.bottom >= -m && Robot.bottom != 999.00) {
            //here there be the rest of the auto bit
            System.out.println("centered on bottom of line");
            Robot.centered = true;
          } else {
            System.out.println("no bottom found");
          }
      }
    }

    public void Align(double m) {
      //Align robot to the same orientation as the line
      //Char dir is the relative direction of the line assuming the robot is facing the line
      //if dir = r the line is to the front and pointing to the right
      //if dir = l the line is to the front and pointing to the left
      //if dir = n the line is not found or already aligned
      System.out.println("Align ran");
      System.out.println( "" + (Robot.widthPos >= 7 + m) + (Robot.widthPos != 999.00) + (Robot.aligned == false) + (Robot.dir == 'n'));
      if ((Robot.widthPos >= 7 + m && Robot.widthPos != 999.00) && Robot.aligned == false && Robot.dir == 'n') {
        Robot.diff = Robot.widthPos;
        System.out.println("direction test");
        Drivetrain.arcadeDrive(0.0, 0.5);
        if (Robot.widthPos >= Robot.diff && Robot.widthPos != 999.00) {
          System.out.println("line is to the left");
          Robot.dir = 'l';
          Drivetrain.arcadeDrive(0.0, 0.0);
        } else if (Robot.widthPos <= Robot.diff) {
          System.out.println("line is to the right");
          Robot.dir = 'r';
          Drivetrain.arcadeDrive(0.0, 0.0);
        } else if (Robot.widthPos <= 7 + m) {
          System.out.println("Robot aligned");
          Robot.centered = true;
          Robot.dir = 'n';
          Drivetrain.arcadeDrive(0.0, 0.0);
        } else if (Robot.widthPos == 999.00) {
          System.out.println("no line width found");
        }
      }

      if (Robot.dir == 'r' && Robot.centered == false) {
        System.out.println("turning to the right");
        Drivetrain.arcadeDrive(0.0, 0.5);
      } else if (Robot.dir == 'l' && Robot.centered == false) {
        System.out.println("turning to the left");
        Drivetrain.arcadeDrive(0.0, -0.5);
      } else if (Robot.centered == true) {
        System.out.println("aligned on line");
        //the rest of the process will be put here
      } else if (Robot.dir == 'n' && Robot.centered == false) {
        System.out.println("no line found");
      }
      System.out.println("Align Info: diff " +  Robot.diff + "dir " + Robot.dir); 
    }

    public void alignByGyro(float desiredYaw) {
      if (Robot.m_navX.getYaw() >= desiredYaw) {
        
      }
    }

    

    public void resetVars() {
      Robot.centered = false;
      Robot.aligned = false;
      Robot.diff = 0.0;
      Robot.dir = 'n';
    }

    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      // setDefaultCommand(new MySpecialCommand());
    }
  
}