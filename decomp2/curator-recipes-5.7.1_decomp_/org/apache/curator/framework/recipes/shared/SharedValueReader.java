package org.apache.curator.framework.recipes.shared;

import org.apache.curator.framework.listen.Listenable;

public interface SharedValueReader {
   byte[] getValue();

   VersionedValue getVersionedValue();

   Listenable getListenable();
}
