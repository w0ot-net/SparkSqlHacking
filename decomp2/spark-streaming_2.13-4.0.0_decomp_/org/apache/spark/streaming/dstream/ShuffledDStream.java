package org.apache.spark.streaming.dstream;

import org.apache.spark.Partitioner;
import org.apache.spark.rdd.PairRDDFunctions;
import org.apache.spark.rdd.RDD;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ud!B\n\u0015\u0001Yq\u0002\u0002C\u001d\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001e\t\u0011}\u0002!\u0011!Q\u0001\n\u0001C\u0001b\u0011\u0001\u0003\u0002\u0003\u0006I\u0001\u0012\u0005\t\u000f\u0002\u0011\t\u0011)A\u0005\u0011\"A\u0011\n\u0001B\u0001B\u0003%!\n\u0003\u0005O\u0001\t\u0005\t\u0015!\u0003P\u0011!\u0011\u0006AaA!\u0002\u0017\u0019\u0006\u0002C-\u0001\u0005\u0007\u0005\u000b1\u0002.\t\u0011m\u0003!1!Q\u0001\fqCQ!\u0018\u0001\u0005\u0002yCQA\u001b\u0001\u0005B-DQ! \u0001\u0005ByDq!a\u0002\u0001\t\u0003\nIa\u0002\u0006\u0002(Q\t\t\u0011#\u0001\u0017\u0003S1\u0011b\u0005\u000b\u0002\u0002#\u0005a#a\u000b\t\ru{A\u0011AA\"\u0011%\t)eDI\u0001\n\u0003\t9\u0005C\u0005\u0002f=\t\t\u0011\"\u0003\u0002h\ty1\u000b[;gM2,G\rR*ue\u0016\fWN\u0003\u0002\u0016-\u00059Am\u001d;sK\u0006l'BA\f\u0019\u0003%\u0019HO]3b[&twM\u0003\u0002\u001a5\u0005)1\u000f]1sW*\u00111\u0004H\u0001\u0007CB\f7\r[3\u000b\u0003u\t1a\u001c:h+\u0011yB&P\u001c\u0014\u0005\u0001\u0001\u0003cA\u0011#I5\tA#\u0003\u0002$)\t9Ai\u0015;sK\u0006l\u0007\u0003B\u0013)UYj\u0011A\n\u0006\u0002O\u0005)1oY1mC&\u0011\u0011F\n\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005-bC\u0002\u0001\u0003\u0006[\u0001\u0011\ra\f\u0002\u0002\u0017\u000e\u0001\u0011C\u0001\u00194!\t)\u0013'\u0003\u00023M\t9aj\u001c;iS:<\u0007CA\u00135\u0013\t)dEA\u0002B]f\u0004\"aK\u001c\u0005\u000ba\u0002!\u0019A\u0018\u0003\u0003\r\u000ba\u0001]1sK:$\bcA\u0011#wA!Q\u0005\u000b\u0016=!\tYS\bB\u0003?\u0001\t\u0007qFA\u0001W\u00039\u0019'/Z1uK\u000e{WNY5oKJ\u0004B!J!=m%\u0011!I\n\u0002\n\rVt7\r^5p]F\n!\"\\3sO\u00164\u0016\r\\;f!\u0015)SI\u000e\u001f7\u0013\t1eEA\u0005Gk:\u001cG/[8oe\u0005iQ.\u001a:hK\u000e{WNY5oKJ\u0004R!J#7mY\n1\u0002]1si&$\u0018n\u001c8feB\u00111\nT\u0007\u00021%\u0011Q\n\u0007\u0002\f!\u0006\u0014H/\u001b;j_:,'/\u0001\bnCB\u001c\u0016\u000eZ3D_6\u0014\u0017N\\3\u0011\u0005\u0015\u0002\u0016BA)'\u0005\u001d\u0011un\u001c7fC:\f!\"\u001a<jI\u0016t7-\u001a\u00132!\r!vKK\u0007\u0002+*\u0011aKJ\u0001\be\u00164G.Z2u\u0013\tAVK\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003))g/\u001b3f]\u000e,GE\r\t\u0004)^c\u0014AC3wS\u0012,gnY3%gA\u0019Ak\u0016\u001c\u0002\rqJg.\u001b;?)\u001dyF-\u001a4hQ&$B\u0001Y1cGB)\u0011\u0005\u0001\u0016=m!)!K\u0003a\u0002'\")\u0011L\u0003a\u00025\")1L\u0003a\u00029\")\u0011H\u0003a\u0001u!)qH\u0003a\u0001\u0001\")1I\u0003a\u0001\t\")qI\u0003a\u0001\u0011\")\u0011J\u0003a\u0001\u0015\"9aJ\u0003I\u0001\u0002\u0004y\u0015\u0001\u00043fa\u0016tG-\u001a8dS\u0016\u001cX#\u00017\u0011\u00075,\bP\u0004\u0002og:\u0011qN]\u0007\u0002a*\u0011\u0011OL\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dJ!\u0001\u001e\u0014\u0002\u000fA\f7m[1hK&\u0011ao\u001e\u0002\u0005\u0019&\u001cHO\u0003\u0002uMA\u0012\u0011p\u001f\t\u0004C\tR\bCA\u0016|\t%a8\"!A\u0001\u0002\u000b\u0005qFA\u0002`IE\nQb\u001d7jI\u0016$UO]1uS>tW#A@\u0011\t\u0005\u0005\u00111A\u0007\u0002-%\u0019\u0011Q\u0001\f\u0003\u0011\u0011+(/\u0019;j_:\fqaY8naV$X\r\u0006\u0003\u0002\f\u0005u\u0001#B\u0013\u0002\u000e\u0005E\u0011bAA\bM\t1q\n\u001d;j_:\u0004R!a\u0005\u0002\u001a\u0011j!!!\u0006\u000b\u0007\u0005]\u0001$A\u0002sI\u0012LA!a\u0007\u0002\u0016\t\u0019!\u000b\u0012#\t\u000f\u0005}Q\u00021\u0001\u0002\"\u0005Ia/\u00197jIRKW.\u001a\t\u0005\u0003\u0003\t\u0019#C\u0002\u0002&Y\u0011A\u0001V5nK\u0006y1\u000b[;gM2,G\rR*ue\u0016\fW\u000e\u0005\u0002\"\u001fM)q\"!\f\u00024A\u0019Q%a\f\n\u0007\u0005EbE\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0003k\ty$\u0004\u0002\u00028)!\u0011\u0011HA\u001e\u0003\tIwN\u0003\u0002\u0002>\u0005!!.\u0019<b\u0013\u0011\t\t%a\u000e\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005%\u0012a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$c'\u0006\u0005\u0002J\u0005}\u0013\u0011MA2+\t\tYEK\u0002P\u0003\u001bZ#!a\u0014\u0011\t\u0005E\u00131L\u0007\u0003\u0003'RA!!\u0016\u0002X\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u000332\u0013AC1o]>$\u0018\r^5p]&!\u0011QLA*\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006[E\u0011\ra\f\u0003\u0006}E\u0011\ra\f\u0003\u0006qE\u0011\raL\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003S\u0002B!a\u001b\u0002r5\u0011\u0011Q\u000e\u0006\u0005\u0003_\nY$\u0001\u0003mC:<\u0017\u0002BA:\u0003[\u0012aa\u00142kK\u000e$\b"
)
public class ShuffledDStream extends DStream {
   private final DStream parent;
   private final Function1 createCombiner;
   private final Function2 mergeValue;
   private final Function2 mergeCombiner;
   private final Partitioner partitioner;
   private final boolean mapSideCombine;
   private final ClassTag evidence$1;
   private final ClassTag evidence$2;

