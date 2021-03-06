package frc.team1816.robot.commands;

import com.edinarobotics.utils.math.Math1816;
import edu.wpi.first.wpilibj.command.Command;
import frc.team1816.robot.Components;
import frc.team1816.robot.Controls;
import frc.team1816.robot.subsystems.Drivetrain;

public class GamepadDriveCommand extends Command {
    private Drivetrain drivetrain;

    public GamepadDriveCommand(){
        super("gamepaddrivecommand");
        this.drivetrain = Components.getInstance().drivetrain;
        requires(drivetrain);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        double speed = Controls.getInstance().getDriveSpeed();
        double turn = Controls.getInstance().getDriveTurn();

        //System.out.println("Gamepad LeftY " + gamepad.getLeftY() + " RightX " + gamepad.getRightX());

        double leftPower = 0.5 * Math1816.coerceValue(1, -1, speed + turn);
        double rightPower = 0.5 * Math1816.coerceValue(1, -1, speed - turn);
        
        //System.out.println("Left Power: " + leftPower + " Right Power: " + rightPower);

        if (turn == 0) {
            drivetrain.setDrivetrain(0.3 * leftPower, 0.45 * rightPower);
        } else if (turn != 0) {
            drivetrain.setDrivetrain(0.5 * leftPower, 0.6 * rightPower);
        }
    }

    @Override
    protected void end() {
        this.drivetrain.setDrivetrain(0,0);
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    
}
