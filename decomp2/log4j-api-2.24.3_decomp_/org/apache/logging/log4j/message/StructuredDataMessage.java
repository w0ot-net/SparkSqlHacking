package org.apache.logging.log4j.message;

import java.util.Map;
import org.apache.logging.log4j.util.EnglishEnums;
import org.apache.logging.log4j.util.StringBuilders;

@AsynchronouslyFormattable
public class StructuredDataMessage extends MapMessage {
   private static final long serialVersionUID = 1703221292892071920L;
   private static final int MAX_LENGTH = 32;
   private static final int HASHVAL = 31;
   private StructuredDataId id;
   private String message;
   private String type;
   private final int maxLength;

   public StructuredDataMessage(final String id, final String msg, final String type) {
      this((String)id, msg, type, 32);
   }

   public StructuredDataMessage(final String id, final String msg, final String type, final int maxLength) {
      this.id = new StructuredDataId(id, (String[])null, (String[])null, maxLength);
      this.message = msg;
      this.type = type;
      this.maxLength = maxLength;
   }

   public StructuredDataMessage(final String id, final String msg, final String type, final Map data) {
      this((String)id, msg, type, data, 32);
   }

   public StructuredDataMessage(final String id, final String msg, final String type, final Map data, final int maxLength) {
      super(data);
      this.id = new StructuredDataId(id, (String[])null, (String[])null, maxLength);
      this.message = msg;
      this.type = type;
      this.maxLength = maxLength;
   }

   public StructuredDataMessage(final StructuredDataId id, final String msg, final String type) {
      this((StructuredDataId)id, msg, type, 32);
   }

   public StructuredDataMessage(final StructuredDataId id, final String msg, final String type, final int maxLength) {
      this.id = id;
      this.message = msg;
      this.type = type;
      this.maxLength = maxLength;
   }

   public StructuredDataMessage(final StructuredDataId id, final String msg, final String type, final Map data) {
      this((StructuredDataId)id, msg, type, data, 32);
   }

   public StructuredDataMessage(final StructuredDataId id, final String msg, final String type, final Map data, final int maxLength) {
      super(data);
      this.id = id;
      this.message = msg;
      this.type = type;
      this.maxLength = maxLength;
   }

   private StructuredDataMessage(final StructuredDataMessage msg, final Map map) {
      super(map);
      this.id = msg.id;
      this.message = msg.message;
      this.type = msg.type;
      this.maxLength = 32;
   }

   protected StructuredDataMessage() {
      this.maxLength = 32;
   }

   public String[] getFormats() {
      String[] formats = new String[StructuredDataMessage.Format.values().length];
      int i = 0;

      for(Format format : StructuredDataMessage.Format.values()) {
         formats[i++] = format.name();
      }

      return formats;
   }

   public StructuredDataId getId() {
      return this.id;
   }

   protected void setId(final String id) {
      this.id = new StructuredDataId(id, (String[])null, (String[])null);
   }

   protected void setId(final StructuredDataId id) {
      this.id = id;
   }

   public String getType() {
      return this.type;
   }

   protected void setType(final String type) {
      if (type.length() > 32) {
         throw new IllegalArgumentException("structured data type exceeds maximum length of 32 characters: " + type);
      } else {
         this.type = type;
      }
   }

   public void formatTo(final StringBuilder buffer) {
      this.asString(StructuredDataMessage.Format.FULL, (StructuredDataId)null, buffer);
   }

   public void formatTo(final String[] formats, final StringBuilder buffer) {
      this.asString(this.getFormat(formats), (StructuredDataId)null, buffer);
   }

   public String getFormat() {
      return this.message;
   }

   protected void setMessageFormat(final String msg) {
      this.message = msg;
   }

   public String asString() {
      return this.asString(StructuredDataMessage.Format.FULL, (StructuredDataId)null);
   }

   public String asString(final String format) {
      try {
         return this.asString((Format)EnglishEnums.valueOf(Format.class, format), (StructuredDataId)null);
      } catch (IllegalArgumentException var3) {
         return this.asString();
      }
   }

   public final String asString(final Format format, final StructuredDataId structuredDataId) {
      StringBuilder sb = new StringBuilder();
      this.asString(format, structuredDataId, sb);
      return sb.toString();
   }

