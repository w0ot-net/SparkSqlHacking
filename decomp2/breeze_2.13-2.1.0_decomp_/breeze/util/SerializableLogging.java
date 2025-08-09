package breeze.util;

import java.io.Serializable;
import org.slf4j.LoggerFactory;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u001f\u0001\u0011\u0005q\u0004C\u0003$\u0001\u0011EA\u0005C\u0004*\u0001\u0001\u0007I\u0011\u0002\u0013\t\u000fI\u0002\u0001\u0019!C\u0005g\t\u00192+\u001a:jC2L'0\u00192mK2{wmZ5oO*\u0011q\u0001C\u0001\u0005kRLGNC\u0001\n\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0001\u0001\r%A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u0004\"aE\u000e\u000f\u0005QIbBA\u000b\u0019\u001b\u00051\"BA\f\u000b\u0003\u0019a$o\\8u}%\tq\"\u0003\u0002\u001b\u001d\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u000f\u001e\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tQb\"\u0001\u0004%S:LG\u000f\n\u000b\u0002AA\u0011Q\"I\u0005\u0003E9\u0011A!\u00168ji\u00061An\\4hKJ,\u0012!\n\t\u0003M\u001dj\u0011AB\u0005\u0003Q\u0019\u0011!\u0002T1{s2{wmZ3s\u0003-yF\u000f[3`Y><w-\u001a:)\u0005\rY\u0003CA\u0007-\u0013\ticB\u0001\u0005w_2\fG/\u001b7fQ\t\u0019q\u0006\u0005\u0002\u000ea%\u0011\u0011G\u0004\u0002\niJ\fgn]5f]R\fqb\u0018;iK~cwnZ4fe~#S-\u001d\u000b\u0003AQBq!\u000e\u0003\u0002\u0002\u0003\u0007Q%A\u0002yIE\u0002"
)
public interface SerializableLogging extends Serializable {
   LazyLogger breeze$util$SerializableLogging$$_the_logger();

   void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1);

   // $FF: synthetic method
   static LazyLogger logger$(final SerializableLogging $this) {
      return $this.logger();
   }

   default LazyLogger logger() {
      LazyLogger logger = this.breeze$util$SerializableLogging$$_the_logger();
      if (logger == null) {
         synchronized(this){}

         try {
            logger = this.breeze$util$SerializableLogging$$_the_logger();
            if (logger == null) {
               LazyLogger ll = new LazyLogger(LoggerFactory.getLogger(this.getClass()));
               this.breeze$util$SerializableLogging$$_the_logger_$eq(ll);
               logger = ll;
            }
         } catch (Throwable var5) {
            throw var5;
         }
      }

      return logger;
   }

   static void $init$(final SerializableLogging $this) {
      $this.breeze$util$SerializableLogging$$_the_logger_$eq((LazyLogger)null);
   }
}
