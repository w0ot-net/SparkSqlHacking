package org.codehaus.janino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.util.ClassFile;
import org.codehaus.janino.util.signature.SignatureParser;

public class ClassFileIClass extends IClass {
   private static final Logger LOGGER = Logger.getLogger(ClassFileIClass.class.getName());
   private final ClassFile classFile;
   private final IClassLoader iClassLoader;
   private final short accessFlags;
   @Nullable
   private final SignatureParser.ClassSignature classSignature;
   private final Map resolvedFields = new HashMap();
   private final Map resolvedClasses = new HashMap();
   private final Map resolvedMethods = new HashMap();

   public ClassFileIClass(ClassFile classFile, IClassLoader iClassLoader) {
      this.classFile = classFile;
      this.iClassLoader = iClassLoader;
      this.accessFlags = classFile.accessFlags;
      ClassFile.SignatureAttribute sa = classFile.getSignatureAttribute();
      if (sa == null) {
         this.classSignature = null;
      } else {
         try {
            this.classSignature = (new SignatureParser()).decodeClassSignature(sa.getSignature(classFile));
         } catch (SignatureParser.SignatureException e) {
            throw new InternalCompilerException("Decoding signature of \"" + this + "\"", e);
         }
      }

   }

   protected ITypeVariable[] getITypeVariables2() throws CompileException {
      SignatureParser.ClassSignature cs = this.classSignature;
      if (cs == null) {
         return new ITypeVariable[0];
      } else {
         ITypeVariable[] result = new ITypeVariable[cs.formalTypeParameters.size()];

         for(int i = 0; i < result.length; ++i) {
            final SignatureParser.FormalTypeParameter ftp = (SignatureParser.FormalTypeParameter)cs.formalTypeParameters.get(i);
            final ITypeVariableOrIClass[] bounds = this.getBounds(ftp);
            result[i] = new ITypeVariable() {
               public String getName() {
                  return ftp.identifier;
               }

               public ITypeVariableOrIClass[] getBounds() {
                  return bounds;
               }

               public String toString() {
                  ITypeVariableOrIClass[] bs = this.getBounds();
                  String s = this.getName() + " extends " + bs[0];

                  for(int i = 1; i < bs.length; ++i) {
                     s = s + " & " + bs[i];
                  }

                  return s;
               }
            };
         }

         return result;
      }
   }

   private ITypeVariableOrIClass[] getBounds(SignatureParser.FormalTypeParameter ftp) throws CompileException {
      List<ITypeVariableOrIClass> result = new ArrayList();
      if (ftp.classBound != null) {
         result.add(this.fieldTypeSignatureToITypeVariableOrIClass(ftp.classBound));
      }

      return (ITypeVariableOrIClass[])result.toArray(new ITypeVariableOrIClass[result.size()]);
   }

   private ITypeVariableOrIClass fieldTypeSignatureToITypeVariableOrIClass(SignatureParser.FieldTypeSignature fts) throws CompileException {
      return (ITypeVariableOrIClass)fts.accept(new SignatureParser.FieldTypeSignatureVisitor() {
         public ITypeVariableOrIClass visitArrayTypeSignature(SignatureParser.ArrayTypeSignature ats) {
            throw new AssertionError(ats);
         }

         public ITypeVariableOrIClass visitClassTypeSignature(SignatureParser.ClassTypeSignature cts) throws CompileException {
            String fd = Descriptor.fromClassName(cts.packageSpecifier + cts.simpleClassName);

            IClass result;
            try {
               result = ClassFileIClass.this.iClassLoader.loadIClass(fd);
            } catch (ClassNotFoundException cnfe) {
               throw new CompileException("Loading \"" + Descriptor.toClassName(fd) + "\"", (Location)null, cnfe);
            }

            if (result == null) {
               throw new CompileException("Cannot load \"" + Descriptor.toClassName(fd) + "\"", (Location)null);
            } else {
               return result;
            }
         }

         public ITypeVariableOrIClass visitTypeVariableSignature(final SignatureParser.TypeVariableSignature tvs) {
            return new ITypeVariable() {
               public String getName() {
                  return tvs.identifier;
               }

               public ITypeVariableOrIClass[] getBounds() {
                  throw new AssertionError(this);
               }
            };
         }
      });
   }

