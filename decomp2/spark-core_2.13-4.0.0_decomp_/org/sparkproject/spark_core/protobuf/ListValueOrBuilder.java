package org.sparkproject.spark_core.protobuf;

import java.util.List;

public interface ListValueOrBuilder extends MessageOrBuilder {
   List getValuesList();

   Value getValues(int index);

   int getValuesCount();

   List getValuesOrBuilderList();

   ValueOrBuilder getValuesOrBuilder(int index);
}
