package org.apache.parquet.format.event;

import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;

public interface FieldConsumer {
   void consumeField(TProtocol var1, EventBasedThriftReader var2, short var3, byte var4) throws TException;
}
