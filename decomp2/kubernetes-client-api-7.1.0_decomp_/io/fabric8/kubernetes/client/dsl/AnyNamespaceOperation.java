package io.fabric8.kubernetes.client.dsl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public interface AnyNamespaceOperation extends FilterWatchListDeletable, ItemWritableOperation {
   /** @deprecated */
   @Deprecated
   ItemWritableOperation dryRun();

   /** @deprecated */
   @Deprecated
   ItemWritableOperation dryRun(boolean var1);

   /** @deprecated */
   @Deprecated
   default boolean delete(Object... items) {
      return this.delete(Arrays.asList(items));
   }

   /** @deprecated */
   @Deprecated
   boolean delete(List var1);

   Object load(InputStream var1);

   Object load(URL var1);

   Object load(File var1);

   Object load(String var1);

   Object resource(Object var1);
}
