package org.apache.spark.internal;

import java.util.Map;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3AAC\u0006\u0001)!A1\u0004\u0001B\u0001J\u0003%A\u0004C\u0003$\u0001\u0011\u0005A\u0005\u0003\u0005(\u0001!\u0015\r\u0011\"\u0003)\u0011\u0015I\u0003\u0001\"\u0001+\u0011\u00151\u0004\u0001\"\u00018\u000f\u0015\u00015\u0002#\u0001B\r\u0015Q1\u0002#\u0001C\u0011\u0015\u0019s\u0001\"\u0001D\u0011\u0015!u\u0001b\u0001F\u0005!aunZ#oiJL(B\u0001\u0007\u000e\u0003!Ig\u000e^3s]\u0006d'B\u0001\b\u0010\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0012#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002%\u0005\u0019qN]4\u0004\u0001M\u0011\u0001!\u0006\t\u0003-ei\u0011a\u0006\u0006\u00021\u0005)1oY1mC&\u0011!d\u0006\u0002\u0007\u0003:L(+\u001a4\u0002%5,7o]1hK^KG\u000f[\"p]R,\u0007\u0010\u001e\t\u0004-uy\u0012B\u0001\u0010\u0018\u0005!a$-\u001f8b[\u0016t\u0004C\u0001\u0011\"\u001b\u0005Y\u0011B\u0001\u0012\f\u0005IiUm]:bO\u0016<\u0016\u000e\u001e5D_:$X\r\u001f;\u0002\rqJg.\u001b;?)\t)c\u0005\u0005\u0002!\u0001!11D\u0001CA\u0002q\t\u0001dY1dQ\u0016$W*Z:tC\u001e,w+\u001b;i\u0007>tG/\u001a=u+\u0005y\u0012aB7fgN\fw-Z\u000b\u0002WA\u0011Af\r\b\u0003[E\u0002\"AL\f\u000e\u0003=R!\u0001M\n\u0002\rq\u0012xn\u001c;?\u0013\t\u0011t#\u0001\u0004Qe\u0016$WMZ\u0005\u0003iU\u0012aa\u0015;sS:<'B\u0001\u001a\u0018\u0003\u001d\u0019wN\u001c;fqR,\u0012\u0001\u000f\t\u0005syZ3&D\u0001;\u0015\tYD(\u0001\u0003vi&d'\"A\u001f\u0002\t)\fg/Y\u0005\u0003\u007fi\u00121!T1q\u0003!aunZ#oiJL\bC\u0001\u0011\b'\t9Q\u0003F\u0001B\u0003\u00111'o\\7\u0015\u0005\u00152\u0005BB$\n\t\u0003\u0007A$\u0001\u0006ng\u001e<\u0016\u000e\u001e5Dib\u0004"
)
public class LogEntry {
   private MessageWithContext cachedMessageWithContext;
   private Function0 messageWithContext;
   private volatile boolean bitmap$0;

   public static LogEntry from(final Function0 msgWithCtx) {
      return LogEntry$.MODULE$.from(msgWithCtx);
   }

   private MessageWithContext cachedMessageWithContext$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.cachedMessageWithContext = (MessageWithContext)this.messageWithContext.apply();
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      this.messageWithContext = null;
      return this.cachedMessageWithContext;
   }

   private MessageWithContext cachedMessageWithContext() {
      return !this.bitmap$0 ? this.cachedMessageWithContext$lzycompute() : this.cachedMessageWithContext;
   }

   public String message() {
      return this.cachedMessageWithContext().message();
   }

   public Map context() {
      return this.cachedMessageWithContext().context();
   }

   public LogEntry(final Function0 messageWithContext) {
      this.messageWithContext = messageWithContext;
      super();
   }
}
