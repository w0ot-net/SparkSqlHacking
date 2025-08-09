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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"command"})
public class ExecAction implements Editable, KubernetesResource {
   @JsonProperty("command")
   @JsonInclude(Include.NON_EMPTY)
   private List command = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ExecAction() {
   }

   public ExecAction(List command) {
      this.command = command;
   }

   @JsonProperty("command")
   @JsonInclude(Include.NON_EMPTY)
   public List getCommand() {
      return this.command;
   }

   @JsonProperty("command")
   public void setCommand(List command) {
      this.command = command;
   }

   @JsonIgnore
   public ExecActionBuilder edit() {
      return new ExecActionBuilder(this);
   }

   @JsonIgnore
   public ExecActionBuilder toBuilder() {
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
      List var10000 = this.getCommand();
      return "ExecAction(command=" + var10000 + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ExecAction)) {
         return false;
      } else {
         ExecAction other = (ExecAction)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$command = this.getCommand();
            Object other$command = other.getCommand();
            if (this$command == null) {
               if (other$command != null) {
                  return false;
               }
            } else if (!this$command.equals(other$command)) {
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
      return other instanceof ExecAction;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $command = this.getCommand();
      result = result * 59 + ($command == null ? 43 : $command.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
