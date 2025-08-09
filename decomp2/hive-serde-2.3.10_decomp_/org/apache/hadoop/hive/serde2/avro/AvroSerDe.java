package org.apache.hadoop.hive.serde2.avro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.avro.Schema;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.StringInternUtils;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeSpec;
import org.apache.hadoop.hive.serde2.SerDeStats;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.Writable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SerDeSpec(
   schemaProps = {"columns", "columns.types", "columns.comments", "name", "comment", "avro.schema.literal", "avro.schema.url", "avro.schema.namespace", "avro.schema.name", "avro.schema.doc"}
)
public class AvroSerDe extends AbstractSerDe {
   private static final Logger LOG = LoggerFactory.getLogger(AvroSerDe.class);
   public static final String TABLE_NAME = "name";
   public static final String TABLE_COMMENT = "comment";
   public static final String LIST_COLUMN_COMMENTS = "columns.comments";
   public static final String DECIMAL_TYPE_NAME = "decimal";
   public static final String CHAR_TYPE_NAME = "char";
   public static final String VARCHAR_TYPE_NAME = "varchar";
   public static final String DATE_TYPE_NAME = "date";
   public static final String TIMESTAMP_TYPE_NAME = "timestamp-millis";
   public static final String AVRO_PROP_LOGICAL_TYPE = "logicalType";
   public static final String AVRO_PROP_PRECISION = "precision";
   public static final String AVRO_PROP_SCALE = "scale";
   public static final String AVRO_PROP_MAX_LENGTH = "maxLength";
   public static final String AVRO_STRING_TYPE_NAME = "string";
   public static final String AVRO_INT_TYPE_NAME = "int";
   public static final String AVRO_LONG_TYPE_NAME = "long";
   private ObjectInspector oi;
   private List columnNames;
   private List columnTypes;
   private Schema schema;
   private AvroDeserializer avroDeserializer = null;
   private AvroSerializer avroSerializer = null;
   private boolean badSchema = false;

   public void initialize(Configuration configuration, Properties tableProperties, Properties partitionProperties) throws SerDeException {
      this.initialize(configuration, tableProperties);
   }

   public void initialize(Configuration configuration, Properties properties) throws SerDeException {
      if (this.schema != null) {
         LOG.debug("Resetting already initialized AvroSerDe");
      }

      this.schema = null;
      this.oi = null;
      this.columnNames = null;
      this.columnTypes = null;
      String columnNameProperty = properties.getProperty("columns");
      String columnTypeProperty = properties.getProperty("columns.types");
      String columnCommentProperty = properties.getProperty("columns.comments", "");
      String columnNameDelimiter = properties.containsKey("column.name.delimiter") ? properties.getProperty("column.name.delimiter") : String.valueOf(',');
      if (!this.hasExternalSchema(properties) && columnNameProperty != null && !columnNameProperty.isEmpty() && columnTypeProperty != null && !columnTypeProperty.isEmpty()) {
         this.columnNames = StringInternUtils.internStringsInList(Arrays.asList(columnNameProperty.split(columnNameDelimiter)));
         this.columnTypes = TypeInfoUtils.getTypeInfosFromTypeString(columnTypeProperty);
         this.schema = getSchemaFromCols(properties, this.columnNames, this.columnTypes, columnCommentProperty);
         properties.setProperty(AvroSerdeUtils.AvroTableProperties.SCHEMA_LITERAL.getPropName(), this.schema.toString());
      } else {
         this.schema = this.determineSchemaOrReturnErrorSchema(configuration, properties);
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Avro schema is " + this.schema);
      }

      if (configuration == null) {
         LOG.debug("Configuration null, not inserting schema");
      } else {
         configuration.set(AvroSerdeUtils.AvroTableProperties.AVRO_SERDE_SCHEMA.getPropName(), this.schema.toString(false));
      }

      this.badSchema = this.schema.equals(SchemaResolutionProblem.SIGNAL_BAD_SCHEMA);
      AvroObjectInspectorGenerator aoig = new AvroObjectInspectorGenerator(this.schema);
      this.columnNames = StringInternUtils.internStringsInList(aoig.getColumnNames());
      this.columnTypes = aoig.getColumnTypes();
      this.oi = aoig.getObjectInspector();
   }

