package org.apache.arrow.flatbuf;

public final class MessageHeader {
   public static final byte NONE = 0;
   public static final byte Schema = 1;
   public static final byte DictionaryBatch = 2;
   public static final byte RecordBatch = 3;
   public static final byte Tensor = 4;
   public static final byte SparseTensor = 5;
   public static final String[] names = new String[]{"NONE", "Schema", "DictionaryBatch", "RecordBatch", "Tensor", "SparseTensor"};

   private MessageHeader() {
   }

   public static String name(int e) {
      return names[e];
   }
}
