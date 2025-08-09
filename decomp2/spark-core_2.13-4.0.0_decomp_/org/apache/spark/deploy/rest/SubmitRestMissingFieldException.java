package org.apache.spark.deploy.rest;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192Qa\u0001\u0003\u0001\t9A\u0001b\u0005\u0001\u0003\u0002\u0003\u0006I!\u0006\u0005\u0006E\u0001!\ta\t\u0002 'V\u0014W.\u001b;SKN$X*[:tS:<g)[3mI\u0016C8-\u001a9uS>t'BA\u0003\u0007\u0003\u0011\u0011Xm\u001d;\u000b\u0005\u001dA\u0011A\u00023fa2|\u0017P\u0003\u0002\n\u0015\u0005)1\u000f]1sW*\u00111\u0002D\u0001\u0007CB\f7\r[3\u000b\u00035\t1a\u001c:h'\t\u0001q\u0002\u0005\u0002\u0011#5\tA!\u0003\u0002\u0013\t\tY2+\u001e2nSR\u0014Vm\u001d;Qe>$xnY8m\u000bb\u001cW\r\u001d;j_:\fq!\\3tg\u0006<Wm\u0001\u0001\u0011\u0005YybBA\f\u001e!\tA2$D\u0001\u001a\u0015\tQB#\u0001\u0004=e>|GO\u0010\u0006\u00029\u0005)1oY1mC&\u0011adG\u0001\u0007!J,G-\u001a4\n\u0005\u0001\n#AB*ue&twM\u0003\u0002\u001f7\u00051A(\u001b8jiz\"\"\u0001J\u0013\u0011\u0005A\u0001\u0001\"B\n\u0003\u0001\u0004)\u0002"
)
public class SubmitRestMissingFieldException extends SubmitRestProtocolException {
   public SubmitRestMissingFieldException(final String message) {
      super(message, SubmitRestProtocolException$.MODULE$.$lessinit$greater$default$2());
   }
}
