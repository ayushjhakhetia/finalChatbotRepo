package com.demo.chatbot.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.chatbot.model.BotRequest;
import com.demo.chatbot.model.BotResponse;
import com.demo.chatbot.model.FollowUpEventInput;
import com.demo.chatbot.model.FulfillmentMessages;
import com.demo.chatbot.model.Message;
import com.demo.chatbot.model.OutputContexts;
import com.demo.chatbot.model.Parameters;
import com.demo.chatbot.model.Text;
import com.demo.chatbot.model.TextResponse;

@RestController
public class ChatbotController {

    @RequestMapping("/status")
    public ResponseEntity<?> echoAgentStatus(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Executing Status of Application");
        return new ResponseEntity<>("Online", HttpStatus.OK);
    }

    @RequestMapping(value = "/fullfillment1", method = RequestMethod.POST)
    public @ResponseBody WebhookResponse webhookForFollowUpEvent(@RequestBody String dr) throws com.fasterxml.jackson.core.JsonParseException, JsonMappingException, IOException, InterruptedException {
        System.out.println("end point /fullfillment1 hit...");
        FollowUpEventInput followUpEventInput = null;
        WebhookResponse wr = new WebhookResponse();
        
        BotResponse botResponse = new BotResponse();
        BotRequest botRequest = null;
        ObjectMapper mapper = new ObjectMapper();
        botRequest = mapper.readValue(dr, BotRequest.class);
        System.out.println(dr);
        
        Thread.sleep(4000);
        Parameters parameters = new Parameters();
        followUpEventInput = new FollowUpEventInput("example", "en-US");
        followUpEventInput.setParameters(parameters);
        wr.setFollowupEventInput(followUpEventInput);
        wr.setOutputContexts(botRequest.getQueryResult().getOutputContexts());
        wr.setSource("Practice.session.com");
        return wr;
    }

    @RequestMapping(value = "/fullfillment", method = RequestMethod.POST)
    public @ResponseBody WebhookResponse webhook(@RequestBody String dr) {
        BotResponse botResponse = new BotResponse();
        BotRequest botRequest = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            botRequest = mapper.readValue(dr, BotRequest.class);
        } catch (JsonParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (JsonMappingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (botRequest != null) {
            String session = botRequest.getSession();
            String fulfillmentText = botRequest.getQueryResult().getFulfillmentText();
            // int num1 = botRequest.getResult().getParameters().getCapacity();
            // int num2 = botRequest.getResult().getParameters().getDuration().getAmount();

            botResponse.setSpeech("Capacity Server Response: "/* +num1 */ + " Session: " + session);
            botResponse.setSpeech(session);
            System.out.println(session);
            // botResponse.setDisplayText("Capacity Server Response: "/*+num1*/+" getFulfillmentText: "+num2);
            /*
             * botResponse.setDisplayText("[\r\n" + "    {\r\n" + "      \"text\": [\r\n" + "        \"text response\"\r\n" + "      ],\r\n" +
             * "    }\r\n" + "  ]");
             */
        }
        // WebhookResponse wr = new WebhookResponse(botResponse.getSpeech(), botResponse.getDisplayText());

        WebhookResponse wr = new WebhookResponse();
        
        //For Agent - Webhook Demo
        
        /*if (botRequest.getQueryResult().getFulfillmentText() != null) {
            wr.setFulfillmentText(botRequest.getQueryResult().getFulfillmentText());
        }
        List<Text> responseText = new ArrayList<>();

        if (botRequest.getQueryResult().getFulfillmentMessages() != null) {
            for (FullfillmentMessages f : botRequest.getQueryResult().getFulfillmentMessages()) {
                responseText.add(f.getText());
            }
        }
        if (responseText != null)
            wr.setFulfillmentMessages(responseText);
        wr.setSource(botRequest.getSession());
        System.out.println(wr);
        System.out.println("webhook response \n Source: " + wr.getSource());
        return wr;
         */

        // For Bank_Service
        String fText=null;
        String queryText = botRequest.getQueryResult().getQueryText();
        String action = botRequest.getQueryResult().getAction();
        String uid = null;
        String pswd = null;
        
        if(queryText.equalsIgnoreCase("Can you change my account password")) {
            wr.setFulfillmentText("Sure, can you please provide your username and password");
            
        } else if(action.equalsIgnoreCase("passwordChange")) {
            System.out.println(" Action: "+ action + "\n queryText: " + queryText);
//            if(botRequest.getQueryResult().getParameters().getUid()!=null) {
//               uid = botRequest.getQueryResult().getParameters().getUid();
//               pswd = botRequest.getQueryResult().getParameters().getPswd();
//               System.out.println(" uid: " + uid + "\npswd: " + pswd);
//               if((uid.equalsIgnoreCase("ayushjhakhetia@gmail.com"))&&(pswd.equalsIgnoreCase("123abc123"))) {
//                   wr.setFulfillmentText("Password reset successfully");
//                   System.out.println("password match");
//               } else if((uid.equalsIgnoreCase("ayushjhakhetia@gmail.com"))&&(!(pswd.equalsIgnoreCase("123abc123")))){
//                   wr.setFulfillmentText("Password doesnot match");
//                   System.out.println("Password doesnot match");
//               } else if((!(uid.equalsIgnoreCase("ayushjhakhetia@gmail.com")))&&(pswd.equalsIgnoreCase("123abc123"))){
//                   wr.setFulfillmentText("Username doesnot match");
//                   System.out.println("Username doesnot match");
//               } else {
//                   wr.setFulfillmentText("Invalid Username and Password");
//                   System.out.println("Invalid Username and Password");
//               }
//            }
        } else {
            wr.setFulfillmentText("Sorry, i didn't understand that... Can you rephrase");
        }
        
        
        /*if (botRequest.getQueryResult().getParameters().getUsername() != null) {
            fText = botRequest.getQueryResult().getParameters().getUsername();
            wr.setFulfillmentText(botRequest.getQueryResult().getParameters().getUsername());    
        }*/
        List<TextResponse> responseText = new ArrayList<>();

/*        if (botRequest.getQueryResult().getFulfillmentMessages() != null) {
            for (FullfillmentMessages f : botRequest.getQueryResult().getFulfillmentMessages()) {
                responseText.add(f.getText());
                System.out.println("  botRequest.getQueryResult().getFulfillmentMessages() not NULL ");
            }
        }
*/        
        Text tx = new Text();
        List<String> str = new ArrayList<String>();
        str.add("abcd");
        str.add("s bcd ");
        tx.setText(str);
        FulfillmentMessages fm = new FulfillmentMessages();
        fm.setText(tx);
        
        /*Text tx2 = new Text();
        List<String> str2 = new ArrayList<String>();
        str2.add("abcd");
        str2.add("s bcd ");
        tx2.setText(str);
        FullfillmentMessages fm2 = new FullfillmentMessages();
        fm2.setText(tx2);*/
        
        List<Text> lt = new ArrayList<>();
        lt.add(tx);
//        lt.add(tx2);
        //wr.setFulfillmentMessages(lt);
        
        Message message = new Message();
        message.setSpeech("Always speech ");
        message.setType(0);
        
        TextResponse tr = new TextResponse();
        tr.setMessage(message);
        
        List<TextResponse> ltr = new ArrayList<>();
        ltr.add(tr);
//        wr.setFulfillmentMessages(ltr);
       // wr.setFulfillmentMessages(lt);
        wr.setSource(botRequest.getSession());
        System.out.println("Source: " + wr.getSource());
        return wr;
    }

}

