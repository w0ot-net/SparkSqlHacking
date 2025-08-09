package org.sparkproject.spark_core.protobuf;

import java.util.List;

public abstract class MapFieldReflectionAccessor {
   abstract List getList();

   abstract List getMutableList();

   abstract Message getMapEntryMessageDefaultInstance();
}
