package org.apache.spark.ml.stat;

import java.io.Serializable;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t%s!\u0002\u0011\"\u0011\u0003ac!\u0002\u0018\"\u0011\u0003y\u0003\"\u0002\u001c\u0002\t\u00039d\u0001\u0002\u001d\u0002\tfB\u0001\"S\u0002\u0003\u0016\u0004%\tA\u0013\u0005\t\u001d\u000e\u0011\t\u0012)A\u0005\u0017\"Aqj\u0001BK\u0002\u0013\u0005!\n\u0003\u0005Q\u0007\tE\t\u0015!\u0003L\u0011\u001514\u0001\"\u0001R\u0011\u001d16!!A\u0005\u0002]CqAW\u0002\u0012\u0002\u0013\u00051\fC\u0004g\u0007E\u0005I\u0011A.\t\u000f\u001d\u001c\u0011\u0011!C!Q\"9\u0011oAA\u0001\n\u0003\u0011\bb\u0002<\u0004\u0003\u0003%\ta\u001e\u0005\b{\u000e\t\t\u0011\"\u0011\u007f\u0011%\tYaAA\u0001\n\u0003\ti\u0001C\u0005\u0002\u0018\r\t\t\u0011\"\u0011\u0002\u001a!I\u0011QD\u0002\u0002\u0002\u0013\u0005\u0013q\u0004\u0005\n\u0003C\u0019\u0011\u0011!C!\u0003GA\u0011\"!\n\u0004\u0003\u0003%\t%a\n\b\u0013\u0005-\u0012!!A\t\n\u00055b\u0001\u0003\u001d\u0002\u0003\u0003EI!a\f\t\rY2B\u0011AA$\u0011%\t\tCFA\u0001\n\u000b\n\u0019\u0003C\u0005\u0002JY\t\t\u0011\"!\u0002L!I\u0011\u0011\u000b\f\u0002\u0002\u0013\u0005\u00151\u000b\u0005\n\u0003K2\u0012\u0011!C\u0005\u0003OBq!a\u001c\u0002\t\u0013\t\t\bC\u0004\u00026\u0006!\t!a.\t\u000f\u0005U\u0016\u0001\"\u0001\u0002r\"9\u0011QW\u0001\u0005\u0002\tm\u0011!F&pY6|wm\u001c:pmNk\u0017N\u001d8pmR+7\u000f\u001e\u0006\u0003E\r\nAa\u001d;bi*\u0011A%J\u0001\u0003[2T!AJ\u0014\u0002\u000bM\u0004\u0018M]6\u000b\u0005!J\u0013AB1qC\u000eDWMC\u0001+\u0003\ry'oZ\u0002\u0001!\ti\u0013!D\u0001\"\u0005UYu\u000e\\7pO>\u0014xN^*nSJtwN\u001e+fgR\u001c\"!\u0001\u0019\u0011\u0005E\"T\"\u0001\u001a\u000b\u0003M\nQa]2bY\u0006L!!\u000e\u001a\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\tAFA\u000eL_2lwnZ8s_Z\u001cV.\u001b:o_Z$Vm\u001d;SKN,H\u000e^\n\u0005\u0007ART\b\u0005\u00022w%\u0011AH\r\u0002\b!J|G-^2u!\tqdI\u0004\u0002@\t:\u0011\u0001iQ\u0007\u0002\u0003*\u0011!iK\u0001\u0007yI|w\u000e\u001e \n\u0003MJ!!\u0012\u001a\u0002\u000fA\f7m[1hK&\u0011q\t\u0013\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u000bJ\na\u0001\u001d,bYV,W#A&\u0011\u0005Eb\u0015BA'3\u0005\u0019!u.\u001e2mK\u00069\u0001OV1mk\u0016\u0004\u0013!C:uCRL7\u000f^5d\u0003)\u0019H/\u0019;jgRL7\r\t\u000b\u0004%R+\u0006CA*\u0004\u001b\u0005\t\u0001\"B%\t\u0001\u0004Y\u0005\"B(\t\u0001\u0004Y\u0015\u0001B2paf$2A\u0015-Z\u0011\u001dI\u0015\u0002%AA\u0002-CqaT\u0005\u0011\u0002\u0003\u00071*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003qS#aS/,\u0003y\u0003\"a\u00183\u000e\u0003\u0001T!!\u00192\u0002\u0013Ut7\r[3dW\u0016$'BA23\u0003)\tgN\\8uCRLwN\\\u0005\u0003K\u0002\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A5\u0011\u0005)|W\"A6\u000b\u00051l\u0017\u0001\u00027b]\u001eT\u0011A\\\u0001\u0005U\u00064\u0018-\u0003\u0002qW\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012a\u001d\t\u0003cQL!!\u001e\u001a\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005a\\\bCA\u0019z\u0013\tQ(GA\u0002B]fDq\u0001 \b\u0002\u0002\u0003\u00071/A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002\u007fB)\u0011\u0011AA\u0004q6\u0011\u00111\u0001\u0006\u0004\u0003\u000b\u0011\u0014AC2pY2,7\r^5p]&!\u0011\u0011BA\u0002\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005=\u0011Q\u0003\t\u0004c\u0005E\u0011bAA\ne\t9!i\\8mK\u0006t\u0007b\u0002?\u0011\u0003\u0003\u0005\r\u0001_\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002j\u00037Aq\u0001`\t\u0002\u0002\u0003\u00071/\u0001\u0005iCND7i\u001c3f)\u0005\u0019\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003%\fa!Z9vC2\u001cH\u0003BA\b\u0003SAq\u0001 \u000b\u0002\u0002\u0003\u0007\u00010A\u000eL_2lwnZ8s_Z\u001cV.\u001b:o_Z$Vm\u001d;SKN,H\u000e\u001e\t\u0003'Z\u0019RAFA\u0019\u0003{\u0001r!a\r\u0002:-[%+\u0004\u0002\u00026)\u0019\u0011q\u0007\u001a\u0002\u000fI,h\u000e^5nK&!\u00111HA\u001b\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003\u007f\t)%\u0004\u0002\u0002B)\u0019\u00111I7\u0002\u0005%|\u0017bA$\u0002BQ\u0011\u0011QF\u0001\u0006CB\u0004H.\u001f\u000b\u0006%\u00065\u0013q\n\u0005\u0006\u0013f\u0001\ra\u0013\u0005\u0006\u001ff\u0001\raS\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t)&!\u0019\u0011\u000bE\n9&a\u0017\n\u0007\u0005e#G\u0001\u0004PaRLwN\u001c\t\u0006c\u0005u3jS\u0005\u0004\u0003?\u0012$A\u0002+va2,'\u0007\u0003\u0005\u0002di\t\t\u00111\u0001S\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003S\u00022A[A6\u0013\r\tig\u001b\u0002\u0007\u001f\nTWm\u0019;\u0002\u0019\u001d,GoU1na2,'\u000b\u0012#\u0015\r\u0005M\u0014qPAR!\u0015\t)(a\u001fL\u001b\t\t9HC\u0002\u0002z\u0015\n1A\u001d3e\u0013\u0011\ti(a\u001e\u0003\u0007I#E\tC\u0004\u0002\u0002r\u0001\r!a!\u0002\u000f\u0011\fG/Y:fiB!\u0011QQAO\u001d\u0011\t9)!'\u000f\t\u0005%\u0015Q\u0013\b\u0005\u0003\u0017\u000b\u0019J\u0004\u0003\u0002\u000e\u0006Eeb\u0001!\u0002\u0010&\t!&\u0003\u0002)S%\u0011aeJ\u0005\u0004\u0003/+\u0013aA:rY&\u0019Q)a'\u000b\u0007\u0005]U%\u0003\u0003\u0002 \u0006\u0005&!\u0003#bi\u00064%/Y7f\u0015\r)\u00151\u0014\u0005\b\u0003Kc\u0002\u0019AAT\u0003%\u0019\u0018-\u001c9mK\u000e{G\u000e\u0005\u0003\u0002*\u0006Ef\u0002BAV\u0003[\u0003\"\u0001\u0011\u001a\n\u0007\u0005=&'\u0001\u0004Qe\u0016$WMZ\u0005\u0004a\u0006M&bAAXe\u0005!A/Z:u)!\t\u0019)!/\u0002V\u0006]\u0007bBAA;\u0001\u0007\u00111\u0018\u0019\u0005\u0003{\u000bI\r\u0005\u0004\u0002@\u0006\u0005\u0017QY\u0007\u0003\u00037KA!a1\u0002\u001c\n9A)\u0019;bg\u0016$\b\u0003BAd\u0003\u0013d\u0001\u0001\u0002\u0007\u0002L\u0006e\u0016\u0011!A\u0001\u0006\u0003\tiMA\u0002`IE\n2!a4y!\r\t\u0014\u0011[\u0005\u0004\u0003'\u0014$a\u0002(pi\"Lgn\u001a\u0005\b\u0003Kk\u0002\u0019AAT\u0011\u001d\tI.\ba\u0001\u00037\f1a\u00193g!\u0015\t\u0014Q\\&L\u0013\r\tyN\r\u0002\n\rVt7\r^5p]FBS!HAr\u0003[\u0004B!!:\u0002j6\u0011\u0011q\u001d\u0006\u0003G\u0016JA!a;\u0002h\n)1+\u001b8dK\u0006\u0012\u0011q^\u0001\u0006e9\"d\u0006\r\u000b\t\u0003\u0007\u000b\u00190a@\u0003\u0002!9\u0011\u0011\u0011\u0010A\u0002\u0005U\b\u0007BA|\u0003w\u0004b!a0\u0002B\u0006e\b\u0003BAd\u0003w$A\"!@\u0002t\u0006\u0005\t\u0011!B\u0001\u0003\u001b\u00141a\u0018\u00133\u0011\u001d\t)K\ba\u0001\u0003OCq!!7\u001f\u0001\u0004\u0011\u0019\u0001\u0005\u0005\u0003\u0006\tE!Q\u0003B\u000b\u001b\t\u00119A\u0003\u0003\u0003\n\t-\u0011\u0001\u00034v]\u000e$\u0018n\u001c8\u000b\u00079\u0014iAC\u0002\u0003\u0010\u0015\n1!\u00199j\u0013\u0011\u0011\u0019Ba\u0002\u0003\u0011\u0019+hn\u0019;j_:\u00042A\u001bB\f\u0013\ti5\u000eK\u0003\u001f\u0003G\fi\u000f\u0006\u0006\u0002\u0004\nu!\u0011\u0006B\u0016\u0005_Aq!!! \u0001\u0004\u0011y\u0002\r\u0003\u0003\"\t\u0015\u0002CBA`\u0003\u0003\u0014\u0019\u0003\u0005\u0003\u0002H\n\u0015B\u0001\u0004B\u0014\u0005;\t\t\u0011!A\u0003\u0002\u00055'aA0%g!9\u0011QU\u0010A\u0002\u0005\u001d\u0006b\u0002B\u0017?\u0001\u0007\u0011qU\u0001\tI&\u001cHOT1nK\"9!\u0011G\u0010A\u0002\tM\u0012A\u00029be\u0006l7\u000f\u0005\u00032\u0005kY\u0015b\u0001B\u001ce\tQAH]3qK\u0006$X\r\u001a )\u000b}\t\u0019/!<)\u0007}\u0011i\u0004\u0005\u0003\u0003@\t\u0005S\"\u00012\n\u0007\t\r#MA\u0004wCJ\f'oZ:)\u000b\u0005\t\u0019/!<)\u000b\u0001\t\u0019/!<"
)
public final class KolmogorovSmirnovTest {
   public static Dataset test(final Dataset dataset, final String sampleCol, final String distName, final double... params) {
      return KolmogorovSmirnovTest$.MODULE$.test(dataset, sampleCol, distName, params);
   }

