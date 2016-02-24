package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;


public class NewTeleOp extends OpMode {
    DcMotor motorRight;
    DcMotor motorLeft;
    //  DcMotor sweeper;
    DcMotor winch;
    DcMotor hook;
    Servo   backClimber;
    Servo   knockerRight;
    Servo   knockerLeft;
    boolean finishedForward = true;
    boolean finishedTurning = false;
    int newValue;
    long waitValue = 500;
//   int currentState;

    // adding current position which starts at getPosition, then
    // add desired movement each move.JCF/DH 1/21/16
    //int RightCurPosition = 0;
   // int LeftCurPosition = 0;
    //int targetDistance = 0;


    /**
     * Constructor
     */
    public NewTeleOp() {

    }

    @Override
    public void init() {
        // currentState = 1;
        knockerRight = hardwareMap.servo.get("knockerRight"); //servo_3
        knockerLeft = hardwareMap.servo.get("knockerLeft"); //servo_2
        backClimber = hardwareMap.servo.get("backClimber"); //servo_4

        // sweeper = hardwareMap.dcMotor.get("motor_5");
        hook = hardwareMap.dcMotor.get("hookMotor"); //motor_3
        winch = hardwareMap.dcMotor.get("winchMotor"); //motor_4
        motorLeft = hardwareMap.dcMotor.get("leftMotor"); //motor_2
        motorRight = hardwareMap.dcMotor.get("rightMotor"); //motor_1

        // physical motors are mounted 180 from each other to both face out to wheels.
        // So, to have robot go forward, one motor needs to be reversed.
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // 1/21/16 - adding (motor mode) Reset Encoders before start loop.
        motorRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);



        telemetry.addData("Text", "***RESETTING***");

        //  motorRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        //  motorLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        motorRight.setTargetPosition(0);
        motorRight.setPower(0.0);
        motorLeft.setTargetPosition(0);
        motorLeft.setPower(0.0);

        knockerLeft.setPosition(.52);
        knockerRight.setPosition(.52);
        backClimber.setPosition(.52);
        //REMEMBER: 0.0 to .5 is forward, while .5 to 1.0 is backwards.

    }


    @Override
    public void start() {

        motorRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        //  currentState = 1;

        telemetry.addData("Text", "***Starting***");

        motorRight.setTargetPosition(5000);
        motorRight.setPower(.75);
        //   motorLeft.setTargetPosition(2500);
        motorLeft.setPower(.75);
    }

    @Override

    public void loop() {
        telemetry.addData("rightEncoder = ", motorRight.getCurrentPosition());
        if (motorRight.getCurrentPosition() <= -5000) {

            if(finishedForward) {

                finishedForward = false;
                finishedTurning = true;
                telemetry.addData("I did it!", motorRight.getCurrentPosition());

             //   wait(waitValue);

                newValue = motorRight.getCurrentPosition() - 250;

                motorRight.setTargetPosition(-newValue);
                motorRight.setPower(.50);
                motorLeft.setPower(-.50);

            }

        }

        if(motorRight.getCurrentPosition() <= -newValue){

            if(finishedTurning) {

                finishedTurning = false;

                motorRight.setPower(0);
                motorLeft.setPower(0);
            }
        }

    }

    public void stop(){



    }
}

