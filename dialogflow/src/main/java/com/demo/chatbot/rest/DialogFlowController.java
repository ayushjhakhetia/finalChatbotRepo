package com.demo.chatbot.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.chatbot.model.BotRequest;
import com.demo.chatbot.model.BotResponse;
import com.demo.chatbot.model.FollowUpEventInput;
import com.demo.chatbot.model.FulfillmentMessages;
import com.demo.chatbot.model.Message;
import com.demo.chatbot.model.Text;
import com.demo.chatbot.model.TextResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DialogFlowController {
    // public static String status = AgentConstants.STATUS_READY;
    private static String UPLOADED_FOLDER = "D://temp//";
    static int var1=0;

    @RequestMapping("/status")
    public ResponseEntity<?> echoAgentStatus(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Executing echoAgentStatus");
        return new ResponseEntity<>("Online", HttpStatus.OK);
    }

    @RequestMapping(value = "/fullfillment", method = RequestMethod.POST)
    public @ResponseBody WebhookResponse webhook(@RequestBody String dr) {
        BotResponse botResponse = new BotResponse();
        BotRequest botRequest = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            botRequest = mapper.readValue(dr, BotRequest.class);
        } catch (JsonParseException |JsonMappingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (botRequest != null) {
        	System.out.println("--------------------REQUEST--------------------");
            String session = botRequest.getSession();
            System.out.println("session: "+session);
            String fulfillmentText = botRequest.getQueryResult().getFulfillmentText();
            System.out.println("fulfillmentText: "+fulfillmentText);
            String timePeriod = botRequest.getQueryResult().getParameters().getTimePeriod();
            System.out.println("timePeriod: " + timePeriod);
            // int num1 = botRequest.getResult().getParameters().getCapacity();
            // int num2 = botRequest.getResult().getParameters().getDuration().getAmount();

            botResponse.setSpeech("Capacity Server Response: "/* +num1 */ + " Session: " + session);
            botResponse.setSpeech(session);
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
        System.out.println("queryText: "+queryText);
        String action = botRequest.getQueryResult().getAction();
        System.out.println("action: "+action);
        
        System.out.println("---------------------RESPONSE---------------------");
        String uid = null;
        String pswd = null;
        
        if(queryText.equalsIgnoreCase("Can you change my account password")) {
            wr.setFulfillmentText("Sure, can you please provide your username and password");
            
        } else if((action!=null)&&(action.equalsIgnoreCase("passwordChange"))) {
            if(botRequest.getQueryResult().getParameters().getUid()!=null) {
               uid = botRequest.getQueryResult().getParameters().getUid();
               pswd = botRequest.getQueryResult().getParameters().getPswd();
               System.out.println(" uid: " + uid + "\npswd: " + pswd);
               if((uid.equalsIgnoreCase("ayushjhakhetia@gmail.com"))&&(pswd.equalsIgnoreCase("123abc123"))) {
                   wr.setFulfillmentText("Password reset successfully");
                   System.out.println("password match");
               } else if((uid.equalsIgnoreCase("ayushjhakhetia@gmail.com"))&&(!(pswd.equalsIgnoreCase("123abc123")))){
                   wr.setFulfillmentText("Password doesnot match");
                   System.out.println("Password doesnot match");
               } else if((!(uid.equalsIgnoreCase("ayushjhakhetia@gmail.com")))&&(pswd.equalsIgnoreCase("123abc123"))){
                   wr.setFulfillmentText("Username doesnot match");
                   System.out.println("Username doesnot match");
               } else {
                   wr.setFulfillmentText("Invalid Username and Password");
                   System.out.println("Invalid Username and Password");
               }
            }
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
        
        boolean flag = true;//From Parameters to check it is first query of user
        //if yes, flag = true
        //else flag = flase
        
        if(flag) {
        	if(queryText.contains("final data")) {
        		var1 = var1 ++;
        		System.out.println("var1 "+var1);
        		//set action to some intent which send webhook request as fulfillment req
        	}
        }
		if (var1 > 5) {
			// set action to some useful output to console/ may be dont use it any way, if
							// works
			
		} else {
			var1 = var1 ++;
			System.out.println("var1 "+var1);
			//set action to some intent which send webhook request as fulfillment req
		}
		
        System.out.println("Source: " + wr.getSource());
        return wr;
    }
    
    
    @RequestMapping(value = "/fullfillment1", method = RequestMethod.POST)
    public @ResponseBody WebhookResponse webhookForFollowUpEvent(@RequestBody String dr) {
        FollowUpEventInput followUpEventInput = new FollowUpEventInput("example", "en-US");
        WebhookResponse wr = new WebhookResponse();
        wr.setFollowUpEventInput(followUpEventInput);
        return wr;
    }

}

