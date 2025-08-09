package org.apache.spark.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.apache.avro.Schema;
import org.apache.avro.SchemaNormalization;
import org.apache.avro.generic.GenericContainer;
import org.apache.avro.generic.GenericData;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.commons.io.IOUtils;
import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkException;
import org.apache.spark.io.CompressionCodec;
import org.apache.spark.io.CompressionCodec$;
import org.apache.spark.util.Utils$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rf!\u0002\f\u0018\u0001]y\u0002\u0002C \u0001\u0005\u0003\u0005\u000b\u0011\u0002!\t\u000bE\u0003A\u0011\u0001*\t\u000fY\u0003!\u0019!C\u0005/\"1!\u000e\u0001Q\u0001\naCqa\u001b\u0001C\u0002\u0013%A\u000e\u0003\u0004w\u0001\u0001\u0006I!\u001c\u0005\bo\u0002\u0011\r\u0011\"\u0003y\u0011\u001d\tI\u0001\u0001Q\u0001\neD\u0011\"a\u0005\u0001\u0005\u0004%I!!\u0006\t\u0011\u0005\u001d\u0002\u0001)A\u0005\u0003/A\u0011\"!\u000b\u0001\u0005\u0004%I!a\u000b\t\u0011\u0005=\u0002\u0001)A\u0005\u0003[A\u0011\"!\r\u0001\u0005\u0004%I!a\r\t\u0011\u0005]\u0002\u0001)A\u0005\u0003kA!\"!\u000f\u0001\u0011\u000b\u0007I\u0011BA\u001e\u0011\u001d\t9\u0005\u0001C\u0001\u0003\u0013Bq!a\u0014\u0001\t\u0003\t\t\u0006C\u0004\u0002X\u0001!\t!!\u0017\t\u000f\u0005M\u0004\u0001\"\u0001\u0002v!9\u0011\u0011\u0011\u0001\u0005B\u0005\r\u0005bBAI\u0001\u0011\u0005\u00131\u0013\u0002\u0016\u000f\u0016tWM]5d\u0003Z\u0014xnU3sS\u0006d\u0017N_3s\u0015\tA\u0012$\u0001\u0006tKJL\u0017\r\\5{KJT!AG\u000e\u0002\u000bM\u0004\u0018M]6\u000b\u0005qi\u0012AB1qC\u000eDWMC\u0001\u001f\u0003\ry'oZ\u000b\u0003A5\u001a\"\u0001A\u0011\u0011\u0007\tJ3&D\u0001$\u0015\t!S%\u0001\u0003lef|'B\u0001\u0014(\u0003A)7o\u001c;fe&\u001c7o\u001c4uo\u0006\u0014XMC\u0001)\u0003\r\u0019w.\\\u0005\u0003U\r\u0012!bU3sS\u0006d\u0017N_3s!\taS\u0006\u0004\u0001\u0005\u000b9\u0002!\u0019\u0001\u0019\u0003\u0003\u0011\u001b\u0001!\u0005\u00022oA\u0011!'N\u0007\u0002g)\tA'A\u0003tG\u0006d\u0017-\u0003\u00027g\t9aj\u001c;iS:<\u0007C\u0001\u001d>\u001b\u0005I$B\u0001\u001e<\u0003\u001d9WM\\3sS\u000eT!\u0001P\u000e\u0002\t\u00054(o\\\u0005\u0003}e\u0012\u0001cR3oKJL7mQ8oi\u0006Lg.\u001a:\u0002\u000fM\u001c\u0007.Z7bgB!\u0011\tS&O\u001d\t\u0011e\t\u0005\u0002Dg5\tAI\u0003\u0002F_\u00051AH]8pizJ!aR\u001a\u0002\rA\u0013X\rZ3g\u0013\tI%JA\u0002NCBT!aR\u001a\u0011\u0005Ib\u0015BA'4\u0005\u0011auN\\4\u0011\u0005\u0005{\u0015B\u0001)K\u0005\u0019\u0019FO]5oO\u00061A(\u001b8jiz\"\"aU+\u0011\u0007Q\u00031&D\u0001\u0018\u0011\u0015y$\u00011\u0001A\u00035\u0019w.\u001c9sKN\u001c8)Y2iKV\t\u0001\f\u0005\u0003Z=\u0002$W\"\u0001.\u000b\u0005mc\u0016aB7vi\u0006\u0014G.\u001a\u0006\u0003;N\n!bY8mY\u0016\u001cG/[8o\u0013\ty&LA\u0004ICNDW*\u00199\u0011\u0005\u0005\u0014W\"A\u001e\n\u0005\r\\$AB*dQ\u0016l\u0017\rE\u00023K\u001eL!AZ\u001a\u0003\u000b\u0005\u0013(/Y=\u0011\u0005IB\u0017BA54\u0005\u0011\u0011\u0015\u0010^3\u0002\u001d\r|W\u000e\u001d:fgN\u001c\u0015m\u00195fA\u0005yA-Z2p[B\u0014Xm]:DC\u000eDW-F\u0001n!\u0011IfL\u001c1\u0011\u0005=$X\"\u00019\u000b\u0005E\u0014\u0018a\u00018j_*\t1/\u0001\u0003kCZ\f\u0017BA;q\u0005)\u0011\u0015\u0010^3Ck\u001a4WM]\u0001\u0011I\u0016\u001cw.\u001c9sKN\u001c8)Y2iK\u0002\n1b\u001e:ji\u0016\u00148)Y2iKV\t\u0011\u0010\u0005\u0003Z=\u0002T\bgA>\u0002\u0006A!Ap`A\u0002\u001b\u0005i(B\u0001@<\u0003\tIw.C\u0002\u0002\u0002u\u00141\u0002R1uk6<&/\u001b;feB\u0019A&!\u0002\u0005\u0017\u0005\u001d\u0001\"!A\u0001\u0002\u000b\u0005\u00111\u0002\u0002\u0004?\u0012\n\u0014\u0001D<sSR,'oQ1dQ\u0016\u0004\u0013cA\u0019\u0002\u000eA\u0019!'a\u0004\n\u0007\u0005E1GA\u0002B]f\f1B]3bI\u0016\u00148)Y2iKV\u0011\u0011q\u0003\t\u00063z\u0003\u0017\u0011\u0004\u0019\u0005\u00037\t\u0019\u0003E\u0003}\u0003;\t\t#C\u0002\u0002 u\u00141\u0002R1uk6\u0014V-\u00193feB\u0019A&a\t\u0005\u0017\u0005\u0015\"\"!A\u0001\u0002\u000b\u0005\u00111\u0002\u0002\u0004?\u0012\u0012\u0014\u0001\u0004:fC\u0012,'oQ1dQ\u0016\u0004\u0013\u0001\u00054j]\u001e,'\u000f\u001d:j]R\u001c\u0015m\u00195f+\t\ti\u0003\u0005\u0003Z=\u0002\\\u0015!\u00054j]\u001e,'\u000f\u001d:j]R\u001c\u0015m\u00195fA\u0005Y1o\u00195f[\u0006\u001c\u0015m\u00195f+\t\t)\u0004\u0005\u0003Z=.\u0003\u0017\u0001D:dQ\u0016l\u0017mQ1dQ\u0016\u0004\u0013!B2pI\u0016\u001cWCAA\u001f!\u0011\ty$a\u0011\u000e\u0005\u0005\u0005#B\u0001@\u001a\u0013\u0011\t)%!\u0011\u0003!\r{W\u000e\u001d:fgNLwN\\\"pI\u0016\u001c\u0017\u0001C2p[B\u0014Xm]:\u0015\u0007\u0011\fY\u0005\u0003\u0004\u0002NA\u0001\r\u0001Y\u0001\u0007g\u000eDW-\\1\u0002\u0015\u0011,7m\\7qe\u0016\u001c8\u000fF\u0002a\u0003'Ba!!\u0016\u0012\u0001\u0004q\u0017aC:dQ\u0016l\u0017MQ=uKN\fab]3sS\u0006d\u0017N_3ECR,X\u000e\u0006\u0004\u0002\\\u0005\u0005\u0014Q\r\t\u0004e\u0005u\u0013bAA0g\t!QK\\5u\u0011\u0019\t\u0019G\u0005a\u0001W\u0005)A-\u0019;v[\"9\u0011q\r\nA\u0002\u0005%\u0014AB8viB,H\u000f\u0005\u0003\u0002l\u0005=TBAA7\u0015\tq8%\u0003\u0003\u0002r\u00055$AB(viB,H/\u0001\teKN,'/[1mSj,G)\u0019;v[R\u00191&a\u001e\t\u000f\u0005e4\u00031\u0001\u0002|\u0005)\u0011N\u001c9viB!\u00111NA?\u0013\u0011\ty(!\u001c\u0003\u000b%s\u0007/\u001e;\u0002\u000b]\u0014\u0018\u000e^3\u0015\u0011\u0005m\u0013QQAG\u0003\u001fCa\u0001\n\u000bA\u0002\u0005\u001d\u0005c\u0001\u0012\u0002\n&\u0019\u00111R\u0012\u0003\t-\u0013\u0018p\u001c\u0005\b\u0003O\"\u0002\u0019AA5\u0011\u0019\t\u0019\u0007\u0006a\u0001W\u0005!!/Z1e)\u001dY\u0013QSAL\u00033Ca\u0001J\u000bA\u0002\u0005\u001d\u0005bBA=+\u0001\u0007\u00111\u0010\u0005\b\u00037+\u0002\u0019AAO\u0003)!\u0017\r^;n\u00072\f7o\u001d\t\u0005\u0003\u0006}5&C\u0002\u0002\"*\u0013Qa\u00117bgN\u0004"
)
public class GenericAvroSerializer extends com.esotericsoftware.kryo.Serializer {
   private CompressionCodec codec;
   private final Map schemas;
   private final HashMap compressCache;
   private final HashMap decompressCache;
   private final HashMap writerCache;
   private final HashMap readerCache;
   private final HashMap fingerprintCache;
   private final HashMap schemaCache;
   private volatile boolean bitmap$0;

