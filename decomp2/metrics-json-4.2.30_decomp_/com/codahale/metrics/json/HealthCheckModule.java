package com.codahale.metrics.json;

import com.codahale.metrics.health.HealthCheck;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class HealthCheckModule extends Module {
   public String getModuleName() {
      return "healthchecks";
   }

   public Version version() {
      return MetricsModule.VERSION;
   }

   public void setupModule(Module.SetupContext context) {
      context.addSerializers(new SimpleSerializers(Collections.singletonList(new HealthCheckResultSerializer())));
   }

   private static class HealthCheckResultSerializer extends StdSerializer {
      private static final long serialVersionUID = 1L;

      private HealthCheckResultSerializer() {
         super(HealthCheck.Result.class);
      }

      public void serialize(HealthCheck.Result result, JsonGenerator json, SerializerProvider provider) throws IOException {
         json.writeStartObject();
         json.writeBooleanField("healthy", result.isHealthy());
         String message = result.getMessage();
         if (message != null) {
            json.writeStringField("message", message);
         }

         this.serializeThrowable(json, result.getError(), "error");
         json.writeNumberField("duration", result.getDuration());
         Map<String, Object> details = result.getDetails();
         if (details != null && !details.isEmpty()) {
            for(Map.Entry e : details.entrySet()) {
               json.writeObjectField((String)e.getKey(), e.getValue());
            }
         }

         json.writeStringField("timestamp", result.getTimestamp());
         json.writeEndObject();
      }

      private void serializeThrowable(JsonGenerator json, Throwable error, String name) throws IOException {
         if (error != null) {
            json.writeObjectFieldStart(name);
            json.writeStringField("type", error.getClass().getTypeName());
            json.writeStringField("message", error.getMessage());
            json.writeArrayFieldStart("stack");

            for(StackTraceElement element : error.getStackTrace()) {
               json.writeString(element.toString());
            }

            json.writeEndArray();
            if (error.getCause() != null) {
               this.serializeThrowable(json, error.getCause(), "cause");
            }

            json.writeEndObject();
         }

      }
   }
}
