package org.apache.spark.graphx;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.graphx.impl.EdgePartition;
import org.apache.spark.graphx.impl.EdgeRDDImpl;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import scala.Function1;
import scala.Function4;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\ra!B\t\u0013\u0003\u0003Y\u0002\u0002C\u001b\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001c\t\u0011i\u0002!\u0011!Q\u0001\nmBQA\u0014\u0001\u0005\u0002=Ca\u0001\u0017\u0001\u0007\u0002II\u0006\"\u00028\u0001\t#z\u0007\"\u0002<\u0001\t\u0003:\bbBA\u0003\u0001\u0019\u0005\u0011q\u0001\u0005\b\u0003_\u0001a\u0011AA\u0019\u0011\u001d\t\u0019\u0004\u0001D\u0001\u0003kA\u0001\"!\u001b\u0001\r\u0003\u0011\u00121N\u0004\b\u0003{\u0012\u0002\u0012AA@\r\u0019\t\"\u0003#\u0001\u0002\u0002\"1a\n\u0004C\u0001\u00033Cq!a'\r\t\u0003\ti\n\u0003\u0005\u0002F2!\tAEAd\u0011%\t\u0019\u0010DA\u0001\n\u0013\t)PA\u0004FI\u001e,'\u000b\u0012#\u000b\u0005M!\u0012AB4sCBD\u0007P\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h\u0007\u0001)\"\u0001H\u0015\u0014\u0005\u0001i\u0002c\u0001\u0010\"G5\tqD\u0003\u0002!)\u0005\u0019!\u000f\u001a3\n\u0005\tz\"a\u0001*E\tB\u0019A%J\u0014\u000e\u0003II!A\n\n\u0003\t\u0015#w-\u001a\t\u0003Q%b\u0001\u0001B\u0003+\u0001\t\u00071F\u0001\u0002F\tF\u0011AF\r\t\u0003[Aj\u0011A\f\u0006\u0002_\u0005)1oY1mC&\u0011\u0011G\f\u0002\b\u001d>$\b.\u001b8h!\ti3'\u0003\u00025]\t\u0019\u0011I\\=\u0002\u0005M\u001c\u0007CA\u001c9\u001b\u0005!\u0012BA\u001d\u0015\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0003\u0011!W\r]:\u0011\u0007q\"uI\u0004\u0002>\u0005:\u0011a(Q\u0007\u0002\u007f)\u0011\u0001IG\u0001\u0007yI|w\u000e\u001e \n\u0003=J!a\u0011\u0018\u0002\u000fA\f7m[1hK&\u0011QI\u0012\u0002\u0004'\u0016\f(BA\"/a\tAE\nE\u00028\u0013.K!A\u0013\u000b\u0003\u0015\u0011+\u0007/\u001a8eK:\u001c\u0017\u0010\u0005\u0002)\u0019\u0012IQJAA\u0001\u0002\u0003\u0015\ta\u000b\u0002\u0004?\u0012\n\u0014A\u0002\u001fj]&$h\bF\u0002Q#J\u00032\u0001\n\u0001(\u0011\u0015)4\u00011\u00017\u0011\u0015Q4\u00011\u0001T!\raD\t\u0016\u0019\u0003+^\u00032aN%W!\tAs\u000bB\u0005N%\u0006\u0005\t\u0011!B\u0001W\u0005i\u0001/\u0019:uSRLwN\\:S\t\u0012+\u0012A\u0017\u0019\u000372\u00042AH\u0011]!\u0011iSlX3\n\u0005ys#A\u0002+va2,'\u0007\u0005\u0002aE:\u0011A%Y\u0005\u0003\u0007JI!a\u00193\u0003\u0017A\u000b'\u000f^5uS>t\u0017\n\u0012\u0006\u0003\u0007J\u0001BAZ5(W6\tqM\u0003\u0002i%\u0005!\u0011.\u001c9m\u0013\tQwMA\u0007FI\u001e,\u0007+\u0019:uSRLwN\u001c\t\u0003Q1$\u0011\"\u001c\u0003\u0002\u0002\u0003\u0005)\u0011A\u0016\u0003\u0005Y#\u0015!D4fiB\u000b'\u000f^5uS>t7/F\u0001q!\ri\u0013o]\u0005\u0003e:\u0012Q!\u0011:sCf\u0004\"a\u000e;\n\u0005U$\"!\u0003)beRLG/[8o\u0003\u001d\u0019w.\u001c9vi\u0016$2\u0001_>~!\ra\u0014pI\u0005\u0003u\u001a\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0005\u0006y\u001a\u0001\ra]\u0001\u0005a\u0006\u0014H\u000fC\u0003\u007f\r\u0001\u0007q0A\u0004d_:$X\r\u001f;\u0011\u0007]\n\t!C\u0002\u0002\u0004Q\u00111\u0002V1tW\u000e{g\u000e^3yi\u0006IQ.\u00199WC2,Xm]\u000b\u0005\u0003\u0013\t\t\u0002\u0006\u0003\u0002\f\u0005\u0015B\u0003BA\u0007\u0003+\u0001B\u0001\n\u0001\u0002\u0010A\u0019\u0001&!\u0005\u0005\r\u0005MqA1\u0001,\u0005\r)EI\r\u0005\n\u0003/9\u0011\u0011!a\u0002\u00033\t!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019\tY\"!\t\u0002\u00105\u0011\u0011Q\u0004\u0006\u0004\u0003?q\u0013a\u0002:fM2,7\r^\u0005\u0005\u0003G\tiB\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011\u001d\t9c\u0002a\u0001\u0003S\t\u0011A\u001a\t\u0007[\u0005-2%a\u0004\n\u0007\u00055bFA\u0005Gk:\u001cG/[8oc\u00059!/\u001a<feN,W#\u0001)\u0002\u0013%tg.\u001a:K_&tWCBA\u001c\u0003\u001b\n\t\u0005\u0006\u0003\u0002:\u0005\rD\u0003BA\u001e\u0003+\"b!!\u0010\u0002F\u0005=\u0003\u0003\u0002\u0013\u0001\u0003\u007f\u00012\u0001KA!\t\u0019\t\u0019%\u0003b\u0001W\t\u0019Q\tR\u001a\t\u0013\u0005\u001d\u0013\"!AA\u0004\u0005%\u0013AC3wS\u0012,gnY3%eA1\u00111DA\u0011\u0003\u0017\u00022\u0001KA'\t\u0019\t\u0019\"\u0003b\u0001W!I\u0011\u0011K\u0005\u0002\u0002\u0003\u000f\u00111K\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004CBA\u000e\u0003C\ty\u0004C\u0004\u0002(%\u0001\r!a\u0016\u0011\u00195\nI&!\u0018\u0002^\u001d\nY%a\u0010\n\u0007\u0005mcFA\u0005Gk:\u001cG/[8oiA\u0019\u0001-a\u0018\n\u0007\u0005\u0005DM\u0001\u0005WKJ$X\r_%e\u0011\u001d\t)'\u0003a\u0001\u0003O\nQa\u001c;iKJ\u0004B\u0001\n\u0001\u0002L\u00051r/\u001b;i)\u0006\u0014x-\u001a;Ti>\u0014\u0018mZ3MKZ,G\u000eF\u0002Q\u0003[Bq!a\u001c\u000b\u0001\u0004\t\t(\u0001\nuCJ<W\r^*u_J\fw-\u001a'fm\u0016d\u0007\u0003BA:\u0003sj!!!\u001e\u000b\u0007\u0005]D#A\u0004ti>\u0014\u0018mZ3\n\t\u0005m\u0014Q\u000f\u0002\r'R|'/Y4f\u0019\u00164X\r\\\u0001\b\u000b\u0012<WM\u0015#E!\t!CbE\u0003\r\u0003\u0007\u000bI\tE\u0002.\u0003\u000bK1!a\"/\u0005\u0019\te.\u001f*fMB!\u00111RAK\u001b\t\tiI\u0003\u0003\u0002\u0010\u0006E\u0015AA5p\u0015\t\t\u0019*\u0001\u0003kCZ\f\u0017\u0002BAL\u0003\u001b\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\"!a \u0002\u0013\u0019\u0014x.\\#eO\u0016\u001cXCBAP\u0003W\u000by\u000b\u0006\u0003\u0002\"\u0006uFCBAR\u0003c\u000b9\fE\u0004g\u0003K\u000bI+!,\n\u0007\u0005\u001dvMA\u0006FI\u001e,'\u000b\u0012#J[Bd\u0007c\u0001\u0015\u0002,\u0012)!F\u0004b\u0001WA\u0019\u0001&a,\u0005\u000b5t!\u0019A\u0016\t\u0013\u0005Mf\"!AA\u0004\u0005U\u0016AC3wS\u0012,gnY3%iA1\u00111DA\u0011\u0003SC\u0011\"!/\u000f\u0003\u0003\u0005\u001d!a/\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007\u0005\u0004\u0002\u001c\u0005\u0005\u0012Q\u0016\u0005\b\u0003\u007fs\u0001\u0019AAa\u0003\u0015)GmZ3t!\u0011q\u0012%a1\u0011\t\u0011*\u0013\u0011V\u0001\u0013MJ|W.\u00123hKB\u000b'\u000f^5uS>t7/\u0006\u0004\u0002J\u0006E\u0017Q\u001b\u000b\u0005\u0003\u0017\f\u0019\u000f\u0006\u0004\u0002N\u0006]\u0017Q\u001c\t\bM\u0006\u0015\u0016qZAj!\rA\u0013\u0011\u001b\u0003\u0006U=\u0011\ra\u000b\t\u0004Q\u0005UG!B7\u0010\u0005\u0004Y\u0003\"CAm\u001f\u0005\u0005\t9AAn\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0007\u00037\t\t#a4\t\u0013\u0005}w\"!AA\u0004\u0005\u0005\u0018AC3wS\u0012,gnY3%oA1\u00111DA\u0011\u0003'Dq!!:\u0010\u0001\u0004\t9/\u0001\bfI\u001e,\u0007+\u0019:uSRLwN\\:\u0011\ty\t\u0013\u0011\u001e\t\u0007[u\u000bY/!=\u0011\u00075\ni/C\u0002\u0002p:\u00121!\u00138u!\u00191\u0017.a4\u0002T\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u001f\t\u0005\u0003s\fy0\u0004\u0002\u0002|*!\u0011Q`AI\u0003\u0011a\u0017M\\4\n\t\t\u0005\u00111 \u0002\u0007\u001f\nTWm\u0019;"
)
public abstract class EdgeRDD extends RDD {
   public static EdgeRDDImpl fromEdges(final RDD edges, final ClassTag evidence$4, final ClassTag evidence$5) {
      return EdgeRDD$.MODULE$.fromEdges(edges, evidence$4, evidence$5);
   }

   public abstract RDD partitionsRDD();

   public Partition[] getPartitions() {
      return this.partitionsRDD().partitions();
   }

   public Iterator compute(final Partition part, final TaskContext context) {
      Iterator p = this.firstParent(.MODULE$.apply(Tuple2.class)).iterator(part, context);
      return p.hasNext() ? ((EdgePartition)((Tuple2)p.next())._2()).iterator().map((x$1) -> x$1.copy(x$1.copy$default$1(), x$1.copy$default$2(), x$1.copy$default$3())) : scala.package..MODULE$.Iterator().empty();
   }

   public abstract EdgeRDD mapValues(final Function1 f, final ClassTag evidence$1);

   public abstract EdgeRDD reverse();

   public abstract EdgeRDD innerJoin(final EdgeRDD other, final Function4 f, final ClassTag evidence$2, final ClassTag evidence$3);

   public abstract EdgeRDD withTargetStorageLevel(final StorageLevel targetStorageLevel);

   public EdgeRDD(final SparkContext sc, final Seq deps) {
      super(sc, deps, .MODULE$.apply(Edge.class));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
