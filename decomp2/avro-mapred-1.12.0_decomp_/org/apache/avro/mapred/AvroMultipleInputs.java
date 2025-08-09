package org.apache.avro.mapred;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.avro.Schema;
import org.apache.avro.SchemaParseException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvroMultipleInputs {
   private static final Logger LOG = LoggerFactory.getLogger(AvroMultipleInputs.class);
   private static final String SCHEMA_KEY = "avro.mapreduce.input.multipleinputs.dir.schemas";
   private static final String MAPPERS_KEY = "avro.mapreduce.input.multipleinputs.dir.mappers";

   private static void addInputPath(JobConf conf, Path path, Schema inputSchema) {
      String schemaMapping = path.toString() + ";" + toBase64(inputSchema.toString());
      String schemas = conf.get("avro.mapreduce.input.multipleinputs.dir.schemas");
      conf.set("avro.mapreduce.input.multipleinputs.dir.schemas", schemas == null ? schemaMapping : schemas + "," + schemaMapping);
      conf.setInputFormat(DelegatingInputFormat.class);
   }

   public static void addInputPath(JobConf conf, Path path, Class mapperClass, Schema inputSchema) {
      addInputPath(conf, path, inputSchema);
      String mapperMapping = path.toString() + ";" + mapperClass.getName();
      LOG.info(mapperMapping);
      String mappers = conf.get("avro.mapreduce.input.multipleinputs.dir.mappers");
      conf.set("avro.mapreduce.input.multipleinputs.dir.mappers", mappers == null ? mapperMapping : mappers + "," + mapperMapping);
      conf.setMapperClass(DelegatingMapper.class);
   }

   static Map getMapperTypeMap(JobConf conf) {
      if (conf.get("avro.mapreduce.input.multipleinputs.dir.mappers") == null) {
         return Collections.emptyMap();
      } else {
         Map<Path, Class<? extends AvroMapper>> m = new HashMap();
         String[] pathMappings = conf.get("avro.mapreduce.input.multipleinputs.dir.mappers").split(",");

         for(String pathMapping : pathMappings) {
            String[] split = pathMapping.split(";");

            Class<? extends AvroMapper> mapClass;
            try {
               mapClass = conf.getClassByName(split[1]);
            } catch (ClassNotFoundException e) {
               throw new RuntimeException(e);
            }

            m.put(new Path(split[0]), mapClass);
         }

         return m;
      }
   }

   static Map getInputSchemaMap(JobConf conf) {
      if (conf.get("avro.mapreduce.input.multipleinputs.dir.schemas") == null) {
         return Collections.emptyMap();
      } else {
         Map<Path, Schema> m = new HashMap();
         String[] schemaMappings = conf.get("avro.mapreduce.input.multipleinputs.dir.schemas").split(",");
         Schema.Parser schemaParser = new Schema.Parser();

         for(String schemaMapping : schemaMappings) {
            String[] split = schemaMapping.split(";");
            String schemaString = fromBase64(split[1]);

            Schema inputSchema;
            try {
               inputSchema = schemaParser.parse(schemaString);
            } catch (SchemaParseException e) {
               throw new RuntimeException(e);
            }

            m.put(new Path(split[0]), inputSchema);
         }

         return m;
      }
   }

   private static String toBase64(String rawString) {
      byte[] buf = rawString.getBytes(StandardCharsets.UTF_8);
      return new String(Base64.getMimeEncoder().encode(buf), StandardCharsets.UTF_8);
   }

   private static String fromBase64(String base64String) {
      byte[] buf = base64String.getBytes(StandardCharsets.UTF_8);
      return new String(Base64.getMimeDecoder().decode(buf), StandardCharsets.UTF_8);
   }
}