   private HashMap compressCache() {
      return this.compressCache;
   }

   private HashMap decompressCache() {
      return this.decompressCache;
   }

   private HashMap writerCache() {
      return this.writerCache;
   }

   private HashMap readerCache() {
      return this.readerCache;
   }

   private HashMap fingerprintCache() {
      return this.fingerprintCache;
   }

   private HashMap schemaCache() {
      return this.schemaCache;
   }

   private CompressionCodec codec$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.codec = CompressionCodec$.MODULE$.createCodec(SparkEnv$.MODULE$.get().conf());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.codec;
   }

   private CompressionCodec codec() {
      return !this.bitmap$0 ? this.codec$lzycompute() : this.codec;
   }

   public byte[] compress(final Schema schema) {
      return (byte[])this.compressCache().getOrElseUpdate(schema, () -> {
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         OutputStream out = this.codec().compressedOutputStream(bos);
         Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> out.write(schema.toString().getBytes(StandardCharsets.UTF_8)), (JFunction0.mcV.sp)() -> out.close());
         return bos.toByteArray();
      });
   }

   public Schema decompress(final ByteBuffer schemaBytes) {
      return (Schema)this.decompressCache().getOrElseUpdate(schemaBytes, () -> {
         ByteArrayInputStream bis = new ByteArrayInputStream(schemaBytes.array(), schemaBytes.arrayOffset() + schemaBytes.position(), schemaBytes.remaining());
         InputStream in = this.codec().compressedInputStream(bis);
         byte[] bytes = (byte[])Utils$.MODULE$.tryWithSafeFinally(() -> IOUtils.toByteArray(in), (JFunction0.mcV.sp)() -> in.close());
         return (new Schema.Parser()).setValidateDefaults(false).parse(new String(bytes, StandardCharsets.UTF_8));
      });
   }

   public void serializeDatum(final GenericContainer datum, final Output output) {
      BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(output, (BinaryEncoder)null);
      Schema schema = datum.getSchema();
      long fingerprint = BoxesRunTime.unboxToLong(this.fingerprintCache().getOrElseUpdate(schema, (JFunction0.mcJ.sp)() -> SchemaNormalization.parsingFingerprint64(schema)));
      Option var8 = this.schemas.get(BoxesRunTime.boxToLong(fingerprint));
      if (var8 instanceof Some) {
         output.writeBoolean(true);
         output.writeLong(fingerprint);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         if (!.MODULE$.equals(var8)) {
            throw new MatchError(var8);
         }

         output.writeBoolean(false);
         byte[] compressedSchema = this.compress(schema);
         output.writeInt(compressedSchema.length);
         output.writeBytes(compressedSchema);
         BoxedUnit var10 = BoxedUnit.UNIT;
      }

      ((DatumWriter)this.writerCache().getOrElseUpdate(schema, () -> GenericData.get().createDatumWriter(schema))).write(datum, encoder);
      encoder.flush();
   }

   public GenericContainer deserializeDatum(final Input input) {
      Schema var10000;
      if (input.readBoolean()) {
         long fingerprint = input.readLong();
         var10000 = (Schema)this.schemaCache().getOrElseUpdate(BoxesRunTime.boxToLong(fingerprint), () -> {
            Option var4 = this.schemas.get(BoxesRunTime.boxToLong(fingerprint));
            if (var4 instanceof Some var5) {
               String s = (String)var5.value();
               return (new Schema.Parser()).setValidateDefaults(false).parse(s);
            } else if (.MODULE$.equals(var4)) {
               throw new SparkException("ERROR_READING_AVRO_UNKNOWN_FINGERPRINT", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("fingerprint"), Long.toString(fingerprint))}))), (Throwable)null);
            } else {
               throw new MatchError(var4);
            }
         });
      } else {
         int length = input.readInt();
         var10000 = this.decompress(ByteBuffer.wrap(input.readBytes(length)));
      }

      Schema schema = var10000;
      BinaryDecoder decoder = DecoderFactory.get().directBinaryDecoder(input, (BinaryDecoder)null);
      return (GenericContainer)((DatumReader)this.readerCache().getOrElseUpdate(schema, () -> GenericData.get().createDatumReader(schema))).read((Object)null, decoder);
   }

   public void write(final Kryo kryo, final Output output, final GenericContainer datum) {
      this.serializeDatum(datum, output);
   }

   public GenericContainer read(final Kryo kryo, final Input input, final Class datumClass) {
      return this.deserializeDatum(input);
   }

   public GenericAvroSerializer(final Map schemas) {
      this.schemas = schemas;
      this.compressCache = new HashMap();
      this.decompressCache = new HashMap();
      this.writerCache = new HashMap();
      this.readerCache = new HashMap();
      this.fingerprintCache = new HashMap();
      this.schemaCache = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
