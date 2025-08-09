package io.fabric8.kubernetes.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VersionInfo {
   private Date buildDate;
   private String gitCommit;
   private String gitVersion;
   private String major;
   private String minor;
   private String gitTreeState;
   private String platform;
   private String goVersion;
   private String compiler;

   public Date getBuildDate() {
      return this.buildDate;
   }

   public String getGitCommit() {
      return this.gitCommit;
   }

   public String getGitVersion() {
      return this.gitVersion;
   }

   public String getMajor() {
      return this.major;
   }

   public String getMinor() {
      return this.minor;
   }

   public String getGitTreeState() {
      return this.gitTreeState;
   }

   public String getPlatform() {
      return this.platform;
   }

   public String getGoVersion() {
      return this.goVersion;
   }

   public String getCompiler() {
      return this.compiler;
   }

   private VersionInfo() {
   }

   public static final class VersionKeys {
      public static final String BUILD_DATE = "buildDate";
      public static final String GIT_COMMIT = "gitCommit";
      public static final String GIT_VERSION = "gitVersion";
      public static final String MAJOR = "major";
      public static final String MINOR = "minor";
      public static final String GIT_TREE_STATE = "gitTreeState";
      public static final String PLATFORM = "platform";
      public static final String GO_VERSION = "goVersion";
      public static final String COMPILER = "compiler";
      public static final String BUILD_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX";

      private VersionKeys() {
      }
   }

   public static class Builder {
      private VersionInfo versionInfo = new VersionInfo();

      public Builder() {
      }

      public Builder(VersionInfo versionInfo) {
         if (versionInfo != null) {
            this.versionInfo.buildDate = versionInfo.getBuildDate();
            this.versionInfo.gitCommit = versionInfo.getGitCommit();
            this.versionInfo.gitVersion = versionInfo.getGitVersion();
            this.versionInfo.major = versionInfo.getMajor();
            this.versionInfo.minor = versionInfo.getMinor();
            this.versionInfo.gitTreeState = versionInfo.getGitTreeState();
            this.versionInfo.platform = versionInfo.getPlatform();
            this.versionInfo.goVersion = versionInfo.getGoVersion();
            this.versionInfo.compiler = versionInfo.getCompiler();
         }

      }

      public Builder withBuildDate(String buildDate) throws ParseException {
         if (buildDate != null) {
            this.versionInfo.buildDate = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")).parse(buildDate);
         }

         return this;
      }

      public Builder withGitCommit(String gitCommit) {
         this.versionInfo.gitCommit = gitCommit;
         return this;
      }

      public Builder withGitVersion(String gitVersion) {
         this.versionInfo.gitVersion = gitVersion;
         return this;
      }

      public Builder withMajor(String major) {
         this.versionInfo.major = major;
         return this;
      }

      public Builder withMinor(String minor) {
         this.versionInfo.minor = minor;
         return this;
      }

      public Builder withGitTreeState(String gitTreeState) {
         this.versionInfo.gitTreeState = gitTreeState;
         return this;
      }

      public Builder withPlatform(String platform) {
         this.versionInfo.platform = platform;
         return this;
      }

      public Builder withGoVersion(String goVersion) {
         this.versionInfo.goVersion = goVersion;
         return this;
      }

      public Builder withCompiler(String compiler) {
         this.versionInfo.compiler = compiler;
         return this;
      }

      public VersionInfo build() {
         return this.versionInfo;
      }
   }
}
