package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.util.Collection;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;

public interface ObjectCollection extends Collection, ObjectIterable {
   ObjectIterator iterator();

   default ObjectSpliterator spliterator() {
      return ObjectSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Collection)this), 64);
   }
}
