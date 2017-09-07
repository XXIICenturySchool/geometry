package com.db.geometry.generators;

import com.db.geometry.services.ImageSaverService;
import com.db.geometry.services.RandomService;
import com.db.geometry.tasks.Constraint;
import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import com.db.geometry.drawers.TriangularDrawer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static com.db.geometry.drawers.helpers.MathHelper.getCathet;
import static com.db.geometry.drawers.helpers.MathHelper.getHypot;


@TaskName("FindHypotenuse")
public class FindHypotenuseTaskGenerator implements TaskGenerator {

    @Autowired
    RandomService randomService;

    @Autowired
    ImageSaverService imageSaverService;

    @Override
    @SneakyThrows
    public Task generateTask(TaskInfo taskInfo, String examId, int taskNum) {
        Task.TaskBuilder taskBuilder = Task.builder();
        TriangularDrawer triangularDrawer = new TriangularDrawer();

        int cat1 = -1, cat2 = -1;

        List<Constraint> constraints = taskInfo.getConstraints();

        Constraint constr1 = constraints.get(0);
        Constraint constr2 = constraints.get(1);
        if (constr1.getLower() != 0 && constr1.getUpper() !=0 ) {
            cat1 = randomService.getInRange(constr1.getLower(), constr1.getUpper() + 1);
        }
        if (constr2.getLower() != 0 && constr2.getUpper() !=0 ) {
            cat2 = randomService.getInRange(constr2.getLower(), constr2.getUpper() + 1);
        }
        if (cat1 + cat2 == -2) {
            cat1 = randomService.getInRange(10, 21);
            cat2 = randomService.getInRange(10, 21);
        } else if (cat1 == -1) {
            cat1 = randomService.getInRange(cat2 / 2, cat2 * 2);
        } else if (cat2 == -1) {
            cat2 = randomService.getInRange(cat1 / 2, cat1 * 2);
        }


        BufferedImage bi = triangularDrawer.createOnCathetus(cat1, cat2);

        taskBuilder.answer(String.valueOf((int) Math.round(getHypot(cat1, cat2))));
        taskBuilder.question("Find hypotenuse (rounded to the nearest integer)");
        taskBuilder.url(imageSaverService.saveAndGetAddress(bi, examId, taskNum));

        return taskBuilder.build();
    }
}
