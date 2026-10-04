package in.strikes.GenAIChatBot;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SummarizeService {

    private final ChatClient chatClient;


    public SummarizeService(ChatClient.Builder builder) {

        this.chatClient = builder.build();

    }


 // SUMMARIZE USER QUERY


    public String summarize(String query) {

        String output = chatClient
                .prompt()
                .user("""
                        Summarize the following user query in exactly two
                        short and clear lines.

                        Focus on:
                        1. The main point or problem mentioned by the user.
                        2. The important information or request from the user.

                        Keep the summary simple, accurate, and easy to understand.
                        Do not add information that is not present in the query.

                        User Query:

                        """ + query)
                .call()
                .content();

        return output.trim();
    }


// ANALYZE USER QUERY SENTIMENT


    public String analyzeSentiment(String query) {

        String output = chatClient
                .prompt()
                .user("""
                        Analyze the sentiment expressed in the following
                        user query.

                        Classify the sentiment into exactly ONE of these
                        three categories:

                        Positive
                        Neutral
                        Negative

                        Consider the overall tone and emotional meaning
                        expressed in the user's query.

                        Return ONLY the category name.
                        Do not provide any explanation.
                        Do not add punctuation.
                        Do not add extra words.

                        User Query:

                        """ + query)
                .call()
                .content();

        return normalizeSentiment(output);
    }




     //NORMALIZE SENTIMENT RESPONSE



    private String normalizeSentiment(String output) {

        if (output == null) {
            return "Neutral";
        }


        String sentiment =
                output.trim()
                        .replace(".", "")
                        .replace("*", "")
                        .trim();


        if (sentiment.equalsIgnoreCase("positive")) {
            return "Positive";
        }


        if (sentiment.equalsIgnoreCase("negative")) {
            return "Negative";
        }


        if (sentiment.equalsIgnoreCase("neutral")) {
            return "Neutral";
        }


        /*
         * Fallback if the model returns
         * something unexpected.
         */

        String lower =
                sentiment.toLowerCase();


        if (lower.contains("negative")) {
            return "Negative";
        }


        if (lower.contains("positive")) {
            return "Positive";
        }


        return "Neutral";
    }

}






//package in.strikes.GenAIChatBot;
//
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SummarizeService {
//
//    private ChatClient chatClient;
//
//    public SummarizeService(ChatClient.Builder builder ){
//        this.chatClient = builder.build();
//    }
//
//    public String summarize(String ticket){
//        String output = chatClient.prompt()
//                .user("Summarize this customer support ticket in two lines\n\n" + ticket)
//                .call()
//                .content();
//
//        return output;
//    }
//}
