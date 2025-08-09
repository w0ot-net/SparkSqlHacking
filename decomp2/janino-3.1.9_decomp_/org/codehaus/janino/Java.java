package org.codehaus.janino;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.util.iterator.ReverseListIterator;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.util.AbstractTraverser;

public final class Java {
   private Java() {
   }

   public static String join(Object[] a, String separator) {
      return join(a, separator, 0, a.length);
   }

   public static String join(Object[][] aa, String innerSeparator, String outerSeparator) {
      String[] tmp = new String[aa.length];

      for(int i = 0; i < aa.length; ++i) {
         tmp[i] = join(aa[i], innerSeparator);
      }

      return join(tmp, outerSeparator);
   }

   public static String join(Object[] a, String separator, int off, int len) {
      if (off >= len) {
         return "";
      } else {
         StringBuilder sb = new StringBuilder(a[off].toString());
         ++off;

         while(off < len) {
            sb.append(separator);
            sb.append(a[off]);
            ++off;
         }

         return sb.toString();
      }
   }

   public static AccessModifier[] accessModifiers(Location location, String... keywords) {
      AccessModifier[] result = new AccessModifier[keywords.length];

      for(int i = 0; i < keywords.length; ++i) {
         result[i] = new AccessModifier(keywords[i], location);
      }

      return result;
   }

   private static boolean hasAccessModifier(Modifier[] modifiers, String... keywords) {
      for(String kw : keywords) {
         for(Modifier m : modifiers) {
            if (m instanceof AccessModifier && kw.equals(((AccessModifier)m).keyword)) {
               return true;
            }
         }
      }

      return false;
   }

   private static Annotation[] getAnnotations(Modifier[] modifiers) {
      int n = 0;

      for(Modifier m : modifiers) {
         if (m instanceof Annotation) {
            ++n;
         }
      }

      Annotation[] result = new Annotation[n];
      n = 0;

      for(Modifier m : modifiers) {
         if (m instanceof Annotation) {
            result[n++] = (Annotation)m;
         }
      }

      return result;
   }

   private static Access modifiers2Access(Modifier[] modifiers) {
      if (hasAccessModifier(modifiers, "private")) {
         return Access.PRIVATE;
      } else if (hasAccessModifier(modifiers, "protected")) {
         return Access.PROTECTED;
      } else {
         return hasAccessModifier(modifiers, "public") ? Access.PUBLIC : Access.DEFAULT;
      }
   }

   private static String toString(Modifier[] modifiers) {
      StringBuilder sb = new StringBuilder();

      for(Modifier m : modifiers) {
         sb.append(m).append(' ');
      }

      return sb.toString();
   }

   public abstract static class Located implements Locatable {
      public static final Located NOWHERE;
      private final Location location;

      protected Located(Location location) {
         this.location = location;
      }

      public Location getLocation() {
         return this.location;
      }

      public void throwCompileException(String message) throws CompileException {
         throw new CompileException(message, this.location);
      }

      static {
         NOWHERE = new Located(Location.NOWHERE) {
         };
      }
   }

   public abstract static class AbstractCompilationUnit implements Scope {
      @Nullable
      public final String fileName;
      public final ImportDeclaration[] importDeclarations;

      public AbstractCompilationUnit(@Nullable String fileName, ImportDeclaration[] importDeclarations) {
         this.fileName = fileName;
         this.importDeclarations = importDeclarations;
      }

      public Scope getEnclosingScope() {
         throw new InternalCompilerException("A compilation unit has no enclosing scope");
      }

      @Nullable
      public abstract Object accept(Visitor.AbstractCompilationUnitVisitor var1) throws Throwable;

      public static class SingleTypeImportDeclaration extends ImportDeclaration {
         public final String[] identifiers;

         public SingleTypeImportDeclaration(Location location, String[] identifiers) {
            super(location);
            this.identifiers = identifiers;
         }

         @Nullable
         public final Object accept(Visitor.ImportVisitor visitor) throws Throwable {
            return visitor.visitSingleTypeImportDeclaration(this);
         }

         public String toString() {
            return "import " + Java.join(this.identifiers, ".") + ';';
         }
      }

      public static class TypeImportOnDemandDeclaration extends ImportDeclaration {
         public final String[] identifiers;

         public TypeImportOnDemandDeclaration(Location location, String[] identifiers) {
            super(location);
            this.identifiers = identifiers;
         }

         @Nullable
         public final Object accept(Visitor.ImportVisitor visitor) throws Throwable {
            return visitor.visitTypeImportOnDemandDeclaration(this);
         }

         public String toString() {
            return "import " + Java.join(this.identifiers, ".") + ".*;";
         }
      }

      public static class SingleStaticImportDeclaration extends ImportDeclaration {
         public final String[] identifiers;

         public SingleStaticImportDeclaration(Location location, String[] identifiers) {
            super(location);
            this.identifiers = identifiers;
         }

         @Nullable
         public final Object accept(Visitor.ImportVisitor visitor) throws Throwable {
            return visitor.visitSingleStaticImportDeclaration(this);
         }

         public String toString() {
            return "import static " + Java.join(this.identifiers, ".") + ";";
         }
      }

      public static class StaticImportOnDemandDeclaration extends ImportDeclaration {
         public final String[] identifiers;

         public StaticImportOnDemandDeclaration(Location location, String[] identifiers) {
            super(location);
            this.identifiers = identifiers;
         }

         @Nullable
         public final Object accept(Visitor.ImportVisitor visitor) throws Throwable {
            return visitor.visitStaticImportOnDemandDeclaration(this);
         }

         public String toString() {
            return "import static " + Java.join(this.identifiers, ".") + ".*;";
         }
      }

      public abstract static class ImportDeclaration extends Located {
         public ImportDeclaration(Location location) {
            super(location);
         }

         @Nullable
         public abstract Object accept(Visitor.ImportVisitor var1) throws Throwable;
      }
   }

   public static final class CompilationUnit extends AbstractCompilationUnit {
      @Nullable
      public PackageDeclaration packageDeclaration;
      public final List packageMemberTypeDeclarations;

      public CompilationUnit(@Nullable String fileName) {
         this(fileName, new AbstractCompilationUnit.ImportDeclaration[0]);
      }

      public CompilationUnit(@Nullable String fileName, AbstractCompilationUnit.ImportDeclaration[] importDeclarations) {
         super(fileName, importDeclarations);
         this.packageMemberTypeDeclarations = new ArrayList();
      }

      public void setPackageDeclaration(@Nullable PackageDeclaration packageDeclaration) {
         this.packageDeclaration = packageDeclaration;
      }

      public void addPackageMemberTypeDeclaration(PackageMemberTypeDeclaration pmtd) {
         this.packageMemberTypeDeclarations.add(pmtd);
         pmtd.setDeclaringCompilationUnit(this);
      }

      public PackageMemberTypeDeclaration[] getPackageMemberTypeDeclarations() {
         return (PackageMemberTypeDeclaration[])this.packageMemberTypeDeclarations.toArray(new PackageMemberTypeDeclaration[this.packageMemberTypeDeclarations.size()]);
      }

      @Nullable
      public PackageMemberTypeDeclaration getPackageMemberTypeDeclaration(String name) {
         for(PackageMemberTypeDeclaration pmtd : this.packageMemberTypeDeclarations) {
            if (pmtd.getName().equals(name)) {
               return pmtd;
            }
         }

         return null;
      }

      @Nullable
      public Object accept(Visitor.AbstractCompilationUnitVisitor visitor) throws Throwable {
         return visitor.visitCompilationUnit(this);
      }
   }

   public static final class ModularCompilationUnit extends AbstractCompilationUnit {
      public final ModuleDeclaration moduleDeclaration;

      public ModularCompilationUnit(@Nullable String fileName, AbstractCompilationUnit.ImportDeclaration[] importDeclarations, ModuleDeclaration moduleDeclaration) {
         super(fileName, importDeclarations);
         this.moduleDeclaration = moduleDeclaration;
      }

      @Nullable
      public Object accept(Visitor.AbstractCompilationUnitVisitor visitor) throws Throwable {
         return visitor.visitModularCompilationUnit(this);
      }
   }

   public static final class ModuleDeclaration extends Located {
      public final Modifier[] modifiers;
      public final boolean isOpen;
      public final String[] moduleName;
      public final ModuleDirective[] moduleDirectives;

      public ModuleDeclaration(Location location, Modifier[] modifiers, boolean isOpen, String[] moduleName, ModuleDirective[] moduleDirectives) {
         super(location);
         this.modifiers = modifiers;
         this.isOpen = isOpen;
         this.moduleName = moduleName;
         this.moduleDirectives = moduleDirectives;
      }
   }

   public static final class RequiresModuleDirective extends Located implements ModuleDirective {
      public final Modifier[] requiresModifiers;
      public final String[] moduleName;

      protected RequiresModuleDirective(Location location, Modifier[] requiresModifiers, String[] moduleName) {
         super(location);
         this.requiresModifiers = requiresModifiers;
         this.moduleName = moduleName;
      }

      @Nullable
      public Object accept(Visitor.ModuleDirectiveVisitor visitor) throws Throwable {
         return visitor.visitRequiresModuleDirective(this);
      }
   }

   public static final class ExportsModuleDirective extends Located implements ModuleDirective {
      public final String[] packageName;
      @Nullable
      public final String[][] toModuleNames;

      protected ExportsModuleDirective(Location location, String[] packageName, @Nullable String[][] toModuleNames) {
         super(location);
         this.packageName = packageName;
         this.toModuleNames = toModuleNames;
      }

      @Nullable
      public Object accept(Visitor.ModuleDirectiveVisitor visitor) throws Throwable {
         return visitor.visitExportsModuleDirective(this);
      }
   }

   public static final class OpensModuleDirective extends Located implements ModuleDirective {
      public final String[] packageName;
      @Nullable
      public final String[][] toModuleNames;

      protected OpensModuleDirective(Location location, String[] packageName, @Nullable String[][] toModuleNames) {
         super(location);
         this.packageName = packageName;
         this.toModuleNames = toModuleNames;
      }

      @Nullable
      public Object accept(Visitor.ModuleDirectiveVisitor visitor) throws Throwable {
         return visitor.visitOpensModuleDirective(this);
      }
   }

   public static final class UsesModuleDirective extends Located implements ModuleDirective {
      public final String[] typeName;

      protected UsesModuleDirective(Location location, String[] typeName) {
         super(location);
         this.typeName = typeName;
      }

      @Nullable
      public Object accept(Visitor.ModuleDirectiveVisitor visitor) throws Throwable {
         return visitor.visitUsesModuleDirective(this);
      }
   }

   public static final class ProvidesModuleDirective extends Located implements ModuleDirective {
      public final String[] typeName;
      public final String[][] withTypeNames;

      protected ProvidesModuleDirective(Location location, String[] typeName, String[][] withTypeNames) {
         super(location);
         this.typeName = typeName;
         this.withTypeNames = withTypeNames;
      }

      @Nullable
      public Object accept(Visitor.ModuleDirectiveVisitor visitor) throws Throwable {
         return visitor.visitProvidesModuleDirective(this);
      }
   }

   public abstract static class AbstractAnnotation implements Annotation {
      public final Type type;

      public AbstractAnnotation(Type type) {
         this.type = type;
      }

      public void setEnclosingScope(Scope enclosingScope) {
         this.type.setEnclosingScope(enclosingScope);
      }

      public Location getLocation() {
         return this.type.getLocation();
      }

      @Nullable
      public final Object accept(Visitor.ElementValueVisitor visitor) throws Throwable {
         return visitor.visitAnnotation(this);
      }

      @Nullable
      public final Object accept(Visitor.ModifierVisitor visitor) throws Throwable {
         return this.accept((Visitor.AnnotationVisitor)visitor);
      }

      public void throwCompileException(String message) throws CompileException {
         throw new CompileException(message, this.getLocation());
      }
   }

   public static final class MarkerAnnotation extends AbstractAnnotation {
      public MarkerAnnotation(Type type) {
         super(type);
      }

      public String toString() {
         return "@" + this.type;
      }

      public Type getType() {
         return this.type;
      }

      @Nullable
      public Object accept(Visitor.AnnotationVisitor visitor) throws Throwable {
         return visitor.visitMarkerAnnotation(this);
      }
   }

   public static final class SingleElementAnnotation extends AbstractAnnotation {
      public final ElementValue elementValue;

      public SingleElementAnnotation(ReferenceType type, ElementValue elementValue) {
         super(type);
         this.elementValue = elementValue;
      }

      public void setEnclosingScope(Scope enclosingScope) {
         super.setEnclosingScope(enclosingScope);
         this.elementValue.setEnclosingScope(enclosingScope);
      }

      public String toString() {
         return "@" + this.type + '(' + this.elementValue + ')';
      }

      public Type getType() {
         return this.type;
      }

      @Nullable
      public Object accept(Visitor.AnnotationVisitor visitor) throws Throwable {
         return visitor.visitSingleElementAnnotation(this);
      }
   }

   public static final class NormalAnnotation extends AbstractAnnotation {
      public final ElementValuePair[] elementValuePairs;

      public NormalAnnotation(ReferenceType type, ElementValuePair[] elementValuePairs) {
         super(type);
         this.elementValuePairs = elementValuePairs;
      }

      public void setEnclosingScope(Scope enclosingScope) {
         super.setEnclosingScope(enclosingScope);

         for(ElementValuePair elementValuePair : this.elementValuePairs) {
            elementValuePair.elementValue.setEnclosingScope(enclosingScope);
         }

      }

      public Type getType() {
         return this.type;
      }

      public String toString() {
         switch (this.elementValuePairs.length) {
            case 0:
               return "@" + this.type + "()";
            case 1:
               return "@" + this.type + "(" + this.elementValuePairs[0] + ")";
            default:
               return "@" + this.type + "(" + this.elementValuePairs[0] + ", ...)";
         }
      }

