package org.sparkproject.jetty.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Index;
import org.sparkproject.jetty.util.StringUtil;

public class MimeTypes {
   private static final Logger LOG = LoggerFactory.getLogger(MimeTypes.class);
   private static final Map __dftMimeMap = new HashMap();
   private static final Map __inferredEncodings = new HashMap();
   private static final Map __assumedEncodings = new HashMap();
   public static final Index CACHE = (new Index.Builder()).caseSensitive(false).withAll(() -> {
      Map<String, Type> result = new HashMap();

      for(Type type : MimeTypes.Type.values()) {
         String key1 = type.toString();
         result.put(key1, type);
         if (key1.indexOf(";charset=") > 0) {
            String key2 = StringUtil.replace(key1, ";charset=", "; charset=");
            result.put(key2, type);
         }
      }

      return result;
   }).build();
   private final Map _mimeMap = new HashMap();

   public Map getMimeMap() {
      return this._mimeMap;
   }

   public void setMimeMap(Map mimeMap) {
      this._mimeMap.clear();
      if (mimeMap != null) {
         for(Map.Entry ext : mimeMap.entrySet()) {
            this._mimeMap.put(StringUtil.asciiToLowerCase((String)ext.getKey()), normalizeMimeType((String)ext.getValue()));
         }
      }

   }

   public static String getDefaultMimeByExtension(String filename) {
      String type = null;
      if (filename != null) {
         int i = -1;

         while(type == null) {
            i = filename.indexOf(".", i + 1);
            if (i < 0 || i >= filename.length()) {
               break;
            }

            String ext = StringUtil.asciiToLowerCase(filename.substring(i + 1));
            if (type == null) {
               type = (String)__dftMimeMap.get(ext);
            }
         }
      }

      if (type == null) {
         type = (String)__dftMimeMap.get("*");
      }

      return type;
   }

   public String getMimeByExtension(String filename) {
      String type = null;
      if (filename != null) {
         int i = -1;

         while(type == null) {
            i = filename.indexOf(".", i + 1);
            if (i < 0 || i >= filename.length()) {
               break;
            }

            String ext = StringUtil.asciiToLowerCase(filename.substring(i + 1));
            if (this._mimeMap != null) {
               type = (String)this._mimeMap.get(ext);
            }

            if (type == null) {
               type = (String)__dftMimeMap.get(ext);
            }
         }
      }

      if (type == null) {
         if (this._mimeMap != null) {
            type = (String)this._mimeMap.get("*");
         }

         if (type == null) {
            type = (String)__dftMimeMap.get("*");
         }
      }

      return type;
   }

   public void addMimeMapping(String extension, String type) {
      this._mimeMap.put(StringUtil.asciiToLowerCase(extension), normalizeMimeType(type));
   }

   public static Set getKnownMimeTypes() {
      return new HashSet(__dftMimeMap.values());
   }

   private static String normalizeMimeType(String type) {
      Type t = (Type)CACHE.get(type);
      return t != null ? t.asString() : StringUtil.asciiToLowerCase(type);
   }

