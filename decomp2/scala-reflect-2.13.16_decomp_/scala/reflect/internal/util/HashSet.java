package scala.reflect.internal.util;

import java.lang.invoke.SerializedLambda;
import scala.collection.AbstractIterator;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.mutable.Clearable;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dr!B\f\u0019\u0011\u0003\tc!B\u0012\u0019\u0011\u0003!\u0003\"B\u0015\u0002\t\u0003Q\u0003\"B\u0016\u0002\t\u0003a\u0003BB\u0016\u0002\t\u0003\tIB\u0002\u0003$1\u0001y\u0003\u0002\u0003#\u0006\u0005\u000b\u0007I\u0011A#\t\u0011E+!\u0011!Q\u0001\n\u0019C\u0001BU\u0003\u0003\u0002\u0003\u0006Ia\u0015\u0005\u0006S\u0015!\tA\u0016\u0005\u00075\u0016\u0001\u000b\u0015B*\t\rm+\u0001\u0015)\u0003]\u0011\u0015yV\u0001\"\u0003a\u0011\u0015\u0019W\u0001\"\u0001e\u0011\u0015)W\u0001\"\u0001g\u0011\u0015QW\u0001\"\u0001l\u0011\u0015iW\u0001\"\u0001o\u0011\u0015\u0001X\u0001\"\u0001r\u0011\u0015\u0019X\u0001\"\u0001u\u0011\u0015qX\u0001\"\u0001\u0000\u0011\u001d\t9!\u0002C\u0005\u0003\u0013Aa!!\u0004\u0006\t\u00131\u0007bBA\b\u000b\u0011\u0005\u0013\u0011C\u0001\b\u0011\u0006\u001c\bnU3u\u0015\tI\"$\u0001\u0003vi&d'BA\u000e\u001d\u0003!Ig\u000e^3s]\u0006d'BA\u000f\u001f\u0003\u001d\u0011XM\u001a7fGRT\u0011aH\u0001\u0006g\u000e\fG.Y\u0002\u0001!\t\u0011\u0013!D\u0001\u0019\u0005\u001dA\u0015m\u001d5TKR\u001c\"!A\u0013\u0011\u0005\u0019:S\"\u0001\u0010\n\u0005!r\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002C\u0005)\u0011\r\u001d9msV\u0019Q&!\u0006\u0015\u00079\n9\u0002\u0005\u0003#\u000b\u0005MQC\u0001\u00197'\r)\u0011\u0007\u0010\t\u0004EI\"\u0014BA\u001a\u0019\u0005\r\u0019V\r\u001e\t\u0003kYb\u0001\u0001B\u00038\u000b\t\u0007\u0001HA\u0001U#\tIT\u0005\u0005\u0002'u%\u00111H\b\u0002\u0005\u001dVdG\u000e\u0005\u0002>\u00056\taH\u0003\u0002@\u0001\u00069Q.\u001e;bE2,'BA!\u001f\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003\u0007z\u0012\u0011b\u00117fCJ\f'\r\\3\u0002\u000b1\f'-\u001a7\u0016\u0003\u0019\u0003\"a\u0012(\u000f\u0005!c\u0005CA%\u001f\u001b\u0005Q%BA&!\u0003\u0019a$o\\8u}%\u0011QJH\u0001\u0007!J,G-\u001a4\n\u0005=\u0003&AB*ue&twM\u0003\u0002N=\u00051A.\u00192fY\u0002\nq\"\u001b8ji&\fGnQ1qC\u000eLG/\u001f\t\u0003MQK!!\u0016\u0010\u0003\u0007%sG\u000fF\u0002X1f\u00032AI\u00035\u0011\u0015!\u0015\u00021\u0001G\u0011\u0015\u0011\u0016\u00021\u0001T\u0003\u0011)8/\u001a3\u0002\u000bQ\f'\r\\3\u0011\u0007\u0019jV%\u0003\u0002_=\t)\u0011I\u001d:bs\u0006)\u0011N\u001c3fqR\u00111+\u0019\u0005\u0006E2\u0001\raU\u0001\u0002q\u0006!1/\u001b>f+\u0005\u0019\u0016!B2mK\u0006\u0014H#A4\u0011\u0005\u0019B\u0017BA5\u001f\u0005\u0011)f.\u001b;\u0002#\u0019Lg\u000eZ#oiJLxJ]+qI\u0006$X\r\u0006\u00025Y\")!m\u0004a\u0001i\u0005Ia-\u001b8e\u000b:$(/\u001f\u000b\u0003i=DQA\u0019\tA\u0002Q\n\u0001\"\u00193e\u000b:$(/\u001f\u000b\u0003OJDQAY\tA\u0002Q\n!\"\u00193e\u000b:$(/[3t)\t9W\u000fC\u0003w%\u0001\u0007q/\u0001\u0002ygB\u0019\u0001p\u001f\u001b\u000f\u0005\u0019J\u0018B\u0001>\u001f\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001`?\u0003\u0019%#XM]1cY\u0016|enY3\u000b\u0005it\u0012\u0001C5uKJ\fGo\u001c:\u0016\u0005\u0005\u0005\u0001\u0003\u0002=\u0002\u0004QJ1!!\u0002~\u0005!IE/\u001a:bi>\u0014\u0018aC1eI>cG-\u00128uef$2aZA\u0006\u0011\u0015\u0011G\u00031\u00015\u0003%9'o\\<UC\ndW-\u0001\u0005u_N#(/\u001b8h)\u00051\u0005cA\u001b\u0002\u0016\u0011)qg\u0001b\u0001q!)!k\u0001a\u0001'V!\u00111DA\u0011)\u0019\ti\"a\t\u0002&A!!%BA\u0010!\r)\u0014\u0011\u0005\u0003\u0006o\u0011\u0011\r\u0001\u000f\u0005\u0006\t\u0012\u0001\rA\u0012\u0005\u0006%\u0012\u0001\ra\u0015"
)
public class HashSet extends Set implements Clearable {
   private final String label;
   private final int initialCapacity;
   private int used;
   public Object[] scala$reflect$internal$util$HashSet$$table;

