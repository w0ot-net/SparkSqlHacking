package org.codehaus.janino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.nullanalysis.Nullable;

public abstract class IClass implements ITypeVariableOrIClass {
   private static final Logger LOGGER = Logger.getLogger(IClass.class.getName());
   public static final Object NOT_CONSTANT = new Object() {
      public String toString() {
         return "NOT_CONSTANT";
      }
   };
   public static final IClass NULL = new PrimitiveIClass("");
   public static final IClass VOID = new PrimitiveIClass("V");
   public static final IClass BYTE = new PrimitiveIClass("B");
   public static final IClass CHAR = new PrimitiveIClass("C");
   public static final IClass DOUBLE = new PrimitiveIClass("D");
   public static final IClass FLOAT = new PrimitiveIClass("F");
   public static final IClass INT = new PrimitiveIClass("I");
   public static final IClass LONG = new PrimitiveIClass("J");
   public static final IClass SHORT = new PrimitiveIClass("S");
   public static final IClass BOOLEAN = new PrimitiveIClass("Z");
   @Nullable
   private ITypeVariable[] iTypeVariablesCache;
   @Nullable
   private IConstructor[] declaredIConstructorsCache;
   @Nullable
   private IMethod[] declaredIMethodsCache;
   @Nullable
   private Map declaredIMethodCache;
   @Nullable
   private IMethod[] iMethodCache;
   private static final IMethod[] NO_IMETHODS = new IMethod[0];
   @Nullable
   private Map declaredIFieldsCache;
   @Nullable
   private IClass[] declaredIClassesCache;
   private boolean declaringIClassIsCached;
   @Nullable
   private IClass declaringIClassCache;
   private boolean outerIClassIsCached;
   @Nullable
   private IClass outerIClassCache;
   private boolean superclassIsCached;
   @Nullable
   private IClass superclassCache;
   @Nullable
   private IClass[] interfacesCache;
   @Nullable
   private String descriptorCache;
   private boolean componentTypeIsCached;
   @Nullable
   private IClass componentTypeCache;
   private static final Set PRIMITIVE_WIDENING_CONVERSIONS = new HashSet();
   private final Map memberTypeCache = new HashMap();
   private static final IClass[] ZERO_ICLASSES;
   @Nullable
   private IAnnotation[] iAnnotationsCache;
   public static final IAnnotation[] NO_ANNOTATIONS;

   public final ITypeVariable[] getITypeVariables() throws CompileException {
      return this.iTypeVariablesCache != null ? this.iTypeVariablesCache : (this.iTypeVariablesCache = this.getITypeVariables2());
   }

   protected abstract ITypeVariable[] getITypeVariables2() throws CompileException;

   public final IConstructor[] getDeclaredIConstructors() {
      return this.declaredIConstructorsCache != null ? this.declaredIConstructorsCache : (this.declaredIConstructorsCache = this.getDeclaredIConstructors2());
   }

   protected abstract IConstructor[] getDeclaredIConstructors2();

   public final IMethod[] getDeclaredIMethods() {
      return this.declaredIMethodsCache != null ? this.declaredIMethodsCache : (this.declaredIMethodsCache = this.getDeclaredIMethods2());
   }

   protected abstract IMethod[] getDeclaredIMethods2();

   public final IMethod[] getDeclaredIMethods(String methodName) {
      Map<String, Object> dimc = this.declaredIMethodCache;
      if (dimc == null) {
         IMethod[] dims = this.getDeclaredIMethods();
         dimc = new HashMap();

         for(IMethod dim : dims) {
            String mn = dim.getName();
            Object o = dimc.get(mn);
            if (o == null) {
               dimc.put(mn, dim);
            } else if (o instanceof IMethod) {
               List<IMethod> l = new ArrayList();
               l.add((IMethod)o);
               l.add(dim);
               dimc.put(mn, l);
            } else {
               List<IMethod> tmp = (List)o;
               tmp.add(dim);
            }
         }

         for(Map.Entry me : dimc.entrySet()) {
            Object v = me.getValue();
            if (v instanceof IMethod) {
               me.setValue(new IMethod[]{(IMethod)v});
            } else {
               List<IMethod> l = (List)v;
               me.setValue(l.toArray(new IMethod[l.size()]));
            }
         }

         this.declaredIMethodCache = dimc;
      }

      IMethod[] methods = (IMethod[])dimc.get(methodName);
      return methods == null ? NO_IMETHODS : methods;
   }

