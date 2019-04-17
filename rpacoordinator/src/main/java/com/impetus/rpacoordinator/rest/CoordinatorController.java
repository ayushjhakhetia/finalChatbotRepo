package com.impetus.rpacoordinator.rest;

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

import org.springframework.boot.json.JsonParseException;
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

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.impetus.rpacoordinator.model.BotRequest;
import com.impetus.rpacoordinator.model.BotResponse;
import com.impetus.rpacoordinator.model.FullfillmentMessages;
import com.impetus.rpacoordinator.model.Message;
import com.impetus.rpacoordinator.model.Text;
import com.impetus.rpacoordinator.model.TextResponse;

@RestController
public class CoordinatorController {
    // public static String status = AgentConstants.STATUS_READY;
    private static String UPLOADED_FOLDER = "D://temp//";

    @RequestMapping("/status")
    public ResponseEntity<?> echoAgentStatus(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Executing echoAgentStatus");
        return new ResponseEntity<>("Online", HttpStatus.OK);
    }

    // Multiple file upload for Bot jar and resource files
    @PostMapping("/uploadfiles")
    public ResponseEntity<?> uploadFileMulti(@RequestParam("files") MultipartFile[] uploadfiles) {
        System.out.println("Multiple file upload!");
        // Get file name
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename()).filter(x -> !StringUtils.isEmpty(x))
                .collect(Collectors.joining(" , "));
        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }
        try {
            saveUploadedFiles(Arrays.asList(uploadfiles));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Successfully uploaded - " + uploadedFileName, HttpStatus.OK);
    }

    // save file
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; // next pls
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            if (!Files.exists(path.getParent()))
                Files.createDirectories(path.getParent());
            Files.write(path, bytes);
        }
    }

    /** @param request
     *            HTTP request
     * @param response
     *            HTTP response
     * @return status code */
    @RequestMapping("/registeragent")
    public final ResponseEntity<?> registerAgent(final HttpServletRequest request, final HttpServletResponse response) {
        System.out.println("registering Agent");
        final String id;
        final String agentbaseurl;
        String line = null;
        try {
            final BufferedReader reader = request.getReader();
            line = reader.readLine();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(line);
            id = json.get("id").toString();
            agentbaseurl = json.get("agentbaseurl").toString();
            System.out.println("id: " + id);
            System.out.println("agentbaseurl: " + agentbaseurl);
        } catch (final Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Registered", HttpStatus.OK);
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
            String timePeriod = botRequest.getQueryResult().getParameters().getTimePeriod();
            System.out.println("timePeriod " + timePeriod);
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

        WebhookResponse wr = new WebhookResponse(botResponse.getSpeech());
        
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
            wr.setFulfillmentText("Please provide your username and password");
            System.out.println(" qyeryText not null");
            System.out.println();
            
        } else if(action.equalsIgnoreCase("passwordChange")) {
            System.out.println(" Action "+ action + " queryText " + queryText);
            System.out.println(" qyeryText null");
            if(botRequest.getQueryResult().getParameters().getUid()!=null) {
               uid = botRequest.getQueryResult().getParameters().getUid();
               pswd = botRequest.getQueryResult().getParameters().getPswd();
               System.out.println(" uid  and pswd " + uid + " pswd " + pswd);
               if((uid.equalsIgnoreCase("ayushjhakhetia@gmail.com"))&&(pswd.equalsIgnoreCase("123abc123"))) {
                   wr.setFulfillmentText("Password reset successfully");
                   System.out.println("password match");
                   System.out.println();
               }
            }
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
        /*if (responseText != null) {
        
            System.out.println("responseText not null/ fullfillmentMessage not null");
            wr.setFulfillmentMessages(responseText);
        }*/
        Text tx = new Text();
        List<String> str = new ArrayList<String>();
        str.add("abcd");
        str.add("s bcd ");
        tx.setText(str);
        FullfillmentMessages fm = new FullfillmentMessages();
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
        wr.setFulfillmentMessages(lt);
        
        Message message = new Message();
        message.setSpeech("Always speech ");
        message.setType(1);
        
        TextResponse tr = new TextResponse();
        tr.setMessage(message);
        
        List<TextResponse> ltr = new ArrayList<>();
        ltr.add(tr);
       // wr.setFulfillmentMessages(ltr);
        
        System.out.println();
        System.out.println(lt.get(0).getText().get(0));
       // wr.setFulfillmentMessages(lt);
        wr.setSource(botRequest.getSession());
        System.out.println(wr);
        System.out.println("Source: " + wr.getSource());
        return wr;
    }

}

