/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

<<<<<<< HEAD
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
=======
>>>>>>> vision
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTableInstance;

import frc.robot.TriggerButton;
import frc.robot.commands.*;
<<<<<<< HEAD
import frc.robot.constants.Constants.*;
import frc.robot.Robot;
import frc.robot.subsystems.BallXtake;
import frc.robot.subsystems.Lift;

=======
import frc.robot.Robot;
import frc.robot.subsystems.LineFind;
>>>>>>> vision
=======
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
// import frc.robot.commands.ToggleSpotlight;
import frc.robot.commands.*;

>>>>>>> hatchIntake
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */



public class OI {
<<<<<<< HEAD
  private int driverPort = 0;
  private int coDriverPort = 1;
  private int buttonBoxPort = 2;
  //Joysticks
  public Joystick driver = new Joystick(this.driverPort);
  public Joystick extreme = new Joystick(this.coDriverPort);
  public Joystick buttonBox = new Joystick(this.buttonBoxPort);

  //All buttons
  Button aButton = new JoystickButton(driver, 1); //
  Button bButton = new JoystickButton(driver, 2); //
  Button xButton = new JoystickButton(driver, 3); //
  Button yButton = new JoystickButton(driver, 4); //
  Button leftBumper = new JoystickButton(driver, 5);
  Button rightBumper = new JoystickButton(driver, 6);
  Button select = new JoystickButton(driver, 7);
  Button start = new JoystickButton(driver, 8); //
  Button leftStick = new JoystickButton(driver, 9);
  Button rightStick = new JoystickButton(driver, 10);
  TriggerButton leftTrigger = new TriggerButton(driver, 2);
  TriggerButton rightTrigger = new TriggerButton(driver, 3);  //

  Button triggerBoi = new JoystickButton(extreme, 1); 
  Button sideButton = new JoystickButton(extreme, 2); 
  Button bottomLeft = new JoystickButton(extreme, 3); 
  Button bottomRight = new JoystickButton(extreme, 4);
  Button topLeft = new JoystickButton(extreme, 5);  //
  Button topRight = new JoystickButton(extreme, 6); //
  Button frontLeft = new JoystickButton(extreme, 7);  
  Button frontRight = new JoystickButton(extreme, 8); 
  Button midLeft = new JoystickButton(extreme, 9);    
  Button midRight = new JoystickButton(extreme, 10);  
  Button backLeft = new JoystickButton(extreme, 11);  
  Button backRight = new JoystickButton(extreme, 12); 

<<<<<<< HEAD
  Button topWhite = new JoystickButton(buttonBox, 2); //
  Button bigWhite = new JoystickButton(buttonBox, 3); //
  Button midRed = new JoystickButton(buttonBox, 4); //
  Button bottomWhite = new JoystickButton(buttonBox, 5);
  Button topRed = new JoystickButton(buttonBox, 6); //
  Button greenBoi = new JoystickButton(buttonBox, 7); //
  Button midWhite = new JoystickButton(buttonBox, 8); //
  Button bigRed = new JoystickButton(buttonBox, 14);  //
  Button yellowBoi = new JoystickButton(buttonBox, 15); //
  Button bottomRed = new JoystickButton(buttonBox, 16); 
=======
  Button buttonBox2 = new JoystickButton(buttonBox, 2);
  Button buttonBox3 = new JoystickButton(buttonBox, 3);
  Button buttonBox4 = new JoystickButton(buttonBox, 4);
  Button buttonBox5 = new JoystickButton(buttonBox, 5);
  Button buttonBox6 = new JoystickButton(buttonBox, 6);
  Button buttonBox7 = new JoystickButton(buttonBox, 7);
  Button buttonBox8 = new JoystickButton(buttonBox, 8);
  Button buttonBox14 = new JoystickButton(buttonBox, 14);
  Button buttonBox15 = new JoystickButton(buttonBox, 15);
  Button buttonBox16 = new JoystickButton(buttonBox, 16); 
>>>>>>> GrandForks

  // Trigger cargoSwitch = new DigitalInputTrigger(BallXtake.getBallOccupancySwitch());
  // Trigger liftSwitch = new DigitalInputTrigger(Lift.getLimitSwitch());

