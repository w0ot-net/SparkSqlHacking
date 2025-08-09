package org.apache.orc.protobuf;

import java.util.List;

public interface EnumValueOrBuilder extends MessageOrBuilder {
   String getName();

   ByteString getNameBytes();

   int getNumber();

   List getOptionsList();

   Option getOptions(int index);

   int getOptionsCount();

   List getOptionsOrBuilderList();

   OptionOrBuilder getOptionsOrBuilder(int index);
}