   public static String getCharsetFromContentType(String value) {
      if (value == null) {
         return null;
      } else {
         int end = value.length();
         int state = 0;
         int start = 0;
         boolean quote = false;

         int i;
         for(i = 0; i < end; ++i) {
            char b = value.charAt(i);
            if (quote && state != 10) {
               if ('"' == b) {
                  quote = false;
               }
            } else if (';' == b && state <= 8) {
               state = 1;
            } else {
               switch (state) {
                  case 0:
                     if ('"' == b) {
                        quote = true;
                     }
                     break;
                  case 1:
                     if ('c' == b) {
                        state = 2;
                     } else if (' ' != b) {
                        state = 0;
                     }
                     break;
                  case 2:
                     if ('h' == b) {
                        state = 3;
                     } else {
                        state = 0;
                     }
                     break;
                  case 3:
                     if ('a' == b) {
                        state = 4;
                     } else {
                        state = 0;
                     }
                     break;
                  case 4:
                     if ('r' == b) {
                        state = 5;
                     } else {
                        state = 0;
                     }
                     break;
                  case 5:
                     if ('s' == b) {
                        state = 6;
                     } else {
                        state = 0;
                     }
                     break;
                  case 6:
                     if ('e' == b) {
                        state = 7;
                     } else {
                        state = 0;
                     }
                     break;
                  case 7:
                     if ('t' == b) {
                        state = 8;
                     } else {
                        state = 0;
                     }
                     break;
                  case 8:
                     if ('=' == b) {
                        state = 9;
                     } else if (' ' != b) {
                        state = 0;
                     }
                     break;
                  case 9:
                     if (' ' != b) {
                        if ('"' == b) {
                           quote = true;
                           start = i + 1;
                           state = 10;
                        } else {
                           start = i;
                           state = 10;
                        }
                     }
                     break;
                  case 10:
                     if (!quote && (';' == b || ' ' == b) || quote && '"' == b) {
                        return StringUtil.normalizeCharset(value, start, i - start);
                     }
                     break;
                  default:
                     throw new IllegalStateException();
               }
            }
         }

         if (state == 10) {
            return StringUtil.normalizeCharset(value, start, i - start);
         } else {
            return null;
         }
      }
   }

   public static Map getInferredEncodings() {
      return __inferredEncodings;
   }

   public static Map getAssumedEncodings() {
      return __assumedEncodings;
   }

   public static String getCharsetInferredFromContentType(String contentType) {
      return (String)__inferredEncodings.get(contentType);
   }

   public static String getCharsetAssumedFromContentType(String contentType) {
      return (String)__assumedEncodings.get(contentType);
   }

   public static String getContentTypeWithoutCharset(String value) {
      int end = value.length();
      int state = 0;
      int start = 0;
      boolean quote = false;
      int i = 0;

      StringBuilder builder;
      for(builder = null; i < end; ++i) {
         char b = value.charAt(i);
         if ('"' == b) {
            if (quote) {
               quote = false;
            } else {
               quote = true;
            }

            switch (state) {
               case 9:
                  builder = new StringBuilder();
                  builder.append(value, 0, start + 1);
                  state = 10;
               case 10:
                  break;
               case 11:
                  builder.append(b);
                  break;
               default:
                  start = i;
                  state = 0;
            }
         } else if (quote) {
            if (builder != null && state != 10) {
               builder.append(b);
            }
         } else {
            switch (state) {
               case 0:
                  if (';' == b) {
                     state = 1;
                  } else if (' ' != b) {
                     start = i;
                  }
                  break;
               case 1:
                  if ('c' == b) {
                     state = 2;
                  } else if (' ' != b) {
                     state = 0;
                  }
                  break;
               case 2:
                  if ('h' == b) {
                     state = 3;
                  } else {
                     state = 0;
                  }
                  break;
               case 3:
                  if ('a' == b) {
                     state = 4;
                  } else {
                     state = 0;
                  }
                  break;
               case 4:
                  if ('r' == b) {
                     state = 5;
                  } else {
                     state = 0;
                  }
                  break;
               case 5:
                  if ('s' == b) {
                     state = 6;
                  } else {
                     state = 0;
                  }
                  break;
               case 6:
                  if ('e' == b) {
                     state = 7;
                  } else {
                     state = 0;
                  }
                  break;
               case 7:
                  if ('t' == b) {
                     state = 8;
                  } else {
                     state = 0;
                  }
                  break;
               case 8:
                  if ('=' == b) {
                     state = 9;
                  } else if (' ' != b) {
                     state = 0;
                  }
                  break;
               case 9:
                  if (' ' != b) {
                     builder = new StringBuilder();
                     builder.append(value, 0, start + 1);
                     state = 10;
                  }
                  break;
               case 10:
                  if (';' == b) {
                     builder.append(b);
                     state = 11;
                  }
                  break;
               case 11:
                  if (' ' != b) {
                     builder.append(b);
                  }
                  break;
               default:
                  throw new IllegalStateException();
            }
         }
      }

      if (builder == null) {
         return value;
      } else {
         return builder.toString();
      }
   }

