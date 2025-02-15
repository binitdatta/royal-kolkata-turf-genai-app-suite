package com.rollingstone.springaifunctions.services;


import com.rollingstone.springaifunctions.model.Answer;
import com.rollingstone.springaifunctions.model.Question;

/**
 * Created by Binit Datta.
 */
public interface OpenAIService {

    Answer getAnswer(Question question);
}
