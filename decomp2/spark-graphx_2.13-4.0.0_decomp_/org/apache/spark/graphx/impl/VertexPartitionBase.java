package org.apache.spark.graphx.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.util.collection.BitSet;
import org.apache.spark.util.collection.OpenHashSet;
import scala.Function2;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rvA\u0002\n\u0014\u0011\u0003)RD\u0002\u0004 '!\u0005Q\u0003\t\u0005\u0006_\u0005!\t!\r\u0005\u0006e\u0005!\ta\r\u0005\u0006e\u0005!\t\u0001 \u0005\n\u0003;\t\u0011\u0011!C\u0005\u0003?1qaH\n\u0002\u0002U\ti\u0003\u0003\u0006\u00026\u0019\u0011\u0019\u0011)A\u0006\u0003oAaa\f\u0004\u0005\u0002\u0005\r\u0004bBA6\r\u0019\u0005\u0011Q\u000e\u0005\b\u0003_2a\u0011AA9\u0011\u001d\t)H\u0002D\u0001\u0003oB\u0011\"!\u001f\u0007\u0005\u0004%\t!a\u001f\t\u0011\u0005\re\u0001)A\u0005\u0003{Bq!!\"\u0007\t\u0003\tY\bC\u0004\u0002\b\u001a!\t!!#\t\u000f\u0005=e\u0001\"\u0001\u0002\u0012\"9\u00111\u0014\u0004\u0005\u0002\u0005u\u0015a\u0005,feR,\u0007\u0010U1si&$\u0018n\u001c8CCN,'B\u0001\u000b\u0016\u0003\u0011IW\u000e\u001d7\u000b\u0005Y9\u0012AB4sCBD\u0007P\u0003\u0002\u00193\u0005)1\u000f]1sW*\u0011!dG\u0001\u0007CB\f7\r[3\u000b\u0003q\t1a\u001c:h!\tq\u0012!D\u0001\u0014\u0005M1VM\u001d;fqB\u000b'\u000f^5uS>t')Y:f'\r\t\u0011e\n\t\u0003E\u0015j\u0011a\t\u0006\u0002I\u0005)1oY1mC&\u0011ae\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005!jS\"A\u0015\u000b\u0005)Z\u0013AA5p\u0015\u0005a\u0013\u0001\u00026bm\u0006L!AL\u0015\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?\u0007\u0001!\u0012!H\u0001\tS:LGO\u0012:p[V\u0011A'\u0012\u000b\u0003ky#\"A\u000e,\u0011\u000b\t:\u0014\b\u0011(\n\u0005a\u001a#A\u0002+va2,7\u0007\u0005\u0002;{9\u0011adO\u0005\u0003yM\tq\u0001]1dW\u0006<W-\u0003\u0002?\u007f\t\u0011b+\u001a:uKbLE\rV8J]\u0012,\u00070T1q\u0015\ta4\u0003E\u0002#\u0003\u000eK!AQ\u0012\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0011+E\u0002\u0001\u0003\u0006\r\u000e\u0011\ra\u0012\u0002\u0003-\u0012\u000b\"\u0001S&\u0011\u0005\tJ\u0015B\u0001&$\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\t'\n\u00055\u001b#aA!osB\u0011q\nV\u0007\u0002!*\u0011\u0011KU\u0001\u000bG>dG.Z2uS>t'BA*\u0018\u0003\u0011)H/\u001b7\n\u0005U\u0003&A\u0002\"jiN+G\u000fC\u0004X\u0007\u0005\u0005\t9\u0001-\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002Z9\u000ek\u0011A\u0017\u0006\u00037\u000e\nqA]3gY\u0016\u001cG/\u0003\u0002^5\nA1\t\\1tgR\u000bw\rC\u0003`\u0007\u0001\u0007\u0001-\u0001\u0003ji\u0016\u0014\bcA1iW:\u0011!m\u001a\b\u0003G\u001al\u0011\u0001\u001a\u0006\u0003KB\na\u0001\u0010:p_Rt\u0014\"\u0001\u0013\n\u0005q\u001a\u0013BA5k\u0005!IE/\u001a:bi>\u0014(B\u0001\u001f$!\u0011\u0011CN\\\"\n\u00055\u001c#A\u0002+va2,'\u0007\u0005\u0002ps:\u0011\u0001\u000f\u001f\b\u0003c^t!A\u001d<\u000f\u0005M,hBA2u\u0013\u0005a\u0012B\u0001\u000e\u001c\u0013\tA\u0012$\u0003\u0002\u0017/%\u0011A(F\u0005\u0003un\u0014\u0001BV3si\u0016D\u0018\n\u001a\u0006\u0003yU)2!`A\u0003)\u0015q\u0018QBA\n)\ry\u0018q\u0001\t\u0007E]J\u0014\u0011\u0001(\u0011\t\t\n\u00151\u0001\t\u0004\t\u0006\u0015A!\u0002$\u0005\u0005\u00049\u0005\"CA\u0005\t\u0005\u0005\t9AA\u0006\u0003))g/\u001b3f]\u000e,GE\r\t\u00053r\u000b\u0019\u0001\u0003\u0004`\t\u0001\u0007\u0011q\u0002\t\u0005C\"\f\t\u0002E\u0003#Y:\f\u0019\u0001C\u0004\u0002\u0016\u0011\u0001\r!a\u0006\u0002\u00135,'oZ3Gk:\u001c\u0007#\u0003\u0012\u0002\u001a\u0005\r\u00111AA\u0002\u0013\r\tYb\t\u0002\n\rVt7\r^5p]J\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\t\u0011\t\u0005\r\u0012\u0011F\u0007\u0003\u0003KQ1!a\n,\u0003\u0011a\u0017M\\4\n\t\u0005-\u0012Q\u0005\u0002\u0007\u001f\nTWm\u0019;\u0016\t\u0005=\u00121H\n\u0005\r\u0005\n\t\u0004E\u0002b\u0003gI!A\f6\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007\u0005\u0003Z9\u0006e\u0002c\u0001#\u0002<\u0011IaI\u0002Q\u0001\u0002\u0003\u0015\ra\u0012\u0015\u000b\u0003w\ty$!\u0012\u0002P\u0005e\u0003c\u0001\u0012\u0002B%\u0019\u00111I\u0012\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\nG\u0005\u001d\u0013\u0011JA'\u0003\u0017r1AIA%\u0013\r\tYeI\u0001\u0005\u0019>tw-\r\u0003%E\u001a$\u0013'C\u0012\u0002R\u0005M\u0013qKA+\u001d\r\u0011\u00131K\u0005\u0004\u0003+\u001a\u0013aA%oiF\"AE\u00194%c%\u0019\u00131LA/\u0003C\nyFD\u0002#\u0003;J1!a\u0018$\u0003\u0019!u.\u001e2mKF\"AE\u00194%)\t\t)\u0007\u0006\u0003\u0002h\u0005%\u0004\u0003\u0002\u0010\u0007\u0003sAq!!\u000e\t\u0001\b\t9$A\u0003j]\u0012,\u00070F\u0001:\u0003\u00191\u0018\r\\;fgV\u0011\u00111\u000f\t\u0005E\u0005\u000bI$\u0001\u0003nCN\\W#\u0001(\u0002\u0011\r\f\u0007/Y2jif,\"!! \u0011\u0007\t\ny(C\u0002\u0002\u0002\u000e\u00121!\u00138u\u0003%\u0019\u0017\r]1dSRL\b%\u0001\u0003tSj,\u0017!B1qa2LH\u0003BA\u001d\u0003\u0017Ca!!$\u0010\u0001\u0004q\u0017a\u0001<jI\u0006I\u0011n\u001d#fM&tW\r\u001a\u000b\u0005\u0003'\u000bI\nE\u0002#\u0003+K1!a&$\u0005\u001d\u0011un\u001c7fC:Da!!$\u0011\u0001\u0004q\u0017\u0001C5uKJ\fGo\u001c:\u0016\u0005\u0005}\u0005\u0003B1i\u0003C\u0003RA\t7o\u0003s\u0001"
)
public abstract class VertexPartitionBase implements Serializable {
   private final int capacity = this.index().capacity();

