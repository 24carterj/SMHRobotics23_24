package org.firstinspires.ftc.teamcode.commands;

// import com.acmerobotics.roadrunner.drive.Drive;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveFC extends CommandBase {

    private final DriveSubsystem driveSS;
    private final DoubleSupplier leftX;
    private final DoubleSupplier leftY;
    private final DoubleSupplier rightX;
    private DoubleSupplier heading;

    public DriveFC(DriveSubsystem subsys, DoubleSupplier lx, DoubleSupplier ly, DoubleSupplier rx, DoubleSupplier hd) {
        driveSS = subsys;
        leftX = lx;
        leftY = ly;
        rightX = rx;
        heading = hd;

        addRequirements(driveSS);
    }


    @Override
    public void execute() {
        driveSS.driveFC(leftX.getAsDouble(), leftY.getAsDouble(), rightX.getAsDouble(), heading.getAsDouble());
    }
}
