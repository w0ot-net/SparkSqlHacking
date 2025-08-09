package org.apache.spark.ml.feature;

import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512\u0001\u0002B\u0003\u0011\u0002\u0007\u0005qa\u0004\u0005\u00069\u0001!\tA\b\u0005\bE\u0001\u0011\r\u0011\"\u0001$\u0011\u00159\u0003\u0001\"\u0002)\u0005\u0005\u0012UoY6fi\u0016$'+\u00198e_6\u0004&o\u001c6fGRLwN\u001c'T\u0011B\u000b'/Y7t\u0015\t1q!A\u0004gK\u0006$XO]3\u000b\u0005!I\u0011AA7m\u0015\tQ1\"A\u0003ta\u0006\u00148N\u0003\u0002\r\u001b\u00051\u0011\r]1dQ\u0016T\u0011AD\u0001\u0004_J<7c\u0001\u0001\u0011-A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\u0004\"a\u0006\u000e\u000e\u0003aQ!!G\u0004\u0002\u000bA\f'/Y7\n\u0005mA\"A\u0002)be\u0006l7/\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005y\u0002CA\t!\u0013\t\t#C\u0001\u0003V]&$\u0018\u0001\u00042vG.,G\u000fT3oORDW#\u0001\u0013\u0011\u0005])\u0013B\u0001\u0014\u0019\u0005-!u.\u001e2mKB\u000b'/Y7\u0002\u001f\u001d,GOQ;dW\u0016$H*\u001a8hi\",\u0012!\u000b\t\u0003#)J!a\u000b\n\u0003\r\u0011{WO\u00197f\u0001"
)
public interface BucketedRandomProjectionLSHParams extends Params {
   void org$apache$spark$ml$feature$BucketedRandomProjectionLSHParams$_setter_$bucketLength_$eq(final DoubleParam x$1);

   DoubleParam bucketLength();

   // $FF: synthetic method
   static double getBucketLength$(final BucketedRandomProjectionLSHParams $this) {
      return $this.getBucketLength();
   }

   default double getBucketLength() {
      return BoxesRunTime.unboxToDouble(this.$(this.bucketLength()));
   }

   static void $init$(final BucketedRandomProjectionLSHParams $this) {
      $this.org$apache$spark$ml$feature$BucketedRandomProjectionLSHParams$_setter_$bucketLength_$eq(new DoubleParam($this, "bucketLength", "the length of each hash bucket, a larger bucket lowers the false negative rate.", ParamValidators$.MODULE$.gt((double)0.0F)));
   }
}
