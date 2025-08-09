package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Currency;
import java.util.IllformedLocaleException;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.core.util.VersionUtil;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.CoercionAction;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import shaded.parquet.com.fasterxml.jackson.databind.exc.InvalidFormatException;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;

public abstract class FromStringDeserializer extends StdScalarDeserializer {
   public static Class[] types() {
      return new Class[]{File.class, URL.class, URI.class, Class.class, JavaType.class, Currency.class, Pattern.class, Locale.class, Charset.class, TimeZone.class, InetAddress.class, InetSocketAddress.class, StringBuilder.class, StringBuffer.class};
   }

   protected FromStringDeserializer(Class vc) {
      super(vc);
   }

   public static FromStringDeserializer findDeserializer(Class rawType) {
      int kind = 0;
      if (rawType == File.class) {
         kind = 1;
      } else if (rawType == URL.class) {
         kind = 2;
      } else if (rawType == URI.class) {
         kind = 3;
      } else if (rawType == Class.class) {
         kind = 4;
      } else if (rawType == JavaType.class) {
         kind = 5;
      } else if (rawType == Currency.class) {
         kind = 6;
      } else if (rawType == Pattern.class) {
         kind = 7;
      } else if (rawType == Locale.class) {
         kind = 8;
      } else if (rawType == Charset.class) {
         kind = 9;
      } else if (rawType == TimeZone.class) {
         kind = 10;
      } else if (rawType == InetAddress.class) {
         kind = 11;
      } else {
         if (rawType != InetSocketAddress.class) {
            if (rawType == StringBuilder.class) {
               return new StringBuilderDeserializer();
            }

            if (rawType == StringBuffer.class) {
               return new StringBufferDeserializer();
            }

            return null;
         }

         kind = 12;
      }

      return new Std(rawType, kind);
   }

   public LogicalType logicalType() {
      return LogicalType.OtherScalar;
   }

   public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      String text = p.getValueAsString();
      if (text == null) {
         JsonToken t = p.currentToken();
         if (t != JsonToken.START_OBJECT) {
            return this._deserializeFromOther(p, ctxt, t);
         }

         text = ctxt.extractScalarFromObject(p, this, this._valueClass);
      }

