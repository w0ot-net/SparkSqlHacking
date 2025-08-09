package io.fabric8.kubernetes.api.model.admissionregistration.v1;

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
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.LabelSelector;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"name", "namespace", "parameterNotFoundAction", "selector"})
public class ParamRef implements Editable, KubernetesResource {
   @JsonProperty("name")
   private String name;
   @JsonProperty("namespace")
   private String namespace;
   @JsonProperty("parameterNotFoundAction")
   private String parameterNotFoundAction;
   @JsonProperty("selector")
   private LabelSelector selector;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ParamRef() {
   }

   public ParamRef(String name, String namespace, String parameterNotFoundAction, LabelSelector selector) {
      this.name = name;
      this.namespace = namespace;
      this.parameterNotFoundAction = parameterNotFoundAction;
      this.selector = selector;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("namespace")
   public String getNamespace() {
      return this.namespace;
   }

   @JsonProperty("namespace")
   public void setNamespace(String namespace) {
      this.namespace = namespace;
   }

   @JsonProperty("parameterNotFoundAction")
   public String getParameterNotFoundAction() {
      return this.parameterNotFoundAction;
   }

   @JsonProperty("parameterNotFoundAction")
   public void setParameterNotFoundAction(String parameterNotFoundAction) {
      this.parameterNotFoundAction = parameterNotFoundAction;
   }

   @JsonProperty("selector")
   public LabelSelector getSelector() {
      return this.selector;
   }

   @JsonProperty("selector")
   public void setSelector(LabelSelector selector) {
      this.selector = selector;
   }

   @JsonIgnore
   public ParamRefBuilder edit() {
      return new ParamRefBuilder(this);
   }

   @JsonIgnore
   public ParamRefBuilder toBuilder() {
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
      String var10000 = this.getName();
      return "ParamRef(name=" + var10000 + ", namespace=" + this.getNamespace() + ", parameterNotFoundAction=" + this.getParameterNotFoundAction() + ", selector=" + this.getSelector() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ParamRef)) {
         return false;
      } else {
         ParamRef other = (ParamRef)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$namespace = this.getNamespace();
            Object other$namespace = other.getNamespace();
            if (this$namespace == null) {
               if (other$namespace != null) {
                  return false;
               }
            } else if (!this$namespace.equals(other$namespace)) {
               return false;
            }

            Object this$parameterNotFoundAction = this.getParameterNotFoundAction();
            Object other$parameterNotFoundAction = other.getParameterNotFoundAction();
            if (this$parameterNotFoundAction == null) {
               if (other$parameterNotFoundAction != null) {
                  return false;
               }
            } else if (!this$parameterNotFoundAction.equals(other$parameterNotFoundAction)) {
               return false;
            }

            Object this$selector = this.getSelector();
            Object other$selector = other.getSelector();
            if (this$selector == null) {
               if (other$selector != null) {
                  return false;
               }
            } else if (!this$selector.equals(other$selector)) {
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
      return other instanceof ParamRef;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $namespace = this.getNamespace();
      result = result * 59 + ($namespace == null ? 43 : $namespace.hashCode());
      Object $parameterNotFoundAction = this.getParameterNotFoundAction();
      result = result * 59 + ($parameterNotFoundAction == null ? 43 : $parameterNotFoundAction.hashCode());
      Object $selector = this.getSelector();
      result = result * 59 + ($selector == null ? 43 : $selector.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
