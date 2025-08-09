package org.apache.spark.ml.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.SparseMatrix;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.rdd.RDD;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t-b!\u0002\u0016,\u0001>*\u0004\u0002\u0003'\u0001\u0005+\u0007I\u0011A'\t\u0011Q\u0003!\u0011#Q\u0001\n9C\u0001\"\u0016\u0001\u0003\u0016\u0004%\t!\u0014\u0005\t-\u0002\u0011\t\u0012)A\u0005\u001d\"Aq\u000b\u0001BK\u0002\u0013\u0005\u0001\f\u0003\u0005`\u0001\tE\t\u0015!\u0003Z\u0011\u0015\u0001\u0007\u0001\"\u0001b\u0011\u00159\u0007\u0001\"\u0001i\u0011\u0015a\u0007\u0001\"\u0001i\u0011\u0015i\u0007\u0001\"\u0001o\u0011\u0015)\b\u0001\"\u0001w\u0011\u0015I\b\u0001\"\u0001{\u0011!a\b\u0001#b\u0001\n\u0003i\bBBA\u0006\u0001\u0011\u0005!\u0010\u0003\u0006\u0002\u000e\u0001A)\u0019!C\u0001\u0003\u001fA\u0011\"!\b\u0001\u0003\u0003%\t!a\b\t\u0013\u0005\u001d\u0002!%A\u0005\u0002\u0005%\u0002\"CA \u0001E\u0005I\u0011AA\u0015\u0011%\t\t\u0005AI\u0001\n\u0003\t\u0019\u0005C\u0005\u0002H\u0001\t\t\u0011\"\u0011\u0002J!A\u00111\f\u0001\u0002\u0002\u0013\u0005\u0001\u000eC\u0005\u0002^\u0001\t\t\u0011\"\u0001\u0002`!I\u00111\u000e\u0001\u0002\u0002\u0013\u0005\u0013Q\u000e\u0005\n\u0003s\u0002\u0011\u0011!C\u0001\u0003wB\u0011\"!\"\u0001\u0003\u0003%\t%a\"\t\u0013\u0005-\u0005!!A\u0005B\u00055\u0005\"CAH\u0001\u0005\u0005I\u0011IAI\u0011%\t\u0019\nAA\u0001\n\u0003\n)j\u0002\u0005\u0002\u001a.B\taLAN\r\u001dQ3\u0006#\u00010\u0003;Ca\u0001\u0019\u0010\u0005\u0002\u0005%\u0006\"CAV=\t\u0007I\u0011AAW\u0011\u001d\tyK\bQ\u0001\nECq!!-\u001f\t\u0013\t\u0019\fC\u0004\u0002Lz!\t!!4\t\u000f\u0005eg\u0004\"\u0001\u0002\\\"9\u0011\u0011\u001f\u0010\u0005\u0002\u0005M\bbBAy=\u0011\u0005\u0011Q \u0005\n\u0005\u0007q\u0012\u0011!CA\u0005\u000bA\u0011B!\u0004\u001f\u0003\u0003%\tIa\u0004\t\u0013\t\u0005b$!A\u0005\n\t\r\"!D%ogR\fgnY3CY>\u001c7N\u0003\u0002-[\u00059a-Z1ukJ,'B\u0001\u00180\u0003\tiGN\u0003\u00021c\u0005)1\u000f]1sW*\u0011!gM\u0001\u0007CB\f7\r[3\u000b\u0003Q\n1a\u001c:h'\u0011\u0001a\u0007P \u0011\u0005]RT\"\u0001\u001d\u000b\u0003e\nQa]2bY\u0006L!a\u000f\u001d\u0003\r\u0005s\u0017PU3g!\t9T(\u0003\u0002?q\t9\u0001K]8ek\u000e$\bC\u0001!J\u001d\t\tuI\u0004\u0002C\r6\t1I\u0003\u0002E\u000b\u00061AH]8piz\u001a\u0001!C\u0001:\u0013\tA\u0005(A\u0004qC\u000e\\\u0017mZ3\n\u0005)[%\u0001D*fe&\fG.\u001b>bE2,'B\u0001%9\u0003\u0019a\u0017MY3mgV\ta\nE\u00028\u001fFK!\u0001\u0015\u001d\u0003\u000b\u0005\u0013(/Y=\u0011\u0005]\u0012\u0016BA*9\u0005\u0019!u.\u001e2mK\u00069A.\u00192fYN\u0004\u0013aB<fS\u001eDGo]\u0001\to\u0016Lw\r\u001b;tA\u00051Q.\u0019;sSb,\u0012!\u0017\t\u00035vk\u0011a\u0017\u0006\u000396\na\u0001\\5oC2<\u0017B\u00010\\\u0005\u0019i\u0015\r\u001e:jq\u00069Q.\u0019;sSb\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003cI\u00164\u0007CA2\u0001\u001b\u0005Y\u0003\"\u0002'\b\u0001\u0004q\u0005\"B+\b\u0001\u0004q\u0005\"B,\b\u0001\u0004I\u0016\u0001B:ju\u0016,\u0012!\u001b\t\u0003o)L!a\u001b\u001d\u0003\u0007%sG/A\u0006ok64U-\u0019;ve\u0016\u001c\u0018\u0001E5ogR\fgnY3Ji\u0016\u0014\u0018\r^8s+\u0005y\u0007c\u0001!qe&\u0011\u0011o\u0013\u0002\t\u0013R,'/\u0019;peB\u00111m]\u0005\u0003i.\u0012\u0001\"\u00138ti\u0006t7-Z\u0001\tO\u0016$H*\u00192fYR\u0011\u0011k\u001e\u0005\u0006q.\u0001\r![\u0001\u0002S\u0006IA.\u00192fY&#XM]\u000b\u0002wB\u0019\u0001\t])\u0002\u0013\u001d,GoV3jO\"$X#\u0001@\u0011\t]z\u0018.U\u0005\u0004\u0003\u0003A$!\u0003$v]\u000e$\u0018n\u001c82Q\ri\u0011Q\u0001\t\u0004o\u0005\u001d\u0011bAA\u0005q\tIAO]1og&,g\u000e^\u0001\u000bo\u0016Lw\r\u001b;Ji\u0016\u0014\u0018AD4fi:{gNW3s_&#XM]\u000b\u0003\u0003#\u0001RaN@j\u0003'\u0001B\u0001\u00119\u0002\u0016A)q'a\u0006j#&\u0019\u0011\u0011\u0004\u001d\u0003\rQ+\b\u000f\\33Q\ry\u0011QA\u0001\u0005G>\u0004\u0018\u0010F\u0004c\u0003C\t\u0019#!\n\t\u000f1\u0003\u0002\u0013!a\u0001\u001d\"9Q\u000b\u0005I\u0001\u0002\u0004q\u0005bB,\u0011!\u0003\u0005\r!W\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tYCK\u0002O\u0003[Y#!a\f\u0011\t\u0005E\u00121H\u0007\u0003\u0003gQA!!\u000e\u00028\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003sA\u0014AC1o]>$\u0018\r^5p]&!\u0011QHA\u001a\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"!!\u0012+\u0007e\u000bi#A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003\u0017\u0002B!!\u0014\u0002X5\u0011\u0011q\n\u0006\u0005\u0003#\n\u0019&\u0001\u0003mC:<'BAA+\u0003\u0011Q\u0017M^1\n\t\u0005e\u0013q\n\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011MA4!\r9\u00141M\u0005\u0004\u0003KB$aA!os\"A\u0011\u0011\u000e\f\u0002\u0002\u0003\u0007\u0011.A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003_\u0002b!!\u001d\u0002x\u0005\u0005TBAA:\u0015\r\t)\bO\u0001\u000bG>dG.Z2uS>t\u0017bA9\u0002t\u0005A1-\u00198FcV\fG\u000e\u0006\u0003\u0002~\u0005\r\u0005cA\u001c\u0002\u0000%\u0019\u0011\u0011\u0011\u001d\u0003\u000f\t{w\u000e\\3b]\"I\u0011\u0011\u000e\r\u0002\u0002\u0003\u0007\u0011\u0011M\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002L\u0005%\u0005\u0002CA53\u0005\u0005\t\u0019A5\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012![\u0001\ti>\u001cFO]5oOR\u0011\u00111J\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005u\u0014q\u0013\u0005\n\u0003Sb\u0012\u0011!a\u0001\u0003C\nQ\"\u00138ti\u0006t7-\u001a\"m_\u000e\\\u0007CA2\u001f'\u0011qb'a(\u0011\t\u0005\u0005\u0016qU\u0007\u0003\u0003GSA!!*\u0002T\u0005\u0011\u0011n\\\u0005\u0004\u0015\u0006\rFCAAN\u0003Q!UMZ1vYR\u0014En\\2l'&TX-\u00138N\u0005V\t\u0011+A\u000bEK\u001a\fW\u000f\u001c;CY>\u001c7nU5{K&sWJ\u0011\u0011\u0002!\u001d,GO\u00117pG.lU-\\+tC\u001e,GCCA[\u0003w\u000by,a1\u0002HB\u0019q'a.\n\u0007\u0005e\u0006H\u0001\u0003M_:<\u0007bBA_E\u0001\u0007\u0011QW\u0001\b]Vl7i\u001c7t\u0011\u001d\t\tM\ta\u0001\u0003k\u000bqA\\;n%><8\u000fC\u0004\u0002F\n\u0002\r!!.\u0002\u00079t'\u0010C\u0004\u0002J\n\u0002\r!! \u0002\u001b\u0005dG.\u00168ji^+\u0017n\u001a5u\u000351'o\\7J]N$\u0018M\\2fgR\u0019!-a4\t\u000f\u0005E7\u00051\u0001\u0002T\u0006I\u0011N\\:uC:\u001cWm\u001d\t\u0005\u0001\u0006U'/C\u0002\u0002X.\u00131aU3r\u0003\u001d\u0011Gn\\6jMf$b!!8\u0002j\u00065\b#BAp\u0003K\u0014WBAAq\u0015\r\t\u0019oL\u0001\u0004e\u0012$\u0017\u0002BAt\u0003C\u00141A\u0015#E\u0011\u001d\t\t\u000e\na\u0001\u0003W\u0004R!a8\u0002fJDa!a<%\u0001\u0004I\u0017!\u00032m_\u000e\\7+\u001b>f\u0003Y\u0011Gn\\6jMf<\u0016\u000e\u001e5NCblU-\\+tC\u001e,GCBA{\u0003o\fI\u0010E\u0002Aa\nDQ!\\\u0013A\u0002=Dq!a?&\u0001\u0004\t),A\u0006nCblU-\\+tC\u001e,GCBAo\u0003\u007f\u0014\t\u0001C\u0004\u0002R\u001a\u0002\r!a;\t\u000f\u0005mh\u00051\u0001\u00026\u0006)\u0011\r\u001d9msR9!Ma\u0002\u0003\n\t-\u0001\"\u0002'(\u0001\u0004q\u0005\"B+(\u0001\u0004q\u0005\"B,(\u0001\u0004I\u0016aB;oCB\u0004H.\u001f\u000b\u0005\u0005#\u0011i\u0002E\u00038\u0005'\u00119\"C\u0002\u0003\u0016a\u0012aa\u00149uS>t\u0007CB\u001c\u0003\u001a9s\u0015,C\u0002\u0003\u001ca\u0012a\u0001V;qY\u0016\u001c\u0004\u0002\u0003B\u0010Q\u0005\u0005\t\u0019\u00012\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003&A!\u0011Q\nB\u0014\u0013\u0011\u0011I#a\u0014\u0003\r=\u0013'.Z2u\u0001"
)
public class InstanceBlock implements Product, Serializable {
   private transient Function1 getWeight;
   private transient Function1 getNonZeroIter;
   private final double[] labels;
   private final double[] weights;
   private final Matrix matrix;
   private transient volatile byte bitmap$trans$0;

