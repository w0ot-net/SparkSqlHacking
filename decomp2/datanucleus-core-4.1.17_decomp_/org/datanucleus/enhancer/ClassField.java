package org.datanucleus.enhancer;

public class ClassField {
   protected ClassEnhancer enhancer;
   protected String fieldName;
   protected int access;
   protected Object type;
   protected Object initialValue;

   public ClassField(ClassEnhancer enhancer, String name, int access, Object type) {
      this.enhancer = enhancer;
      this.fieldName = name;
      this.access = access;
      this.type = type;
   }

   public ClassField(ClassEnhancer enhancer, String name, int access, Object type, Object value) {
      this.enhancer = enhancer;
      this.fieldName = name;
      this.access = access;
      this.type = type;
      this.initialValue = value;
   }

   public String getName() {
      return this.fieldName;
   }

   public int getAccess() {
      return this.access;
   }

   public Object getType() {
      return this.type;
   }

   public Object getInitialValue() {
      return this.initialValue;
   }

   public int hashCode() {
      return this.fieldName.hashCode();
   }

   public boolean equals(Object o) {
      if (o instanceof ClassField) {
         ClassField cf = (ClassField)o;
         if (cf.fieldName.equals(this.fieldName)) {
            return this.type == cf.type;
         }
      }

      return false;
   }
}
