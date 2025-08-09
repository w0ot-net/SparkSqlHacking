package org.apache.parquet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SemanticVersion implements Comparable {
   private static final String FORMAT = "^(\\d+)\\.(\\d+)\\.(\\d+)([^-+]*)?(?:-([^+]*))?(?:\\+(.*))?$";
   private static final Pattern PATTERN = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)([^-+]*)?(?:-([^+]*))?(?:\\+(.*))?$");
   public final int major;
   public final int minor;
   public final int patch;
   public final boolean prerelease;
   public final String unknown;
   public final Prerelease pre;
   public final String buildInfo;

   public SemanticVersion(int major, int minor, int patch) {
      Preconditions.checkArgument(major >= 0, "major must be >= 0");
      Preconditions.checkArgument(minor >= 0, "minor must be >= 0");
      Preconditions.checkArgument(patch >= 0, "patch must be >= 0");
      this.major = major;
      this.minor = minor;
      this.patch = patch;
      this.prerelease = false;
      this.unknown = null;
      this.pre = null;
      this.buildInfo = null;
   }

   public SemanticVersion(int major, int minor, int patch, boolean hasUnknown) {
      Preconditions.checkArgument(major >= 0, "major must be >= 0");
      Preconditions.checkArgument(minor >= 0, "minor must be >= 0");
      Preconditions.checkArgument(patch >= 0, "patch must be >= 0");
      this.major = major;
      this.minor = minor;
      this.patch = patch;
      this.prerelease = hasUnknown;
      this.unknown = null;
      this.pre = null;
      this.buildInfo = null;
   }

   public SemanticVersion(int major, int minor, int patch, String unknown, String pre, String buildInfo) {
      Preconditions.checkArgument(major >= 0, "major must be >= 0");
      Preconditions.checkArgument(minor >= 0, "minor must be >= 0");
      Preconditions.checkArgument(patch >= 0, "patch must be >= 0");
      this.major = major;
      this.minor = minor;
      this.patch = patch;
      this.prerelease = unknown != null && !unknown.isEmpty();
      this.unknown = unknown;
      this.pre = pre != null ? new Prerelease(pre) : null;
      this.buildInfo = buildInfo;
   }

   public static SemanticVersion parse(String version) throws SemanticVersionParseException {
      Matcher matcher = PATTERN.matcher(version);
      if (!matcher.matches()) {
         throw new SemanticVersionParseException("" + version + " does not match format " + "^(\\d+)\\.(\\d+)\\.(\\d+)([^-+]*)?(?:-([^+]*))?(?:\\+(.*))?$");
      } else {
         int major;
         int minor;
         int patch;
         try {
            major = Integer.parseInt(matcher.group(1));
            minor = Integer.parseInt(matcher.group(2));
            patch = Integer.parseInt(matcher.group(3));
         } catch (NumberFormatException e) {
            throw new SemanticVersionParseException(e);
         }

         String unknown = matcher.group(4);
         String prerelease = matcher.group(5);
         String buildInfo = matcher.group(6);
         if (major >= 0 && minor >= 0 && patch >= 0) {
            return new SemanticVersion(major, minor, patch, unknown, prerelease, buildInfo);
         } else {
            throw new SemanticVersionParseException(String.format("major(%d), minor(%d), and patch(%d) must all be >= 0", major, minor, patch));
         }
      }
   }

   public int compareTo(SemanticVersion o) {
      int cmp = compareIntegers(this.major, o.major);
      if (cmp != 0) {
         return cmp;
      } else {
         cmp = compareIntegers(this.minor, o.minor);
         if (cmp != 0) {
            return cmp;
         } else {
            cmp = compareIntegers(this.patch, o.patch);
            if (cmp != 0) {
               return cmp;
            } else {
               cmp = compareBooleans(o.prerelease, this.prerelease);
               if (cmp != 0) {
                  return cmp;
               } else if (this.pre != null) {
                  return o.pre != null ? this.pre.compareTo(o.pre) : -1;
               } else {
                  return o.pre != null ? 1 : 0;
               }
            }
         }
      }
   }

   private static int compareIntegers(int x, int y) {
      return x < y ? -1 : (x == y ? 0 : 1);
   }

   private static int compareBooleans(boolean x, boolean y) {
      return x == y ? 0 : (x ? 1 : -1);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         SemanticVersion that = (SemanticVersion)o;
         return this.compareTo(that) == 0;
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.major;
      result = 31 * result + this.minor;
      result = 31 * result + this.patch;
      return result;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(this.major).append(".").append(this.minor).append(".").append(this.patch);
      if (this.prerelease) {
         sb.append(this.unknown);
      }

      if (this.pre != null) {
         sb.append(this.pre.original);
      }

      if (this.buildInfo != null) {
         sb.append(this.buildInfo);
      }

      return sb.toString();
   }

   private static class NumberOrString implements Comparable {
      private static final Pattern NUMERIC = Pattern.compile("\\d+");
      private final String original;
      private final boolean isNumeric;
      private final int number;

      public NumberOrString(String numberOrString) {
         this.original = numberOrString;
         this.isNumeric = NUMERIC.matcher(numberOrString).matches();
         if (this.isNumeric) {
            this.number = Integer.parseInt(numberOrString);
         } else {
            this.number = -1;
         }

      }

      public int compareTo(NumberOrString that) {
         int cmp = SemanticVersion.compareBooleans(that.isNumeric, this.isNumeric);
         if (cmp != 0) {
            return cmp;
         } else {
            return this.isNumeric ? SemanticVersion.compareIntegers(this.number, that.number) : this.original.compareTo(that.original);
         }
      }

      public String toString() {
         return this.original;
      }
   }

   private static class Prerelease implements Comparable {
      private static final Pattern DOT = Pattern.compile("\\.");
      private final String original;
      private final List identifiers = new ArrayList();

      public Prerelease(String original) {
         this.original = original;

         for(String identifier : DOT.split(original)) {
            this.identifiers.add(new NumberOrString(identifier));
         }

      }

      public int compareTo(Prerelease that) {
         int size = Math.min(this.identifiers.size(), that.identifiers.size());

         for(int i = 0; i < size; ++i) {
            int cmp = ((NumberOrString)this.identifiers.get(i)).compareTo((NumberOrString)that.identifiers.get(i));
            if (cmp != 0) {
               return cmp;
            }
         }

         return SemanticVersion.compareIntegers(this.identifiers.size(), that.identifiers.size());
      }

      public String toString() {
         return this.original;
      }
   }

   public static class SemanticVersionParseException extends Exception {
      public SemanticVersionParseException() {
      }

      public SemanticVersionParseException(String message) {
         super(message);
      }

      public SemanticVersionParseException(String message, Throwable cause) {
         super(message, cause);
      }

      public SemanticVersionParseException(Throwable cause) {
         super(cause);
      }
   }
}
