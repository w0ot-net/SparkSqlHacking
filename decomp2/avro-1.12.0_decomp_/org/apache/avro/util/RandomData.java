package org.apache.avro.util;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.avro.LogicalType;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericArray;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;

public class RandomData implements Iterable {
   public static final String USE_DEFAULT = "use-default";
   private final GenericData genericData;
   private static final int MILLIS_IN_DAY = (int)Duration.ofDays(1L).toMillis();
   private final Schema root;
   private final long seed;
   private final int count;
   private final boolean utf8ForString;
   private static final Charset UTF8;

   public RandomData(Schema schema, int count) {
      this(schema, count, false);
   }

   public RandomData(Schema schema, int count, long seed) {
      this(schema, count, seed, false);
   }

   public RandomData(Schema schema, int count, boolean utf8ForString) {
      this(schema, count, System.currentTimeMillis(), utf8ForString);
   }

   public RandomData(Schema schema, int count, long seed, boolean utf8ForString) {
      this(GenericData.get(), schema, count, seed, utf8ForString);
   }

   public RandomData(GenericData genericData, Schema schema, int count) {
      this(genericData, schema, count, false);
   }

   public RandomData(GenericData genericData, Schema schema, int count, long seed) {
      this(genericData, schema, count, seed, false);
   }

   public RandomData(GenericData genericData, Schema schema, int count, boolean utf8ForString) {
      this(genericData, schema, count, System.currentTimeMillis(), utf8ForString);
   }

   public RandomData(GenericData genericData, Schema schema, int count, long seed, boolean utf8ForString) {
      this.genericData = genericData;
      this.root = schema;
      this.seed = seed;
      this.count = count;
      this.utf8ForString = utf8ForString;
   }

   public Iterator iterator() {
      return new Iterator() {
         private int n;
         private final Random random;

         {
            this.random = new Random(RandomData.this.seed);
         }

         public boolean hasNext() {
            return this.n < RandomData.this.count;
         }

         public Object next() {
            ++this.n;
            return RandomData.this.generate(RandomData.this.root, this.random, 0);
         }

         public void remove() {
            throw new UnsupportedOperationException();
         }
      };
   }

   private Object generate(Schema schema, Random random, int d) {
      switch (schema.getType()) {
         case RECORD:
            Object record = this.genericData.newRecord((Object)null, schema);

            for(Schema.Field field : schema.getFields()) {
               Object value = field.getObjectProp("use-default") == null ? this.generate(field.schema(), random, d + 1) : GenericData.get().getDefaultValue(field);
               this.genericData.setField(record, field.name(), field.pos(), value);
            }

            return record;
         case ENUM:
            List<String> symbols = schema.getEnumSymbols();
            return this.genericData.createEnum((String)symbols.get(random.nextInt(symbols.size())), schema);
         case ARRAY:
            int length = Math.max(0, random.nextInt(5) + 2 - d);
            GenericArray<Object> array = (GenericArray)this.genericData.newArray((Object)null, length, schema);

            for(int i = 0; i < length; ++i) {
               array.add(this.generate(schema.getElementType(), random, d + 1));
            }

            return array;
         case MAP:
            int length = Math.max(0, random.nextInt(5) + 2 - d);
            Map<Object, Object> map = (Map)this.genericData.newMap((Object)null, length);

            for(int i = 0; i < length; ++i) {
               map.put(this.randomString(random, 40), this.generate(schema.getValueType(), random, d + 1));
            }

            return map;
         case UNION:
            List<Schema> types = schema.getTypes();
            return this.generate((Schema)types.get(random.nextInt(types.size())), random, d);
         case FIXED:
            byte[] bytes = new byte[schema.getFixedSize()];
            random.nextBytes(bytes);
            return this.genericData.createFixed((Object)null, bytes, schema);
         case STRING:
            return this.randomString(random, 40);
         case BYTES:
            return randomBytes(random, 40);
         case INT:
            return this.randomInt(random, schema.getLogicalType());
         case LONG:
            return this.randomLong(random, schema.getLogicalType());
         case FLOAT:
            return random.nextFloat();
         case DOUBLE:
            return random.nextDouble();
         case BOOLEAN:
            return random.nextBoolean();
         case NULL:
            return null;
         default:
            throw new RuntimeException("Unknown type: " + String.valueOf(schema));
      }
   }

   private int randomInt(Random random, LogicalType type) {
      return type instanceof LogicalTypes.TimeMillis ? random.nextInt(MILLIS_IN_DAY - 1) : random.nextInt();
   }

   private long randomLong(Random random, LogicalType type) {
      return type instanceof LogicalTypes.TimeMicros ? ThreadLocalRandom.current().nextLong((long)MILLIS_IN_DAY * 1000L) : random.nextLong();
   }

   private Object randomString(Random random, int maxLength) {
      int length = random.nextInt(maxLength);
      byte[] bytes = new byte[length];

      for(int i = 0; i < length; ++i) {
         bytes[i] = (byte)(97 + random.nextInt(25));
      }

      return this.utf8ForString ? new Utf8(bytes) : new String(bytes, UTF8);
   }

   private static ByteBuffer randomBytes(Random rand, int maxLength) {
      ByteBuffer bytes = ByteBuffer.allocate(rand.nextInt(maxLength));
      bytes.limit(bytes.capacity());
      rand.nextBytes(bytes.array());
      return bytes;
   }

   public static void main(String[] args) throws Exception {
      if (args.length < 3 || args.length > 4) {
         System.out.println("Usage: RandomData <schemafile> <outputfile> <count> [codec]");
         System.exit(-1);
      }

      Schema sch = (new Schema.Parser()).parse(new File(args[0]));
      DataFileWriter<Object> writer = new DataFileWriter(new GenericDatumWriter());

      try {
         writer.setCodec(CodecFactory.fromString(args.length >= 4 ? args[3] : "null"));
         writer.setMeta("user_metadata", "someByteArray".getBytes(StandardCharsets.UTF_8));
         File file = new File(args[1]);
         Files.createDirectories(Paths.get(file.getParent()));
         writer.create(sch, file);

         for(Object datum : new RandomData(sch, Integer.parseInt(args[2]))) {
            writer.append(datum);
         }
      } catch (Throwable var7) {
         try {
            writer.close();
         } catch (Throwable var6) {
            var7.addSuppressed(var6);
         }

         throw var7;
      }

      writer.close();
   }

   static {
      UTF8 = StandardCharsets.UTF_8;
   }
}
