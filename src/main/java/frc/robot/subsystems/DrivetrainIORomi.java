// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.sensors.RomiGyro;

/** Add your docs here. */
public class DrivetrainIORomi implements DrivetrainIO {
    private final Spark m_leftMotor = new Spark(0);
    private final Spark m_rightMotor = new Spark(1);
    private final Encoder m_leftEncoder = new Encoder(4, 5);
    private final Encoder m_rightEncoder = new Encoder(6, 7);
    private final RomiGyro m_gyro = new RomiGyro();

    public DrivetrainIORomi() {
        m_rightMotor.setInverted(true);
    }

    @Override
    public void setOutputs(double leftPercent, double rightPercent) {
        m_leftMotor.set(leftPercent);
        m_rightMotor.set(rightPercent);
    }

    @Override
    public void resetEncoders() {
        m_leftEncoder.reset();
        m_rightEncoder.reset();
    }

    @Override
    public void resetGyro() {
        m_gyro.reset();

    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.rightPositionRadians = m_rightEncoder.getRaw() / 1440.0 * 2 * Math.PI;
        inputs.leftPositionRadians = m_leftEncoder.getRaw() / 1440.0 * 2 * Math.PI;
        inputs.gyroPositionRadians = Math.toRadians(m_gyro.getAngleZ());
    }
}
