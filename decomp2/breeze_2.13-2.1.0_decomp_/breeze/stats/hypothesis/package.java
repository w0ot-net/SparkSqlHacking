package breeze.stats.hypothesis;

import breeze.linalg.support.CanTraverseValues;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.math.Numeric;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tMr!B\u0013'\u0011\u0003ic!B\u0018'\u0011\u0003\u0001\u0004\"B\u001c\u0002\t\u0003A\u0004\"B\u001d\u0002\t\u0003Q\u0004\"B\u001d\u0002\t\u0003\u0011\u0007\"B\u001d\u0002\t\u0003!\b\"B\u001d\u0002\t\u0003ihABA\u0006\u0003\u0001\u000bi\u0001\u0003\u0006\u0002\u001c\u001d\u0011)\u001a!C\u0001\u0003;A\u0011\"a\b\b\u0005#\u0005\u000b\u0011B\u001f\t\u0015\u0005\u0005rA!f\u0001\n\u0003\ti\u0002C\u0005\u0002$\u001d\u0011\t\u0012)A\u0005{!1qg\u0002C\u0001\u0003KA\u0011\"a\f\b\u0003\u0003%\t!!\r\t\u0013\u0005]r!%A\u0005\u0002\u0005e\u0002\"CA(\u000fE\u0005I\u0011AA\u001d\u0011%\t\tfBA\u0001\n\u0003\n\u0019\u0006C\u0005\u0002f\u001d\t\t\u0011\"\u0001\u0002h!I\u0011qN\u0004\u0002\u0002\u0013\u0005\u0011\u0011\u000f\u0005\n\u0003o:\u0011\u0011!C!\u0003sB\u0011\"a\"\b\u0003\u0003%\t!!#\t\u0013\u0005Mu!!A\u0005B\u0005U\u0005\"CAM\u000f\u0005\u0005I\u0011IAN\u0011%\tijBA\u0001\n\u0003\ny\nC\u0005\u0002\"\u001e\t\t\u0011\"\u0011\u0002$\u001eI\u0011qU\u0001\u0002\u0002#\u0005\u0011\u0011\u0016\u0004\n\u0003\u0017\t\u0011\u0011!E\u0001\u0003WCaa\u000e\u000e\u0005\u0002\u0005\r\u0007\"CAO5\u0005\u0005IQIAP\u0011%\t)MGA\u0001\n\u0003\u000b9\rC\u0005\u0002Nj\t\t\u0011\"!\u0002P\"I\u0011\u0011\u001d\u000e\u0002\u0002\u0013%\u00111\u001d\u0005\b\u0003W\fA\u0011BAw\u0011\u001d\t90\u0001C\u0001\u0003sDq!a>\u0002\t\u0003\u0011Y\u0001C\u0004\u0003 \u0005!\tA!\t\t\u000f\t-\u0012\u0001\"\u0001\u0003.\u00059\u0001/Y2lC\u001e,'BA\u0014)\u0003)A\u0017\u0010]8uQ\u0016\u001c\u0018n\u001d\u0006\u0003S)\nQa\u001d;biNT\u0011aK\u0001\u0007EJ,WM_3\u0004\u0001A\u0011a&A\u0007\u0002M\t9\u0001/Y2lC\u001e,7CA\u00012!\t\u0011T'D\u00014\u0015\u0005!\u0014!B:dC2\f\u0017B\u0001\u001c4\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012!L\u0001\u0006iR+7\u000f^\u000b\u0003w=#2\u0001\u0010-^)\ti\u0004\t\u0005\u00023}%\u0011qh\r\u0002\u0007\t>,(\r\\3\t\u000b\u0005\u001b\u00019\u0001\"\u0002\u000f9,X.\u001a:jGB\u00191IS'\u000f\u0005\u0011KeBA#I\u001b\u00051%BA$-\u0003\u0019a$o\\8u}%\tA'\u0003\u0002&g%\u00111\n\u0014\u0002\b\u001dVlWM]5d\u0015\t)3\u0007\u0005\u0002O\u001f2\u0001A!\u0002)\u0004\u0005\u0004\t&!\u0001+\u0012\u0005I+\u0006C\u0001\u001aT\u0013\t!6GA\u0004O_RD\u0017N\\4\u0011\u0005I2\u0016BA,4\u0005\r\te.\u001f\u0005\u00063\u000e\u0001\rAW\u0001\u0004SR\f\u0004cA\"\\\u001b&\u0011A\f\u0014\u0002\u0010)J\fg/\u001a:tC\ndWm\u00148dK\")al\u0001a\u0001?\u0006\u0019\u0011\u000e\u001e\u001a\u0011\u0007\r\u0003W*\u0003\u0002b\u0019\nYAK]1wKJ\u001c\u0018M\u00197f+\t\u0019\u0007\u000fF\u0002eeN$\"!P3\t\u000b\u0019$\u00019A4\u0002\u0005\r$\b\u0003\u00025n_vj\u0011!\u001b\u0006\u0003U.\fqa];qa>\u0014HO\u0003\u0002mU\u00051A.\u001b8bY\u001eL!A\\5\u0003#\r\u000bg\u000e\u0016:bm\u0016\u00148/\u001a,bYV,7\u000f\u0005\u0002Oa\u0012)\u0011\u000f\u0002b\u0001#\n\t\u0001\fC\u0003Z\t\u0001\u0007q\u000eC\u0003_\t\u0001\u0007q.\u0006\u0002vuR\u0011ao\u001f\u000b\u0003{]DQ!Q\u0003A\u0004a\u00042a\u0011&z!\tq%\u0010B\u0003Q\u000b\t\u0007\u0011\u000bC\u0003Z\u000b\u0001\u0007A\u0010E\u0002DAf,2A`A\u0004)\ry\u0018\u0011\u0002\u000b\u0004{\u0005\u0005\u0001B\u00024\u0007\u0001\b\t\u0019\u0001E\u0003i[\u0006\u0015Q\bE\u0002O\u0003\u000f!Q!\u001d\u0004C\u0002ECa!\u0017\u0004A\u0002\u0005\u0015!AC\"iSJ\u0012Vm];miN1q!MA\b\u0003+\u00012AMA\t\u0013\r\t\u0019b\r\u0002\b!J|G-^2u!\r\u0019\u0015qC\u0005\u0004\u00033a%\u0001D*fe&\fG.\u001b>bE2,\u0017\u0001B2iSJ*\u0012!P\u0001\u0006G\"L'\u0007I\u0001\u0005aZ\u000bG.A\u0003q-\u0006d\u0007\u0005\u0006\u0004\u0002(\u0005-\u0012Q\u0006\t\u0004\u0003S9Q\"A\u0001\t\r\u0005mA\u00021\u0001>\u0011\u0019\t\t\u0003\u0004a\u0001{\u0005!1m\u001c9z)\u0019\t9#a\r\u00026!A\u00111D\u0007\u0011\u0002\u0003\u0007Q\b\u0003\u0005\u0002\"5\u0001\n\u00111\u0001>\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!a\u000f+\u0007u\nid\u000b\u0002\u0002@A!\u0011\u0011IA&\u001b\t\t\u0019E\u0003\u0003\u0002F\u0005\u001d\u0013!C;oG\",7m[3e\u0015\r\tIeM\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA'\u0003\u0007\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA+!\u0011\t9&!\u0019\u000e\u0005\u0005e#\u0002BA.\u0003;\nA\u0001\\1oO*\u0011\u0011qL\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002d\u0005e#AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002jA\u0019!'a\u001b\n\u0007\u000554GA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002V\u0003gB\u0011\"!\u001e\u0013\u0003\u0003\u0005\r!!\u001b\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tY\bE\u0003\u0002~\u0005\rU+\u0004\u0002\u0002\u0000)\u0019\u0011\u0011Q\u001a\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0006\u0006}$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a#\u0002\u0012B\u0019!'!$\n\u0007\u0005=5GA\u0004C_>dW-\u00198\t\u0011\u0005UD#!AA\u0002U\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011QKAL\u0011%\t)(FA\u0001\u0002\u0004\tI'\u0001\u0005iCND7i\u001c3f)\t\tI'\u0001\u0005u_N#(/\u001b8h)\t\t)&\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u0017\u000b)\u000b\u0003\u0005\u0002va\t\t\u00111\u0001V\u0003)\u0019\u0005.\u001b\u001aSKN,H\u000e\u001e\t\u0004\u0003SQ2#\u0002\u000e\u0002.\u0006e\u0006\u0003CAX\u0003kkT(a\n\u000e\u0005\u0005E&bAAZg\u00059!/\u001e8uS6,\u0017\u0002BA\\\u0003c\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\tY,!1\u000e\u0005\u0005u&\u0002BA`\u0003;\n!![8\n\t\u0005e\u0011Q\u0018\u000b\u0003\u0003S\u000bQ!\u00199qYf$b!a\n\u0002J\u0006-\u0007BBA\u000e;\u0001\u0007Q\b\u0003\u0004\u0002\"u\u0001\r!P\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\t.!8\u0011\u000bI\n\u0019.a6\n\u0007\u0005U7G\u0001\u0004PaRLwN\u001c\t\u0006e\u0005eW(P\u0005\u0004\u00037\u001c$A\u0002+va2,'\u0007C\u0005\u0002`z\t\t\u00111\u0001\u0002(\u0005\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u0015\b\u0003BA,\u0003OLA!!;\u0002Z\t1qJ\u00196fGR\fab\u00195j'F,\u0018M]3e)\u0016\u0014X\u000eF\u0003>\u0003_\f\u0019\u0010\u0003\u0004\u0002r\u0002\u0002\r!P\u0001\u0002K\"1\u0011Q\u001f\u0011A\u0002u\n\u0011a\\\u0001\tG\"L'\u0007V3tiRQ\u0011qEA~\u0003\u007f\u0014\u0019Aa\u0002\t\u000f\u0005u\u0018\u00051\u0001\u0002j\u0005q1/^2dKN\u001c8i\u001c8ue>d\u0007b\u0002B\u0001C\u0001\u0007\u0011\u0011N\u0001\u000eiJL\u0017\r\\:D_:$(o\u001c7\t\u000f\t\u0015\u0011\u00051\u0001\u0002j\u0005q1/^2dKN\u001ch+\u0019:jC:$\bb\u0002B\u0005C\u0001\u0007\u0011\u0011N\u0001\u000eiJL\u0017\r\\:WCJL\u0017M\u001c;\u0015\r\t5!1\u0003B\r!\u0015\u0019%qBA\u0014\u0013\r\u0011\t\u0002\u0014\u0002\u0004'\u0016\f\bb\u0002B\u000bE\u0001\u0007!qC\u0001\bG>tGO]8m!\u001d\u0011\u0014\u0011\\A5\u0003SBqAa\u0007#\u0001\u0004\u0011i\"\u0001\u0004ue&\fGn\u001d\t\u0006\u0007\n=!qC\u0001\u0013g&$\u0017m[\"peJ,7\r^3e!Z\u000bG\u000eF\u0003>\u0005G\u00119\u0003\u0003\u0004\u0003&\r\u0002\r!P\u0001\u0002a\"9!\u0011F\u0012A\u0002\u0005%\u0014!\u00018\u00021MLG-Y6D_J\u0014Xm\u0019;fIB3\u0016\r\\\"vi>4g\rF\u0003>\u0005_\u0011\t\u0004\u0003\u0004\u0003&\u0011\u0002\r!\u0010\u0005\b\u0005S!\u0003\u0019AA5\u0001"
)
public final class package {
   public static double sidakCorrectedPValCutoff(final double p, final int n) {
      return package$.MODULE$.sidakCorrectedPValCutoff(p, n);
   }

