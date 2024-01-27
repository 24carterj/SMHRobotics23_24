package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ClawGrab;
import org.firstinspires.ftc.teamcode.commands.ClawRelease;
import org.firstinspires.ftc.teamcode.commands.DefDrive;
import org.firstinspires.ftc.teamcode.commands.Launch;
import org.firstinspires.ftc.teamcode.commands.RotateDown;
import org.firstinspires.ftc.teamcode.commands.RotateUp;
import org.firstinspires.ftc.teamcode.commands.TwoLiftDown;
import org.firstinspires.ftc.teamcode.commands.TwoLiftUp;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LauncherSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.RotatorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TwoLiftSubsystem;


//@Disabled
@TeleOp(name="DriveGameLift2")
public class DriveLift2 extends CommandOpMode {
    private GamepadEx driver, driver2;

    private DriveSubsystem drive;
    private TwoLiftSubsystem lifts;
    private ClawSubsystem claw;
    private RotatorSubsystem rotator;
    private LauncherSubsystem launcher;

    private boolean lastJoystickUp = false;
    private boolean lastJoystickDown = false;

    @Override
    public void initialize() {

        // Gamepad(s)
        driver = new GamepadEx(gamepad1);
        driver2 = new GamepadEx(gamepad2);

        // Subsystems
        drive   = new DriveSubsystem(hardwareMap);
        lifts   = new TwoLiftSubsystem(hardwareMap, 1);
        claw    = new ClawSubsystem(hardwareMap, "claw");
        rotator = new RotatorSubsystem(hardwareMap, "rotator");
        launcher = new LauncherSubsystem(hardwareMap, "launcher");

        // Lift
        boolean ltrig = gamepad1.left_trigger > 0.5;
        boolean rtrig = gamepad1.right_trigger > 0.5;

        if (ltrig && !rtrig) CommandScheduler.getInstance().schedule(new TwoLiftDown(lifts));
        else if (rtrig && !ltrig) CommandScheduler.getInstance().schedule(new TwoLiftUp(lifts));
        else CommandScheduler.getInstance().schedule(new InstantCommand(lifts::stop, lifts));


        // Lift backup
        driver.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(new TwoLiftUp(lifts))
                .whenReleased(new InstantCommand(lifts::stop, lifts));

        driver.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new TwoLiftDown(lifts))
                .whenReleased(new InstantCommand(lifts::stop, lifts));


        // Claw
        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .toggleWhenPressed(new ClawRelease(claw), new ClawGrab(claw));
        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .toggleWhenPressed(new RotateDown(rotator), new RotateUp(rotator));

        // Launcher
        // driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).and(GamepadKeys.Button.DPAD_DOWN).whenActive(new Launch(launch))
        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(new Launch(launcher));
        // Driving

        drive.setDefaultCommand(new DefDrive(drive, driver::getLeftX, driver::getLeftY, driver::getRightX));
    }
}