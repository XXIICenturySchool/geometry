package com.db.geometry.generators;

import com.db.geometry.drawers.RectangleDrawer;
import com.db.geometry.services.ImageSaverService;
import com.db.geometry.services.RandomService;
import com.db.geometry.tasks.Constraint;
import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;
import java.util.List;


@TaskName("RectangleFindSide")
public class RectangleFindSideTaskGenerator implements TaskGenerator{
    @Autowired
    RandomService randomService;

    @Autowired
    ImageSaverService imageSaverService;

    @Override
    @SneakyThrows
    public Task generateTask(TaskInfo taskInfo, String examId, int taskNum) {
        Task.TaskBuilder taskBuilder = Task.builder();
        RectangleDrawer rectangleDrawer = new RectangleDrawer();

        int side1 = -1, side2 = -1;

        List<Constraint> constraints = taskInfo.getConstraints();

        Constraint constr1 = constraints.get(0);
        Constraint constr2 = constraints.get(1);
        if (constr1.getLower() != 0 && constr1.getUpper() !=0 ) {
            side1 = randomService.getInRange(constr1.getLower(), constr1.getUpper() + 1);
        }
        if (constr2.getLower() != 0 && constr2.getUpper() !=0 ) {
            side2 = randomService.getInRange(constr2.getLower(), constr2.getUpper() + 1);
        }

        if (side1 + side2 == -2) {
            side1 = randomService.getInRange(10, 21);
            side2 = randomService.getInRange(10, 21);
        } else if (side1 == -1) {
            side1 = randomService.getInRange(side2 / 2, side2 * 2);
        } else if (side2 == -1) {
            side2 = randomService.getInRange(side1 / 2, side1 * 2);
        }

        BufferedImage bi = rectangleDrawer.drawRectangleSideFromArea(side1, side2);

        taskBuilder.answer(String.valueOf(side1 * side2));
        taskBuilder.question("Find unknown side of rectangle by known side and area.");
        taskBuilder.url(imageSaverService.saveAndGetAddress(bi, examId, taskNum));

        return taskBuilder.build();
    }
}
