

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class CallumAutoNew extends RobotManager {

    int leftEncoder = 0;
    int rightEncoder = 0;
    int currentAction = 1;
    int wait = 0;
    boolean finished = false;
    float delta = 1;

    public CallumAutoNew() {
    }

    @Override
    public void init() {
        super.init();
        motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }


    @Override
    public void start() {
        motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorLeft.setPower(1.0f);
        motorRight.setPower(1.0f);
    }

    @Override

    public void loop() {



        rightEncoder = -motorLeft.getCurrentPosition();
        leftEncoder = -motorRight.getCurrentPosition();

        telemetry.addData("CurrentAction", currentAction);
        telemetry.addData("LeftEncoder", leftEncoder);
        telemetry.addData("RightEncoder", rightEncoder);
        telemetry.addData("Wait", wait);
        telemetry.addData("IsFinished", finished);

        switch(currentAction)
        {
            case 1:
                if(leftEncoder >= 4000)
                    motorLeft.setPower(0.0f);
                if(rightEncoder >= 4000)
                    motorRight.setPower(0.0f);
                if(leftEncoder >= 4000 && rightEncoder >= 4000) {
                    motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    currentAction++;
                }
                break;
            case 2:
                wait++;
                if(wait >= 100) {
                    wait = 0;
                    motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                    motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                    currentAction++;
                }
                break;
            case 3:
                motorRight.setPower(-1.0f);
                motorLeft.setPower(1.0f);
                currentAction++;
                break;
            case 4:
                if(rightEncoder <= -1000)
                    motorRight.setPower(0.0f);
                if(leftEncoder >= 1000)
                    motorLeft.setPower(0.0f);
                if(rightEncoder <= -1000 && leftEncoder >= 1000) {
                    motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    currentAction++;
                }
                break;
            case 5:
                wait++;
                if(wait >= 100) {
                    wait = 0;
                    motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                    motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                    currentAction++;
                }
                break;
            case 6:
                motorRight.setPower(1.0f);
                motorLeft.setPower(1.0f);
                currentAction++;
                break;
            case 7:
                if(rightEncoder >= 5650)
                    motorRight.setPower(0.0f);
                if(leftEncoder >= 5650)
                    motorLeft.setPower(0.0f);
                if(rightEncoder >= 5650 && leftEncoder >= 5650) {
                    motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    currentAction++;
                }
                break;
            case 8:
                wait++;
                if(wait >= 100) {
                    wait = 0;
                    motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                    motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                    currentAction++;
                }
                break;
            case 9:
                motorLeft.setPower(1.0f);
                currentAction++;
                break;
            case 10:
                if(leftEncoder >= 1200)
                    motorLeft.setPower(0.0f);
                if(leftEncoder >= 1200) {
                    motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    currentAction++;
                }
                break;
            case 11:
                wait++;
                if(wait >= 100) {
                    wait = 0;
                    motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                    motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                    currentAction++;
                }
                break;
            case 12:
                motorRight.setPower(0.75f);
                motorLeft.setPower(0.75f);
                currentAction++;
                break;
            case 13:
                if(rightEncoder >= 500)
                    motorRight.setPower(0.0f);
                if(leftEncoder >= 500)
                    motorLeft.setPower(0.0f);
                if(rightEncoder >= 500 && leftEncoder >= 500) {
                    motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    currentAction++;
                }
                break;
            case 14:
                if(backClimber.getPosition()>=0.5)
                    delta -= 0.0075;
                backClimber.setPosition(delta);
                if(delta <= 0.5)
                    currentAction++;
                break;
            case 15:
                motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                motorLeft.setPower(-0.5f);
                motorRight.setPower(-0.5f);
                currentAction++;
                break;
            case 16:
                if(rightEncoder <= -1000)
                    motorRight.setPower(0.0f);
                if(leftEncoder <= -1000)
                    motorLeft.setPower(0.0f);
                if(rightEncoder <= -1000 && leftEncoder <= -1000) {
                    motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    currentAction++;
                }
                break;
            case 17:
                finished = true;
                break;
            default:
                break;

        }




    }
    public void stop(){
        motorLeft.setPower(0.0f);
        motorRight.setPower(0.0f);
    }
}