package org.apache.curator.framework.api;

public interface ErrorListenerPathable extends Pathable {
   Pathable withUnhandledErrorListener(UnhandledErrorListener var1);
}
