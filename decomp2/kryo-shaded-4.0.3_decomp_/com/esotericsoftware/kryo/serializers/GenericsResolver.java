package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.minlog.Log;
import java.util.LinkedList;

public final class GenericsResolver {
   private LinkedList stack = new LinkedList();

   Class getConcreteClass(String typeVar) {
      for(Generics generics : this.stack) {
         Class clazz = generics.getConcreteClass(typeVar);
         if (clazz != null) {
            return clazz;
         }
      }

      return null;
   }

   boolean isSet() {
      return !this.stack.isEmpty();
   }

   void pushScope(Class type, Generics scope) {
      if (Log.TRACE) {
         Log.trace("generics", "Settting a new generics scope for class " + type.getName() + ": " + scope);
      }

      this.stack.addFirst(scope);
   }

   void popScope() {
      this.stack.removeFirst();
   }
}
