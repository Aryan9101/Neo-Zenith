package frc.team1816.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Collector extends Subsystem {
    private TalonSRX collector;
    private double velocity;

    public Collector(int collector, double velocity){
        super();
        this.collector = new TalonSRX(collector);
    }

    public void setCollector(){
        this.collector.set(ControlMode.Velocity, velocity);
    }


    @Override
    protected void initDefaultCommand() {

    }
}
