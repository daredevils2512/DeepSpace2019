/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Compressorsorus extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Compressor sorus;
  // private Boolean enabled; 

  public Compressorsorus() {

    this.sorus = new Compressor();
    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void compressorOff() {
    this.sorus.setClosedLoopControl(false);
  }

  public void compressorOn() {
    this.sorus.setClosedLoopControl(true);
  }

  public void toggleCompressor() {
    // enabled = sorus.enabled();
    if (!sorus.enabled()) {
      System.out.println("Compressor enabled");
      compressorOn();
    } else {
      System.out.println("Compressor disabled");
      compressorOff();
    }
  }

  public boolean isOn() {
    return sorus.enabled();
  }
}