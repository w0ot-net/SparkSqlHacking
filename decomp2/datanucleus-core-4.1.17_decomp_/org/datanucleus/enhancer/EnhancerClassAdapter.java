package org.datanucleus.enhancer;

import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.datanucleus.asm.ClassVisitor;
import org.datanucleus.asm.FieldVisitor;
import org.datanucleus.asm.MethodVisitor;
import org.datanucleus.asm.Type;
import org.datanucleus.enhancer.methods.DefaultConstructor;
import org.datanucleus.enhancer.methods.GetNormal;
import org.datanucleus.enhancer.methods.GetViaCheck;
import org.datanucleus.enhancer.methods.GetViaMediate;
import org.datanucleus.enhancer.methods.InitClass;
import org.datanucleus.enhancer.methods.SetNormal;
import org.datanucleus.enhancer.methods.SetViaCheck;
import org.datanucleus.enhancer.methods.SetViaMediate;
import org.datanucleus.enhancer.methods.WriteObject;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassPersistenceModifier;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.metadata.PropertyMetaData;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.StringUtils;

public class EnhancerClassAdapter extends ClassVisitor {
   protected ClassEnhancer enhancer;
   protected boolean hasDefaultConstructor = false;
   protected boolean hasSerialVersionUID = false;
   protected boolean hasDetachedState = false;
   protected boolean hasWriteObject = false;
   protected boolean hasStaticInitialisation = false;

   public EnhancerClassAdapter(ClassVisitor cv, ClassEnhancer enhancer) {
      super(327680, cv);
      this.enhancer = enhancer;
   }

