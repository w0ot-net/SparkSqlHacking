package org.apache.spark.ml.evaluation;

import java.io.Serializable;
import org.apache.spark.SparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001dsA\u0002\u0013&\u0011\u0003)sF\u0002\u00042K!\u0005QE\r\u0005\u0006m\u0005!\t\u0001\u000f\u0005\u0007s\u0005\u0001\u000b\u0015\u0002\u001e\t\u000b\u0001\u000bA\u0011A!\u0007\t-\u000b\u0001\t\u0014\u0005\t?\u0016\u0011)\u001a!C\u0001A\"Aq-\u0002B\tB\u0003%\u0011\r\u0003\u0005i\u000b\tU\r\u0011\"\u0001j\u0011!iWA!E!\u0002\u0013Q\u0007\u0002\u00038\u0006\u0005+\u0007I\u0011A5\t\u0011=,!\u0011#Q\u0001\n)DQAN\u0003\u0005\u0002ADqA^\u0003\u0002\u0002\u0013\u0005q\u000fC\u0004|\u000bE\u0005I\u0011\u0001?\t\u0013\u0005=Q!%A\u0005\u0002\u0005E\u0001\"CA\u000b\u000bE\u0005I\u0011AA\t\u0011%\t9\"BA\u0001\n\u0003\nI\u0002C\u0005\u0002,\u0015\t\t\u0011\"\u0001\u0002.!I\u0011QG\u0003\u0002\u0002\u0013\u0005\u0011q\u0007\u0005\n\u0003\u0007*\u0011\u0011!C!\u0003\u000bB\u0011\"a\u0015\u0006\u0003\u0003%\t!!\u0016\t\u0013\u0005eS!!A\u0005B\u0005m\u0003\"CA0\u000b\u0005\u0005I\u0011IA1\u0011%\t\u0019'BA\u0001\n\u0003\n)\u0007C\u0005\u0002h\u0015\t\t\u0011\"\u0011\u0002j\u001dI\u0011QN\u0001\u0002\u0002#\u0005\u0011q\u000e\u0004\t\u0017\u0006\t\t\u0011#\u0001\u0002r!1ag\u0007C\u0001\u0003\u0013C\u0011\"a\u0019\u001c\u0003\u0003%)%!\u001a\t\u0013\u0005-5$!A\u0005\u0002\u00065\u0005\"CAK7\u0005\u0005I\u0011QAL\u0011%\tIkGA\u0001\n\u0013\tY\u000bC\u0004\u00024\u0006!\t!!.\t\u000f\u0005m\u0018\u0001\"\u0001\u0002~\"9!qD\u0001\u0005\u0002\t\u0005\u0012AG*rk\u0006\u0014X\rZ#vG2LG-Z1o'&d\u0007n\\;fiR,'B\u0001\u0014(\u0003))g/\u00197vCRLwN\u001c\u0006\u0003Q%\n!!\u001c7\u000b\u0005)Z\u0013!B:qCJ\\'B\u0001\u0017.\u0003\u0019\t\u0007/Y2iK*\ta&A\u0002pe\u001e\u0004\"\u0001M\u0001\u000e\u0003\u0015\u0012!dU9vCJ,G-R;dY&$W-\u00198TS2Dw.^3ui\u0016\u001c\"!A\u001a\u0011\u0005A\"\u0014BA\u001b&\u0005)\u0019\u0016\u000e\u001c5pk\u0016$H/Z\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tq&A\rlef|'+Z4jgR\u0014\u0018\r^5p]B+'OZ8s[\u0016$\u0007CA\u001e?\u001b\u0005a$\"A\u001f\u0002\u000bM\u001c\u0017\r\\1\n\u0005}b$a\u0002\"p_2,\u0017M\\\u0001\u0014e\u0016<\u0017n\u001d;fe.\u0013\u0018p\\\"mCN\u001cXm\u001d\u000b\u0003\u0005\u0016\u0003\"aO\"\n\u0005\u0011c$\u0001B+oSRDQA\u0012\u0003A\u0002\u001d\u000b!a]2\u0011\u0005!KU\"A\u0015\n\u0005)K#\u0001D*qCJ\\7i\u001c8uKb$(\u0001D\"mkN$XM]*uCR\u001c8\u0003B\u0003N!N\u0003\"a\u000f(\n\u0005=c$AB!osJ+g\r\u0005\u0002<#&\u0011!\u000b\u0010\u0002\b!J|G-^2u!\t!FL\u0004\u0002V5:\u0011a+W\u0007\u0002/*\u0011\u0001lN\u0001\u0007yI|w\u000e\u001e \n\u0003uJ!a\u0017\u001f\u0002\u000fA\f7m[1hK&\u0011QL\u0018\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u00037r\n!BZ3biV\u0014XmU;n+\u0005\t\u0007C\u00012f\u001b\u0005\u0019'B\u00013(\u0003\u0019a\u0017N\\1mO&\u0011am\u0019\u0002\u0007-\u0016\u001cGo\u001c:\u0002\u0017\u0019,\u0017\r^;sKN+X\u000eI\u0001\u000fgF,\u0018M]3e\u001d>\u0014XnU;n+\u0005Q\u0007CA\u001el\u0013\taGH\u0001\u0004E_V\u0014G.Z\u0001\u0010gF,\u0018M]3e\u001d>\u0014XnU;nA\u0005Iq/Z5hQR\u001cV/\\\u0001\u000bo\u0016Lw\r\u001b;Tk6\u0004C\u0003B9tiV\u0004\"A]\u0003\u000e\u0003\u0005AQa\u0018\u0007A\u0002\u0005DQ\u0001\u001b\u0007A\u0002)DQA\u001c\u0007A\u0002)\fAaY8qsR!\u0011\u000f_={\u0011\u001dyV\u0002%AA\u0002\u0005Dq\u0001[\u0007\u0011\u0002\u0003\u0007!\u000eC\u0004o\u001bA\u0005\t\u0019\u00016\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tQP\u000b\u0002b}.\nq\u0010\u0005\u0003\u0002\u0002\u0005-QBAA\u0002\u0015\u0011\t)!a\u0002\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0005y\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u00055\u00111\u0001\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003'Q#A\u001b@\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!a\u0007\u0011\t\u0005u\u0011qE\u0007\u0003\u0003?QA!!\t\u0002$\u0005!A.\u00198h\u0015\t\t)#\u0001\u0003kCZ\f\u0017\u0002BA\u0015\u0003?\u0011aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u0018!\rY\u0014\u0011G\u0005\u0004\u0003ga$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\u001d\u0003\u007f\u00012aOA\u001e\u0013\r\ti\u0004\u0010\u0002\u0004\u0003:L\b\"CA!'\u0005\u0005\t\u0019AA\u0018\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\t\t\u0007\u0003\u0013\ny%!\u000f\u000e\u0005\u0005-#bAA'y\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005E\u00131\n\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000eF\u0002;\u0003/B\u0011\"!\u0011\u0016\u0003\u0003\u0005\r!!\u000f\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u00037\ti\u0006C\u0005\u0002BY\t\t\u00111\u0001\u00020\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u00020\u0005AAo\\*ue&tw\r\u0006\u0002\u0002\u001c\u00051Q-];bYN$2AOA6\u0011%\t\t%GA\u0001\u0002\u0004\tI$\u0001\u0007DYV\u001cH/\u001a:Ti\u0006$8\u000f\u0005\u0002s7M)1$a\u001d\u0002\u0000AA\u0011QOA>C*T\u0017/\u0004\u0002\u0002x)\u0019\u0011\u0011\u0010\u001f\u0002\u000fI,h\u000e^5nK&!\u0011QPA<\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u0003\u0003\u000b9)\u0004\u0002\u0002\u0004*!\u0011QQA\u0012\u0003\tIw.C\u0002^\u0003\u0007#\"!a\u001c\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000fE\fy)!%\u0002\u0014\")qL\ba\u0001C\")\u0001N\ba\u0001U\")aN\ba\u0001U\u00069QO\\1qa2LH\u0003BAM\u0003K\u0003RaOAN\u0003?K1!!(=\u0005\u0019y\u0005\u000f^5p]B11(!)bU*L1!a)=\u0005\u0019!V\u000f\u001d7fg!A\u0011qU\u0010\u0002\u0002\u0003\u0007\u0011/A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!,\u0011\t\u0005u\u0011qV\u0005\u0005\u0003c\u000byB\u0001\u0004PE*,7\r^\u0001\u0014G>l\u0007/\u001e;f\u00072,8\u000f^3s'R\fGo\u001d\u000b\u000b\u0003o\u000b9-a;\u0002t\u0006]\bCBA]\u0003\u0003T\u0017O\u0004\u0003\u0002<\u0006u\u0006C\u0001,=\u0013\r\ty\fP\u0001\u0007!J,G-\u001a4\n\t\u0005\r\u0017Q\u0019\u0002\u0004\u001b\u0006\u0004(bAA`y!9\u0011\u0011Z\u0011A\u0002\u0005-\u0017A\u00013g!\u0011\ti-!:\u000f\t\u0005=\u0017\u0011\u001d\b\u0005\u0003#\fiN\u0004\u0003\u0002T\u0006mg\u0002BAk\u00033t1AVAl\u0013\u0005q\u0013B\u0001\u0017.\u0013\tQ3&C\u0002\u0002`&\n1a]9m\u0013\rY\u00161\u001d\u0006\u0004\u0003?L\u0013\u0002BAt\u0003S\u0014\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u0007m\u000b\u0019\u000fC\u0004\u0002n\u0006\u0002\r!a<\u0002\u001bA\u0014X\rZ5di&|gnQ8m!\u0011\tI,!=\n\t\u0005%\u0012Q\u0019\u0005\b\u0003k\f\u0003\u0019AAx\u0003-1W-\u0019;ve\u0016\u001c8i\u001c7\t\u000f\u0005e\u0018\u00051\u0001\u0002p\u0006Iq/Z5hQR\u001cu\u000e\\\u0001\u001dG>l\u0007/\u001e;f'&d\u0007n\\;fiR,7i\\3gM&\u001c\u0017.\u001a8u)-Q\u0017q B\b\u0005'\u00119Ba\u0007\t\u000f\t\u0005!\u00051\u0001\u0003\u0004\u00051\"M]8bI\u000e\f7\u000f^3e\u00072,8\u000f^3sg6\u000b\u0007\u000f\u0005\u0004\u0003\u0006\t-\u0011qW\u0007\u0003\u0005\u000fQ1A!\u0003*\u0003%\u0011'o\\1eG\u0006\u001cH/\u0003\u0003\u0003\u000e\t\u001d!!\u0003\"s_\u0006$7-Y:u\u0011\u0019\u0011\tB\ta\u0001C\u0006)\u0001o\\5oi\"1!Q\u0003\u0012A\u0002)\f\u0011b\u00197vgR,'/\u00133\t\r\te!\u00051\u0001k\u0003\u00199X-[4ii\"1!Q\u0004\u0012A\u0002)\f1b]9vCJ,GMT8s[\u000612m\\7qkR,7+\u001b7i_V,G\u000f^3TG>\u0014X\rF\u0005k\u0005G\u0011\tEa\u0011\u0003F!9!QE\u0012A\u0002\t\u001d\u0012a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0005S\u0011)\u0004\u0005\u0004\u0003,\t5\"\u0011G\u0007\u0003\u0003GLAAa\f\u0002d\n9A)\u0019;bg\u0016$\b\u0003\u0002B\u001a\u0005ka\u0001\u0001\u0002\u0007\u00038\t\r\u0012\u0011!A\u0001\u0006\u0003\u0011IDA\u0002`II\nBAa\u000f\u0002:A\u00191H!\u0010\n\u0007\t}BHA\u0004O_RD\u0017N\\4\t\u000f\u000558\u00051\u0001\u0002p\"9\u0011Q_\u0012A\u0002\u0005=\bbBA}G\u0001\u0007\u0011q\u001e"
)
public final class SquaredEuclideanSilhouette {
   public static double computeSilhouetteScore(final Dataset dataset, final String predictionCol, final String featuresCol, final String weightCol) {
      return SquaredEuclideanSilhouette$.MODULE$.computeSilhouetteScore(dataset, predictionCol, featuresCol, weightCol);
   }

