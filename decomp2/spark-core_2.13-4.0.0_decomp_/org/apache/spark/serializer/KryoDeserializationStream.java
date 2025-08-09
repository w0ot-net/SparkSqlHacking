package org.apache.spark.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.UnsafeInput;
import java.io.EOFException;
import java.io.InputStream;
import java.util.Locale;
import org.apache.spark.util.NextIterator;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005]4Q\u0001D\u0007\u0001\u001fUA\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\t?\u0001\u0011\t\u0011)A\u0005A!A\u0001\u0006\u0001B\u0001B\u0003%\u0011\u0006C\u00030\u0001\u0011\u0005\u0001\u0007\u0003\u00046\u0001\u0001\u0006KA\u000e\u0005\u0007u\u0001\u0001\u000b\u0015B!\t\r\u0015\u0003\u0001\u0015\"\u0003G\u0011\u00159\u0005\u0001\"\u0011I\u0011\u0015q\u0006\u0001\"\u0011`\u0011\u0015\u0019\u0007\u0001\"\u0012e\u0011\u0015\t\b\u0001\"\u0012s\u0005eY%/_8EKN,'/[1mSj\fG/[8o'R\u0014X-Y7\u000b\u00059y\u0011AC:fe&\fG.\u001b>fe*\u0011\u0001#E\u0001\u0006gB\f'o\u001b\u0006\u0003%M\ta!\u00199bG\",'\"\u0001\u000b\u0002\u0007=\u0014xm\u0005\u0002\u0001-A\u0011q\u0003G\u0007\u0002\u001b%\u0011\u0011$\u0004\u0002\u0016\t\u0016\u001cXM]5bY&T\u0018\r^5p]N#(/Z1n\u0003-\u0019XM]%ogR\fgnY3\u0004\u0001A\u0011q#H\u0005\u0003=5\u0011ac\u0013:z_N+'/[1mSj,'/\u00138ti\u0006t7-Z\u0001\tS:\u001cFO]3b[B\u0011\u0011EJ\u0007\u0002E)\u00111\u0005J\u0001\u0003S>T\u0011!J\u0001\u0005U\u00064\u0018-\u0003\u0002(E\tY\u0011J\u001c9viN#(/Z1n\u0003%)8/Z+og\u00064W\r\u0005\u0002+[5\t1FC\u0001-\u0003\u0015\u00198-\u00197b\u0013\tq3FA\u0004C_>dW-\u00198\u0002\rqJg.\u001b;?)\u0011\t$g\r\u001b\u0011\u0005]\u0001\u0001\"\u0002\u000e\u0005\u0001\u0004a\u0002\"B\u0010\u0005\u0001\u0004\u0001\u0003\"\u0002\u0015\u0005\u0001\u0004I\u0013!B5oaV$\bCA\u001c@\u001b\u0005A$BA\u0012:\u0015\tQ4(\u0001\u0003lef|'B\u0001\u001f>\u0003A)7o\u001c;fe&\u001c7o\u001c4uo\u0006\u0014XMC\u0001?\u0003\r\u0019w.\\\u0005\u0003\u0001b\u0012Q!\u00138qkR\u0004\"AQ\"\u000e\u0003eJ!\u0001R\u001d\u0003\t-\u0013\u0018p\\\u0001\bQ\u0006\u001ch*\u001a=u+\u0005I\u0013A\u0003:fC\u0012|%M[3diV\u0011\u0011*\u0014\u000b\u0002\u0015R\u00111J\u0016\t\u0003\u00196c\u0001\u0001B\u0003O\u0011\t\u0007qJA\u0001U#\t\u00016\u000b\u0005\u0002+#&\u0011!k\u000b\u0002\b\u001d>$\b.\u001b8h!\tQC+\u0003\u0002VW\t\u0019\u0011I\\=\t\u000f]C\u0011\u0011!a\u00021\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u0007ec6*D\u0001[\u0015\tY6&A\u0004sK\u001adWm\u0019;\n\u0005uS&\u0001C\"mCN\u001cH+Y4\u0002\u000b\rdwn]3\u0015\u0003\u0001\u0004\"AK1\n\u0005\t\\#\u0001B+oSR\f!\"Y:Ji\u0016\u0014\u0018\r^8s+\u0005)\u0007c\u00014o':\u0011q\r\u001c\b\u0003Q.l\u0011!\u001b\u0006\u0003Un\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0017\n\u00055\\\u0013a\u00029bG.\fw-Z\u0005\u0003_B\u0014\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003[.\n!#Y:LKf4\u0016\r\\;f\u0013R,'/\u0019;peV\t1\u000fE\u0002g]R\u0004BAK;T'&\u0011ao\u000b\u0002\u0007)V\u0004H.\u001a\u001a"
)
public class KryoDeserializationStream extends DeserializationStream {
   private final KryoSerializerInstance serInstance;
   private Input input;
   private Kryo kryo;

   public boolean org$apache$spark$serializer$KryoDeserializationStream$$hasNext() {
      if (this.input == null) {
         return false;
      } else {
         boolean eof = this.input.eof();
         if (eof) {
            this.close();
         }

         return !eof;
      }
   }

   public Object readObject(final ClassTag evidence$2) {
      try {
         return this.kryo.readClassAndObject(this.input);
      } catch (Throwable var6) {
         if (var6 instanceof KryoException var5) {
            if (var5.getMessage().toLowerCase(Locale.ROOT).contains("buffer underflow")) {
               throw new EOFException();
            }
         }

         throw var6;
      }
   }

   public void close() {
      if (this.input != null) {
         try {
            this.input.close();
         } finally {
            this.serInstance.releaseKryo(this.kryo);
            this.kryo = null;
            this.input = null;
         }

      }
   }

   public final Iterator asIterator() {
      return new NextIterator() {
         // $FF: synthetic field
         private final KryoDeserializationStream $outer;

         public Object getNext() {
            if (this.$outer.org$apache$spark$serializer$KryoDeserializationStream$$hasNext()) {
               try {
                  return this.$outer.readObject(.MODULE$.Any());
               } catch (EOFException var2) {
               }
            }

            this.finished_$eq(true);
            return null;
         }

         public void close() {
            this.$outer.close();
         }

         public {
            if (KryoDeserializationStream.this == null) {
               throw null;
            } else {
               this.$outer = KryoDeserializationStream.this;
            }
         }
      };
   }

   public final Iterator asKeyValueIterator() {
      return new NextIterator() {
         // $FF: synthetic field
         private final KryoDeserializationStream $outer;

         public Tuple2 getNext() {
            if (this.$outer.org$apache$spark$serializer$KryoDeserializationStream$$hasNext()) {
               try {
                  return new Tuple2(this.$outer.readKey(.MODULE$.Any()), this.$outer.readValue(.MODULE$.Any()));
               } catch (EOFException var2) {
               }
            }

            this.finished_$eq(true);
            return null;
         }

         public void close() {
            this.$outer.close();
         }

         public {
            if (KryoDeserializationStream.this == null) {
               throw null;
            } else {
               this.$outer = KryoDeserializationStream.this;
            }
         }
      };
   }

   public KryoDeserializationStream(final KryoSerializerInstance serInstance, final InputStream inStream, final boolean useUnsafe) {
      this.serInstance = serInstance;
      this.input = (Input)(useUnsafe ? new UnsafeInput(inStream) : new Input(inStream));
      this.kryo = serInstance.borrowKryo();
   }
}
