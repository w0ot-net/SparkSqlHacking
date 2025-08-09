package org.sparkproject.spark_core.protobuf;

@CheckReturnValue
interface NewInstanceSchema {
   Object newInstance(Object defaultInstance);
}
