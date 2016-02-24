package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.Range;

/**
 * Created by Callum on 2/16/16.
 */
public class OmniTeleOp extends OmniManager {

    public OmniTeleOp()
    {

    }

    @Override
    public void init()
    {
        super.init();
    }

    @Override
    public void loop()
    {
        if(Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y))
        {
            backLeft.setPower(Range.clip(gamepad1.left_stick_x, -1, 1));
            frontLeft.setPower(Range.clip(gamepad1.left_stick_x, -1, 1));
            backRight.setPower(Range.clip(-gamepad1.left_stick_x, -1, 1));
            frontRight.setPower(Range.clip(-gamepad1.left_stick_x, -1, 1));
        }
        else if(Math.abs(gamepad1.left_stick_x) < Math.abs(gamepad1.left_stick_y))
        {
            backLeft.setPower(Range.clip(-gamepad1.left_stick_y, -1, 1));
            frontLeft.setPower(Range.clip(-gamepad1.left_stick_y, -1, 1));
            backRight.setPower(Range.clip(-gamepad1.left_stick_y, -1, 1));
            frontRight.setPower(Range.clip(-gamepad1.left_stick_y, -1, 1));
        }
        else
        {
            if(Math.abs(gamepad1.right_stick_x) < 0.01)
            {
                backLeft.setPower(0);
                frontLeft.setPower(0);
                backRight.setPower(0);
                frontRight.setPower(0);
            }
            else
            {
                double speed = gamepad1.right_stick_x;
                backLeft.setPower(Range.clip(-speed, -1, 1));
                frontLeft.setPower(Range.clip(speed, -1, 1));
                backRight.setPower(Range.clip(speed, -1, 1));
                frontRight.setPower(Range.clip(-speed, -1, 1));
            }
        }
    }

}
