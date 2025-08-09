package org.apache.hive.service.cli;

public enum FetchType {
   QUERY_OUTPUT((short)0),
   LOG((short)1);

   private final short tFetchType;

   private FetchType(short tFetchType) {
      this.tFetchType = tFetchType;
   }

   public static FetchType getFetchType(short tFetchType) {
      for(FetchType fetchType : values()) {
         if (tFetchType == fetchType.toTFetchType()) {
            return fetchType;
         }
      }

      return QUERY_OUTPUT;
   }

   public short toTFetchType() {
      return this.tFetchType;
   }

   // $FF: synthetic method
   private static FetchType[] $values() {
      return new FetchType[]{QUERY_OUTPUT, LOG};
   }
}
