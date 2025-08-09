package org.apache.arrow.flatbuf;

public final class MetadataVersion {
   public static final short V1 = 0;
   public static final short V2 = 1;
   public static final short V3 = 2;
   public static final short V4 = 3;
   public static final short V5 = 4;
   public static final String[] names = new String[]{"V1", "V2", "V3", "V4", "V5"};

   private MetadataVersion() {
   }

   public static String name(int e) {
      return names[e];
   }
}
