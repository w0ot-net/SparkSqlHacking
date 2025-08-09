package org.apache.parquet.format.event;

import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;

class SkippingFieldConsumer implements FieldConsumer {
   public void consumeField(TProtocol protocol, EventBasedThriftReader reader, short id, byte type) throws TException {
      TProtocolUtil.skip(protocol, type);
   }
}
