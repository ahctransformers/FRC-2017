
package org.usfirst.frc.team6584.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6584.robot.Robot;

/**
 * Drives to a value set during autonomous
 * @author Yvette Kiptoo
 * @params speedforward, speedrotation
 */
public class AutoMoveToDistance extends Command {
	
	double swagSpeed;
	double swagTurn;
	double swagDistance;
	double tolerance = 1.5;

    public AutoMoveToDistance(double swagSpeed,double swagDistance) {
        // Use requires() here to declare subsystem dependencies
    		requires(Robot.drivetrain);
    		
    		this.swagSpeed = swagSpeed;
    		this.swagTurn = 0.0;
    		this.swagDistance = swagDistance;  
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoder();
    	Robot.drivetrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (Robot.drivetrain.getGucciAngle()>tolerance)
    		swagTurn=0.5;
    	else if (Robot.drivetrain.getGucciAngle()<-tolerance)
    		swagTurn=-0.5;
    	else 
    		swagTurn=0.0;
    	
    	Robot.drivetrain.moveArcade(swagSpeed, swagTurn);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.drivetrain.getSwagDistance() >= swagDistance;
    	
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
