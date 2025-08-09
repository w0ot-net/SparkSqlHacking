package org.apache.spark.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.UnsafeInput;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import org.apache.spark.SparkException;
import org.apache.spark.internal.config.Kryo$;
import org.apache.spark.util.ByteBufferInputStream;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc!\u0002\t\u0012\u0001MI\u0002\u0002\u0003\u0010\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0011\t\u0011\r\u0002!\u0011!Q\u0001\n\u0011B\u0001B\u000b\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\u0006W\u0001!\t\u0001\f\u0005\u0007c\u0001\u0001\u000b\u0015\u0002\u001a\t\r\u0015\u0003A\u0011A\tG\u0011\u00199\u0005\u0001\"\u0001\u0012\u0011\"AQ\n\u0001EC\u0002\u0013%a\n\u0003\u0005V\u0001!\u0015\r\u0011\"\u0003W\u0011\u0015Q\u0006\u0001\"\u0011\\\u0011\u0015Y\b\u0001\"\u0011}\u0011\u0019Y\b\u0001\"\u0011\u0002\u000e!9\u0011q\u0006\u0001\u0005B\u0005E\u0002bBA$\u0001\u0011\u0005\u0013\u0011\n\u0005\b\u00033\u0002A\u0011AA.\u0005YY%/_8TKJL\u0017\r\\5{KJLen\u001d;b]\u000e,'B\u0001\n\u0014\u0003)\u0019XM]5bY&TXM\u001d\u0006\u0003)U\tQa\u001d9be.T!AF\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0012aA8sON\u0011\u0001A\u0007\t\u00037qi\u0011!E\u0005\u0003;E\u0011!cU3sS\u0006d\u0017N_3s\u0013:\u001cH/\u00198dK\u0006\u00111n]\u0002\u0001!\tY\u0012%\u0003\u0002##\tq1J]=p'\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018!C;tKVs7/\u00194f!\t)\u0003&D\u0001'\u0015\u00059\u0013!B:dC2\f\u0017BA\u0015'\u0005\u001d\u0011un\u001c7fC:\fq!^:f!>|G.\u0001\u0004=S:LGO\u0010\u000b\u0005[9z\u0003\u0007\u0005\u0002\u001c\u0001!)a\u0004\u0002a\u0001A!)1\u0005\u0002a\u0001I!)!\u0006\u0002a\u0001I\u0005Q1-Y2iK\u0012\\%/_8\u0011\u0005MRT\"\u0001\u001b\u000b\u0005U2\u0014\u0001B6ss>T!a\u000e\u001d\u0002!\u0015\u001cx\u000e^3sS\u000e\u001cxN\u001a;xCJ,'\"A\u001d\u0002\u0007\r|W.\u0003\u0002<i\t!1J]=pQ\t)Q\b\u0005\u0002?\u00076\tqH\u0003\u0002A\u0003\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\u000b\u0003\t\u000bQA[1wCbL!\u0001R \u0003\u00119+H\u000e\\1cY\u0016\f!BY8se><8J]=p)\u0005\u0011\u0014a\u0003:fY\u0016\f7/Z&ss>$\"!\u0013'\u0011\u0005\u0015R\u0015BA&'\u0005\u0011)f.\u001b;\t\u000bU:\u0001\u0019\u0001\u001a\u0002\r=,H\u000f];u+\u0005y\u0005C\u0001)T\u001b\u0005\t&B\u0001*5\u0003\tIw.\u0003\u0002U#\n1q*\u001e;qkR\fQ!\u001b8qkR,\u0012a\u0016\t\u0003!bK!!W)\u0003\u000b%s\u0007/\u001e;\u0002\u0013M,'/[1mSj,WC\u0001/q)\ti\u0016\u0010\u0006\u0002_MB\u0011q\fZ\u0007\u0002A*\u0011\u0011MY\u0001\u0004]&|'\"A2\u0002\t)\fg/Y\u0005\u0003K\u0002\u0014!BQ=uK\n+hMZ3s\u0011\u001d9'\"!AA\u0004!\f!\"\u001a<jI\u0016t7-\u001a\u00134!\rIGN\\\u0007\u0002U*\u00111NJ\u0001\be\u00164G.Z2u\u0013\ti'N\u0001\u0005DY\u0006\u001c8\u000fV1h!\ty\u0007\u000f\u0004\u0001\u0005\u000bET!\u0019\u0001:\u0003\u0003Q\u000b\"a\u001d<\u0011\u0005\u0015\"\u0018BA;'\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!J<\n\u0005a4#aA!os\")!P\u0003a\u0001]\u0006\tA/A\u0006eKN,'/[1mSj,WcA?\u0002\u0002Q\u0019a0!\u0003\u0015\u0007}\f\u0019\u0001E\u0002p\u0003\u0003!Q!]\u0006C\u0002ID\u0011\"!\u0002\f\u0003\u0003\u0005\u001d!a\u0002\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007E\u0002jY~Da!a\u0003\f\u0001\u0004q\u0016!\u00022zi\u0016\u001cX\u0003BA\b\u0003+!b!!\u0005\u0002\u001e\u0005}A\u0003BA\n\u0003/\u00012a\\A\u000b\t\u0015\tHB1\u0001s\u0011%\tI\u0002DA\u0001\u0002\b\tY\"\u0001\u0006fm&$WM\\2fIU\u0002B!\u001b7\u0002\u0014!1\u00111\u0002\u0007A\u0002yCq!!\t\r\u0001\u0004\t\u0019#\u0001\u0004m_\u0006$WM\u001d\t\u0005\u0003K\tY#\u0004\u0002\u0002()\u0019\u0011\u0011\u00062\u0002\t1\fgnZ\u0005\u0005\u0003[\t9CA\u0006DY\u0006\u001c8\u000fT8bI\u0016\u0014\u0018aD:fe&\fG.\u001b>f'R\u0014X-Y7\u0015\t\u0005M\u0012\u0011\b\t\u00047\u0005U\u0012bAA\u001c#\t\u00192+\u001a:jC2L'0\u0019;j_:\u001cFO]3b[\"9\u00111H\u0007A\u0002\u0005u\u0012!A:\u0011\t\u0005}\u00121I\u0007\u0003\u0003\u0003R!A\u00152\n\t\u0005\u0015\u0013\u0011\t\u0002\r\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001\u0012I\u0016\u001cXM]5bY&TXm\u0015;sK\u0006lG\u0003BA&\u0003#\u00022aGA'\u0013\r\ty%\u0005\u0002\u0016\t\u0016\u001cXM]5bY&T\u0018\r^5p]N#(/Z1n\u0011\u001d\tYD\u0004a\u0001\u0003'\u0002B!a\u0010\u0002V%!\u0011qKA!\u0005-Ie\u000e];u'R\u0014X-Y7\u0002\u0019\u001d,G/Q;u_J+7/\u001a;\u0015\u0003\u0011\u0002"
)
public class KryoSerializerInstance extends SerializerInstance {
   private Output output;
   private Input input;
   private final KryoSerializer ks;
   private final boolean useUnsafe;
   private final boolean usePool;
   @Nullable
   private Kryo cachedKryo;
   private volatile byte bitmap$0;

