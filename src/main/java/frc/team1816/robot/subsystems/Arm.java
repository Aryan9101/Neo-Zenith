package frc.team1816.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
    private TalonSRX armTalon;

    private double armSpeed;
    private final double threshold = 0; //Recalibrate the pot and find correct value

    private AnalogPotentiometer potentiometer;

    public Arm(int armTalon, int potentiometer){
        super();
        this.armTalon = new TalonSRX(armTalon);
        this.armTalon.setNeutralMode(NeutralMode.Brake);
        this.armTalon.setInverted(true);
        this.potentiometer = new AnalogPotentiometer(potentiometer);
    }

    public void setArm(double armSpeed){
        if ((getArmPos() < 0.164) || (getArmPos() > 0.363)){
            this.armSpeed = 0;
        } else {
            this.armSpeed = armSpeed;
        }
        this.armTalon.set(ControlMode.PercentOutput, this.armSpeed);
    }

    public double getArmPos() {
        return potentiometer.get();
    }


    @Override
    protected void initDefaultCommand() { }
}
