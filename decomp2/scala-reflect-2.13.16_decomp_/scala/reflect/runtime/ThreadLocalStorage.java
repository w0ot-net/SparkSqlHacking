package scala.reflect.runtime;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r4\u0011\u0002D\u0007\u0011\u0002\u0007\u0005qbE0\t\u000ba\u0001A\u0011\u0001\u000e\u0007\u000f1\u0001\u0001\u0013aI\u0001=!)\u0001E\u0001D\u0001C!)QF\u0001D\u0001]\u0019!\u0011\u0007\u0001\u00033\u0011!ATA!A%\u0002\u0013I\u0004\"\u0002\u001f\u0006\t\u0003i\u0004B\u0002!\u0006A\u0003%\u0011\tC\u0003!\u000b\u0011\u0005q\nC\u0003.\u000b\u0011\u0005\u0001\u000bC\u0003S\u0001\u0011\u00151K\u0001\nUQJ,\u0017\r\u001a'pG\u0006d7\u000b^8sC\u001e,'B\u0001\b\u0010\u0003\u001d\u0011XO\u001c;j[\u0016T!\u0001E\t\u0002\u000fI,g\r\\3di*\t!#A\u0003tG\u0006d\u0017m\u0005\u0002\u0001)A\u0011QCF\u0007\u0002#%\u0011q#\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012a\u0007\t\u0003+qI!!H\t\u0003\tUs\u0017\u000e^\u000b\u0003?\u0011\u001a\"A\u0001\u000b\u0002\u0007\u001d,G/F\u0001#!\t\u0019C\u0005\u0004\u0001\u0005\u000b\u0015\u0012!\u0019\u0001\u0014\u0003\u0003Q\u000b\"a\n\u0016\u0011\u0005UA\u0013BA\u0015\u0012\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!F\u0016\n\u00051\n\"aA!os\u0006\u00191/\u001a;\u0015\u0005my\u0003\"\u0002\u0019\u0005\u0001\u0004\u0011\u0013\u0001\u00038foZ\u000bG.^3\u0003)5KH\u000b\u001b:fC\u0012dunY1m'R|'/Y4f+\t\u0019tgE\u0002\u0006)Q\u00022!\u000e\u00027\u001b\u0005\u0001\u0001CA\u00128\t\u0015)SA1\u0001'\u00031Ig.\u001b;jC24\u0016\r\\;f!\r)\"HN\u0005\u0003wE\u0011\u0001\u0002\u00102z]\u0006lWMP\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005yz\u0004cA\u001b\u0006m!1\u0001h\u0002CA\u0002e\naA^1mk\u0016\u001c\b\u0003\u0002\"H\u0013Zj\u0011a\u0011\u0006\u0003\t\u0016\u000bA!\u001e;jY*\ta)\u0001\u0003kCZ\f\u0017B\u0001%D\u0005\ri\u0015\r\u001d\t\u0003\u00156k\u0011a\u0013\u0006\u0003\u0019\u0016\u000bA\u0001\\1oO&\u0011aj\u0013\u0002\u0007)\"\u0014X-\u00193\u0016\u0003Y\"\"aG)\t\u000bAR\u0001\u0019\u0001\u001c\u0002)5\\G\u000b\u001b:fC\u0012dunY1m'R|'/Y4f+\t!v\u000b\u0006\u0002V1B\u0019QG\u0001,\u0011\u0005\r:F!B\u0013\f\u0005\u00041\u0003BB-\f\t\u0003\u0007!,A\u0001y!\r)\"H\u0016\u0015\u0003\u0017q\u0003\"!F/\n\u0005y\u000b\"AB5oY&tW\r\u0005\u0002aC6\tQ\"\u0003\u0002c\u001b\tY1+_7c_2$\u0016M\u00197f\u0001"
)
public interface ThreadLocalStorage {
   // $FF: synthetic method
   static scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage mkThreadLocalStorage$(final scala.reflect.runtime.ThreadLocalStorage $this, final Function0 x) {
      return $this.mkThreadLocalStorage(x);
   }

   default scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage mkThreadLocalStorage(final Function0 x) {
      return (SymbolTable)this.new MyThreadLocalStorage(x);
   }

   static void $init$(final scala.reflect.runtime.ThreadLocalStorage $this) {
   }

   private class MyThreadLocalStorage implements scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage {
      private final Function0 initialValue;
      private final Map values;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public Object get() {
         if (this.values.containsKey(Thread.currentThread())) {
            return this.values.get(Thread.currentThread());
         } else {
            Object value = this.initialValue.apply();
            this.values.put(Thread.currentThread(), value);
            return value;
         }
      }

      public void set(final Object newValue) {
         this.values.put(Thread.currentThread(), newValue);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$runtime$ThreadLocalStorage$MyThreadLocalStorage$$$outer() {
         return this.$outer;
      }

      public MyThreadLocalStorage(final Function0 initialValue) {
         this.initialValue = initialValue;
         if (ThreadLocalStorage.this == null) {
            throw null;
         } else {
            this.$outer = ThreadLocalStorage.this;
            super();
            this.values = Collections.synchronizedMap(new WeakHashMap());
         }
      }
   }

   public interface ThreadLocalStorage {
      Object get();

      void set(final Object newValue);
   }
}
