package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
public class SimpAuto extends SequentialCommandGroup{

    private final Drivetrain driveBot;
    private double forward;
    private double turn;
    
    public SimpAuto(Drivetrain drive, double forward, double turn){
            driveBot = drive;
            this.forward = forward;
            this.turn = turn;
    }

    @Override
    public void execute() {
    
        driveBot.arcadeDrive(this.forward, this.turn);
    }

    @Override
    public void end(boolean interrupted) {
        
        driveBot.arcadeDrive(0,0);
    }

}