package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.builder.Nested;

public interface FilterNested extends Filterable, Nested {
   default Object endFilter() {
      return this.and();
   }
}
