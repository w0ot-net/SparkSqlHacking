package org.apache.ivy.osgi.util;

import java.text.ParseException;
import org.apache.ivy.util.StringUtils;

public class VersionRange {
   private boolean startExclusive;
   private Version startVersion;
   private boolean endExclusive;
   private Version endVersion;

   public VersionRange(String versionStr) throws ParseException {
      if (StringUtils.isNullOrEmpty(versionStr)) {
         this.startExclusive = false;
         this.startVersion = new Version(0, 0, 0, (String)null);
         this.endExclusive = true;
         this.endVersion = null;
      } else {
         (new VersionRangeParser(versionStr)).parse();
      }

   }

   public VersionRange(boolean startExclusive, Version startVersion, boolean endExclusive, Version endVersion) {
      this.startExclusive = startExclusive;
      this.startVersion = startVersion;
      this.endExclusive = endExclusive;
      this.endVersion = endVersion;
   }

   public VersionRange(Version startVersion) {
      this.startExclusive = false;
      this.startVersion = startVersion;
      this.endExclusive = true;
      this.endVersion = null;
   }

   public String toString() {
      return (this.startExclusive ? "(" : "[") + this.startVersion.toString() + "," + (this.endVersion == null ? "" : this.endVersion.toString()) + (this.endExclusive ? ")" : "]");
   }

   public String toIvyRevision() {
      StringBuilder buffer = new StringBuilder();
      buffer.append(this.startExclusive ? "(" : "[").append(this.startVersion).append(",");
      if (this.endVersion != null) {
         if (this.endExclusive && !this.startVersion.equals(this.endVersion)) {
            buffer.append(this.endVersion);
         } else {
            buffer.append(this.endVersion.withNudgedPatch());
         }
      }

      return buffer.append(")").toString();
   }

   public boolean isEndExclusive() {
      return this.endExclusive;
   }

   public Version getEndVersion() {
      return this.endVersion;
   }

   public boolean isStartExclusive() {
      return this.startExclusive;
   }

   public Version getStartVersion() {
      return this.startVersion;
   }

   public boolean isClosedRange() {
      return this.startVersion.equals(this.endVersion);
   }

   public boolean contains(String versionStr) {
      return this.contains(new Version(versionStr));
   }

