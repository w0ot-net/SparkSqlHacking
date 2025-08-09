package scala.reflect.runtime;

import java.util.concurrent.locks.ReentrantLock;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2\u0011\u0002B\u0003\u0011\u0002\u0007\u0005qaC\u001e\t\u000bA\u0001A\u0011\u0001\n\t\u0019Y\u0001A\u0011!A\u0003\u0012\u000b\u0007I\u0011B\f\t\u000b\u0011\u0002AQA\u0013\u0003\u0007\u001dKGN\u0003\u0002\u0007\u000f\u00059!/\u001e8uS6,'B\u0001\u0005\n\u0003\u001d\u0011XM\u001a7fGRT\u0011AC\u0001\u0006g\u000e\fG.Y\n\u0003\u00011\u0001\"!\u0004\b\u000e\u0003%I!aD\u0005\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#A\n\u0011\u00055!\u0012BA\u000b\n\u0005\u0011)f.\u001b;\u0002=M\u001c\u0017\r\\1%e\u00164G.Z2uII,h\u000e^5nK\u0012:\u0015\u000e\u001c\u0013%O&dW#\u0001\r\u0011\u0005e\u0011S\"\u0001\u000e\u000b\u0005ma\u0012!\u00027pG.\u001c(BA\u000f\u001f\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003?\u0001\nA!\u001e;jY*\t\u0011%\u0001\u0003kCZ\f\u0017BA\u0012\u001b\u00055\u0011V-\u001a8ue\u0006tG\u000fT8dW\u0006yq-\u001b7Ts:\u001c\u0007N]8oSj,G-\u0006\u0002'SQ\u0011qE\r\t\u0003Q%b\u0001\u0001B\u0003+\u0007\t\u00071FA\u0001U#\tas\u0006\u0005\u0002\u000e[%\u0011a&\u0003\u0002\b\u001d>$\b.\u001b8h!\ti\u0001'\u0003\u00022\u0013\t\u0019\u0011I\\=\t\rM\u001aA\u00111\u00015\u0003\u0011\u0011w\u000eZ=\u0011\u00075)t%\u0003\u00027\u0013\tAAHY=oC6,g\b\u000b\u0002\u0004qA\u0011Q\"O\u0005\u0003u%\u0011a!\u001b8mS:,\u0007C\u0001\u001f>\u001b\u0005)\u0011B\u0001 \u0006\u0005-\u0019\u00160\u001c2pYR\u000b'\r\\3"
)
public interface Gil {
   // $FF: synthetic method
   static ReentrantLock scala$reflect$runtime$Gil$$gil$(final Gil $this) {
      return $this.scala$reflect$runtime$Gil$$gil();
   }

   default ReentrantLock scala$reflect$runtime$Gil$$gil() {
      return new ReentrantLock();
   }

   // $FF: synthetic method
   static Object gilSynchronized$(final Gil $this, final Function0 body) {
      return $this.gilSynchronized(body);
   }

   default Object gilSynchronized(final Function0 body) {
      if (((SymbolTable)this).isCompilerUniverse()) {
         return body.apply();
      } else {
         Object var10000;
         try {
            this.scala$reflect$runtime$Gil$$gil().lock();
            var10000 = body.apply();
         } finally {
            this.scala$reflect$runtime$Gil$$gil().unlock();
         }

         return var10000;
      }
   }

   static void $init$(final Gil $this) {
   }
}
