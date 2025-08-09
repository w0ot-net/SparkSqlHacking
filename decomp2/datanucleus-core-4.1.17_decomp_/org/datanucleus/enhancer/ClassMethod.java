package org.datanucleus.enhancer;

import java.util.Arrays;
import org.datanucleus.asm.ClassVisitor;
import org.datanucleus.asm.MethodVisitor;
import org.datanucleus.asm.Type;
import org.datanucleus.util.Localiser;

public abstract class ClassMethod {
   protected ClassEnhancer enhancer;
   protected String methodName;
   protected int access;
   protected Object returnType;
   protected Object[] argTypes;
   protected String[] argNames;
   protected String[] exceptions;
   protected MethodVisitor visitor;

   public ClassMethod(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      this(enhancer, name, access, returnType, argTypes, argNames, (String[])null);
   }

   public ClassMethod(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames, String[] exceptions) {
      this.enhancer = enhancer;
      this.methodName = name;
      this.access = access;
      this.returnType = returnType;
      this.argTypes = argTypes;
      this.argNames = argNames;
      this.exceptions = exceptions;
   }

   public void initialise() {
   }

   public void initialise(ClassVisitor classVisitor) {
      Type type = null;
      Type[] argtypes = null;
      if (this.returnType != null) {
         type = Type.getType((Class)this.returnType);
      } else {
         type = Type.VOID_TYPE;
      }

      if (this.argTypes != null) {
         argtypes = new Type[this.argTypes.length];

         for(int i = 0; i < this.argTypes.length; ++i) {
            argtypes[i] = Type.getType((Class)this.argTypes[i]);
         }
      } else {
         argtypes = new Type[0];
      }

      String methodDesc = Type.getMethodDescriptor(type, argtypes);
      this.visitor = classVisitor.visitMethod(this.access, this.methodName, methodDesc, (String)null, this.exceptions);
   }

   protected ClassEnhancer getClassEnhancer() {
      return this.enhancer;
   }

   public String getDescriptor() {
      StringBuilder str = new StringBuilder("(");
      if (this.argTypes != null && this.argTypes.length > 0) {
         for(int i = 0; i < this.argTypes.length; ++i) {
            str.append(Type.getDescriptor((Class)this.argTypes[i]));
         }
      }

      str.append(")");
      if (this.returnType != null) {
         str.append(Type.getDescriptor((Class)this.returnType));
      } else {
         str.append("V");
      }

      return str.toString();
   }

   public EnhancementNamer getNamer() {
      return this.enhancer.getNamer();
   }

   public String getName() {
      return this.methodName;
   }

   public int getAccess() {
      return this.access;
   }

   public abstract void execute();

   public void close() {
      if (DataNucleusEnhancer.LOGGER.isDebugEnabled()) {
         String msg = getMethodAdditionMessage(this.methodName, this.returnType, this.argTypes, this.argNames);
         DataNucleusEnhancer.LOGGER.debug(Localiser.msg("005019", msg));
      }

   }

   public int hashCode() {
      return this.methodName.hashCode();
   }

   public boolean equals(Object o) {
      if (o instanceof ClassMethod) {
         ClassMethod cb = (ClassMethod)o;
         if (cb.methodName.equals(this.methodName)) {
            return Arrays.equals(cb.argTypes, this.argTypes);
         }
      }

      return false;
   }

   public static String getMethodAdditionMessage(String methodName, Object returnType, Object[] argTypes, String[] argNames) {
      StringBuilder sb = new StringBuilder();
      if (returnType != null) {
         if (returnType instanceof Class) {
            sb.append(((Class)returnType).getName()).append(" ");
         } else {
            sb.append(returnType).append(" ");
         }
      } else {
         sb.append("void ");
      }

      sb.append(methodName).append("(");
      if (argTypes != null) {
         for(int i = 0; i < argTypes.length; ++i) {
            if (i != 0) {
               sb.append(", ");
            }

            if (argTypes[i] instanceof Class) {
               sb.append(((Class)argTypes[i]).getName());
            } else {
               sb.append(argTypes[i]);
            }

            sb.append(" ").append(argNames[i]);
         }
      }

      sb.append(")");
      return sb.toString();
   }
}
