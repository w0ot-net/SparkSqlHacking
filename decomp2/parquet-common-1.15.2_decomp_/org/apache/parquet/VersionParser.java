package org.apache.parquet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionParser {
   public static final String FORMAT = "(.*?)\\s+version\\s*(?:([^(]*?)\\s*(?:\\(\\s*build\\s*([^)]*?)\\s*\\))?)?";
   public static final Pattern PATTERN = Pattern.compile("(.*?)\\s+version\\s*(?:([^(]*?)\\s*(?:\\(\\s*build\\s*([^)]*?)\\s*\\))?)?");

   public static ParsedVersion parse(String createdBy) throws VersionParseException {
      Matcher matcher = PATTERN.matcher(createdBy);
      if (!matcher.matches()) {
         throw new VersionParseException("Could not parse created_by: " + createdBy + " using format: " + "(.*?)\\s+version\\s*(?:([^(]*?)\\s*(?:\\(\\s*build\\s*([^)]*?)\\s*\\))?)?");
      } else {
         String application = matcher.group(1);
         String semver = matcher.group(2);
         String appBuildHash = matcher.group(3);
         if (Strings.isNullOrEmpty(application)) {
            throw new VersionParseException("application cannot be null or empty");
         } else {
            return new ParsedVersion(application, semver, appBuildHash);
         }
      }
   }

   public static class ParsedVersion {
      public final String application;
      public final String version;
      public final String appBuildHash;
      private final boolean hasSemver;
      private final SemanticVersion semver;

      public ParsedVersion(String application, String version, String appBuildHash) {
         Preconditions.checkArgument(!Strings.isNullOrEmpty(application), "application cannot be null or empty");
         this.application = application;
         this.version = Strings.isNullOrEmpty(version) ? null : version;
         this.appBuildHash = Strings.isNullOrEmpty(appBuildHash) ? null : appBuildHash;

         SemanticVersion sv;
         boolean hasSemver;
         try {
            sv = SemanticVersion.parse(version);
            hasSemver = true;
         } catch (SemanticVersion.SemanticVersionParseException | RuntimeException var7) {
            sv = null;
            hasSemver = false;
         }

         this.semver = sv;
         this.hasSemver = hasSemver;
      }

      public boolean hasSemanticVersion() {
         return this.hasSemver;
      }

      public SemanticVersion getSemanticVersion() {
         return this.semver;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            ParsedVersion version = (ParsedVersion)o;
            if (this.appBuildHash != null) {
               if (!this.appBuildHash.equals(version.appBuildHash)) {
                  return false;
               }
            } else if (version.appBuildHash != null) {
               return false;
            }

            if (this.application != null) {
               if (!this.application.equals(version.application)) {
                  return false;
               }
            } else if (version.application != null) {
               return false;
            }

            if (this.version != null) {
               if (!this.version.equals(version.version)) {
                  return false;
               }
            } else if (version.version != null) {
               return false;
            }

            return true;
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = this.application != null ? this.application.hashCode() : 0;
         result = 31 * result + (this.version != null ? this.version.hashCode() : 0);
         result = 31 * result + (this.appBuildHash != null ? this.appBuildHash.hashCode() : 0);
         return result;
      }

      public String toString() {
         return "ParsedVersion(application=" + this.application + ", semver=" + this.version + ", appBuildHash=" + this.appBuildHash + ')';
      }
   }

   public static class VersionParseException extends Exception {
      public VersionParseException(String message) {
         super(message);
      }
   }
}