   public static double sidakCorrectedPVal(final double p, final int n) {
      return package$.MODULE$.sidakCorrectedPVal(p, n);
   }

   public static Seq chi2Test(final Tuple2 control, final Seq trials) {
      return package$.MODULE$.chi2Test(control, trials);
   }

   public static Chi2Result chi2Test(final int successControl, final int trialsControl, final int successVariant, final int trialsVariant) {
      return package$.MODULE$.chi2Test(successControl, trialsControl, successVariant, trialsVariant);
   }

   public static double tTest(final Object it1, final CanTraverseValues ct) {
      return package$.MODULE$.tTest(it1, ct);
   }

   public static double tTest(final Iterable it1, final Numeric numeric) {
      return package$.MODULE$.tTest(it1, numeric);
   }

   public static double tTest(final Object it1, final Object it2, final CanTraverseValues ct) {
      return package$.MODULE$.tTest(it1, it2, ct);
   }

   public static double tTest(final IterableOnce it1, final Iterable it2, final Numeric numeric) {
      return package$.MODULE$.tTest(it1, it2, numeric);
   }

   public static class Chi2Result implements Product, Serializable {
      private final double chi2;
      private final double pVal;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double chi2() {
         return this.chi2;
      }

      public double pVal() {
         return this.pVal;
      }

