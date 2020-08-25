package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import java.util.function.DoubleSupplier;
import java.util.function.BooleanSupplier;

public class JustDrive extends CommandBase {
    
    private final Drivetrain m_drive;
    private final DoubleSupplier m_forward, m_turn;
    private final BooleanSupplier m_sloMode;

    public JustDrive(Drivetrain drive, DoubleSupplier forward, DoubleSupplier turn, BooleanSupplier sloMode){
        
        addRequirements(drive);

        m_drive = drive;
        m_forward = forward;
        m_turn = turn;
        m_sloMode = sloMode;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        
        boolean isSloMode = m_sloMode.getAsBoolean();
        double forward = m_drive.handleDeadband(m_forward.getAsDouble(), 0.1);
        double turn = m_drive.handleDeadband(m_turn.getAsDouble(), 0.1);

        if(isSloMode){
            m_drive.sloModeDrive(forward, turn);
        }
        else{
            m_drive.sinDrive(forward, turn);
        }

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_drive.sinDrive(0.0, 0.0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }


}