   protected IClass.IConstructor[] getDeclaredIConstructors2() {
      List<IClass.IInvocable> iConstructors = new ArrayList();

      for(ClassFile.MethodInfo mi : this.classFile.methodInfos) {
         IClass.IInvocable ii;
         try {
            ii = this.resolveMethod(mi);
         } catch (ClassNotFoundException ex) {
            throw new InternalCompilerException(ex.getMessage(), ex);
         }

         if (ii instanceof IClass.IConstructor) {
            iConstructors.add(ii);
         }
      }

      return (IClass.IConstructor[])iConstructors.toArray(new IClass.IConstructor[iConstructors.size()]);
   }

   protected IClass.IMethod[] getDeclaredIMethods2() {
      List<IClass.IMethod> iMethods = new ArrayList();

      for(ClassFile.MethodInfo mi : this.classFile.methodInfos) {
         IClass.IInvocable ii;
         try {
            ii = this.resolveMethod(mi);
         } catch (ClassNotFoundException ex) {
            throw new InternalCompilerException(ex.getMessage(), ex);
         }

         if (ii instanceof IClass.IMethod) {
            iMethods.add((IClass.IMethod)ii);
         }
      }

      return (IClass.IMethod[])iMethods.toArray(new IClass.IMethod[iMethods.size()]);
   }

   protected IClass.IField[] getDeclaredIFields2() {
      IClass.IField[] ifs = new IClass.IField[this.classFile.fieldInfos.size()];

      for(int i = 0; i < this.classFile.fieldInfos.size(); ++i) {
         try {
            ifs[i] = this.resolveField((ClassFile.FieldInfo)this.classFile.fieldInfos.get(i));
         } catch (ClassNotFoundException ex) {
            throw new InternalCompilerException(ex.getMessage(), ex);
         }
      }

      return ifs;
   }

   protected IClass[] getDeclaredIClasses2() throws CompileException {
      ClassFile.InnerClassesAttribute ica = this.classFile.getInnerClassesAttribute();
      if (ica == null) {
         return new IClass[0];
      } else {
         List<IClass> res = new ArrayList();

         for(ClassFile.InnerClassesAttribute.Entry e : ica.getEntries()) {
            if (e.outerClassInfoIndex == this.classFile.thisClass) {
               try {
                  res.add(this.resolveClass(e.innerClassInfoIndex));
               } catch (ClassNotFoundException ex) {
                  throw new CompileException(ex.getMessage(), (Location)null);
               }
            }
         }

         return (IClass[])res.toArray(new IClass[res.size()]);
      }
   }

   @Nullable
   protected IClass getDeclaringIClass2() throws CompileException {
      ClassFile.InnerClassesAttribute ica = this.classFile.getInnerClassesAttribute();
      if (ica == null) {
         return null;
      } else {
         for(ClassFile.InnerClassesAttribute.Entry e : ica.getEntries()) {
            if (e.innerClassInfoIndex == this.classFile.thisClass) {
               if (e.outerClassInfoIndex == 0) {
                  return null;
               }

               try {
                  return this.resolveClass(e.outerClassInfoIndex);
               } catch (ClassNotFoundException ex) {
                  throw new CompileException(ex.getMessage(), (Location)null);
               }
            }
         }

         return null;
      }
   }

   @Nullable
   protected IClass getOuterIClass2() throws CompileException {
      ClassFile.InnerClassesAttribute ica = this.classFile.getInnerClassesAttribute();
      if (ica == null) {
         return null;
      } else {
         for(ClassFile.InnerClassesAttribute.Entry e : ica.getEntries()) {
            if (e.innerClassInfoIndex == this.classFile.thisClass) {
               if (e.outerClassInfoIndex == 0) {
                  return null;
               }

               if (Mod.isStatic(e.innerClassAccessFlags)) {
                  return null;
               }

               try {
                  return this.resolveClass(e.outerClassInfoIndex);
               } catch (ClassNotFoundException ex) {
                  throw new CompileException(ex.getMessage(), (Location)null);
               }
            }
         }

         return null;
      }
   }

   @Nullable
   protected IClass getSuperclass2() throws CompileException {
      if (this.classFile.superclass != 0 && (this.classFile.accessFlags & 512) == 0) {
         try {
            return this.resolveClass(this.classFile.superclass);
         } catch (ClassNotFoundException e) {
            throw new CompileException(e.getMessage(), (Location)null);
         }
      } else {
         return null;
      }
   }

   public Access getAccess() {
      return accessFlags2Access(this.accessFlags);
   }

   public boolean isFinal() {
      return Mod.isFinal(this.accessFlags);
   }

   protected IClass[] getInterfaces2() throws CompileException {
      return this.resolveClasses(this.classFile.interfaces);
   }

   public boolean isAbstract() {
      return Mod.isAbstract(this.accessFlags);
   }

   protected String getDescriptor2() {
      return Descriptor.fromClassName(this.classFile.getThisClassName());
   }

