package org.apache.logging.log4j.core.layout;

public interface Encoder {
   void encode(Object source, ByteBufferDestination destination);
}
