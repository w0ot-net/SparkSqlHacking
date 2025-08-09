package scala.xml.include;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2A!\u0003\u0006\u0001#!A!\u0004\u0001B\u0001B\u0003%1\u0004C\u0003'\u0001\u0011\u0005q\u0005C\u0003'\u0001\u0011\u00051\u0006C\u0005-\u0001\u0001\u0007\t\u0019!C\u0005[!I\u0011\u0007\u0001a\u0001\u0002\u0004%IA\r\u0005\nq\u0001\u0001\r\u0011!Q!\n9BQ!\u000f\u0001\u0005\u0002iBQ!\u0010\u0001\u0005\u00025\u0012\u0011\u0003W%oG2,H-Z#yG\u0016\u0004H/[8o\u0015\tYA\"A\u0004j]\u000edW\u000fZ3\u000b\u00055q\u0011a\u0001=nY*\tq\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001\u0011\u0002CA\n\u0018\u001d\t!R#D\u0001\u000f\u0013\t1b\"A\u0004qC\u000e\\\u0017mZ3\n\u0005aI\"!C#yG\u0016\u0004H/[8o\u0015\t1b\"A\u0004nKN\u001c\u0018mZ3\u0011\u0005q\u0019cBA\u000f\"!\tqb\"D\u0001 \u0015\t\u0001\u0003#\u0001\u0004=e>|GOP\u0005\u0003E9\ta\u0001\u0015:fI\u00164\u0017B\u0001\u0013&\u0005\u0019\u0019FO]5oO*\u0011!ED\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005!R\u0003CA\u0015\u0001\u001b\u0005Q\u0001\"\u0002\u000e\u0003\u0001\u0004YB#\u0001\u0015\u0002\u0013I|w\u000e^\"bkN,W#\u0001\u0018\u0011\u0005My\u0013B\u0001\u0019\u001a\u0005%!\u0006N]8xC\ndW-A\u0007s_>$8)Y;tK~#S-\u001d\u000b\u0003gY\u0002\"\u0001\u0006\u001b\n\u0005Ur!\u0001B+oSRDqaN\u0003\u0002\u0002\u0003\u0007a&A\u0002yIE\n!B]8pi\u000e\u000bWo]3!\u00031\u0019X\r\u001e*p_R\u001c\u0015-^:f)\t\u00194\bC\u0003=\u000f\u0001\u0007a&A\boKN$X\rZ#yG\u0016\u0004H/[8o\u000319W\r\u001e*p_R\u001c\u0015-^:f\u0001"
)
public class XIncludeException extends Exception {
   private Throwable rootCause;

   private Throwable rootCause() {
      return this.rootCause;
   }

   private void rootCause_$eq(final Throwable x$1) {
      this.rootCause = x$1;
   }

   public void setRootCause(final Throwable nestedException) {
      this.rootCause_$eq(nestedException);
   }

   public Throwable getRootCause() {
      return this.rootCause();
   }

   public XIncludeException(final String message) {
      super(message);
   }

   public XIncludeException() {
      this((String)null);
   }
}