   public boolean isEnum() {
      return Mod.isEnum(this.accessFlags);
   }

   public boolean isInterface() {
      return Mod.isInterface(this.accessFlags);
   }

   public boolean isArray() {
      return false;
   }

   public boolean isPrimitive() {
      return false;
   }

   public boolean isPrimitiveNumeric() {
      return false;
   }

   @Nullable
   protected IClass getComponentType2() {
      return null;
   }

   protected IClass.IAnnotation[] getIAnnotations2() throws CompileException {
      return this.toIAnnotations(this.classFile.getAnnotations(true));
   }

   private IClass.IAnnotation[] toIAnnotations(ClassFile.Annotation[] annotations) throws CompileException {
      int count = annotations.length;
      if (count == 0) {
         return new IClass.IAnnotation[0];
      } else {
         IClass.IAnnotation[] result = new IClass.IAnnotation[count];

         for(int i = 0; i < count; ++i) {
            result[i] = this.toIAnnotation(annotations[i]);
         }

         return result;
      }
   }

   private IClass.IAnnotation toIAnnotation(final ClassFile.Annotation annotation) throws CompileException {
      final Map<String, Object> evps2 = new HashMap();

      for(Map.Entry e : annotation.elementValuePairs.entrySet()) {
         Short elementNameIndex = (Short)e.getKey();
         ClassFile.ElementValue elementValue = (ClassFile.ElementValue)e.getValue();
         Object ev = elementValue.accept(new ClassFile.ElementValue.Visitor() {
            final ClassFile cf;

            {
               this.cf = ClassFileIClass.this.classFile;
            }

            public Object visitBooleanElementValue(ClassFile.BooleanElementValue subject) {
               return this.getConstantValue(subject.constantValueIndex);
            }

            public Object visitByteElementValue(ClassFile.ByteElementValue subject) {
               return this.getConstantValue(subject.constantValueIndex);
            }

            public Object visitCharElementValue(ClassFile.CharElementValue subject) {
               return this.getConstantValue(subject.constantValueIndex);
            }

            public Object visitClassElementValue(ClassFile.ClassElementValue subject) {
               return this.getConstantValue(subject.constantValueIndex);
            }

            public Object visitDoubleElementValue(ClassFile.DoubleElementValue subject) {
               return this.getConstantValue(subject.constantValueIndex);
            }

            public Object visitFloatElementValue(ClassFile.FloatElementValue subject) {
               return this.getConstantValue(subject.constantValueIndex);
            }

            public Object visitIntElementValue(ClassFile.IntElementValue subject) {
               return this.getConstantValue(subject.constantValueIndex);
            }

            public Object visitLongElementValue(ClassFile.LongElementValue subject) {
               return this.getConstantValue(subject.constantValueIndex);
            }

            public Object visitShortElementValue(ClassFile.ShortElementValue subject) {
               return this.getConstantValue(subject.constantValueIndex);
            }

            public Object visitStringElementValue(ClassFile.StringElementValue subject) {
               return this.getConstantValue(subject.constantValueIndex);
            }

            public Object visitAnnotation(ClassFile.Annotation subject) {
               throw new AssertionError("NYI");
            }

            public Object visitArrayElementValue(ClassFile.ArrayElementValue subject) throws CompileException {
               Object[] result = new Object[subject.values.length];

               for(int i = 0; i < result.length; ++i) {
                  result[i] = subject.values[i].accept(this);
               }

               return result;
            }

            public Object visitEnumConstValue(ClassFile.EnumConstValue subject) throws CompileException {
               IClass enumIClass;
               try {
                  enumIClass = ClassFileIClass.this.resolveClass(this.cf.getConstantUtf8(subject.typeNameIndex));
               } catch (ClassNotFoundException cnfe) {
                  throw new CompileException("Resolving enum element value: " + cnfe.getMessage(), (Location)null);
               }

               String enumConstantName = this.cf.getConstantUtf8(subject.constNameIndex);
               IClass.IField enumConstField = enumIClass.getDeclaredIField(enumConstantName);
               if (enumConstField == null) {
                  throw new CompileException("Enum \"" + enumIClass + "\" has no constant \"" + enumConstantName + "", (Location)null);
               } else {
                  return enumConstField;
               }
            }

            private Object getConstantValue(short index) {
               return this.cf.getConstantValuePoolInfo(index).getValue(this.cf);
            }
         });
         evps2.put(this.classFile.getConstantUtf8(elementNameIndex), ev);
      }

      return new IClass.IAnnotation() {
         public Object getElementValue(String name) {
            return evps2.get(name);
         }

         public IClass getAnnotationType() throws CompileException {
            try {
               return ClassFileIClass.this.resolveClass(ClassFileIClass.this.classFile.getConstantUtf8(annotation.typeIndex));
            } catch (ClassNotFoundException cnfe) {
               throw new CompileException("Resolving annotation type: " + cnfe.getMessage(), (Location)null);
            }
         }

         public String toString() {
            String result = "@" + Descriptor.toClassName(ClassFileIClass.this.classFile.getConstantUtf8(annotation.typeIndex));
            StringBuilder args = null;

            for(Map.Entry e : evps2.entrySet()) {
               if (args == null) {
                  args = new StringBuilder("(");
               } else {
                  args.append(", ");
               }

               args.append((String)e.getKey()).append(" = ").append(e.getValue());
            }

            return args == null ? result : result + args.toString() + ")";
         }
      };
   }

