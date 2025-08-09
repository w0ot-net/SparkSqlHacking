package io.fabric8.kubernetes.client.dsl;

import java.util.stream.Stream;

public interface FilterWatchListDeletable extends Filterable, Listable, WatchAndWaitable, DeletableWithOptions, Informable {
   FilterNested withNewFilter();

   Stream resources();
}