   public static Option unapply(final InstanceBlock x$0) {
      return InstanceBlock$.MODULE$.unapply(x$0);
   }

   public static InstanceBlock apply(final double[] labels, final double[] weights, final Matrix matrix) {
      return InstanceBlock$.MODULE$.apply(labels, weights, matrix);
   }

   public static RDD blokifyWithMaxMemUsage(final RDD instances, final long maxMemUsage) {
      return InstanceBlock$.MODULE$.blokifyWithMaxMemUsage(instances, maxMemUsage);
   }

   public static Iterator blokifyWithMaxMemUsage(final Iterator instanceIterator, final long maxMemUsage) {
      return InstanceBlock$.MODULE$.blokifyWithMaxMemUsage(instanceIterator, maxMemUsage);
   }

   public static RDD blokify(final RDD instances, final int blockSize) {
      return InstanceBlock$.MODULE$.blokify(instances, blockSize);
   }

   public static InstanceBlock fromInstances(final Seq instances) {
      return InstanceBlock$.MODULE$.fromInstances(instances);
   }

   public static double DefaultBlockSizeInMB() {
      return InstanceBlock$.MODULE$.DefaultBlockSizeInMB();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double[] labels() {
      return this.labels;
   }

   public double[] weights() {
      return this.weights;
   }

   public Matrix matrix() {
      return this.matrix;
   }

   public int size() {
      return this.labels().length;
   }

   public int numFeatures() {
      return this.matrix().numCols();
   }

   public Iterator instanceIterator() {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.doubleArrayOps(this.weights())) ? .MODULE$.iterator$extension(scala.Predef..MODULE$.doubleArrayOps(this.labels())).zip(.MODULE$.iterator$extension(scala.Predef..MODULE$.doubleArrayOps(this.weights()))).zip(this.matrix().rowIter()).map((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var3 = (Tuple2)x0$1._1();
            Vector vec = (Vector)x0$1._2();
            if (var3 != null) {
               double label = var3._1$mcD$sp();
               double weight = var3._2$mcD$sp();
               return new Instance(label, weight, vec);
            }
         }

         throw new MatchError(x0$1);
      }) : .MODULE$.iterator$extension(scala.Predef..MODULE$.doubleArrayOps(this.labels())).zip(this.matrix().rowIter()).map((x0$2) -> {
         if (x0$2 != null) {
            double label = x0$2._1$mcD$sp();
            Vector vec = (Vector)x0$2._2();
            return new Instance(label, (double)1.0F, vec);
         } else {
            throw new MatchError(x0$2);
         }
      });
   }

   public double getLabel(final int i) {
      return this.labels()[i];
   }

   public Iterator labelIter() {
      return .MODULE$.iterator$extension(scala.Predef..MODULE$.doubleArrayOps(this.labels()));
   }

   private Function1 getWeight$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.getWeight = .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.doubleArrayOps(this.weights())) ? (i) -> this.weights()[i] : (i) -> (double)1.0F;
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.getWeight;
   }

   public Function1 getWeight() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.getWeight$lzycompute() : this.getWeight;
   }

   public Iterator weightIter() {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.doubleArrayOps(this.weights())) ? .MODULE$.iterator$extension(scala.Predef..MODULE$.doubleArrayOps(this.weights())) : scala.package..MODULE$.Iterator().fill(this.size(), (JFunction0.mcD.sp)() -> (double)1.0F);
   }

   private Function1 getNonZeroIter$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            Matrix var3 = this.matrix();
            Function1 var10001;
            if (var3 instanceof DenseMatrix) {
               DenseMatrix var4 = (DenseMatrix)var3;
               var10001 = (i) -> $anonfun$getNonZeroIter$1(this, var4, BoxesRunTime.unboxToInt(i));
            } else {
               if (!(var3 instanceof SparseMatrix)) {
                  throw new MatchError(var3);
               }

               SparseMatrix var5 = (SparseMatrix)var3;
               var10001 = (i) -> $anonfun$getNonZeroIter$4(var5, BoxesRunTime.unboxToInt(i));
            }

            this.getNonZeroIter = var10001;
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var7) {
         throw var7;
      }

      return this.getNonZeroIter;
   }

   public Function1 getNonZeroIter() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.getNonZeroIter$lzycompute() : this.getNonZeroIter;
   }

   public InstanceBlock copy(final double[] labels, final double[] weights, final Matrix matrix) {
      return new InstanceBlock(labels, weights, matrix);
   }

   public double[] copy$default$1() {
      return this.labels();
   }

   public double[] copy$default$2() {
      return this.weights();
   }

   public Matrix copy$default$3() {
      return this.matrix();
   }

   public String productPrefix() {
      return "InstanceBlock";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.labels();
         }
         case 1 -> {
            return this.weights();
         }
         case 2 -> {
            return this.matrix();
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
      return x$1 instanceof InstanceBlock;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "labels";
         }
         case 1 -> {
            return "weights";
         }
         case 2 -> {
            return "matrix";
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
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof InstanceBlock) {
               InstanceBlock var4 = (InstanceBlock)x$1;
               if (this.labels() == var4.labels() && this.weights() == var4.weights()) {
                  label48: {
                     Matrix var10000 = this.matrix();
                     Matrix var5 = var4.matrix();
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

   // $FF: synthetic method
   public static final Tuple2 $anonfun$getNonZeroIter$2(final DenseMatrix x2$1, final int start$1, final int j) {
      return new Tuple2.mcID.sp(j, x2$1.values()[start$1 + j]);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getNonZeroIter$3(final Tuple2 x$1) {
      return x$1._2$mcD$sp() != (double)0;
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$getNonZeroIter$1(final InstanceBlock $this, final DenseMatrix x2$1, final int i) {
      int start = $this.numFeatures() * i;
      return scala.package..MODULE$.Iterator().tabulate($this.numFeatures(), (j) -> $anonfun$getNonZeroIter$2(x2$1, start, BoxesRunTime.unboxToInt(j))).filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$getNonZeroIter$3(x$1)));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$getNonZeroIter$5(final SparseMatrix x3$1, final int start$2, final int j) {
      return new Tuple2.mcID.sp(x3$1.rowIndices()[start$2 + j], x3$1.values()[start$2 + j]);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getNonZeroIter$6(final Tuple2 x$2) {
      return x$2._2$mcD$sp() != (double)0;
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$getNonZeroIter$4(final SparseMatrix x3$1, final int i) {
      int start = x3$1.colPtrs()[i];
      int end = x3$1.colPtrs()[i + 1];
      return scala.package..MODULE$.Iterator().tabulate(end - start, (j) -> $anonfun$getNonZeroIter$5(x3$1, start, BoxesRunTime.unboxToInt(j))).filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$getNonZeroIter$6(x$2)));
   }

   public InstanceBlock(final double[] labels, final double[] weights, final Matrix matrix) {
      this.labels = labels;
      this.weights = weights;
      this.matrix = matrix;
      Product.$init$(this);
      scala.Predef..MODULE$.require(labels.length == matrix.numRows());
      scala.Predef..MODULE$.require(matrix.isTransposed());
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.doubleArrayOps(weights))) {
         scala.Predef..MODULE$.require(labels.length == weights.length);
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
