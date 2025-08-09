package org.codehaus.janino.util.signature;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.util.charstream.StringCharStream;
import org.codehaus.janino.util.charstream.UnexpectedCharacterException;

public class SignatureParser {
   public static final Options DEFAULT_OPTIONS = new Options() {
      public String beautifyPackageNamePrefix(String packageSpecifier) {
         return packageSpecifier;
      }
   };
   private Options options;
   public final ClassTypeSignature object;
   public static final PrimitiveTypeSignature BYTE = new PrimitiveTypeSignature("byte");
   public static final PrimitiveTypeSignature CHAR = new PrimitiveTypeSignature("char");
   public static final PrimitiveTypeSignature DOUBLE = new PrimitiveTypeSignature("double");
   public static final PrimitiveTypeSignature FLOAT = new PrimitiveTypeSignature("float");
   public static final PrimitiveTypeSignature INT = new PrimitiveTypeSignature("int");
   public static final PrimitiveTypeSignature LONG = new PrimitiveTypeSignature("long");
   public static final PrimitiveTypeSignature SHORT = new PrimitiveTypeSignature("short");
   public static final PrimitiveTypeSignature BOOLEAN = new PrimitiveTypeSignature("boolean");
   public static final TypeSignature VOID = new TypeSignature() {
      public String toString() {
         return "void";
      }
   };
   private static final PrimitiveTypeSignature[] PRIMITIVE_TYPES;

   public SignatureParser() {
      this.options = DEFAULT_OPTIONS;
      this.object = new ClassTypeSignature("java/lang/", "Object", Collections.emptyList(), Collections.emptyList(), this.options);
   }

   public SignatureParser(Options options) {
      this.options = DEFAULT_OPTIONS;
      this.object = new ClassTypeSignature("java/lang/", "Object", Collections.emptyList(), Collections.emptyList(), this.options);
      this.options = options;
   }

   public ClassSignature decodeClassSignature(String s) throws SignatureException {
      try {
         StringCharStream scs = new StringCharStream(s);
         ClassSignature cls = this.parseClassSignature(scs);
         scs.eoi();
         return cls;
      } catch (SignatureException e) {
         throw new SignatureException("Class signature '" + s + "': " + e.getMessage(), e);
      } catch (EOFException e) {
         throw new SignatureException("Class signature '" + s + "': " + e.getMessage(), e);
      } catch (UnexpectedCharacterException e) {
         throw new SignatureException("Class signature '" + s + "': " + e.getMessage(), e);
      }
   }

   public MethodTypeSignature decodeMethodTypeSignature(String s) throws SignatureException {
      try {
         StringCharStream scs = new StringCharStream(s);
         MethodTypeSignature mts = this.parseMethodTypeSignature(scs);
         scs.eoi();
         return mts;
      } catch (SignatureException e) {
         throw new SignatureException("Method type signature '" + s + "': " + e.getMessage(), e);
      } catch (EOFException e) {
         throw new SignatureException("Method type signature '" + s + "': " + e.getMessage(), e);
      } catch (UnexpectedCharacterException e) {
         throw new SignatureException("Method type signature '" + s + "': " + e.getMessage(), e);
      }
   }

   public TypeSignature decodeTypeSignature(String s) throws SignatureException {
      try {
         StringCharStream scs = new StringCharStream(s);
         TypeSignature ts = this.parseTypeSignature(scs);
         scs.eoi();
         return ts;
      } catch (SignatureException e) {
         throw new SignatureException("Field type signature '" + s + "': " + e.getMessage(), e);
      } catch (EOFException e) {
         throw new SignatureException("Field type signature '" + s + "': " + e.getMessage(), e);
      } catch (UnexpectedCharacterException e) {
         throw new SignatureException("Field type signature '" + s + "': " + e.getMessage(), e);
      }
   }

   public FieldTypeSignature decodeFieldTypeSignature(String s) throws SignatureException {
      try {
         StringCharStream scs = new StringCharStream(s);
         FieldTypeSignature fts = this.parseFieldTypeSignature(scs);
         scs.eoi();
         return fts;
      } catch (SignatureException e) {
         throw new SignatureException("Field type signature '" + s + "': " + e.getMessage(), e);
      } catch (EOFException e) {
         throw new SignatureException("Field type signature '" + s + "': " + e.getMessage(), e);
      } catch (UnexpectedCharacterException e) {
         throw new SignatureException("Field type signature '" + s + "': " + e.getMessage(), e);
      }
   }

