package com.rollingstone.springaifunctions.services;


import com.rollingstone.springaifunctions.functions.*;
import com.rollingstone.springaifunctions.model.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.model.ModelOptionsUtils;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Binit Datta.
 */
@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

    Logger logger  = LoggerFactory.getLogger("OpenAIServiceImpl");
    private final OpenAiChatModel openAiChatModel;

    private final QuestionRouter questionRouter;

    public Answer getAnswer(Question question) {
        String methodName = questionRouter.getMethodForQuestion(question.question());
        if (methodName != null) {
            try {
                Method method = this.getClass().getDeclaredMethod(methodName, Question.class);
                logger.info("Routing to method: {}", methodName);
                return (Answer) method.invoke(this, question);
            } catch (Exception e) {
                logger.error("Error invoking method: {}", methodName, e);
            }
        }
        return null;
    }

//    @Override
//    public Answer getAnswer(Question question) {
//        if (question.question().contains("horses By Category Bar Chart")) {
//            logger.info("Arrived at the right Function horses By Category Bar Chart!");
//            return horsesByCategoryBarChartQuestion(question);
//        }
//        else if (question.question().contains("Horses By Category")) {
//            logger.info("Arrived at the right Function Horses By Category!");
//            return horsesByCategoryTextQuestion(question);
//        }
//        else if (question.question().contains("Category Wise Average Handicap")) {
//            logger.info("Arrived at the right Function Category Wise Average Handicap!");
//            return categoryWiseHandicapBarChartQuestion(question);
//        }
//        else if (question.question().contains("Race Wise Jockey Participation")) {
//            logger.info("Arrived at the right Function Race Wise Jockey Participation!");
//            return raceWiseJockeyParticipationBarChartQuestion(question);
//        } else if (question.question().contains("Bets By Race Bar Chart")) {
//            return totalBetsByRaceBarChartQuestion(question);
//        } else if (question.question().contains("Horse By Wins Bar Chart")) {
//            return horsesByWinsBarChartQuestion(question);
//        }
//        else if (question.question().contains("Membership By TotalMembers")) {
//            return membershipByTotalMembersPieChartQuestion(question);
//        }
//        else if (question.question().contains("Monthly Revenue By Party Bookings")) {
//            return monthlyRevenueByPartyBookingsChartQuestion(question);
//        }
//        else if (question.question().contains("Fetch a Tabular Grid of Horses")) {
//            return horseSearchTabularGridQuestion(question);
//        }
//        return null;
//    }

    private Answer horsesByCategoryBarChartQuestion(Question question) {
        // Log the incoming question for debugging
        logger.info("horsesByCategoryBarChartQuestion: {}", question.question());

        // Build the function callback for the HorsesByCategoryBarChartServiceFunction
        var functionCallback = FunctionCallback.builder()
                .function("HorsesByCategoryBarChartServiceFunction", new HorsesByCategoryBarChartServiceFunction())
                .description("Fetch a histogram of elapsed times for the Activity Stream API")
                .inputType(HorseAnalyticsRequest.class) // Input type to generate JSON schema
                .responseConverter(response -> {
                    String schema = ModelOptionsUtils.getJsonSchema(String.class, false);
                    logger.info("Generated JSON Schema: {}", schema);
                    return schema + "\n" + response; // Combine schema and response for better debugging
                })
                .build();

        // Create the prompt options
        var promptOptions = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(functionCallback))
                .build();

        // Create user and system messages
        Message userMessage = new PromptTemplate(question.question()).createMessage();


        Message systemMessage = new SystemPromptTemplate(
                "You are an AI assistant. Use the function HorsesByCategoryBarChartServiceFunction to fetch bar chart data. " +
                        "Respond with the exact JSON output in triple quotes (\"\"\"), wrapping arrays in a root object if necessary."
        ).createMessage();

        // Log message details for debugging
        logger.info("SystemPromptTemplate Content: {}", systemMessage.getContent());
        logger.info("UserPromptTemplate Content: {}", userMessage.getContent());
        logger.info("Prompt Options: {}", promptOptions);

        // Call the OpenAI Chat Model
        var response = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));

        // Log the response for debugging
        logger.info("Model Response: {}", response.getResult().getOutput().getContent());

        // Return the response wrapped in an Answer object
        return new Answer(response.getResult().getOutput().getContent());
    }

    private Answer horsesByCategoryTextQuestion(Question question) {
        // Log the incoming question for debugging
        logger.info("Horses By Category Question: {}", question.question());

        // Build the function callback for the HorsesByCategoryTextServiceFunction
        var functionCallback = FunctionCallback.builder()
                .function("HorsesByCategoryTextServiceFunction", new HorsesByCategoryTextServiceFunction())
                .description("Fetch a text answer through the  HorsesByCategoryTextServiceFunction")
                .inputType(HorseAnalyticsRequest.class) // Input type to generate JSON schema
                .responseConverter(response -> {
                    String schema = ModelOptionsUtils.getJsonSchema(String.class, false);
                    logger.info("Generated JSON Schema: {}", schema);
                    return schema + "\n" + response; // Combine schema and response for better debugging
                })
                .build();

        // Create the prompt options
        var promptOptions = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(functionCallback))
                .build();

        // Create user and system messages
        Message userMessage = new PromptTemplate(question.question()).createMessage();


        Message systemMessage = new SystemPromptTemplate(
                "You are an AI assistant. Use the function HorsesByCategoryTextServiceFunction to fetch text data data."
        ).createMessage();

        // Log message details for debugging
        logger.info("SystemPromptTemplate Content: {}", systemMessage.getContent());
        logger.info("UserPromptTemplate Content: {}", userMessage.getContent());
        logger.info("Prompt Options: {}", promptOptions);

        // Call the OpenAI Chat Model
        var response = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));

        // Log the response for debugging
        logger.info("Model Response: {}", response.getResult().getOutput().getContent());

        // Return the response wrapped in an Answer object
        return new Answer(response.getResult().getOutput().getContent());
    }


    private Answer totalBetsByRaceBarChartQuestion(Question question) {
        // Log the incoming question for debugging
        logger.info("Activity Stream Question AS Histogram: {}", question.question());

        // Build the function callback for the TotalBetsByRaceBarChartServiceFunction
        var functionCallback = FunctionCallback.builder()
                .function("TotalBetsByRaceBarChartServiceFunction", new TotalBetsByRaceBarChartServiceFunction())
                .description("Fetch a Bar Chart of Total Bets By Race")
                .inputType(HorseAnalyticsRequest.class) // Input type to generate JSON schema
                .responseConverter(response -> {
                    String schema = ModelOptionsUtils.getJsonSchema(String.class, false);
                    logger.info("Generated JSON Schema: {}", schema);
                    return schema + "\n" + response; // Combine schema and response for better debugging
                })
                .build();

        // Create the prompt options
        var promptOptions = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(functionCallback))
                .build();

        // Create user and system messages
        Message userMessage = new PromptTemplate(question.question()).createMessage();


        Message systemMessage = new SystemPromptTemplate(
                "You are an AI assistant. Use the function HorsesByCategoryBarChartServiceFunction to fetch bar chart data. " +
                        "Respond with the exact JSON output in triple quotes (\"\"\"), wrapping arrays in a root object if necessary."
        ).createMessage();

        // Log message details for debugging
        logger.info("SystemPromptTemplate Content: {}", systemMessage.getContent());
        logger.info("UserPromptTemplate Content: {}", userMessage.getContent());
        logger.info("Prompt Options: {}", promptOptions);

        // Call the OpenAI Chat Model
        var response = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));

        // Log the response for debugging
        logger.info("Model Response: {}", response.getResult().getOutput().getContent());

        // Return the response wrapped in an Answer object
        return new Answer(response.getResult().getOutput().getContent());
    }

    private Answer categoryWiseHandicapBarChartQuestion(Question question) {
        // Log the incoming question for debugging
        logger.info("Fetch a Bar Chart of Category Wise Average Handicap: {}", question.question());

        // Build the function callback for the CategoryWiseHandicapBarChartServiceFunction
        var functionCallback = FunctionCallback.builder()
                .function("CategoryWiseHandicapBarChartServiceFunction", new CategoryWiseHandicapBarChartServiceFunction())
                .description("Fetch a Bar Chart of Category Wise Average Handicap")
                .inputType(HorseAnalyticsRequest.class) // Input type to generate JSON schema
                .responseConverter(response -> {
                    String schema = ModelOptionsUtils.getJsonSchema(String.class, false);
                    logger.info("Generated JSON Schema: {}", schema);
                    return schema + "\n" + response; // Combine schema and response for better debugging
                })
                .build();

        // Create the prompt options
        var promptOptions = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(functionCallback))
                .build();

        // Create user and system messages
        Message userMessage = new PromptTemplate(question.question()).createMessage();


        Message systemMessage = new SystemPromptTemplate(
                "You are an AI assistant. Use the function CategoryWiseHandicapBarChartServiceFunction to fetch bar chart data. " +
                        "Respond with the exact JSON output in triple quotes (\"\"\"), wrapping arrays in a root object if necessary."
        ).createMessage();

        // Log message details for debugging
        logger.info("SystemPromptTemplate Content: {}", systemMessage.getContent());
        logger.info("UserPromptTemplate Content: {}", userMessage.getContent());
        logger.info("Prompt Options: {}", promptOptions);

        // Call the OpenAI Chat Model
        var response = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));

        // Log the response for debugging
        logger.info("Model Response: {}", response.getResult().getOutput().getContent());

        // Return the response wrapped in an Answer object
        return new Answer(response.getResult().getOutput().getContent());
    }

    private Answer monthlyRevenueByPartyBookingsChartQuestion(Question question) {
        // Log the incoming question for debugging
        logger.info("Fetch a Line or Column Chart of Monthly Revenue By Party Bookings: {}", question.question());

        // Build the function callback for the MonthlyRevenueByPartyBookingsChartServiceFunction
        var functionCallback = FunctionCallback.builder()
                .function("MonthlyRevenueByPartyBookingsChartServiceFunction", new MonthlyRevenueByPartyBookingsChartServiceFunction())
                .description("Fetch a Line or Column Chart of Monthly Revenue By Party Bookings")
                .inputType(HorseAnalyticsRequest.class) // Input type to generate JSON schema
                .responseConverter(response -> {
                    String schema = ModelOptionsUtils.getJsonSchema(String.class, false);
                    logger.info("Generated JSON Schema: {}", schema);
                    return schema + "\n" + response; // Combine schema and response for better debugging
                })
                .build();

        // Create the prompt options
        var promptOptions = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(functionCallback))
                .build();

        // Create user and system messages
        Message userMessage = new PromptTemplate(question.question()).createMessage();


        Message systemMessage = new SystemPromptTemplate(
                "You are an AI assistant. Use the function MonthlyRevenueByPartyBookingsChartServiceFunction to fetch Line chart data. " +
                        "Respond with the exact JSON output in triple quotes (\"\"\"), wrapping arrays in a root object if necessary."
        ).createMessage();

        // Log message details for debugging
        logger.info("SystemPromptTemplate Content: {}", systemMessage.getContent());
        logger.info("UserPromptTemplate Content: {}", userMessage.getContent());
        logger.info("Prompt Options: {}", promptOptions);

        // Call the OpenAI Chat Model
        var response = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));

        // Log the response for debugging
        logger.info("Model Response: {}", response.getResult().getOutput().getContent());

        // Return the response wrapped in an Answer object
        return new Answer(response.getResult().getOutput().getContent());
    }


    private Answer raceWiseJockeyParticipationBarChartQuestion(Question question) {
        // Log the incoming question for debugging
        logger.info("Fetch a Bar Chart of Race Wise Jockey Participation: {}", question.question());

        // Build the function callback for the RaceByJockeyParticipationBarChartServiceFunction
        var functionCallback = FunctionCallback.builder()
                .function("RaceByJockeyParticipationBarChartServiceFunction", new RaceByJockeyParticipationBarChartServiceFunction())
                .description("Fetch a Bar Chart of Race Wise Jockey Participation")
                .inputType(HorseAnalyticsRequest.class) // Input type to generate JSON schema
                .responseConverter(response -> {
                    String schema = ModelOptionsUtils.getJsonSchema(String.class, false);
                    logger.info("Generated JSON Schema: {}", schema);
                    return schema + "\n" + response; // Combine schema and response for better debugging
                })
                .build();

        // Create the prompt options
        var promptOptions = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(functionCallback))
                .build();

        // Create user and system messages
        Message userMessage = new PromptTemplate(question.question()).createMessage();


        Message systemMessage = new SystemPromptTemplate(
                "You are an AI assistant. Use the function RaceByJockeyParticipationBarChartServiceFunction to fetch bar chart data. " +
                        "Respond with the exact JSON output in triple quotes (\"\"\"), wrapping arrays in a root object if necessary." +
                        "Please ignore any reference of the json schema in the response."
        ).createMessage();

        // Log message details for debugging
        logger.info("SystemPromptTemplate Content: {}", systemMessage.getContent());
        logger.info("UserPromptTemplate Content: {}", userMessage.getContent());
        logger.info("Prompt Options: {}", promptOptions);

        // Call the OpenAI Chat Model
        var response = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));

        // Log the response for debugging
        logger.info("Model Response: {}", response.getResult().getOutput().getContent());

        // Return the response wrapped in an Answer object
        return new Answer(response.getResult().getOutput().getContent());
    }

    private Answer horsesByWinsBarChartQuestion(Question question) {
        // Log the incoming question for debugging
        logger.info("Fetch a Bar Chart of Horse By Wins: {}", question.question());

        // Build the function callback for the HorsesByWinsBarChartServiceFunction
        var functionCallback = FunctionCallback.builder()
                .function("HorsesByWinsBarChartServiceFunction", new HorsesByWinsBarChartServiceFunction())
                .description("Fetch a Bar Chart of Horse By Wins")
                .inputType(HorseAnalyticsRequest.class) // Input type to generate JSON schema
                .responseConverter(response -> {
                    String schema = ModelOptionsUtils.getJsonSchema(String.class, false);
                    logger.info("Generated JSON Schema: {}", schema);
                    return schema + "\n" + response; // Combine schema and response for better debugging
                })
                .build();

        // Create the prompt options
        var promptOptions = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(functionCallback))
                .build();

        // Create user and system messages
        Message userMessage = new PromptTemplate(question.question()).createMessage();


        Message systemMessage = new SystemPromptTemplate(
                "You are an AI assistant. Use the function HorsesByWinsBarChartServiceFunction to fetch bar chart data. " +
                        "Respond with the exact JSON output in triple quotes (\"\"\"), wrapping arrays in a root object if necessary."
        ).createMessage();

        // Log message details for debugging
        logger.info("SystemPromptTemplate Content: {}", systemMessage.getContent());
        logger.info("UserPromptTemplate Content: {}", userMessage.getContent());
        logger.info("Prompt Options: {}", promptOptions);

        // Call the OpenAI Chat Model
        var response = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));

        // Log the response for debugging
        logger.info("Model Response: {}", response.getResult().getOutput().getContent());

        // Return the response wrapped in an Answer object
        return new Answer(response.getResult().getOutput().getContent());
    }

    private Answer membershipByTotalMembersPieChartQuestion(Question question) {
        // Log the incoming question for debugging
        logger.info("Fetch a Pie Chart of Membership By TotalMembers: {}", question.question());

        // Build the function callback for the MembershipByTotalMembersPieChartServiceFunction
        var functionCallback = FunctionCallback.builder()
                .function("MembershipByTotalMembersPieChartServiceFunction", new MembershipByTotalMembersPieChartServiceFunction())
                .description("Fetch a Pie Chart of Membership By TotalMembers")
                .inputType(HorseAnalyticsRequest.class) // Input type to generate JSON schema
                .responseConverter(response -> {
                    String schema = ModelOptionsUtils.getJsonSchema(String.class, false);
                    logger.info("Generated JSON Schema: {}", schema);
                    return schema + "\n" + response; // Combine schema and response for better debugging
                })
                .build();

        // Create the prompt options
        var promptOptions = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(functionCallback))
                .build();

        // Create user and system messages
        Message userMessage = new PromptTemplate(question.question()).createMessage();


        Message systemMessage = new SystemPromptTemplate(
                "You are an AI assistant. Use the function MembershipByTotalMembersPieChartServiceFunction to fetch bar chart data. " +
                        "Respond with the exact JSON output in triple quotes (\"\"\"), wrapping arrays in a root object if necessary."
        ).createMessage();

        // Log message details for debugging
        logger.info("SystemPromptTemplate Content: {}", systemMessage.getContent());
        logger.info("UserPromptTemplate Content: {}", userMessage.getContent());
        logger.info("Prompt Options: {}", promptOptions);

        // Call the OpenAI Chat Model
        var response = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));

        // Log the response for debugging
        logger.info("Model Response: {}", response.getResult().getOutput().getContent());

        // Return the response wrapped in an Answer object
        return new Answer(response.getResult().getOutput().getContent());
    }

    private Answer horseSearchTabularGridQuestion(Question question) {
        // Log the incoming question for debugging
        logger.info("Fetch a Tabular Grid of Horses matching the Search Criteria: {}", question.question());

        // Build the function callback for the HorsesSearchTabularGridServiceFunction
        var functionCallback = FunctionCallback.builder()
                .function("HorsesSearchTabularGridServiceFunction", new HorsesSearchTabularGridServiceFunction())
                .description("Fetch a Tabular Grid of Horses matching the Search Criteria")
                .inputType(HorseSearchRequest.class) // Input type to generate JSON schema
                .responseConverter(response -> {
                    String schema = ModelOptionsUtils.getJsonSchema(String.class, false);
                    logger.info("Generated JSON Schema: {}", schema);
                    return schema + "\n" + response; // Combine schema and response for better debugging
                })
                .build();

        // Create the prompt options
        var promptOptions = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(functionCallback))
                .build();

        // Create user and system messages
        Message userMessage = new PromptTemplate(question.question()).createMessage();



        // The following was working but was too slow.
        Message systemMessage = new SystemPromptTemplate(
                "You are an AI assistant. Use the function HorsesSearchTabularGridServiceFunction to fetch bar chart data. " +
                        "Respond with the exact JSON output, wrapping arrays in a root object if necessary. " +
                        "Considering this sample response, please remove all plain English text and only keep the JSON part: " +
                        "Here is a tabular grid of male horses:\n\n" +
                        "[ \\{ \"horseId\": 2, \"name\": \"Thunderstorm\", \"birthDate\": \"2015-02-15T06:00:00.000+00:00\", \"gender\": \"Male\", \"color\": \"Brown\", \"height\": 14.4, \"weight\": 920, \"birthmarks\": \"White Star\", \"category\": \\{ \"categoryId\": 3, \"categoryName\": \"Arabian\" \\}, \"handicapRating\": 53 \\}, " +
                        "\\{ \"horseId\": 4, \"name\": \"Shadow Dancer\", \"birthDate\": \"2015-05-16T05:00:00.000+00:00\", \"gender\": \"Male\", \"color\": \"Gray\", \"height\": 14.8, \"weight\": 940, \"birthmarks\": \"Snip\", \"category\": \\{ \"categoryId\": 5, \"categoryName\": \"Appaloosa\" \\}, \"handicapRating\": 56 \\} " +
                        "... ]\n\n" +
                        "This data includes the horse's ID, name, birth date, gender, color, height, weight, birthmarks, category, and handicap rating."
        ).createMessage();

        // Log message details for debugging
        logger.info("SystemPromptTemplate Content: {}", systemMessage.getContent());
        logger.info("UserPromptTemplate Content: {}", userMessage.getContent());
        logger.info("Prompt Options: {}", promptOptions);

        // Call the OpenAI Chat Model
        var response = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));

        // Log the response for debugging
        logger.info("Model Response: {}", response.getResult().getOutput().getContent());

        // Return the response wrapped in an Answer object
        return new Answer(response.getResult().getOutput().getContent());
    }
}




















