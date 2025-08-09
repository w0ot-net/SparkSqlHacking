package org.apache.orc.protobuf;

import java.util.List;

public abstract class MapFieldReflectionAccessor {
   abstract List getList();

   abstract List getMutableList();

   abstract Message getMapEntryMessageDefaultInstance();
}
