package scala.util.parsing.input;

import java.util.Map;
import java.util.WeakHashMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0004\u0005\u0006%\u0001!\t\u0001\u0006\u0005\t1\u0001A)\u0019!C\u00053!1\u0011\b\u0001C\u0001\u000bi\u0012Q\u0002U8tSRLwN\\\"bG\",'B\u0001\u0004\b\u0003\u0015Ig\u000e];u\u0015\tA\u0011\"A\u0004qCJ\u001c\u0018N\\4\u000b\u0005)Y\u0011\u0001B;uS2T\u0011\u0001D\u0001\u0006g\u000e\fG.Y\n\u0003\u00019\u0001\"a\u0004\t\u000e\u0003-I!!E\u0006\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#A\u000b\u0011\u0005=1\u0012BA\f\f\u0005\u0011)f.\u001b;\u0002\u0019%tG-\u001a=DC\u000eDW\r\u0016'\u0016\u0003i\u0011\"aG\u000f\u0007\tq\u0011\u0001A\u0007\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0004=\r*S\"A\u0010\u000b\u0005\u0001\n\u0013\u0001\u00027b]\u001eT\u0011AI\u0001\u0005U\u00064\u0018-\u0003\u0002%?\tYA\u000b\u001b:fC\u0012dunY1m!\u00111\u0003FK\u0017\u000e\u0003\u001dR!AC\u0011\n\u0005%:#aA'baB\u0011adK\u0005\u0003Y}\u0011Ab\u00115beN+\u0017/^3oG\u0016\u00042a\u0004\u00181\u0013\ty3BA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0010c%\u0011!g\u0003\u0002\u0004\u0013:$\b\"\u0002\u001b\u001c\t\u0003*\u0014\u0001D5oSRL\u0017\r\u001c,bYV,G#\u0001\u001c\u0011\t\u0019:$&L\u0005\u0003q\u001d\u00121bV3bW\"\u000b7\u000f['ba\u0006Q\u0011N\u001c3fq\u000e\u000b7\r[3\u0016\u0003\u0015\u0002"
)
public interface PositionCache {
   // $FF: synthetic method
   static ThreadLocal scala$util$parsing$input$PositionCache$$indexCacheTL$(final PositionCache $this) {
      return $this.scala$util$parsing$input$PositionCache$$indexCacheTL();
   }

   default ThreadLocal scala$util$parsing$input$PositionCache$$indexCacheTL() {
      return new ThreadLocal() {
         public WeakHashMap initialValue() {
            return new WeakHashMap();
         }
      };
   }

   // $FF: synthetic method
   static Map indexCache$(final PositionCache $this) {
      return $this.indexCache();
   }

   default Map indexCache() {
      return (Map)this.scala$util$parsing$input$PositionCache$$indexCacheTL().get();
   }

   static void $init$(final PositionCache $this) {
   }
}
