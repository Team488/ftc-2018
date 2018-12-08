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

@Autonomous(name="Autonomous noDrive", group="Competition Opmodes")
public class AutonomousStill extends LinearOpMode {

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
    static final double DRIVE_GEAR_REDUCTION = ((1.0/15.0)*4.0*(40.0/32.0)*(4.0/5.0));     // this is what theo told me
    static final double WHEEL_DIAMETER_INCHES = 3.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.3;
    static final double TURN_SPEED = 1;

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
        waitSeconds(2);
        if (!isStopRequested()) { armExtender.setPower(-.45); } else { stopMotors(); }
        telemetry.addData("STATUS:", "UNLOCKED, attempting to LAND");
        telemetry.update();
        waitSeconds(2.5);
        armExtender.setPower(0);
        waitSeconds(2);
        if (!isStopRequested()) { rightDrive.setPower(.6); } else { stopMotors(); }
        if (!isStopRequested()) { leftDrive.setPower(-.6); } else { stopMotors(); }
        waitSeconds(1.65);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void waitSeconds(double waitTime) {
        double currentTime = runtime.time(TimeUnit.SECONDS);
        while (currentTime + waitTime > runtime.time(TimeUnit.SECONDS)) {
        }
    }

    public void stopMotors() {
        rightDrive.setPower(0);
        leftDrive.setPower(0);
        armExtender.setPower(0);
        armJoint.setPower(0);
    }



}
