package org.apache.hadoop.hive.serde2;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.UnionObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BinaryObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ByteObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DateObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.FloatObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveCharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveDecimalObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveIntervalDayTimeObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveIntervalYearMonthObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveVarcharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.IntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ShortObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.TimestampObjectInspector;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SerDeUtils {
   public static final char QUOTE = '"';
   public static final char COLON = ':';
   public static final char COMMA = ',';
   public static final char COLUMN_COMMENTS_DELIMITER = '\u0000';
   public static final String LBRACKET = "[";
   public static final String RBRACKET = "]";
   public static final String LBRACE = "{";
   public static final String RBRACE = "}";
   private static final String JSON_NULL = "null";
   public static final String LIST_SINK_OUTPUT_FORMATTER = "list.sink.output.formatter";
   public static final String LIST_SINK_OUTPUT_PROTOCOL = "list.sink.output.protocol";
   public static final Logger LOG = LoggerFactory.getLogger(SerDeUtils.class.getName());

   public static String escapeString(String str) {
      int length = str.length();
      StringBuilder escape = new StringBuilder(length + 16);

      for(int i = 0; i < length; ++i) {
         char c = str.charAt(i);
         String hex;
         int j;
         switch (c) {
            case '\b':
               escape.append('\\');
               escape.append('b');
               continue;
            case '\t':
               escape.append('\\');
               escape.append('t');
               continue;
            case '\n':
               escape.append('\\');
               escape.append('n');
               continue;
            case '\f':
               escape.append('\\');
               escape.append('f');
               continue;
            case '\r':
               escape.append('\\');
               escape.append('r');
               continue;
            case '"':
            case '\\':
               escape.append('\\');
               escape.append(c);
               continue;
            default:
               if (c >= ' ') {
                  escape.append(c);
                  continue;
               }

               hex = Integer.toHexString(c);
               escape.append('\\');
               escape.append('u');
               j = 4;
         }

         while(j > hex.length()) {
            escape.append('0');
            --j;
         }

         escape.append(hex);
      }

      return escape.toString();
   }

   public static String lightEscapeString(String str) {
      int length = str.length();
      StringBuilder escape = new StringBuilder(length + 16);

      for(int i = 0; i < length; ++i) {
         char c = str.charAt(i);
         switch (c) {
            case '\t':
               escape.append('\\');
               escape.append('t');
               break;
            case '\n':
               escape.append('\\');
               escape.append('n');
               break;
            case '\u000b':
            case '\f':
            default:
               escape.append(c);
               break;
            case '\r':
               escape.append('\\');
               escape.append('r');
         }
      }

      return escape.toString();
   }

   public static Object toThriftPayload(Object val, ObjectInspector valOI, int version) {
      if (valOI.getCategory() == ObjectInspector.Category.PRIMITIVE) {
         if (val == null) {
            return null;
         } else {
            Object obj = ObjectInspectorUtils.copyToStandardObject(val, valOI, ObjectInspectorUtils.ObjectInspectorCopyOption.JAVA);
            return version < 5 && ((PrimitiveObjectInspector)valOI).getPrimitiveCategory() == PrimitiveObjectInspector.PrimitiveCategory.BINARY ? new String((byte[])obj) : obj;
         }
      } else {
         return getJSONString(val, valOI);
      }
   }

   public static String getJSONString(Object o, ObjectInspector oi) {
      return getJSONString(o, oi, "null");
   }

   public static String getJSONString(Object o, ObjectInspector oi, String nullStr) {
      StringBuilder sb = new StringBuilder();
      buildJSONString(sb, o, oi, nullStr);
      return sb.toString();
   }

   static void buildJSONString(StringBuilder sb, Object o, ObjectInspector oi, String nullStr) {
      switch (oi.getCategory()) {
         case PRIMITIVE:
            PrimitiveObjectInspector poi = (PrimitiveObjectInspector)oi;
            if (o == null) {
               sb.append(nullStr);
               break;
            } else {
               switch (poi.getPrimitiveCategory()) {
                  case BOOLEAN:
                     boolean b = ((BooleanObjectInspector)poi).get(o);
                     sb.append(b ? "true" : "false");
                     return;
                  case BYTE:
                     sb.append(((ByteObjectInspector)poi).get(o));
                     return;
                  case SHORT:
                     sb.append(((ShortObjectInspector)poi).get(o));
                     return;
                  case INT:
                     sb.append(((IntObjectInspector)poi).get(o));
                     return;
                  case LONG:
                     sb.append(((LongObjectInspector)poi).get(o));
                     return;
                  case FLOAT:
                     sb.append(((FloatObjectInspector)poi).get(o));
                     return;
                  case DOUBLE:
                     sb.append(((DoubleObjectInspector)poi).get(o));
                     return;
                  case STRING:
                     sb.append('"');
                     sb.append(escapeString(((StringObjectInspector)poi).getPrimitiveJavaObject(o)));
                     sb.append('"');
                     return;
                  case CHAR:
                     sb.append('"');
                     sb.append(escapeString(((HiveCharObjectInspector)poi).getPrimitiveJavaObject(o).toString()));
                     sb.append('"');
                     return;
                  case VARCHAR:
                     sb.append('"');
                     sb.append(escapeString(((HiveVarcharObjectInspector)poi).getPrimitiveJavaObject(o).toString()));
                     sb.append('"');
                     return;
                  case DATE:
                     sb.append('"');
                     sb.append(((DateObjectInspector)poi).getPrimitiveWritableObject(o));
                     sb.append('"');
                     return;
                  case TIMESTAMP:
                     sb.append('"');
                     sb.append(((TimestampObjectInspector)poi).getPrimitiveWritableObject(o));
                     sb.append('"');
                     return;
                  case BINARY:
                     BytesWritable bw = ((BinaryObjectInspector)oi).getPrimitiveWritableObject(o);
                     Text txt = new Text();
                     txt.set(bw.getBytes(), 0, bw.getLength());
                     sb.append(txt.toString());
                     return;
                  case DECIMAL:
                     sb.append(((HiveDecimalObjectInspector)oi).getPrimitiveJavaObject(o));
                     return;
                  case INTERVAL_YEAR_MONTH:
                     sb.append(((HiveIntervalYearMonthObjectInspector)oi).getPrimitiveJavaObject(o));
                     return;
                  case INTERVAL_DAY_TIME:
                     sb.append(((HiveIntervalDayTimeObjectInspector)oi).getPrimitiveJavaObject(o));
                     return;
                  default:
                     throw new RuntimeException("Unknown primitive type: " + poi.getPrimitiveCategory());
               }
            }
         case LIST:
            ListObjectInspector loi = (ListObjectInspector)oi;
            ObjectInspector listElementObjectInspector = loi.getListElementObjectInspector();
            List<?> olist = loi.getList(o);
            if (olist == null) {
               sb.append(nullStr);
            } else {
               sb.append("[");

               for(int i = 0; i < olist.size(); ++i) {
                  if (i > 0) {
                     sb.append(',');
                  }

                  buildJSONString(sb, olist.get(i), listElementObjectInspector, "null");
               }

               sb.append("]");
            }
            break;
         case MAP:
            MapObjectInspector moi = (MapObjectInspector)oi;
            ObjectInspector mapKeyObjectInspector = moi.getMapKeyObjectInspector();
            ObjectInspector mapValueObjectInspector = moi.getMapValueObjectInspector();
            Map<?, ?> omap = moi.getMap(o);
            if (omap == null) {
               sb.append(nullStr);
            } else {
               sb.append("{");
               boolean first = true;

               for(Object entry : omap.entrySet()) {
                  if (first) {
                     first = false;
                  } else {
                     sb.append(',');
                  }

                  Map.Entry<?, ?> e = (Map.Entry)entry;
                  buildJSONString(sb, e.getKey(), mapKeyObjectInspector, "null");
                  sb.append(':');
                  buildJSONString(sb, e.getValue(), mapValueObjectInspector, "null");
               }

               sb.append("}");
            }
            break;
         case STRUCT:
            StructObjectInspector soi = (StructObjectInspector)oi;
            List<? extends StructField> structFields = soi.getAllStructFieldRefs();
            if (o == null) {
               sb.append(nullStr);
            } else {
               sb.append("{");

               for(int i = 0; i < structFields.size(); ++i) {
                  if (i > 0) {
                     sb.append(',');
                  }

                  sb.append('"');
                  sb.append(((StructField)structFields.get(i)).getFieldName());
                  sb.append('"');
                  sb.append(':');
                  buildJSONString(sb, soi.getStructFieldData(o, (StructField)structFields.get(i)), ((StructField)structFields.get(i)).getFieldObjectInspector(), "null");
               }

               sb.append("}");
            }
            break;
         case UNION:
            UnionObjectInspector uoi = (UnionObjectInspector)oi;
            if (o == null) {
               sb.append(nullStr);
            } else {
               sb.append("{");
               sb.append(uoi.getTag(o));
               sb.append(':');
               buildJSONString(sb, uoi.getField(o), (ObjectInspector)uoi.getObjectInspectors().get(uoi.getTag(o)), "null");
               sb.append("}");
            }
            break;
         default:
            throw new RuntimeException("Unknown type in ObjectInspector!");
      }

   }

   public static boolean hasAnyNullObject(List o, StructObjectInspector loi, boolean[] nullSafes) {
      List<? extends StructField> fields = loi.getAllStructFieldRefs();

      for(int i = 0; i < o.size(); ++i) {
         if ((nullSafes == null || !nullSafes[i]) && hasAnyNullObject(o.get(i), ((StructField)fields.get(i)).getFieldObjectInspector())) {
            return true;
         }
      }

      return false;
   }

   public static boolean hasAnyNullObject(Object o, ObjectInspector oi) {
      switch (oi.getCategory()) {
         case PRIMITIVE:
            if (o == null) {
               return true;
            }

            return false;
         case LIST:
            ListObjectInspector loi = (ListObjectInspector)oi;
            ObjectInspector listElementObjectInspector = loi.getListElementObjectInspector();
            List<?> olist = loi.getList(o);
            if (olist == null) {
               return true;
            } else if (olist.size() == 0) {
               return false;
            } else {
               for(int i = 0; i < olist.size(); ++i) {
                  if (hasAnyNullObject(olist.get(i), listElementObjectInspector)) {
                     return true;
                  }
               }

               return false;
            }
         case MAP:
            MapObjectInspector moi = (MapObjectInspector)oi;
            ObjectInspector mapKeyObjectInspector = moi.getMapKeyObjectInspector();
            ObjectInspector mapValueObjectInspector = moi.getMapValueObjectInspector();
            Map<?, ?> omap = moi.getMap(o);
            if (omap == null) {
               return true;
            } else if (omap.entrySet().size() == 0) {
               return false;
            } else {
               for(Map.Entry entry : omap.entrySet()) {
                  if (hasAnyNullObject(entry.getKey(), mapKeyObjectInspector) || hasAnyNullObject(entry.getValue(), mapValueObjectInspector)) {
                     return true;
                  }
               }

               return false;
            }
         case STRUCT:
            StructObjectInspector soi = (StructObjectInspector)oi;
            List<? extends StructField> structFields = soi.getAllStructFieldRefs();
            if (o == null) {
               return true;
            } else if (structFields.size() == 0) {
               return false;
            } else {
               for(int i = 0; i < structFields.size(); ++i) {
                  if (hasAnyNullObject(soi.getStructFieldData(o, (StructField)structFields.get(i)), ((StructField)structFields.get(i)).getFieldObjectInspector())) {
                     return true;
                  }
               }

               return false;
            }
         case UNION:
            UnionObjectInspector uoi = (UnionObjectInspector)oi;
            if (o == null) {
               return true;
            } else {
               if (uoi.getObjectInspectors().size() == 0) {
                  return false;
               }

               return hasAnyNullObject(uoi.getField(o), (ObjectInspector)uoi.getObjectInspectors().get(uoi.getTag(o)));
            }
         default:
            throw new RuntimeException("Unknown type in ObjectInspector!");
      }
   }

   public static Properties createOverlayedProperties(Properties tblProps, Properties partProps) {
      Properties props = new Properties();
      props.putAll(tblProps);
      if (partProps != null) {
         props.putAll(partProps);
      }

      return props;
   }

   public static void initializeSerDe(Deserializer deserializer, Configuration conf, Properties tblProps, Properties partProps) throws SerDeException {
      if (deserializer instanceof AbstractSerDe) {
         ((AbstractSerDe)deserializer).initialize(conf, tblProps, partProps);
         String msg = ((AbstractSerDe)deserializer).getConfigurationErrors();
         if (msg != null && !msg.isEmpty()) {
            throw new SerDeException(msg);
         }
      } else {
         deserializer.initialize(conf, createOverlayedProperties(tblProps, partProps));
      }

   }

   public static void initializeSerDeWithoutErrorCheck(Deserializer deserializer, Configuration conf, Properties tblProps, Properties partProps) throws SerDeException {
      if (deserializer instanceof AbstractSerDe) {
         ((AbstractSerDe)deserializer).initialize(conf, tblProps, partProps);
      } else {
         deserializer.initialize(conf, createOverlayedProperties(tblProps, partProps));
      }

   }

   private SerDeUtils() {
   }

   public static Text transformTextToUTF8(Text text, Charset previousCharset) {
      return new Text(new String(text.getBytes(), 0, text.getLength(), previousCharset));
   }

   public static Text transformTextFromUTF8(Text text, Charset targetCharset) {
      return new Text((new String(text.getBytes(), 0, text.getLength())).getBytes(targetCharset));
   }

   public static void writeLong(byte[] writeBuffer, int offset, long value) {
      writeBuffer[offset] = (byte)((int)(value >> 0 & 255L));
      writeBuffer[offset + 1] = (byte)((int)(value >> 8 & 255L));
      writeBuffer[offset + 2] = (byte)((int)(value >> 16 & 255L));
      writeBuffer[offset + 3] = (byte)((int)(value >> 24 & 255L));
      writeBuffer[offset + 4] = (byte)((int)(value >> 32 & 255L));
      writeBuffer[offset + 5] = (byte)((int)(value >> 40 & 255L));
      writeBuffer[offset + 6] = (byte)((int)(value >> 48 & 255L));
      writeBuffer[offset + 7] = (byte)((int)(value >> 56 & 255L));
   }
}
