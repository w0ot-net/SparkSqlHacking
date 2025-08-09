package scala.collection.parallel.mutable;

import scala.collection.mutable.HashTable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005a\u0002\u0011\u0005\u0006)\u0001!\t!\u0006\u0005\u00063\u0001!\tB\u0007\u0005\u0007y\u0001!\t!C\u001f\u0003\u0019]KG\u000f[\"p]R,g\u000e^:\u000b\u0005\u00199\u0011aB7vi\u0006\u0014G.\u001a\u0006\u0003\u0011%\t\u0001\u0002]1sC2dW\r\u001c\u0006\u0003\u0015-\t!bY8mY\u0016\u001cG/[8o\u0015\u0005a\u0011!B:dC2\f7\u0001A\u000b\u0005\u001f\u001dr\u0014g\u0005\u0002\u0001!A\u0011\u0011CE\u0007\u0002\u0017%\u00111c\u0003\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u00051\u0002CA\t\u0018\u0013\tA2B\u0001\u0003V]&$\u0018\u0001E5oSR<\u0016\u000e\u001e5D_:$XM\u001c;t)\t12\u0004C\u0003\u001d\u0005\u0001\u0007Q$A\u0001d!\u0011q\"%\n\u0019\u000f\u0005}\u0001S\"A\u0003\n\u0005\u0005*\u0011\u0001\u0004)be\"\u000b7\u000f\u001b+bE2,\u0017BA\u0012%\u0005!\u0019uN\u001c;f]R\u001c(BA\u0011\u0006!\t1s\u0005\u0004\u0001\u0005\u000b!\u0002!\u0019A\u0015\u0003\u0003-\u000b\"AK\u0017\u0011\u0005EY\u0013B\u0001\u0017\f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0005\u0018\n\u0005=Z!aA!osB\u0011a%\r\u0003\u0006e\u0001\u0011\ra\r\u0002\u0006\u000b:$(/_\t\u0003i]\u0002\"!E\u001b\n\u0005YZ!\u0001\u0002(vY2\u0004B\u0001\u000f\u001e&a5\t\u0011H\u0003\u0002\u0007\u0013%\u00111(\u000f\u0002\n\u0011\u0006\u001c\b.\u00128uef\f\u0011\u0003[1tQR\u000b'\r\\3D_:$XM\u001c;t+\u0005iB!B \u0001\u0005\u0004I#!\u0001,\u0013\u0007\u0005\u001bUI\u0002\u0003C\u0001\u0001\u0001%\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004#B\u0010\u0001K\u0011\u0003\u0004C\u0001\u0014?!\u0015Ad)\n#1\u0013\t9\u0015HA\u0005ICNDG+\u00192mK\u0002"
)
public interface WithContents {
   // $FF: synthetic method
   static void initWithContents$(final WithContents $this, final ParHashTable.Contents c) {
      $this.initWithContents(c);
   }

   default void initWithContents(final ParHashTable.Contents c) {
      if (c != null) {
         ((HashTable)this)._loadFactor_$eq(c.loadFactor());
         ((HashTable)this).table_$eq(c.table());
         ((HashTable)this).tableSize_$eq(c.tableSize());
         ((HashTable)this).threshold_$eq(c.threshold());
         ((HashTable)this).seedvalue_$eq(c.seedvalue());
         ((HashTable)this).sizemap_$eq(c.sizemap());
      }

      if (((HashTable)this).alwaysInitSizeMap() && ((HashTable)this).sizemap() == null) {
         ((HashTable)this).sizeMapInitAndRebuild();
      }
   }

   // $FF: synthetic method
   static ParHashTable.Contents hashTableContents$(final WithContents $this) {
      return $this.hashTableContents();
   }

   default ParHashTable.Contents hashTableContents() {
      return new ParHashTable.Contents(((HashTable)this)._loadFactor(), ((HashTable)this).table(), ((HashTable)this).tableSize(), ((HashTable)this).threshold(), ((HashTable)this).seedvalue(), ((HashTable)this).sizemap());
   }

   static void $init$(final WithContents $this) {
   }
}
