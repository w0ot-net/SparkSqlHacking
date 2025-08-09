package org.apache.spark.ml.tree;

import java.lang.invoke.SerializedLambda;
import java.util.Objects;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.mllib.tree.configuration.FeatureType$;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ea\u0001B\n\u0015\u0001}A\u0001B\u000b\u0001\u0003\u0006\u0004%\te\u000b\u0005\t_\u0001\u0011\t\u0011)A\u0005Y!A\u0001\u0007\u0001B\u0001B\u0003%\u0011\u0007\u0003\u00058\u0001\t\u0015\r\u0011\"\u0001,\u0011!\t\u0005A!A!\u0002\u0013a\u0003BB\"\u0001\t\u00031B\tC\u0004K\u0001\t\u0007I\u0011B&\t\r=\u0003\u0001\u0015!\u0003M\u0011\u001d\u0001\u0006A1A\u0005\nECa!\u0018\u0001!\u0002\u0013\u0011\u0006B\u00020\u0001\t\u00032r\f\u0003\u0004_\u0001\u0011\u0005c\u0003\u001b\u0005\u0006]\u0002!\te\u001c\u0005\u0006a\u0002!\t%\u001d\u0005\u0007o\u0002!\t\u0005\u0006=\t\u000f\u0005\r\u0001\u0001\"\u0001\u0002\u0006!9\u0011q\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\u0005\u0001\u0011%\u00111\u0002\u0002\u0011\u0007\u0006$XmZ8sS\u000e\fGn\u00159mSRT!!\u0006\f\u0002\tQ\u0014X-\u001a\u0006\u0003/a\t!!\u001c7\u000b\u0005eQ\u0012!B:qCJ\\'BA\u000e\u001d\u0003\u0019\t\u0007/Y2iK*\tQ$A\u0002pe\u001e\u001c\u0001aE\u0002\u0001A\u0019\u0002\"!\t\u0013\u000e\u0003\tR\u0011aI\u0001\u0006g\u000e\fG.Y\u0005\u0003K\t\u0012a!\u00118z%\u00164\u0007CA\u0014)\u001b\u0005!\u0012BA\u0015\u0015\u0005\u0015\u0019\u0006\u000f\\5u\u000311W-\u0019;ve\u0016Le\u000eZ3y+\u0005a\u0003CA\u0011.\u0013\tq#EA\u0002J]R\fQBZ3biV\u0014X-\u00138eKb\u0004\u0013aD0mK\u001a$8)\u0019;fO>\u0014\u0018.Z:\u0011\u0007\u0005\u0012D'\u0003\u00024E\t)\u0011I\u001d:bsB\u0011\u0011%N\u0005\u0003m\t\u0012a\u0001R8vE2,\u0017!\u00048v[\u000e\u000bG/Z4pe&,7\u000fK\u0002\u0005s}\u0002\"AO\u001f\u000e\u0003mR!\u0001\u0010\r\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002?w\t)1+\u001b8dK\u0006\n\u0001)A\u00033]Ar\u0003'\u0001\bok6\u001c\u0015\r^3h_JLWm\u001d\u0011)\u0007\u0015It(\u0001\u0004=S:LGO\u0010\u000b\u0005\u000b\u001a;\u0005\n\u0005\u0002(\u0001!)!F\u0002a\u0001Y!)\u0001G\u0002a\u0001c!)qG\u0002a\u0001Y!\u001a\u0001*O \u0002\r%\u001cH*\u001a4u+\u0005a\u0005CA\u0011N\u0013\tq%EA\u0004C_>dW-\u00198\u0002\u000f%\u001cH*\u001a4uA\u0005Q1-\u0019;fO>\u0014\u0018.Z:\u0016\u0003I\u00032a\u0015.5\u001d\t!\u0006\f\u0005\u0002VE5\taK\u0003\u0002X=\u00051AH]8pizJ!!\u0017\u0012\u0002\rA\u0013X\rZ3g\u0013\tYFLA\u0002TKRT!!\u0017\u0012\u0002\u0017\r\fG/Z4pe&,7\u000fI\u0001\rg\"|W\u000f\u001c3H_2+g\r\u001e\u000b\u0003\u0019\u0002DQ!Y\u0006A\u0002\t\f\u0001BZ3biV\u0014Xm\u001d\t\u0003G\u001al\u0011\u0001\u001a\u0006\u0003KZ\ta\u0001\\5oC2<\u0017BA4e\u0005\u00191Vm\u0019;peR\u0019A*[6\t\u000b)d\u0001\u0019\u0001\u0017\u0002\u001b\tLgN\\3e\r\u0016\fG/\u001e:f\u0011\u0015aG\u00021\u0001n\u0003\u0019\u0019\b\u000f\\5ugB\u0019\u0011E\r\u0014\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001L\u0001\u0007KF,\u0018\r\\:\u0015\u00051\u0013\b\"B:\u000f\u0001\u0004!\u0018!A8\u0011\u0005\u0005*\u0018B\u0001<#\u0005\r\te._\u0001\u0006i>|E\u000eZ\u000b\u0002sB\u0019!0!\u0001\u000e\u0003mT!\u0001`?\u0002\u000b5|G-\u001a7\u000b\u0005Uq(BA@\u0019\u0003\u0015iG\u000e\\5c\u0013\tI30\u0001\bmK\u001a$8)\u0019;fO>\u0014\u0018.Z:\u0016\u0003E\nqB]5hQR\u001c\u0015\r^3h_JLWm]\u0001\u000eg\u0016$8i\\7qY\u0016lWM\u001c;\u0015\u0007I\u000bi\u0001\u0003\u0004\u0002\u0010I\u0001\rAU\u0001\u0005G\u0006$8\u000f"
)
public class CategoricalSplit implements Split {
   private final int featureIndex;
   private final double[] _leftCategories;
   private final int numCategories;
   private final boolean isLeft;
   private final Set categories;

