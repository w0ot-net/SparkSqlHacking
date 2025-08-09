package org.codehaus.janino;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.nullanalysis.Nullable;

public class Parser {
   private final Scanner scanner;
   private final TokenStream tokenStream;
   @Nullable
   private String docComment;
   private static final String[] ACCESS_MODIFIER_KEYWORDS = new String[]{"public", "protected", "private", "static", "abstract", "final", "native", "synchronized", "transient", "volatile", "strictfp", "default", "transitive"};
   private boolean preferParametrizedTypes;
   private int sourceVersion;
   @Nullable
   private WarningHandler warningHandler;
   private static final List MUTUALLY_EXCLUSIVE_ACCESS_MODIFIERS = Arrays.asList(new HashSet(Arrays.asList("public", "protected", "private")), new HashSet(Arrays.asList("abstract", "final")));

   public Parser(Scanner scanner) {
      this(scanner, new TokenStreamImpl(scanner));
   }

   public Parser(Scanner scanner, TokenStream tokenStream) {
      this.sourceVersion = -1;
      (this.scanner = scanner).setIgnoreWhiteSpace(true);
      this.tokenStream = tokenStream;
   }

   @Nullable
   public String doc() {
      String s = this.docComment;
      this.docComment = null;
      return s;
   }

   public Scanner getScanner() {
      return this.scanner;
   }

   public Java.AbstractCompilationUnit parseAbstractCompilationUnit() throws CompileException, IOException {
      String docComment = this.doc();
      Java.Modifier[] modifiers = this.parseModifiers();
      Java.PackageDeclaration packageDeclaration = null;
      if (this.peek("package")) {
         packageDeclaration = this.parsePackageDeclarationRest(docComment, modifiers);
         docComment = this.doc();
         modifiers = this.parseModifiers();
      }

      List<Java.AbstractCompilationUnit.ImportDeclaration> l;
      for(l = new ArrayList(); this.peek("import"); modifiers = this.parseModifiers()) {
         if (modifiers.length > 0) {
            this.warning("import.modifiers", "No modifiers allowed on import declarations");
         }

         if (docComment != null) {
            this.warning("import.doc_comment", "Doc comment on import declaration");
         }

         l.add(this.parseImportDeclaration());
         docComment = this.doc();
      }

      Java.AbstractCompilationUnit.ImportDeclaration[] importDeclarations = (Java.AbstractCompilationUnit.ImportDeclaration[])l.toArray(new Java.AbstractCompilationUnit.ImportDeclaration[l.size()]);
      if (this.peek("open", "module") != -1) {
         return new Java.ModularCompilationUnit(this.location().getFileName(), importDeclarations, this.parseModuleDeclarationRest(modifiers));
      } else {
         Java.CompilationUnit compilationUnit = new Java.CompilationUnit(this.location().getFileName(), importDeclarations);
         compilationUnit.setPackageDeclaration(packageDeclaration);
         if (this.peek(TokenType.END_OF_INPUT)) {
            return compilationUnit;
         } else {
            compilationUnit.addPackageMemberTypeDeclaration(this.parsePackageMemberTypeDeclarationRest(docComment, modifiers));

            while(!this.peek(TokenType.END_OF_INPUT)) {
               if (!this.peekRead(";")) {
                  compilationUnit.addPackageMemberTypeDeclaration(this.parsePackageMemberTypeDeclaration());
               }
            }

            return compilationUnit;
         }
      }
   }

   public Java.ModuleDeclaration parseModuleDeclarationRest(Java.Modifier[] modifiers) throws CompileException, IOException {
      boolean isOpen = this.peekRead("open");
      this.read("module");
      String[] moduleName = this.parseQualifiedIdentifier();
      this.read("(");
      List<Java.ModuleDirective> moduleDirectives = new ArrayList();

      while(!this.peekRead(")")) {
         Java.ModuleDirective md;
         switch (this.read("requires", "exports", "opens", "uses", "provides")) {
            case 0:
               md = new Java.RequiresModuleDirective(this.location(), this.parseModifiers(), this.parseQualifiedIdentifier());
               break;
            case 1:
               String[] packageName = this.parseQualifiedIdentifier();
               String[][] toModuleNames;
               if (!this.peekRead("to")) {
                  toModuleNames = null;
               } else {
                  List<String[]> l = new ArrayList();
                  l.add(this.parseQualifiedIdentifier());

                  while(this.peekRead(",")) {
                     l.add(this.parseQualifiedIdentifier());
                  }

                  toModuleNames = (String[][])l.toArray(new String[l.size()][]);
               }

               md = new Java.ExportsModuleDirective(this.location(), packageName, toModuleNames);
               break;
            case 2:
               String[] packageName = this.parseQualifiedIdentifier();
               String[][] toModuleNames;
               if (!this.peekRead("to")) {
                  toModuleNames = null;
               } else {
                  List<String[]> l = new ArrayList();
                  l.add(this.parseQualifiedIdentifier());

                  while(this.peekRead(",")) {
                     l.add(this.parseQualifiedIdentifier());
                  }

                  toModuleNames = (String[][])l.toArray(new String[l.size()][]);
               }

               md = new Java.OpensModuleDirective(this.location(), packageName, toModuleNames);
               break;
            case 3:
               md = new Java.UsesModuleDirective(this.location(), this.parseQualifiedIdentifier());
               break;
            case 4:
               String[] typeName = this.parseQualifiedIdentifier();
               this.read("with");
               List<String[]> withTypeNames = new ArrayList();
               withTypeNames.add(this.parseQualifiedIdentifier());

               while(this.peekRead(",")) {
                  withTypeNames.add(this.parseQualifiedIdentifier());
               }

               md = new Java.ProvidesModuleDirective(this.location(), typeName, (String[][])withTypeNames.toArray(new String[withTypeNames.size()][]));
               break;
            default:
               throw new AssertionError();
         }

         this.read(";");
         moduleDirectives.add(md);
      }

      return new Java.ModuleDeclaration(this.location(), modifiers, isOpen, moduleName, (Java.ModuleDirective[])moduleDirectives.toArray(new Java.ModuleDirective[moduleDirectives.size()]));
   }

   public Java.PackageDeclaration parsePackageDeclaration() throws CompileException, IOException {
      return this.parsePackageDeclarationRest(this.doc(), this.parseModifiers());
   }

   public Java.PackageDeclaration parsePackageDeclarationRest(@Nullable String docComment, Java.Modifier[] modifiers) throws CompileException, IOException {
      this.packageModifiers(modifiers);
      this.read("package");
      Location loc = this.location();
      String packageName = join(this.parseQualifiedIdentifier(), ".");
      this.read(";");
      this.verifyStringIsConventionalPackageName(packageName, loc);
      return new Java.PackageDeclaration(loc, packageName);
   }

   public Java.AbstractCompilationUnit.ImportDeclaration parseImportDeclaration() throws CompileException, IOException {
      this.read("import");
      Java.AbstractCompilationUnit.ImportDeclaration importDeclaration = this.parseImportDeclarationBody();
      this.read(";");
      return importDeclaration;
   }

   public Java.AbstractCompilationUnit.ImportDeclaration parseImportDeclarationBody() throws CompileException, IOException {
      Location loc = this.location();
      boolean isStatic = this.peekRead("static");
      List<String> l = new ArrayList();
      l.add(this.read(TokenType.IDENTIFIER));

      while(this.peek(".")) {
         this.read(".");
         if (this.peekRead("*")) {
            String[] identifiers = (String[])l.toArray(new String[l.size()]);
            return (Java.AbstractCompilationUnit.ImportDeclaration)(isStatic ? new Java.AbstractCompilationUnit.StaticImportOnDemandDeclaration(loc, identifiers) : new Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration(loc, identifiers));
         }

         l.add(this.read(TokenType.IDENTIFIER));
      }

      String[] identifiers = (String[])l.toArray(new String[l.size()]);
      return (Java.AbstractCompilationUnit.ImportDeclaration)(isStatic ? new Java.AbstractCompilationUnit.SingleStaticImportDeclaration(loc, identifiers) : new Java.AbstractCompilationUnit.SingleTypeImportDeclaration(loc, identifiers));
   }

   public String[] parseQualifiedIdentifier() throws CompileException, IOException {
      List<String> l = new ArrayList();
      l.add(this.read(TokenType.IDENTIFIER));

      while(this.peek(".") && this.peekNextButOne().type == TokenType.IDENTIFIER) {
         this.read();
         l.add(this.read(TokenType.IDENTIFIER));
      }

      return (String[])l.toArray(new String[l.size()]);
   }

   public Java.PackageMemberTypeDeclaration parsePackageMemberTypeDeclaration() throws CompileException, IOException {
      return this.parsePackageMemberTypeDeclarationRest(this.doc(), this.parseModifiers());
   }

   private Java.PackageMemberTypeDeclaration parsePackageMemberTypeDeclarationRest(@Nullable String docComment, Java.Modifier[] modifiers) throws CompileException, IOException {
      switch (this.read("class", "enum", "interface", "@")) {
         case 0:
            if (docComment == null) {
               this.warning("CDCM", "Class doc comment missing");
            }

            return (Java.PackageMemberClassDeclaration)this.parseClassDeclarationRest(docComment, modifiers, Parser.ClassDeclarationContext.COMPILATION_UNIT);
         case 1:
            if (docComment == null) {
               this.warning("EDCM", "Enum doc comment missing");
            }

            return (Java.PackageMemberEnumDeclaration)this.parseEnumDeclarationRest(docComment, modifiers, Parser.ClassDeclarationContext.COMPILATION_UNIT);
         case 2:
            if (docComment == null) {
               this.warning("IDCM", "Interface doc comment missing");
            }

            return (Java.PackageMemberInterfaceDeclaration)this.parseInterfaceDeclarationRest(docComment, modifiers, Parser.InterfaceDeclarationContext.COMPILATION_UNIT);
         case 3:
            this.read("interface");
            if (docComment == null) {
               this.warning("ATDCM", "Annotation type doc comment missing");
            }

            return (Java.PackageMemberAnnotationTypeDeclaration)this.parseAnnotationTypeDeclarationRest(docComment, modifiers, Parser.InterfaceDeclarationContext.COMPILATION_UNIT);
         default:
            throw new IllegalStateException();
      }
   }

   public Java.Modifier[] parseModifiers() throws CompileException, IOException {
      List<Java.Modifier> result = new ArrayList();

      while(true) {
         Java.Modifier m = this.parseOptionalModifier();
         if (m == null) {
            return (Java.Modifier[])result.toArray(new Java.Modifier[result.size()]);
         }

         result.add(m);
      }
   }

   @Nullable
   public Java.Modifier parseOptionalModifier() throws CompileException, IOException {
      if (this.peek("@")) {
         return this.peekNextButOne().value.equals("interface") ? null : this.parseAnnotation();
      } else {
         int idx = this.peekRead(ACCESS_MODIFIER_KEYWORDS);
         return idx == -1 ? null : new Java.AccessModifier(ACCESS_MODIFIER_KEYWORDS[idx], this.location());
      }
   }

