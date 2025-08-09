package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"field", "message", "reason"})
public class StatusCause implements Editable, KubernetesResource {
   @JsonProperty("field")
   private String field;
   @JsonProperty("message")
   private String message;
   @JsonProperty("reason")
   private String reason;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public StatusCause() {
   }

   public StatusCause(String field, String message, String reason) {
      this.field = field;
      this.message = message;
      this.reason = reason;
   }

   @JsonProperty("field")
   public String getField() {
      return this.field;
   }

   @JsonProperty("field")
   public void setField(String field) {
      this.field = field;
   }

   @JsonProperty("message")
   public String getMessage() {
      return this.message;
   }

   @JsonProperty("message")
   public void setMessage(String message) {
      this.message = message;
   }

   @JsonProperty("reason")
   public String getReason() {
      return this.reason;
   }

   @JsonProperty("reason")
   public void setReason(String reason) {
      this.reason = reason;
   }

   @JsonIgnore
   public StatusCauseBuilder edit() {
      return new StatusCauseBuilder(this);
   }

   @JsonIgnore
   public StatusCauseBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      String var10000 = this.getField();
      return "StatusCause(field=" + var10000 + ", message=" + this.getMessage() + ", reason=" + this.getReason() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StatusCause)) {
         return false;
      } else {
         StatusCause other = (StatusCause)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$field = this.getField();
            Object other$field = other.getField();
            if (this$field == null) {
               if (other$field != null) {
                  return false;
               }
            } else if (!this$field.equals(other$field)) {
               return false;
            }

            Object this$message = this.getMessage();
            Object other$message = other.getMessage();
            if (this$message == null) {
               if (other$message != null) {
                  return false;
               }
            } else if (!this$message.equals(other$message)) {
               return false;
            }

            Object this$reason = this.getReason();
            Object other$reason = other.getReason();
            if (this$reason == null) {
               if (other$reason != null) {
                  return false;
               }
            } else if (!this$reason.equals(other$reason)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof StatusCause;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $field = this.getField();
      result = result * 59 + ($field == null ? 43 : $field.hashCode());
      Object $message = this.getMessage();
      result = result * 59 + ($message == null ? 43 : $message.hashCode());
      Object $reason = this.getReason();
      result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
