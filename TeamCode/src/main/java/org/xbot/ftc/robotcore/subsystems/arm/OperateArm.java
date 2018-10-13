package org.xbot.ftc.robotcore.subsystems.arm;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.xbot.ftc.robotcore.subsystems.intake.Intake;

public class OperateArm {
    private Arm arm;

    protected OperateArm(Arm arm) {this.arm = arm;}

    public void rotate(Gamepad gamepad) {
        if(gamepad.left_stick_y > .1 || gamepad.left_stick_y < -.1) {
            arm.setRotationPower(gamepad.left_stick_y);
        } else {
            //do something to hold the arm in place
        }
    }
    public void extend(Gamepad gamepad) {
        if(gamepad.right_stick_y > .1 || gamepad.right_stick_y < -.1) {
            arm.setExtensionPower(gamepad.right_stick_y);
        } else {
            //do something to hold the arm in place
        }
    }



    public void stop() {
        arm.setRotationPower(0);
        arm.setExtensionPower(0);
    }
}
