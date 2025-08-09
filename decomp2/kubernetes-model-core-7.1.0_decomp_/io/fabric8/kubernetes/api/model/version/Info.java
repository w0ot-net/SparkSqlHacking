package io.fabric8.kubernetes.api.model.version;

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
@JsonPropertyOrder({"buildDate", "compiler", "gitCommit", "gitTreeState", "gitVersion", "goVersion", "major", "minor", "platform"})
public class Info implements Editable, KubernetesResource {
   @JsonProperty("buildDate")
   private String buildDate;
   @JsonProperty("compiler")
   private String compiler;
   @JsonProperty("gitCommit")
   private String gitCommit;
   @JsonProperty("gitTreeState")
   private String gitTreeState;
   @JsonProperty("gitVersion")
   private String gitVersion;
   @JsonProperty("goVersion")
   private String goVersion;
   @JsonProperty("major")
   private String major;
   @JsonProperty("minor")
   private String minor;
   @JsonProperty("platform")
   private String platform;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Info() {
   }

   public Info(String buildDate, String compiler, String gitCommit, String gitTreeState, String gitVersion, String goVersion, String major, String minor, String platform) {
      this.buildDate = buildDate;
      this.compiler = compiler;
      this.gitCommit = gitCommit;
      this.gitTreeState = gitTreeState;
      this.gitVersion = gitVersion;
      this.goVersion = goVersion;
      this.major = major;
      this.minor = minor;
      this.platform = platform;
   }

   @JsonProperty("buildDate")
   public String getBuildDate() {
      return this.buildDate;
   }

   @JsonProperty("buildDate")
   public void setBuildDate(String buildDate) {
      this.buildDate = buildDate;
   }

   @JsonProperty("compiler")
   public String getCompiler() {
      return this.compiler;
   }

   @JsonProperty("compiler")
   public void setCompiler(String compiler) {
      this.compiler = compiler;
   }

   @JsonProperty("gitCommit")
   public String getGitCommit() {
      return this.gitCommit;
   }

   @JsonProperty("gitCommit")
   public void setGitCommit(String gitCommit) {
      this.gitCommit = gitCommit;
   }

   @JsonProperty("gitTreeState")
   public String getGitTreeState() {
      return this.gitTreeState;
   }

   @JsonProperty("gitTreeState")
   public void setGitTreeState(String gitTreeState) {
      this.gitTreeState = gitTreeState;
   }

   @JsonProperty("gitVersion")
   public String getGitVersion() {
      return this.gitVersion;
   }

   @JsonProperty("gitVersion")
   public void setGitVersion(String gitVersion) {
      this.gitVersion = gitVersion;
   }

   @JsonProperty("goVersion")
   public String getGoVersion() {
      return this.goVersion;
   }

   @JsonProperty("goVersion")
   public void setGoVersion(String goVersion) {
      this.goVersion = goVersion;
   }

   @JsonProperty("major")
   public String getMajor() {
      return this.major;
   }

   @JsonProperty("major")
   public void setMajor(String major) {
      this.major = major;
   }

   @JsonProperty("minor")
   public String getMinor() {
      return this.minor;
   }

   @JsonProperty("minor")
   public void setMinor(String minor) {
      this.minor = minor;
   }

   @JsonProperty("platform")
   public String getPlatform() {
      return this.platform;
   }

   @JsonProperty("platform")
   public void setPlatform(String platform) {
      this.platform = platform;
   }

   @JsonIgnore
   public InfoBuilder edit() {
      return new InfoBuilder(this);
   }

   @JsonIgnore
   public InfoBuilder toBuilder() {
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
      String var10000 = this.getBuildDate();
      return "Info(buildDate=" + var10000 + ", compiler=" + this.getCompiler() + ", gitCommit=" + this.getGitCommit() + ", gitTreeState=" + this.getGitTreeState() + ", gitVersion=" + this.getGitVersion() + ", goVersion=" + this.getGoVersion() + ", major=" + this.getMajor() + ", minor=" + this.getMinor() + ", platform=" + this.getPlatform() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Info)) {
         return false;
      } else {
         Info other = (Info)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$buildDate = this.getBuildDate();
            Object other$buildDate = other.getBuildDate();
            if (this$buildDate == null) {
               if (other$buildDate != null) {
                  return false;
               }
            } else if (!this$buildDate.equals(other$buildDate)) {
               return false;
            }

            Object this$compiler = this.getCompiler();
            Object other$compiler = other.getCompiler();
            if (this$compiler == null) {
               if (other$compiler != null) {
                  return false;
               }
            } else if (!this$compiler.equals(other$compiler)) {
               return false;
            }

            Object this$gitCommit = this.getGitCommit();
            Object other$gitCommit = other.getGitCommit();
            if (this$gitCommit == null) {
               if (other$gitCommit != null) {
                  return false;
               }
            } else if (!this$gitCommit.equals(other$gitCommit)) {
               return false;
            }

            Object this$gitTreeState = this.getGitTreeState();
            Object other$gitTreeState = other.getGitTreeState();
            if (this$gitTreeState == null) {
               if (other$gitTreeState != null) {
                  return false;
               }
            } else if (!this$gitTreeState.equals(other$gitTreeState)) {
               return false;
            }

            Object this$gitVersion = this.getGitVersion();
            Object other$gitVersion = other.getGitVersion();
            if (this$gitVersion == null) {
               if (other$gitVersion != null) {
                  return false;
               }
            } else if (!this$gitVersion.equals(other$gitVersion)) {
               return false;
            }

            Object this$goVersion = this.getGoVersion();
            Object other$goVersion = other.getGoVersion();
            if (this$goVersion == null) {
               if (other$goVersion != null) {
                  return false;
               }
            } else if (!this$goVersion.equals(other$goVersion)) {
               return false;
            }

            Object this$major = this.getMajor();
            Object other$major = other.getMajor();
            if (this$major == null) {
               if (other$major != null) {
                  return false;
               }
            } else if (!this$major.equals(other$major)) {
               return false;
            }

            Object this$minor = this.getMinor();
            Object other$minor = other.getMinor();
            if (this$minor == null) {
               if (other$minor != null) {
                  return false;
               }
            } else if (!this$minor.equals(other$minor)) {
               return false;
            }

            Object this$platform = this.getPlatform();
            Object other$platform = other.getPlatform();
            if (this$platform == null) {
               if (other$platform != null) {
                  return false;
               }
            } else if (!this$platform.equals(other$platform)) {
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
      return other instanceof Info;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $buildDate = this.getBuildDate();
      result = result * 59 + ($buildDate == null ? 43 : $buildDate.hashCode());
      Object $compiler = this.getCompiler();
      result = result * 59 + ($compiler == null ? 43 : $compiler.hashCode());
      Object $gitCommit = this.getGitCommit();
      result = result * 59 + ($gitCommit == null ? 43 : $gitCommit.hashCode());
      Object $gitTreeState = this.getGitTreeState();
      result = result * 59 + ($gitTreeState == null ? 43 : $gitTreeState.hashCode());
      Object $gitVersion = this.getGitVersion();
      result = result * 59 + ($gitVersion == null ? 43 : $gitVersion.hashCode());
      Object $goVersion = this.getGoVersion();
      result = result * 59 + ($goVersion == null ? 43 : $goVersion.hashCode());
      Object $major = this.getMajor();
      result = result * 59 + ($major == null ? 43 : $major.hashCode());
      Object $minor = this.getMinor();
      result = result * 59 + ($minor == null ? 43 : $minor.hashCode());
      Object $platform = this.getPlatform();
      result = result * 59 + ($platform == null ? 43 : $platform.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
