package com.google.crypto.tink.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/** @deprecated */
@Deprecated
public interface RegistryConfigOrBuilder extends MessageOrBuilder {
   String getConfigName();

   ByteString getConfigNameBytes();

   List getEntryList();

   KeyTypeEntry getEntry(int index);

   int getEntryCount();

   List getEntryOrBuilderList();

   KeyTypeEntryOrBuilder getEntryOrBuilder(int index);
}
