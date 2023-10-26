package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ClawGrab;
import org.firstinspires.ftc.teamcode.commands.ClawRelease;
import org.firstinspires.ftc.teamcode.commands.DefDrive;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LadderSubsystem;


//@Disabled
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

        // Drive
        drive = new DriveSubsystem(hardwareMap);
        arm = new ArmSubsystem(hardwareMap, "arm");
        claw = new ClawSubsystem(hardwareMap, "claw");
        ladder = new LadderSubsystem(hardwareMap, "ladder");

        // commands
        drive.setDefaultCommand(new DefDrive(drive, driver::getLeftX, driver::getLeftY, driver::getRightX));

        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new ClawGrab(claw));

        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new ClawRelease(claw));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(new InstantCommand(drive::incrSpeed));
        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(new InstantCommand(drive::decrSpeed));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(new InstantCommand(ladder::incrSpeed));
        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(new InstantCommand(ladder::decrSpeed));

    }
}