   public void resolveAllClasses() throws ClassNotFoundException {
      for(short i = 1; i < this.classFile.getConstantPoolSize(); ++i) {
         ClassFile.ConstantPoolInfo cpi = this.classFile.getConstantPoolInfo(i);
         if (cpi instanceof ClassFile.ConstantClassInfo) {
            this.resolveClass(i);
         } else if (cpi instanceof ClassFile.ConstantNameAndTypeInfo) {
            String descriptor = ((ClassFile.ConstantNameAndTypeInfo)cpi).getDescriptor(this.classFile);
            if (descriptor.charAt(0) == '(') {
               MethodDescriptor md = new MethodDescriptor(descriptor);
               this.resolveClass(md.returnFd);

               for(String parameterFd : md.parameterFds) {
                  this.resolveClass(parameterFd);
               }
            } else {
               this.resolveClass(descriptor);
            }
         }

         if (cpi.isWide()) {
            ++i;
         }
      }

   }

   private IClass resolveClass(short index) throws ClassNotFoundException {
      LOGGER.entering((String)null, "resolveClass", index);
      String cnif = this.classFile.getConstantClassInfo(index).getName(this.classFile);

      try {
         return this.resolveClass(Descriptor.fromInternalForm(cnif));
      } catch (RuntimeException re) {
         throw new RuntimeException("Resolving class \"" + cnif + "\": " + re.getMessage(), re);
      }
   }

   private IClass resolveClass(String descriptor) throws ClassNotFoundException {
      LOGGER.entering((String)null, "resolveIClass", descriptor);
      IClass result = (IClass)this.resolvedClasses.get(descriptor);
      if (result != null) {
         return result;
      } else {
         result = this.iClassLoader.loadIClass(descriptor);
         if (result == null) {
            throw new ClassNotFoundException(descriptor);
         } else {
            this.resolvedClasses.put(descriptor, result);
            return result;
         }
      }
   }

   private IClass[] resolveClasses(short[] ifs) throws CompileException {
      IClass[] result = new IClass[ifs.length];

      for(int i = 0; i < result.length; ++i) {
         try {
            result[i] = this.resolveClass(ifs[i]);
         } catch (ClassNotFoundException e) {
            throw new CompileException(e.getMessage(), (Location)null);
         }
      }

      return result;
   }

