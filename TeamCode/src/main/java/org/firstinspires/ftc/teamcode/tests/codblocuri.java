package org.firstinspires.ftc.teamcode.tests;

import static org.firstinspires.ftc.teamcode.libs.Globals.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.libs.AutoImport;

@Autonomous(name = "FakeAutonom")
public class codblocuri extends AutoImport {

    public codblocuri() { super(0,  0, 0, 0, 0, 0); }

    private DcMotor armX;
    private DcMotor armY;
    private DcMotor light;
    private Servo fork;
    private DcMotor rr;
    private DcMotor fr;
    private DcMotor intake;
    private DcMotor rl;
    private DcMotor fl;

    int target_stanga;
    int target_stanga_1;
    int target_sus_high;
    int target_sus_5, target_sus_4, target_sus_3, target_sus_2, target_sus_1;
    int pozitie_verticala_brat;
    int pozitie_orizontala_brat;
    int rotatie_orizontala_curenta_brat;

    public codblocuri(int startX, int startY, int cam1X, int cam1Y, int cam2X, int cam2Y) {
        super(startX, startY, cam1X, cam1Y, cam2X, cam2Y);
    }

    /**
     * Describe this function...
     */
    private void roteste_brat_stanga() {
        if (pozitie_orizontala_brat == 0 && pozitie_verticala_brat == 1) {
            alimenteaza_pentru_rotatie_stanga();
        }
    }

    private void roteste_brat_stanga_1() {
        if (pozitie_orizontala_brat == 0 && pozitie_verticala_brat == 1) {
            alimenteaza_pentru_rotatie_stanga_1();
        }
    }

    /**
     * Describe this function...
     */
    private void roteste_brat_dreapta() {
        if (pozitie_orizontala_brat == 1) {
            alimenteaza_pentru_rotatie_dreapta();
        }
    }

    /**
     * Describe this function...
     */
    private void masurare_inaltime() {
        telemetry.addData("Poz br 1: ", armX.getCurrentPosition());
        telemetry.update();
    }

    /**
     * Describe this function...
     */
    private void alimenteaza_pentru_rotatie_stanga_1() {
        light.setDirection(DcMotorSimple.Direction.REVERSE);
        light.setTargetPosition(target_stanga_1);
        light.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotatie_orizontala_curenta_brat = light.getCurrentPosition();
        while (light.getCurrentPosition() <= target_stanga_1) {
            light.setPower(0.4);
        }
        light.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pozitie_orizontala_brat = 1;
    }

    private void alimenteaza_pentru_rotatie_stanga() {
        light.setDirection(DcMotorSimple.Direction.FORWARD);
        light.setTargetPosition(target_stanga);
        light.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotatie_orizontala_curenta_brat = light.getCurrentPosition();
        while (light.getCurrentPosition() <= target_stanga) {
            light.setPower(0.4);
        }
        light.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pozitie_orizontala_brat = 1;
    }




    /**
     * prinde conul
     */
    private void prinde_con() {
        fork.setPosition(1);
    }

    /**
     * elibereaza conul
     */
    private void elibereaza_con() {
        fork.setPosition(0.5);
    }



