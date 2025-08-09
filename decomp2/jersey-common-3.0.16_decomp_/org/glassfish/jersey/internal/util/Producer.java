package org.glassfish.jersey.internal.util;

import java.util.concurrent.Callable;

public interface Producer extends Callable {
   Object call();
}
