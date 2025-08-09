package io.fabric8.kubernetes.client.dsl;

import java.util.Map;
import java.util.function.UnaryOperator;

/** @deprecated */
@Deprecated
public interface ImageEditReplacePatchable extends ImageUpdateable {
   /** @deprecated */
   @Deprecated
   Object edit(UnaryOperator var1);

   /** @deprecated */
   @Deprecated
   Object pause();

   /** @deprecated */
   @Deprecated
   Object resume();

   /** @deprecated */
   @Deprecated
   Object restart();

   /** @deprecated */
   @Deprecated
   Object undo();

   /** @deprecated */
   @Deprecated
   Object updateImage(Map var1);

   /** @deprecated */
   @Deprecated
   Object updateImage(String var1);
}
