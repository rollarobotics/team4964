

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class CallumAuto extends RobotManager {

    int leftEncoder = 0;
    int rightEncoder = 0;
    int currentAction = 1;
    int wait = 0;

    public CallumAuto() {
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

        switch(currentAction)
        {
            case 1:
                if(leftEncoder >= 4000)
                    motorLeft.setPower(0.0f);
                if(rightEncoder >= 4000)
                    motorRight.setPower(0.0f);
                if(leftEncoder >= 4000 && rightEncoder >= 4000)
                    currentAction++;
                break;
            case 2:
                wait++;
                if(wait >= 100) {
                    wait = 0;
                    currentAction++;
                }
                break;
            case 3:
                motorRight.setPower(-1.0f);
                motorLeft.setPower(1.0f);
                currentAction++;
                break;
            case 4:
                if(rightEncoder <= 2000)
                  motorRight.setPower(0.0f);
                if(leftEncoder >= 6000)
                  motorLeft.setPower(0.0f);
                if(rightEncoder <= 2000 && leftEncoder >= 6000)
                  currentAction++;
                break;
            case 5:
               wait++;
                if(wait >= 100) {
                    wait = 0;
                    currentAction++;
                }
               break;
            case 6:
                motorRight.setPower(1.0f);
                motorLeft.setPower(1.0f);
                currentAction++;
               break;
            case 7:
               if(rightEncoder <= 2000)
                  motorRight.setPower(0.0f);
                if(leftEncoder >= 6000)
                  motorLeft.setPower(0.0f);
                if(rightEncoder <= 2000 && leftEncoder >= 6000)
                  currentAction++;
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