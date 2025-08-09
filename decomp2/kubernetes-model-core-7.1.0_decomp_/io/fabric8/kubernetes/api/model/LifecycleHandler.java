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
@JsonPropertyOrder({"exec", "httpGet", "sleep", "tcpSocket"})
public class LifecycleHandler implements Editable, KubernetesResource {
   @JsonProperty("exec")
   private ExecAction exec;
   @JsonProperty("httpGet")
   private HTTPGetAction httpGet;
   @JsonProperty("sleep")
   private SleepAction sleep;
   @JsonProperty("tcpSocket")
   private TCPSocketAction tcpSocket;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public LifecycleHandler() {
   }

   public LifecycleHandler(ExecAction exec, HTTPGetAction httpGet, SleepAction sleep, TCPSocketAction tcpSocket) {
      this.exec = exec;
      this.httpGet = httpGet;
      this.sleep = sleep;
      this.tcpSocket = tcpSocket;
   }

   @JsonProperty("exec")
   public ExecAction getExec() {
      return this.exec;
   }

   @JsonProperty("exec")
   public void setExec(ExecAction exec) {
      this.exec = exec;
   }

   @JsonProperty("httpGet")
   public HTTPGetAction getHttpGet() {
      return this.httpGet;
   }

   @JsonProperty("httpGet")
   public void setHttpGet(HTTPGetAction httpGet) {
      this.httpGet = httpGet;
   }

   @JsonProperty("sleep")
   public SleepAction getSleep() {
      return this.sleep;
   }

   @JsonProperty("sleep")
   public void setSleep(SleepAction sleep) {
      this.sleep = sleep;
   }

   @JsonProperty("tcpSocket")
   public TCPSocketAction getTcpSocket() {
      return this.tcpSocket;
   }

   @JsonProperty("tcpSocket")
   public void setTcpSocket(TCPSocketAction tcpSocket) {
      this.tcpSocket = tcpSocket;
   }

   @JsonIgnore
   public LifecycleHandlerBuilder edit() {
      return new LifecycleHandlerBuilder(this);
   }

   @JsonIgnore
   public LifecycleHandlerBuilder toBuilder() {
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
      ExecAction var10000 = this.getExec();
      return "LifecycleHandler(exec=" + var10000 + ", httpGet=" + this.getHttpGet() + ", sleep=" + this.getSleep() + ", tcpSocket=" + this.getTcpSocket() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LifecycleHandler)) {
         return false;
      } else {
         LifecycleHandler other = (LifecycleHandler)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$exec = this.getExec();
            Object other$exec = other.getExec();
            if (this$exec == null) {
               if (other$exec != null) {
                  return false;
               }
            } else if (!this$exec.equals(other$exec)) {
               return false;
            }

            Object this$httpGet = this.getHttpGet();
            Object other$httpGet = other.getHttpGet();
            if (this$httpGet == null) {
               if (other$httpGet != null) {
                  return false;
               }
            } else if (!this$httpGet.equals(other$httpGet)) {
               return false;
            }

            Object this$sleep = this.getSleep();
            Object other$sleep = other.getSleep();
            if (this$sleep == null) {
               if (other$sleep != null) {
                  return false;
               }
            } else if (!this$sleep.equals(other$sleep)) {
               return false;
            }

            Object this$tcpSocket = this.getTcpSocket();
            Object other$tcpSocket = other.getTcpSocket();
            if (this$tcpSocket == null) {
               if (other$tcpSocket != null) {
                  return false;
               }
            } else if (!this$tcpSocket.equals(other$tcpSocket)) {
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
      return other instanceof LifecycleHandler;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $exec = this.getExec();
      result = result * 59 + ($exec == null ? 43 : $exec.hashCode());
      Object $httpGet = this.getHttpGet();
      result = result * 59 + ($httpGet == null ? 43 : $httpGet.hashCode());
      Object $sleep = this.getSleep();
      result = result * 59 + ($sleep == null ? 43 : $sleep.hashCode());
      Object $tcpSocket = this.getTcpSocket();
      result = result * 59 + ($tcpSocket == null ? 43 : $tcpSocket.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
