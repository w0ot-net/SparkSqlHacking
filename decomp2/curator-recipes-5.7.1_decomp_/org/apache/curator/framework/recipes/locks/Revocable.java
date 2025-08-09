package org.apache.curator.framework.recipes.locks;

import java.util.concurrent.Executor;

public interface Revocable {
   void makeRevocable(RevocationListener var1);

   void makeRevocable(RevocationListener var1, Executor var2);
}
