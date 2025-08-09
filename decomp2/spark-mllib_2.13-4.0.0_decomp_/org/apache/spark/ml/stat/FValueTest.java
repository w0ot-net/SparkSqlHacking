package org.apache.spark.ml.stat;

import java.io.Serializable;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.sql.Dataset;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tMrA\u0002\u0012$\u0011\u0003)SF\u0002\u00040G!\u0005Q\u0005\r\u0005\u0006o\u0005!\t!\u000f\u0004\u0005u\u0005!5\b\u0003\u0005L\u0007\tU\r\u0011\"\u0001M\u0011!\u00196A!E!\u0002\u0013i\u0005\u0002\u0003+\u0004\u0005+\u0007I\u0011A+\t\u0011q\u001b!\u0011#Q\u0001\nYC\u0001\"X\u0002\u0003\u0016\u0004%\t\u0001\u0014\u0005\t=\u000e\u0011\t\u0012)A\u0005\u001b\")qg\u0001C\u0001?\"9QmAA\u0001\n\u00031\u0007b\u00026\u0004#\u0003%\ta\u001b\u0005\bm\u000e\t\n\u0011\"\u0001x\u0011\u001dI8!%A\u0005\u0002-DqA_\u0002\u0002\u0002\u0013\u00053\u0010C\u0005\u0002\n\r\t\t\u0011\"\u0001\u0002\f!I\u00111C\u0002\u0002\u0002\u0013\u0005\u0011Q\u0003\u0005\n\u0003C\u0019\u0011\u0011!C!\u0003GA\u0011\"!\r\u0004\u0003\u0003%\t!a\r\t\u0013\u0005u2!!A\u0005B\u0005}\u0002\"CA\"\u0007\u0005\u0005I\u0011IA#\u0011%\t9eAA\u0001\n\u0003\nI\u0005C\u0005\u0002L\r\t\t\u0011\"\u0011\u0002N\u001dI\u0011\u0011K\u0001\u0002\u0002#%\u00111\u000b\u0004\tu\u0005\t\t\u0011#\u0003\u0002V!1q'\u0007C\u0001\u0003[B\u0011\"a\u0012\u001a\u0003\u0003%)%!\u0013\t\u0013\u0005=\u0014$!A\u0005\u0002\u0006E\u0004\"CA=3\u0005\u0005I\u0011QA>\u0011%\ti)GA\u0001\n\u0013\ty\tC\u0004\u0002\u0018\u0006!\t!!'\t\u000f\u0005]\u0015\u0001\"\u0001\u0002f\"A\u00111_\u0001\u0005\u0002\u0015\n)0\u0001\u0006G-\u0006dW/\u001a+fgRT!\u0001J\u0013\u0002\tM$\u0018\r\u001e\u0006\u0003M\u001d\n!!\u001c7\u000b\u0005!J\u0013!B:qCJ\\'B\u0001\u0016,\u0003\u0019\t\u0007/Y2iK*\tA&A\u0002pe\u001e\u0004\"AL\u0001\u000e\u0003\r\u0012!B\u0012,bYV,G+Z:u'\t\t\u0011\u0007\u0005\u00023k5\t1GC\u00015\u0003\u0015\u00198-\u00197b\u0013\t14G\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tQF\u0001\u0007G-\u0006dW/\u001a*fgVdGo\u0005\u0003\u0004cqz\u0004C\u0001\u001a>\u0013\tq4GA\u0004Qe>$Wo\u0019;\u0011\u0005\u0001CeBA!G\u001d\t\u0011U)D\u0001D\u0015\t!\u0005(\u0001\u0004=e>|GOP\u0005\u0002i%\u0011qiM\u0001\ba\u0006\u001c7.Y4f\u0013\tI%J\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Hg\u00059\u0001OV1mk\u0016\u001cX#A'\u0011\u00059\u000bV\"A(\u000b\u0005A+\u0013A\u00027j]\u0006dw-\u0003\u0002S\u001f\n1a+Z2u_J\f\u0001\u0002\u001d,bYV,7\u000fI\u0001\u0011I\u0016<'/Z3t\u001f\u001a4%/Z3e_6,\u0012A\u0016\t\u0004e]K\u0016B\u0001-4\u0005\u0015\t%O]1z!\t\u0011$,\u0003\u0002\\g\t!Aj\u001c8h\u0003E!Wm\u001a:fKN|eM\u0012:fK\u0012|W\u000eI\u0001\bMZ\u000bG.^3t\u0003!1g+\u00197vKN\u0004C\u0003\u00021cG\u0012\u0004\"!Y\u0002\u000e\u0003\u0005AQa\u0013\u0006A\u00025CQ\u0001\u0016\u0006A\u0002YCQ!\u0018\u0006A\u00025\u000bAaY8qsR!\u0001m\u001a5j\u0011\u001dY5\u0002%AA\u00025Cq\u0001V\u0006\u0011\u0002\u0003\u0007a\u000bC\u0004^\u0017A\u0005\t\u0019A'\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tAN\u000b\u0002N[.\na\u000e\u0005\u0002pi6\t\u0001O\u0003\u0002re\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003gN\n!\"\u00198o_R\fG/[8o\u0013\t)\bOA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001yU\t1V.\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005a\bcA?\u0002\u00065\taPC\u0002\u0000\u0003\u0003\tA\u0001\\1oO*\u0011\u00111A\u0001\u0005U\u00064\u0018-C\u0002\u0002\by\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u0007!\r\u0011\u0014qB\u0005\u0004\u0003#\u0019$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\f\u0003;\u00012AMA\r\u0013\r\tYb\r\u0002\u0004\u0003:L\b\"CA\u0010#\u0005\u0005\t\u0019AA\u0007\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\u0005\t\u0007\u0003O\ti#a\u0006\u000e\u0005\u0005%\"bAA\u0016g\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005=\u0012\u0011\u0006\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u00026\u0005m\u0002c\u0001\u001a\u00028%\u0019\u0011\u0011H\u001a\u0003\u000f\t{w\u000e\\3b]\"I\u0011qD\n\u0002\u0002\u0003\u0007\u0011qC\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002}\u0003\u0003B\u0011\"a\b\u0015\u0003\u0003\u0005\r!!\u0004\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u0004\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001`\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005U\u0012q\n\u0005\n\u0003?9\u0012\u0011!a\u0001\u0003/\tAB\u0012,bYV,'+Z:vYR\u0004\"!Y\r\u0014\u000be\t9&a\u0019\u0011\u0011\u0005e\u0013qL'W\u001b\u0002l!!a\u0017\u000b\u0007\u0005u3'A\u0004sk:$\u0018.\\3\n\t\u0005\u0005\u00141\f\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003BA3\u0003Wj!!a\u001a\u000b\t\u0005%\u0014\u0011A\u0001\u0003S>L1!SA4)\t\t\u0019&A\u0003baBd\u0017\u0010F\u0004a\u0003g\n)(a\u001e\t\u000b-c\u0002\u0019A'\t\u000bQc\u0002\u0019\u0001,\t\u000buc\u0002\u0019A'\u0002\u000fUt\u0017\r\u001d9msR!\u0011QPAE!\u0015\u0011\u0014qPAB\u0013\r\t\ti\r\u0002\u0007\u001fB$\u0018n\u001c8\u0011\rI\n))\u0014,N\u0013\r\t9i\r\u0002\u0007)V\u0004H.Z\u001a\t\u0011\u0005-U$!AA\u0002\u0001\f1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\nE\u0002~\u0003'K1!!&\u007f\u0005\u0019y%M[3di\u0006!A/Z:u)!\tY*a/\u0002@\u0006E\u0007\u0003BAO\u0003ksA!a(\u00022:!\u0011\u0011UAW\u001d\u0011\t\u0019+a+\u000f\t\u0005\u0015\u0016\u0011\u0016\b\u0004\u0005\u0006\u001d\u0016\"\u0001\u0017\n\u0005)Z\u0013B\u0001\u0015*\u0013\r\tykJ\u0001\u0004gFd\u0017bA$\u00024*\u0019\u0011qV\u0014\n\t\u0005]\u0016\u0011\u0018\u0002\n\t\u0006$\u0018M\u0012:b[\u0016T1aRAZ\u0011\u001d\til\ba\u0001\u00037\u000bq\u0001Z1uCN,G\u000fC\u0004\u0002B~\u0001\r!a1\u0002\u0017\u0019,\u0017\r^;sKN\u001cu\u000e\u001c\t\u0005\u0003\u000b\fiM\u0004\u0003\u0002H\u0006%\u0007C\u0001\"4\u0013\r\tYmM\u0001\u0007!J,G-\u001a4\n\t\u0005\u001d\u0011q\u001a\u0006\u0004\u0003\u0017\u001c\u0004bBAj?\u0001\u0007\u00111Y\u0001\tY\u0006\u0014W\r\\\"pY\"*q$a6\u0002bB!\u0011\u0011\\Ao\u001b\t\tYN\u0003\u0002tO%!\u0011q\\An\u0005\u0015\u0019\u0016N\\2fC\t\t\u0019/A\u00034]Er\u0003\u0007\u0006\u0006\u0002\u001c\u0006\u001d\u0018\u0011^Av\u0003[Dq!!0!\u0001\u0004\tY\nC\u0004\u0002B\u0002\u0002\r!a1\t\u000f\u0005M\u0007\u00051\u0001\u0002D\"9\u0011q\u001e\u0011A\u0002\u0005U\u0012a\u00024mCR$XM\u001c\u0015\u0006A\u0005]\u0017\u0011]\u0001\u000fi\u0016\u001cHOU3he\u0016\u001c8/[8o)!\t9Pa\u0004\u0003,\t5\u0002CBA}\u0003\u007f\u0014\u0019!\u0004\u0002\u0002|*\u0019\u0011Q`\u0014\u0002\u0007I$G-\u0003\u0003\u0003\u0002\u0005m(a\u0001*E\tBQ!G!\u0002\u0002\u000e\t%\u0011L!\u0003\n\u0007\t\u001d1G\u0001\u0004UkBdW\r\u000e\t\u0004e\t-\u0011b\u0001B\u0007g\t1Ai\\;cY\u0016Dq!!0\"\u0001\u0004\u0011\t\u0002\r\u0003\u0003\u0014\t}\u0001C\u0002B\u000b\u0005/\u0011Y\"\u0004\u0002\u00024&!!\u0011DAZ\u0005\u001d!\u0015\r^1tKR\u0004BA!\b\u0003 1\u0001A\u0001\u0004B\u0011\u0005\u001f\t\t\u0011!A\u0003\u0002\t\r\"aA0%cE!!QEA\f!\r\u0011$qE\u0005\u0004\u0005S\u0019$a\u0002(pi\"Lgn\u001a\u0005\b\u0003\u0003\f\u0003\u0019AAb\u0011\u001d\t\u0019.\ta\u0001\u0003\u0007DS!AAl\u0003CDS\u0001AAl\u0003C\u0004"
)
public final class FValueTest {
   public static Dataset test(final Dataset dataset, final String featuresCol, final String labelCol, final boolean flatten) {
      return FValueTest$.MODULE$.test(dataset, featuresCol, labelCol, flatten);
   }

