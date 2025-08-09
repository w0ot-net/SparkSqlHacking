package org.apache.avro.mapred;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.avro.file.FileReader;
import org.apache.avro.reflect.ReflectData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;

public class SequenceFileReader implements FileReader {
   private SequenceFile.Reader reader;
   private Schema schema;
   private boolean ready;
   private boolean done;
   private Writable key;
   private Writable spareKey;
   private Writable value;
   private Converter keyConverter;
   private Converter valConverter;
   private static final Map WRITABLE_SCHEMAS = new HashMap();
   private static final Map WRITABLE_CONVERTERS;

   public SequenceFileReader(File file) throws IOException {
      this(file.toURI(), new Configuration());
   }

   public SequenceFileReader(URI uri, Configuration c) throws IOException {
      this(new SequenceFile.Reader(FileSystem.get(uri, c), new Path(uri.toString()), c), c);
   }

   public SequenceFileReader(SequenceFile.Reader reader, Configuration conf) {
      this.ready = false;
      this.done = false;
      this.keyConverter = (o) -> o;
      this.valConverter = (o) -> o;
      this.reader = reader;
      this.schema = Pair.getPairSchema(SequenceFileReader.WritableData.get().getSchema(reader.getKeyClass()), SequenceFileReader.WritableData.get().getSchema(reader.getValueClass()));
      this.key = (Writable)ReflectionUtils.newInstance(reader.getKeyClass(), conf);
      this.spareKey = (Writable)ReflectionUtils.newInstance(reader.getKeyClass(), conf);
      this.value = (Writable)ReflectionUtils.newInstance(reader.getValueClass(), conf);
      if (WRITABLE_CONVERTERS.containsKey(reader.getKeyClass())) {
         this.keyConverter = (Converter)WRITABLE_CONVERTERS.get(reader.getKeyClass());
      }

      if (WRITABLE_CONVERTERS.containsKey(reader.getValueClass())) {
         this.valConverter = (Converter)WRITABLE_CONVERTERS.get(reader.getValueClass());
      }

   }

   public void close() throws IOException {
      this.reader.close();
   }

   public void remove() {
      throw new UnsupportedOperationException();
   }

   public Iterator iterator() {
      return this;
   }

   public Schema getSchema() {
      return this.schema;
   }

   private void prepare() throws IOException {
      if (!this.ready) {
         this.done = !this.reader.next(this.key);
         this.ready = true;
      }
   }

   public boolean hasNext() {
      try {
         this.prepare();
         return !this.done;
      } catch (IOException e) {
         throw new AvroRuntimeException(e);
      }
   }

   public Pair next() {
      try {
         return this.next((Pair)null);
      } catch (IOException e) {
         throw new AvroRuntimeException(e);
      }
   }

   public Pair next(Pair reuse) throws IOException {
      this.prepare();
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         Pair<K, V> result = reuse;
         if (reuse == null) {
            result = new Pair(this.schema);
         }

         result.key(this.keyConverter.convert(this.key));
         this.reader.getCurrentValue(this.value);
         result.value(this.valConverter.convert(this.value));
         Writable k = this.key;
         this.key = this.spareKey;
         this.spareKey = k;
         this.ready = false;
         return result;
      }
   }

   public void sync(long position) throws IOException {
      if (position > this.reader.getPosition()) {
         this.reader.sync(position);
      }

      this.ready = false;
   }

   public boolean pastSync(long position) throws IOException {
      return this.reader.getPosition() >= position && this.reader.syncSeen();
   }

   public long tell() throws IOException {
      return this.reader.getPosition();
   }

   static {
      WRITABLE_SCHEMAS.put(NullWritable.class, Schema.create(Type.NULL));
      WRITABLE_SCHEMAS.put(BooleanWritable.class, Schema.create(Type.BOOLEAN));
      WRITABLE_SCHEMAS.put(IntWritable.class, Schema.create(Type.INT));
      WRITABLE_SCHEMAS.put(LongWritable.class, Schema.create(Type.LONG));
      WRITABLE_SCHEMAS.put(FloatWritable.class, Schema.create(Type.FLOAT));
      WRITABLE_SCHEMAS.put(DoubleWritable.class, Schema.create(Type.DOUBLE));
      WRITABLE_SCHEMAS.put(BytesWritable.class, Schema.create(Type.BYTES));
      WRITABLE_SCHEMAS.put(Text.class, Schema.create(Type.STRING));
      WRITABLE_CONVERTERS = new HashMap();
      WRITABLE_CONVERTERS.put(NullWritable.class, (Converter)(o) -> null);
      WRITABLE_CONVERTERS.put(BooleanWritable.class, (Converter)(o) -> ((BooleanWritable)o).get());
      WRITABLE_CONVERTERS.put(IntWritable.class, (Converter)(o) -> ((IntWritable)o).get());
      WRITABLE_CONVERTERS.put(LongWritable.class, (Converter)(o) -> ((LongWritable)o).get());
      WRITABLE_CONVERTERS.put(FloatWritable.class, (Converter)(o) -> ((FloatWritable)o).get());
      WRITABLE_CONVERTERS.put(DoubleWritable.class, (Converter)(o) -> ((DoubleWritable)o).get());
      WRITABLE_CONVERTERS.put(BytesWritable.class, (Converter)(o) -> {
         BytesWritable b = (BytesWritable)o;
         return ByteBuffer.wrap(b.getBytes(), 0, b.getLength());
      });
      WRITABLE_CONVERTERS.put(Text.class, Object::toString);
   }

   private static class WritableData extends ReflectData {
      private static final WritableData INSTANCE = new WritableData();

      protected WritableData() {
      }

      public static WritableData get() {
         return INSTANCE;
      }

      public Schema getSchema(java.lang.reflect.Type type) {
         return SequenceFileReader.WRITABLE_SCHEMAS.containsKey(type) ? (Schema)SequenceFileReader.WRITABLE_SCHEMAS.get(type) : super.getSchema(type);
      }
   }

   private interface Converter {
      Object convert(Writable o);
   }
}
