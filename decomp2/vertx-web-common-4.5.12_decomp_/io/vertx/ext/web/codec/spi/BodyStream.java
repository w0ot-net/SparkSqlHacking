package io.vertx.ext.web.codec.spi;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.streams.WriteStream;

public interface BodyStream extends WriteStream, Handler {
   Future result();
}