   private Java.Annotation parseAnnotation() throws CompileException, IOException {
      this.read("@");
      Java.ReferenceType type = new Java.ReferenceType(this.location(), new Java.Annotation[0], this.parseQualifiedIdentifier(), (Java.TypeArgument[])null);
      if (!this.peekRead("(")) {
         return new Java.MarkerAnnotation(type);
      } else if (this.peek(TokenType.IDENTIFIER) && this.peekNextButOne("=")) {
         Java.ElementValuePair[] elementValuePairs;
         if (this.peekRead(")")) {
            elementValuePairs = new Java.ElementValuePair[0];
         } else {
            List<Java.ElementValuePair> evps = new ArrayList();

            do {
               evps.add(this.parseElementValuePair());
            } while(this.read(",", ")") == 0);

            elementValuePairs = (Java.ElementValuePair[])evps.toArray(new Java.ElementValuePair[evps.size()]);
         }

         return new Java.NormalAnnotation(type, elementValuePairs);
      } else {
         Java.ElementValue elementValue = this.parseElementValue();
         this.read(")");
         return new Java.SingleElementAnnotation(type, elementValue);
      }
   }

   private Java.ElementValuePair parseElementValuePair() throws CompileException, IOException {
      String identifier = this.read(TokenType.IDENTIFIER);
      this.read("=");
      return new Java.ElementValuePair(identifier, this.parseElementValue());
   }

   private Java.ElementValue parseElementValue() throws CompileException, IOException {
      if (this.peek("@")) {
         return this.parseAnnotation();
      } else {
         return (Java.ElementValue)(this.peek("{") ? this.parseElementValueArrayInitializer() : this.parseConditionalAndExpression().toRvalueOrCompileException());
      }
   }

   private Java.ElementValue parseElementValueArrayInitializer() throws CompileException, IOException {
      this.read("{");
      Location loc = this.location();
      List<Java.ElementValue> evs = new ArrayList();

      while(!this.peekRead("}")) {
         if (!this.peekRead(",")) {
            evs.add(this.parseElementValue());
         }
      }

      return new Java.ElementValueArrayInitializer((Java.ElementValue[])evs.toArray(new Java.ElementValue[evs.size()]), loc);
   }

   public Java.NamedClassDeclaration parseClassDeclarationRest(@Nullable String docComment, Java.Modifier[] modifiers, ClassDeclarationContext context) throws CompileException, IOException {
      Location location = this.location();
      String className = this.read(TokenType.IDENTIFIER);
      this.verifyIdentifierIsConventionalClassOrInterfaceName(className, location);
      Java.TypeParameter[] typeParameters = this.parseTypeParametersOpt();
      Java.ReferenceType extendedType = null;
      if (this.peekRead("extends")) {
         extendedType = this.parseReferenceType();
      }

      Java.ReferenceType[] implementedTypes = new Java.ReferenceType[0];
      if (this.peekRead("implements")) {
         implementedTypes = this.parseReferenceTypeList();
      }

      Java.NamedClassDeclaration namedClassDeclaration;
      if (context == Parser.ClassDeclarationContext.COMPILATION_UNIT) {
         namedClassDeclaration = new Java.PackageMemberClassDeclaration(location, docComment, this.packageMemberClassModifiers(modifiers), className, typeParameters, extendedType, implementedTypes);
      } else if (context == Parser.ClassDeclarationContext.TYPE_DECLARATION) {
         namedClassDeclaration = new Java.MemberClassDeclaration(location, docComment, this.classModifiers(modifiers), className, typeParameters, extendedType, implementedTypes);
      } else {
         if (context != Parser.ClassDeclarationContext.BLOCK) {
            throw new InternalCompilerException("SNO: Class declaration in unexpected context " + context);
         }

         namedClassDeclaration = new Java.LocalClassDeclaration(location, docComment, this.classModifiers(modifiers), className, typeParameters, extendedType, implementedTypes);
      }

      this.parseClassBody(namedClassDeclaration);
      return namedClassDeclaration;
   }

   public Java.EnumDeclaration parseEnumDeclarationRest(@Nullable String docComment, Java.Modifier[] modifiers, ClassDeclarationContext context) throws CompileException, IOException {
      Location location = this.location();
      String enumName = this.read(TokenType.IDENTIFIER);
      this.verifyIdentifierIsConventionalClassOrInterfaceName(enumName, location);
      if (this.peekRead("<")) {
         throw this.compileException("Enum declaration must not have type parameters");
      } else if (this.peekRead("extends")) {
         throw this.compileException("Enum declaration must not have an EXTENDS clause");
      } else {
         Java.ReferenceType[] implementedTypes = new Java.ReferenceType[0];
         if (this.peekRead("implements")) {
            implementedTypes = this.parseReferenceTypeList();
         }

         Java.EnumDeclaration enumDeclaration;
         if (context == Parser.ClassDeclarationContext.COMPILATION_UNIT) {
            enumDeclaration = new Java.PackageMemberEnumDeclaration(location, docComment, this.classModifiers(modifiers), enumName, implementedTypes);
         } else {
            if (context != Parser.ClassDeclarationContext.TYPE_DECLARATION) {
               throw new InternalCompilerException("SNO: Enum declaration in unexpected context " + context);
            }

            enumDeclaration = new Java.MemberEnumDeclaration(location, docComment, this.classModifiers(modifiers), enumName, implementedTypes);
         }

         this.parseEnumBody(enumDeclaration);
         return enumDeclaration;
      }
   }

   public void parseClassBody(Java.AbstractClassDeclaration classDeclaration) throws CompileException, IOException {
      this.read("{");

      while(!this.peekRead("}")) {
         this.parseClassBodyDeclaration(classDeclaration);
      }

   }

   public void parseEnumBody(Java.EnumDeclaration enumDeclaration) throws CompileException, IOException {
      this.read("{");

      while(this.peek(";", "}") == -1) {
         enumDeclaration.addConstant(this.parseEnumConstant());
         if (!this.peekRead(",")) {
            break;
         }
      }

      while(!this.peekRead("}")) {
         this.parseClassBodyDeclaration((Java.AbstractClassDeclaration)enumDeclaration);
      }

   }

   public Java.EnumConstant parseEnumConstant() throws CompileException, IOException {
      Java.EnumConstant result = new Java.EnumConstant(this.location(), this.doc(), this.enumConstantModifiers(this.parseModifiers()), this.read(TokenType.IDENTIFIER), this.peek("(") ? this.parseArguments() : null);
      if (this.peek("{")) {
         this.parseClassBody(result);
      }

      return result;
   }

   public void parseClassBodyDeclaration(Java.AbstractClassDeclaration classDeclaration) throws CompileException, IOException {
      if (!this.peekRead(";")) {
         String docComment = this.doc();
         Java.Modifier[] modifiers = this.parseModifiers();
         if (this.peek("{")) {
            if (hasAccessModifierOtherThan(modifiers, "static")) {
               throw this.compileException("Only access flag \"static\" allowed on initializer");
            } else {
               Java.Initializer initializer = new Java.Initializer(this.location(), modifiers, this.parseBlock());
               classDeclaration.addInitializer(initializer);
            }
         } else if (this.peekRead("void")) {
            Location location = this.location();
            String name = this.read(TokenType.IDENTIFIER);
            classDeclaration.addDeclaredMethod(this.parseMethodDeclarationRest(docComment, this.methodModifiers(modifiers), (Java.TypeParameter[])null, new Java.PrimitiveType(location, Java.Primitive.VOID), name, false, Parser.MethodDeclarationContext.CLASS_DECLARATION));
         } else if (this.peekRead("class")) {
            if (docComment == null) {
               this.warning("MCDCM", "Member class doc comment missing");
            }

            classDeclaration.addMemberTypeDeclaration((Java.MemberTypeDeclaration)this.parseClassDeclarationRest(docComment, this.classModifiers(modifiers), Parser.ClassDeclarationContext.TYPE_DECLARATION));
         } else if (this.peekRead("enum")) {
            if (docComment == null) {
               this.warning("MEDCM", "Member enum doc comment missing");
            }

            classDeclaration.addMemberTypeDeclaration((Java.MemberTypeDeclaration)this.parseEnumDeclarationRest(docComment, this.classModifiers(modifiers), Parser.ClassDeclarationContext.TYPE_DECLARATION));
         } else if (this.peekRead("interface")) {
            if (docComment == null) {
               this.warning("MIDCM", "Member interface doc comment missing");
            }

            classDeclaration.addMemberTypeDeclaration((Java.MemberTypeDeclaration)this.parseInterfaceDeclarationRest(docComment, this.interfaceModifiers(modifiers), Parser.InterfaceDeclarationContext.NAMED_TYPE_DECLARATION));
         } else if (this.peek("@") && this.peekNextButOne("interface")) {
            this.read();
            this.read();
            if (docComment == null) {
               this.warning("MATDCM", "Member annotation type doc comment missing", this.location());
            }

            classDeclaration.addMemberTypeDeclaration((Java.MemberTypeDeclaration)this.parseInterfaceDeclarationRest(docComment, this.interfaceModifiers(modifiers), Parser.InterfaceDeclarationContext.NAMED_TYPE_DECLARATION));
         } else if (classDeclaration instanceof Java.NamedClassDeclaration && this.peek().value.equals(((Java.NamedClassDeclaration)classDeclaration).getName()) && this.peekNextButOne("(")) {
            if (docComment == null) {
               this.warning("CDCM", "Constructor doc comment missing", this.location());
            }

            classDeclaration.addConstructor(this.parseConstructorDeclarator(docComment, this.constructorModifiers(modifiers)));
         } else {
            Java.TypeParameter[] typeParameters = this.parseTypeParametersOpt();
            if (this.peekRead("void")) {
               String name = this.read(TokenType.IDENTIFIER);
               classDeclaration.addDeclaredMethod(this.parseMethodDeclarationRest(docComment, this.methodModifiers(modifiers), typeParameters, new Java.PrimitiveType(this.location(), Java.Primitive.VOID), name, false, Parser.MethodDeclarationContext.CLASS_DECLARATION));
            } else {
               Java.Type memberType = this.parseType();
               Location location = this.location();
               String memberName = this.read(TokenType.IDENTIFIER);
               if (this.peek("(")) {
                  classDeclaration.addDeclaredMethod(this.parseMethodDeclarationRest(docComment, this.methodModifiers(modifiers), typeParameters, memberType, memberName, false, Parser.MethodDeclarationContext.CLASS_DECLARATION));
               } else if (typeParameters != null) {
                  throw this.compileException("Type parameters not allowed on field declaration");
               } else {
                  if (docComment == null) {
                     this.warning("FDCM", "Field doc comment missing", this.location());
                  }

                  Java.FieldDeclaration fd = new Java.FieldDeclaration(location, docComment, this.fieldModifiers(modifiers), memberType, this.parseFieldDeclarationRest(memberName));
                  this.read(";");
                  classDeclaration.addFieldDeclaration(fd);
               }
            }
         }
      }
   }

   public Java.InterfaceDeclaration parseInterfaceDeclarationRest(@Nullable String docComment, Java.Modifier[] modifiers, InterfaceDeclarationContext context) throws CompileException, IOException {
      Location location = this.location();
      String interfaceName = this.read(TokenType.IDENTIFIER);
      this.verifyIdentifierIsConventionalClassOrInterfaceName(interfaceName, location);
      Java.TypeParameter[] typeParameters = this.parseTypeParametersOpt();
      Java.ReferenceType[] extendedTypes = new Java.ReferenceType[0];
      if (this.peekRead("extends")) {
         extendedTypes = this.parseReferenceTypeList();
      }

      Java.InterfaceDeclaration id;
      if (context == Parser.InterfaceDeclarationContext.COMPILATION_UNIT) {
         id = new Java.PackageMemberInterfaceDeclaration(location, docComment, this.packageMemberInterfaceModifiers(modifiers), interfaceName, typeParameters, extendedTypes);
      } else {
         if (context != Parser.InterfaceDeclarationContext.NAMED_TYPE_DECLARATION) {
            throw new InternalCompilerException("SNO: Interface declaration in unexpected context " + context);
         }

         id = new Java.MemberInterfaceDeclaration(location, docComment, this.interfaceModifiers(modifiers), interfaceName, typeParameters, extendedTypes);
      }

      this.parseInterfaceBody(id);
      return id;
   }

