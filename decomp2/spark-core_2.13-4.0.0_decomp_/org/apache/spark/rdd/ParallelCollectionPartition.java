package org.apache.spark.rdd;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.SparkEnv$;
import org.apache.spark.serializer.DeserializationStream;
import org.apache.spark.serializer.JavaSerializer;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.util.Utils$;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb!\u0002\n\u0014\u0001UY\u0002\u0002C\u0018\u0001\u0005\u0003\u0007I\u0011A\u0019\t\u0011U\u0002!\u00111A\u0005\u0002YB\u0001\u0002\u0010\u0001\u0003\u0002\u0003\u0006KA\r\u0005\t{\u0001\u0011\t\u0019!C\u0001}!A!\t\u0001BA\u0002\u0013\u00051\t\u0003\u0005F\u0001\t\u0005\t\u0015)\u0003@\u0011!1\u0005A!a\u0001\n\u00039\u0005\u0002C0\u0001\u0005\u0003\u0007I\u0011\u00011\t\u0011\t\u0004!\u0011!Q!\n!C\u0001b\u0019\u0001\u0003\u0004\u0003\u0006Y\u0001\u001a\u0005\u0006U\u0002!\ta\u001b\u0005\u0006g\u0002!\t\u0001\u001e\u0005\u0006q\u0002!\t%\u001f\u0005\u0006u\u0002!\te\u001f\u0005\u0007\u0003\u0007\u0001A\u0011\t \t\u000f\u0005\u0015\u0001\u0001\"\u0003\u0002\b!9\u00111\u0005\u0001\u0005\n\u0005\u0015\"a\u0007)be\u0006dG.\u001a7D_2dWm\u0019;j_:\u0004\u0016M\u001d;ji&|gN\u0003\u0002\u0015+\u0005\u0019!\u000f\u001a3\u000b\u0005Y9\u0012!B:qCJ\\'B\u0001\r\u001a\u0003\u0019\t\u0007/Y2iK*\t!$A\u0002pe\u001e,\"\u0001\b,\u0014\t\u0001i2e\n\t\u0003=\u0005j\u0011a\b\u0006\u0002A\u0005)1oY1mC&\u0011!e\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0011*S\"A\u000b\n\u0005\u0019*\"!\u0003)beRLG/[8o!\tAS&D\u0001*\u0015\tQ3&\u0001\u0002j_*\tA&\u0001\u0003kCZ\f\u0017B\u0001\u0018*\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0015\u0011H\rZ%e\u0007\u0001)\u0012A\r\t\u0003=MJ!\u0001N\u0010\u0003\t1{gnZ\u0001\ne\u0012$\u0017\nZ0%KF$\"a\u000e\u001e\u0011\u0005yA\u0014BA\u001d \u0005\u0011)f.\u001b;\t\u000fm\u0012\u0011\u0011!a\u0001e\u0005\u0019\u0001\u0010J\u0019\u0002\rI$G-\u00133!\u0003\u0015\u0019H.[2f+\u0005y\u0004C\u0001\u0010A\u0013\t\tuDA\u0002J]R\f\u0011b\u001d7jG\u0016|F%Z9\u0015\u0005]\"\u0005bB\u001e\u0006\u0003\u0003\u0005\raP\u0001\u0007g2L7-\u001a\u0011\u0002\rY\fG.^3t+\u0005A\u0005cA%R):\u0011!j\u0014\b\u0003\u0017:k\u0011\u0001\u0014\u0006\u0003\u001bB\na\u0001\u0010:p_Rt\u0014\"\u0001\u0011\n\u0005A{\u0012a\u00029bG.\fw-Z\u0005\u0003%N\u00131aU3r\u0015\t\u0001v\u0004\u0005\u0002V-2\u0001A!B,\u0001\u0005\u0004A&!\u0001+\u0012\u0005ec\u0006C\u0001\u0010[\u0013\tYvDA\u0004O_RD\u0017N\\4\u0011\u0005yi\u0016B\u00010 \u0005\r\te._\u0001\u000bm\u0006dW/Z:`I\u0015\fHCA\u001cb\u0011\u001dY\u0004\"!AA\u0002!\u000bqA^1mk\u0016\u001c\b%\u0001\u0006fm&$WM\\2fIE\u00022!\u001a5U\u001b\u00051'BA4 \u0003\u001d\u0011XM\u001a7fGRL!!\u001b4\u0003\u0011\rc\u0017m]:UC\u001e\fa\u0001P5oSRtD\u0003\u00027qcJ$\"!\\8\u0011\u00079\u0004A+D\u0001\u0014\u0011\u0015\u00197\u0002q\u0001e\u0011\u0015y3\u00021\u00013\u0011\u0015i4\u00021\u0001@\u0011\u001515\u00021\u0001I\u0003!IG/\u001a:bi>\u0014X#A;\u0011\u0007%3H+\u0003\u0002x'\nA\u0011\n^3sCR|'/\u0001\u0005iCND7i\u001c3f)\u0005y\u0014AB3rk\u0006d7\u000f\u0006\u0002}\u007fB\u0011a$`\u0005\u0003}~\u0011qAQ8pY\u0016\fg\u000e\u0003\u0004\u0002\u00029\u0001\r\u0001X\u0001\u0006_RDWM]\u0001\u0006S:$W\r_\u0001\foJLG/Z(cU\u0016\u001cG\u000fF\u00028\u0003\u0013Aq!a\u0003\u0011\u0001\u0004\ti!A\u0002pkR\u00042\u0001KA\b\u0013\r\t\t\"\u000b\u0002\u0013\u001f\nTWm\u0019;PkR\u0004X\u000f^*ue\u0016\fW\u000eK\u0003\u0011\u0003+\t\t\u0003E\u0003\u001f\u0003/\tY\"C\u0002\u0002\u001a}\u0011a\u0001\u001e5s_^\u001c\bc\u0001\u0015\u0002\u001e%\u0019\u0011qD\u0015\u0003\u0017%{U\t_2faRLwN\\\u0012\u0003\u00037\t!B]3bI>\u0013'.Z2u)\r9\u0014q\u0005\u0005\b\u0003S\t\u0002\u0019AA\u0016\u0003\tIg\u000eE\u0002)\u0003[I1!a\f*\u0005Ey%M[3di&s\u0007/\u001e;TiJ,\u0017-\u001c\u0015\u0006#\u0005U\u0011\u0011\u0005"
)
public class ParallelCollectionPartition implements Partition {
   private long rddId;
   private int slice;
   private Seq values;

