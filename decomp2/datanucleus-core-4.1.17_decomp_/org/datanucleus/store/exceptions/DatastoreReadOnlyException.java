package org.datanucleus.store.exceptions;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;

public class DatastoreReadOnlyException extends NucleusUserException {
   private static final long serialVersionUID = -4173680935945334047L;
   ClassLoaderResolver clr;

   public DatastoreReadOnlyException(String msg, ClassLoaderResolver clr) {
      super(msg);
      this.clr = clr;
      this.setFatal();
   }

   public ClassLoaderResolver getClassLoaderResolver() {
      return this.clr;
   }
}