   public final IMethod[] getIMethods() throws CompileException {
      if (this.iMethodCache != null) {
         return this.iMethodCache;
      } else {
         List<IMethod> iMethods = new ArrayList();
         this.getIMethods(iMethods);
         return this.iMethodCache = (IMethod[])iMethods.toArray(new IMethod[iMethods.size()]);
      }
   }

   private void getIMethods(List result) throws CompileException {
      IMethod[] ms = this.getDeclaredIMethods();

      label38:
      for(IMethod candidate : ms) {
         MethodDescriptor candidateDescriptor = candidate.getDescriptor();
         String candidateName = candidate.getName();

         for(IMethod oldMethod : result) {
            if (candidateName.equals(oldMethod.getName()) && candidateDescriptor.equals(oldMethod.getDescriptor())) {
               continue label38;
            }
         }

         result.add(candidate);
      }

      IClass sc = this.getSuperclass();
      if (sc != null) {
         sc.getIMethods(result);
      }

      for(IClass ii : this.getInterfaces()) {
         ii.getIMethods(result);
      }

   }

   public final boolean hasIMethod(String methodName, IClass[] parameterTypes) throws CompileException {
      return this.findIMethod(methodName, parameterTypes) != null;
   }

   @Nullable
   public final IMethod findIMethod(String methodName, IClass[] parameterTypes) throws CompileException {
      IMethod result = null;

      for(IMethod im : this.getDeclaredIMethods(methodName)) {
         if (Arrays.equals(im.getParameterTypes(), parameterTypes) && (result == null || result.getReturnType().isAssignableFrom(im.getReturnType()))) {
            result = im;
         }
      }

      if (result != null) {
         return result;
      } else {
         IClass superclass = this.getSuperclass();
         if (superclass != null) {
            IMethod result = superclass.findIMethod(methodName, parameterTypes);
            if (result != null) {
               return result;
            }
         }

         superclass = this.getInterfaces();

         for(IClass interfacE : superclass) {
            IMethod result = interfacE.findIMethod(methodName, parameterTypes);
            if (result != null) {
               return result;
            }
         }

         return null;
      }
   }

   @Nullable
   public final IConstructor findIConstructor(IClass[] parameterTypes) throws CompileException {
      IConstructor[] ics = this.getDeclaredIConstructors();

      for(IConstructor ic : ics) {
         if (Arrays.equals(ic.getParameterTypes(), parameterTypes)) {
            return ic;
         }
      }

      return null;
   }

   public final IField[] getDeclaredIFields() {
      Collection<IField> allFields = this.getDeclaredIFieldsCache().values();
      return (IField[])allFields.toArray(new IField[allFields.size()]);
   }

   private Map getDeclaredIFieldsCache() {
      if (this.declaredIFieldsCache != null) {
         return this.declaredIFieldsCache;
      } else {
         IField[] fields = this.getDeclaredIFields2();
         Map<String, IField> m = new LinkedHashMap();

         for(IField f : fields) {
            m.put(f.getName(), f);
         }

         return this.declaredIFieldsCache = m;
      }
   }

   @Nullable
   public final IField getDeclaredIField(String name) {
      return (IField)this.getDeclaredIFieldsCache().get(name);
   }

   protected void clearIFieldCaches() {
      this.declaredIFieldsCache = null;
   }

   protected abstract IField[] getDeclaredIFields2();

   public IField[] getSyntheticIFields() {
      return new IField[0];
   }

   public final IClass[] getDeclaredIClasses() throws CompileException {
      return this.declaredIClassesCache != null ? this.declaredIClassesCache : (this.declaredIClassesCache = this.getDeclaredIClasses2());
   }

   protected abstract IClass[] getDeclaredIClasses2() throws CompileException;

   @Nullable
   public final IClass getDeclaringIClass() throws CompileException {
      if (!this.declaringIClassIsCached) {
         this.declaringIClassCache = this.getDeclaringIClass2();
         this.declaringIClassIsCached = true;
      }

      return this.declaringIClassCache;
   }

   @Nullable
   protected abstract IClass getDeclaringIClass2() throws CompileException;