   public static Tuple3 initFrom(final Iterator iter, final Function2 mergeFunc, final ClassTag evidence$2) {
      return VertexPartitionBase$.MODULE$.initFrom(iter, mergeFunc, evidence$2);
   }

   public static Tuple3 initFrom(final Iterator iter, final ClassTag evidence$1) {
      return VertexPartitionBase$.MODULE$.initFrom(iter, evidence$1);
   }

   public abstract OpenHashSet index();

   public abstract Object values();

   public abstract BitSet mask();

   public int capacity() {
      return this.capacity;
   }

   public int size() {
      return this.mask().cardinality();
   }

   public Object apply(final long vid) {
      return .MODULE$.array_apply(this.values(), this.index().getPos$mcJ$sp(vid));
   }

   public boolean isDefined(final long vid) {
      int pos = this.index().getPos$mcJ$sp(vid);
      return pos >= 0 && this.mask().get(pos);
   }

   public Iterator iterator() {
      return this.mask().iterator().map((ind) -> $anonfun$iterator$1(this, BoxesRunTime.unboxToInt(ind)));
   }

   public double[] values$mcD$sp() {
      return (double[])this.values();
   }

   public int[] values$mcI$sp() {
      return (int[])this.values();
   }

   public long[] values$mcJ$sp() {
      return (long[])this.values();
   }

   public double apply$mcD$sp(final long vid) {
      return BoxesRunTime.unboxToDouble(this.apply(vid));
   }

   public int apply$mcI$sp(final long vid) {
      return BoxesRunTime.unboxToInt(this.apply(vid));
   }

   public long apply$mcJ$sp(final long vid) {
      return BoxesRunTime.unboxToLong(this.apply(vid));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$iterator$1(final VertexPartitionBase $this, final int ind) {
      return new Tuple2(BoxesRunTime.boxToLong($this.index().getValue$mcJ$sp(ind)), .MODULE$.array_apply($this.values(), ind));
   }

   public VertexPartitionBase(final ClassTag evidence$3) {
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