   public Java.AnnotationTypeDeclaration parseAnnotationTypeDeclarationRest(@Nullable String docComment, Java.Modifier[] modifiers, InterfaceDeclarationContext context) throws CompileException, IOException {
      Location location = this.location();
      String annotationTypeName = this.read(TokenType.IDENTIFIER);
      this.verifyIdentifierIsConventionalClassOrInterfaceName(annotationTypeName, location);
      Java.AnnotationTypeDeclaration atd;
      if (context == Parser.InterfaceDeclarationContext.COMPILATION_UNIT) {
         atd = new Java.PackageMemberAnnotationTypeDeclaration(location, docComment, this.packageMemberInterfaceModifiers(modifiers), annotationTypeName);
      } else {
         if (context != Parser.InterfaceDeclarationContext.NAMED_TYPE_DECLARATION) {
            throw new InternalCompilerException("SNO: Annotation type declaration in unexpected context " + context);
         }

         atd = new Java.MemberAnnotationTypeDeclaration(location, docComment, this.interfaceModifiers(modifiers), annotationTypeName);
      }

      this.parseInterfaceBody((Java.InterfaceDeclaration)atd);
      return atd;
   }

   public void parseInterfaceBody(Java.InterfaceDeclaration interfaceDeclaration) throws CompileException, IOException {
      this.read("{");

      while(!this.peekRead("}")) {
         if (!this.peekRead(";")) {
            String docComment = this.doc();
            Java.Modifier[] modifiers = this.parseModifiers();
            if (this.peekRead("void")) {
               interfaceDeclaration.addDeclaredMethod(this.parseMethodDeclarationRest(docComment, modifiers, (Java.TypeParameter[])null, new Java.PrimitiveType(this.location(), Java.Primitive.VOID), this.read(TokenType.IDENTIFIER), true, interfaceDeclaration instanceof Java.AnnotationTypeDeclaration ? Parser.MethodDeclarationContext.ANNOTATION_TYPE_DECLARATION : Parser.MethodDeclarationContext.INTERFACE_DECLARATION));
            } else if (this.peekRead("class")) {
               if (docComment == null) {
                  this.warning("MCDCM", "Member class doc comment missing", this.location());
               }

               if (hasAccessModifier(modifiers, "default")) {
                  throw this.compileException("Modifier \"default\" not allowed on member class declaration");
               }

               interfaceDeclaration.addMemberTypeDeclaration((Java.MemberTypeDeclaration)this.parseClassDeclarationRest(docComment, this.classModifiers(modifiers), Parser.ClassDeclarationContext.TYPE_DECLARATION));
            } else if (this.peekRead("enum")) {
               if (docComment == null) {
                  this.warning("MEDCM", "Member enum doc comment missing", this.location());
               }

               if (hasAccessModifier(modifiers, "default")) {
                  throw this.compileException("Modifier \"default\" not allowed on member enum declaration");
               }

               interfaceDeclaration.addMemberTypeDeclaration((Java.MemberTypeDeclaration)this.parseClassDeclarationRest(docComment, this.classModifiers(modifiers), Parser.ClassDeclarationContext.TYPE_DECLARATION));
            } else if (this.peekRead("interface")) {
               if (docComment == null) {
                  this.warning("MIDCM", "Member interface doc comment missing", this.location());
               }

               if (hasAccessModifier(modifiers, "default")) {
                  throw this.compileException("Modifier \"default\" not allowed on member interface declaration");
               }

               interfaceDeclaration.addMemberTypeDeclaration((Java.MemberTypeDeclaration)this.parseInterfaceDeclarationRest(docComment, this.interfaceModifiers(modifiers), Parser.InterfaceDeclarationContext.NAMED_TYPE_DECLARATION));
            } else if (this.peek("@") && this.peekNextButOne("interface")) {
               this.read();
               this.read();
               if (docComment == null) {
                  this.warning("MATDCM", "Member annotation type doc comment missing", this.location());
               }

               if (hasAccessModifier(modifiers, "default")) {
                  throw this.compileException("Modifier \"default\" not allowed on member annotation type declaration");
               }

               interfaceDeclaration.addMemberTypeDeclaration((Java.MemberTypeDeclaration)this.parseInterfaceDeclarationRest(docComment, this.interfaceModifiers(modifiers), Parser.InterfaceDeclarationContext.NAMED_TYPE_DECLARATION));
            } else {
               Java.TypeParameter[] typeParameters = this.parseTypeParametersOpt();
               if (this.peekRead("void")) {
                  Location location = this.location();
                  String name = this.read(TokenType.IDENTIFIER);
                  interfaceDeclaration.addDeclaredMethod(this.parseMethodDeclarationRest(docComment, modifiers, typeParameters, new Java.PrimitiveType(location, Java.Primitive.VOID), name, true, interfaceDeclaration instanceof Java.AnnotationTypeDeclaration ? Parser.MethodDeclarationContext.ANNOTATION_TYPE_DECLARATION : Parser.MethodDeclarationContext.INTERFACE_DECLARATION));
               } else {
                  Java.Type memberType = this.parseType();
                  String memberName = this.read(TokenType.IDENTIFIER);
                  Location location = this.location();
                  if (this.peek("(")) {
                     if (this.getSourceVersion() < 8 && hasAccessModifier(modifiers, "static")) {
                        throw this.compileException("Static interface methods only available for source version 8+");
                     }

                     interfaceDeclaration.addDeclaredMethod(this.parseMethodDeclarationRest(docComment, modifiers, typeParameters, memberType, memberName, true, interfaceDeclaration instanceof Java.AnnotationTypeDeclaration ? Parser.MethodDeclarationContext.ANNOTATION_TYPE_DECLARATION : Parser.MethodDeclarationContext.INTERFACE_DECLARATION));
                  } else {
                     if (typeParameters != null) {
                        throw this.compileException("Type parameters not allowed with constant declaration");
                     }

                     if (docComment == null) {
                        this.warning("CDCM", "Constant doc comment missing", this.location());
                     }

                     if (hasAccessModifier(modifiers, "default")) {
                        throw this.compileException("Modifier \"default\" not allowed for constants");
                     }

                     Java.FieldDeclaration cd = new Java.FieldDeclaration(location, docComment, this.constantModifiers(modifiers), memberType, this.parseFieldDeclarationRest(memberName));
                     interfaceDeclaration.addConstantDeclaration(cd);
                  }
               }
            }
         }
      }

   }

   public Java.ConstructorDeclarator parseConstructorDeclarator(@Nullable String docComment, Java.Modifier[] modifiers) throws CompileException, IOException {
      this.read(TokenType.IDENTIFIER);
      Java.FunctionDeclarator.FormalParameters formalParameters = this.parseFormalParameters();
      Java.ReferenceType[] thrownExceptions;
      if (this.peekRead("throws")) {
         thrownExceptions = this.parseReferenceTypeList();
      } else {
         thrownExceptions = new Java.ReferenceType[0];
      }

      Location location = this.location();
      this.read("{");
      Java.ConstructorInvocation constructorInvocation = null;
      List<Java.BlockStatement> statements = new ArrayList();
      if (this.peek("this", "super", "new", "void", "byte", "char", "short", "int", "long", "float", "double", "boolean") != -1 || this.peekLiteral() || this.peek(TokenType.IDENTIFIER)) {
         Java.Atom a = this.parseExpressionOrType();
         if (a instanceof Java.ConstructorInvocation) {
            this.read(";");
            constructorInvocation = (Java.ConstructorInvocation)a;
         } else {
            Java.Statement s;
            if (this.peek(TokenType.IDENTIFIER)) {
               Java.Type variableType = a.toTypeOrCompileException();
               s = new Java.LocalVariableDeclarationStatement(a.getLocation(), new Java.Modifier[0], variableType, this.parseVariableDeclarators());
               this.read(";");
            } else {
               s = new Java.ExpressionStatement(a.toRvalueOrCompileException());
               this.read(";");
            }

            statements.add(s);
         }
      }

      statements.addAll(this.parseBlockStatements());
      this.read("}");
      return new Java.ConstructorDeclarator(location, docComment, this.constructorModifiers(modifiers), formalParameters, thrownExceptions, constructorInvocation, statements);
   }

   public Java.MethodDeclarator parseMethodDeclaration() throws CompileException, IOException {
      return this.parseMethodDeclaration(false, Parser.MethodDeclarationContext.CLASS_DECLARATION);
   }

   public Java.MethodDeclarator parseMethodDeclaration(boolean allowDefaultClause, MethodDeclarationContext context) throws CompileException, IOException {
      return this.parseMethodDeclarationRest(this.doc(), this.parseModifiers(), this.parseTypeParametersOpt(), this.parseVoidOrType(), this.read(TokenType.IDENTIFIER), allowDefaultClause, context);
   }

   public Java.Type parseVoidOrType() throws CompileException, IOException {
      return (Java.Type)(this.peekRead("void") ? new Java.PrimitiveType(this.location(), Java.Primitive.VOID) : this.parseType());
   }

   public Java.MethodDeclarator parseMethodDeclarationRest(@Nullable String docComment, Java.Modifier[] modifiers, @Nullable Java.TypeParameter[] typeParameters, Java.Type type, String name, boolean allowDefaultClause, MethodDeclarationContext context) throws CompileException, IOException {
      Location location = this.location();
      if (docComment == null) {
         this.warning("MDCM", "Method doc comment missing", location);
      }

      if (this.getSourceVersion() < 8 && hasAccessModifier(modifiers, "default")) {
         throw this.compileException("Default interface methods only available for source version 8+");
      } else {
         this.verifyIdentifierIsConventionalMethodName(name, location);
         Java.FunctionDeclarator.FormalParameters formalParameters = this.parseFormalParameters();

         for(int i = this.parseBracketsOpt(); i > 0; --i) {
            type = new Java.ArrayType(type);
         }

         Java.ReferenceType[] thrownExceptions;
         if (this.peekRead("throws")) {
            thrownExceptions = this.parseReferenceTypeList();
         } else {
            thrownExceptions = new Java.ReferenceType[0];
         }

         Java.ElementValue defaultValue = allowDefaultClause && this.peekRead("default") ? this.parseElementValue() : null;
         List<Java.BlockStatement> statements;
         if (this.peekRead(";")) {
            statements = null;
         } else {
            if (hasAccessModifier(modifiers, "abstract", "native")) {
               throw this.compileException("Abstract or native method must not have a body");
            }

            this.read("{");
            statements = this.parseBlockStatements();
            this.read("}");
         }

         return new Java.MethodDeclarator(location, docComment, context == Parser.MethodDeclarationContext.ANNOTATION_TYPE_DECLARATION ? this.annotationTypeElementModifiers(modifiers) : (context == Parser.MethodDeclarationContext.CLASS_DECLARATION ? this.methodModifiers(modifiers) : (context == Parser.MethodDeclarationContext.INTERFACE_DECLARATION ? this.interfaceMethodModifiers(modifiers) : new Java.Modifier[1])), typeParameters, type, name, formalParameters, thrownExceptions, defaultValue, statements);
      }
   }

   private int getSourceVersion() {
      if (this.sourceVersion == -1) {
         this.sourceVersion = 11;
      }

      return this.sourceVersion;
   }

   public Java.ArrayInitializerOrRvalue parseVariableInitializer() throws CompileException, IOException {
      return (Java.ArrayInitializerOrRvalue)(this.peek("{") ? this.parseArrayInitializer() : this.parseExpression());
   }

