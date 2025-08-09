package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.builder.Visitor;
import io.fabric8.kubernetes.client.dsl.base.PatchContext;
import io.fabric8.kubernetes.client.dsl.base.PatchType;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public interface EditReplacePatchable extends Updatable {
   Object edit(UnaryOperator var1);

   Object edit(Visitor... var1);

   Object edit(Class var1, Visitor var2);

   Object accept(Consumer var1);

   default Object patch(Object item) {
      return this.patch(PatchContext.of(PatchType.JSON), item);
   }

   Object patch(PatchContext var1, Object var2);

   default Object patch(String patch) {
      return this.patch((PatchContext)null, (String)patch);
   }

   Object patch(PatchContext var1, String var2);

   Object patch();

   Object patch(PatchContext var1);
}
