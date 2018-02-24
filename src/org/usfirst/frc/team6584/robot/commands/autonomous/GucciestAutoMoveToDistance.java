package org.usfirst.frc.team6584.robot.commands.autonomous;

import org.team708.util.Math708;
import org.usfirst.frc.team6584.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GucciestAutoMoveToDistance extends Command {
	double swagSpeed;
	double swagDistance;
	

    public GucciestAutoMoveToDistance(double swagDistance) {
    	requires(Robot.drivetrain);
    	
    	this.swagDistance= swagDistance;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	swagSpeed = Math708.getClippedPercentError(Robot.drivetrain.getSwagDistance(), swagDistance, 0.5, 1.0);
    
    	Robot.drivetrain.moveArcade(swagSpeed, 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getSwagDistance()) >= Math.abs(swagDistance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.moveTank(0.0,0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
