/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.PretendElevator;
import frc.robot.commands.JustDrive;
import frc.robot.commands.SimpAuto;
import frc.robot.Constants.Controllers;
import frc.robot.Constants.Controls;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Drivetrain m_robotDrive = new Drivetrain();
  private final PretendElevator m_pretendElevator = new PretendElevator();
  private final XboxController m_driverController = new XboxController(Controllers.DriverControllerPort);



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    
    m_robotDrive.setDefaultCommand(new JustDrive(m_robotDrive,
      () -> -m_driverController.getY(GenericHID.Hand.kRight),
      () -> m_driverController.getX(GenericHID.Hand.kLeft),
      () -> m_driverController.getBumperPressed(Hand.kRight)));
    
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    //guessed for the button ID
    new JoystickButton(m_driverController, Controls.AButtonID)
      .whenPressed(() -> m_driverController.setRumble(RumbleType.kLeftRumble, 0.5))
      .whenReleased(() -> m_driverController.setRumble(RumbleType.kLeftRumble, 0.0));

    //holding down the right trigger will spin motor forwards
    new Button(() -> m_driverController.getTriggerAxis(Hand.kRight) >= 0.15)
      .whenPressed(() -> m_pretendElevator.spinForward(m_driverController.getTriggerAxis(Hand.kRight)))
      .whenReleased(() -> m_pretendElevator.stopSpinning());

    //holding down the left trigger will spin motor the other way
    new Button(() -> m_driverController.getTriggerAxis(Hand.kLeft) >= 0.15)
      .whenPressed(() -> m_pretendElevator.spinBackward(m_driverController.getTriggerAxis(Hand.kLeft)))
      .whenReleased(() -> m_pretendElevator.stopSpinning());

    

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return m_autoCommand;
    return new SimpAuto(m_robotDrive, 0.5, 0.0).withTimeout(2.0);
  }
}
