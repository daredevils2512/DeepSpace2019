package frc.robot.constants;

public final class Constants {
    public enum LiftPosition {
        FEEDER(12),
        CARGO_SHIP_CARGO(48), // Needs to be measured
        ROCKET_CARGO_BOTTOM(27),
        ROCKET_CARGO_MIDDLE(57),
        ROCKET_CARGO_TOP(75),
        ROCKET_HATCH_BOTTOM(14),
        ROCKET_HATCH_MIDDLE(46),
        ROCKET_HATCH_TOP(68);

        private double position;

        LiftPosition(double position) {
            this.position = position;
        }

        public double getPosition() {
            return position;
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

    public static final class Lift {
        public static final double BACKDRIVE = 0.08; // Idle lift speed to maintain position
        public static final double MAXHEIGHT = Height.HATCHTOP;
        public static final double MAXHEIGHTTOLERANCE = 2; // Stops backdrive this high above max height
        public static final class Height {
            public static final double FEEDER = 12;
            public static final double CARGOBOTTOM = 27;
            public static final double CARGOMIDDLE = 57;
            public static final double CARGOTOP = 75;
            public static final double HATCHBOTTOM = 14;
            public static final double HATCHMIDDLE = 46;
            public static final double HATCHTOP = 68;
        }
    }

    public static final class VisionTape {
        public static final class Height {
            public static final double ROCKETCARGO = 33; // Estimated: check game manual
        }
    }

    public static final class Limelight {
        public static final double LOWEST = 17.5; // Height when lift height is 0
        public static final double HEIGHTOFFSET = 24; // Temporary: height difference between limelight and target
        public static final double PITCH = 0; // Pitch angle of camera for distance calculations
    }
}