      @Nullable
      public Object accept(Visitor.AnnotationVisitor visitor) throws Throwable {
         return visitor.visitNormalAnnotation(this);
      }
   }

   /** @deprecated */
   @Deprecated
   public static class Modifiers {
   }

   public static class AccessModifier extends Located implements Modifier {
      public final String keyword;

      public AccessModifier(String keyword, Location location) {
         super(location);
         this.keyword = keyword;
      }

      @Nullable
      public Object accept(Visitor.ModifierVisitor visitor) throws Throwable {
         return visitor.visitAccessModifier(this);
      }

      public String toString() {
         return this.keyword;
      }
   }

   public static class ElementValuePair {
      public final String identifier;
      public final ElementValue elementValue;

      public ElementValuePair(String identifier, ElementValue elementValue) {
         this.identifier = identifier;
         this.elementValue = elementValue;
      }

      public String toString() {
         return this.identifier + " = " + this.elementValue;
      }
   }

   public static final class ElementValueArrayInitializer extends Located implements ElementValue {
      public final ElementValue[] elementValues;

      public ElementValueArrayInitializer(ElementValue[] elementValues, Location location) {
         super(location);
         this.elementValues = elementValues;
      }

      public void setEnclosingScope(Scope scope) {
         for(ElementValue elementValue : this.elementValues) {
            elementValue.setEnclosingScope(scope);
         }

      }

      public String toString() {
         switch (this.elementValues.length) {
            case 0:
               return "{}";
            case 1:
               return "{ " + this.elementValues[0] + " }";
            default:
               return "{ " + this.elementValues[0] + ", ... }";
         }
      }

      @Nullable
      public Object accept(Visitor.ElementValueVisitor visitor) throws Throwable {
         return visitor.visitElementValueArrayInitializer(this);
      }
   }

   public static class PackageDeclaration extends Located {
      public final String packageName;

      public PackageDeclaration(Location location, String packageName) {
         super(location);
         this.packageName = packageName;
      }
   }

   public abstract static class AbstractTypeDeclaration implements TypeDeclaration {
      private final Location location;
      private final Modifier[] modifiers;
      @Nullable
      private final TypeParameter[] typeParameters;
      private final List declaredMethods = new ArrayList();
      private final List declaredClassesAndInterfaces = new ArrayList();
      @Nullable
      private Scope enclosingScope;
      @Nullable
      IClass resolvedType;
      public int anonymousClassCount;
      public int localClassCount;

      public AbstractTypeDeclaration(Location location, Modifier[] modifiers, @Nullable TypeParameter[] typeParameters) {
         this.location = location;
         this.modifiers = modifiers;
         this.typeParameters = typeParameters;
      }

      public void setEnclosingScope(Scope enclosingScope) {
         if (this.enclosingScope != null && enclosingScope != this.enclosingScope) {
            throw new InternalCompilerException("Enclosing scope is already set for type declaration \"" + this.toString() + "\" at " + this.getLocation());
         } else {
            this.enclosingScope = enclosingScope;

            for(Modifier m : this.modifiers) {
               if (m instanceof Annotation) {
                  ((Annotation)m).setEnclosingScope(enclosingScope);
               }
            }

            if (this.typeParameters != null) {
               for(TypeParameter tp : this.typeParameters) {
                  if (tp.bound != null) {
                     for(ReferenceType boundType : tp.bound) {
                        boundType.setEnclosingScope(enclosingScope);
                     }
                  }
               }
            }

         }
      }

      public Modifier[] getModifiers() {
         return this.modifiers;
      }

      public Annotation[] getAnnotations() {
         return Java.getAnnotations(this.modifiers);
      }

      @Nullable
      public TypeParameter[] getOptionalTypeParameters() {
         return this.typeParameters;
      }

      public Scope getEnclosingScope() {
         assert this.enclosingScope != null;

         return this.enclosingScope;
      }

      public void invalidateMethodCaches() {
         if (this.resolvedType != null) {
            this.resolvedType.invalidateMethodCaches();
         }

      }

      public void addMemberTypeDeclaration(MemberTypeDeclaration mcoid) {
         this.declaredClassesAndInterfaces.add(mcoid);
         mcoid.setDeclaringType(this);
      }

      public void addDeclaredMethod(MethodDeclarator method) {
         this.declaredMethods.add(method);
         method.setDeclaringType(this);
      }

      public Collection getMemberTypeDeclarations() {
         return this.declaredClassesAndInterfaces;
      }

      @Nullable
      public MemberTypeDeclaration getMemberTypeDeclaration(String name) {
         for(MemberTypeDeclaration mtd : this.declaredClassesAndInterfaces) {
            if (mtd.getName().equals(name)) {
               return mtd;
            }
         }

         return null;
      }

      @Nullable
      public MethodDeclarator getMethodDeclaration(String name) {
         for(MethodDeclarator md : this.declaredMethods) {
            if (md.name.equals(name)) {
               return md;
            }
         }

         return null;
      }

      public List getMethodDeclarations() {
         return this.declaredMethods;
      }

      public String createLocalTypeName(String localTypeName) {
         return this.getClassName() + '$' + ++this.localClassCount + '$' + localTypeName;
      }

      public String createAnonymousClassName() {
         return this.getClassName() + '$' + ++this.anonymousClassCount;
      }

      public Location getLocation() {
         return this.location;
      }

      public void throwCompileException(String message) throws CompileException {
         throw new CompileException(message, this.location);
      }

      public abstract String toString();
   }

   public abstract static class AbstractClassDeclaration extends AbstractTypeDeclaration implements ClassDeclaration {
      public final List constructors = new ArrayList();
      public final List fieldDeclarationsAndInitializers = new ArrayList();
      final SortedMap syntheticFields = new TreeMap();

      public AbstractClassDeclaration(Location location, Modifier[] modifiers, @Nullable TypeParameter[] typeParameters) {
         super(location, modifiers, typeParameters);
      }

      public void addConstructor(ConstructorDeclarator cd) {
         this.constructors.add(cd);
         cd.setDeclaringType(this);
      }

      public void addFieldDeclaration(FieldDeclaration fd) {
         this.addFieldDeclarationOrInitializer(fd);
      }

      public void addInitializer(Initializer i) {
         this.addFieldDeclarationOrInitializer(i);
      }

      public void addFieldDeclarationOrInitializer(FieldDeclarationOrInitializer fdoi) {
         this.fieldDeclarationsAndInitializers.add(fdoi);
         fdoi.setDeclaringType(this);
         if (this.resolvedType != null) {
            this.resolvedType.clearIFieldCaches();
         }

      }

      public void defineSyntheticField(IClass.IField iField) throws CompileException {
         if (!(this instanceof InnerClassDeclaration)) {
            throw new InternalCompilerException();
         } else {
            IClass.IField if2 = (IClass.IField)this.syntheticFields.get(iField.getName());
            if (if2 != null) {
               if (iField.getType() != if2.getType()) {
                  throw new InternalCompilerException();
               }
            } else {
               this.syntheticFields.put(iField.getName(), iField);
            }
         }
      }

      public List getVariableDeclaratorsAndInitializers() {
         return this.fieldDeclarationsAndInitializers;
      }

      ConstructorDeclarator[] getConstructors() {
         if (this.constructors.isEmpty()) {
            ConstructorDeclarator defaultConstructor = new ConstructorDeclarator(this.getLocation(), (String)null, Java.accessModifiers(this.getLocation(), "public"), new FunctionDeclarator.FormalParameters(this.getLocation()), new Type[0], (ConstructorInvocation)null, Collections.emptyList());
            defaultConstructor.setDeclaringType(this);
            return new ConstructorDeclarator[]{defaultConstructor};
         } else {
            return (ConstructorDeclarator[])this.constructors.toArray(new ConstructorDeclarator[this.constructors.size()]);
         }
      }

      public SortedMap getSyntheticFields() {
         return this.syntheticFields;
      }
   }

   public static final class AnonymousClassDeclaration extends AbstractClassDeclaration implements InnerClassDeclaration {
      public final Type baseType;
      @Nullable
      private String myName;

      public AnonymousClassDeclaration(Location location, Type baseType) {
         super(location, Java.accessModifiers(location, "private", "final"), (TypeParameter[])null);
         (this.baseType = baseType).setEnclosingScope(new EnclosingScopeOfTypeDeclaration(this));
      }

      @Nullable
      public Object accept(Visitor.TypeDeclarationVisitor visitor) throws Throwable {
         return visitor.visitAnonymousClassDeclaration(this);
      }

      public String getClassName() {
         if (this.myName != null) {
            return this.myName;
         } else {
            Scope s;
            for(s = this.getEnclosingScope(); !(s instanceof TypeDeclaration); s = s.getEnclosingScope()) {
            }

            return this.myName = ((TypeDeclaration)s).createAnonymousClassName();
         }
      }

      public String toString() {
         return this.getClassName();
      }
   }

   public abstract static class NamedClassDeclaration extends AbstractClassDeclaration implements NamedTypeDeclaration, DocCommentable {
      @Nullable
      private final String docComment;
      public final String name;
      @Nullable
      public final Type extendedType;
      public final Type[] implementedTypes;

      public NamedClassDeclaration(Location location, @Nullable String docComment, Modifier[] modifiers, String name, @Nullable TypeParameter[] typeParameters, @Nullable Type extendedType, Type[] implementedTypes) {
         super(location, modifiers, typeParameters);
         this.docComment = docComment;
         this.name = name;
         this.extendedType = extendedType;
         if (extendedType != null) {
            extendedType.setEnclosingScope(new EnclosingScopeOfTypeDeclaration(this));
         }

         this.implementedTypes = implementedTypes;

         for(Type implementedType : implementedTypes) {
            implementedType.setEnclosingScope(new EnclosingScopeOfTypeDeclaration(this));
         }

      }

      public String toString() {
         return this.name;
      }

      public String getName() {
         return this.name;
      }

      @Nullable
      public String getDocComment() {
         return this.docComment;
      }

      public boolean hasDeprecatedDocTag() {
         return this.docComment != null && this.docComment.indexOf("@deprecated") != -1;
      }

      public boolean isAbstract() {
         return Java.hasAccessModifier(this.getModifiers(), "abstract");
      }

      public boolean isFinal() {
         return Java.hasAccessModifier(this.getModifiers(), "final");
      }

      public boolean isStrictfp() {
         return Java.hasAccessModifier(this.getModifiers(), "strictfp");
      }
   }

   public static final class EnclosingScopeOfTypeDeclaration implements Scope {
      public final TypeDeclaration typeDeclaration;

      public EnclosingScopeOfTypeDeclaration(TypeDeclaration typeDeclaration) {
         this.typeDeclaration = typeDeclaration;
      }

      public Scope getEnclosingScope() {
         return this.typeDeclaration.getEnclosingScope();
      }
   }

   public static class MemberClassDeclaration extends NamedClassDeclaration implements MemberTypeDeclaration, InnerClassDeclaration {
      public MemberClassDeclaration(Location location, @Nullable String docComment, Modifier[] modifiers, String name, @Nullable TypeParameter[] typeParameters, @Nullable Type extendedType, Type[] implementedTypes) {
         super(location, docComment, modifiers, name, typeParameters, extendedType, implementedTypes);
      }

      public Access getAccess() {
         return Java.modifiers2Access(this.getModifiers());
      }

      public void setDeclaringType(TypeDeclaration declaringType) {
         this.setEnclosingScope(declaringType);
      }

      public TypeDeclaration getDeclaringType() {
         return (TypeDeclaration)this.getEnclosingScope();
      }

      public String getClassName() {
         return this.getDeclaringType().getClassName() + '$' + this.getName();
      }

      @Nullable
      public Object accept(Visitor.TypeDeclarationVisitor visitor) throws Throwable {
         return visitor.visitMemberClassDeclaration(this);
      }

      @Nullable
      public Object accept(Visitor.TypeBodyDeclarationVisitor visitor) throws Throwable {
         return visitor.visitMemberClassDeclaration(this);
      }

      public boolean isStatic() {
         return Java.hasAccessModifier(this.getModifiers(), "static");
      }
   }

   public static final class MemberEnumDeclaration extends MemberClassDeclaration implements EnumDeclaration {
      private final List constants = new ArrayList();

      public MemberEnumDeclaration(Location location, @Nullable String docComment, Modifier[] modifiers, String name, Type[] implementedTypes) {
         super(location, docComment, modifiers, name, (TypeParameter[])null, (Type)null, implementedTypes);
      }

      public Type[] getImplementedTypes() {
         return this.implementedTypes;
      }

      public List getConstants() {
         return this.constants;
      }

      public void addConstant(EnumConstant ec) {
         this.constants.add(ec);
      }

      @Nullable
      public Object accept(Visitor.TypeDeclarationVisitor visitor) throws Throwable {
         return visitor.visitMemberEnumDeclaration(this);
      }

      @Nullable
      public Object accept(Visitor.TypeBodyDeclarationVisitor visitor) throws Throwable {
         return visitor.visitMemberEnumDeclaration(this);
      }
   }

   public static final class LocalClassDeclaration extends NamedClassDeclaration implements InnerClassDeclaration {
      public LocalClassDeclaration(Location location, @Nullable String docComment, Modifier[] modifiers, String name, @Nullable TypeParameter[] typeParameters, @Nullable Type extendedType, Type[] implementedTypes) {
         super(location, docComment, modifiers, name, typeParameters, extendedType, implementedTypes);
      }

      public String getClassName() {
         Scope s;
         for(s = this.getEnclosingScope(); !(s instanceof TypeDeclaration); s = s.getEnclosingScope()) {
         }

         return ((TypeDeclaration)s).getClassName() + '$' + this.name;
      }

