package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class DriveFieldCentric extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // constructor takes in frontLeft, frontRight, backLeft, backRight motors
        // IN THAT ORDER
        MecanumDrive drive = new MecanumDrive(
                new Motor(hardwareMap, "leftFront", Motor.GoBILDA.RPM_435),
                new Motor(hardwareMap, "rightFront", Motor.GoBILDA.RPM_435),
                new Motor(hardwareMap, "leftBack", Motor.GoBILDA.RPM_435),
                new Motor(hardwareMap, "rightBack", Motor.GoBILDA.RPM_435)
        );

        RevIMU imu = new RevIMU(hardwareMap);
        imu.init();

        // the extended gamepad object
        GamepadEx driver = new GamepadEx(gamepad1);

        waitForStart();

        while (!isStopRequested()) {
            drive.driveFieldCentric(
                    -driver.getLeftX(),
                    driver.getLeftY(),
                    driver.getRightX(),
                    imu.getRotation2d().getDegrees(),   // gyro value passed in here must be in degrees
                    false
            );
        }
    }

}