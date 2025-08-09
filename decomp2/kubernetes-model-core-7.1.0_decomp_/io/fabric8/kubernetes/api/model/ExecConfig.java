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
@JsonPropertyOrder({"apiVersion", "args", "command", "env", "installHint", "interactiveMode", "provideClusterInfo"})
public class ExecConfig implements Editable, KubernetesResource {
   @JsonProperty("apiVersion")
   private String apiVersion;
   @JsonProperty("args")
   @JsonInclude(Include.NON_EMPTY)
   private List args = new ArrayList();
   @JsonProperty("command")
   private String command;
   @JsonProperty("env")
   @JsonInclude(Include.NON_EMPTY)
   private List env = new ArrayList();
   @JsonProperty("installHint")
   private String installHint;
   @JsonProperty("interactiveMode")
   private String interactiveMode;
   @JsonProperty("provideClusterInfo")
   private Boolean provideClusterInfo;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ExecConfig() {
   }

   public ExecConfig(String apiVersion, List args, String command, List env, String installHint, String interactiveMode, Boolean provideClusterInfo) {
      this.apiVersion = apiVersion;
      this.args = args;
      this.command = command;
      this.env = env;
      this.installHint = installHint;
      this.interactiveMode = interactiveMode;
      this.provideClusterInfo = provideClusterInfo;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("args")
   @JsonInclude(Include.NON_EMPTY)
   public List getArgs() {
      return this.args;
   }

   @JsonProperty("args")
   public void setArgs(List args) {
      this.args = args;
   }

   @JsonProperty("command")
   public String getCommand() {
      return this.command;
   }

   @JsonProperty("command")
   public void setCommand(String command) {
      this.command = command;
   }

   @JsonProperty("env")
   @JsonInclude(Include.NON_EMPTY)
   public List getEnv() {
      return this.env;
   }

   @JsonProperty("env")
   public void setEnv(List env) {
      this.env = env;
   }

   @JsonProperty("installHint")
   public String getInstallHint() {
      return this.installHint;
   }

   @JsonProperty("installHint")
   public void setInstallHint(String installHint) {
      this.installHint = installHint;
   }

   @JsonProperty("interactiveMode")
   public String getInteractiveMode() {
      return this.interactiveMode;
   }

   @JsonProperty("interactiveMode")
   public void setInteractiveMode(String interactiveMode) {
      this.interactiveMode = interactiveMode;
   }

   @JsonProperty("provideClusterInfo")
   public Boolean getProvideClusterInfo() {
      return this.provideClusterInfo;
   }

   @JsonProperty("provideClusterInfo")
   public void setProvideClusterInfo(Boolean provideClusterInfo) {
      this.provideClusterInfo = provideClusterInfo;
   }

   @JsonIgnore
   public ExecConfigBuilder edit() {
      return new ExecConfigBuilder(this);
   }

   @JsonIgnore
   public ExecConfigBuilder toBuilder() {
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
      String var10000 = this.getApiVersion();
      return "ExecConfig(apiVersion=" + var10000 + ", args=" + this.getArgs() + ", command=" + this.getCommand() + ", env=" + this.getEnv() + ", installHint=" + this.getInstallHint() + ", interactiveMode=" + this.getInteractiveMode() + ", provideClusterInfo=" + this.getProvideClusterInfo() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ExecConfig)) {
         return false;
      } else {
         ExecConfig other = (ExecConfig)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$provideClusterInfo = this.getProvideClusterInfo();
            Object other$provideClusterInfo = other.getProvideClusterInfo();
            if (this$provideClusterInfo == null) {
               if (other$provideClusterInfo != null) {
                  return false;
               }
            } else if (!this$provideClusterInfo.equals(other$provideClusterInfo)) {
               return false;
            }

            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$args = this.getArgs();
            Object other$args = other.getArgs();
            if (this$args == null) {
               if (other$args != null) {
                  return false;
               }
            } else if (!this$args.equals(other$args)) {
               return false;
            }

            Object this$command = this.getCommand();
            Object other$command = other.getCommand();
            if (this$command == null) {
               if (other$command != null) {
                  return false;
               }
            } else if (!this$command.equals(other$command)) {
               return false;
            }

            Object this$env = this.getEnv();
            Object other$env = other.getEnv();
            if (this$env == null) {
               if (other$env != null) {
                  return false;
               }
            } else if (!this$env.equals(other$env)) {
               return false;
            }

            Object this$installHint = this.getInstallHint();
            Object other$installHint = other.getInstallHint();
            if (this$installHint == null) {
               if (other$installHint != null) {
                  return false;
               }
            } else if (!this$installHint.equals(other$installHint)) {
               return false;
            }

            Object this$interactiveMode = this.getInteractiveMode();
            Object other$interactiveMode = other.getInteractiveMode();
            if (this$interactiveMode == null) {
               if (other$interactiveMode != null) {
                  return false;
               }
            } else if (!this$interactiveMode.equals(other$interactiveMode)) {
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
      return other instanceof ExecConfig;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $provideClusterInfo = this.getProvideClusterInfo();
      result = result * 59 + ($provideClusterInfo == null ? 43 : $provideClusterInfo.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $args = this.getArgs();
      result = result * 59 + ($args == null ? 43 : $args.hashCode());
      Object $command = this.getCommand();
      result = result * 59 + ($command == null ? 43 : $command.hashCode());
      Object $env = this.getEnv();
      result = result * 59 + ($env == null ? 43 : $env.hashCode());
      Object $installHint = this.getInstallHint();
      result = result * 59 + ($installHint == null ? 43 : $installHint.hashCode());
      Object $interactiveMode = this.getInteractiveMode();
      result = result * 59 + ($interactiveMode == null ? 43 : $interactiveMode.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
