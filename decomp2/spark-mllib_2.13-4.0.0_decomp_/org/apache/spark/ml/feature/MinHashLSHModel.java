package org.apache.spark.ml.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.Params;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t%e\u0001B\u001a5\u0001}B\u0001\"\u0012\u0001\u0003\u0006\u0004%\tE\u0012\u0005\t)\u0002\u0011\t\u0011)A\u0005\u000f\"IQ\u000b\u0001BC\u0002\u0013\u0005aG\u0016\u0005\tC\u0002\u0011\t\u0011)A\u0005/\"1!\r\u0001C\u0001m\rDaA\u0019\u0001\u0005\u0002Y2\u0007\"B4\u0001\t\u0003B\u0007\"B;\u0001\t\u00032\bBB=\u0001\t#2$\u0010\u0003\u0005\u0002\u0010\u0001!\tFNA\t\u0011!\t\u0019\u0003\u0001C)m\u0005\u0015\u0002bBA\u0017\u0001\u0011\u0005\u0013q\u0006\u0005\b\u0003\u0007\u0002A\u0011IA#\u0011\u001d\t)\u0006\u0001C!\u0003/:q!!\u00195\u0011\u0003\t\u0019G\u0002\u00044i!\u0005\u0011Q\r\u0005\u0007EB!\t!a!\t\u000f\u0005\u0015\u0005\u0003\"\u0011\u0002\b\"9\u0011\u0011\u0013\t\u0005B\u0005MeaBAN!\u0001\u0001\u0012Q\u0014\u0005\n\u0003?#\"\u0011!Q\u0001\n\u0011CaA\u0019\u000b\u0005\u0002\u0005\u0005fABAU)\u0011\u000bY\u000bC\u0005V/\tU\r\u0011\"\u0001\u0002D\"I\u0011m\u0006B\tB\u0003%\u0011Q\u0019\u0005\u0007E^!\t!a2\t\u0013\u00055r#!A\u0005\u0002\u0005=\u0007\"CAj/E\u0005I\u0011AAk\u0011%\tIoFA\u0001\n\u0003\nY\u000fC\u0005\u0002x^\t\t\u0011\"\u0001\u0002z\"I\u00111`\f\u0002\u0002\u0013\u0005\u0011Q \u0005\n\u0005\u00139\u0012\u0011!C!\u0005\u0017A\u0011B!\u0007\u0018\u0003\u0003%\tAa\u0007\t\u0013\t\u0015r#!A\u0005B\t\u001d\u0002\"\u0003B\u0016/\u0005\u0005I\u0011\tB\u0017\u0011%\t)fFA\u0001\n\u0003\u0012y\u0003C\u0005\u00032]\t\t\u0011\"\u0011\u00034\u001dI!q\u0007\u000b\u0002\u0002#%!\u0011\b\u0004\n\u0003S#\u0012\u0011!E\u0005\u0005wAaAY\u0014\u0005\u0002\t%\u0003\"CA+O\u0005\u0005IQ\tB\u0018\u0011%\u0011YeJA\u0001\n\u0003\u0013i\u0005C\u0005\u0003R\u001d\n\t\u0011\"!\u0003T!9!q\f\u000b\u0005R\t\u0005dA\u0002B6!\u0011\u0011i\u0007\u0003\u0004c[\u0011\u0005!q\u000e\u0005\n\u0005gj#\u0019!C\u0005\u0003WD\u0001B!\u001e.A\u0003%\u0011Q\u001e\u0005\b\u0003#kC\u0011\tB<\u0011%\u0011Y\bEA\u0001\n\u0013\u0011iHA\bNS:D\u0015m\u001d5M'\"ku\u000eZ3m\u0015\t)d'A\u0004gK\u0006$XO]3\u000b\u0005]B\u0014AA7m\u0015\tI$(A\u0003ta\u0006\u00148N\u0003\u0002<y\u00051\u0011\r]1dQ\u0016T\u0011!P\u0001\u0004_J<7\u0001A\n\u0003\u0001\u0001\u00032!\u0011\"E\u001b\u0005!\u0014BA\"5\u0005!a5\u000bS'pI\u0016d\u0007CA!\u0001\u0003\r)\u0018\u000eZ\u000b\u0002\u000fB\u0011\u0001*\u0015\b\u0003\u0013>\u0003\"AS'\u000e\u0003-S!\u0001\u0014 \u0002\rq\u0012xn\u001c;?\u0015\u0005q\u0015!B:dC2\f\u0017B\u0001)N\u0003\u0019\u0001&/\u001a3fM&\u0011!k\u0015\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Ak\u0015\u0001B;jI\u0002\n\u0001C]1oI\u000e{WM\u001a4jG&,g\u000e^:\u0016\u0003]\u00032\u0001W-\\\u001b\u0005i\u0015B\u0001.N\u0005\u0015\t%O]1z!\u0011AFL\u00180\n\u0005uk%A\u0002+va2,'\u0007\u0005\u0002Y?&\u0011\u0001-\u0014\u0002\u0004\u0013:$\u0018!\u0005:b]\u0012\u001cu.\u001a4gS\u000eLWM\u001c;tA\u00051A(\u001b8jiz\"2\u0001\u00123f\u0011\u0015)U\u00011\u0001H\u0011\u0015)V\u00011\u0001X)\u0005!\u0015aC:fi&s\u0007/\u001e;D_2$\"!\u001b6\u000e\u0003\u0001AQa[\u0004A\u0002\u001d\u000bQA^1mk\u0016D3aB7t!\tq\u0017/D\u0001p\u0015\t\u0001\b(\u0001\u0006b]:|G/\u0019;j_:L!A]8\u0003\u000bMKgnY3\"\u0003Q\fQA\r\u00185]A\nAb]3u\u001fV$\b/\u001e;D_2$\"![<\t\u000b-D\u0001\u0019A$)\u0007!i7/\u0001\u0007iCNDg)\u001e8di&|g\u000eF\u0002|\u0003\u000b\u00012\u0001W-}!\ri\u0018\u0011A\u0007\u0002}*\u0011qPN\u0001\u0007Y&t\u0017\r\\4\n\u0007\u0005\raP\u0001\u0004WK\u000e$xN\u001d\u0005\u0007\u0003\u000fI\u0001\u0019\u0001?\u0002\u000b\u0015dW-\\:)\t%i\u00171B\u0011\u0003\u0003\u001b\tQA\r\u00182]A\n1b[3z\t&\u001cH/\u00198dKR1\u00111CA\r\u0003;\u00012\u0001WA\u000b\u0013\r\t9\"\u0014\u0002\u0007\t>,(\r\\3\t\r\u0005m!\u00021\u0001}\u0003\u0005A\bBBA\u0010\u0015\u0001\u0007A0A\u0001zQ\u0011QQ.a\u0003\u0002\u0019!\f7\u000f\u001b#jgR\fgnY3\u0015\r\u0005M\u0011qEA\u0015\u0011\u0019\tYb\u0003a\u0001w\"1\u0011qD\u0006A\u0002mDCaC7\u0002\f\u0005!1m\u001c9z)\r!\u0015\u0011\u0007\u0005\b\u0003ga\u0001\u0019AA\u001b\u0003\u0015)\u0007\u0010\u001e:b!\u0011\t9$!\u0010\u000e\u0005\u0005e\"bAA\u001em\u0005)\u0001/\u0019:b[&!\u0011qHA\u001d\u0005!\u0001\u0016M]1n\u001b\u0006\u0004\b\u0006\u0002\u0007n\u0003\u0017\tQa\u001e:ji\u0016,\"!a\u0012\u0011\t\u0005%\u0013qJ\u0007\u0003\u0003\u0017R1!!\u00147\u0003\u0011)H/\u001b7\n\t\u0005E\u00131\n\u0002\t\u001b2;&/\u001b;fe\"\"Q\"\\A\u0006\u0003!!xn\u0015;sS:<G#A$)\t9i\u00171L\u0011\u0003\u0003;\nQa\r\u00181]ABC\u0001A7\u0002\f\u0005yQ*\u001b8ICNDGj\u0015%N_\u0012,G\u000e\u0005\u0002B!M9\u0001#a\u001a\u0002n\u0005M\u0004c\u0001-\u0002j%\u0019\u00111N'\u0003\r\u0005s\u0017PU3g!\u0015\tI%a\u001cE\u0013\u0011\t\t(a\u0013\u0003\u00155c%+Z1eC\ndW\r\u0005\u0003\u0002v\u0005}TBAA<\u0015\u0011\tI(a\u001f\u0002\u0005%|'BAA?\u0003\u0011Q\u0017M^1\n\t\u0005\u0005\u0015q\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003G\nAA]3bIV\u0011\u0011\u0011\u0012\t\u0006\u0003\u0013\nY\tR\u0005\u0005\u0003\u001b\u000bYE\u0001\u0005N\u0019J+\u0017\rZ3sQ\u0011\u0011R.a\u0003\u0002\t1|\u0017\r\u001a\u000b\u0004\t\u0006U\u0005BBAL'\u0001\u0007q)\u0001\u0003qCRD\u0007\u0006B\nn\u0003\u0017\u0011Q#T5o\u0011\u0006\u001c\b\u000eT*I\u001b>$W\r\\,sSR,'oE\u0002\u0015\u0003\u000f\n\u0001\"\u001b8ti\u0006t7-\u001a\u000b\u0005\u0003G\u000b9\u000bE\u0002\u0002&Ri\u0011\u0001\u0005\u0005\u0007\u0003?3\u0002\u0019\u0001#\u0003\t\u0011\u000bG/Y\n\b/\u0005\u001d\u0014QVAZ!\rA\u0016qV\u0005\u0004\u0003ck%a\u0002)s_\u0012,8\r\u001e\t\u0005\u0003k\u000byL\u0004\u0003\u00028\u0006mfb\u0001&\u0002:&\ta*C\u0002\u0002>6\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u0002\u0006\u0005'bAA_\u001bV\u0011\u0011Q\u0019\t\u00041fsF\u0003BAe\u0003\u001b\u00042!a3\u0018\u001b\u0005!\u0002BB+\u001b\u0001\u0004\t)\r\u0006\u0003\u0002J\u0006E\u0007\u0002C+\u001c!\u0003\u0005\r!!2\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011q\u001b\u0016\u0005\u0003\u000b\fIn\u000b\u0002\u0002\\B!\u0011Q\\As\u001b\t\tyN\u0003\u0003\u0002b\u0006\r\u0018!C;oG\",7m[3e\u0015\t\u0001X*\u0003\u0003\u0002h\u0006}'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!<\u0011\t\u0005=\u0018Q_\u0007\u0003\u0003cTA!a=\u0002|\u0005!A.\u00198h\u0013\r\u0011\u0016\u0011_\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002=\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\u0000\u0005\u000b\u00012\u0001\u0017B\u0001\u0013\r\u0011\u0019!\u0014\u0002\u0004\u0003:L\b\u0002\u0003B\u0004?\u0005\u0005\t\u0019\u00010\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011i\u0001\u0005\u0004\u0003\u0010\tU\u0011q`\u0007\u0003\u0005#Q1Aa\u0005N\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005/\u0011\tB\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003\u0002B\u000f\u0005G\u00012\u0001\u0017B\u0010\u0013\r\u0011\t#\u0014\u0002\b\u0005>|G.Z1o\u0011%\u00119!IA\u0001\u0002\u0004\ty0\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BAw\u0005SA\u0001Ba\u0002#\u0003\u0003\u0005\rAX\u0001\tQ\u0006\u001c\bnQ8eKR\ta\f\u0006\u0002\u0002n\u00061Q-];bYN$BA!\b\u00036!I!qA\u0013\u0002\u0002\u0003\u0007\u0011q`\u0001\u0005\t\u0006$\u0018\rE\u0002\u0002L\u001e\u001aRa\nB\u001f\u0003g\u0002\u0002Ba\u0010\u0003F\u0005\u0015\u0017\u0011Z\u0007\u0003\u0005\u0003R1Aa\u0011N\u0003\u001d\u0011XO\u001c;j[\u0016LAAa\u0012\u0003B\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0015\u0005\te\u0012!B1qa2LH\u0003BAe\u0005\u001fBa!\u0016\u0016A\u0002\u0005\u0015\u0017aB;oCB\u0004H.\u001f\u000b\u0005\u0005+\u0012Y\u0006E\u0003Y\u0005/\n)-C\u0002\u0003Z5\u0013aa\u00149uS>t\u0007\"\u0003B/W\u0005\u0005\t\u0019AAe\u0003\rAH\u0005M\u0001\tg\u00064X-S7qYR!!1\rB5!\rA&QM\u0005\u0004\u0005Oj%\u0001B+oSRDa!a&-\u0001\u00049%!F'j]\"\u000b7\u000f\u001b'T\u00116{G-\u001a7SK\u0006$WM]\n\u0004[\u0005%EC\u0001B9!\r\t)+L\u0001\nG2\f7o\u001d(b[\u0016\f!b\u00197bgNt\u0015-\\3!)\r!%\u0011\u0010\u0005\u0007\u0003/\u000b\u0004\u0019A$\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t}\u0004\u0003BAx\u0005\u0003KAAa!\u0002r\n1qJ\u00196fGRDC\u0001E7\u0002\f!\"q\"\\A\u0006\u0001"
)
public class MinHashLSHModel extends LSHModel {
   private final String uid;
   private final Tuple2[] randCoefficients;

