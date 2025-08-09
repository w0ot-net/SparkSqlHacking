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
@JsonPropertyOrder({"exec", "failureThreshold", "grpc", "httpGet", "initialDelaySeconds", "periodSeconds", "successThreshold", "tcpSocket", "terminationGracePeriodSeconds", "timeoutSeconds"})
public class Probe implements Editable, KubernetesResource {
   @JsonProperty("exec")
   private ExecAction exec;
   @JsonProperty("failureThreshold")
   private Integer failureThreshold;
   @JsonProperty("grpc")
   private GRPCAction grpc;
   @JsonProperty("httpGet")
   private HTTPGetAction httpGet;
   @JsonProperty("initialDelaySeconds")
   private Integer initialDelaySeconds;
   @JsonProperty("periodSeconds")
   private Integer periodSeconds;
   @JsonProperty("successThreshold")
   private Integer successThreshold;
   @JsonProperty("tcpSocket")
   private TCPSocketAction tcpSocket;
   @JsonProperty("terminationGracePeriodSeconds")
   private Long terminationGracePeriodSeconds;
   @JsonProperty("timeoutSeconds")
   private Integer timeoutSeconds;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Probe() {
   }

   public Probe(ExecAction exec, Integer failureThreshold, GRPCAction grpc, HTTPGetAction httpGet, Integer initialDelaySeconds, Integer periodSeconds, Integer successThreshold, TCPSocketAction tcpSocket, Long terminationGracePeriodSeconds, Integer timeoutSeconds) {
      this.exec = exec;
      this.failureThreshold = failureThreshold;
      this.grpc = grpc;
      this.httpGet = httpGet;
      this.initialDelaySeconds = initialDelaySeconds;
      this.periodSeconds = periodSeconds;
      this.successThreshold = successThreshold;
      this.tcpSocket = tcpSocket;
      this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
      this.timeoutSeconds = timeoutSeconds;
   }

   @JsonProperty("exec")
   public ExecAction getExec() {
      return this.exec;
   }

   @JsonProperty("exec")
   public void setExec(ExecAction exec) {
      this.exec = exec;
   }

   @JsonProperty("failureThreshold")
   public Integer getFailureThreshold() {
      return this.failureThreshold;
   }

   @JsonProperty("failureThreshold")
   public void setFailureThreshold(Integer failureThreshold) {
      this.failureThreshold = failureThreshold;
   }

   @JsonProperty("grpc")
   public GRPCAction getGrpc() {
      return this.grpc;
   }

   @JsonProperty("grpc")
   public void setGrpc(GRPCAction grpc) {
      this.grpc = grpc;
   }

   @JsonProperty("httpGet")
   public HTTPGetAction getHttpGet() {
      return this.httpGet;
   }

   @JsonProperty("httpGet")
   public void setHttpGet(HTTPGetAction httpGet) {
      this.httpGet = httpGet;
   }

   @JsonProperty("initialDelaySeconds")
   public Integer getInitialDelaySeconds() {
      return this.initialDelaySeconds;
   }

   @JsonProperty("initialDelaySeconds")
   public void setInitialDelaySeconds(Integer initialDelaySeconds) {
      this.initialDelaySeconds = initialDelaySeconds;
   }

   @JsonProperty("periodSeconds")
   public Integer getPeriodSeconds() {
      return this.periodSeconds;
   }

   @JsonProperty("periodSeconds")
   public void setPeriodSeconds(Integer periodSeconds) {
      this.periodSeconds = periodSeconds;
   }

   @JsonProperty("successThreshold")
   public Integer getSuccessThreshold() {
      return this.successThreshold;
   }

   @JsonProperty("successThreshold")
   public void setSuccessThreshold(Integer successThreshold) {
      this.successThreshold = successThreshold;
   }

   @JsonProperty("tcpSocket")
   public TCPSocketAction getTcpSocket() {
      return this.tcpSocket;
   }

   @JsonProperty("tcpSocket")
   public void setTcpSocket(TCPSocketAction tcpSocket) {
      this.tcpSocket = tcpSocket;
   }

   @JsonProperty("terminationGracePeriodSeconds")
   public Long getTerminationGracePeriodSeconds() {
      return this.terminationGracePeriodSeconds;
   }

   @JsonProperty("terminationGracePeriodSeconds")
   public void setTerminationGracePeriodSeconds(Long terminationGracePeriodSeconds) {
      this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
   }

   @JsonProperty("timeoutSeconds")
   public Integer getTimeoutSeconds() {
      return this.timeoutSeconds;
   }

   @JsonProperty("timeoutSeconds")
   public void setTimeoutSeconds(Integer timeoutSeconds) {
      this.timeoutSeconds = timeoutSeconds;
   }

   @JsonIgnore
   public ProbeBuilder edit() {
      return new ProbeBuilder(this);
   }

