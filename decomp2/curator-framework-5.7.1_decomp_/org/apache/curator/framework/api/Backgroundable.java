package org.apache.curator.framework.api;

import java.util.concurrent.Executor;

public interface Backgroundable {
   Object inBackground();

   Object inBackground(Object var1);

   Object inBackground(BackgroundCallback var1);

   Object inBackground(BackgroundCallback var1, Object var2);

   Object inBackground(BackgroundCallback var1, Executor var2);

   Object inBackground(BackgroundCallback var1, Object var2, Executor var3);
}
