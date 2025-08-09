package org.apache.hadoop.hive.serde2.avro;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.mapred.JobConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvroSerdeUtils {
   private static final Logger LOG = LoggerFactory.getLogger(AvroSerdeUtils.class);
   /** @deprecated */
   @Deprecated
   public static final String SCHEMA_LITERAL = "avro.schema.literal";
   /** @deprecated */
   @Deprecated
   public static final String SCHEMA_URL = "avro.schema.url";
   /** @deprecated */
   @Deprecated
   public static final String SCHEMA_NAMESPACE = "avro.schema.namespace";
   /** @deprecated */
   @Deprecated
   public static final String SCHEMA_NAME = "avro.schema.name";
   /** @deprecated */
   @Deprecated
   public static final String SCHEMA_DOC = "avro.schema.doc";
   /** @deprecated */
   @Deprecated
   public static final String AVRO_SERDE_SCHEMA;
   /** @deprecated */
   @Deprecated
   public static final String SCHEMA_RETRIEVER;
   public static final String SCHEMA_NONE = "none";
   public static final String EXCEPTION_MESSAGE;

   public static Schema determineSchemaOrThrowException(Configuration conf, Properties properties) throws IOException, AvroSerdeException {
      String schemaString = properties.getProperty(AvroSerdeUtils.AvroTableProperties.SCHEMA_LITERAL.getPropName());
      if (schemaString != null && !schemaString.equals("none")) {
         return getSchemaFor(schemaString);
      } else {
         schemaString = properties.getProperty(AvroSerdeUtils.AvroTableProperties.SCHEMA_URL.getPropName());
         if (schemaString == null) {
            String columnNameProperty = properties.getProperty("columns");
            String columnTypeProperty = properties.getProperty("columns.types");
            String columnCommentProperty = properties.getProperty("columns.comments");
            if (columnNameProperty != null && !columnNameProperty.isEmpty() && columnTypeProperty != null && !columnTypeProperty.isEmpty()) {
               String columnNameDelimiter = properties.containsKey("column.name.delimiter") ? properties.getProperty("column.name.delimiter") : String.valueOf(',');
               List<String> columnNames = Arrays.asList(columnNameProperty.split(columnNameDelimiter));
               List<TypeInfo> columnTypes = TypeInfoUtils.getTypeInfosFromTypeString(columnTypeProperty);
               Schema schema = AvroSerDe.getSchemaFromCols(properties, columnNames, columnTypes, columnCommentProperty);
               properties.setProperty(AvroSerdeUtils.AvroTableProperties.SCHEMA_LITERAL.getPropName(), schema.toString());
               if (conf != null) {
                  conf.set(AvroSerdeUtils.AvroTableProperties.AVRO_SERDE_SCHEMA.getPropName(), schema.toString(false));
               }

               return schema;
            } else {
               throw new AvroSerdeException(EXCEPTION_MESSAGE);
            }
         } else if (schemaString.equals("none")) {
            throw new AvroSerdeException(EXCEPTION_MESSAGE);
         } else {
            try {
               Schema s = getSchemaFromFS(schemaString, conf);
               return s == null ? getSchemaFor(new URL(schemaString)) : s;
            } catch (IOException ioe) {
               throw new AvroSerdeException("Unable to read schema from given path: " + schemaString, ioe);
            } catch (URISyntaxException urie) {
               throw new AvroSerdeException("Unable to read schema from given path: " + schemaString, urie);
            }
         }
      }
   }

   protected static Schema getSchemaFromFS(String schemaFSUrl, Configuration conf) throws IOException, URISyntaxException {
      FSDataInputStream in = null;
      FileSystem fs = null;

      try {
         fs = FileSystem.get(new URI(schemaFSUrl), conf);
      } catch (IOException ioe) {
         if (LOG.isDebugEnabled()) {
            String msg = "Failed to open file system for uri " + schemaFSUrl + " assuming it is not a FileSystem url";
            LOG.debug(msg, ioe);
         }

         return null;
      }

      Schema var12;
      try {
         in = fs.open(new Path(schemaFSUrl));
         Schema s = getSchemaFor((InputStream)in);
         var12 = s;
      } finally {
         if (in != null) {
            in.close();
         }

      }

      return var12;
   }

   public static boolean isNullableType(Schema schema) {
      if (!schema.getType().equals(Type.UNION)) {
         return false;
      } else {
         List<Schema> itemSchemas = schema.getTypes();
         if (itemSchemas.size() < 2) {
            return false;
         } else {
            for(Schema itemSchema : itemSchemas) {
               if (Type.NULL.equals(itemSchema.getType())) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   public static Schema getOtherTypeFromNullableType(Schema schema) {
      List<Schema> itemSchemas = new ArrayList();

      for(Schema itemSchema : schema.getTypes()) {
         if (!Type.NULL.equals(itemSchema.getType())) {
            itemSchemas.add(itemSchema);
         }
      }

      if (itemSchemas.size() > 1) {
         return Schema.createUnion(itemSchemas);
      } else {
         return (Schema)itemSchemas.get(0);
      }
   }

   public static boolean insideMRJob(JobConf job) {
      return job != null && HiveConf.getVar(job, ConfVars.PLAN) != null && !HiveConf.getVar(job, ConfVars.PLAN).isEmpty();
   }

   public static Buffer getBufferFromBytes(byte[] input) {
      ByteBuffer bb = ByteBuffer.wrap(input);
      return bb.rewind();
   }

   public static Buffer getBufferFromDecimal(HiveDecimal dec, int scale) {
      return dec == null ? null : getBufferFromBytes(dec.bigIntegerBytesScaled(scale));
   }

   public static byte[] getBytesFromByteBuffer(ByteBuffer byteBuffer) {
      byteBuffer.rewind();
      byte[] result = new byte[byteBuffer.limit()];
      byteBuffer.get(result);
      return result;
   }

   public static HiveDecimal getHiveDecimalFromByteBuffer(ByteBuffer byteBuffer, int scale) {
      byte[] result = getBytesFromByteBuffer(byteBuffer);
      HiveDecimal dec = HiveDecimal.create(new BigInteger(result), scale);
      return dec;
   }

   private static Schema.Parser getSchemaParser() {
      return (new Schema.Parser()).setValidateDefaults(false);
   }

   public static Schema getSchemaFor(String str) {
      Schema schema = getSchemaParser().parse(str);
      return schema;
   }

   public static Schema getSchemaFor(File file) {
      try {
         Schema schema = getSchemaParser().parse(file);
         return schema;
      } catch (IOException e) {
         throw new RuntimeException("Failed to parse Avro schema from " + file.getName(), e);
      }
   }

   public static Schema getSchemaFor(InputStream stream) {
      try {
         Schema schema = getSchemaParser().parse(stream);
         return schema;
      } catch (IOException e) {
         throw new RuntimeException("Failed to parse Avro schema", e);
      }
   }

   public static Schema getSchemaFor(URL url) {
      InputStream in = null;

      Schema var2;
      try {
         in = url.openStream();
         var2 = getSchemaFor(in);
      } catch (Exception e) {
         throw new RuntimeException("Failed to parse Avro schema", e);
      } finally {
         if (in != null) {
            try {
               in.close();
            } catch (IOException var10) {
            }
         }

      }

      return var2;
   }

   public static int getIntFromSchema(Schema schema, String name) {
      Object obj = schema.getObjectProp(name);
      if (obj instanceof String) {
         return Integer.parseInt((String)obj);
      } else if (obj instanceof Integer) {
         return (Integer)obj;
      } else {
         throw new IllegalArgumentException("Expect integer or string value from property " + name + " but found type " + obj.getClass().getName());
      }
   }

   public static void handleAlterTableForAvro(HiveConf conf, String serializationLib, Map parameters) {
      if (AvroSerDe.class.getName().equals(serializationLib)) {
         String literalPropName = AvroSerdeUtils.AvroTableProperties.SCHEMA_LITERAL.getPropName();
         String urlPropName = AvroSerdeUtils.AvroTableProperties.SCHEMA_URL.getPropName();
         if (parameters.containsKey(literalPropName) || parameters.containsKey(urlPropName)) {
            throw new RuntimeException("Not allowed to alter schema of Avro stored table having external schema. Consider removing " + AvroSerdeUtils.AvroTableProperties.SCHEMA_LITERAL.getPropName() + " or " + AvroSerdeUtils.AvroTableProperties.SCHEMA_URL.getPropName() + " from table properties.");
         }
      }

   }

   static {
      AVRO_SERDE_SCHEMA = AvroSerdeUtils.AvroTableProperties.AVRO_SERDE_SCHEMA.getPropName();
      SCHEMA_RETRIEVER = AvroSerdeUtils.AvroTableProperties.SCHEMA_RETRIEVER.getPropName();
      EXCEPTION_MESSAGE = "Neither " + AvroSerdeUtils.AvroTableProperties.SCHEMA_LITERAL.getPropName() + " nor " + AvroSerdeUtils.AvroTableProperties.SCHEMA_URL.getPropName() + " specified, can't determine table schema";
   }

   public static enum AvroTableProperties {
      SCHEMA_LITERAL("avro.schema.literal"),
      SCHEMA_URL("avro.schema.url"),
      SCHEMA_NAMESPACE("avro.schema.namespace"),
      SCHEMA_NAME("avro.schema.name"),
      SCHEMA_DOC("avro.schema.doc"),
      AVRO_SERDE_SCHEMA("avro.serde.schema"),
      SCHEMA_RETRIEVER("avro.schema.retriever");

      private final String propName;

      private AvroTableProperties(String propName) {
         this.propName = propName;
      }

      public String getPropName() {
         return this.propName;
      }
   }
}
