package javassist.bytecode.analysis;

import java.util.HashMap;
import java.util.Map;
import javassist.CtClass;

public class MultiType extends Type {
   private Map interfaces;
   private Type resolved;
   private Type potentialClass;
   private MultiType mergeSource;
   private boolean changed;

   public MultiType(Map interfaces) {
      this(interfaces, (Type)null);
   }

   public MultiType(Map interfaces, Type potentialClass) {
      super((CtClass)null);
      this.changed = false;
      this.interfaces = interfaces;
      this.potentialClass = potentialClass;
   }

   public CtClass getCtClass() {
      return this.resolved != null ? this.resolved.getCtClass() : Type.OBJECT.getCtClass();
   }

   public Type getComponent() {
      return null;
   }

   public int getSize() {
      return 1;
   }

   public boolean isArray() {
      return false;
   }

   boolean popChanged() {
      boolean changed = this.changed;
      this.changed = false;
      return changed;
   }

   public boolean isAssignableFrom(Type type) {
      throw new UnsupportedOperationException("Not implemented");
   }

   public boolean isAssignableTo(Type type) {
      if (this.resolved != null) {
         return type.isAssignableFrom(this.resolved);
      } else if (Type.OBJECT.equals(type)) {
         return true;
      } else {
         if (this.potentialClass != null && !type.isAssignableFrom(this.potentialClass)) {
            this.potentialClass = null;
         }

         Map<String, CtClass> map = this.mergeMultiAndSingle(this, type);
         if (map.size() == 1 && this.potentialClass == null) {
            this.resolved = Type.get((CtClass)map.values().iterator().next());
            this.propogateResolved();
            return true;
         } else if (map.size() >= 1) {
            this.interfaces = map;
            this.propogateState();
            return true;
         } else if (this.potentialClass != null) {
            this.resolved = this.potentialClass;
            this.propogateResolved();
            return true;
         } else {
            return false;
         }
      }
   }

   private void propogateState() {
      for(MultiType source = this.mergeSource; source != null; source = source.mergeSource) {
         source.interfaces = this.interfaces;
         source.potentialClass = this.potentialClass;
      }

   }

   private void propogateResolved() {
      for(MultiType source = this.mergeSource; source != null; source = source.mergeSource) {
         source.resolved = this.resolved;
      }

   }

   public boolean isReference() {
      return true;
   }

   private Map getAllMultiInterfaces(MultiType type) {
      Map<String, CtClass> map = new HashMap();

      for(CtClass intf : type.interfaces.values()) {
         map.put(intf.getName(), intf);
         this.getAllInterfaces(intf, map);
      }

      return map;
   }

   private Map mergeMultiInterfaces(MultiType type1, MultiType type2) {
      Map<String, CtClass> map1 = this.getAllMultiInterfaces(type1);
      Map<String, CtClass> map2 = this.getAllMultiInterfaces(type2);
      return this.findCommonInterfaces(map1, map2);
   }

   private Map mergeMultiAndSingle(MultiType multi, Type single) {
      Map<String, CtClass> map1 = this.getAllMultiInterfaces(multi);
      Map<String, CtClass> map2 = this.getAllInterfaces(single.getCtClass(), (Map)null);
      return this.findCommonInterfaces(map1, map2);
   }

   private boolean inMergeSource(MultiType source) {
      while(source != null) {
         if (source == this) {
            return true;
         }

         source = source.mergeSource;
      }

      return false;
   }

   public Type merge(Type type) {
      if (this == type) {
         return this;
      } else if (type == UNINIT) {
         return this;
      } else if (type == BOGUS) {
         return BOGUS;
      } else if (type == null) {
         return this;
      } else if (this.resolved != null) {
         return this.resolved.merge(type);
      } else {
         if (this.potentialClass != null) {
            Type mergePotential = this.potentialClass.merge(type);
            if (!mergePotential.equals(this.potentialClass) || mergePotential.popChanged()) {
               this.potentialClass = Type.OBJECT.equals(mergePotential) ? null : mergePotential;
               this.changed = true;
            }
         }

         Map<String, CtClass> merged;
         if (type instanceof MultiType) {
            MultiType multi = (MultiType)type;
            if (multi.resolved != null) {
               merged = this.mergeMultiAndSingle(this, multi.resolved);
            } else {
               merged = this.mergeMultiInterfaces(multi, this);
               if (!this.inMergeSource(multi)) {
                  this.mergeSource = multi;
               }
            }
         } else {
            merged = this.mergeMultiAndSingle(this, type);
         }

         if (merged.size() > 1 || merged.size() == 1 && this.potentialClass != null) {
            if (merged.size() != this.interfaces.size()) {
               this.changed = true;
            } else if (!this.changed) {
               for(String key : merged.keySet()) {
                  if (!this.interfaces.containsKey(key)) {
                     this.changed = true;
                  }
               }
            }

            this.interfaces = merged;
            this.propogateState();
            return this;
         } else {
            if (merged.size() == 1) {
               this.resolved = Type.get((CtClass)merged.values().iterator().next());
            } else if (this.potentialClass != null) {
               this.resolved = this.potentialClass;
            } else {
               this.resolved = OBJECT;
            }

            this.propogateResolved();
            return this.resolved;
         }
      }
   }

   public int hashCode() {
      return this.resolved != null ? this.resolved.hashCode() : this.interfaces.keySet().hashCode();
   }

   public boolean equals(Object o) {
      if (!(o instanceof MultiType)) {
         return false;
      } else {
         MultiType multi = (MultiType)o;
         if (this.resolved != null) {
            return this.resolved.equals(multi.resolved);
         } else {
            return multi.resolved != null ? false : this.interfaces.keySet().equals(multi.interfaces.keySet());
         }
      }
   }

   public String toString() {
      if (this.resolved != null) {
         return this.resolved.toString();
      } else {
         StringBuilder buffer = new StringBuilder();
         buffer.append('{');

         for(String key : this.interfaces.keySet()) {
            buffer.append(key).append(", ");
         }

         if (this.potentialClass != null) {
            buffer.append('*').append(this.potentialClass.toString());
         } else {
            buffer.setLength(buffer.length() - 2);
         }

         buffer.append('}');
         return buffer.toString();
      }
   }
}