   @JsonIgnore
   public ProbeBuilder toBuilder() {
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
      return "Probe(exec=" + var10000 + ", failureThreshold=" + this.getFailureThreshold() + ", grpc=" + this.getGrpc() + ", httpGet=" + this.getHttpGet() + ", initialDelaySeconds=" + this.getInitialDelaySeconds() + ", periodSeconds=" + this.getPeriodSeconds() + ", successThreshold=" + this.getSuccessThreshold() + ", tcpSocket=" + this.getTcpSocket() + ", terminationGracePeriodSeconds=" + this.getTerminationGracePeriodSeconds() + ", timeoutSeconds=" + this.getTimeoutSeconds() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Probe)) {
         return false;
      } else {
         Probe other = (Probe)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$failureThreshold = this.getFailureThreshold();
            Object other$failureThreshold = other.getFailureThreshold();
            if (this$failureThreshold == null) {
               if (other$failureThreshold != null) {
                  return false;
               }
            } else if (!this$failureThreshold.equals(other$failureThreshold)) {
               return false;
            }

            Object this$initialDelaySeconds = this.getInitialDelaySeconds();
            Object other$initialDelaySeconds = other.getInitialDelaySeconds();
            if (this$initialDelaySeconds == null) {
               if (other$initialDelaySeconds != null) {
                  return false;
               }
            } else if (!this$initialDelaySeconds.equals(other$initialDelaySeconds)) {
               return false;
            }

            Object this$periodSeconds = this.getPeriodSeconds();
            Object other$periodSeconds = other.getPeriodSeconds();
            if (this$periodSeconds == null) {
               if (other$periodSeconds != null) {
                  return false;
               }
            } else if (!this$periodSeconds.equals(other$periodSeconds)) {
               return false;
            }

            Object this$successThreshold = this.getSuccessThreshold();
            Object other$successThreshold = other.getSuccessThreshold();
            if (this$successThreshold == null) {
               if (other$successThreshold != null) {
                  return false;
               }
            } else if (!this$successThreshold.equals(other$successThreshold)) {
               return false;
            }

            Object this$terminationGracePeriodSeconds = this.getTerminationGracePeriodSeconds();
            Object other$terminationGracePeriodSeconds = other.getTerminationGracePeriodSeconds();
            if (this$terminationGracePeriodSeconds == null) {
               if (other$terminationGracePeriodSeconds != null) {
                  return false;
               }
            } else if (!this$terminationGracePeriodSeconds.equals(other$terminationGracePeriodSeconds)) {
               return false;
            }

            Object this$timeoutSeconds = this.getTimeoutSeconds();
            Object other$timeoutSeconds = other.getTimeoutSeconds();
            if (this$timeoutSeconds == null) {
               if (other$timeoutSeconds != null) {
                  return false;
               }
            } else if (!this$timeoutSeconds.equals(other$timeoutSeconds)) {
               return false;
            }

            Object this$exec = this.getExec();
            Object other$exec = other.getExec();
            if (this$exec == null) {
               if (other$exec != null) {
                  return false;
               }
            } else if (!this$exec.equals(other$exec)) {
               return false;
            }

            Object this$grpc = this.getGrpc();
            Object other$grpc = other.getGrpc();
            if (this$grpc == null) {
               if (other$grpc != null) {
                  return false;
               }
            } else if (!this$grpc.equals(other$grpc)) {
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
      return other instanceof Probe;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $failureThreshold = this.getFailureThreshold();
      result = result * 59 + ($failureThreshold == null ? 43 : $failureThreshold.hashCode());
      Object $initialDelaySeconds = this.getInitialDelaySeconds();
      result = result * 59 + ($initialDelaySeconds == null ? 43 : $initialDelaySeconds.hashCode());
      Object $periodSeconds = this.getPeriodSeconds();
      result = result * 59 + ($periodSeconds == null ? 43 : $periodSeconds.hashCode());
      Object $successThreshold = this.getSuccessThreshold();
      result = result * 59 + ($successThreshold == null ? 43 : $successThreshold.hashCode());
      Object $terminationGracePeriodSeconds = this.getTerminationGracePeriodSeconds();
      result = result * 59 + ($terminationGracePeriodSeconds == null ? 43 : $terminationGracePeriodSeconds.hashCode());
      Object $timeoutSeconds = this.getTimeoutSeconds();
      result = result * 59 + ($timeoutSeconds == null ? 43 : $timeoutSeconds.hashCode());
      Object $exec = this.getExec();
      result = result * 59 + ($exec == null ? 43 : $exec.hashCode());
      Object $grpc = this.getGrpc();
      result = result * 59 + ($grpc == null ? 43 : $grpc.hashCode());
      Object $httpGet = this.getHttpGet();
      result = result * 59 + ($httpGet == null ? 43 : $httpGet.hashCode());
      Object $tcpSocket = this.getTcpSocket();
      result = result * 59 + ($tcpSocket == null ? 43 : $tcpSocket.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
