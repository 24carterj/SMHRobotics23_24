package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmUp extends CommandBase {

    private final ArmSubsystem arm;

    public ArmUp(ArmSubsystem a) {
        arm = a;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        arm.up();
    }
}
