package org.apache.curator.framework.api;

import java.util.List;

public interface Leaveable {
   Object leaving(String... var1);

   Object leaving(List var1);
}
