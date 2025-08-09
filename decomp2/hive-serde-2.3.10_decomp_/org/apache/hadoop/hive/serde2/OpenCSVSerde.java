package org.apache.hadoop.hive.serde2;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SerDeSpec(
   schemaProps = {"columns", "separatorChar", "quoteChar", "escapeChar"}
)
public final class OpenCSVSerde extends AbstractSerDe {
   public static final Logger LOG = LoggerFactory.getLogger(OpenCSVSerde.class.getName());
   private ObjectInspector inspector;
   private String[] outputFields;
   private int numCols;
   private List row;
   private char separatorChar;
   private char quoteChar;
   private char escapeChar;
   public static final String SEPARATORCHAR = "separatorChar";
   public static final String QUOTECHAR = "quoteChar";
   public static final String ESCAPECHAR = "escapeChar";

   public void initialize(Configuration conf, Properties tbl) throws SerDeException {
      List<String> columnNames = Arrays.asList(tbl.getProperty("columns").split(","));
      this.numCols = columnNames.size();
      List<ObjectInspector> columnOIs = new ArrayList(this.numCols);

      for(int i = 0; i < this.numCols; ++i) {
         columnOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
      }

      this.inspector = ObjectInspectorFactory.getStandardStructObjectInspector(columnNames, columnOIs);
      this.outputFields = new String[this.numCols];
      this.row = new ArrayList(this.numCols);

      for(int i = 0; i < this.numCols; ++i) {
         this.row.add((Object)null);
      }

      this.separatorChar = this.getProperty(tbl, "separatorChar", ',');
      this.quoteChar = this.getProperty(tbl, "quoteChar", '"');
      this.escapeChar = this.getProperty(tbl, "escapeChar", '"');
   }

   private char getProperty(Properties tbl, String property, char def) {
      String val = tbl.getProperty(property);
      return val != null ? val.charAt(0) : def;
   }

   public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
      StructObjectInspector outputRowOI = (StructObjectInspector)objInspector;
      List<? extends StructField> outputFieldRefs = outputRowOI.getAllStructFieldRefs();
      if (outputFieldRefs.size() != this.numCols) {
         throw new SerDeException("Cannot serialize the object because there are " + outputFieldRefs.size() + " fields but the table has " + this.numCols + " columns.");
      } else {
         for(int c = 0; c < this.numCols; ++c) {
            Object field = outputRowOI.getStructFieldData(obj, (StructField)outputFieldRefs.get(c));
            ObjectInspector fieldOI = ((StructField)outputFieldRefs.get(c)).getFieldObjectInspector();
            StringObjectInspector fieldStringOI = (StringObjectInspector)fieldOI;
            this.outputFields[c] = fieldStringOI.getPrimitiveJavaObject(field);
         }

         StringWriter writer = new StringWriter();
         CSVWriter csv = this.newWriter(writer, this.separatorChar, this.quoteChar, this.escapeChar);

         try {
            csv.writeNext(this.outputFields);
            csv.close();
            return new Text(writer.toString());
         } catch (IOException ioe) {
            throw new SerDeException(ioe);
         }
      }
   }

   public Object deserialize(Writable blob) throws SerDeException {
      Text rowText = (Text)blob;
      CSVReader csv = null;

      List var16;
      try {
         csv = this.newReader(new CharArrayReader(rowText.toString().toCharArray()), this.separatorChar, this.quoteChar, this.escapeChar);
         String[] read = csv.readNext();

         for(int i = 0; i < this.numCols; ++i) {
            if (read != null && i < read.length) {
               this.row.set(i, read[i]);
            } else {
               this.row.set(i, (Object)null);
            }
         }

         var16 = this.row;
      } catch (Exception e) {
         throw new SerDeException(e);
      } finally {
         if (csv != null) {
            try {
               csv.close();
            } catch (Exception e) {
               LOG.error("fail to close csv writer ", e);
            }
         }

      }

      return var16;
   }

   private CSVReader newReader(Reader reader, char separator, char quote, char escape) {
      return '"' == escape ? new CSVReader(reader, separator, quote) : new CSVReader(reader, separator, quote, escape);
   }

   private CSVWriter newWriter(Writer writer, char separator, char quote, char escape) {
      return '"' == escape ? new CSVWriter(writer, separator, quote, "") : new CSVWriter(writer, separator, quote, escape, "");
   }

   public ObjectInspector getObjectInspector() throws SerDeException {
      return this.inspector;
   }

   public Class getSerializedClass() {
      return Text.class;
   }

   public SerDeStats getSerDeStats() {
      return null;
   }
}
