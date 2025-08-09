package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.builder.Visitable;
import io.fabric8.kubernetes.client.FromServerGettable;
import java.util.stream.Stream;

public interface ListVisitFromServerGetDeleteRecreateWaitApplicable extends Visitable, FromServerGettable, Waitable, ListVisitFromServerWritable, DryRunable {
   Stream resources();
}
