package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@Disabled
@TeleOp(name="LinearDrive")
public class DriveLinOp extends LinearOpMode {

    // instantiate motors
    private static DcMotor leftFront, rightFront, leftBack, rightBack;

    HardwareMap hwMap =  null;
    Telemetry telemetry;
    private ElapsedTime period = new ElapsedTime();

    @Override
    public void runOpMode() {

        // declare motors, mapping them to the names listed in the configuration (must be the same name!)
        leftFront  = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack  = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        // calibrate motor directions - VERY IMPORTANT! biggest problem area.
        // Check for consistency w/ setpower in while loop
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);


        // brake when no movement
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // no initial movement
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

        // instantiate soem numerical values
        double x, y, r, theta, turn;
        double rcos, rsin;

        waitForStart();
        while (opModeIsActive()) {
            // save to variables for ease
            x = gamepad1.left_stick_x;
            y = gamepad1.left_stick_y;

            // velocity magnitude
            r = Math.hypot(x, y);
            // velocity direction (angle)
            theta = Math.atan2(y, x);
            /*
            if (lx == 0 && ly == 0) ltheta = 0;
            else if (lx == 0)       ltheta = ly > 0 ? Math.PI/2 : 3*Math.PI/2;
            else if (ly == 0)       ltheta = lx > 0 ? 0 : 180;
            else                    ltheta = Math.copySign(Math.atan(ly/lx), lx) ;
             */

            turn = gamepad1.right_stick_x;

            rcos = r * Math.cos(theta);
            rsin = r * Math.sin(theta);

            // biggest problem area - check for consistency w/ motor inversion. Negate only the rcos/rsin?
            leftFront.setPower(rcos + turn);
            leftBack.setPower(rsin + turn);
            rightFront.setPower(rsin + turn);
            rightBack.setPower(rcos + turn);
        }
    }
}
