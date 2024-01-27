package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmDownFF extends CommandBase {

    private final ArmSubsystem arm;

    public ArmDownFF(ArmSubsystem a) {
        arm = a;
        addRequirements(arm);

        // this.interruptOn(arm::cantGo);
    }

    @Override
    public void execute() {
        arm.downFF();
    }

}
