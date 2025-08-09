package org.apache.curator.framework.recipes.shared;

import org.apache.curator.framework.listen.Listenable;

public interface SharedCountReader extends Listenable {
   int getCount();

   VersionedValue getVersionedValue();
}
