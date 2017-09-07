package com.db.geometry.taskGenerators;

import com.db.geometry.services.RandomService;
import com.db.geometry.tasks.Constraint;
import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import com.db.geometry.tasksCreation.TriangularDrawer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.db.geometry.tasksCreation.heplpers.MathHelpers.getCathet;
import static com.db.geometry.tasksCreation.heplpers.MathHelpers.getHypot;


@TaskName("Pythagoras")
public class PythagorasTaskGenerator implements TaskGenerator {

    @Autowired
    RandomService randomService;

    @Value("${app.hostUrl}")
    String hostUrl;

    @Value("${app.task.static.folder}")
    String staticFolder;

    @Value("${app.task.images.path}")
    String imagesPath;

    @Value("${app.task.images.url}")
    String imagesUrl;

    @Override
    @SneakyThrows
    public Task generateTask(TaskInfo taskInfo, String examId, int taskNum) {
        Task.TaskBuilder taskBuilder = Task.builder();

        List<Constraint> constraints = taskInfo.getConstraints();

        int cathetA = -1, cathetB = -1, hypot = -1;
        Constraint constraintOnCthet1 = constraints.get(0);
        Constraint constraintOnCathet2 = constraints.get(1);
        Constraint constraintOnHypot = constraints.get(2);

        BufferedImage bi;
        TriangularDrawer triangularDrawer = new TriangularDrawer();

        if (constraintOnHypot.getLower() != 0 || constraintOnHypot.getUpper() != 0) {
            String question = "В прямоугольном треугольнике дана гипотенуза = %d, и один кактет = %d, найдите неизвестный кактет.";
            hypot = randomService.getInRange(constraintOnHypot.getLower(), constraintOnHypot.getUpper() + 1);
            if (constraintOnCthet1.getLower() != 0 || constraintOnCthet1.getUpper() != 0) {
                cathetA = randomService.getInRange(constraintOnCthet1.getLower(), Math.min(constraintOnCthet1.getUpper(), hypot) + 1);
                bi = triangularDrawer.createOnCathetAndHypotenuse(cathetA, hypot);
                taskBuilder.answer(String.valueOf(getCathet(cathetA, hypot)));
                taskBuilder.question(String.format(question, hypot, cathetA));
            } else if (constraintOnCathet2.getLower() != 0 || constraintOnCathet2.getUpper() != 0) {
                cathetB = randomService.getInRange(constraintOnCathet2.getLower(), Math.min(constraintOnCathet2.getUpper(), hypot) + 1);
                bi = triangularDrawer.createOnCathetAndHypotenuse(cathetB, hypot);
                taskBuilder.answer(String.valueOf(getCathet(cathetB, hypot)));
                taskBuilder.question(String.format(question, hypot, cathetB));
            } else {
                cathetA = randomService.getInRange(hypot/4, hypot/3 * 2);
                bi = triangularDrawer.createOnCathetAndHypotenuse(cathetA, hypot);
                taskBuilder.answer(String.valueOf(getCathet(cathetA, hypot)));
                taskBuilder.question(String.format(question, hypot, cathetA));
                System.out.println(cathetA + " " + hypot);
            }
        } else {
            String question = "В прямоугольном треугольнике даны два катета один = %d, и дугой кактет = %d, найдите гипотенузу.";
            if (constraintOnCthet1.getLower() != 0 || constraintOnCthet1.getUpper() != 0) {
                cathetA = randomService.getInRange(constraintOnCthet1.getLower(), constraintOnCthet1.getUpper() + 1);
            }
            if (constraintOnCathet2.getLower() != 0 || constraintOnCathet2.getUpper() != 0) {
                cathetB = randomService.getInRange(constraintOnCathet2.getLower(), constraintOnCathet2.getUpper() + 1);
            }
            if (cathetA + cathetB == -2) {
                cathetA = randomService.getInRange(3, 10);
                cathetB = randomService.getInRange(3, 10);
            } else {
                if (cathetA != -1) {
                    cathetB = randomService.getInRange(cathetA, cathetA * 2);
                } else if (cathetB != -1) {
                    cathetA = randomService.getInRange(cathetB, cathetB * 2);
                }
            }

            bi = triangularDrawer.createOnCathetus(cathetA, cathetB);
            taskBuilder.answer(String.valueOf(getHypot(cathetA, cathetB)));
            taskBuilder.question(String.format(question, cathetA, cathetB));
        }

        String fileFullName = String.format("%s-%s.gif", examId, taskNum);
        String safeFileName = staticFolder + imagesPath + fileFullName;
        ImageIO.write(bi, "gif", new File(safeFileName));
        taskBuilder.url(imagesUrl + fileFullName);

        return taskBuilder.build();
    }
}
