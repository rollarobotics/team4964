
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

//                               Robot Manageer extends OpMode
public class CallumClass extends RobotManager {

    //variables for preventing spaming
    private boolean runRB = true;
    private boolean runRT = true;
    private boolean runLB = true;
    private boolean runLT = true;
    private boolean runA = true;
    private boolean runB = true;

    public CallumClass() {

    }
    @Override
    public void init()
    {
        super.init();
    }
    @Override
    public void loop() {

        //drive train
        float powerLeft = -gamepad1.left_stick_y;
        float powerRight = -gamepad1.right_stick_y;
        powerLeft = Range.clip(powerLeft, -1, 1);
        powerRight = Range.clip(powerRight, -1, 1);
        motorRight.setPower(powerRight);
        motorLeft.setPower(powerLeft);

        //hook
        float powerHook = gamepad2.right_stick_y;
        powerHook = Range.clip(powerHook, -1, 1);
        hook.setPower(-powerHook);

        //winch
        float powerWinch = gamepad2.left_stick_y;
        powerWinch = Range.clip(powerWinch, -1, 1);
        winch.setPower(-powerWinch);

        //knockers
        //right knockers
        if(gamepad2.right_bumper && runRB)
        {
            runRB = false;
            //this will only run once per press
            knockerRight.setPosition(1);
        }
        else
            runRB = true;
        if(gamepad2.right_trigger > 0.01)
        {
            runRT = false;
            //this will only run once per press
            knockerRight.setPosition(0.5 - (gamepad2.right_trigger / 7.5));
        }
        else
            runRT = true;

        //left knocker
        if(gamepad2.left_bumper && runLB)
        {
            runLB = false;
            //this will only run once per press
            knockerLeft.setPosition(0);
        }
        else
            runLB = true;
        if(gamepad2.left_trigger > 0.01)
        {
            runLT = false;
            //this will only run once per press
            knockerLeft.setPosition(0.5 + (gamepad2.left_trigger / 7.5));
        }
        else
            runLT = true;

        //back climber
        if(gamepad2.a)
            backClimber.setPosition(0.5);
        if(gamepad2.b)
            backClimber.setPosition(1);
    }
    @Override
    public void stop ()
    {

    }
}

