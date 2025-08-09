package org.apache.spark.ml.regression;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed!\u0002\u000f\u001e\u0001~9\u0003\u0002\u0003 \u0001\u0005+\u0007I\u0011A \t\u0011\u0019\u0003!\u0011#Q\u0001\n\u0001C\u0001b\u0012\u0001\u0003\u0016\u0004%\t\u0001\u0013\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005\u0013\"AQ\n\u0001BK\u0002\u0013\u0005\u0001\n\u0003\u0005O\u0001\tE\t\u0015!\u0003J\u0011\u0015y\u0005\u0001\"\u0001Q\u0011\u001d1\u0006!!A\u0005\u0002]Cqa\u0017\u0001\u0012\u0002\u0013\u0005A\fC\u0004h\u0001E\u0005I\u0011\u00015\t\u000f)\u0004\u0011\u0013!C\u0001Q\"91\u000eAA\u0001\n\u0003b\u0007bB;\u0001\u0003\u0003%\tA\u001e\u0005\bu\u0002\t\t\u0011\"\u0001|\u0011%\t\u0019\u0001AA\u0001\n\u0003\n)\u0001C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0001\u0002\u0016!I\u0011q\u0004\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0005\u0005\n\u0003K\u0001\u0011\u0011!C!\u0003OA\u0011\"!\u000b\u0001\u0003\u0003%\t%a\u000b\t\u0013\u00055\u0002!!A\u0005B\u0005=rACA\u001a;\u0005\u0005\t\u0012A\u0010\u00026\u0019IA$HA\u0001\u0012\u0003y\u0012q\u0007\u0005\u0007\u001fZ!\t!a\u0014\t\u0013\u0005%b#!A\u0005F\u0005-\u0002\"CA)-\u0005\u0005I\u0011QA*\u0011%\tYFFA\u0001\n\u0003\u000bi\u0006C\u0005\u0002pY\t\t\u0011\"\u0003\u0002r\tA\u0011I\u0012+Q_&tGO\u0003\u0002\u001f?\u0005Q!/Z4sKN\u001c\u0018n\u001c8\u000b\u0005\u0001\n\u0013AA7m\u0015\t\u00113%A\u0003ta\u0006\u00148N\u0003\u0002%K\u00051\u0011\r]1dQ\u0016T\u0011AJ\u0001\u0004_J<7\u0003\u0002\u0001)]E\u0002\"!\u000b\u0017\u000e\u0003)R\u0011aK\u0001\u0006g\u000e\fG.Y\u0005\u0003[)\u0012a!\u00118z%\u00164\u0007CA\u00150\u0013\t\u0001$FA\u0004Qe>$Wo\u0019;\u0011\u0005IZdBA\u001a:\u001d\t!\u0004(D\u00016\u0015\t1t'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Y\u0013B\u0001\u001e+\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001P\u001f\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005iR\u0013\u0001\u00034fCR,(/Z:\u0016\u0003\u0001\u0003\"!\u0011#\u000e\u0003\tS!aQ\u0010\u0002\r1Lg.\u00197h\u0013\t)%I\u0001\u0004WK\u000e$xN]\u0001\nM\u0016\fG/\u001e:fg\u0002\nQ\u0001\\1cK2,\u0012!\u0013\t\u0003S)K!a\u0013\u0016\u0003\r\u0011{WO\u00197f\u0003\u0019a\u0017MY3mA\u000511-\u001a8t_J\fqaY3og>\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\u0005#N#V\u000b\u0005\u0002S\u00015\tQ\u0004C\u0003?\u000f\u0001\u0007\u0001\tC\u0003H\u000f\u0001\u0007\u0011\nC\u0003N\u000f\u0001\u0007\u0011*\u0001\u0003d_BLH\u0003B)Y3jCqA\u0010\u0005\u0011\u0002\u0003\u0007\u0001\tC\u0004H\u0011A\u0005\t\u0019A%\t\u000f5C\u0001\u0013!a\u0001\u0013\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A/+\u0005\u0001s6&A0\u0011\u0005\u0001,W\"A1\u000b\u0005\t\u001c\u0017!C;oG\",7m[3e\u0015\t!'&\u0001\u0006b]:|G/\u0019;j_:L!AZ1\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003%T#!\u00130\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u001c\t\u0003]Nl\u0011a\u001c\u0006\u0003aF\fA\u0001\\1oO*\t!/\u0001\u0003kCZ\f\u0017B\u0001;p\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tq\u000f\u0005\u0002*q&\u0011\u0011P\u000b\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003y~\u0004\"!K?\n\u0005yT#aA!os\"A\u0011\u0011\u0001\b\u0002\u0002\u0003\u0007q/A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u000f\u0001R!!\u0003\u0002\u0010ql!!a\u0003\u000b\u0007\u00055!&\u0001\u0006d_2dWm\u0019;j_:LA!!\u0005\u0002\f\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t9\"!\b\u0011\u0007%\nI\"C\u0002\u0002\u001c)\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u0002A\t\t\u00111\u0001}\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u00075\f\u0019\u0003\u0003\u0005\u0002\u0002E\t\t\u00111\u0001x\u0003!A\u0017m\u001d5D_\u0012,G#A<\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!\\\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005]\u0011\u0011\u0007\u0005\t\u0003\u0003!\u0012\u0011!a\u0001y\u0006A\u0011I\u0012+Q_&tG\u000f\u0005\u0002S-M)a#!\u000f\u0002FAA\u00111HA!\u0001&K\u0015+\u0004\u0002\u0002>)\u0019\u0011q\b\u0016\u0002\u000fI,h\u000e^5nK&!\u00111IA\u001f\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u0003\u000f\ni%\u0004\u0002\u0002J)\u0019\u00111J9\u0002\u0005%|\u0017b\u0001\u001f\u0002JQ\u0011\u0011QG\u0001\u0006CB\u0004H.\u001f\u000b\b#\u0006U\u0013qKA-\u0011\u0015q\u0014\u00041\u0001A\u0011\u00159\u0015\u00041\u0001J\u0011\u0015i\u0015\u00041\u0001J\u0003\u001d)h.\u00199qYf$B!a\u0018\u0002lA)\u0011&!\u0019\u0002f%\u0019\u00111\r\u0016\u0003\r=\u0003H/[8o!\u0019I\u0013q\r!J\u0013&\u0019\u0011\u0011\u000e\u0016\u0003\rQ+\b\u000f\\34\u0011!\tiGGA\u0001\u0002\u0004\t\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u000f\t\u0004]\u0006U\u0014bAA<_\n1qJ\u00196fGR\u0004"
)
public class AFTPoint implements Product, Serializable {
   private final Vector features;
   private final double label;
   private final double censor;