  public OI() {

    rightTrigger.whileHeld(new ShiftDown());
    rightTrigger.whenReleased(new ShiftUp());
    leftTrigger.whenPressed(new InvertDriving());
    yButton.whenPressed(new CargoFoldUp());
    aButton.whenPressed(new CargoFoldDown());
    xButton.whileHeld(new CargoRunIntake(1.0, 1.0, false)); // out
    bButton.whileHeld(new CargoRunIntake(-1.0, -1.0, false)); // in
    leftBumper.whenPressed(new ControlShift()); // toggles between arcade and tank

    topLeft.whileHeld(new RunBallXtake(1.0, true)); //out
    bottomLeft.whileHeld(new RunBallXtake(-0.75, true)); //in
    topRight.whileHeld(new CargoRunIntake(1.0, 1.0, true));
    bottomRight.whileHeld(new CargoRunIntake(-1.0, -1.0, true));
    backLeft.whileHeld(new CMG_ExtakeBallBottom());
    
    
    frontLeft.whenPressed(new FlowerOpen());
    frontRight.whenPressed(new FlowerClose());
    midLeft.whenPressed(new FlowerSlideOut());
    midRight.whenPressed(new FlowerSlideIn());

    // center flower begins 1'3" off ground
    // center ball begins 7.5" off ground
<<<<<<< HEAD
    bottomRed.whenPressed(new RunToBottom());
    bottomWhite.whenPressed(new RunToPosition(6)); // feeder and everything else
    midRed.whenPressed(new RunToPosition(41)); // second level rocket hatch
    // run top hatch all up
    midWhite.whenPressed(new RunToPosition(20)); // bottom level ball rocket
    topRed.whenPressed(new RunToPosition(34)); // cargo ball
    topWhite.whenPressed(new RunToPosition(54.5)); //ball second level rocket
    bigRed.whenPressed(new Compressor());
    bigWhite.whenPressed(new CMG_IntakeBall());
=======
    buttonBox16.whenPressed(new RunToBottom());

    buttonBox5.whenPressed(new RunToPosition(6)); // feeder and everything else
    buttonBox4.whenPressed(new RunToPosition(41)); // second level rocket hatch
    // run top hatch all up

    buttonBox8.whenPressed(new RunToPosition(20)); // bottom level ball rocket
    buttonBox6.whenPressed(new RunToPosition(34)); // cargo ball
    buttonBox2.whenPressed(new RunToPosition(54.5)); //ball second level rocket

    buttonBox14.whenPressed(new Compressor());
    buttonBox3.whenPressed(new CMG_IntakeBall());

>>>>>>> GrandForks
    // start.whenPressed(new DriveToWall());
    greenBoi.whenPressed(new CargoFoldUp());
    yellowBoi.whenPressed(new CargoFoldDown());

    buttonBox7.whenPressed(new FlowerSlideIn());
    buttonBox15.whenPressed(new FlowerSlideOut());

    // topWhite.whenPressed(new FlowerControl());
    // topRed.whenPressed(new FlowerSlideControl());

<<<<<<< HEAD
    // liftSwitch.whenActive(new ResetLiftEncoder());

    // Add in disable until ball is extaked. then reset trigger
    // cargoSwitch.whenActive(new CMG_LiftCargo());
    
    // cargoSwitch.whenActive(new CMG_IntakeBall());
=======
    greenBoi.whenPressed(new CargoFoldIntake(RobotMap.cargoUpPos));
    yellowBoi.whenPressed(new CargoFoldIntake(RobotMap.cargoDownPos));

    start.whenPressed(Robot.alignChooser.getSelected());
>>>>>>> vision
    
    // topRight.whenPressed(new FlowerControl());
  }

  public Double desensitize(Double val) {
=======
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public Joystick driver = new Joystick(0);
  public Joystick buttonBox = new Joystick(1);
  public Joystick extreme = new Joystick(2);

  Button aButton = new JoystickButton(driver, 1);
  Button bButton = new JoystickButton(driver, 2);
  Button xButton = new JoystickButton(driver, 3);
  Button yButton = new JoystickButton(driver, 4);
  Button leftBumper = new JoystickButton(driver, 5);
  Button rightBumper = new JoystickButton(driver, 6);
  Button select = new JoystickButton(driver, 7);
  Button start = new JoystickButton(driver, 8);
  Button leftStick = new JoystickButton(driver, 9);
  Button rightStick = new JoystickButton(driver, 10);

  Button triggerBoi = new JoystickButton(extreme, 1);
  Button sideButton = new JoystickButton(extreme, 2);
  Button bottomLeft = new JoystickButton(extreme, 3);
  Button bottomRight = new JoystickButton(extreme, 4);
  Button topLeft = new JoystickButton(extreme, 5);
  Button topRight = new JoystickButton(extreme, 6);
  Button frontLeft = new JoystickButton(extreme, 7);
  Button frontRight = new JoystickButton(extreme, 8);
  Button midLeft = new JoystickButton(extreme, 9);
  Button midRight = new JoystickButton(extreme, 10);
  Button backLeft = new JoystickButton(extreme, 11);
  Button backRight = new JoystickButton(extreme, 12);

  Button topWhite = new JoystickButton(buttonBox, 2);
  Button bigWhite = new JoystickButton(buttonBox, 3);
  Button midRed = new JoystickButton(buttonBox, 4);
  Button bottomWhite = new JoystickButton(buttonBox, 5);
  Button topRed = new JoystickButton(buttonBox, 6);
  Button greenBoi = new JoystickButton(buttonBox, 7);
  Button midWhite = new JoystickButton(buttonBox, 8);
  Button bigRed = new JoystickButton(buttonBox, 14);
  Button yellowBoi = new JoystickButton(buttonBox, 15);
  Button bottomRed = new JoystickButton(buttonBox, 16); 

  public OI() {
    rightBumper.whenPressed(new HatchFlip(RobotMap.hatchUp));
    leftBumper.whenPressed(new HatchFlip(RobotMap.hatchDown));
  }

  public double desensitize(double val) {
>>>>>>> hatchIntake
    double result = val;
    if (Math.abs(result) < 0.15) {
			result = 0.0;
		}
		return result;
<<<<<<< HEAD
  }
  
  public Double liftControl() {
  //  return desensitize(extreme.getRawAxis(1));
      return 0.0;
  }
  
  public Double getMove() {
    return desensitize(driver.getRawAxis(1));
  }

  public Double getTurn() {
    return desensitize(-driver.getRawAxis(4));
  }

  public Double getRight() {
    return desensitize(driver.getRawAxis(5));
  }
=======
	}

  public double getMove() {
    return desensitize(driver.getRawAxis(1));
  }

  public double getTurn() {
    return desensitize(-driver.getRawAxis(4));
  }

  public double getRight() {
    return desensitize(driver.getRawAxis(5));
  }

  public double getRightTrigger() {
    return desensitize(driver.getRawAxis(2));
  }
>>>>>>> hatchIntake
}
