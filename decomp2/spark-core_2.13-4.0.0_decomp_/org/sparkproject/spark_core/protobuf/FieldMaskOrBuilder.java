package org.sparkproject.spark_core.protobuf;

import java.util.List;

public interface FieldMaskOrBuilder extends MessageOrBuilder {
   List getPathsList();

   int getPathsCount();

   String getPaths(int index);

   ByteString getPathsBytes(int index);
}
