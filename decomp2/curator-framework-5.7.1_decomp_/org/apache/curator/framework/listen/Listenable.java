package org.apache.curator.framework.listen;

import java.util.concurrent.Executor;

public interface Listenable {
   void addListener(Object var1);

   void addListener(Object var1, Executor var2);

   void removeListener(Object var1);
}