   public String label() {
      return this.label;
   }

   private int index(final int x) {
      scala.math.package var10000 = .MODULE$;
      return Math.abs(x % this.scala$reflect$internal$util$HashSet$$table.length);
   }

   public int size() {
      return this.used;
   }

   public void clear() {
      this.used = 0;
      this.scala$reflect$internal$util$HashSet$$table = new Object[this.initialCapacity];
   }

   public Object findEntryOrUpdate(final Object x) {
      int h = this.index(Statics.anyHash(x));

      for(Object entry = this.scala$reflect$internal$util$HashSet$$table[h]; entry != null; entry = this.scala$reflect$internal$util$HashSet$$table[h]) {
         if (BoxesRunTime.equals(x, entry)) {
            return entry;
         }

         h = this.index(h + 1);
      }

      this.scala$reflect$internal$util$HashSet$$table[h] = x;
      ++this.used;
      if (this.used > this.scala$reflect$internal$util$HashSet$$table.length >> 2) {
         this.growTable();
      }

      return x;
   }

   public Object findEntry(final Object x) {
      int h = this.index(Statics.anyHash(x));

      Object entry;
      for(entry = this.scala$reflect$internal$util$HashSet$$table[h]; entry != null && !BoxesRunTime.equals(x, entry); entry = this.scala$reflect$internal$util$HashSet$$table[h]) {
         h = this.index(h + 1);
      }

      return entry;
   }

   public void addEntry(final Object x) {
      int h = this.index(Statics.anyHash(x));

      for(Object entry = this.scala$reflect$internal$util$HashSet$$table[h]; entry != null; entry = this.scala$reflect$internal$util$HashSet$$table[h]) {
         if (BoxesRunTime.equals(x, entry)) {
            return;
         }

         h = this.index(h + 1);
      }

      this.scala$reflect$internal$util$HashSet$$table[h] = x;
      ++this.used;
      if (this.used > this.scala$reflect$internal$util$HashSet$$table.length >> 2) {
         this.growTable();
      }
   }

   public void addEntries(final IterableOnce xs) {
      xs.iterator().foreach((x) -> {
         $anonfun$addEntries$1(this, x);
         return BoxedUnit.UNIT;
      });
   }

   public Iterator iterator() {
      return new AbstractIterator() {
         private int i;
         // $FF: synthetic field
         private final HashSet $outer;

         public boolean hasNext() {
            while(this.i < this.$outer.scala$reflect$internal$util$HashSet$$table.length && this.$outer.scala$reflect$internal$util$HashSet$$table[this.i] == null) {
               ++this.i;
            }

            if (this.i < this.$outer.scala$reflect$internal$util$HashSet$$table.length) {
               return true;
            } else {
               return false;
            }
         }

         public Object next() {
            if (this.hasNext()) {
               ++this.i;
               return this.$outer.scala$reflect$internal$util$HashSet$$table[this.i - 1];
            } else {
               return null;
            }
         }

         public {
            if (HashSet.this == null) {
               throw null;
            } else {
               this.$outer = HashSet.this;
               this.i = 0;
            }
         }
      };
   }

   private void addOldEntry(final Object x) {
      int h = this.index(Statics.anyHash(x));

      for(Object entry = this.scala$reflect$internal$util$HashSet$$table[h]; entry != null; entry = this.scala$reflect$internal$util$HashSet$$table[h]) {
         h = this.index(h + 1);
      }

      this.scala$reflect$internal$util$HashSet$$table[h] = x;
   }

   private void growTable() {
      Object[] oldtable = this.scala$reflect$internal$util$HashSet$$table;
      int growthFactor = this.scala$reflect$internal$util$HashSet$$table.length <= this.initialCapacity ? 8 : (this.scala$reflect$internal$util$HashSet$$table.length <= this.initialCapacity * 8 ? 4 : 2);
      this.scala$reflect$internal$util$HashSet$$table = new Object[this.scala$reflect$internal$util$HashSet$$table.length * growthFactor];

      for(int i = 0; i < oldtable.length; ++i) {
         Object entry = oldtable[i];
         if (entry != null) {
            this.addOldEntry(entry);
         }
      }

   }

   public String toString() {
      return scala.collection.StringOps..MODULE$.format$extension("HashSet %s(%d / %d)", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.label(), this.used, this.scala$reflect$internal$util$HashSet$$table.length}));
   }

   // $FF: synthetic method
   public static final void $anonfun$addEntries$1(final HashSet $this, final Object x) {
      $this.addEntry(x);
   }

   public HashSet(final String label, final int initialCapacity) {
      this.label = label;
      this.initialCapacity = initialCapacity;
      this.used = 0;
      this.scala$reflect$internal$util$HashSet$$table = new Object[initialCapacity];
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