   @Nullable
   public final IClass getOuterIClass() throws CompileException {
      if (this.outerIClassIsCached) {
         return this.outerIClassCache;
      } else {
         this.outerIClassIsCached = true;
         return this.outerIClassCache = this.getOuterIClass2();
      }
   }

   @Nullable
   protected abstract IClass getOuterIClass2() throws CompileException;

   @Nullable
   public final IClass getSuperclass() throws CompileException {
      if (this.superclassIsCached) {
         return this.superclassCache;
      } else {
         IClass sc = this.getSuperclass2();
         if (sc != null && rawTypeOf(sc).isSubclassOf(this)) {
            throw new CompileException("Class circularity detected for \"" + Descriptor.toClassName(this.getDescriptor()) + "\"", (Location)null);
         } else {
            this.superclassIsCached = true;
            return this.superclassCache = sc;
         }
      }
   }

   @Nullable
   protected abstract IClass getSuperclass2() throws CompileException;

   public abstract Access getAccess();

   public abstract boolean isFinal();

   public final IClass[] getInterfaces() throws CompileException {
      if (this.interfacesCache != null) {
         return this.interfacesCache;
      } else {
         IClass[] is = this.getInterfaces2();

         for(IClass ii : is) {
            if (ii.implementsInterface(this)) {
               throw new CompileException("Interface circularity detected for \"" + Descriptor.toClassName(this.getDescriptor()) + "\"", (Location)null);
            }
         }

         return this.interfacesCache = is;
      }
   }

   protected abstract IClass[] getInterfaces2() throws CompileException;

   public abstract boolean isAbstract();

   public final String getDescriptor() {
      return this.descriptorCache != null ? this.descriptorCache : (this.descriptorCache = this.getDescriptor2());
   }

   protected abstract String getDescriptor2();

   public static String[] getDescriptors(IClass[] iClasses) {
      String[] descriptors = new String[iClasses.length];

      for(int i = 0; i < iClasses.length; ++i) {
         descriptors[i] = iClasses[i].getDescriptor();
      }

      return descriptors;
   }

   public abstract boolean isEnum();

   public abstract boolean isInterface();

   public abstract boolean isArray();

   public abstract boolean isPrimitive();

   public abstract boolean isPrimitiveNumeric();

   @Nullable
   public final IClass getComponentType() {
      if (this.componentTypeIsCached) {
         return this.componentTypeCache;
      } else {
         this.componentTypeCache = this.getComponentType2();
         this.componentTypeIsCached = true;
         return this.componentTypeCache;
      }
   }

   @Nullable
   protected abstract IClass getComponentType2();

   public String toString() {
      String className = Descriptor.toClassName(this.getDescriptor());
      if (className.startsWith("java.lang.") && className.indexOf(46, 10) == -1) {
         className = className.substring(10);
      }

      return className;
   }

   public boolean isAssignableFrom(IClass that) throws CompileException {
      if (this == that) {
         return true;
      } else {
         String ds = that.getDescriptor() + this.getDescriptor();
         if (ds.length() == 2 && PRIMITIVE_WIDENING_CONVERSIONS.contains(ds)) {
            return true;
         } else if (that.isSubclassOf(this)) {
            return true;
         } else if (that.implementsInterface(this)) {
            return true;
         } else if (that == NULL && !this.isPrimitive()) {
            return true;
         } else if (that.isInterface() && this.getDescriptor().equals("Ljava/lang/Object;")) {
            return true;
         } else {
            if (that.isArray()) {
               if (this.getDescriptor().equals("Ljava/lang/Object;")) {
                  return true;
               }

               if (this.getDescriptor().equals("Ljava/lang/Cloneable;")) {
                  return true;
               }

               if (this.getDescriptor().equals("Ljava/io/Serializable;")) {
                  return true;
               }

               if (this.isArray()) {
                  IClass thisCt = this.getComponentType();
                  IClass thatCt = that.getComponentType();

                  assert thisCt != null;

                  assert thatCt != null;

                  if (!thisCt.isPrimitive() && thisCt.isAssignableFrom(thatCt)) {
                     return true;
                  }
               }
            }

            return false;
         }
      }
   }

   public boolean isSubclassOf(IClass that) throws CompileException {
      for(IClass sc = this.getSuperclass(); sc != null; sc = sc.getSuperclass()) {
         if (sc == that) {
            return true;
         }
      }

      return false;
   }

