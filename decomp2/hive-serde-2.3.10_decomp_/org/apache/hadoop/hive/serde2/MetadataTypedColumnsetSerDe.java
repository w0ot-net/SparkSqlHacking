package org.apache.hadoop.hive.serde2;

import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.MetadataListStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SerDeSpec(
   schemaProps = {"serialization.format", "serialization.null.format", "serialization.lib", "serialization.last.column.takes.rest"}
)
public class MetadataTypedColumnsetSerDe extends AbstractSerDe {
   public static final Logger LOG = LoggerFactory.getLogger(MetadataTypedColumnsetSerDe.class.getName());
   public static final String DefaultSeparator = "\u0001";
   private String separator = "\u0001";
   public static final String defaultNullString = "\\N";
   private String nullString;
   private List columnNames;
   private ObjectInspector cachedObjectInspector;
   private boolean lastColumnTakesRest = false;
   private int splitLimit = -1;
   ColumnSet deserializeCache = new ColumnSet();
   Text serializeCache = new Text();

   public String toString() {
      return "MetaDataTypedColumnsetSerDe[" + this.separator + "," + this.columnNames + "]";
   }

   public MetadataTypedColumnsetSerDe() throws SerDeException {
   }

   private String getByteValue(String altValue, String defaultVal) {
      if (altValue != null && altValue.length() > 0) {
         try {
            byte[] b = new byte[1];
            b[0] = Byte.parseByte(altValue);
            return new String(b);
         } catch (NumberFormatException var4) {
            return altValue;
         }
      } else {
         return defaultVal;
      }
   }

   public void initialize(Configuration job, Properties tbl) throws SerDeException {
      String altSep = tbl.getProperty("serialization.format");
      this.separator = this.getByteValue(altSep, "\u0001");
      String altNull = tbl.getProperty("serialization.null.format");
      this.nullString = this.getByteValue(altNull, "\\N");
      String columnProperty = tbl.getProperty("columns");
      String serdeName = tbl.getProperty("serialization.lib");
      boolean columnsetSerDe = false;
      if (serdeName != null && serdeName.equals("org.apache.hadoop.hive.serde.thrift.columnsetSerDe")) {
         columnsetSerDe = true;
      }

      String columnNameDelimiter = tbl.containsKey("column.name.delimiter") ? tbl.getProperty("column.name.delimiter") : String.valueOf(',');
      if (columnProperty != null && columnProperty.length() != 0 && !columnsetSerDe) {
         this.columnNames = Arrays.asList(columnProperty.split(columnNameDelimiter));
         this.cachedObjectInspector = MetadataListStructObjectInspector.getInstance(this.columnNames);
      } else {
         this.cachedObjectInspector = ObjectInspectorFactory.getReflectionObjectInspector(ColumnSet.class, ObjectInspectorFactory.ObjectInspectorOptions.JAVA);
      }

      String lastColumnTakesRestString = tbl.getProperty("serialization.last.column.takes.rest");
      this.lastColumnTakesRest = lastColumnTakesRestString != null && lastColumnTakesRestString.equalsIgnoreCase("true");
      this.splitLimit = this.lastColumnTakesRest && this.columnNames != null ? this.columnNames.size() : -1;
      LOG.debug(this.getClass().getName() + ": initialized with columnNames: " + this.columnNames + " and separator code=" + this.separator.charAt(0) + " lastColumnTakesRest=" + this.lastColumnTakesRest + " splitLimit=" + this.splitLimit);
   }

   public static Object deserialize(ColumnSet c, String row, String sep, String nullString, int limit) throws Exception {
      if (c.col == null) {
         c.col = new ArrayList();
      } else {
         c.col.clear();
      }

      String[] l1 = row.split(sep, limit);

      for(String s : l1) {
         if (s.equals(nullString)) {
            c.col.add((Object)null);
         } else {
            c.col.add(s);
         }
      }

      return c;
   }

   public Object deserialize(Writable field) throws SerDeException {
      String row = null;
      if (field instanceof BytesWritable) {
         BytesWritable b = (BytesWritable)field;

         try {
            row = Text.decode(b.getBytes(), 0, b.getLength());
         } catch (CharacterCodingException e) {
            throw new SerDeException(e);
         }
      } else if (field instanceof Text) {
         row = field.toString();
      }

      try {
         deserialize(this.deserializeCache, row, this.separator, this.nullString, this.splitLimit);

         assert this.columnNames == null || this.columnNames.size() == this.deserializeCache.col.size();

         return this.deserializeCache;
      } catch (ClassCastException e) {
         throw new SerDeException(this.getClass().getName() + " expects Text or BytesWritable", e);
      } catch (Exception e) {
         throw new SerDeException(e);
      }
   }

   public ObjectInspector getObjectInspector() throws SerDeException {
      return this.cachedObjectInspector;
   }

   public Class getSerializedClass() {
      return Text.class;
   }

   public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
      if (objInspector.getCategory() != ObjectInspector.Category.STRUCT) {
         throw new SerDeException(this.getClass().toString() + " can only serialize struct types, but we got: " + objInspector.getTypeName());
      } else {
         StructObjectInspector soi = (StructObjectInspector)objInspector;
         List<? extends StructField> fields = soi.getAllStructFieldRefs();
         StringBuilder sb = new StringBuilder();

         for(int i = 0; i < fields.size(); ++i) {
            if (i > 0) {
               sb.append(this.separator);
            }

            Object column = soi.getStructFieldData(obj, (StructField)fields.get(i));
            if (((StructField)fields.get(i)).getFieldObjectInspector().getCategory() == ObjectInspector.Category.PRIMITIVE) {
               sb.append(column == null ? this.nullString : column.toString());
            } else {
               sb.append(SerDeUtils.getJSONString(column, ((StructField)fields.get(i)).getFieldObjectInspector()));
            }
         }

         this.serializeCache.set(sb.toString());
         return this.serializeCache;
      }
   }

   public SerDeStats getSerDeStats() {
      return null;
   }
}
