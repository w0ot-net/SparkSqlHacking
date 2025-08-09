package org.sparkproject.spark_core.protobuf;

import java.util.List;

public interface ApiOrBuilder extends MessageOrBuilder {
   String getName();

   ByteString getNameBytes();

   List getMethodsList();

   Method getMethods(int index);

   int getMethodsCount();

   List getMethodsOrBuilderList();

   MethodOrBuilder getMethodsOrBuilder(int index);

   List getOptionsList();

   Option getOptions(int index);

   int getOptionsCount();

   List getOptionsOrBuilderList();

   OptionOrBuilder getOptionsOrBuilder(int index);

   String getVersion();

   ByteString getVersionBytes();

   boolean hasSourceContext();

   SourceContext getSourceContext();

   SourceContextOrBuilder getSourceContextOrBuilder();

   List getMixinsList();

   Mixin getMixins(int index);

   int getMixinsCount();

   List getMixinsOrBuilderList();

   MixinOrBuilder getMixinsOrBuilder(int index);

   int getSyntaxValue();

   Syntax getSyntax();
}