   public int featureIndex() {
      return this.featureIndex;
   }

   public int numCategories() {
      return this.numCategories;
   }

   private boolean isLeft() {
      return this.isLeft;
   }

   private Set categories() {
      return this.categories;
   }

   public boolean shouldGoLeft(final Vector features) {
      if (this.isLeft()) {
         return this.categories().contains(BoxesRunTime.boxToDouble(features.apply(this.featureIndex())));
      } else {
         return !this.categories().contains(BoxesRunTime.boxToDouble(features.apply(this.featureIndex())));
      }
   }

   public boolean shouldGoLeft(final int binnedFeature, final Split[] splits) {
      if (this.isLeft()) {
         return this.categories().contains(BoxesRunTime.boxToDouble((double)binnedFeature));
      } else {
         return !this.categories().contains(BoxesRunTime.boxToDouble((double)binnedFeature));
      }
   }

   public int hashCode() {
      Seq state = (Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.featureIndex()), BoxesRunTime.boxToBoolean(this.isLeft()), this.categories()}));
      return BoxesRunTime.unboxToInt(((IterableOnceOps)state.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$hashCode$1(x$1)))).foldLeft(BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(a, b) -> 31 * a + b));
   }

   public boolean equals(final Object o) {
      if (!(o instanceof CategoricalSplit var4)) {
         return false;
      } else {
         boolean var6;
         label34: {
            if (this.featureIndex() == var4.featureIndex() && this.isLeft() == var4.isLeft()) {
               Set var10000 = this.categories();
               Set var5 = var4.categories();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label34;
                  }
               } else if (var10000.equals(var5)) {
                  break label34;
               }
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   public org.apache.spark.mllib.tree.model.Split toOld() {
      Set oldCats = this.isLeft() ? this.categories() : this.setComplement(this.categories());
      return new org.apache.spark.mllib.tree.model.Split(this.featureIndex(), (double)0.0F, FeatureType$.MODULE$.Categorical(), oldCats.toList());
   }

   public double[] leftCategories() {
      Set cats = this.isLeft() ? this.categories() : this.setComplement(this.categories());
      return (double[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps((double[])cats.toArray(scala.reflect.ClassTag..MODULE$.Double())), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
   }

   public double[] rightCategories() {
      Set cats = this.isLeft() ? this.setComplement(this.categories()) : this.categories();
      return (double[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps((double[])cats.toArray(scala.reflect.ClassTag..MODULE$.Double())), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
   }

   private Set setComplement(final Set cats) {
      return ((IterableOnceOps).MODULE$.Range().apply(0, this.numCategories()).map((JFunction1.mcDI.sp)(x$1) -> (double)x$1).filter((JFunction1.mcZD.sp)(cat) -> !cats.contains(BoxesRunTime.boxToDouble(cat)))).toSet();
   }

   // $FF: synthetic method
   public static final int $anonfun$hashCode$1(final Object x$1) {
      return Objects.hashCode(x$1);
   }

   public CategoricalSplit(final int featureIndex, final double[] _leftCategories, final int numCategories) {
      this.featureIndex = featureIndex;
      this._leftCategories = _leftCategories;
      this.numCategories = numCategories;
      scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.doubleArrayOps(_leftCategories), (JFunction1.mcZD.sp)(cat) -> (double)0 <= cat && cat < (double)this.numCategories()), () -> {
         int var10000 = this.numCategories();
         return "Invalid leftCategories (should be in range [0, " + var10000 + ")): " + scala.Predef..MODULE$.wrapDoubleArray(this._leftCategories).mkString(",");
      });
      this.isLeft = _leftCategories.length <= numCategories / 2;
      this.categories = this.isLeft() ? scala.Predef..MODULE$.wrapDoubleArray(_leftCategories).toSet() : this.setComplement(scala.Predef..MODULE$.wrapDoubleArray(_leftCategories).toSet());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
