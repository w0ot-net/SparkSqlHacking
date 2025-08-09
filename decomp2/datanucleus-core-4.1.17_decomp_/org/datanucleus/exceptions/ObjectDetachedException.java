package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class ObjectDetachedException extends NucleusUserException {
   private static final long serialVersionUID = 3668084504263839288L;

   public ObjectDetachedException(String class_name) {
      super(Localiser.msg("018006", class_name));
   }

   public ObjectDetachedException(String class_name, Exception nested) {
      super(Localiser.msg("018006", class_name), (Throwable)nested);
   }

   public ObjectDetachedException(Throwable[] nested) {
      super(Localiser.msg("018006"), nested);
   }
}
