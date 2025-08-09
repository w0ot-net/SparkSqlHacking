package org.apache.curator.framework.api;

public interface ErrorListenerPathAndBytesable extends PathAndBytesable {
   PathAndBytesable withUnhandledErrorListener(UnhandledErrorListener var1);
}