      public Chi2Result copy(final double chi2, final double pVal) {
         return new Chi2Result(chi2, pVal);
      }

      public double copy$default$1() {
         return this.chi2();
      }

      public double copy$default$2() {
         return this.pVal();
      }

      public String productPrefix() {
         return "Chi2Result";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.chi2());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToDouble(this.pVal());
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Chi2Result;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "chi2";
               break;
            case 1:
               var10000 = "pVal";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.chi2()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.pVal()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label51: {
               boolean var2;
               if (x$1 instanceof Chi2Result) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Chi2Result var4 = (Chi2Result)x$1;
                  if (this.chi2() == var4.chi2() && this.pVal() == var4.pVal() && var4.canEqual(this)) {
                     break label51;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Chi2Result(final double chi2, final double pVal) {
         this.chi2 = chi2;
         this.pVal = pVal;
         Product.$init$(this);
      }
   }

   public static class Chi2Result$ extends AbstractFunction2 implements Serializable {
      public static final Chi2Result$ MODULE$ = new Chi2Result$();

      public final String toString() {
         return "Chi2Result";
      }

      public Chi2Result apply(final double chi2, final double pVal) {
         return new Chi2Result(chi2, pVal);
      }

      public Option unapply(final Chi2Result x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.chi2(), x$0.pVal())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Chi2Result$.class);
      }
   }
}
