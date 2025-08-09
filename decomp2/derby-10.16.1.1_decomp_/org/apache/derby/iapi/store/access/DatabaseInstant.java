package org.apache.derby.iapi.store.access;

import java.io.Serializable;

public interface DatabaseInstant extends Serializable {
   boolean lessThan(DatabaseInstant var1);

   boolean equals(Object var1);

   DatabaseInstant next();

   DatabaseInstant prior();

   String toString();
}