   public static double computeSilhouetteCoefficient(final Broadcast broadcastedClustersMap, final Vector point, final double clusterId, final double weight, final double squaredNorm) {
      return SquaredEuclideanSilhouette$.MODULE$.computeSilhouetteCoefficient(broadcastedClustersMap, point, clusterId, weight, squaredNorm);
   }

   public static Map computeClusterStats(final Dataset df, final String predictionCol, final String featuresCol, final String weightCol) {
      return SquaredEuclideanSilhouette$.MODULE$.computeClusterStats(df, predictionCol, featuresCol, weightCol);
   }

   public static void registerKryoClasses(final SparkContext sc) {
      SquaredEuclideanSilhouette$.MODULE$.registerKryoClasses(sc);
   }

   public static double overallScore(final Dataset df, final Column scoreColumn, final Column weightColumn) {
      return SquaredEuclideanSilhouette$.MODULE$.overallScore(df, scoreColumn, weightColumn);
   }

   public static double pointSilhouetteCoefficient(final Set clusterIds, final double pointClusterId, final double weightSum, final double weight, final Function1 averageDistanceToCluster) {
      return SquaredEuclideanSilhouette$.MODULE$.pointSilhouetteCoefficient(clusterIds, pointClusterId, weightSum, weight, averageDistanceToCluster);
   }

