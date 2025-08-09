package org.apache.spark.mllib.stat.test;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ee\u0001B\u000e\u001d\u0001&B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u001b\u0002\u0011\t\u0012)A\u0005\u0003\"Aq\n\u0001BK\u0002\u0013\u0005\u0001\u000b\u0003\u0005V\u0001\tE\t\u0015!\u0003R\u0011\u00159\u0006\u0001\"\u0001Y\u0011\u0015\u0001\u0007\u0001\"\u0001A\u0011\u0015\t\u0007\u0001\"\u0001Q\u0011\u0015\u0011\u0007\u0001\"\u0011d\u0011\u001da\u0007!!A\u0005\u00025Dq\u0001\u001d\u0001\u0012\u0002\u0013\u0005\u0011\u000fC\u0004|\u0001E\u0005I\u0011\u0001?\t\u000fy\u0004\u0011\u0011!C!\u007f\"I\u0011q\u0002\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0003\u0005\n\u00033\u0001\u0011\u0011!C\u0001\u00037A\u0011\"a\n\u0001\u0003\u0003%\t%!\u000b\t\u0013\u0005]\u0002!!A\u0005\u0002\u0005e\u0002\"CA\u001f\u0001\u0005\u0005I\u0011IA \u0011%\t\u0019\u0005AA\u0001\n\u0003\n)\u0005C\u0005\u0002H\u0001\t\t\u0011\"\u0011\u0002J\u001dI\u0011q\n\u000f\u0002\u0002#\u0005\u0011\u0011\u000b\u0004\t7q\t\t\u0011#\u0001\u0002T!1q+\u0006C\u0001\u0003WB\u0001BY\u000b\u0002\u0002\u0013\u0015\u0013Q\u000e\u0005\n\u0003_*\u0012\u0011!CA\u0003cB\u0011\"a\u001f\u0016\u0003\u0003%\t)! \t\u0013\u0005=U#!A\u0005\n\u0005E%\u0001\u0004\"j]\u0006\u0014\u0018pU1na2,'BA\u000f\u001f\u0003\u0011!Xm\u001d;\u000b\u0005}\u0001\u0013\u0001B:uCRT!!\t\u0012\u0002\u000b5dG.\u001b2\u000b\u0005\r\"\u0013!B:qCJ\\'BA\u0013'\u0003\u0019\t\u0007/Y2iK*\tq%A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001UA\u001a\u0004CA\u0016/\u001b\u0005a#\"A\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u0005=b#AB!osJ+g\r\u0005\u0002,c%\u0011!\u0007\f\u0002\b!J|G-^2u!\t!DH\u0004\u00026u9\u0011a'O\u0007\u0002o)\u0011\u0001\bK\u0001\u0007yI|w\u000e\u001e \n\u00035J!a\u000f\u0017\u0002\u000fA\f7m[1hK&\u0011QH\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003w1\nA\"[:FqB,'/[7f]R,\u0012!\u0011\t\u0003W\tK!a\u0011\u0017\u0003\u000f\t{w\u000e\\3b]\"\u001a\u0011!R&\u0011\u0005\u0019KU\"A$\u000b\u0005!\u0013\u0013AC1o]>$\u0018\r^5p]&\u0011!j\u0012\u0002\u0006'&t7-Z\u0011\u0002\u0019\u0006)\u0011G\f\u001c/a\u0005i\u0011n]#ya\u0016\u0014\u0018.\\3oi\u0002B3AA#L\u0003\u00151\u0018\r\\;f+\u0005\t\u0006CA\u0016S\u0013\t\u0019FF\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0007\u0015[\u0015A\u0002<bYV,\u0007\u0005K\u0002\u0005\u000b.\u000ba\u0001P5oSRtDcA-\\;B\u0011!\fA\u0007\u00029!)q(\u0002a\u0001\u0003\"\u001a1,R&\t\u000b=+\u0001\u0019A))\u0007u+5\nK\u0002\u0006\u000b.\u000bqbZ3u\u0013N,\u0005\u0010]3sS6,g\u000e^\u0001\tO\u0016$h+\u00197vK\u0006AAo\\*ue&tw\rF\u0001e!\t)\u0017N\u0004\u0002gOB\u0011a\u0007L\u0005\u0003Q2\na\u0001\u0015:fI\u00164\u0017B\u00016l\u0005\u0019\u0019FO]5oO*\u0011\u0001\u000eL\u0001\u0005G>\u0004\u0018\u0010F\u0002Z]>DqaP\u0005\u0011\u0002\u0003\u0007\u0011\tC\u0004P\u0013A\u0005\t\u0019A)\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t!O\u000b\u0002Bg.\nA\u000f\u0005\u0002vs6\taO\u0003\u0002xq\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\u00112J!A\u001f<\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003uT#!U:\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t\t\u0001\u0005\u0003\u0002\u0004\u00055QBAA\u0003\u0015\u0011\t9!!\u0003\u0002\t1\fgn\u001a\u0006\u0003\u0003\u0017\tAA[1wC&\u0019!.!\u0002\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005M\u0001cA\u0016\u0002\u0016%\u0019\u0011q\u0003\u0017\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005u\u00111\u0005\t\u0004W\u0005}\u0011bAA\u0011Y\t\u0019\u0011I\\=\t\u0013\u0005\u0015b\"!AA\u0002\u0005M\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002,A1\u0011QFA\u001a\u0003;i!!a\f\u000b\u0007\u0005EB&\u0001\u0006d_2dWm\u0019;j_:LA!!\u000e\u00020\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\r\t\u00151\b\u0005\n\u0003K\u0001\u0012\u0011!a\u0001\u0003;\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011\u0011AA!\u0011%\t)#EA\u0001\u0002\u0004\t\u0019\"\u0001\u0005iCND7i\u001c3f)\t\t\u0019\"\u0001\u0004fcV\fGn\u001d\u000b\u0004\u0003\u0006-\u0003\"CA\u0013'\u0005\u0005\t\u0019AA\u000fQ\r\u0001QiS\u0001\r\u0005&t\u0017M]=TC6\u0004H.\u001a\t\u00035V\u0019R!FA+\u0003C\u0002r!a\u0016\u0002^\u0005\u000b\u0016,\u0004\u0002\u0002Z)\u0019\u00111\f\u0017\u0002\u000fI,h\u000e^5nK&!\u0011qLA-\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003G\nI'\u0004\u0002\u0002f)!\u0011qMA\u0005\u0003\tIw.C\u0002>\u0003K\"\"!!\u0015\u0015\u0005\u0005\u0005\u0011!B1qa2LH#B-\u0002t\u0005]\u0004\"B \u0019\u0001\u0004\t\u0005\u0006BA:\u000b.CQa\u0014\rA\u0002ECC!a\u001eF\u0017\u00069QO\\1qa2LH\u0003BA@\u0003\u0017\u0003RaKAA\u0003\u000bK1!a!-\u0005\u0019y\u0005\u000f^5p]B)1&a\"B#&\u0019\u0011\u0011\u0012\u0017\u0003\rQ+\b\u000f\\33\u0011!\ti)GA\u0001\u0002\u0004I\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0013\t\u0005\u0003\u0007\t)*\u0003\u0003\u0002\u0018\u0006\u0015!AB(cU\u0016\u001cG\u000f"
)
public class BinarySample implements Product, Serializable {
   private final boolean isExperiment;
   private final double value;

