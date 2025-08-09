package org.apache.avro.hadoop.file;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.hadoop.io.AvroKeyValue;
import org.apache.avro.hadoop.util.AvroCharSequenceComparator;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.mapred.FsInput;
import org.apache.avro.specific.SpecificData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortedKeyValueFile {
   private static final Logger LOG = LoggerFactory.getLogger(SortedKeyValueFile.class);
   public static final String DATA_FILENAME = "data";
   public static final String INDEX_FILENAME = "index";

   public static class Reader implements Closeable, Iterable {
      private final NavigableMap mIndex;
      private final DataFileReader mDataFileReader;
      private final Schema mKeySchema;
      private GenericData model;

      public Reader(Options options) throws IOException {
         this.mKeySchema = options.getKeySchema();
         this.model = options.getDataModel();
         Path indexFilePath = new Path(options.getPath(), "index");
         SortedKeyValueFile.LOG.debug("Loading the index from {}", indexFilePath);
         this.mIndex = this.loadIndexFile(options.getConfiguration(), indexFilePath, this.mKeySchema);
         Path dataFilePath = new Path(options.getPath(), "data");
         SortedKeyValueFile.LOG.debug("Loading the data file {}", dataFilePath);
         Schema recordSchema = AvroKeyValue.getSchema(this.mKeySchema, options.getValueSchema());
         DatumReader<GenericRecord> datumReader = this.model.createDatumReader(recordSchema);
         this.mDataFileReader = new DataFileReader(new FsInput(dataFilePath, options.getConfiguration()), datumReader);
      }

      public Object get(Object key) throws IOException {
         SortedKeyValueFile.LOG.debug("Looking up key {} in the index", key);
         Map.Entry<K, Long> indexEntry = this.mIndex.floorEntry(key);
         if (null == indexEntry) {
            SortedKeyValueFile.LOG.debug("Key {} was not found in the index (it is before the first entry)", key);
            return null;
         } else {
            SortedKeyValueFile.LOG.debug("Key was found in the index, seeking to syncpoint {}", indexEntry.getValue());
            this.mDataFileReader.seek((Long)indexEntry.getValue());

            for(AvroKeyValue record : this) {
               int comparison = this.model.compare(record.getKey(), key, this.mKeySchema);
               if (0 == comparison) {
                  SortedKeyValueFile.LOG.debug("Found record for key {}", key);
                  return record.getValue();
               }

               if (comparison > 0) {
                  SortedKeyValueFile.LOG.debug("Searched beyond the point where key {} would appear in the file", key);
                  return null;
               }
            }

            SortedKeyValueFile.LOG.debug("Searched to the end of the file but did not find key {}", key);
            return null;
         }
      }

      public Iterator iterator() {
         return new AvroKeyValue.Iterator(this.mDataFileReader.iterator());
      }

      public void close() throws IOException {
         this.mDataFileReader.close();
      }

      private NavigableMap loadIndexFile(Configuration conf, Path path, Schema keySchema) throws IOException {
         DatumReader<GenericRecord> datumReader = this.model.createDatumReader(AvroKeyValue.getSchema(keySchema, Schema.create(Type.LONG)));
         NavigableMap<K, Long> index = new TreeMap();
         DataFileReader<GenericRecord> fileReader = new DataFileReader(new FsInput(path, conf), datumReader);

         try {
            if (Schema.create(Type.STRING).equals(keySchema)) {
               index = new TreeMap(new AvroCharSequenceComparator());
            }

            for(GenericRecord genericRecord : fileReader) {
               AvroKeyValue<K, Long> indexRecord = new AvroKeyValue(genericRecord);
               index.put(indexRecord.getKey(), (Long)indexRecord.getValue());
            }
         } catch (Throwable var11) {
            try {
               fileReader.close();
            } catch (Throwable var10) {
               var11.addSuppressed(var10);
            }

            throw var11;
         }

         fileReader.close();
         return index;
      }

      public static class Options {
         private Configuration mConf;
         private Path mPath;
         private Schema mKeySchema;
         private Schema mValueSchema;
         private GenericData model = SpecificData.get();

         public Options withConfiguration(Configuration conf) {
            this.mConf = conf;
            return this;
         }

         public Configuration getConfiguration() {
            return this.mConf;
         }

         public Options withPath(Path path) {
            this.mPath = path;
            return this;
         }

         public Path getPath() {
            return this.mPath;
         }

         public Options withKeySchema(Schema keySchema) {
            this.mKeySchema = keySchema;
            return this;
         }

         public Schema getKeySchema() {
            return this.mKeySchema;
         }

         public Options withValueSchema(Schema valueSchema) {
            this.mValueSchema = valueSchema;
            return this;
         }

         public Schema getValueSchema() {
            return this.mValueSchema;
         }

         public Options withDataModel(GenericData model) {
            this.model = model;
            return this;
         }

         public GenericData getDataModel() {
            return this.model;
         }
      }
   }

   public static class Writer implements Closeable {
      private final Schema mKeySchema;
      private final Schema mValueSchema;
      private final Schema mRecordSchema;
      private final Schema mIndexSchema;
      private GenericData model;
      private final DataFileWriter mDataFileWriter;
      private final DataFileWriter mIndexFileWriter;
      private final int mIndexInterval;
      private long mRecordsWritten;
      private Object mPreviousKey;

      public Writer(Options options) throws IOException {
         this.model = options.getDataModel();
         if (null == options.getConfiguration()) {
            throw new IllegalArgumentException("Configuration may not be null");
         } else {
            FileSystem fileSystem = options.getPath().getFileSystem(options.getConfiguration());
            this.mKeySchema = options.getKeySchema();
            if (null == this.mKeySchema) {
               throw new IllegalArgumentException("Key schema may not be null");
            } else {
               this.mValueSchema = options.getValueSchema();
               if (null == this.mValueSchema) {
                  throw new IllegalArgumentException("Value schema may not be null");
               } else {
                  this.mIndexInterval = options.getIndexInterval();
                  if (!fileSystem.mkdirs(options.getPath())) {
                     throw new IOException("Unable to create directory for SortedKeyValueFile: " + options.getPath());
                  } else {
                     SortedKeyValueFile.LOG.debug("Created directory {}", options.getPath());
                     Path dataFilePath = new Path(options.getPath(), "data");
                     SortedKeyValueFile.LOG.debug("Creating writer for avro data file: {}", dataFilePath);
                     this.mRecordSchema = AvroKeyValue.getSchema(this.mKeySchema, this.mValueSchema);
                     DatumWriter<GenericRecord> datumWriter = this.model.createDatumWriter(this.mRecordSchema);
                     OutputStream dataOutputStream = fileSystem.create(dataFilePath);
                     this.mDataFileWriter = (new DataFileWriter(datumWriter)).setSyncInterval(1048576).setCodec(options.getCodec()).create(this.mRecordSchema, dataOutputStream);
                     Path indexFilePath = new Path(options.getPath(), "index");
                     SortedKeyValueFile.LOG.debug("Creating writer for avro index file: {}", indexFilePath);
                     this.mIndexSchema = AvroKeyValue.getSchema(this.mKeySchema, Schema.create(Type.LONG));
                     DatumWriter<GenericRecord> indexWriter = this.model.createDatumWriter(this.mIndexSchema);
                     OutputStream indexOutputStream = fileSystem.create(indexFilePath);
                     this.mIndexFileWriter = (new DataFileWriter(indexWriter)).create(this.mIndexSchema, indexOutputStream);
                  }
               }
            }
         }
      }

      public void append(Object key, Object value) throws IOException {
         if (null != this.mPreviousKey && this.model.compare(key, this.mPreviousKey, this.mKeySchema) < 0) {
            throw new IllegalArgumentException("Records must be inserted in sorted key order. Attempted to insert key " + key + " after " + this.mPreviousKey + ".");
         } else {
            this.mPreviousKey = this.model.deepCopy(this.mKeySchema, key);
            AvroKeyValue<K, V> dataRecord = new AvroKeyValue(new GenericData.Record(this.mRecordSchema));
            dataRecord.setKey(key);
            dataRecord.setValue(value);
            if (0L == this.mRecordsWritten++ % (long)this.mIndexInterval) {
               long position = this.mDataFileWriter.sync();
               AvroKeyValue<K, Long> indexRecord = new AvroKeyValue(new GenericData.Record(this.mIndexSchema));
               indexRecord.setKey(key);
               indexRecord.setValue(position);
               this.mIndexFileWriter.append(indexRecord.get());
            }

            this.mDataFileWriter.append(dataRecord.get());
         }
      }

      public void close() throws IOException {
         this.mIndexFileWriter.close();
         this.mDataFileWriter.close();
      }

      public static class Options {
         private Schema mKeySchema;
         private Schema mValueSchema;
         private Configuration mConf;
         private Path mPath;
         private int mIndexInterval = 128;
         private GenericData model = SpecificData.get();
         private CodecFactory codec = CodecFactory.nullCodec();

         public Options withKeySchema(Schema keySchema) {
            this.mKeySchema = keySchema;
            return this;
         }

         public Schema getKeySchema() {
            return this.mKeySchema;
         }

         public Options withValueSchema(Schema valueSchema) {
            this.mValueSchema = valueSchema;
            return this;
         }

         public Schema getValueSchema() {
            return this.mValueSchema;
         }

         public Options withConfiguration(Configuration conf) {
            this.mConf = conf;
            return this;
         }

         public Configuration getConfiguration() {
            return this.mConf;
         }

         public Options withPath(Path path) {
            this.mPath = path;
            return this;
         }

         public Path getPath() {
            return this.mPath;
         }

         public Options withIndexInterval(int indexInterval) {
            this.mIndexInterval = indexInterval;
            return this;
         }

         public int getIndexInterval() {
            return this.mIndexInterval;
         }

         public Options withDataModel(GenericData model) {
            this.model = model;
            return this;
         }

         public GenericData getDataModel() {
            return this.model;
         }

         public Options withCodec(String codec) {
            this.codec = CodecFactory.fromString(codec);
            return this;
         }

         public Options withCodec(CodecFactory codec) {
            this.codec = codec;
            return this;
         }

         public CodecFactory getCodec() {
            return this.codec;
         }
      }
   }
}