   public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
      if (this.enhancer.getClassMetaData().getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
         boolean alreadyPersistable = false;
         boolean alreadyDetachable = false;
         boolean needsPersistable = false;
         boolean needsDetachable = false;
         int numInterfaces = 0;
         if (interfaces != null && interfaces.length > 0) {
            numInterfaces = interfaces.length;

            for(int i = 0; i < interfaces.length; ++i) {
               if (interfaces[i].equals(this.enhancer.getNamer().getDetachableAsmClassName())) {
                  alreadyDetachable = true;
               }

               if (interfaces[i].equals(this.enhancer.getNamer().getPersistableAsmClassName())) {
                  alreadyPersistable = true;
               }
            }
         }

         if (!alreadyDetachable && this.enhancer.getClassMetaData().isDetachable()) {
            ++numInterfaces;
            needsDetachable = true;
         }

         if (!alreadyPersistable) {
            ++numInterfaces;
            needsPersistable = true;
         }

         String[] intfs = interfaces;
         if (needsDetachable || needsPersistable) {
            intfs = new String[numInterfaces];
            int position = 0;
            if (interfaces != null && interfaces.length > 0) {
               for(int i = 0; i < interfaces.length; ++i) {
                  intfs[position++] = interfaces[i];
               }
            }

            if (needsDetachable) {
               intfs[position++] = this.enhancer.getNamer().getDetachableAsmClassName();
               if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
                  DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005022", this.enhancer.getNamer().getDetachableClass().getName()));
               }
            }

            if (needsPersistable) {
               intfs[position++] = this.enhancer.getNamer().getPersistableAsmClassName();
               if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
                  DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005022", this.enhancer.getNamer().getPersistableClass().getName()));
               }
            }
         }

         this.cv.visit(version, access, name, signature, superName, intfs);
      } else {
         this.cv.visit(version, access, name, signature, superName, interfaces);
      }

   }

   public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
      if (name.equals(this.enhancer.getNamer().getSerialVersionUidFieldName())) {
         this.hasSerialVersionUID = true;
      } else if (name.equals(this.enhancer.getNamer().getDetachedStateFieldName())) {
         this.hasDetachedState = true;
      }

      return super.visitField(access, name, desc, signature, value);
   }

   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
      if (name.equals("<init>") && desc != null && desc.equals("()V")) {
         this.hasDefaultConstructor = true;
      }

      if (name.equals("writeObject") && desc != null && desc.equals("(Ljava/io/ObjectOutputStream;)V")) {
         this.hasWriteObject = true;
      }

      if (name.equals("<clinit>") && desc != null && desc.equals("()V")) {
         this.hasStaticInitialisation = true;
      }

      MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
      if (mv == null) {
         return null;
      } else if (!name.equals("jdoPreClear") && !name.equals("jdoPostLoad")) {
         if (!name.equals("readObject") || !desc.equals("(Ljava/io/ObjectOutputStream;)V") && !desc.equals("(Ljava/io/ObjectInputStream;)V")) {
            if ((access & 64) != 64) {
               String propGetterName = ClassUtils.getFieldNameForJavaBeanGetter(name);
               String propSetterName = ClassUtils.getFieldNameForJavaBeanSetter(name);
               if (propGetterName != null) {
                  AbstractMemberMetaData mmd = this.enhancer.getClassMetaData().getMetaDataForMember(propGetterName);
                  if (mmd != null && mmd instanceof PropertyMetaData && mmd.getPersistenceModifier() != FieldPersistenceModifier.NONE) {
                     return new EnhancerPropertyGetterAdapter(mv, this.enhancer, name, desc, mmd, this.cv);
                  }
               } else if (propSetterName != null) {
                  AbstractMemberMetaData mmd = this.enhancer.getClassMetaData().getMetaDataForMember(propSetterName);
                  if (mmd != null && mmd instanceof PropertyMetaData && mmd.getPersistenceModifier() != FieldPersistenceModifier.NONE) {
                     return new EnhancerPropertySetterAdapter(mv, this.enhancer, name, desc, mmd, this.cv);
                  }
               }
            }

            return new EnhancerMethodAdapter(mv, this.enhancer, name, desc);
         } else {
            return mv;
         }
      } else {
         return mv;
      }
   }

   public void visitEnd() {
      AbstractClassMetaData cmd = this.enhancer.getClassMetaData();
      if (cmd.getPersistenceModifier() == ClassPersistenceModifier.PERSISTENCE_CAPABLE) {
         for(ClassField field : this.enhancer.getFieldsList()) {
            if (!field.getName().equals(this.enhancer.getNamer().getDetachedStateFieldName()) || !this.hasDetachedState) {
               if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
                  DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005021", ((Class)field.getType()).getName() + " " + field.getName()));
               }

               this.cv.visitField(field.getAccess(), field.getName(), Type.getDescriptor((Class)field.getType()), (String)null, (Object)null);
            }
         }

         if (!this.hasStaticInitialisation) {
            InitClass method = InitClass.getInstance(this.enhancer);
            method.initialise(this.cv);
            method.execute();
            method.close();
         }

         if (!this.hasDefaultConstructor && this.enhancer.hasOption("generate-default-constructor")) {
            DefaultConstructor ctr = DefaultConstructor.getInstance(this.enhancer);
            ctr.initialise(this.cv);
            ctr.execute();
            ctr.close();
         }

         for(ClassMethod method : this.enhancer.getMethodsList()) {
            method.initialise(this.cv);
            method.execute();
            method.close();
         }

         if (Serializable.class.isAssignableFrom(this.enhancer.getClassBeingEnhanced())) {
            if (!this.hasSerialVersionUID) {
               Long uid = null;

               try {
                  uid = (Long)AccessController.doPrivileged(new PrivilegedAction() {
                     public Object run() {
                        return ObjectStreamClass.lookup(EnhancerClassAdapter.this.enhancer.getClassBeingEnhanced()).getSerialVersionUID();
                     }
                  });
               } catch (Throwable e) {
                  DataNucleusEnhancer.LOGGER.warn(StringUtils.getStringFromStackTrace(e));
               }

               ClassField cf = new ClassField(this.enhancer, this.enhancer.getNamer().getSerialVersionUidFieldName(), 26, Long.TYPE, uid);
               if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
                  DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005021", ((Class)cf.getType()).getName() + " " + cf.getName()));
               }

               this.cv.visitField(cf.getAccess(), cf.getName(), Type.getDescriptor((Class)cf.getType()), (String)null, cf.getInitialValue());
            }

            if (cmd.getSuperAbstractClassMetaData() == null && !this.hasWriteObject) {
               ClassMethod method = WriteObject.getInstance(this.enhancer);
               method.initialise(this.cv);
               method.execute();
               method.close();
            }
         }

         AbstractMemberMetaData[] fmds = cmd.getManagedMembers();

         for(int i = 0; i < fmds.length; ++i) {
            if (fmds[i].getPersistenceModifier() != FieldPersistenceModifier.NONE) {
               byte persistenceFlags = fmds[i].getPersistenceFlags();
               ClassMethod getMethod = null;
               ClassMethod setMethod = null;
               if (!(fmds[i] instanceof PropertyMetaData)) {
                  if ((persistenceFlags & 2) == 2) {
                     getMethod = new GetViaMediate(this.enhancer, fmds[i]);
                  } else if ((persistenceFlags & 1) == 1) {
                     getMethod = new GetViaCheck(this.enhancer, fmds[i]);
                  } else {
                     getMethod = new GetNormal(this.enhancer, fmds[i]);
                  }

                  if ((persistenceFlags & 8) == 8) {
                     setMethod = new SetViaMediate(this.enhancer, fmds[i]);
                  } else if ((persistenceFlags & 4) == 4) {
                     setMethod = new SetViaCheck(this.enhancer, fmds[i]);
                  } else {
                     setMethod = new SetNormal(this.enhancer, fmds[i]);
                  }
               }

               if (getMethod != null) {
                  getMethod.initialise(this.cv);
                  getMethod.execute();
                  getMethod.close();
               }

               if (setMethod != null) {
                  setMethod.initialise(this.cv);
                  setMethod.execute();
                  setMethod.close();
               }
            }
         }
      }

      this.cv.visitEnd();
   }
}
