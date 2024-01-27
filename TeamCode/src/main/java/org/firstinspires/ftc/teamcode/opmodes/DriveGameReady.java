package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.PerpetualCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.ArmDown;
import org.firstinspires.ftc.teamcode.commands.ArmUp;
import org.firstinspires.ftc.teamcode.commands.ClawGrab;
import org.firstinspires.ftc.teamcode.commands.ClawRelease;
import org.firstinspires.ftc.teamcode.commands.DefDrive;
import org.firstinspires.ftc.teamcode.commands.LadderExtend;
import org.firstinspires.ftc.teamcode.commands.LadderPower;
import org.firstinspires.ftc.teamcode.commands.LadderRetract;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LadderSubsystem;


@Disabled
@TeleOp(name="DriveGame")
public class DriveGameReady extends CommandOpMode {
    private GamepadEx driver;

    private DriveSubsystem drive;
    private ArmSubsystem arm;
    private ClawSubsystem claw;
    private LadderSubsystem ladder;


    @Override
    public void initialize() {

        // Gamepad(s)
        driver = new GamepadEx(gamepad1);

        // Subsystems
        drive  = new DriveSubsystem(hardwareMap);
        arm    = new ArmSubsystem(hardwareMap, "arm");
        claw   = new ClawSubsystem(hardwareMap, "claw");
        ladder = new LadderSubsystem(hardwareMap, "ladder");

        // Telemetry
        // schedule(new RunCommand(() -> telemetry.addData("Claw at ","%f", claw.getPos())));
        // schedule(new RunCommand(()"Ladder position: ", "%f", ladder.getPos());
        // telemetry.addData("Arm position: ", "%f", arm.getPos());
        schedule(new RunCommand(() -> telemetry.addData("Arm position: ", "%f", arm.getPos())));
        schedule(new RunCommand(telemetry::update));
        // Claw

        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new ClawGrab(claw));

        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new ClawRelease(claw));

        // Elevator
        double ltrig = driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);
        double rtrig = driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);

        if (ltrig > 0.3 && rtrig < 0.3) {
            schedule(new LadderPower(ladder, ltrig));
            telemetry.addData("Trigger: ", "%s", "right");
            telemetry.speak("HELLO");

        }
        else if (rtrig > 0.3 && ltrig < 0.3) {
            schedule(new LadderPower(ladder, -rtrig));
            telemetry.addData("Trigger: ", "%s", "right");

        }
        else {
            schedule(new InstantCommand(ladder::stop, ladder));
        }

        driver.getGamepadButton(GamepadKeys.Button.A)
                .whenHeld(new LadderRetract(ladder))
                .whenReleased(new InstantCommand(ladder::stop, ladder));

        driver.getGamepadButton(GamepadKeys.Button.Y)
                .whenHeld(new LadderExtend(ladder))
                .whenReleased(new InstantCommand(ladder::stop, ladder));

        // Arm
        driver.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new ArmUp(arm))
                .whenReleased(new RunCommand(arm::stop, arm));


        driver.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(new ArmDown(arm))
                .whenReleased(new RunCommand(arm::stop, arm));

        schedule(new PerpetualCommand(new RunCommand(() -> telemetry.addData("claw angle ", "%f", claw.getAngle()))));
        schedule(new PerpetualCommand(new RunCommand(telemetry::update)));
        // Driving
        drive.setDefaultCommand(new DefDrive(drive, driver::getLeftX, driver::getLeftY, driver::getRightX));

        telemetry.addData("claw angle ", "%f", claw.getAngle());
        telemetry.update();
    }
}