   private boolean hasExternalSchema(Properties properties) {
      return properties.getProperty(AvroSerdeUtils.AvroTableProperties.SCHEMA_LITERAL.getPropName()) != null || properties.getProperty(AvroSerdeUtils.AvroTableProperties.SCHEMA_URL.getPropName()) != null;
   }

   private boolean hasExternalSchema(Map tableParams) {
      return tableParams.containsKey(AvroSerdeUtils.AvroTableProperties.SCHEMA_LITERAL.getPropName()) || tableParams.containsKey(AvroSerdeUtils.AvroTableProperties.SCHEMA_URL.getPropName());
   }

   public static Schema getSchemaFromCols(Properties properties, List columnNames, List columnTypes, String columnCommentProperty) {
      List<String> columnComments;
      if (columnCommentProperty != null && !columnCommentProperty.isEmpty()) {
         columnComments = Arrays.asList(columnCommentProperty.split("\u0000"));
         if (LOG.isDebugEnabled()) {
            LOG.debug("columnComments is " + columnCommentProperty);
         }
      } else {
         columnComments = new ArrayList();
      }

      if (columnNames.size() != columnTypes.size()) {
         throw new IllegalArgumentException("AvroSerde initialization failed. Number of column name and column type differs. columnNames = " + columnNames + ", columnTypes = " + columnTypes);
      } else {
         String tableName = properties.getProperty("name");
         String tableComment = properties.getProperty("comment");
         TypeInfoToSchema typeInfoToSchema = new TypeInfoToSchema();
         return typeInfoToSchema.convert(columnNames, columnTypes, columnComments, properties.getProperty(AvroSerdeUtils.AvroTableProperties.SCHEMA_NAMESPACE.getPropName()), properties.getProperty(AvroSerdeUtils.AvroTableProperties.SCHEMA_NAME.getPropName(), tableName), properties.getProperty(AvroSerdeUtils.AvroTableProperties.SCHEMA_DOC.getPropName(), tableComment));
      }
   }

   public Schema determineSchemaOrReturnErrorSchema(Configuration conf, Properties props) {
      try {
         this.configErrors = "";
         return AvroSerdeUtils.determineSchemaOrThrowException(conf, props);
      } catch (AvroSerdeException he) {
         LOG.warn("Encountered AvroSerdeException determining schema. Returning signal schema to indicate problem", he);
         this.configErrors = new String("Encountered AvroSerdeException determining schema. Returning signal schema to indicate problem: " + he.getMessage());
         return this.schema = SchemaResolutionProblem.SIGNAL_BAD_SCHEMA;
      } catch (Exception e) {
         LOG.warn("Encountered exception determining schema. Returning signal schema to indicate problem", e);
         this.configErrors = new String("Encountered exception determining schema. Returning signal schema to indicate problem: " + e.getMessage());
         return SchemaResolutionProblem.SIGNAL_BAD_SCHEMA;
      }
   }

   public Class getSerializedClass() {
      return AvroGenericRecordWritable.class;
   }

   public Writable serialize(Object o, ObjectInspector objectInspector) throws SerDeException {
      if (this.badSchema) {
         throw new BadSchemaException();
      } else {
         return this.getSerializer().serialize(o, objectInspector, this.columnNames, this.columnTypes, this.schema);
      }
   }

   public Object deserialize(Writable writable) throws SerDeException {
      if (this.badSchema) {
         throw new BadSchemaException();
      } else {
         return this.getDeserializer().deserialize(this.columnNames, this.columnTypes, writable, this.schema);
      }
   }

   public ObjectInspector getObjectInspector() throws SerDeException {
      return this.oi;
   }

   public SerDeStats getSerDeStats() {
      return null;
   }

   private AvroDeserializer getDeserializer() {
      if (this.avroDeserializer == null) {
         this.avroDeserializer = new AvroDeserializer();
      }

      return this.avroDeserializer;
   }

   private AvroSerializer getSerializer() {
      if (this.avroSerializer == null) {
         this.avroSerializer = new AvroSerializer();
      }

      return this.avroSerializer;
   }

   public boolean shouldStoreFieldsInMetastore(Map tableParams) {
      return !this.hasExternalSchema(tableParams);
   }
}
