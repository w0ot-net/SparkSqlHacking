package org.apache.avro.mapred;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.avro.Schema;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DelegatingInputFormat implements InputFormat {
   private static final Logger LOG = LoggerFactory.getLogger(DelegatingInputFormat.class);

   public InputSplit[] getSplits(JobConf conf, int numSplits) throws IOException {
      JobConf confCopy = new JobConf(conf);
      List<InputSplit> splits = new ArrayList();
      Map<Path, Class<? extends AvroMapper>> mapperMap = AvroMultipleInputs.getMapperTypeMap(conf);
      Map<Path, Schema> schemaMap = AvroMultipleInputs.getInputSchemaMap(conf);
      Map<Schema, List<Path>> schemaPaths = new HashMap();

      for(Map.Entry entry : schemaMap.entrySet()) {
         if (!schemaPaths.containsKey(entry.getValue())) {
            schemaPaths.put((Schema)entry.getValue(), new ArrayList());
            LOG.info(((Schema)entry.getValue()).toString());
            LOG.info(String.valueOf(entry.getKey()));
         }

         ((List)schemaPaths.get(entry.getValue())).add((Path)entry.getKey());
      }

      for(Map.Entry schemaEntry : schemaPaths.entrySet()) {
         Schema schema = (Schema)schemaEntry.getKey();
         LOG.info(schema.toString());
         InputFormat format = (InputFormat)ReflectionUtils.newInstance(AvroInputFormat.class, conf);
         List<Path> paths = (List)schemaEntry.getValue();
         Map<Class<? extends AvroMapper>, List<Path>> mapperPaths = new HashMap();

         for(Path path : paths) {
            Class<? extends AvroMapper> mapperClass = (Class)mapperMap.get(path);
            if (!mapperPaths.containsKey(mapperClass)) {
               mapperPaths.put(mapperClass, new ArrayList());
            }

            ((List)mapperPaths.get(mapperClass)).add(path);
         }

         for(Map.Entry mapEntry : mapperPaths.entrySet()) {
            paths = (List)mapEntry.getValue();
            Class<? extends AvroMapper> mapperClass = (Class)mapEntry.getKey();
            if (mapperClass == null) {
               mapperClass = conf.getMapperClass();
            }

            FileInputFormat.setInputPaths(confCopy, (Path[])paths.toArray(new Path[0]));
            InputSplit[] pathSplits = format.getSplits(confCopy, numSplits);

            for(InputSplit pathSplit : pathSplits) {
               splits.add(new TaggedInputSplit(pathSplit, conf, format.getClass(), mapperClass, schema));
            }
         }
      }

      return (InputSplit[])splits.toArray(new InputSplit[0]);
   }

   public RecordReader getRecordReader(InputSplit split, JobConf conf, Reporter reporter) throws IOException {
      TaggedInputSplit taggedInputSplit = (TaggedInputSplit)split;
      Schema schema = taggedInputSplit.getSchema();
      AvroJob.setInputSchema(conf, schema);
      InputFormat<K, V> inputFormat = (InputFormat)ReflectionUtils.newInstance(taggedInputSplit.getInputFormatClass(), conf);
      return inputFormat.getRecordReader(taggedInputSplit.getInputSplit(), conf, reporter);
   }
}
