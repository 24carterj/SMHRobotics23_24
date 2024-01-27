package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.MoveFB;
import org.firstinspires.ftc.teamcode.commands.MoveLR;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.RotatorSubsystem;

@Autonomous(name="ClawConfigure")
public class ClawConfigure extends LinearOpMode {

    static final double COUNTS_PER_MOTOR_REV = 312;    // Gobilda 5202 19.2:1 Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = 45;
    static final double DIST_PER_REV = Math.PI * 4.0;

    // ClawSubsystem claw;
    // RotatorSubsystem rotator;

    ServoEx c;
    ServoEx r;

    @Override
    public void runOpMode() throws InterruptedException {

        c = new SimpleServo(hardwareMap, "claw",0, 360);
        r = new SimpleServo(hardwareMap, "rotator", 0, 360);
        CommandScheduler.getInstance().reset();

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Claw: ",c.getPosition());
            telemetry.addData("Rotator: ",r.getPosition());
            telemetry.update();
        }
    }
}
