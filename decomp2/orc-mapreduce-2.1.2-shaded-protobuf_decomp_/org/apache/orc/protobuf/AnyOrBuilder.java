package org.apache.orc.protobuf;

public interface AnyOrBuilder extends MessageOrBuilder {
   String getTypeUrl();

   ByteString getTypeUrlBytes();

   ByteString getValue();
}