   public Java.ArrayInitializer parseArrayInitializer() throws CompileException, IOException {
      this.read("{");
      Location location = this.location();
      List<Java.ArrayInitializerOrRvalue> l = new ArrayList();

      while(!this.peekRead("}")) {
         l.add(this.parseVariableInitializer());
         if (this.peekRead("}")) {
            break;
         }

         this.read(",");
      }

      return new Java.ArrayInitializer(location, (Java.ArrayInitializerOrRvalue[])l.toArray(new Java.ArrayInitializerOrRvalue[l.size()]));
   }

   public Java.FunctionDeclarator.FormalParameters parseFormalParameters() throws CompileException, IOException {
      this.read("(");
      if (this.peekRead(")")) {
         return new Java.FunctionDeclarator.FormalParameters(this.location());
      } else {
         Java.FunctionDeclarator.FormalParameters result = this.parseFormalParameterList();
         this.read(")");
         return result;
      }
   }

   public Java.FunctionDeclarator.FormalParameters parseFormalParameterList() throws CompileException, IOException {
      List<Java.FunctionDeclarator.FormalParameter> l = new ArrayList();
      boolean[] hasEllipsis = new boolean[1];

      while(!hasEllipsis[0]) {
         l.add(this.parseFormalParameter(hasEllipsis));
         if (!this.peekRead(",")) {
            return new Java.FunctionDeclarator.FormalParameters(this.location(), (Java.FunctionDeclarator.FormalParameter[])l.toArray(new Java.FunctionDeclarator.FormalParameter[l.size()]), hasEllipsis[0]);
         }
      }

      throw this.compileException("Only the last parameter may have an ellipsis");
   }

   public Java.FunctionDeclarator.FormalParameters parseFormalParameterListRest(Java.Type firstParameterType) throws CompileException, IOException {
      List<Java.FunctionDeclarator.FormalParameter> l = new ArrayList();
      boolean[] hasEllipsis = new boolean[1];
      l.add(this.parseFormalParameterRest(new Java.Modifier[0], firstParameterType, hasEllipsis));

      while(this.peekRead(",")) {
         if (hasEllipsis[0]) {
            throw this.compileException("Only the last parameter may have an ellipsis");
         }

         l.add(this.parseFormalParameter(hasEllipsis));
      }

      return new Java.FunctionDeclarator.FormalParameters(this.location(), (Java.FunctionDeclarator.FormalParameter[])l.toArray(new Java.FunctionDeclarator.FormalParameter[l.size()]), hasEllipsis[0]);
   }

   public Java.FunctionDeclarator.FormalParameter parseFormalParameter(boolean[] hasEllipsis) throws CompileException, IOException {
      Java.Modifier[] modifiers = this.parseModifiers();
      if (hasAccessModifier(modifiers, "default")) {
         throw this.compileException("Modifier \"default\" not allowed on formal parameters");
      } else {
         return this.parseFormalParameterRest(modifiers, this.parseType(), hasEllipsis);
      }
   }

   public Java.FunctionDeclarator.FormalParameter parseFormalParameterRest(Java.Modifier[] modifiers, Java.Type type, boolean[] hasEllipsis) throws CompileException, IOException {
      if (this.peekRead(".")) {
         this.read(".");
         this.read(".");
         hasEllipsis[0] = true;
      }

      Location location = this.location();
      String name = this.read(TokenType.IDENTIFIER);
      this.verifyIdentifierIsConventionalLocalVariableOrParameterName(name, location);

      for(int i = this.parseBracketsOpt(); i > 0; --i) {
         type = new Java.ArrayType(type);
      }

      return new Java.FunctionDeclarator.FormalParameter(location, modifiers, type, name);
   }

   public Java.CatchParameter parseCatchParameter() throws CompileException, IOException {
      Java.Modifier[] modifiers = this.parseModifiers();
      this.variableModifiers(modifiers);
      List<Java.ReferenceType> catchTypes = new ArrayList();
      catchTypes.add(this.parseReferenceType());

      while(this.peekRead("|")) {
         catchTypes.add(this.parseReferenceType());
      }

      Location location = this.location();
      String name = this.read(TokenType.IDENTIFIER);
      this.verifyIdentifierIsConventionalLocalVariableOrParameterName(name, location);
      return new Java.CatchParameter(location, hasAccessModifier(modifiers, "final"), (Java.ReferenceType[])catchTypes.toArray(new Java.ReferenceType[catchTypes.size()]), name);
   }

   int parseBracketsOpt() throws CompileException, IOException {
      int res;
      for(res = 0; this.peek("[") && this.peekNextButOne("]"); ++res) {
         this.read();
         this.read();
      }

      return res;
   }

   public Java.Block parseMethodBody() throws CompileException, IOException {
      return this.parseBlock();
   }

   public Java.Block parseBlock() throws CompileException, IOException {
      this.read("{");
      Java.Block block = new Java.Block(this.location());
      block.addStatements(this.parseBlockStatements());
      this.read("}");
      return block;
   }

   public List parseBlockStatements() throws CompileException, IOException {
      List<Java.BlockStatement> l = new ArrayList();

      while(!this.peek("}") && !this.peek("case") && !this.peek("default") && !this.peek(TokenType.END_OF_INPUT)) {
         l.add(this.parseBlockStatement());
      }

      return l;
   }

   public Java.BlockStatement parseBlockStatement() throws CompileException, IOException {
      if ((!this.peek(TokenType.IDENTIFIER) || !this.peekNextButOne(":")) && this.peek("if", "for", "while", "do", "try", "switch", "synchronized", "return", "throw", "break", "continue", "assert") == -1 && this.peek("{", ";") == -1) {
         if (this.peekRead("class")) {
            String docComment = this.doc();
            if (docComment == null) {
               this.warning("LCDCM", "Local class doc comment missing", this.location());
            }

            Java.LocalClassDeclaration lcd = (Java.LocalClassDeclaration)this.parseClassDeclarationRest(docComment, new Java.Modifier[0], Parser.ClassDeclarationContext.BLOCK);
            return new Java.LocalClassDeclarationStatement(lcd);
         } else if (this.peek("final", "@") != -1) {
            Java.LocalVariableDeclarationStatement lvds = new Java.LocalVariableDeclarationStatement(this.location(), this.variableModifiers(this.parseModifiers()), this.parseType(), this.parseVariableDeclarators());
            this.read(";");
            return lvds;
         } else {
            Java.Atom a = this.parseExpressionOrType();
            if (this.peekRead(";")) {
               return new Java.ExpressionStatement(a.toRvalueOrCompileException());
            } else {
               Java.Type variableType = a.toTypeOrCompileException();

               for(int i = this.parseBracketsOpt(); i > 0; --i) {
                  variableType = new Java.ArrayType(variableType);
               }

               Java.LocalVariableDeclarationStatement lvds = new Java.LocalVariableDeclarationStatement(a.getLocation(), new Java.Modifier[0], variableType, this.parseVariableDeclarators());
               this.read(";");
               return lvds;
            }
         }
      } else {
         return this.parseStatement();
      }
   }

   public Java.VariableDeclarator[] parseVariableDeclarators() throws CompileException, IOException {
      List<Java.VariableDeclarator> l = new ArrayList();

      do {
         Java.VariableDeclarator vd = this.parseVariableDeclarator();
         this.verifyIdentifierIsConventionalLocalVariableOrParameterName(vd.name, vd.getLocation());
         l.add(vd);
      } while(this.peekRead(","));

      return (Java.VariableDeclarator[])l.toArray(new Java.VariableDeclarator[l.size()]);
   }

   public Java.VariableDeclarator[] parseFieldDeclarationRest(String name) throws CompileException, IOException {
      List<Java.VariableDeclarator> l = new ArrayList();
      Java.VariableDeclarator vd = this.parseVariableDeclaratorRest(name);
      this.verifyIdentifierIsConventionalFieldName(vd.name, vd.getLocation());
      l.add(vd);

      while(this.peekRead(",")) {
         vd = this.parseVariableDeclarator();
         this.verifyIdentifierIsConventionalFieldName(vd.name, vd.getLocation());
         l.add(vd);
      }

      return (Java.VariableDeclarator[])l.toArray(new Java.VariableDeclarator[l.size()]);
   }

   public Java.VariableDeclarator parseVariableDeclarator() throws CompileException, IOException {
      return this.parseVariableDeclaratorRest(this.read(TokenType.IDENTIFIER));
   }

   public Java.VariableDeclarator parseVariableDeclaratorRest(String name) throws CompileException, IOException {
      Location loc = this.location();
      int brackets = this.parseBracketsOpt();
      Java.ArrayInitializerOrRvalue initializer = this.peekRead("=") ? this.parseVariableInitializer() : null;
      return new Java.VariableDeclarator(loc, name, brackets, initializer);
   }

   public Java.Statement parseStatement() throws CompileException, IOException {
      if (this.peek(TokenType.IDENTIFIER) && this.peekNextButOne(":")) {
         return this.parseLabeledStatement();
      } else {
         Java.Statement stmt = (Java.Statement)(this.peek("{") ? this.parseBlock() : (this.peek("if") ? this.parseIfStatement() : (this.peek("for") ? this.parseForStatement() : (this.peek("while") ? this.parseWhileStatement() : (this.peek("do") ? this.parseDoStatement() : (this.peek("try") ? this.parseTryStatement() : (this.peek("switch") ? this.parseSwitchStatement() : (this.peek("synchronized") ? this.parseSynchronizedStatement() : (this.peek("return") ? this.parseReturnStatement() : (this.peek("throw") ? this.parseThrowStatement() : (this.peek("break") ? this.parseBreakStatement() : (this.peek("continue") ? this.parseContinueStatement() : (this.peek("assert") ? this.parseAssertStatement() : (this.peek(";") ? this.parseEmptyStatement() : this.parseExpressionStatement()))))))))))))));
         return stmt;
      }
   }

   public Java.Statement parseLabeledStatement() throws CompileException, IOException {
      String label = this.read(TokenType.IDENTIFIER);
      this.read(":");
      return new Java.LabeledStatement(this.location(), label, this.parseStatement());
   }

   public Java.Statement parseIfStatement() throws CompileException, IOException {
      this.read("if");
      Location location = this.location();
      this.read("(");
      Java.Rvalue condition = this.parseExpression();
      this.read(")");
      Java.Statement thenStatement = this.parseStatement();
      Java.Statement elseStatement = this.peekRead("else") ? this.parseStatement() : null;
      return new Java.IfStatement(location, condition, thenStatement, elseStatement);
   }

