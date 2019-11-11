package frc.robot.constants;

public final class Constants {
    public enum LiftHeight {
        FEEDER(12),
        CARGO_SHIP_CARGO(56),
        ROCKET_CARGO_BOTTOM(27),
        ROCKET_CARGO_MIDDLE(57),
        ROCKET_CARGO_TOP(75),
        ROCKET_HATCH_BOTTOM(14),
        ROCKET_HATCH_MIDDLE(46),
        ROCKET_HATCH_TOP(68);

        private double height;

        LiftHeight(double height) {
            this.height = height;
        }

        public double getHeight() {
            return height;
        }
    }
    
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
        public static final double DEADZONE = 0.0;
    }

    public static final class Lift {
        public static final double MAX_UP_SPEED = 0.75; //Not using yet
        public static final double MAX_DOWN_SPEED = 0.55; //Not using yet
        public static final double BACKDRIVE = 0.08; //Idle lift speed to maintain position
        public static final double MAX_HEIGHT = 70;
        public static final double MAX_HEIGHT_TOLERANCE = 1; //Stops backdrive this many inches above max height
        public static final double MANUAL_OVERRIDE_TOLERANCE = 0.5; //Lift control value required to override RunTo commands
        public static final class Height {
            public static final double FEEDER = 12;
            public static final double CARGO_BOTTOM = 27;
            public static final double CARGO_MIDDLE = 57;
            public static final double CARGO_TOP = 75;
            public static final double HATCH_BOTTOM = 14;
            public static final double HATCH_MIDDLE = 46;
            public static final double HATCH_TOP = 68;
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