package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class ArmUp extends CommandBase {

    private final ArmSubsystem arm;

    public ArmUp(ArmSubsystem a) {
        arm = a;
        addRequirements(arm);

        // this.interruptOn(arm::cantGo);
    }

    @Override
    public void execute() {
        arm.up();
    }


}