    /**
     * Describe this function...
     */
    private void alimenteaza_pentru_rotatie_dreapta() {
        light.setDirection(DcMotorSimple.Direction.REVERSE);
        light.setTargetPosition(light.getCurrentPosition() / light.getCurrentPosition() - 1);
        light.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotatie_orizontala_curenta_brat = light.getCurrentPosition();
        while (light.getCurrentPosition() <= light.getTargetPosition()) {
            light.setPower(0.4);
        }
        light.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pozitie_orizontala_brat = 0;
    }

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    //@Override
    public void runOpMode() {
        super.runOpMode();
        double pivot;
        int pozitie_curenta_brat;
        int pozitie_brat;
        double vertical;
        double horizontal;

        armX = hardwareMap.get(DcMotor.class, "armX");
        armY = hardwareMap.get(DcMotor.class, "armY");
        light = hardwareMap.get(DcMotor.class, "light");
        fork = hardwareMap.get(Servo.class, "fork");
        rr = hardwareMap.get(DcMotor.class, "rr");
        fr = hardwareMap.get(DcMotor.class, "fr");
        intake = hardwareMap.get(DcMotor.class, "intake");
        rl = hardwareMap.get(DcMotor.class, "rl");
        fl = hardwareMap.get(DcMotor.class, "fl");

        // Put initialization blocks here.
        fork.setPosition(1);
        light.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armX.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armY.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pozitie_orizontala_brat = 0;
        pozitie_verticala_brat = 0;
        rotatie_orizontala_curenta_brat = 0;
        target_stanga = 650;
        target_stanga_1 = 900;
        target_sus_high = 1500;
        target_sus_5 = 500;
        target_sus_4 = 420;
        target_sus_3 = 330;
        target_sus_2 = 230;
        target_sus_1 = 80;
        pozitie_curenta_brat = 0;
        pozitie_brat = 0;
        //rr.setDirection(DcMotorSimple.Direction.REVERSE);
        //fr.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                prinde_con();
                slauto.drive(32, 0, 0, 0.8, 9000, this, true, true, false, false);
                ridica_brat_high_1();
                roteste_brat_stanga_1();
                sleep(500);
                elibereaza_con();
                sleep(500);
                alimenteaza_pentru_rotatie_dreapta();
                coboara_brat();
                slauto.drive(52, 0, 0, 0.8, 9000, this, true, true, false, false);
                slauto.drive(52, 0, 0, 0.8, 9000, this, true, true, false, false);
                sleep(300);
                sleep(5000000);
                //prinde conul 4
                ridica_brat4();
                slauto.drive(-17, 51, 90, 0.6, 9000, this, true, true, false, false);
                sleep(100);
                prinde_con();
                sleep(300);
                ridica_brat_high();
                slauto.drive(0, 52, 90, 0.6, 9000, this, true, true, false, false);
                roteste_brat_stanga();
                sleep(200);
                elibereaza_con();
                sleep(300);
                roteste_brat_dreapta();
                coboara_brat();
                //aici se termina tura pentru 4


                //prinde conul 3

                ridica_brat3();
                slauto.drive(-19, 51, 90, 0.6, 9000, this, true, true, false, false);
                sleep(100);
                prinde_con();
                sleep(300);
                ridica_brat_high();
                slauto.drive(0, 52, 90, 0.6, 9000, this, true, true, false, false);
                roteste_brat_stanga();
                sleep(200);
                elibereaza_con();
                sleep(300);
                roteste_brat_dreapta();
                coboara_brat();
                //aici se termina tura pentru 3

                ridica_brat2();
                slauto.drive(-23, 51, 90, 0.6, 9000, this, true, true, false, false);
                sleep(300);
                prinde_con();
                sleep(300);
                ridica_brat_high();
                slauto.drive(0, 52, 90, 0.6, 9000, this, true, true, false, false);
                roteste_brat_stanga();
                sleep(200);
                elibereaza_con();
                sleep(300);
                roteste_brat_dreapta();
                coboara_brat();

                ridica_brat1();
                slauto.drive(-23, 51, 90, 0.6, 9000, this, true, true, false, false);
                sleep(300);
                prinde_con();
                sleep(300);
                ridica_brat_high();
                slauto.drive(0, 52, 90, 0.6, 9000, this, true, true, false, false);
                roteste_brat_stanga();
                sleep(200);
                elibereaza_con();
                sleep(300);
                roteste_brat_dreapta();
                coboara_brat();





                sleep(50000);
                ridica_brat_high();
                slauto.drive(0, 10, 0, 0.8, 9000, this, true, true, false, false);
                roteste_brat_stanga();
                sleep(300);
                elibereaza_con();
                sleep(400);
                roteste_brat_dreapta();
                coboara_brat();
                ridica_brat2();
                slauto.drive(0, 23, 0, 0.7, 9000, this, true, true, false, false);
                //sleep(5000000);
                prinde_con();
                sleep(500);
                ridica_brat_high();
                slauto.drive(0, 10, 0, 0.7, 9000, this, true, true, false, false);
                roteste_brat_stanga();
                sleep(300);
                elibereaza_con();
                sleep(400);
                roteste_brat_dreapta();
                coboara_brat();
                //luam ultimul con
                ridica_brat1();
                slauto.drive(0, 25, 0, 0.7, 9000, this, true, true, false, false);
                //sleep(5000000);
                prinde_con();
                sleep(500);
                ridica_brat_high();
                slauto.drive(0, 10, 0, 0.7, 9000, this, true, true, false, false);
                roteste_brat_stanga();
                sleep(300);
                elibereaza_con();
                sleep(400);
                roteste_brat_dreapta();
                coboara_brat();

                sleep(500000);
                //roteste_brat_stanga();
                //sleep(500);
                elibereaza_con();
                sleep(500);
                roteste_brat_dreapta();
                //sleep(200);
                coboara_brat();
                //urmeaza tur retur ia conuri si le pune pe prajina
                slauto.drive(0, 30, 90, 0.5, 9000, this, false, true, false, false);
                sleep(100);
                slauto.drive(-20, 30, 90, 0.5, 9000, this, false, true, false, false);
                prinde_con();
                sleep(300);
                ridica_brat1();
                roteste_brat_stanga();
                elibereaza_con();
                sleep(400);
                roteste_brat_dreapta();
                coboara_brat();
                slauto.drive(0, 30, 90, 0.5, 9000, this, false, true, false, false);
                sleep(20000);
                // VERTICAL & ORIZONTAL & PIVOT
                vertical = -(gamepad2.right_stick_y * gamepad2.right_stick_y * gamepad2.right_stick_y * 0.6);
                horizontal = gamepad2.right_stick_x * gamepad2.right_stick_x * gamepad2.right_stick_x * 0.6;
                pivot = gamepad2.left_stick_x * gamepad2.left_stick_x * gamepad2.left_stick_x * 0.5;
                if (horizontal < Math.abs(0.1)) {
                    horizontal += 0;
                    frana();
                }
                if (vertical < Math.abs(0.1)) {
                    vertical += 0;
                    frana();
                }
                if (pivot < Math.abs(0.1)) {
                    pivot += 0;
                    frana();
                }
                telemetry.addData("Poz. Vert. Brat:", pozitie_verticala_brat);
                telemetry.addData("Inaltime curenta", armX.getCurrentPosition());
                telemetry.addData("Poz. oriz. Brat:", pozitie_orizontala_brat);
                telemetry.update();
                rr.setPower((pivot + (horizontal - vertical)) * 0.9);
                rl.setPower((-pivot + horizontal + vertical) * 0.9);
                fr.setPower((pivot + vertical + horizontal) * 0.9);
                fl.setPower((-pivot + (horizontal - vertical)) * 0.9);
            }
        }
    }

    /**
     * Describe this function...
     */
    private void coboara_brat() {
        if (pozitie_verticala_brat == 1 && pozitie_orizontala_brat == 0) {
            alimenteaza_pentru_coborare_brat();
        }
    }

    /**
     * Ridica bratul pe niveluri
     */
    private void ridica_brat1() {
        if (pozitie_verticala_brat == 0) {
            alimenteaza_pentru_ridicare_brat1();
        }
    }


    /**
     * Ridica bratul pe niveluri
     */
    private void ridica_brat2() {
        if (pozitie_verticala_brat == 0) {
            alimenteaza_pentru_ridicare_brat2();
        }
    }

    /**
     * Ridica bratul pe niveluri
     */
    private void ridica_brat3() {
        if (pozitie_verticala_brat == 0) {
            alimenteaza_pentru_ridicare_brat3();
        }
    }

    /**
     * Ridica bratul pe niveluri
     */
    private void ridica_brat4() {
        if (pozitie_verticala_brat == 0) {
            alimenteaza_pentru_ridicare_brat4();
        }
    }

    /**
     * Ridica bratul pe niveluri
     */
    private void ridica_brat5() {
        if (pozitie_verticala_brat == 0) {
            alimenteaza_pentru_ridicare_brat5();
        }
    }

    /**
     * Ridica bratul pe niveluri
     */
    private void ridica_brat_high() {
            alimenteaza_pentru_ridicare_brat_high();
    }


    private void ridica_brat_high_1() {
        alimenteaza_pentru_ridicare_brat_high_1();
    }

    /**
     * alimenteaza pentru ridicare brat pe niveluri
     */
    private void alimenteaza_pentru_ridicare_brat1() {
        armX.setDirection(DcMotorSimple.Direction.REVERSE);
        armX.setTargetPosition(target_sus_1);
        armX.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armX.setPower(0.7);
        armX.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pozitie_verticala_brat = 1;
    }


    /**
     * alimenteaza pentru ridicare brat pe niveluri
     */
    private void alimenteaza_pentru_ridicare_brat2() {
        armX.setDirection(DcMotorSimple.Direction.REVERSE);
        armX.setTargetPosition(target_sus_2);
        armX.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armX.setPower(0.7);
        armX.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pozitie_verticala_brat = 1;
    }


    /**
     * alimenteaza pentru ridicare brat pe niveluri
     */
    private void alimenteaza_pentru_ridicare_brat3() {
        armX.setDirection(DcMotorSimple.Direction.REVERSE);
        armX.setTargetPosition(target_sus_3);
        armX.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armX.setPower(0.7);
        armX.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pozitie_verticala_brat = 1;
    }


    /**
     * alimenteaza pentru ridicare brat pe niveluri
     */
    private void alimenteaza_pentru_ridicare_brat4() {
        armX.setDirection(DcMotorSimple.Direction.REVERSE);
        armX.setTargetPosition(target_sus_4);
        armX.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armX.setPower(0.7);
        armX.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pozitie_verticala_brat = 1;
    }


    /**
     * alimenteaza pentru ridicare brat pe niveluri
     */
    private void alimenteaza_pentru_ridicare_brat5() {
        armX.setDirection(DcMotorSimple.Direction.REVERSE);
        armX.setTargetPosition(target_sus_5);
        armX.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armX.setPower(0.7);
        armX.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pozitie_verticala_brat = 1;
    }


    /**
     * alimenteaza pentru ridicare brat pe niveluri
     */
    private void alimenteaza_pentru_ridicare_brat_high() {
        armX.setDirection(DcMotorSimple.Direction.REVERSE);
        armX.setTargetPosition(target_sus_high);
        armX.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armX.setPower(0.35);
        armX.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pozitie_verticala_brat = 1;
    }


    private void alimenteaza_pentru_ridicare_brat_high_1() {
        armX.setDirection(DcMotorSimple.Direction.REVERSE);
        armX.setTargetPosition(target_sus_high);
        armX.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armX.setPower(0.9);
        armX.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pozitie_verticala_brat = 1;
    }



    /**
     * Describe this function...
     */
    private void alimenteaza_pentru_coborare_brat() {
        boolean tinta_jos;

        armX.setDirection(DcMotorSimple.Direction.FORWARD);
        armX.setTargetPosition(armX.getCurrentPosition() / armX.getCurrentPosition() - 1);
        armX.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (armX.getCurrentPosition() >= armX.getTargetPosition()) {
            armX.setPower(0.8);
        }
        armX.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        tinta_jos = armX.getCurrentPosition() >= armX.getTargetPosition();
        pozitie_verticala_brat = 0;
    }

    /**
     * Describe this function...
     */
    private void frana_brat() {
        armX.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armY.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Describe this function...
     */
    private void frana() {
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}