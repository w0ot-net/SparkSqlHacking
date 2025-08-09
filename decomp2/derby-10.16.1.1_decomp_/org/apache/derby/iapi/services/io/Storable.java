package org.apache.derby.iapi.services.io;

public interface Storable extends Formatable {
   boolean isNull();

   void restoreToNull();
}
