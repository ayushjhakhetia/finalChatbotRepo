package com.impetus.rpacoordinator.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

@RestController
public class CoordinatorController {
	//public static String status = AgentConstants.STATUS_READY;
	private static String UPLOADED_FOLDER = "D://temp//";
	
	@RequestMapping("/status")
    public ResponseEntity<?> echoAgentStatus(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("Executing echoAgentStatus");
        return new ResponseEntity<>("Online", HttpStatus.OK);
    }
	
    //Multiple file upload for Bot jar and resource files
    @PostMapping("/uploadfiles")
    public ResponseEntity<?> uploadFileMulti(@RequestParam("files") MultipartFile[] uploadfiles) {
    	System.out.println("Multiple file upload!");
        // Get file name
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
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
    
    //save file
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; //next pls
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            if (!Files.exists(path.getParent())) 
            	Files.createDirectories(path.getParent());
            Files.write(path, bytes);
        }
    }

   /**
     * @param request HTTP request
     * @param response HTTP response
     * @return  status code
     */
    @RequestMapping("/registeragent") 
    public final ResponseEntity<?> registerAgent(final HttpServletRequest 
            request, final HttpServletResponse response)
    {
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
    
    @RequestMapping(value = "/fullfillment",method = RequestMethod.POST)
    public @ResponseBody ResponseEntity webhook(@RequestBody String dr){
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
         if(botRequest != null){
                String num1 = botRequest.getId()+" HI ";
                String num2 = botRequest.getId()+"   cimedosweptus : ; ";
                
                botResponse.setSpeech("Capacity Server Response: "+num1+" Duration: "+num2);
                botResponse.setDisplayText("Capacity Server Response: "+num1+" Duration: "+num2);
         }
         
        return new ResponseEntity<>(botResponse ,HttpStatus.OK);
    }

} 
