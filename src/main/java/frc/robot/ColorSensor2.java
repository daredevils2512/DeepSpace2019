/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;

/**
 * Add your docs here.
 */
public class ColorSensor2 {
    protected final static int COMMAND_REGISTER_BIT = 0x80;
    protected final static int MULTI_BYTE_BIT = 0x20;

    protected final static int ENABLE_REGISTER   = 0x00;
    protected final static int ATIME_REGISTER    = 0x01;
    protected final static int PPULSE_REGISTER   = 0x0E;
    
    protected final static int ID_REGISTER       = 0x12;
    protected final static int CDATA_REGISTER    = 0x14;
    protected final static int RDATA_REGISTER    = 0x16;
    protected final static int GDATA_REGISTER    = 0x18;
    protected final static int BDATA_REGISTER    = 0x1A;
    protected final static int PDATA_REGISTER    = 0x1C;
    
    private I2C sensor;

    public ColorSensor2(I2C.Port port) {
      this.sensor = new I2C(port, 0x39); //port, I2c address    
    
      this.sensor.write(COMMAND_REGISTER_BIT | 0x00, 0b00000111); //power on, color sensor on
    }
    
    protected int readWordRegister(int address) {
      ByteBuffer buf = ByteBuffer.allocate(2);
      this.sensor.read(COMMAND_REGISTER_BIT | MULTI_BYTE_BIT | address, 2, buf);
      buf.order(ByteOrder.LITTLE_ENDIAN);
      return buf.getShort(0);
    }
    
    public int red() {
     return readWordRegister(RDATA_REGISTER);
    }
    
    public int green() {
     return readWordRegister(GDATA_REGISTER);
    }
    
    public int blue() {
     return readWordRegister(BDATA_REGISTER);
    }
    
    public int clear() {
     return readWordRegister(CDATA_REGISTER);
    }
    
    public int proximity() {
     return readWordRegister(PDATA_REGISTER);
    }
}
