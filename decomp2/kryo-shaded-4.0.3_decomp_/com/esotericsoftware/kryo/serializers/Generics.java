package com.esotericsoftware.kryo.serializers;

import java.util.HashMap;
import java.util.Map;

final class Generics {
   private Map typeVar2class;

   public Generics() {
      this.typeVar2class = new HashMap();
   }

   public Generics(Map mappings) {
      this.typeVar2class = new HashMap(mappings);
   }

   public void add(String typeVar, Class clazz) {
      this.typeVar2class.put(typeVar, clazz);
   }

   public Class getConcreteClass(String typeVar) {
      return (Class)this.typeVar2class.get(typeVar);
   }

   public Map getMappings() {
      return this.typeVar2class;
   }

   public String toString() {
      return this.typeVar2class.toString();
   }
}
