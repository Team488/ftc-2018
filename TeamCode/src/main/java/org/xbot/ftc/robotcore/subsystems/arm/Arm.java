package org.xbot.ftc.robotcore.subsystems.arm;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;
// import org.xbot.ftc.robotcore.subsystems.intake.Intake;
import org.xbot.ftc.robotcore.utils.GameClock;

public class Arm extends XbotSubsystem {

    private static XbotSubsystem instance = null;
    private static boolean initialized = false;

    private DcMotor rotationMotor = null;
    private DcMotor extensionMotor = null;

    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;
        super.init(hardwareMap, telemetry);
        rotationMotor = hardwareMap.get(DcMotor.class, XbotRobotConstants.ROTATION_MOTOR);
        extensionMotor = hardwareMap.get(DcMotor.class, XbotRobotConstants.EXTENSION_MOTOR);
        rotationMotor.setDirection((DcMotorSimple.Direction.FORWARD));
        extensionMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        initialized = true;
    }

    public double getCurrentAngle() {
        // get angle from a gyro or encoder or something
        return 90;
   }
    public void holdRotation() {
        //pid loop to hold rotation
    }


    public void autonomousRotation(double angle) {

        //rotate to angle
        if (0<=angle && angle<=180) {
            if (getCurrentAngle() > angle) {
                while (getCurrentAngle() > angle) {
                    setRotationPower(.5);
                }
                holdRotation();
            } else if (getCurrentAngle() < angle) {
                while (getCurrentAngle() < angle) {
                    setRotationPower(-.5);
                } holdRotation();
            }
        } else{
            telemetry.addData("ERROR: ","Unsupported Angle!");
        }

    }

    public void autonomousExtension(double time, double power) {
        double timeToExtend = GameClock.getInstance().getTimeElapsed() + time;
        setExtensionPower(power);
        //wait until it hits a limit switch or goes a certain distance according to encoders
        while(timeToExtend > GameClock.getInstance().getTimeElapsed()) {
            setExtensionPower(.05);
        }
        setExtensionPower(0);
    }

    public void setExtensionPower(double power) {
        extensionMotor.setPower(power);
    }
    public void setRotationPower(double power) {
        rotationMotor.setPower(power);
    }

    @Override
    public void shutdownSubsystem() {
        setExtensionPower(0);
        setRotationPower(0);
        initialized = false;
    }
    @Override
    public String getClassName() {
        return Arm.class.getName();
    }

    public static XbotSubsystem getInstance() {
        if (instance == null)
            instance = new Arm();
        return instance;
    }
}
