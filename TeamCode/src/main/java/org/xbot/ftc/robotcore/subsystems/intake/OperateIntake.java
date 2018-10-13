package org.xbot.ftc.robotcore.subsystems.intake;

import com.qualcomm.robotcore.hardware.Gamepad;

public class OperateIntake {
    private Intake intake;

    protected OperateIntake(Intake intake) {this.intake = intake;}

    public void intake(Gamepad gamepad) {
        if (gamepad.left_bumper) {
            intake.setIntakePower(1);
        } else if (gamepad.right_bumper) {
            intake.setIntakePower(-1);
        } else {
            intake.setIntakePower(0);
        }
    }
    public void stop() {
        intake.setIntakePower(0);
    }
}