   public MethodTypeSignature decodeMethodDescriptor(String s) throws SignatureException {
      try {
         StringCharStream scs = new StringCharStream(s);
         MethodTypeSignature mts = this.parseMethodDescriptor(scs);
         scs.eoi();
         return mts;
      } catch (SignatureException e) {
         throw new SignatureException("Method descriptor '" + s + "': " + e.getMessage(), e);
      } catch (EOFException e) {
         throw new SignatureException("Method descriptor '" + s + "': " + e.getMessage(), e);
      } catch (UnexpectedCharacterException e) {
         throw new SignatureException("Method descriptor '" + s + "': " + e.getMessage(), e);
      }
   }

   private TypeSignature decodeClassName(String internalName) {
      String className = internalName.replace('/', '.');
      int idx = className.lastIndexOf(46) + 1;
      final String packageNamePrefix = className.substring(0, idx);
      final String simpleClassName = className.substring(idx);
      return new TypeSignature() {
         public String toString() {
            return SignatureParser.this.options.beautifyPackageNamePrefix(packageNamePrefix) + simpleClassName;
         }
      };
   }

   public TypeSignature decodeFieldDescriptor(String s) throws SignatureException {
      try {
         StringCharStream scs = new StringCharStream(s);
         TypeSignature ts = this.parseFieldDescriptor(scs);
         scs.eoi();
         return ts;
      } catch (SignatureException e) {
         throw new SignatureException("Field descriptor '" + s + "': " + e.getMessage(), e);
      } catch (EOFException e) {
         throw new SignatureException("Field descriptor '" + s + "': " + e.getMessage(), e);
      } catch (UnexpectedCharacterException e) {
         throw new SignatureException("Field descriptor '" + s + "': " + e.getMessage(), e);
      }
   }

   public TypeSignature decodeClassNameOrFieldDescriptor(String s) throws SignatureException {
      return Character.isJavaIdentifierStart(s.charAt(0)) ? this.decodeClassName(s) : this.decodeFieldDescriptor(s);
   }

   public TypeSignature decodeReturnType(String s) throws SignatureException {
      try {
         StringCharStream scs = new StringCharStream(s);
         TypeSignature ts = this.parseReturnType(scs);
         scs.eoi();
         return ts;
      } catch (SignatureException e) {
         throw new SignatureException("Return type '" + s + "': " + e.getMessage(), e);
      } catch (EOFException e) {
         throw new SignatureException("Return type '" + s + "': " + e.getMessage(), e);
      } catch (UnexpectedCharacterException e) {
         throw new SignatureException("Return type '" + s + "': " + e.getMessage(), e);
      }
   }

   private TypeSignature parseFieldDescriptor(StringCharStream scs) throws EOFException, UnexpectedCharacterException, SignatureException {
      return this.parseTypeSignature(scs);
   }

   private MethodTypeSignature parseMethodDescriptor(StringCharStream scs) throws EOFException, UnexpectedCharacterException, SignatureException {
      return this.parseMethodTypeSignature(scs);
   }

   private ClassSignature parseClassSignature(StringCharStream scs) throws EOFException, SignatureException, UnexpectedCharacterException {
      List<FormalTypeParameter> ftps = new ArrayList();
      if (scs.peekRead('<')) {
         while(!scs.peekRead('>')) {
            ftps.add(this.parseFormalTypeParameter(scs));
         }
      }

      ClassTypeSignature cts = this.parseClassTypeSignature(scs);
      List<ClassTypeSignature> siss = new ArrayList();

      while(!scs.atEoi()) {
         siss.add(this.parseClassTypeSignature(scs));
      }

      return new ClassSignature(ftps, cts, siss);
   }

