package org.apache.hive.service.cli;

import org.apache.hive.service.rpc.thrift.TFetchOrientation;

public enum FetchOrientation {
   FETCH_NEXT(TFetchOrientation.FETCH_NEXT),
   FETCH_PRIOR(TFetchOrientation.FETCH_PRIOR),
   FETCH_RELATIVE(TFetchOrientation.FETCH_RELATIVE),
   FETCH_ABSOLUTE(TFetchOrientation.FETCH_ABSOLUTE),
   FETCH_FIRST(TFetchOrientation.FETCH_FIRST),
   FETCH_LAST(TFetchOrientation.FETCH_LAST);

   private TFetchOrientation tFetchOrientation;

   private FetchOrientation(TFetchOrientation tFetchOrientation) {
      this.tFetchOrientation = tFetchOrientation;
   }

   public static FetchOrientation getFetchOrientation(TFetchOrientation tFetchOrientation) {
      for(FetchOrientation fetchOrientation : values()) {
         if (tFetchOrientation.equals(fetchOrientation.toTFetchOrientation())) {
            return fetchOrientation;
         }
      }

      return FETCH_NEXT;
   }

   public TFetchOrientation toTFetchOrientation() {
      return this.tFetchOrientation;
   }

   // $FF: synthetic method
   private static FetchOrientation[] $values() {
      return new FetchOrientation[]{FETCH_NEXT, FETCH_PRIOR, FETCH_RELATIVE, FETCH_ABSOLUTE, FETCH_FIRST, FETCH_LAST};
   }
}
