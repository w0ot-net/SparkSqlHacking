package io.fabric8.kubernetes.client.utils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KubernetesVersionFactory {
   private KubernetesVersionFactory() {
   }

   public static Version create(String versionValue) {
      Version version = KubernetesVersionFactory.KubernetesVersion.FACTORY.create(versionValue);
      if (version == null) {
         version = KubernetesVersionFactory.NonKubernetesVersion.FACTORY.create(versionValue);
      }

      return version;
   }

   public static class KubernetesVersion extends Version {
      public static final VersionFactory FACTORY = new VersionFactory() {
         private final Pattern versionPattern = Pattern.compile("v([0-9]+)((alpha|beta)([0-9]+)?)*");

         public KubernetesVersion create(String version) {
            if (version == null) {
               return null;
            } else {
               Matcher matcher = this.versionPattern.matcher(version);
               if (!matcher.matches()) {
                  return null;
               } else {
                  Integer majorValue = this.getInt(matcher.group(1));
                  String qualifierValue = matcher.group(3);
                  Integer minorValue = this.getInt(matcher.group(4));
                  return new KubernetesVersion(majorValue, qualifierValue, minorValue, version);
               }
            }
         }

         private Integer getInt(String value) {
            if (value == null) {
               return null;
            } else {
               try {
                  return Integer.parseInt(value);
               } catch (NumberFormatException var3) {
                  return null;
               }
            }
         }
      };
      private final Integer major;
      private final Optional qualifier;
      private final Optional minor;

      private KubernetesVersion(Integer major, String qualifier, Integer minor, String version) {
         super(version);
         this.major = major;
         this.qualifier = Optional.ofNullable(qualifier);
         this.minor = Optional.ofNullable(minor);
      }

      public Integer getMajor() {
         return this.major;
      }

      public Optional getQualifier() {
         return this.qualifier;
      }

      public Optional getMinor() {
         return this.minor;
      }

      public boolean isStable() {
         return this.qualifier.orElse((Object)null) == null;
      }

      public boolean isKubernetes() {
         return true;
      }

      public int compareTo(Version other) {
         if (other == this) {
            return 0;
         } else if (other instanceof NonKubernetesVersion) {
            return 1;
         } else if (!(other instanceof KubernetesVersion)) {
            return 1;
         } else {
            KubernetesVersion otherKube = (KubernetesVersion)other;
            if (this.qualifier.isPresent()) {
               if (!otherKube.qualifier.isPresent()) {
                  return -1;
               } else {
                  int qualifierComparison = ((String)this.qualifier.get()).compareTo((String)otherKube.qualifier.orElse((Object)null));
                  if (qualifierComparison != 0) {
                     return qualifierComparison;
                  } else {
                     int majorComparison = this.compareMajor(otherKube);
                     return majorComparison != 0 ? majorComparison : this.compareMinor(otherKube);
                  }
               }
            } else {
               return !otherKube.qualifier.isPresent() ? this.compareMajor(otherKube) : 1;
            }
         }
      }

      private int compareMajor(KubernetesVersion other) {
         return this.major.compareTo(other.major);
      }

      private int compareMinor(KubernetesVersion other) {
         if (this.minor.isPresent()) {
            return !other.minor.isPresent() ? 1 : ((Integer)this.minor.get()).compareTo((Integer)other.minor.orElse((Object)null));
         } else {
            return !other.minor.isPresent() ? 0 : -1;
         }
      }
   }

   public static class NonKubernetesVersion extends Version {
      public static final VersionFactory FACTORY = (version) -> version == null ? null : new NonKubernetesVersion(version);

      private NonKubernetesVersion(String version) {
         super(version);
      }

      public boolean isKubernetes() {
         return false;
      }

      public int compareTo(Version other) {
         if (other == this) {
            return 0;
         } else if (other instanceof KubernetesVersion) {
            return -1;
         } else {
            return other instanceof NonKubernetesVersion ? this.full.compareTo(other.full) * -1 : -1;
         }
      }
   }

   protected abstract static class Version implements Comparable {
      protected final String full;

      protected Version(String full) {
         this.full = full;
      }

      public abstract boolean isKubernetes();

      public String getFull() {
         return this.full;
      }

      public String toString() {
         return this.full;
      }
   }

   public interface VersionFactory {
      Version create(String var1);
   }
}