   private MethodTypeSignature parseMethodTypeSignature(StringCharStream scs) throws EOFException, UnexpectedCharacterException, SignatureException {
      List<FormalTypeParameter> ftps = new ArrayList();
      if (scs.peekRead('<')) {
         while(!scs.peekRead('>')) {
            ftps.add(this.parseFormalTypeParameter(scs));
         }
      }

      scs.read('(');
      List<TypeSignature> pts = new ArrayList();

      while(!scs.peekRead(')')) {
         pts.add(this.parseTypeSignature(scs));
      }

      TypeSignature rt = this.parseReturnType(scs);
      List<ThrowsSignature> tts = new ArrayList();

      while(!scs.atEoi()) {
         tts.add(this.parseThrowsSignature(scs));
      }

      return new MethodTypeSignature(ftps, pts, rt, tts);
   }

   private TypeSignature parseReturnType(StringCharStream scs) throws EOFException, UnexpectedCharacterException, SignatureException {
      return scs.peekRead('V') ? VOID : this.parseTypeSignature(scs);
   }

   private ThrowsSignature parseThrowsSignature(StringCharStream scs) throws EOFException, UnexpectedCharacterException, SignatureException {
      scs.read('^');
      return (ThrowsSignature)(scs.peek('T') ? parseTypeVariableSignature(scs) : this.parseClassTypeSignature(scs));
   }

   private ClassTypeSignature parseClassTypeSignature(StringCharStream scs) throws EOFException, UnexpectedCharacterException, SignatureException {
      scs.read('L');
      String ps = "";
      List<TypeArgument> tas = new ArrayList();

      String scn;
      label39:
      while(true) {
         String s = parseIdentifier(scs);
         switch (scs.peek("<./;")) {
            case 0:
               scs.read('<');
               scn = s;

               while(true) {
                  if (scs.peekRead('>')) {
                     break label39;
                  }

                  tas.add(this.parseTypeArgument(scs));
               }
            case 1:
               scn = s;
               break label39;
            case 2:
               ps = ps + s + '/';
               scs.read();
               break;
            case 3:
               scn = s;
               break label39;
            default:
               scs.read("<./;");
         }
      }

      String ir;
      List<TypeArgument> ta;
      List<SimpleClassTypeSignature> ss;
      for(ss = new ArrayList(); scs.peekRead('.'); ss.add(new SimpleClassTypeSignature(ir, ta))) {
         ir = parseIdentifierRest(scs);
         ta = new ArrayList();
         if (scs.peekRead('<')) {
            while(!scs.peekRead('>')) {
               ta.add(this.parseTypeArgument(scs));
            }
         }
      }

      scs.read(';');
      return new ClassTypeSignature(ps, scn, tas, ss, this.options);
   }

   private static String parseIdentifier(StringCharStream scs) throws EOFException, SignatureException {
      char c = scs.read();
      if (!Character.isJavaIdentifierStart(c)) {
         throw new SignatureException("Identifier expected instead of '" + c + "'");
      } else {
         StringBuilder sb = (new StringBuilder()).append(c);

         while(Character.isJavaIdentifierPart(scs.peek())) {
            sb.append(scs.read());
         }

         return sb.toString();
      }
   }

   private static String parseIdentifierRest(StringCharStream scs) throws EOFException, SignatureException {
      char c = scs.read();
      if (!Character.isJavaIdentifierPart(c)) {
         throw new SignatureException("Identifier rest expected instead of '" + c + "'");
      } else {
         StringBuilder sb = (new StringBuilder()).append(c);

         while(Character.isJavaIdentifierPart(scs.peek())) {
            sb.append(scs.read());
         }

         return sb.toString();
      }
   }

   private static TypeVariableSignature parseTypeVariableSignature(StringCharStream scs) throws EOFException, UnexpectedCharacterException, SignatureException {
      scs.read('T');
      String identifier = parseIdentifier(scs);
      scs.read(';');
      return new TypeVariableSignature(identifier);
   }

   private FormalTypeParameter parseFormalTypeParameter(StringCharStream scs) throws EOFException, SignatureException, UnexpectedCharacterException {
      String identifier = parseIdentifier(scs);
      scs.read(':');
      FieldTypeSignature cb = !scs.peek(':') ? this.parseFieldTypeSignature(scs) : null;
      List<FieldTypeSignature> ibs = new ArrayList();

      while(scs.peekRead(':')) {
         ibs.add(this.parseFieldTypeSignature(scs));
      }

      return new FormalTypeParameter(identifier, cb, ibs);
   }

