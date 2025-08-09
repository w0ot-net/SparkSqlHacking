package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.IntArrayParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.ml.param.shared.HasRelativeError;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001B\u0005\u0005\u0006i\u0001!\tA\u000e\u0005\bu\u0001\u0011\r\u0011\"\u0001<\u0011\u0015y\u0004\u0001\"\u0001A\u0011\u001d!\u0005A1A\u0005\u0002\u0015CQ!\u0013\u0001\u0005\u0002)CqA\u0014\u0001C\u0002\u0013\u0005sJA\fRk\u0006tG/\u001b7f\t&\u001c8M]3uSj,'OQ1tK*\u0011\u0011BC\u0001\bM\u0016\fG/\u001e:f\u0015\tYA\"\u0001\u0002nY*\u0011QBD\u0001\u0006gB\f'o\u001b\u0006\u0003\u001fA\ta!\u00199bG\",'\"A\t\u0002\u0007=\u0014xmE\u0005\u0001'eyR\u0005K\u0016/cA\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t1\u0011I\\=SK\u001a\u0004\"AG\u000f\u000e\u0003mQ!\u0001\b\u0006\u0002\u000bA\f'/Y7\n\u0005yY\"A\u0002)be\u0006l7\u000f\u0005\u0002!G5\t\u0011E\u0003\u0002#7\u000511\u000f[1sK\u0012L!\u0001J\u0011\u0003!!\u000b7\u000fS1oI2,\u0017J\u001c<bY&$\u0007C\u0001\u0011'\u0013\t9\u0013EA\u0006ICNLe\u000e];u\u0007>d\u0007C\u0001\u0011*\u0013\tQ\u0013E\u0001\u0007ICN|U\u000f\u001e9vi\u000e{G\u000e\u0005\u0002!Y%\u0011Q&\t\u0002\r\u0011\u0006\u001c\u0018J\u001c9vi\u000e{Gn\u001d\t\u0003A=J!\u0001M\u0011\u0003\u001b!\u000b7oT;uaV$8i\u001c7t!\t\u0001#'\u0003\u00024C\t\u0001\u0002*Y:SK2\fG/\u001b<f\u000bJ\u0014xN]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tq\u0007\u0005\u0002\u0015q%\u0011\u0011(\u0006\u0002\u0005+:LG/\u0001\u0006ok6\u0014UoY6fiN,\u0012\u0001\u0010\t\u00035uJ!AP\u000e\u0003\u0011%sG\u000fU1sC6\fQbZ3u\u001dVl')^2lKR\u001cX#A!\u0011\u0005Q\u0011\u0015BA\"\u0016\u0005\rIe\u000e^\u0001\u0010]Vl')^2lKR\u001c\u0018I\u001d:bsV\ta\t\u0005\u0002\u001b\u000f&\u0011\u0001j\u0007\u0002\u000e\u0013:$\u0018I\u001d:bsB\u000b'/Y7\u0002%\u001d,GOT;n\u0005V\u001c7.\u001a;t\u0003J\u0014\u0018-_\u000b\u0002\u0017B\u0019A\u0003T!\n\u00055+\"!B!se\u0006L\u0018!\u00045b]\u0012dW-\u00138wC2LG-F\u0001Q!\rQ\u0012kU\u0005\u0003%n\u0011Q\u0001U1sC6\u0004\"\u0001V.\u000f\u0005UK\u0006C\u0001,\u0016\u001b\u00059&B\u0001-6\u0003\u0019a$o\\8u}%\u0011!,F\u0001\u0007!J,G-\u001a4\n\u0005qk&AB*ue&twM\u0003\u0002[+!\u001aaaX3\u0011\u0005\u0001\u001cW\"A1\u000b\u0005\td\u0011AC1o]>$\u0018\r^5p]&\u0011A-\u0019\u0002\u0006'&t7-Z\u0011\u0002M\u0006)!GL\u0019/a\u0001"
)
public interface QuantileDiscretizerBase extends HasHandleInvalid, HasInputCol, HasOutputCol, HasInputCols, HasOutputCols, HasRelativeError {
   void org$apache$spark$ml$feature$QuantileDiscretizerBase$_setter_$numBuckets_$eq(final IntParam x$1);

   void org$apache$spark$ml$feature$QuantileDiscretizerBase$_setter_$numBucketsArray_$eq(final IntArrayParam x$1);

   void org$apache$spark$ml$feature$QuantileDiscretizerBase$_setter_$handleInvalid_$eq(final Param x$1);

   IntParam numBuckets();

   // $FF: synthetic method
   static int getNumBuckets$(final QuantileDiscretizerBase $this) {
      return $this.getNumBuckets();
   }

   default int getNumBuckets() {
      return BoxesRunTime.unboxToInt(this.getOrDefault(this.numBuckets()));
   }

   IntArrayParam numBucketsArray();

   // $FF: synthetic method
   static int[] getNumBucketsArray$(final QuantileDiscretizerBase $this) {
      return $this.getNumBucketsArray();
   }

   default int[] getNumBucketsArray() {
      return (int[])this.$(this.numBucketsArray());
   }

   Param handleInvalid();

   // $FF: synthetic method
   static boolean $anonfun$numBucketsArray$1(final int[] arrayOfNumBuckets) {
      return .MODULE$.forall$extension(scala.Predef..MODULE$.intArrayOps(arrayOfNumBuckets), ParamValidators$.MODULE$.gtEq((double)2.0F));
   }

   static void $init$(final QuantileDiscretizerBase $this) {
      $this.org$apache$spark$ml$feature$QuantileDiscretizerBase$_setter_$numBuckets_$eq(new IntParam($this, "numBuckets", "Number of buckets (quantiles, or categories) into which data points are grouped. Must be >= 2.", ParamValidators$.MODULE$.gtEq((double)2.0F)));
      $this.org$apache$spark$ml$feature$QuantileDiscretizerBase$_setter_$numBucketsArray_$eq(new IntArrayParam($this, "numBucketsArray", "Array of number of buckets (quantiles, or categories) into which data points are grouped. This is for multiple columns input. If transforming multiple columns and numBucketsArray is not set, but numBuckets is set, then numBuckets will be applied across all columns.", (arrayOfNumBuckets) -> BoxesRunTime.boxToBoolean($anonfun$numBucketsArray$1(arrayOfNumBuckets))));
      $this.org$apache$spark$ml$feature$QuantileDiscretizerBase$_setter_$handleInvalid_$eq(new Param($this, "handleInvalid", "how to handle invalid entries. Options are skip (filter out rows with invalid values), error (throw an error), or keep (keep invalid values in a special additional bucket).", ParamValidators$.MODULE$.inArray((Object)Bucketizer$.MODULE$.supportedHandleInvalids()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.handleInvalid().$minus$greater(Bucketizer$.MODULE$.ERROR_INVALID()), $this.numBuckets().$minus$greater(BoxesRunTime.boxToInteger(2))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
