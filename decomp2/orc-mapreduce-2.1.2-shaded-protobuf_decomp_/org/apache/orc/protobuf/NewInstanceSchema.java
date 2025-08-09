package org.apache.orc.protobuf;

@CheckReturnValue
interface NewInstanceSchema {
   Object newInstance(Object defaultInstance);
}