   private TypeSignature parseTypeSignature(StringCharStream scs) throws EOFException, UnexpectedCharacterException, SignatureException {
      int idx = scs.peekRead("BCDFIJSZ");
      return (TypeSignature)(idx != -1 ? PRIMITIVE_TYPES[idx] : this.parseFieldTypeSignature(scs));
   }

   private FieldTypeSignature parseFieldTypeSignature(StringCharStream scs) throws EOFException, UnexpectedCharacterException, SignatureException {
      switch (scs.peek("L[T")) {
         case 0:
            return this.parseClassTypeSignature(scs);
         case 1:
            return this.parseArrayTypeSignature(scs);
         case 2:
            return parseTypeVariableSignature(scs);
         default:
            throw new SignatureException("Parsing field type signature \"" + scs + "\": Class type signature, array type signature or type variable signature expected");
      }
   }

   private FieldTypeSignature parseArrayTypeSignature(StringCharStream scs) throws EOFException, UnexpectedCharacterException, SignatureException {
      scs.read('[');
      return new ArrayTypeSignature(this.parseTypeSignature(scs));
   }

   private TypeArgument parseTypeArgument(StringCharStream scs) throws EOFException, UnexpectedCharacterException, SignatureException {
      if (scs.peekRead('+')) {
         return new TypeArgument(SignatureParser.TypeArgument.Mode.EXTENDS, this.parseFieldTypeSignature(scs));
      } else if (scs.peekRead('-')) {
         return new TypeArgument(SignatureParser.TypeArgument.Mode.SUPER, this.parseFieldTypeSignature(scs));
      } else {
         return scs.peekRead('*') ? new TypeArgument(SignatureParser.TypeArgument.Mode.ANY, (FieldTypeSignature)null) : new TypeArgument(SignatureParser.TypeArgument.Mode.NONE, this.parseFieldTypeSignature(scs));
      }
   }

   static {
      PRIMITIVE_TYPES = new PrimitiveTypeSignature[]{BYTE, CHAR, DOUBLE, FLOAT, INT, LONG, SHORT, BOOLEAN};
   }

   public static class MethodTypeSignature {
      public final List formalTypeParameters;
      public final List parameterTypes;
      public final TypeSignature returnType;
      public final List thrownTypes;

      public MethodTypeSignature(List formalTypeParameters, List parameterTypes, TypeSignature returnType, List thrownTypes) {
         this.formalTypeParameters = formalTypeParameters;
         this.parameterTypes = parameterTypes;
         this.returnType = returnType;
         this.thrownTypes = thrownTypes;
      }

      public String toString(String declaringClassName, String methodName) {
         StringBuilder sb = new StringBuilder();
         if (!this.formalTypeParameters.isEmpty()) {
            Iterator<FormalTypeParameter> it = this.formalTypeParameters.iterator();
            sb.append('<' + ((FormalTypeParameter)it.next()).toString());

            while(it.hasNext()) {
               sb.append(", " + ((FormalTypeParameter)it.next()).toString());
            }

            sb.append("> ");
         }

         if ("<init>".equals(methodName) && this.returnType == SignatureParser.VOID) {
            sb.append(declaringClassName);
         } else {
            sb.append(declaringClassName).append('.').append(methodName);
         }

         sb.append('(');
         Iterator<TypeSignature> it = this.parameterTypes.iterator();
         if (it.hasNext()) {
            while(true) {
               sb.append(((TypeSignature)it.next()).toString());
               if (!it.hasNext()) {
                  break;
               }

               sb.append(", ");
            }
         }

         sb.append(')');
         if (!this.thrownTypes.isEmpty()) {
            Iterator<ThrowsSignature> it2 = this.thrownTypes.iterator();
            sb.append(" throws ").append(it2.next());

            while(it.hasNext()) {
               sb.append(", ").append(it2.next());
            }
         }

         if (this.returnType != SignatureParser.VOID) {
            sb.append(" => ").append(this.returnType.toString());
         }

         return sb.toString();
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         if (!this.formalTypeParameters.isEmpty()) {
            Iterator<FormalTypeParameter> it = this.formalTypeParameters.iterator();
            sb.append('<' + ((FormalTypeParameter)it.next()).toString());

            while(it.hasNext()) {
               sb.append(", " + ((FormalTypeParameter)it.next()).toString());
            }

            sb.append("> ");
         }

         sb.append('(');
         Iterator<TypeSignature> it = this.parameterTypes.iterator();
         if (it.hasNext()) {
            sb.append(it.next());

            while(it.hasNext()) {
               sb.append(", ").append(it.next());
            }
         }

         sb.append(')');
         if (this.returnType != SignatureParser.VOID) {
            sb.append(" => ").append(this.returnType.toString());
         }

         return sb.toString();
      }
   }

