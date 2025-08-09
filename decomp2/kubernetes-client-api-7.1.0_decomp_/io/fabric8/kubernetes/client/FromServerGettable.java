package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.dsl.Gettable;

public interface FromServerGettable extends Gettable {
   /** @deprecated */
   @Deprecated
   Gettable fromServer();
}
