package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="controller")

public class Controller extends OpMode {

    DcMotor leftWheel;
    DcMotor rightWheel;
    DcMotor armMotor;
    Servo leftservo;
    Servo rightservo;

    @Override
    public  void init() {
        leftWheel = hardwareMap.dcMotor.get("motor0");
        rightWheel = hardwareMap.dcMotor.get("motor1");
        armMotor = hardwareMap.dcMotor.get("motor2");
        leftservo = hardwareMap.servo.get("servo0");
        rightservo = hardwareMap.servo.get("servo1");
    }

    @Override
    public void  loop() {
        float steering = gamepad1.left_stick_x;
        float forward = gamepad1.right_trigger;
        float reverse = gamepad1.left_trigger;
        boolean handbrake = gamepad1.right_bumper;
        boolean armUp = gamepad2.dpad_up;
        boolean armDown = gamepad2.dpad_down;

        if (forward > 0) {
            leftWheel.setPower(forward);
            rightWheel.setPower(-forward);
        } else if (reverse > 0) {
            leftWheel.setPower(-reverse);
            rightWheel.setPower(reverse);
        } else if (steering > 0 || steering < 0) {
            leftWheel.setPower(steering);
            rightWheel.setPower(steering);
        } else if (handbrake) {
            leftWheel.setPower(0);
            rightWheel.setPower(0);
        } else {
            leftWheel.setPower(0);
            rightWheel.setPower(0);
        }

        if (armUp) {
            armMotor.setPower(0.2);
        } else if (armDown) {
            armMotor.setPower(-0.2);
        } else {
            armMotor.setPower(0);
        }

        if(gamepad2.y) {
            // move to 90 degrees.
            leftservo.setPosition(0.5);
        } else if (gamepad2.a) {
            leftservo.setPosition(leftservo.getPosition()-0.001);
            gamepad2.a=false;
        } else if (gamepad2.b) {
            leftservo.setPosition(leftservo.getPosition()+0.001);
            gamepad2.b=false;
        }else if (gamepad2.x) {
            // move to 180 degrees.
            leftservo.setPosition(1);
        }
    }
}