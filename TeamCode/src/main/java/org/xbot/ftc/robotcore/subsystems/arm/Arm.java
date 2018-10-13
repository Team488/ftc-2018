package org.xbot.ftc.robotcore.subsystems.arm;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;
import org.xbot.ftc.robotcore.subsystems.intake.Intake;
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
    public void autonomousRotation(double time, double power) {
        double timeToRotate = GameClock.getInstance().getTimeElapsed() + time;
        setRotationPower(power);
        while(timeToRotate > GameClock.getInstance().getTimeElapsed()) {
        }

    }

    public void autonomousExtension(double time, double power) {
        double timeToExtend = GameClock.getInstance().getTimeElapsed() + time;
        setExtensionPower(power);
        while(timeToExtend > GameClock.getInstance().getTimeElapsed()) {
        }

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
        return Intake.class.getName();
    }

    public static XbotSubsystem getInstance() {
        if (instance == null)
            instance = new Arm();
        return instance;
    }
}
