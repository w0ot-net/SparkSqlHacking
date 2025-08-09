package org.apache.spark.deploy.rest;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I2Q\u0001B\u0003\u0001\u000f=A\u0001\u0002\u0006\u0001\u0003\u0002\u0003\u0006IA\u0006\u0005\tG\u0001\u0011\t\u0011)A\u0005I!)Q\u0006\u0001C\u0001]\ti2+\u001e2nSR\u0014Vm\u001d;D_:tWm\u0019;j_:,\u0005pY3qi&|gN\u0003\u0002\u0007\u000f\u0005!!/Z:u\u0015\tA\u0011\"\u0001\u0004eKBdw.\u001f\u0006\u0003\u0015-\tQa\u001d9be.T!\u0001D\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0011aA8sON\u0011\u0001\u0001\u0005\t\u0003#Ii\u0011!B\u0005\u0003'\u0015\u00111dU;c[&$(+Z:u!J|Go\\2pY\u0016C8-\u001a9uS>t\u0017aB7fgN\fw-Z\u0002\u0001!\t9\u0002E\u0004\u0002\u0019=A\u0011\u0011\u0004H\u0007\u00025)\u00111$F\u0001\u0007yI|w\u000e\u001e \u000b\u0003u\tQa]2bY\u0006L!a\b\u000f\u0002\rA\u0013X\rZ3g\u0013\t\t#E\u0001\u0004TiJLgn\u001a\u0006\u0003?q\tQaY1vg\u0016\u0004\"!\n\u0016\u000f\u0005\u0019BcBA\r(\u0013\u0005i\u0012BA\u0015\u001d\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u000b\u0017\u0003\u0013QC'o\\<bE2,'BA\u0015\u001d\u0003\u0019a\u0014N\\5u}Q\u0019q\u0006M\u0019\u0011\u0005E\u0001\u0001\"\u0002\u000b\u0004\u0001\u00041\u0002\"B\u0012\u0004\u0001\u0004!\u0003"
)
public class SubmitRestConnectionException extends SubmitRestProtocolException {
   public SubmitRestConnectionException(final String message, final Throwable cause) {
      super(message, cause);
   }
}
