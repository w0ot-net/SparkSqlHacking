package org.sparkproject.spark_core.protobuf;

import java.util.List;

@CheckReturnValue
interface ListFieldSchema {
   List mutableListAt(Object msg, long offset);

   void makeImmutableListAt(Object msg, long offset);

   void mergeListsAt(Object msg, Object otherMsg, long offset);
}
