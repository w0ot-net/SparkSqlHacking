package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;

public interface Encodable {
   int encodedLength();

   void encode(ByteBuf var1);
}
