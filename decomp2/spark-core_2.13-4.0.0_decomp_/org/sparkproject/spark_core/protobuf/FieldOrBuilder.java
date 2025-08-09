package org.sparkproject.spark_core.protobuf;

import java.util.List;

public interface FieldOrBuilder extends MessageOrBuilder {
   int getKindValue();

   Field.Kind getKind();

   int getCardinalityValue();

   Field.Cardinality getCardinality();

   int getNumber();

   String getName();

   ByteString getNameBytes();

   String getTypeUrl();

   ByteString getTypeUrlBytes();

   int getOneofIndex();

   boolean getPacked();

   List getOptionsList();

   Option getOptions(int index);

   int getOptionsCount();

   List getOptionsOrBuilderList();

   OptionOrBuilder getOptionsOrBuilder(int index);

   String getJsonName();

   ByteString getJsonNameBytes();

   String getDefaultValue();

   ByteString getDefaultValueBytes();
}