      @Nullable
      public Object accept(Visitor.TypeDeclarationVisitor visitor) throws Throwable {
         return visitor.visitLocalClassDeclaration(this);
      }
   }

   public static class PackageMemberClassDeclaration extends NamedClassDeclaration implements PackageMemberTypeDeclaration {
      public PackageMemberClassDeclaration(Location location, @Nullable String docComment, Modifier[] modifiers, String name, @Nullable TypeParameter[] typeParameters, @Nullable Type extendedType, Type[] implementedTypes) {
         super(location, docComment, modifiers, name, typeParameters, extendedType, implementedTypes);
      }

      public void setDeclaringCompilationUnit(CompilationUnit declaringCompilationUnit) {
         this.setEnclosingScope(declaringCompilationUnit);
      }

      public CompilationUnit getDeclaringCompilationUnit() {
         return (CompilationUnit)this.getEnclosingScope();
      }

      public Access getAccess() {
         return Java.modifiers2Access(this.getModifiers());
      }

      public String getClassName() {
         String className = this.getName();
         CompilationUnit compilationUnit = (CompilationUnit)this.getEnclosingScope();
         PackageDeclaration opd = compilationUnit.packageDeclaration;
         if (opd != null) {
            className = opd.packageName + '.' + className;
         }

         return className;
      }

      @Nullable
      public Object accept(Visitor.TypeDeclarationVisitor visitor) throws Throwable {
         return visitor.visitPackageMemberClassDeclaration(this);
      }

      public boolean isStatic() {
         return Java.hasAccessModifier(this.getModifiers(), "static");
      }
   }

   public static final class EnumConstant extends AbstractClassDeclaration implements DocCommentable {
      @Nullable
      public final String docComment;
      public final String name;
      @Nullable
      public final Rvalue[] arguments;

      public EnumConstant(Location location, @Nullable String docComment, Modifier[] modifiers, String name, @Nullable Rvalue[] arguments) {
         super(location, modifiers, (TypeParameter[])null);
         this.docComment = docComment;
         this.name = name;
         this.arguments = arguments;
      }

      public String getClassName() {
         return this.name;
      }

      @Nullable
      public String getDocComment() {
         return this.docComment;
      }

      public boolean hasDeprecatedDocTag() {
         return this.docComment != null && this.docComment.indexOf("@deprecated") != -1;
      }

      @Nullable
      public Object accept(Visitor.TypeDeclarationVisitor visitor) throws Throwable {
         return visitor.visitEnumConstant(this);
      }

      public String toString() {
         return "enum " + this.name + " { ... }";
      }
   }

   public static final class PackageMemberEnumDeclaration extends PackageMemberClassDeclaration implements EnumDeclaration {
      private final List constants = new ArrayList();

      public PackageMemberEnumDeclaration(Location location, @Nullable String docComment, Modifier[] modifiers, String name, Type[] implementedTypes) {
         super(location, docComment, modifiers, name, (TypeParameter[])null, (Type)null, implementedTypes);
      }

      public Type[] getImplementedTypes() {
         return this.implementedTypes;
      }

      public List getConstants() {
         return this.constants;
      }

      public void addConstant(EnumConstant ec) {
         this.constants.add(ec);
      }

      @Nullable
      public Object accept(Visitor.TypeDeclarationVisitor visitor) throws Throwable {
         return visitor.visitPackageMemberEnumDeclaration(this);
      }
   }

   public abstract static class InterfaceDeclaration extends AbstractTypeDeclaration implements NamedTypeDeclaration, DocCommentable {
      @Nullable
      private final String docComment;
      public final String name;
      public final Type[] extendedTypes;
      public final List constantDeclarations = new ArrayList();
      @Nullable
      IType[] interfaces;

      protected InterfaceDeclaration(Location location, @Nullable String docComment, Modifier[] modifiers, String name, @Nullable TypeParameter[] typeParameters, Type[] extendedTypes) {
         super(location, modifiers, typeParameters);
         this.docComment = docComment;
         this.name = name;
         this.extendedTypes = extendedTypes;

         for(Type extendedType : extendedTypes) {
            extendedType.setEnclosingScope(new EnclosingScopeOfTypeDeclaration(this));
         }

      }

      public String toString() {
         return this.name;
      }

      public void addConstantDeclaration(FieldDeclaration fd) {
         this.constantDeclarations.add(fd);
         fd.setDeclaringType(this);
         if (this.resolvedType != null) {
            this.resolvedType.clearIFieldCaches();
         }

      }

      public String getName() {
         return this.name;
      }

      @Nullable
      public String getDocComment() {
         return this.docComment;
      }

      public boolean hasDeprecatedDocTag() {
         return this.docComment != null && this.docComment.indexOf("@deprecated") != -1;
      }
   }

   public static class MemberInterfaceDeclaration extends InterfaceDeclaration implements MemberTypeDeclaration {
      public MemberInterfaceDeclaration(Location location, @Nullable String docComment, Modifier[] modifiers, String name, @Nullable TypeParameter[] typeParameters, Type[] extendedTypes) {
         super(location, docComment, modifiers, name, typeParameters, extendedTypes);
      }

      public Access getAccess() {
         return Java.modifiers2Access(this.getModifiers());
      }

      public String getClassName() {
         NamedTypeDeclaration declaringType = (NamedTypeDeclaration)this.getEnclosingScope();
         return declaringType.getClassName() + '$' + this.getName();
      }

      public void setDeclaringType(TypeDeclaration declaringType) {
         this.setEnclosingScope(declaringType);
      }

      public TypeDeclaration getDeclaringType() {
         return (TypeDeclaration)this.getEnclosingScope();
      }

      @Nullable
      public Object accept(Visitor.TypeDeclarationVisitor visitor) throws Throwable {
         return visitor.visitMemberInterfaceDeclaration(this);
      }

      @Nullable
      public Object accept(Visitor.TypeBodyDeclarationVisitor visitor) throws Throwable {
         return visitor.visitMemberInterfaceDeclaration(this);
      }
   }

   public static final class MemberAnnotationTypeDeclaration extends MemberInterfaceDeclaration implements AnnotationTypeDeclaration {
      public MemberAnnotationTypeDeclaration(Location location, @Nullable String docComment, Modifier[] modifiers, String name) {
         super(location, docComment, modifiers, name, (TypeParameter[])null, new Type[]{new ReferenceType(location, new Annotation[0], new String[]{"java", "lang", "annotation", "Annotation"}, (TypeArgument[])null)});
      }

      @Nullable
      public Object accept(Visitor.TypeDeclarationVisitor visitor) throws Throwable {
         return visitor.visitMemberAnnotationTypeDeclaration(this);
      }

      @Nullable
      public Object accept(Visitor.TypeBodyDeclarationVisitor visitor) throws Throwable {
         return visitor.visitMemberAnnotationTypeDeclaration(this);
      }
   }

   public static class PackageMemberInterfaceDeclaration extends InterfaceDeclaration implements PackageMemberTypeDeclaration {
      public PackageMemberInterfaceDeclaration(Location location, @Nullable String docComment, Modifier[] modifiers, String name, @Nullable TypeParameter[] typeParameters, Type[] extendedTypes) {
         super(location, docComment, modifiers, name, typeParameters, extendedTypes);
      }

      public void setDeclaringCompilationUnit(CompilationUnit declaringCompilationUnit) {
         this.setEnclosingScope(declaringCompilationUnit);
      }

      public CompilationUnit getDeclaringCompilationUnit() {
         return (CompilationUnit)this.getEnclosingScope();
      }

      public Access getAccess() {
         return Java.modifiers2Access(this.getModifiers());
      }

      public Annotation[] getAnnotations() {
         return Java.getAnnotations(this.getModifiers());
      }

      public boolean isAbstract() {
         return Java.hasAccessModifier(this.getModifiers(), "abstract");
      }

      public boolean isStatic() {
         return Java.hasAccessModifier(this.getModifiers(), "static");
      }

      public boolean isStrictfp() {
         return Java.hasAccessModifier(this.getModifiers(), "strictfp");
      }

      public String getClassName() {
         String className = this.getName();
         CompilationUnit compilationUnit = (CompilationUnit)this.getEnclosingScope();
         PackageDeclaration opd = compilationUnit.packageDeclaration;
         if (opd != null) {
            className = opd.packageName + '.' + className;
         }

         return className;
      }

      @Nullable
      public Object accept(Visitor.TypeDeclarationVisitor visitor) throws Throwable {
         return visitor.visitPackageMemberInterfaceDeclaration(this);
      }
   }

   public static final class PackageMemberAnnotationTypeDeclaration extends PackageMemberInterfaceDeclaration implements AnnotationTypeDeclaration {
      public PackageMemberAnnotationTypeDeclaration(Location location, @Nullable String docComment, Modifier[] modifiers, String name) {
         super(location, docComment, modifiers, name, (TypeParameter[])null, new Type[]{new ReferenceType(location, new Annotation[0], new String[]{"java", "lang", "annotation", "Annotation"}, (TypeArgument[])null)});
      }

      @Nullable
      public Object accept(Visitor.TypeDeclarationVisitor visitor) throws Throwable {
         return visitor.visitPackageMemberAnnotationTypeDeclaration(this);
      }
   }

   public static class TypeParameter {
      public final String name;
      @Nullable
      public final ReferenceType[] bound;

      public TypeParameter(String name, @Nullable ReferenceType[] bound) {
         assert name != null;

         this.name = name;
         this.bound = bound;
      }

      public String toString() {
         return this.bound != null ? this.name + " extends " + Java.join(this.bound, " & ") : this.name;
      }
   }

   public abstract static class AbstractTypeBodyDeclaration extends Located implements TypeBodyDeclaration {
      @Nullable
      private TypeDeclaration declaringType;
      public final Modifier[] modifiers;

      protected AbstractTypeBodyDeclaration(Location location, Modifier[] modifiers) {
         super(location);
         this.modifiers = modifiers;
      }

      public void setDeclaringType(TypeDeclaration declaringType) {
         if (this.declaringType != null) {
            throw new InternalCompilerException("Declaring type for type body declaration \"" + this.toString() + "\"at " + this.getLocation() + " is already set");
         } else {
            this.declaringType = declaringType;
         }
      }

      public TypeDeclaration getDeclaringType() {
         assert this.declaringType != null;

         return this.declaringType;
      }

      public Modifier[] getModifiers() {
         return this.modifiers;
      }

      public Annotation[] getAnnotations() {
         return Java.getAnnotations(this.modifiers);
      }

      public void setEnclosingScope(Scope enclosingScope) {
         if (!(enclosingScope instanceof MethodDeclarator) || !"<clinit>".equals(((MethodDeclarator)enclosingScope).name)) {
            assert this.declaringType == null;

            this.declaringType = (TypeDeclaration)enclosingScope;
         }
      }

      public Scope getEnclosingScope() {
         assert this.declaringType != null;

         return this.declaringType;
      }
   }

   public static final class Initializer extends AbstractTypeBodyDeclaration implements FieldDeclarationOrInitializer {
      public final Block block;

      public Initializer(Location location, Modifier[] modifiers, Block block) {
         super(location, modifiers);
         (this.block = block).setEnclosingScope(this);
      }

      public boolean isStatic() {
         return Java.hasAccessModifier(this.getModifiers(), "static");
      }

      public String toString() {
         return Java.toString(this.getModifiers()) + this.block;
      }

      @Nullable
      public Object accept(Visitor.FieldDeclarationOrInitializerVisitor visitor) throws Throwable {
         return visitor.visitInitializer(this);
      }

      @Nullable
      public Object accept(Visitor.TypeBodyDeclarationVisitor visitor) throws Throwable {
         return visitor.visitInitializer(this);
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitInitializer(this);
      }

      @Nullable
      public LocalVariable findLocalVariable(String name) {
         return this.block.findLocalVariable(name);
      }
   }

   public abstract static class FunctionDeclarator extends AbstractTypeBodyDeclaration implements Annotatable, DocCommentable {
      @Nullable
      private final String docComment;
      public final Type type;
      public final String name;
      public final FormalParameters formalParameters;
      public final Type[] thrownExceptions;
      @Nullable
      public final List statements;
      @Nullable
      IType returnType;
      @Nullable
      public Map localVariables;

      public FunctionDeclarator(Location location, @Nullable String docComment, Modifier[] modifiers, Type type, String name, FormalParameters formalParameters, Type[] thrownExceptions, @Nullable List statements) {
         super(location, modifiers);
         this.docComment = docComment;
         this.type = type;
         this.name = name;
         this.formalParameters = formalParameters;
         this.thrownExceptions = thrownExceptions;
         this.statements = statements;
         this.type.setEnclosingScope(this);

         for(FormalParameter fp : formalParameters.parameters) {
            fp.type.setEnclosingScope(this);
         }

         for(Type te : thrownExceptions) {
            te.setEnclosingScope(this);
         }

         if (statements != null) {
            for(BlockStatement bs : statements) {
               if (!(bs instanceof FieldDeclaration)) {
                  bs.setEnclosingScope(this);
               }
            }
         }

      }

      public Access getAccess() {
         return Java.modifiers2Access(this.getModifiers());
      }

      public Annotation[] getAnnotations() {
         return Java.getAnnotations(this.getModifiers());
      }

      @Nullable
      public final Object accept(Visitor.TypeBodyDeclarationVisitor visitor) throws Throwable {
         return visitor.visitFunctionDeclarator(this);
      }

      @Nullable
      public abstract Object accept(Visitor.FunctionDeclaratorVisitor var1) throws Throwable;

      public void setDeclaringType(TypeDeclaration declaringType) {
         super.setDeclaringType(declaringType);

         for(Annotation a : this.getAnnotations()) {
            a.setEnclosingScope(declaringType);
         }

      }

