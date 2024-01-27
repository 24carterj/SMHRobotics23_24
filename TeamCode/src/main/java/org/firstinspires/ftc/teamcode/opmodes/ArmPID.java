package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.commands.ArmDown;
import org.firstinspires.ftc.teamcode.commands.ArmDownFF;
import org.firstinspires.ftc.teamcode.commands.ArmUp;
import org.firstinspires.ftc.teamcode.commands.ArmUpFF;
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
@TeleOp(name="ArmPID")
public class ArmPID extends CommandOpMode {
    private GamepadEx driver;

    private ArmSubsystem arm;

    private final String[] coeffTypes = {"ks", "kcos", "kv"};
    private int coeffIndex = 0;

    private double incr = 0.0;
    private double amt = 0.0;

    @Override
    public void initialize() {
        // Gamepad(s)
        driver = new GamepadEx(gamepad1);

        // Subsystems
        arm    = new ArmSubsystem(hardwareMap, "arm");

        // Telemetry
        telemetry.clearAll();

        telemetry.addData("MOTION ", "Pos = %f   Vel = %f    Acc = %f", arm.getPos(), arm.getMotor().getVelocity(), arm.getMotor().getAcceleration());
        double[] coeffs = arm.affCoeffs();
        telemetry.addData("COEFFS ", "ks = %f    kcos = %f     kv = %f", coeffs[0], coeffs[1], coeffs[2]);
        telemetry.addData("CURRENT ", "coeff = %s   incr = %f", coeffTypes[coeffIndex], incr);

        telemetry.update();

        // Arm
        driver.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new ArmUpFF(arm))
                .whenReleased(new InstantCommand(arm::stopFF, arm));

        driver.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(new ArmDownFF(arm))
                .whenReleased(new InstantCommand(arm::stopFF, arm));

        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(new InstantCommand(() -> {
            incr -= 0.02;
        }));
        driver.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(new InstantCommand(() -> {
            incr += 0.02;
        }));
        driver.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(new InstantCommand(() -> {
            coeffIndex++;
            coeffIndex %= 3;
        }));
        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new InstantCommand(() -> {
            arm.changeCoeffBy(coeffTypes[coeffIndex], incr);
        }));
        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new InstantCommand(() -> {
            arm.changeCoeffBy(coeffTypes[coeffIndex], -incr);
        }));
        // Driving

        telemetry.update();
    }
}