   // $FF: synthetic method
   public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
      return super.equals(x$1);
   }

   public long rddId() {
      return this.rddId;
   }

   public void rddId_$eq(final long x$1) {
      this.rddId = x$1;
   }

   public int slice() {
      return this.slice;
   }

   public void slice_$eq(final int x$1) {
      this.slice = x$1;
   }

   public Seq values() {
      return this.values;
   }

   public void values_$eq(final Seq x$1) {
      this.values = x$1;
   }

   public Iterator iterator() {
      return this.values().iterator();
   }

   public int hashCode() {
      return (int)(41L * (41L + this.rddId()) + (long)this.slice());
   }

   public boolean equals(final Object other) {
      if (!(other instanceof ParallelCollectionPartition var4)) {
         return false;
      } else {
         return this.rddId() == var4.rddId() && this.slice() == var4.slice();
      }
   }

   public int index() {
      return this.slice();
   }

   private void writeObject(final ObjectOutputStream out) throws IOException {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         Serializer sfactory = SparkEnv$.MODULE$.get().serializer();
         if (sfactory instanceof JavaSerializer) {
            out.defaultWriteObject();
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else {
            out.writeLong(this.rddId());
            out.writeInt(this.slice());
            SerializerInstance ser = sfactory.newInstance();
            Utils$.MODULE$.serializeViaNestedStream(out, ser, (x$1) -> {
               $anonfun$writeObject$2(this, x$1);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      });
   }

   private void readObject(final ObjectInputStream in) throws IOException {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         Serializer sfactory = SparkEnv$.MODULE$.get().serializer();
         if (sfactory instanceof JavaSerializer) {
            in.defaultReadObject();
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else {
            this.rddId_$eq(in.readLong());
            this.slice_$eq(in.readInt());
            SerializerInstance ser = sfactory.newInstance();
            Utils$.MODULE$.deserializeViaNestedStream(in, ser, (ds) -> {
               $anonfun$readObject$2(this, ds);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$writeObject$2(final ParallelCollectionPartition $this, final SerializationStream x$1) {
      x$1.writeObject($this.values(), .MODULE$.apply(Seq.class));
   }

   // $FF: synthetic method
   public static final void $anonfun$readObject$2(final ParallelCollectionPartition $this, final DeserializationStream ds) {
      $this.values_$eq((Seq)ds.readObject(.MODULE$.apply(Seq.class)));
   }

   public ParallelCollectionPartition(final long rddId, final int slice, final Seq values, final ClassTag evidence$1) {
      this.rddId = rddId;
      this.slice = slice;
      this.values = values;
      super();
      Partition.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