      public void setEnclosingScope(Scope enclosingScope) {
         super.setEnclosingScope(enclosingScope);

         for(Annotation a : this.getAnnotations()) {
            a.setEnclosingScope(enclosingScope);
         }

      }

      public Scope getEnclosingScope() {
         return this.getDeclaringType();
      }

      @Nullable
      public String getDocComment() {
         return this.docComment;
      }

      public boolean hasDeprecatedDocTag() {
         return this.docComment != null && this.docComment.indexOf("@deprecated") != -1;
      }

      public boolean isStrictfp() {
         return Java.hasAccessModifier(this.getModifiers(), "strictfp");
      }

      public static final class FormalParameters extends Located {
         public final FormalParameter[] parameters;
         public final boolean variableArity;

         public FormalParameters(Location location) {
            this(location, new FormalParameter[0], false);
         }

         public FormalParameters(Location location, FormalParameter[] parameters, boolean variableArity) {
            super(location);
            this.parameters = parameters;
            this.variableArity = variableArity;
         }

         public String toString() {
            if (this.parameters.length == 0) {
               return "()";
            } else {
               StringBuilder sb = new StringBuilder("(");

               for(int i = 0; i < this.parameters.length; ++i) {
                  if (i > 0) {
                     sb.append(", ");
                  }

                  sb.append(this.parameters[i].toString(i == this.parameters.length - 1 && this.variableArity));
               }

               return sb.append(')').toString();
            }
         }
      }

      public static final class FormalParameter extends Located {
         public final Modifier[] modifiers;
         public final Type type;
         public final String name;
         @Nullable
         public LocalVariable localVariable;

         public FormalParameter(Location location, Modifier[] modifiers, Type type, String name) {
            super(location);
            this.modifiers = modifiers;
            this.type = type;
            this.name = name;
         }

         public boolean isFinal() {
            return Java.hasAccessModifier(this.modifiers, "final");
         }

         public String toString(boolean hasEllipsis) {
            return this.type.toString() + (hasEllipsis ? "... " : " ") + this.name;
         }

         public String toString() {
            return this.toString(false);
         }
      }
   }

   public static final class CatchParameter extends Located {
      public final boolean finaL;
      public final Type[] types;
      public final String name;
      @Nullable
      public LocalVariable localVariable;

      public CatchParameter(Location location, boolean finaL, Type[] types, String name) {
         super(location);
         this.finaL = finaL;
         this.types = types;
         this.name = name;
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         if (this.finaL) {
            sb.append("final ");
         }

         sb.append(this.types[0]);

         for(int i = 1; i < this.types.length; ++i) {
            sb.append(" | ").append(this.types[i]);
         }

         return sb.append(" ").append(this.name).toString();
      }

      public void setEnclosingScope(Scope enclosingScope) {
         for(Type t : this.types) {
            t.setEnclosingScope(enclosingScope);
         }

      }
   }

   public static final class ConstructorDeclarator extends FunctionDeclarator {
      @Nullable
      IClass.IConstructor iConstructor;
      @Nullable
      public final ConstructorInvocation constructorInvocation;
      final Map syntheticParameters = new HashMap();

      public ConstructorDeclarator(Location location, @Nullable String docComment, Modifier[] modifiers, FunctionDeclarator.FormalParameters formalParameters, Type[] thrownExceptions, @Nullable ConstructorInvocation constructorInvocation, List statements) {
         super(location, docComment, modifiers, new PrimitiveType(location, Java.Primitive.VOID), "<init>", formalParameters, thrownExceptions, statements);
         this.constructorInvocation = constructorInvocation;
         if (constructorInvocation != null) {
            constructorInvocation.setEnclosingScope(this);
         }

      }

      public AbstractClassDeclaration getDeclaringClass() {
         return (AbstractClassDeclaration)this.getEnclosingScope();
      }

      public String toString() {
         StringBuilder sb = (new StringBuilder(this.getDeclaringClass().getClassName())).append('(');
         FunctionDeclarator.FormalParameter[] fps = this.formalParameters.parameters;

         for(int i = 0; i < fps.length; ++i) {
            if (i > 0) {
               sb.append(", ");
            }

            sb.append(fps[i].toString(i == fps.length - 1 && this.formalParameters.variableArity));
         }

         sb.append(')');
         return sb.toString();
      }

      @Nullable
      public Object accept(Visitor.FunctionDeclaratorVisitor visitor) throws Throwable {
         return visitor.visitConstructorDeclarator(this);
      }
   }

   public static final class MethodDeclarator extends FunctionDeclarator {
      @Nullable
      public final TypeParameter[] typeParameters;
      @Nullable
      public ElementValue defaultValue;
      @Nullable
      IClass.IMethod iMethod;

      public MethodDeclarator(Location location, @Nullable String docComment, Modifier[] modifiers, @Nullable TypeParameter[] typeParameters, Type type, String name, FunctionDeclarator.FormalParameters formalParameters, Type[] thrownExceptions, @Nullable ElementValue defaultValue, @Nullable List statements) {
         super(location, docComment, modifiers, type, name, formalParameters, thrownExceptions, statements);
         this.typeParameters = typeParameters;
         this.defaultValue = defaultValue;
      }

      @Nullable
      TypeParameter[] getOptionalTypeParameters() {
         return this.typeParameters;
      }

      public void setDeclaringType(TypeDeclaration declaringType) {
         super.setDeclaringType(declaringType);
         if (this.typeParameters != null) {
            for(TypeParameter tp : this.typeParameters) {
               if (tp.bound != null) {
                  for(ReferenceType boundType : tp.bound) {
                     boundType.setEnclosingScope(declaringType);
                  }
               }
            }
         }

      }

      public void setEnclosingScope(Scope enclosingScope) {
         super.setEnclosingScope(enclosingScope);
         if (this.typeParameters != null) {
            for(TypeParameter tp : this.typeParameters) {
               if (tp.bound != null) {
                  for(ReferenceType boundType : tp.bound) {
                     boundType.setEnclosingScope(enclosingScope);
                  }
               }
            }
         }

         for(FunctionDeclarator.FormalParameter fp : this.formalParameters.parameters) {
            fp.type.setEnclosingScope(enclosingScope);
         }

      }

      public String toString() {
         StringBuilder sb = (new StringBuilder(this.name)).append('(');
         FunctionDeclarator.FormalParameter[] fps = this.formalParameters.parameters;

         for(int i = 0; i < fps.length; ++i) {
            if (i > 0) {
               sb.append(", ");
            }

            sb.append(fps[i].toString(i == fps.length - 1 && this.formalParameters.variableArity));
         }

         sb.append(')');
         return sb.toString();
      }

      @Nullable
      public Object accept(Visitor.FunctionDeclaratorVisitor visitor) throws Throwable {
         return visitor.visitMethodDeclarator(this);
      }

      public boolean isStatic() {
         return Java.hasAccessModifier(this.getModifiers(), "static");
      }

      public boolean isDefault() {
         return Java.hasAccessModifier(this.getModifiers(), "default");
      }

      public boolean isAbstract() {
         return Java.hasAccessModifier(this.getModifiers(), "abstract");
      }

      public boolean isNative() {
         return Java.hasAccessModifier(this.getModifiers(), "native");
      }

      public boolean isFinal() {
         return Java.hasAccessModifier(this.getModifiers(), "final");
      }

      public boolean isSynchronized() {
         return Java.hasAccessModifier(this.getModifiers(), "synchronized");
      }
   }

   public static final class FieldDeclaration extends Statement implements Annotatable, DocCommentable, FieldDeclarationOrInitializer {
      @Nullable
      private final String docComment;
      public final Modifier[] modifiers;
      public final Type type;
      public final VariableDeclarator[] variableDeclarators;

      public FieldDeclaration(Location location, @Nullable String docComment, Modifier[] modifiers, Type type, VariableDeclarator[] variableDeclarators) {
         super(location);
         this.docComment = docComment;
         this.modifiers = modifiers;
         this.type = type;
         this.variableDeclarators = variableDeclarators;
         this.type.setEnclosingScope(this);

         for(VariableDeclarator vd : variableDeclarators) {
            vd.setEnclosingScope(this);
         }

      }

      public void setDeclaringType(TypeDeclaration declaringType) {
         this.setEnclosingScope(declaringType);
      }

      public TypeDeclaration getDeclaringType() {
         return (TypeDeclaration)this.getEnclosingScope();
      }

      public void setEnclosingScope(Scope enclosingScope) {
         super.setEnclosingScope(enclosingScope);

         for(Annotation a : this.getAnnotations()) {
            a.setEnclosingScope(enclosingScope);
         }

      }

      public Modifier[] getModifiers() {
         return this.modifiers;
      }

      public String toString() {
         StringBuilder sb = (new StringBuilder()).append(Java.toString(this.getModifiers())).append(this.type).append(' ').append(this.variableDeclarators[0]);

         for(int i = 1; i < this.variableDeclarators.length; ++i) {
            sb.append(", ").append(this.variableDeclarators[i]);
         }

         return sb.toString();
      }

      @Nullable
      public Object accept(Visitor.FieldDeclarationOrInitializerVisitor visitor) throws Throwable {
         return visitor.visitFieldDeclaration(this);
      }

      @Nullable
      public Object accept(Visitor.TypeBodyDeclarationVisitor visitor) throws Throwable {
         return visitor.visitFieldDeclaration(this);
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitFieldDeclaration(this);
      }

      @Nullable
      public String getDocComment() {
         return this.docComment;
      }

      public boolean hasDeprecatedDocTag() {
         return this.docComment != null && this.docComment.indexOf("@deprecated") != -1;
      }

      public Access getAccess() {
         return Java.modifiers2Access(this.modifiers);
      }

      public Annotation[] getAnnotations() {
         return Java.getAnnotations(this.modifiers);
      }

      public boolean isFinal() {
         return Java.hasAccessModifier(this.modifiers, "final");
      }

      public boolean isPrivate() {
         return Java.hasAccessModifier(this.modifiers, "private");
      }

      public boolean isStatic() {
         return Java.hasAccessModifier(this.modifiers, "static");
      }

      public boolean isTransient() {
         return Java.hasAccessModifier(this.modifiers, "transient");
      }

      public boolean isVolatile() {
         return Java.hasAccessModifier(this.modifiers, "volatile");
      }
   }

   public static final class VariableDeclarator extends Located {
      public final String name;
      public final int brackets;
      @Nullable
      public final ArrayInitializerOrRvalue initializer;
      @Nullable
      public LocalVariable localVariable;

      public VariableDeclarator(Location location, String name, int brackets, @Nullable ArrayInitializerOrRvalue initializer) {
         super(location);
         this.name = name;
         this.brackets = brackets;
         this.initializer = initializer;
      }

      public void setEnclosingScope(Scope s) {
         if (this.initializer != null) {
            this.initializer.setEnclosingScope(s);
         }

      }

      public String toString() {
         StringBuilder sb = new StringBuilder(this.name);

         for(int i = 0; i < this.brackets; ++i) {
            sb.append("[]");
         }

         if (this.initializer != null) {
            sb.append(" = ").append(this.initializer);
         }

         return sb.toString();
      }
   }

   public abstract static class Statement extends Located implements BlockStatement {
      @Nullable
      private Scope enclosingScope;
      @Nullable
      public Map localVariables;

      protected Statement(Location location) {
         super(location);
      }

      public void setEnclosingScope(Scope enclosingScope) {
         if (this.enclosingScope != null && enclosingScope != this.enclosingScope) {
            if (!(enclosingScope instanceof MethodDeclarator) || !"<clinit>".equals(((MethodDeclarator)enclosingScope).name)) {
               throw new InternalCompilerException("Enclosing scope is already set for statement \"" + this.toString() + "\" at " + this.getLocation());
            }
         } else {
            this.enclosingScope = enclosingScope;
         }
      }

      public Scope getEnclosingScope() {
         assert this.enclosingScope != null;

         return this.enclosingScope;
      }

      @Nullable
      public LocalVariable findLocalVariable(String name) {
         Map<String, LocalVariable> lvs = this.localVariables;
         return lvs == null ? null : (LocalVariable)lvs.get(name);
      }
   }

   public static final class LabeledStatement extends BreakableStatement {
      public final String label;
      public final Statement body;

      public LabeledStatement(Location location, String label, Statement body) {
         super(location);
         this.label = label;
         (this.body = body).setEnclosingScope(this);
      }

      public String toString() {
         return this.label + ": " + this.body;
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitLabeledStatement(this);
      }
   }

   public static final class Block extends Statement {
      public final List statements = new ArrayList();

      public Block(Location location) {
         super(location);
      }

      public void addStatement(BlockStatement statement) {
         this.statements.add(statement);
         statement.setEnclosingScope(this);
      }

      public void addStatements(List statements) {
         this.statements.addAll(statements);

         for(BlockStatement bs : statements) {
            bs.setEnclosingScope(this);
         }

      }

      public BlockStatement[] getStatements() {
         return (BlockStatement[])this.statements.toArray(new BlockStatement[this.statements.size()]);
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitBlock(this);
      }

      public String toString() {
         return "{ ... }";
      }
   }

   public abstract static class BreakableStatement extends Statement {
      @Nullable
      CodeContext.Offset whereToBreak;

      protected BreakableStatement(Location location) {
         super(location);
      }
   }

   public abstract static class ContinuableStatement extends BreakableStatement {
      @Nullable
      protected CodeContext.Offset whereToContinue;
      public final BlockStatement body;

      protected ContinuableStatement(Location location, BlockStatement body) {
         super(location);
         (this.body = body).setEnclosingScope(this);
      }
   }

   public static final class ExpressionStatement extends Statement {
      public final Rvalue rvalue;