   public Kryo borrowKryo() {
      if (this.usePool) {
         Kryo kryo = this.ks.pool().borrow();
         kryo.reset();
         return kryo;
      } else if (this.cachedKryo != null) {
         Kryo kryo = this.cachedKryo;
         kryo.reset();
         this.cachedKryo = null;
         return kryo;
      } else {
         return this.ks.newKryo();
      }
   }

   public void releaseKryo(final Kryo kryo) {
      if (this.usePool) {
         this.ks.pool().release(kryo);
      } else if (this.cachedKryo == null) {
         this.cachedKryo = kryo;
      }
   }

   private Output output$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.output = this.ks.newKryoOutput();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.output;
   }

   private Output output() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.output$lzycompute() : this.output;
   }

   private Input input$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.input = (Input)(this.useUnsafe ? new UnsafeInput() : new Input());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.input;
   }

   private Input input() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.input$lzycompute() : this.input;
   }

   public ByteBuffer serialize(final Object t, final ClassTag evidence$3) {
      this.output().clear();
      Kryo kryo = this.borrowKryo();

      try {
         kryo.writeClassAndObject(this.output(), t);
      } catch (Throwable var11) {
         if (var11 instanceof KryoException var7) {
            if (var7.getMessage().startsWith("Buffer overflow")) {
               throw new SparkException("KRYO_BUFFER_OVERFLOW", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("exceptionMsg"), var7.getMessage()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("bufferSizeConfKey"), Kryo$.MODULE$.KRYO_SERIALIZER_MAX_BUFFER_SIZE().key())}))), var7);
            }
         }

         throw var11;
      } finally {
         this.releaseKryo(kryo);
      }

      return ByteBuffer.wrap(this.output().toBytes());
   }

   public Object deserialize(final ByteBuffer bytes, final ClassTag evidence$4) {
      Kryo kryo = this.borrowKryo();

      Object var10000;
      try {
         if (bytes.hasArray()) {
            this.input().setBuffer(bytes.array(), bytes.arrayOffset() + bytes.position(), bytes.remaining());
         } else {
            this.input().setBuffer(new byte[4096]);
            this.input().setInputStream(new ByteBufferInputStream(bytes));
         }

         var10000 = kryo.readClassAndObject(this.input());
      } finally {
         this.releaseKryo(kryo);
      }

      return var10000;
   }

   public Object deserialize(final ByteBuffer bytes, final ClassLoader loader, final ClassTag evidence$5) {
      Kryo kryo = this.borrowKryo();
      ClassLoader oldClassLoader = kryo.getClassLoader();

      Object var10000;
      try {
         kryo.setClassLoader(loader);
         if (bytes.hasArray()) {
            this.input().setBuffer(bytes.array(), bytes.arrayOffset() + bytes.position(), bytes.remaining());
         } else {
            this.input().setBuffer(new byte[4096]);
            this.input().setInputStream(new ByteBufferInputStream(bytes));
         }

         var10000 = kryo.readClassAndObject(this.input());
      } finally {
         kryo.setClassLoader(oldClassLoader);
         this.releaseKryo(kryo);
      }

      return var10000;
   }

   public SerializationStream serializeStream(final OutputStream s) {
      return new KryoSerializationStream(this, s, this.useUnsafe);
   }

   public DeserializationStream deserializeStream(final InputStream s) {
      return new KryoDeserializationStream(this, s, this.useUnsafe);
   }

   public boolean getAutoReset() {
      Field field = Kryo.class.getDeclaredField("autoReset");
      field.setAccessible(true);
      Kryo kryo = this.borrowKryo();

      boolean var10000;
      try {
         var10000 = BoxesRunTime.unboxToBoolean(field.get(kryo));
      } finally {
         this.releaseKryo(kryo);
      }

      return var10000;
   }

   public KryoSerializerInstance(final KryoSerializer ks, final boolean useUnsafe, final boolean usePool) {
      this.ks = ks;
      this.useUnsafe = useUnsafe;
      this.usePool = usePool;
      this.cachedKryo = usePool ? null : this.borrowKryo();
   }
}
