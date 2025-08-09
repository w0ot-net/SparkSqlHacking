package org.apache.orc.protobuf;

import java.util.List;
import java.util.Map;

@CheckReturnValue
public interface MessageOrBuilder extends MessageLiteOrBuilder {
   Message getDefaultInstanceForType();

   List findInitializationErrors();

   String getInitializationErrorString();

   Descriptors.Descriptor getDescriptorForType();

   Map getAllFields();

   boolean hasOneof(Descriptors.OneofDescriptor oneof);

   Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneof);

   boolean hasField(Descriptors.FieldDescriptor field);

   Object getField(Descriptors.FieldDescriptor field);

   int getRepeatedFieldCount(Descriptors.FieldDescriptor field);

   Object getRepeatedField(Descriptors.FieldDescriptor field, int index);

   UnknownFieldSet getUnknownFields();
}
