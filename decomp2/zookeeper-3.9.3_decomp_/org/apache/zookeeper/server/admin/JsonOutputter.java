package org.apache.zookeeper.server.admin;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonOutputter implements CommandOutputter {
   static final Logger LOG = LoggerFactory.getLogger(JsonOutputter.class);
   public static final String ERROR_RESPONSE = "{\"error\": \"Exception writing command response to JSON\"}";
   private ObjectMapper mapper = new ObjectMapper();
   private final String clientIP;

   public JsonOutputter(String clientIP) {
      this.mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
      this.mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
      this.mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
      this.clientIP = clientIP;
   }

   public String getContentType() {
      return "application/json";
   }

   public void output(CommandResponse response, PrintWriter pw) {
      try {
         this.mapper.writeValue(pw, response.toMap());
      } catch (JsonGenerationException e) {
         LOG.warn("Exception writing command response to JSON:", e);
         pw.write("{\"error\": \"Exception writing command response to JSON\"}");
      } catch (JsonMappingException e) {
         LOG.warn("Exception writing command response to JSON:", e);
         pw.write("{\"error\": \"Exception writing command response to JSON\"}");
      } catch (IOException e) {
         LOG.warn("Exception writing command response as JSON to {}", this.clientIP, e);
         pw.write("{\"error\": \"Exception writing command response to JSON\"}");
      }

   }
}
