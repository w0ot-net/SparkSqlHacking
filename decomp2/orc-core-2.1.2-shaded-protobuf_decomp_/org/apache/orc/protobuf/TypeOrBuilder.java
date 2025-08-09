package org.apache.orc.protobuf;

import java.util.List;

public interface TypeOrBuilder extends MessageOrBuilder {
   String getName();

   ByteString getNameBytes();

   List getFieldsList();

   Field getFields(int index);

   int getFieldsCount();

   List getFieldsOrBuilderList();

   FieldOrBuilder getFieldsOrBuilder(int index);

   List getOneofsList();

   int getOneofsCount();

   String getOneofs(int index);

   ByteString getOneofsBytes(int index);

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
