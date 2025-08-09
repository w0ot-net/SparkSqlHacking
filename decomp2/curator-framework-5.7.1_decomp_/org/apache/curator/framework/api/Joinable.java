package org.apache.curator.framework.api;

import java.util.List;

public interface Joinable {
   Object joining(String... var1);

   Object joining(List var1);
}