      public ExpressionStatement(Rvalue rvalue) throws CompileException {
         super(rvalue.getLocation());
         if (!(rvalue instanceof Assignment) && !(rvalue instanceof Crement) && !(rvalue instanceof MethodInvocation) && !(rvalue instanceof SuperclassMethodInvocation) && !(rvalue instanceof NewClassInstance) && !(rvalue instanceof NewAnonymousClassInstance)) {
            String expressionType = rvalue.getClass().getName();
            expressionType = expressionType.substring(expressionType.lastIndexOf(46) + 1);
            this.throwCompileException(expressionType + " is not allowed as an expression statement. Expressions statements must be one of assignments, method invocations, or object allocations.");
         }

         (this.rvalue = rvalue).setEnclosingScope(this);
      }

      public String toString() {
         return this.rvalue.toString() + ';';
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitExpressionStatement(this);
      }
   }

   public static final class LocalClassDeclarationStatement extends Statement {
      public final LocalClassDeclaration lcd;

      public LocalClassDeclarationStatement(LocalClassDeclaration lcd) {
         super(lcd.getLocation());
         (this.lcd = lcd).setEnclosingScope(this);
      }

      public String toString() {
         return this.lcd.toString();
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitLocalClassDeclarationStatement(this);
      }
   }

   public static final class IfStatement extends Statement {
      public final Rvalue condition;
      public final BlockStatement thenStatement;
      @Nullable
      public final BlockStatement elseStatement;

      public IfStatement(Location location, Rvalue condition, BlockStatement thenStatement) {
         this(location, condition, thenStatement, (BlockStatement)null);
      }

      public IfStatement(Location location, Rvalue condition, BlockStatement thenStatement, @Nullable BlockStatement elseStatement) {
         super(location);
         (this.condition = condition).setEnclosingScope(this);
         (this.thenStatement = thenStatement).setEnclosingScope(this);
         this.elseStatement = elseStatement;
         if (elseStatement != null) {
            elseStatement.setEnclosingScope(this);
         }

      }

      public String toString() {
         return this.elseStatement == null ? "if" : "if ... else";
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitIfStatement(this);
      }
   }

   public static final class ForStatement extends ContinuableStatement {
      @Nullable
      public final BlockStatement init;
      @Nullable
      public final Rvalue condition;
      @Nullable
      public final Rvalue[] update;

      public ForStatement(Location location, @Nullable BlockStatement init, @Nullable Rvalue condition, @Nullable Rvalue[] update, BlockStatement body) {
         super(location, body);
         this.init = init;
         if (init != null) {
            init.setEnclosingScope(this);
         }

         this.condition = condition;
         if (condition != null) {
            condition.setEnclosingScope(this);
         }

         this.update = update;
         if (update != null) {
            for(Rvalue rv : update) {
               rv.setEnclosingScope(this);
            }
         }

      }

      public String toString() {
         return "for (...; ...; ...) ...";
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitForStatement(this);
      }
   }

   public static final class ForEachStatement extends ContinuableStatement {
      public final FunctionDeclarator.FormalParameter currentElement;
      public final Rvalue expression;

      public ForEachStatement(Location location, FunctionDeclarator.FormalParameter currentElement, Rvalue expression, BlockStatement body) {
         super(location, body);
         (this.currentElement = currentElement).type.setEnclosingScope(this);
         (this.expression = expression).setEnclosingScope(this);
      }

      public String toString() {
         return "for (... : ...) ...";
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitForEachStatement(this);
      }
   }

   public static final class WhileStatement extends ContinuableStatement {
      public final Rvalue condition;

      public WhileStatement(Location location, Rvalue condition, BlockStatement body) {
         super(location, body);
         (this.condition = condition).setEnclosingScope(this);
      }

      public String toString() {
         return "while (" + this.condition + ") " + this.body + ';';
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitWhileStatement(this);
      }
   }

   public static final class TryStatement extends Statement {
      public final List resources;
      public final BlockStatement body;
      public final List catchClauses;
      @Nullable
      public final Block finallY;

      public TryStatement(Location location, BlockStatement body, List catchClauses) {
         this(location, Collections.emptyList(), body, catchClauses, (Block)null);
      }

      public TryStatement(Location location, List resources, BlockStatement body, List catchClauses) {
         this(location, resources, body, catchClauses, (Block)null);
      }

      public TryStatement(Location location, List resources, BlockStatement body, List catchClauses, @Nullable Block finallY) {
         super(location);

         for(Resource r : this.resources = resources) {
            r.setEnclosingTryStatement(this);
         }

         (this.body = body).setEnclosingScope(this);

         for(CatchClause cc : this.catchClauses = catchClauses) {
            cc.setEnclosingTryStatement(this);
         }

         this.finallY = finallY;
         if (finallY != null) {
            finallY.setEnclosingScope(this);
         }

      }

      public String toString() {
         return "try ... " + Integer.toString(this.catchClauses.size()) + (this.finallY == null ? " catches" : " catches ... finally");
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitTryStatement(this);
      }

      public abstract static class Resource extends Located {
         protected Resource(Location location) {
            super(location);
         }

         public abstract void setEnclosingTryStatement(TryStatement var1);

         @Nullable
         public abstract Object accept(Visitor.TryStatementResourceVisitor var1) throws Throwable;
      }

      public static class LocalVariableDeclaratorResource extends Resource {
         public final Modifier[] modifiers;
         public final Type type;
         public final VariableDeclarator variableDeclarator;

         public LocalVariableDeclaratorResource(Location location, Modifier[] modifiers, Type type, VariableDeclarator variableDeclarator) {
            super(location);
            this.modifiers = modifiers;
            this.type = type;
            this.variableDeclarator = variableDeclarator;
         }

         public void setEnclosingTryStatement(TryStatement ts) {
            this.type.setEnclosingScope(ts);
            this.variableDeclarator.setEnclosingScope(ts);
         }

         @Nullable
         public Object accept(Visitor.TryStatementResourceVisitor visitor) throws Throwable {
            return visitor.visitLocalVariableDeclaratorResource(this);
         }

         public String toString() {
            return Java.toString(this.modifiers) + this.type + ' ' + this.variableDeclarator;
         }
      }

      public static class VariableAccessResource extends Resource {
         public final Rvalue variableAccess;

         public VariableAccessResource(Location location, Rvalue variableAccess) {
            super(location);
            this.variableAccess = variableAccess;
         }

         public void setEnclosingTryStatement(TryStatement ts) {
            this.variableAccess.setEnclosingScope(ts);
         }

         @Nullable
         public Object accept(Visitor.TryStatementResourceVisitor visitor) throws Throwable {
            return visitor.visitVariableAccessResource(this);
         }

         public String toString() {
            return this.variableAccess.toString();
         }
      }
   }

   public static class CatchClause extends Located implements Scope {
      public final CatchParameter catchParameter;
      public final BlockStatement body;
      @Nullable
      private TryStatement enclosingTryStatement;
      public boolean reachable;

      public CatchClause(Location location, CatchParameter catchParameter, BlockStatement body) {
         super(location);
         (this.catchParameter = catchParameter).setEnclosingScope(this);
         (this.body = body).setEnclosingScope(this);
      }

      public void setEnclosingTryStatement(TryStatement enclosingTryStatement) {
         if (this.enclosingTryStatement != null && enclosingTryStatement != this.enclosingTryStatement) {
            throw new InternalCompilerException("Enclosing TRY statement already set for catch clause " + this.toString() + " at " + this.getLocation());
         } else {
            this.enclosingTryStatement = enclosingTryStatement;
         }
      }

      public Scope getEnclosingScope() {
         assert this.enclosingTryStatement != null;

         return this.enclosingTryStatement;
      }

      public String toString() {
         return "catch (" + this.catchParameter + ") " + this.body;
      }
   }

   public static final class SwitchStatement extends BreakableStatement {
      public final Rvalue condition;
      public final List sbsgs;

      public SwitchStatement(Location location, Rvalue condition, List sbsgs) {
         super(location);
         (this.condition = condition).setEnclosingScope(this);

         for(SwitchBlockStatementGroup sbsg : this.sbsgs = sbsgs) {
            for(Rvalue cl : sbsg.caseLabels) {
               cl.setEnclosingScope(this);
            }

            for(BlockStatement bs : sbsg.blockStatements) {
               bs.setEnclosingScope(this);
            }
         }

      }

      public String toString() {
         return "switch (" + this.condition + ") { (" + Integer.toString(this.sbsgs.size()) + " statement groups) }";
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitSwitchStatement(this);
      }

      public static class SwitchBlockStatementGroup extends Located {
         public final List caseLabels;
         public final boolean hasDefaultLabel;
         public final List blockStatements;

         public SwitchBlockStatementGroup(Location location, List caseLabels, boolean hasDefaultLabel, List blockStatements) {
            super(location);
            this.caseLabels = caseLabels;
            this.hasDefaultLabel = hasDefaultLabel;
            this.blockStatements = blockStatements;
         }

         public String toString() {
            return this.caseLabels.size() + (this.hasDefaultLabel ? " case label(s) plus DEFAULT" : " case label(s)");
         }
      }
   }

   static class Padder extends CodeContext.Inserter implements CodeContext.FixUp {
      Padder(CodeContext codeContext) {
         Objects.requireNonNull(codeContext);
         super();
      }

      public void fixUp() {
         int x = this.offset % 4;
         if (x != 0) {
            CodeContext ca = this.getCodeContext();
            ca.pushInserter(this);
            ca.makeSpace(4 - x);
            ca.popInserter();
         }

      }
   }

   public static final class SynchronizedStatement extends Statement {
      public final Rvalue expression;
      public final BlockStatement body;
      short monitorLvIndex = -1;

      public SynchronizedStatement(Location location, Rvalue expression, BlockStatement body) {
         super(location);
         (this.expression = expression).setEnclosingScope(this);
         (this.body = body).setEnclosingScope(this);
      }

      public String toString() {
         return "synchronized(" + this.expression + ") " + this.body;
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitSynchronizedStatement(this);
      }
   }

   public static final class DoStatement extends ContinuableStatement {
      public final Rvalue condition;

      public DoStatement(Location location, BlockStatement body, Rvalue condition) {
         super(location, body);
         (this.condition = condition).setEnclosingScope(this);
      }

      public String toString() {
         return "do " + this.body + " while(" + this.condition + ");";
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitDoStatement(this);
      }
   }

   public static final class LocalVariableDeclarationStatement extends Statement {
      public final Modifier[] modifiers;
      public final Type type;
      public final VariableDeclarator[] variableDeclarators;

      public LocalVariableDeclarationStatement(Location location, Modifier[] modifiers, Type type, VariableDeclarator[] variableDeclarators) {
         super(location);
         this.modifiers = modifiers;
         this.type = type;
         this.variableDeclarators = variableDeclarators;
         this.type.setEnclosingScope(this);

         for(VariableDeclarator vd : variableDeclarators) {
            vd.setEnclosingScope(this);
         }

      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitLocalVariableDeclarationStatement(this);
      }

      public String toString() {
         StringBuilder sb = (new StringBuilder()).append(Java.toString(this.modifiers)).append(this.type).append(' ').append(this.variableDeclarators[0]);

         for(int i = 1; i < this.variableDeclarators.length; ++i) {
            sb.append(", ").append(this.variableDeclarators[i].toString());
         }

         return sb.append(';').toString();
      }

      public boolean isFinal() {
         return Java.hasAccessModifier(this.modifiers, "final");
      }
   }

   public static final class ReturnStatement extends Statement {
      @Nullable
      public final Rvalue returnValue;

      public ReturnStatement(Location location, @Nullable Rvalue returnValue) {
         super(location);
         this.returnValue = returnValue;
         if (returnValue != null) {
            returnValue.setEnclosingScope(this);
         }

      }

      public String toString() {
         return this.returnValue == null ? "return;" : "return " + this.returnValue + ';';
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitReturnStatement(this);
      }
   }

   public static final class ThrowStatement extends Statement {
      public final Rvalue expression;

      public ThrowStatement(Location location, Rvalue expression) {
         super(location);
         (this.expression = expression).setEnclosingScope(this);
      }

      public String toString() {
         return "throw " + this.expression + ';';
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitThrowStatement(this);
      }
   }

   public static final class BreakStatement extends Statement {
      @Nullable
      public final String label;

      public BreakStatement(Location location, @Nullable String label) {
         super(location);
         this.label = label;
      }

      public String toString() {
         return this.label == null ? "break;" : "break " + this.label + ';';
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitBreakStatement(this);
      }
   }

   public static final class ContinueStatement extends Statement {
      @Nullable
      public final String label;

      public ContinueStatement(Location location, @Nullable String label) {
         super(location);
         this.label = label;
      }

      public String toString() {
         return this.label == null ? "continue;" : "continue " + this.label + ';';
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitContinueStatement(this);
      }
   }

   public static final class AssertStatement extends Statement {
      public final Rvalue expression1;
      @Nullable
      public final Rvalue expression2;

      public AssertStatement(Location location, Rvalue expression1, @Nullable Rvalue expression2) {
         super(location);
         this.expression1 = expression1;
         this.expression2 = expression2;
         this.expression1.setEnclosingScope(this);
         if (this.expression2 != null) {
            this.expression2.setEnclosingScope(this);
         }

      }

      public String toString() {
         return this.expression2 == null ? "assert " + this.expression1 + ';' : "assert " + this.expression1 + " : " + this.expression2 + ';';
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitAssertStatement(this);
      }
   }

   public static final class EmptyStatement extends Statement {
      public EmptyStatement(Location location) {
         super(location);
      }

      public String toString() {
         return ";";
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitEmptyStatement(this);
      }
   }

   public abstract static class Atom extends Located {
      public Atom(Location location) {
         super(location);
      }

      @Nullable
      public Type toType() {
         return null;
      }

      @Nullable
      public Rvalue toRvalue() {
         return null;
      }

      @Nullable
      public Lvalue toLvalue() {
         return null;
      }

