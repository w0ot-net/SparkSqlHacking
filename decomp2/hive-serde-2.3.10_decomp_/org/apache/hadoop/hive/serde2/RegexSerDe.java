package org.apache.hadoop.hive.serde2;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.AbstractPrimitiveJavaObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.CharTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.hive.serde2.typeinfo.VarcharTypeInfo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SerDeSpec(
   schemaProps = {"columns", "columns.types", "input.regex", "input.regex.case.insensitive"}
)
public class RegexSerDe extends AbstractSerDe {
   public static final Logger LOG = LoggerFactory.getLogger(RegexSerDe.class.getName());
   public static final String INPUT_REGEX = "input.regex";
   public static final String INPUT_REGEX_CASE_SENSITIVE = "input.regex.case.insensitive";
   int numColumns;
   String inputRegex;
   Pattern inputPattern;
   StructObjectInspector rowOI;
   List row;
   List columnTypes;
   Object[] outputFields;
   Text outputRowText;
   boolean alreadyLoggedNoMatch = false;
   boolean alreadyLoggedPartialMatch = false;
   long unmatchedRowsCount = 0L;
   long partialMatchedRowsCount = 0L;

   public void initialize(Configuration conf, Properties tbl) throws SerDeException {
      this.inputRegex = tbl.getProperty("input.regex");
      String columnNameProperty = tbl.getProperty("columns");
      String columnTypeProperty = tbl.getProperty("columns.types");
      boolean inputRegexIgnoreCase = "true".equalsIgnoreCase(tbl.getProperty("input.regex.case.insensitive"));
      if (null != tbl.getProperty("output.format.string")) {
         LOG.warn("output.format.string has been deprecated");
      }

      if (this.inputRegex == null) {
         this.inputPattern = null;
         throw new SerDeException("This table does not have serde property \"input.regex\"!");
      } else {
         this.inputPattern = Pattern.compile(this.inputRegex, 32 + (inputRegexIgnoreCase ? 2 : 0));
         String columnNameDelimiter = tbl.containsKey("column.name.delimiter") ? tbl.getProperty("column.name.delimiter") : String.valueOf(',');
         List<String> columnNames = Arrays.asList(columnNameProperty.split(columnNameDelimiter));
         this.columnTypes = TypeInfoUtils.getTypeInfosFromTypeString(columnTypeProperty);

         assert columnNames.size() == this.columnTypes.size();

         this.numColumns = columnNames.size();
         List<ObjectInspector> columnOIs = new ArrayList(columnNames.size());

         for(int c = 0; c < this.numColumns; ++c) {
            TypeInfo typeInfo = (TypeInfo)this.columnTypes.get(c);
            if (!(typeInfo instanceof PrimitiveTypeInfo)) {
               throw new SerDeException(this.getClass().getName() + " doesn't allow column [" + c + "] named " + (String)columnNames.get(c) + " with type " + this.columnTypes.get(c));
            }

            PrimitiveTypeInfo pti = (PrimitiveTypeInfo)this.columnTypes.get(c);
            AbstractPrimitiveJavaObjectInspector oi = PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector(pti);
            columnOIs.add(oi);
         }

         this.rowOI = ObjectInspectorFactory.getStandardStructObjectInspector(columnNames, columnOIs, Lists.newArrayList(Splitter.on('\u0000').split(tbl.getProperty("columns.comments"))));
         this.row = new ArrayList(this.numColumns);

         for(int c = 0; c < this.numColumns; ++c) {
            this.row.add((Object)null);
         }

         this.outputFields = new Object[this.numColumns];
         this.outputRowText = new Text();
      }
   }

   public ObjectInspector getObjectInspector() throws SerDeException {
      return this.rowOI;
   }

   public Class getSerializedClass() {
      return Text.class;
   }

   public Object deserialize(Writable blob) throws SerDeException {
      Text rowText = (Text)blob;
      Matcher m = this.inputPattern.matcher(rowText.toString());
      if (m.groupCount() != this.numColumns) {
         throw new SerDeException("Number of matching groups doesn't match the number of columns");
      } else if (!m.matches()) {
         ++this.unmatchedRowsCount;
         if (!this.alreadyLoggedNoMatch) {
            LOG.warn("" + this.unmatchedRowsCount + " unmatched rows are found: " + rowText);
            this.alreadyLoggedNoMatch = true;
         }

         return null;
      } else {
         for(int c = 0; c < this.numColumns; ++c) {
            try {
               String t = m.group(c + 1);
               TypeInfo typeInfo = (TypeInfo)this.columnTypes.get(c);
               PrimitiveTypeInfo pti = (PrimitiveTypeInfo)typeInfo;
               switch (pti.getPrimitiveCategory()) {
                  case STRING:
                     this.row.set(c, t);
                     break;
                  case BYTE:
                     Byte b = Byte.valueOf(t);
                     this.row.set(c, b);
                     break;
                  case SHORT:
                     Short s = Short.valueOf(t);
                     this.row.set(c, s);
                     break;
                  case INT:
                     Integer i = Integer.valueOf(t);
                     this.row.set(c, i);
                     break;
                  case LONG:
                     Long l = Long.valueOf(t);
                     this.row.set(c, l);
                     break;
                  case FLOAT:
                     Float f = Float.valueOf(t);
                     this.row.set(c, f);
                     break;
                  case DOUBLE:
                     Double d = Double.valueOf(t);
                     this.row.set(c, d);
                     break;
                  case BOOLEAN:
                     Boolean bool = Boolean.valueOf(t);
                     this.row.set(c, bool);
                     break;
                  case TIMESTAMP:
                     Timestamp ts = Timestamp.valueOf(t);
                     this.row.set(c, ts);
                     break;
                  case DATE:
                     Date date = Date.valueOf(t);
                     this.row.set(c, date);
                     break;
                  case DECIMAL:
                     HiveDecimal bd = HiveDecimal.create(t);
                     this.row.set(c, bd);
                     break;
                  case CHAR:
                     HiveChar hc = new HiveChar(t, ((CharTypeInfo)typeInfo).getLength());
                     this.row.set(c, hc);
                     break;
                  case VARCHAR:
                     HiveVarchar hv = new HiveVarchar(t, ((VarcharTypeInfo)typeInfo).getLength());
                     this.row.set(c, hv);
                     break;
                  default:
                     throw new SerDeException("Unsupported type " + typeInfo);
               }
            } catch (RuntimeException var20) {
               ++this.partialMatchedRowsCount;
               if (!this.alreadyLoggedPartialMatch) {
                  LOG.warn("" + this.partialMatchedRowsCount + " partially unmatched rows are found,  cannot find group " + c + ": " + rowText);
                  this.alreadyLoggedPartialMatch = true;
               }

               this.row.set(c, (Object)null);
            }
         }

         return this.row;
      }
   }

   public Writable serialize(Object obj, ObjectInspector objInspector) throws SerDeException {
      throw new UnsupportedOperationException("Regex SerDe doesn't support the serialize() method");
   }

   public SerDeStats getSerDeStats() {
      return null;
   }
}