   public boolean contains(Version version) {
      boolean var10000;
      label36: {
         label31: {
            if (this.startExclusive) {
               if (version.compareUnqualified(this.startVersion) <= 0) {
                  break label31;
               }
            } else if (version.compareUnqualified(this.startVersion) < 0) {
               break label31;
            }

            if (this.endVersion == null) {
               break label36;
            }

            if (this.endExclusive) {
               if (version.compareUnqualified(this.endVersion) < 0) {
                  break label36;
               }
            } else if (version.compareUnqualified(this.endVersion) <= 0) {
               break label36;
            }
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.endExclusive ? 1231 : 1237);
      result = 31 * result + (this.endVersion == null ? 0 : this.endVersion.hashCode());
      result = 31 * result + (this.startExclusive ? 1231 : 1237);
      result = 31 * result + (this.startVersion == null ? 0 : this.startVersion.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && obj instanceof VersionRange) {
         VersionRange other = (VersionRange)obj;
         if (this.endExclusive != other.endExclusive) {
            return false;
         } else {
            if (this.endVersion == null) {
               if (other.endVersion != null) {
                  return false;
               }
            } else if (!this.endVersion.equals(other.endVersion)) {
               return false;
            }

            boolean var10000;
            label52: {
               if (this.startExclusive == other.startExclusive) {
                  if (this.startVersion == null) {
                     if (other.startVersion == null) {
                        break label52;
                     }
                  } else if (this.startVersion.equals(other.startVersion)) {
                     break label52;
                  }
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }
      } else {
         return false;
      }
   }

   class VersionRangeParser {
      private final String version;
      private int length;
      private int pos = 0;
      private char c;

      VersionRangeParser(String version) {
         this.version = version;
         this.length = version.length();
      }

      void parse() throws ParseException {
         boolean range = this.parseStart();
         VersionRange.this.startVersion = this.parseVersion();
         if (VersionRange.this.startVersion == null) {
            throw new ParseException("Expecting a number", this.pos);
         } else {
            if (this.parseVersionSeparator()) {
               VersionRange.this.endVersion = this.parseVersion();
               this.parseEnd();
            } else {
               if (range) {
                  throw new ParseException("Expecting ,", this.pos);
               }

               VersionRange.this.endVersion = null;
               VersionRange.this.startExclusive = false;
               VersionRange.this.endExclusive = false;
            }

         }
      }

      private char readNext() {
         if (this.pos == this.length) {
            this.c = 0;
         } else {
            this.c = this.version.charAt(this.pos++);
         }

         return this.c;
      }

      private void unread() {
         if (this.pos > 0) {
            --this.pos;
         }

      }

      private boolean parseStart() {
         this.skipWhiteSpace();
         switch (this.readNext()) {
            case '(':
               VersionRange.this.startExclusive = true;
               return true;
            case '[':
               VersionRange.this.startExclusive = false;
               return true;
            default:
               this.unread();
               return false;
         }
      }

      private void skipWhiteSpace() {
         while(true) {
            switch (this.readNext()) {
               case ' ':
                  if (this.pos < this.length) {
                     break;
                  }

                  return;
               default:
                  this.unread();
                  return;
            }
         }
      }

      private Version parseVersion() {
         Integer major = this.parseNumber();
         if (major == null) {
            return null;
         } else {
            Integer minor = 0;
            Integer patch = 0;
            String qualifier = null;
            if (this.parseNumberSeparator()) {
               minor = this.parseNumber();
               if (minor == null) {
                  minor = 0;
               } else if (this.parseNumberSeparator()) {
                  patch = this.parseNumber();
                  if (patch == null) {
                     patch = 0;
                  } else if (this.parseNumberSeparator()) {
                     qualifier = this.parseQualifier();
                  }
               }
            }

            return new Version(major, minor, patch, qualifier);
         }
      }

      private Integer parseNumber() {
         this.skipWhiteSpace();
         Integer n = null;

         label21:
         while(true) {
            switch (this.readNext()) {
               case '\u0000':
                  return n;
               case '0':
               case '1':
               case '2':
               case '3':
               case '4':
               case '5':
               case '6':
               case '7':
               case '8':
               case '9':
                  n = (n == null ? 0 : n * 10) + this.c - 48;
                  if (this.pos >= this.length) {
                     break label21;
                  }
                  break;
               default:
                  this.unread();
                  return n;
            }
         }

         return n;
      }

      private boolean parseNumberSeparator() {
         switch (this.readNext()) {
            case '.':
               return true;
            default:
               this.unread();
               return false;
         }
      }

      private boolean parseVersionSeparator() {
         this.skipWhiteSpace();
         switch (this.readNext()) {
            case ',':
               return true;
            default:
               this.unread();
               return false;
         }
      }

      private String parseQualifier() {
         StringBuilder q = new StringBuilder();

         do {
            this.readNext();
            if ((this.c < 'a' || this.c > 'z') && (this.c < 'A' || this.c > 'Z') && (this.c < '0' || this.c > '9') && this.c != '-' && this.c != '_') {
               this.unread();
               break;
            }

            q.append(this.c);
         } while(this.pos < this.length);

         return q.length() == 0 ? null : q.toString();
      }

      private void parseEnd() throws ParseException {
         this.skipWhiteSpace();
         switch (this.readNext()) {
            case ')':
               VersionRange.this.endExclusive = true;
               break;
            case ']':
               VersionRange.this.endExclusive = false;
               break;
            default:
               this.unread();
               throw new ParseException("Expecting ] or )", this.pos);
         }

      }
   }
}
