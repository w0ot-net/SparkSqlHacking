package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005Q2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007I\u0011A\u0012\t\u000bI\u0002AQA\u001a\u0003\u000f!\u000b7\u000fT8tg*\u0011aaB\u0001\u0007g\"\f'/\u001a3\u000b\u0005!I\u0011!\u00029be\u0006l'B\u0001\u0006\f\u0003\tiGN\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h\u0007\u0001\u00192\u0001A\n\u001a!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0011!dG\u0007\u0002\u000f%\u0011Ad\u0002\u0002\u0007!\u0006\u0014\u0018-\\:\u0002\r\u0011Jg.\u001b;%)\u0005y\u0002C\u0001\u000b!\u0013\t\tSC\u0001\u0003V]&$\u0018\u0001\u00027pgN,\u0012\u0001\n\t\u00045\u0015:\u0013B\u0001\u0014\b\u0005\u0015\u0001\u0016M]1n!\tAsF\u0004\u0002*[A\u0011!&F\u0007\u0002W)\u0011A&E\u0001\u0007yI|w\u000e\u001e \n\u00059*\u0012A\u0002)sK\u0012,g-\u0003\u00021c\t11\u000b\u001e:j]\u001eT!AL\u000b\u0002\u000f\u001d,G\u000fT8tgV\tq\u0005"
)
public interface HasLoss extends Params {
   void org$apache$spark$ml$param$shared$HasLoss$_setter_$loss_$eq(final Param x$1);

   Param loss();

   // $FF: synthetic method
   static String getLoss$(final HasLoss $this) {
      return $this.getLoss();
   }

   default String getLoss() {
      return (String)this.$(this.loss());
   }

   static void $init$(final HasLoss $this) {
      $this.org$apache$spark$ml$param$shared$HasLoss$_setter_$loss_$eq(new Param($this, "loss", "the loss function to be optimized", .MODULE$.apply(String.class)));
   }
}
