package io.fabric8.kubernetes.client.dsl;

import java.util.List;

public interface ItemWritableOperation extends DeletableWithOptions, ItemReplacable {
   /** @deprecated */
   @Deprecated
   Object createOrReplace(Object var1);

   /** @deprecated */
   @Deprecated
   Object create(Object var1);

   /** @deprecated */
   @Deprecated
   List delete(Object var1);

   /** @deprecated */
   @Deprecated
   Object updateStatus(Object var1);

   /** @deprecated */
   @Deprecated
   Object patchStatus(Object var1);
}
