package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003\u001d!\u000b7OT;n\r\u0016\fG/\u001e:fg*\u0011aaB\u0001\u0007g\"\f'/\u001a3\u000b\u0005!I\u0011!\u00029be\u0006l'B\u0001\u0006\f\u0003\tiGN\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h\u0007\u0001\u00192\u0001A\n\u001a!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0011!dG\u0007\u0002\u000f%\u0011Ad\u0002\u0002\u0007!\u0006\u0014\u0018-\\:\u0002\r\u0011Jg.\u001b;%)\u0005y\u0002C\u0001\u000b!\u0013\t\tSC\u0001\u0003V]&$\u0018a\u00038v[\u001a+\u0017\r^;sKN,\u0012\u0001\n\t\u00035\u0015J!AJ\u0004\u0003\u0011%sG\u000fU1sC6\fabZ3u\u001dVlg)Z1ukJ,7/F\u0001*!\t!\"&\u0003\u0002,+\t\u0019\u0011J\u001c;"
)
public interface HasNumFeatures extends Params {
   void org$apache$spark$ml$param$shared$HasNumFeatures$_setter_$numFeatures_$eq(final IntParam x$1);

   IntParam numFeatures();

   // $FF: synthetic method
   static int getNumFeatures$(final HasNumFeatures $this) {
      return $this.getNumFeatures();
   }

   default int getNumFeatures() {
      return BoxesRunTime.unboxToInt(this.$(this.numFeatures()));
   }

   static void $init$(final HasNumFeatures $this) {
      $this.org$apache$spark$ml$param$shared$HasNumFeatures$_setter_$numFeatures_$eq(new IntParam($this, "numFeatures", "Number of features. Should be greater than 0", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.setDefault($this.numFeatures(), BoxesRunTime.boxToInteger(262144));
   }
}