   public Java.Statement parseForStatement() throws CompileException, IOException {
      this.read("for");
      Location forLocation = this.location();
      this.read("(");
      Java.BlockStatement init = null;
      if (!this.peek(";")) {
         if (this.peek("final", "@", "byte", "short", "char", "int", "long", "float", "double", "boolean") != -1) {
            Java.Modifier[] modifiers = this.parseModifiers();
            Java.Type type = this.parseType();
            if (this.peek(TokenType.IDENTIFIER) && this.peekNextButOne(":")) {
               String name = this.read(TokenType.IDENTIFIER);
               Location nameLocation = this.location();
               this.read(":");
               Java.Rvalue expression = this.parseExpression();
               this.read(")");
               return new Java.ForEachStatement(forLocation, new Java.FunctionDeclarator.FormalParameter(nameLocation, modifiers, type, name), expression, this.parseStatement());
            }

            init = new Java.LocalVariableDeclarationStatement(this.location(), this.variableModifiers(modifiers), type, this.parseVariableDeclarators());
         } else {
            Java.Atom a = this.parseExpressionOrType();
            if (this.peek(TokenType.IDENTIFIER)) {
               if (this.peekNextButOne(":")) {
                  String name = this.read(TokenType.IDENTIFIER);
                  Location nameLocation = this.location();
                  this.read(":");
                  Java.Rvalue expression = this.parseExpression();
                  this.read(")");
                  return new Java.ForEachStatement(forLocation, new Java.FunctionDeclarator.FormalParameter(nameLocation, new Java.Modifier[0], a.toTypeOrCompileException(), name), expression, this.parseStatement());
               }

               init = new Java.LocalVariableDeclarationStatement(this.location(), new Java.Modifier[0], a.toTypeOrCompileException(), this.parseVariableDeclarators());
            } else if (!this.peekRead(",")) {
               init = new Java.ExpressionStatement(a.toRvalueOrCompileException());
            } else {
               List<Java.BlockStatement> l = new ArrayList();
               l.add(new Java.ExpressionStatement(a.toRvalueOrCompileException()));

               do {
                  l.add(new Java.ExpressionStatement(this.parseExpression()));
               } while(this.peekRead(","));

               Java.Block b = new Java.Block(a.getLocation());
               b.addStatements(l);
               init = b;
            }
         }
      }

      this.read(";");
      Java.Rvalue condition = this.peek(";") ? null : this.parseExpression();
      this.read(";");
      Java.Rvalue[] update = null;
      if (!this.peek(")")) {
         update = this.parseExpressionList();
      }

      this.read(")");
      return new Java.ForStatement(forLocation, init, condition, update, this.parseStatement());
   }

   public Java.Statement parseWhileStatement() throws CompileException, IOException {
      this.read("while");
      Location location = this.location();
      this.read("(");
      Java.Rvalue condition = this.parseExpression();
      this.read(")");
      return new Java.WhileStatement(location, condition, this.parseStatement());
   }

   public Java.Statement parseDoStatement() throws CompileException, IOException {
      this.read("do");
      Location location = this.location();
      Java.Statement body = this.parseStatement();
      this.read("while");
      this.read("(");
      Java.Rvalue condition = this.parseExpression();
      this.read(")");
      this.read(";");
      return new Java.DoStatement(location, body, condition);
   }

   public Java.Statement parseTryStatement() throws CompileException, IOException {
      this.read("try");
      Location location = this.location();
      List<Java.TryStatement.Resource> resources = new ArrayList();
      if (this.peekRead("(")) {
         resources.add(this.parseResource());

         label47:
         while(true) {
            switch (this.read(";", ")")) {
               case 0:
                  if (!this.peekRead(")")) {
                     resources.add(this.parseResource());
                     break;
                  }
               case 1:
                  break label47;
               default:
                  throw new AssertionError();
            }
         }
      }

      Java.Block body = this.parseBlock();
      List<Java.CatchClause> ccs = new ArrayList();

      while(this.peekRead("catch")) {
         Location loc = this.location();
         this.read("(");
         Java.CatchParameter catchParameter = this.parseCatchParameter();
         this.read(")");
         ccs.add(new Java.CatchClause(loc, catchParameter, this.parseBlock()));
      }

      Java.Block finallY = this.peekRead("finally") ? this.parseBlock() : null;
      if (resources.isEmpty() && ccs.isEmpty() && finallY == null) {
         throw this.compileException("\"try\" statement must have at least one resource, \"catch\" clause or \"finally\" clause");
      } else {
         return new Java.TryStatement(location, resources, body, ccs, finallY);
      }
   }

   private Java.TryStatement.Resource parseResource() throws CompileException, IOException {
      Location loc = this.location();
      Java.Modifier[] modifiers = this.parseModifiers();
      Java.Atom a = this.parseExpressionOrType();
      if (modifiers.length <= 0 && !this.peek(TokenType.IDENTIFIER)) {
         Java.Rvalue rv = a.toRvalueOrCompileException();
         if (!(rv instanceof Java.FieldAccess)) {
            this.compileException("Rvalue " + rv.getClass().getSimpleName() + " disallowed as a resource");
         }

         return new Java.TryStatement.VariableAccessResource(loc, rv);
      } else if (hasAccessModifier(modifiers, "default")) {
         throw this.compileException("Modifier \"default\" not allowed on resource");
      } else {
         return new Java.TryStatement.LocalVariableDeclaratorResource(loc, this.variableModifiers(modifiers), a.toTypeOrCompileException(), this.parseVariableDeclarator());
      }
   }

   public Java.Statement parseSwitchStatement() throws CompileException, IOException {
      this.read("switch");
      Location location = this.location();
      this.read("(");
      Java.Rvalue condition = this.parseExpression();
      this.read(")");
      this.read("{");
      List<Java.SwitchStatement.SwitchBlockStatementGroup> sbsgs = new ArrayList();

      while(!this.peekRead("}")) {
         Location location2 = this.location();
         boolean hasDefaultLabel = false;
         List<Java.Rvalue> caseLabels = new ArrayList();

         do {
            if (this.peekRead("case")) {
               caseLabels.add(this.parseExpression());
            } else {
               if (!this.peekRead("default")) {
                  throw this.compileException("\"case\" or \"default\" expected");
               }

               if (hasDefaultLabel) {
                  throw this.compileException("Duplicate \"default\" label");
               }

               hasDefaultLabel = true;
            }

            this.read(":");
         } while(this.peek("case", "default") != -1);

         Java.SwitchStatement.SwitchBlockStatementGroup sbsg = new Java.SwitchStatement.SwitchBlockStatementGroup(location2, caseLabels, hasDefaultLabel, this.parseBlockStatements());
         sbsgs.add(sbsg);
      }

      return new Java.SwitchStatement(location, condition, sbsgs);
   }

   public Java.Statement parseSynchronizedStatement() throws CompileException, IOException {
      this.read("synchronized");
      Location location = this.location();
      this.read("(");
      Java.Rvalue expression = this.parseExpression();
      this.read(")");
      return new Java.SynchronizedStatement(location, expression, this.parseBlock());
   }

   public Java.Statement parseReturnStatement() throws CompileException, IOException {
      this.read("return");
      Location location = this.location();
      Java.Rvalue returnValue = this.peek(";") ? null : this.parseExpression();
      this.read(";");
      return new Java.ReturnStatement(location, returnValue);
   }

   public Java.Statement parseThrowStatement() throws CompileException, IOException {
      this.read("throw");
      Location location = this.location();
      Java.Rvalue expression = this.parseExpression();
      this.read(";");
      return new Java.ThrowStatement(location, expression);
   }

   public Java.Statement parseBreakStatement() throws CompileException, IOException {
      this.read("break");
      Location location = this.location();
      String label = null;
      if (this.peek(TokenType.IDENTIFIER)) {
         label = this.read(TokenType.IDENTIFIER);
      }

      this.read(";");
      return new Java.BreakStatement(location, label);
   }

   public Java.Statement parseContinueStatement() throws CompileException, IOException {
      this.read("continue");
      Location location = this.location();
      String label = this.peekRead(TokenType.IDENTIFIER);
      this.read(";");
      return new Java.ContinueStatement(location, label);
   }

   public Java.Statement parseAssertStatement() throws CompileException, IOException {
      this.read("assert");
      Location loc = this.location();
      Java.Rvalue expression1 = this.parseExpression();
      Java.Rvalue expression2 = this.peekRead(":") ? this.parseExpression() : null;
      this.read(";");
      return new Java.AssertStatement(loc, expression1, expression2);
   }

   public Java.Statement parseEmptyStatement() throws CompileException, IOException {
      this.read(";");
      Location location = this.location();
      return new Java.EmptyStatement(location);
   }

   public Java.Rvalue[] parseExpressionList() throws CompileException, IOException {
      List<Java.Rvalue> l = new ArrayList();

      do {
         l.add(this.parseExpression());
      } while(this.peekRead(","));

      return (Java.Rvalue[])l.toArray(new Java.Rvalue[l.size()]);
   }

   public Java.Type parseType() throws CompileException, IOException {
      Java.Type res;
      switch (this.peekRead("byte", "short", "char", "int", "long", "float", "double", "boolean")) {
         case -1:
            res = this.parseReferenceType();
            break;
         case 0:
            res = new Java.PrimitiveType(this.location(), Java.Primitive.BYTE);
            break;
         case 1:
            res = new Java.PrimitiveType(this.location(), Java.Primitive.SHORT);
            break;
         case 2:
            res = new Java.PrimitiveType(this.location(), Java.Primitive.CHAR);
            break;
         case 3:
            res = new Java.PrimitiveType(this.location(), Java.Primitive.INT);
            break;
         case 4:
            res = new Java.PrimitiveType(this.location(), Java.Primitive.LONG);
            break;
         case 5:
            res = new Java.PrimitiveType(this.location(), Java.Primitive.FLOAT);
            break;
         case 6:
            res = new Java.PrimitiveType(this.location(), Java.Primitive.DOUBLE);
            break;
         case 7:
            res = new Java.PrimitiveType(this.location(), Java.Primitive.BOOLEAN);
            break;
         default:
            throw new AssertionError();
      }

      for(int i = this.parseBracketsOpt(); i > 0; --i) {
         res = new Java.ArrayType(res);
      }

      return res;
   }

   public Java.ReferenceType parseReferenceType() throws CompileException, IOException {
      List<Java.Annotation> annotations = new ArrayList();

      while(this.peek("@")) {
         annotations.add(this.parseAnnotation());
      }

      return new Java.ReferenceType(this.location(), (Java.Annotation[])annotations.toArray(new Java.Annotation[annotations.size()]), this.parseQualifiedIdentifier(), this.parseTypeArgumentsOpt());
   }

   @Nullable
   private Java.TypeParameter[] parseTypeParametersOpt() throws CompileException, IOException {
      if (!this.peekRead("<")) {
         return null;
      } else {
         List<Java.TypeParameter> l = new ArrayList();
         l.add(this.parseTypeParameter());

         while(this.read(",", ">") == 0) {
            l.add(this.parseTypeParameter());
         }

         return (Java.TypeParameter[])l.toArray(new Java.TypeParameter[l.size()]);
      }
   }

   private Java.TypeParameter parseTypeParameter() throws CompileException, IOException {
      String name = this.read(TokenType.IDENTIFIER);
      if (!this.peekRead("extends")) {
         return new Java.TypeParameter(name, (Java.ReferenceType[])null);
      } else {
         List<Java.ReferenceType> bound = new ArrayList();
         bound.add(this.parseReferenceType());

         while(this.peekRead("&")) {
            this.parseReferenceType();
         }

         return new Java.TypeParameter(name, (Java.ReferenceType[])bound.toArray(new Java.ReferenceType[bound.size()]));
      }
   }

   @Nullable
   private Java.TypeArgument[] parseTypeArgumentsOpt() throws CompileException, IOException {
      if (!this.peekRead("<")) {
         return null;
      } else if (this.peekRead(">")) {
         return new Java.TypeArgument[0];
      } else {
         List<Java.TypeArgument> typeArguments = new ArrayList();
         typeArguments.add(this.parseTypeArgument());

         while(this.read(">", ",") == 1) {
            typeArguments.add(this.parseTypeArgument());
         }

         return (Java.TypeArgument[])typeArguments.toArray(new Java.TypeArgument[typeArguments.size()]);
      }
   }

