package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.libs.Globals.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.libs.AutoImport;

@Autonomous(name="R1 - RedAutoStorage", group="redAuto")
public class RedAutoStorage extends AutoImport {

    public RedAutoStorage() { super(65, -40, 68, 215, 158, 215); }

    public void runOpMode() {
        super.runOpMode();

        if (opModeIsActive()) {
            // Goes to spinner and does spinny
            slauto.drive(50, -60, -90, 0.75, 4000, this, true, true, false, false);
            setSpinny(true, 1000);

            // Goes to the shipping hub and delivers based on the team element position
            // 1 is added to elementPosition because height's 0 is ground level, not the first layer
            setArm(elementPosition + 1, 1);
            sleep(500);
            slauto.drive(25, -60, -90, 0.75, 0, this, false, true, false, false);
            slauto.drive(25, -33, -90, 0.75, 0, this, true, false, false, false);
            deposit(true);

            // Does a little shimmy if it is in the highest goal, as it needs a bit of help to drop
            sleep(500);
            if (elementPosition == 2) {
                shimmy(0.8, 1, 100);
            } else {
                sleep(200);
            }

            deposit(false);

            // Goes over to the warehouse
            slauto.drive(25, -55, -90, 0.75, 3000, this, false, true, false, false);
            setArm(3, 1);
            slauto.drive(47, -57, 0, 0.75, 3000, this, true, false, false, false);

            // Waits for other team before moving
            while (timer.seconds() < 24) {
                sleep(10);
            }

            // Goes into the warehouse
            slauto.drive(64, 0, 0, 0.5, 0, this, false, true, false, false);
            slauto.drive(70, 40, 0, 0.9, 0, this, false, false, true, false);

            // Lowers arm
            setArm(0, 1);

            while (timer.seconds() < 30) {
                sleep(100);
            }
        }
        stopCamera();
    }
}