   public final void asString(final Format format, final StructuredDataId structuredDataId, final StringBuilder sb) {
      boolean full = StructuredDataMessage.Format.FULL.equals(format);
      if (full) {
         String myType = this.getType();
         if (myType == null) {
            return;
         }

         sb.append(this.getType()).append(' ');
      }

      StructuredDataId sdId = this.getId();
      if (sdId != null) {
         sdId = sdId.makeId(structuredDataId);
      } else {
         sdId = structuredDataId;
      }

      if (sdId != null && sdId.getName() != null) {
         if (StructuredDataMessage.Format.XML.equals(format)) {
            this.asXml(sdId, sb);
         } else {
            sb.append('[');
            StringBuilders.appendValue(sb, sdId);
            sb.append(' ');
            this.appendMap(sb);
            sb.append(']');
            if (full) {
               String msg = this.getFormat();
               if (msg != null) {
                  sb.append(' ').append(msg);
               }
            }

         }
      }
   }

   private void asXml(final StructuredDataId structuredDataId, final StringBuilder sb) {
      sb.append("<StructuredData>\n");
      sb.append("<type>").append(this.type).append("</type>\n");
      sb.append("<id>").append(structuredDataId).append("</id>\n");
      super.asXml(sb);
      sb.append("\n</StructuredData>\n");
   }

   public String getFormattedMessage() {
      return this.asString(StructuredDataMessage.Format.FULL, (StructuredDataId)null);
   }

   public String getFormattedMessage(final String[] formats) {
      return this.asString(this.getFormat(formats), (StructuredDataId)null);
   }

   private Format getFormat(final String[] formats) {
      if (formats != null && formats.length > 0) {
         for(int i = 0; i < formats.length; ++i) {
            String format = formats[i];
            if (StructuredDataMessage.Format.XML.name().equalsIgnoreCase(format)) {
               return StructuredDataMessage.Format.XML;
            }

            if (StructuredDataMessage.Format.FULL.name().equalsIgnoreCase(format)) {
               return StructuredDataMessage.Format.FULL;
            }
         }

         return null;
      } else {
         return StructuredDataMessage.Format.FULL;
      }
   }

   public String toString() {
      return this.asString((Format)null, (StructuredDataId)null);
   }

   public StructuredDataMessage newInstance(final Map map) {
      return new StructuredDataMessage(this, map);
   }

   public boolean equals(final Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof StructuredDataMessage)) {
         return false;
      } else {
         StructuredDataMessage that = (StructuredDataMessage)o;
         if (!super.equals(o)) {
            return false;
         } else {
            if (this.type != null) {
               if (!this.type.equals(that.type)) {
                  return false;
               }
            } else if (that.type != null) {
               return false;
            }

            if (this.id != null) {
               if (!this.id.equals(that.id)) {
                  return false;
               }
            } else if (that.id != null) {
               return false;
            }

            if (this.message != null) {
               if (!this.message.equals(that.message)) {
                  return false;
               }
            } else if (that.message != null) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int result = super.hashCode();
      result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
      result = 31 * result + (this.id != null ? this.id.hashCode() : 0);
      result = 31 * result + (this.message != null ? this.message.hashCode() : 0);
      return result;
   }

   protected void validate(final String key, final boolean value) {
      this.validateKey(key);
   }

   protected void validate(final String key, final byte value) {
      this.validateKey(key);
   }

   protected void validate(final String key, final char value) {
      this.validateKey(key);
   }

   protected void validate(final String key, final double value) {
      this.validateKey(key);
   }

   protected void validate(final String key, final float value) {
      this.validateKey(key);
   }

   protected void validate(final String key, final int value) {
      this.validateKey(key);
   }

   protected void validate(final String key, final long value) {
      this.validateKey(key);
   }

   protected void validate(final String key, final Object value) {
      this.validateKey(key);
   }

   protected void validate(final String key, final short value) {
      this.validateKey(key);
   }

   protected void validate(final String key, final String value) {
      this.validateKey(key);
   }

   protected void validateKey(final String key) {
      if (this.maxLength > 0 && key.length() > this.maxLength) {
         throw new IllegalArgumentException("Structured data keys are limited to " + this.maxLength + " characters. key: " + key);
      } else {
         for(int i = 0; i < key.length(); ++i) {
            char c = key.charAt(i);
            if (c < '!' || c > '~' || c == '=' || c == ']' || c == '"') {
               throw new IllegalArgumentException("Structured data keys must contain printable US ASCII charactersand may not contain a space, =, ], or \"");
            }
         }

      }
   }

   public static enum Format {
      XML,
      FULL;

      // $FF: synthetic method
      private static Format[] $values() {
         return new Format[]{XML, FULL};
      }
   }
}
