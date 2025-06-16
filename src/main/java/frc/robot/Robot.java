// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

public class Robot extends TimedRobot {
  SparkMax frontLeft, backLeft, frontRight, backRight;
  XboxController joy;

  public Robot() {
    frontLeft = new SparkMax(1, MotorType.kBrushless);
    backLeft = new SparkMax(2, MotorType.kBrushless);
    
    frontRight = new SparkMax(3, MotorType.kBrushless);
    backRight = new SparkMax(4, MotorType.kBrushless);

    joy = new XboxController(0);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  public void foward(double speed) {
    frontLeft.set(speed);
    backLeft.set(speed);

    frontRight.set(-speed);
    backRight.set(-speed);
  }

  public void turn(double speed) {
    // Speed > 0 = right
    // Speed < 0 = left
    frontLeft.set(speed);
    backLeft.set(speed);

    frontRight.set(speed);
    backRight.set(speed);
  }

  public void stop() {
    frontLeft.stopMotor();
    backLeft.stopMotor();

    frontRight.stopMotor();
    backRight.stopMotor();
  }

  public void turnInDrive(double fdSpeed, double turnSpeed) {
    frontLeft.set(fdSpeed - turnSpeed);
    backLeft.set(fdSpeed - turnSpeed);

    frontRight.set(-fdSpeed + turnSpeed);
    backRight.set(-fdSpeed + turnSpeed);
  }

  @Override
  public void teleopPeriodic() {
    if (joy.getRightTriggerAxis() > 0.1) {
      foward(joy.getRightTriggerAxis());
    } else if (joy.getLeftTriggerAxis() > 0.1) {
      foward(-joy.getLeftTriggerAxis());
    }

    // ATENCAO!!!! NAO SEI SE FUNCIONA ESSA PARTE
    else if (joy.getLeftX() != 0) {
      turnInDrive(joy.getRightTriggerAxis(), joy.getLeftX());
    } else if (joy.getLeftTriggerAxis() > 0.1 && joy.getLeftX() != 0) {
      turnInDrive(joy.getLeftTriggerAxis(), joy.getLeftX());
    }
    //

    else {
      stop();
    }
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {
  }
}
