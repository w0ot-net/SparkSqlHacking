package org.apache.orc.protobuf;

@CheckReturnValue
interface MessageInfoFactory {
   boolean isSupported(Class clazz);

   MessageInfo messageInfoFor(Class clazz);
}
