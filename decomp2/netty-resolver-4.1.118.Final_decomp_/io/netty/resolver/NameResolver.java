package io.netty.resolver;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import java.io.Closeable;

public interface NameResolver extends Closeable {
   Future resolve(String var1);

   Future resolve(String var1, Promise var2);

   Future resolveAll(String var1);

   Future resolveAll(String var1, Promise var2);

   void close();
}
