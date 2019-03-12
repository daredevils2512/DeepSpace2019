/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class CargoIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX infinityMotor;
  private WPI_TalonSRX inMotor;

  private DoubleSolenoid upDown;

  public CargoIntake() {
    this.infinityMotor = new WPI_TalonSRX(RobotMap.cargoInfinityPort);
    this.inMotor = new WPI_TalonSRX(RobotMap.cargoInMotorPort);

    this.upDown = new DoubleSolenoid(RobotMap.cargoUpDownAPort, RobotMap.cargoUpDownBPort);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void runIntake(double infinitySpeed, double inSpeed) {
    if (this.getCurrentPos().equals(RobotMap.cargoUpPos)) {
      this.foldDown();
    } 
    this.infinityMotor.set(infinitySpeed);
    this.inMotor.set(inSpeed);
  }

  private void foldIntake(DoubleSolenoid.Value dir) {
    Timer t = new Timer();
    t.start();
    this.upDown.set(dir);
    t.stop();
    System.out.println("FoldIntake took: "+t.get());
  }

  public void foldUp() {
    System.out.println("Folding up");
    this.foldIntake(RobotMap.cargoUpPos);
  }

  public void foldDown() {
    System.out.println("Folding down");
    this.foldIntake(RobotMap.cargoDownPos);
  }

  public DoubleSolenoid.Value getCurrentPos() {
    return this.upDown.get();
  }
  
}