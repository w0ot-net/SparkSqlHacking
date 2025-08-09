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
@JsonPropertyOrder({"args", "command", "env", "envFrom", "image", "imagePullPolicy", "lifecycle", "livenessProbe", "name", "ports", "readinessProbe", "resizePolicy", "resources", "restartPolicy", "securityContext", "startupProbe", "stdin", "stdinOnce", "terminationMessagePath", "terminationMessagePolicy", "tty", "volumeDevices", "volumeMounts", "workingDir"})
public class Container implements Editable, KubernetesResource {
   @JsonProperty("args")
   @JsonInclude(Include.NON_EMPTY)
   private List args = new ArrayList();
   @JsonProperty("command")
   @JsonInclude(Include.NON_EMPTY)
   private List command = new ArrayList();
   @JsonProperty("env")
   @JsonInclude(Include.NON_EMPTY)
   private List env = new ArrayList();
   @JsonProperty("envFrom")
   @JsonInclude(Include.NON_EMPTY)
   private List envFrom = new ArrayList();
   @JsonProperty("image")
   private String image;
   @JsonProperty("imagePullPolicy")
   private String imagePullPolicy;
   @JsonProperty("lifecycle")
   private Lifecycle lifecycle;
   @JsonProperty("livenessProbe")
   private Probe livenessProbe;
   @JsonProperty("name")
   private String name;
   @JsonProperty("ports")
   @JsonInclude(Include.NON_EMPTY)
   private List ports = new ArrayList();
   @JsonProperty("readinessProbe")
   private Probe readinessProbe;
   @JsonProperty("resizePolicy")
   @JsonInclude(Include.NON_EMPTY)
   private List resizePolicy = new ArrayList();
   @JsonProperty("resources")
   private ResourceRequirements resources;
   @JsonProperty("restartPolicy")
   private String restartPolicy;
   @JsonProperty("securityContext")
   private SecurityContext securityContext;
   @JsonProperty("startupProbe")
   private Probe startupProbe;
   @JsonProperty("stdin")
   private Boolean stdin;
   @JsonProperty("stdinOnce")
   private Boolean stdinOnce;
   @JsonProperty("terminationMessagePath")
   private String terminationMessagePath;
   @JsonProperty("terminationMessagePolicy")
   private String terminationMessagePolicy;
   @JsonProperty("tty")
   private Boolean tty;
   @JsonProperty("volumeDevices")
   @JsonInclude(Include.NON_EMPTY)
   private List volumeDevices = new ArrayList();
   @JsonProperty("volumeMounts")
   @JsonInclude(Include.NON_EMPTY)
   private List volumeMounts = new ArrayList();
   @JsonProperty("workingDir")
   private String workingDir;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Container() {
   }

