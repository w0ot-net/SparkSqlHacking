package org.json4s;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class MappingException$ implements Serializable {
   public static final MappingException$ MODULE$ = new MappingException$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(MappingException$.class);
   }

   private MappingException$() {
   }
}