   public static Option unapply(final BinarySample x$0) {
      return BinarySample$.MODULE$.unapply(x$0);
   }

   public static BinarySample apply(final boolean isExperiment, final double value) {
      return BinarySample$.MODULE$.apply(isExperiment, value);
   }

   public static Function1 tupled() {
      return BinarySample$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return BinarySample$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean isExperiment() {
      return this.isExperiment;
   }

   public double value() {
      return this.value;
   }

   public boolean getIsExperiment() {
      return this.isExperiment();
   }

   public double getValue() {
      return this.value();
   }

   public String toString() {
      boolean var10000 = this.isExperiment();
      return "(" + var10000 + ", " + this.value() + ")";
   }

   public BinarySample copy(final boolean isExperiment, final double value) {
      return new BinarySample(isExperiment, value);
   }

   public boolean copy$default$1() {
      return this.isExperiment();
   }

   public double copy$default$2() {
      return this.value();
   }

   public String productPrefix() {
      return "BinarySample";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToBoolean(this.isExperiment());
         }
         case 1 -> {
            return BoxesRunTime.boxToDouble(this.value());
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
      return x$1 instanceof BinarySample;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "isExperiment";
         }
         case 1 -> {
            return "value";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.isExperiment() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.doubleHash(this.value()));
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof BinarySample) {
               BinarySample var4 = (BinarySample)x$1;
               if (this.isExperiment() == var4.isExperiment() && this.value() == var4.value() && var4.canEqual(this)) {
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

   public BinarySample(final boolean isExperiment, final double value) {
      this.isExperiment = isExperiment;
      this.value = value;
      Product.$init$(this);
   }
}