   public static class ClassSignature {
      public final List formalTypeParameters;
      public ClassTypeSignature superclassSignature;
      public final List superinterfaceSignatures;

      public ClassSignature(List formalTypeParameters, ClassTypeSignature superclassSignature, List superinterfaceSignatures) {
         this.formalTypeParameters = formalTypeParameters;
         this.superclassSignature = superclassSignature;
         this.superinterfaceSignatures = superinterfaceSignatures;
      }

      public String toString(String className) {
         StringBuilder sb = new StringBuilder(className);
         if (!this.formalTypeParameters.isEmpty()) {
            Iterator<FormalTypeParameter> it = this.formalTypeParameters.iterator();
            sb.append('<').append(((FormalTypeParameter)it.next()).toString());

            while(it.hasNext()) {
               sb.append(", ").append(((FormalTypeParameter)it.next()).toString());
            }

            sb.append('>');
         }

         sb.append(" extends ").append(this.superclassSignature.toString());
         if (!this.superinterfaceSignatures.isEmpty()) {
            Iterator<ClassTypeSignature> it = this.superinterfaceSignatures.iterator();
            sb.append(" implements ").append(((ClassTypeSignature)it.next()).toString());

            while(it.hasNext()) {
               sb.append(", ").append(((ClassTypeSignature)it.next()).toString());
            }
         }

         return sb.toString();
      }
   }

   public static class ClassTypeSignature implements ThrowsSignature, FieldTypeSignature {
      public final String packageSpecifier;
      public final String simpleClassName;
      public final List typeArguments;
      public final List suffixes;
      private final Options options;

      public ClassTypeSignature(String packageSpecifier, String simpleClassName, List typeArguments, List suffixes, Options options) {
         this.packageSpecifier = packageSpecifier;
         this.simpleClassName = simpleClassName;
         this.typeArguments = typeArguments;
         this.suffixes = suffixes;
         this.options = options;
      }

      public Object accept(FieldTypeSignatureVisitor visitor) throws Throwable {
         return visitor.visitClassTypeSignature(this);
      }

      public String toString() {
         String packageNamePrefix = this.packageSpecifier.replace('/', '.');
         StringBuilder sb = (new StringBuilder()).append(this.options.beautifyPackageNamePrefix(packageNamePrefix)).append(this.simpleClassName);
         if (!this.typeArguments.isEmpty()) {
            Iterator<TypeArgument> it = this.typeArguments.iterator();
            sb.append('<').append(((TypeArgument)it.next()).toString());

            while(it.hasNext()) {
               sb.append(", ").append(((TypeArgument)it.next()).toString());
            }

            sb.append('>');
         }

         for(SimpleClassTypeSignature suffix : this.suffixes) {
            sb.append('.').append(suffix.toString());
         }

         return sb.toString();
      }
   }

   public static class SimpleClassTypeSignature {
      public final String simpleClassName;
      public final List typeArguments;

      public SimpleClassTypeSignature(String simpleClassName, List typeArguments) {
         this.simpleClassName = simpleClassName;
         this.typeArguments = typeArguments;
      }

      public String toString() {
         StringBuilder sb = new StringBuilder(this.simpleClassName);
         if (!this.typeArguments.isEmpty()) {
            Iterator<TypeArgument> it = this.typeArguments.iterator();
            sb.append('<').append(((TypeArgument)it.next()).toString());

            while(it.hasNext()) {
               sb.append(", ").append(((TypeArgument)it.next()).toString());
            }

            sb.append('>');
         }

         return sb.toString();
      }
   }

   public static class ArrayTypeSignature implements FieldTypeSignature {
      public final TypeSignature componentTypeSignature;