      public abstract String toString();

      public final Type toTypeOrCompileException() throws CompileException {
         Type result = this.toType();
         if (result != null) {
            return result;
         } else {
            throw new CompileException("Expression \"" + this.toString() + "\" is not a type", this.getLocation());
         }
      }

      public final Rvalue toRvalueOrCompileException() throws CompileException {
         Rvalue result = this.toRvalue();
         if (result != null) {
            return result;
         } else {
            throw new CompileException("Expression \"" + this.toString() + "\" is not an rvalue", this.getLocation());
         }
      }

      public final Lvalue toLvalueOrCompileException() throws CompileException {
         Lvalue result = this.toLvalue();
         if (result != null) {
            return result;
         } else {
            throw new CompileException("Expression \"" + this.toString() + "\" is not an lvalue", this.getLocation());
         }
      }

      @Nullable
      public abstract Object accept(Visitor.AtomVisitor var1) throws Throwable;
   }

   public abstract static class Type extends Atom {
      @Nullable
      private Scope enclosingScope;

      protected Type(Location location) {
         super(location);
      }

      public void setEnclosingScope(Scope enclosingScope) {
         if (this.enclosingScope != null && enclosingScope != this.enclosingScope) {
            if (!(enclosingScope instanceof MethodDeclarator) || !"<clinit>".equals(((MethodDeclarator)enclosingScope).name)) {
               throw new InternalCompilerException("Enclosing scope already set for type \"" + this.toString() + "\" at " + this.getLocation());
            }
         } else {
            this.enclosingScope = enclosingScope;
         }
      }

      public Scope getEnclosingScope() {
         assert this.enclosingScope != null : this.getLocation() + ": " + this;

         return this.enclosingScope;
      }

      public Type toType() {
         return this;
      }

      @Nullable
      public abstract Object accept(Visitor.TypeVisitor var1) throws Throwable;
   }

   public static final class SimpleType extends Type {
      public final IType iType;

      public SimpleType(Location location, IType iType) {
         super(location);
         this.iType = iType;
      }

      public void setEnclosingScope(Scope enclosingScope) {
      }

      public Scope getEnclosingScope() {
         throw new AssertionError();
      }

      public String toString() {
         return this.iType.toString();
      }

      @Nullable
      public Object accept(Visitor.AtomVisitor visitor) throws Throwable {
         return visitor.visitType(this);
      }

      @Nullable
      public Object accept(Visitor.TypeVisitor visitor) throws Throwable {
         return visitor.visitSimpleType(this);
      }
   }

   public static final class PrimitiveType extends Type {
      public final Primitive primitive;

      public PrimitiveType(Location location, Primitive primitive) {
         super(location);
         this.primitive = primitive;
      }

      public String toString() {
         return this.primitive.toString();
      }

      @Nullable
      public Object accept(Visitor.TypeVisitor visitor) throws Throwable {
         return visitor.visitPrimitiveType(this);
      }

      @Nullable
      public Object accept(Visitor.AtomVisitor visitor) throws Throwable {
         return visitor.visitType(this);
      }
   }

   public static enum Primitive {
      VOID,
      BYTE,
      SHORT,
      CHAR,
      INT,
      LONG,
      FLOAT,
      DOUBLE,
      BOOLEAN;

      public String toString() {
         return this.name().toLowerCase();
      }

      // $FF: synthetic method
      private static Primitive[] $values() {
         return new Primitive[]{VOID, BYTE, SHORT, CHAR, INT, LONG, FLOAT, DOUBLE, BOOLEAN};
      }
   }

   public static final class ReferenceType extends Type implements TypeArgument {
      public final Annotation[] annotations;
      public final String[] identifiers;
      @Nullable
      public final TypeArgument[] typeArguments;

      public ReferenceType(Location location, Annotation[] annotations, String[] identifiers, @Nullable TypeArgument[] typeArguments) {
         super(location);

         assert annotations != null;

         this.annotations = annotations;

         assert identifiers != null;

         this.identifiers = identifiers;
         this.typeArguments = typeArguments;
      }

      public void setEnclosingScope(Scope enclosingScope) {
         super.setEnclosingScope(enclosingScope);
         if (this.typeArguments != null) {
            for(TypeArgument ta : this.typeArguments) {
               ta.setEnclosingScope(enclosingScope);
            }
         }

      }

      public String toString() {
         String s = Java.join(this.annotations, " ");
         if (this.annotations.length >= 1) {
            s = s + ' ';
         }

         s = s + Java.join(this.identifiers, ".");
         if (this.typeArguments != null) {
            s = s + '<' + Java.join(this.typeArguments, ", ") + ">";
         }

         return s;
      }

      @Nullable
      public Object accept(Visitor.AtomVisitor visitor) throws Throwable {
         return visitor.visitType(this);
      }

      @Nullable
      public Object accept(Visitor.TypeVisitor visitor) throws Throwable {
         return visitor.visitReferenceType(this);
      }

      @Nullable
      public Object accept(Visitor.TypeArgumentVisitor visitor) throws Throwable {
         return visitor.visitReferenceType(this);
      }
   }

   public static final class RvalueMemberType extends Type {
      public final Rvalue rvalue;
      public final String identifier;

      public RvalueMemberType(Location location, Rvalue rvalue, String identifier) {
         super(location);
         this.rvalue = rvalue;
         this.identifier = identifier;
      }

      public String toString() {
         return this.identifier;
      }

      @Nullable
      public Object accept(Visitor.AtomVisitor visitor) throws Throwable {
         return visitor.visitType(this);
      }

      @Nullable
      public Object accept(Visitor.TypeVisitor visitor) throws Throwable {
         return visitor.visitRvalueMemberType(this);
      }
   }

   public static final class ArrayType extends Type implements TypeArgument {
      public final Type componentType;

      public ArrayType(Type componentType) {
         super(componentType.getLocation());
         this.componentType = componentType;
      }

      public void setEnclosingScope(Scope enclosingScope) {
         super.setEnclosingScope(enclosingScope);
         this.componentType.setEnclosingScope(enclosingScope);
      }

      public String toString() {
         return this.componentType.toString() + "[]";
      }

      @Nullable
      public Object accept(Visitor.AtomVisitor visitor) throws Throwable {
         return visitor.visitType(this);
      }

      @Nullable
      public Object accept(Visitor.TypeVisitor visitor) throws Throwable {
         return visitor.visitArrayType(this);
      }

      @Nullable
      public Object accept(Visitor.TypeArgumentVisitor visitor) throws Throwable {
         return visitor.visitArrayType(this);
      }
   }

   public abstract static class Rvalue extends Atom implements ArrayInitializerOrRvalue, ElementValue {
      @Nullable
      private Scope enclosingScope;
      static final Object CONSTANT_VALUE_UNKNOWN = new Object() {
         public String toString() {
            return "CONSTANT_VALUE_UNKNOWN";
         }
      };
      @Nullable
      Object constantValue;

      protected Rvalue(Location location) {
         super(location);
         this.constantValue = CONSTANT_VALUE_UNKNOWN;
      }

      @Nullable
      public final Object accept(Visitor.ElementValueVisitor visitor) throws Throwable {
         return visitor.visitRvalue(this);
      }

      public final void setEnclosingScope(final Scope enclosingScope) {
         (new AbstractTraverser() {
            public void traverseRvalue(Rvalue rv) {
               if (rv.enclosingScope != null && enclosingScope != rv.enclosingScope) {
                  throw new InternalCompilerException("Enclosing block statement for rvalue \"" + rv + "\" at " + rv.getLocation() + " is already set");
               } else {
                  rv.enclosingScope = enclosingScope;
                  super.traverseRvalue(rv);
               }
            }

            public void traverseAnonymousClassDeclaration(AnonymousClassDeclaration acd) {
               acd.setEnclosingScope(enclosingScope);
            }

            public void traverseType(Type t) {
               if (t.enclosingScope == null) {
                  t.setEnclosingScope(enclosingScope);
               } else if (enclosingScope != t.enclosingScope) {
                  throw new InternalCompilerException("Enclosing scope already set for type \"" + t.toString() + "\" at " + t.getLocation());
               }

               super.traverseType(t);
            }

            public void traverseNewInitializedArray(NewInitializedArray nia) {
               if (nia.arrayType != null) {
                  nia.arrayType.setEnclosingScope(enclosingScope);
               }

               nia.arrayInitializer.setEnclosingScope(enclosingScope);
            }
         }).visitAtom(this);
      }

      public Scope getEnclosingScope() {
         assert this.enclosingScope != null;

         return this.enclosingScope;
      }

      @Nullable
      public Scope getEnclosingScopeOrNull() {
         return this.enclosingScope;
      }

      @Nullable
      public Rvalue toRvalue() {
         return this;
      }

      @Nullable
      public abstract Object accept(Visitor.RvalueVisitor var1) throws Throwable;

      @Nullable
      public final Object accept(Visitor.AtomVisitor visitor) throws Throwable {
         return visitor.visitRvalue(this);
      }

      @Nullable
      public final Object accept(Visitor.ArrayInitializerOrRvalueVisitor visitor) throws Throwable {
         return visitor.visitRvalue(this);
      }
   }

   public abstract static class BooleanRvalue extends Rvalue {
      protected BooleanRvalue(Location location) {
         super(location);
      }
   }

   public abstract static class Lvalue extends Rvalue {
      protected Lvalue(Location location) {
         super(location);
      }

      @Nullable
      public Lvalue toLvalue() {
         return this;
      }

      @Nullable
      public abstract Object accept(Visitor.LvalueVisitor var1) throws Throwable;

