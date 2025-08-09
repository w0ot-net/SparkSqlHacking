package org.datanucleus.enhancer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.datanucleus.asm.AnnotationVisitor;
import org.datanucleus.asm.Attribute;
import org.datanucleus.asm.ClassVisitor;
import org.datanucleus.asm.FieldVisitor;
import org.datanucleus.asm.MethodVisitor;
import org.datanucleus.asm.Type;
import org.datanucleus.metadata.ClassPersistenceModifier;
import org.datanucleus.util.Localiser;

public class EnhancerClassChecker extends ClassVisitor {
   protected ClassEnhancer enhancer;
   protected Set fieldsRequired = new HashSet();
   protected Set methodsRequired = new HashSet();
   protected boolean enhanced = false;
   protected boolean logErrors = true;

   public EnhancerClassChecker(ClassEnhancer enhancer, boolean logErrors) {
      super(327680);
      this.enhancer = enhancer;
      this.logErrors = logErrors;
      if (enhancer.getClassMetaData().getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
         this.fieldsRequired.addAll(enhancer.getFieldsList());
         this.methodsRequired.addAll(enhancer.getMethodsList());
      }

   }

   public boolean isEnhanced() {
      return this.enhanced;
   }

   protected void reportError(String msg) {
      if (this.logErrors) {
         DataNucleusEnhancer.LOGGER.error(msg);
      } else if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
         DataNucleusEnhancer.LOGGER.debug(msg);
      }

      this.enhanced = false;
   }

   public void visit(int version, int access, String name, String signature, String supername, String[] interfaces) {
      this.enhanced = true;
      if (this.enhancer.getClassMetaData().getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
         if (interfaces == null) {
            this.enhanced = false;
            return;
         }

         if (!this.hasInterface(interfaces, this.enhancer.getNamer().getPersistableAsmClassName())) {
            this.reportError(Localiser.msg("005027", this.enhancer.getClassName(), this.enhancer.getNamer().getPersistableClass().getName()));
         }

         if (this.enhancer.getClassMetaData().isDetachable() && !this.hasInterface(interfaces, this.enhancer.getNamer().getDetachableAsmClassName())) {
            this.reportError(Localiser.msg("005027", this.enhancer.getClassName(), this.enhancer.getNamer().getDetachableClass().getName()));
         }
      }

   }

   protected boolean hasInterface(String[] interfaces, String intf) {
      if (interfaces != null && interfaces.length > 0) {
         for(int i = 0; i < interfaces.length; ++i) {
            if (interfaces[i].equals(intf)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
      return null;
   }

   public void visitAttribute(Attribute attr) {
   }

   public void visitEnd() {
      if (this.enhancer.getClassMetaData().getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
         for(ClassField field : this.fieldsRequired) {
            this.reportError(Localiser.msg("005028", this.enhancer.getClassName(), field.getName()));
         }

         for(ClassMethod method : this.methodsRequired) {
            this.reportError(Localiser.msg("005031", this.enhancer.getClassName(), method.getName()));
         }
      } else if (this.enhancer.getClassMetaData().getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_AWARE) {
         this.enhanced = false;
      }

   }

   public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
      Iterator iter = this.fieldsRequired.iterator();

      while(iter.hasNext()) {
         ClassField field = (ClassField)iter.next();
         if (field.getName().equals(name)) {
            if (field.getAccess() != access) {
               this.reportError(Localiser.msg("005029", this.enhancer.getClassName(), name));
            } else {
               if (desc.equals(Type.getDescriptor((Class)field.getType()))) {
                  iter.remove();
                  break;
               }

               this.reportError(Localiser.msg("005030", this.enhancer.getClassName(), name));
            }
         }
      }

      return null;
   }

   public void visitInnerClass(String name, String outerName, String innerName, int access) {
   }

   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
      Iterator<ClassMethod> iter = this.methodsRequired.iterator();

      while(iter.hasNext()) {
         ClassMethod method = (ClassMethod)iter.next();
         if (method.getName().equals(name) && method.getDescriptor().equals(desc)) {
            if (method.getAccess() == access) {
               iter.remove();
               break;
            }

            this.reportError(Localiser.msg("005032", this.enhancer.getClassName(), name));
         }
      }

      return null;
   }

   public void visitOuterClass(String owner, String name, String desc) {
   }

   public void visitSource(String source, String debug) {
   }
}
