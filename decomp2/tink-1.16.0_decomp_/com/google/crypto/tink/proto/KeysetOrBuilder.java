package com.google.crypto.tink.proto;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface KeysetOrBuilder extends MessageOrBuilder {
   int getPrimaryKeyId();

   List getKeyList();

   Keyset.Key getKey(int index);

   int getKeyCount();

   List getKeyOrBuilderList();

   Keyset.KeyOrBuilder getKeyOrBuilder(int index);
}
