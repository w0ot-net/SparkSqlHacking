package io.fabric8.kubernetes.api.model.version;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class InfoFluent extends BaseFluent {
   private String buildDate;
   private String compiler;
   private String gitCommit;
   private String gitTreeState;
   private String gitVersion;
   private String goVersion;
   private String major;
   private String minor;
   private String platform;
   private Map additionalProperties;

   public InfoFluent() {
   }

   public InfoFluent(Info instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Info instance) {
      instance = instance != null ? instance : new Info();
      if (instance != null) {
         this.withBuildDate(instance.getBuildDate());
         this.withCompiler(instance.getCompiler());
         this.withGitCommit(instance.getGitCommit());
         this.withGitTreeState(instance.getGitTreeState());
         this.withGitVersion(instance.getGitVersion());
         this.withGoVersion(instance.getGoVersion());
         this.withMajor(instance.getMajor());
         this.withMinor(instance.getMinor());
         this.withPlatform(instance.getPlatform());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getBuildDate() {
      return this.buildDate;
   }

   public InfoFluent withBuildDate(String buildDate) {
      this.buildDate = buildDate;
      return this;
   }

   public boolean hasBuildDate() {
      return this.buildDate != null;
   }

   public String getCompiler() {
      return this.compiler;
   }

   public InfoFluent withCompiler(String compiler) {
      this.compiler = compiler;
      return this;
   }

   public boolean hasCompiler() {
      return this.compiler != null;
   }

   public String getGitCommit() {
      return this.gitCommit;
   }

   public InfoFluent withGitCommit(String gitCommit) {
      this.gitCommit = gitCommit;
      return this;
   }

   public boolean hasGitCommit() {
      return this.gitCommit != null;
   }

   public String getGitTreeState() {
      return this.gitTreeState;
   }

   public InfoFluent withGitTreeState(String gitTreeState) {
      this.gitTreeState = gitTreeState;
      return this;
   }

   public boolean hasGitTreeState() {
      return this.gitTreeState != null;
   }

   public String getGitVersion() {
      return this.gitVersion;
   }

   public InfoFluent withGitVersion(String gitVersion) {
      this.gitVersion = gitVersion;
      return this;
   }

   public boolean hasGitVersion() {
      return this.gitVersion != null;
   }

   public String getGoVersion() {
      return this.goVersion;
   }

   public InfoFluent withGoVersion(String goVersion) {
      this.goVersion = goVersion;
      return this;
   }

   public boolean hasGoVersion() {
      return this.goVersion != null;
   }

   public String getMajor() {
      return this.major;
   }

   public InfoFluent withMajor(String major) {
      this.major = major;
      return this;
   }

   public boolean hasMajor() {
      return this.major != null;
   }

   public String getMinor() {
      return this.minor;
   }

   public InfoFluent withMinor(String minor) {
      this.minor = minor;
      return this;
   }

   public boolean hasMinor() {
      return this.minor != null;
   }

   public String getPlatform() {
      return this.platform;
   }

   public InfoFluent withPlatform(String platform) {
      this.platform = platform;
      return this;
   }

   public boolean hasPlatform() {
      return this.platform != null;
   }

   public InfoFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public InfoFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public InfoFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public InfoFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public InfoFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            InfoFluent that = (InfoFluent)o;
            if (!Objects.equals(this.buildDate, that.buildDate)) {
               return false;
            } else if (!Objects.equals(this.compiler, that.compiler)) {
               return false;
            } else if (!Objects.equals(this.gitCommit, that.gitCommit)) {
               return false;
            } else if (!Objects.equals(this.gitTreeState, that.gitTreeState)) {
               return false;
            } else if (!Objects.equals(this.gitVersion, that.gitVersion)) {
               return false;
            } else if (!Objects.equals(this.goVersion, that.goVersion)) {
               return false;
            } else if (!Objects.equals(this.major, that.major)) {
               return false;
            } else if (!Objects.equals(this.minor, that.minor)) {
               return false;
            } else if (!Objects.equals(this.platform, that.platform)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.buildDate, this.compiler, this.gitCommit, this.gitTreeState, this.gitVersion, this.goVersion, this.major, this.minor, this.platform, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.buildDate != null) {
         sb.append("buildDate:");
         sb.append(this.buildDate + ",");
      }

      if (this.compiler != null) {
         sb.append("compiler:");
         sb.append(this.compiler + ",");
      }

      if (this.gitCommit != null) {
         sb.append("gitCommit:");
         sb.append(this.gitCommit + ",");
      }

      if (this.gitTreeState != null) {
         sb.append("gitTreeState:");
         sb.append(this.gitTreeState + ",");
      }

      if (this.gitVersion != null) {
         sb.append("gitVersion:");
         sb.append(this.gitVersion + ",");
      }

      if (this.goVersion != null) {
         sb.append("goVersion:");
         sb.append(this.goVersion + ",");
      }

      if (this.major != null) {
         sb.append("major:");
         sb.append(this.major + ",");
      }

      if (this.minor != null) {
         sb.append("minor:");
         sb.append(this.minor + ",");
      }

      if (this.platform != null) {
         sb.append("platform:");
         sb.append(this.platform + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
