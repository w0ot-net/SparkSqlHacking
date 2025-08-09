package org.apache.hadoop.hive.serde2.lazy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParameters;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.Text;
import org.apache.hive.common.util.HiveStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Public
@Stable
public class LazySerDeParameters implements LazyObjectInspectorParameters {
   public static final Logger LOG = LoggerFactory.getLogger(LazySerDeParameters.class.getName());
   public static final byte[] DefaultSeparators = new byte[]{1, 2, 3};
   public static final String SERIALIZATION_EXTEND_NESTING_LEVELS = "hive.serialization.extend.nesting.levels";
   public static final String SERIALIZATION_EXTEND_ADDITIONAL_NESTING_LEVELS = "hive.serialization.extend.additional.nesting.levels";
   private Properties tableProperties;
   private String serdeName;
   private byte[] separators;
   private Text nullSequence;
   private TypeInfo rowTypeInfo;
   private boolean lastColumnTakesRest;
   private List columnNames;
   private List columnTypes;
   private boolean escaped;
   private byte escapeChar;
   private boolean[] needsEscape = new boolean[256];
   private boolean extendedBooleanLiteral;
   List timestampFormats;

   public LazySerDeParameters(Configuration job, Properties tbl, String serdeName) throws SerDeException {
      this.tableProperties = tbl;
      this.serdeName = serdeName;
      String nullString = tbl.getProperty("serialization.null.format", "\\N");
      this.nullSequence = new Text(nullString);
      String lastColumnTakesRestString = tbl.getProperty("serialization.last.column.takes.rest");
      this.lastColumnTakesRest = lastColumnTakesRestString != null && lastColumnTakesRestString.equalsIgnoreCase("true");
      this.extractColumnInfo();
      this.rowTypeInfo = TypeInfoFactory.getStructTypeInfo(this.columnNames, this.columnTypes);
      this.collectSeparators(tbl);
      String escapeProperty = tbl.getProperty("escape.delim");
      this.escaped = escapeProperty != null;
      if (this.escaped) {
         this.escapeChar = LazyUtils.getByte(escapeProperty, (byte)92);
         this.needsEscape[this.escapeChar & 255] = true;

         for(byte b : this.separators) {
            this.needsEscape[b & 255] = true;
         }

         boolean isEscapeCRLF = Boolean.parseBoolean(tbl.getProperty("serialization.escape.crlf"));
         if (isEscapeCRLF) {
            this.needsEscape[13] = true;
            this.needsEscape[10] = true;
         }
      }

      this.extendedBooleanLiteral = job == null ? false : job.getBoolean(ConfVars.HIVE_LAZYSIMPLE_EXTENDED_BOOLEAN_LITERAL.varname, false);
      String[] timestampFormatsArray = HiveStringUtils.splitAndUnEscape(tbl.getProperty("timestamp.formats"));
      if (timestampFormatsArray != null) {
         this.timestampFormats = Arrays.asList(timestampFormatsArray);
      }

      LOG.debug(serdeName + " initialized with: columnNames=" + this.columnNames + " columnTypes=" + this.columnTypes + " separator=" + Arrays.asList(this.separators) + " nullstring=" + nullString + " lastColumnTakesRest=" + this.lastColumnTakesRest + " timestampFormats=" + this.timestampFormats);
   }

   public void extractColumnInfo() throws SerDeException {
      String columnNameProperty = this.tableProperties.getProperty("columns");
      String columnTypeProperty = this.tableProperties.getProperty("columns.types");
      String columnNameDelimiter = this.tableProperties.containsKey("column.name.delimiter") ? this.tableProperties.getProperty("column.name.delimiter") : String.valueOf(',');
      if (columnNameProperty != null && columnNameProperty.length() > 0) {
         this.columnNames = Arrays.asList(columnNameProperty.split(columnNameDelimiter));
      } else {
         this.columnNames = new ArrayList();
      }

      if (columnTypeProperty == null) {
         StringBuilder sb = new StringBuilder();

         for(int i = 0; i < this.columnNames.size(); ++i) {
            if (i > 0) {
               sb.append(":");
            }

            sb.append("string");
         }

         columnTypeProperty = sb.toString();
      }

      this.columnTypes = TypeInfoUtils.getTypeInfosFromTypeString(columnTypeProperty);
      if (this.columnNames.size() != this.columnTypes.size()) {
         throw new SerDeException(this.serdeName + ": columns has " + this.columnNames.size() + " elements while columns.types has " + this.columnTypes.size() + " elements!");
      }
   }

   public List getColumnTypes() {
      return this.columnTypes;
   }

   public List getColumnNames() {
      return this.columnNames;
   }

   public byte[] getSeparators() {
      return this.separators;
   }

   public Text getNullSequence() {
      return this.nullSequence;
   }

   public TypeInfo getRowTypeInfo() {
      return this.rowTypeInfo;
   }

   public boolean isLastColumnTakesRest() {
      return this.lastColumnTakesRest;
   }

   public boolean isEscaped() {
      return this.escaped;
   }

   public byte getEscapeChar() {
      return this.escapeChar;
   }

   public boolean[] getNeedsEscape() {
      return this.needsEscape;
   }

   public boolean isExtendedBooleanLiteral() {
      return this.extendedBooleanLiteral;
   }

   public List getTimestampFormats() {
      return this.timestampFormats;
   }

   public void setSeparator(int index, byte separator) throws SerDeException {
      if (index >= 0 && index < this.separators.length) {
         this.separators[index] = separator;
      } else {
         throw new SerDeException("Invalid separator array index value: " + index);
      }
   }

   private void collectSeparators(Properties tableProperties) {
      List<Byte> separatorCandidates = new ArrayList();
      String extendNestingValue = tableProperties.getProperty("hive.serialization.extend.nesting.levels");
      String extendAdditionalNestingValue = tableProperties.getProperty("hive.serialization.extend.additional.nesting.levels");
      boolean extendedNesting = extendNestingValue != null && extendNestingValue.equalsIgnoreCase("true");
      boolean extendedAdditionalNesting = extendAdditionalNestingValue != null && extendAdditionalNestingValue.equalsIgnoreCase("true");
      separatorCandidates.add(LazyUtils.getByte(tableProperties.getProperty("field.delim", tableProperties.getProperty("serialization.format")), DefaultSeparators[0]));
      separatorCandidates.add(LazyUtils.getByte(tableProperties.getProperty("colelction.delim"), DefaultSeparators[1]));
      separatorCandidates.add(LazyUtils.getByte(tableProperties.getProperty("mapkey.delim"), DefaultSeparators[2]));

      for(byte b = 4; b <= 8; ++b) {
         separatorCandidates.add(b);
      }

      separatorCandidates.add((byte)11);

      for(byte b = 14; b <= 26; ++b) {
         separatorCandidates.add(b);
      }

      for(byte b = 28; b <= 31; ++b) {
         separatorCandidates.add(b);
      }

      for(byte b = -128; b <= -1; ++b) {
         separatorCandidates.add(b);
      }

      int numSeparators = 8;
      if (extendedAdditionalNesting) {
         numSeparators = separatorCandidates.size();
      } else if (extendedNesting) {
         numSeparators = 24;
      }

      this.separators = new byte[numSeparators];

      for(int i = 0; i < numSeparators; ++i) {
         this.separators[i] = (Byte)separatorCandidates.get(i);
      }

   }
}
