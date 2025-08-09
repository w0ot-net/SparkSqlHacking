package org.sparkproject.spark_core.protobuf;

import java.util.List;

public interface EnumOrBuilder extends MessageOrBuilder {
   String getName();

   ByteString getNameBytes();

   List getEnumvalueList();

   EnumValue getEnumvalue(int index);

   int getEnumvalueCount();

   List getEnumvalueOrBuilderList();

   EnumValueOrBuilder getEnumvalueOrBuilder(int index);

   List getOptionsList();

   Option getOptions(int index);

   int getOptionsCount();

   List getOptionsOrBuilderList();

   OptionOrBuilder getOptionsOrBuilder(int index);

   boolean hasSourceContext();

   SourceContext getSourceContext();

   SourceContextOrBuilder getSourceContextOrBuilder();

   int getSyntaxValue();

   Syntax getSyntax();

   String getEdition();

   ByteString getEditionBytes();
}