   public static Dataset test(final Dataset dataset, final String featuresCol, final String labelCol) {
      return FValueTest$.MODULE$.test(dataset, featuresCol, labelCol);
   }

   private static class FValueResult implements Product, Serializable {
      private final Vector pValues;
      private final long[] degreesOfFreedom;
      private final Vector fValues;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Vector pValues() {
         return this.pValues;
      }

      public long[] degreesOfFreedom() {
         return this.degreesOfFreedom;
      }

      public Vector fValues() {
         return this.fValues;
      }

      public FValueResult copy(final Vector pValues, final long[] degreesOfFreedom, final Vector fValues) {
         return new FValueResult(pValues, degreesOfFreedom, fValues);
      }

      public Vector copy$default$1() {
         return this.pValues();
      }

      public long[] copy$default$2() {
         return this.degreesOfFreedom();
      }

      public Vector copy$default$3() {
         return this.fValues();
      }

      public String productPrefix() {
         return "FValueResult";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.pValues();
            }
            case 1 -> {
               return this.degreesOfFreedom();
            }
            case 2 -> {
               return this.fValues();
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
         return x$1 instanceof FValueResult;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "pValues";
            }
            case 1 -> {
               return "degreesOfFreedom";
            }
            case 2 -> {
               return "fValues";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var8;
         if (this != x$1) {
            label59: {
               if (x$1 instanceof FValueResult) {
                  label51: {
                     FValueResult var4 = (FValueResult)x$1;
                     Vector var10000 = this.pValues();
                     Vector var5 = var4.pValues();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label51;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label51;
                     }

                     if (this.degreesOfFreedom() == var4.degreesOfFreedom()) {
                        label52: {
                           var10000 = this.fValues();
                           Vector var6 = var4.fValues();
                           if (var10000 == null) {
                              if (var6 != null) {
                                 break label52;
                              }
                           } else if (!var10000.equals(var6)) {
                              break label52;
                           }

                           if (var4.canEqual(this)) {
                              break label59;
                           }
                        }
                     }
                  }
               }

               var8 = false;
               return var8;
            }
         }

         var8 = true;
         return var8;
      }

      public FValueResult(final Vector pValues, final long[] degreesOfFreedom, final Vector fValues) {
         this.pValues = pValues;
         this.degreesOfFreedom = degreesOfFreedom;
         this.fValues = fValues;
         Product.$init$(this);
      }
   }

   private static class FValueResult$ extends AbstractFunction3 implements Serializable {
      public static final FValueResult$ MODULE$ = new FValueResult$();

      public final String toString() {
         return "FValueResult";
      }

      public FValueResult apply(final Vector pValues, final long[] degreesOfFreedom, final Vector fValues) {
         return new FValueResult(pValues, degreesOfFreedom, fValues);
      }

      public Option unapply(final FValueResult x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.pValues(), x$0.degreesOfFreedom(), x$0.fValues())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(FValueResult$.class);
      }

      public FValueResult$() {
      }
   }
}
