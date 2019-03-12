package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class DigitalInputTrigger extends Trigger {

    private DigitalInput m_input;

    public DigitalInputTrigger(DigitalInput input) {
        m_input = input;
    }

    public boolean get() {
        return m_input.get();
    }
}