      if (text.isEmpty()) {
         return this._deserializeFromEmptyString(ctxt);
      } else {
         if (this._shouldTrim()) {
            String old = text;
            text = text.trim();
            if (text != old && text.isEmpty()) {
               return this._deserializeFromEmptyString(ctxt);
            }
         }

         Exception cause = null;

         try {
            return this._deserialize(text, ctxt);
         } catch (MalformedURLException | IllegalArgumentException e) {
            String msg = "not a valid textual representation";
            String m2 = ((Exception)e).getMessage();
            if (m2 != null) {
               msg = msg + ", problem: " + m2;
            }

            throw ctxt.weirdStringException(text, this._valueClass, msg).withCause(e);
         }
      }
   }

   protected abstract Object _deserialize(String var1, DeserializationContext var2) throws IOException;

   protected boolean _shouldTrim() {
      return true;
   }

   protected Object _deserializeFromOther(JsonParser p, DeserializationContext ctxt, JsonToken t) throws IOException {
      if (t == JsonToken.START_ARRAY) {
         return this._deserializeFromArray(p, ctxt);
      } else if (t == JsonToken.VALUE_EMBEDDED_OBJECT) {
         Object ob = p.getEmbeddedObject();
         if (ob == null) {
            return null;
         } else {
            return this._valueClass.isAssignableFrom(ob.getClass()) ? ob : this._deserializeEmbedded(ob, ctxt);
         }
      } else {
         return ctxt.handleUnexpectedToken(this._valueClass, p);
      }
   }

   protected Object _deserializeEmbedded(Object ob, DeserializationContext ctxt) throws IOException {
      ctxt.reportInputMismatch((JsonDeserializer)this, "Don't know how to convert embedded Object of type %s into %s", ob.getClass().getName(), this._valueClass.getName());
      return null;
   }

   /** @deprecated */
   @Deprecated
   protected final Object _deserializeFromEmptyString() throws IOException {
      return null;
   }

   protected Object _deserializeFromEmptyString(DeserializationContext ctxt) throws IOException {
      CoercionAction act = ctxt.findCoercionAction(this.logicalType(), this._valueClass, CoercionInputShape.EmptyString);
      if (act == CoercionAction.Fail) {
         ctxt.reportInputMismatch((JsonDeserializer)this, "Cannot coerce empty String (\"\") to %s (but could if enabling coercion using `CoercionConfig`)", this._coercedTypeDesc());
      }

      if (act == CoercionAction.AsNull) {
         return this.getNullValue(ctxt);
      } else {
         return act == CoercionAction.AsEmpty ? this.getEmptyValue(ctxt) : this._deserializeFromEmptyStringDefault(ctxt);
      }
   }

   protected Object _deserializeFromEmptyStringDefault(DeserializationContext ctxt) throws IOException {
      return this.getNullValue(ctxt);
   }

   public static class Std extends FromStringDeserializer {
      private static final long serialVersionUID = 1L;
      public static final int STD_FILE = 1;
      public static final int STD_URL = 2;
      public static final int STD_URI = 3;
      public static final int STD_CLASS = 4;
      public static final int STD_JAVA_TYPE = 5;
      public static final int STD_CURRENCY = 6;
      public static final int STD_PATTERN = 7;
      public static final int STD_LOCALE = 8;
      public static final int STD_CHARSET = 9;
      public static final int STD_TIME_ZONE = 10;
      public static final int STD_INET_ADDRESS = 11;
      public static final int STD_INET_SOCKET_ADDRESS = 12;
      protected static final String LOCALE_EXT_MARKER = "_#";
      protected final int _kind;

      protected Std(Class valueType, int kind) {
         super(valueType);
         this._kind = kind;
      }

      protected Object _deserialize(String value, DeserializationContext ctxt) throws IOException {
         switch (this._kind) {
            case 1:
               return new File(value);
            case 2:
               return new URL(value);
            case 3:
               return URI.create(value);
            case 4:
               try {
                  return ctxt.findClass(value);
               } catch (Exception e) {
                  return ctxt.handleInstantiationProblem(this._valueClass, value, ClassUtil.getRootCause(e));
               }
            case 5:
               return ctxt.getTypeFactory().constructFromCanonical(value);
            case 6:
               try {
                  return Currency.getInstance(value);
               } catch (IllegalArgumentException var7) {
                  return ctxt.handleWeirdStringValue(this._valueClass, value, "Unrecognized currency");
               }
            case 7:
               try {
                  return Pattern.compile(value);
               } catch (PatternSyntaxException e) {
                  return ctxt.handleWeirdStringValue(this._valueClass, value, "Invalid Pattern, problem: " + e.getDescription());
               }
            case 8:
               return this._deserializeLocale(value, ctxt);
            case 9:
               return Charset.forName(value);
            case 10:
               return TimeZone.getTimeZone(value);
            case 11:
               return InetAddress.getByName(value);
            case 12:
               if (value.startsWith("[")) {
                  int i = value.lastIndexOf(93);
                  if (i == -1) {
                     throw new InvalidFormatException(ctxt.getParser(), "Bracketed IPv6 address must contain closing bracket", value, InetSocketAddress.class);
                  }

                  int j = value.indexOf(58, i);
                  int port = j > -1 ? Integer.parseInt(value.substring(j + 1)) : 0;
                  return new InetSocketAddress(value.substring(0, i + 1), port);
               } else {
                  int ix = value.indexOf(58);
                  if (ix >= 0 && value.indexOf(58, ix + 1) < 0) {
                     int port = Integer.parseInt(value.substring(ix + 1));
                     return new InetSocketAddress(value.substring(0, ix), port);
                  }

                  return new InetSocketAddress(value, 0);
               }
            default:
               VersionUtil.throwInternal();
               return null;
         }
      }

      public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
         switch (this._kind) {
            case 3:
               return URI.create("");
            case 8:
               return Locale.ROOT;
            default:
               return super.getEmptyValue(ctxt);
         }
      }

      protected Object _deserializeFromEmptyStringDefault(DeserializationContext ctxt) throws IOException {
         return this.getEmptyValue(ctxt);
      }

      protected boolean _shouldTrim() {
         return this._kind != 7;
      }

      protected int _firstHyphenOrUnderscore(String str) {
         int i = 0;

         for(int end = str.length(); i < end; ++i) {
            char c = str.charAt(i);
            if (c == '_' || c == '-') {
               return i;
            }
         }

         return -1;
      }

      private Locale _deserializeLocale(String value, DeserializationContext ctxt) throws IOException {
         int ix = this._firstHyphenOrUnderscore(value);
         if (ix < 0) {
            return new Locale(value);
         } else {
            String first = value.substring(0, ix);
            value = value.substring(ix + 1);
            ix = this._firstHyphenOrUnderscore(value);
            if (ix < 0) {
               return new Locale(first, value);
            } else {
               String second = value.substring(0, ix);
               int extMarkerIx = value.indexOf("_#");
               return extMarkerIx < 0 ? new Locale(first, second, value.substring(ix + 1)) : this._deSerializeBCP47Locale(value, ix, first, second, extMarkerIx);
            }
         }
      }

      private Locale _deSerializeBCP47Locale(String value, int ix, String first, String second, int extMarkerIx) {
         String third = "";

         try {
            if (extMarkerIx > 0 && extMarkerIx > ix) {
               third = value.substring(ix + 1, extMarkerIx);
            }

            value = value.substring(extMarkerIx + 2);
            int underscoreIx = value.indexOf(95);
            if (underscoreIx < 0) {
               int hyphenIx = value.indexOf(45);
               return hyphenIx < 0 ? (new Locale.Builder()).setLanguage(first).setRegion(second).setVariant(third).setScript(value).build() : (new Locale.Builder()).setLanguage(first).setRegion(second).setVariant(third).setExtension(value.charAt(0), value.substring(hyphenIx + 1)).build();
            } else {
               int len = value.length();
               Locale.Builder b = (new Locale.Builder()).setLanguage(first).setRegion(second).setVariant(third).setScript(value.substring(0, underscoreIx));
               if (underscoreIx + 1 < len) {
                  b = b.setExtension(value.charAt(underscoreIx + 1), value.substring(Math.min(len, underscoreIx + 3)));
               }

               return b.build();
            }
         } catch (IllformedLocaleException var10) {
            return new Locale(first, second, third);
         }
      }
   }

   static class StringBuilderDeserializer extends FromStringDeserializer {
      public StringBuilderDeserializer() {
         super(StringBuilder.class);
      }

      public LogicalType logicalType() {
         return LogicalType.Textual;
      }

      public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
         return new StringBuilder();
      }

      public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         String text = p.getValueAsString();
         return text != null ? this._deserialize(text, ctxt) : super.deserialize(p, ctxt);
      }

      protected Object _deserialize(String value, DeserializationContext ctxt) throws IOException {
         return new StringBuilder(value);
      }
   }

   static class StringBufferDeserializer extends FromStringDeserializer {
      public StringBufferDeserializer() {
         super(StringBuffer.class);
      }

      public LogicalType logicalType() {
         return LogicalType.Textual;
      }

      public Object getEmptyValue(DeserializationContext ctxt) {
         return new StringBuffer();
      }

      public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         String text = p.getValueAsString();
         return text != null ? this._deserialize(text, ctxt) : super.deserialize(p, ctxt);
      }

      protected Object _deserialize(String value, DeserializationContext ctxt) throws IOException {
         return new StringBuffer(value);
      }
   }
}
