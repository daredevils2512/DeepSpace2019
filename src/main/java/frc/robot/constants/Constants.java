package frc.robot.constants;

public final class Constants {
    public enum LiftPosition {
        FEEDER,
        CARGOBOTTOM,
        HATCHBOTTOM,
        CARGOMIDDLE,
        HATCHMIDDLE,
        CARGOTOP,
        HATCHTOP
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
        public static final double SPEED = 0.5;
        public static final double HEIGHTOFFSET = 15; // Actual height is 15 inches more than lift height
        public static final class Position {
            public static final double LOWEST = 7; // Lift stops going down at height of 7 for some reason
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
            public static final double ROCKETCARGO = 24; // Just a guess
        }
    }

    public static final class Limelight {
        public static final double HEIGHTOFFSET = 12; // Temporary
    }
}