   static {
      for(Type type : MimeTypes.Type.values()) {
         if (type.isCharsetAssumed()) {
            __assumedEncodings.put(type.asString(), type.getCharsetString());
         }
      }

      String resourceName = "mime.properties";

      try {
         InputStream stream = MimeTypes.class.getResourceAsStream(resourceName);

         try {
            if (stream == null) {
               LOG.warn("Missing mime-type resource: {}", resourceName);
            } else {
               try {
                  InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);

                  try {
                     Properties props = new Properties();
                     props.load(reader);
                     props.stringPropertyNames().stream().filter((x) -> x != null).forEach((x) -> __dftMimeMap.put(StringUtil.asciiToLowerCase(x), normalizeMimeType(props.getProperty(x))));
                     if (__dftMimeMap.isEmpty()) {
                        LOG.warn("Empty mime types at {}", resourceName);
                     } else if (__dftMimeMap.size() < props.keySet().size()) {
                        LOG.warn("Duplicate or null mime-type extension in resource: {}", resourceName);
                     }
                  } catch (Throwable var10) {
                     try {
                        reader.close();
                     } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                     }

                     throw var10;
                  }

                  reader.close();
               } catch (IOException e) {
                  if (LOG.isDebugEnabled()) {
                     LOG.warn("Unable to read mime-type resource: {}", resourceName, e);
                  } else {
                     LOG.warn("Unable to read mime-type resource: {} - {}", resourceName, e.toString());
                  }
               }
            }
         } catch (Throwable var15) {
            if (stream != null) {
               try {
                  stream.close();
               } catch (Throwable var8) {
                  var15.addSuppressed(var8);
               }
            }

            throw var15;
         }

