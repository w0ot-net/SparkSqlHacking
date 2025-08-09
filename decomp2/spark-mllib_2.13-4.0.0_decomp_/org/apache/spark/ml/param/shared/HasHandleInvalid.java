package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005Q2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007I\u0011A\u0012\t\u000bI\u0002AQA\u001a\u0003!!\u000b7\u000fS1oI2,\u0017J\u001c<bY&$'B\u0001\u0004\b\u0003\u0019\u0019\b.\u0019:fI*\u0011\u0001\"C\u0001\u0006a\u0006\u0014\u0018-\u001c\u0006\u0003\u0015-\t!!\u001c7\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001'e\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0007C\u0001\u000e\u001c\u001b\u00059\u0011B\u0001\u000f\b\u0005\u0019\u0001\u0016M]1ng\u00061A%\u001b8ji\u0012\"\u0012a\b\t\u0003)\u0001J!!I\u000b\u0003\tUs\u0017\u000e^\u0001\u000eQ\u0006tG\r\\3J]Z\fG.\u001b3\u0016\u0003\u0011\u00022AG\u0013(\u0013\t1sAA\u0003QCJ\fW\u000e\u0005\u0002)_9\u0011\u0011&\f\t\u0003UUi\u0011a\u000b\u0006\u0003YE\ta\u0001\u0010:p_Rt\u0014B\u0001\u0018\u0016\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001'\r\u0002\u0007'R\u0014\u0018N\\4\u000b\u00059*\u0012\u0001E4fi\"\u000bg\u000e\u001a7f\u0013:4\u0018\r\\5e+\u00059\u0003"
)
public interface HasHandleInvalid extends Params {
   void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1);

   Param handleInvalid();

   // $FF: synthetic method
   static String getHandleInvalid$(final HasHandleInvalid $this) {
      return $this.getHandleInvalid();
   }

   default String getHandleInvalid() {
      return (String)this.$(this.handleInvalid());
   }

   static void $init$(final HasHandleInvalid $this) {
      $this.org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(new Param($this, "handleInvalid", "how to handle invalid entries. Options are skip (which will filter out rows with bad values), or error (which will throw an error). More options may be added later", ParamValidators$.MODULE$.inArray((Object)((Object[])(new String[]{"skip", "error"}))), .MODULE$.apply(String.class)));
   }
}
