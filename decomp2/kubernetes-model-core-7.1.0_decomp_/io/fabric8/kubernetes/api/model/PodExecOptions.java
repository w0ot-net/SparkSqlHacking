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
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "command", "container", "stderr", "stdin", "stdout", "tty"})
@Version("v1")
@Group("")
public class PodExecOptions implements Editable, KubernetesResource {
   @JsonProperty("apiVersion")
   private String apiVersion = "v1";
   @JsonProperty("command")
   @JsonInclude(Include.NON_EMPTY)
   private List command = new ArrayList();
   @JsonProperty("container")
   private String container;
   @JsonProperty("kind")
   private String kind = "PodExecOptions";
   @JsonProperty("stderr")
   private Boolean stderr;
   @JsonProperty("stdin")
   private Boolean stdin;
   @JsonProperty("stdout")
   private Boolean stdout;
   @JsonProperty("tty")
   private Boolean tty;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodExecOptions() {
   }

   public PodExecOptions(String apiVersion, List command, String container, String kind, Boolean stderr, Boolean stdin, Boolean stdout, Boolean tty) {
      this.apiVersion = apiVersion;
      this.command = command;
      this.container = container;
      this.kind = kind;
      this.stderr = stderr;
      this.stdin = stdin;
      this.stdout = stdout;
      this.tty = tty;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
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

   @JsonProperty("container")
   public String getContainer() {
      return this.container;
   }

   @JsonProperty("container")
   public void setContainer(String container) {
      this.container = container;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("stderr")
   public Boolean getStderr() {
      return this.stderr;
   }

   @JsonProperty("stderr")
   public void setStderr(Boolean stderr) {
      this.stderr = stderr;
   }

   @JsonProperty("stdin")
   public Boolean getStdin() {
      return this.stdin;
   }

   @JsonProperty("stdin")
   public void setStdin(Boolean stdin) {
      this.stdin = stdin;
   }

   @JsonProperty("stdout")
   public Boolean getStdout() {
      return this.stdout;
   }

   @JsonProperty("stdout")
   public void setStdout(Boolean stdout) {
      this.stdout = stdout;
   }

   @JsonProperty("tty")
   public Boolean getTty() {
      return this.tty;
   }

   @JsonProperty("tty")
   public void setTty(Boolean tty) {
      this.tty = tty;
   }

   @JsonIgnore
   public PodExecOptionsBuilder edit() {
      return new PodExecOptionsBuilder(this);
   }

   @JsonIgnore
   public PodExecOptionsBuilder toBuilder() {
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
      return "PodExecOptions(apiVersion=" + var10000 + ", command=" + this.getCommand() + ", container=" + this.getContainer() + ", kind=" + this.getKind() + ", stderr=" + this.getStderr() + ", stdin=" + this.getStdin() + ", stdout=" + this.getStdout() + ", tty=" + this.getTty() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodExecOptions)) {
         return false;
      } else {
         PodExecOptions other = (PodExecOptions)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$stderr = this.getStderr();
            Object other$stderr = other.getStderr();
            if (this$stderr == null) {
               if (other$stderr != null) {
                  return false;
               }
            } else if (!this$stderr.equals(other$stderr)) {
               return false;
            }

            Object this$stdin = this.getStdin();
            Object other$stdin = other.getStdin();
            if (this$stdin == null) {
               if (other$stdin != null) {
                  return false;
               }
            } else if (!this$stdin.equals(other$stdin)) {
               return false;
            }

            Object this$stdout = this.getStdout();
            Object other$stdout = other.getStdout();
            if (this$stdout == null) {
               if (other$stdout != null) {
                  return false;
               }
            } else if (!this$stdout.equals(other$stdout)) {
               return false;
            }

            Object this$tty = this.getTty();
            Object other$tty = other.getTty();
            if (this$tty == null) {
               if (other$tty != null) {
                  return false;
               }
            } else if (!this$tty.equals(other$tty)) {
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

            Object this$command = this.getCommand();
            Object other$command = other.getCommand();
            if (this$command == null) {
               if (other$command != null) {
                  return false;
               }
            } else if (!this$command.equals(other$command)) {
               return false;
            }

            Object this$container = this.getContainer();
            Object other$container = other.getContainer();
            if (this$container == null) {
               if (other$container != null) {
                  return false;
               }
            } else if (!this$container.equals(other$container)) {
               return false;
            }

            Object this$kind = this.getKind();
            Object other$kind = other.getKind();
            if (this$kind == null) {
               if (other$kind != null) {
                  return false;
               }
            } else if (!this$kind.equals(other$kind)) {
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
      return other instanceof PodExecOptions;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $stderr = this.getStderr();
      result = result * 59 + ($stderr == null ? 43 : $stderr.hashCode());
      Object $stdin = this.getStdin();
      result = result * 59 + ($stdin == null ? 43 : $stdin.hashCode());
      Object $stdout = this.getStdout();
      result = result * 59 + ($stdout == null ? 43 : $stdout.hashCode());
      Object $tty = this.getTty();
      result = result * 59 + ($tty == null ? 43 : $tty.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $command = this.getCommand();
      result = result * 59 + ($command == null ? 43 : $command.hashCode());
      Object $container = this.getContainer();
      result = result * 59 + ($container == null ? 43 : $container.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
