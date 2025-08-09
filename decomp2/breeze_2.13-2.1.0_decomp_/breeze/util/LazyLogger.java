package breeze.util;

import java.io.Serializable;
import org.slf4j.Logger;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!4AAD\b\u0001)!Aq\u0005\u0001BC\u0002\u0013\u0005\u0001\u0006\u0003\u00052\u0001\t\u0005\t\u0015!\u0003*\u0011\u0015\u0011\u0004\u0001\"\u00014\u0011\u00159\u0004\u0001\"\u00019\u0011\u0015I\u0005\u0001\"\u0001K\u0011\u0015a\u0005\u0001\"\u0001N\u0011\u0015y\u0005\u0001\"\u0001Q\u0011\u0015\u0011\u0006\u0001\"\u0001T\u0011\u00159\u0004\u0001\"\u0001V\u0011\u0015I\u0005\u0001\"\u0001]\u0011\u0015a\u0005\u0001\"\u0001`\u0011\u0015y\u0005\u0001\"\u0001c\u0011\u0015\u0011\u0006\u0001\"\u0001f\u0005)a\u0015M_=M_\u001e<WM\u001d\u0006\u0003!E\tA!\u001e;jY*\t!#\u0001\u0004ce\u0016,'0Z\u0002\u0001'\r\u0001Qc\u0007\t\u0003-ei\u0011a\u0006\u0006\u00021\u0005)1oY1mC&\u0011!d\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005q!cBA\u000f#\u001d\tq\u0012%D\u0001 \u0015\t\u00013#\u0001\u0004=e>|GOP\u0005\u00021%\u00111eF\u0001\ba\u0006\u001c7.Y4f\u0013\t)cE\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002$/\u00051An\\4hKJ,\u0012!\u000b\t\u0003U=j\u0011a\u000b\u0006\u0003Y5\nQa\u001d7gi)T\u0011AL\u0001\u0004_J<\u0017B\u0001\u0019,\u0005\u0019aunZ4fe\u00069An\\4hKJ\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u00025mA\u0011Q\u0007A\u0007\u0002\u001f!)qe\u0001a\u0001S\u0005!\u0011N\u001c4p)\tID\b\u0005\u0002\u0017u%\u00111h\u0006\u0002\u0005+:LG\u000f\u0003\u0004>\t\u0011\u0005\rAP\u0001\u0004[N<\u0007c\u0001\f@\u0003&\u0011\u0001i\u0006\u0002\ty\tLh.Y7f}A\u0011!I\u0012\b\u0003\u0007\u0012\u0003\"AH\f\n\u0005\u0015;\u0012A\u0002)sK\u0012,g-\u0003\u0002H\u0011\n11\u000b\u001e:j]\u001eT!!R\f\u0002\u000b\u0011,'-^4\u0015\u0005eZ\u0005BB\u001f\u0006\t\u0003\u0007a(A\u0003ue\u0006\u001cW\r\u0006\u0002:\u001d\"1QH\u0002CA\u0002y\nAa^1s]R\u0011\u0011(\u0015\u0005\u0007{\u001d!\t\u0019\u0001 \u0002\u000b\u0015\u0014(o\u001c:\u0015\u0005e\"\u0006BB\u001f\t\t\u0003\u0007a\bF\u0002:-^Ca!P\u0005\u0005\u0002\u0004q\u0004\"\u0002-\n\u0001\u0004I\u0016!\u0003;ie><\u0018M\u00197f!\ta\",\u0003\u0002\\M\tIA\u000b\u001b:po\u0006\u0014G.\u001a\u000b\u0004sus\u0006BB\u001f\u000b\t\u0003\u0007a\bC\u0003Y\u0015\u0001\u0007\u0011\fF\u0002:A\u0006Da!P\u0006\u0005\u0002\u0004q\u0004\"\u0002-\f\u0001\u0004IFcA\u001ddI\"1Q\b\u0004CA\u0002yBQ\u0001\u0017\u0007A\u0002e#2!\u000f4h\u0011\u0019iT\u0002\"a\u0001}!)\u0001,\u0004a\u00013\u0002"
)
public class LazyLogger implements Serializable {
   private final Logger logger;

   public Logger logger() {
      return this.logger;
   }

   public void info(final Function0 msg) {
      if (this.logger().isInfoEnabled()) {
         this.logger().info((String)msg.apply());
      }

   }

   public void debug(final Function0 msg) {
      if (this.logger().isDebugEnabled()) {
         this.logger().debug((String)msg.apply());
      }

   }

   public void trace(final Function0 msg) {
      if (this.logger().isTraceEnabled()) {
         this.logger().trace((String)msg.apply());
      }

   }

   public void warn(final Function0 msg) {
      if (this.logger().isWarnEnabled()) {
         this.logger().warn((String)msg.apply());
      }

   }

   public void error(final Function0 msg) {
      if (this.logger().isErrorEnabled()) {
         this.logger().error((String)msg.apply());
      }

   }

   public void info(final Function0 msg, final Throwable throwable) {
      if (this.logger().isInfoEnabled()) {
         this.logger().info((String)msg.apply(), throwable);
      }

   }

   public void debug(final Function0 msg, final Throwable throwable) {
      if (this.logger().isDebugEnabled()) {
         this.logger().debug((String)msg.apply(), throwable);
      }

   }

   public void trace(final Function0 msg, final Throwable throwable) {
      if (this.logger().isTraceEnabled()) {
         this.logger().trace((String)msg.apply(), throwable);
      }

   }

   public void warn(final Function0 msg, final Throwable throwable) {
      if (this.logger().isWarnEnabled()) {
         this.logger().warn((String)msg.apply(), throwable);
      }

   }

   public void error(final Function0 msg, final Throwable throwable) {
      if (this.logger().isErrorEnabled()) {
         this.logger().error((String)msg.apply(), throwable);
      }

   }

   public LazyLogger(final Logger logger) {
      this.logger = logger;
   }
}