   private Java.TypeArgument parseTypeArgument() throws CompileException, IOException {
      if (this.peekRead("?")) {
         return this.peekRead("extends") ? new Java.Wildcard(1, this.parseReferenceType()) : (this.peekRead("super") ? new Java.Wildcard(2, this.parseReferenceType()) : new Java.Wildcard());
      } else {
         Java.Type t = this.parseType();

         for(int i = this.parseBracketsOpt(); i > 0; --i) {
            t = new Java.ArrayType(t);
         }

         if (!(t instanceof Java.TypeArgument)) {
            throw this.compileException("'" + t + "' is not a valid type argument");
         } else {
            return (Java.TypeArgument)t;
         }
      }
   }

   public Java.ReferenceType[] parseReferenceTypeList() throws CompileException, IOException {
      List<Java.ReferenceType> l = new ArrayList();
      l.add(this.parseReferenceType());

      while(this.peekRead(",")) {
         l.add(this.parseReferenceType());
      }

      return (Java.ReferenceType[])l.toArray(new Java.ReferenceType[l.size()]);
   }

   public Java.Rvalue parseExpression() throws CompileException, IOException {
      return (Java.Rvalue)(this.peek(TokenType.IDENTIFIER) && this.peekNextButOne("->") ? this.parseLambdaExpression() : this.parseAssignmentExpression().toRvalueOrCompileException());
   }

   public Java.Atom parseExpressionOrType() throws CompileException, IOException {
      if (this.peek(TokenType.IDENTIFIER) && this.peekNextButOne("->")) {
         return this.parseLambdaExpression();
      } else {
         this.preferParametrizedTypes = true;

         Java.Atom var1;
         try {
            var1 = this.parseAssignmentExpression();
         } finally {
            this.preferParametrizedTypes = false;
         }

         return var1;
      }
   }

   public Java.Atom parseAssignmentExpression() throws CompileException, IOException {
      Java.Atom a = this.parseConditionalExpression();
      if (this.peek("=", "+=", "-=", "*=", "/=", "&=", "|=", "^=", "%=", "<<=", ">>=", ">>>=") != -1) {
         Java.Lvalue lhs = a.toLvalueOrCompileException();
         Location location = this.location();
         String operator = this.read(TokenType.OPERATOR);
         Java.Rvalue rhs = this.parseAssignmentExpression().toRvalueOrCompileException();
         return new Java.Assignment(location, lhs, operator, rhs);
      } else {
         return a;
      }
   }

   public Java.Atom parseConditionalExpression() throws CompileException, IOException {
      Java.Atom a = this.parseConditionalOrExpression();
      if (!this.peekRead("?")) {
         return a;
      } else {
         Location location = this.location();
         Java.Rvalue lhs = a.toRvalueOrCompileException();
         Java.Rvalue mhs = this.parseExpression();
         this.read(":");
         Java.Rvalue rhs = this.parseConditionalExpression().toRvalueOrCompileException();
         return new Java.ConditionalExpression(location, lhs, mhs, rhs);
      }
   }

   public Java.Atom parseConditionalOrExpression() throws CompileException, IOException {
      Java.Atom a;
      Location location;
      for(a = this.parseConditionalAndExpression(); this.peekRead("||"); a = new Java.BinaryOperation(location, a.toRvalueOrCompileException(), "||", this.parseConditionalAndExpression().toRvalueOrCompileException())) {
         location = this.location();
      }

      return a;
   }

   public Java.Atom parseConditionalAndExpression() throws CompileException, IOException {
      Java.Atom a;
      Location location;
      for(a = this.parseInclusiveOrExpression(); this.peekRead("&&"); a = new Java.BinaryOperation(location, a.toRvalueOrCompileException(), "&&", this.parseInclusiveOrExpression().toRvalueOrCompileException())) {
         location = this.location();
      }

      return a;
   }

   public Java.Atom parseInclusiveOrExpression() throws CompileException, IOException {
      Java.Atom a;
      Location location;
      for(a = this.parseExclusiveOrExpression(); this.peekRead("|"); a = new Java.BinaryOperation(location, a.toRvalueOrCompileException(), "|", this.parseExclusiveOrExpression().toRvalueOrCompileException())) {
         location = this.location();
      }

      return a;
   }

   public Java.Atom parseExclusiveOrExpression() throws CompileException, IOException {
      Java.Atom a;
      Location location;
      for(a = this.parseAndExpression(); this.peekRead("^"); a = new Java.BinaryOperation(location, a.toRvalueOrCompileException(), "^", this.parseAndExpression().toRvalueOrCompileException())) {
         location = this.location();
      }

      return a;
   }

   public Java.Atom parseAndExpression() throws CompileException, IOException {
      Java.Atom a;
      Location location;
      for(a = this.parseEqualityExpression(); this.peekRead("&"); a = new Java.BinaryOperation(location, a.toRvalueOrCompileException(), "&", this.parseEqualityExpression().toRvalueOrCompileException())) {
         location = this.location();
      }

      return a;
   }

   public Java.Atom parseEqualityExpression() throws CompileException, IOException {
      Java.Atom a;
      for(a = this.parseRelationalExpression(); this.peek("==", "!=") != -1; a = new Java.BinaryOperation(this.location(), a.toRvalueOrCompileException(), this.read().value, this.parseRelationalExpression().toRvalueOrCompileException())) {
      }

      return a;
   }

   public Java.Atom parseRelationalExpression() throws CompileException, IOException {
      Java.Atom a = this.parseShiftExpression();

      while(true) {
         while(!this.peekRead("instanceof")) {
            if (this.peek("<", ">", "<=", ">=") == -1) {
               return a;
            }

            if (this.preferParametrizedTypes && a instanceof Java.AmbiguousName && this.peek("<") && this.peekNextButOne("?")) {
               return new Java.ReferenceType(this.location(), new Java.Annotation[0], ((Java.AmbiguousName)a).identifiers, this.parseTypeArgumentsOpt());
            }

            String operator = this.read().value;
            Java.Atom rhs = this.parseShiftExpression();
            if (this.preferParametrizedTypes && "<".equals(operator) && this.peek("<", ">", ",") != -1 && a instanceof Java.AmbiguousName && rhs.toType() != null) {
               String[] identifiers = ((Java.AmbiguousName)a).identifiers;
               this.parseTypeArgumentsOpt();
               Java.Type t = rhs.toTypeOrCompileException();
               Java.TypeArgument firstTypeArgument;
               if (t instanceof Java.ArrayType) {
                  firstTypeArgument = (Java.ArrayType)t;
               } else {
                  if (!(t instanceof Java.ReferenceType)) {
                     throw this.compileException("'" + t + "' is not a valid type argument");
                  }

                  firstTypeArgument = (Java.ReferenceType)t;
               }

               List<Java.TypeArgument> typeArguments = new ArrayList();
               typeArguments.add(firstTypeArgument);

               while(this.read(">", ",") == 1) {
                  typeArguments.add(this.parseTypeArgument());
               }

               return new Java.ReferenceType(this.location(), new Java.Annotation[0], identifiers, (Java.TypeArgument[])typeArguments.toArray(new Java.TypeArgument[typeArguments.size()]));
            }

            a = new Java.BinaryOperation(this.location(), a.toRvalueOrCompileException(), operator, rhs.toRvalueOrCompileException());
         }

         Location location = this.location();
         a = new Java.Instanceof(location, a.toRvalueOrCompileException(), this.parseType());
      }
   }

   public Java.Atom parseShiftExpression() throws CompileException, IOException {
      Java.Atom a;
      for(a = this.parseAdditiveExpression(); this.peek("<<", ">>", ">>>") != -1; a = new Java.BinaryOperation(this.location(), a.toRvalueOrCompileException(), this.read().value, this.parseAdditiveExpression().toRvalueOrCompileException())) {
      }

      return a;
   }

   public Java.Atom parseAdditiveExpression() throws CompileException, IOException {
      Java.Atom a;
      for(a = this.parseMultiplicativeExpression(); this.peek("+", "-") != -1; a = new Java.BinaryOperation(this.location(), a.toRvalueOrCompileException(), this.read().value, this.parseMultiplicativeExpression().toRvalueOrCompileException())) {
      }

      return a;
   }

   public Java.Atom parseMultiplicativeExpression() throws CompileException, IOException {
      Java.Atom a;
      for(a = this.parseUnaryExpression(); this.peek("*", "/", "%") != -1; a = new Java.BinaryOperation(this.location(), a.toRvalueOrCompileException(), this.read().value, this.parseUnaryExpression().toRvalueOrCompileException())) {
      }

      return a;
   }

   public Java.Atom parseUnaryExpression() throws CompileException, IOException {
      if (this.peek("++", "--") != -1) {
         return new Java.Crement(this.location(), this.read().value, this.parseUnaryExpression().toLvalueOrCompileException());
      } else if (this.peek("+", "-", "~", "!") != -1) {
         return new Java.UnaryOperation(this.location(), this.read().value, this.parseUnaryExpression().toRvalueOrCompileException());
      } else {
         Java.Atom a;
         for(a = this.parsePrimary(); this.peek(".", "[") != -1; a = this.parseSelector(a)) {
         }

         if (this.peekRead("::")) {
            if (a instanceof Java.ArrayType) {
               this.read("new");
               return new Java.ArrayCreationReference(this.location(), (Java.ArrayType)a);
            } else {
               Java.TypeArgument[] typeArguments = this.parseTypeArgumentsOpt();
               switch (this.peek(TokenType.KEYWORD, TokenType.IDENTIFIER)) {
                  case 0:
                     this.read("new");
                     return new Java.ClassInstanceCreationReference(this.location(), a.toTypeOrCompileException(), typeArguments);
                  case 1:
                     return new Java.MethodReference(this.location(), a, this.read(TokenType.IDENTIFIER));
                  default:
                     throw new AssertionError(this.peek());
               }
            }
         } else {
            while(this.peek("++", "--") != -1) {
               a = new Java.Crement(this.location(), a.toLvalueOrCompileException(), this.read().value);
            }

            return a;
         }
      }
   }