   public static MinHashLSHModel load(final String path) {
      return MinHashLSHModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return MinHashLSHModel$.MODULE$.read();
   }

   public String uid() {
      return this.uid;
   }

   public Tuple2[] randCoefficients() {
      return this.randCoefficients;
   }

   public MinHashLSHModel setInputCol(final String value) {
      return (MinHashLSHModel)Params.set$(this, (Param)this.inputCol(), value);
   }

   public MinHashLSHModel setOutputCol(final String value) {
      return (MinHashLSHModel)Params.set$(this, (Param)this.outputCol(), value);
   }

   public Vector[] hashFunction(final Vector elems) {
      .MODULE$.require(elems.nonZeroIterator().nonEmpty(), () -> "Must have at least 1 non zero entry.");
      double[] hashValues = (double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])this.randCoefficients()), (x0$1) -> BoxesRunTime.boxToDouble($anonfun$hashFunction$2(elems, x0$1)), scala.reflect.ClassTag..MODULE$.Double());
      return (Vector[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.doubleArrayOps(hashValues), (x$1) -> $anonfun$hashFunction$4(BoxesRunTime.unboxToDouble(x$1)), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   public double keyDistance(final Vector x, final Vector y) {
      Iterator xIter = x.nonZeroIterator().map((x$2) -> BoxesRunTime.boxToInteger($anonfun$keyDistance$1(x$2)));
      Iterator yIter = y.nonZeroIterator().map((x$3) -> BoxesRunTime.boxToInteger($anonfun$keyDistance$2(x$3)));
      if (xIter.isEmpty()) {
         .MODULE$.require(yIter.hasNext(), () -> "The union of two input sets must have at least 1 elements");
         return (double)1.0F;
      } else if (yIter.isEmpty()) {
         return (double)1.0F;
      } else {
         int xIndex = BoxesRunTime.unboxToInt(xIter.next());
         int yIndex = BoxesRunTime.unboxToInt(yIter.next());
         int xSize = 1;
         int ySize = 1;
         int intersectionSize = 0;

         while(xIndex != -1 && yIndex != -1) {
            if (xIndex == yIndex) {
               ++intersectionSize;
               int var10000;
               if (xIter.hasNext()) {
                  ++xSize;
                  var10000 = BoxesRunTime.unboxToInt(xIter.next());
               } else {
                  var10000 = -1;
               }

               xIndex = var10000;
               if (yIter.hasNext()) {
                  ++ySize;
                  var10000 = BoxesRunTime.unboxToInt(yIter.next());
               } else {
                  var10000 = -1;
               }

               yIndex = var10000;
            } else if (xIndex > yIndex) {
               int var14;
               if (yIter.hasNext()) {
                  ++ySize;
                  var14 = BoxesRunTime.unboxToInt(yIter.next());
               } else {
                  var14 = -1;
               }

               yIndex = var14;
            } else {
               int var15;
               if (xIter.hasNext()) {
                  ++xSize;
                  var15 = BoxesRunTime.unboxToInt(xIter.next());
               } else {
                  var15 = -1;
               }

               xIndex = var15;
            }
         }

         xSize += xIter.size();
         ySize += yIter.size();
         int unionSize = xSize + ySize - intersectionSize;
         .MODULE$.require(unionSize > 0, () -> "The union of two input sets must have at least 1 elements");
         return (double)1 - (double)intersectionSize / (double)unionSize;
      }
   }

   public double hashDistance(final Vector[] x, final Vector[] y) {
      int distance = Integer.MAX_VALUE;

      for(int i = 0; i < x.length; ++i) {
         double[] vx = x[i].toArray();
         double[] vy = y[i].toArray();
         int j = 0;

         int d;
         for(d = 0; j < vx.length && d < distance; ++j) {
            if (vx[j] != vy[j]) {
               ++d;
            }
         }

         if (d == 0) {
            return (double)0.0F;
         }

         if (d < distance) {
            distance = d;
         }
      }

      return (double)distance;
   }

   public MinHashLSHModel copy(final ParamMap extra) {
      MinHashLSHModel copied = (MinHashLSHModel)(new MinHashLSHModel(this.uid(), this.randCoefficients())).setParent(this.parent());
      return (MinHashLSHModel)this.copyValues(copied, extra);
   }

   public MLWriter write() {
      return new MinHashLSHModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "MinHashLSHModel: uid=" + var10000 + ", numHashTables=" + this.$(this.numHashTables());
   }

   // $FF: synthetic method
   public static final long $anonfun$hashFunction$3(final int a$1, final int b$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         int i = x0$2._1$mcI$sp();
         return ((1L + (long)i) * (long)a$1 + (long)b$1) % (long)MinHashLSH$.MODULE$.HASH_PRIME();
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$hashFunction$2(final Vector elems$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int a = x0$1._1$mcI$sp();
         int b = x0$1._2$mcI$sp();
         return (double)BoxesRunTime.unboxToLong(elems$1.nonZeroIterator().map((x0$2) -> BoxesRunTime.boxToLong($anonfun$hashFunction$3(a, b, x0$2))).min(scala.math.Ordering.Long..MODULE$));
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final Vector $anonfun$hashFunction$4(final double x$1) {
      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(x$1, scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   public static final int $anonfun$keyDistance$1(final Tuple2 x$2) {
      return x$2._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final int $anonfun$keyDistance$2(final Tuple2 x$3) {
      return x$3._1$mcI$sp();
   }

   public MinHashLSHModel(final String uid, final Tuple2[] randCoefficients) {
      this.uid = uid;
      this.randCoefficients = randCoefficients;
   }

   public MinHashLSHModel() {
      this("", (Tuple2[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class MinHashLSHModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final MinHashLSHModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data((int[])scala.collection.ArrayOps..MODULE$.flatMap$extension(.MODULE$.refArrayOps((Object[])this.instance.randCoefficients()), (tuple) -> new int[]{tuple._1$mcI$sp(), tuple._2$mcI$sp()}, (xs) -> .MODULE$.wrapIntArray(xs), scala.reflect.ClassTag..MODULE$.Int()));
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(MinHashLSHModelWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.MinHashLSHModel.MinHashLSHModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.MinHashLSHModel.MinHashLSHModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$1() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().parquet(dataPath);
      }

      private final void Data$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.Data$module == null) {
               this.Data$module = new Data$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public MinHashLSHModelWriter(final MinHashLSHModel instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }

      private class Data implements Product, Serializable {
         private final int[] randCoefficients;
         // $FF: synthetic field
         public final MinHashLSHModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public int[] randCoefficients() {
            return this.randCoefficients;
         }

         public Data copy(final int[] randCoefficients) {
            return this.org$apache$spark$ml$feature$MinHashLSHModel$MinHashLSHModelWriter$Data$$$outer().new Data(randCoefficients);
         }

         public int[] copy$default$1() {
            return this.randCoefficients();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 1;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return this.randCoefficients();
               }
               default -> {
                  return Statics.ioobe(x$1);
               }
            }
         }

         public Iterator productIterator() {
            return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
         }

         public boolean canEqual(final Object x$1) {
            return x$1 instanceof Data;
         }

         public String productElementName(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return "randCoefficients";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var10000;
            if (this != x$1) {
               label41: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$MinHashLSHModel$MinHashLSHModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$MinHashLSHModel$MinHashLSHModelWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.randCoefficients() == var4.randCoefficients() && var4.canEqual(this)) {
                        break label41;
                     }
                  }

                  var10000 = false;
                  return var10000;
               }
            }

            var10000 = true;
            return var10000;
         }

         // $FF: synthetic method
         public MinHashLSHModelWriter org$apache$spark$ml$feature$MinHashLSHModel$MinHashLSHModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final int[] randCoefficients) {
            this.randCoefficients = randCoefficients;
            if (MinHashLSHModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = MinHashLSHModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final MinHashLSHModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final int[] randCoefficients) {
            return this.$outer.new Data(randCoefficients);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.randCoefficients()));
         }

         public Data$() {
            if (MinHashLSHModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = MinHashLSHModelWriter.this;
               super();
            }
         }
      }
   }

   private static class MinHashLSHModelReader extends MLReader {
      private final String className = MinHashLSHModel.class.getName();

      private String className() {
         return this.className;
      }

      public MinHashLSHModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row data = (Row)this.sparkSession().read().parquet(dataPath).select("randCoefficients", scala.collection.immutable.Nil..MODULE$).head();
         Tuple2[] randCoefficients = (Tuple2[])data.getSeq(0).grouped(2).map((tuple) -> new Tuple2.mcII.sp(BoxesRunTime.unboxToInt(tuple.apply(0)), BoxesRunTime.unboxToInt(tuple.apply(1)))).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         MinHashLSHModel model = new MinHashLSHModel(metadata.uid(), randCoefficients);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public MinHashLSHModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
