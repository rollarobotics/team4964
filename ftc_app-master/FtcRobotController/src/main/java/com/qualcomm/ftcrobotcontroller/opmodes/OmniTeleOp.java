package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.Range;

/**
 * Created by Callum on 2/16/16.
 */
public class OmniTeleOp extends OmniManager {

    int loopPart = 0;

    float frontLeftPower;
    float frontRightPower;
    float backLeftPower;
    float backRightPower;
    float leftX;
    float leftY;
    float rightX;
    float rightY;

    public OmniTeleOp() {}

    @Override
    public void init()
    {
        super.init();
    }

    @Override
    public void loop()
    {
        leftX = Range.clip(gamepad1.left_stick_x, -1, 1);
        leftY = Range.clip(gamepad1.left_stick_y, -1, 1);
        rightX = Range.clip(gamepad1.right_stick_x, -1, 1);
        rightY = Range.clip(gamepad1.right_stick_y, -1, 1);

        frontLeftPower = 0;
        frontRightPower = 0;
        backLeftPower = 0;
        backRightPower = 0;

        if(Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y))
        {
            //left / right
            loopPart = 1;
            backLeftPower += -leftX;
            frontLeftPower += leftX;
            backRightPower += leftX;
            frontRightPower += -leftX;
        }
        else if(Math.abs(gamepad1.left_stick_x) < Math.abs(gamepad1.left_stick_y))
        {
            //forward / backwards
            loopPart = 2;
            backLeftPower += -leftY;
            frontLeftPower += -leftY;
            backRightPower += -leftY;
            frontRightPower += -leftY;
        }
        else
        {
            if(Math.abs(gamepad1.right_stick_x) < 0.01)
            {
                //not moving
                loopPart = 3;
                backLeftPower = 0;
                frontLeftPower = 0;
                backRightPower = 0;
                frontRightPower = 0;
            }
            else
            {
                //turn left / right
                loopPart = 4;
                backLeftPower += rightX;
                frontLeftPower += rightX;
                backRightPower += -rightX;
                frontRightPower += -rightX;
            }
        }

        if(loopPart > -)
        {
            frontLeftPower -= (rightX * 2);
            backLeftPower -= (rightX * 2);
        }

        frontLeftPower = Range.clip(frontLeftPower, -1, 1);
        frontRightPower = Range.clip(frontRightPower, -1, 1);
        backLeftPower = Range.clip(backLeftPower, -1, 1);
        backRightPower = Range.clip(backRightPower, -1, 1);

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

        telemetry.addData("LoopPart", loopPart);
        telemetry.addData("FrontLeft", frontLeftPower);
        telemetry.addData("FrontRight", frontRightPower);
        telemetry.addData("BackLeft", backLeftPower);
        telemetry.addData("BackRight", backRightPower);
    }

}
