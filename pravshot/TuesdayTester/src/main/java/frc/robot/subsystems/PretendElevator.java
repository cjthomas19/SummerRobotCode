package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.ElevatorConstants.*;

public class PretendElevator extends SubsystemBase {
    
    private final WPI_TalonSRX m_elevator = new WPI_TalonSRX(elevatorCAN);

    public PretendElevator(){
        m_elevator.configFactoryDefault();
        m_elevator.setNeutralMode(NeutralMode.Brake);
    }

    public void spinForward(double speed){
        m_elevator.set(speed);
    }

    public void spinBackward(double speed){
        m_elevator.set(-speed);
    }

    public void stopSpinning(){
        m_elevator.set(0);
    }

}