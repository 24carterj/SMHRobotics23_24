package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmDown extends CommandBase {

    private final ArmSubsystem arm;

    public ArmDown(ArmSubsystem a) {
        arm = a;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        arm.down();
    }
}
