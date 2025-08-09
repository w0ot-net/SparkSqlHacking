package org.apache.orc.protobuf;

import java.util.List;

public interface MethodOrBuilder extends MessageOrBuilder {
   String getName();

   ByteString getNameBytes();

   String getRequestTypeUrl();

   ByteString getRequestTypeUrlBytes();

   boolean getRequestStreaming();

   String getResponseTypeUrl();

   ByteString getResponseTypeUrlBytes();

   boolean getResponseStreaming();

   List getOptionsList();

   Option getOptions(int index);

   int getOptionsCount();

   List getOptionsOrBuilderList();

   OptionOrBuilder getOptionsOrBuilder(int index);

   int getSyntaxValue();

   Syntax getSyntax();
}