   public Java.Atom parsePrimary() throws CompileException, IOException {
      if (this.peekRead("(")) {
         if (this.peek("boolean", "char", "byte", "short", "int", "long", "float", "double") != -1 && !this.peekNextButOne(TokenType.IDENTIFIER)) {
            Java.Type type = this.parseType();
            int brackets = this.parseBracketsOpt();
            this.read(")");

            for(int i = 0; i < brackets; ++i) {
               type = new Java.ArrayType(type);
            }

            return new Java.Cast(this.location(), type, this.parseUnaryExpression().toRvalueOrCompileException());
         } else if (this.peekRead(")")) {
            Java.LambdaParameters parameters = new Java.FormalLambdaParameters(new Java.FunctionDeclarator.FormalParameters(this.location()));
            Location loc = this.location();
            this.read("->");
            return new Java.LambdaExpression(loc, parameters, this.parseLambdaBody());
         } else {
            Java.Atom a;
            if (this.peek(TokenType.IDENTIFIER) && (this.peekNextButOne(",") || this.peekNextButOne(")"))) {
               List<String> l = new ArrayList();
               l.add(this.read(TokenType.IDENTIFIER));

               while(this.peekRead(",")) {
                  l.add(this.read(TokenType.IDENTIFIER));
               }

               String[] names = (String[])l.toArray(new String[l.size()]);
               Location loc = this.location();
               if (this.peek(")") && this.peekNextButOne("->")) {
                  this.read();
                  this.read();
                  return new Java.LambdaExpression(loc, new Java.InferredLambdaParameters(names), this.parseLambdaBody());
               }

               if (names.length != 1) {
                  throw this.compileException("Lambda expected");
               }

               a = new Java.AmbiguousName(loc, new String[]{names[0]});
            } else {
               a = this.parseExpressionOrType();
            }

            if (this.peek(TokenType.IDENTIFIER)) {
               Java.FunctionDeclarator.FormalParameters fpl = this.parseFormalParameterListRest(a.toTypeOrCompileException());
               this.read(")");
               Java.LambdaParameters parameters = new Java.FormalLambdaParameters(fpl);
               Location loc = this.location();
               this.read("->");
               return new Java.LambdaExpression(loc, parameters, this.parseLambdaBody());
            } else {
               this.read(")");
               return (Java.Atom)(!this.peekLiteral() && !this.peek(TokenType.IDENTIFIER) && this.peek("(", "~", "!") == -1 && this.peek("this", "super", "new") == -1 ? new Java.ParenthesizedExpression(a.getLocation(), a.toRvalueOrCompileException()) : new Java.Cast(this.location(), a.toTypeOrCompileException(), this.parseUnaryExpression().toRvalueOrCompileException()));
            }
         }
      } else if (this.peekLiteral()) {
         return this.parseLiteral();
      } else if (this.peek(TokenType.IDENTIFIER)) {
         String[] qi = this.parseQualifiedIdentifier();
         if (this.peek("(")) {
            return new Java.MethodInvocation(this.location(), qi.length == 1 ? null : new Java.AmbiguousName(this.location(), qi, qi.length - 1), qi[qi.length - 1], this.parseArguments());
         } else if (this.peek("[") && this.peekNextButOne("]")) {
            Java.Type res = new Java.ReferenceType(this.location(), new Java.Annotation[0], qi, (Java.TypeArgument[])null);
            int brackets = this.parseBracketsOpt();

            for(int i = 0; i < brackets; ++i) {
               res = new Java.ArrayType(res);
            }

            if (this.peek(".") && this.peekNextButOne("class")) {
               this.read();
               Location location2 = this.location();
               this.read();
               return new Java.ClassLiteral(location2, res);
            } else {
               return res;
            }
         } else {
            return new Java.AmbiguousName(this.location(), qi);
         }
      } else if (this.peekRead("this")) {
         Location location = this.location();
         return (Java.Atom)(this.peek("(") ? new Java.AlternateConstructorInvocation(location, this.parseArguments()) : new Java.ThisReference(location));
      } else if (this.peekRead("super")) {
         if (this.peek("(")) {
            return new Java.SuperConstructorInvocation(this.location(), (Java.Rvalue)null, this.parseArguments());
         } else {
            this.read(".");
            String name = this.read(TokenType.IDENTIFIER);
            return (Java.Atom)(this.peek("(") ? new Java.SuperclassMethodInvocation(this.location(), name, this.parseArguments()) : new Java.SuperclassFieldAccessExpression(this.location(), (Java.Type)null, name));
         }
      } else if (this.peekRead("new")) {
         Location location = this.location();
         Java.Type type = this.parseType();
         if (type instanceof Java.ArrayType) {
            return new Java.NewInitializedArray(location, (Java.ArrayType)type, this.parseArrayInitializer());
         } else if (type instanceof Java.ReferenceType && this.peek("(")) {
            Java.Rvalue[] arguments = this.parseArguments();
            if (this.peek("{")) {
               Java.AnonymousClassDeclaration anonymousClassDeclaration = new Java.AnonymousClassDeclaration(this.location(), type);
               this.parseClassBody(anonymousClassDeclaration);
               return new Java.NewAnonymousClassInstance(location, (Java.Rvalue)null, anonymousClassDeclaration, arguments);
            } else {
               return new Java.NewClassInstance(location, (Java.Rvalue)null, type, arguments);
            }
         } else {
            return new Java.NewArray(location, type, this.parseDimExprs(), this.parseBracketsOpt());
         }
      } else if (this.peek("boolean", "char", "byte", "short", "int", "long", "float", "double") == -1) {
         if (this.peekRead("void")) {
            if (this.peek(".") && this.peekNextButOne("class")) {
               this.read();
               Location location = this.location();
               this.read();
               return new Java.ClassLiteral(location, new Java.PrimitiveType(location, Java.Primitive.VOID));
            } else {
               throw this.compileException("\"void\" encountered in wrong context");
            }
         } else {
            throw this.compileException("Unexpected token \"" + this.read().value + "\" in primary");
         }
      } else {
         Java.Type res = this.parseType();
         int brackets = this.parseBracketsOpt();

         for(int i = 0; i < brackets; ++i) {
            res = new Java.ArrayType(res);
         }

         if (this.peek(".") && this.peekNextButOne("class")) {
            this.read();
            Location location = this.location();
            this.read();
            return new Java.ClassLiteral(location, res);
         } else {
            return res;
         }
      }
   }

   public Java.Atom parseSelector(Java.Atom atom) throws CompileException, IOException {
      if (this.peekRead(".")) {
         this.parseTypeArgumentsOpt();
         if (this.peek().type == TokenType.IDENTIFIER) {
            String identifier = this.read(TokenType.IDENTIFIER);
            return (Java.Atom)(this.peek("(") ? new Java.MethodInvocation(this.location(), atom.toRvalueOrCompileException(), identifier, this.parseArguments()) : new Java.FieldAccessExpression(this.location(), atom.toRvalueOrCompileException(), identifier));
         } else if (this.peekRead("this")) {
            Location location = this.location();
            return new Java.QualifiedThisReference(location, atom.toTypeOrCompileException());
         } else if (this.peekRead("super")) {
            Location location = this.location();
            if (this.peek("(")) {
               return new Java.SuperConstructorInvocation(location, atom.toRvalueOrCompileException(), this.parseArguments());
            } else {
               this.read(".");
               String identifier = this.read(TokenType.IDENTIFIER);
               if (this.peek("(")) {
                  throw this.compileException("Qualified superclass method invocation NYI");
               } else {
                  return new Java.SuperclassFieldAccessExpression(location, atom.toTypeOrCompileException(), identifier);
               }
            }
         } else if (this.peekRead("new")) {
            Java.Rvalue lhs = atom.toRvalueOrCompileException();
            Location location = this.location();
            String identifier = this.read(TokenType.IDENTIFIER);
            Java.Type type = new Java.RvalueMemberType(location, lhs, identifier);
            Java.Rvalue[] arguments = this.parseArguments();
            if (this.peek("{")) {
               Java.AnonymousClassDeclaration anonymousClassDeclaration = new Java.AnonymousClassDeclaration(this.location(), type);
               this.parseClassBody(anonymousClassDeclaration);
               return new Java.NewAnonymousClassInstance(location, lhs, anonymousClassDeclaration, arguments);
            } else {
               return new Java.NewClassInstance(location, lhs, type, arguments);
            }
         } else if (this.peekRead("class")) {
            Location location = this.location();
            return new Java.ClassLiteral(location, atom.toTypeOrCompileException());
         } else {
            throw this.compileException("Unexpected selector '" + this.peek().value + "' after \".\"");
         }
      } else if (this.peekRead("[")) {
         Location location = this.location();
         Java.Rvalue index = this.parseExpression();
         this.read("]");
         return new Java.ArrayAccessExpression(location, atom.toRvalueOrCompileException(), index);
      } else {
         throw this.compileException("Unexpected token '" + this.peek().value + "' in selector");
      }
   }

   public Java.Rvalue[] parseDimExprs() throws CompileException, IOException {
      List<Java.Rvalue> l = new ArrayList();
      l.add(this.parseDimExpr());

      while(this.peek("[") && !this.peekNextButOne("]")) {
         l.add(this.parseDimExpr());
      }

      return (Java.Rvalue[])l.toArray(new Java.Rvalue[l.size()]);
   }

   public Java.Rvalue parseDimExpr() throws CompileException, IOException {
      this.read("[");
      Java.Rvalue res = this.parseExpression();
      this.read("]");
      return res;
   }

   public Java.Rvalue[] parseArguments() throws CompileException, IOException {
      this.read("(");
      if (this.peekRead(")")) {
         return new Java.Rvalue[0];
      } else {
         Java.Rvalue[] arguments = this.parseArgumentList();
         this.read(")");
         return arguments;
      }
   }

   public Java.Rvalue[] parseArgumentList() throws CompileException, IOException {
      List<Java.Rvalue> l = new ArrayList();

      do {
         l.add(this.parseExpression());
      } while(this.peekRead(","));

      return (Java.Rvalue[])l.toArray(new Java.Rvalue[l.size()]);
   }

   public Java.Rvalue parseLiteral() throws CompileException, IOException {
      Token t = this.read();
      switch (t.type) {
         case INTEGER_LITERAL:
            return new Java.IntegerLiteral(t.getLocation(), t.value);
         case FLOATING_POINT_LITERAL:
            return new Java.FloatingPointLiteral(t.getLocation(), t.value);
         case BOOLEAN_LITERAL:
            return new Java.BooleanLiteral(t.getLocation(), t.value);
         case CHARACTER_LITERAL:
            return new Java.CharacterLiteral(t.getLocation(), t.value);
         case STRING_LITERAL:
            return new Java.StringLiteral(t.getLocation(), t.value);
         case NULL_LITERAL:
            return new Java.NullLiteral(t.getLocation());
         default:
            throw this.compileException("Literal expected");
      }
   }

   private Java.LambdaExpression parseLambdaExpression() throws CompileException, IOException {
      Java.LambdaParameters parameters = this.parseLambdaParameters();
      Location loc = this.location();
      this.read("->");
      Java.LambdaBody body = this.parseLambdaBody();
      return new Java.LambdaExpression(loc, parameters, body);
   }

   private Java.LambdaParameters parseLambdaParameters() throws CompileException, IOException {
      String identifier = this.peekRead(TokenType.IDENTIFIER);
      if (identifier != null) {
         return new Java.IdentifierLambdaParameters(identifier);
      } else {
         this.read("(");
         if (this.peekRead(")")) {
            return new Java.FormalLambdaParameters(new Java.FunctionDeclarator.FormalParameters(this.location()));
         } else if (this.peek(TokenType.IDENTIFIER) && (this.peekNextButOne(",") || this.peekNextButOne(")"))) {
            List<String> names = new ArrayList();
            names.add(this.read(TokenType.IDENTIFIER));

            while(this.peekRead(",")) {
               names.add(this.read(TokenType.IDENTIFIER));
            }

            this.read(")");
            return new Java.InferredLambdaParameters((String[])names.toArray(new String[names.size()]));
         } else {
            Java.FunctionDeclarator.FormalParameters fpl = this.parseFormalParameterList();
            this.read(")");
            return new Java.FormalLambdaParameters(fpl);
         }
      }
   }

   private Java.LambdaBody parseLambdaBody() throws CompileException, IOException {
      return (Java.LambdaBody)(this.peek("{") ? new Java.BlockLambdaBody(this.parseBlock()) : new Java.ExpressionLambdaBody(this.parseExpression()));
   }

   public Java.Statement parseExpressionStatement() throws CompileException, IOException {
      Java.Rvalue rv = this.parseExpression();
      this.read(";");
      return new Java.ExpressionStatement(rv);
   }

   public Location location() {
      return this.tokenStream.location();
   }

   public Token peek() throws CompileException, IOException {
      return this.tokenStream.peek();
   }

   public Token read() throws CompileException, IOException {
      return this.tokenStream.read();
   }

   public boolean peek(String suspected) throws CompileException, IOException {
      return this.tokenStream.peek(suspected);
   }

   public int peek(String... suspected) throws CompileException, IOException {
      return this.tokenStream.peek(suspected);
   }

   public boolean peek(TokenType suspected) throws CompileException, IOException {
      return this.tokenStream.peek(suspected);
   }