      @Nullable
      public final Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitLvalue(this);
      }
   }

   public static final class AmbiguousName extends Lvalue {
      public final String[] identifiers;
      public final int n;
      @Nullable
      private Type type;
      @Nullable
      Atom reclassified;

      public AmbiguousName(Location location, String[] identifiers) {
         this(location, identifiers, identifiers.length);
      }

      public AmbiguousName(Location location, String[] identifiers, int n) {
         super(location);
         this.identifiers = identifiers;
         this.n = n;
      }

      public Type toType() {
         if (this.type != null) {
            return this.type;
         } else {
            String[] is = new String[this.n];
            System.arraycopy(this.identifiers, 0, is, 0, this.n);
            Type result = new ReferenceType(this.getLocation(), new Annotation[0], is, (TypeArgument[])null);
            Scope es = this.getEnclosingScopeOrNull();
            if (es != null) {
               result.setEnclosingScope(es);
            }

            return this.type = result;
         }
      }

      public String toString() {
         return Java.join(this.identifiers, ".", 0, this.n);
      }

      @Nullable
      public Lvalue toLvalue() {
         return (Lvalue)(this.reclassified != null ? this.reclassified.toLvalue() : this);
      }

      @Nullable
      public Rvalue toRvalue() {
         return (Rvalue)(this.reclassified != null ? this.reclassified.toRvalue() : this);
      }

      @Nullable
      public Object accept(Visitor.LvalueVisitor visitor) throws Throwable {
         return visitor.visitAmbiguousName(this);
      }
   }

   public static final class Package extends Atom {
      public final String name;

      public Package(Location location, String name) {
         super(location);
         this.name = name;
      }

      public String toString() {
         return this.name;
      }

      @Nullable
      public Object accept(Visitor.AtomVisitor visitor) throws Throwable {
         return visitor.visitPackage(this);
      }
   }

   public static final class LocalVariableAccess extends Lvalue {
      public final LocalVariable localVariable;

      public LocalVariableAccess(Location location, LocalVariable localVariable) {
         super(location);
         this.localVariable = localVariable;
      }

      public String toString() {
         return this.localVariable.slot != null ? (this.localVariable.slot.name != null ? this.localVariable.slot.name : "unnamed_lv") : "???";
      }

      @Nullable
      public Object accept(Visitor.LvalueVisitor visitor) throws Throwable {
         return visitor.visitLocalVariableAccess(this);
      }
   }

   public static final class FieldAccess extends Lvalue {
      public final Atom lhs;
      public final IClass.IField field;

      public FieldAccess(Location location, Atom lhs, IClass.IField field) {
         super(location);
         this.lhs = lhs;
         this.field = field;
      }

      public String toString() {
         return this.lhs.toString() + '.' + this.field.getName();
      }

      @Nullable
      public Object accept(Visitor.LvalueVisitor visitor) throws Throwable {
         return visitor.visitFieldAccess(this);
      }
   }

   public static final class ArrayLength extends Rvalue {
      public final Rvalue lhs;

      public ArrayLength(Location location, Rvalue lhs) {
         super(location);
         this.lhs = lhs;
      }

      public String toString() {
         return this.lhs.toString() + ".length";
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitArrayLength(this);
      }
   }

   public static final class ThisReference extends Rvalue {
      @Nullable
      IClass iClass;

      public ThisReference(Location location) {
         super(location);
      }

      public String toString() {
         return "this";
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitThisReference(this);
      }
   }

   public static final class QualifiedThisReference extends Rvalue {
      public final Type qualification;
      @Nullable
      AbstractClassDeclaration declaringClass;
      @Nullable
      TypeBodyDeclaration declaringTypeBodyDeclaration;
      @Nullable
      IType targetIType;

      public QualifiedThisReference(Location location, Type qualification) {
         super(location);
         this.qualification = qualification;
      }

      public String toString() {
         return this.qualification.toString() + ".this";
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitQualifiedThisReference(this);
      }
   }

   public static final class ClassLiteral extends Rvalue {
      public final Type type;

      public ClassLiteral(Location location, Type type) {
         super(location);
         this.type = type;
      }

      public String toString() {
         return this.type.toString() + ".class";
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitClassLiteral(this);
      }
   }

   public static final class Assignment extends Rvalue {
      public final Lvalue lhs;
      public final String operator;
      public final Rvalue rhs;

      public Assignment(Location location, Lvalue lhs, String operator, Rvalue rhs) {
         super(location);
         this.lhs = lhs;
         this.operator = operator;
         this.rhs = rhs;
      }

      public String toString() {
         return this.lhs.toString() + ' ' + this.operator + ' ' + this.rhs.toString();
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitAssignment(this);
      }
   }

   public static final class ConditionalExpression extends Rvalue {
      public final Rvalue lhs;
      public final Rvalue mhs;
      public final Rvalue rhs;

      public ConditionalExpression(Location location, Rvalue lhs, Rvalue mhs, Rvalue rhs) {
         super(location);
         this.lhs = lhs;
         this.mhs = mhs;
         this.rhs = rhs;
      }

      public String toString() {
         return this.lhs.toString() + " ? " + this.mhs.toString() + " : " + this.rhs.toString();
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitConditionalExpression(this);
      }
   }

   public static final class Crement extends Rvalue {
      public final boolean pre;
      public final String operator;
      public final Lvalue operand;

      public Crement(Location location, String operator, Lvalue operand) {
         super(location);
         this.pre = true;
         this.operator = operator;
         this.operand = operand;
      }

      public Crement(Location location, Lvalue operand, String operator) {
         super(location);
         this.pre = false;
         this.operator = operator;
         this.operand = operand;
      }

      public String toString() {
         return this.pre ? this.operator + this.operand : this.operand + this.operator;
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitCrement(this);
      }
   }

   public static final class ArrayAccessExpression extends Lvalue {
      public final Rvalue lhs;
      public final Rvalue index;

      public ArrayAccessExpression(Location location, Rvalue lhs, Rvalue index) {
         super(location);
         this.lhs = lhs;
         this.index = index;
      }

      public String toString() {
         return this.lhs.toString() + '[' + this.index + ']';
      }

      @Nullable
      public Object accept(Visitor.LvalueVisitor visitor) throws Throwable {
         return visitor.visitArrayAccessExpression(this);
      }
   }

   public static final class FieldAccessExpression extends Lvalue {
      public final Atom lhs;
      public final String fieldName;
      @Nullable
      Rvalue value;

      public FieldAccessExpression(Location location, Atom lhs, String fieldName) {
         super(location);
         this.lhs = lhs;
         this.fieldName = fieldName;
      }

      public String toString() {
         return this.lhs.toString() + '.' + this.fieldName;
      }

      @Nullable
      public Object accept(Visitor.LvalueVisitor visitor) throws Throwable {
         return visitor.visitFieldAccessExpression(this);
      }
   }

   public static final class SuperclassFieldAccessExpression extends Lvalue {
      @Nullable
      public final Type qualification;
      public final String fieldName;
      @Nullable
      Rvalue value;

      public SuperclassFieldAccessExpression(Location location, @Nullable Type qualification, String fieldName) {
         super(location);
         this.qualification = qualification;
         this.fieldName = fieldName;
      }

      public String toString() {
         return (this.qualification != null ? this.qualification.toString() + ".super." : "super.") + this.fieldName;
      }

      @Nullable
      public Object accept(Visitor.LvalueVisitor visitor) throws Throwable {
         return visitor.visitSuperclassFieldAccessExpression(this);
      }
   }

   public static final class UnaryOperation extends BooleanRvalue {
      public final String operator;
      public final Rvalue operand;

      public UnaryOperation(Location location, String operator, Rvalue operand) {
         super(location);
         this.operator = operator;
         this.operand = operand;
      }

      public String toString() {
         return this.operator + this.operand.toString();
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitUnaryOperation(this);
      }
   }

   public static final class Instanceof extends Rvalue {
      public final Rvalue lhs;
      public final Type rhs;

      public Instanceof(Location location, Rvalue lhs, Type rhs) {
         super(location);
         this.lhs = lhs;
         this.rhs = rhs;
      }

      public String toString() {
         return this.lhs.toString() + " instanceof " + this.rhs.toString();
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitInstanceof(this);
      }
   }

   public static final class BinaryOperation extends BooleanRvalue {
      public final Rvalue lhs;
      public final String operator;
      public final Rvalue rhs;

      public BinaryOperation(Location location, Rvalue lhs, String operator, Rvalue rhs) {
         super(location);
         this.lhs = lhs;
         this.operator = operator;
         this.rhs = rhs;
      }

      public String toString() {
         return this.lhs.toString() + ' ' + this.operator + ' ' + this.rhs.toString();
      }

      public Iterator unrollLeftAssociation() {
         List<Rvalue> operands = new ArrayList();
         BinaryOperation bo = this;

         while(true) {
            operands.add(bo.rhs);
            Rvalue lhs = bo.lhs;
            if (!(lhs instanceof BinaryOperation) || ((BinaryOperation)lhs).operator != this.operator) {
               operands.add(lhs);
               return new ReverseListIterator(operands.listIterator(operands.size()));
            }

            bo = (BinaryOperation)lhs;
         }
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitBinaryOperation(this);
      }
   }

   public static final class Cast extends Rvalue {
      public final Type targetType;
      public final Rvalue value;

      public Cast(Location location, Type targetType, Rvalue value) {
         super(location);
         this.targetType = targetType;
         this.value = value;
      }

      public String toString() {
         return '(' + this.targetType.toString() + ") " + this.value.toString();
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitCast(this);
      }
   }

   public static final class ParenthesizedExpression extends Lvalue {
      public final Rvalue value;

      public ParenthesizedExpression(Location location, Rvalue value) {
         super(location);
         this.value = value;
      }

      public String toString() {
         return '(' + this.value.toString() + ')';
      }

      @Nullable
      public Object accept(Visitor.LvalueVisitor visitor) throws Throwable {
         return visitor.visitParenthesizedExpression(this);
      }
   }

   public abstract static class ConstructorInvocation extends Atom implements BlockStatement {
      public final Rvalue[] arguments;
      @Nullable
      private Scope enclosingScope;
      @Nullable
      public Map localVariables;

      protected ConstructorInvocation(Location location, Rvalue[] arguments) {
         super(location);
         this.arguments = arguments;

         for(Rvalue a : arguments) {
            a.setEnclosingScope(this);
         }

      }

      public void setEnclosingScope(Scope enclosingScope) {
         if (this.enclosingScope != null) {
            throw new InternalCompilerException("Enclosing scope is already set for statement \"" + this.toString() + "\" at " + this.getLocation());
         } else {
            this.enclosingScope = enclosingScope;
         }
      }

      public Scope getEnclosingScope() {
         assert this.enclosingScope != null;

         return this.enclosingScope;
      }

      @Nullable
      public LocalVariable findLocalVariable(String name) {
         return this.localVariables != null ? (LocalVariable)this.localVariables.get(name) : null;
      }

      @Nullable
      public final Object accept(Visitor.AtomVisitor visitor) throws Throwable {
         return visitor.visitConstructorInvocation(this);
      }

      @Nullable
      public abstract Object accept(Visitor.ConstructorInvocationVisitor var1) throws Throwable;
   }

   public static final class AlternateConstructorInvocation extends ConstructorInvocation {
      public AlternateConstructorInvocation(Location location, Rvalue[] arguments) {
         super(location, arguments);
      }

      public String toString() {
         return "this()";
      }

      @Nullable
      public Object accept(Visitor.ConstructorInvocationVisitor visitor) throws Throwable {
         return visitor.visitAlternateConstructorInvocation(this);
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitAlternateConstructorInvocation(this);
      }
   }

   public static final class SuperConstructorInvocation extends ConstructorInvocation {
      @Nullable
      public final Rvalue qualification;

      public SuperConstructorInvocation(Location location, @Nullable Rvalue qualification, Rvalue[] arguments) {
         super(location, arguments);
         this.qualification = qualification;
         if (qualification != null) {
            qualification.setEnclosingScope(this);
         }

      }

      public String toString() {
         return "super()";
      }

      @Nullable
      public Object accept(Visitor.ConstructorInvocationVisitor visitor) throws Throwable {
         return visitor.visitSuperConstructorInvocation(this);
      }

      @Nullable
      public Object accept(Visitor.BlockStatementVisitor visitor) throws Throwable {
         return visitor.visitSuperConstructorInvocation(this);
      }
   }

   public static final class MethodInvocation extends Invocation {
      @Nullable
      public final Atom target;
      @Nullable
      IClass.IMethod iMethod;

      public MethodInvocation(Location location, @Nullable Atom target, String methodName, Rvalue[] arguments) {
         super(location, methodName, arguments);
         this.target = target;
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         if (this.target != null) {
            sb.append(this.target.toString()).append('.');
         }

         sb.append(this.methodName).append('(');

         for(int i = 0; i < this.arguments.length; ++i) {
            if (i > 0) {
               sb.append(", ");
            }

            sb.append(this.arguments[i].toString());
         }

         sb.append(')');
         return sb.toString();
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitMethodInvocation(this);
      }
   }

   public static final class SuperclassMethodInvocation extends Invocation {
      public SuperclassMethodInvocation(Location location, String methodName, Rvalue[] arguments) {
         super(location, methodName, arguments);
      }

      public String toString() {
         return "super." + this.methodName + "()";
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitSuperclassMethodInvocation(this);
      }
   }

   public abstract static class Invocation extends Rvalue {
      public final String methodName;
      public final Rvalue[] arguments;

      protected Invocation(Location location, String methodName, Rvalue[] arguments) {
         super(location);
         this.methodName = methodName;
         this.arguments = arguments;
      }
   }

   public static final class NewClassInstance extends Rvalue {
      @Nullable
      public final Rvalue qualification;
      @Nullable
      public final Type type;
      public final Rvalue[] arguments;
      @Nullable
      public IType iType;

      public NewClassInstance(Location location, @Nullable Rvalue qualification, Type type, Rvalue[] arguments) {
         super(location);
         this.qualification = qualification;
         this.type = type;
         this.arguments = arguments;
      }

      public NewClassInstance(Location location, @Nullable Rvalue qualification, IType iType, Rvalue[] arguments) {
         super(location);
         this.qualification = qualification;
         this.type = null;
         this.arguments = arguments;
         this.iType = iType;
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         if (this.qualification != null) {
            sb.append(this.qualification.toString()).append('.');
         }

         sb.append("new ");
         if (this.type != null) {
            sb.append(this.type.toString());
         } else if (this.iType != null) {
            sb.append(this.iType.toString());
         } else {
            sb.append("???");
         }

         sb.append('(');

         for(int i = 0; i < this.arguments.length; ++i) {
            if (i > 0) {
               sb.append(", ");
            }

            sb.append(this.arguments[i].toString());
         }

         sb.append(')');
         return sb.toString();
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitNewClassInstance(this);
      }
   }

   public static final class NewAnonymousClassInstance extends Rvalue {
      @Nullable
      public final Rvalue qualification;
      public final AnonymousClassDeclaration anonymousClassDeclaration;
      public final Rvalue[] arguments;

      public NewAnonymousClassInstance(Location location, @Nullable Rvalue qualification, AnonymousClassDeclaration anonymousClassDeclaration, Rvalue[] arguments) {
         super(location);
         this.qualification = qualification;
         this.anonymousClassDeclaration = anonymousClassDeclaration;
         this.arguments = arguments;
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         if (this.qualification != null) {
            sb.append(this.qualification.toString()).append('.');
         }

         sb.append("new ").append(this.anonymousClassDeclaration.baseType.toString()).append("() { ... }");
         return sb.toString();
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitNewAnonymousClassInstance(this);
      }
   }

   public static final class ParameterAccess extends Rvalue {
      public final FunctionDeclarator.FormalParameter formalParameter;

      public ParameterAccess(Location location, FunctionDeclarator.FormalParameter formalParameter) {
         super(location);
         this.formalParameter = formalParameter;
      }

      public String toString() {
         return this.formalParameter.name;
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitParameterAccess(this);
      }
   }

   public static final class NewArray extends Rvalue {
      public final Type type;
      public final Rvalue[] dimExprs;
      public final int dims;

      public NewArray(Location location, Type type, Rvalue[] dimExprs, int dims) {
         super(location);
         this.type = type;
         this.dimExprs = dimExprs;
         this.dims = dims;
      }

      public String toString() {
         return "new " + this.type.toString() + "[]...";
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitNewArray(this);
      }
   }

   public static final class NewInitializedArray extends Rvalue {
      @Nullable
      public final ArrayType arrayType;
      public final ArrayInitializer arrayInitializer;
      @Nullable
      public final IClass arrayIClass;

      public NewInitializedArray(Location location, @Nullable ArrayType arrayType, ArrayInitializer arrayInitializer) {
         super(location);
         this.arrayType = arrayType;
         this.arrayInitializer = arrayInitializer;
         this.arrayIClass = null;
      }

      NewInitializedArray(Location location, IClass arrayIClass, ArrayInitializer arrayInitializer) {
         super(location);
         this.arrayType = null;
         this.arrayInitializer = arrayInitializer;
         this.arrayIClass = arrayIClass;
      }

      public String toString() {
         return "new " + (this.arrayType != null ? this.arrayType.toString() : String.valueOf(this.arrayIClass)) + " { ... }";
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitNewInitializedArray(this);
      }
   }

   public static final class ArrayInitializer extends Located implements ArrayInitializerOrRvalue {
      public final ArrayInitializerOrRvalue[] values;

      public ArrayInitializer(Location location, ArrayInitializerOrRvalue[] values) {
         super(location);
         this.values = values;
      }

      public void setEnclosingScope(Scope s) {
         for(ArrayInitializerOrRvalue v : this.values) {
            v.setEnclosingScope(s);
         }

      }

      @Nullable
      public Object accept(Visitor.ArrayInitializerOrRvalueVisitor aiorvv) throws Throwable {
         return aiorvv.visitArrayInitializer(this);
      }

      public String toString() {
         return " { (" + this.values.length + " values) }";
      }
   }

   public abstract static class Literal extends Rvalue {
      public final String value;

      public Literal(Location location, String value) {
         super(location);
         this.value = value;
      }

      public String toString() {
         return this.value;
      }
   }

   public static class LambdaExpression extends Rvalue {
      public final LambdaParameters parameters;
      public final LambdaBody body;

      public LambdaExpression(Location location, LambdaParameters parameters, LambdaBody body) {
         super(location);
         this.parameters = parameters;
         this.body = body;
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor rvv) throws Throwable {
         return rvv.visitLambdaExpression(this);
      }

      public String toString() {
         return this.parameters + " -> " + this.body;
      }
   }

   public static class IdentifierLambdaParameters implements LambdaParameters {
      public final String identifier;

      public IdentifierLambdaParameters(String identifier) {
         this.identifier = identifier;
      }

      @Nullable
      public Object accept(Visitor.LambdaParametersVisitor lpv) throws Throwable {
         return lpv.visitIdentifierLambdaParameters(this);
      }
   }

   public static class FormalLambdaParameters implements LambdaParameters {
      public final FunctionDeclarator.FormalParameters formalParameters;

      public FormalLambdaParameters(FunctionDeclarator.FormalParameters formalParameters) {
         this.formalParameters = formalParameters;
      }

      @Nullable
      public Object accept(Visitor.LambdaParametersVisitor lpv) throws Throwable {
         return lpv.visitFormalLambdaParameters(this);
      }

      public String toString() {
         return this.formalParameters.toString();
      }
   }

   public static class InferredLambdaParameters implements LambdaParameters {
      public final String[] names;

      public InferredLambdaParameters(String[] names) {
         this.names = names;
      }

      @Nullable
      public Object accept(Visitor.LambdaParametersVisitor lpv) throws Throwable {
         return lpv.visitInferredLambdaParameters(this);
      }
   }

   public static class BlockLambdaBody implements LambdaBody {
      public final Block block;

      public BlockLambdaBody(Block block) {
         this.block = block;
      }

      @Nullable
      public Object accept(Visitor.LambdaBodyVisitor lbv) throws Throwable {
         return lbv.visitBlockLambdaBody(this);
      }
   }

   public static class ExpressionLambdaBody implements LambdaBody {
      public final Rvalue expression;

      public ExpressionLambdaBody(Rvalue expression) {
         this.expression = expression;
      }

      @Nullable
      public Object accept(Visitor.LambdaBodyVisitor lbv) throws Throwable {
         return lbv.visitExpressionLambdaBody(this);
      }
   }

   public static final class IntegerLiteral extends Literal {
      public IntegerLiteral(Location location, String value) {
         super(location, value);
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitIntegerLiteral(this);
      }
   }

   public static final class FloatingPointLiteral extends Literal {
      public FloatingPointLiteral(Location location, String value) {
         super(location, value);
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitFloatingPointLiteral(this);
      }
   }

   public static final class BooleanLiteral extends Literal {
      public BooleanLiteral(Location location, String value) {
         super(location, value);
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitBooleanLiteral(this);
      }
   }

   public static final class CharacterLiteral extends Literal {
      public CharacterLiteral(Location location, String value) {
         super(location, value);
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitCharacterLiteral(this);
      }
   }

   public static final class StringLiteral extends Literal {
      public StringLiteral(Location location, String value) {
         super(location, value);
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitStringLiteral(this);
      }
   }

   public static final class NullLiteral extends Literal {
      public NullLiteral(Location location) {
         super(location, "null");
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitNullLiteral(this);
      }
   }

   public static final class SimpleConstant extends Rvalue {
      @Nullable
      final Object value;

      public SimpleConstant(Location location) {
         super(location);
         this.value = null;
      }

      public SimpleConstant(Location location, byte value) {
         super(location);
         this.value = value;
      }

      public SimpleConstant(Location location, short value) {
         super(location);
         this.value = value;
      }

      public SimpleConstant(Location location, int value) {
         super(location);
         this.value = value;
      }

      public SimpleConstant(Location location, long value) {
         super(location);
         this.value = value;
      }

      public SimpleConstant(Location location, float value) {
         super(location);
         this.value = value;
      }

      public SimpleConstant(Location location, double value) {
         super(location);
         this.value = value;
      }

      public SimpleConstant(Location location, char value) {
         super(location);
         this.value = value;
      }

      public SimpleConstant(Location location, boolean value) {
         super(location);
         this.value = value;
      }

      public SimpleConstant(Location location, String value) {
         super(location);
         this.value = value;
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor visitor) throws Throwable {
         return visitor.visitSimpleConstant(this);
      }

      public String toString() {
         return "[" + this.value + ']';
      }
   }

   public static final class MethodReference extends Rvalue {
      public final Atom lhs;
      public final String methodName;

      public MethodReference(Location location, Atom lhs, String methodName) {
         super(location);
         this.lhs = lhs;
         this.methodName = methodName;
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor rvv) throws Throwable {
         return rvv.visitMethodReference(this);
      }

      public String toString() {
         return this.lhs + "::" + this.methodName;
      }
   }

   public static final class ClassInstanceCreationReference extends Rvalue {
      public final Type type;
      @Nullable
      public final TypeArgument[] typeArguments;

      public ClassInstanceCreationReference(Location location, Type type, @Nullable TypeArgument[] typeArguments) {
         super(location);
         this.type = type;
         this.typeArguments = typeArguments;
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor rvv) throws Throwable {
         return rvv.visitInstanceCreationReference(this);
      }

      public String toString() {
         return this.type + "::" + (this.typeArguments != null ? this.typeArguments : "") + "new";
      }
   }

   public static final class ArrayCreationReference extends Rvalue {
      public final ArrayType type;

      public ArrayCreationReference(Location location, ArrayType type) {
         super(location);
         this.type = type;
      }

      @Nullable
      public Object accept(Visitor.RvalueVisitor rvv) throws Throwable {
         return rvv.visitArrayCreationReference(this);
      }

      public String toString() {
         return this.type + "::new";
      }
   }

   public static class LocalVariableSlot {
      private short slotIndex = -1;
      @Nullable
      private String name;
      @Nullable
      private final IType type;
      @Nullable
      private CodeContext.Offset start;
      @Nullable
      private CodeContext.Offset end;

      public LocalVariableSlot(@Nullable String name, short slotNumber, @Nullable IType type) {
         this.name = name;
         this.slotIndex = slotNumber;
         this.type = type;
      }

      public String toString() {
         StringBuilder buf = new StringBuilder();
         buf.append("local var(").append(this.name).append(", slot# ").append(this.slotIndex);
         if (this.type != null) {
            buf.append(", ").append(this.type);
         }

         CodeContext.Offset s = this.start;
         if (s != null) {
            buf.append(", start offset ").append(s.offset);
         }

         CodeContext.Offset e = this.end;
         if (e != null) {
            buf.append(", end offset ").append(e.offset);
         }

         buf.append(")");
         return buf.toString();
      }

      public short getSlotIndex() {
         return this.slotIndex;
      }

      public void setSlotIndex(short slotIndex) {
         this.slotIndex = slotIndex;
      }

      @Nullable
      public String getName() {
         return this.name;
      }

      public void setName(String name) {
         this.name = name;
      }

      @Nullable
      public CodeContext.Offset getStart() {
         return this.start;
      }

      public void setStart(CodeContext.Offset start) {
         assert this.start == null;

         this.start = start;
      }

      @Nullable
      public CodeContext.Offset getEnd() {
         return this.end;
      }

      public void setEnd(CodeContext.Offset end) {
         assert this.end == null;

         this.end = end;
      }

      public IType getType() {
         assert this.type != null;

         return this.type;
      }
   }

   public static class LocalVariable {
      public final boolean finaL;
      public final IType type;
      @Nullable
      public LocalVariableSlot slot;

      public LocalVariable(boolean finaL, IType type) {
         this.finaL = finaL;
         this.type = type;
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         if (this.finaL) {
            sb.append("final ");
         }

         if (this.slot != null) {
            sb.append(this.slot);
         } else {
            sb.append(this.type);
         }

         return sb.toString();
      }

      public void setSlot(LocalVariableSlot slot) {
         this.slot = slot;
      }

      public short getSlotIndex() {
         return this.slot != null ? this.slot.getSlotIndex() : -1;
      }
   }

   public static class Wildcard implements TypeArgument {
      public static final int BOUNDS_NONE = 0;
      public static final int BOUNDS_EXTENDS = 1;
      public static final int BOUNDS_SUPER = 2;
      public final int bounds;
      @Nullable
      public final ReferenceType referenceType;

      public Wildcard() {
         this(0, (ReferenceType)null);
      }

      public Wildcard(int bounds, @Nullable ReferenceType referenceType) {
         if (referenceType == null) {
            assert bounds == 0;

            this.bounds = bounds;
            this.referenceType = null;
         } else {
            assert bounds == 1 || bounds == 2;

            this.bounds = bounds;
            this.referenceType = referenceType;
         }

      }

      public void setEnclosingScope(Scope enclosingScope) {
         if (this.referenceType != null) {
            this.referenceType.setEnclosingScope(enclosingScope);
         }

      }

      @Nullable
      public final Object accept(Visitor.TypeArgumentVisitor visitor) throws Throwable {
         return visitor.visitWildcard(this);
      }

      public String toString() {
         return this.bounds == 1 ? "? extends " + this.referenceType : (this.bounds == 2 ? "? super " + this.referenceType : "?");
      }
   }

   public interface Annotatable {
      Annotation[] getAnnotations();
   }

   public interface Annotation extends Locatable, ElementValue, Modifier {
      @Nullable
      Object accept(Visitor.AnnotationVisitor var1) throws Throwable;

      void setEnclosingScope(Scope var1);

      Type getType();
   }

   public interface AnnotationTypeDeclaration extends NamedTypeDeclaration, DocCommentable {
      Modifier[] getModifiers();
   }

   public interface ArrayInitializerOrRvalue extends Locatable {
      void setEnclosingScope(Scope var1);

      @Nullable
      Object accept(Visitor.ArrayInitializerOrRvalueVisitor var1) throws Throwable;
   }

   public interface BlockStatement extends Locatable, Scope {
      void setEnclosingScope(Scope var1);

      Scope getEnclosingScope();

      @Nullable
      Object accept(Visitor.BlockStatementVisitor var1) throws Throwable;

      @Nullable
      LocalVariable findLocalVariable(String var1);
   }

   public interface ClassDeclaration extends TypeDeclaration {
      List getVariableDeclaratorsAndInitializers();

      SortedMap getSyntheticFields();
   }

   public interface DocCommentable {
      @Nullable
      String getDocComment();

      boolean hasDeprecatedDocTag();
   }

   public interface ElementValue extends Locatable {
      @Nullable
      Object accept(Visitor.ElementValueVisitor var1) throws Throwable;

      void setEnclosingScope(Scope var1);
   }

   public interface EnumDeclaration extends ClassDeclaration, NamedTypeDeclaration, DocCommentable {
      Modifier[] getModifiers();

      String getName();

      Type[] getImplementedTypes();

      List getConstants();

      void addConstant(EnumConstant var1);
   }

   public interface FieldDeclarationOrInitializer extends BlockStatement, TypeBodyDeclaration {
      @Nullable
      Object accept(Visitor.FieldDeclarationOrInitializerVisitor var1) throws Throwable;
   }

   interface InnerClassDeclaration extends ClassDeclaration {
      void defineSyntheticField(IClass.IField var1) throws CompileException;
   }

   public interface LambdaBody {
      @Nullable
      Object accept(Visitor.LambdaBodyVisitor var1) throws Throwable;
   }

   public interface LambdaParameters {
      @Nullable
      Object accept(Visitor.LambdaParametersVisitor var1) throws Throwable;
   }

   public interface Locatable {
      Location getLocation();

      void throwCompileException(String var1) throws CompileException;
   }

   public interface MemberTypeDeclaration extends NamedTypeDeclaration, TypeBodyDeclaration {
      Access getAccess();
   }

   public interface Modifier extends Locatable {
      @Nullable
      Object accept(Visitor.ModifierVisitor var1) throws Throwable;
   }

   public interface ModuleDirective {
      @Nullable
      Object accept(Visitor.ModuleDirectiveVisitor var1) throws Throwable;
   }

   public interface NamedTypeDeclaration extends TypeDeclaration {
      String getName();

      @Nullable
      TypeParameter[] getOptionalTypeParameters();
   }

   public interface PackageMemberTypeDeclaration extends NamedTypeDeclaration {
      void setDeclaringCompilationUnit(CompilationUnit var1);

      CompilationUnit getDeclaringCompilationUnit();

      Access getAccess();
   }

   public interface Scope {
      Scope getEnclosingScope();
   }

   public interface TypeArgument {
      void setEnclosingScope(Scope var1);

      @Nullable
      Object accept(Visitor.TypeArgumentVisitor var1) throws Throwable;
   }

   public interface TypeBodyDeclaration extends Locatable, Scope {
      void setDeclaringType(TypeDeclaration var1);

      TypeDeclaration getDeclaringType();

      Modifier[] getModifiers();

      @Nullable
      Object accept(Visitor.TypeBodyDeclarationVisitor var1) throws Throwable;
   }

   public interface TypeDeclaration extends Annotatable, Locatable, Scope {
      @Nullable
      MemberTypeDeclaration getMemberTypeDeclaration(String var1);

      Collection getMemberTypeDeclarations();

      @Nullable
      MethodDeclarator getMethodDeclaration(String var1);

      List getMethodDeclarations();

      String getClassName();

      String createLocalTypeName(String var1);

      String createAnonymousClassName();

      @Nullable
      Object accept(Visitor.TypeDeclarationVisitor var1) throws Throwable;
   }
}
