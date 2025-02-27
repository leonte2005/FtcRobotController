package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.libs.Globals.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.libs.AutoImport;

@Autonomous(name="B1 - BlueAutoStorage", group="blueAuto")
public class BlueAutoStorage extends AutoImport {

    public BlueAutoStorage() { super(0,  0, 150, 115, 150, 115); }

    public void runOpMode() {
        super.runOpMode();

        if (opModeIsActive()) {
            // Goes to spinner and does spinny
            //slauto.drive(50, 60, 0, 0.75, 4000, this, true, true, false, false);
            //setSpinny(false, 1000);

            // Goes to the shipping hub and delivers based on the team element position
            // 1 is added to elementPosition because height's 0 is ground level, not the first layer
            //setArm(elementPosition + 1, 1);
            //sleep(1000);
            //slauto.drive(20, 60, 90, 0.75, 0, this, false, true, false, false);
            //slauto.drive(25, 33, 90, 0.75, 0, this, true, false, false, false);
            deposit(true);

            // Does a little shimmy if it is in the highest goal, as it needs a bit of help to drop
            //sleep(500);
            if (elementPosition == 1) {
                slauto.drive(0, 27, 0, 0.7, 4000, this, true, true, false, false);
            }
            if (elementPosition == 2) {
                slauto.drive(0, 27, 0, 0.7, 4000, this, true, true, false, false);
                sleep(500);
                slauto.drive(25, 27, 0, 0.7, 4000, this, true, true, false, false);
            }
            if (elementPosition == 0) {
                slauto.drive(0, 27, 0, 0.7, 4000, this, true, true, false, false);
                sleep(500);
                slauto.drive(-25, 27, 0, 0.7, 4000, this, true, true, false, false);
            }

            deposit(false);

            // Goes over to the warehouse
            //slauto.drive(25, 55, 90, 0.75, 3000, this, false, true, false, false);
            //setArm(3, 1);
            //slauto.drive(47, 57, 0, 0.75, 3000, this, true, false, false, false);

            // Waits for other team before moving
            while (timer.seconds() < 24) {
                sleep(10);
            }

            // Goes into the warehouse
            //slauto.drive(64, 0, 0, 0.5, 0, this, false, true, false, false);
            //slauto.drive(70, -40, 0, 0.9, 0, this, false, false, true, false);

            // Lowers arm
            //setArm(0, 1);

            while (timer.seconds() < 30) {
                sleep(100);
            }
        }
        stopCamera();
    }
}
