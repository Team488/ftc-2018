//package org.xbot.ftc.robotcore.subsystems.intake;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.xbot.ftc.robotcore.XbotRobotConstants;
import org.xbot.ftc.robotcore.subsystems.XbotSubsystem;
import org.xbot.ftc.robotcore.utils.GameClock;

/*
public class Intake extends XbotSubsystem {

    private static XbotSubsystem instance = null;
    private static boolean initialized = false;

    private DcMotor intakeMotor = null;

    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        if (initialized) return;
        super.init(hardwareMap, telemetry);
        intakeMotor = hardwareMap.get(DcMotor.class, XbotRobotConstants.INTAKE_MOTOR);
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        initialized = true;
    }
    public void timedIntake(double time, double power) {
       double timeToIntake = GameClock.getInstance().getTimeElapsed() + time;
       setIntakePower(power);
       while(timeToIntake > GameClock.getInstance().getTimeElapsed()) {
       }

    }

    public void setIntakePower(double power) {
        intakeMotor.setPower(power);
    }
    @Override
    public void shutdownSubsystem() {
        setIntakePower(0);
        initialized = false;
    }
    @Override
    public String getClassName() {
        return Intake.class.getName();
    }

    public static XbotSubsystem getInstance() {
        if (instance == null)
            instance = new Intake();
        return instance;
    }

}
*/