package org.jline.style;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

public class NopStyleSource implements StyleSource {
   @Nullable
   public String get(String group, String name) {
      Objects.requireNonNull(group);
      Objects.requireNonNull(name);
      return null;
   }

   public void set(String group, String name, String style) {
      Objects.requireNonNull(group);
      Objects.requireNonNull(name);
      Objects.requireNonNull(style);
   }

   public void remove(String group) {
      Objects.requireNonNull(group);
   }

   public void remove(String group, String name) {
      Objects.requireNonNull(group);
      Objects.requireNonNull(name);
   }

   public void clear() {
   }

   public Iterable groups() {
      return Collections.unmodifiableList(Collections.emptyList());
   }

   public Map styles(String group) {
      return Collections.unmodifiableMap(Collections.emptyMap());
   }
}