      public ArrayTypeSignature(TypeSignature componentTypeSignature) {
         this.componentTypeSignature = componentTypeSignature;
      }

      public Object accept(FieldTypeSignatureVisitor visitor) throws Throwable {
         return visitor.visitArrayTypeSignature(this);
      }

      public String toString() {
         return this.componentTypeSignature.toString() + "[]";
      }
   }

   public static class TypeVariableSignature implements ThrowsSignature, FieldTypeSignature {
      public String identifier;

      public TypeVariableSignature(String identifier) {
         this.identifier = identifier;
      }

      public Object accept(FieldTypeSignatureVisitor visitor) throws Throwable {
         return visitor.visitTypeVariableSignature(this);
      }

      public String toString() {
         return this.identifier;
      }
   }

   public static class FormalTypeParameter {
      public final String identifier;
      @Nullable
      public final FieldTypeSignature classBound;
      public final List interfaceBounds;

      public FormalTypeParameter(String identifier, @Nullable FieldTypeSignature classBound, List interfaceBounds) {
         this.identifier = identifier;
         this.classBound = classBound;
         this.interfaceBounds = interfaceBounds;
      }

      public String toString() {
         FieldTypeSignature cb = this.classBound;
         if (cb == null) {
            Iterator<FieldTypeSignature> it = this.interfaceBounds.iterator();
            if (!it.hasNext()) {
               return this.identifier;
            } else {
               StringBuilder sb = (new StringBuilder(this.identifier)).append(" extends ").append(((FieldTypeSignature)it.next()).toString());

               while(it.hasNext()) {
                  sb.append(" & ").append(((FieldTypeSignature)it.next()).toString());
               }

               return sb.toString();
            }
         } else {
            StringBuilder sb = (new StringBuilder(this.identifier)).append(" extends ").append(cb.toString());

            for(FieldTypeSignature ib : this.interfaceBounds) {
               sb.append(" & ").append(ib.toString());
            }

            return sb.toString();
         }
      }
   }

   public static class PrimitiveTypeSignature implements TypeSignature {
      public final String typeName;

      PrimitiveTypeSignature(String typeName) {
         this.typeName = typeName;
      }

      public String toString() {
         return this.typeName;
      }
   }

   public static class TypeArgument {
      public final Mode mode;
      @Nullable
      public final FieldTypeSignature fieldTypeSignature;

      public TypeArgument(Mode mode, @Nullable FieldTypeSignature fieldTypeSignature) {
         assert mode == SignatureParser.TypeArgument.Mode.ANY ^ fieldTypeSignature != null;

         this.mode = mode;
         this.fieldTypeSignature = fieldTypeSignature;
      }

      public String toString() {
         FieldTypeSignature fts = this.fieldTypeSignature;
         switch (this.mode) {
            case EXTENDS:
               assert fts != null;

               return "extends " + fts.toString();
            case SUPER:
               assert fts != null;

               return "super " + fts.toString();
            case ANY:
               return "*";
            case NONE:
               assert fts != null;

               return fts.toString();
            default:
               throw new IllegalStateException();
         }
      }

      static enum Mode {
         EXTENDS,
         SUPER,
         ANY,
         NONE;

         // $FF: synthetic method
         private static Mode[] $values() {
            return new Mode[]{EXTENDS, SUPER, ANY, NONE};
         }
      }
   }

   public static class SignatureException extends Exception {
      private static final long serialVersionUID = 1L;

      public SignatureException(String message) {
         super(message);
      }

      public SignatureException(String message, Throwable t) {
         super(message, t);
      }
   }

   public interface FieldTypeSignature extends TypeSignature {
      Object accept(FieldTypeSignatureVisitor var1) throws Throwable;

      String toString();
   }

   public interface FieldTypeSignatureVisitor {
      Object visitArrayTypeSignature(ArrayTypeSignature var1) throws Throwable;

      Object visitClassTypeSignature(ClassTypeSignature var1) throws Throwable;

      Object visitTypeVariableSignature(TypeVariableSignature var1) throws Throwable;
   }

   public interface Options {
      String beautifyPackageNamePrefix(String var1);
   }

   public interface ThrowsSignature {
   }

   public interface TypeSignature {
      String toString();
   }
}
