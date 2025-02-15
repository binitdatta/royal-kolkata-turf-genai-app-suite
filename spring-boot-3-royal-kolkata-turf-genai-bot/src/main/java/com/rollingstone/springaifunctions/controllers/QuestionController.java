package com.rollingstone.springaifunctions.controllers;


import com.rollingstone.springaifunctions.model.Answer;
import com.rollingstone.springaifunctions.model.Question;
import com.rollingstone.springaifunctions.services.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Binit Datta
 */
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200, http://localhost:4300, http://localhost:4400") // Allow requests from Angular app
public class QuestionController {
    Logger logger = LoggerFactory.getLogger("QuestionController");
    private final OpenAIService openAIService;

    @PostMapping("/turf/analytics")
    public Answer askQuestion(@RequestBody Question question) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();  // Start tracking time
        logger.info("Question " + question.question());
        Answer answer =  openAIService.getAnswer(question);

        stopWatch.stop();

        logger.info("Total execution time for answering AI Question: {} ms", stopWatch.getTotalTimeMillis());

        return answer;
    }

}
