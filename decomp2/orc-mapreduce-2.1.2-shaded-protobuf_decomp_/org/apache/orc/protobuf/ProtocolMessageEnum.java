package org.apache.orc.protobuf;

public interface ProtocolMessageEnum extends Internal.EnumLite {
   int getNumber();

   Descriptors.EnumValueDescriptor getValueDescriptor();

   Descriptors.EnumDescriptor getDescriptorForType();
}
