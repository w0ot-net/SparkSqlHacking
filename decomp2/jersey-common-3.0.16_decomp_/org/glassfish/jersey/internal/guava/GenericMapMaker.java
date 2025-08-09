package org.glassfish.jersey.internal.guava;

/** @deprecated */
@Deprecated
abstract class GenericMapMaker {
   MapMaker.RemovalListener getRemovalListener() {
      return GenericMapMaker.NullListener.INSTANCE;
   }

   static enum NullListener implements MapMaker.RemovalListener {
      INSTANCE;

      public void onRemoval(MapMaker.RemovalNotification notification) {
      }
   }
}
