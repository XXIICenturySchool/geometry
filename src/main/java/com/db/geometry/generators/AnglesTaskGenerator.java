package com.db.geometry.generators;

import com.db.geometry.services.ImageSaverService;
import com.db.geometry.services.RandomService;
import com.db.geometry.tasks.Task;
import com.db.geometry.tasks.TaskInfo;
import com.db.geometry.drawers.TriangularDrawer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@TaskName("Angles")
public class AnglesTaskGenerator implements TaskGenerator {

    @Autowired
    RandomService randomService;

    @Autowired
    ImageSaverService imageSaverService;

    @Override
    @SneakyThrows
    public Task generateTask(TaskInfo taskInfo, String examId, int taskNum) {

        Task.TaskBuilder taskBuilder = Task.builder();

        int alpha = randomService.getInRange(30, 81);
        int beta = randomService.getInRange(30, 81);
        int gama = 180 - alpha - beta;

        taskBuilder.question("Find third angle");
        taskBuilder.answer(String.valueOf(gama));

        TriangularDrawer triangularDrawer = new TriangularDrawer();
        BufferedImage bi = triangularDrawer.createOnAngles(Arrays.asList(alpha, beta, gama));
        taskBuilder.url(imageSaverService.saveAndGetAddress(bi, examId, taskNum));

        return taskBuilder.build();
    }
}
