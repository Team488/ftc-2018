package org.xbot.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.PushbotAutoDriveByEncoder_Linear;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.TimeUnit;

@Autonomous(name="Autonomous toCrater", group="Competition Opmodes")
public class AutonomousTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor armJoint = null;
    private DcMotor armExtender = null;
    private DcMotor intake = null;
    private Servo trapdoor = null;
    private Servo climbLock = null;
    private Servo dump = null;

    //encoder constants
    static final double COUNTS_PER_MOTOR_REV = 1120;
    static final double DRIVE_GEAR_REDUCTION = ((1.0/15.0)*4.0*(40.0/32.0)*(4.0/5.0)*(48.0/44.0));     // this is what theo told me
    static final double WHEEL_DIAMETER_INCHES = 3.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.3;
    static final double TURN_SPEED = 1.0;

    //servo constants
    private final double UNLOCKED = .75;
    private final double LOCKED = .25;


    public void runOpMode() {
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        armJoint = hardwareMap.get(DcMotor.class, "extend");
        armExtender = hardwareMap.get(DcMotor.class, "rotate");
        dump = hardwareMap.get(Servo.class, "dump");


        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armJoint.setDirection(DcMotor.Direction.FORWARD);
        armExtender.setDirection(DcMotor.Direction.FORWARD);
        dump.setDirection(Servo.Direction.FORWARD);


        armExtender.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



        telemetry.addData("Encoder Ticks: " , rightDrive.getMotorType().getTicksPerRev());
        telemetry.addData("Encoder Ticks: " , leftDrive.getMotorType().getTicksPerRev());
        telemetry.update();

        waitForStart();

       if (!isStopRequested()) { armExtender.setPower(1); } else { stopMotors(); }

        telemetry.addData("STATUS:", "Preparing to UNLOCK");
        waitSeconds(2000);
        if (!isStopRequested()) { armExtender.setPower(-.45); } else { stopMotors(); }

        telemetry.addData("STATUS:", "UNLOCKED, attempting to LAND");
        telemetry.update();
        waitSeconds(2500);
        armExtender.setPower(0);
        waitSeconds(1000);
        encoderDrive(.5,-36.0,36.0,5.0);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        telemetry.addData("STATUS:", "Taking a breather");
        telemetry.update();
        waitSeconds(500);
        encoderDrive(.5,48.0,48.0,5.0);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        //drive 4 ft forward
        telemetry.addData("STATUS:", "Zooming to crater.");
       telemetry.addData("STATUS:", "I should be sitting in the crater right now!");
    }

    public void waitSeconds(double waitTime) {
        double currentTime = runtime.time(TimeUnit.MILLISECONDS);
        while (currentTime + waitTime > runtime.time(TimeUnit.MILLISECONDS)) {
        }
    }

    public void stopMotors() {
        rightDrive.setPower(0);
        leftDrive.setPower(0);
        armExtender.setPower(0);
        armJoint.setPower(0);
    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = (leftDrive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH));
            newRightTarget = (rightDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH));
            leftDrive.setTargetPosition(newLeftTarget);
            rightDrive.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            // reset the timeout time and start motion.
            runtime.reset();
            if(leftInches<0){
                leftDrive.setPower(-speed);
            } else {
                leftDrive.setPower(speed);
            }
            if(rightInches<0) {
                rightDrive.setPower(-speed);
            } else {
                rightDrive.setPower(speed);
            }


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (Math.abs(leftDrive.getCurrentPosition()) < Math.abs(newLeftTarget) ||Math.abs(rightDrive.getCurrentPosition()) < Math.abs(newRightTarget))) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        leftDrive.getCurrentPosition(),
                        rightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;

            // Turn off RUN_TO_POSITION
            leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }



}
