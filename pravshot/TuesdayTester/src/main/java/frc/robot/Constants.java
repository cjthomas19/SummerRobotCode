/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.XboxController.Button;;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public final static class Controls{
        public static final int AButtonID = Button.kA.value;
        
    }
    public final class Controllers{
        public static final int DriverControllerPort = 0;
    }

    public final class ElevatorConstants{
        public static final int elevatorCAN = 2;
    }

    public final class DriveConstants{
        public static final int leftDriveCAN = 4;
        public static final int rightDriveCAN = 3;
        public static final double sloModeTurnScalar = 0.5;
        public static final double sloModeForwardScalar = 0.5;
        
    }
}