   public Container(List args, List command, List env, List envFrom, String image, String imagePullPolicy, Lifecycle lifecycle, Probe livenessProbe, String name, List ports, Probe readinessProbe, List resizePolicy, ResourceRequirements resources, String restartPolicy, SecurityContext securityContext, Probe startupProbe, Boolean stdin, Boolean stdinOnce, String terminationMessagePath, String terminationMessagePolicy, Boolean tty, List volumeDevices, List volumeMounts, String workingDir) {
      this.args = args;
      this.command = command;
      this.env = env;
      this.envFrom = envFrom;
      this.image = image;
      this.imagePullPolicy = imagePullPolicy;
      this.lifecycle = lifecycle;
      this.livenessProbe = livenessProbe;
      this.name = name;
      this.ports = ports;
      this.readinessProbe = readinessProbe;
      this.resizePolicy = resizePolicy;
      this.resources = resources;
      this.restartPolicy = restartPolicy;
      this.securityContext = securityContext;
      this.startupProbe = startupProbe;
      this.stdin = stdin;
      this.stdinOnce = stdinOnce;
      this.terminationMessagePath = terminationMessagePath;
      this.terminationMessagePolicy = terminationMessagePolicy;
      this.tty = tty;
      this.volumeDevices = volumeDevices;
      this.volumeMounts = volumeMounts;
      this.workingDir = workingDir;
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
   @JsonInclude(Include.NON_EMPTY)
   public List getCommand() {
      return this.command;
   }

   @JsonProperty("command")
   public void setCommand(List command) {
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

   @JsonProperty("envFrom")
   @JsonInclude(Include.NON_EMPTY)
   public List getEnvFrom() {
      return this.envFrom;
   }

   @JsonProperty("envFrom")
   public void setEnvFrom(List envFrom) {
      this.envFrom = envFrom;
   }

   @JsonProperty("image")
   public String getImage() {
      return this.image;
   }

   @JsonProperty("image")
   public void setImage(String image) {
      this.image = image;
   }

   @JsonProperty("imagePullPolicy")
   public String getImagePullPolicy() {
      return this.imagePullPolicy;
   }

   @JsonProperty("imagePullPolicy")
   public void setImagePullPolicy(String imagePullPolicy) {
      this.imagePullPolicy = imagePullPolicy;
   }

   @JsonProperty("lifecycle")
   public Lifecycle getLifecycle() {
      return this.lifecycle;
   }

   @JsonProperty("lifecycle")
   public void setLifecycle(Lifecycle lifecycle) {
      this.lifecycle = lifecycle;
   }

   @JsonProperty("livenessProbe")
   public Probe getLivenessProbe() {
      return this.livenessProbe;
   }

   @JsonProperty("livenessProbe")
   public void setLivenessProbe(Probe livenessProbe) {
      this.livenessProbe = livenessProbe;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("ports")
   @JsonInclude(Include.NON_EMPTY)
   public List getPorts() {
      return this.ports;
   }

   @JsonProperty("ports")
   public void setPorts(List ports) {
      this.ports = ports;
   }

   @JsonProperty("readinessProbe")
   public Probe getReadinessProbe() {
      return this.readinessProbe;
   }

   @JsonProperty("readinessProbe")
   public void setReadinessProbe(Probe readinessProbe) {
      this.readinessProbe = readinessProbe;
   }

   @JsonProperty("resizePolicy")
   @JsonInclude(Include.NON_EMPTY)
   public List getResizePolicy() {
      return this.resizePolicy;
   }

   @JsonProperty("resizePolicy")
   public void setResizePolicy(List resizePolicy) {
      this.resizePolicy = resizePolicy;
   }

   @JsonProperty("resources")
   public ResourceRequirements getResources() {
      return this.resources;
   }

   @JsonProperty("resources")
   public void setResources(ResourceRequirements resources) {
      this.resources = resources;
   }

   @JsonProperty("restartPolicy")
   public String getRestartPolicy() {
      return this.restartPolicy;
   }

   @JsonProperty("restartPolicy")
   public void setRestartPolicy(String restartPolicy) {
      this.restartPolicy = restartPolicy;
   }

   @JsonProperty("securityContext")
   public SecurityContext getSecurityContext() {
      return this.securityContext;
   }

   @JsonProperty("securityContext")
   public void setSecurityContext(SecurityContext securityContext) {
      this.securityContext = securityContext;
   }

   @JsonProperty("startupProbe")
   public Probe getStartupProbe() {
      return this.startupProbe;
   }

   @JsonProperty("startupProbe")
   public void setStartupProbe(Probe startupProbe) {
      this.startupProbe = startupProbe;
   }

   @JsonProperty("stdin")
   public Boolean getStdin() {
      return this.stdin;
   }

   @JsonProperty("stdin")
   public void setStdin(Boolean stdin) {
      this.stdin = stdin;
   }

   @JsonProperty("stdinOnce")
   public Boolean getStdinOnce() {
      return this.stdinOnce;
   }

   @JsonProperty("stdinOnce")
   public void setStdinOnce(Boolean stdinOnce) {
      this.stdinOnce = stdinOnce;
   }

   @JsonProperty("terminationMessagePath")
   public String getTerminationMessagePath() {
      return this.terminationMessagePath;
   }

   @JsonProperty("terminationMessagePath")
   public void setTerminationMessagePath(String terminationMessagePath) {
      this.terminationMessagePath = terminationMessagePath;
   }

   @JsonProperty("terminationMessagePolicy")
   public String getTerminationMessagePolicy() {
      return this.terminationMessagePolicy;
   }

   @JsonProperty("terminationMessagePolicy")
   public void setTerminationMessagePolicy(String terminationMessagePolicy) {
      this.terminationMessagePolicy = terminationMessagePolicy;
   }

   @JsonProperty("tty")
   public Boolean getTty() {
      return this.tty;
   }

   @JsonProperty("tty")
   public void setTty(Boolean tty) {
      this.tty = tty;
   }

   @JsonProperty("volumeDevices")
   @JsonInclude(Include.NON_EMPTY)
   public List getVolumeDevices() {
      return this.volumeDevices;
   }

   @JsonProperty("volumeDevices")
   public void setVolumeDevices(List volumeDevices) {
      this.volumeDevices = volumeDevices;
   }

   @JsonProperty("volumeMounts")
   @JsonInclude(Include.NON_EMPTY)
   public List getVolumeMounts() {
      return this.volumeMounts;
   }

   @JsonProperty("volumeMounts")
   public void setVolumeMounts(List volumeMounts) {
      this.volumeMounts = volumeMounts;
   }

   @JsonProperty("workingDir")
   public String getWorkingDir() {
      return this.workingDir;
   }

   @JsonProperty("workingDir")
   public void setWorkingDir(String workingDir) {
      this.workingDir = workingDir;
   }

   @JsonIgnore
   public ContainerBuilder edit() {
      return new ContainerBuilder(this);
   }

   @JsonIgnore
   public ContainerBuilder toBuilder() {
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
      List var10000 = this.getArgs();
      return "Container(args=" + var10000 + ", command=" + this.getCommand() + ", env=" + this.getEnv() + ", envFrom=" + this.getEnvFrom() + ", image=" + this.getImage() + ", imagePullPolicy=" + this.getImagePullPolicy() + ", lifecycle=" + this.getLifecycle() + ", livenessProbe=" + this.getLivenessProbe() + ", name=" + this.getName() + ", ports=" + this.getPorts() + ", readinessProbe=" + this.getReadinessProbe() + ", resizePolicy=" + this.getResizePolicy() + ", resources=" + this.getResources() + ", restartPolicy=" + this.getRestartPolicy() + ", securityContext=" + this.getSecurityContext() + ", startupProbe=" + this.getStartupProbe() + ", stdin=" + this.getStdin() + ", stdinOnce=" + this.getStdinOnce() + ", terminationMessagePath=" + this.getTerminationMessagePath() + ", terminationMessagePolicy=" + this.getTerminationMessagePolicy() + ", tty=" + this.getTty() + ", volumeDevices=" + this.getVolumeDevices() + ", volumeMounts=" + this.getVolumeMounts() + ", workingDir=" + this.getWorkingDir() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Container)) {
         return false;
      } else {
         Container other = (Container)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$stdin = this.getStdin();
            Object other$stdin = other.getStdin();
            if (this$stdin == null) {
               if (other$stdin != null) {
                  return false;
               }
            } else if (!this$stdin.equals(other$stdin)) {
               return false;
            }

            Object this$stdinOnce = this.getStdinOnce();
            Object other$stdinOnce = other.getStdinOnce();
            if (this$stdinOnce == null) {
               if (other$stdinOnce != null) {
                  return false;
               }
            } else if (!this$stdinOnce.equals(other$stdinOnce)) {
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

            Object this$envFrom = this.getEnvFrom();
            Object other$envFrom = other.getEnvFrom();
            if (this$envFrom == null) {
               if (other$envFrom != null) {
                  return false;
               }
            } else if (!this$envFrom.equals(other$envFrom)) {
               return false;
            }

            Object this$image = this.getImage();
            Object other$image = other.getImage();
            if (this$image == null) {
               if (other$image != null) {
                  return false;
               }
            } else if (!this$image.equals(other$image)) {
               return false;
            }

            Object this$imagePullPolicy = this.getImagePullPolicy();
            Object other$imagePullPolicy = other.getImagePullPolicy();
            if (this$imagePullPolicy == null) {
               if (other$imagePullPolicy != null) {
                  return false;
               }
            } else if (!this$imagePullPolicy.equals(other$imagePullPolicy)) {
               return false;
            }

            Object this$lifecycle = this.getLifecycle();
            Object other$lifecycle = other.getLifecycle();
            if (this$lifecycle == null) {
               if (other$lifecycle != null) {
                  return false;
               }
            } else if (!this$lifecycle.equals(other$lifecycle)) {
               return false;
            }

            Object this$livenessProbe = this.getLivenessProbe();
            Object other$livenessProbe = other.getLivenessProbe();
            if (this$livenessProbe == null) {
               if (other$livenessProbe != null) {
                  return false;
               }
            } else if (!this$livenessProbe.equals(other$livenessProbe)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$ports = this.getPorts();
            Object other$ports = other.getPorts();
            if (this$ports == null) {
               if (other$ports != null) {
                  return false;
               }
            } else if (!this$ports.equals(other$ports)) {
               return false;
            }

            Object this$readinessProbe = this.getReadinessProbe();
            Object other$readinessProbe = other.getReadinessProbe();
            if (this$readinessProbe == null) {
               if (other$readinessProbe != null) {
                  return false;
               }
            } else if (!this$readinessProbe.equals(other$readinessProbe)) {
               return false;
            }

            Object this$resizePolicy = this.getResizePolicy();
            Object other$resizePolicy = other.getResizePolicy();
            if (this$resizePolicy == null) {
               if (other$resizePolicy != null) {
                  return false;
               }
            } else if (!this$resizePolicy.equals(other$resizePolicy)) {
               return false;
            }

            Object this$resources = this.getResources();
            Object other$resources = other.getResources();
            if (this$resources == null) {
               if (other$resources != null) {
                  return false;
               }
            } else if (!this$resources.equals(other$resources)) {
               return false;
            }

            Object this$restartPolicy = this.getRestartPolicy();
            Object other$restartPolicy = other.getRestartPolicy();
            if (this$restartPolicy == null) {
               if (other$restartPolicy != null) {
                  return false;
               }
            } else if (!this$restartPolicy.equals(other$restartPolicy)) {
               return false;
            }

            Object this$securityContext = this.getSecurityContext();
            Object other$securityContext = other.getSecurityContext();
            if (this$securityContext == null) {
               if (other$securityContext != null) {
                  return false;
               }
            } else if (!this$securityContext.equals(other$securityContext)) {
               return false;
            }

            Object this$startupProbe = this.getStartupProbe();
            Object other$startupProbe = other.getStartupProbe();
            if (this$startupProbe == null) {
               if (other$startupProbe != null) {
                  return false;
               }
            } else if (!this$startupProbe.equals(other$startupProbe)) {
               return false;
            }

            Object this$terminationMessagePath = this.getTerminationMessagePath();
            Object other$terminationMessagePath = other.getTerminationMessagePath();
            if (this$terminationMessagePath == null) {
               if (other$terminationMessagePath != null) {
                  return false;
               }
            } else if (!this$terminationMessagePath.equals(other$terminationMessagePath)) {
               return false;
            }

            Object this$terminationMessagePolicy = this.getTerminationMessagePolicy();
            Object other$terminationMessagePolicy = other.getTerminationMessagePolicy();
            if (this$terminationMessagePolicy == null) {
               if (other$terminationMessagePolicy != null) {
                  return false;
               }
            } else if (!this$terminationMessagePolicy.equals(other$terminationMessagePolicy)) {
               return false;
            }

            Object this$volumeDevices = this.getVolumeDevices();
            Object other$volumeDevices = other.getVolumeDevices();
            if (this$volumeDevices == null) {
               if (other$volumeDevices != null) {
                  return false;
               }
            } else if (!this$volumeDevices.equals(other$volumeDevices)) {
               return false;
            }

            Object this$volumeMounts = this.getVolumeMounts();
            Object other$volumeMounts = other.getVolumeMounts();
            if (this$volumeMounts == null) {
               if (other$volumeMounts != null) {
                  return false;
               }
            } else if (!this$volumeMounts.equals(other$volumeMounts)) {
               return false;
            }

            Object this$workingDir = this.getWorkingDir();
            Object other$workingDir = other.getWorkingDir();
            if (this$workingDir == null) {
               if (other$workingDir != null) {
                  return false;
               }
            } else if (!this$workingDir.equals(other$workingDir)) {
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
      return other instanceof Container;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $stdin = this.getStdin();
      result = result * 59 + ($stdin == null ? 43 : $stdin.hashCode());
      Object $stdinOnce = this.getStdinOnce();
      result = result * 59 + ($stdinOnce == null ? 43 : $stdinOnce.hashCode());
      Object $tty = this.getTty();
      result = result * 59 + ($tty == null ? 43 : $tty.hashCode());
      Object $args = this.getArgs();
      result = result * 59 + ($args == null ? 43 : $args.hashCode());
      Object $command = this.getCommand();
      result = result * 59 + ($command == null ? 43 : $command.hashCode());
      Object $env = this.getEnv();
      result = result * 59 + ($env == null ? 43 : $env.hashCode());
      Object $envFrom = this.getEnvFrom();
      result = result * 59 + ($envFrom == null ? 43 : $envFrom.hashCode());
      Object $image = this.getImage();
      result = result * 59 + ($image == null ? 43 : $image.hashCode());
      Object $imagePullPolicy = this.getImagePullPolicy();
      result = result * 59 + ($imagePullPolicy == null ? 43 : $imagePullPolicy.hashCode());
      Object $lifecycle = this.getLifecycle();
      result = result * 59 + ($lifecycle == null ? 43 : $lifecycle.hashCode());
      Object $livenessProbe = this.getLivenessProbe();
      result = result * 59 + ($livenessProbe == null ? 43 : $livenessProbe.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $ports = this.getPorts();
      result = result * 59 + ($ports == null ? 43 : $ports.hashCode());
      Object $readinessProbe = this.getReadinessProbe();
      result = result * 59 + ($readinessProbe == null ? 43 : $readinessProbe.hashCode());
      Object $resizePolicy = this.getResizePolicy();
      result = result * 59 + ($resizePolicy == null ? 43 : $resizePolicy.hashCode());
      Object $resources = this.getResources();
      result = result * 59 + ($resources == null ? 43 : $resources.hashCode());
      Object $restartPolicy = this.getRestartPolicy();
      result = result * 59 + ($restartPolicy == null ? 43 : $restartPolicy.hashCode());
      Object $securityContext = this.getSecurityContext();
      result = result * 59 + ($securityContext == null ? 43 : $securityContext.hashCode());
      Object $startupProbe = this.getStartupProbe();
      result = result * 59 + ($startupProbe == null ? 43 : $startupProbe.hashCode());
      Object $terminationMessagePath = this.getTerminationMessagePath();
      result = result * 59 + ($terminationMessagePath == null ? 43 : $terminationMessagePath.hashCode());
      Object $terminationMessagePolicy = this.getTerminationMessagePolicy();
      result = result * 59 + ($terminationMessagePolicy == null ? 43 : $terminationMessagePolicy.hashCode());
      Object $volumeDevices = this.getVolumeDevices();
      result = result * 59 + ($volumeDevices == null ? 43 : $volumeDevices.hashCode());
      Object $volumeMounts = this.getVolumeMounts();
      result = result * 59 + ($volumeMounts == null ? 43 : $volumeMounts.hashCode());
      Object $workingDir = this.getWorkingDir();
      result = result * 59 + ($workingDir == null ? 43 : $workingDir.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