   public boolean implementsInterface(IClass that) throws CompileException {
      for(IClass c = this; c != null; c = c.getSuperclass()) {
         IClass[] tis = c.getInterfaces();

         for(IClass ti : tis) {
            if (ti == that || ti.implementsInterface(that)) {
               return true;
            }
         }
      }

      return false;
   }

   IClass[] findMemberType(@Nullable String name) throws CompileException {
      IClass[] res = (IClass[])this.memberTypeCache.get(name);
      if (res == null) {
         Set<IClass> s = new HashSet();
         this.findMemberType(name, s);
         res = s.isEmpty() ? ZERO_ICLASSES : (IClass[])s.toArray(new IClass[s.size()]);
         this.memberTypeCache.put(name, res);
      }

      return res;
   }

   private void findMemberType(@Nullable String name, Collection result) throws CompileException {
      IClass[] memberTypes = this.getDeclaredIClasses();
      if (name == null) {
         result.addAll(Arrays.asList(memberTypes));
      } else {
         String memberDescriptor = Descriptor.fromClassName(Descriptor.toClassName(this.getDescriptor()) + '$' + name);

         for(IClass mt : memberTypes) {
            if (mt.getDescriptor().equals(memberDescriptor)) {
               result.add(mt);
               return;
            }
         }
      }

      IClass superclass = this.getSuperclass();
      if (superclass != null) {
         superclass.findMemberType(name, result);
      }

      for(IClass i : this.getInterfaces()) {
         i.findMemberType(name, result);
      }

      superclass = this.getDeclaringIClass();
      IClass outerIClass = this.getOuterIClass();
      if (superclass != null) {
         superclass.findMemberType(name, result);
      }

      if (outerIClass != null && outerIClass != superclass) {
         outerIClass.findMemberType(name, result);
      }

   }

   public final IAnnotation[] getIAnnotations() throws CompileException {
      return this.iAnnotationsCache != null ? this.iAnnotationsCache : (this.iAnnotationsCache = this.getIAnnotations2());
   }

   protected IAnnotation[] getIAnnotations2() throws CompileException {
      return NO_ANNOTATIONS;
   }

   public void invalidateMethodCaches() {
      this.declaredIMethodsCache = null;
      this.declaredIMethodCache = null;
   }

   public static IClass rawTypeOf(IType type) {
      while(type instanceof IParameterizedType) {
         type = ((IParameterizedType)type).getRawType();
      }

      assert type instanceof IClass;

      return (IClass)type;
   }

   static {
      String[] pwcs = new String[]{"BS", "BI", "SI", "CI", "BJ", "SJ", "CJ", "IJ", "BF", "SF", "CF", "IF", "JF", "BD", "SD", "CD", "ID", "JD", "FD"};

      for(String pwc : pwcs) {
         PRIMITIVE_WIDENING_CONVERSIONS.add(pwc);
      }

      ZERO_ICLASSES = new IClass[0];
      NO_ANNOTATIONS = new IAnnotation[0];
   }

   private static class PrimitiveIClass extends IClass {
      private final String fieldDescriptor;

      PrimitiveIClass(String fieldDescriptor) {
         this.fieldDescriptor = fieldDescriptor;
      }

      protected ITypeVariable[] getITypeVariables2() {
         return new ITypeVariable[0];
      }

      @Nullable
      protected IClass getComponentType2() {
         return null;
      }

      protected IClass[] getDeclaredIClasses2() {
         return new IClass[0];
      }

      protected IConstructor[] getDeclaredIConstructors2() {
         return new IConstructor[0];
      }

      protected IField[] getDeclaredIFields2() {
         return new IField[0];
      }

      protected IMethod[] getDeclaredIMethods2() {
         return new IMethod[0];
      }

      @Nullable
      protected IClass getDeclaringIClass2() {
         return null;
      }

      protected String getDescriptor2() {
         return this.fieldDescriptor;
      }

      protected IClass[] getInterfaces2() {
         return new IClass[0];
      }

      @Nullable
      protected IClass getOuterIClass2() {
         return null;
      }

      @Nullable
      protected IClass getSuperclass2() {
         return null;
      }

      public boolean isAbstract() {
         return false;
      }

      public boolean isArray() {
         return false;
      }

      public boolean isFinal() {
         return true;
      }

