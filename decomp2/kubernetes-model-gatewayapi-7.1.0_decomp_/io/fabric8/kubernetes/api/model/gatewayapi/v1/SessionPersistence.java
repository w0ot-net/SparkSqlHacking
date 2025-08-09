package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"absoluteTimeout", "cookieConfig", "idleTimeout", "sessionName", "type"})
public class SessionPersistence implements Editable, KubernetesResource {
   @JsonProperty("absoluteTimeout")
   private String absoluteTimeout;
   @JsonProperty("cookieConfig")
   private CookieConfig cookieConfig;
   @JsonProperty("idleTimeout")
   private String idleTimeout;
   @JsonProperty("sessionName")
   private String sessionName;
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public SessionPersistence() {
   }

   public SessionPersistence(String absoluteTimeout, CookieConfig cookieConfig, String idleTimeout, String sessionName, String type) {
      this.absoluteTimeout = absoluteTimeout;
      this.cookieConfig = cookieConfig;
      this.idleTimeout = idleTimeout;
      this.sessionName = sessionName;
      this.type = type;
   }

   @JsonProperty("absoluteTimeout")
   public String getAbsoluteTimeout() {
      return this.absoluteTimeout;
   }

   @JsonProperty("absoluteTimeout")
   public void setAbsoluteTimeout(String absoluteTimeout) {
      this.absoluteTimeout = absoluteTimeout;
   }

   @JsonProperty("cookieConfig")
   public CookieConfig getCookieConfig() {
      return this.cookieConfig;
   }

   @JsonProperty("cookieConfig")
   public void setCookieConfig(CookieConfig cookieConfig) {
      this.cookieConfig = cookieConfig;
   }

   @JsonProperty("idleTimeout")
   public String getIdleTimeout() {
      return this.idleTimeout;
   }

   @JsonProperty("idleTimeout")
   public void setIdleTimeout(String idleTimeout) {
      this.idleTimeout = idleTimeout;
   }

   @JsonProperty("sessionName")
   public String getSessionName() {
      return this.sessionName;
   }

   @JsonProperty("sessionName")
   public void setSessionName(String sessionName) {
      this.sessionName = sessionName;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonIgnore
   public SessionPersistenceBuilder edit() {
      return new SessionPersistenceBuilder(this);
   }

   @JsonIgnore
   public SessionPersistenceBuilder toBuilder() {
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
      String var10000 = this.getAbsoluteTimeout();
      return "SessionPersistence(absoluteTimeout=" + var10000 + ", cookieConfig=" + this.getCookieConfig() + ", idleTimeout=" + this.getIdleTimeout() + ", sessionName=" + this.getSessionName() + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SessionPersistence)) {
         return false;
      } else {
         SessionPersistence other = (SessionPersistence)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$absoluteTimeout = this.getAbsoluteTimeout();
            Object other$absoluteTimeout = other.getAbsoluteTimeout();
            if (this$absoluteTimeout == null) {
               if (other$absoluteTimeout != null) {
                  return false;
               }
            } else if (!this$absoluteTimeout.equals(other$absoluteTimeout)) {
               return false;
            }

            Object this$cookieConfig = this.getCookieConfig();
            Object other$cookieConfig = other.getCookieConfig();
            if (this$cookieConfig == null) {
               if (other$cookieConfig != null) {
                  return false;
               }
            } else if (!this$cookieConfig.equals(other$cookieConfig)) {
               return false;
            }

            Object this$idleTimeout = this.getIdleTimeout();
            Object other$idleTimeout = other.getIdleTimeout();
            if (this$idleTimeout == null) {
               if (other$idleTimeout != null) {
                  return false;
               }
            } else if (!this$idleTimeout.equals(other$idleTimeout)) {
               return false;
            }

            Object this$sessionName = this.getSessionName();
            Object other$sessionName = other.getSessionName();
            if (this$sessionName == null) {
               if (other$sessionName != null) {
                  return false;
               }
            } else if (!this$sessionName.equals(other$sessionName)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
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
      return other instanceof SessionPersistence;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $absoluteTimeout = this.getAbsoluteTimeout();
      result = result * 59 + ($absoluteTimeout == null ? 43 : $absoluteTimeout.hashCode());
      Object $cookieConfig = this.getCookieConfig();
      result = result * 59 + ($cookieConfig == null ? 43 : $cookieConfig.hashCode());
      Object $idleTimeout = this.getIdleTimeout();
      result = result * 59 + ($idleTimeout == null ? 43 : $idleTimeout.hashCode());
      Object $sessionName = this.getSessionName();
      result = result * 59 + ($sessionName == null ? 43 : $sessionName.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
