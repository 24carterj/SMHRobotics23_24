package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.MoveFB;
import org.firstinspires.ftc.teamcode.commands.MoveLR;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
@Disabled
@Autonomous(name="BP RIGHT")
public class BlueAuto2 extends LinearOpMode {

    static final double COUNTS_PER_MOTOR_REV = 312;    // Gobilda 5202 19.2:1 Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = 45;
    static final double DIST_PER_REV = Math.PI * 4.0;

    DriveSubsystem drive;

    @Override
    public void runOpMode() throws InterruptedException {

        drive = new DriveSubsystem(hardwareMap);
        CommandScheduler.getInstance().reset();

        // CommandScheduler.getInstance().schedule(new InstantCommand(this::requestOpModeStop));
        waitForStart();
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new WaitCommand(3000).deadlineWith(new MoveFB(drive, 300, 0.6)),
                        new WaitCommand(1000),
                        new WaitCommand(4850).deadlineWith(new MoveLR(drive, 147, 1.0)),
                        new InstantCommand(this::requestOpModeStop)
                )
        );
    /*
     new MoveLR(drive, 147, 1)
                        // .withTimeout(1000)
                        .interruptOn(new WaitCommand(1000)::isFinished),
     */

        // CommandScheduler.getInstance().schedule(new WaitCommand(500));
        // CommandScheduler.getInstance().schedule(new InstantCommand(this::requestOpModeStop));

        while (opModeIsActive()) {
            CommandScheduler.getInstance().run();
        }

        // drive.positionMode();
    }
}