   public static boolean $lessinit$greater$default$6() {
      return ShuffledDStream$.MODULE$.$lessinit$greater$default$6();
   }

   public List dependencies() {
      return new .colon.colon(this.parent, scala.collection.immutable.Nil..MODULE$);
   }

   public Duration slideDuration() {
      return this.parent.slideDuration();
   }

   public Option compute(final Time validTime) {
      Option var3 = this.parent.getOrCompute(validTime);
      if (var3 instanceof Some var4) {
         RDD rdd = (RDD)var4.value();
         ClassTag x$2 = this.evidence$1;
         ClassTag x$3 = this.evidence$2;
         Null x$4 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions$default$4(rdd);
         PairRDDFunctions qual$1 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(rdd, x$2, x$3, (Ordering)null);
         Function1 x$5 = this.createCombiner;
         Function2 x$6 = this.mergeValue;
         Function2 x$7 = this.mergeCombiner;
         Partitioner x$8 = this.partitioner;
         boolean x$9 = this.mapSideCombine;
         Serializer x$10 = qual$1.combineByKey$default$6();
         return new Some(qual$1.combineByKey(x$5, x$6, x$7, x$8, x$9, x$10));
      } else if (scala.None..MODULE$.equals(var3)) {
         return scala.None..MODULE$;
      } else {
         throw new MatchError(var3);
      }
   }

   public ShuffledDStream(final DStream parent, final Function1 createCombiner, final Function2 mergeValue, final Function2 mergeCombiner, final Partitioner partitioner, final boolean mapSideCombine, final ClassTag evidence$1, final ClassTag evidence$2, final ClassTag evidence$3) {
      super(parent.ssc(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      this.parent = parent;
      this.createCombiner = createCombiner;
      this.mergeValue = mergeValue;
      this.mergeCombiner = mergeCombiner;
      this.partitioner = partitioner;
      this.mapSideCombine = mapSideCombine;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
   }
}
