package org.apache.avro.hadoop.io;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.util.Progressable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvroSequenceFile {
   private static final Logger LOG = LoggerFactory.getLogger(AvroSequenceFile.class);
   public static final Text METADATA_FIELD_KEY_SCHEMA = new Text("avro.key.schema");
   public static final Text METADATA_FIELD_VALUE_SCHEMA = new Text("avro.value.schema");

   private AvroSequenceFile() {
   }

   public static SequenceFile.Writer createWriter(Writer.Options options) throws IOException {
      return SequenceFile.createWriter(options.getFileSystem(), options.getConfigurationWithAvroSerialization(), options.getOutputPath(), options.getKeyClass(), options.getValueClass(), options.getBufferSizeBytes(), options.getReplicationFactor(), options.getBlockSizeBytes(), options.getCompressionType(), options.getCompressionCodec(), options.getProgressable(), options.getMetadataWithAvroSchemas());
   }

   private static SequenceFile.Metadata getMetadata(FileSystem fs, Path path, Configuration conf) throws IOException {
      SequenceFile.Reader metadataReader = new SequenceFile.Reader(fs, path, conf);

      SequenceFile.Metadata var4;
      try {
         var4 = metadataReader.getMetadata();
      } catch (Throwable var7) {
         try {
            metadataReader.close();
         } catch (Throwable var6) {
            var7.addSuppressed(var6);
         }

         throw var7;
      }

      metadataReader.close();
      return var4;
   }

   public static class Writer extends SequenceFile.Writer {
      public Writer(Options options) throws IOException {
         super(options.getFileSystem(), options.getConfigurationWithAvroSerialization(), options.getOutputPath(), options.getKeyClass(), options.getValueClass(), options.getBufferSizeBytes(), options.getReplicationFactor(), options.getBlockSizeBytes(), options.getProgressable(), options.getMetadataWithAvroSchemas());
      }

      public static class Options {
         private static final short DEFAULT = -1;
         private FileSystem mFileSystem;
         private Configuration mConf;
         private Path mOutputPath;
         private Class mKeyClass;
         private Schema mKeyWriterSchema;
         private Class mValueClass;
         private Schema mValueWriterSchema;
         private int mBufferSizeBytes = -1;
         private short mReplicationFactor = -1;
         private long mBlockSizeBytes = -1L;
         private Progressable mProgressable;
         private SequenceFile.CompressionType mCompressionType;
         private CompressionCodec mCompressionCodec;
         private SequenceFile.Metadata mMetadata;

         public Options() {
            this.mCompressionType = CompressionType.NONE;
            this.mMetadata = new SequenceFile.Metadata();
         }

         public Options withFileSystem(FileSystem fileSystem) {
            if (null == fileSystem) {
               throw new IllegalArgumentException("Filesystem may not be null");
            } else {
               this.mFileSystem = fileSystem;
               return this;
            }
         }

         public Options withConfiguration(Configuration conf) {
            if (null == conf) {
               throw new IllegalArgumentException("Configuration may not be null");
            } else {
               this.mConf = conf;
               return this;
            }
         }

         public Options withOutputPath(Path outputPath) {
            if (null == outputPath) {
               throw new IllegalArgumentException("Output path may not be null");
            } else {
               this.mOutputPath = outputPath;
               return this;
            }
         }

         public Options withKeyClass(Class keyClass) {
            if (null == keyClass) {
               throw new IllegalArgumentException("Key class may not be null");
            } else {
               this.mKeyClass = keyClass;
               return this;
            }
         }

         public Options withKeySchema(Schema keyWriterSchema) {
            if (null == keyWriterSchema) {
               throw new IllegalArgumentException("Key schema may not be null");
            } else {
               this.withKeyClass(AvroKey.class);
               this.mKeyWriterSchema = keyWriterSchema;
               return this;
            }
         }

         public Options withValueClass(Class valueClass) {
            if (null == valueClass) {
               throw new IllegalArgumentException("Value class may not be null");
            } else {
               this.mValueClass = valueClass;
               return this;
            }
         }

         public Options withValueSchema(Schema valueWriterSchema) {
            if (null == valueWriterSchema) {
               throw new IllegalArgumentException("Value schema may not be null");
            } else {
               this.withValueClass(AvroValue.class);
               this.mValueWriterSchema = valueWriterSchema;
               return this;
            }
         }

         public Options withBufferSizeBytes(int bytes) {
            if (bytes < 0) {
               throw new IllegalArgumentException("Buffer size may not be negative");
            } else {
               this.mBufferSizeBytes = bytes;
               return this;
            }
         }

         public Options withReplicationFactor(short replicationFactor) {
            if (replicationFactor <= 0) {
               throw new IllegalArgumentException("Replication factor must be positive");
            } else {
               this.mReplicationFactor = replicationFactor;
               return this;
            }
         }

         public Options withBlockSizeBytes(long bytes) {
            if (bytes <= 0L) {
               throw new IllegalArgumentException("Block size must be positive");
            } else {
               this.mBlockSizeBytes = bytes;
               return this;
            }
         }

         public Options withProgressable(Progressable progressable) {
            this.mProgressable = progressable;
            return this;
         }

         public Options withCompressionType(SequenceFile.CompressionType compressionType) {
            this.mCompressionType = compressionType;
            return this;
         }

         public Options withCompressionCodec(CompressionCodec compressionCodec) {
            this.mCompressionCodec = compressionCodec;
            return this;
         }

         public Options withMetadata(SequenceFile.Metadata metadata) {
            if (null == metadata) {
               throw new IllegalArgumentException("Metadata may not be null");
            } else {
               this.mMetadata = metadata;
               return this;
            }
         }

         public FileSystem getFileSystem() {
            if (null == this.mFileSystem) {
               throw new RuntimeException("Must call Options.withFileSystem()");
            } else {
               return this.mFileSystem;
            }
         }

         public Configuration getConfiguration() {
            return this.mConf;
         }

         public Configuration getConfigurationWithAvroSerialization() {
            Configuration conf = this.getConfiguration();
            if (null == conf) {
               throw new RuntimeException("Must call Options.withConfiguration()");
            } else {
               Configuration confWithAvro = new Configuration(conf);
               if (null != this.mKeyWriterSchema) {
                  AvroSerialization.setKeyWriterSchema(confWithAvro, this.mKeyWriterSchema);
               }

               if (null != this.mValueWriterSchema) {
                  AvroSerialization.setValueWriterSchema(confWithAvro, this.mValueWriterSchema);
               }

               AvroSerialization.addToConfiguration(confWithAvro);
               return confWithAvro;
            }
         }

         public Path getOutputPath() {
            if (null == this.mOutputPath) {
               throw new RuntimeException("Must call Options.withOutputPath()");
            } else {
               return this.mOutputPath;
            }
         }

         public Class getKeyClass() {
            if (null == this.mKeyClass) {
               throw new RuntimeException("Must call Options.withKeyClass() or Options.withKeySchema()");
            } else {
               return this.mKeyClass;
            }
         }

         public Class getValueClass() {
            if (null == this.mValueClass) {
               throw new RuntimeException("Must call Options.withValueClass() or Options.withValueSchema()");
            } else {
               return this.mValueClass;
            }
         }

         public int getBufferSizeBytes() {
            return -1 == this.mBufferSizeBytes ? this.getConfiguration().getInt("io.file.buffer.size", 4096) : this.mBufferSizeBytes;
         }

         public short getReplicationFactor() {
            return -1 == this.mReplicationFactor ? this.getFileSystem().getDefaultReplication() : this.mReplicationFactor;
         }

         public long getBlockSizeBytes() {
            return -1L == this.mBlockSizeBytes ? this.getFileSystem().getDefaultBlockSize() : this.mBlockSizeBytes;
         }

         public Progressable getProgressable() {
            return this.mProgressable;
         }

         public SequenceFile.CompressionType getCompressionType() {
            return this.mCompressionType;
         }

         public CompressionCodec getCompressionCodec() {
            return this.mCompressionCodec;
         }

         public SequenceFile.Metadata getMetadata() {
            return this.mMetadata;
         }

         private SequenceFile.Metadata getMetadataWithAvroSchemas() {
            assert null != this.mMetadata;

            if (null != this.mKeyWriterSchema) {
               this.mMetadata.set(AvroSequenceFile.METADATA_FIELD_KEY_SCHEMA, new Text(this.mKeyWriterSchema.toString()));
            }

            if (null != this.mValueWriterSchema) {
               this.mMetadata.set(AvroSequenceFile.METADATA_FIELD_VALUE_SCHEMA, new Text(this.mValueWriterSchema.toString()));
            }

            return this.mMetadata;
         }
      }
   }

   public static class Reader extends SequenceFile.Reader {
      public Reader(Options options) throws IOException {
         super(options.getFileSystem(), options.getInputPath(), options.getConfigurationWithAvroSerialization());
      }

      public static class Options {
         private FileSystem mFileSystem;
         private Path mInputPath;
         private Configuration mConf;
         private Schema mKeyReaderSchema;
         private Schema mValueReaderSchema;

         public Options withFileSystem(FileSystem fileSystem) {
            if (null == fileSystem) {
               throw new IllegalArgumentException("Filesystem may not be null");
            } else {
               this.mFileSystem = fileSystem;
               return this;
            }
         }

         public Options withInputPath(Path inputPath) {
            if (null == inputPath) {
               throw new IllegalArgumentException("Input path may not be null");
            } else {
               this.mInputPath = inputPath;
               return this;
            }
         }

         public Options withConfiguration(Configuration conf) {
            if (null == conf) {
               throw new IllegalArgumentException("Configuration may not be null");
            } else {
               this.mConf = conf;
               return this;
            }
         }

         public Options withKeySchema(Schema keyReaderSchema) {
            this.mKeyReaderSchema = keyReaderSchema;
            return this;
         }

         public Options withValueSchema(Schema valueReaderSchema) {
            this.mValueReaderSchema = valueReaderSchema;
            return this;
         }

         public FileSystem getFileSystem() {
            if (null == this.mFileSystem) {
               throw new RuntimeException("Must call Options.withFileSystem()");
            } else {
               return this.mFileSystem;
            }
         }

         public Path getInputPath() {
            if (null == this.mInputPath) {
               throw new RuntimeException("Must call Options.withInputPath()");
            } else {
               return this.mInputPath;
            }
         }

         public Configuration getConfiguration() {
            return this.mConf;
         }

         public Configuration getConfigurationWithAvroSerialization() throws IOException {
            Configuration conf = this.getConfiguration();
            if (null == conf) {
               throw new RuntimeException("Must call Options.withConfiguration()");
            } else {
               Configuration confWithAvro = new Configuration(conf);
               AvroSerialization.addToConfiguration(confWithAvro);
               SequenceFile.Metadata metadata = AvroSequenceFile.getMetadata(this.getFileSystem(), this.getInputPath(), confWithAvro);
               Text keySchemaText = metadata.get(AvroSequenceFile.METADATA_FIELD_KEY_SCHEMA);
               if (null != keySchemaText) {
                  AvroSequenceFile.LOG.debug("Using key writer schema from SequenceFile metadata: {}", keySchemaText);
                  AvroSerialization.setKeyWriterSchema(confWithAvro, (new Schema.Parser()).parse(keySchemaText.toString()));
                  if (null != this.mKeyReaderSchema) {
                     AvroSerialization.setKeyReaderSchema(confWithAvro, this.mKeyReaderSchema);
                  }
               }

               Text valueSchemaText = metadata.get(AvroSequenceFile.METADATA_FIELD_VALUE_SCHEMA);
               if (null != valueSchemaText) {
                  AvroSequenceFile.LOG.debug("Using value writer schema from SequenceFile metadata: {}", valueSchemaText);
                  AvroSerialization.setValueWriterSchema(confWithAvro, (new Schema.Parser()).parse(valueSchemaText.toString()));
                  if (null != this.mValueReaderSchema) {
                     AvroSerialization.setValueReaderSchema(confWithAvro, this.mValueReaderSchema);
                  }
               }

               return confWithAvro;
            }
         }
      }
   }
}