         if (stream != null) {
            stream.close();
         }
      } catch (IOException e) {
         if (LOG.isDebugEnabled()) {
            LOG.warn("Unable to load mime-type resource: {}", resourceName, e);
         } else {
            LOG.warn("Unable to load mime-type resource: {} - {}", resourceName, e.toString());
         }
      }

      resourceName = "encoding.properties";

      try {
         InputStream stream = MimeTypes.class.getResourceAsStream(resourceName);

         try {
            if (stream == null) {
               LOG.warn("Missing encoding resource: {}", resourceName);
            } else {
               try {
                  InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);

                  try {
                     Properties props = new Properties();
                     props.load(reader);
                     props.stringPropertyNames().stream().filter((t) -> t != null).forEach((t) -> {
                        String charset = props.getProperty(t);
                        if (charset.startsWith("-")) {
                           __assumedEncodings.put(t, charset.substring(1));
                        } else {
                           __inferredEncodings.put(t, props.getProperty(t));
                        }

                     });
                     if (__inferredEncodings.isEmpty()) {
                        LOG.warn("Empty encodings at {}", resourceName);
                     } else if (__inferredEncodings.size() + __assumedEncodings.size() < props.keySet().size()) {
                        LOG.warn("Null or duplicate encodings in resource: {}", resourceName);
                     }
                  } catch (Throwable var7) {
                     try {
                        reader.close();
                     } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                     }

                     throw var7;
                  }

                  reader.close();
               } catch (IOException e) {
                  if (LOG.isDebugEnabled()) {
                     LOG.warn("Unable to read encoding resource: {}", resourceName, e);
                  } else {
                     LOG.warn("Unable to read encoding resource: {} - {}", resourceName, e.toString());
                  }
               }
            }
         } catch (Throwable var12) {
            if (stream != null) {
               try {
                  stream.close();
               } catch (Throwable var5) {
                  var12.addSuppressed(var5);
               }
            }

            throw var12;
         }

         if (stream != null) {
            stream.close();
         }
      } catch (IOException e) {
         if (LOG.isDebugEnabled()) {
            LOG.warn("Unable to load encoding resource: {}", resourceName, e);
         } else {
            LOG.warn("Unable to load encoding resource: {} - {}", resourceName, e.toString());
         }
      }

   }

   public static enum Type {
      FORM_ENCODED("application/x-www-form-urlencoded"),
      MESSAGE_HTTP("message/http"),
      MULTIPART_BYTERANGES("multipart/byteranges"),
      MULTIPART_FORM_DATA("multipart/form-data"),
      TEXT_HTML("text/html"),
      TEXT_PLAIN("text/plain"),
      TEXT_XML("text/xml"),
      TEXT_JSON("text/json", StandardCharsets.UTF_8),
      APPLICATION_JSON("application/json", StandardCharsets.UTF_8),
      TEXT_HTML_8859_1("text/html;charset=iso-8859-1", TEXT_HTML),
      TEXT_HTML_UTF_8("text/html;charset=utf-8", TEXT_HTML),
      TEXT_PLAIN_8859_1("text/plain;charset=iso-8859-1", TEXT_PLAIN),
      TEXT_PLAIN_UTF_8("text/plain;charset=utf-8", TEXT_PLAIN),
      TEXT_XML_8859_1("text/xml;charset=iso-8859-1", TEXT_XML),
      TEXT_XML_UTF_8("text/xml;charset=utf-8", TEXT_XML),
      TEXT_JSON_8859_1("text/json;charset=iso-8859-1", TEXT_JSON),
      TEXT_JSON_UTF_8("text/json;charset=utf-8", TEXT_JSON),
      APPLICATION_JSON_8859_1("application/json;charset=iso-8859-1", APPLICATION_JSON),
      APPLICATION_JSON_UTF_8("application/json;charset=utf-8", APPLICATION_JSON);

      private final String _string;
      private final Type _base;
      private final ByteBuffer _buffer;
      private final Charset _charset;
      private final String _charsetString;
      private final boolean _assumedCharset;
      private final HttpField _field;

      private Type(String s) {
         this._string = s;
         this._buffer = BufferUtil.toBuffer(s);
         this._base = this;
         this._charset = null;
         this._charsetString = null;
         this._assumedCharset = false;
         this._field = new PreEncodedHttpField(HttpHeader.CONTENT_TYPE, this._string);
      }

      private Type(String s, Type base) {
         this._string = s;
         this._buffer = BufferUtil.toBuffer(s);
         this._base = base;
         int i = s.indexOf(";charset=");
         this._charset = Charset.forName(s.substring(i + 9));
         this._charsetString = this._charset.toString().toLowerCase(Locale.ENGLISH);
         this._assumedCharset = false;
         this._field = new PreEncodedHttpField(HttpHeader.CONTENT_TYPE, this._string);
      }

      private Type(String s, Charset cs) {
         this._string = s;
         this._base = this;
         this._buffer = BufferUtil.toBuffer(s);
         this._charset = cs;
         this._charsetString = this._charset == null ? null : this._charset.toString().toLowerCase(Locale.ENGLISH);
         this._assumedCharset = true;
         this._field = new PreEncodedHttpField(HttpHeader.CONTENT_TYPE, this._string);
      }

      public ByteBuffer asBuffer() {
         return this._buffer.asReadOnlyBuffer();
      }

      public Charset getCharset() {
         return this._charset;
      }

      public String getCharsetString() {
         return this._charsetString;
      }

      public boolean is(String s) {
         return this._string.equalsIgnoreCase(s);
      }

      public String asString() {
         return this._string;
      }

      public String toString() {
         return this._string;
      }

      public boolean isCharsetAssumed() {
         return this._assumedCharset;
      }

      public HttpField getContentTypeField() {
         return this._field;
      }

      public Type getBaseType() {
         return this._base;
      }

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{FORM_ENCODED, MESSAGE_HTTP, MULTIPART_BYTERANGES, MULTIPART_FORM_DATA, TEXT_HTML, TEXT_PLAIN, TEXT_XML, TEXT_JSON, APPLICATION_JSON, TEXT_HTML_8859_1, TEXT_HTML_UTF_8, TEXT_PLAIN_8859_1, TEXT_PLAIN_UTF_8, TEXT_XML_8859_1, TEXT_XML_UTF_8, TEXT_JSON_8859_1, TEXT_JSON_UTF_8, APPLICATION_JSON_8859_1, APPLICATION_JSON_UTF_8};
      }
   }
}
