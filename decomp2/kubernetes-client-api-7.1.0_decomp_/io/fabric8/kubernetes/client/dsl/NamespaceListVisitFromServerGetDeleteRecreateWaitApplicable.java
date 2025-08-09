package io.fabric8.kubernetes.client.dsl;

import java.util.List;
import java.util.stream.Stream;

public interface NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable extends ListVisitFromServerGetDeleteRecreateWaitApplicable, Namespaceable, AnyNamespaceable {
   Stream resources();

   List items();
}