      public boolean isEnum() {
         return false;
      }

      public boolean isInterface() {
         return false;
      }

      public boolean isPrimitive() {
         return true;
      }

      public Access getAccess() {
         return Access.PUBLIC;
      }

      public boolean isPrimitiveNumeric() {
         return Descriptor.isPrimitiveNumeric(this.fieldDescriptor);
      }
   }

   public abstract class IInvocable implements IMember {
      private boolean argsNeedAdjust;
      @Nullable
      private IClass[] parameterTypesCache;
      @Nullable
      private MethodDescriptor descriptorCache;
      @Nullable
      private IClass[] thrownExceptionsCache;

      public void setArgsNeedAdjust(boolean newVal) {
         this.argsNeedAdjust = newVal;
      }

      public boolean argsNeedAdjust() {
         return this.argsNeedAdjust;
      }

      public abstract boolean isVarargs();

      public IClass getDeclaringIClass() {
         return IClass.this;
      }

      public final IClass[] getParameterTypes() throws CompileException {
         return this.parameterTypesCache != null ? this.parameterTypesCache : (this.parameterTypesCache = this.getParameterTypes2());
      }

      public abstract IClass[] getParameterTypes2() throws CompileException;

      public final MethodDescriptor getDescriptor() throws CompileException {
         return this.descriptorCache != null ? this.descriptorCache : (this.descriptorCache = this.getDescriptor2());
      }

      public abstract MethodDescriptor getDescriptor2() throws CompileException;

      public final IClass[] getThrownExceptions() throws CompileException {
         return this.thrownExceptionsCache != null ? this.thrownExceptionsCache : (this.thrownExceptionsCache = this.getThrownExceptions2());
      }

      public abstract IClass[] getThrownExceptions2() throws CompileException;

      public boolean isMoreSpecificThan(IInvocable that) throws CompileException {
         IClass.LOGGER.entering((String)null, "isMoreSpecificThan", that);
         boolean thatIsVarargs;
         if ((thatIsVarargs = that.isVarargs()) != this.isVarargs()) {
            return thatIsVarargs;
         } else if (!thatIsVarargs) {
            IClass[] thisParameterTypes = this.getParameterTypes();
            IClass[] thatParameterTypes = that.getParameterTypes();

            for(int i = 0; i < thisParameterTypes.length; ++i) {
               if (!thatParameterTypes[i].isAssignableFrom(thisParameterTypes[i])) {
                  IClass.LOGGER.exiting((String)null, "isMoreSpecificThan", false);
                  return false;
               }
            }

            boolean result = !Arrays.equals(thisParameterTypes, thatParameterTypes);
            IClass.LOGGER.exiting((String)null, "isMoreSpecificThan", result);
            return result;
         } else {
            IClass[] thisParameterTypes = this.getParameterTypes();
            IClass[] thatParameterTypes = that.getParameterTypes();
            if (thisParameterTypes.length >= thatParameterTypes.length) {
               IClass[] t = thisParameterTypes;
               int n = thisParameterTypes.length;
               int k = thatParameterTypes.length;
               IClass[] s = thatParameterTypes;
               int kMinus1 = k - 1;

               for(int j = 0; j < kMinus1; ++j) {
                  if (!s[j].isAssignableFrom(t[j])) {
                     return false;
                  }
               }

               IClass sk1 = s[kMinus1].getComponentType();

               assert sk1 != null;

               int nMinus1 = n - 1;

               for(int j = kMinus1; j < nMinus1; ++j) {
                  if (!sk1.isAssignableFrom(t[j])) {
                     return false;
                  }
               }

               if (!sk1.isAssignableFrom(t[nMinus1])) {
                  return false;
               }
            } else {
               IClass[] u = thisParameterTypes;
               int n = thatParameterTypes.length;
               int k = thisParameterTypes.length;
               IClass[] s = thatParameterTypes;
               int kMinus1 = k - 1;

               for(int j = 0; j < kMinus1; ++j) {
                  if (!s[j].isAssignableFrom(u[j])) {
                     return false;
                  }
               }

               IClass uk1 = u[kMinus1].getComponentType();

               assert uk1 != null;

               int nMinus1 = n - 1;

               for(int j = kMinus1; j < nMinus1; ++j) {
                  if (!s[j].isAssignableFrom(uk1)) {
                     return false;
                  }
               }

               IClass snm1ct = s[nMinus1].getComponentType();

               assert snm1ct != null;

               if (!snm1ct.isAssignableFrom(uk1)) {
                  return false;
               }
            }

            return true;
         }
      }