   public static Dataset test(final Dataset dataset, final String sampleCol, final String distName, final Seq params) {
      return KolmogorovSmirnovTest$.MODULE$.test(dataset, sampleCol, distName, params);
   }

   public static Dataset test(final Dataset dataset, final String sampleCol, final Function cdf) {
      return KolmogorovSmirnovTest$.MODULE$.test(dataset, sampleCol, cdf);
   }

   public static Dataset test(final Dataset dataset, final String sampleCol, final Function1 cdf) {
      return KolmogorovSmirnovTest$.MODULE$.test(dataset, sampleCol, cdf);
   }

   private static class KolmogorovSmirnovTestResult implements Product, Serializable {
      private final double pValue;
      private final double statistic;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double pValue() {
         return this.pValue;
      }

      public double statistic() {
         return this.statistic;
      }

      public KolmogorovSmirnovTestResult copy(final double pValue, final double statistic) {
         return new KolmogorovSmirnovTestResult(pValue, statistic);
      }

      public double copy$default$1() {
         return this.pValue();
      }

      public double copy$default$2() {
         return this.statistic();
      }

      public String productPrefix() {
         return "KolmogorovSmirnovTestResult";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToDouble(this.pValue());
            }
            case 1 -> {
               return BoxesRunTime.boxToDouble(this.statistic());
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
         return x$1 instanceof KolmogorovSmirnovTestResult;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "pValue";
            }
            case 1 -> {
               return "statistic";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.pValue()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.statistic()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label38: {
               if (x$1 instanceof KolmogorovSmirnovTestResult) {
                  KolmogorovSmirnovTestResult var4 = (KolmogorovSmirnovTestResult)x$1;
                  if (this.pValue() == var4.pValue() && this.statistic() == var4.statistic() && var4.canEqual(this)) {
                     break label38;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public KolmogorovSmirnovTestResult(final double pValue, final double statistic) {
         this.pValue = pValue;
         this.statistic = statistic;
         Product.$init$(this);
      }
   }

   private static class KolmogorovSmirnovTestResult$ extends AbstractFunction2 implements Serializable {
      public static final KolmogorovSmirnovTestResult$ MODULE$ = new KolmogorovSmirnovTestResult$();

      public final String toString() {
         return "KolmogorovSmirnovTestResult";
      }

      public KolmogorovSmirnovTestResult apply(final double pValue, final double statistic) {
         return new KolmogorovSmirnovTestResult(pValue, statistic);
      }

      public Option unapply(final KolmogorovSmirnovTestResult x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.pValue(), x$0.statistic())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(KolmogorovSmirnovTestResult$.class);
      }

      public KolmogorovSmirnovTestResult$() {
      }
   }
}
