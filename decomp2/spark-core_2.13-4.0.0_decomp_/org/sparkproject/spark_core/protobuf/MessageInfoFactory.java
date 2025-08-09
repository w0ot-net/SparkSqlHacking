package org.sparkproject.spark_core.protobuf;

@CheckReturnValue
interface MessageInfoFactory {
   boolean isSupported(Class clazz);

   MessageInfo messageInfoFor(Class clazz);
}