      public boolean isLessSpecificThan(IInvocable that) throws CompileException {
         return that.isMoreSpecificThan(this);
      }

      public abstract String toString();
   }

   public abstract class IConstructor extends IInvocable {
      public MethodDescriptor getDescriptor2() throws CompileException {
         IClass[] parameterTypes = this.getParameterTypes();
         IClass outerIClass = IClass.this.getOuterIClass();
         if (outerIClass != null) {
            IClass[] tmp = new IClass[parameterTypes.length + 1];
            tmp[0] = outerIClass;
            System.arraycopy(parameterTypes, 0, tmp, 1, parameterTypes.length);
            parameterTypes = tmp;
         }

         String[] parameterFds = IClass.getDescriptors(parameterTypes);
         if (this.getDeclaringIClass().isEnum()) {
            String[] tmp = new String[parameterFds.length + 2];
            tmp[0] = "Ljava/lang/String;";
            tmp[1] = "I";
            System.arraycopy(parameterFds, 0, tmp, 2, parameterFds.length);
            parameterFds = tmp;
         }

         return new MethodDescriptor("V", parameterFds);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder(this.getDeclaringIClass().toString());
         sb.append('(');

         try {
            IClass[] parameterTypes = this.getParameterTypes();

            for(int i = 0; i < parameterTypes.length; ++i) {
               if (i > 0) {
                  sb.append(", ");
               }

               sb.append(parameterTypes[i].toString());
            }
         } catch (CompileException var4) {
            sb.append("<invalid type>");
         }

         sb.append(')');
         return sb.toString();
      }
   }

   public abstract class IMethod extends IInvocable {
      public abstract boolean isStatic();

      public abstract boolean isAbstract();

      public abstract IClass getReturnType() throws CompileException;

      public abstract String getName();

      public MethodDescriptor getDescriptor2() throws CompileException {
         return new MethodDescriptor(this.getReturnType().getDescriptor(), IClass.getDescriptors(this.getParameterTypes()));
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         sb.append(this.getAccess().toString()).append(' ');
         if (this.isStatic()) {
            sb.append("static ");
         }

         if (this.isAbstract()) {
            sb.append("abstract ");
         }

         try {
            sb.append(this.getReturnType().toString());
         } catch (CompileException var4) {
            sb.append("<invalid type>");
         }

         sb.append(' ');
         sb.append(this.getDeclaringIClass().toString());
         sb.append('.');
         sb.append(this.getName());
         sb.append('(');

         try {
            IClass[] parameterTypes = this.getParameterTypes();

            for(int i = 0; i < parameterTypes.length; ++i) {
               if (i > 0) {
                  sb.append(", ");
               }

               sb.append(parameterTypes[i].toString());
            }
         } catch (CompileException var6) {
            sb.append("<invalid type>");
         }

         sb.append(')');

         try {
            IClass[] tes = this.getThrownExceptions();
            if (tes.length > 0) {
               sb.append(" throws ").append(tes[0]);

               for(int i = 1; i < tes.length; ++i) {
                  sb.append(", ").append(tes[i]);
               }
            }
         } catch (CompileException var5) {
            sb.append("<invalid thrown exception type>");
         }

         return sb.toString();
      }
   }

   public abstract class IField implements IMember {
      public abstract Access getAccess();

      public IClass getDeclaringIClass() {
         return IClass.this;
      }

      public abstract boolean isStatic();

      public abstract IClass getType() throws CompileException;

      public abstract String getName();

      public String getDescriptor() throws CompileException {
         return IClass.rawTypeOf(this.getType()).getDescriptor();
      }

      @Nullable
      public abstract Object getConstantValue() throws CompileException;

      public String toString() {
         return this.getDeclaringIClass().toString() + "." + this.getName();
      }
   }

   public interface IAnnotation {
      IType getAnnotationType() throws CompileException;

      Object getElementValue(String var1) throws CompileException;
   }

   public interface IMember {
      Access getAccess();

      IAnnotation[] getAnnotations();

      IClass getDeclaringIClass();
   }
}
