package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.DriveConstants.*;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Drivetrain extends SubsystemBase{
    //set up our talons
    private final WPI_TalonSRX m_left = new WPI_TalonSRX(leftDriveCAN);
    private final WPI_TalonSRX m_right = new WPI_TalonSRX(rightDriveCAN);
    //set up our diff drive
    private final DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);


    public Drivetrain(){
        
        m_left.configFactoryDefault();
        m_right.configFactoryDefault();

        m_left.setNeutralMode(NeutralMode.Brake);
        m_right.setNeutralMode(NeutralMode.Brake);
    }

    //Applies sin function to the joystick values 
    //to allow variability at the low end and high end
    public void sinDrive(double forward, double turn){
        //apply the sin part to both
        double new_forward = Math.sin(((Math.PI/2)*forward));
        double new_turn = Math.sin(((Math.PI/2)*turn));
        //square both of them but keep the signs
        new_forward = Math.copySign(Math.pow(new_forward,2), new_forward);
        new_turn = Math.copySign(Math.pow(new_turn,2), new_turn);
        //now activate arcade drive with new values
        m_drive.arcadeDrive(new_forward, new_turn, false);
    }

    //regular drive, just down scales the output by certain scale factor
    public void sloModeDrive(double forward, double turn){
        //apply the sin part to both
        double new_forward = Math.sin(((Math.PI/2)*forward));
        double new_turn = Math.sin(((Math.PI/2)*turn));
        //square both of them but keep the signs
        new_forward = Math.copySign(Math.pow(new_forward,2), new_forward);
        new_turn = Math.copySign(Math.pow(new_turn,2), new_turn);
        //scale the output down by the sloModeScalar
        new_forward*=sloModeForwardScalar;
        new_turn*=sloModeTurnScalar;
        //now activate arcade drive with new values
        m_drive.arcadeDrive(new_forward, new_turn, false);
    }

    //simple deadband method
    public double handleDeadband(double value, double deadband){
        return (Math.abs(value)<deadband) ? 0.0:value;
    }
    
    //standard default arcade drive if I ever want to use it
    public void arcadeDrive(double forward, double turn){
        m_drive.arcadeDrive(forward, turn, false);
    }

    //standard defualt tank drive if I ever want to use it
    public void tankDrive(double left, double right){
        m_drive.tankDrive(left, right, false);
    }

    @Override
    public void periodic() {
    // This method will be called once per scheduler run
    }

}