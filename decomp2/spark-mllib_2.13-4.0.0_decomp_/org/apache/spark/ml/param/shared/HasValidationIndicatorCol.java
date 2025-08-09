package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005Q2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000bI\u0002AQA\u001a\u00033!\u000b7OV1mS\u0012\fG/[8o\u0013:$\u0017nY1u_J\u001cu\u000e\u001c\u0006\u0003\r\u001d\taa\u001d5be\u0016$'B\u0001\u0005\n\u0003\u0015\u0001\u0018M]1n\u0015\tQ1\"\u0001\u0002nY*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001\u0019\u0012\u0004\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u00035mi\u0011aB\u0005\u00039\u001d\u0011a\u0001U1sC6\u001c\u0018A\u0002\u0013j]&$H\u0005F\u0001 !\t!\u0002%\u0003\u0002\"+\t!QK\\5u\u0003Y1\u0018\r\\5eCRLwN\\%oI&\u001c\u0017\r^8s\u0007>dW#\u0001\u0013\u0011\u0007i)s%\u0003\u0002'\u000f\t)\u0001+\u0019:b[B\u0011\u0001f\f\b\u0003S5\u0002\"AK\u000b\u000e\u0003-R!\u0001L\t\u0002\rq\u0012xn\u001c;?\u0013\tqS#\u0001\u0004Qe\u0016$WMZ\u0005\u0003aE\u0012aa\u0015;sS:<'B\u0001\u0018\u0016\u0003e9W\r\u001e,bY&$\u0017\r^5p]&sG-[2bi>\u00148i\u001c7\u0016\u0003\u001d\u0002"
)
public interface HasValidationIndicatorCol extends Params {
   void org$apache$spark$ml$param$shared$HasValidationIndicatorCol$_setter_$validationIndicatorCol_$eq(final Param x$1);

   Param validationIndicatorCol();

   // $FF: synthetic method
   static String getValidationIndicatorCol$(final HasValidationIndicatorCol $this) {
      return $this.getValidationIndicatorCol();
   }

   default String getValidationIndicatorCol() {
      return (String)this.$(this.validationIndicatorCol());
   }

   static void $init$(final HasValidationIndicatorCol $this) {
      $this.org$apache$spark$ml$param$shared$HasValidationIndicatorCol$_setter_$validationIndicatorCol_$eq(new Param($this, "validationIndicatorCol", "name of the column that indicates whether each row is for training or for validation. False indicates training; true indicates validation.", .MODULE$.apply(String.class)));
   }
}
