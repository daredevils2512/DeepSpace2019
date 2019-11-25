package frc.robot.constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public final class Constants {
    public enum YPRSelect {
        YAW,
        PITCH,
        ROLL
    }

    public enum DistanceSensorSide {
        BALL, 
        HATCH
    }

    public static final class OI {
        public static final double DEADZONE = 0.1;
    }

    public static final class Drivetrain {
        public static final DoubleSolenoid.Value HIGH_GEAR_VALUE = Value.kForward;
        public static final DoubleSolenoid.Value LOW_GEAR_VALUE = Value.kReverse;
    }

    public static final class Lift {
        // Not using these
        public static final int ENCODER_PULSES_PER_REVOLUTION = 4096;
        public static final double GEAR_RATIO = 1.0; // TODO: Find out actual value
        public static final double DISTANCE_PER_ROTATION = 0.0; // TODO: Find out actual value
        
        // Used as multipliers (should be positive)
        public static final double MAX_UP_SPEED = 1.0;
        public static final double MAX_DOWN_SPEED = 0.75;

        public static final double BACKDRIVE = 0.08; // Idle lift speed to maintain position
        public static final double MAX_HEIGHT = 70;
        public static final double MANUAL_OVERRIDE_TOLERANCE = 0.5; //Lift control value required to override RunTo commands
        public static enum Height {
            IDLE(12),
            FEEDER(12),
            CARGO_SHIP_CARGO(56),
            ROCKET_CARGO_BOTTOM(27),
            ROCKET_CARGO_MIDDLE(57),
            ROCKET_CARGO_TOP(75),
            ROCKET_HATCH_BOTTOM(14),
            ROCKET_HATCH_MIDDLE(46),
            ROCKET_HATCH_TOP(68);
            
            private double height;
            
            Height(double height) {
                this.height = height;
            }
            
            public double getHeight() {
                return height;
            }
        }
    }

    public static final class VisionTape {
        public static final class Height {
            public static final double ROCKET_CARGO = 33; // Estimated: check game manual
        }
    }

    private Constants() {

    }
}