   public int peek(TokenType... suspected) throws CompileException, IOException {
      return this.tokenStream.peek(suspected);
   }

   public Token peekNextButOne() throws CompileException, IOException {
      return this.tokenStream.peekNextButOne();
   }

   public boolean peekNextButOne(String suspected) throws CompileException, IOException {
      return this.tokenStream.peekNextButOne(suspected);
   }

   public boolean peekNextButOne(TokenType suspected) throws CompileException, IOException {
      return this.tokenStream.peekNextButOne().type == suspected;
   }

   public void read(String expected) throws CompileException, IOException {
      this.tokenStream.read(expected);
   }

   public int read(String... expected) throws CompileException, IOException {
      return this.tokenStream.read(expected);
   }

   public String read(TokenType expected) throws CompileException, IOException {
      return this.tokenStream.read(expected);
   }

   public boolean peekRead(String suspected) throws CompileException, IOException {
      return this.tokenStream.peekRead(suspected);
   }

   public int peekRead(String... suspected) throws CompileException, IOException {
      return this.tokenStream.peekRead(suspected);
   }

   @Nullable
   public String peekRead(TokenType suspected) throws CompileException, IOException {
      return this.tokenStream.peekRead(suspected);
   }

   private boolean peekLiteral() throws CompileException, IOException {
      return this.peek(TokenType.INTEGER_LITERAL, TokenType.FLOATING_POINT_LITERAL, TokenType.BOOLEAN_LITERAL, TokenType.CHARACTER_LITERAL, TokenType.STRING_LITERAL, TokenType.NULL_LITERAL) != -1;
   }

   private void verifyStringIsConventionalPackageName(String s, Location loc) throws CompileException {
      if (!Character.isLowerCase(s.charAt(0))) {
         this.warning("UPN", "Package name \"" + s + "\" does not begin with a lower-case letter (see JLS7 6.8.1)", loc);
      } else {
         for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (!Character.isLowerCase(c) && c != '_' && c != '.') {
               this.warning("PPN", "Poorly chosen package name \"" + s + "\" contains bad character '" + c + "'", loc);
               return;
            }
         }

      }
   }

   private void verifyIdentifierIsConventionalClassOrInterfaceName(String id, Location loc) throws CompileException {
      if (!Character.isUpperCase(id.charAt(0))) {
         this.warning("UCOIN1", "Class or interface name \"" + id + "\" does not begin with an upper-case letter (see JLS7 6.8.2)", loc);
      } else {
         for(int i = 0; i < id.length(); ++i) {
            char c = id.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
               this.warning("UCOIN", "Class or interface name \"" + id + "\" contains unconventional character \"" + c + "\" (see JLS7 6.8.2)", loc);
               return;
            }
         }

      }
   }

   private void verifyIdentifierIsConventionalMethodName(String id, Location loc) throws CompileException {
      if (!Character.isLowerCase(id.charAt(0))) {
         this.warning("UMN1", "Method name \"" + id + "\" does not begin with a lower-case letter (see JLS7 6.8.3)", loc);
      } else {
         for(int i = 0; i < id.length(); ++i) {
            char c = id.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
               this.warning("UMN", "Method name \"" + id + "\" contains unconventional character \"" + c + "\" (see JLS7 6.8.3)", loc);
               return;
            }
         }

      }
   }

   private void verifyIdentifierIsConventionalFieldName(String id, Location loc) throws CompileException {
      if (Character.isUpperCase(id.charAt(0))) {
         for(int i = 0; i < id.length(); ++i) {
            char c = id.charAt(i);
            if (!Character.isUpperCase(c) && !Character.isDigit(c) && c != '_') {
               this.warning("UCN", "Constant name \"" + id + "\" contains unconventional character \"" + c + "\" (see JLS7 6.8.5)", loc);
               return;
            }
         }
      } else if (Character.isLowerCase(id.charAt(0))) {
         for(int i = 0; i < id.length(); ++i) {
            char c = id.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
               this.warning("UFN", "Field name \"" + id + "\" contains unconventional character \"" + c + "\" (see JLS7 6.8.4)", loc);
               return;
            }
         }
      } else {
         this.warning("UFN1", "\"" + id + "\" is neither a conventional field name (JLS7 6.8.4) nor a conventional constant name (JLS7 6.8.5)", loc);
      }

   }

   private void verifyIdentifierIsConventionalLocalVariableOrParameterName(String id, Location loc) throws CompileException {
      if (!Character.isLowerCase(id.charAt(0))) {
         this.warning("ULVN1", "Local variable name \"" + id + "\" does not begin with a lower-case letter (see JLS7 6.8.6)", loc);
      } else {
         for(int i = 0; i < id.length(); ++i) {
            char c = id.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
               this.warning("ULVN", "Local variable name \"" + id + "\" contains unconventional character \"" + c + "\" (see JLS7 6.8.6)", loc);
               return;
            }
         }

      }
   }

   public void setSourceVersion(int version) {
      this.sourceVersion = version;
   }

   public void setWarningHandler(@Nullable WarningHandler warningHandler) {
      this.warningHandler = warningHandler;
      this.tokenStream.setWarningHandler(warningHandler);
   }

   private void warning(String handle, String message) throws CompileException {
      this.warning(handle, message, this.location());
   }

   private void warning(String handle, String message, @Nullable Location location) throws CompileException {
      if (this.warningHandler != null) {
         this.warningHandler.handleWarning(handle, message, location);
      }

   }

   protected final CompileException compileException(String message) {
      return compileException(message, this.location());
   }

   protected static CompileException compileException(String message, Location location) {
      return new CompileException(message, location);
   }

   private static String join(@Nullable String[] sa, String separator) {
      if (sa == null) {
         return "(null)";
      } else if (sa.length == 0) {
         return "(zero length array)";
      } else {
         StringBuilder sb = new StringBuilder(sa[0]);

         for(int i = 1; i < sa.length; ++i) {
            sb.append(separator).append(sa[i]);
         }

         return sb.toString();
      }
   }

   private static boolean hasAccessModifier(Java.Modifier[] modifiers, String... keywords) {
      for(String kw : keywords) {
         for(Java.Modifier m : modifiers) {
            if (m instanceof Java.AccessModifier && kw.equals(((Java.AccessModifier)m).keyword)) {
               return true;
            }
         }
      }

      return false;
   }

   private static boolean hasAccessModifierOtherThan(Java.Modifier[] modifiers, String... keywords) {
      label27:
      for(Java.Modifier m : modifiers) {
         if (m instanceof Java.AccessModifier) {
            for(String kw : keywords) {
               if (kw.equals(((Java.AccessModifier)m).keyword)) {
                  continue label27;
               }
            }

            return true;
         }
      }

      return false;
   }

   private Java.Modifier[] packageModifiers(Java.Modifier[] modifiers) throws CompileException {
      return this.checkModifiers(modifiers);
   }

   private Java.Modifier[] classModifiers(Java.Modifier[] modifiers) throws CompileException {
      return this.checkModifiers(modifiers, "public", "protected", "private", "abstract", "static", "final", "strictfp");
   }

   private Java.Modifier[] packageMemberClassModifiers(Java.Modifier[] modifiers) throws CompileException {
      return this.checkModifiers(modifiers, "public", "abstract", "final", "strictfp");
   }

   private Java.Modifier[] fieldModifiers(Java.Modifier[] modifiers) throws CompileException {
      return this.checkModifiers(modifiers, "public", "protected", "private", "static", "final", "transient", "volatile");
   }

   private Java.Modifier[] methodModifiers(Java.Modifier[] modifiers) throws CompileException {
      return this.checkModifiers(modifiers, "public", "protected", "private", "abstract", "static", "final", "synchronized", "native", "strictfp");
   }

   private Java.Modifier[] variableModifiers(Java.Modifier[] modifiers) throws CompileException {
      return this.checkModifiers(modifiers, "final");
   }

   private Java.Modifier[] constructorModifiers(Java.Modifier[] modifiers) throws CompileException {
      return this.checkModifiers(modifiers, "public", "protected", "private");
   }

   private Java.Modifier[] enumConstantModifiers(Java.Modifier[] modifiers) throws CompileException {
      return this.checkModifiers(modifiers, "xxx");
   }

   private Java.Modifier[] interfaceModifiers(Java.Modifier[] modifiers) throws CompileException {
      return this.checkModifiers(modifiers, "public", "protected", "private", "abstract", "static", "strictfp");
   }

   private Java.Modifier[] packageMemberInterfaceModifiers(Java.Modifier[] modifiers) throws CompileException {
      return this.checkModifiers(modifiers, "public", "abstract", "strictfp");
   }

   private Java.Modifier[] constantModifiers(Java.Modifier[] modifiers) throws CompileException {
      return this.checkModifiers(modifiers, "public", "static", "final");
   }

   private Java.Modifier[] interfaceMethodModifiers(Java.Modifier[] modifiers) throws CompileException {
      return this.checkModifiers(modifiers, "public", "private", "abstract", "default", "static", "strictfp");
   }

   private Java.Modifier[] annotationTypeElementModifiers(Java.Modifier[] modifiers) throws CompileException {
      return this.checkModifiers(modifiers, "public", "abstract");
   }

   private Java.Modifier[] checkModifiers(Java.Modifier[] modifiers, String... allowedKeywords) throws CompileException {
      Set<String> keywords = new HashSet();

      for(Java.Modifier m : modifiers) {
         if (m instanceof Java.AccessModifier) {
            Java.AccessModifier am = (Java.AccessModifier)m;
            if (!keywords.add(am.keyword)) {
               throw compileException("Duplication access modifier \"" + am.keyword + "\"", am.getLocation());
            }

            for(Set meams : MUTUALLY_EXCLUSIVE_ACCESS_MODIFIERS) {
               Set<String> tmp = new HashSet(keywords);
               tmp.retainAll(meams);
               if (tmp.size() > 1) {
                  String[] a = (String[])tmp.toArray(new String[tmp.size()]);
                  Arrays.sort(a);
                  throw compileException("Only one of " + join(a, " ") + " is allowed", am.getLocation());
               }
            }
         }
      }

      for(String kw : allowedKeywords) {
         keywords.remove(kw);
      }

      if (!keywords.isEmpty()) {
         String[] a = (String[])keywords.toArray(new String[keywords.size()]);
         Arrays.sort(a);
         throw this.compileException("Access modifier(s) " + join(a, " ") + " not allowed in this context");
      } else {
         return modifiers;
      }
   }

   public static enum ClassDeclarationContext {
      BLOCK,
      TYPE_DECLARATION,
      COMPILATION_UNIT;

      // $FF: synthetic method
      private static ClassDeclarationContext[] $values() {
         return new ClassDeclarationContext[]{BLOCK, TYPE_DECLARATION, COMPILATION_UNIT};
      }
   }

   public static enum MethodDeclarationContext {
      CLASS_DECLARATION,
      INTERFACE_DECLARATION,
      ANNOTATION_TYPE_DECLARATION;

      // $FF: synthetic method
      private static MethodDeclarationContext[] $values() {
         return new MethodDeclarationContext[]{CLASS_DECLARATION, INTERFACE_DECLARATION, ANNOTATION_TYPE_DECLARATION};
      }
   }

   public static enum InterfaceDeclarationContext {
      NAMED_TYPE_DECLARATION,
      COMPILATION_UNIT;

      // $FF: synthetic method
      private static InterfaceDeclarationContext[] $values() {
         return new InterfaceDeclarationContext[]{NAMED_TYPE_DECLARATION, COMPILATION_UNIT};
      }
   }
}
