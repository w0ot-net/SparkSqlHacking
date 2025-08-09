package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.ObjectReference;
import java.util.Map;

public interface Filterable {
   Object withLabels(Map var1);

   /** @deprecated */
   @Deprecated
   Object withoutLabels(Map var1);

   Object withLabelIn(String var1, String... var2);

   Object withLabelNotIn(String var1, String... var2);

   Object withLabel(String var1, String var2);

   default Object withLabel(String key) {
      return this.withLabel(key, (String)null);
   }

   Object withoutLabel(String var1, String var2);

   default Object withoutLabel(String key) {
      return this.withoutLabel(key, (String)null);
   }

   Object withFields(Map var1);

   Object withField(String var1, String var2);

   /** @deprecated */
   @Deprecated
   Object withoutFields(Map var1);

   Object withoutField(String var1, String var2);

   Object withLabelSelector(LabelSelector var1);

   Object withLabelSelector(String var1);

   Object withInvolvedObject(ObjectReference var1);
}
