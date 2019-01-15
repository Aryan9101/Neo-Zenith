package frc.team1816.robot.commands;

import badlog.lib.BadLog;
import com.edinarobotics.utils.math.Math1816;
import com.edinarobotics.utils.gamepad.Gamepad;
import edu.wpi.first.wpilibj.command.Command;
import frc.team1816.robot.Components;
import frc.team1816.robot.Controls;
import frc.team1816.robot.Robot;
import frc.team1816.robot.subsystems.Drivetrain;

public class GamepadDriveCommand extends Command {
    private Drivetrain drivetrain;
    private Gamepad gamepad;

    public GamepadDriveCommand(){
        super("gamepaddrivecommand");
        this.drivetrain = Components.getInstance().drivetrain;
        this.gamepad = Controls.getInstance().gamepadDriver;
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

        BadLog.publish(Robot.LOG_GAMEPAD_LEFTPOWER, leftPower);
        BadLog.publish(Robot.LOG_GAMEPAD_RIGHTPOWER, rightPower);

        if (gamepad.leftTrigger().get()){
            drivetrain.setDrivetrainPercentOutput(leftPower, rightPower);
            System.out.println("Mode: PercentOutput");
            BadLog.publish(Robot.LOG_GAMEPAD_VELOCITY_MODE, 0.0);
        } else {
            drivetrain.setDrivetrainVelocity(leftPower, rightPower);
            System.err.println("Mode: Velocity");
            BadLog.publish(Robot.LOG_GAMEPAD_VELOCITY_MODE, 1.0);
        }
    }

    @Override
    protected void end() {
        this.drivetrain.setDrivetrainVelocity(0,0);
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