   public static class ClusterStats implements Product, Serializable {
      private final Vector featureSum;
      private final double squaredNormSum;
      private final double weightSum;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Vector featureSum() {
         return this.featureSum;
      }

      public double squaredNormSum() {
         return this.squaredNormSum;
      }

      public double weightSum() {
         return this.weightSum;
      }

      public ClusterStats copy(final Vector featureSum, final double squaredNormSum, final double weightSum) {
         return new ClusterStats(featureSum, squaredNormSum, weightSum);
      }

      public Vector copy$default$1() {
         return this.featureSum();
      }

      public double copy$default$2() {
         return this.squaredNormSum();
      }

      public double copy$default$3() {
         return this.weightSum();
      }

      public String productPrefix() {
         return "ClusterStats";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.featureSum();
            }
            case 1 -> {
               return BoxesRunTime.boxToDouble(this.squaredNormSum());
            }
            case 2 -> {
               return BoxesRunTime.boxToDouble(this.weightSum());
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ClusterStats;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "featureSum";
            }
            case 1 -> {
               return "squaredNormSum";
            }
            case 2 -> {
               return "weightSum";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.featureSum()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.squaredNormSum()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.weightSum()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label55: {
               if (x$1 instanceof ClusterStats) {
                  ClusterStats var4 = (ClusterStats)x$1;
                  if (this.squaredNormSum() == var4.squaredNormSum() && this.weightSum() == var4.weightSum()) {
                     label48: {
                        Vector var10000 = this.featureSum();
                        Vector var5 = var4.featureSum();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label48;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label48;
                        }

                        if (var4.canEqual(this)) {
                           break label55;
                        }
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      public ClusterStats(final Vector featureSum, final double squaredNormSum, final double weightSum) {
         this.featureSum = featureSum;
         this.squaredNormSum = squaredNormSum;
         this.weightSum = weightSum;
         Product.$init$(this);
      }
   }

   public static class ClusterStats$ extends AbstractFunction3 implements Serializable {
      public static final ClusterStats$ MODULE$ = new ClusterStats$();

      public final String toString() {
         return "ClusterStats";
      }

      public ClusterStats apply(final Vector featureSum, final double squaredNormSum, final double weightSum) {
         return new ClusterStats(featureSum, squaredNormSum, weightSum);
      }

      public Option unapply(final ClusterStats x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.featureSum(), BoxesRunTime.boxToDouble(x$0.squaredNormSum()), BoxesRunTime.boxToDouble(x$0.weightSum()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ClusterStats$.class);
      }
   }
}
