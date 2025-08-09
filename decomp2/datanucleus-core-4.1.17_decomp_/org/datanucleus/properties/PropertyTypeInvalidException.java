package org.datanucleus.properties;

import org.datanucleus.exceptions.NucleusUserException;

public class PropertyTypeInvalidException extends NucleusUserException {
   private static final long serialVersionUID = -8890511238750369471L;

   public PropertyTypeInvalidException(String name, String type) {
      super("Property \"" + name + "\" is not of required type \"" + type + "\"");
   }
}