   private IClass.IInvocable resolveMethod(final ClassFile.MethodInfo methodInfo) throws ClassNotFoundException {
      IClass.IInvocable result = (IClass.IInvocable)this.resolvedMethods.get(methodInfo);
      if (result != null) {
         return result;
      } else {
         final String name = methodInfo.getName();
         MethodDescriptor md = new MethodDescriptor(methodInfo.getDescriptor());
         final IClass returnType = this.resolveClass(md.returnFd);
         final IClass[] parameterTypes = new IClass[md.parameterFds.length];

         for(int i = 0; i < parameterTypes.length; ++i) {
            parameterTypes[i] = this.resolveClass(md.parameterFds[i]);
         }

         IClass[] tes = null;
         ClassFile.AttributeInfo[] ais = methodInfo.getAttributes();

         for(ClassFile.AttributeInfo ai : ais) {
            if (ai instanceof ClassFile.ExceptionsAttribute) {
               ClassFile.ConstantClassInfo[] ccis = ((ClassFile.ExceptionsAttribute)ai).getExceptions(this.classFile);
               tes = new IClass[ccis.length];

               for(int i = 0; i < tes.length; ++i) {
                  tes[i] = this.resolveClass(Descriptor.fromInternalForm(ccis[i].getName(this.classFile)));
               }
            }
         }

         final IClass[] thrownExceptions = tes == null ? new IClass[0] : tes;
         final Access access = accessFlags2Access(methodInfo.getAccessFlags());

         final IClass.IAnnotation[] iAnnotations;
         try {
            iAnnotations = this.toIAnnotations(methodInfo.getAnnotations(true));
         } catch (CompileException ce) {
            throw new InternalCompilerException(ce.getMessage(), ce);
         }

         if ("<init>".equals(name)) {
            result = new IClass.IConstructor() {
               public boolean isVarargs() {
                  return Mod.isVarargs(methodInfo.getAccessFlags());
               }

               public IClass[] getParameterTypes2() throws CompileException {
                  IClass outerIClass = ClassFileIClass.this.getOuterIClass();
                  if (outerIClass != null) {
                     if (parameterTypes.length < 1) {
                        throw new InternalCompilerException("Inner class constructor lacks magic first parameter");
                     } else if (parameterTypes[0] != outerIClass) {
                        throw new InternalCompilerException("Magic first parameter of inner class constructor has type \"" + parameterTypes[0].toString() + "\" instead of that of its enclosing instance (\"" + outerIClass.toString() + "\")");
                     } else {
                        IClass[] tmp = new IClass[parameterTypes.length - 1];
                        System.arraycopy(parameterTypes, 1, tmp, 0, tmp.length);
                        return tmp;
                     }
                  } else {
                     return parameterTypes;
                  }
               }

               public IClass[] getThrownExceptions2() {
                  return thrownExceptions;
               }

               public Access getAccess() {
                  return access;
               }

               public IClass.IAnnotation[] getAnnotations() {
                  return iAnnotations;
               }
            };
         } else {
            result = new IClass.IMethod() {
               public IClass.IAnnotation[] getAnnotations() {
                  return iAnnotations;
               }

               public Access getAccess() {
                  return access;
               }

               public boolean isStatic() {
                  return Mod.isStatic(methodInfo.getAccessFlags());
               }

               public boolean isAbstract() {
                  return Mod.isAbstract(methodInfo.getAccessFlags());
               }

               public IClass getReturnType() {
                  return returnType;
               }

               public String getName() {
                  return name;
               }

               public IClass[] getParameterTypes2() {
                  return parameterTypes;
               }

               public boolean isVarargs() {
                  return Mod.isVarargs(methodInfo.getAccessFlags());
               }

               public IClass[] getThrownExceptions2() {
                  return thrownExceptions;
               }
            };
         }

         this.resolvedMethods.put(methodInfo, result);
         return result;
      }
   }

   private IClass.IField resolveField(final ClassFile.FieldInfo fieldInfo) throws ClassNotFoundException {
      IClass.IField result = (IClass.IField)this.resolvedFields.get(fieldInfo);
      if (result != null) {
         return result;
      } else {
         final String name = fieldInfo.getName(this.classFile);
         String descriptor = fieldInfo.getDescriptor(this.classFile);
         final IClass type = this.resolveClass(descriptor);
         ClassFile.ConstantValueAttribute cva = null;

         for(ClassFile.AttributeInfo ai : fieldInfo.getAttributes()) {
            if (ai instanceof ClassFile.ConstantValueAttribute) {
               cva = (ClassFile.ConstantValueAttribute)ai;
               break;
            }
         }

         final Object constantValue = cva == null ? IClass.NOT_CONSTANT : cva.getConstantValue(this.classFile).getValue(this.classFile);
         final Access access = accessFlags2Access(fieldInfo.getAccessFlags());

         final IClass.IAnnotation[] iAnnotations;
         try {
            iAnnotations = this.toIAnnotations(fieldInfo.getAnnotations(true));
         } catch (CompileException ce) {
            throw new InternalCompilerException(ce.getMessage(), ce);
         }

         result = new IClass.IField() {
            public Object getConstantValue() {
               return constantValue;
            }

            public String getName() {
               return name;
            }

            public IClass getType() {
               return type;
            }

            public boolean isStatic() {
               return Mod.isStatic(fieldInfo.getAccessFlags());
            }

            public Access getAccess() {
               return access;
            }

            public IClass.IAnnotation[] getAnnotations() {
               return iAnnotations;
            }
         };
         this.resolvedFields.put(fieldInfo, result);
         return result;
      }
   }

   private static Access accessFlags2Access(short accessFlags) {
      return Mod.isPublicAccess(accessFlags) ? Access.PUBLIC : (Mod.isProtectedAccess(accessFlags) ? Access.PROTECTED : (Mod.isPrivateAccess(accessFlags) ? Access.PRIVATE : Access.DEFAULT));
   }
}