   public static Option unapply(final AFTPoint x$0) {
      return AFTPoint$.MODULE$.unapply(x$0);
   }

   public static AFTPoint apply(final Vector features, final double label, final double censor) {
      return AFTPoint$.MODULE$.apply(features, label, censor);
   }

   public static Function1 tupled() {
      return AFTPoint$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return AFTPoint$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Vector features() {
      return this.features;
   }

   public double label() {
      return this.label;
   }

   public double censor() {
      return this.censor;
   }

   public AFTPoint copy(final Vector features, final double label, final double censor) {
      return new AFTPoint(features, label, censor);
   }

   public Vector copy$default$1() {
      return this.features();
   }

   public double copy$default$2() {
      return this.label();
   }

   public double copy$default$3() {
      return this.censor();
   }

   public String productPrefix() {
      return "AFTPoint";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.features();
         }
         case 1 -> {
            return BoxesRunTime.boxToDouble(this.label());
         }
         case 2 -> {
            return BoxesRunTime.boxToDouble(this.censor());
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
      return x$1 instanceof AFTPoint;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "features";
         }
         case 1 -> {
            return "label";
         }
         case 2 -> {
            return "censor";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.features()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.label()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.censor()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof AFTPoint) {
               AFTPoint var4 = (AFTPoint)x$1;
               if (this.label() == var4.label() && this.censor() == var4.censor()) {
                  label48: {
                     Vector var10000 = this.features();
                     Vector var5 = var4.features();
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

   public AFTPoint(final Vector features, final double label, final double censor) {
      this.features = features;
      this.label = label;
      this.censor = censor;
      Product.$init$(this);
      scala.Predef..MODULE$.require(censor == (double)1.0F || censor == (double)0.0F, () -> "censor of class AFTPoint must be 1.0 or 0.0");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
