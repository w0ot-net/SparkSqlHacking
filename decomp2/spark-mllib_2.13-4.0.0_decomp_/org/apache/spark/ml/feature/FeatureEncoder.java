package org.apache.spark.ml.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.ml.linalg.Vector;
import scala.Function2;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005-3Q\u0001C\u0005\u0001\u0017MA\u0001b\n\u0001\u0003\u0002\u0003\u0006I\u0001\u000b\u0005\u0006]\u0001!\ta\f\u0005\bg\u0001\u0011\r\u0011\"\u00015\u0011\u0019)\u0004\u0001)A\u0005W!9a\u0007\u0001b\u0001\n\u00139\u0004B\u0002\u001d\u0001A\u0003%\u0001\u0006C\u0003:\u0001\u0011\u0005!H\u0001\bGK\u0006$XO]3F]\u000e|G-\u001a:\u000b\u0005)Y\u0011a\u00024fCR,(/\u001a\u0006\u0003\u00195\t!!\u001c7\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e\u001c2\u0001\u0001\u000b\u001b!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fMB\u00111\u0004\n\b\u00039\tr!!H\u0011\u000e\u0003yQ!a\b\u0011\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011aF\u0005\u0003GY\tq\u0001]1dW\u0006<W-\u0003\u0002&M\ta1+\u001a:jC2L'0\u00192mK*\u00111EF\u0001\f]Vlg)Z1ukJ,7\u000fE\u0002\u0016S-J!A\u000b\f\u0003\u000b\u0005\u0013(/Y=\u0011\u0005Ua\u0013BA\u0017\u0017\u0005\rIe\u000e^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005A\u0012\u0004CA\u0019\u0001\u001b\u0005I\u0001\"B\u0014\u0003\u0001\u0004A\u0013AC8viB,HoU5{KV\t1&A\u0006pkR\u0004X\u000f^*ju\u0016\u0004\u0013!D8viB,Ho\u00144gg\u0016$8/F\u0001)\u00039yW\u000f\u001e9vi>3gm]3ug\u0002\nACZ8sK\u0006\u001c\u0007NT8ou\u0016\u0014xnT;uaV$HcA\u001e?\u0007B\u0011Q\u0003P\u0005\u0003{Y\u0011A!\u00168ji\")qh\u0002a\u0001\u0001\u0006)a/\u00197vKB\u0011Q#Q\u0005\u0003\u0005Z\u00111!\u00118z\u0011\u0015!u\u00011\u0001F\u0003\u00051\u0007#B\u000bGW![\u0014BA$\u0017\u0005%1UO\\2uS>t'\u0007\u0005\u0002\u0016\u0013&\u0011!J\u0006\u0002\u0007\t>,(\r\\3"
)
public class FeatureEncoder implements Serializable {
   private final int[] numFeatures;
   private final int outputSize;
   private final int[] outputOffsets;

   public int outputSize() {
      return this.outputSize;
   }

   private int[] outputOffsets() {
      return this.outputOffsets;
   }

   public void foreachNonzeroOutput(final Object value, final Function2 f) {
      if (value instanceof Double) {
         double var5 = BoxesRunTime.unboxToDouble(value);
         .MODULE$.assert(this.numFeatures.length == 1, () -> org.apache.spark.sql.types.DoubleType..MODULE$.catalogString() + " columns should only contain one feature.");
         int numOutputCols = BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.head$extension(.MODULE$.intArrayOps(this.numFeatures)));
         if (numOutputCols <= 1) {
            f.apply$mcVID$sp(0, var5);
            BoxedUnit var10 = BoxedUnit.UNIT;
         } else {
            .MODULE$.assert(var5 >= (double)0.0F && var5 == (double)((int)var5) && var5 < (double)numOutputCols, () -> "Values from column must be indices, but got " + var5 + ".");
            f.apply$mcVID$sp((int)var5, (double)1.0F);
            BoxedUnit var9 = BoxedUnit.UNIT;
         }
      } else if (value instanceof Vector) {
         Vector var8 = (Vector)value;
         .MODULE$.assert(this.numFeatures.length == var8.size(), () -> {
            int var10000 = var8.size();
            return "Vector column size was " + var10000 + ", expected " + this.numFeatures.length;
         });
         var8.foreachActive((JFunction2.mcVID.sp)(i, v) -> {
            int numOutputCols = this.numFeatures[i];
            if (numOutputCols <= 1) {
               f.apply$mcVID$sp(this.outputOffsets()[i], v);
            } else {
               .MODULE$.assert(v >= (double)0.0F && v == (double)((int)v) && v < (double)numOutputCols, () -> "Values from column must be indices, but got " + v + ".");
               f.apply$mcVID$sp(this.outputOffsets()[i] + (int)v, (double)1.0F);
            }
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else if (value == null) {
         throw new SparkException("Values to interact cannot be null.");
      } else {
         throw new SparkException(value + " of type " + value.getClass().getName() + " is not supported.");
      }
   }

   public FeatureEncoder(final int[] numFeatures) {
      this.numFeatures = numFeatures;
      .MODULE$.assert(scala.collection.ArrayOps..MODULE$.forall$extension(.MODULE$.intArrayOps(numFeatures), (JFunction1.mcZI.sp)(x$1) -> x$1 > 0), () -> "Features counts must all be positive.");
      this.outputSize = BoxesRunTime.unboxToInt(.MODULE$.wrapIntArray(numFeatures).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      int[] arr = new int[numFeatures.length];

      for(int i = 1; i < arr.length; ++i) {
         arr[i] = arr[i - 1] + numFeatures[i - 1];
      }

      this.outputOffsets = arr;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
