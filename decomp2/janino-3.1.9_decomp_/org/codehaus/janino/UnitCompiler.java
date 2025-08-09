package org.codehaus.janino;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.ErrorHandler;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.compiler.util.Numbers;
import org.codehaus.commons.compiler.util.SystemProperties;
import org.codehaus.commons.compiler.util.iterator.Iterables;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.util.Annotatable;
import org.codehaus.janino.util.ClassFile;

public class UnitCompiler {
   private static final Logger LOGGER = Logger.getLogger(UnitCompiler.class.getName());
   private static final int defaultTargetVersion = SystemProperties.getIntegerClassProperty(UnitCompiler.class, "defaultTargetVersion", -1);
   private static final int STRING_CONCAT_LIMIT = 3;
   public static final boolean JUMP_IF_TRUE = true;
   public static final boolean JUMP_IF_FALSE = false;
   private static final Pattern LOOKS_LIKE_TYPE_PARAMETER = Pattern.compile("\\p{javaUpperCase}+");
   private EnumSet options = EnumSet.noneOf(JaninoOption.class);
   private int targetVersion = -1;
   private static final Visitor.ArrayInitializerOrRvalueVisitor MAY_HAVE_SIDE_EFFECTS_VISITOR = new Visitor.ArrayInitializerOrRvalueVisitor() {
      final Visitor.LvalueVisitor lvalueVisitor = new Visitor.LvalueVisitor() {
         @Nullable
         public Boolean visitAmbiguousName(Java.AmbiguousName an) {
            return false;
         }

         @Nullable
         public Boolean visitArrayAccessExpression(Java.ArrayAccessExpression aae) {
            return UnitCompiler.mayHaveSideEffects(aae.lhs, aae.index);
         }

         @Nullable
         public Boolean visitFieldAccess(Java.FieldAccess fa) {
            return false;
         }

         @Nullable
         public Boolean visitFieldAccessExpression(Java.FieldAccessExpression fae) {
            return false;
         }

         @Nullable
         public Boolean visitSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression scfae) {
            return false;
         }

         @Nullable
         public Boolean visitLocalVariableAccess(Java.LocalVariableAccess lva) {
            return false;
         }

         @Nullable
         public Boolean visitParenthesizedExpression(Java.ParenthesizedExpression pe) {
            return UnitCompiler.mayHaveSideEffects((Java.ArrayInitializerOrRvalue)pe.value);
         }
      };
      final Visitor.RvalueVisitor rvalueVisitor = new Visitor.RvalueVisitor() {
         @Nullable
         public Boolean visitLvalue(Java.Lvalue lv) {
            return (Boolean)lv.accept(lvalueVisitor);
         }

         @Nullable
         public Boolean visitArrayLength(Java.ArrayLength al) {
            return UnitCompiler.mayHaveSideEffects((Java.ArrayInitializerOrRvalue)al.lhs);
         }

         @Nullable
         public Boolean visitAssignment(Java.Assignment a) {
            return true;
         }

         @Nullable
         public Boolean visitUnaryOperation(Java.UnaryOperation uo) {
            return UnitCompiler.mayHaveSideEffects((Java.ArrayInitializerOrRvalue)uo.operand);
         }

         @Nullable
         public Boolean visitBinaryOperation(Java.BinaryOperation bo) {
            return UnitCompiler.mayHaveSideEffects(bo.lhs, bo.rhs);
         }

         @Nullable
         public Boolean visitCast(Java.Cast c) {
            return UnitCompiler.mayHaveSideEffects((Java.ArrayInitializerOrRvalue)c.value);
         }

         @Nullable
         public Boolean visitClassLiteral(Java.ClassLiteral cl) {
            return false;
         }

         @Nullable
         public Boolean visitConditionalExpression(Java.ConditionalExpression ce) {
            return UnitCompiler.mayHaveSideEffects(ce.lhs, ce.mhs, ce.rhs);
         }

         @Nullable
         public Boolean visitCrement(Java.Crement c) {
            return true;
         }

         @Nullable
         public Boolean visitInstanceof(Java.Instanceof io) {
            return false;
         }

         @Nullable
         public Boolean visitMethodInvocation(Java.MethodInvocation mi) {
            return true;
         }

         @Nullable
         public Boolean visitSuperclassMethodInvocation(Java.SuperclassMethodInvocation smi) {
            return true;
         }

         @Nullable
         public Boolean visitIntegerLiteral(Java.IntegerLiteral il) {
            return false;
         }

         @Nullable
         public Boolean visitFloatingPointLiteral(Java.FloatingPointLiteral fpl) {
            return false;
         }

         @Nullable
         public Boolean visitBooleanLiteral(Java.BooleanLiteral bl) {
            return false;
         }

         @Nullable
         public Boolean visitCharacterLiteral(Java.CharacterLiteral cl) {
            return false;
         }

         @Nullable
         public Boolean visitStringLiteral(Java.StringLiteral sl) {
            return false;
         }

         @Nullable
         public Boolean visitNullLiteral(Java.NullLiteral nl) {
            return false;
         }

         @Nullable
         public Boolean visitSimpleConstant(Java.SimpleConstant sl) {
            return false;
         }

         @Nullable
         public Boolean visitNewAnonymousClassInstance(Java.NewAnonymousClassInstance naci) {
            return true;
         }

         @Nullable
         public Boolean visitNewArray(Java.NewArray na) {
            return UnitCompiler.mayHaveSideEffects((Java.ArrayInitializerOrRvalue[])na.dimExprs);
         }

         @Nullable
         public Boolean visitNewInitializedArray(Java.NewInitializedArray nia) {
            return UnitCompiler.mayHaveSideEffects((Java.ArrayInitializerOrRvalue)nia.arrayInitializer);
         }

         @Nullable
         public Boolean visitNewClassInstance(Java.NewClassInstance nci) {
            return true;
         }

         @Nullable
         public Boolean visitParameterAccess(Java.ParameterAccess pa) {
            return false;
         }

         @Nullable
         public Boolean visitQualifiedThisReference(Java.QualifiedThisReference qtr) {
            return false;
         }

         @Nullable
         public Boolean visitThisReference(Java.ThisReference tr) {
            return false;
         }

         @Nullable
         public Boolean visitLambdaExpression(Java.LambdaExpression le) {
            return true;
         }

         @Nullable
         public Boolean visitMethodReference(Java.MethodReference mr) {
            return true;
         }

         @Nullable
         public Boolean visitInstanceCreationReference(Java.ClassInstanceCreationReference cicr) {
            return true;
         }

         @Nullable
         public Boolean visitArrayCreationReference(Java.ArrayCreationReference acr) {
            return false;
         }
      };

      @Nullable
      public Boolean visitRvalue(Java.Rvalue rvalue) {
         return (Boolean)rvalue.accept(this.rvalueVisitor);
      }

      @Nullable
      public Boolean visitArrayInitializer(Java.ArrayInitializer ai) {
         return UnitCompiler.mayHaveSideEffects(ai.values);
      }
   };
   public static final Object NOT_CONSTANT;
   private static final Pattern TWO_E_31_INTEGER;
   private static final Pattern TWO_E_63_LONG;
   @Nullable
   private Map singleTypeImports;
   private final Map onDemandImportableTypes = new HashMap();
   private static final Map PRIMITIVE_WIDENING_CONVERSIONS;
   private static final Map PRIMITIVE_NARROWING_CONVERSIONS;
   private static final int EQ = 0;
   private static final int NE = 1;
   private static final int LT = 2;
   private static final int GE = 3;
   private static final int GT = 4;
   private static final int LE = 5;
   @Nullable
   private CodeContext codeContext;
   @Nullable
   private ErrorHandler compileErrorHandler;
   private int compileErrorCount;
   @Nullable
   private WarningHandler warningHandler;
   private final Java.AbstractCompilationUnit abstractCompilationUnit;
   private final IClassLoader iClassLoader;
   @Nullable
   private ClassFileConsumer storesClassFiles;
   private boolean debugSource;
   private boolean debugLines;
   private boolean debugVars;

   public UnitCompiler(Java.AbstractCompilationUnit abstractCompilationUnit, IClassLoader iClassLoader) {
      this.abstractCompilationUnit = abstractCompilationUnit;
      this.iClassLoader = iClassLoader;
   }

   public EnumSet options() {
      return this.options;
   }

   public UnitCompiler options(EnumSet options) {
      this.options = options;
      return this;
   }

   public void setTargetVersion(int version) {
      this.targetVersion = version;
   }

   public Java.AbstractCompilationUnit getAbstractCompilationUnit() {
      return this.abstractCompilationUnit;
   }

   public void compileUnit(boolean debugSource, boolean debugLines, boolean debugVars, final Collection generatedClassFiles) throws CompileException {
      this.compileUnit(debugSource, debugLines, debugVars, new ClassFileConsumer() {
         public void consume(ClassFile classFile) {
            generatedClassFiles.add(classFile);
         }
      });
   }

   public void compileUnit(boolean debugSource, boolean debugLines, boolean debugVars, ClassFileConsumer storesClassFiles) throws CompileException {
      this.debugSource = debugSource;
      this.debugLines = debugLines;
      this.debugVars = debugVars;
      if (this.storesClassFiles != null) {
         throw new IllegalStateException("\"UnitCompiler.compileUnit()\" is not reentrant");
      } else {
         this.storesClassFiles = storesClassFiles;

         try {
            this.abstractCompilationUnit.accept(new Visitor.AbstractCompilationUnitVisitor() {
               @Nullable
               public Void visitCompilationUnit(Java.CompilationUnit cu) throws CompileException {
                  UnitCompiler.this.compile2(cu);
                  return null;
               }

               @Nullable
               public Void visitModularCompilationUnit(Java.ModularCompilationUnit mcu) throws CompileException {
                  UnitCompiler.this.compile2(mcu);
                  return null;
               }
            });
            if (this.compileErrorCount > 0) {
               throw new CompileException(this.compileErrorCount + " error(s) while compiling unit \"" + this.abstractCompilationUnit.fileName + "\"", (Location)null);
            }
         } finally {
            this.storesClassFiles = null;
         }

      }
   }

   private void compile2(Java.CompilationUnit cu) throws CompileException {
      for(Java.PackageMemberTypeDeclaration pmtd : cu.packageMemberTypeDeclarations) {
         try {
            this.compile((Java.TypeDeclaration)pmtd);
         } catch (ClassFile.ClassFileException cfe) {
            throw new CompileException(cfe.getMessage(), pmtd.getLocation(), cfe);
         } catch (RuntimeException re) {
            throw new InternalCompilerException("Compiling \"" + pmtd + "\" in " + pmtd.getLocation() + ": " + re.getMessage(), re);
         }
      }

   }

   private void compile2(Java.ModularCompilationUnit mcu) throws CompileException {
      this.compileError("Compilation of modular compilation unit not implemented");
   }

   private void compile(Java.TypeDeclaration td) throws CompileException {
      td.accept(new Visitor.TypeDeclarationVisitor() {
         @Nullable
         public Void visitAnonymousClassDeclaration(Java.AnonymousClassDeclaration acd) throws CompileException {
            UnitCompiler.this.compile2(acd);
            return null;
         }

         @Nullable
         public Void visitLocalClassDeclaration(Java.LocalClassDeclaration lcd) throws CompileException {
            UnitCompiler.this.compile2(lcd);
            return null;
         }

         @Nullable
         public Void visitPackageMemberClassDeclaration(Java.PackageMemberClassDeclaration pmcd) throws CompileException {
            UnitCompiler.this.compile2(pmcd);
            return null;
         }

         @Nullable
         public Void visitMemberInterfaceDeclaration(Java.MemberInterfaceDeclaration mid) throws CompileException {
            UnitCompiler.this.compile2((Java.InterfaceDeclaration)mid);
            return null;
         }

         @Nullable
         public Void visitPackageMemberInterfaceDeclaration(Java.PackageMemberInterfaceDeclaration pmid) throws CompileException {
            UnitCompiler.this.compile2(pmid);
            return null;
         }

         @Nullable
         public Void visitMemberClassDeclaration(Java.MemberClassDeclaration mcd) throws CompileException {
            UnitCompiler.this.compile2((Java.InnerClassDeclaration)mcd);
            return null;
         }

         @Nullable
         public Void visitMemberEnumDeclaration(Java.MemberEnumDeclaration med) throws CompileException {
            UnitCompiler.this.compile2((Java.InnerClassDeclaration)med);
            return null;
         }

         @Nullable
         public Void visitPackageMemberEnumDeclaration(Java.PackageMemberEnumDeclaration pmed) throws CompileException {
            UnitCompiler.this.compile2((Java.PackageMemberClassDeclaration)pmed);
            return null;
         }

         @Nullable
         public Void visitPackageMemberAnnotationTypeDeclaration(Java.PackageMemberAnnotationTypeDeclaration pmatd) throws CompileException {
            UnitCompiler.this.compile2((Java.PackageMemberInterfaceDeclaration)pmatd);
            return null;
         }

         @Nullable
         public Void visitEnumConstant(Java.EnumConstant ec) throws CompileException {
            UnitCompiler.this.compileError("Compilation of enum constant NYI", ec.getLocation());
            return null;
         }

         @Nullable
         public Void visitMemberAnnotationTypeDeclaration(Java.MemberAnnotationTypeDeclaration matd) throws CompileException {
            UnitCompiler.this.compileError("Compilation of member annotation type declaration NYI", matd.getLocation());
            return null;
         }
      });
   }

   private void compile2(Java.PackageMemberClassDeclaration pmcd) throws CompileException {
      this.checkForConflictWithSingleTypeImport(pmcd.getName(), pmcd.getLocation());
      this.checkForNameConflictWithAnotherPackageMemberTypeDeclaration(pmcd);
      this.compile2((Java.AbstractClassDeclaration)pmcd);
   }

   private void compile2(Java.PackageMemberInterfaceDeclaration pmid) throws CompileException {
      this.checkForConflictWithSingleTypeImport(pmid.getName(), pmid.getLocation());
      this.checkForNameConflictWithAnotherPackageMemberTypeDeclaration(pmid);
      this.compile2((Java.InterfaceDeclaration)pmid);
   }

   private void checkForNameConflictWithAnotherPackageMemberTypeDeclaration(Java.PackageMemberTypeDeclaration pmtd) throws CompileException {
      Java.CompilationUnit declaringCompilationUnit = pmtd.getDeclaringCompilationUnit();
      String name = pmtd.getName();
      Java.PackageMemberTypeDeclaration otherPmtd = declaringCompilationUnit.getPackageMemberTypeDeclaration(name);
      if (otherPmtd != null && otherPmtd != pmtd) {
         this.compileError("Redeclaration of type \"" + name + "\", previously declared in " + otherPmtd.getLocation(), pmtd.getLocation());
      }

   }

   private void checkForConflictWithSingleTypeImport(String name, Location location) throws CompileException {
      String[] ss = this.getSingleTypeImport(name, location);
      if (ss != null) {
         this.compileError("Package member type declaration \"" + name + "\" conflicts with single-type-import \"" + Java.join(ss, ".") + "\"", location);
      }

   }

   private void compile2(Java.AbstractClassDeclaration cd) throws CompileException {
      IClass iClass = this.resolve(cd);
      if (!(cd instanceof Java.NamedClassDeclaration) || !((Java.NamedClassDeclaration)cd).isAbstract()) {
         IClass.IMethod[] ms = iClass.getIMethods();

         for(IClass.IMethod base : ms) {
            if (base.isAbstract() && !"<clinit>".equals(base.getName())) {
               IClass.IMethod override = iClass.findIMethod(base.getName(), base.getParameterTypes());
               if (override == null || override.isAbstract() || !base.getReturnType().isAssignableFrom(override.getReturnType())) {
                  this.compileError("Non-abstract class \"" + iClass + "\" must implement method \"" + base + "\"", cd.getLocation());
               }
            }
         }
      }

      short accessFlags = this.accessFlags(cd.getModifiers());
      if (cd instanceof Java.PackageMemberTypeDeclaration) {
         accessFlags = (short)(accessFlags | 32);
      }

      if (cd instanceof Java.EnumDeclaration) {
         accessFlags = (short)(accessFlags | 16384);
      }

      ClassFile cf = this.newClassFile(accessFlags, iClass, iClass.getSuperclass(), iClass.getInterfaces());
      this.compileAnnotations(cd.getAnnotations(), cf, cf);
      if (cd.getEnclosingScope() instanceof Java.Block) {
         short innerClassInfoIndex = cf.addConstantClassInfo(iClass.getDescriptor());
         short innerNameIndex = this instanceof Java.NamedTypeDeclaration ? cf.addConstantUtf8Info(((Java.NamedTypeDeclaration)this).getName()) : 0;

         assert cd.getAnnotations().length == 0 : "NYI";

         cf.addInnerClassesAttributeEntry(new ClassFile.InnerClassesAttribute.Entry(innerClassInfoIndex, (short)0, innerNameIndex, accessFlags));
      } else if (cd.getEnclosingScope() instanceof Java.TypeDeclaration) {
         short innerClassInfoIndex = cf.addConstantClassInfo(iClass.getDescriptor());
         short outerClassInfoIndex = cf.addConstantClassInfo(this.resolve((Java.TypeDeclaration)cd.getEnclosingScope()).getDescriptor());
         short innerNameIndex = cf.addConstantUtf8Info(((Java.MemberTypeDeclaration)cd).getName());
         cf.addInnerClassesAttributeEntry(new ClassFile.InnerClassesAttribute.Entry(innerClassInfoIndex, outerClassInfoIndex, innerNameIndex, accessFlags));
      }

      if (this.debugSource) {
         String s = cd.getLocation().getFileName();
         String sourceFileName;
         if (s != null) {
            sourceFileName = (new File(s)).getName();
         } else if (cd instanceof Java.NamedTypeDeclaration) {
            sourceFileName = ((Java.NamedTypeDeclaration)cd).getName() + ".java";
         } else {
            sourceFileName = "ANONYMOUS.java";
         }

         cf.addSourceFileAttribute(sourceFileName);
      }

      if (cd instanceof Java.DocCommentable && ((Java.DocCommentable)cd).hasDeprecatedDocTag()) {
         cf.addDeprecatedAttribute();
      }

      List<Java.BlockStatement> classInitializationStatements = new ArrayList();
      if (cd instanceof Java.EnumDeclaration) {
         Java.EnumDeclaration ed = (Java.EnumDeclaration)cd;

         for(Java.EnumConstant ec : ed.getConstants()) {
            Java.VariableDeclarator variableDeclarator = new Java.VariableDeclarator(ec.getLocation(), ec.name, 0, new Java.NewClassInstance(ec.getLocation(), (Java.Rvalue)null, iClass, ec.arguments != null ? ec.arguments : new Java.Rvalue[0]));
            Java.FieldDeclaration fd = new Java.FieldDeclaration(ec.getLocation(), ec.getDocComment(), accessModifiers(ec.getLocation(), "public", "static", "final"), new Java.SimpleType(ec.getLocation(), iClass), new Java.VariableDeclarator[]{variableDeclarator});
            fd.setDeclaringType(ed);
            classInitializationStatements.add(fd);
            this.addFields(fd, cf);
         }

         Location loc = ed.getLocation();
         IClass enumIClass = this.resolve(ed);
         Java.FieldDeclaration fd = new Java.FieldDeclaration(loc, (String)null, accessModifiers(loc, "private", "static", "final"), new Java.SimpleType(loc, enumIClass), new Java.VariableDeclarator[]{new Java.VariableDeclarator(loc, "ENUM$VALUES", 1, new Java.NewArray(loc, new Java.SimpleType(loc, enumIClass), new Java.Rvalue[]{new Java.IntegerLiteral(loc, String.valueOf(ed.getConstants().size()))}, 0))});
         ((Java.AbstractClassDeclaration)ed).addFieldDeclaration(fd);
      }

      for(Java.BlockStatement fdoi : cd.fieldDeclarationsAndInitializers) {
         if (fdoi instanceof Java.FieldDeclaration && ((Java.FieldDeclaration)fdoi).isStatic() || fdoi instanceof Java.Initializer && ((Java.Initializer)fdoi).isStatic()) {
            classInitializationStatements.add(fdoi);
         }
      }

      if (cd instanceof Java.EnumDeclaration) {
         Java.EnumDeclaration ed = (Java.EnumDeclaration)cd;
         IClass enumIClass = this.resolve(ed);
         int index = 0;

         for(Java.EnumConstant ec : ed.getConstants()) {
            classInitializationStatements.add(new Java.ExpressionStatement(new Java.Assignment(ec.getLocation(), new Java.ArrayAccessExpression(ec.getLocation(), new Java.FieldAccessExpression(ec.getLocation(), new Java.SimpleType(ec.getLocation(), enumIClass), "ENUM$VALUES"), new Java.IntegerLiteral(ec.getLocation(), String.valueOf(index++))), "=", new Java.FieldAccessExpression(ec.getLocation(), new Java.SimpleType(ec.getLocation(), enumIClass), ec.name))));
         }
      }

      this.maybeCreateInitMethod(cd, cf, classInitializationStatements);
      if (cd instanceof Java.EnumDeclaration) {
         Java.EnumDeclaration ed = (Java.EnumDeclaration)cd;
         Location loc = ed.getLocation();
         int numberOfEnumConstants = ed.getConstants().size();
         IClass enumIClass = this.resolve(ed);
         Java.VariableDeclarator vd = new Java.VariableDeclarator(loc, "tmp", 0, new Java.NewArray(loc, new Java.SimpleType(loc, enumIClass), new Java.Rvalue[]{new Java.IntegerLiteral(loc, String.valueOf(numberOfEnumConstants))}, 0));
         Java.LocalVariableDeclarationStatement lvds = new Java.LocalVariableDeclarationStatement(loc, new Java.Modifier[0], new Java.SimpleType(loc, this.iClassLoader.getArrayIClass(enumIClass)), new Java.VariableDeclarator[]{vd});
         Java.MethodDeclarator md = new Java.MethodDeclarator(loc, (String)null, accessModifiers(loc, "public", "static"), (Java.TypeParameter[])null, new Java.ArrayType(new Java.SimpleType(loc, enumIClass)), "values", new Java.FunctionDeclarator.FormalParameters(loc), new Java.Type[0], (Java.ElementValue)null, Arrays.asList(lvds, new Java.ExpressionStatement(new Java.MethodInvocation(loc, new Java.SimpleType(loc, this.iClassLoader.TYPE_java_lang_System), "arraycopy", new Java.Rvalue[]{new Java.FieldAccessExpression(loc, new Java.SimpleType(loc, enumIClass), "ENUM$VALUES"), new Java.IntegerLiteral(loc, "0"), new Java.LocalVariableAccess(loc, this.getLocalVariable(lvds, vd)), new Java.IntegerLiteral(loc, "0"), new Java.IntegerLiteral(loc, String.valueOf(numberOfEnumConstants))})), new Java.ReturnStatement(loc, new Java.LocalVariableAccess(loc, this.getLocalVariable(lvds, vd)))));
         md.setDeclaringType(ed);
         this.compile((Java.FunctionDeclarator)md, (ClassFile)cf);
         Java.FunctionDeclarator.FormalParameter fp = new Java.FunctionDeclarator.FormalParameter(loc, new Java.Modifier[0], new Java.SimpleType(loc, this.iClassLoader.TYPE_java_lang_String), "s");
         Java.MethodDeclarator md = new Java.MethodDeclarator(loc, (String)null, accessModifiers(loc, "public", "static"), (Java.TypeParameter[])null, new Java.SimpleType(loc, enumIClass), "valueOf", new Java.FunctionDeclarator.FormalParameters(loc, new Java.FunctionDeclarator.FormalParameter[]{fp}, false), new Java.Type[0], (Java.ElementValue)null, Arrays.asList(new Java.ReturnStatement(loc, new Java.Cast(loc, new Java.SimpleType(loc, enumIClass), new Java.MethodInvocation(loc, new Java.SimpleType(loc, this.iClassLoader.TYPE_java_lang_Enum), "valueOf", new Java.Rvalue[]{new Java.ClassLiteral(loc, new Java.SimpleType(loc, enumIClass)), new Java.ParameterAccess(loc, fp)})))));
         md.setEnclosingScope(ed);
         this.compile((Java.FunctionDeclarator)md, (ClassFile)cf);
      }

      this.compileDeclaredMethods(cd, cf);
      int declaredMethodCount = cd.getMethodDeclarations().size();
      Java.ConstructorDeclarator[] ctords = cd.getConstructors();
      int syntheticFieldCount = cd.syntheticFields.size();
      int methodInfoCount = cf.methodInfos.size();

      for(Java.ConstructorDeclarator ctord : ctords) {
         this.compile((Java.FunctionDeclarator)ctord, (ClassFile)cf);
      }

      if (syntheticFieldCount != cd.syntheticFields.size()) {
         syntheticFieldCount = cd.syntheticFields.size();

         while(cf.methodInfos.size() > methodInfoCount) {
            cf.methodInfos.remove(methodInfoCount);
         }

         for(Java.ConstructorDeclarator ctord : ctords) {
            ctord.iConstructor = null;
            this.compile((Java.FunctionDeclarator)ctord, (ClassFile)cf);
         }

         assert syntheticFieldCount == cd.syntheticFields.size();
      }

      this.compileDeclaredMemberTypes(cd, cf);
      this.compileDeclaredMethods(cd, cf, declaredMethodCount);

      for(IClass.IMethod base : iClass.getIMethods()) {
         if (!base.isStatic() && base.getAccess() != Access.PRIVATE) {
            IClass.IMethod override = iClass.findIMethod(base.getName(), base.getParameterTypes());
            if (override != null && base.getReturnType() != override.getReturnType()) {
               try {
                  this.generateBridgeMethod(cf, iClass, base, override);
               } catch (RuntimeException re) {
                  throw new InternalCompilerException(cd.getLocation(), "Generating bridge method from \"" + base + "\" to \"" + override + "\": " + re.getMessage(), re);
               }
            }
         }
      }

      for(Java.FieldDeclaration fd : Iterables.filterByClass(cd.getVariableDeclaratorsAndInitializers(), Java.FieldDeclaration.class)) {
         this.addFields(fd, cf);
      }

      for(IClass.IField f : cd.getSyntheticFields().values()) {
         cf.addFieldInfo((short)0, f.getName(), rawTypeOf(f.getType()).getDescriptor(), (Object)null);
      }

      this.addClassFile(cf);
   }

   private void addClassFile(ClassFile cf) {
      assert this.storesClassFiles != null;

      try {
         this.storesClassFiles.consume(cf);
      } catch (IOException ioe) {
         throw new InternalCompilerException(cf.getThisClassName(), ioe);
      }
   }

   private void addFields(Java.FieldDeclaration fd, ClassFile cf) throws CompileException {
      for(Java.VariableDeclarator vd : fd.variableDeclarators) {
         Java.Type type = fd.type;

         for(int i = 0; i < vd.brackets; ++i) {
            type = new Java.ArrayType(type);
         }

         Object ocv = fd.isFinal() && vd.initializer instanceof Java.Rvalue ? this.constantAssignmentConversion(vd.initializer, this.getConstantValue((Java.Rvalue)vd.initializer), this.getRawType(type)) : NOT_CONSTANT;
         short accessFlags = this.accessFlags(fd.modifiers);
         ClassFile.FieldInfo fi;
         if (fd.isPrivate()) {
            accessFlags = changeAccessibility(accessFlags, (short)0);
            fi = cf.addFieldInfo(accessFlags, vd.name, this.getRawType(type).getDescriptor(), ocv == NOT_CONSTANT ? null : ocv);
         } else {
            fi = cf.addFieldInfo(fd.getDeclaringType() instanceof Java.InterfaceDeclaration ? 25 : accessFlags, vd.name, this.getRawType(type).getDescriptor(), ocv == NOT_CONSTANT ? null : ocv);
         }

         this.compileAnnotations(fd.getAnnotations(), fi, cf);
         if (fd.hasDeprecatedDocTag()) {
            fi.addAttribute(new ClassFile.DeprecatedAttribute(cf.addConstantUtf8Info("Deprecated")));
         }
      }

   }

   private void compile2(Java.AnonymousClassDeclaration acd) throws CompileException {
      this.compile2((Java.InnerClassDeclaration)acd);
   }

   private void compile2(Java.LocalClassDeclaration lcd) throws CompileException {
      this.fakeCompileVariableDeclaratorsAndInitializers(lcd);
      this.compile2((Java.InnerClassDeclaration)lcd);
   }

   private void compile2(Java.InnerClassDeclaration icd) throws CompileException {
      List<Java.TypeDeclaration> ocs = getOuterClasses(icd);
      int nesting = ocs.size();
      if (nesting >= 2) {
         Java.TypeDeclaration immediatelyEnclosingOuterClassDeclaration = (Java.TypeDeclaration)ocs.get(1);
         icd.defineSyntheticField(new SimpleIField(this.resolve(icd), "this$" + (nesting - 2), this.resolve(immediatelyEnclosingOuterClassDeclaration)));
      }

      this.compile2((Java.AbstractClassDeclaration)icd);
   }

   private void fakeCompileVariableDeclaratorsAndInitializers(Java.AbstractClassDeclaration cd) throws CompileException {
      List<Java.FieldDeclarationOrInitializer> fdais = cd.fieldDeclarationsAndInitializers;

      for(int i = 0; i < fdais.size(); ++i) {
         Java.BlockStatement fdoi = (Java.BlockStatement)fdais.get(i);
         this.fakeCompile(fdoi);
      }

   }

   private void compile2(Java.InterfaceDeclaration id) throws CompileException {
      IClass iClass = this.resolve(id);
      IClass[] rawInterfaces = rawTypesOf(id.interfaces = this.getTypes(id.extendedTypes));
      short accessFlags = this.accessFlags(id.getModifiers());
      accessFlags = (short)(accessFlags | 512);
      accessFlags = (short)(accessFlags | 1024);
      if (id instanceof Java.AnnotationTypeDeclaration) {
         accessFlags = (short)(accessFlags | 8192);
      }

      if (id instanceof Java.MemberInterfaceDeclaration) {
         accessFlags = (short)(accessFlags | 8);
      }

      ClassFile cf = this.newClassFile(accessFlags, iClass, this.iClassLoader.TYPE_java_lang_Object, rawInterfaces);
      this.compileAnnotations(id.getAnnotations(), cf, cf);
      if (this.debugSource) {
         String s = id.getLocation().getFileName();
         String sourceFileName;
         if (s != null) {
            sourceFileName = (new File(s)).getName();
         } else {
            sourceFileName = id.getName() + ".java";
         }

         cf.addSourceFileAttribute(sourceFileName);
      }

      if (id.hasDeprecatedDocTag()) {
         cf.addDeprecatedAttribute();
      }

      if (!id.constantDeclarations.isEmpty()) {
         List<Java.BlockStatement> statements = new ArrayList();
         statements.addAll(id.constantDeclarations);
         this.maybeCreateInitMethod(id, cf, statements);
      }

      this.compileDeclaredMethods(id, cf);

      for(Java.FieldDeclaration constantDeclaration : id.constantDeclarations) {
         this.addFields(constantDeclaration, cf);
      }

      this.compileDeclaredMemberTypes(id, cf);
      this.addClassFile(cf);
   }

   private ClassFile newClassFile(short accessFlags, IClass iClass, @Nullable IClass superclass, IClass[] interfaces) throws CompileException {
      ClassFile result = new ClassFile(accessFlags, iClass.getDescriptor(), superclass != null ? superclass.getDescriptor() : null, IClass.getDescriptors(interfaces));
      int v = this.getTargetVersion();
      if (v < 6) {
         throw new CompileException("Cannot generate version " + v + " .class files", Location.NOWHERE);
      } else {
         result.setVersion((short)(44 + v), (short)0);
         return result;
      }
   }

   private void compileAnnotations(Java.Annotation[] annotations, Annotatable target, final ClassFile cf) throws CompileException {
      Set<IClass> seenAnnotations = new HashSet();

      label42:
      for(Java.Annotation a : annotations) {
         Java.Type annotationType = a.getType();
         final IClass annotationIClass = this.getRawType(annotationType);
         IClass.IAnnotation[] annotationAnnotations = annotationIClass.getIAnnotations();
         if (!seenAnnotations.add(annotationIClass)) {
            this.compileError("Duplicate annotation \"" + annotationIClass + "\"", annotationType.getLocation());
         }

         boolean runtimeVisible = false;

         for(IClass.IAnnotation aa : annotationAnnotations) {
            if (aa.getAnnotationType() == this.iClassLoader.TYPE_java_lang_annotation_Retention) {
               Object rev = aa.getElementValue("value");
               String retention = ((IClass.IField)rev).getName();
               if ("SOURCE".equals(retention)) {
                  continue label42;
               }

               if ("CLASS".equals(retention)) {
                  runtimeVisible = false;
               } else {
                  if (!"RUNTIME".equals(retention)) {
                     throw new AssertionError(retention);
                  }

                  runtimeVisible = true;
               }
               break;
            }
         }

         final Map<Short, ClassFile.ElementValue> evps = new HashMap();
         a.accept(new Visitor.AnnotationVisitor() {
            @Nullable
            public Void visitSingleElementAnnotation(Java.SingleElementAnnotation sea) throws CompileException {
               IClass.IMethod[] definitions = annotationIClass.getDeclaredIMethods("value");

               assert definitions.length == 1;

               boolean isArray = definitions[0].getReturnType().isArray();
               evps.put(cf.addConstantUtf8Info("value"), UnitCompiler.this.compileElementValue(sea.elementValue, cf, isArray));
               return null;
            }

            @Nullable
            public Void visitNormalAnnotation(Java.NormalAnnotation na) throws CompileException {
               for(Java.ElementValuePair evp : na.elementValuePairs) {
                  IClass.IMethod[] definitions = annotationIClass.getDeclaredIMethods(evp.identifier);

                  assert definitions.length == 1;

                  boolean isArray = definitions[0].getReturnType().isArray();
                  evps.put(cf.addConstantUtf8Info(evp.identifier), UnitCompiler.this.compileElementValue(evp.elementValue, cf, isArray));
               }

               return null;
            }

            @Nullable
            public Void visitMarkerAnnotation(Java.MarkerAnnotation ma) {
               return null;
            }
         });
         target.addAnnotationsAttributeEntry(runtimeVisible, annotationIClass.getDescriptor(), evps);
      }

   }

   private ClassFile.ElementValue compileElementValue(Java.ElementValue elementValue, final ClassFile cf, boolean compileAsArray) throws CompileException {
      ClassFile.ElementValue result = (ClassFile.ElementValue)elementValue.accept(new Visitor.ElementValueVisitor() {
         public ClassFile.ElementValue visitRvalue(Java.Rvalue rv) throws CompileException {
            if (rv instanceof Java.AmbiguousName) {
               Java.Rvalue enumConstant = UnitCompiler.this.reclassify((Java.AmbiguousName)rv).toRvalue();
               if (enumConstant instanceof Java.FieldAccess) {
                  Java.FieldAccess enumConstantFieldAccess = (Java.FieldAccess)enumConstant;
                  Java.Type enumType = enumConstantFieldAccess.lhs.toType();
                  if (enumType != null) {
                     IClass enumIClass = UnitCompiler.this.findTypeByName(rv.getLocation(), enumType.toString());
                     if (enumIClass == null) {
                        UnitCompiler.this.compileError("Cannot find enum \"" + enumType + "\"", enumType.getLocation());
                     } else if (enumIClass.getSuperclass() == UnitCompiler.this.iClassLoader.TYPE_java_lang_Enum) {
                        return new ClassFile.EnumConstValue(cf.addConstantUtf8Info(enumIClass.getDescriptor()), cf.addConstantUtf8Info(enumConstantFieldAccess.field.getName()));
                     }
                  }
               }
            }

            if (rv instanceof Java.ClassLiteral) {
               return new ClassFile.ClassElementValue(cf.addConstantUtf8Info(UnitCompiler.this.getRawType(((Java.ClassLiteral)rv).type).getDescriptor()));
            } else {
               Object cv = UnitCompiler.this.getConstantValue(rv);
               if (cv == UnitCompiler.NOT_CONSTANT) {
                  throw new CompileException("\"" + rv + "\" is not a constant expression", rv.getLocation());
               } else if (cv == null) {
                  throw new CompileException("Null literal not allowed as element value", rv.getLocation());
               } else if (cv instanceof Boolean) {
                  return new ClassFile.BooleanElementValue(cf.addConstantIntegerInfo((Boolean)cv ? 1 : 0));
               } else if (cv instanceof Byte) {
                  return new ClassFile.ByteElementValue(cf.addConstantIntegerInfo((Byte)cv));
               } else if (cv instanceof Short) {
                  return new ClassFile.ShortElementValue(cf.addConstantIntegerInfo((Short)cv));
               } else if (cv instanceof Integer) {
                  return new ClassFile.IntElementValue(cf.addConstantIntegerInfo((Integer)cv));
               } else if (cv instanceof Long) {
                  return new ClassFile.LongElementValue(cf.addConstantLongInfo((Long)cv));
               } else if (cv instanceof Float) {
                  return new ClassFile.FloatElementValue(cf.addConstantFloatInfo((Float)cv));
               } else if (cv instanceof Double) {
                  return new ClassFile.DoubleElementValue(cf.addConstantDoubleInfo((Double)cv));
               } else if (cv instanceof Character) {
                  return new ClassFile.CharElementValue(cf.addConstantIntegerInfo((Character)cv));
               } else if (cv instanceof String) {
                  return new ClassFile.StringElementValue(cf.addConstantUtf8Info((String)cv));
               } else {
                  throw new AssertionError(cv);
               }
            }
         }

         public ClassFile.ElementValue visitAnnotation(Java.Annotation a) throws CompileException {
            final IClass annotationIClass = UnitCompiler.this.getRawType(a.getType());
            short annotationTypeIndex = cf.addConstantUtf8Info(annotationIClass.getDescriptor());
            final Map<Short, ClassFile.ElementValue> evps = new HashMap();
            a.accept(new Visitor.AnnotationVisitor() {
               @Nullable
               public Void visitMarkerAnnotation(Java.MarkerAnnotation ma) {
                  return null;
               }

               @Nullable
               public Void visitSingleElementAnnotation(Java.SingleElementAnnotation sea) throws CompileException {
                  IClass.IMethod[] definitions = annotationIClass.getDeclaredIMethods("value");

                  assert definitions.length == 1;

                  boolean expectArray = definitions[0].getReturnType().isArray();
                  evps.put(cf.addConstantUtf8Info("value"), UnitCompiler.this.compileElementValue(sea.elementValue, cf, expectArray));
                  return null;
               }

               @Nullable
               public Void visitNormalAnnotation(Java.NormalAnnotation na) throws CompileException {
                  for(Java.ElementValuePair evp : na.elementValuePairs) {
                     IClass.IMethod[] definitions = annotationIClass.getDeclaredIMethods(evp.identifier);

                     assert definitions.length == 1;

                     boolean expectArray = Descriptor.isArrayReference(definitions[0].getDescriptor().returnFd);
                     evps.put(cf.addConstantUtf8Info(evp.identifier), UnitCompiler.this.compileElementValue(evp.elementValue, cf, expectArray));
                  }

                  return null;
               }
            });
            return new ClassFile.Annotation(annotationTypeIndex, evps);
         }

         public ClassFile.ElementValue visitElementValueArrayInitializer(Java.ElementValueArrayInitializer evai) throws CompileException {
            ClassFile.ElementValue[] evs = new ClassFile.ElementValue[evai.elementValues.length];

            for(int i = 0; i < evai.elementValues.length; ++i) {
               evs[i] = UnitCompiler.this.compileElementValue(evai.elementValues[i], cf, false);
            }

            return new ClassFile.ArrayElementValue(evs);
         }
      });
      if (compileAsArray && result instanceof ClassFile.ConstantElementValue) {
         ClassFile.ElementValue[] arrayValues = new ClassFile.ElementValue[1];
         arrayValues[0] = result;
         result = new ClassFile.ArrayElementValue(arrayValues);
      }

      assert result != null;

      return result;
   }

   private void maybeCreateInitMethod(Java.TypeDeclaration td, ClassFile cf, List statements) throws CompileException {
      if (this.generatesCode2(statements)) {
         Java.MethodDeclarator md = new Java.MethodDeclarator(td.getLocation(), (String)null, accessModifiers(td.getLocation(), "static", "public"), (Java.TypeParameter[])null, new Java.PrimitiveType(td.getLocation(), Java.Primitive.VOID), "<clinit>", new Java.FunctionDeclarator.FormalParameters(td.getLocation()), new Java.ReferenceType[0], (Java.ElementValue)null, statements);
         md.setDeclaringType(td);
         this.compile((Java.FunctionDeclarator)md, (ClassFile)cf);
      }

   }

   private void compileDeclaredMemberTypes(Java.TypeDeclaration decl, ClassFile cf) throws CompileException {
      for(Java.MemberTypeDeclaration mtd : decl.getMemberTypeDeclarations()) {
         this.compile((Java.TypeDeclaration)mtd);
         short innerClassInfoIndex = cf.addConstantClassInfo(this.resolve(mtd).getDescriptor());
         short outerClassInfoIndex = cf.addConstantClassInfo(this.resolve(decl).getDescriptor());
         short innerNameIndex = cf.addConstantUtf8Info(mtd.getName());
         cf.addInnerClassesAttributeEntry(new ClassFile.InnerClassesAttribute.Entry(innerClassInfoIndex, outerClassInfoIndex, innerNameIndex, this.accessFlags(mtd.getModifiers())));
      }

   }

   private void compileDeclaredMethods(Java.TypeDeclaration typeDeclaration, ClassFile cf) throws CompileException {
      this.compileDeclaredMethods(typeDeclaration, cf, 0);
   }

   private void compileDeclaredMethods(Java.TypeDeclaration typeDeclaration, ClassFile cf, int startPos) throws CompileException {
      for(int i = startPos; i < typeDeclaration.getMethodDeclarations().size(); ++i) {
         Java.MethodDeclarator md = (Java.MethodDeclarator)typeDeclaration.getMethodDeclarations().get(i);
         IClass.IMethod m = this.toIMethod(md);
         boolean overrides = this.overridesMethodFromSupertype(m, this.resolve(md.getDeclaringType()));
         boolean hasOverrideAnnotation = this.hasAnnotation(md, this.iClassLoader.TYPE_java_lang_Override);
         if (overrides && !hasOverrideAnnotation && !(typeDeclaration instanceof Java.InterfaceDeclaration)) {
            this.warning("MO", "Missing @Override", md.getLocation());
         } else if (!overrides && hasOverrideAnnotation) {
            this.compileError("Method does not override a method declared in a supertype", md.getLocation());
         }

         this.compile((Java.FunctionDeclarator)md, (ClassFile)cf);
      }

   }

   private boolean hasAnnotation(Java.FunctionDeclarator fd, IClass annotationType) throws CompileException {
      for(Java.Annotation a : Iterables.filterByClass(fd.getModifiers(), Java.Annotation.class)) {
         if (this.getType(a.getType()) == annotationType) {
            return true;
         }
      }

      return false;
   }

   private boolean overridesMethodFromSupertype(IClass.IMethod m, IClass type) throws CompileException {
      IClass superclass = type.getSuperclass();
      if (superclass != null && this.overridesMethod(m, superclass)) {
         return true;
      } else {
         superclass = type.getInterfaces();

         for(IClass i : superclass) {
            if (this.overridesMethod(m, i)) {
               return true;
            }
         }

         if (((Object[])superclass).length == 0 && type.isInterface()) {
            return this.overridesMethod(m, this.iClassLoader.TYPE_java_lang_Object);
         } else {
            return false;
         }
      }
   }

   private boolean overridesMethod(IClass.IMethod method, IClass type) throws CompileException {
      IClass.IMethod[] ms = type.getDeclaredIMethods(method.getName());

      for(IClass.IMethod m : ms) {
         if (Arrays.equals(method.getParameterTypes(), m.getParameterTypes())) {
            return true;
         }
      }

      return this.overridesMethodFromSupertype(method, type);
   }

   private void generateBridgeMethod(ClassFile cf, IClass declaringIClass, IClass.IMethod base, IClass.IMethod override) throws CompileException {
      if (base.getReturnType().isAssignableFrom(override.getReturnType()) && override.getReturnType() != IClass.VOID) {
         ClassFile.MethodInfo mi = cf.addMethodInfo((short)4097, base.getName(), base.getDescriptor());
         IClass[] thrownExceptions = base.getThrownExceptions();
         if (thrownExceptions.length > 0) {
            short eani = cf.addConstantUtf8Info("Exceptions");
            short[] tecciis = new short[thrownExceptions.length];

            for(int i = 0; i < thrownExceptions.length; ++i) {
               tecciis[i] = cf.addConstantClassInfo(thrownExceptions[i].getDescriptor());
            }

            mi.addAttribute(new ClassFile.ExceptionsAttribute(eani, tecciis));
         }

         CodeContext codeContext = new CodeContext(mi.getClassFile());
         CodeContext savedCodeContext = this.replaceCodeContext(codeContext);

         try {
            codeContext.saveLocalVariables();
            this.allocateLocalVariableSlotAndMarkAsInitialized(override.getDeclaringIClass(), "this");
            IClass[] paramTypes = override.getParameterTypes();
            Java.LocalVariableSlot[] locals = new Java.LocalVariableSlot[paramTypes.length];

            for(int i = 0; i < paramTypes.length; ++i) {
               locals[i] = this.allocateLocalVariableSlotAndMarkAsInitialized(paramTypes[i], "param" + i);
            }

            this.load(Java.Located.NOWHERE, declaringIClass, 0);

            for(Java.LocalVariableSlot l : locals) {
               this.load(Java.Located.NOWHERE, l.getType(), l.getSlotIndex());
            }

            this.invokeMethod(Java.Located.NOWHERE, override);
            this.xreturn(Java.Located.NOWHERE, base.getReturnType());
         } finally {
            this.replaceCodeContext(savedCodeContext);
         }

         mi.addAttribute(codeContext.newCodeAttribute(1 + override.getParameterTypes().length, false, false));
      } else {
         this.compileError("The return type of \"" + override + "\" is incompatible with that of \"" + base + "\"");
      }
   }

   private boolean compile(Java.BlockStatement bs) throws CompileException {
      try {
         Boolean result = (Boolean)bs.accept(new Visitor.BlockStatementVisitor() {
            public Boolean visitInitializer(Java.Initializer i) throws CompileException {
               return UnitCompiler.this.compile2(i);
            }

            public Boolean visitFieldDeclaration(Java.FieldDeclaration fd) throws CompileException {
               return UnitCompiler.this.compile2(fd);
            }

            public Boolean visitLabeledStatement(Java.LabeledStatement ls) throws CompileException {
               return UnitCompiler.this.compile2(ls);
            }

            public Boolean visitBlock(Java.Block b) throws CompileException {
               return UnitCompiler.this.compile2(b);
            }

            public Boolean visitExpressionStatement(Java.ExpressionStatement es) throws CompileException {
               return UnitCompiler.this.compile2(es);
            }

            public Boolean visitIfStatement(Java.IfStatement is) throws CompileException {
               return UnitCompiler.this.compile2(is);
            }

            public Boolean visitForStatement(Java.ForStatement fs) throws CompileException {
               return UnitCompiler.this.compile2(fs);
            }

            public Boolean visitForEachStatement(Java.ForEachStatement fes) throws CompileException {
               return UnitCompiler.this.compile2(fes);
            }

            public Boolean visitWhileStatement(Java.WhileStatement ws) throws CompileException {
               return UnitCompiler.this.compile2(ws);
            }

            public Boolean visitTryStatement(Java.TryStatement ts) throws CompileException {
               return UnitCompiler.this.compile2(ts);
            }

            public Boolean visitSwitchStatement(Java.SwitchStatement ss) throws CompileException {
               return UnitCompiler.this.compile2(ss);
            }

            public Boolean visitSynchronizedStatement(Java.SynchronizedStatement ss) throws CompileException {
               return UnitCompiler.this.compile2(ss);
            }

            public Boolean visitDoStatement(Java.DoStatement ds) throws CompileException {
               return UnitCompiler.this.compile2(ds);
            }

            public Boolean visitLocalVariableDeclarationStatement(Java.LocalVariableDeclarationStatement lvds) throws CompileException {
               return UnitCompiler.this.compile2(lvds);
            }

            public Boolean visitReturnStatement(Java.ReturnStatement rs) throws CompileException {
               return UnitCompiler.this.compile2(rs);
            }

            public Boolean visitThrowStatement(Java.ThrowStatement ts) throws CompileException {
               return UnitCompiler.this.compile2(ts);
            }

            public Boolean visitBreakStatement(Java.BreakStatement bs) throws CompileException {
               return UnitCompiler.this.compile2(bs);
            }

            public Boolean visitContinueStatement(Java.ContinueStatement cs) throws CompileException {
               return UnitCompiler.this.compile2(cs);
            }

            public Boolean visitAssertStatement(Java.AssertStatement as) throws CompileException {
               return UnitCompiler.this.compile2(as);
            }

            public Boolean visitEmptyStatement(Java.EmptyStatement es) {
               return UnitCompiler.this.compile2(es);
            }

            public Boolean visitLocalClassDeclarationStatement(Java.LocalClassDeclarationStatement lcds) throws CompileException {
               return UnitCompiler.this.compile2(lcds);
            }

            public Boolean visitAlternateConstructorInvocation(Java.AlternateConstructorInvocation aci) throws CompileException {
               return UnitCompiler.this.compile2(aci);
            }

            public Boolean visitSuperConstructorInvocation(Java.SuperConstructorInvocation sci) throws CompileException {
               return UnitCompiler.this.compile2(sci);
            }
         });

         assert result != null;

         return result;
      } catch (RuntimeException re) {
         throw new RuntimeException(bs.getLocation().toString(), re);
      }
   }

   private boolean fakeCompile(Java.BlockStatement bs) throws CompileException {
      CodeContext.Offset from = this.getCodeContext().newOffset();
      boolean ccn = this.compile(bs);
      CodeContext.Offset to = this.getCodeContext().newOffset();
      this.getCodeContext().removeCode(from, to);
      return ccn;
   }

   private CodeContext getCodeContext() {
      assert this.codeContext != null;

      return this.codeContext;
   }

   private boolean compile2(Java.Initializer i) throws CompileException {
      this.buildLocalVariableMap((Java.Block)i.block, new HashMap());
      return this.compile((Java.BlockStatement)i.block);
   }

   private boolean compile2(Java.Block b) throws CompileException {
      this.getCodeContext().saveLocalVariables();

      boolean var2;
      try {
         var2 = this.compileStatements(b.statements);
      } finally {
         this.getCodeContext().restoreLocalVariables();
      }

      return var2;
   }

   private boolean compileStatements(List statements) throws CompileException {
      boolean previousStatementCanCompleteNormally = true;

      for(Java.BlockStatement bs : statements) {
         if (!previousStatementCanCompleteNormally && this.generatesCode(bs)) {
            this.compileError("Statement is unreachable", bs.getLocation());
            break;
         }

         try {
            previousStatementCanCompleteNormally = this.compile(bs);
         } catch (RuntimeException re) {
            throw new RuntimeException(bs.getLocation().toString(), re);
         } catch (AssertionError ae) {
            throw new InternalCompilerException(bs.getLocation(), (String)null, ae);
         }
      }

      return previousStatementCanCompleteNormally;
   }

   private boolean compile2(Java.DoStatement ds) throws CompileException {
      Object cvc = this.getConstantValue(ds.condition);
      if (cvc != NOT_CONSTANT) {
         if (Boolean.TRUE.equals(cvc)) {
            this.warning("DSTC", "Condition of DO statement is always TRUE; the proper way of declaring an unconditional loop is \"for (;;)\"", ds.getLocation());
            return this.compileUnconditionalLoop(ds, ds.body, (Java.Rvalue[])null);
         }

         this.warning("DSNR", "DO statement never repeats", ds.getLocation());
      }

      CodeContext.Offset bodyOffset = this.getCodeContext().newBasicBlock();
      ds.whereToContinue = null;
      if (!this.compile(ds.body) && ds.whereToContinue == null) {
         this.warning("DSNTC", "\"do\" statement never tests its condition", ds.getLocation());
         CodeContext.Offset wtb = ds.whereToBreak;
         if (wtb == null) {
            return false;
         } else {
            wtb.set();
            ds.whereToBreak = null;
            return true;
         }
      } else {
         if (ds.whereToContinue != null) {
            ds.whereToContinue.set();
            ds.whereToContinue = null;
         }

         this.compileBoolean(ds.condition, bodyOffset, true);
         if (ds.whereToBreak != null) {
            ds.whereToBreak.set();
            ds.whereToBreak = null;
         }

         return true;
      }
   }

   private boolean compile2(Java.ForStatement fs) throws CompileException {
      this.getCodeContext().saveLocalVariables();

      try {
         Java.BlockStatement oi = fs.init;
         Java.Rvalue[] ou = fs.update;
         Java.Rvalue oc = fs.condition;
         if (oi != null) {
            this.compile(oi);
         }

         if (oc == null) {
            boolean var17 = this.compileUnconditionalLoop(fs, fs.body, ou);
            return var17;
         }

         Object cvc = this.getConstantValue(oc);
         if (cvc != NOT_CONSTANT) {
            if (Boolean.TRUE.equals(cvc)) {
               this.warning("FSTC", "Condition of FOR statement is always TRUE; the proper way of declaring an unconditional loop is \"for (;;)\"", fs.getLocation());
               boolean var18 = this.compileUnconditionalLoop(fs, fs.body, ou);
               return var18;
            }

            this.warning("FSNR", "FOR statement never repeats", fs.getLocation());
         }

         CodeContext var10002 = this.getCodeContext();
         Objects.requireNonNull(var10002);
         CodeContext.Offset toCondition = var10002.new BasicBlock();
         StackMap smBeforeBody = this.codeContext.currentInserter().getStackMap();
         this.gotO(fs, toCondition);
         fs.whereToContinue = null;
         this.codeContext.currentInserter().setStackMap(smBeforeBody);
         CodeContext.Offset bodyOffset = this.getCodeContext().newBasicBlock();
         boolean bodyCcn = this.compile(fs.body);
         if (fs.whereToContinue != null) {
            fs.whereToContinue.set();
         }

         if (ou != null) {
            if (!bodyCcn && fs.whereToContinue == null) {
               this.warning("FUUR", "For update is unreachable", fs.getLocation());
            } else {
               for(Java.Rvalue rv : ou) {
                  this.compile(rv);
               }
            }
         }

         fs.whereToContinue = null;
         toCondition.set();
         this.compileBoolean(oc, bodyOffset, true);
      } finally {
         this.getCodeContext().restoreLocalVariables();
      }

      if (fs.whereToBreak != null) {
         fs.whereToBreak.set();
         fs.whereToBreak = null;
      }

      return true;
   }

   private boolean compile2(Java.ForEachStatement fes) throws CompileException {
      IType expressionType = this.getType(fes.expression);
      if (isArray(expressionType)) {
         this.getCodeContext().saveLocalVariables();

         try {
            StackMap beforeStatement = this.getCodeContext().currentInserter().getStackMap();
            Java.LocalVariable elementLv = this.getLocalVariable(fes.currentElement, false);
            elementLv.setSlot(this.allocateLocalVariableSlot(elementLv.type, fes.currentElement.name));
            this.compileGetValue(fes.expression);
            short expressionLv = this.getCodeContext().allocateLocalVariable((short)1);
            this.store(fes.expression, expressionType, expressionLv);
            this.consT(fes, 0);
            Java.LocalVariable indexLv = this.allocateLocalVariable(false, IClass.INT);
            this.store(fes, indexLv);
            StackMap beforeBody = this.getCodeContext().currentInserter().getStackMap();
            CodeContext var10002 = this.getCodeContext();
            Objects.requireNonNull(var10002);
            CodeContext.Offset toCondition = var10002.new BasicBlock();
            this.gotO(fes, toCondition);
            this.codeContext.currentInserter().setStackMap(beforeBody);
            fes.whereToContinue = null;
            CodeContext.Offset bodyOffset = this.getCodeContext().newBasicBlock();
            this.load(fes, expressionType, expressionLv);
            this.load(fes, indexLv);
            IType componentType = getComponentType(expressionType);

            assert componentType != null;

            this.xaload(fes.currentElement, componentType);
            this.assignmentConversion(fes.currentElement, componentType, elementLv.type, (Object)null);
            this.store(fes, elementLv);
            boolean bodyCcn = this.compile(fes.body);
            if (fes.whereToContinue != null) {
               fes.whereToContinue.set();
            }

            if (!bodyCcn && fes.whereToContinue == null) {
               this.warning("FUUR", "For update is unreachable", fes.getLocation());
            } else {
               this.iinc(fes, indexLv, "++");
            }

            fes.whereToContinue = null;
            toCondition.set();
            this.load(fes, indexLv);
            this.load(fes, expressionType, expressionLv);
            this.arraylength(fes);
            this.if_icmpxx(fes, 2, bodyOffset);
            this.getCodeContext().currentInserter().setStackMap(beforeStatement);
         } finally {
            this.getCodeContext().restoreLocalVariables();
         }

         if (fes.whereToBreak != null) {
            fes.whereToBreak.set();
            fes.whereToBreak = null;
         }
      } else if (isAssignableFrom(this.iClassLoader.TYPE_java_lang_Iterable, expressionType)) {
         this.getCodeContext().saveLocalVariables();

         try {
            StackMap beforeStatement = this.getCodeContext().currentInserter().getStackMap();
            this.compileGetValue(fes.expression);
            this.invokeMethod(fes.expression, this.iClassLoader.METH_java_lang_Iterable__iterator);
            Java.LocalVariable iteratorLv = this.allocateLocalVariable(false, this.iClassLoader.TYPE_java_util_Iterator);
            this.store(fes, iteratorLv);
            CodeContext var28 = this.getCodeContext();
            Objects.requireNonNull(var28);
            CodeContext.Offset toCondition = var28.new BasicBlock();
            StackMap smBeforeBody = this.codeContext.currentInserter().getStackMap();
            this.gotO(fes, toCondition);
            this.codeContext.currentInserter().setStackMap(smBeforeBody);
            Java.LocalVariable elementLv = this.getLocalVariable(fes.currentElement, false);
            elementLv.setSlot(this.allocateLocalVariableSlot(elementLv.type, fes.currentElement.name));
            fes.whereToContinue = null;
            CodeContext.Offset bodyOffset = this.getCodeContext().newBasicBlock();
            this.load(fes, iteratorLv);
            this.invokeMethod(fes.expression, this.iClassLoader.METH_java_util_Iterator__next);
            IClass boxedType = this.isBoxingConvertible(elementLv.type);
            if (boxedType != null) {
               this.checkcast(fes.currentElement, boxedType);
               this.unboxingConversion(fes.currentElement, boxedType, (IClass)elementLv.type);
            } else if (!this.tryAssignmentConversion(fes.currentElement, this.iClassLoader.TYPE_java_lang_Object, elementLv.type, (Object)null) && !this.tryNarrowingReferenceConversion(fes.currentElement, this.iClassLoader.TYPE_java_lang_Object, elementLv.type)) {
               throw new InternalCompilerException(fes.getLocation(), "Don't know how to convert to " + elementLv.type);
            }

            this.store(fes, elementLv);
            boolean bodyCcn = this.compile(fes.body);
            if (fes.whereToContinue != null) {
               fes.whereToContinue.set();
            }

            if (!bodyCcn && fes.whereToContinue == null) {
               this.warning("FUUR", "For update is unreachable", fes.getLocation());
            }

            fes.whereToContinue = null;
            toCondition.set();
            this.load(fes, iteratorLv);
            this.invokeMethod(fes.expression, this.iClassLoader.METH_java_util_Iterator__hasNext);
            this.ifxx(fes, 1, bodyOffset);
            this.getCodeContext().currentInserter().setStackMap(beforeStatement);
         } finally {
            this.getCodeContext().restoreLocalVariables();
         }

         if (fes.whereToBreak != null) {
            fes.whereToBreak.set();
            fes.whereToBreak = null;
         }
      } else {
         this.compileError("Cannot iterate over \"" + expressionType + "\"", fes.expression.getLocation());
      }

      return true;
   }

   private Java.LocalVariable allocateLocalVariable(boolean finaL, IType localVariableType) {
      Java.LocalVariable result = new Java.LocalVariable(finaL, localVariableType);
      result.setSlot(this.allocateLocalVariableSlot(localVariableType, (String)null));
      return result;
   }

   private Java.LocalVariable allocateLocalVariableAndMarkAsInitialized(boolean finaL, IType localVariableType) {
      Java.LocalVariable result = new Java.LocalVariable(finaL, localVariableType);
      result.setSlot(this.allocateLocalVariableSlotAndMarkAsInitialized(localVariableType, (String)null));
      return result;
   }

   private boolean compile2(Java.WhileStatement ws) throws CompileException {
      Object cvc = this.getConstantValue(ws.condition);
      if (cvc != NOT_CONSTANT) {
         if (Boolean.TRUE.equals(cvc)) {
            this.warning("WSTC", "Condition of WHILE statement is always TRUE; the proper way of declaring an unconditional loop is \"for (;;)\"", ws.getLocation());
            return this.compileUnconditionalLoop(ws, ws.body, (Java.Rvalue[])null);
         }

         this.warning("WSNR", "WHILE statement never repeats", ws.getLocation());
      }

      CodeContext var10002 = this.getCodeContext();
      Objects.requireNonNull(var10002);
      CodeContext.Offset wtc = var10002.new BasicBlock();
      this.gotO(ws, wtc);
      CodeContext.Offset bodyOffset = this.getCodeContext().newBasicBlock();
      CodeContext.Inserter bodyInserter = this.codeContext.newInserter();
      wtc.set();
      ws.whereToContinue = null;
      this.compileBoolean(ws.condition, bodyOffset, true);

      try {
         StackMap smBeforeBody = this.codeContext.currentInserter().getStackMap();
         this.codeContext.pushInserter(bodyInserter);
         this.codeContext.currentInserter().setStackMap(smBeforeBody);
         this.compile(ws.body);
         if (ws.whereToContinue != null) {
            ws.whereToContinue.set();
            ws.whereToContinue = null;
         }
      } finally {
         this.codeContext.popInserter();
      }

      if (ws.whereToBreak != null) {
         ws.whereToBreak.set();
         ws.whereToBreak = null;
      }

      return true;
   }

   private boolean compileUnconditionalLoop(Java.ContinuableStatement cs, Java.BlockStatement body, @Nullable Java.Rvalue[] update) throws CompileException {
      if (update != null) {
         return this.compileUnconditionalLoopWithUpdate(cs, body, update);
      } else {
         CodeContext.Offset wtc = cs.whereToContinue = this.getCodeContext().newBasicBlock();
         if (this.compile(body)) {
            this.gotO(cs, wtc);
         }

         cs.whereToContinue = null;
         CodeContext.Offset wtb = cs.whereToBreak;
         if (wtb == null) {
            return false;
         } else {
            wtb.set();
            cs.whereToBreak = null;
            return true;
         }
      }
   }

   private boolean compileUnconditionalLoopWithUpdate(Java.ContinuableStatement cs, Java.BlockStatement body, Java.Rvalue[] update) throws CompileException {
      cs.whereToContinue = null;
      CodeContext.Offset bodyOffset = this.getCodeContext().newBasicBlock();
      boolean bodyCcn = this.compile(body);
      if (cs.whereToContinue != null) {
         cs.whereToContinue.set();
      }

      if (!bodyCcn && cs.whereToContinue == null) {
         this.warning("LUUR", "Loop update is unreachable", update[0].getLocation());
      } else {
         for(Java.Rvalue rv : update) {
            this.compile(rv);
         }

         this.gotO(cs, bodyOffset);
         this.getCodeContext().currentInserter().setStackMap((StackMap)null);
      }

      cs.whereToContinue = null;
      CodeContext.Offset wtb = cs.whereToBreak;
      if (wtb == null) {
         return false;
      } else {
         wtb.set();
         cs.whereToBreak = null;
         return true;
      }
   }

   private boolean compile2(Java.LabeledStatement ls) throws CompileException {
      boolean canCompleteNormally = this.compile((Java.BlockStatement)ls.body);
      CodeContext.Offset wtb = ls.whereToBreak;
      if (wtb == null) {
         return canCompleteNormally;
      } else {
         if (!canCompleteNormally) {
            this.getCodeContext().currentInserter().setStackMap(wtb.getStackMap());
         }

         wtb.set();
         ls.whereToBreak = null;
         return true;
      }
   }

   private boolean compile2(Java.SwitchStatement ss) throws CompileException {
      short ssvLvIndex = -1;
      StackMap smBeforeSwitch = this.codeContext.currentInserter().getStackMap();
      IType switchExpressionType = this.getType(ss.condition);
      SwitchKind kind;
      if (this.iClassLoader.TYPE_java_lang_String == switchExpressionType) {
         kind = UnitCompiler.SwitchKind.STRING;
      } else if (isAssignableFrom(this.iClassLoader.TYPE_java_lang_Enum, switchExpressionType)) {
         kind = UnitCompiler.SwitchKind.ENUM;
      } else {
         kind = UnitCompiler.SwitchKind.INT;
      }

      TreeMap<Integer, CodeContext.Offset> caseLabelMap = new TreeMap();
      CodeContext.Offset defaultLabelOffset = null;
      CodeContext.Offset[] sbsgOffsets = new CodeContext.Offset[ss.sbsgs.size()];

      for(int i = 0; i < ss.sbsgs.size(); ++i) {
         Java.SwitchStatement.SwitchBlockStatementGroup sbsg = (Java.SwitchStatement.SwitchBlockStatementGroup)ss.sbsgs.get(i);
         CodeContext var10004 = this.getCodeContext();
         Objects.requireNonNull(var10004);
         sbsgOffsets[i] = var10004.new BasicBlock();

         for(Java.Rvalue caseLabel : sbsg.caseLabels) {
            switch (kind) {
               case ENUM:
                  if (!(caseLabel instanceof Java.AmbiguousName)) {
                     this.compileError("Case label must be an enum constant", caseLabel.getLocation());
                     Integer civ = 99;
                  } else {
                     String[] identifiers = ((Java.AmbiguousName)caseLabel).identifiers;
                     if (identifiers.length != 1) {
                        this.compileError("Case label must be a plain enum constant", caseLabel.getLocation());
                        Integer civ = 99;
                        continue;
                     }

                     String constantName = identifiers[0];
                     int ordinal = 0;
                     IClass.IField[] var17 = rawTypeOf(switchExpressionType).getDeclaredIFields();
                     int var18 = var17.length;
                     int var19 = 0;

                     Integer civ;
                     while(true) {
                        if (var19 >= var18) {
                           this.compileError("Unknown enum constant \"" + constantName + "\"", caseLabel.getLocation());
                           civ = 99;
                           break;
                        }

                        IClass.IField f = var17[var19];
                        if (f.getAccess() == Access.PUBLIC && f.isStatic()) {
                           if (f.getName().equals(constantName)) {
                              civ = ordinal;
                              break;
                           }

                           ++ordinal;
                        }

                        ++var19;
                     }

                     if (caseLabelMap.containsKey(civ)) {
                        this.compileError("Duplicate \"case\" switch label value", caseLabel.getLocation());
                     }

                     caseLabelMap.put(civ, sbsgOffsets[i]);
                  }
                  break;
               case INT:
                  Object cv = this.getConstantValue(caseLabel);
                  if (cv == NOT_CONSTANT) {
                     this.compileError("Value of 'case' label does not pose a constant value", caseLabel.getLocation());
                     Integer civ = 99;
                  } else {
                     Integer civ;
                     if (cv instanceof Integer) {
                        civ = (Integer)cv;
                     } else if (cv instanceof Number) {
                        civ = ((Number)cv).intValue();
                     } else if (cv instanceof Character) {
                        civ = Integer.valueOf((Character)cv);
                     } else {
                        this.compileError("Value of case label must be a char, byte, short or int constant", caseLabel.getLocation());
                        civ = 99;
                     }

                     if (caseLabelMap.containsKey(civ)) {
                        this.compileError("Duplicate \"case\" switch label value", caseLabel.getLocation());
                     }

                     caseLabelMap.put(civ, sbsgOffsets[i]);
                  }
                  break;
               case STRING:
                  Object cv = this.getConstantValue(caseLabel);
                  if (!(cv instanceof String)) {
                     this.compileError("Value of 'case' label is not a string constant", caseLabel.getLocation());
                     Integer civ = 99;
                  } else {
                     Integer civ = cv.hashCode();
                     if (!caseLabelMap.containsKey(civ)) {
                        var10004 = this.getCodeContext();
                        Objects.requireNonNull(var10004);
                        caseLabelMap.put(civ, var10004.new BasicBlock());
                     }
                  }
                  break;
               default:
                  throw new AssertionError(kind);
            }
         }

         if (sbsg.hasDefaultLabel) {
            if (defaultLabelOffset != null) {
               this.compileError("Duplicate \"default\" switch label", sbsg.getLocation());
            }

            defaultLabelOffset = sbsgOffsets[i];
         }
      }

      if (defaultLabelOffset == null) {
         defaultLabelOffset = this.getWhereToBreak(ss);
      }

      this.compileGetValue(ss.condition);
      switch (kind) {
         case ENUM:
            this.invokeMethod(ss, this.iClassLoader.METH_java_lang_Enum__ordinal);
            break;
         case INT:
            this.assignmentConversion(ss, switchExpressionType, IClass.INT, (Object)null);
            break;
         case STRING:
            this.dup(ss);
            ssvLvIndex = this.getCodeContext().allocateLocalVariable((short)1);
            this.store(ss, this.iClassLoader.TYPE_java_lang_String, ssvLvIndex);
            this.invokeMethod(ss, this.iClassLoader.METH_java_lang_String__hashCode);
            break;
         default:
            throw new AssertionError(kind);
      }

      if (caseLabelMap.isEmpty()) {
         this.pop(ss, IClass.INT);
      } else if ((Integer)caseLabelMap.firstKey() + caseLabelMap.size() >= (Integer)caseLabelMap.lastKey() - caseLabelMap.size()) {
         this.tableswitch(ss, caseLabelMap, defaultLabelOffset);
      } else {
         this.lookupswitch(ss, caseLabelMap, defaultLabelOffset);
      }

      if (kind == UnitCompiler.SwitchKind.STRING) {
         StackMap smBeforeSbsg = this.codeContext.currentInserter().getStackMap();

         for(Map.Entry e : caseLabelMap.entrySet()) {
            Integer caseHashCode = (Integer)e.getKey();
            CodeContext.Offset offset = (CodeContext.Offset)e.getValue();
            offset.set();
            Set<String> caseLabelValues = new HashSet();

            for(int i = 0; i < ss.sbsgs.size(); ++i) {
               Java.SwitchStatement.SwitchBlockStatementGroup sbsg = (Java.SwitchStatement.SwitchBlockStatementGroup)ss.sbsgs.get(i);
               this.codeContext.currentInserter().setStackMap(smBeforeSbsg);

               for(Java.Rvalue caseLabel : sbsg.caseLabels) {
                  String cv = (String)this.getConstantValue(caseLabel);

                  assert cv != null;

                  if (!caseLabelValues.add(cv)) {
                     this.compileError("Duplicate case label \"" + cv + "\"", caseLabel.getLocation());
                  }

                  if (cv.hashCode() == caseHashCode) {
                     this.load(sbsg, this.iClassLoader.TYPE_java_lang_String, ssvLvIndex);
                     this.consT(caseLabel, (String)cv);
                     this.invokeMethod(caseLabel, this.iClassLoader.METH_java_lang_String__equals__java_lang_Object);
                     this.ifxx(sbsg, 1, sbsgOffsets[i]);
                  }
               }
            }

            this.gotO(ss, defaultLabelOffset);
         }
      }

      boolean canCompleteNormally = true;

      for(int i = 0; i < ss.sbsgs.size(); ++i) {
         Java.SwitchStatement.SwitchBlockStatementGroup sbsg = (Java.SwitchStatement.SwitchBlockStatementGroup)ss.sbsgs.get(i);
         sbsgOffsets[i].set();
         this.codeContext.currentInserter().setStackMap(smBeforeSwitch);
         canCompleteNormally = true;

         for(Java.BlockStatement bs : sbsg.blockStatements) {
            if (!canCompleteNormally) {
               this.compileError("Statement is unreachable", bs.getLocation());
               break;
            }

            canCompleteNormally = this.compile(bs);
         }
      }

      CodeContext.Offset wtb = ss.whereToBreak;
      if (wtb == null) {
         return canCompleteNormally;
      } else {
         if (!canCompleteNormally) {
            this.codeContext.currentInserter().setStackMap(wtb.getStackMap());
         }

         wtb.set();
         ss.whereToBreak = null;
         return true;
      }
   }

   private boolean compile2(Java.BreakStatement bs) throws CompileException {
      Java.BreakableStatement brokenStatement = null;
      if (bs.label == null) {
         for(Java.Scope s = bs.getEnclosingScope(); s instanceof Java.Statement || s instanceof Java.CatchClause; s = s.getEnclosingScope()) {
            if (s instanceof Java.BreakableStatement) {
               brokenStatement = (Java.BreakableStatement)s;
               break;
            }
         }

         if (brokenStatement == null) {
            this.compileError("\"break\" statement is not enclosed by a breakable statement", bs.getLocation());
            return false;
         }
      } else {
         for(Java.Scope s = bs.getEnclosingScope(); s instanceof Java.Statement || s instanceof Java.CatchClause; s = s.getEnclosingScope()) {
            if (s instanceof Java.LabeledStatement) {
               Java.LabeledStatement ls = (Java.LabeledStatement)s;
               if (ls.label.equals(bs.label)) {
                  brokenStatement = ls;
                  break;
               }
            }
         }

         if (brokenStatement == null) {
            this.compileError("Statement \"break " + bs.label + "\" is not enclosed by a breakable statement with label \"" + bs.label + "\"", bs.getLocation());
            return false;
         }
      }

      this.leaveStatements(bs.getEnclosingScope(), brokenStatement.getEnclosingScope());
      this.gotO(bs, this.getWhereToBreak(brokenStatement));
      return false;
   }

   private boolean compile2(Java.ContinueStatement cs) throws CompileException {
      Java.ContinuableStatement continuedStatement = null;
      if (cs.label == null) {
         for(Java.Scope s = cs.getEnclosingScope(); s instanceof Java.Statement || s instanceof Java.CatchClause; s = s.getEnclosingScope()) {
            if (s instanceof Java.ContinuableStatement) {
               continuedStatement = (Java.ContinuableStatement)s;
               break;
            }
         }

         if (continuedStatement == null) {
            this.compileError("\"continue\" statement is not enclosed by a continuable statement", cs.getLocation());
            return false;
         }
      } else {
         for(Java.Scope s = cs.getEnclosingScope(); s instanceof Java.Statement || s instanceof Java.CatchClause; s = s.getEnclosingScope()) {
            if (s instanceof Java.LabeledStatement) {
               Java.LabeledStatement ls = (Java.LabeledStatement)s;
               if (ls.label.equals(cs.label)) {
                  Java.Statement st;
                  for(st = ls.body; st instanceof Java.LabeledStatement; st = ((Java.LabeledStatement)st).body) {
                  }

                  if (!(st instanceof Java.ContinuableStatement)) {
                     this.compileError("Labeled statement is not continuable", st.getLocation());
                     return false;
                  }

                  continuedStatement = (Java.ContinuableStatement)st;
                  break;
               }
            }
         }

         if (continuedStatement == null) {
            this.compileError("Statement \"continue " + cs.label + "\" is not enclosed by a continuable statement with label \"" + cs.label + "\"", cs.getLocation());
            return false;
         }
      }

      CodeContext.Offset wtc = continuedStatement.whereToContinue;
      if (wtc == null) {
         CodeContext var10003 = this.getCodeContext();
         Objects.requireNonNull(var10003);
         wtc = continuedStatement.whereToContinue = var10003.new BasicBlock();
      }

      this.leaveStatements(cs.getEnclosingScope(), continuedStatement.getEnclosingScope());
      this.gotO(cs, wtc);
      return false;
   }

   private boolean compile2(Java.AssertStatement as) throws CompileException {
      CodeContext var10002 = this.getCodeContext();
      Objects.requireNonNull(var10002);
      CodeContext.Offset end = var10002.new BasicBlock();

      try {
         this.compileBoolean(as.expression1, end, true);
         this.neW(as, this.iClassLoader.TYPE_java_lang_AssertionError);
         this.dup(as);
         Java.Rvalue[] arguments = as.expression2 == null ? new Java.Rvalue[0] : new Java.Rvalue[]{as.expression2};
         this.invokeConstructor(as, as, (Java.Rvalue)null, this.iClassLoader.TYPE_java_lang_AssertionError, arguments);
         this.getCodeContext().popUninitializedVariableOperand();
         this.getCodeContext().pushObjectOperand("Ljava/lang/AssertionError;");
         this.athrow(as);
      } finally {
         end.setBasicBlock();
      }

      return true;
   }

   private boolean compile2(Java.EmptyStatement es) {
      return true;
   }

   private boolean compile2(Java.ExpressionStatement ee) throws CompileException {
      try {
         this.compile(ee.rvalue);
         return true;
      } catch (InternalCompilerException ice) {
         throw new InternalCompilerException(ee.rvalue.getLocation(), (String)null, ice);
      }
   }

   private boolean compile2(Java.FieldDeclaration fd) throws CompileException {
      IClass declaringIClass = this.resolve(fd.getDeclaringType());

      for(Java.VariableDeclarator vd : fd.variableDeclarators) {
         Java.ArrayInitializerOrRvalue initializer = this.getNonConstantFinalInitializer(fd, vd);
         if (initializer != null) {
            try {
               this.addLineNumberOffset(vd);
               if (!declaringIClass.isInterface() && !fd.isStatic()) {
                  this.load(vd, declaringIClass, 0);
               }

               IClass fieldType = this.getRawType(fd.type);
               this.compile((Java.ArrayInitializerOrRvalue)initializer, (IType)this.iClassLoader.getArrayIClass(fieldType, vd.brackets));
               IClass.IField iField = declaringIClass.getDeclaredIField(vd.name);

               assert iField != null : fd.getDeclaringType() + " has no field " + vd.name;

               this.putfield(fd, iField);
            } catch (InternalCompilerException ice) {
               throw new InternalCompilerException(initializer.getLocation(), (String)null, ice);
            }
         }
      }

      return true;
   }

   private boolean compile2(Java.IfStatement is) throws CompileException {
      Java.BlockStatement ts = is.thenStatement;
      Java.BlockStatement es = (Java.BlockStatement)(is.elseStatement != null ? is.elseStatement : new Java.EmptyStatement(ts.getLocation()));
      Object cv = this.getConstantValue(is.condition);
      if (cv instanceof Boolean) {
         this.fakeCompile(is.condition);
         Java.BlockStatement seeingStatement;
         Java.BlockStatement blindStatement;
         if ((Boolean)cv) {
            seeingStatement = ts;
            blindStatement = es;
         } else {
            seeingStatement = es;
            blindStatement = ts;
         }

         CodeContext.Inserter ins = this.getCodeContext().newInserter();
         StackMap smBeforeSeeingStatement = this.codeContext.currentInserter().getStackMap();
         boolean ssccn = this.compile(seeingStatement);
         CodeContext.Offset afterSeeingStatement = this.codeContext.newOffset();
         this.codeContext.currentInserter().setStackMap(smBeforeSeeingStatement);
         boolean bsccn = this.fakeCompile(blindStatement);
         afterSeeingStatement.setStackMap();
         if (ssccn) {
            return true;
         } else if (!bsccn) {
            return false;
         } else {
            CodeContext.Offset off = this.getCodeContext().newBasicBlock();
            this.getCodeContext().pushInserter(ins);

            try {
               this.consT(is, (Object)Boolean.FALSE);
               this.ifxx(is, 1, off);
            } finally {
               this.getCodeContext().popInserter();
            }

            return true;
         }
      } else if (this.generatesCode(ts)) {
         if (!this.generatesCode(es)) {
            CodeContext var25 = this.getCodeContext();
            Objects.requireNonNull(var25);
            CodeContext.Offset end = var25.new BasicBlock();
            this.compileBoolean(is.condition, end, false);
            this.compile(ts);
            end.setBasicBlock();
            return true;
         } else {
            CodeContext var23 = this.getCodeContext();
            Objects.requireNonNull(var23);
            CodeContext.Offset eso = var23.new BasicBlock();
            var23 = this.getCodeContext();
            Objects.requireNonNull(var23);
            CodeContext.Offset end = var23.new BasicBlock();
            this.compileBoolean(is.condition, eso, false);
            boolean tsccn = this.compile(ts);
            if (tsccn) {
               this.gotO(is, end);
            }

            eso.setBasicBlock();
            boolean esccn = this.compile(es);
            if (!tsccn && !esccn) {
               return false;
            } else {
               end.setBasicBlock();
               return tsccn || esccn;
            }
         }
      } else if (this.generatesCode(es)) {
         CodeContext var10002 = this.getCodeContext();
         Objects.requireNonNull(var10002);
         CodeContext.Offset end = var10002.new BasicBlock();
         this.compileBoolean(is.condition, end, true);
         this.compile(es);
         end.setBasicBlock();
         return true;
      } else {
         IType conditionType = this.compileGetValue(is.condition);
         if (conditionType != IClass.BOOLEAN) {
            this.compileError("Not a boolean expression", is.getLocation());
         }

         this.pop(is, conditionType);
         return true;
      }
   }

   private boolean compile2(Java.LocalClassDeclarationStatement lcds) throws CompileException {
      Java.LocalClassDeclaration otherLcd = findLocalClassDeclaration(lcds, lcds.lcd.name);
      if (otherLcd != null && otherLcd != lcds.lcd) {
         this.compileError("Redeclaration of local class \"" + lcds.lcd.name + "\"; previously declared in " + otherLcd.getLocation());
      }

      this.compile((Java.TypeDeclaration)lcds.lcd);
      return true;
   }

   @Nullable
   private static Java.LocalClassDeclaration findLocalClassDeclaration(Java.Scope s, String name) {
      if (s instanceof Java.CompilationUnit) {
         return null;
      } else {
         while(true) {
            Java.Scope es = s.getEnclosingScope();
            if (es instanceof Java.CompilationUnit) {
               return null;
            }

            if (s instanceof Java.BlockStatement && (es instanceof Java.Block || es instanceof Java.FunctionDeclarator)) {
               Java.BlockStatement bs = (Java.BlockStatement)s;
               List<? extends Java.BlockStatement> statements = es instanceof Java.BlockStatement ? ((Java.Block)es).statements : ((Java.FunctionDeclarator)es).statements;
               if (statements != null) {
                  for(Java.BlockStatement bs2 : statements) {
                     if (bs2 instanceof Java.LocalClassDeclarationStatement) {
                        Java.LocalClassDeclarationStatement lcds = (Java.LocalClassDeclarationStatement)bs2;
                        if (lcds.lcd.name.equals(name)) {
                           return lcds.lcd;
                        }
                     }

                     if (bs2 == bs) {
                        break;
                     }
                  }
               }
            }

            s = es;
         }
      }
   }

   private boolean compile2(Java.LocalVariableDeclarationStatement lvds) throws CompileException {
      for(Java.VariableDeclarator vd : lvds.variableDeclarators) {
         try {
            Java.LocalVariable lv = this.getLocalVariable(lvds, vd);
            lv.setSlot(this.allocateLocalVariableSlot(lv.type, vd.name));
            Java.ArrayInitializerOrRvalue initializer = vd.initializer;
            if (initializer != null) {
               this.compile(initializer, lv.type);
               this.store(lvds, lv);
            }
         } catch (RuntimeException re) {
            throw new RuntimeException(vd.getLocation().toString(), re);
         }
      }

      return true;
   }

   private void compile(final Java.ArrayInitializerOrRvalue aiorv, final IType arrayType) throws CompileException {
      aiorv.accept(new Visitor.ArrayInitializerOrRvalueVisitor() {
         @Nullable
         public Void visitArrayInitializer(Java.ArrayInitializer ai) throws CompileException {
            UnitCompiler.this.compileGetValue(ai, arrayType);
            return null;
         }

         @Nullable
         public Void visitRvalue(Java.Rvalue rhs) throws CompileException {
            UnitCompiler.this.assignmentConversion(aiorv, UnitCompiler.this.compileGetValue(rhs), arrayType, UnitCompiler.this.getConstantValue(rhs));
            return null;
         }
      });
   }

   private ClassFile.StackMapTableAttribute.VerificationTypeInfo verificationTypeInfo(@Nullable IType type) {
      if (type == null) {
         return ClassFile.StackMapTableAttribute.NULL_VARIABLE_INFO;
      } else {
         String fd = rawTypeOf(type).getDescriptor();
         if (!"Z".equals(fd) && !"B".equals(fd) && !"C".equals(fd) && !"I".equals(fd) && !"S".equals(fd)) {
            if ("J".equals(fd)) {
               return ClassFile.StackMapTableAttribute.LONG_VARIABLE_INFO;
            } else if ("F".equals(fd)) {
               return ClassFile.StackMapTableAttribute.FLOAT_VARIABLE_INFO;
            } else if ("D".equals(fd)) {
               return ClassFile.StackMapTableAttribute.DOUBLE_VARIABLE_INFO;
            } else if (!Descriptor.isClassOrInterfaceReference(fd) && !Descriptor.isArrayReference(fd)) {
               throw new InternalCompilerException("Cannot make VerificationTypeInfo from \"" + fd + "\"");
            } else {
               return new ClassFile.StackMapTableAttribute.ObjectVariableInfo(this.getCodeContext().getClassFile().addConstantClassInfo(fd), fd);
            }
         } else {
            return ClassFile.StackMapTableAttribute.INTEGER_VARIABLE_INFO;
         }
      }
   }

   public Java.LocalVariable getLocalVariable(Java.LocalVariableDeclarationStatement lvds, Java.VariableDeclarator vd) throws CompileException {
      if (vd.localVariable != null) {
         return vd.localVariable;
      } else {
         Java.Type variableType = lvds.type;

         for(int k = 0; k < vd.brackets; ++k) {
            variableType = new Java.ArrayType(variableType);
         }

         return vd.localVariable = new Java.LocalVariable(lvds.isFinal(), this.getType(variableType));
      }
   }

   private boolean compile2(Java.ReturnStatement rs) throws CompileException {
      Java.FunctionDeclarator enclosingFunction = null;

      Java.Scope s;
      for(s = rs.getEnclosingScope(); s instanceof Java.Statement || s instanceof Java.CatchClause; s = s.getEnclosingScope()) {
      }

      enclosingFunction = (Java.FunctionDeclarator)s;
      Java.Rvalue orv = rs.returnValue;
      IType returnType = this.getReturnType(enclosingFunction);
      if (returnType == IClass.VOID) {
         if (orv != null) {
            this.compileError("Method must not return a value", rs.getLocation());
         }

         this.leaveStatements(rs.getEnclosingScope(), enclosingFunction);
         this.returN(rs);
         return false;
      } else if (orv == null) {
         this.compileError("Method must return a value", rs.getLocation());
         return false;
      } else {
         IType type = this.compileGetValue(orv);
         this.assignmentConversion(rs, type, returnType, this.getConstantValue(orv));
         this.leaveStatements(rs.getEnclosingScope(), enclosingFunction);
         this.xreturn(rs, returnType);
         return false;
      }
   }

   private boolean compile2(Java.SynchronizedStatement ss) throws CompileException {
      if (!isAssignableFrom(this.iClassLoader.TYPE_java_lang_Object, this.compileGetValue(ss.expression))) {
         this.compileError("Monitor object of \"synchronized\" statement is not a subclass of \"Object\"", ss.getLocation());
      }

      this.getCodeContext().saveLocalVariables();
      boolean canCompleteNormally = false;

      try {
         ss.monitorLvIndex = this.getCodeContext().allocateLocalVariable((short)1);
         this.dup(ss);
         this.store(ss, this.iClassLoader.TYPE_java_lang_Object, ss.monitorLvIndex);
         this.monitorenter(ss);
         CodeContext var10002 = this.getCodeContext();
         Objects.requireNonNull(var10002);
         CodeContext.Offset monitorExitOffset = var10002.new BasicBlock();
         CodeContext.Offset beginningOfBody = this.getCodeContext().newOffset();
         StackMap smBeforeBody = this.codeContext.currentInserter().getStackMap();
         canCompleteNormally = this.compile(ss.body);
         if (canCompleteNormally) {
            this.gotO(ss, monitorExitOffset);
         }

         StackMap save = this.codeContext.currentInserter().getStackMap();

         try {
            this.codeContext.currentInserter().setStackMap(smBeforeBody);
            this.getCodeContext().pushObjectOperand("Ljava/lang/Throwable;");
            CodeContext.Offset here = this.getCodeContext().newBasicBlock();
            this.getCodeContext().addExceptionTableEntry(beginningOfBody, here, here, (String)null);
            this.leave(ss);
            this.athrow(ss);
         } finally {
            this.codeContext.currentInserter().setStackMap(save);
         }

         if (canCompleteNormally) {
            monitorExitOffset.set();
            this.leave(ss);
         }
      } finally {
         this.getCodeContext().restoreLocalVariables();
      }

      return canCompleteNormally;
   }

   private boolean compile2(Java.ThrowStatement ts) throws CompileException {
      IType expressionType = this.compileGetValue(ts.expression);
      this.checkThrownException(ts, expressionType, ts.getEnclosingScope());
      this.athrow(ts);
      return false;
   }

   private boolean compile2(final Java.TryStatement ts) throws CompileException {
      return this.compileTryCatchFinallyWithResources(ts, ts.resources, new Compilable2() {
         public boolean compile() throws CompileException {
            return UnitCompiler.this.compile(ts.body);
         }
      }, ts.finallY);
   }

   private boolean compileTryCatchFinallyWithResources(final Java.TryStatement ts, List resources, final Compilable2 compileBody, @Nullable final Java.Block finallY) throws CompileException {
      if (resources.isEmpty()) {
         return this.compileTryCatchFinally(ts, compileBody, finallY);
      } else {
         Java.TryStatement.Resource firstResource = (Java.TryStatement.Resource)resources.get(0);
         final List<Java.TryStatement.Resource> followingResources = resources.subList(1, resources.size());
         Location loc = firstResource.getLocation();
         IClass tt = this.iClassLoader.TYPE_java_lang_Throwable;
         this.getCodeContext().saveLocalVariables();

         boolean var14;
         try {
            Java.LocalVariable identifier = (Java.LocalVariable)firstResource.accept(new Visitor.TryStatementResourceVisitor() {
               @Nullable
               public Java.LocalVariable visitLocalVariableDeclaratorResource(Java.TryStatement.LocalVariableDeclaratorResource lvdr) throws CompileException {
                  IType lvType = UnitCompiler.this.getType(lvdr.type);
                  Java.LocalVariable result = UnitCompiler.this.allocateLocalVariable(true, lvType);
                  Java.ArrayInitializerOrRvalue initializer = lvdr.variableDeclarator.initializer;

                  assert initializer != null;

                  UnitCompiler.this.compile(initializer, lvType);
                  UnitCompiler.this.store(ts, result);
                  return result;
               }

               @Nullable
               public Java.LocalVariable visitVariableAccessResource(Java.TryStatement.VariableAccessResource var) throws CompileException {
                  if (!UnitCompiler.this.options.contains(JaninoOption.EXPRESSIONS_IN_TRY_WITH_RESOURCES_ALLOWED) && !(var.variableAccess instanceof Java.AmbiguousName) && !(var.variableAccess instanceof Java.FieldAccessExpression) && !(var.variableAccess instanceof Java.SuperclassFieldAccessExpression)) {
                     throw new CompileException(var.variableAccess.getClass().getSimpleName() + " rvalue not allowed as a resource", var.getLocation());
                  } else {
                     Java.LocalVariable result = UnitCompiler.this.allocateLocalVariable(true, UnitCompiler.this.compileGetValue(var.variableAccess));
                     UnitCompiler.this.store(ts, result);
                     return result;
                  }
               }
            });

            assert identifier != null;

            Java.LocalVariable primaryExc = this.allocateLocalVariable(true, tt);
            this.consT(ts, (Object)null);
            this.store(ts, primaryExc);
            Java.CatchParameter suppressedException = new Java.CatchParameter(loc, false, new Java.Type[]{new Java.SimpleType(loc, tt)}, "___");
            Java.BlockStatement afterClose = (Java.BlockStatement)(this.iClassLoader.METH_java_lang_Throwable__addSuppressed == null ? new Java.EmptyStatement(loc) : new Java.ExpressionStatement(new Java.MethodInvocation(loc, new Java.LocalVariableAccess(loc, primaryExc), "addSuppressed", new Java.Rvalue[]{new Java.LocalVariableAccess(loc, this.getLocalVariable(suppressedException))})));
            Java.BlockStatement f = new Java.IfStatement(loc, new Java.BinaryOperation(loc, new Java.LocalVariableAccess(loc, identifier), "!=", new Java.NullLiteral(loc)), new Java.IfStatement(loc, new Java.BinaryOperation(loc, new Java.LocalVariableAccess(loc, primaryExc), "!=", new Java.NullLiteral(loc)), new Java.TryStatement(loc, new Java.ExpressionStatement(new Java.MethodInvocation(loc, new Java.LocalVariableAccess(loc, identifier), "close", new Java.Rvalue[0])), Collections.singletonList(new Java.CatchClause(loc, suppressedException, afterClose))), new Java.ExpressionStatement(new Java.MethodInvocation(loc, new Java.LocalVariableAccess(loc, identifier), "close", new Java.Rvalue[0]))));
            f.setEnclosingScope(ts);
            var14 = this.compileTryCatchFinally(ts, new Compilable2() {
               public boolean compile() throws CompileException {
                  return UnitCompiler.this.compileTryCatchFinallyWithResources(ts, followingResources, compileBody, finallY);
               }
            }, f);
         } finally {
            this.getCodeContext().restoreLocalVariables();
         }

         return var14;
      }
   }

   private boolean compileTryCatchFinally(Java.TryStatement ts, Compilable2 compileBody, @Nullable Java.BlockStatement finallY) throws CompileException {
      if (finallY == null) {
         CodeContext.Offset beginningOfBody = this.getCodeContext().newOffset();
         CodeContext var22 = this.getCodeContext();
         Objects.requireNonNull(var22);
         CodeContext.Offset afterStatement = var22.new BasicBlock();
         boolean canCompleteNormally = this.compileTryCatch(ts, compileBody, beginningOfBody, afterStatement);
         afterStatement.set();
         return canCompleteNormally;
      } else {
         CodeContext var10002 = this.getCodeContext();
         Objects.requireNonNull(var10002);
         CodeContext.Offset afterStatement = var10002.new BasicBlock();
         this.getCodeContext().saveLocalVariables();

         boolean canCompleteNormally;
         try {
            StackMap smBeforeBody = this.getCodeContext().currentInserter().getStackMap();
            CodeContext.Offset beginningOfBody = this.getCodeContext().newOffset();
            canCompleteNormally = this.compileTryCatch(ts, compileBody, beginningOfBody, afterStatement);
            StackMap smAfterBody = this.getCodeContext().currentInserter().getStackMap();
            this.getCodeContext().saveLocalVariables();

            try {
               this.getCodeContext().currentInserter().setStackMap(smBeforeBody);
               this.getCodeContext().pushObjectOperand("Ljava/lang/Throwable;");
               CodeContext.Offset here = this.getCodeContext().newBasicBlock();
               this.getCodeContext().addExceptionTableEntry(beginningOfBody, here, here, (String)null);
               short evi = this.getCodeContext().allocateLocalVariable((short)1);
               this.store(finallY, this.iClassLoader.TYPE_java_lang_Throwable, evi);
               if (this.compile(finallY)) {
                  this.load(finallY, this.iClassLoader.TYPE_java_lang_Throwable, evi);
                  this.athrow(finallY);
               }

               this.getCodeContext().currentInserter().setStackMap(smAfterBody);
            } finally {
               this.getCodeContext().restoreLocalVariables();
            }
         } finally {
            this.getCodeContext().restoreLocalVariables();
         }

         afterStatement.set();
         if (canCompleteNormally) {
            canCompleteNormally = this.compile(finallY);
         }

         return canCompleteNormally;
      }
   }

   private boolean compileTryCatch(Java.TryStatement tryStatement, Compilable2 compileBody, CodeContext.Offset beginningOfBody, CodeContext.Offset afterStatement) throws CompileException {
      for(Java.CatchClause catchClause : tryStatement.catchClauses) {
         catchClause.reachable = false;

         for(Java.Type t : catchClause.catchParameter.types) {
            IType caughtExceptionType = this.getType(t);
            catchClause.reachable |= isAssignableFrom(this.iClassLoader.TYPE_java_lang_Error, caughtExceptionType) || isAssignableFrom(caughtExceptionType, this.iClassLoader.TYPE_java_lang_Error) || isAssignableFrom(this.iClassLoader.TYPE_java_lang_RuntimeException, caughtExceptionType) || isAssignableFrom(caughtExceptionType, this.iClassLoader.TYPE_java_lang_RuntimeException);
         }
      }

      StackMap smBeforeBody = this.getCodeContext().currentInserter().getStackMap();
      boolean bodyCcn = compileBody.compile();
      CodeContext.Offset afterBody = this.getCodeContext().newOffset();
      StackMap smAfterBody = this.getCodeContext().currentInserter().getStackMap();
      if (bodyCcn) {
         this.gotO(tryStatement, afterStatement);
      }

      boolean catchCcn = false;
      if (beginningOfBody.offset != afterBody.offset) {
         for(int i = 0; i < tryStatement.catchClauses.size(); ++i) {
            this.getCodeContext().currentInserter().setStackMap(smBeforeBody);
            this.getCodeContext().saveLocalVariables();

            try {
               Java.CatchClause catchClause = (Java.CatchClause)tryStatement.catchClauses.get(i);
               if (catchClause.catchParameter.types.length != 1) {
                  throw compileException(catchClause, "Multi-type CATCH parameter NYI");
               }

               IClass caughtExceptionType = this.getRawType(catchClause.catchParameter.types[0]);
               if (!catchClause.reachable) {
                  this.compileError("Catch clause is unreachable", catchClause.getLocation());
               }

               this.getCodeContext().pushObjectOperand(caughtExceptionType.getDescriptor());
               Java.LocalVariableSlot exceptionVarSlot = this.allocateLocalVariableSlot(caughtExceptionType, catchClause.catchParameter.name);
               this.getLocalVariable(catchClause.catchParameter).setSlot(exceptionVarSlot);
               this.getCodeContext().addExceptionTableEntry(beginningOfBody, afterBody, this.getCodeContext().newBasicBlock(), caughtExceptionType.getDescriptor());
               this.store(catchClause, caughtExceptionType, exceptionVarSlot.getSlotIndex());
               if (this.compile(catchClause.body) && (tryStatement.finallY == null || this.compile((Java.BlockStatement)tryStatement.finallY))) {
                  catchCcn = true;
                  this.gotO(catchClause, afterStatement);
                  afterStatement.setStackMap();
               }
            } finally {
               this.getCodeContext().restoreLocalVariables();
            }
         }
      }

      this.getCodeContext().currentInserter().setStackMap(smAfterBody);
      return bodyCcn | catchCcn;
   }

   private void compile(Java.FunctionDeclarator fd, ClassFile classFile) throws CompileException {
      try {
         this.compile2(fd, classFile);
      } catch (ClassFile.ClassFileException cfe) {
         throw new ClassFile.ClassFileException("Compiling \"" + fd + "\": " + cfe.getMessage(), cfe);
      } catch (RuntimeException re) {
         throw new InternalCompilerException(fd.getLocation(), "Compiling \"" + fd + "\"", re);
      }
   }

   private void compile2(Java.FunctionDeclarator fd, ClassFile classFile) throws CompileException {
      if (this.getTargetVersion() < 8 && fd instanceof Java.MethodDeclarator && ((Java.MethodDeclarator)fd).isDefault()) {
         this.compileError("Default interface methods only available for target version 8+. Either use \"setTargetVersion(8)\", or \"-DdefaultTargetVersion=8\".", fd.getLocation());
      }

      ClassFile.MethodInfo mi;
      if (fd.getAccess() == Access.PRIVATE) {
         if (fd instanceof Java.MethodDeclarator && !((Java.MethodDeclarator)fd).isStatic() && !(fd.getDeclaringType() instanceof Java.InterfaceDeclaration)) {
            short accessFlags = changeAccessibility(this.accessFlags(fd.getModifiers()), (short)0);
            accessFlags = (short)(accessFlags | 8);
            mi = classFile.addMethodInfo(accessFlags, fd.name + '$', this.toIMethod((Java.MethodDeclarator)fd).getDescriptor().prependParameter(this.resolve(fd.getDeclaringType()).getDescriptor()));
         } else {
            short accessFlags = this.accessFlags(fd.getModifiers());
            if (!(fd.getDeclaringType() instanceof Java.InterfaceDeclaration)) {
               accessFlags = changeAccessibility(accessFlags, (short)0);
            }

            if (fd.formalParameters.variableArity) {
               accessFlags = (short)(accessFlags | 128);
            }

            mi = classFile.addMethodInfo(accessFlags, fd.name, this.toIInvocable(fd).getDescriptor());
         }
      } else {
         short accessFlags = this.accessFlags(fd.getModifiers());
         if (fd.formalParameters.variableArity) {
            accessFlags = (short)(accessFlags | 128);
         }

         if (fd.getDeclaringType() instanceof Java.InterfaceDeclaration) {
            accessFlags = (short)(accessFlags | 1);
            if (Mod.isStatic(accessFlags) && !"<clinit>".equals(fd.name)) {
               if (this.getTargetVersion() < 8) {
                  this.compileError("Static interface methods only available for target version 8+", fd.getLocation());
               }
            } else if (fd instanceof Java.MethodDeclarator && ((Java.MethodDeclarator)fd).isDefault()) {
               if (this.getTargetVersion() < 8) {
                  this.compileError("Default methods only available for target version 8+", fd.getLocation());
               }
            } else {
               accessFlags = (short)(accessFlags | 1024);
            }
         }

         mi = classFile.addMethodInfo(accessFlags, fd.name, this.toIInvocable(fd).getDescriptor());
      }

      this.compileAnnotations(fd.getAnnotations(), mi, classFile);
      if (fd.thrownExceptions.length > 0) {
         short eani = classFile.addConstantUtf8Info("Exceptions");
         List<Short> tecciis = new ArrayList();

         for(int i = 0; i < fd.thrownExceptions.length; ++i) {
            Java.Type te = fd.thrownExceptions[i];
            if (te instanceof Java.ReferenceType) {
               Java.ReferenceType rt = (Java.ReferenceType)te;
               if (rt.identifiers.length == 1 && LOOKS_LIKE_TYPE_PARAMETER.matcher(rt.identifiers[0]).matches()) {
                  continue;
               }
            }

            tecciis.add(classFile.addConstantClassInfo(this.getRawType(te).getDescriptor()));
         }

         short[] sa = new short[tecciis.size()];

         for(int i = 0; i < tecciis.size(); ++i) {
            sa[i] = (Short)tecciis.get(i);
         }

         mi.addAttribute(new ClassFile.ExceptionsAttribute(eani, sa));
      }

      if (fd.hasDeprecatedDocTag()) {
         mi.addAttribute(new ClassFile.DeprecatedAttribute(classFile.addConstantUtf8Info("Deprecated")));
      }

      if (fd instanceof Java.MethodDeclarator) {
         Java.ElementValue defaultValue = ((Java.MethodDeclarator)fd).defaultValue;
         if (defaultValue != null) {
            mi.addAttribute(new ClassFile.AnnotationDefaultAttribute(classFile.addConstantUtf8Info("AnnotationDefault"), this.compileElementValue(defaultValue, classFile, fd.type instanceof Java.ArrayType)));
         }
      }

      if (fd.getDeclaringType() instanceof Java.InterfaceDeclaration) {
         Java.MethodDeclarator md = (Java.MethodDeclarator)fd;
         if (md.getAccess() == Access.PRIVATE && this.getTargetVersion() < 9) {
            this.compileError("Private interface methods only available for target version 9+", fd.getLocation());
            return;
         }

         if (md.isStrictfp() && !md.isDefault() && !md.isStatic()) {
            this.compileError("Modifier strictfp only allowed for interface default methods and static interface methods", fd.getLocation());
            return;
         }
      }

      if (fd.getDeclaringType() instanceof Java.InterfaceDeclaration && !((Java.MethodDeclarator)fd).isStatic() && ((Java.MethodDeclarator)fd).getAccess() != Access.PRIVATE || fd instanceof Java.MethodDeclarator && ((Java.MethodDeclarator)fd).isAbstract() || fd instanceof Java.MethodDeclarator && ((Java.MethodDeclarator)fd).isNative()) {
         if (!((Java.MethodDeclarator)fd).isDefault()) {
            if (fd.statements != null) {
               this.compileError("Method must not declare a body", fd.getLocation());
            }

            return;
         }

         if (!(fd.getDeclaringType() instanceof Java.InterfaceDeclaration)) {
            this.compileError("Only interface method declarations may have the \"default\" modifier", fd.getLocation());
         } else if (((Java.MethodDeclarator)fd).isStatic()) {
            this.compileError("Static interface method declarations must not have the \"default\" modifier", fd.getLocation());
         } else if (fd.statements == null) {
            this.compileError("Default method declarations must have a body", fd.getLocation());
         }
      }

      CodeContext codeContext = new CodeContext(mi.getClassFile());
      CodeContext savedCodeContext = this.replaceCodeContext(codeContext);

      try {
         this.getCodeContext().saveLocalVariables();
         if (fd instanceof Java.MethodDeclarator) {
            Java.MethodDeclarator md = (Java.MethodDeclarator)fd;
            if (!md.isStatic()) {
               this.allocateLocalVariableSlotAndMarkAsInitialized(this.resolve(fd.getDeclaringType()), "this");
            }
         }

         if (fd instanceof Java.ConstructorDeclarator) {
            IClass rawThisType = this.resolve(fd.getDeclaringType());
            Java.LocalVariableSlot result = this.getCodeContext().allocateLocalVariable((short)1, "this", rawThisType);
            this.updateLocalVariableInCurrentStackMap(result.getSlotIndex(), ClassFile.StackMapTableAttribute.UNINITIALIZED_THIS_VARIABLE_INFO);
            Java.ConstructorDeclarator constructorDeclarator = (Java.ConstructorDeclarator)fd;
            if (fd.getDeclaringType() instanceof Java.EnumDeclaration) {
               Java.LocalVariable lv1 = this.allocateLocalVariableAndMarkAsInitialized(true, this.iClassLoader.TYPE_java_lang_String);
               constructorDeclarator.syntheticParameters.put("$name", lv1);
               Java.LocalVariable lv2 = this.allocateLocalVariableAndMarkAsInitialized(true, IClass.INT);
               constructorDeclarator.syntheticParameters.put("$ordinal", lv2);
            }

            for(IClass.IField sf : constructorDeclarator.getDeclaringClass().syntheticFields.values()) {
               Java.LocalVariable lv = this.allocateLocalVariableAndMarkAsInitialized(true, sf.getType());
               constructorDeclarator.syntheticParameters.put(sf.getName(), lv);
            }
         }

         this.buildLocalVariableMap(fd);
         this.codeContext.newOffset();
         if (fd instanceof Java.ConstructorDeclarator) {
            Java.ConstructorDeclarator cd = (Java.ConstructorDeclarator)fd;
            Java.ConstructorInvocation ci = cd.constructorInvocation;
            if (ci != null) {
               if (ci instanceof Java.SuperConstructorInvocation) {
                  this.assignSyntheticParametersToSyntheticFields(cd);
               }

               this.compile((Java.BlockStatement)ci);
               this.updateLocalVariableInCurrentStackMap((short)0, this.verificationTypeInfo(this.resolve(fd.getDeclaringType())));
               if (ci instanceof Java.SuperConstructorInvocation) {
                  this.initializeInstanceVariablesAndInvokeInstanceInitializers(cd);
               }
            } else {
               IClass superclass = this.resolve(cd.getDeclaringClass()).getSuperclass();
               if (superclass == null) {
                  throw new CompileException("\"" + cd + "\" has no superclass", cd.getLocation());
               }

               IClass outerClassOfSuperclass = superclass.getOuterIClass();
               Java.QualifiedThisReference qualification = null;
               if (outerClassOfSuperclass != null) {
                  qualification = new Java.QualifiedThisReference(cd.getLocation(), new Java.SimpleType(cd.getLocation(), outerClassOfSuperclass));
               }

               this.assignSyntheticParametersToSyntheticFields(cd);
               Java.Rvalue[] arguments;
               if (fd.getDeclaringType() instanceof Java.EnumDeclaration) {
                  Java.LocalVariableAccess nameAccess = new Java.LocalVariableAccess(cd.getLocation(), (Java.LocalVariable)cd.syntheticParameters.get("$name"));

                  assert nameAccess != null;

                  Java.LocalVariableAccess ordinalAccess = new Java.LocalVariableAccess(cd.getLocation(), (Java.LocalVariable)cd.syntheticParameters.get("$ordinal"));

                  assert ordinalAccess != null;

                  arguments = new Java.Rvalue[]{nameAccess, ordinalAccess};
               } else {
                  arguments = new Java.Rvalue[0];
               }

               Java.SuperConstructorInvocation sci = new Java.SuperConstructorInvocation(cd.getLocation(), qualification, arguments);
               sci.setEnclosingScope(fd);
               this.compile((Java.BlockStatement)sci);
               this.updateLocalVariableInCurrentStackMap((short)0, this.verificationTypeInfo(this.resolve(fd.getDeclaringType())));
               this.initializeInstanceVariablesAndInvokeInstanceInitializers(cd);
            }
         }

         List<? extends Java.BlockStatement> oss = fd.statements;
         if (oss == null) {
            this.compileError("Method must have a body", fd.getLocation());
            return;
         }

         if (this.compileStatements(oss)) {
            if (this.getReturnType(fd) != IClass.VOID) {
               this.compileError("Method must return a value", fd.getLocation());
            }

            this.returN(fd);
         }
      } finally {
         this.getCodeContext().restoreLocalVariables();
         this.replaceCodeContext(savedCodeContext);
      }

      if (this.compileErrorCount <= 0) {
         codeContext.fixUpAndRelocate();
         if (this.debugVars) {
            makeLocalVariableNames(codeContext, mi);
         }

         boolean hasThis = (Boolean)fd.accept(new Visitor.FunctionDeclaratorVisitor() {
            @Nullable
            public Boolean visitConstructorDeclarator(Java.ConstructorDeclarator cd) {
               return true;
            }

            @Nullable
            public Boolean visitMethodDeclarator(Java.MethodDeclarator md) {
               return !md.isStatic();
            }
         });

         try {
            mi.addAttribute(codeContext.newCodeAttribute((hasThis ? 1 : 0) + (fd instanceof Java.ConstructorDeclarator ? ((Java.ConstructorDeclarator)fd).syntheticParameters.size() : 0) + fd.formalParameters.parameters.length, this.debugLines, this.debugVars));
         } catch (Error e) {
            throw new InternalCompilerException(fd.getLocation(), (String)null, e);
         }
      }
   }

   private int getTargetVersion() {
      if (this.targetVersion == -1) {
         this.targetVersion = defaultTargetVersion;
         if (this.targetVersion == -1) {
            this.targetVersion = 6;
         }
      }

      return this.targetVersion;
   }

   private static void makeLocalVariableNames(CodeContext cc, ClassFile.MethodInfo mi) {
      ClassFile cf = mi.getClassFile();
      cf.addConstantUtf8Info("LocalVariableTable");

      for(Java.LocalVariableSlot slot : cc.getAllLocalVars()) {
         String localVariableName = slot.getName();
         if (localVariableName != null) {
            String typeName = rawTypeOf(slot.getType()).getDescriptor();
            cf.addConstantUtf8Info(typeName);
            cf.addConstantUtf8Info(localVariableName);
         }
      }

   }

   private void buildLocalVariableMap(Java.FunctionDeclarator fd) throws CompileException {
      Map<String, Java.LocalVariable> localVars = new HashMap();

      for(int i = 0; i < fd.formalParameters.parameters.length; ++i) {
         Java.FunctionDeclarator.FormalParameter fp = fd.formalParameters.parameters[i];
         Java.LocalVariable lv = this.getLocalVariable(fp, i == fd.formalParameters.parameters.length - 1 && fd.formalParameters.variableArity);
         lv.setSlot(this.allocateLocalVariableSlotAndMarkAsInitialized(lv.type, fp.name));
         if (localVars.put(fp.name, lv) != null) {
            this.compileError("Redefinition of parameter \"" + fp.name + "\"", fd.getLocation());
         }
      }

      fd.localVariables = localVars;
      if (fd instanceof Java.ConstructorDeclarator) {
         Java.ConstructorDeclarator cd = (Java.ConstructorDeclarator)fd;
         Java.ConstructorInvocation ci = cd.constructorInvocation;
         if (ci != null) {
            buildLocalVariableMap(ci, localVars);
         }
      }

      if (fd.statements != null) {
         for(Java.BlockStatement bs : fd.statements) {
            localVars = this.buildLocalVariableMap(bs, localVars);
         }
      }

   }

   private Java.LocalVariableSlot allocateLocalVariableSlot(IType localVariableType, @Nullable String localVariableName) {
      Java.LocalVariableSlot result = this.getCodeContext().allocateLocalVariable(Descriptor.size(rawTypeOf(localVariableType).getDescriptor()), localVariableName, localVariableType);
      return result;
   }

   private Java.LocalVariableSlot allocateLocalVariableSlotAndMarkAsInitialized(IType localVariableType, @Nullable String localVariableName) {
      Java.LocalVariableSlot result = this.allocateLocalVariableSlot(localVariableType, localVariableName);
      this.updateLocalVariableInCurrentStackMap(result.getSlotIndex(), this.verificationTypeInfo(localVariableType));
      return result;
   }

   private Map buildLocalVariableMap(Java.BlockStatement blockStatement, final Map localVars) throws CompileException {
      Map<String, Java.LocalVariable> result = (Map)blockStatement.accept(new Visitor.BlockStatementVisitor() {
         public Map visitAlternateConstructorInvocation(Java.AlternateConstructorInvocation aci) {
            UnitCompiler.buildLocalVariableMap((Java.ConstructorInvocation)aci, localVars);
            return localVars;
         }

         public Map visitBreakStatement(Java.BreakStatement bs) {
            UnitCompiler.buildLocalVariableMap((Java.Statement)bs, localVars);
            return localVars;
         }

         public Map visitContinueStatement(Java.ContinueStatement cs) {
            UnitCompiler.buildLocalVariableMap((Java.Statement)cs, localVars);
            return localVars;
         }

         public Map visitAssertStatement(Java.AssertStatement as) {
            UnitCompiler.buildLocalVariableMap((Java.Statement)as, localVars);
            return localVars;
         }

         public Map visitEmptyStatement(Java.EmptyStatement es) {
            UnitCompiler.buildLocalVariableMap((Java.Statement)es, localVars);
            return localVars;
         }

         public Map visitExpressionStatement(Java.ExpressionStatement es) {
            UnitCompiler.buildLocalVariableMap((Java.Statement)es, localVars);
            return localVars;
         }

         public Map visitFieldDeclaration(Java.FieldDeclaration fd) {
            UnitCompiler.buildLocalVariableMap((Java.Statement)fd, localVars);
            return localVars;
         }

         public Map visitReturnStatement(Java.ReturnStatement rs) {
            UnitCompiler.buildLocalVariableMap((Java.Statement)rs, localVars);
            return localVars;
         }

         public Map visitSuperConstructorInvocation(Java.SuperConstructorInvocation sci) {
            UnitCompiler.buildLocalVariableMap((Java.ConstructorInvocation)sci, localVars);
            return localVars;
         }

         public Map visitThrowStatement(Java.ThrowStatement ts) {
            UnitCompiler.buildLocalVariableMap((Java.Statement)ts, localVars);
            return localVars;
         }

         public Map visitLocalClassDeclarationStatement(Java.LocalClassDeclarationStatement lcds) {
            UnitCompiler.buildLocalVariableMap((Java.Statement)lcds, localVars);
            return localVars;
         }

         public Map visitBlock(Java.Block b) throws CompileException {
            UnitCompiler.this.buildLocalVariableMap(b, localVars);
            return localVars;
         }

         public Map visitDoStatement(Java.DoStatement ds) throws CompileException {
            UnitCompiler.this.buildLocalVariableMap(ds, localVars);
            return localVars;
         }

         public Map visitForStatement(Java.ForStatement fs) throws CompileException {
            UnitCompiler.this.buildLocalVariableMap(fs, localVars);
            return localVars;
         }

         public Map visitForEachStatement(Java.ForEachStatement fes) throws CompileException {
            UnitCompiler.this.buildLocalVariableMap(fes, localVars);
            return localVars;
         }

         public Map visitIfStatement(Java.IfStatement is) throws CompileException {
            UnitCompiler.this.buildLocalVariableMap(is, localVars);
            return localVars;
         }

         public Map visitInitializer(Java.Initializer i) throws CompileException {
            UnitCompiler.this.buildLocalVariableMap(i, localVars);
            return localVars;
         }

         public Map visitSwitchStatement(Java.SwitchStatement ss) throws CompileException {
            UnitCompiler.this.buildLocalVariableMap(ss, localVars);
            return localVars;
         }

         public Map visitSynchronizedStatement(Java.SynchronizedStatement ss) throws CompileException {
            UnitCompiler.this.buildLocalVariableMap(ss, localVars);
            return localVars;
         }

         public Map visitTryStatement(Java.TryStatement ts) throws CompileException {
            UnitCompiler.this.buildLocalVariableMap(ts, localVars);
            return localVars;
         }

         public Map visitWhileStatement(Java.WhileStatement ws) throws CompileException {
            UnitCompiler.this.buildLocalVariableMap(ws, localVars);
            return localVars;
         }

         public Map visitLabeledStatement(Java.LabeledStatement ls) throws CompileException {
            return UnitCompiler.this.buildLocalVariableMap(ls, localVars);
         }

         public Map visitLocalVariableDeclarationStatement(Java.LocalVariableDeclarationStatement lvds) throws CompileException {
            return UnitCompiler.this.buildLocalVariableMap(lvds, localVars);
         }
      });

      assert result != null;

      return result;
   }

   private static Map buildLocalVariableMap(Java.Statement s, Map localVars) {
      return s.localVariables = localVars;
   }

   private static Map buildLocalVariableMap(Java.ConstructorInvocation ci, Map localVars) {
      return ci.localVariables = localVars;
   }

   private void buildLocalVariableMap(Java.Block block, Map localVars) throws CompileException {
      block.localVariables = localVars;

      for(Java.BlockStatement bs : block.statements) {
         localVars = this.buildLocalVariableMap(bs, localVars);
      }

   }

   private void buildLocalVariableMap(Java.DoStatement ds, Map localVars) throws CompileException {
      ds.localVariables = localVars;
      this.buildLocalVariableMap(ds.body, localVars);
   }

   private void buildLocalVariableMap(Java.ForStatement fs, Map localVars) throws CompileException {
      Map<String, Java.LocalVariable> inner = localVars;
      if (fs.init != null) {
         inner = this.buildLocalVariableMap(fs.init, localVars);
      }

      fs.localVariables = inner;
      this.buildLocalVariableMap(fs.body, inner);
   }

   private void buildLocalVariableMap(Java.ForEachStatement fes, Map localVars) throws CompileException {
      Map<String, Java.LocalVariable> vars = new HashMap();
      vars.putAll(localVars);
      Java.LocalVariable elementLv = this.getLocalVariable(fes.currentElement, false);
      vars.put(fes.currentElement.name, elementLv);
      fes.localVariables = vars;
      this.buildLocalVariableMap(fes.body, vars);
   }

   private void buildLocalVariableMap(Java.IfStatement is, Map localVars) throws CompileException {
      is.localVariables = localVars;
      this.buildLocalVariableMap(is.thenStatement, localVars);
      if (is.elseStatement != null) {
         this.buildLocalVariableMap(is.elseStatement, localVars);
      }

   }

   private void buildLocalVariableMap(Java.Initializer i, Map localVars) throws CompileException {
      this.buildLocalVariableMap(i.block, localVars);
   }

   private void buildLocalVariableMap(Java.SwitchStatement ss, Map localVars) throws CompileException {
      ss.localVariables = localVars;
      Map<String, Java.LocalVariable> vars = localVars;

      for(Java.SwitchStatement.SwitchBlockStatementGroup sbsg : ss.sbsgs) {
         for(Java.BlockStatement bs : sbsg.blockStatements) {
            vars = this.buildLocalVariableMap(bs, vars);
         }
      }

   }

   private void buildLocalVariableMap(Java.SynchronizedStatement ss, Map localVars) throws CompileException {
      ss.localVariables = localVars;
      this.buildLocalVariableMap(ss.body, localVars);
   }

   private void buildLocalVariableMap(Java.TryStatement ts, Map localVars) throws CompileException {
      ts.localVariables = localVars;
      this.buildLocalVariableMap(ts.body, localVars);

      for(Java.CatchClause cc : ts.catchClauses) {
         this.buildLocalVariableMap(cc, localVars);
      }

      if (ts.finallY != null) {
         this.buildLocalVariableMap(ts.finallY, localVars);
      }

   }

   private void buildLocalVariableMap(Java.WhileStatement ws, Map localVars) throws CompileException {
      ws.localVariables = localVars;
      this.buildLocalVariableMap(ws.body, localVars);
   }

   private Map buildLocalVariableMap(Java.LabeledStatement ls, Map localVars) throws CompileException {
      ls.localVariables = localVars;
      return this.buildLocalVariableMap((Java.BlockStatement)ls.body, localVars);
   }

   private Map buildLocalVariableMap(Java.LocalVariableDeclarationStatement lvds, Map localVars) throws CompileException {
      Map<String, Java.LocalVariable> newVars = new HashMap();
      newVars.putAll(localVars);

      for(Java.VariableDeclarator vd : lvds.variableDeclarators) {
         Java.LocalVariable lv = this.getLocalVariable(lvds, vd);
         if (newVars.put(vd.name, lv) != null) {
            this.compileError("Redefinition of local variable \"" + vd.name + "\" ", vd.getLocation());
         }
      }

      lvds.localVariables = newVars;
      return newVars;
   }

   protected void buildLocalVariableMap(Java.CatchClause catchClause, Map localVars) throws CompileException {
      Map<String, Java.LocalVariable> vars = new HashMap();
      vars.putAll(localVars);
      Java.LocalVariable lv = this.getLocalVariable(catchClause.catchParameter);
      vars.put(catchClause.catchParameter.name, lv);
      this.buildLocalVariableMap(catchClause.body, vars);
   }

   public Java.LocalVariable getLocalVariable(Java.FunctionDeclarator.FormalParameter parameter) throws CompileException {
      return this.getLocalVariable(parameter, false);
   }

   public Java.LocalVariable getLocalVariable(Java.FunctionDeclarator.FormalParameter parameter, boolean isVariableArityParameter) throws CompileException {
      if (parameter.localVariable != null) {
         return parameter.localVariable;
      } else {
         assert parameter.type != null;

         IType parameterType = this.getType(parameter.type);
         if (isVariableArityParameter) {
            parameterType = this.iClassLoader.getArrayIClass(rawTypeOf(parameterType));
         }

         return parameter.localVariable = new Java.LocalVariable(parameter.isFinal(), parameterType);
      }
   }

   public Java.LocalVariable getLocalVariable(Java.CatchParameter parameter) throws CompileException {
      if (parameter.localVariable != null) {
         return parameter.localVariable;
      } else if (parameter.types.length != 1) {
         throw compileException(parameter, "Multi-type CATCH parameters NYI");
      } else {
         IType parameterType = this.getType(parameter.types[0]);
         return parameter.localVariable = new Java.LocalVariable(parameter.finaL, parameterType);
      }
   }

   private void fakeCompile(Java.Rvalue rv) throws CompileException {
      CodeContext.Offset from = this.getCodeContext().newOffset();
      StackMap savedStackMap = this.getCodeContext().currentInserter().getStackMap();
      this.compileContext(rv);
      this.compileGet(rv);
      CodeContext.Offset to = this.getCodeContext().newOffset();
      this.getCodeContext().removeCode(from, to.next);
      this.getCodeContext().currentInserter().setStackMap(savedStackMap);
   }

   private void compile(Java.Rvalue rv) throws CompileException {
      rv.accept(new Visitor.RvalueVisitor() {
         @Nullable
         public Void visitLvalue(Java.Lvalue lv) throws CompileException {
            lv.accept(new Visitor.LvalueVisitor() {
               @Nullable
               public Void visitAmbiguousName(Java.AmbiguousName an) throws CompileException {
                  UnitCompiler.this.compile2((Java.Rvalue)an);
                  return null;
               }

               @Nullable
               public Void visitArrayAccessExpression(Java.ArrayAccessExpression aae) throws CompileException {
                  UnitCompiler.this.compile2((Java.Rvalue)aae);
                  return null;
               }

               @Nullable
               public Void visitFieldAccess(Java.FieldAccess fa) throws CompileException {
                  UnitCompiler.this.compile2((Java.Rvalue)fa);
                  return null;
               }

               @Nullable
               public Void visitFieldAccessExpression(Java.FieldAccessExpression fae) throws CompileException {
                  UnitCompiler.this.compile2((Java.Rvalue)fae);
                  return null;
               }

               @Nullable
               public Void visitSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression scfae) throws CompileException {
                  UnitCompiler.this.compile2((Java.Rvalue)scfae);
                  return null;
               }

               @Nullable
               public Void visitLocalVariableAccess(Java.LocalVariableAccess lva) throws CompileException {
                  UnitCompiler.this.compile2((Java.Rvalue)lva);
                  return null;
               }

               @Nullable
               public Void visitParenthesizedExpression(Java.ParenthesizedExpression pe) throws CompileException {
                  UnitCompiler.this.compile2(pe);
                  return null;
               }
            });
            return null;
         }

         @Nullable
         public Void visitArrayLength(Java.ArrayLength al) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)al);
            return null;
         }

         @Nullable
         public Void visitAssignment(Java.Assignment a) throws CompileException {
            UnitCompiler.this.compile2(a);
            return null;
         }

         @Nullable
         public Void visitUnaryOperation(Java.UnaryOperation uo) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)uo);
            return null;
         }

         @Nullable
         public Void visitBinaryOperation(Java.BinaryOperation bo) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)bo);
            return null;
         }

         @Nullable
         public Void visitCast(Java.Cast c) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)c);
            return null;
         }

         @Nullable
         public Void visitClassLiteral(Java.ClassLiteral cl) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)cl);
            return null;
         }

         @Nullable
         public Void visitConditionalExpression(Java.ConditionalExpression ce) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)ce);
            return null;
         }

         @Nullable
         public Void visitCrement(Java.Crement c) throws CompileException {
            UnitCompiler.this.compile2(c);
            return null;
         }

         @Nullable
         public Void visitInstanceof(Java.Instanceof io) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)io);
            return null;
         }

         @Nullable
         public Void visitMethodInvocation(Java.MethodInvocation mi) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)mi);
            return null;
         }

         @Nullable
         public Void visitSuperclassMethodInvocation(Java.SuperclassMethodInvocation smi) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)smi);
            return null;
         }

         @Nullable
         public Void visitIntegerLiteral(Java.IntegerLiteral il) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)il);
            return null;
         }

         @Nullable
         public Void visitFloatingPointLiteral(Java.FloatingPointLiteral fpl) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)fpl);
            return null;
         }

         @Nullable
         public Void visitBooleanLiteral(Java.BooleanLiteral bl) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)bl);
            return null;
         }

         @Nullable
         public Void visitCharacterLiteral(Java.CharacterLiteral cl) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)cl);
            return null;
         }

         @Nullable
         public Void visitStringLiteral(Java.StringLiteral sl) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)sl);
            return null;
         }

         @Nullable
         public Void visitNullLiteral(Java.NullLiteral nl) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)nl);
            return null;
         }

         @Nullable
         public Void visitSimpleConstant(Java.SimpleConstant sl) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)sl);
            return null;
         }

         @Nullable
         public Void visitNewAnonymousClassInstance(Java.NewAnonymousClassInstance naci) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)naci);
            return null;
         }

         @Nullable
         public Void visitNewArray(Java.NewArray na) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)na);
            return null;
         }

         @Nullable
         public Void visitNewInitializedArray(Java.NewInitializedArray nia) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)nia);
            return null;
         }

         @Nullable
         public Void visitNewClassInstance(Java.NewClassInstance nci) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)nci);
            return null;
         }

         @Nullable
         public Void visitParameterAccess(Java.ParameterAccess pa) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)pa);
            return null;
         }

         @Nullable
         public Void visitQualifiedThisReference(Java.QualifiedThisReference qtr) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)qtr);
            return null;
         }

         @Nullable
         public Void visitThisReference(Java.ThisReference tr) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)tr);
            return null;
         }

         @Nullable
         public Void visitLambdaExpression(Java.LambdaExpression le) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)le);
            return null;
         }

         @Nullable
         public Void visitMethodReference(Java.MethodReference mr) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)mr);
            return null;
         }

         @Nullable
         public Void visitInstanceCreationReference(Java.ClassInstanceCreationReference cicr) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)cicr);
            return null;
         }

         @Nullable
         public Void visitArrayCreationReference(Java.ArrayCreationReference acr) throws CompileException {
            UnitCompiler.this.compile2((Java.Rvalue)acr);
            return null;
         }
      });
   }

   private void compile2(Java.Rvalue rv) throws CompileException {
      this.pop(rv, this.compileGetValue(rv));
   }

   private void compile2(Java.Assignment a) throws CompileException {
      if (a.operator == "=") {
         this.compileContext(a.lhs);
         this.assignmentConversion(a, this.compileGetValue(a.rhs), this.getType(a.lhs), this.getConstantValue(a.rhs));
         this.compileSet(a.lhs);
      } else {
         int lhsCs = this.compileContext(a.lhs);
         this.dupn(a.lhs, lhsCs);
         IType lhsType = this.compileGet(a.lhs);
         IType resultType = this.compileArithmeticBinaryOperation(a, lhsType, a.operator.substring(0, a.operator.length() - 1).intern(), a.rhs);
         if (!this.tryIdentityConversion(resultType, lhsType) && !this.tryNarrowingPrimitiveConversion(a, resultType, lhsType) && !this.tryBoxingConversion(a, resultType, lhsType)) {
            this.compileError("Operand types unsuitable for \"" + a.operator + "\"", a.getLocation());
         }

         this.compileSet(a.lhs);
      }
   }

   private void compile2(Java.Crement c) throws CompileException {
      Java.LocalVariable lv = this.isIntLv(c);
      if (lv != null) {
         this.iinc(c, lv, c.operator);
      } else {
         int operandCs = this.compileContext(c.operand);
         this.dupn(c, operandCs);
         IType type = this.compileGet(c.operand);
         IClass promotedType = this.unaryNumericPromotion(c, type);
         this.consT(c, promotedType, 1);
         if (c.operator == "++") {
            this.add(c);
         } else if (c.operator == "--") {
            this.sub(c);
         } else {
            this.compileError("Unexpected operator \"" + c.operator + "\"", c.getLocation());
         }

         this.reverseUnaryNumericPromotion(c, promotedType, type);
         this.compileSet(c.operand);
      }
   }

   private void compile2(Java.ParenthesizedExpression pe) throws CompileException {
      this.compile(pe.value);
   }

   private boolean compile2(Java.AlternateConstructorInvocation aci) throws CompileException {
      Java.ConstructorDeclarator declaringConstructor = (Java.ConstructorDeclarator)aci.getEnclosingScope();
      IClass declaringIClass = this.resolve(declaringConstructor.getDeclaringClass());
      this.load(aci, declaringIClass, 0);
      if (declaringIClass.getOuterIClass() != null) {
         this.load(aci, declaringIClass.getOuterIClass(), 1);
      }

      this.invokeConstructor(aci, declaringConstructor, (Java.Rvalue)null, declaringIClass, aci.arguments);
      return true;
   }

   private boolean compile2(Java.SuperConstructorInvocation sci) throws CompileException {
      Java.ConstructorDeclarator declaringConstructor = (Java.ConstructorDeclarator)sci.getEnclosingScope();
      Java.AbstractClassDeclaration declaringClass = declaringConstructor.getDeclaringClass();
      IClass declaringIClass = this.resolve(declaringClass);
      IClass superclass = declaringIClass.getSuperclass();
      this.load(sci, declaringIClass, 0);
      if (superclass == null) {
         throw new CompileException("Class has no superclass", sci.getLocation());
      } else {
         Java.Rvalue enclosingInstance;
         if (sci.qualification != null) {
            enclosingInstance = sci.qualification;
         } else {
            IClass outerIClassOfSuperclass = superclass.getOuterIClass();
            if (outerIClassOfSuperclass == null) {
               enclosingInstance = null;
            } else {
               enclosingInstance = new Java.QualifiedThisReference(sci.getLocation(), new Java.SimpleType(sci.getLocation(), outerIClassOfSuperclass));
               enclosingInstance.setEnclosingScope(sci);
            }
         }

         this.invokeConstructor(sci, declaringConstructor, enclosingInstance, superclass, sci.arguments);
         return true;
      }
   }

   private void compileBoolean(Java.Rvalue rv, final CodeContext.Offset dst, final boolean orientation) throws CompileException {
      rv.accept(new Visitor.RvalueVisitor() {
         @Nullable
         public Void visitLvalue(Java.Lvalue lv) throws CompileException {
            lv.accept(new Visitor.LvalueVisitor() {
               @Nullable
               public Void visitAmbiguousName(Java.AmbiguousName an) throws CompileException {
                  UnitCompiler.this.compileBoolean2((Java.Rvalue)an, dst, orientation);
                  return null;
               }

               @Nullable
               public Void visitArrayAccessExpression(Java.ArrayAccessExpression aae) throws CompileException {
                  UnitCompiler.this.compileBoolean2((Java.Rvalue)aae, dst, orientation);
                  return null;
               }

               @Nullable
               public Void visitFieldAccess(Java.FieldAccess fa) throws CompileException {
                  UnitCompiler.this.compileBoolean2((Java.Rvalue)fa, dst, orientation);
                  return null;
               }

               @Nullable
               public Void visitFieldAccessExpression(Java.FieldAccessExpression fae) throws CompileException {
                  UnitCompiler.this.compileBoolean2((Java.Rvalue)fae, dst, orientation);
                  return null;
               }

               @Nullable
               public Void visitSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression scfae) throws CompileException {
                  UnitCompiler.this.compileBoolean2((Java.Rvalue)scfae, dst, orientation);
                  return null;
               }

               @Nullable
               public Void visitLocalVariableAccess(Java.LocalVariableAccess lva) throws CompileException {
                  UnitCompiler.this.compileBoolean2((Java.Rvalue)lva, dst, orientation);
                  return null;
               }

               @Nullable
               public Void visitParenthesizedExpression(Java.ParenthesizedExpression pe) throws CompileException {
                  UnitCompiler.this.compileBoolean2(pe, dst, orientation);
                  return null;
               }
            });
            return null;
         }

         @Nullable
         public Void visitArrayLength(Java.ArrayLength al) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)al, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitAssignment(Java.Assignment a) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)a, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitUnaryOperation(Java.UnaryOperation uo) throws CompileException {
            UnitCompiler.this.compileBoolean2(uo, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitBinaryOperation(Java.BinaryOperation bo) throws CompileException {
            UnitCompiler.this.compileBoolean2(bo, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitCast(Java.Cast c) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)c, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitClassLiteral(Java.ClassLiteral cl) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)cl, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitConditionalExpression(Java.ConditionalExpression ce) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)ce, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitCrement(Java.Crement c) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)c, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitInstanceof(Java.Instanceof io) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)io, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitMethodInvocation(Java.MethodInvocation mi) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)mi, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitSuperclassMethodInvocation(Java.SuperclassMethodInvocation smi) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)smi, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitIntegerLiteral(Java.IntegerLiteral il) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)il, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitFloatingPointLiteral(Java.FloatingPointLiteral fpl) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)fpl, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitBooleanLiteral(Java.BooleanLiteral bl) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)bl, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitCharacterLiteral(Java.CharacterLiteral cl) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)cl, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitStringLiteral(Java.StringLiteral sl) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)sl, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitNullLiteral(Java.NullLiteral nl) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)nl, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitSimpleConstant(Java.SimpleConstant sl) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)sl, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitNewAnonymousClassInstance(Java.NewAnonymousClassInstance naci) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)naci, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitNewArray(Java.NewArray na) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)na, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitNewInitializedArray(Java.NewInitializedArray nia) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)nia, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitNewClassInstance(Java.NewClassInstance nci) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)nci, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitParameterAccess(Java.ParameterAccess pa) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)pa, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitQualifiedThisReference(Java.QualifiedThisReference qtr) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)qtr, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitThisReference(Java.ThisReference tr) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)tr, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitLambdaExpression(Java.LambdaExpression le) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)le, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitMethodReference(Java.MethodReference mr) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)mr, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitInstanceCreationReference(Java.ClassInstanceCreationReference cicr) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)cicr, dst, orientation);
            return null;
         }

         @Nullable
         public Void visitArrayCreationReference(Java.ArrayCreationReference acr) throws CompileException {
            UnitCompiler.this.compileBoolean2((Java.Rvalue)acr, dst, orientation);
            return null;
         }
      });
   }

   private void compileBoolean2(Java.Rvalue rv, CodeContext.Offset dst, boolean orientation) throws CompileException {
      IType type = this.compileGetValue(rv);
      IClassLoader icl = this.iClassLoader;
      if (type == icl.TYPE_java_lang_Boolean) {
         this.unboxingConversion(rv, icl.TYPE_java_lang_Boolean, IClass.BOOLEAN);
      } else if (type != IClass.BOOLEAN) {
         this.compileError("Not a boolean expression", rv.getLocation());
      }

      this.ifxx(rv, orientation ? 1 : 0, dst);
   }

   private void compileBoolean2(Java.UnaryOperation ue, CodeContext.Offset dst, boolean orientation) throws CompileException {
      if (ue.operator == "!") {
         this.compileBoolean(ue.operand, dst, !orientation);
      } else {
         this.compileError("Boolean expression expected", ue.getLocation());
      }
   }

   private void compileBoolean2(Java.BinaryOperation bo, CodeContext.Offset dst, boolean orientation) throws CompileException {
      if (bo.operator != "|" && bo.operator != "^" && bo.operator != "&") {
         if (bo.operator != "||" && bo.operator != "&&") {
            int opIdx = bo.operator == "==" ? 0 : (bo.operator == "!=" ? 1 : (bo.operator == "<" ? 2 : (bo.operator == ">=" ? 3 : (bo.operator == ">" ? 4 : (bo.operator == "<=" ? 5 : Integer.MIN_VALUE)))));
            if (opIdx != Integer.MIN_VALUE) {
               boolean lhsIsNull = this.getConstantValue(bo.lhs) == null;
               boolean rhsIsNull = this.getConstantValue(bo.rhs) == null;
               if (lhsIsNull || rhsIsNull) {
                  if (bo.operator != "==" && bo.operator != "!=") {
                     this.compileError("Operator \"" + bo.operator + "\" not allowed on operand \"null\"", bo.getLocation());
                  }

                  if (!lhsIsNull) {
                     IType lhsType = this.compileGetValue(bo.lhs);
                     if (rawTypeOf(lhsType).isPrimitive()) {
                        this.compileError("Cannot compare primitive type \"" + lhsType.toString() + "\" with \"null\"", bo.getLocation());
                     }
                  } else if (!rhsIsNull) {
                     IType rhsType = this.compileGetValue(bo.rhs);
                     if (rawTypeOf(rhsType).isPrimitive()) {
                        this.compileError("Cannot compare \"null\" with primitive type \"" + rhsType.toString() + "\"", bo.getLocation());
                     }
                  } else {
                     this.consT(bo, (Object)null);
                  }

                  switch (!orientation ? opIdx ^ 1 : opIdx) {
                     case 0:
                        this.ifnull(bo, dst);
                        break;
                     case 1:
                        this.ifnonnull(bo, dst);
                        break;
                     default:
                        throw new AssertionError(opIdx);
                  }

                  return;
               }

               IType lhsType = this.compileGetValue(bo.lhs);
               IType rhsType = this.getType(bo.rhs);
               if (rawTypeOf(this.getUnboxedType(lhsType)).isPrimitiveNumeric() && rawTypeOf(this.getUnboxedType(rhsType)).isPrimitiveNumeric() && (bo.operator != "==" && bo.operator != "!=" || rawTypeOf(lhsType).isPrimitive() || rawTypeOf(rhsType).isPrimitive())) {
                  IClass promotedType = this.binaryNumericPromotionType(bo, this.getUnboxedType(lhsType), this.getUnboxedType(rhsType));
                  this.numericPromotion(bo.lhs, this.convertToPrimitiveNumericType(bo.lhs, lhsType), promotedType);
                  this.compileGetValue(bo.rhs);
                  this.numericPromotion(bo.rhs, this.convertToPrimitiveNumericType(bo.rhs, rhsType), promotedType);
                  this.ifNumeric(bo, opIdx, dst, orientation);
                  return;
               }

               if (lhsType == IClass.BOOLEAN && this.getUnboxedType(rhsType) == IClass.BOOLEAN || rhsType == IClass.BOOLEAN && this.getUnboxedType(lhsType) == IClass.BOOLEAN) {
                  if (bo.operator != "==" && bo.operator != "!=") {
                     this.compileError("Operator \"" + bo.operator + "\" not allowed on boolean operands", bo.getLocation());
                  }

                  IClassLoader icl = this.iClassLoader;
                  if (lhsType == icl.TYPE_java_lang_Boolean) {
                     this.unboxingConversion(bo, icl.TYPE_java_lang_Boolean, IClass.BOOLEAN);
                  }

                  this.compileGetValue(bo.rhs);
                  if (rhsType == icl.TYPE_java_lang_Boolean) {
                     this.unboxingConversion(bo, icl.TYPE_java_lang_Boolean, IClass.BOOLEAN);
                  }

                  this.if_icmpxx(bo, !orientation ? opIdx ^ 1 : opIdx, dst);
                  return;
               }

               if (!rawTypeOf(lhsType).isPrimitive() && !rawTypeOf(rhsType).isPrimitive()) {
                  if (bo.operator != "==" && bo.operator != "!=") {
                     this.compileError("Operator \"" + bo.operator + "\" not allowed on reference operands", bo.getLocation());
                  }

                  if (!this.isCastReferenceConvertible(lhsType, rhsType) || !this.isCastReferenceConvertible(rhsType, lhsType)) {
                     this.compileError("Incomparable types \"" + lhsType + "\" and \"" + rhsType + "\"", bo.getLocation());
                  }

                  this.compileGetValue(bo.rhs);
                  this.if_acmpxx(bo, !orientation ? opIdx ^ 1 : opIdx, dst);
                  return;
               }

               this.compileError("Cannot compare types \"" + lhsType + "\" and \"" + rhsType + "\"", bo.getLocation());
            }

            this.compileError("Boolean expression expected", bo.getLocation());
         } else {
            Object lhsCv = this.getConstantValue(bo.lhs);
            if (lhsCv instanceof Boolean) {
               if ((Boolean)lhsCv ^ bo.operator == "||") {
                  this.compileBoolean(bo.rhs, dst, orientation);
               } else {
                  this.compileBoolean(bo.lhs, dst, orientation);
                  this.fakeCompile(bo.rhs);
               }

            } else {
               Object rhsCv = this.getConstantValue(bo.rhs);
               if (rhsCv instanceof Boolean) {
                  if ((Boolean)rhsCv ^ bo.operator == "||") {
                     this.compileBoolean(bo.lhs, dst, orientation);
                  } else {
                     this.pop(bo.lhs, this.compileGetValue(bo.lhs));
                     this.compileBoolean(bo.rhs, dst, orientation);
                  }

               } else {
                  if (bo.operator == "||" ^ !orientation) {
                     this.compileBoolean(bo.lhs, dst, orientation);
                     this.compileBoolean(bo.rhs, dst, orientation);
                  } else {
                     CodeContext var10002 = this.getCodeContext();
                     Objects.requireNonNull(var10002);
                     CodeContext.Offset end = var10002.new BasicBlock();
                     this.compileBoolean(bo.lhs, end, !orientation);
                     this.compileBoolean(bo.rhs, dst, orientation);
                     end.set();
                  }

               }
            }
         }
      } else {
         this.compileBoolean2((Java.Rvalue)bo, dst, orientation);
      }
   }

   private void compileBoolean2(Java.ParenthesizedExpression pe, CodeContext.Offset dst, boolean orientation) throws CompileException {
      this.compileBoolean(pe.value, dst, orientation);
   }

   private int compileContext(Java.Rvalue rv) throws CompileException {
      Integer result = (Integer)rv.accept(new Visitor.RvalueVisitor() {
         @Nullable
         public Integer visitLvalue(Java.Lvalue lv) throws CompileException {
            return (Integer)lv.accept(new Visitor.LvalueVisitor() {
               public Integer visitAmbiguousName(Java.AmbiguousName an) throws CompileException {
                  return UnitCompiler.this.compileContext2(an);
               }

               public Integer visitArrayAccessExpression(Java.ArrayAccessExpression aae) throws CompileException {
                  return UnitCompiler.this.compileContext2(aae);
               }

               public Integer visitFieldAccess(Java.FieldAccess fa) throws CompileException {
                  return UnitCompiler.this.compileContext2(fa);
               }

               public Integer visitFieldAccessExpression(Java.FieldAccessExpression fae) throws CompileException {
                  return UnitCompiler.this.compileContext2(fae);
               }

               public Integer visitSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression scfae) throws CompileException {
                  return UnitCompiler.this.compileContext2(scfae);
               }

               public Integer visitLocalVariableAccess(Java.LocalVariableAccess lva) {
                  return UnitCompiler.this.compileContext2((Java.Rvalue)lva);
               }

               public Integer visitParenthesizedExpression(Java.ParenthesizedExpression pe) throws CompileException {
                  return UnitCompiler.this.compileContext2(pe);
               }
            });
         }

         public Integer visitArrayLength(Java.ArrayLength al) throws CompileException {
            return UnitCompiler.this.compileContext2(al);
         }

         public Integer visitAssignment(Java.Assignment a) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)a);
         }

         public Integer visitUnaryOperation(Java.UnaryOperation uo) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)uo);
         }

         public Integer visitBinaryOperation(Java.BinaryOperation bo) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)bo);
         }

         public Integer visitCast(Java.Cast c) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)c);
         }

         public Integer visitClassLiteral(Java.ClassLiteral cl) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)cl);
         }

         public Integer visitConditionalExpression(Java.ConditionalExpression ce) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)ce);
         }

         public Integer visitCrement(Java.Crement c) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)c);
         }

         public Integer visitInstanceof(Java.Instanceof io) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)io);
         }

         public Integer visitMethodInvocation(Java.MethodInvocation mi) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)mi);
         }

         public Integer visitSuperclassMethodInvocation(Java.SuperclassMethodInvocation smi) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)smi);
         }

         public Integer visitIntegerLiteral(Java.IntegerLiteral il) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)il);
         }

         public Integer visitFloatingPointLiteral(Java.FloatingPointLiteral fpl) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)fpl);
         }

         public Integer visitBooleanLiteral(Java.BooleanLiteral bl) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)bl);
         }

         public Integer visitCharacterLiteral(Java.CharacterLiteral cl) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)cl);
         }

         public Integer visitStringLiteral(Java.StringLiteral sl) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)sl);
         }

         public Integer visitNullLiteral(Java.NullLiteral nl) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)nl);
         }

         public Integer visitSimpleConstant(Java.SimpleConstant sl) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)sl);
         }

         public Integer visitNewAnonymousClassInstance(Java.NewAnonymousClassInstance naci) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)naci);
         }

         public Integer visitNewArray(Java.NewArray na) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)na);
         }

         public Integer visitNewInitializedArray(Java.NewInitializedArray nia) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)nia);
         }

         public Integer visitNewClassInstance(Java.NewClassInstance nci) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)nci);
         }

         public Integer visitParameterAccess(Java.ParameterAccess pa) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)pa);
         }

         public Integer visitQualifiedThisReference(Java.QualifiedThisReference qtr) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)qtr);
         }

         public Integer visitThisReference(Java.ThisReference tr) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)tr);
         }

         public Integer visitLambdaExpression(Java.LambdaExpression le) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)le);
         }

         public Integer visitMethodReference(Java.MethodReference mr) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)mr);
         }

         public Integer visitInstanceCreationReference(Java.ClassInstanceCreationReference cicr) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)cicr);
         }

         public Integer visitArrayCreationReference(Java.ArrayCreationReference acr) {
            return UnitCompiler.this.compileContext2((Java.Rvalue)acr);
         }
      });

      assert result != null;

      return result;
   }

   private int compileContext2(Java.Rvalue rv) {
      return 0;
   }

   private int compileContext2(Java.AmbiguousName an) throws CompileException {
      return this.compileContext(this.toRvalueOrCompileException(this.reclassify(an)));
   }

   private int compileContext2(Java.FieldAccess fa) throws CompileException {
      if (fa.field.isStatic()) {
         Java.Rvalue rv = fa.lhs.toRvalue();
         if (rv != null) {
            this.warning("CNSFA", "Left-hand side of static field access should be a type, not an rvalue", fa.lhs.getLocation());
            this.pop(fa.lhs, this.compileGetValue(rv));
         }

         return 0;
      } else {
         this.compileGetValue(this.toRvalueOrCompileException(fa.lhs));
         return 1;
      }
   }

   private int compileContext2(Java.ArrayLength al) throws CompileException {
      if (!rawTypeOf(this.compileGetValue(al.lhs)).isArray()) {
         this.compileError("Cannot determine length of non-array type", al.getLocation());
      }

      return 1;
   }

   private int compileContext2(Java.ArrayAccessExpression aae) throws CompileException {
      IType lhsType = this.compileGetValue(aae.lhs);
      if (!rawTypeOf(lhsType).isArray()) {
         this.compileError("Subscript not allowed on non-array type \"" + lhsType.toString() + "\"", aae.getLocation());
      }

      IType indexType = this.compileGetValue(aae.index);
      if (this.unaryNumericPromotion(aae.index, indexType) != IClass.INT) {
         this.compileError("Index expression of type \"" + indexType + "\" cannot be promoted to \"int\"", aae.getLocation());
      }

      return 2;
   }

   private int compileContext2(Java.FieldAccessExpression fae) throws CompileException {
      return this.compileContext(this.determineValue(fae));
   }

   private int compileContext2(Java.SuperclassFieldAccessExpression scfae) throws CompileException {
      return this.compileContext(this.determineValue(scfae));
   }

   private int compileContext2(Java.ParenthesizedExpression pe) throws CompileException {
      return this.compileContext(pe.value);
   }

   private IType compileGet(Java.Rvalue rv) throws CompileException {
      IType result = (IType)rv.accept(new Visitor.RvalueVisitor() {
         @Nullable
         public IType visitLvalue(Java.Lvalue lv) throws CompileException {
            return (IType)lv.accept(new Visitor.LvalueVisitor() {
               public IType visitAmbiguousName(Java.AmbiguousName an) throws CompileException {
                  return UnitCompiler.this.compileGet2(an);
               }

               public IType visitArrayAccessExpression(Java.ArrayAccessExpression aae) throws CompileException {
                  return UnitCompiler.this.compileGet2(aae);
               }

               public IType visitFieldAccess(Java.FieldAccess fa) throws CompileException {
                  return UnitCompiler.this.compileGet2(fa);
               }

               public IType visitFieldAccessExpression(Java.FieldAccessExpression fae) throws CompileException {
                  return UnitCompiler.this.compileGet2(fae);
               }

               public IType visitSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression scfae) throws CompileException {
                  return UnitCompiler.this.compileGet2(scfae);
               }

               public IType visitLocalVariableAccess(Java.LocalVariableAccess lva) {
                  return UnitCompiler.this.compileGet2(lva);
               }

               public IType visitParenthesizedExpression(Java.ParenthesizedExpression pe) throws CompileException {
                  return UnitCompiler.this.compileGet2(pe);
               }
            });
         }

         public IType visitArrayLength(Java.ArrayLength al) {
            return UnitCompiler.this.compileGet2(al);
         }

         public IType visitAssignment(Java.Assignment a) throws CompileException {
            return UnitCompiler.this.compileGet2(a);
         }

         public IType visitUnaryOperation(Java.UnaryOperation uo) throws CompileException {
            return UnitCompiler.this.compileGet2(uo);
         }

         public IType visitBinaryOperation(Java.BinaryOperation bo) throws CompileException {
            return UnitCompiler.this.compileGet2(bo);
         }

         public IType visitCast(Java.Cast c) throws CompileException {
            return UnitCompiler.this.compileGet2(c);
         }

         public IType visitClassLiteral(Java.ClassLiteral cl) throws CompileException {
            return UnitCompiler.this.compileGet2(cl);
         }

         public IType visitConditionalExpression(Java.ConditionalExpression ce) throws CompileException {
            return UnitCompiler.this.compileGet2(ce);
         }

         public IType visitCrement(Java.Crement c) throws CompileException {
            return UnitCompiler.this.compileGet2(c);
         }

         public IType visitInstanceof(Java.Instanceof io) throws CompileException {
            return UnitCompiler.this.compileGet2(io);
         }

         public IType visitMethodInvocation(Java.MethodInvocation mi) throws CompileException {
            return UnitCompiler.this.compileGet2(mi);
         }

         public IType visitSuperclassMethodInvocation(Java.SuperclassMethodInvocation smi) throws CompileException {
            return UnitCompiler.this.compileGet2(smi);
         }

         public IType visitIntegerLiteral(Java.IntegerLiteral il) throws CompileException {
            return UnitCompiler.this.compileGet2((Java.Literal)il);
         }

         public IType visitFloatingPointLiteral(Java.FloatingPointLiteral fpl) throws CompileException {
            return UnitCompiler.this.compileGet2((Java.Literal)fpl);
         }

         public IType visitBooleanLiteral(Java.BooleanLiteral bl) throws CompileException {
            return UnitCompiler.this.compileGet2((Java.Literal)bl);
         }

         public IType visitCharacterLiteral(Java.CharacterLiteral cl) throws CompileException {
            return UnitCompiler.this.compileGet2((Java.Literal)cl);
         }

         public IType visitStringLiteral(Java.StringLiteral sl) throws CompileException {
            return UnitCompiler.this.compileGet2((Java.Literal)sl);
         }

         public IType visitNullLiteral(Java.NullLiteral nl) throws CompileException {
            return UnitCompiler.this.compileGet2((Java.Literal)nl);
         }

         public IType visitSimpleConstant(Java.SimpleConstant sl) throws CompileException {
            return UnitCompiler.this.compileGet2(sl);
         }

         public IType visitNewAnonymousClassInstance(Java.NewAnonymousClassInstance naci) throws CompileException {
            return UnitCompiler.this.compileGet2(naci);
         }

         public IType visitNewArray(Java.NewArray na) throws CompileException {
            return UnitCompiler.this.compileGet2(na);
         }

         public IType visitNewInitializedArray(Java.NewInitializedArray nia) throws CompileException {
            return UnitCompiler.this.compileGet2(nia);
         }

         public IType visitNewClassInstance(Java.NewClassInstance nci) throws CompileException {
            return UnitCompiler.this.compileGet2(nci);
         }

         public IType visitParameterAccess(Java.ParameterAccess pa) throws CompileException {
            return UnitCompiler.this.compileGet2(pa);
         }

         public IType visitQualifiedThisReference(Java.QualifiedThisReference qtr) throws CompileException {
            return UnitCompiler.this.compileGet2(qtr);
         }

         public IType visitThisReference(Java.ThisReference tr) throws CompileException {
            return UnitCompiler.this.compileGet2(tr);
         }

         public IType visitLambdaExpression(Java.LambdaExpression le) throws CompileException {
            return UnitCompiler.this.compileGet2(le);
         }

         public IType visitMethodReference(Java.MethodReference mr) throws CompileException {
            return UnitCompiler.this.compileGet2(mr);
         }

         public IType visitInstanceCreationReference(Java.ClassInstanceCreationReference cicr) throws CompileException {
            return UnitCompiler.this.compileGet2(cicr);
         }

         public IType visitArrayCreationReference(Java.ArrayCreationReference acr) throws CompileException {
            return UnitCompiler.this.compileGet2(acr);
         }
      });

      assert result != null;

      return result;
   }

   private IClass compileGet2(Java.BooleanRvalue brv) throws CompileException {
      CodeContext var10002 = this.getCodeContext();
      Objects.requireNonNull(var10002);
      CodeContext.Offset isTrue = var10002.new BasicBlock();
      isTrue.setStackMap(this.getCodeContext().currentInserter().getStackMap());
      this.compileBoolean(brv, isTrue, true);
      this.consT(brv, 0);
      var10002 = this.getCodeContext();
      Objects.requireNonNull(var10002);
      CodeContext.Offset end = var10002.new BasicBlock();
      this.gotO(brv, end);
      isTrue.setBasicBlock();
      this.consT(brv, 1);
      end.set();
      return IClass.BOOLEAN;
   }

   private IType compileGet2(Java.AmbiguousName an) throws CompileException {
      return this.compileGet(this.toRvalueOrCompileException(this.reclassify(an)));
   }

   private IType compileGet2(Java.LocalVariableAccess lva) {
      return this.load(lva, lva.localVariable);
   }

   private IType compileGet2(Java.FieldAccess fa) throws CompileException {
      this.checkAccessible((IClass.IMember)fa.field, fa.getEnclosingScope(), fa.getLocation());
      this.getfield(fa, fa.field);
      return fa.field.getType();
   }

   private IClass compileGet2(Java.ArrayLength al) {
      this.arraylength(al);
      return IClass.INT;
   }

   private IClass compileGet2(Java.ThisReference tr) throws CompileException {
      IClass currentIClass = this.getIClass(tr);
      this.referenceThis(tr, currentIClass);
      return currentIClass;
   }

   private IClass compileGet2(Java.LambdaExpression le) throws CompileException {
      throw compileException(le, "Compilation of lambda expression NYI");
   }

   private IClass compileGet2(Java.MethodReference mr) throws CompileException {
      throw compileException(mr, "Compilation of method reference NYI");
   }

   private IClass compileGet2(Java.ClassInstanceCreationReference cicr) throws CompileException {
      throw compileException(cicr, "Compilation of class instance creation reference NYI");
   }

   private IClass compileGet2(Java.ArrayCreationReference acr) throws CompileException {
      throw compileException(acr, "Compilation of array creation reference NYI");
   }

   private IType compileGet2(Java.QualifiedThisReference qtr) throws CompileException {
      this.referenceThis(qtr, this.getDeclaringClass(qtr), this.getDeclaringTypeBodyDeclaration(qtr), this.getTargetIType(qtr));
      return this.getTargetIType(qtr);
   }

   private IClass compileGet2(Java.ClassLiteral cl) throws CompileException {
      IType type = this.getType(cl.type);
      if (type instanceof IParameterizedType) {
         this.compileError("LHS of class literal must not be a parameterized type", cl.getLocation());
      }

      assert type instanceof IClass;

      IClass iClass = (IClass)type;
      if (iClass.isPrimitive()) {
         IClass wrapperIClass = iClass == IClass.VOID ? this.iClassLoader.TYPE_java_lang_Void : (iClass == IClass.BYTE ? this.iClassLoader.TYPE_java_lang_Byte : (iClass == IClass.CHAR ? this.iClassLoader.TYPE_java_lang_Character : (iClass == IClass.DOUBLE ? this.iClassLoader.TYPE_java_lang_Double : (iClass == IClass.FLOAT ? this.iClassLoader.TYPE_java_lang_Float : (iClass == IClass.INT ? this.iClassLoader.TYPE_java_lang_Integer : (iClass == IClass.LONG ? this.iClassLoader.TYPE_java_lang_Long : (iClass == IClass.SHORT ? this.iClassLoader.TYPE_java_lang_Short : (iClass == IClass.BOOLEAN ? this.iClassLoader.TYPE_java_lang_Boolean : null))))))));

         assert wrapperIClass != null;

         this.getfield(cl, wrapperIClass, "TYPE", this.iClassLoader.TYPE_java_lang_Class, true);
      } else {
         this.consT(cl, (IClass)iClass);
      }

      return this.iClassLoader.TYPE_java_lang_Class;
   }

   private IType compileGet2(Java.Assignment a) throws CompileException {
      if (a.operator == "=") {
         int lhsCs = this.compileContext(a.lhs);
         IType lhsType = this.getType(a.lhs);
         IType rhsType = this.compileGetValue(a.rhs);
         this.assignmentConversion(a, rhsType, lhsType, this.getConstantValue(a.rhs));
         this.dupxx(a, lhsCs);
         this.compileSet(a.lhs);
         return lhsType;
      } else {
         int lhsCs = this.compileContext(a.lhs);
         this.dupn(a, lhsCs);
         IType lhsType = this.compileGet(a.lhs);
         IType resultType = this.compileArithmeticBinaryOperation(a, lhsType, a.operator.substring(0, a.operator.length() - 1).intern(), a.rhs);
         if (!this.tryIdentityConversion(resultType, lhsType) && !this.tryNarrowingPrimitiveConversion(a, resultType, lhsType)) {
            throw new InternalCompilerException(a.getLocation(), "SNO: \"" + a.operator + "\" reconversion failed");
         } else {
            this.dupx(a);
            this.compileSet(a.lhs);
            return lhsType;
         }
      }
   }

   private IType compileGet2(Java.ConditionalExpression ce) throws CompileException {
      IType expressionType = this.getType2(ce);
      IType mhsType = this.getType(ce.mhs);
      IType rhsType = this.getType(ce.rhs);
      Object lhsCv = this.getConstantValue(ce.lhs);
      if (lhsCv instanceof Boolean) {
         if ((Boolean)lhsCv) {
            this.compileGetValue(ce.mhs);
            this.castConversion(ce.mhs, mhsType, expressionType, NOT_CONSTANT);
         } else {
            this.compileGetValue(ce.rhs);
            this.castConversion(ce.rhs, rhsType, expressionType, NOT_CONSTANT);
         }

         return expressionType;
      } else {
         CodeContext var10002 = this.getCodeContext();
         Objects.requireNonNull(var10002);
         lhsCv = var10002.new BasicBlock();
         var10002 = this.getCodeContext();
         Objects.requireNonNull(var10002);
         CodeContext.Offset toRhs = var10002.new BasicBlock();
         StackMap sm = this.getCodeContext().currentInserter().getStackMap();
         this.compileBoolean(ce.lhs, toRhs, false);
         this.compileGetValue(ce.mhs);
         this.assignmentConversion(ce.mhs, mhsType, expressionType, NOT_CONSTANT);
         this.gotO(ce, (CodeContext.Offset)lhsCv);
         this.getCodeContext().currentInserter().setStackMap(sm);
         toRhs.setBasicBlock();
         this.compileGetValue(ce.rhs);
         this.assignmentConversion(ce.mhs, rhsType, expressionType, NOT_CONSTANT);
         ((CodeContext.Offset)lhsCv).set();
         return expressionType;
      }
   }

   private IType commonSupertype(IType t1, IType t2) throws CompileException {
      return isAssignableFrom(t2, t1) ? t2 : this.commonSupertype2(t1, t2);
   }

   private IType commonSupertype2(IType t1, IType t2) throws CompileException {
      if (isAssignableFrom(t1, t2)) {
         return t1;
      } else {
         IClass sc = rawTypeOf(t1).getSuperclass();
         if (sc != null) {
            IType result = this.commonSupertype2(sc, t2);
            if (result != this.iClassLoader.TYPE_java_lang_Object) {
               return result;
            }
         }

         for(IType i : getInterfaces(t1)) {
            IType result = this.commonSupertype2(i, t2);
            if (result != this.iClassLoader.TYPE_java_lang_Object) {
               return result;
            }
         }

         return this.iClassLoader.TYPE_java_lang_Object;
      }
   }

   @Nullable
   private static Byte isByteConstant(@Nullable Object o) {
      if (!(o instanceof Integer)) {
         return null;
      } else {
         int v = (Integer)o;
         return v >= -128 && v <= 127 ? (byte)v : null;
      }
   }

   private IType compileGet2(Java.Crement c) throws CompileException {
      Java.LocalVariable lv = this.isIntLv(c);
      if (lv != null) {
         if (!c.pre) {
            this.load(c, lv);
         }

         this.iinc(c, lv, c.operator);
         if (c.pre) {
            this.load(c, lv);
         }

         return IClass.INT;
      } else {
         int operandCs = this.compileContext(c.operand);
         this.dupn(c, operandCs);
         IType type = this.compileGet(c.operand);
         if (!c.pre) {
            this.dupxx(c, operandCs);
         }

         IClass promotedType = this.unaryNumericPromotion(c, type);
         this.consT(c, promotedType, 1);
         if (c.operator == "++") {
            this.add(c);
         } else if (c.operator == "--") {
            this.sub(c);
         } else {
            this.compileError("Unexpected operator \"" + c.operator + "\"", c.getLocation());
         }

         this.reverseUnaryNumericPromotion(c, promotedType, type);
         if (c.pre) {
            this.dupxx(c, operandCs);
         }

         this.compileSet(c.operand);
         return type;
      }
   }

   private IType compileGet2(Java.ArrayAccessExpression aae) throws CompileException {
      IType lhsComponentType = this.getType((Java.Lvalue)aae);
      this.xaload(aae, lhsComponentType);
      return lhsComponentType;
   }

   private IType compileGet2(Java.FieldAccessExpression fae) throws CompileException {
      return this.compileGet(this.determineValue(fae));
   }

   private IType compileGet2(Java.SuperclassFieldAccessExpression scfae) throws CompileException {
      return this.compileGet(this.determineValue(scfae));
   }

   private IClass compileGet2(Java.UnaryOperation uo) throws CompileException {
      if (uo.operator == "!") {
         return this.compileGet2((Java.BooleanRvalue)uo);
      } else if (uo.operator == "+") {
         return this.unaryNumericPromotion(uo, this.convertToPrimitiveNumericType(uo, this.compileGetValue(uo.operand)));
      } else if (uo.operator == "-") {
         Object ncv = this.getConstantValue2(uo);
         if (ncv != NOT_CONSTANT) {
            return this.unaryNumericPromotion(uo, this.consT(uo, (Object)ncv));
         } else {
            ncv = this.unaryNumericPromotion(uo, this.convertToPrimitiveNumericType(uo, this.compileGetValue(uo.operand)));
            this.neg(uo, (IClass)ncv);
            return (IClass)ncv;
         }
      } else {
         if (uo.operator == "~") {
            IType operandType = this.compileGetValue(uo.operand);
            IClass promotedType = this.unaryNumericPromotion(uo, operandType);
            if (promotedType == IClass.INT) {
               this.consT(uo, -1);
               this.xor(uo, 130);
               return IClass.INT;
            }

            if (promotedType == IClass.LONG) {
               this.consT(uo, -1L);
               this.xor(uo, 131);
               return IClass.LONG;
            }

            this.compileError("Operator \"~\" not applicable to type \"" + promotedType + "\"", uo.getLocation());
         }

         this.compileError("Unexpected operator \"" + uo.operator + "\"", uo.getLocation());
         return this.iClassLoader.TYPE_java_lang_Object;
      }
   }

   private IClass compileGet2(Java.Instanceof io) throws CompileException {
      IType lhsType = this.compileGetValue(io.lhs);
      IType rhsType = this.getType(io.rhs);
      if (rhsType instanceof IParameterizedType) {
         this.compileError("Cannot check against parameterized type", io.getLocation());
         return IClass.BOOLEAN;
      } else {
         if (!isInterface(lhsType) && !isInterface(rhsType) && !isAssignableFrom(lhsType, rhsType) && !isAssignableFrom(rhsType, lhsType)) {
            this.compileError("\"" + lhsType + "\" can never be an instance of \"" + rhsType + "\"", io.getLocation());
         } else {
            this.instanceoF(io, rhsType);
         }

         return IClass.BOOLEAN;
      }
   }

   @Nullable
   private static IType getComponentType(IType expressionType) {
      return rawTypeOf(expressionType).getComponentType();
   }

   private static boolean isPrimitive(IType type) {
      return rawTypeOf(type).isPrimitive();
   }

   @Nullable
   private static IType getSuperclass(IType type) throws CompileException {
      return rawTypeOf(type).getSuperclass();
   }

   private static boolean isInterface(IType type) {
      return rawTypeOf(type).isInterface();
   }

   private static IType[] getInterfaces(IType t1) throws CompileException {
      IClass[] rawInterfaces = rawTypeOf(t1).getInterfaces();
      IType[] result = new IType[rawInterfaces.length];

      for(int i = 0; i < result.length; ++i) {
         result[i] = rawInterfaces[i];
      }

      return result;
   }

   private static boolean isArray(IType type) {
      return rawTypeOf(type).isArray();
   }

   private static boolean isAssignableFrom(IType targetType, IType sourceType) throws CompileException {
      return rawTypeOf(targetType).isAssignableFrom(rawTypeOf(sourceType));
   }

   private IType compileGet2(Java.BinaryOperation bo) throws CompileException {
      return (IType)(bo.operator != "||" && bo.operator != "&&" && bo.operator != "==" && bo.operator != "!=" && bo.operator != "<" && bo.operator != ">" && bo.operator != "<=" && bo.operator != ">=" ? this.compileArithmeticOperation(bo, (IType)null, bo.unrollLeftAssociation(), bo.operator) : this.compileGet2((Java.BooleanRvalue)bo));
   }

   private IType compileGet2(Java.Cast c) throws CompileException {
      IType tt = this.getType(c.targetType);
      IType vt = this.compileGetValue(c.value);
      if (this.tryCastConversion(c, vt, tt, this.getConstantValue2(c.value))) {
         return tt;
      } else {
         IClass boxedType = this.isBoxingConvertible(vt);
         if (boxedType != null && this.isWideningReferenceConvertible(boxedType, tt)) {
            this.boxingConversion(c, vt, boxedType);
            return tt;
         } else {
            IClass unboxedType = this.isUnboxingConvertible(vt);
            if (unboxedType != null && this.isWideningPrimitiveConvertible(unboxedType, tt)) {
               this.unboxingConversion(c, vt, unboxedType);
               this.tryWideningPrimitiveConversion(c, unboxedType, tt);
               return tt;
            } else {
               this.compileError("Cannot cast \"" + vt + "\" to \"" + tt + "\"", c.getLocation());
               return tt;
            }
         }
      }
   }

   private IType compileGet2(Java.ParenthesizedExpression pe) throws CompileException {
      return this.compileGet(pe.value);
   }

   private IClass compileGet2(Java.MethodInvocation mi) throws CompileException {
      IClass.IMethod iMethod = this.findIMethod(mi);
      Java.Atom ot = mi.target;
      if (ot == null) {
         Java.Scope s;
         for(s = mi.getEnclosingScope(); !(s instanceof Java.TypeBodyDeclaration); s = s.getEnclosingScope()) {
         }

         Java.TypeBodyDeclaration scopeTbd = (Java.TypeBodyDeclaration)s;
         if (!(s instanceof Java.AbstractTypeDeclaration)) {
            s = s.getEnclosingScope();
         }

         Java.AbstractTypeDeclaration scopeTypeDeclaration = (Java.AbstractTypeDeclaration)s;
         if (iMethod.isStatic()) {
            this.warning("IASM", "Implicit access to static method \"" + iMethod.toString() + "\"", mi.getLocation());
         } else {
            this.warning("IANSM", "Implicit access to non-static method \"" + iMethod.toString() + "\"", mi.getLocation());
            if (isStaticContext(scopeTbd)) {
               this.compileError("Instance method \"" + iMethod.toString() + "\" cannot be invoked in static context", mi.getLocation());
            }

            this.referenceThis(mi, scopeTypeDeclaration, scopeTbd, iMethod.getDeclaringIClass());
         }
      } else if (this.isType(ot)) {
         this.getType(this.toTypeOrCompileException(ot));
         if (!iMethod.isStatic()) {
            this.compileError("Instance method \"" + mi.methodName + "\" cannot be invoked in static context", mi.getLocation());
         }
      } else {
         Java.Rvalue rot = this.toRvalueOrCompileException(ot);
         if (iMethod.isStatic()) {
            if (mayHaveSideEffects((Java.ArrayInitializerOrRvalue)rot)) {
               this.pop(ot, this.compileGetValue(rot));
            }
         } else {
            this.compileGetValue(rot);
            if (this.getCodeContext().peekNullOperand()) {
               this.compileError("Method invocation target is always null");
               this.getCodeContext().popOperand();
               this.getCodeContext().pushObjectOperand(iMethod.getDeclaringIClass().getDescriptor());
            }
         }
      }

      IClass[] parameterTypes = iMethod.getParameterTypes();
      Java.Rvalue[] adjustedArgs = null;
      int actualSize = mi.arguments.length;
      if (iMethod.isVarargs() && iMethod.argsNeedAdjust()) {
         adjustedArgs = new Java.Rvalue[parameterTypes.length];
         Java.Rvalue[] lastArgs = new Java.Rvalue[actualSize - parameterTypes.length + 1];
         Location loc = mi.getLocation();
         if (lastArgs.length > 0) {
            int i = 0;

            for(int j = parameterTypes.length - 1; i < lastArgs.length; ++j) {
               lastArgs[i] = mi.arguments[j];
               ++i;
            }
         }

         for(int i = parameterTypes.length - 2; i >= 0; --i) {
            adjustedArgs[i] = mi.arguments[i];
         }

         adjustedArgs[adjustedArgs.length - 1] = new Java.NewInitializedArray(loc, parameterTypes[parameterTypes.length - 1], new Java.ArrayInitializer(loc, lastArgs));
      } else {
         adjustedArgs = mi.arguments;
      }

      for(int i = 0; i < adjustedArgs.length; ++i) {
         this.assignmentConversion(mi, this.compileGetValue(adjustedArgs[i]), parameterTypes[i], this.getConstantValue(adjustedArgs[i]));
      }

      this.checkAccessible((IClass.IMember)iMethod, mi.getEnclosingScope(), mi.getLocation());
      if (!iMethod.getDeclaringIClass().isInterface() && !iMethod.isStatic() && iMethod.getAccess() == Access.PRIVATE) {
         this.invoke(mi, 184, iMethod.getDeclaringIClass(), iMethod.getName() + '$', iMethod.getDescriptor().prependParameter(iMethod.getDeclaringIClass().getDescriptor()), false);
      } else {
         this.invokeMethod(mi, iMethod);
      }

      return iMethod.getReturnType();
   }

   private static boolean isStaticContext(Java.TypeBodyDeclaration tbd) {
      if (!(tbd instanceof Java.FieldDeclaration)) {
         if (tbd instanceof Java.MethodDeclarator) {
            return ((Java.MethodDeclarator)tbd).isStatic();
         } else if (tbd instanceof Java.Initializer) {
            return ((Java.Initializer)tbd).isStatic();
         } else {
            return tbd instanceof Java.MemberClassDeclaration ? ((Java.MemberClassDeclaration)tbd).isStatic() : false;
         }
      } else {
         return ((Java.FieldDeclaration)tbd).isStatic() || ((Java.FieldDeclaration)tbd).getDeclaringType() instanceof Java.InterfaceDeclaration;
      }
   }

   private static boolean mayHaveSideEffects(Java.ArrayInitializerOrRvalue... arrayInitializersOrRvalues) {
      for(Java.ArrayInitializerOrRvalue aiorv : arrayInitializersOrRvalues) {
         if (mayHaveSideEffects(aiorv)) {
            return true;
         }
      }

      return false;
   }

   private static boolean mayHaveSideEffects(Java.ArrayInitializerOrRvalue arrayInitializerOrRvalue) {
      Boolean result = (Boolean)arrayInitializerOrRvalue.accept(MAY_HAVE_SIDE_EFFECTS_VISITOR);

      assert result != null;

      return result;
   }

   private IClass compileGet2(Java.SuperclassMethodInvocation scmi) throws CompileException {
      IClass.IMethod iMethod = this.findIMethod(scmi);

      Java.Scope s;
      for(s = scmi.getEnclosingScope(); s instanceof Java.Statement || s instanceof Java.CatchClause; s = s.getEnclosingScope()) {
      }

      Java.FunctionDeclarator fd = s instanceof Java.FunctionDeclarator ? (Java.FunctionDeclarator)s : null;
      if (fd == null) {
         this.compileError("Cannot invoke superclass method in non-method scope", scmi.getLocation());
         return IClass.INT;
      } else {
         if (fd instanceof Java.MethodDeclarator && ((Java.MethodDeclarator)fd).isStatic()) {
            this.compileError("Cannot invoke superclass method in static context", scmi.getLocation());
         }

         this.load(scmi, this.resolve(fd.getDeclaringType()), 0);
         IClass[] parameterTypes = iMethod.getParameterTypes();

         for(int i = 0; i < scmi.arguments.length; ++i) {
            this.assignmentConversion(scmi, this.compileGetValue(scmi.arguments[i]), parameterTypes[i], this.getConstantValue(scmi.arguments[i]));
         }

         this.invoke(scmi, 183, iMethod.getDeclaringIClass(), iMethod.getName(), iMethod.getDescriptor(), false);
         return iMethod.getReturnType();
      }
   }

   private IType compileGet2(Java.NewClassInstance nci) throws CompileException {
      IType iType;
      if (nci.iType != null) {
         iType = nci.iType;
      } else {
         assert nci.type != null;

         iType = nci.iType = this.getType(nci.type);
      }

      IClass rawType = rawTypeOf(iType);
      if (rawType.isInterface()) {
         this.compileError("Cannot instantiate \"" + iType + "\"", nci.getLocation());
      }

      this.checkAccessible(rawType, nci.getEnclosingScope(), nci.getLocation());
      if (rawType.isAbstract()) {
         this.compileError("Cannot instantiate abstract \"" + iType + "\"", nci.getLocation());
      }

      Java.Rvalue enclosingInstance;
      if (nci.qualification != null) {
         if (rawType.getOuterIClass() == null) {
            this.compileError("Static member class cannot be instantiated with qualified NEW");
         }

         enclosingInstance = nci.qualification;
      } else {
         Java.Scope s;
         for(s = nci.getEnclosingScope(); !(s instanceof Java.TypeBodyDeclaration); s = s.getEnclosingScope()) {
         }

         Java.TypeBodyDeclaration enclosingTypeBodyDeclaration = (Java.TypeBodyDeclaration)s;
         Java.TypeDeclaration enclosingTypeDeclaration = (Java.TypeDeclaration)s.getEnclosingScope();
         if (!(enclosingTypeDeclaration instanceof Java.AbstractClassDeclaration) || enclosingTypeBodyDeclaration instanceof Java.MemberClassDeclaration && ((Java.MemberClassDeclaration)enclosingTypeBodyDeclaration).isStatic() || enclosingTypeBodyDeclaration instanceof Java.PackageMemberClassDeclaration && ((Java.PackageMemberClassDeclaration)enclosingTypeBodyDeclaration).isStatic()) {
            if (rawType.getOuterIClass() != null) {
               this.compileError("Instantiation of \"" + (nci.type != null ? nci.type.toString() : String.valueOf(nci.iType)) + "\" requires an enclosing instance", nci.getLocation());
            }

            enclosingInstance = null;
         } else {
            IClass outerIClass = rawType.getDeclaringIClass();
            if (outerIClass == null) {
               enclosingInstance = null;
            } else {
               enclosingInstance = new Java.QualifiedThisReference(nci.getLocation(), new Java.SimpleType(nci.getLocation(), outerIClass));
               enclosingInstance.setEnclosingScope(nci.getEnclosingScope());
            }
         }
      }

      this.neW(nci, iType);
      this.dup(nci);
      this.invokeConstructor(nci, nci.getEnclosingScope(), enclosingInstance, iType, nci.arguments);
      this.getCodeContext().popUninitializedVariableOperand();
      this.getCodeContext().pushObjectOperand(rawType.getDescriptor());
      return iType;
   }

   private IClass compileGet2(Java.NewAnonymousClassInstance naci) throws CompileException {
      Java.AnonymousClassDeclaration acd = naci.anonymousClassDeclaration;
      IClass sc = this.resolve(acd).getSuperclass();

      assert sc != null;

      IClass.IConstructor[] superclassIConstructors = sc.getDeclaredIConstructors();
      if (superclassIConstructors.length == 0) {
         throw new InternalCompilerException(naci.getLocation(), "SNO: Superclass has no constructors");
      } else {
         IClass.IConstructor superclassIConstructor = (IClass.IConstructor)this.findMostSpecificIInvocable(naci, superclassIConstructors, naci.arguments, acd);
         Location loc = naci.getLocation();
         Java.Rvalue qualification = naci.qualification;
         IClass[] scpts = superclassIConstructor.getParameterTypes();
         List<Java.FunctionDeclarator.FormalParameter> l = new ArrayList();
         if (qualification != null) {
            l.add(new Java.FunctionDeclarator.FormalParameter(loc, accessModifiers(loc, "final"), new Java.SimpleType(loc, this.getType(qualification)), "this$base"));
         }

         for(int i = 0; i < scpts.length; ++i) {
            l.add(new Java.FunctionDeclarator.FormalParameter(loc, accessModifiers(loc, "final"), new Java.SimpleType(loc, scpts[i]), "p" + i));
         }

         Java.FunctionDeclarator.FormalParameters parameters = new Java.FunctionDeclarator.FormalParameters(loc, (Java.FunctionDeclarator.FormalParameter[])l.toArray(new Java.FunctionDeclarator.FormalParameter[l.size()]), false);
         IClass[] tes = superclassIConstructor.getThrownExceptions();
         Java.Type[] thrownExceptions = new Java.Type[tes.length];

         for(int i = 0; i < tes.length; ++i) {
            thrownExceptions[i] = new Java.SimpleType(loc, tes[i]);
         }

         int j = 0;
         Java.Rvalue qualificationAccess;
         if (qualification == null) {
            qualificationAccess = null;
         } else {
            qualificationAccess = new Java.ParameterAccess(loc, parameters.parameters[j++]);
         }

         Java.Rvalue[] parameterAccesses = new Java.Rvalue[scpts.length];

         for(int i = 0; i < scpts.length; ++i) {
            parameterAccesses[i] = new Java.ParameterAccess(loc, parameters.parameters[j++]);
         }

         acd.addConstructor(new Java.ConstructorDeclarator(loc, (String)null, new Java.Modifier[0], parameters, thrownExceptions, new Java.SuperConstructorInvocation(loc, qualificationAccess, parameterAccesses), Collections.emptyList()));
         IClass iClass = this.resolve(naci.anonymousClassDeclaration);

         try {
            this.compile((Java.TypeDeclaration)acd);
            IClass anonymousIClass = this.resolve(acd);
            this.neW(naci, anonymousIClass);
            this.dup(naci);
            Java.Rvalue[] arguments2;
            if (qualification == null) {
               arguments2 = naci.arguments;
            } else {
               arguments2 = new Java.Rvalue[naci.arguments.length + 1];
               arguments2[0] = qualification;
               System.arraycopy(naci.arguments, 0, arguments2, 1, naci.arguments.length);
            }

            Java.Scope s;
            for(s = naci.getEnclosingScope(); !(s instanceof Java.TypeBodyDeclaration); s = s.getEnclosingScope()) {
            }

            Java.ThisReference oei;
            if (isStaticContext((Java.TypeBodyDeclaration)s)) {
               oei = null;
            } else {
               oei = new Java.ThisReference(loc);
               oei.setEnclosingScope(naci.getEnclosingScope());
            }

            this.invokeConstructor(naci, naci.getEnclosingScope(), oei, iClass, arguments2);
            this.getCodeContext().popUninitializedVariableOperand();
            this.getCodeContext().pushObjectOperand(iClass.getDescriptor());
         } finally {
            acd.constructors.remove(acd.constructors.size() - 1);
         }

         return iClass;
      }
   }

   private IType compileGet2(Java.ParameterAccess pa) throws CompileException {
      Java.LocalVariable lv = this.getLocalVariable(pa.formalParameter);
      this.load(pa, lv);
      return lv.type;
   }

   private IClass compileGet2(Java.NewArray na) throws CompileException {
      for(Java.Rvalue dimExpr : na.dimExprs) {
         IType dimType = this.compileGetValue(dimExpr);
         if (dimType != IClass.INT && this.unaryNumericPromotion(na, dimType) != IClass.INT) {
            this.compileError("Invalid array size expression type", na.getLocation());
         }
      }

      return this.newArray(na, na.dimExprs.length, na.dims, this.getType(na.type));
   }

   private IType compileGet2(Java.NewInitializedArray nia) throws CompileException {
      IType at = this.getType2(nia);
      this.compileGetValue(nia.arrayInitializer, at);
      return at;
   }

   private void compileGetValue(Java.ArrayInitializer ai, IType arrayType) throws CompileException {
      if (!(arrayType instanceof IClass) || !((IClass)arrayType).isArray()) {
         this.compileError("Array initializer not allowed for non-array type \"" + arrayType.toString() + "\"");
      }

      IClass componentType = ((IClass)arrayType).getComponentType();

      assert componentType != null;

      this.consT(ai, (Object)ai.values.length);
      this.newArray(ai, 1, 0, componentType);

      for(int index = 0; index < ai.values.length; ++index) {
         Java.ArrayInitializerOrRvalue componentInitializer = ai.values[index];
         this.dup(componentInitializer);
         this.consT(ai, index);
         this.compile((Java.ArrayInitializerOrRvalue)componentInitializer, (IType)componentType);
         this.arraystore(componentInitializer, componentType);
      }

   }

   private IClass compileGet2(Java.Literal l) throws CompileException {
      return this.consT(l, (Object)this.getConstantValue((Java.Rvalue)l));
   }

   private IClass compileGet2(Java.SimpleConstant sl) throws CompileException {
      return this.consT(sl, (Object)sl.value);
   }

   private IType compileGetValue(Java.Rvalue rv) throws CompileException {
      Object cv = this.getConstantValue(rv);
      if (cv != NOT_CONSTANT) {
         this.fakeCompile(rv);
         this.consT(rv, (Object)cv);
         return this.getType(rv);
      } else {
         try {
            this.compileContext(rv);
            return this.compileGet(rv);
         } catch (RuntimeException re) {
            throw new InternalCompilerException(rv.getLocation(), "Compiling \"" + rv + "\"", re);
         }
      }
   }

   @Nullable
   public final Object getConstantValue(Java.ArrayInitializerOrRvalue rv) throws CompileException {
      return rv.accept(new Visitor.ArrayInitializerOrRvalueVisitor() {
         @Nullable
         public Object visitArrayInitializer(Java.ArrayInitializer ai) {
            return UnitCompiler.NOT_CONSTANT;
         }

         @Nullable
         public Object visitRvalue(Java.Rvalue rvalue) throws CompileException {
            return UnitCompiler.this.getConstantValue(rvalue);
         }
      });
   }

   @Nullable
   public final Object getConstantValue(Java.Rvalue rv) throws CompileException {
      return rv.constantValue != Java.Rvalue.CONSTANT_VALUE_UNKNOWN ? rv.constantValue : (rv.constantValue = rv.accept(new Visitor.RvalueVisitor() {
         @Nullable
         public Object visitLvalue(Java.Lvalue lv) throws CompileException {
            return lv.accept(new Visitor.LvalueVisitor() {
               @Nullable
               public Object visitAmbiguousName(Java.AmbiguousName an) throws CompileException {
                  return UnitCompiler.this.getConstantValue2(an);
               }

               @Nullable
               public Object visitArrayAccessExpression(Java.ArrayAccessExpression aae) {
                  return UnitCompiler.this.getConstantValue2((Java.Rvalue)aae);
               }

               @Nullable
               public Object visitFieldAccess(Java.FieldAccess fa) throws CompileException {
                  return UnitCompiler.this.getConstantValue2(fa);
               }

               @Nullable
               public Object visitFieldAccessExpression(Java.FieldAccessExpression fae) {
                  return UnitCompiler.this.getConstantValue2((Java.Rvalue)fae);
               }

               @Nullable
               public Object visitSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression scfae) {
                  return UnitCompiler.this.getConstantValue2((Java.Rvalue)scfae);
               }

               @Nullable
               public Object visitLocalVariableAccess(Java.LocalVariableAccess lva) throws CompileException {
                  return UnitCompiler.this.getConstantValue2(lva);
               }

               @Nullable
               public Object visitParenthesizedExpression(Java.ParenthesizedExpression pe) throws CompileException {
                  return UnitCompiler.this.getConstantValue2(pe);
               }
            });
         }

         @Nullable
         public Object visitArrayLength(Java.ArrayLength al) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)al);
         }

         @Nullable
         public Object visitAssignment(Java.Assignment a) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)a);
         }

         @Nullable
         public Object visitUnaryOperation(Java.UnaryOperation uo) throws CompileException {
            return UnitCompiler.this.getConstantValue2(uo);
         }

         @Nullable
         public Object visitBinaryOperation(Java.BinaryOperation bo) throws CompileException {
            return UnitCompiler.this.getConstantValue2(bo);
         }

         @Nullable
         public Object visitCast(Java.Cast c) throws CompileException {
            return UnitCompiler.this.getConstantValue2(c);
         }

         @Nullable
         public Object visitClassLiteral(Java.ClassLiteral cl) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)cl);
         }

         @Nullable
         public Object visitConditionalExpression(Java.ConditionalExpression ce) throws CompileException {
            return UnitCompiler.this.getConstantValue2(ce);
         }

         @Nullable
         public Object visitCrement(Java.Crement c) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)c);
         }

         @Nullable
         public Object visitInstanceof(Java.Instanceof io) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)io);
         }

         @Nullable
         public Object visitMethodInvocation(Java.MethodInvocation mi) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)mi);
         }

         @Nullable
         public Object visitSuperclassMethodInvocation(Java.SuperclassMethodInvocation smi) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)smi);
         }

         @Nullable
         public Object visitIntegerLiteral(Java.IntegerLiteral il) throws CompileException {
            return UnitCompiler.this.getConstantValue2(il);
         }

         @Nullable
         public Object visitFloatingPointLiteral(Java.FloatingPointLiteral fpl) throws CompileException {
            return UnitCompiler.this.getConstantValue2(fpl);
         }

         @Nullable
         public Object visitBooleanLiteral(Java.BooleanLiteral bl) {
            return UnitCompiler.this.getConstantValue2(bl);
         }

         @Nullable
         public Object visitCharacterLiteral(Java.CharacterLiteral cl) throws CompileException {
            return UnitCompiler.this.getConstantValue2(cl);
         }

         @Nullable
         public Object visitStringLiteral(Java.StringLiteral sl) throws CompileException {
            return UnitCompiler.this.getConstantValue2(sl);
         }

         @Nullable
         public Object visitNullLiteral(Java.NullLiteral nl) {
            return UnitCompiler.this.getConstantValue2(nl);
         }

         @Nullable
         public Object visitSimpleConstant(Java.SimpleConstant sl) {
            return UnitCompiler.this.getConstantValue2(sl);
         }

         @Nullable
         public Object visitNewAnonymousClassInstance(Java.NewAnonymousClassInstance naci) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)naci);
         }

         @Nullable
         public Object visitNewArray(Java.NewArray na) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)na);
         }

         @Nullable
         public Object visitNewInitializedArray(Java.NewInitializedArray nia) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)nia);
         }

         @Nullable
         public Object visitNewClassInstance(Java.NewClassInstance nci) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)nci);
         }

         @Nullable
         public Object visitParameterAccess(Java.ParameterAccess pa) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)pa);
         }

         @Nullable
         public Object visitQualifiedThisReference(Java.QualifiedThisReference qtr) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)qtr);
         }

         @Nullable
         public Object visitThisReference(Java.ThisReference tr) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)tr);
         }

         @Nullable
         public Object visitLambdaExpression(Java.LambdaExpression le) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)le);
         }

         @Nullable
         public Object visitMethodReference(Java.MethodReference mr) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)mr);
         }

         @Nullable
         public Object visitInstanceCreationReference(Java.ClassInstanceCreationReference cicr) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)cicr);
         }

         @Nullable
         public Object visitArrayCreationReference(Java.ArrayCreationReference acr) {
            return UnitCompiler.this.getConstantValue2((Java.Rvalue)acr);
         }
      }));
   }

   @Nullable
   private Object getConstantValue2(Java.Rvalue rv) {
      return NOT_CONSTANT;
   }

   @Nullable
   private Object getConstantValue2(Java.AmbiguousName an) throws CompileException {
      return this.getConstantValue(this.toRvalueOrCompileException(this.reclassify(an)));
   }

   @Nullable
   private Object getConstantValue2(Java.FieldAccess fa) throws CompileException {
      return fa.field.getConstantValue();
   }

   @Nullable
   private Object getConstantValue2(Java.UnaryOperation uo) throws CompileException {
      if (uo.operator == "+") {
         return this.getConstantValue(uo.operand);
      } else if (uo.operator == "-") {
         if (uo.operand instanceof Java.IntegerLiteral) {
            String v = ((Java.Literal)uo.operand).value;
            if (TWO_E_31_INTEGER.matcher(v).matches()) {
               return Integer.MIN_VALUE;
            }

            if (TWO_E_63_LONG.matcher(v).matches()) {
               return Long.MIN_VALUE;
            }
         }

         Object cv = this.getConstantValue(uo.operand);
         if (cv == NOT_CONSTANT) {
            return NOT_CONSTANT;
         } else if (cv instanceof Byte) {
            return (byte)(-(Byte)cv);
         } else if (cv instanceof Short) {
            return (short)(-(Short)cv);
         } else if (cv instanceof Integer) {
            return -(Integer)cv;
         } else if (cv instanceof Long) {
            return -(Long)cv;
         } else if (cv instanceof Float) {
            return -(Float)cv;
         } else {
            return cv instanceof Double ? -(Double)cv : NOT_CONSTANT;
         }
      } else if (uo.operator == "!") {
         Object cv = this.getConstantValue(uo.operand);
         return cv == Boolean.TRUE ? Boolean.FALSE : (cv == Boolean.FALSE ? Boolean.TRUE : NOT_CONSTANT);
      } else {
         return NOT_CONSTANT;
      }
   }

   @Nullable
   private Object getConstantValue2(Java.ConditionalExpression ce) throws CompileException {
      Object lhsCv = this.getConstantValue(ce.lhs);
      if (!(lhsCv instanceof Boolean)) {
         return NOT_CONSTANT;
      } else {
         IType ceType = this.getType2(ce);
         if (!isPrimitive(ceType) && ceType != this.iClassLoader.TYPE_java_lang_String) {
            return NOT_CONSTANT;
         } else if ((Boolean)lhsCv) {
            this.fakeCompile(ce.rhs);
            return this.getConstantValue(ce.mhs);
         } else {
            this.fakeCompile(ce.mhs);
            return this.getConstantValue(ce.rhs);
         }
      }
   }

   @Nullable
   private Object getConstantValue2(Java.BinaryOperation bo) throws CompileException {
      if (bo.operator != "|" && bo.operator != "^" && bo.operator != "&" && bo.operator != "*" && bo.operator != "/" && bo.operator != "%" && bo.operator != "+" && bo.operator != "-" && bo.operator != "==" && bo.operator != "!=") {
         if (bo.operator == "&&" || bo.operator == "||") {
            Object lhsValue = this.getConstantValue(bo.lhs);
            if (lhsValue instanceof Boolean) {
               boolean lhsBv = (Boolean)lhsValue;
               return bo.operator == "&&" ? (lhsBv ? this.getConstantValue(bo.rhs) : Boolean.FALSE) : (lhsBv ? Boolean.TRUE : this.getConstantValue(bo.rhs));
            }
         }

         return NOT_CONSTANT;
      } else {
         List<Object> cvs = new ArrayList();
         Iterator<Java.Rvalue> it = bo.unrollLeftAssociation();

         while(it.hasNext()) {
            Object cv = this.getConstantValue((Java.Rvalue)it.next());
            if (cv == NOT_CONSTANT) {
               return NOT_CONSTANT;
            }

            cvs.add(cv);
         }

         it = cvs.iterator();
         Object lhs = it.next();

         while(it.hasNext()) {
            if (lhs == NOT_CONSTANT) {
               return NOT_CONSTANT;
            }

            Object rhs = it.next();
            if (bo.operator == "+" && (lhs instanceof String || rhs instanceof String)) {
               StringBuilder sb = (new StringBuilder(lhs.toString())).append(rhs);

               while(it.hasNext()) {
                  sb.append(it.next().toString());
               }

               return sb.toString();
            }

            if (lhs instanceof Number && rhs instanceof Number) {
               try {
                  if (lhs instanceof Double || rhs instanceof Double) {
                     double lhsD = ((Number)lhs).doubleValue();
                     double rhsD = ((Number)rhs).doubleValue();
                     lhs = bo.operator == "*" ? lhsD * rhsD : (bo.operator == "/" ? lhsD / rhsD : (bo.operator == "%" ? lhsD % rhsD : (bo.operator == "+" ? lhsD + rhsD : (bo.operator == "-" ? lhsD - rhsD : (bo.operator == "==" ? lhsD == rhsD : (bo.operator == "!=" ? lhsD != rhsD : NOT_CONSTANT))))));
                     continue;
                  }

                  if (lhs instanceof Float || rhs instanceof Float) {
                     float lhsF = ((Number)lhs).floatValue();
                     float rhsF = ((Number)rhs).floatValue();
                     lhs = bo.operator == "*" ? lhsF * rhsF : (bo.operator == "/" ? lhsF / rhsF : (bo.operator == "%" ? lhsF % rhsF : (bo.operator == "+" ? lhsF + rhsF : (bo.operator == "-" ? lhsF - rhsF : (bo.operator == "==" ? lhsF == rhsF : (bo.operator == "!=" ? lhsF != rhsF : NOT_CONSTANT))))));
                     continue;
                  }

                  if (lhs instanceof Long || rhs instanceof Long) {
                     long lhsL = ((Number)lhs).longValue();
                     long rhsL = ((Number)rhs).longValue();
                     lhs = bo.operator == "|" ? lhsL | rhsL : (bo.operator == "^" ? lhsL ^ rhsL : (bo.operator == "&" ? lhsL & rhsL : (bo.operator == "*" ? lhsL * rhsL : (bo.operator == "/" ? lhsL / rhsL : (bo.operator == "%" ? lhsL % rhsL : (bo.operator == "+" ? lhsL + rhsL : (bo.operator == "-" ? lhsL - rhsL : (bo.operator == "==" ? lhsL == rhsL : (bo.operator == "!=" ? lhsL != rhsL : NOT_CONSTANT)))))))));
                     continue;
                  }

                  if (lhs instanceof Integer || lhs instanceof Byte || lhs instanceof Short || rhs instanceof Integer || lhs instanceof Byte || lhs instanceof Short) {
                     int lhsI = ((Number)lhs).intValue();
                     int rhsI = ((Number)rhs).intValue();
                     lhs = bo.operator == "|" ? lhsI | rhsI : (bo.operator == "^" ? lhsI ^ rhsI : (bo.operator == "&" ? lhsI & rhsI : (bo.operator == "*" ? lhsI * rhsI : (bo.operator == "/" ? lhsI / rhsI : (bo.operator == "%" ? lhsI % rhsI : (bo.operator == "+" ? lhsI + rhsI : (bo.operator == "-" ? lhsI - rhsI : (bo.operator == "==" ? lhsI == rhsI : (bo.operator == "!=" ? lhsI != rhsI : NOT_CONSTANT)))))))));
                     continue;
                  }
               } catch (ArithmeticException var10) {
                  return NOT_CONSTANT;
               }

               throw new IllegalStateException();
            } else if (lhs instanceof Character && rhs instanceof Character) {
               char lhsC = (Character)lhs;
               char rhsC = (Character)rhs;
               lhs = bo.operator == "==" ? lhsC == rhsC : (bo.operator == "!=" ? lhsC != rhsC : NOT_CONSTANT);
            } else {
               if (lhs != null && rhs != null) {
                  return NOT_CONSTANT;
               }

               lhs = bo.operator == "==" ? lhs == rhs : (bo.operator == "!=" ? lhs != rhs : NOT_CONSTANT);
            }
         }

         return lhs;
      }
   }

   private Object getConstantValue2(Java.Cast c) throws CompileException {
      Object cv = this.getConstantValue(c.value);
      if (cv == NOT_CONSTANT) {
         return NOT_CONSTANT;
      } else {
         if (cv instanceof Number) {
            IType tt = this.getType(c.targetType);
            if (tt == IClass.BYTE) {
               return ((Number)cv).byteValue();
            }

            if (tt == IClass.SHORT) {
               return ((Number)cv).shortValue();
            }

            if (tt == IClass.INT) {
               return ((Number)cv).intValue();
            }

            if (tt == IClass.LONG) {
               return ((Number)cv).longValue();
            }

            if (tt == IClass.FLOAT) {
               return ((Number)cv).floatValue();
            }

            if (tt == IClass.DOUBLE) {
               return ((Number)cv).doubleValue();
            }
         }

         return NOT_CONSTANT;
      }
   }

   @Nullable
   private Object getConstantValue2(Java.ParenthesizedExpression pe) throws CompileException {
      return this.getConstantValue(pe.value);
   }

   @Nullable
   private Object getConstantValue2(Java.LocalVariableAccess lva) throws CompileException {
      if (lva.getEnclosingScope() instanceof Java.IfStatement) {
         Java.IfStatement is = (Java.IfStatement)lva.getEnclosingScope();
         if (is.condition instanceof Java.AmbiguousName) {
            Java.Atom ra = ((Java.AmbiguousName)is.condition).reclassified;
            if (ra instanceof Java.LocalVariableAccess) {
               Java.LocalVariable lv = ((Java.LocalVariableAccess)ra).localVariable;
               List<? extends Java.BlockStatement> ss = is.getEnclosingScope() instanceof Java.FunctionDeclarator ? ((Java.FunctionDeclarator)is.getEnclosingScope()).statements : (is.getEnclosingScope() instanceof Java.Block ? ((Java.Block)is.getEnclosingScope()).statements : null);
               if (ss != null) {
                  int isi = ss.indexOf(is);
                  boolean haveSideEffects = false;

                  for(int i = isi - 1; i >= 0; --i) {
                     Java.BlockStatement bs = (Java.BlockStatement)ss.get(i);
                     if (!(bs instanceof Java.LocalVariableDeclarationStatement)) {
                        break;
                     }

                     Java.LocalVariableDeclarationStatement lvds = (Java.LocalVariableDeclarationStatement)bs;

                     for(int j = lvds.variableDeclarators.length - 1; j >= 0; --j) {
                        Java.VariableDeclarator vd = lvds.variableDeclarators[j];
                        Java.ArrayInitializerOrRvalue lvi = vd.initializer;
                        if (vd.localVariable == lv) {
                           if (!lvds.isFinal() && haveSideEffects) {
                              return NOT_CONSTANT;
                           }

                           return this.getConstantValue(lvi);
                        }

                        if (lvi != null) {
                           haveSideEffects |= mayHaveSideEffects(lvi);
                        }
                     }
                  }
               }
            }
         }
      }

      return NOT_CONSTANT;
   }

   private Object getConstantValue2(Java.IntegerLiteral il) throws CompileException {
      String v = il.value.toLowerCase();

      while(true) {
         int ui = v.indexOf(95);
         if (ui == -1) {
            boolean signed;
            if (v.startsWith("0x")) {
               ui = 16;
               signed = false;
               v = v.substring(2);
            } else if (v.startsWith("0b")) {
               ui = 2;
               signed = false;
               v = v.substring(2);
            } else if (v.startsWith("0") && !"0".equals(v) && !"0l".equals(v)) {
               ui = 8;
               signed = false;
               v = v.substring(1);
            } else {
               ui = 10;
               signed = true;
            }

            try {
               if (v.endsWith("l")) {
                  v = v.substring(0, v.length() - 1);
                  return signed ? Long.parseLong(v, ui) : Numbers.parseUnsignedLong(v, ui);
               } else {
                  return signed ? Integer.parseInt(v, ui) : Numbers.parseUnsignedInt(v, ui);
               }
            } catch (NumberFormatException var6) {
               throw compileException(il, "Invalid integer literal \"" + il.value + "\"");
            }
         }

         v = v.substring(0, ui) + v.substring(ui + 1);
      }
   }

   private Object getConstantValue2(Java.FloatingPointLiteral fpl) throws CompileException {
      String v = fpl.value;

      while(true) {
         int ui = v.indexOf(95);
         if (ui == -1) {
            ui = v.charAt(v.length() - 1);
            if (ui != 102 && ui != 70) {
               if (ui == 100 || ui == 68) {
                  v = v.substring(0, v.length() - 1);
               }

               double dv;
               try {
                  dv = Double.parseDouble(v);
               } catch (NumberFormatException e) {
                  throw new InternalCompilerException(fpl.getLocation(), "SNO: parsing double literal \"" + v + "\": " + e.getMessage(), e);
               }

               if (Double.isInfinite(dv)) {
                  throw compileException(fpl, "Value of double literal \"" + v + "\" is out of range");
               } else if (Double.isNaN(dv)) {
                  throw new InternalCompilerException(fpl.getLocation(), "SNO: parsing double literal \"" + v + "\" results is NaN");
               } else {
                  if (dv == (double)0.0F) {
                     for(int i = 0; i < v.length(); ++i) {
                        char c = v.charAt(i);
                        if ("123456789".indexOf(c) != -1) {
                           throw compileException(fpl, "Literal \"" + v + "\" is too small to be represented as a double");
                        }

                        if (c != '0' && c != '.') {
                           break;
                        }
                     }
                  }

                  return dv;
               }
            } else {
               v = v.substring(0, v.length() - 1);

               float fv;
               try {
                  fv = Float.parseFloat(v);
               } catch (NumberFormatException e) {
                  throw new InternalCompilerException(fpl.getLocation(), "SNO: parsing float literal \"" + v + "\": " + e.getMessage(), e);
               }

               if (Float.isInfinite(fv)) {
                  throw compileException(fpl, "Value of float literal \"" + v + "\" is out of range");
               } else if (Float.isNaN(fv)) {
                  throw new InternalCompilerException(fpl.getLocation(), "SNO: parsing float literal \"" + v + "\" results in NaN");
               } else {
                  if (fv == 0.0F) {
                     for(int i = 0; i < v.length(); ++i) {
                        char c = v.charAt(i);
                        if ("123456789".indexOf(c) != -1) {
                           throw compileException(fpl, "Literal \"" + v + "\" is too small to be represented as a float");
                        }

                        if (c != '0' && c != '.') {
                           break;
                        }
                     }
                  }

                  return fv;
               }
            }
         }

         v = v.substring(0, ui) + v.substring(ui + 1);
      }
   }

   private boolean getConstantValue2(Java.BooleanLiteral bl) {
      if (bl.value == "true") {
         return true;
      } else if (bl.value == "false") {
         return false;
      } else {
         throw new InternalCompilerException(bl.getLocation(), bl.value);
      }
   }

   private char getConstantValue2(Java.CharacterLiteral cl) throws CompileException {
      String v = cl.value;
      v = v.substring(1, v.length() - 1);
      v = unescape(v, cl.getLocation());
      if (v.isEmpty()) {
         throw new CompileException("Empty character literal", cl.getLocation());
      } else if (v.length() > 1) {
         throw new CompileException("Invalid character literal " + cl.value, cl.getLocation());
      } else {
         return Character.valueOf(v.charAt(0));
      }
   }

   private String getConstantValue2(Java.StringLiteral sl) throws CompileException {
      String v = sl.value;
      v = v.substring(1, v.length() - 1);
      v = unescape(v, sl.getLocation());
      return v;
   }

   @Nullable
   private Object getConstantValue2(Java.NullLiteral nl) {
      return null;
   }

   @Nullable
   private Object getConstantValue2(Java.SimpleConstant sl) {
      return sl.value;
   }

   private boolean generatesCode(Java.BlockStatement bs) throws CompileException {
      Boolean result = (Boolean)bs.accept(new Visitor.BlockStatementVisitor() {
         public Boolean visitInitializer(Java.Initializer i) throws CompileException {
            return UnitCompiler.this.generatesCode2(i);
         }

         public Boolean visitFieldDeclaration(Java.FieldDeclaration fd) throws CompileException {
            return UnitCompiler.this.generatesCode2(fd);
         }

         public Boolean visitLabeledStatement(Java.LabeledStatement ls) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)ls);
         }

         public Boolean visitBlock(Java.Block b) throws CompileException {
            return UnitCompiler.this.generatesCode2(b);
         }

         public Boolean visitExpressionStatement(Java.ExpressionStatement es) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)es);
         }

         public Boolean visitIfStatement(Java.IfStatement is) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)is);
         }

         public Boolean visitForStatement(Java.ForStatement fs) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)fs);
         }

         public Boolean visitForEachStatement(Java.ForEachStatement fes) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)fes);
         }

         public Boolean visitWhileStatement(Java.WhileStatement ws) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)ws);
         }

         public Boolean visitTryStatement(Java.TryStatement ts) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)ts);
         }

         public Boolean visitSwitchStatement(Java.SwitchStatement ss) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)ss);
         }

         public Boolean visitSynchronizedStatement(Java.SynchronizedStatement ss) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)ss);
         }

         public Boolean visitDoStatement(Java.DoStatement ds) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)ds);
         }

         public Boolean visitLocalVariableDeclarationStatement(Java.LocalVariableDeclarationStatement lvds) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)lvds);
         }

         public Boolean visitReturnStatement(Java.ReturnStatement rs) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)rs);
         }

         public Boolean visitThrowStatement(Java.ThrowStatement ts) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)ts);
         }

         public Boolean visitBreakStatement(Java.BreakStatement bs) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)bs);
         }

         public Boolean visitContinueStatement(Java.ContinueStatement cs) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)cs);
         }

         public Boolean visitAssertStatement(Java.AssertStatement as) {
            return UnitCompiler.this.generatesCode2(as);
         }

         public Boolean visitEmptyStatement(Java.EmptyStatement es) {
            return UnitCompiler.this.generatesCode2(es);
         }

         public Boolean visitLocalClassDeclarationStatement(Java.LocalClassDeclarationStatement lcds) {
            return UnitCompiler.this.generatesCode2(lcds);
         }

         public Boolean visitAlternateConstructorInvocation(Java.AlternateConstructorInvocation aci) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)aci);
         }

         public Boolean visitSuperConstructorInvocation(Java.SuperConstructorInvocation sci) {
            return UnitCompiler.this.generatesCode2((Java.BlockStatement)sci);
         }
      });

      assert result != null;

      return result;
   }

   private boolean generatesCode2(Java.BlockStatement bs) {
      return true;
   }

   private boolean generatesCode2(Java.AssertStatement as) {
      return true;
   }

   private boolean generatesCode2(Java.EmptyStatement es) {
      return false;
   }

   private boolean generatesCode2(Java.LocalClassDeclarationStatement lcds) {
      return false;
   }

   private boolean generatesCode2(Java.Initializer i) throws CompileException {
      return this.generatesCode(i.block);
   }

   private boolean generatesCode2(List l) throws CompileException {
      for(Java.BlockStatement bs : l) {
         if (this.generatesCode(bs)) {
            return true;
         }
      }

      return false;
   }

   private boolean generatesCode2(Java.Block b) throws CompileException {
      return this.generatesCode2(b.statements);
   }

   private boolean generatesCode2(Java.FieldDeclaration fd) throws CompileException {
      for(Java.VariableDeclarator vd : fd.variableDeclarators) {
         if (this.getNonConstantFinalInitializer(fd, vd) != null) {
            return true;
         }
      }

      return false;
   }

   private void leave(Java.BlockStatement bs) throws CompileException {
      Visitor.BlockStatementVisitor<Void, CompileException> bsv = new Visitor.BlockStatementVisitor() {
         @Nullable
         public Void visitInitializer(Java.Initializer i) {
            UnitCompiler.this.leave2((Java.BlockStatement)i);
            return null;
         }

         @Nullable
         public Void visitFieldDeclaration(Java.FieldDeclaration fd) {
            UnitCompiler.this.leave2((Java.BlockStatement)fd);
            return null;
         }

         @Nullable
         public Void visitLabeledStatement(Java.LabeledStatement ls) {
            UnitCompiler.this.leave2((Java.BlockStatement)ls);
            return null;
         }

         @Nullable
         public Void visitBlock(Java.Block b) {
            UnitCompiler.this.leave2((Java.BlockStatement)b);
            return null;
         }

         @Nullable
         public Void visitExpressionStatement(Java.ExpressionStatement es) {
            UnitCompiler.this.leave2((Java.BlockStatement)es);
            return null;
         }

         @Nullable
         public Void visitIfStatement(Java.IfStatement is) {
            UnitCompiler.this.leave2((Java.BlockStatement)is);
            return null;
         }

         @Nullable
         public Void visitForStatement(Java.ForStatement fs) {
            UnitCompiler.this.leave2((Java.BlockStatement)fs);
            return null;
         }

         @Nullable
         public Void visitForEachStatement(Java.ForEachStatement fes) {
            UnitCompiler.this.leave2((Java.BlockStatement)fes);
            return null;
         }

         @Nullable
         public Void visitWhileStatement(Java.WhileStatement ws) {
            UnitCompiler.this.leave2((Java.BlockStatement)ws);
            return null;
         }

         @Nullable
         public Void visitTryStatement(Java.TryStatement ts) throws CompileException {
            UnitCompiler.this.leave2(ts);
            return null;
         }

         @Nullable
         public Void visitSwitchStatement(Java.SwitchStatement ss) {
            UnitCompiler.this.leave2((Java.BlockStatement)ss);
            return null;
         }

         @Nullable
         public Void visitSynchronizedStatement(Java.SynchronizedStatement ss) {
            UnitCompiler.this.leave2(ss);
            return null;
         }

         @Nullable
         public Void visitDoStatement(Java.DoStatement ds) {
            UnitCompiler.this.leave2((Java.BlockStatement)ds);
            return null;
         }

         @Nullable
         public Void visitLocalVariableDeclarationStatement(Java.LocalVariableDeclarationStatement lvds) {
            UnitCompiler.this.leave2((Java.BlockStatement)lvds);
            return null;
         }

         @Nullable
         public Void visitReturnStatement(Java.ReturnStatement rs) {
            UnitCompiler.this.leave2((Java.BlockStatement)rs);
            return null;
         }

         @Nullable
         public Void visitThrowStatement(Java.ThrowStatement ts) {
            UnitCompiler.this.leave2((Java.BlockStatement)ts);
            return null;
         }

         @Nullable
         public Void visitBreakStatement(Java.BreakStatement bs) {
            UnitCompiler.this.leave2((Java.BlockStatement)bs);
            return null;
         }

         @Nullable
         public Void visitContinueStatement(Java.ContinueStatement cs) {
            UnitCompiler.this.leave2((Java.BlockStatement)cs);
            return null;
         }

         @Nullable
         public Void visitAssertStatement(Java.AssertStatement as) {
            UnitCompiler.this.leave2((Java.BlockStatement)as);
            return null;
         }

         @Nullable
         public Void visitEmptyStatement(Java.EmptyStatement es) {
            UnitCompiler.this.leave2((Java.BlockStatement)es);
            return null;
         }

         @Nullable
         public Void visitLocalClassDeclarationStatement(Java.LocalClassDeclarationStatement lcds) {
            UnitCompiler.this.leave2((Java.BlockStatement)lcds);
            return null;
         }

         @Nullable
         public Void visitAlternateConstructorInvocation(Java.AlternateConstructorInvocation aci) {
            UnitCompiler.this.leave2((Java.BlockStatement)aci);
            return null;
         }

         @Nullable
         public Void visitSuperConstructorInvocation(Java.SuperConstructorInvocation sci) {
            UnitCompiler.this.leave2((Java.BlockStatement)sci);
            return null;
         }
      };
      bs.accept(bsv);
   }

   private void leave2(Java.BlockStatement bs) {
   }

   private void leave2(Java.SynchronizedStatement ss) {
      this.load(ss, this.iClassLoader.TYPE_java_lang_Object, ss.monitorLvIndex);
      this.monitorexit(ss);
   }

   private void leave2(Java.TryStatement ts) throws CompileException {
      Java.Block f = ts.finallY;
      if (f != null) {
         this.getCodeContext().saveLocalVariables();

         try {
            if (!this.compile((Java.BlockStatement)f)) {
               return;
            }
         } finally {
            this.getCodeContext().restoreLocalVariables();
         }

      }
   }

   private void compileSet(Java.Lvalue lv) throws CompileException {
      lv.accept(new Visitor.LvalueVisitor() {
         @Nullable
         public Void visitAmbiguousName(Java.AmbiguousName an) throws CompileException {
            UnitCompiler.this.compileSet2(an);
            return null;
         }

         @Nullable
         public Void visitArrayAccessExpression(Java.ArrayAccessExpression aae) throws CompileException {
            UnitCompiler.this.compileSet2(aae);
            return null;
         }

         @Nullable
         public Void visitFieldAccess(Java.FieldAccess fa) throws CompileException {
            UnitCompiler.this.compileSet2(fa);
            return null;
         }

         @Nullable
         public Void visitFieldAccessExpression(Java.FieldAccessExpression fae) throws CompileException {
            UnitCompiler.this.compileSet2(fae);
            return null;
         }

         @Nullable
         public Void visitSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression scfae) throws CompileException {
            UnitCompiler.this.compileSet2(scfae);
            return null;
         }

         @Nullable
         public Void visitLocalVariableAccess(Java.LocalVariableAccess lva) {
            UnitCompiler.this.compileSet2(lva);
            return null;
         }

         @Nullable
         public Void visitParenthesizedExpression(Java.ParenthesizedExpression pe) throws CompileException {
            UnitCompiler.this.compileSet2(pe);
            return null;
         }
      });
   }

   private void compileSet2(Java.AmbiguousName an) throws CompileException {
      this.compileSet(this.toLvalueOrCompileException(this.reclassify(an)));
   }

   private void compileSet2(Java.LocalVariableAccess lva) {
      this.store(lva, lva.localVariable);
   }

   private void compileSet2(Java.FieldAccess fa) throws CompileException {
      this.checkAccessible((IClass.IMember)fa.field, fa.getEnclosingScope(), fa.getLocation());
      this.putfield(fa, fa.field);
   }

   private void compileSet2(Java.ArrayAccessExpression aae) throws CompileException {
      this.arraystore(aae, this.getType((Java.Lvalue)aae));
   }

   private void compileSet2(Java.FieldAccessExpression fae) throws CompileException {
      this.compileSet(this.toLvalueOrCompileException(this.determineValue(fae)));
   }

   private void compileSet2(Java.SuperclassFieldAccessExpression scfae) throws CompileException {
      this.determineValue(scfae);
      this.compileSet(this.toLvalueOrCompileException(this.determineValue(scfae)));
   }

   private void compileSet2(Java.ParenthesizedExpression pe) throws CompileException {
      this.compileSet(this.toLvalueOrCompileException(pe.value));
   }

   private IType getType(Java.Atom a) throws CompileException {
      IType result = (IType)a.accept(new Visitor.AtomVisitor() {
         public IType visitPackage(Java.Package p) throws CompileException {
            return UnitCompiler.this.getType2(p);
         }

         @Nullable
         public IType visitType(Java.Type t) throws CompileException {
            return UnitCompiler.this.getType(t);
         }

         @Nullable
         public IType visitRvalue(Java.Rvalue rv) throws CompileException {
            return UnitCompiler.this.getType(rv);
         }

         @Nullable
         public IType visitConstructorInvocation(Java.ConstructorInvocation ci) throws CompileException {
            return UnitCompiler.this.getType2(ci);
         }
      });

      assert result != null;

      return result;
   }

   private static IClass rawTypeOf(IType iType) {
      while(iType instanceof IParameterizedType) {
         iType = ((IParameterizedType)iType).getRawType();
      }

      assert iType instanceof IClass;

      return (IClass)iType;
   }

   private static IClass[] rawTypesOf(IType[] iTypes) {
      IClass[] result = new IClass[iTypes.length];

      for(int i = 0; i < result.length; ++i) {
         result[i] = rawTypeOf(iTypes[i]);
      }

      return result;
   }

   private IClass getRawType(Java.Type t) throws CompileException {
      return rawTypeOf(this.getType(t));
   }

   private IType getType(Java.Type t) throws CompileException {
      IType result = (IType)t.accept(new Visitor.TypeVisitor() {
         public IType visitArrayType(Java.ArrayType at) throws CompileException {
            return UnitCompiler.this.getType2(at);
         }

         public IType visitPrimitiveType(Java.PrimitiveType bt) {
            return UnitCompiler.this.getType2(bt);
         }

         public IType visitReferenceType(Java.ReferenceType rt) throws CompileException {
            return UnitCompiler.this.getType2(rt);
         }

         public IType visitRvalueMemberType(Java.RvalueMemberType rmt) throws CompileException {
            return UnitCompiler.this.getType2(rmt);
         }

         public IType visitSimpleType(Java.SimpleType st) {
            return UnitCompiler.this.getType2(st);
         }
      });

      assert result != null;

      return result;
   }

   private IType[] getTypes(Java.Type[] types) throws CompileException {
      IType[] result = new IType[types.length];

      for(int i = 0; i < types.length; ++i) {
         result[i] = this.getType(types[i]);
      }

      return result;
   }

   private IType getType(Java.Rvalue rv) throws CompileException {
      IType result = (IType)rv.accept(new Visitor.RvalueVisitor() {
         @Nullable
         public IType visitLvalue(Java.Lvalue lv) throws CompileException {
            return UnitCompiler.this.getType(lv);
         }

         public IType visitArrayLength(Java.ArrayLength al) {
            return UnitCompiler.this.getType2(al);
         }

         public IType visitAssignment(Java.Assignment a) throws CompileException {
            return UnitCompiler.this.getType2(a);
         }

         public IType visitUnaryOperation(Java.UnaryOperation uo) throws CompileException {
            return UnitCompiler.this.getType2(uo);
         }

         public IType visitBinaryOperation(Java.BinaryOperation bo) throws CompileException {
            return UnitCompiler.this.getType2(bo);
         }

         public IType visitCast(Java.Cast c) throws CompileException {
            return UnitCompiler.this.getType2(c);
         }

         public IType visitClassLiteral(Java.ClassLiteral cl) {
            return UnitCompiler.this.getType2(cl);
         }

         public IType visitConditionalExpression(Java.ConditionalExpression ce) throws CompileException {
            return UnitCompiler.this.getType2(ce);
         }

         public IType visitCrement(Java.Crement c) throws CompileException {
            return UnitCompiler.this.getType2(c);
         }

         public IType visitInstanceof(Java.Instanceof io) {
            return UnitCompiler.this.getType2(io);
         }

         public IType visitMethodInvocation(Java.MethodInvocation mi) throws CompileException {
            return UnitCompiler.this.getType2(mi);
         }

         public IType visitSuperclassMethodInvocation(Java.SuperclassMethodInvocation smi) throws CompileException {
            return UnitCompiler.this.getType2(smi);
         }

         public IType visitIntegerLiteral(Java.IntegerLiteral il) {
            return UnitCompiler.this.getType2(il);
         }

         public IType visitFloatingPointLiteral(Java.FloatingPointLiteral fpl) {
            return UnitCompiler.this.getType2(fpl);
         }

         public IType visitBooleanLiteral(Java.BooleanLiteral bl) {
            return UnitCompiler.this.getType2(bl);
         }

         public IType visitCharacterLiteral(Java.CharacterLiteral cl) {
            return UnitCompiler.this.getType2(cl);
         }

         public IType visitStringLiteral(Java.StringLiteral sl) {
            return UnitCompiler.this.getType2(sl);
         }

         public IType visitNullLiteral(Java.NullLiteral nl) {
            return UnitCompiler.this.getType2(nl);
         }

         public IType visitSimpleConstant(Java.SimpleConstant sl) {
            return UnitCompiler.this.getType2(sl);
         }

         public IType visitNewAnonymousClassInstance(Java.NewAnonymousClassInstance naci) {
            return UnitCompiler.this.getType2(naci);
         }

         public IType visitNewArray(Java.NewArray na) throws CompileException {
            return UnitCompiler.this.getType2(na);
         }

         public IType visitNewInitializedArray(Java.NewInitializedArray nia) throws CompileException {
            return UnitCompiler.this.getType2(nia);
         }

         public IType visitNewClassInstance(Java.NewClassInstance nci) throws CompileException {
            return UnitCompiler.this.getType2(nci);
         }

         public IType visitParameterAccess(Java.ParameterAccess pa) throws CompileException {
            return UnitCompiler.this.getType2(pa);
         }

         public IType visitQualifiedThisReference(Java.QualifiedThisReference qtr) throws CompileException {
            return UnitCompiler.this.getType2(qtr);
         }

         public IType visitThisReference(Java.ThisReference tr) throws CompileException {
            return UnitCompiler.this.getType2(tr);
         }

         public IType visitLambdaExpression(Java.LambdaExpression le) throws CompileException {
            return UnitCompiler.this.getType2(le);
         }

         public IType visitMethodReference(Java.MethodReference mr) throws CompileException {
            return UnitCompiler.this.getType2(mr);
         }

         public IType visitInstanceCreationReference(Java.ClassInstanceCreationReference cicr) throws CompileException {
            return UnitCompiler.this.getType2(cicr);
         }

         public IType visitArrayCreationReference(Java.ArrayCreationReference acr) throws CompileException {
            return UnitCompiler.this.getType2(acr);
         }
      });

      assert result != null;

      return result;
   }

   private IType getType(Java.Lvalue lv) throws CompileException {
      IType result = (IType)lv.accept(new Visitor.LvalueVisitor() {
         public IType visitAmbiguousName(Java.AmbiguousName an) throws CompileException {
            return UnitCompiler.this.getType2(an);
         }

         public IType visitArrayAccessExpression(Java.ArrayAccessExpression aae) throws CompileException {
            return UnitCompiler.this.getType2(aae);
         }

         public IType visitFieldAccess(Java.FieldAccess fa) throws CompileException {
            return UnitCompiler.this.getType2(fa);
         }

         public IType visitFieldAccessExpression(Java.FieldAccessExpression fae) throws CompileException {
            return UnitCompiler.this.getType2(fae);
         }

         public IType visitSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression scfae) throws CompileException {
            return UnitCompiler.this.getType2(scfae);
         }

         public IType visitLocalVariableAccess(Java.LocalVariableAccess lva) {
            return UnitCompiler.this.getType2(lva);
         }

         public IType visitParenthesizedExpression(Java.ParenthesizedExpression pe) throws CompileException {
            return UnitCompiler.this.getType2(pe);
         }
      });

      assert result != null;

      return result;
   }

   private IClass getType2(Java.ConstructorInvocation ci) throws CompileException {
      this.compileError("Explicit constructor invocation not allowed here", ci.getLocation());
      return this.iClassLoader.TYPE_java_lang_Object;
   }

   private IType getType2(Java.SimpleType st) {
      return st.iType;
   }

   private IClass getType2(Java.PrimitiveType bt) {
      switch (bt.primitive) {
         case VOID:
            return IClass.VOID;
         case BYTE:
            return IClass.BYTE;
         case SHORT:
            return IClass.SHORT;
         case CHAR:
            return IClass.CHAR;
         case INT:
            return IClass.INT;
         case LONG:
            return IClass.LONG;
         case FLOAT:
            return IClass.FLOAT;
         case DOUBLE:
            return IClass.DOUBLE;
         case BOOLEAN:
            return IClass.BOOLEAN;
         default:
            throw new InternalCompilerException(bt.getLocation(), "Invalid primitive " + bt.primitive);
      }
   }

   private IType getType2(Java.ReferenceType rt) throws CompileException {
      String[] identifiers = rt.identifiers;
      IType result = this.getReferenceType(rt.getLocation(), rt.getEnclosingScope(), identifiers, identifiers.length, rt.typeArguments);
      if (result == null) {
         this.compileError("Reference type \"" + rt + "\" not found", rt.getLocation());
         return this.iClassLoader.TYPE_java_lang_Object;
      } else {
         return result;
      }
   }

   @Nullable
   private IType getReferenceType(Location location, Java.Scope scope, String[] identifiers, int n, @Nullable Java.TypeArgument[] typeArguments) throws CompileException {
      if (n == 1) {
         return this.getReferenceType(location, identifiers[0], typeArguments, scope);
      } else {
         String className = Java.join(identifiers, ".", 0, n);
         IClass result = this.findTypeByName(location, className);
         if (result != null) {
            return result;
         } else {
            if (n >= 2) {
               IType enclosingType = this.getReferenceType(location, scope, identifiers, n - 1, new Java.TypeArgument[0]);
               if (enclosingType != null) {
                  String memberTypeName = identifiers[n - 1];
                  IClass memberType = this.findMemberType(enclosingType, memberTypeName, typeArguments, location);
                  if (memberType == null) {
                     this.compileError("\"" + enclosingType + "\" declares no member type \"" + memberTypeName + "\"", location);
                     return this.iClassLoader.TYPE_java_lang_Object;
                  }

                  return memberType;
               }
            }

            return null;
         }
      }
   }

   private IType getReferenceType(Location location, String simpleTypeName, @Nullable Java.TypeArgument[] typeArguments, Java.Scope scope) throws CompileException {
      if ("var".equals(simpleTypeName)) {
         this.compileError("Local variable type inference NYI", location);
         return this.iClassLoader.TYPE_java_lang_Object;
      } else {
         for(Java.Scope s = scope; !(s instanceof Java.CompilationUnit); s = s.getEnclosingScope()) {
            if (s instanceof Java.MethodDeclarator) {
               Java.MethodDeclarator md = (Java.MethodDeclarator)s;
               Java.TypeParameter[] typeParameters = md.getOptionalTypeParameters();
               if (typeParameters != null) {
                  for(Java.TypeParameter tp : typeParameters) {
                     if (tp.name.equals(simpleTypeName)) {
                        Java.ReferenceType[] ob = tp.bound;
                        IType[] boundTypes;
                        if (ob == null) {
                           boundTypes = new IType[]{this.iClassLoader.TYPE_java_lang_Object};
                        } else {
                           boundTypes = new IType[ob.length];

                           for(int i = 0; i < boundTypes.length; ++i) {
                              boundTypes[i] = this.getType((Java.Type)ob[i]);
                           }
                        }

                        return boundTypes[0];
                     }
                  }
               }
            }
         }

         for(Java.Scope s = scope; !(s instanceof Java.CompilationUnit); s = s.getEnclosingScope()) {
            if (s instanceof Java.NamedTypeDeclaration) {
               Java.NamedTypeDeclaration ntd = (Java.NamedTypeDeclaration)s;
               Java.TypeParameter[] typeParameters = ntd.getOptionalTypeParameters();
               if (typeParameters != null) {
                  for(Java.TypeParameter tp : typeParameters) {
                     if (tp.name.equals(simpleTypeName)) {
                        Java.ReferenceType[] ob = tp.bound;
                        IType[] boundTypes;
                        if (ob == null) {
                           boundTypes = new IClass[]{this.iClassLoader.TYPE_java_lang_Object};
                        } else {
                           boundTypes = new IClass[ob.length];

                           for(int i = 0; i < boundTypes.length; ++i) {
                              boundTypes[i] = this.getType((Java.Type)ob[i]);
                           }
                        }

                        return boundTypes[0];
                     }
                  }
               }
            }
         }

         try {
            return this.getRawReferenceType(location, simpleTypeName, scope);
         } catch (CompileException ce) {
            throw new CompileException(ce.getMessage(), location, ce);
         }
      }
   }

   private IType typeArgumentToIType(Java.TypeArgument ta) throws CompileException {
      if (ta instanceof Java.ReferenceType) {
         return this.getType((Java.Type)((Java.ReferenceType)ta));
      } else if (ta instanceof Java.Wildcard) {
         Java.Wildcard w = (Java.Wildcard)ta;
         final IType ub = (IType)(w.bounds == 1 ? this.getType((Java.Type)w.referenceType) : this.iClassLoader.TYPE_java_lang_Object);
         final IType lb = w.bounds == 2 ? this.getType((Java.Type)w.referenceType) : null;
         return new IWildcardType() {
            public IType getUpperBound() {
               return ub;
            }

            @Nullable
            public IType getLowerBound() {
               return lb;
            }

            public String toString() {
               String s = "?";
               if (ub != UnitCompiler.this.iClassLoader.TYPE_java_lang_Object) {
                  s = s + " extends " + ub;
               }

               if (lb != null) {
                  s = s + " super " + lb;
               }

               return s;
            }
         };
      } else if (ta instanceof Java.ArrayType) {
         return this.iClassLoader.TYPE_java_lang_Object;
      } else {
         throw new AssertionError(ta.getClass() + ": " + ta);
      }
   }

   private IClass getRawReferenceType(Location location, String simpleTypeName, Java.Scope scope) throws CompileException {
      Java.LocalClassDeclaration lcd = findLocalClassDeclaration(scope, simpleTypeName);
      if (lcd != null) {
         return this.resolve(lcd);
      } else {
         for(Java.Scope s = scope; !(s instanceof Java.CompilationUnit); s = s.getEnclosingScope()) {
            if (s instanceof Java.TypeDeclaration) {
               IClass mt = this.findMemberType(this.resolve((Java.AbstractTypeDeclaration)s), simpleTypeName, (Java.TypeArgument[])null, location);
               if (mt != null) {
                  return mt;
               }
            }
         }

         IClass importedClass = this.importSingleType(simpleTypeName, location);
         if (importedClass != null) {
            return importedClass;
         } else {
            Java.Scope s;
            for(s = scope; !(s instanceof Java.CompilationUnit); s = s.getEnclosingScope()) {
            }

            Java.CompilationUnit scopeCompilationUnit = (Java.CompilationUnit)s;
            Java.PackageMemberTypeDeclaration pmtd = scopeCompilationUnit.getPackageMemberTypeDeclaration(simpleTypeName);
            if (pmtd != null) {
               return this.resolve(pmtd);
            } else {
               Java.PackageDeclaration opd = scopeCompilationUnit.packageDeclaration;
               String pkg = opd == null ? null : opd.packageName;
               String className = pkg == null ? simpleTypeName : pkg + "." + simpleTypeName;
               IClass result = this.findTypeByName(location, className);
               if (result != null) {
                  return result;
               } else {
                  IClass importedClass = this.importTypeOnDemand(simpleTypeName, location);
                  if (importedClass != null) {
                     return importedClass;
                  } else {
                     importedClass = this.importSingleType(simpleTypeName, location);
                     if (importedClass != null) {
                        if (!this.isAccessible(importedClass, scope)) {
                           this.compileError("Member type \"" + simpleTypeName + "\" is not accessible", location);
                        }

                        return importedClass;
                     } else {
                        importedClass = null;

                        for(IClass mt : Iterables.filterByClass(this.importSingleStatic(simpleTypeName), IClass.class)) {
                           if (importedClass != null && mt != importedClass) {
                              this.compileError("Ambiguous static member type import: \"" + importedClass.toString() + "\" vs. \"" + mt + "\"");
                           }

                           importedClass = mt;
                        }

                        if (importedClass != null) {
                           return importedClass;
                        } else {
                           Iterator<IClass> it = Iterables.filterByClass(this.importStaticOnDemand(simpleTypeName).iterator(), IClass.class);
                           if (it.hasNext()) {
                              return (IClass)it.next();
                           } else {
                              IClass result = this.findTypeByName(location, simpleTypeName);
                              if (result != null) {
                                 return result;
                              } else {
                                 for(Java.Scope s = scope; !(s instanceof Java.CompilationUnit); s = s.getEnclosingScope()) {
                                    if (s instanceof Java.AnonymousClassDeclaration) {
                                       Java.AnonymousClassDeclaration acd = (Java.AnonymousClassDeclaration)s;
                                       Java.Type bt = acd.baseType;
                                       if (bt instanceof Java.ReferenceType) {
                                          Java.TypeArgument[] otas = ((Java.ReferenceType)bt).typeArguments;
                                          if (otas != null) {
                                             for(Java.TypeArgument ta : otas) {
                                                if (ta instanceof Java.ReferenceType) {
                                                   String[] is = ((Java.ReferenceType)ta).identifiers;
                                                   if (is.length == 1 && is[0].equals(simpleTypeName)) {
                                                      return this.iClassLoader.TYPE_java_lang_Object;
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }

                                 this.compileError("Cannot determine simple type name \"" + simpleTypeName + "\"", location);
                                 return this.iClassLoader.TYPE_java_lang_Object;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private List importStaticOnDemand(String simpleName) throws CompileException {
      List<Object> result = new ArrayList();

      for(Java.AbstractCompilationUnit.StaticImportOnDemandDeclaration siodd : Iterables.filterByClass(this.abstractCompilationUnit.importDeclarations, Java.AbstractCompilationUnit.StaticImportOnDemandDeclaration.class)) {
         IClass iClass = this.findTypeByFullyQualifiedName(siodd.getLocation(), siodd.identifiers);
         if (iClass == null) {
            this.compileError("Could not load \"" + Java.join(siodd.identifiers, ".") + "\"", siodd.getLocation());
         } else {
            this.importStatic(iClass, simpleName, result, siodd.getLocation());
         }
      }

      return result;
   }

   private IClass getType2(Java.RvalueMemberType rvmt) throws CompileException {
      IType rvt = this.getType(rvmt.rvalue);
      IClass memberType = this.findMemberType(rvt, rvmt.identifier, (Java.TypeArgument[])null, rvmt.getLocation());
      if (memberType == null) {
         this.compileError("\"" + rvt + "\" has no member type \"" + rvmt.identifier + "\"", rvmt.getLocation());
         return this.iClassLoader.TYPE_java_lang_Object;
      } else {
         return memberType;
      }
   }

   private IClass getType2(Java.ArrayType at) throws CompileException {
      return this.iClassLoader.getArrayIClass(this.getRawType(at.componentType));
   }

   private IType getType2(Java.AmbiguousName an) throws CompileException {
      return this.getType(this.reclassify(an));
   }

   private IClass getType2(Java.Package p) throws CompileException {
      this.compileError("Unknown variable or type \"" + p.name + "\"", p.getLocation());
      return this.iClassLoader.TYPE_java_lang_Object;
   }

   private IType getType2(Java.LocalVariableAccess lva) {
      return lva.localVariable.type;
   }

   private IType getType2(Java.FieldAccess fa) throws CompileException {
      return fa.field.getType();
   }

   private IClass getType2(Java.ArrayLength al) {
      return IClass.INT;
   }

   private IClass getType2(Java.ThisReference tr) throws CompileException {
      return this.getIClass(tr);
   }

   private IClass getType2(Java.LambdaExpression le) throws CompileException {
      throw compileException(le, "Compilation of lambda expression NYI");
   }

   private IClass getType2(Java.MethodReference mr) throws CompileException {
      throw compileException(mr, "Compilation of method reference NYI");
   }

   private IClass getType2(Java.ClassInstanceCreationReference cicr) throws CompileException {
      throw compileException(cicr, "Compilation of class instance creation reference NYI");
   }

   private IClass getType2(Java.ArrayCreationReference acr) throws CompileException {
      throw compileException(acr, "Compilation of array creation reference NYI");
   }

   private IType getType2(Java.QualifiedThisReference qtr) throws CompileException {
      return this.getTargetIType(qtr);
   }

   private IClass getType2(Java.ClassLiteral cl) {
      return this.iClassLoader.TYPE_java_lang_Class;
   }

   private IType getType2(Java.Assignment a) throws CompileException {
      return this.getType(a.lhs);
   }

   private IType getType2(Java.ConditionalExpression ce) throws CompileException {
      IType mhsType = this.getType(ce.mhs);
      IType rhsType = this.getType(ce.rhs);
      if (mhsType == rhsType) {
         return mhsType;
      } else if (this.isUnboxingConvertible(mhsType) == rhsType) {
         return rhsType;
      } else if (this.isUnboxingConvertible(rhsType) == mhsType) {
         return mhsType;
      } else if (this.getConstantValue(ce.mhs) == null && !isPrimitive(rhsType)) {
         return rhsType;
      } else if (this.getConstantValue(ce.mhs) == null && this.isBoxingConvertible(rhsType) != null) {
         IClass result = this.isBoxingConvertible(rhsType);

         assert result != null;

         return result;
      } else if (!isPrimitive(mhsType) && this.getConstantValue(ce.rhs) == null) {
         return mhsType;
      } else if (this.isBoxingConvertible(mhsType) != null && this.getConstantValue(ce.rhs) == null) {
         IClass result = this.isBoxingConvertible(mhsType);

         assert result != null;

         return result;
      } else if (this.isConvertibleToPrimitiveNumeric(mhsType) && this.isConvertibleToPrimitiveNumeric(rhsType)) {
         if (mhsType != IClass.BYTE && mhsType != this.iClassLoader.TYPE_java_lang_Byte || rhsType != IClass.SHORT && rhsType != this.iClassLoader.TYPE_java_lang_Short) {
            if (rhsType != IClass.BYTE && rhsType != this.iClassLoader.TYPE_java_lang_Byte || mhsType != IClass.SHORT && mhsType != this.iClassLoader.TYPE_java_lang_Short) {
               Object rhscv = this.getConstantValue(ce.rhs);
               if ((mhsType == IClass.BYTE || mhsType == IClass.SHORT || mhsType == IClass.CHAR) && rhscv != null && this.constantAssignmentConversion(ce.rhs, rhscv, mhsType) != null) {
                  return mhsType;
               } else {
                  Object mhscv = this.getConstantValue(ce.mhs);
                  if ((rhsType == IClass.BYTE || rhsType == IClass.SHORT || rhsType == IClass.CHAR) && mhscv != null && this.constantAssignmentConversion(ce.mhs, mhscv, rhsType) != null) {
                     return rhsType;
                  } else if (mhsType == IClass.INT && rhsType == IClass.BYTE && isByteConstant(mhscv) != null) {
                     ce.mhs.constantValue = isByteConstant(mhscv);
                     return IClass.BYTE;
                  } else if (rhsType == IClass.INT && mhsType == IClass.BYTE && isByteConstant(rhscv) != null) {
                     ce.rhs.constantValue = isByteConstant(rhscv);
                     return IClass.BYTE;
                  } else {
                     return this.binaryNumericPromotionType(ce, this.getUnboxedType(mhsType), this.getUnboxedType(rhsType));
                  }
               }
            } else {
               return IClass.SHORT;
            }
         } else {
            return IClass.SHORT;
         }
      } else if (isPrimitive(mhsType) && isPrimitive(rhsType)) {
         this.compileError("Incompatible expression types \"" + mhsType + "\" and \"" + rhsType + "\"", ce.getLocation());
         return this.iClassLoader.TYPE_java_lang_Object;
      } else {
         return this.commonSupertype(mhsType, rhsType);
      }
   }

   private IType getType2(Java.Crement c) throws CompileException {
      return this.getType(c.operand);
   }

   private IType getType2(Java.ArrayAccessExpression aae) throws CompileException {
      IType componentType = getComponentType(this.getType(aae.lhs));

      assert componentType != null : "null component type for " + aae;

      return componentType;
   }

   private IType getType2(Java.FieldAccessExpression fae) throws CompileException {
      this.determineValue(fae);
      return this.getType(this.determineValue(fae));
   }

   private IType getType2(Java.SuperclassFieldAccessExpression scfae) throws CompileException {
      this.determineValue(scfae);
      return this.getType(this.determineValue(scfae));
   }

   private IClass getType2(Java.UnaryOperation uo) throws CompileException {
      if (uo.operator == "!") {
         return IClass.BOOLEAN;
      } else if (uo.operator != "+" && uo.operator != "-" && uo.operator != "~") {
         this.compileError("Unexpected operator \"" + uo.operator + "\"", uo.getLocation());
         return IClass.BOOLEAN;
      } else {
         return this.unaryNumericPromotionType(uo, this.getUnboxedType(this.getType(uo.operand)));
      }
   }

   private IClass getType2(Java.Instanceof io) {
      return IClass.BOOLEAN;
   }

   private IType getType2(Java.BinaryOperation bo) throws CompileException {
      if (bo.operator != "||" && bo.operator != "&&" && bo.operator != "==" && bo.operator != "!=" && bo.operator != "<" && bo.operator != ">" && bo.operator != "<=" && bo.operator != ">=") {
         if (bo.operator != "|" && bo.operator != "^" && bo.operator != "&") {
            if (bo.operator != "*" && bo.operator != "/" && bo.operator != "%" && bo.operator != "+" && bo.operator != "-") {
               if (bo.operator != "<<" && bo.operator != ">>" && bo.operator != ">>>") {
                  this.compileError("Unexpected operator \"" + bo.operator + "\"", bo.getLocation());
                  return this.iClassLoader.TYPE_java_lang_Object;
               } else {
                  IType lhsType = this.getType(bo.lhs);
                  return this.unaryNumericPromotionType(bo, lhsType);
               }
            } else {
               IClassLoader icl = this.iClassLoader;
               Iterator<Java.Rvalue> ops = bo.unrollLeftAssociation();
               IType lhsType = this.getType((Java.Rvalue)ops.next());
               if (bo.operator == "+" && lhsType == icl.TYPE_java_lang_String) {
                  return icl.TYPE_java_lang_String;
               } else {
                  lhsType = this.getUnboxedType(lhsType);

                  do {
                     IType rhsType = this.getUnboxedType(this.getType((Java.Rvalue)ops.next()));
                     if (bo.operator == "+" && rhsType == icl.TYPE_java_lang_String) {
                        return icl.TYPE_java_lang_String;
                     }

                     lhsType = this.binaryNumericPromotionType(bo, lhsType, rhsType);
                  } while(ops.hasNext());

                  return lhsType;
               }
            }
         } else {
            IType lhsType = this.getType(bo.lhs);
            return lhsType != IClass.BOOLEAN && lhsType != this.iClassLoader.TYPE_java_lang_Boolean ? this.binaryNumericPromotionType(bo, lhsType, this.getType(bo.rhs)) : IClass.BOOLEAN;
         }
      } else {
         return IClass.BOOLEAN;
      }
   }

   private IType getUnboxedType(IType type) {
      IClass c = this.isUnboxingConvertible(type);
      return (IType)(c != null ? c : type);
   }

   private IType getType2(Java.Cast c) throws CompileException {
      return this.getType(c.targetType);
   }

   private IType getType2(Java.ParenthesizedExpression pe) throws CompileException {
      return this.getType(pe.value);
   }

   private IClass getType2(Java.MethodInvocation mi) throws CompileException {
      IClass.IMethod iMethod = mi.iMethod != null ? mi.iMethod : (mi.iMethod = this.findIMethod(mi));
      return iMethod.getReturnType();
   }

   private IClass getType2(Java.SuperclassMethodInvocation scmi) throws CompileException {
      return this.findIMethod(scmi).getReturnType();
   }

   private IType getType2(Java.NewClassInstance nci) throws CompileException {
      if (nci.iType != null) {
         return nci.iType;
      } else {
         assert nci.type != null;

         return nci.iType = this.getType(nci.type);
      }
   }

   private IClass getType2(Java.NewAnonymousClassInstance naci) {
      return this.resolve(naci.anonymousClassDeclaration);
   }

   private IType getType2(Java.ParameterAccess pa) throws CompileException {
      return this.getLocalVariable(pa.formalParameter).type;
   }

   private IClass getType2(Java.NewArray na) throws CompileException {
      return this.iClassLoader.getArrayIClass(rawTypeOf(this.getType(na.type)), na.dimExprs.length + na.dims);
   }

   private IType getType2(Java.NewInitializedArray nia) throws CompileException {
      IType at = (IType)(nia.arrayType != null ? this.getType((Java.Type)nia.arrayType) : nia.arrayIClass);

      assert at != null;

      return at;
   }

   private IClass getType2(Java.IntegerLiteral il) {
      String v = il.value;
      char lastChar = v.charAt(v.length() - 1);
      return lastChar != 'l' && lastChar != 'L' ? IClass.INT : IClass.LONG;
   }

   private IClass getType2(Java.FloatingPointLiteral fpl) {
      String v = fpl.value;
      char lastChar = v.charAt(v.length() - 1);
      return lastChar != 'f' && lastChar != 'F' ? IClass.DOUBLE : IClass.FLOAT;
   }

   private IClass getType2(Java.BooleanLiteral bl) {
      return IClass.BOOLEAN;
   }

   private IClass getType2(Java.CharacterLiteral cl) {
      return IClass.CHAR;
   }

   private IClass getType2(Java.StringLiteral sl) {
      return this.iClassLoader.TYPE_java_lang_String;
   }

   private IClass getType2(Java.NullLiteral nl) {
      return IClass.NULL;
   }

   private IClass getType2(Java.SimpleConstant sl) {
      Object v = sl.value;
      if (v instanceof Byte) {
         return IClass.BYTE;
      } else if (v instanceof Short) {
         return IClass.SHORT;
      } else if (v instanceof Integer) {
         return IClass.INT;
      } else if (v instanceof Long) {
         return IClass.LONG;
      } else if (v instanceof Float) {
         return IClass.FLOAT;
      } else if (v instanceof Double) {
         return IClass.DOUBLE;
      } else if (v instanceof Boolean) {
         return IClass.BOOLEAN;
      } else if (v instanceof Character) {
         return IClass.CHAR;
      } else if (v instanceof String) {
         return this.iClassLoader.TYPE_java_lang_String;
      } else if (v == null) {
         return IClass.NULL;
      } else {
         throw new InternalCompilerException(sl.getLocation(), "Invalid SimpleLiteral value type \"" + v.getClass() + "\"");
      }
   }

   private boolean isType(Java.Atom a) throws CompileException {
      Boolean result = (Boolean)a.accept(new Visitor.AtomVisitor() {
         public Boolean visitPackage(Java.Package p) {
            return UnitCompiler.this.isType2((Java.Atom)p);
         }

         @Nullable
         public Boolean visitType(Java.Type t) {
            return (Boolean)t.accept(new Visitor.TypeVisitor() {
               public Boolean visitArrayType(Java.ArrayType at) {
                  return UnitCompiler.this.isType2((Java.Atom)at);
               }

               public Boolean visitPrimitiveType(Java.PrimitiveType bt) {
                  return UnitCompiler.this.isType2((Java.Atom)bt);
               }

               public Boolean visitReferenceType(Java.ReferenceType rt) {
                  return UnitCompiler.this.isType2((Java.Atom)rt);
               }

               public Boolean visitRvalueMemberType(Java.RvalueMemberType rmt) {
                  return UnitCompiler.this.isType2((Java.Atom)rmt);
               }

               public Boolean visitSimpleType(Java.SimpleType st) {
                  return UnitCompiler.this.isType2((Java.Atom)st);
               }
            });
         }

         @Nullable
         public Boolean visitRvalue(Java.Rvalue rv) throws CompileException {
            return (Boolean)rv.accept(new Visitor.RvalueVisitor() {
               @Nullable
               public Boolean visitLvalue(Java.Lvalue lv) throws CompileException {
                  return (Boolean)lv.accept(new Visitor.LvalueVisitor() {
                     public Boolean visitAmbiguousName(Java.AmbiguousName an) throws CompileException {
                        return UnitCompiler.this.isType2(an);
                     }

                     public Boolean visitArrayAccessExpression(Java.ArrayAccessExpression aae) {
                        return UnitCompiler.this.isType2((Java.Atom)aae);
                     }

                     public Boolean visitFieldAccess(Java.FieldAccess fa) {
                        return UnitCompiler.this.isType2((Java.Atom)fa);
                     }

                     public Boolean visitFieldAccessExpression(Java.FieldAccessExpression fae) {
                        return UnitCompiler.this.isType2((Java.Atom)fae);
                     }

                     public Boolean visitSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression scfae) {
                        return UnitCompiler.this.isType2((Java.Atom)scfae);
                     }

                     public Boolean visitLocalVariableAccess(Java.LocalVariableAccess lva) {
                        return UnitCompiler.this.isType2((Java.Atom)lva);
                     }

                     public Boolean visitParenthesizedExpression(Java.ParenthesizedExpression pe) {
                        return UnitCompiler.this.isType2((Java.Atom)pe);
                     }
                  });
               }

               public Boolean visitArrayLength(Java.ArrayLength al) {
                  return UnitCompiler.this.isType2((Java.Atom)al);
               }

               public Boolean visitAssignment(Java.Assignment a) {
                  return UnitCompiler.this.isType2((Java.Atom)a);
               }

               public Boolean visitUnaryOperation(Java.UnaryOperation uo) {
                  return UnitCompiler.this.isType2((Java.Atom)uo);
               }

               public Boolean visitBinaryOperation(Java.BinaryOperation bo) {
                  return UnitCompiler.this.isType2((Java.Atom)bo);
               }

               public Boolean visitCast(Java.Cast c) {
                  return UnitCompiler.this.isType2((Java.Atom)c);
               }

               public Boolean visitClassLiteral(Java.ClassLiteral cl) {
                  return UnitCompiler.this.isType2((Java.Atom)cl);
               }

               public Boolean visitConditionalExpression(Java.ConditionalExpression ce) {
                  return UnitCompiler.this.isType2((Java.Atom)ce);
               }

               public Boolean visitCrement(Java.Crement c) {
                  return UnitCompiler.this.isType2((Java.Atom)c);
               }

               public Boolean visitInstanceof(Java.Instanceof io) {
                  return UnitCompiler.this.isType2((Java.Atom)io);
               }

               public Boolean visitMethodInvocation(Java.MethodInvocation mi) {
                  return UnitCompiler.this.isType2((Java.Atom)mi);
               }

               public Boolean visitSuperclassMethodInvocation(Java.SuperclassMethodInvocation smi) {
                  return UnitCompiler.this.isType2((Java.Atom)smi);
               }

               public Boolean visitIntegerLiteral(Java.IntegerLiteral il) {
                  return UnitCompiler.this.isType2((Java.Atom)il);
               }

               public Boolean visitFloatingPointLiteral(Java.FloatingPointLiteral fpl) {
                  return UnitCompiler.this.isType2((Java.Atom)fpl);
               }

               public Boolean visitBooleanLiteral(Java.BooleanLiteral bl) {
                  return UnitCompiler.this.isType2((Java.Atom)bl);
               }

               public Boolean visitCharacterLiteral(Java.CharacterLiteral cl) {
                  return UnitCompiler.this.isType2((Java.Atom)cl);
               }

               public Boolean visitStringLiteral(Java.StringLiteral sl) {
                  return UnitCompiler.this.isType2((Java.Atom)sl);
               }

               public Boolean visitNullLiteral(Java.NullLiteral nl) {
                  return UnitCompiler.this.isType2((Java.Atom)nl);
               }

               public Boolean visitSimpleConstant(Java.SimpleConstant sl) {
                  return UnitCompiler.this.isType2((Java.Atom)sl);
               }

               public Boolean visitNewAnonymousClassInstance(Java.NewAnonymousClassInstance naci) {
                  return UnitCompiler.this.isType2((Java.Atom)naci);
               }

               public Boolean visitNewArray(Java.NewArray na) {
                  return UnitCompiler.this.isType2((Java.Atom)na);
               }

               public Boolean visitNewInitializedArray(Java.NewInitializedArray nia) {
                  return UnitCompiler.this.isType2((Java.Atom)nia);
               }

               public Boolean visitNewClassInstance(Java.NewClassInstance nci) {
                  return UnitCompiler.this.isType2((Java.Atom)nci);
               }

               public Boolean visitParameterAccess(Java.ParameterAccess pa) {
                  return UnitCompiler.this.isType2((Java.Atom)pa);
               }

               public Boolean visitQualifiedThisReference(Java.QualifiedThisReference qtr) {
                  return UnitCompiler.this.isType2((Java.Atom)qtr);
               }

               public Boolean visitThisReference(Java.ThisReference tr) {
                  return UnitCompiler.this.isType2((Java.Atom)tr);
               }

               public Boolean visitLambdaExpression(Java.LambdaExpression le) {
                  return UnitCompiler.this.isType2((Java.Atom)le);
               }

               public Boolean visitMethodReference(Java.MethodReference mr) {
                  return UnitCompiler.this.isType2((Java.Atom)mr);
               }

               public Boolean visitInstanceCreationReference(Java.ClassInstanceCreationReference cicr) {
                  return UnitCompiler.this.isType2((Java.Atom)cicr);
               }

               public Boolean visitArrayCreationReference(Java.ArrayCreationReference acr) {
                  return UnitCompiler.this.isType2((Java.Atom)acr);
               }
            });
         }

         @Nullable
         public Boolean visitConstructorInvocation(Java.ConstructorInvocation ci) {
            return false;
         }
      });

      assert result != null;

      return result;
   }

   private boolean isType2(Java.Atom a) {
      return a instanceof Java.Type;
   }

   private boolean isType2(Java.AmbiguousName an) throws CompileException {
      return this.isType(this.reclassify(an));
   }

   private boolean isAccessible(IClass.IMember member, Java.Scope contextScope) throws CompileException {
      IClass declaringIClass = member.getDeclaringIClass();
      return this.isAccessible(declaringIClass, contextScope) && this.isAccessible(declaringIClass, member.getAccess(), contextScope);
   }

   private void checkAccessible(IClass.IMember member, Java.Scope contextScope, Location location) throws CompileException {
      IClass declaringIClass = member.getDeclaringIClass();
      this.checkAccessible(declaringIClass, contextScope, location);
      this.checkMemberAccessible(declaringIClass, member, contextScope, location);
   }

   private boolean isAccessible(IClass iClassDeclaringMember, Access memberAccess, Java.Scope contextScope) throws CompileException {
      return null == this.internalCheckAccessible(iClassDeclaringMember, memberAccess, contextScope);
   }

   private void checkMemberAccessible(IClass iClassDeclaringMember, IClass.IMember member, Java.Scope contextScope, Location location) throws CompileException {
      String message = this.internalCheckAccessible(iClassDeclaringMember, member.getAccess(), contextScope);
      if (message != null) {
         this.compileError(member.toString() + ": " + message, location);
      }

   }

   @Nullable
   private String internalCheckAccessible(IClass iClassDeclaringMember, Access memberAccess, Java.Scope contextScope) throws CompileException {
      if (memberAccess == Access.PUBLIC) {
         return null;
      } else {
         IClass iClassDeclaringContext = null;

         for(Java.Scope s = contextScope; !(s instanceof Java.CompilationUnit); s = s.getEnclosingScope()) {
            if (s instanceof Java.TypeDeclaration) {
               iClassDeclaringContext = this.resolve((Java.TypeDeclaration)s);
               break;
            }
         }

         if (iClassDeclaringContext == iClassDeclaringMember) {
            return null;
         } else {
            if (iClassDeclaringContext != null && !this.options.contains(JaninoOption.PRIVATE_MEMBERS_OF_ENCLOSING_AND_ENCLOSED_TYPES_INACCESSIBLE)) {
               IClass topLevelIClassEnclosingMember = iClassDeclaringMember;

               for(IClass c = iClassDeclaringMember.getDeclaringIClass(); c != null; c = c.getDeclaringIClass()) {
                  topLevelIClassEnclosingMember = c;
               }

               IClass topLevelIClassEnclosingContextBlockStatement = iClassDeclaringContext;

               for(IClass c = iClassDeclaringContext.getDeclaringIClass(); c != null; c = c.getDeclaringIClass()) {
                  topLevelIClassEnclosingContextBlockStatement = c;
               }

               if (topLevelIClassEnclosingMember == topLevelIClassEnclosingContextBlockStatement) {
                  return null;
               }
            }

            if (memberAccess == Access.PRIVATE) {
               return "Private member cannot be accessed from type \"" + iClassDeclaringContext + "\".";
            } else if (iClassDeclaringContext != null && Descriptor.areInSamePackage(iClassDeclaringMember.getDescriptor(), iClassDeclaringContext.getDescriptor())) {
               return null;
            } else if (memberAccess == Access.DEFAULT) {
               return "Member with \"package\" access cannot be accessed from type \"" + iClassDeclaringContext + "\".";
            } else {
               IClass parentClass = iClassDeclaringContext;

               while($assertionsDisabled || parentClass != null) {
                  if (iClassDeclaringMember.isAssignableFrom(parentClass)) {
                     return null;
                  }

                  parentClass = parentClass.getOuterIClass();
                  if (parentClass == null) {
                     return "Protected member cannot be accessed from type \"" + iClassDeclaringContext + "\", which is neither declared in the same package as nor is a subclass of \"" + iClassDeclaringMember + "\".";
                  }
               }

               throw new AssertionError();
            }
         }
      }
   }

   private boolean isAccessible(IClass type, Java.Scope contextScope) throws CompileException {
      return null == this.internalCheckAccessible(type, contextScope);
   }

   private void checkAccessible(IClass type, Java.Scope contextScope, Location location) throws CompileException {
      String message = this.internalCheckAccessible(type, contextScope);
      if (message != null) {
         this.compileError(message, location);
      }

   }

   @Nullable
   private String internalCheckAccessible(IClass type, Java.Scope contextScope) throws CompileException {
      IClass iClassDeclaringType = type.getDeclaringIClass();
      if (iClassDeclaringType != null) {
         return this.internalCheckAccessible(iClassDeclaringType, type.getAccess(), contextScope);
      } else if (type.getAccess() == Access.PUBLIC) {
         return null;
      } else if (type.getAccess() != Access.DEFAULT) {
         throw new InternalCompilerException("\"" + type + "\" has unexpected access \"" + type.getAccess() + "\"");
      } else {
         Java.Scope s = contextScope;

         IClass iClassDeclaringContextBlockStatement;
         while(true) {
            if (s instanceof Java.TypeDeclaration) {
               iClassDeclaringContextBlockStatement = this.resolve((Java.TypeDeclaration)s);
               break;
            }

            if (s instanceof Java.EnclosingScopeOfTypeDeclaration) {
               iClassDeclaringContextBlockStatement = this.resolve(((Java.EnclosingScopeOfTypeDeclaration)s).typeDeclaration);
               break;
            }

            s = s.getEnclosingScope();
         }

         String packageDeclaringType = Descriptor.getPackageName(type.getDescriptor());
         String contextPackage = Descriptor.getPackageName(iClassDeclaringContextBlockStatement.getDescriptor());
         if (packageDeclaringType == null) {
            if (contextPackage != null) {
               return "\"" + type + "\" is inaccessible from this package";
            }
         } else if (!packageDeclaringType.equals(contextPackage)) {
            return "\"" + type + "\" is inaccessible from this package";
         }

         return null;
      }
   }

   private Java.Type toTypeOrCompileException(Java.Atom a) throws CompileException {
      Java.Type result = a.toType();
      if (result == null) {
         this.compileError("Expression \"" + a.toString() + "\" is not a type", a.getLocation());
         return new Java.SimpleType(a.getLocation(), this.iClassLoader.TYPE_java_lang_Object);
      } else {
         return result;
      }
   }

   private Java.Rvalue toRvalueOrCompileException(Java.Atom a) throws CompileException {
      Java.Rvalue result = a.toRvalue();
      if (result == null) {
         this.compileError("Expression \"" + a.toString() + "\" is not an rvalue", a.getLocation());
         return new Java.StringLiteral(a.getLocation(), "\"X\"");
      } else {
         return result;
      }
   }

   private Java.Lvalue toLvalueOrCompileException(final Java.Atom a) throws CompileException {
      Java.Lvalue result = a.toLvalue();
      if (result == null) {
         this.compileError("Expression \"" + a.toString() + "\" is not an lvalue", a.getLocation());
         return new Java.Lvalue(a.getLocation()) {
            @Nullable
            public Object accept(Visitor.LvalueVisitor visitor) {
               return null;
            }

            public String toString() {
               return a.toString();
            }
         };
      } else {
         return result;
      }
   }

   void assignSyntheticParametersToSyntheticFields(Java.ConstructorDeclarator cd) throws CompileException {
      for(IClass.IField sf : cd.getDeclaringClass().syntheticFields.values()) {
         Java.LocalVariable syntheticParameter = (Java.LocalVariable)cd.syntheticParameters.get(sf.getName());
         if (syntheticParameter == null) {
            throw new InternalCompilerException(cd.getLocation(), "SNO: Synthetic parameter for synthetic field \"" + sf.getName() + "\" not found");
         }

         Java.ExpressionStatement es = new Java.ExpressionStatement(new Java.Assignment(cd.getLocation(), new Java.FieldAccess(cd.getLocation(), new Java.ThisReference(cd.getLocation()), sf), "=", new Java.LocalVariableAccess(cd.getLocation(), syntheticParameter)));
         es.setEnclosingScope(cd);
         this.compile((Java.BlockStatement)es);
      }

   }

   void initializeInstanceVariablesAndInvokeInstanceInitializers(Java.ConstructorDeclarator cd) throws CompileException {
      List<Java.FieldDeclarationOrInitializer> vdais = cd.getDeclaringClass().fieldDeclarationsAndInitializers;

      for(int i = 0; i < vdais.size(); ++i) {
         Java.BlockStatement bs = (Java.BlockStatement)vdais.get(i);
         if ((!(bs instanceof Java.Initializer) || !((Java.Initializer)bs).isStatic()) && (!(bs instanceof Java.FieldDeclaration) || !((Java.FieldDeclaration)bs).isStatic()) && !this.compile(bs)) {
            this.compileError("Instance variable declarator or instance initializer does not complete normally", bs.getLocation());
         }
      }

   }

   private void leaveStatements(Java.Scope from, Java.Scope to) throws CompileException {
      Java.Scope prev = null;

      for(Java.Scope s = from; s != to; s = s.getEnclosingScope()) {
         if (s instanceof Java.BlockStatement && (!(s instanceof Java.TryStatement) || ((Java.TryStatement)s).finallY != prev)) {
            this.leave((Java.BlockStatement)s);
         }

         prev = s;
      }

   }

   private IType compileArithmeticBinaryOperation(Java.Locatable locatable, IType lhsType, String operator, Java.Rvalue rhs) throws CompileException {
      return this.compileArithmeticOperation(locatable, lhsType, Arrays.asList(rhs).iterator(), operator);
   }

   private IType compileArithmeticOperation(Java.Locatable locatable, @Nullable IType firstOperandType, Iterator operands, String operator) throws CompileException {
      if (operator == "+" && firstOperandType == this.iClassLoader.TYPE_java_lang_String) {
         assert firstOperandType != null;

         return this.compileStringConcatenation(locatable, firstOperandType, (Java.Rvalue)operands.next(), operands);
      } else {
         IType type = firstOperandType == null ? this.compileGetValue((Java.Rvalue)operands.next()) : firstOperandType;
         if (operator != "|" && operator != "^" && operator != "&") {
            if (operator != "*" && operator != "/" && operator != "%" && operator != "+" && operator != "-") {
               if (operator != "<<" && operator != ">>" && operator != ">>>") {
                  throw new InternalCompilerException(locatable.getLocation(), "Unexpected operator \"" + operator + "\"");
               } else {
                  Java.Rvalue operand;
                  for(; operands.hasNext(); this.shift(operand, operator)) {
                     operand = (Java.Rvalue)operands.next();
                     type = this.unaryNumericPromotion(operand, type);
                     IType rhsType = this.compileGetValue(operand);
                     IClass promotedRhsType = this.unaryNumericPromotion(operand, rhsType);
                     if (promotedRhsType != IClass.INT) {
                        if (promotedRhsType == IClass.LONG) {
                           this.l2i(operand);
                        } else {
                           this.compileError("Shift distance of type \"" + rhsType + "\" is not allowed", locatable.getLocation());
                        }
                     }
                  }

                  return type;
               }
            } else {
               while(operands.hasNext()) {
                  Java.Rvalue operand = (Java.Rvalue)operands.next();
                  if (operator == "+" && (type == this.iClassLoader.TYPE_java_lang_String || this.getType(operand) == this.iClassLoader.TYPE_java_lang_String)) {
                     return this.compileStringConcatenation(locatable, type, operand, operands);
                  }

                  IType rhsType = this.getType(operand);
                  IClass promotedType = this.binaryNumericPromotionType(operand, this.getUnboxedType(type), this.getUnboxedType(rhsType));
                  this.numericPromotion(operand, this.convertToPrimitiveNumericType(operand, type), promotedType);
                  this.compileGetValue(operand);
                  this.numericPromotion(operand, this.convertToPrimitiveNumericType(operand, rhsType), promotedType);
                  this.mulDivRemAddSub(operand, operator);
                  type = promotedType;
               }

               return type;
            }
         } else {
            while(operands.hasNext()) {
               Java.Rvalue operand = (Java.Rvalue)operands.next();
               IType rhsType = this.getType(operand);
               if (this.isConvertibleToPrimitiveNumeric(type) && this.isConvertibleToPrimitiveNumeric(rhsType)) {
                  IClass promotedType = this.binaryNumericPromotionType(operand, this.getUnboxedType(type), this.getUnboxedType(rhsType));
                  if (promotedType != IClass.INT && promotedType != IClass.LONG) {
                     throw new CompileException("Invalid operand type " + promotedType, operand.getLocation());
                  }

                  this.numericPromotion(operand, this.convertToPrimitiveNumericType(operand, type), promotedType);
                  this.compileGetValue(operand);
                  this.numericPromotion(operand, this.convertToPrimitiveNumericType(operand, rhsType), promotedType);
                  this.andOrXor(operand, operator);
                  type = promotedType;
               } else if ((type == IClass.BOOLEAN || this.getUnboxedType(type) == IClass.BOOLEAN) && (rhsType == IClass.BOOLEAN || this.getUnboxedType(rhsType) == IClass.BOOLEAN)) {
                  IClassLoader icl = this.iClassLoader;
                  if (type == icl.TYPE_java_lang_Boolean) {
                     this.unboxingConversion(locatable, icl.TYPE_java_lang_Boolean, IClass.BOOLEAN);
                  }

                  this.compileGetValue(operand);
                  if (rhsType == icl.TYPE_java_lang_Boolean) {
                     this.unboxingConversion(locatable, icl.TYPE_java_lang_Boolean, IClass.BOOLEAN);
                  }

                  this.andOrXor(operand, operator);
                  type = IClass.BOOLEAN;
               } else {
                  this.compileError("Operator \"" + operator + "\" not defined on types \"" + type + "\" and \"" + rhsType + "\"", locatable.getLocation());
                  type = IClass.INT;
               }
            }

            return type;
         }
      }
   }

   private IClass compileStringConcatenation(Java.Locatable locatable, IType type, Java.Rvalue secondOperand, Iterator operands) throws CompileException {
      this.stringConversion(locatable, type);
      List<Java.Rvalue> tmp = new ArrayList();
      Java.Rvalue nextOperand = secondOperand;

      while(nextOperand != null) {
         Object cv = this.getConstantValue(nextOperand);
         if (cv == NOT_CONSTANT) {
            tmp.add(nextOperand);
            nextOperand = operands.hasNext() ? (Java.Rvalue)operands.next() : null;
         } else {
            if (operands.hasNext()) {
               nextOperand = (Java.Rvalue)operands.next();
               Object cv2 = this.getConstantValue(nextOperand);
               if (cv2 != NOT_CONSTANT) {
                  StringBuilder sb = (new StringBuilder(String.valueOf(cv))).append(cv2);

                  while(true) {
                     if (!operands.hasNext()) {
                        nextOperand = null;
                        break;
                     }

                     nextOperand = (Java.Rvalue)operands.next();
                     Object cv3 = this.getConstantValue(nextOperand);
                     if (cv3 == NOT_CONSTANT) {
                        break;
                     }

                     sb.append(cv3);
                  }

                  cv = sb.toString();
               }
            } else {
               nextOperand = null;
            }

            for(String s : makeUtf8Able(String.valueOf(cv))) {
               tmp.add(new Java.SimpleConstant(locatable.getLocation(), s));
            }
         }
      }

      if (tmp.size() > 2) {
         this.neW(locatable, this.iClassLoader.TYPE_java_lang_StringBuilder);
         this.dupx(locatable);
         this.swap(locatable);
         this.invokeConstructor(locatable, this.iClassLoader.CTOR_java_lang_StringBuilder__java_lang_String);
         this.getCodeContext().popUninitializedVariableOperand();
         this.getCodeContext().pushObjectOperand("Ljava/lang/StringBuilder;");

         for(Java.Rvalue operand : tmp) {
            IType t = this.compileGetValue(operand);
            this.invokeMethod(locatable, t == IClass.BYTE ? this.iClassLoader.METH_java_lang_StringBuilder__append__int : (t == IClass.SHORT ? this.iClassLoader.METH_java_lang_StringBuilder__append__int : (t == IClass.INT ? this.iClassLoader.METH_java_lang_StringBuilder__append__int : (t == IClass.LONG ? this.iClassLoader.METH_java_lang_StringBuilder__append__long : (t == IClass.FLOAT ? this.iClassLoader.METH_java_lang_StringBuilder__append__float : (t == IClass.DOUBLE ? this.iClassLoader.METH_java_lang_StringBuilder__append__double : (t == IClass.CHAR ? this.iClassLoader.METH_java_lang_StringBuilder__append__char : (t == IClass.BOOLEAN ? this.iClassLoader.METH_java_lang_StringBuilder__append__boolean : this.iClassLoader.METH_java_lang_StringBuilder__append__java_lang_Object))))))));
         }

         this.invokeMethod(locatable, this.iClassLoader.METH_java_lang_StringBuilder__toString);
         return this.iClassLoader.TYPE_java_lang_String;
      } else {
         for(Java.Rvalue operand : tmp) {
            this.stringConversion(operand, this.compileGetValue(operand));
            this.invokeMethod(locatable, this.iClassLoader.METH_java_lang_String__concat__java_lang_String);
         }

         return this.iClassLoader.TYPE_java_lang_String;
      }
   }

   private void stringConversion(Java.Locatable locatable, IType sourceType) throws CompileException {
      IClass sourceClass = rawTypeOf(sourceType);
      this.invokeMethod(locatable, sourceClass == IClass.BYTE ? this.iClassLoader.METH_java_lang_String__valueOf__int : (sourceClass == IClass.SHORT ? this.iClassLoader.METH_java_lang_String__valueOf__int : (sourceClass == IClass.INT ? this.iClassLoader.METH_java_lang_String__valueOf__int : (sourceClass == IClass.LONG ? this.iClassLoader.METH_java_lang_String__valueOf__long : (sourceClass == IClass.FLOAT ? this.iClassLoader.METH_java_lang_String__valueOf__float : (sourceClass == IClass.DOUBLE ? this.iClassLoader.METH_java_lang_String__valueOf__double : (sourceClass == IClass.CHAR ? this.iClassLoader.METH_java_lang_String__valueOf__char : (sourceClass == IClass.BOOLEAN ? this.iClassLoader.METH_java_lang_String__valueOf__boolean : this.iClassLoader.METH_java_lang_String__valueOf__java_lang_Object))))))));
   }

   private void invokeConstructor(Java.Locatable locatable, Java.Scope scope, @Nullable Java.Rvalue enclosingInstance, IType targetType, Java.Rvalue[] arguments) throws CompileException {
      IClass rawTargetType = rawTypeOf(targetType);
      IClass.IConstructor[] iConstructors = rawTargetType.getDeclaredIConstructors();
      if (iConstructors.length == 0) {
         throw new InternalCompilerException(locatable.getLocation(), "SNO: Target class \"" + rawTargetType.getDescriptor() + "\" has no constructors");
      } else {
         IClass.IConstructor iConstructor = (IClass.IConstructor)this.findMostSpecificIInvocable(locatable, iConstructors, arguments, scope);
         IClass[] thrownExceptions = iConstructor.getThrownExceptions();

         for(IClass te : thrownExceptions) {
            this.checkThrownException(locatable, te, scope);
         }

         if (scope instanceof Java.FieldDeclaration && scope.getEnclosingScope() instanceof Java.EnumDeclaration) {
            Java.FieldDeclaration fd = (Java.FieldDeclaration)scope;
            Java.EnumDeclaration ed = (Java.EnumDeclaration)fd.getEnclosingScope();
            if (fd.variableDeclarators.length == 1) {
               String fieldName = fd.variableDeclarators[0].name;
               int ordinal = 0;

               for(Java.EnumConstant ec : ed.getConstants()) {
                  if (fieldName.equals(ec.name)) {
                     this.consT(locatable, fieldName);
                     this.consT(locatable, ordinal);
                     break;
                  }

                  ++ordinal;
               }
            }
         }

         if (enclosingInstance != null) {
            IClass outerIClass = rawTargetType.getOuterIClass();
            if (outerIClass != null) {
               IType eiic = this.compileGetValue(enclosingInstance);
               if (!isAssignableFrom(outerIClass, eiic)) {
                  this.compileError("Type of enclosing instance (\"" + eiic + "\") is not assignable to \"" + outerIClass + "\"", locatable.getLocation());
               }
            }
         }

         IClass.IField[] syntheticFields = rawTargetType.getSyntheticIFields();

         Java.Scope s;
         for(s = scope; !(s instanceof Java.TypeBodyDeclaration); s = s.getEnclosingScope()) {
         }

         Java.TypeBodyDeclaration scopeTbd = (Java.TypeBodyDeclaration)s;
         Java.TypeDeclaration scopeTypeDeclaration = scopeTbd.getDeclaringType();
         if (!(scopeTypeDeclaration instanceof Java.AbstractClassDeclaration) && syntheticFields.length > 0) {
            throw new InternalCompilerException(locatable.getLocation(), "SNO: Target class has synthetic fields");
         } else {
            if (scopeTypeDeclaration instanceof Java.AbstractClassDeclaration) {
               Java.AbstractClassDeclaration scopeClassDeclaration = (Java.AbstractClassDeclaration)scopeTypeDeclaration;

               for(IClass.IField sf : syntheticFields) {
                  if (sf.getName().startsWith("val$")) {
                     IClass.IField eisf = (IClass.IField)scopeClassDeclaration.syntheticFields.get(sf.getName());
                     if (eisf != null) {
                        if (scopeTbd instanceof Java.MethodDeclarator) {
                           this.load(locatable, this.resolve(scopeClassDeclaration), 0);
                           this.getfield(locatable, eisf);
                        } else if (scopeTbd instanceof Java.ConstructorDeclarator) {
                           Java.ConstructorDeclarator constructorDeclarator = (Java.ConstructorDeclarator)scopeTbd;
                           Java.LocalVariable syntheticParameter = (Java.LocalVariable)constructorDeclarator.syntheticParameters.get(sf.getName());
                           if (syntheticParameter == null) {
                              this.compileError("Compiler limitation: Constructor cannot access local variable \"" + sf.getName().substring(4) + "\" declared in an enclosing block because none of the methods accesses it. As a workaround, declare a dummy method that accesses the local variable.", locatable.getLocation());
                              this.consT(locatable, (Object)null);
                           } else {
                              this.load(locatable, syntheticParameter);
                           }
                        } else {
                           if (!(scopeTbd instanceof Java.FieldDeclaration)) {
                              throw new AssertionError(scopeTbd);
                           }

                           this.compileError("Compiler limitation: Field initializers cannot access local variable \"" + sf.getName().substring(4) + "\" declared in an enclosing block because none of the methods accesses it. As a workaround, declare a dummy method that accesses the local variable.", locatable.getLocation());
                           this.consT(locatable, (Object)null);
                        }
                     } else {
                        String localVariableName = sf.getName().substring(4);
                        Java.Scope s = scope;

                        Java.LocalVariable lv;
                        label179:
                        while(true) {
                           if (!(s instanceof Java.BlockStatement)) {
                              while(!(s instanceof Java.FunctionDeclarator)) {
                                 s = s.getEnclosingScope();
                              }

                              Java.FunctionDeclarator fd = (Java.FunctionDeclarator)s;

                              for(Java.FunctionDeclarator.FormalParameter fp : fd.formalParameters.parameters) {
                                 if (fp.name.equals(localVariableName)) {
                                    lv = this.getLocalVariable(fp);
                                    break label179;
                                 }
                              }

                              throw new InternalCompilerException(fd.getLocation(), "SNO: Synthetic field \"" + sf.getName() + "\" neither maps a synthetic field of an enclosing instance nor a local variable");
                           }

                           label170: {
                              Java.BlockStatement bs = (Java.BlockStatement)s;
                              Java.Scope es = bs.getEnclosingScope();
                              List<? extends Java.BlockStatement> statements;
                              if (es instanceof Java.Block) {
                                 statements = ((Java.Block)es).statements;
                              } else {
                                 if (!(es instanceof Java.FunctionDeclarator)) {
                                    if (es instanceof Java.ForEachStatement) {
                                       Java.FunctionDeclarator.FormalParameter fp = ((Java.ForEachStatement)es).currentElement;
                                       if (fp.name.equals(localVariableName)) {
                                          lv = this.getLocalVariable(fp);
                                          break;
                                       }
                                    }
                                    break label170;
                                 }

                                 statements = ((Java.FunctionDeclarator)es).statements;
                              }

                              if (statements != null) {
                                 for(Java.BlockStatement bs2 : statements) {
                                    if (bs2 == bs) {
                                       break;
                                    }

                                    if (bs2 instanceof Java.LocalVariableDeclarationStatement) {
                                       Java.LocalVariableDeclarationStatement lvds = (Java.LocalVariableDeclarationStatement)bs2;

                                       for(Java.VariableDeclarator vd : lvds.variableDeclarators) {
                                          if (vd.name.equals(localVariableName)) {
                                             lv = this.getLocalVariable(lvds, vd);
                                             break label179;
                                          }
                                       }
                                    }
                                 }
                              }
                           }

                           s = s.getEnclosingScope();
                        }

                        this.load(locatable, lv);
                     }
                  }
               }
            }

            Java.Rvalue[] adjustedArgs = null;
            IClass[] parameterTypes = iConstructor.getParameterTypes();
            int actualSize = arguments.length;
            if (iConstructor.isVarargs() && iConstructor.argsNeedAdjust()) {
               adjustedArgs = new Java.Rvalue[parameterTypes.length];
               Java.Rvalue[] lastArgs = new Java.Rvalue[actualSize - parameterTypes.length + 1];
               int i = 0;

               for(int j = parameterTypes.length - 1; i < lastArgs.length; ++j) {
                  lastArgs[i] = arguments[j];
                  ++i;
               }

               for(int i = parameterTypes.length - 2; i >= 0; --i) {
                  adjustedArgs[i] = arguments[i];
               }

               Location loc = ((Java.Locatable)(lastArgs.length == 0 ? locatable : lastArgs[lastArgs.length - 1])).getLocation();
               adjustedArgs[adjustedArgs.length - 1] = new Java.NewInitializedArray(loc, parameterTypes[parameterTypes.length - 1], new Java.ArrayInitializer(loc, lastArgs));
               arguments = adjustedArgs;
            }

            for(int i = 0; i < arguments.length; ++i) {
               this.assignmentConversion(locatable, this.compileGetValue(arguments[i]), parameterTypes[i], this.getConstantValue(arguments[i]));
            }

            this.invokeConstructor(locatable, iConstructor);
         }
      }
   }

   private IClass.IField[] compileFields(Java.FieldDeclaration fieldDeclaration) {
      IClass.IField[] result = new IClass.IField[fieldDeclaration.variableDeclarators.length];

      for(int i = 0; i < result.length; ++i) {
         Java.VariableDeclarator vd = fieldDeclaration.variableDeclarators[i];
         result[i] = this.compileField(fieldDeclaration.getDeclaringType(), fieldDeclaration.getAnnotations(), fieldDeclaration.getAccess(), fieldDeclaration.isStatic(), fieldDeclaration.isFinal(), fieldDeclaration.type, vd.brackets, vd.name, vd.initializer);
      }

      return result;
   }

   private IClass.IField compileField(final Java.TypeDeclaration declaringType, final Java.Annotation[] annotations, final Access access, final boolean statiC, final boolean finaL, final Java.Type type, final int brackets, final String name, @Nullable final Java.ArrayInitializerOrRvalue initializer) {
      IClass var10003 = this.resolve(declaringType);
      Objects.requireNonNull(var10003);
      return new IClass.IField(var10003) {
         @Nullable
         private IClass.IAnnotation[] ias;

         {
            Objects.requireNonNull(x0);
         }

         public Access getAccess() {
            return declaringType instanceof Java.InterfaceDeclaration ? Access.PUBLIC : access;
         }

         public IClass.IAnnotation[] getAnnotations() {
            if (this.ias != null) {
               return this.ias;
            } else {
               try {
                  return this.ias = UnitCompiler.this.toIAnnotations(annotations);
               } catch (CompileException ce) {
                  throw new InternalCompilerException(declaringType.getLocation(), (String)null, ce);
               }
            }
         }

         public boolean isStatic() {
            return declaringType instanceof Java.InterfaceDeclaration || statiC;
         }

         public IClass getType() throws CompileException {
            return UnitCompiler.this.iClassLoader.getArrayIClass(UnitCompiler.this.getRawType(type), brackets);
         }

         public String getName() {
            return name;
         }

         @Nullable
         public Object getConstantValue() throws CompileException {
            if (finaL && initializer != null) {
               Object constantInitializerValue = UnitCompiler.this.getConstantValue(initializer);
               if (constantInitializerValue != UnitCompiler.NOT_CONSTANT) {
                  return UnitCompiler.this.constantAssignmentConversion(initializer, constantInitializerValue, this.getType());
               }
            }

            return UnitCompiler.NOT_CONSTANT;
         }
      };
   }

   @Nullable
   Java.ArrayInitializerOrRvalue getNonConstantFinalInitializer(Java.FieldDeclaration fd, Java.VariableDeclarator vd) throws CompileException {
      if (vd.initializer == null) {
         return null;
      } else {
         return fd.isStatic() && fd.isFinal() && vd.initializer instanceof Java.Rvalue && this.getConstantValue((Java.Rvalue)vd.initializer) != NOT_CONSTANT ? null : vd.initializer;
      }
   }

   private Java.Atom reclassify(Java.AmbiguousName an) throws CompileException {
      return an.reclassified != null ? an.reclassified : (an.reclassified = this.reclassifyName(an.getLocation(), an.getEnclosingScope(), an.identifiers, an.n));
   }

   private IClass.IAnnotation[] toIAnnotations(Java.Annotation[] annotations) throws CompileException {
      IClass.IAnnotation[] result = new IClass.IAnnotation[annotations.length];

      for(int i = 0; i < result.length; ++i) {
         result[i] = this.toIAnnotation(annotations[i]);
      }

      return result;
   }

   private IClass.IAnnotation toIAnnotation(Java.Annotation annotation) throws CompileException {
      IClass.IAnnotation result = (IClass.IAnnotation)annotation.accept(new Visitor.AnnotationVisitor() {
         public IClass.IAnnotation visitMarkerAnnotation(Java.MarkerAnnotation ma) throws CompileException {
            return this.toIAnnotation(ma.type, new Java.ElementValuePair[0]);
         }

         public IClass.IAnnotation visitSingleElementAnnotation(Java.SingleElementAnnotation sea) throws CompileException {
            return this.toIAnnotation(sea.type, new Java.ElementValuePair[]{new Java.ElementValuePair("value", sea.elementValue)});
         }

         public IClass.IAnnotation visitNormalAnnotation(Java.NormalAnnotation na) throws CompileException {
            return this.toIAnnotation(na.type, na.elementValuePairs);
         }

         private IClass.IAnnotation toIAnnotation(final Java.Type type, Java.ElementValuePair[] elementValuePairs) throws CompileException {
            final Map<String, Object> m = new HashMap();

            for(Java.ElementValuePair evp : elementValuePairs) {
               m.put(evp.identifier, this.toObject(evp.elementValue));
            }

            return new IClass.IAnnotation() {
               public Object getElementValue(String name) {
                  return m.get(name);
               }

               public IType getAnnotationType() throws CompileException {
                  return UnitCompiler.this.getType(type);
               }
            };
         }

         private Object toObject(Java.ElementValue ev) throws CompileException {
            try {
               Object result = ev.accept(new Visitor.ElementValueVisitor() {
                  public Object visitRvalue(Java.Rvalue rv) throws CompileException {
                     if (rv instanceof Java.AmbiguousName) {
                        Java.AmbiguousName an = (Java.AmbiguousName)rv;
                        rv = UnitCompiler.this.reclassify(an).toRvalueOrCompileException();
                     }

                     if (rv instanceof Java.ClassLiteral) {
                        Java.ClassLiteral cl = (Java.ClassLiteral)rv;
                        return UnitCompiler.this.getType(cl.type);
                     } else if (rv instanceof Java.FieldAccess) {
                        Java.FieldAccess fa = (Java.FieldAccess)rv;
                        return fa.field;
                     } else {
                        Object result = UnitCompiler.this.getConstantValue(rv);
                        if (result == null) {
                           UnitCompiler.this.compileError("Null value not allowed as an element value", rv.getLocation());
                           return 1;
                        } else if (result == UnitCompiler.NOT_CONSTANT) {
                           UnitCompiler.this.compileError("Element value is not a constant expression", rv.getLocation());
                           return 1;
                        } else {
                           return result;
                        }
                     }
                  }

                  public Object visitAnnotation(Java.Annotation a) throws CompileException {
                     return UnitCompiler.this.toIAnnotation(a);
                  }

                  public Object visitElementValueArrayInitializer(Java.ElementValueArrayInitializer evai) throws CompileException {
                     Object[] result = new Object[evai.elementValues.length];

                     for(int i = 0; i < result.length; ++i) {
                        result[i] = toObject(evai.elementValues[i]);
                     }

                     return result;
                  }
               });

               assert result != null;

               return result;
            } catch (Exception ce) {
               if (ce instanceof CompileException) {
                  throw (CompileException)ce;
               } else {
                  throw new IllegalStateException(ce);
               }
            }
         }
      });

      assert result != null;

      return result;
   }

   private Java.Atom reclassifyName(Location location, Java.Scope scope, final String[] identifiers, int n) throws CompileException {
      if (n == 1) {
         return this.reclassifyName(location, scope, identifiers[0]);
      } else {
         Java.Atom lhs = this.reclassifyName(location, scope, identifiers, n - 1);
         String rhs = identifiers[n - 1];
         LOGGER.log(Level.FINE, "lhs={0}", lhs);
         if (lhs instanceof Java.Package) {
            String className = ((Java.Package)lhs).name + '.' + rhs;
            IClass result = this.findTypeByName(location, className);
            return (Java.Atom)(result != null ? new Java.SimpleType(location, result) : new Java.Package(location, className));
         } else if ("length".equals(rhs) && isArray(this.getType(lhs))) {
            Java.ArrayLength al = new Java.ArrayLength(location, this.toRvalueOrCompileException(lhs));
            if (!(scope instanceof Java.BlockStatement)) {
               this.compileError("\".length\" only allowed in expression context");
               return al;
            } else {
               al.setEnclosingScope(scope);
               return al;
            }
         } else {
            IType lhsType = this.getType(lhs);
            IClass.IField field = this.findIField(rawTypeOf(lhsType), rhs, location);
            if (field != null) {
               Java.FieldAccess fa = new Java.FieldAccess(location, lhs, field);
               fa.setEnclosingScope(scope);
               return fa;
            } else {
               IClass[] classes = rawTypeOf(lhsType).getDeclaredIClasses();

               for(IClass memberType : classes) {
                  String name = Descriptor.toClassName(memberType.getDescriptor());
                  name = name.substring(name.lastIndexOf(36) + 1);
                  if (name.equals(rhs)) {
                     return new Java.SimpleType(location, memberType);
                  }
               }

               this.compileError("\"" + rhs + "\" is neither a method, a field, nor a member class of \"" + lhsType + "\"", location);
               return new Java.Atom(location) {
                  @Nullable
                  public Object accept(Visitor.AtomVisitor visitor) {
                     return null;
                  }

                  public String toString() {
                     return Java.join(identifiers, ".");
                  }
               };
            }
         }
      }
   }

   @Nullable
   private IClass findTypeByName(Location location, String className) throws CompileException {
      IClass res = this.findClass(className);
      if (res != null) {
         return res;
      } else {
         try {
            return this.iClassLoader.loadIClass(Descriptor.fromClassName(className));
         } catch (ClassNotFoundException ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof CompileException) {
               throw (CompileException)cause;
            } else {
               throw new CompileException(className, location, ex);
            }
         }
      }
   }

   private Java.Atom reclassifyName(Location location, Java.Scope scope, String identifier) throws CompileException {
      Java.TypeBodyDeclaration scopeTbd = null;
      Java.AbstractTypeDeclaration scopeTypeDeclaration = null;

      Java.Scope s;
      for(s = scope; (s instanceof Java.BlockStatement || s instanceof Java.CatchClause) && !(s instanceof Java.TypeBodyDeclaration); s = s.getEnclosingScope()) {
      }

      if (s instanceof Java.TypeBodyDeclaration) {
         scopeTbd = (Java.TypeBodyDeclaration)s;
         s = s.getEnclosingScope();
      }

      if (s instanceof Java.TypeDeclaration) {
         scopeTypeDeclaration = (Java.AbstractTypeDeclaration)s;
         s = s.getEnclosingScope();
      }

      while(!(s instanceof Java.CompilationUnit)) {
         s = s.getEnclosingScope();
      }

      Java.CompilationUnit scopeCompilationUnit = (Java.CompilationUnit)s;
      s = scope;
      if (scope instanceof Java.BlockStatement) {
         Java.BlockStatement bs = (Java.BlockStatement)scope;
         Java.LocalVariable lv = bs.findLocalVariable(identifier);
         if (lv != null) {
            Java.LocalVariableAccess lva = new Java.LocalVariableAccess(location, lv);
            lva.setEnclosingScope(bs);
            return lva;
         }

         s = scope.getEnclosingScope();
      }

      while(s instanceof Java.BlockStatement || s instanceof Java.CatchClause) {
         s = s.getEnclosingScope();
      }

      if (s instanceof Java.FunctionDeclarator) {
         s = s.getEnclosingScope();
      }

      if (s instanceof Java.InnerClassDeclaration) {
         Java.InnerClassDeclaration icd = (Java.InnerClassDeclaration)s;
         s = s.getEnclosingScope();
         if (s instanceof Java.AnonymousClassDeclaration) {
            s = s.getEnclosingScope();
         } else if (s instanceof Java.FieldDeclaration) {
            s = s.getEnclosingScope().getEnclosingScope();
         }

         while(s instanceof Java.BlockStatement) {
            Java.LocalVariable lv = ((Java.BlockStatement)s).findLocalVariable(identifier);
            if (lv != null) {
               if (!lv.finaL) {
                  this.compileError("Cannot access non-final local variable \"" + identifier + "\" from inner class", location);
               }

               IType lvType = lv.type;
               IClass.IField iField = new SimpleIField(this.resolve(icd), "val$" + identifier, rawTypeOf(lvType));
               icd.defineSyntheticField(iField);
               Java.FieldAccess fa = new Java.FieldAccess(location, new Java.QualifiedThisReference(location, new Java.SimpleType(location, this.resolve(icd))), iField);
               fa.setEnclosingScope(scope);
               return fa;
            }

            for(s = s.getEnclosingScope(); s instanceof Java.BlockStatement; s = s.getEnclosingScope()) {
            }

            if (!(s instanceof Java.FunctionDeclarator)) {
               break;
            }

            s = s.getEnclosingScope();
            if (!(s instanceof Java.InnerClassDeclaration)) {
               break;
            }

            icd = (Java.InnerClassDeclaration)s;
            s = s.getEnclosingScope();
         }
      }

      s = null;

      for(Java.Scope s = scope; !(s instanceof Java.CompilationUnit); s = s.getEnclosingScope()) {
         if (s instanceof Java.BlockStatement && s == null) {
            s = (Java.BlockStatement)s;
         }

         if (s instanceof Java.TypeDeclaration) {
            Java.AbstractTypeDeclaration enclosingTypeDecl = (Java.AbstractTypeDeclaration)s;
            IClass etd = this.resolve(enclosingTypeDecl);
            IClass.IField f = this.findIField(etd, identifier, location);
            if (f != null) {
               if (f.isStatic()) {
                  this.warning("IASF", "Implicit access to static field \"" + identifier + "\" of declaring class (better write \"" + f.getDeclaringIClass() + '.' + f.getName() + "\")", location);
               } else if (f.getDeclaringIClass() == etd) {
                  this.warning("IANSF", "Implicit access to non-static field \"" + identifier + "\" of declaring class (better write \"this." + f.getName() + "\")", location);
               } else {
                  this.warning("IANSFEI", "Implicit access to non-static field \"" + identifier + "\" of enclosing instance (better write \"" + f.getDeclaringIClass() + ".this." + f.getName() + "\")", location);
               }

               assert scopeTypeDeclaration != null;

               Java.SimpleType ct = new Java.SimpleType(scopeTypeDeclaration.getLocation(), etd);
               Java.Atom lhs;
               if (scopeTbd == null) {
                  lhs = ct;
               } else if (scopeTbd instanceof Java.MethodDeclarator && ((Java.MethodDeclarator)scopeTbd).isStatic()) {
                  lhs = ct;
               } else if (f.isStatic()) {
                  lhs = ct;
               } else {
                  lhs = new Java.QualifiedThisReference(location, ct);
               }

               Java.Rvalue res = new Java.FieldAccess(location, lhs, f);
               res.setEnclosingScope(scope);
               return res;
            }
         }
      }

      for(IClass.IField f : Iterables.filterByClass(this.importSingleStatic(identifier), IClass.IField.class)) {
         if (this.isAccessible((IClass.IMember)f, scope)) {
            Java.FieldAccess fieldAccess = new Java.FieldAccess(location, new Java.SimpleType(location, f.getDeclaringIClass()), f);
            fieldAccess.setEnclosingScope(scope);
            return fieldAccess;
         }
      }

      for(IClass.IField f : Iterables.filterByClass(this.importStaticOnDemand(identifier), IClass.IField.class)) {
         if (this.isAccessible((IClass.IMember)f, scope)) {
            Java.FieldAccess fieldAccess = new Java.FieldAccess(location, new Java.SimpleType(location, f.getDeclaringIClass()), f);
            fieldAccess.setEnclosingScope(scope);
            return fieldAccess;
         }
      }

      if ("java".equals(identifier)) {
         return new Java.Package(location, identifier);
      } else {
         IClass unnamedPackageType = this.findTypeByName(location, identifier);
         if (unnamedPackageType != null) {
            return new Java.SimpleType(location, unnamedPackageType);
         } else {
            Java.LocalClassDeclaration lcd = findLocalClassDeclaration(scope, identifier);
            if (lcd != null) {
               return new Java.SimpleType(location, this.resolve(lcd));
            } else {
               if (scopeTypeDeclaration != null) {
                  IClass memberType = this.findMemberType(this.resolve(scopeTypeDeclaration), identifier, (Java.TypeArgument[])null, location);
                  if (memberType != null) {
                     return new Java.SimpleType(location, memberType);
                  }
               }

               IClass iClass = this.importSingleType(identifier, location);
               if (iClass != null) {
                  return new Java.SimpleType(location, iClass);
               } else {
                  Java.PackageMemberTypeDeclaration pmtd = scopeCompilationUnit.getPackageMemberTypeDeclaration(identifier);
                  if (pmtd != null) {
                     return new Java.SimpleType(location, this.resolve(pmtd));
                  } else {
                     Java.PackageDeclaration opd = scopeCompilationUnit.packageDeclaration;
                     String className = opd == null ? identifier : opd.packageName + '.' + identifier;
                     IClass result = this.findTypeByName(location, className);
                     if (result != null) {
                        return new Java.SimpleType(location, result);
                     } else {
                        IClass importedClass = this.importTypeOnDemand(identifier, location);
                        if (importedClass != null) {
                           return new Java.SimpleType(location, importedClass);
                        } else {
                           Iterator<IClass> it = Iterables.filterByClass(this.importSingleStatic(identifier).iterator(), IClass.class);
                           if (it.hasNext()) {
                              return new Java.SimpleType(location, (IClass)it.next());
                           } else {
                              for(IClass mt : Iterables.filterByClass(this.importStaticOnDemand(identifier), IClass.class)) {
                                 if (this.isAccessible(mt, scope)) {
                                    return new Java.SimpleType(location, mt);
                                 }
                              }

                              return new Java.Package(location, identifier);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private List importSingleStatic(String simpleName) throws CompileException {
      List<Object> result = new ArrayList();

      for(Java.AbstractCompilationUnit.SingleStaticImportDeclaration ssid : Iterables.filterByClass(this.abstractCompilationUnit.importDeclarations, Java.AbstractCompilationUnit.SingleStaticImportDeclaration.class)) {
         if (simpleName.equals(last(ssid.identifiers))) {
            IClass declaringIClass = this.findTypeByName(ssid.getLocation(), Java.join(allButLast(ssid.identifiers), "."));
            if (declaringIClass != null) {
               this.importStatic(declaringIClass, simpleName, result, ssid.getLocation());
            }
         }
      }

      return result;
   }

   private void importStatic(IClass declaringIClass, String simpleName, Collection result, Location location) throws CompileException {
      for(IClass memberIClass : declaringIClass.findMemberType(simpleName)) {
         if (memberIClass.getDeclaringIClass() == declaringIClass) {
            result.add(memberIClass);
         }
      }

      IClass.IField iField = declaringIClass.getDeclaredIField(simpleName);
      if (iField != null) {
         if (!iField.isStatic()) {
            this.compileError("Field \"" + simpleName + "\" of \"" + declaringIClass + "\" must be static", location);
         }

         result.add(iField);
      }

      for(IClass.IMethod iMethod : declaringIClass.getDeclaredIMethods(simpleName)) {
         if (!iMethod.isStatic()) {
            this.compileError("method \"" + iMethod + "\" of \"" + declaringIClass + "\" must be static", location);
         }

         result.add(iMethod);
      }

   }

   private Java.Rvalue determineValue(Java.FieldAccessExpression fae) throws CompileException {
      if (fae.value != null) {
         return fae.value;
      } else {
         IType lhsType = this.getType(fae.lhs);
         Java.Rvalue value;
         if (fae.fieldName.equals("length") && isArray(lhsType)) {
            value = new Java.ArrayLength(fae.getLocation(), this.toRvalueOrCompileException(fae.lhs));
         } else {
            IClass.IField iField = this.findIField(rawTypeOf(lhsType), fae.fieldName, fae.getLocation());
            if (iField == null) {
               this.compileError("\"" + this.getType(fae.lhs).toString() + "\" has no field \"" + fae.fieldName + "\"", fae.getLocation());
               value = new Java.Rvalue(fae.getLocation()) {
                  @Nullable
                  public Object accept(Visitor.RvalueVisitor visitor) {
                     return null;
                  }

                  public String toString() {
                     return "???";
                  }
               };
            } else {
               value = new Java.FieldAccess(fae.getLocation(), fae.lhs, iField);
            }
         }

         value.setEnclosingScope(fae.getEnclosingScope());
         return fae.value = value;
      }
   }

   private Java.Rvalue determineValue(Java.SuperclassFieldAccessExpression scfae) throws CompileException {
      if (scfae.value != null) {
         return scfae.value;
      } else {
         Java.ThisReference tr = new Java.ThisReference(scfae.getLocation());
         tr.setEnclosingScope(scfae.getEnclosingScope());
         IType type;
         if (scfae.qualification != null) {
            type = this.getType(scfae.qualification);
         } else {
            type = this.getType((Java.Rvalue)tr);
         }

         IType superclass = getSuperclass(type);
         if (superclass == null) {
            throw new CompileException("Cannot use \"super\" on \"" + type + "\"", scfae.getLocation());
         } else {
            Java.Rvalue lhs = new Java.Cast(scfae.getLocation(), new Java.SimpleType(scfae.getLocation(), superclass), tr);
            IClass.IField iField = this.findIField(rawTypeOf(this.getType(lhs)), scfae.fieldName, scfae.getLocation());
            Java.Rvalue value;
            if (iField == null) {
               this.compileError("Class has no field \"" + scfae.fieldName + "\"", scfae.getLocation());
               value = new Java.Rvalue(scfae.getLocation()) {
                  @Nullable
                  public Object accept(Visitor.RvalueVisitor visitor) {
                     return null;
                  }

                  public String toString() {
                     return "???";
                  }
               };
            } else {
               value = new Java.FieldAccess(scfae.getLocation(), lhs, iField);
            }

            value.setEnclosingScope(scfae.getEnclosingScope());
            return scfae.value = value;
         }
      }
   }

   public IClass.IMethod findIMethod(Java.MethodInvocation mi) throws CompileException {
      IClass.IMethod iMethod;
      label40: {
         Java.Atom ot = mi.target;
         if (ot == null) {
            for(Java.Scope s = mi.getEnclosingScope(); !(s instanceof Java.CompilationUnit); s = s.getEnclosingScope()) {
               if (s instanceof Java.TypeDeclaration) {
                  Java.TypeDeclaration td = (Java.TypeDeclaration)s;
                  iMethod = this.findIMethod(this.resolve(td), mi);
                  if (iMethod != null) {
                     break label40;
                  }
               }
            }
         } else {
            iMethod = this.findIMethod(this.getType(ot), mi);
            if (iMethod != null) {
               break label40;
            }
         }

         IClass.IMethod[] candidates = (IClass.IMethod[])Iterables.toArray(Iterables.filterByClass(this.importSingleStatic(mi.methodName), IClass.IMethod.class), IClass.IMethod.class);
         if (candidates.length > 0) {
            iMethod = (IClass.IMethod)this.findMostSpecificIInvocable(mi, candidates, mi.arguments, mi.getEnclosingScope());
         } else {
            candidates = (IClass.IMethod[])Iterables.toArray(Iterables.filterByClass(this.importStaticOnDemand(mi.methodName), IClass.IMethod.class), IClass.IMethod.class);
            if (candidates.length <= 0) {
               this.compileError("A method named \"" + mi.methodName + "\" is not declared in any enclosing class nor any supertype, nor through a static import", mi.getLocation());
               return this.fakeIMethod(this.iClassLoader.TYPE_java_lang_Object, mi.methodName, mi.arguments);
            }

            iMethod = (IClass.IMethod)this.findMostSpecificIInvocable(mi, candidates, mi.arguments, mi.getEnclosingScope());
         }
      }

      assert iMethod != null;

      this.checkThrownExceptions(mi, iMethod);
      return iMethod;
   }

   @Nullable
   private IClass.IMethod findIMethod(IType targetType, Java.Invocation invocation) throws CompileException {
      IClass rawTargetType = rawTypeOf(targetType);
      List<IClass.IMethod> ms = new ArrayList();
      this.getIMethods(rawTargetType, invocation.methodName, ms);
      if (rawTargetType.isInterface()) {
         IClass.IMethod[] oms = this.iClassLoader.TYPE_java_lang_Object.getDeclaredIMethods(invocation.methodName);

         for(IClass.IMethod om : oms) {
            if (!om.isStatic() && om.getAccess() == Access.PUBLIC) {
               ms.add(om);
            }
         }
      }

      return ms.size() == 0 ? null : (IClass.IMethod)this.findMostSpecificIInvocable(invocation, (IClass.IMethod[])ms.toArray(new IClass.IMethod[ms.size()]), invocation.arguments, invocation.getEnclosingScope());
   }

   private IClass.IMethod fakeIMethod(IClass targetType, final String name, Java.Rvalue[] arguments) throws CompileException {
      final IClass[] pts = new IClass[arguments.length];

      for(int i = 0; i < arguments.length; ++i) {
         pts[i] = rawTypeOf(this.getType(arguments[i]));
      }

      Objects.requireNonNull(targetType);
      return new IClass.IMethod(targetType) {
         {
            Objects.requireNonNull(x0);
         }

         public IClass.IAnnotation[] getAnnotations() {
            return new IClass.IAnnotation[0];
         }

         public Access getAccess() {
            return Access.PUBLIC;
         }

         public boolean isStatic() {
            return false;
         }

         public boolean isAbstract() {
            return false;
         }

         public IClass getReturnType() {
            return IClass.INT;
         }

         public String getName() {
            return name;
         }

         public IClass[] getParameterTypes2() {
            return pts;
         }

         public boolean isVarargs() {
            return false;
         }

         public IClass[] getThrownExceptions2() {
            return new IClass[0];
         }
      };
   }

   public void getIMethods(IClass type, String methodName, List v) throws CompileException {
      IClass.IMethod[] ims = type.getDeclaredIMethods(methodName);

      for(IClass.IMethod im : ims) {
         v.add(im);
      }

      IClass superclass = type.getSuperclass();
      if (superclass != null) {
         this.getIMethods(superclass, methodName, v);
      }

      IClass[] interfaces = type.getInterfaces();

      for(IClass interfacE : interfaces) {
         this.getIMethods(interfacE, methodName, v);
      }

   }

   public IClass.IMethod findIMethod(Java.SuperclassMethodInvocation superclassMethodInvocation) throws CompileException {
      Java.Scope s = superclassMethodInvocation.getEnclosingScope();

      while(true) {
         if (s instanceof Java.FunctionDeclarator) {
            Java.FunctionDeclarator fd = (Java.FunctionDeclarator)s;
            if (fd instanceof Java.MethodDeclarator && ((Java.MethodDeclarator)fd).isStatic()) {
               this.compileError("Superclass method cannot be invoked in static context", superclassMethodInvocation.getLocation());
            }
         }

         if (s instanceof Java.AbstractClassDeclaration) {
            Java.AbstractClassDeclaration declaringClass = (Java.AbstractClassDeclaration)s;
            IClass superclass = this.resolve(declaringClass).getSuperclass();
            if (superclass == null) {
               throw new CompileException("\"" + declaringClass + "\" has no superclass", superclassMethodInvocation.getLocation());
            }

            IClass.IMethod iMethod = this.findIMethod(superclass, superclassMethodInvocation);
            if (iMethod == null) {
               this.compileError("Class \"" + superclass + "\" has no method named \"" + superclassMethodInvocation.methodName + "\"", superclassMethodInvocation.getLocation());
               return this.fakeIMethod(superclass, superclassMethodInvocation.methodName, superclassMethodInvocation.arguments);
            }

            this.checkThrownExceptions(superclassMethodInvocation, iMethod);
            return iMethod;
         }

         s = s.getEnclosingScope();
      }
   }

   private IClass.IInvocable findMostSpecificIInvocable(Java.Locatable locatable, IClass.IInvocable[] iInvocables, Java.Rvalue[] arguments, Java.Scope contextScope) throws CompileException {
      final IClass[] argumentTypes = new IClass[arguments.length];

      for(int i = 0; i < arguments.length; ++i) {
         argumentTypes[i] = rawTypeOf(this.getType(arguments[i]));
      }

      IClass.IInvocable ii = this.findMostSpecificIInvocable(locatable, iInvocables, argumentTypes, false, contextScope);
      if (ii != null) {
         return ii;
      } else {
         ii = this.findMostSpecificIInvocable(locatable, iInvocables, argumentTypes, true, contextScope);
         if (ii != null) {
            return ii;
         } else {
            StringBuilder sb = new StringBuilder("No applicable constructor/method found for ");
            if (argumentTypes.length == 0) {
               sb.append("zero actual parameters");
            } else {
               sb.append("actual parameters \"").append(argumentTypes[0]);

               for(int i = 1; i < argumentTypes.length; ++i) {
                  sb.append(", ").append(argumentTypes[i]);
               }

               sb.append("\"");
            }

            sb.append("; candidates are: \"").append(iInvocables[0]).append('"');

            for(int i = 1; i < iInvocables.length; ++i) {
               sb.append(", \"").append(iInvocables[i]).append('"');
            }

            this.compileError(sb.toString(), locatable.getLocation());
            if (iInvocables[0] instanceof IClass.IConstructor) {
               IClass var13 = iInvocables[0].getDeclaringIClass();
               Objects.requireNonNull(var13);
               return new IClass.IConstructor(var13) {
                  {
                     Objects.requireNonNull(x0);
                  }

                  public boolean isVarargs() {
                     return false;
                  }

                  public IClass[] getParameterTypes2() {
                     return argumentTypes;
                  }

                  public Access getAccess() {
                     return Access.PUBLIC;
                  }

                  public IClass[] getThrownExceptions2() {
                     return new IClass[0];
                  }

                  public IClass.IAnnotation[] getAnnotations() {
                     return new IClass.IAnnotation[0];
                  }
               };
            } else if (iInvocables[0] instanceof IClass.IMethod) {
               final String methodName = ((IClass.IMethod)iInvocables[0]).getName();
               IClass var10003 = iInvocables[0].getDeclaringIClass();
               Objects.requireNonNull(var10003);
               return new IClass.IMethod(var10003) {
                  {
                     Objects.requireNonNull(x0);
                  }

                  public IClass.IAnnotation[] getAnnotations() {
                     return new IClass.IAnnotation[0];
                  }

                  public Access getAccess() {
                     return Access.PUBLIC;
                  }

                  public boolean isStatic() {
                     return true;
                  }

                  public boolean isAbstract() {
                     return false;
                  }

                  public IClass getReturnType() {
                     return IClass.INT;
                  }

                  public String getName() {
                     return methodName;
                  }

                  public IClass[] getParameterTypes2() {
                     return argumentTypes;
                  }

                  public boolean isVarargs() {
                     return false;
                  }

                  public IClass[] getThrownExceptions2() {
                     return new IClass[0];
                  }
               };
            } else {
               return iInvocables[0];
            }
         }
      }
   }

   @Nullable
   public IClass.IInvocable findMostSpecificIInvocable(Java.Locatable locatable, IClass.IInvocable[] iInvocables, IClass[] argumentTypes, boolean boxingPermitted, Java.Scope contextScope) throws CompileException {
      if (LOGGER.isLoggable(Level.FINER)) {
         LOGGER.entering((String)null, "findMostSpecificIInvocable", new Object[]{locatable, Arrays.toString(iInvocables), Arrays.toString(argumentTypes), boxingPermitted, contextScope});
      }

      List<IClass.IInvocable> applicableIInvocables = new ArrayList();
      List<IClass.IInvocable> varargApplicables = new ArrayList();

      label267:
      for(IClass.IInvocable ii : iInvocables) {
         boolean argsNeedAdjust = false;
         if (this.isAccessible((IClass.IMember)ii, contextScope)) {
            IClass[] parameterTypes = ii.getParameterTypes();
            int formalParamCount = parameterTypes.length;
            int nUncheckedArg = argumentTypes.length;
            boolean isVarargs = ii.isVarargs();
            if (isVarargs) {
               --formalParamCount;
               IClass lastParamType = parameterTypes[formalParamCount].getComponentType();

               assert lastParamType != null;

               int lastActualArg = nUncheckedArg - 1;
               if (formalParamCount == lastActualArg && argumentTypes[lastActualArg].isArray() && this.isMethodInvocationConvertible((IClass)assertNonNull(argumentTypes[lastActualArg].getComponentType()), lastParamType, boxingPermitted)) {
                  --nUncheckedArg;
               } else {
                  int idx = lastActualArg;

                  while(true) {
                     if (idx < formalParamCount) {
                        argsNeedAdjust = true;
                        break;
                     }

                     LOGGER.log(Level.FINE, "{0} <=> {1}", new Object[]{lastParamType, argumentTypes[idx]});
                     if (!this.isMethodInvocationConvertible(argumentTypes[idx], lastParamType, boxingPermitted)) {
                        ++formalParamCount;
                        break;
                     }

                     --nUncheckedArg;
                     --idx;
                  }
               }
            }

            if (formalParamCount == nUncheckedArg) {
               for(int j = 0; j < nUncheckedArg; ++j) {
                  LOGGER.log(Level.FINE, "{0}: {1} <=> {2}", new Object[]{j, parameterTypes[j], argumentTypes[j]});
                  if (!this.isMethodInvocationConvertible(argumentTypes[j], parameterTypes[j], boxingPermitted)) {
                     continue label267;
                  }
               }

               LOGGER.fine("Applicable!");
               if (isVarargs) {
                  ii.setArgsNeedAdjust(argsNeedAdjust);
                  varargApplicables.add(ii);
               } else {
                  applicableIInvocables.add(ii);
               }
            }
         }
      }

      if (applicableIInvocables.size() == 1) {
         return (IClass.IInvocable)applicableIInvocables.get(0);
      } else {
         if (applicableIInvocables.size() == 0 && !varargApplicables.isEmpty()) {
            applicableIInvocables = varargApplicables;
            if (varargApplicables.size() == 1) {
               return (IClass.IInvocable)varargApplicables.get(0);
            }
         }

         if (applicableIInvocables.size() == 0) {
            return null;
         } else {
            List<IClass.IInvocable> maximallySpecificIInvocables = new ArrayList();

            for(IClass.IInvocable applicableIInvocable : applicableIInvocables) {
               int moreSpecific = 0;
               int lessSpecific = 0;

               for(IClass.IInvocable mostSpecificIInvocable : maximallySpecificIInvocables) {
                  if (applicableIInvocable.isMoreSpecificThan(mostSpecificIInvocable)) {
                     ++moreSpecific;
                  } else if (applicableIInvocable.isLessSpecificThan(mostSpecificIInvocable)) {
                     ++lessSpecific;
                  }
               }

               if (moreSpecific == maximallySpecificIInvocables.size()) {
                  maximallySpecificIInvocables.clear();
                  maximallySpecificIInvocables.add(applicableIInvocable);
               } else if (lessSpecific < maximallySpecificIInvocables.size()) {
                  maximallySpecificIInvocables.add(applicableIInvocable);
               }

               LOGGER.log(Level.FINE, "maximallySpecificIInvocables={0}", maximallySpecificIInvocables);
            }

            if (maximallySpecificIInvocables.size() == 1) {
               return (IClass.IInvocable)maximallySpecificIInvocables.get(0);
            } else {
               if (maximallySpecificIInvocables.size() > 1 && iInvocables[0] instanceof IClass.IMethod) {
                  IClass.IMethod theNonAbstractMethod = null;
                  Iterator<IClass.IInvocable> it = maximallySpecificIInvocables.iterator();
                  IClass.IMethod m = (IClass.IMethod)it.next();
                  IClass[] parameterTypesOfFirstMethod = m.getParameterTypes();

                  label222:
                  while(true) {
                     if (!m.isAbstract() && !m.getDeclaringIClass().isInterface()) {
                        if (theNonAbstractMethod == null) {
                           theNonAbstractMethod = m;
                        } else {
                           IClass declaringIClass = m.getDeclaringIClass();
                           IClass theNonAbstractMethodDeclaringIClass = theNonAbstractMethod.getDeclaringIClass();
                           if (declaringIClass == theNonAbstractMethodDeclaringIClass) {
                              if (m.getReturnType() == theNonAbstractMethod.getReturnType()) {
                                 throw new InternalCompilerException(locatable.getLocation(), "Two non-abstract methods \"" + m + "\" have the same parameter types, declaring type and return type");
                              }

                              if (!this.isMethodInvocationConvertible(theNonAbstractMethod.getReturnType(), m.getReturnType(), boxingPermitted)) {
                                 if (!this.isMethodInvocationConvertible(m.getReturnType(), theNonAbstractMethod.getReturnType(), boxingPermitted)) {
                                    throw new InternalCompilerException(locatable.getLocation(), "Incompatible return types");
                                 }

                                 theNonAbstractMethod = m;
                              }
                           } else if (!declaringIClass.isAssignableFrom(theNonAbstractMethodDeclaringIClass)) {
                              if (theNonAbstractMethodDeclaringIClass.isAssignableFrom(declaringIClass)) {
                                 theNonAbstractMethod = m;
                              } else {
                                 this.compileError("Ambiguous static method import: \"" + theNonAbstractMethod + "\" vs. \"" + m + "\"");
                              }
                           }
                        }
                     }

                     if (!it.hasNext()) {
                        if (theNonAbstractMethod != null) {
                           return theNonAbstractMethod;
                        }

                        Set<IClass> s = new HashSet();
                        IClass[][] tes = new IClass[maximallySpecificIInvocables.size()][];
                        Iterator<IClass.IInvocable> it = maximallySpecificIInvocables.iterator();

                        for(int i = 0; i < tes.length; ++i) {
                           tes[i] = ((IClass.IMethod)it.next()).getThrownExceptions();
                        }

                        for(int i = 0; i < tes.length; ++i) {
                           label200:
                           for(IClass te1 : tes[i]) {
                              for(int k = 0; k < tes.length; ++k) {
                                 if (k != i) {
                                    IClass[] var56 = tes[k];
                                    int var20 = var56.length;
                                    int var21 = 0;

                                    while(true) {
                                       if (var21 >= var20) {
                                          continue label200;
                                       }

                                       IClass te2 = var56[var21];
                                       if (te2.isAssignableFrom(te1)) {
                                          break;
                                       }

                                       ++var21;
                                    }
                                 }
                              }

                              s.add(te1);
                           }
                        }

                        it = maximallySpecificIInvocables.iterator();
                        final IClass.IMethod methodWithMostSpecificReturnType = (IClass.IMethod)it.next();

                        while(it.hasNext()) {
                           IClass.IMethod im2 = (IClass.IMethod)it.next();
                           if (methodWithMostSpecificReturnType.getReturnType().isAssignableFrom(im2.getReturnType())) {
                              methodWithMostSpecificReturnType = im2;
                           }
                        }

                        final IClass[] tes = (IClass[])s.toArray(new IClass[s.size()]);
                        IClass var10003 = methodWithMostSpecificReturnType.getDeclaringIClass();
                        Objects.requireNonNull(var10003);
                        return new IClass.IMethod(var10003) {
                           {
                              Objects.requireNonNull(x0);
                           }

                           public IClass.IAnnotation[] getAnnotations() {
                              return methodWithMostSpecificReturnType.getAnnotations();
                           }

                           public Access getAccess() {
                              return methodWithMostSpecificReturnType.getAccess();
                           }

                           public boolean isAbstract() {
                              return true;
                           }

                           public boolean isStatic() {
                              return methodWithMostSpecificReturnType.isStatic();
                           }

                           public IClass getReturnType() throws CompileException {
                              return methodWithMostSpecificReturnType.getReturnType();
                           }

                           public String getName() {
                              return methodWithMostSpecificReturnType.getName();
                           }

                           public IClass[] getParameterTypes2() throws CompileException {
                              return methodWithMostSpecificReturnType.getParameterTypes();
                           }

                           public boolean isVarargs() {
                              return methodWithMostSpecificReturnType.isVarargs();
                           }

                           public IClass[] getThrownExceptions2() {
                              return tes;
                           }
                        };
                     }

                     m = (IClass.IMethod)it.next();
                     IClass[] pts = m.getParameterTypes();

                     for(int i = 0; i < pts.length; ++i) {
                        if (pts[i] != parameterTypesOfFirstMethod[i]) {
                           break label222;
                        }
                     }
                  }
               }

               if (!boxingPermitted) {
                  return null;
               } else {
                  StringBuilder sb = new StringBuilder("Invocation of constructor/method with argument type(s) \"");

                  for(int i = 0; i < argumentTypes.length; ++i) {
                     if (i > 0) {
                        sb.append(", ");
                     }

                     sb.append(Descriptor.toString(argumentTypes[i].getDescriptor()));
                  }

                  sb.append("\" is ambiguous: ");

                  for(int i = 0; i < maximallySpecificIInvocables.size(); ++i) {
                     if (i > 0) {
                        sb.append(" vs. ");
                     }

                     sb.append("\"" + maximallySpecificIInvocables.get(i) + "\"");
                  }

                  this.compileError(sb.toString(), locatable.getLocation());
                  return iInvocables[0];
               }
            }
         }
      }
   }

   private static Object assertNonNull(@Nullable Object subject) {
      assert subject != null;

      return subject;
   }

   private boolean isMethodInvocationConvertible(IClass sourceType, IClass targetType, boolean boxingPermitted) throws CompileException {
      if (sourceType == targetType) {
         return true;
      } else if (this.isWideningPrimitiveConvertible(sourceType, targetType)) {
         return true;
      } else if (this.isWideningReferenceConvertible(sourceType, targetType)) {
         return true;
      } else {
         if (boxingPermitted) {
            IClass boxedType = this.isBoxingConvertible(sourceType);
            if (boxedType != null) {
               return this.isIdentityConvertible(boxedType, targetType) || this.isWideningReferenceConvertible(boxedType, targetType);
            }
         }

         if (boxingPermitted) {
            IClass unboxedType = this.isUnboxingConvertible(sourceType);
            if (unboxedType != null) {
               return this.isIdentityConvertible(unboxedType, targetType) || this.isWideningPrimitiveConvertible(unboxedType, targetType);
            }
         }

         return false;
      }
   }

   private void checkThrownExceptions(Java.Invocation in, IClass.IMethod iMethod) throws CompileException {
      IClass[] thrownExceptions = iMethod.getThrownExceptions();

      for(IClass thrownException : thrownExceptions) {
         this.checkThrownException(in, thrownException, in.getEnclosingScope());
      }

   }

   private void checkThrownException(Java.Locatable locatable, IType type, Java.Scope scope) throws CompileException {
      if (!(type instanceof IClass) || !this.iClassLoader.TYPE_java_lang_Throwable.isAssignableFrom((IClass)type)) {
         this.compileError("Thrown object of type \"" + type + "\" is not assignable to \"Throwable\"", locatable.getLocation());
      }

      IClass rawType = (IClass)type;
      if (!this.iClassLoader.TYPE_java_lang_RuntimeException.isAssignableFrom(rawType) && !this.iClassLoader.TYPE_java_lang_Error.isAssignableFrom(rawType)) {
         label83:
         while(true) {
            if (scope instanceof Java.TryStatement) {
               Java.TryStatement ts = (Java.TryStatement)scope;

               for(int i = 0; i < ts.catchClauses.size(); ++i) {
                  Java.CatchClause cc = (Java.CatchClause)ts.catchClauses.get(i);

                  label76:
                  for(Java.Type ct : cc.catchParameter.types) {
                     IClass caughtType = this.getRawType(ct);
                     if (caughtType.isAssignableFrom(rawType)) {
                        cc.reachable = true;
                        return;
                     }

                     if (rawType.isAssignableFrom(caughtType)) {
                        for(int j = 0; j < i; ++j) {
                           for(Java.Type ct2 : ((Java.CatchClause)ts.catchClauses.get(j)).catchParameter.types) {
                              if (this.getRawType(ct2).isAssignableFrom(caughtType)) {
                                 continue label76;
                              }
                           }
                        }

                        cc.reachable = true;
                     }
                  }
               }
            } else {
               if (scope instanceof Java.FunctionDeclarator) {
                  Java.FunctionDeclarator fd = (Java.FunctionDeclarator)scope;
                  Java.Type[] var19 = fd.thrownExceptions;
                  int var20 = var19.length;
                  int var21 = 0;

                  while(true) {
                     if (var21 >= var20) {
                        break label83;
                     }

                     Java.Type thrownException = var19[var21];
                     if (this.getRawType(thrownException).isAssignableFrom(rawType)) {
                        return;
                     }

                     ++var21;
                  }
               }

               if (scope instanceof Java.TypeBodyDeclaration) {
                  break;
               }
            }

            scope = scope.getEnclosingScope();
         }

         this.compileError("Thrown exception of type \"" + type + "\" is neither caught by a \"try...catch\" block nor declared in the \"throws\" clause of the declaring function", locatable.getLocation());
      }
   }

   private IType getTargetIType(Java.QualifiedThisReference qtr) throws CompileException {
      return qtr.targetIType != null ? qtr.targetIType : (qtr.targetIType = this.getType(qtr.qualification));
   }

   @Nullable
   Java.LocalVariable isIntLv(Java.Crement c) throws CompileException {
      if (!(c.operand instanceof Java.AmbiguousName)) {
         return null;
      } else {
         Java.AmbiguousName an = (Java.AmbiguousName)c.operand;
         Java.Atom rec = this.reclassify(an);
         if (!(rec instanceof Java.LocalVariableAccess)) {
            return null;
         } else {
            Java.LocalVariableAccess lva = (Java.LocalVariableAccess)rec;
            Java.LocalVariable lv = lva.localVariable;
            if (lv.finaL) {
               this.compileError("Must not increment or decrement \"final\" local variable", lva.getLocation());
            }

            return lv.type == IClass.INT ? lv : null;
         }
      }
   }

   private IClass resolve(final Java.TypeDeclaration td) {
      final Java.AbstractTypeDeclaration atd = (Java.AbstractTypeDeclaration)td;
      return atd.resolvedType != null ? atd.resolvedType : (atd.resolvedType = new IClass() {
         @Nullable
         private IClass[] declaredClasses;

         protected ITypeVariable[] getITypeVariables2() throws CompileException {
            if (!(atd instanceof Java.NamedTypeDeclaration)) {
               return new ITypeVariable[0];
            } else {
               Java.NamedTypeDeclaration ntd = (Java.NamedTypeDeclaration)atd;
               Java.TypeParameter[] typeParameters = ntd.getOptionalTypeParameters();
               if (typeParameters == null) {
                  return new ITypeVariable[0];
               } else {
                  ITypeVariable[] result = new ITypeVariable[typeParameters.length];

                  for(int i = 0; i < result.length; ++i) {
                     final Java.TypeParameter tp = typeParameters[i];
                     final ITypeVariableOrIClass[] bounds = new ITypeVariableOrIClass[tp.bound == null ? 0 : tp.bound.length];

                     for(int j = 0; j < bounds.length; ++j) {
                        IType b = UnitCompiler.this.getType((Java.Type)tp.bound[j]);

                        assert b instanceof ITypeVariableOrIClass;

                        bounds[j] = (ITypeVariableOrIClass)b;
                     }

                     result[i] = new ITypeVariable() {
                        public String getName() {
                           return tp.name;
                        }

                        public ITypeVariableOrIClass[] getBounds() {
                           return bounds;
                        }
                     };
                  }

                  return result;
               }
            }
         }

         protected IClass.IMethod[] getDeclaredIMethods2() {
            List<IClass.IMethod> res = new ArrayList(atd.getMethodDeclarations().size());

            for(Java.MethodDeclarator md : atd.getMethodDeclarations()) {
               res.add(UnitCompiler.this.toIMethod(md));
            }

            if (td instanceof Java.EnumDeclaration) {
               res.add(new IClass.IMethod() {
                  public IClass.IAnnotation[] getAnnotations() {
                     return new IClass.IAnnotation[0];
                  }

                  public Access getAccess() {
                     return Access.PUBLIC;
                  }

                  public boolean isStatic() {
                     return true;
                  }

                  public boolean isAbstract() {
                     return false;
                  }

                  public String getName() {
                     return "values";
                  }

                  public IClass[] getParameterTypes2() {
                     return new IClass[0];
                  }

                  public boolean isVarargs() {
                     return false;
                  }

                  public IClass[] getThrownExceptions2() {
                     return new IClass[0];
                  }

                  public IClass getReturnType() {
                     IClass rt = atd.resolvedType;

                     assert rt != null;

                     return UnitCompiler.this.iClassLoader.getArrayIClass(rt);
                  }
               });
               res.add(new IClass.IMethod() {
                  public IClass.IAnnotation[] getAnnotations() {
                     return new IClass.IAnnotation[0];
                  }

                  public Access getAccess() {
                     return Access.PUBLIC;
                  }

                  public boolean isStatic() {
                     return true;
                  }

                  public boolean isAbstract() {
                     return false;
                  }

                  public String getName() {
                     return "valueOf";
                  }

                  public IClass[] getParameterTypes2() {
                     return new IClass[]{UnitCompiler.this.iClassLoader.TYPE_java_lang_String};
                  }

                  public boolean isVarargs() {
                     return false;
                  }

                  public IClass[] getThrownExceptions2() {
                     return new IClass[0];
                  }

                  public IClass getReturnType() {
                     IClass rt = atd.resolvedType;

                     assert rt != null;

                     return rt;
                  }
               });
            }

            return (IClass.IMethod[])res.toArray(new IClass.IMethod[res.size()]);
         }

         protected IClass[] getDeclaredIClasses2() {
            if (this.declaredClasses != null) {
               return this.declaredClasses;
            } else {
               Collection<Java.MemberTypeDeclaration> mtds = td.getMemberTypeDeclarations();
               IClass[] mts = new IClass[mtds.size()];
               int i = 0;

               for(Java.MemberTypeDeclaration mtd : mtds) {
                  mts[i++] = UnitCompiler.this.resolve(mtd);
               }

               return this.declaredClasses = mts;
            }
         }

         @Nullable
         protected IClass getDeclaringIClass2() {
            Java.Scope s;
            for(s = atd; !(s instanceof Java.TypeBodyDeclaration); s = s.getEnclosingScope()) {
               if (s instanceof Java.CompilationUnit) {
                  return null;
               }
            }

            return UnitCompiler.this.resolve((Java.AbstractTypeDeclaration)s.getEnclosingScope());
         }

         @Nullable
         protected IClass getOuterIClass2() {
            Java.AbstractTypeDeclaration oc = (Java.AbstractTypeDeclaration)UnitCompiler.getOuterClass(atd);
            return oc == null ? null : UnitCompiler.this.resolve(oc);
         }

         protected String getDescriptor2() {
            return Descriptor.fromClassName(atd.getClassName());
         }

         public boolean isArray() {
            return false;
         }

         protected IClass getComponentType2() {
            throw new InternalCompilerException(td.getLocation(), "SNO: Non-array type has no component type");
         }

         public boolean isPrimitive() {
            return false;
         }

         public boolean isPrimitiveNumeric() {
            return false;
         }

         protected IClass.IConstructor[] getDeclaredIConstructors2() {
            if (!(atd instanceof Java.AbstractClassDeclaration)) {
               return new IClass.IConstructor[0];
            } else {
               Java.AbstractClassDeclaration acd = (Java.AbstractClassDeclaration)atd;
               Java.ConstructorDeclarator[] cs = acd.getConstructors();
               IClass.IConstructor[] result = new IClass.IConstructor[cs.length];

               for(int i = 0; i < cs.length; ++i) {
                  result[i] = UnitCompiler.this.toIConstructor(cs[i]);
               }

               return result;
            }
         }

         protected IClass.IField[] getDeclaredIFields2() {
            if (atd instanceof Java.AbstractClassDeclaration) {
               Java.AbstractClassDeclaration cd = (Java.AbstractClassDeclaration)atd;
               List<IClass.IField> l = new ArrayList();

               for(Java.FieldDeclaration fd : Iterables.filterByClass(cd.fieldDeclarationsAndInitializers, Java.FieldDeclaration.class)) {
                  l.addAll(Arrays.asList(UnitCompiler.this.compileFields(fd)));
               }

               if (atd instanceof Java.EnumDeclaration) {
                  Java.EnumDeclaration ed = (Java.EnumDeclaration)atd;

                  for(Java.EnumConstant ec : ed.getConstants()) {
                     l.add(UnitCompiler.this.compileField(ed, new Java.Annotation[0], Access.PUBLIC, true, true, new Java.SimpleType(ed.getLocation(), UnitCompiler.this.resolve(ed)), 0, ec.name, (Java.ArrayInitializerOrRvalue)null));
                  }
               }

               return (IClass.IField[])l.toArray(new IClass.IField[l.size()]);
            } else if (!(atd instanceof Java.InterfaceDeclaration)) {
               throw new InternalCompilerException(td.getLocation(), "SNO: AbstractTypeDeclaration is neither ClassDeclaration nor InterfaceDeclaration");
            } else {
               Java.InterfaceDeclaration id = (Java.InterfaceDeclaration)atd;
               List<IClass.IField> l = new ArrayList();

               for(Java.FieldDeclaration fd : Iterables.filterByClass(id.constantDeclarations, Java.FieldDeclaration.class)) {
                  l.addAll(Arrays.asList(UnitCompiler.this.compileFields(fd)));
               }

               return (IClass.IField[])l.toArray(new IClass.IField[l.size()]);
            }
         }

         public IClass.IField[] getSyntheticIFields() {
            if (atd instanceof Java.AbstractClassDeclaration) {
               Collection<IClass.IField> c = ((Java.AbstractClassDeclaration)atd).syntheticFields.values();
               return (IClass.IField[])c.toArray(new IClass.IField[c.size()]);
            } else {
               return new IClass.IField[0];
            }
         }

         @Nullable
         protected IClass getSuperclass2() throws CompileException {
            if (atd instanceof Java.EnumDeclaration) {
               return UnitCompiler.this.iClassLoader.TYPE_java_lang_Enum;
            } else if (atd instanceof Java.AnonymousClassDeclaration) {
               IClass bt = UnitCompiler.this.getRawType(((Java.AnonymousClassDeclaration)atd).baseType);
               return UnitCompiler.isInterface(bt) ? UnitCompiler.this.iClassLoader.TYPE_java_lang_Object : bt;
            } else if (atd instanceof Java.NamedClassDeclaration) {
               Java.NamedClassDeclaration ncd = (Java.NamedClassDeclaration)atd;
               Java.Type oet = ncd.extendedType;
               if (oet == null) {
                  return UnitCompiler.this.iClassLoader.TYPE_java_lang_Object;
               } else {
                  IClass superclass = UnitCompiler.this.getRawType(oet);
                  if (superclass.isInterface()) {
                     UnitCompiler.this.compileError("\"" + superclass.toString() + "\" is an interface; classes can only extend a class", td.getLocation());
                  }

                  return superclass;
               }
            } else {
               return null;
            }
         }

         public Access getAccess() {
            if (atd instanceof Java.MemberClassDeclaration) {
               return ((Java.MemberClassDeclaration)atd).getAccess();
            } else if (atd instanceof Java.PackageMemberClassDeclaration) {
               return ((Java.PackageMemberClassDeclaration)atd).getAccess();
            } else if (atd instanceof Java.MemberInterfaceDeclaration) {
               return ((Java.MemberInterfaceDeclaration)atd).getAccess();
            } else if (atd instanceof Java.PackageMemberInterfaceDeclaration) {
               return ((Java.PackageMemberInterfaceDeclaration)atd).getAccess();
            } else if (atd instanceof Java.AnonymousClassDeclaration) {
               return Access.PUBLIC;
            } else if (atd instanceof Java.LocalClassDeclaration) {
               return Access.PUBLIC;
            } else {
               throw new InternalCompilerException(td.getLocation(), atd.getClass().getName());
            }
         }

         public boolean isFinal() {
            return atd instanceof Java.NamedClassDeclaration && ((Java.NamedClassDeclaration)atd).isFinal();
         }

         protected IClass[] getInterfaces2() throws CompileException {
            if (atd instanceof Java.AnonymousClassDeclaration) {
               IClass bt = UnitCompiler.this.getRawType(((Java.AnonymousClassDeclaration)atd).baseType);
               return bt.isInterface() ? new IClass[]{bt} : new IClass[0];
            } else if (atd instanceof Java.NamedClassDeclaration) {
               Java.NamedClassDeclaration ncd = (Java.NamedClassDeclaration)atd;
               IClass[] res = new IClass[ncd.implementedTypes.length];

               for(int i = 0; i < res.length; ++i) {
                  res[i] = UnitCompiler.this.getRawType(ncd.implementedTypes[i]);
                  if (!res[i].isInterface()) {
                     UnitCompiler.this.compileError("\"" + res[i].toString() + "\" is not an interface; classes can only implement interfaces", td.getLocation());
                  }
               }

               return res;
            } else if (atd instanceof Java.InterfaceDeclaration) {
               Java.InterfaceDeclaration id = (Java.InterfaceDeclaration)atd;
               IClass[] res = new IClass[id.extendedTypes.length];

               for(int i = 0; i < res.length; ++i) {
                  res[i] = UnitCompiler.this.getRawType(id.extendedTypes[i]);
                  if (!res[i].isInterface()) {
                     UnitCompiler.this.compileError("\"" + res[i].toString() + "\" is not an interface; interfaces can only extend interfaces", td.getLocation());
                  }
               }

               return res;
            } else {
               throw new InternalCompilerException(td.getLocation(), "SNO: AbstractTypeDeclaration is neither ClassDeclaration nor InterfaceDeclaration");
            }
         }

         protected IClass.IAnnotation[] getIAnnotations2() throws CompileException {
            return UnitCompiler.this.toIAnnotations(td.getAnnotations());
         }

         public boolean isAbstract() {
            return atd instanceof Java.InterfaceDeclaration || atd instanceof Java.NamedClassDeclaration && ((Java.NamedClassDeclaration)atd).isAbstract();
         }

         public boolean isEnum() {
            return atd instanceof Java.EnumDeclaration;
         }

         public boolean isInterface() {
            return atd instanceof Java.InterfaceDeclaration;
         }
      });
   }

   private void referenceThis(Java.Locatable locatable, Java.AbstractTypeDeclaration declaringType, Java.TypeBodyDeclaration declaringTypeBodyDeclaration, IType targetIType) throws CompileException {
      List<Java.TypeDeclaration> path = getOuterClasses(declaringType);
      if (isStaticContext(declaringTypeBodyDeclaration)) {
         this.compileError("No current instance available in static context", locatable.getLocation());
      }

      int j = 0;

      while(true) {
         if (j >= path.size()) {
            this.compileError("\"" + declaringType + "\" is not enclosed by \"" + targetIType + "\"", locatable.getLocation());
            break;
         }

         if (isAssignableFrom(targetIType, this.resolve((Java.TypeDeclaration)path.get(j)))) {
            break;
         }

         ++j;
      }

      int i;
      if (declaringTypeBodyDeclaration instanceof Java.ConstructorDeclarator) {
         if (j == 0) {
            this.load(locatable, this.resolve(declaringType), 0);
            return;
         }

         Java.ConstructorDeclarator constructorDeclarator = (Java.ConstructorDeclarator)declaringTypeBodyDeclaration;
         String spn = "this$" + (path.size() - 2);
         Java.LocalVariable syntheticParameter = (Java.LocalVariable)constructorDeclarator.syntheticParameters.get(spn);
         if (syntheticParameter == null) {
            throw new InternalCompilerException(locatable.getLocation(), "SNO: Synthetic parameter \"" + spn + "\" not found");
         }

         this.load(locatable, syntheticParameter);
         i = 1;
      } else {
         this.load(locatable, this.resolve(declaringType), 0);
         i = 0;
      }

      while(i < j) {
         Java.InnerClassDeclaration inner = (Java.InnerClassDeclaration)path.get(i);
         Java.TypeDeclaration outer = (Java.TypeDeclaration)path.get(i + 1);
         SimpleIField sf = new SimpleIField(this.resolve(inner), "this$" + (path.size() - i - 2), this.resolve(outer));
         inner.defineSyntheticField(sf);
         this.getfield(locatable, sf);
         ++i;
      }

   }

   private static List getOuterClasses(Java.TypeDeclaration inner) {
      List<Java.TypeDeclaration> path = new ArrayList();

      for(Java.TypeDeclaration ic = inner; ic != null; ic = getOuterClass(ic)) {
         path.add(ic);
      }

      return path;
   }

   @Nullable
   static Java.TypeDeclaration getOuterClass(Java.TypeDeclaration typeDeclaration) {
      if (typeDeclaration instanceof Java.PackageMemberClassDeclaration) {
         return null;
      } else if (typeDeclaration instanceof Java.MemberEnumDeclaration) {
         return null;
      } else if (!(typeDeclaration instanceof Java.LocalClassDeclaration)) {
         if (typeDeclaration instanceof Java.MemberClassDeclaration && ((Java.MemberClassDeclaration)typeDeclaration).isStatic()) {
            return null;
         } else {
            Java.Scope s;
            for(s = typeDeclaration; !(s instanceof Java.TypeBodyDeclaration); s = s.getEnclosingScope()) {
               if (s instanceof Java.ConstructorInvocation) {
                  return null;
               }

               if (s instanceof Java.CompilationUnit) {
                  return null;
               }
            }

            if (isStaticContext((Java.TypeBodyDeclaration)s)) {
               return null;
            } else {
               return (Java.AbstractTypeDeclaration)s.getEnclosingScope();
            }
         }
      } else {
         Java.Scope s;
         for(s = typeDeclaration.getEnclosingScope(); !(s instanceof Java.FunctionDeclarator) && !(s instanceof Java.Initializer); s = s.getEnclosingScope()) {
         }

         if (s instanceof Java.MethodDeclarator && ((Java.MethodDeclarator)s).isStatic()) {
            return null;
         } else if (s instanceof Java.Initializer && ((Java.Initializer)s).isStatic()) {
            return null;
         } else {
            while(!(s instanceof Java.TypeDeclaration)) {
               s = s.getEnclosingScope();
            }

            Java.TypeDeclaration immediatelyEnclosingTypeDeclaration = (Java.TypeDeclaration)s;
            return immediatelyEnclosingTypeDeclaration instanceof Java.AbstractClassDeclaration ? immediatelyEnclosingTypeDeclaration : null;
         }
      }
   }

   private IClass getIClass(Java.ThisReference tr) throws CompileException {
      if (tr.iClass != null) {
         return tr.iClass;
      } else {
         Java.Scope s;
         for(s = tr.getEnclosingScope(); s instanceof Java.Statement || s instanceof Java.CatchClause; s = s.getEnclosingScope()) {
         }

         if (s instanceof Java.FunctionDeclarator) {
            Java.FunctionDeclarator function = (Java.FunctionDeclarator)s;
            if (function instanceof Java.MethodDeclarator && ((Java.MethodDeclarator)function).isStatic()) {
               this.compileError("No current instance available in static method", tr.getLocation());
            }
         }

         while(!(s instanceof Java.TypeDeclaration)) {
            s = s.getEnclosingScope();
         }

         if (!(s instanceof Java.AbstractClassDeclaration)) {
            this.compileError("Only methods of classes can have a current instance", tr.getLocation());
         }

         return tr.iClass = this.resolve((Java.AbstractClassDeclaration)s);
      }
   }

   private IType getReturnType(Java.FunctionDeclarator fd) throws CompileException {
      return fd.returnType != null ? fd.returnType : (fd.returnType = this.getType(fd.type));
   }

   IClass.IConstructor toIConstructor(final Java.ConstructorDeclarator constructorDeclarator) {
      if (constructorDeclarator.iConstructor != null) {
         return constructorDeclarator.iConstructor;
      } else {
         IClass var10004 = this.resolve(constructorDeclarator.getDeclaringType());
         Objects.requireNonNull(var10004);
         constructorDeclarator.iConstructor = new IClass.IConstructor(var10004) {
            @Nullable
            private IClass.IAnnotation[] ias;

            {
               Objects.requireNonNull(x0);
            }

            public Access getAccess() {
               return constructorDeclarator.getAccess();
            }

            public IClass.IAnnotation[] getAnnotations() {
               if (this.ias != null) {
                  return this.ias;
               } else {
                  try {
                     return this.ias = UnitCompiler.this.toIAnnotations(constructorDeclarator.getAnnotations());
                  } catch (CompileException ce) {
                     throw new InternalCompilerException(constructorDeclarator.getLocation(), (String)null, ce);
                  }
               }
            }

            public MethodDescriptor getDescriptor2() throws CompileException {
               if (!(constructorDeclarator.getDeclaringClass() instanceof Java.InnerClassDeclaration)) {
                  return super.getDescriptor2();
               } else if (constructorDeclarator.getDeclaringClass() instanceof Java.MemberEnumDeclaration) {
                  return super.getDescriptor2();
               } else {
                  List<String> parameterFds = new ArrayList();
                  IClass outerClass = UnitCompiler.this.resolve(constructorDeclarator.getDeclaringClass()).getOuterIClass();
                  if (outerClass != null) {
                     parameterFds.add(outerClass.getDescriptor());
                  }

                  for(IClass.IField sf : constructorDeclarator.getDeclaringClass().syntheticFields.values()) {
                     if (sf.getName().startsWith("val$")) {
                        parameterFds.add(sf.getType().getDescriptor());
                     }
                  }

                  for(IClass pt : this.getParameterTypes2()) {
                     parameterFds.add(pt.getDescriptor());
                  }

                  return new MethodDescriptor("V", (String[])parameterFds.toArray(new String[parameterFds.size()]));
               }
            }

            public boolean isVarargs() {
               return constructorDeclarator.formalParameters.variableArity;
            }

            public IClass[] getParameterTypes2() throws CompileException {
               Java.FunctionDeclarator.FormalParameter[] parameters = constructorDeclarator.formalParameters.parameters;
               IClass[] res = new IClass[parameters.length];

               for(int i = 0; i < parameters.length; ++i) {
                  IClass parameterType = UnitCompiler.this.getRawType(parameters[i].type);
                  if (i == parameters.length - 1 && constructorDeclarator.formalParameters.variableArity) {
                     parameterType = UnitCompiler.this.iClassLoader.getArrayIClass(UnitCompiler.rawTypeOf(parameterType));
                  }

                  res[i] = parameterType;
               }

               return res;
            }

            public IClass[] getThrownExceptions2() throws CompileException {
               IClass[] res = new IClass[constructorDeclarator.thrownExceptions.length];

               for(int i = 0; i < res.length; ++i) {
                  res[i] = UnitCompiler.this.getRawType(constructorDeclarator.thrownExceptions[i]);
               }

               return res;
            }

            public String toString() {
               StringBuilder sb = (new StringBuilder()).append(constructorDeclarator.getDeclaringType().getClassName()).append('(');
               Java.FunctionDeclarator.FormalParameter[] parameters = constructorDeclarator.formalParameters.parameters;

               for(int i = 0; i < parameters.length; ++i) {
                  if (i != 0) {
                     sb.append(", ");
                  }

                  sb.append(parameters[i].toString(i == parameters.length - 1 && constructorDeclarator.formalParameters.variableArity));
               }

               return sb.append(')').toString();
            }
         };
         return constructorDeclarator.iConstructor;
      }
   }

   public IClass.IMethod toIMethod(final Java.MethodDeclarator methodDeclarator) {
      if (methodDeclarator.iMethod != null) {
         return methodDeclarator.iMethod;
      } else {
         IClass var10004 = this.resolve(methodDeclarator.getDeclaringType());
         Objects.requireNonNull(var10004);
         methodDeclarator.iMethod = new IClass.IMethod(var10004) {
            @Nullable
            IClass.IAnnotation[] ias;

            {
               Objects.requireNonNull(x0);
            }

            public Access getAccess() {
               return methodDeclarator.getDeclaringType() instanceof Java.InterfaceDeclaration && methodDeclarator.getAccess() == Access.DEFAULT ? Access.PUBLIC : methodDeclarator.getAccess();
            }

            public IClass.IAnnotation[] getAnnotations() {
               if (this.ias != null) {
                  return this.ias;
               } else {
                  try {
                     return this.ias = UnitCompiler.this.toIAnnotations(methodDeclarator.getAnnotations());
                  } catch (CompileException ce) {
                     throw new InternalCompilerException(methodDeclarator.getLocation(), (String)null, ce);
                  }
               }
            }

            public boolean isVarargs() {
               return methodDeclarator.formalParameters.variableArity;
            }

            public IClass[] getParameterTypes2() throws CompileException {
               Java.FunctionDeclarator.FormalParameter[] parameters = methodDeclarator.formalParameters.parameters;
               IClass[] res = new IClass[parameters.length];

               for(int i = 0; i < parameters.length; ++i) {
                  IClass parameterType = UnitCompiler.this.getRawType(parameters[i].type);
                  if (i == parameters.length - 1 && methodDeclarator.formalParameters.variableArity) {
                     parameterType = UnitCompiler.this.iClassLoader.getArrayIClass(parameterType);
                  }

                  res[i] = parameterType;
               }

               return res;
            }

            public IClass[] getThrownExceptions2() throws CompileException {
               List<IClass> result = new ArrayList();

               for(Java.Type ti : methodDeclarator.thrownExceptions) {
                  if (ti instanceof Java.ReferenceType) {
                     String[] identifiers = ((Java.ReferenceType)ti).identifiers;
                     if (identifiers.length == 1 && UnitCompiler.LOOKS_LIKE_TYPE_PARAMETER.matcher(identifiers[0]).matches()) {
                        continue;
                     }
                  }

                  result.add(UnitCompiler.this.getRawType(ti));
               }

               return (IClass[])result.toArray(new IClass[result.size()]);
            }

            public boolean isStatic() {
               return methodDeclarator.isStatic();
            }

            public boolean isAbstract() {
               return methodDeclarator.getDeclaringType() instanceof Java.InterfaceDeclaration && !methodDeclarator.isDefault() && methodDeclarator.getAccess() != Access.PRIVATE || methodDeclarator.isAbstract();
            }

            public IClass getReturnType() throws CompileException {
               return UnitCompiler.rawTypeOf(UnitCompiler.this.getReturnType(methodDeclarator));
            }

            public String getName() {
               return methodDeclarator.name;
            }
         };
         return methodDeclarator.iMethod;
      }
   }

   private IClass.IInvocable toIInvocable(final Java.FunctionDeclarator fd) {
      IClass.IInvocable result = (IClass.IInvocable)fd.accept(new Visitor.FunctionDeclaratorVisitor() {
         public IClass.IInvocable visitMethodDeclarator(Java.MethodDeclarator md) {
            return UnitCompiler.this.toIMethod((Java.MethodDeclarator)fd);
         }

         public IClass.IInvocable visitConstructorDeclarator(Java.ConstructorDeclarator cd) {
            return UnitCompiler.this.toIConstructor((Java.ConstructorDeclarator)fd);
         }
      });

      assert result != null;

      return result;
   }

   @Nullable
   private IClass importSingleType(String simpleTypeName, Location location) throws CompileException {
      String[] ss = this.getSingleTypeImport(simpleTypeName, location);
      if (ss == null) {
         return null;
      } else {
         IClass iClass = this.findTypeByFullyQualifiedName(location, ss);
         if (iClass == null) {
            this.compileError("Imported class \"" + Java.join(ss, ".") + "\" could not be loaded", location);
            return this.iClassLoader.TYPE_java_lang_Object;
         } else {
            return iClass;
         }
      }
   }

   @Nullable
   public String[] getSingleTypeImport(String name, Location location) throws CompileException {
      Map<String, String[]> stis = this.singleTypeImports;
      if (stis == null) {
         final List<Java.AbstractCompilationUnit.SingleTypeImportDeclaration> stids = new ArrayList();

         for(Java.AbstractCompilationUnit.ImportDeclaration id : this.abstractCompilationUnit.importDeclarations) {
            id.accept(new Visitor.ImportVisitor() {
               @Nullable
               public Void visitSingleTypeImportDeclaration(Java.AbstractCompilationUnit.SingleTypeImportDeclaration stid) {
                  stids.add(stid);
                  return null;
               }

               @Nullable
               public Void visitTypeImportOnDemandDeclaration(Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration tiodd) {
                  return null;
               }

               @Nullable
               public Void visitSingleStaticImportDeclaration(Java.AbstractCompilationUnit.SingleStaticImportDeclaration ssid) {
                  return null;
               }

               @Nullable
               public Void visitStaticImportOnDemandDeclaration(Java.AbstractCompilationUnit.StaticImportOnDemandDeclaration siodd) {
                  return null;
               }
            });
         }

         stis = new HashMap();

         for(Java.AbstractCompilationUnit.SingleTypeImportDeclaration stid : stids) {
            String[] ids = stid.identifiers;
            String simpleName = last(ids);
            String[] prev = (String[])stis.put(simpleName, ids);
            if (prev != null && !Arrays.equals(prev, ids)) {
               this.compileError("Class \"" + simpleName + "\" was previously imported as \"" + Java.join(prev, ".") + "\", now as \"" + Java.join(ids, ".") + "\"", stid.getLocation());
            }

            if (this.findTypeByFullyQualifiedName(location, ids) == null) {
               this.compileError("A class \"" + Java.join(ids, ".") + "\" could not be found", stid.getLocation());
            }
         }

         this.singleTypeImports = stis;
      }

      return (String[])stis.get(name);
   }

   @Nullable
   public IClass importTypeOnDemand(String simpleTypeName, Location location) throws CompileException {
      IClass importedClass = (IClass)this.onDemandImportableTypes.get(simpleTypeName);
      if (importedClass == null) {
         importedClass = this.importTypeOnDemand2(simpleTypeName, location);
         this.onDemandImportableTypes.put(simpleTypeName, importedClass);
      }

      return importedClass;
   }

   @Nullable
   private IClass importTypeOnDemand2(String simpleTypeName, Location location) throws CompileException {
      IClass importedClass = null;

      for(Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration tiodd : this.getTypeImportOnDemandImportDeclarations()) {
         IClass iClass = this.findTypeByFullyQualifiedName(location, concat(tiodd.identifiers, simpleTypeName));
         if (iClass != null) {
            if (importedClass != null && importedClass != iClass) {
               this.compileError("Ambiguous class name: \"" + importedClass + "\" vs. \"" + iClass + "\"", location);
            }

            importedClass = iClass;
         }
      }

      if (importedClass == null) {
         return null;
      } else {
         return importedClass;
      }
   }

   private Collection getTypeImportOnDemandImportDeclarations() {
      Collection<Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration> result = new ArrayList();

      for(Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration tiodd : Iterables.filterByClass(this.abstractCompilationUnit.importDeclarations, Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration.class)) {
         result.add(tiodd);
      }

      result.add(new Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration(Location.NOWHERE, new String[]{"java", "lang"}));
      return result;
   }

   private IClass consT(Java.Locatable locatable, @Nullable Object value) throws CompileException {
      if (value instanceof Character) {
         this.consT(locatable, (Character)value);
         return IClass.INT;
      } else if (!(value instanceof Byte) && !(value instanceof Short) && !(value instanceof Integer)) {
         if (Boolean.TRUE.equals(value)) {
            this.consT(locatable, 1);
            return IClass.BOOLEAN;
         } else if (Boolean.FALSE.equals(value)) {
            this.consT(locatable, 0);
            return IClass.BOOLEAN;
         } else if (value instanceof Float) {
            this.consT(locatable, (Float)value);
            return IClass.FLOAT;
         } else if (value instanceof Long) {
            this.consT(locatable, (Long)value);
            return IClass.LONG;
         } else if (value instanceof Double) {
            this.consT(locatable, (Double)value);
            return IClass.DOUBLE;
         } else if (!(value instanceof String)) {
            if (value instanceof IClass) {
               this.consT(locatable, (IClass)value);
               return this.iClassLoader.TYPE_java_lang_Class;
            } else if (value == null) {
               this.aconstnull(locatable);
               return IClass.NULL;
            } else {
               throw new InternalCompilerException(locatable.getLocation(), "Unknown literal \"" + value + "\"");
            }
         } else {
            String s = (String)value;
            String[] ss = makeUtf8Able(s);
            this.consT(locatable, ss[0]);

            for(int i = 1; i < ss.length; ++i) {
               this.consT(locatable, ss[i]);
               this.invokeMethod(locatable, this.iClassLoader.METH_java_lang_String__concat__java_lang_String);
            }

            return this.iClassLoader.TYPE_java_lang_String;
         }
      } else {
         this.consT(locatable, ((Number)value).intValue());
         return IClass.INT;
      }
   }

   private static String[] makeUtf8Able(String s) {
      if (s.length() < 21845) {
         return new String[]{s};
      } else {
         int sLength = s.length();
         int utfLength = 0;
         int from = 0;
         List<String> l = new ArrayList();
         int i = 0;

         while(true) {
            if (i == sLength) {
               l.add(s.substring(from));
               break;
            }

            if (utfLength >= 65532) {
               l.add(s.substring(from, i));
               if (i + 21845 > sLength) {
                  l.add(s.substring(i));
                  break;
               }

               from = i;
               utfLength = 0;
            }

            int c = s.charAt(i);
            if (c >= 1 && c <= 127) {
               ++utfLength;
            } else if (c > 2047) {
               utfLength += 3;
            } else {
               utfLength += 2;
            }

            ++i;
         }

         return (String[])l.toArray(new String[l.size()]);
      }
   }

   private void consT(Java.Locatable locatable, IClass t, int value) {
      if (t != IClass.BYTE && t != IClass.CHAR && t != IClass.INT && t != IClass.SHORT && t != IClass.BOOLEAN) {
         if (t == IClass.LONG) {
            this.consT(locatable, (long)value);
         } else if (t == IClass.FLOAT) {
            this.consT(locatable, (float)value);
         } else {
            if (t != IClass.DOUBLE) {
               throw new AssertionError(t);
            }

            this.consT(locatable, (double)value);
         }
      } else {
         this.consT(locatable, value);
      }

   }

   private void consT(Java.Locatable locatable, int value) {
      this.addLineNumberOffset(locatable);
      if (value >= -1 && value <= 5) {
         this.write(3 + value);
      } else if (value >= -128 && value <= 127) {
         this.write(16);
         this.writeByte(value);
      } else if (value >= -32768 && value <= 32767) {
         this.write(17);
         this.writeShort(value);
      } else {
         this.writeLdc(this.addConstantIntegerInfo(value));
      }

      this.getCodeContext().pushIntOperand();
   }

   private void consT(Java.Locatable locatable, long value) {
      this.addLineNumberOffset(locatable);
      if (value != 0L && value != 1L) {
         this.writeLdc2(this.addConstantLongInfo(value));
      } else {
         this.write(9 + (int)value);
      }

      this.getCodeContext().pushLongOperand();
   }

   private void consT(Java.Locatable locatable, float value) {
      this.addLineNumberOffset(locatable);
      if (Float.floatToIntBits(value) != Float.floatToIntBits(0.0F) && value != 1.0F && value != 2.0F) {
         this.writeLdc(this.addConstantFloatInfo(value));
      } else {
         this.write(11 + (int)value);
      }

      this.getCodeContext().pushFloatOperand();
   }

   private void consT(Java.Locatable locatable, double value) {
      this.addLineNumberOffset(locatable);
      if (Double.doubleToLongBits(value) != Double.doubleToLongBits((double)0.0F) && value != (double)1.0F) {
         this.writeLdc2(this.addConstantDoubleInfo(value));
      } else {
         this.write(14 + (int)value);
      }

      this.getCodeContext().pushDoubleOperand();
   }

   private void consT(Java.Locatable locatable, String s) {
      this.addLineNumberOffset(locatable);
      this.writeLdc(this.addConstantStringInfo(s));
      this.getCodeContext().pushObjectOperand("Ljava/lang/String;");
   }

   private void consT(Java.Locatable locatable, IClass iClass) {
      this.addLineNumberOffset(locatable);
      this.writeLdc(this.addConstantClassInfo(iClass));
      this.getCodeContext().pushObjectOperand("Ljava/lang/Class;");
   }

   private void castConversion(Java.Locatable locatable, IType sourceType, IType targetType, @Nullable Object constantValue) throws CompileException {
      if (!this.tryCastConversion(locatable, sourceType, targetType, constantValue)) {
         this.compileError("Cast conversion not possible from type \"" + sourceType + "\" to type \"" + targetType + "\"", locatable.getLocation());
      }

   }

   private boolean tryCastConversion(Java.Locatable locatable, IType sourceType, IType targetType, @Nullable Object constantValue) throws CompileException {
      return this.tryAssignmentConversion(locatable, sourceType, targetType, constantValue) || this.tryNarrowingPrimitiveConversion(locatable, sourceType, targetType) || this.tryNarrowingReferenceConversion(locatable, sourceType, targetType);
   }

   private void assignmentConversion(Java.Locatable locatable, IType sourceType, IType targetType, @Nullable Object constantValue) throws CompileException {
      if (!this.tryAssignmentConversion(locatable, sourceType, targetType, constantValue)) {
         this.compileError("Assignment conversion not possible from type \"" + sourceType + "\" to type \"" + targetType + "\"", locatable.getLocation());
      }

   }

   private boolean tryAssignmentConversion(Java.Locatable locatable, IType sourceType, IType targetType, @Nullable Object constantValue) throws CompileException {
      LOGGER.entering((String)null, "tryAssignmentConversion", new Object[]{locatable, sourceType, targetType, constantValue});
      if (this.tryIdentityConversion(sourceType, targetType)) {
         return true;
      } else if (this.tryWideningPrimitiveConversion(locatable, sourceType, targetType)) {
         return true;
      } else if (this.isWideningReferenceConvertible(sourceType, targetType)) {
         this.getCodeContext().popOperand(sourceType == IClass.NULL ? "V" : rawTypeOf(sourceType).getDescriptor());
         this.getCodeContext().pushOperand(rawTypeOf(targetType).getDescriptor());
         return true;
      } else {
         IClass boxedType = this.isBoxingConvertible(sourceType);
         if (boxedType != null) {
            if (this.tryIdentityConversion(boxedType, targetType)) {
               this.boxingConversion(locatable, sourceType, boxedType);
               return true;
            }

            if (this.isWideningReferenceConvertible(boxedType, targetType)) {
               this.boxingConversion(locatable, sourceType, boxedType);
               this.getCodeContext().popOperand(boxedType.getDescriptor());
               this.getCodeContext().pushOperand(rawTypeOf(targetType).getDescriptor());
               return true;
            }
         }

         boxedType = this.isUnboxingConvertible(sourceType);
         if (boxedType != null) {
            if (this.tryIdentityConversion(boxedType, targetType)) {
               this.unboxingConversion(locatable, sourceType, boxedType);
               return true;
            }

            if (this.isWideningPrimitiveConvertible(boxedType, targetType)) {
               this.unboxingConversion(locatable, sourceType, boxedType);
               this.tryWideningPrimitiveConversion(locatable, boxedType, targetType);
               return true;
            }
         }

         return constantValue != NOT_CONSTANT && this.tryConstantAssignmentConversion(locatable, constantValue, targetType);
      }
   }

   @Nullable
   private Object constantAssignmentConversion(Java.Locatable locatable, @Nullable Object value, IType targetType) throws CompileException {
      if (value == NOT_CONSTANT) {
         return NOT_CONSTANT;
      } else {
         if (targetType == IClass.BOOLEAN) {
            if (value instanceof Boolean) {
               return value;
            }
         } else if (targetType == this.iClassLoader.TYPE_java_lang_String) {
            if (value instanceof String || value == null) {
               return value;
            }
         } else if (targetType == IClass.BYTE) {
            if (value instanceof Byte) {
               return value;
            }

            if (!(value instanceof Short) && !(value instanceof Integer)) {
               if (value instanceof Character) {
                  int x = (Character)value;
                  if (x >= -128 && x <= 127) {
                     return (byte)x;
                  }
               }
            } else {
               assert value != null;

               int x = ((Number)value).intValue();
               if (x >= -128 && x <= 127) {
                  return (byte)x;
               }
            }
         } else if (targetType == IClass.SHORT) {
            if (value instanceof Byte) {
               return ((Number)value).shortValue();
            }

            if (value instanceof Short) {
               return value;
            }

            if (value instanceof Character) {
               int x = (Character)value;
               if (x >= -32768 && x <= 32767) {
                  return (short)x;
               }
            } else if (value instanceof Integer) {
               int x = (Integer)value;
               if (x >= -32768 && x <= 32767) {
                  return (short)x;
               }
            }
         } else if (targetType == IClass.CHAR) {
            if (value instanceof Short) {
               return value;
            }

            if (value instanceof Byte || value instanceof Short || value instanceof Integer) {
               assert value != null;

               int x = ((Number)value).intValue();
               if (x >= 0 && x <= 65535) {
                  return (char)x;
               }
            }
         } else if (targetType == IClass.INT) {
            if (value instanceof Integer) {
               return value;
            }

            if (value instanceof Byte || value instanceof Short) {
               assert value != null;

               return ((Number)value).intValue();
            }

            if (value instanceof Character) {
               return Integer.valueOf((Character)value);
            }
         } else if (targetType == IClass.LONG) {
            if (value instanceof Long) {
               return value;
            }

            if (value instanceof Byte || value instanceof Short || value instanceof Integer) {
               assert value != null;

               return ((Number)value).longValue();
            }

            if (value instanceof Character) {
               return (long)(Character)value;
            }
         } else if (targetType == IClass.FLOAT) {
            if (value instanceof Float) {
               return value;
            }

            if (value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Long) {
               assert value != null;

               return ((Number)value).floatValue();
            }

            if (value instanceof Character) {
               return (float)(Character)value;
            }
         } else if (targetType == IClass.DOUBLE) {
            if (value instanceof Double) {
               return value;
            }

            if (value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Long || value instanceof Float) {
               assert value != null;

               return ((Number)value).doubleValue();
            }

            if (value instanceof Character) {
               return (double)(Character)value;
            }
         } else {
            if (value == null && !isPrimitive(targetType)) {
               return null;
            }

            if (value instanceof String && isAssignableFrom(targetType, this.iClassLoader.TYPE_java_lang_String)) {
               return value;
            }
         }

         if (value == null) {
            this.compileError("Cannot convert 'null' to type \"" + targetType.toString() + "\"", locatable.getLocation());
         } else {
            this.compileError("Cannot convert constant of type \"" + value.getClass().getName() + "\" to type \"" + targetType.toString() + "\"", locatable.getLocation());
         }

         return value;
      }
   }

   private IClass unaryNumericPromotion(Java.Locatable locatable, IType type) throws CompileException {
      type = this.convertToPrimitiveNumericType(locatable, type);
      IClass promotedType = this.unaryNumericPromotionType(locatable, type);
      this.numericPromotion(locatable, type, promotedType);
      return promotedType;
   }

   private void reverseUnaryNumericPromotion(Java.Locatable locatable, IClass sourceType, IType targetType) {
      IClass unboxedType = this.isUnboxingConvertible(targetType);
      IType pt = (IType)(unboxedType != null ? unboxedType : targetType);
      if (!this.tryIdentityConversion(sourceType, pt) && !this.tryNarrowingPrimitiveConversion(locatable, sourceType, pt)) {
         throw new InternalCompilerException(locatable.getLocation(), "SNO: reverse unary numeric promotion failed");
      } else {
         if (unboxedType != null) {
            this.boxingConversion(locatable, unboxedType, targetType);
         }

      }
   }

   private IClass convertToPrimitiveNumericType(Java.Locatable locatable, IType type) throws CompileException {
      if (type instanceof IClass && ((IClass)type).isPrimitiveNumeric()) {
         return (IClass)type;
      } else {
         IClass unboxedType = this.isUnboxingConvertible(type);
         if (unboxedType != null) {
            this.unboxingConversion(locatable, type, unboxedType);
            return unboxedType;
         } else {
            this.compileError("Object of type \"" + type.toString() + "\" cannot be converted to a numeric type", locatable.getLocation());
            return IClass.INT;
         }
      }
   }

   private void numericPromotion(Java.Locatable locatable, IType sourceType, IClass targetType) {
      if (!this.tryIdentityConversion(sourceType, targetType) && !this.tryWideningPrimitiveConversion(locatable, sourceType, targetType)) {
         throw new InternalCompilerException(locatable.getLocation(), "SNO: Conversion failed");
      }
   }

   private IClass unaryNumericPromotionType(Java.Locatable locatable, IType type) throws CompileException {
      if (!(type instanceof IClass) || !((IClass)type).isPrimitiveNumeric()) {
         this.compileError("Unary numeric promotion not possible on non-numeric-primitive type \"" + type + "\"", locatable.getLocation());
      }

      return type == IClass.DOUBLE ? IClass.DOUBLE : (type == IClass.FLOAT ? IClass.FLOAT : (type == IClass.LONG ? IClass.LONG : IClass.INT));
   }

   private IClass binaryNumericPromotionType(Java.Locatable locatable, IType type1, IType type2) throws CompileException {
      if (!(type1 instanceof IClass) || !((IClass)type1).isPrimitiveNumeric() || !(type2 instanceof IClass) || !((IClass)type2).isPrimitiveNumeric()) {
         this.compileError("Binary numeric promotion not possible on types \"" + type1 + "\" and \"" + type2 + "\"", locatable.getLocation());
      }

      return type1 != IClass.DOUBLE && type2 != IClass.DOUBLE ? (type1 != IClass.FLOAT && type2 != IClass.FLOAT ? (type1 != IClass.LONG && type2 != IClass.LONG ? IClass.INT : IClass.LONG) : IClass.FLOAT) : IClass.DOUBLE;
   }

   private boolean isIdentityConvertible(IType sourceType, IType targetType) {
      return sourceType == targetType;
   }

   private boolean tryIdentityConversion(IType sourceType, IType targetType) {
      return sourceType == targetType;
   }

   private boolean isWideningPrimitiveConvertible(IClass sourceType, IType targetType) {
      return PRIMITIVE_WIDENING_CONVERSIONS.get(sourceType.getDescriptor() + rawTypeOf(targetType).getDescriptor()) != null;
   }

   private boolean tryWideningPrimitiveConversion(Java.Locatable locatable, IType sourceType, IType targetType) {
      if (sourceType instanceof IParameterizedType) {
         return false;
      } else if (targetType instanceof IParameterizedType) {
         return false;
      } else {
         IClass targetClass = (IClass)targetType;
         int[] opcodes = (int[])PRIMITIVE_WIDENING_CONVERSIONS.get(rawTypeOf(sourceType).getDescriptor() + targetClass.getDescriptor());
         if (opcodes == null) {
            return false;
         } else {
            this.addLineNumberOffset(locatable);

            for(int opcode : opcodes) {
               this.write(opcode);
            }

            this.getCodeContext().popOperand();
            this.getCodeContext().pushOperand(targetClass.getDescriptor());
            return true;
         }
      }
   }

   private static void fillConversionMap(Object[] array, Map map) {
      int[] opcodes = null;

      for(Object o : array) {
         if (o instanceof int[]) {
            opcodes = (int[])o;
         } else {
            map.put((String)o, opcodes);
         }
      }

   }

   private boolean isWideningReferenceConvertible(IType sourceType, IType targetType) throws CompileException {
      IClass sourceClass = rawTypeOf(sourceType);
      IClass targetClass = rawTypeOf(targetType);
      return !targetClass.isPrimitive() && sourceType != targetType ? targetClass.isAssignableFrom(sourceClass) : false;
   }

   private boolean isNarrowingPrimitiveConvertible(IType sourceType, IType targetType) {
      return PRIMITIVE_NARROWING_CONVERSIONS.containsKey(rawTypeOf(sourceType).getDescriptor() + rawTypeOf(targetType).getDescriptor());
   }

   private boolean tryNarrowingPrimitiveConversion(Java.Locatable locatable, IType sourceType, IType targetType) {
      if (sourceType instanceof IClass && targetType instanceof IClass) {
         IClass sourceClass = (IClass)sourceType;
         IClass targetClass = (IClass)targetType;
         int[] opcodes = (int[])PRIMITIVE_NARROWING_CONVERSIONS.get(sourceClass.getDescriptor() + targetClass.getDescriptor());
         if (opcodes == null) {
            return false;
         } else {
            this.addLineNumberOffset(locatable);

            for(int opcode : opcodes) {
               this.write(opcode);
            }

            this.getCodeContext().popOperand();
            this.getCodeContext().pushOperand(targetClass.getDescriptor());
            return true;
         }
      } else {
         return false;
      }
   }

   private boolean tryConstantAssignmentConversion(Java.Locatable locatable, @Nullable Object constantValue, IType targetType) {
      LOGGER.entering((String)null, "tryConstantAssignmentConversion", new Object[]{locatable, constantValue, targetType});
      int cv;
      if (constantValue instanceof Byte) {
         cv = (Byte)constantValue;
      } else if (constantValue instanceof Short) {
         cv = (Short)constantValue;
      } else if (constantValue instanceof Integer) {
         cv = (Integer)constantValue;
      } else {
         if (!(constantValue instanceof Character)) {
            return false;
         }

         cv = (Character)constantValue;
      }

      if (targetType == IClass.BYTE) {
         return cv >= -128 && cv <= 127;
      } else if (targetType == IClass.SHORT) {
         return cv >= -32768 && cv <= 32767;
      } else if (targetType != IClass.CHAR) {
         IClassLoader icl = this.iClassLoader;
         if (targetType == icl.TYPE_java_lang_Byte && cv >= -128 && cv <= 127) {
            this.boxingConversion(locatable, IClass.BYTE, targetType);
            return true;
         } else if (targetType == icl.TYPE_java_lang_Short && cv >= -32768 && cv <= 32767) {
            this.boxingConversion(locatable, IClass.SHORT, targetType);
            return true;
         } else if (targetType == icl.TYPE_java_lang_Character && cv >= 0 && cv <= 65535) {
            this.boxingConversion(locatable, IClass.CHAR, targetType);
            return true;
         } else {
            return false;
         }
      } else {
         return cv >= 0 && cv <= 65535;
      }
   }

   private boolean isNarrowingReferenceConvertible(IType sourceType, IType targetType) throws CompileException {
      if (rawTypeOf(sourceType).isPrimitive()) {
         return false;
      } else if (sourceType == targetType) {
         return false;
      } else {
         IClass rawSourceType = rawTypeOf(sourceType);
         IClass rawTargetType = rawTypeOf(targetType);
         if (rawSourceType.isAssignableFrom(rawTargetType)) {
            return true;
         } else if (rawTargetType.isInterface() && !rawSourceType.isFinal() && !rawTargetType.isAssignableFrom(rawSourceType)) {
            return true;
         } else if (sourceType == this.iClassLoader.TYPE_java_lang_Object && rawTargetType.isArray()) {
            return true;
         } else if (sourceType == this.iClassLoader.TYPE_java_lang_Object && rawTargetType.isInterface()) {
            return true;
         } else if (rawSourceType.isInterface() && !rawTargetType.isFinal()) {
            return true;
         } else if (rawSourceType.isInterface() && rawTargetType.isFinal() && rawSourceType.isAssignableFrom(rawTargetType)) {
            return true;
         } else if (rawSourceType.isInterface() && rawTargetType.isInterface() && !rawTargetType.isAssignableFrom(rawSourceType)) {
            return true;
         } else {
            if (rawSourceType.isArray() && rawTargetType.isArray()) {
               IType st = rawSourceType.getComponentType();

               assert st != null;

               IType tt = rawTargetType.getComponentType();

               assert tt != null;

               if (this.isNarrowingPrimitiveConvertible(st, tt) || this.isNarrowingReferenceConvertible(st, tt)) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   private boolean tryNarrowingReferenceConversion(Java.Locatable locatable, IType sourceType, IType targetType) throws CompileException {
      if (!this.isNarrowingReferenceConvertible(sourceType, targetType)) {
         return false;
      } else {
         this.checkcast(locatable, targetType);
         return true;
      }
   }

   private boolean isCastReferenceConvertible(IType sourceType, IType targetType) throws CompileException {
      return this.isIdentityConvertible(sourceType, targetType) || this.isWideningReferenceConvertible(sourceType, targetType) || this.isNarrowingReferenceConvertible(sourceType, targetType);
   }

   @Nullable
   private IClass isBoxingConvertible(IType sourceType) {
      IClassLoader icl = this.iClassLoader;
      if (sourceType == IClass.BOOLEAN) {
         return icl.TYPE_java_lang_Boolean;
      } else if (sourceType == IClass.BYTE) {
         return icl.TYPE_java_lang_Byte;
      } else if (sourceType == IClass.CHAR) {
         return icl.TYPE_java_lang_Character;
      } else if (sourceType == IClass.SHORT) {
         return icl.TYPE_java_lang_Short;
      } else if (sourceType == IClass.INT) {
         return icl.TYPE_java_lang_Integer;
      } else if (sourceType == IClass.LONG) {
         return icl.TYPE_java_lang_Long;
      } else if (sourceType == IClass.FLOAT) {
         return icl.TYPE_java_lang_Float;
      } else {
         return sourceType == IClass.DOUBLE ? icl.TYPE_java_lang_Double : null;
      }
   }

   private boolean tryBoxingConversion(Java.Locatable locatable, IType sourceType, IType targetType) {
      if (this.isBoxingConvertible(sourceType) == targetType) {
         this.boxingConversion(locatable, sourceType, targetType);
         return true;
      } else {
         return false;
      }
   }

   private void boxingConversion(Java.Locatable locatable, IType sourceType, IType targetType) {
      assert targetType instanceof IClass;

      IClass targetClass = (IClass)targetType;

      assert sourceType instanceof IClass;

      IClass sourceClass = (IClass)sourceType;
      this.invoke(locatable, 184, targetClass, "valueOf", new MethodDescriptor(targetClass.getDescriptor(), new String[]{sourceClass.getDescriptor()}), false);
   }

   @Nullable
   private IClass isUnboxingConvertible(IType sourceType) {
      IClassLoader icl = this.iClassLoader;
      if (sourceType == icl.TYPE_java_lang_Boolean) {
         return IClass.BOOLEAN;
      } else if (sourceType == icl.TYPE_java_lang_Byte) {
         return IClass.BYTE;
      } else if (sourceType == icl.TYPE_java_lang_Character) {
         return IClass.CHAR;
      } else if (sourceType == icl.TYPE_java_lang_Short) {
         return IClass.SHORT;
      } else if (sourceType == icl.TYPE_java_lang_Integer) {
         return IClass.INT;
      } else if (sourceType == icl.TYPE_java_lang_Long) {
         return IClass.LONG;
      } else if (sourceType == icl.TYPE_java_lang_Float) {
         return IClass.FLOAT;
      } else {
         return sourceType == icl.TYPE_java_lang_Double ? IClass.DOUBLE : null;
      }
   }

   private boolean isConvertibleToPrimitiveNumeric(IType sourceType) {
      if (sourceType instanceof IClass && ((IClass)sourceType).isPrimitiveNumeric()) {
         return true;
      } else {
         IClass unboxedType = this.isUnboxingConvertible(sourceType);
         return unboxedType != null && unboxedType.isPrimitiveNumeric();
      }
   }

   private void unboxingConversion(Java.Locatable locatable, IType sourceType, IClass targetType) {
      assert sourceType instanceof IClass;

      this.invoke(locatable, 182, (IClass)sourceType, targetType.toString() + "Value", new MethodDescriptor(targetType.getDescriptor(), new String[0]), false);
   }

   @Nullable
   private IClass findTypeByFullyQualifiedName(Location location, String[] identifiers) throws CompileException {
      String className = Java.join(identifiers, ".");

      while(true) {
         IClass iClass = this.findTypeByName(location, className);
         if (iClass != null) {
            return iClass;
         }

         int idx = className.lastIndexOf(46);
         if (idx == -1) {
            return null;
         }

         className = className.substring(0, idx) + '$' + className.substring(idx + 1);
      }
   }

   private void ifNumeric(Java.Locatable locatable, int opIdx, CodeContext.Offset dst, boolean orientation) {
      assert opIdx >= 0 && opIdx <= 5;

      ClassFile.StackMapTableAttribute.VerificationTypeInfo topOperand = this.getCodeContext().peekOperand();
      if (topOperand == ClassFile.StackMapTableAttribute.INTEGER_VARIABLE_INFO) {
         this.if_icmpxx(locatable, !orientation ? opIdx ^ 1 : opIdx, dst);
      } else {
         if (topOperand != ClassFile.StackMapTableAttribute.LONG_VARIABLE_INFO && topOperand != ClassFile.StackMapTableAttribute.FLOAT_VARIABLE_INFO && topOperand != ClassFile.StackMapTableAttribute.DOUBLE_VARIABLE_INFO) {
            throw new InternalCompilerException(locatable.getLocation(), "Unexpected computational type \"" + topOperand + "\"");
         }

         this.cmp(locatable, opIdx);
         this.ifxx(locatable, !orientation ? opIdx ^ 1 : opIdx, dst);
      }

   }

   private void aconstnull(Java.Locatable locatable) {
      this.addLineNumberOffset(locatable);
      this.write(1);
      this.getCodeContext().pushNullOperand();
   }

   private void add(Java.Locatable locatable) {
      this.mulDivRemAddSub(locatable, "+");
   }

   private void andOrXor(Java.Locatable locatable, String operator) {
      ClassFile.StackMapTableAttribute.VerificationTypeInfo operand2 = this.getCodeContext().popIntOrLongOperand();
      ClassFile.StackMapTableAttribute.VerificationTypeInfo operand1 = this.getCodeContext().popIntOrLongOperand();

      assert operand1 == operand2;

      int opcode = (operator == "&" ? 126 : (operator == "|" ? 128 : (operator == "^" ? 130 : Integer.MAX_VALUE))) + (operand1 == ClassFile.StackMapTableAttribute.LONG_VARIABLE_INFO ? 1 : 0);
      this.addLineNumberOffset(locatable);
      this.write(opcode);
      this.getCodeContext().pushOperand(operand1);
   }

   private void anewarray(Java.Locatable locatable, IClass componentType) {
      IClass arrayType = this.iClassLoader.getArrayIClass(componentType);
      this.addLineNumberOffset(locatable);
      this.getCodeContext().popIntOperand();
      this.write(189);
      this.writeConstantClassInfo(componentType);
      this.getCodeContext().pushObjectOperand(arrayType.getDescriptor());
   }

   private void arraylength(Java.Locatable locatable) {
      this.addLineNumberOffset(locatable);

      try {
         this.getCodeContext().popObjectOperand();
         this.write(190);
         this.getCodeContext().pushIntOperand();
      } catch (AssertionError ae) {
         throw new InternalCompilerException(locatable.getLocation(), (String)null, ae);
      }
   }

   private void arraystore(Java.Locatable locatable, IType lhsComponentType) {
      this.addLineNumberOffset(locatable);
      this.getCodeContext().popOperand();
      this.getCodeContext().popOperand();
      this.getCodeContext().popOperand();
      this.write(79 + ilfdabcs(rawTypeOf(lhsComponentType)));
   }

   private void athrow(Java.Locatable locatable) {
      this.addLineNumberOffset(locatable);
      this.write(191);
      this.codeContext.currentInserter().setStackMap((StackMap)null);
   }

   private void checkcast(Java.Locatable locatable, IType targetType) {
      IClass rawTargetType = rawTypeOf(targetType);
      this.addLineNumberOffset(locatable);
      this.write(192);
      this.writeConstantClassInfo(rawTargetType);
      this.getCodeContext().popOperand();
      this.getCodeContext().pushObjectOperand(rawTargetType.getDescriptor());
   }

   private void cmp(Java.Locatable locatable, int opIdx) {
      assert opIdx >= 0 && opIdx <= 5;

      ClassFile.StackMapTableAttribute.VerificationTypeInfo operand2 = this.getCodeContext().currentInserter().getStackMap().peekOperand();
      this.getCodeContext().popOperand();
      ClassFile.StackMapTableAttribute.VerificationTypeInfo operand1 = this.getCodeContext().currentInserter().getStackMap().peekOperand();
      this.getCodeContext().popOperand();
      if (operand1 == ClassFile.StackMapTableAttribute.LONG_VARIABLE_INFO && operand2 == ClassFile.StackMapTableAttribute.LONG_VARIABLE_INFO) {
         this.write(148);
      } else if (operand1 == ClassFile.StackMapTableAttribute.FLOAT_VARIABLE_INFO && operand2 == ClassFile.StackMapTableAttribute.FLOAT_VARIABLE_INFO) {
         this.write(opIdx != 3 && opIdx != 4 ? 150 : 149);
      } else {
         if (operand1 != ClassFile.StackMapTableAttribute.DOUBLE_VARIABLE_INFO || operand2 != ClassFile.StackMapTableAttribute.DOUBLE_VARIABLE_INFO) {
            throw new AssertionError(operand1 + " and " + operand2);
         }

         this.write(opIdx != 3 && opIdx != 4 ? 152 : 151);
      }

      this.getCodeContext().pushIntOperand();
   }

   private void dup(Java.Locatable locatable) {
      ClassFile.StackMapTableAttribute.VerificationTypeInfo topOperand = this.getCodeContext().peekOperand();
      this.addLineNumberOffset(locatable);
      this.write(topOperand.category() == 1 ? 89 : 92);
      this.getCodeContext().pushOperand(topOperand);
   }

   private void dup2(Java.Locatable locatable) {
      this.addLineNumberOffset(locatable);
      ClassFile.StackMapTableAttribute.VerificationTypeInfo topOperand = this.getCodeContext().popOperand();

      assert topOperand.category() == 1;

      ClassFile.StackMapTableAttribute.VerificationTypeInfo topButOneOperand = this.getCodeContext().popOperand();

      assert topButOneOperand.category() == 1;

      this.write(92);
      this.getCodeContext().pushOperand(topButOneOperand);
      this.getCodeContext().pushOperand(topOperand);
      this.getCodeContext().pushOperand(topButOneOperand);
      this.getCodeContext().pushOperand(topOperand);
   }

   private void dupn(Java.Locatable locatable, int n) {
      switch (n) {
         case 0:
            break;
         case 1:
            this.dup(locatable);
            break;
         case 2:
            this.dup2(locatable);
            break;
         default:
            throw new AssertionError(n);
      }

   }

   private void dupx(Java.Locatable locatable) {
      ClassFile.StackMapTableAttribute.VerificationTypeInfo topOperand = this.getCodeContext().popOperand();
      ClassFile.StackMapTableAttribute.VerificationTypeInfo topButOneOperand = this.getCodeContext().popOperand();
      this.addLineNumberOffset(locatable);
      this.write(topOperand.category() == 1 ? (topButOneOperand.category() == 1 ? 90 : 91) : (topButOneOperand.category() == 1 ? 93 : 94));
      this.getCodeContext().pushOperand(topOperand);
      this.getCodeContext().pushOperand(topButOneOperand);
      this.getCodeContext().pushOperand(topOperand);
   }

   private void dupx2(Java.Locatable locatable) {
      ClassFile.StackMapTableAttribute.VerificationTypeInfo topOperand = this.getCodeContext().popOperand();
      ClassFile.StackMapTableAttribute.VerificationTypeInfo topButOneOperand = this.getCodeContext().popOperand();
      ClassFile.StackMapTableAttribute.VerificationTypeInfo topButTwoOperand = this.getCodeContext().popOperand();

      assert topButOneOperand.category() == 1;

      assert topButTwoOperand.category() == 1;

      this.addLineNumberOffset(locatable);
      this.write(topOperand.category() == 1 ? 91 : 94);
      this.getCodeContext().pushOperand(topOperand);
      this.getCodeContext().pushOperand(topButTwoOperand);
      this.getCodeContext().pushOperand(topButOneOperand);
      this.getCodeContext().pushOperand(topOperand);
   }

   private void dupxx(Java.Locatable locatable, int positions) {
      switch (positions) {
         case 0:
            this.dup(locatable);
            break;
         case 1:
            this.dupx(locatable);
            break;
         case 2:
            this.dupx2(locatable);
            break;
         default:
            throw new AssertionError(positions);
      }

   }

   private void getfield(Java.Locatable locatable, IClass.IField iField) throws CompileException {
      this.getfield(locatable, iField.getDeclaringIClass(), iField.getName(), iField.getType(), iField.isStatic());
   }

   private void getfield(Java.Locatable locatable, IClass declaringIClass, String fieldName, IClass fieldType, boolean statiC) {
      this.addLineNumberOffset(locatable);
      if (statiC) {
         this.write(178);
      } else {
         this.write(180);
         this.getCodeContext().popOperand();
      }

      this.writeConstantFieldrefInfo(declaringIClass, fieldName, fieldType);
      this.getCodeContext().pushOperand(fieldType.getDescriptor());
   }

   private void gotO(Java.Locatable locatable, CodeContext.Offset dst) {
      assert dst instanceof CodeContext.BasicBlock;

      this.getCodeContext().writeBranch(167, dst);
      this.getCodeContext().currentInserter().setStackMap((StackMap)null);
   }

   private void if_acmpxx(Java.Locatable locatable, int opIdx, CodeContext.Offset dst) {
      assert opIdx == 0 || opIdx == 1 : opIdx;

      this.addLineNumberOffset(locatable);
      this.getCodeContext().writeBranch(165 + opIdx, dst);
      this.getCodeContext().popReferenceOperand();
      this.getCodeContext().popReferenceOperand();
      dst.setStackMap(this.getCodeContext().currentInserter().getStackMap());
   }

   private void if_icmpxx(Java.Locatable locatable, int opIdx, CodeContext.Offset dst) {
      assert opIdx >= 0 && opIdx <= 5;

      assert dst instanceof CodeContext.BasicBlock;

      this.addLineNumberOffset(locatable);
      this.getCodeContext().writeBranch(159 + opIdx, dst);
      this.getCodeContext().popIntOperand();
      this.getCodeContext().popIntOperand();
      dst.setStackMap(this.getCodeContext().currentInserter().getStackMap());
   }

   private void ifnonnull(Java.Locatable locatable, CodeContext.Offset dst) {
      this.getCodeContext().writeBranch(199, dst);
      this.getCodeContext().popReferenceOperand();
      dst.setStackMap(this.getCodeContext().currentInserter().getStackMap());
   }

   private void ifnull(Java.Locatable locatable, CodeContext.Offset dst) {
      this.getCodeContext().writeBranch(198, dst);
      this.getCodeContext().popReferenceOperand();
      dst.setStackMap(this.getCodeContext().currentInserter().getStackMap());
   }

   private void ifxx(Java.Locatable locatable, int opIdx, CodeContext.Offset dst) {
      assert opIdx >= 0 && opIdx <= 5;

      this.addLineNumberOffset(locatable);
      this.getCodeContext().writeBranch(153 + opIdx, dst);
      this.getCodeContext().popIntOperand();
      dst.setStackMap(this.getCodeContext().currentInserter().getStackMap());
   }

   private void iinc(Java.Locatable locatable, Java.LocalVariable lv, String operator) {
      this.addLineNumberOffset(locatable);
      if (lv.getSlotIndex() > 255) {
         this.write(196);
         this.write(132);
         this.writeShort(lv.getSlotIndex());
         this.writeShort(operator == "++" ? 1 : -1);
      } else {
         this.write(132);
         this.writeByte(lv.getSlotIndex());
         this.writeByte(operator == "++" ? 1 : -1);
      }

   }

   private void instanceoF(Java.Locatable locatable, IType rhsType) {
      this.addLineNumberOffset(locatable);
      this.getCodeContext().popReferenceOperand();
      this.write(193);
      this.writeConstantClassInfo(rawTypeOf(rhsType));
      this.getCodeContext().pushIntOperand();
   }

   private void invoke(Java.Locatable locatable, int opcode, IClass declaringIClass, String methodName, MethodDescriptor methodDescriptor, boolean useInterfaceMethodRef) {
      this.addLineNumberOffset(locatable);

      for(int i = methodDescriptor.parameterFds.length - 1; i >= 0; --i) {
         this.getCodeContext().popOperandAssignableTo(methodDescriptor.parameterFds[i]);
      }

      if (opcode == 185 || opcode == 183 || opcode == 182) {
         this.getCodeContext().popObjectOrUninitializedOrUninitializedThisOperand();
      }

      this.write(opcode);
      if (useInterfaceMethodRef) {
         this.writeConstantInterfaceMethodrefInfo(declaringIClass, methodName, methodDescriptor);
      } else {
         this.writeConstantMethodrefInfo(declaringIClass, methodName, methodDescriptor);
      }

      switch (opcode) {
         case 182:
         case 183:
         case 184:
            break;
         case 185:
            int count = 1;

            for(String pfd : methodDescriptor.parameterFds) {
               count += Descriptor.size(pfd);
            }

            this.writeByte(count);
            this.writeByte(0);
            break;
         case 186:
            this.writeByte(0);
            this.writeByte(0);
            break;
         default:
            throw new AssertionError(opcode);
      }

      if (!methodDescriptor.returnFd.equals("V")) {
         this.getCodeContext().pushOperand(methodDescriptor.returnFd);
      }

   }

   private void l2i(Java.Locatable locatable) {
      this.addLineNumberOffset(locatable);
      this.getCodeContext().popLongOperand();
      this.write(136);
      this.getCodeContext().pushIntOperand();
   }

   private IType load(Java.Locatable locatable, Java.LocalVariable localVariable) {
      this.load(locatable, localVariable.type, localVariable.getSlotIndex());
      return localVariable.type;
   }

   private void load(Java.Locatable locatable, IType localVariableType, int localVariableIndex) {
      assert localVariableIndex >= 0 && localVariableIndex <= 65535;

      this.addLineNumberOffset(locatable);
      IClass rawClass = rawTypeOf(localVariableType);
      if (localVariableIndex <= 3) {
         this.write(26 + 4 * ilfda(rawClass) + localVariableIndex);
      } else if (localVariableIndex <= 255) {
         this.write(21 + ilfda(rawClass));
         this.write(localVariableIndex);
      } else {
         this.write(196);
         this.write(21 + ilfda(rawClass));
         this.writeUnsignedShort(localVariableIndex);
      }

      ClassFile.StackMapTableAttribute.VerificationTypeInfo vti = this.getLocalVariableTypeInfo((short)localVariableIndex);
      this.getCodeContext().pushOperand(vti);
   }

   private void lookupswitch(Java.Locatable locatable, SortedMap caseLabelMap, CodeContext.Offset defaultLabelOffset) {
      CodeContext.Offset switchOffset = this.getCodeContext().newOffset();
      this.addLineNumberOffset(locatable);
      this.getCodeContext().popIntOperand();
      StackMap smAtCase = this.getCodeContext().currentInserter().getStackMap();
      this.write(171);
      (new Java.Padder(this.getCodeContext())).set();
      defaultLabelOffset.setStackMap(smAtCase);
      this.writeOffset(switchOffset, defaultLabelOffset);
      this.writeInt(caseLabelMap.size());

      for(Map.Entry me : caseLabelMap.entrySet()) {
         Integer match = (Integer)me.getKey();
         CodeContext.Offset offset = (CodeContext.Offset)me.getValue();
         offset.setStackMap(smAtCase);
         this.writeInt(match);
         this.writeOffset(switchOffset, offset);
      }

   }

   private void monitorenter(Java.Locatable locatable) {
      this.addLineNumberOffset(locatable);
      this.getCodeContext().popReferenceOperand();
      this.write(194);
   }

   private void monitorexit(Java.Locatable locatable) {
      this.addLineNumberOffset(locatable);
      this.getCodeContext().popReferenceOperand();
      this.write(195);
   }

   private void mulDivRemAddSub(Java.Locatable locatable, String operator) {
      ClassFile.StackMapTableAttribute.VerificationTypeInfo operand2 = this.getCodeContext().popOperand();
      ClassFile.StackMapTableAttribute.VerificationTypeInfo operand1 = this.getCodeContext().popOperand();

      assert operand1 == operand2 : operand1 + " vs. " + operand2;

      int opcode = (operator == "*" ? 104 : (operator == "/" ? 108 : (operator == "%" ? 112 : (operator == "+" ? 96 : (operator == "-" ? 100 : Integer.MAX_VALUE))))) + ilfd(operand1);
      this.addLineNumberOffset(locatable);
      this.write(opcode);
      this.getCodeContext().pushOperand(operand1);
   }

   private void multianewarray(Java.Locatable locatable, int dimExprCount, int dims, IType componentType) {
      IClass arrayType = this.iClassLoader.getArrayIClass(rawTypeOf(componentType), dimExprCount + dims);
      this.addLineNumberOffset(locatable);

      for(int i = 0; i < dimExprCount; ++i) {
         this.getCodeContext().popIntOperand();
      }

      this.write(197);
      this.writeConstantClassInfo(arrayType);
      this.writeByte(dimExprCount);
      this.getCodeContext().pushObjectOperand(arrayType.getDescriptor());
   }

   private void neg(Java.Locatable locatable, IClass operandType) {
      this.addLineNumberOffset(locatable);
      this.write(116 + ilfd((IType)operandType));
   }

   private void neW(Java.Locatable locatable, IType iType) {
      this.addLineNumberOffset(locatable);
      this.getCodeContext().pushUninitializedOperand();
      this.write(187);
      this.writeConstantClassInfo(rawTypeOf(iType));
   }

   private void newarray(Java.Locatable locatable, IType componentType) {
      IClass arrayType = this.iClassLoader.getArrayIClass(rawTypeOf(componentType));
      this.addLineNumberOffset(locatable);
      this.getCodeContext().popIntOperand();
      this.write(188);
      this.writeByte(componentType == IClass.BOOLEAN ? 4 : (componentType == IClass.CHAR ? 5 : (componentType == IClass.FLOAT ? 6 : (componentType == IClass.DOUBLE ? 7 : (componentType == IClass.BYTE ? 8 : (componentType == IClass.SHORT ? 9 : (componentType == IClass.INT ? 10 : (componentType == IClass.LONG ? 11 : -1))))))));
      this.getCodeContext().pushObjectOperand(arrayType.getDescriptor());
   }

   private void pop(Java.Locatable locatable, IType type) {
      if (type != IClass.VOID) {
         this.addLineNumberOffset(locatable);
         this.write(type != IClass.LONG && type != IClass.DOUBLE ? 87 : 88);
         this.getCodeContext().popOperand(rawTypeOf(type).getDescriptor());
      }
   }

   private void putfield(Java.Locatable locatable, IClass.IField iField) throws CompileException {
      this.addLineNumberOffset(locatable);
      this.getCodeContext().popOperand();
      if (iField.isStatic()) {
         this.write(179);
      } else {
         this.write(181);
         this.getCodeContext().popOperand();
      }

      this.writeConstantFieldrefInfo(iField.getDeclaringIClass(), iField.getName(), iField.getType());
   }

   private void returN(Java.Locatable locatable) {
      this.addLineNumberOffset(locatable);
      this.write(177);
      this.codeContext.currentInserter().setStackMap((StackMap)null);
   }

   private void shift(Java.Locatable locatable, String operator) {
      this.getCodeContext().popIntOperand();
      ClassFile.StackMapTableAttribute.VerificationTypeInfo operand1 = this.getCodeContext().popIntOrLongOperand();
      int iopcode = operator == "<<" ? 120 : (operator == ">>" ? 122 : (operator == ">>>" ? 124 : Integer.MAX_VALUE));
      int opcode = iopcode + il(operand1);
      this.addLineNumberOffset(locatable);
      this.write(opcode);
      this.getCodeContext().pushOperand(operand1);
   }

   private void store(Java.Locatable locatable, Java.LocalVariable localVariable) {
      this.store(locatable, localVariable.type, localVariable.getSlotIndex());
   }

   private void store(Java.Locatable locatable, IType lvType, short lvIndex) {
      this.addLineNumberOffset(locatable);
      if (lvIndex <= 3) {
         this.write(59 + 4 * ilfda(lvType) + lvIndex);
      } else if (lvIndex <= 255) {
         this.write(54 + ilfda(lvType));
         this.write(lvIndex);
      } else {
         this.write(196);
         this.write(54 + ilfda(lvType));
         this.writeUnsignedShort(lvIndex);
      }

      this.getCodeContext().popOperand();
      this.updateLocalVariableInCurrentStackMap(lvIndex, this.verificationTypeInfo(lvType));
   }

   private void sub(Java.Locatable locatable) {
      this.mulDivRemAddSub(locatable, "-");
   }

   private void swap(Java.Locatable locatable) {
      ClassFile.StackMapTableAttribute.VerificationTypeInfo topOperand = this.getCodeContext().popOperand();
      ClassFile.StackMapTableAttribute.VerificationTypeInfo topButOneOperand = this.getCodeContext().popOperand();
      this.addLineNumberOffset(locatable);
      this.write(95);
      this.getCodeContext().pushOperand(topOperand);
      this.getCodeContext().pushOperand(topButOneOperand);
   }

   private void tableswitch(Java.Locatable locatable, SortedMap caseLabelMap, CodeContext.Offset defaultLabelOffset) {
      assert defaultLabelOffset instanceof CodeContext.BasicBlock;

      CodeContext.Offset switchOffset = this.getCodeContext().newOffset();
      int low = (Integer)caseLabelMap.firstKey();
      int high = (Integer)caseLabelMap.lastKey();
      this.addLineNumberOffset(locatable);
      this.getCodeContext().popIntOperand();
      StackMap smAtCase = this.getCodeContext().currentInserter().getStackMap();
      this.write(170);
      (new Java.Padder(this.getCodeContext())).set();
      defaultLabelOffset.setStackMap(smAtCase);
      this.writeOffset(switchOffset, defaultLabelOffset);
      this.writeInt(low);
      this.writeInt(high);
      int cur = low;

      for(Map.Entry me : caseLabelMap.entrySet()) {
         int caseLabelValue = (Integer)me.getKey();
         CodeContext.Offset caseLabelOffset = (CodeContext.Offset)me.getValue();

         assert caseLabelOffset instanceof CodeContext.BasicBlock;

         caseLabelOffset.setStackMap(smAtCase);

         while(cur < caseLabelValue) {
            this.writeOffset(switchOffset, defaultLabelOffset);
            ++cur;
         }

         this.writeOffset(switchOffset, caseLabelOffset);
         ++cur;
      }

   }

   private void xaload(Java.Locatable locatable, IType componentType) {
      this.addLineNumberOffset(locatable);
      IClass rawComponentType = rawTypeOf(componentType);
      this.getCodeContext().popIntOperand();
      this.getCodeContext().popReferenceOperand();
      this.write(46 + ilfdabcs(rawComponentType));
      this.getCodeContext().pushOperand(rawComponentType.getDescriptor());
   }

   private void xor(Java.Locatable locatable, int opcode) {
      if (opcode != 130 && opcode != 131) {
         throw new AssertionError(opcode);
      } else {
         this.addLineNumberOffset(locatable);
         this.write(opcode);
         this.getCodeContext().popOperand();
      }
   }

   private void xreturn(Java.Locatable locatable, IType returnType) {
      this.addLineNumberOffset(locatable);
      this.write(172 + ilfda(returnType));
      this.codeContext.currentInserter().setStackMap((StackMap)null);
   }

   private static int ilfd(IType t) {
      if (t != IClass.BYTE && t != IClass.CHAR && t != IClass.INT && t != IClass.SHORT && t != IClass.BOOLEAN) {
         if (t == IClass.LONG) {
            return 1;
         } else if (t == IClass.FLOAT) {
            return 2;
         } else if (t == IClass.DOUBLE) {
            return 3;
         } else {
            throw new InternalCompilerException("Unexpected type \"" + t + "\"");
         }
      } else {
         return 0;
      }
   }

   private static int ilfd(ClassFile.StackMapTableAttribute.VerificationTypeInfo vti) {
      if (vti == ClassFile.StackMapTableAttribute.INTEGER_VARIABLE_INFO) {
         return 0;
      } else if (vti == ClassFile.StackMapTableAttribute.LONG_VARIABLE_INFO) {
         return 1;
      } else if (vti == ClassFile.StackMapTableAttribute.FLOAT_VARIABLE_INFO) {
         return 2;
      } else if (vti == ClassFile.StackMapTableAttribute.DOUBLE_VARIABLE_INFO) {
         return 3;
      } else {
         throw new InternalCompilerException("Unexpected type \"" + vti + "\"");
      }
   }

   private static int ilfda(IType t) {
      return !isPrimitive(t) ? 4 : ilfd(t);
   }

   private static int il(ClassFile.StackMapTableAttribute.VerificationTypeInfo vti) {
      if (vti == ClassFile.StackMapTableAttribute.INTEGER_VARIABLE_INFO) {
         return 0;
      } else if (vti == ClassFile.StackMapTableAttribute.LONG_VARIABLE_INFO) {
         return 1;
      } else {
         throw new AssertionError(vti);
      }
   }

   private static int ilfdabcs(IClass t) {
      if (t == IClass.INT) {
         return 0;
      } else if (t == IClass.LONG) {
         return 1;
      } else if (t == IClass.FLOAT) {
         return 2;
      } else if (t == IClass.DOUBLE) {
         return 3;
      } else if (!t.isPrimitive()) {
         return 4;
      } else if (t == IClass.BOOLEAN) {
         return 5;
      } else if (t == IClass.BYTE) {
         return 5;
      } else if (t == IClass.CHAR) {
         return 6;
      } else if (t == IClass.SHORT) {
         return 7;
      } else {
         throw new InternalCompilerException("Unexpected type \"" + t + "\"");
      }
   }

   @Nullable
   private IClass.IField findIField(IClass iClass, String name, Location location) throws CompileException {
      IClass.IField f = iClass.getDeclaredIField(name);
      if (f != null) {
         return f;
      } else {
         IClass superclass = iClass.getSuperclass();
         if (superclass != null) {
            f = this.findIField(superclass, name, location);
         }

         superclass = iClass.getInterfaces();

         for(IClass iF : superclass) {
            IClass.IField f2 = this.findIField(iF, name, location);
            if (f2 != null) {
               if (f != null) {
                  throw new CompileException("Access to field \"" + name + "\" is ambiguous - both \"" + f.getDeclaringIClass() + "\" and \"" + f2.getDeclaringIClass() + "\" declare it", location);
               }

               f = f2;
            }
         }

         return f;
      }
   }

   @Nullable
   private IClass findMemberType(IType iType, String name, @Nullable Java.TypeArgument[] typeArguments, Location location) throws CompileException {
      IClass[] types = rawTypeOf(iType).findMemberType(name);
      if (types.length == 0) {
         return null;
      } else if (types.length == 1) {
         return types[0];
      } else {
         StringBuilder sb = (new StringBuilder("Type \"")).append(name).append("\" is ambiguous: ").append(types[0]);

         for(int i = 1; i < types.length; ++i) {
            sb.append(" vs. ").append(types[i].toString());
         }

         this.compileError(sb.toString(), location);
         return types[0];
      }
   }

   @Nullable
   public IClass findClass(String className) {
      Java.AbstractCompilationUnit acu = this.abstractCompilationUnit;
      if (!(acu instanceof Java.CompilationUnit)) {
         return null;
      } else {
         Java.CompilationUnit cu = (Java.CompilationUnit)acu;
         Java.PackageDeclaration opd = cu.packageDeclaration;
         if (opd != null) {
            String packageName = opd.packageName;
            if (!className.startsWith(packageName + '.')) {
               return null;
            }

            className = className.substring(packageName.length() + 1);
         }

         Java.TypeDeclaration td = cu.getPackageMemberTypeDeclaration(className);
         if (td == null) {
            int idx = className.indexOf(36);
            if (idx == -1) {
               return null;
            }

            StringTokenizer st = new StringTokenizer(className, "$");
            td = cu.getPackageMemberTypeDeclaration(st.nextToken());
            if (td == null) {
               return null;
            }

            while(st.hasMoreTokens()) {
               td = td.getMemberTypeDeclaration(st.nextToken());
               if (td == null) {
                  return null;
               }
            }
         }

         return this.resolve(td);
      }
   }

   private void compileError(String message) throws CompileException {
      this.compileError(message, (Location)null);
   }

   private void compileError(String message, @Nullable Location location) throws CompileException {
      ++this.compileErrorCount;
      if (this.compileErrorHandler != null) {
         this.compileErrorHandler.handleError(message, location);
      } else {
         throw new CompileException(message, location);
      }
   }

   private void warning(String handle, String message, @Nullable Location location) throws CompileException {
      if (this.warningHandler != null) {
         this.warningHandler.handleWarning(handle, message, location);
      }

   }

   public void setCompileErrorHandler(@Nullable ErrorHandler compileErrorHandler) {
      this.compileErrorHandler = compileErrorHandler;
   }

   public void setWarningHandler(@Nullable WarningHandler warningHandler) {
      this.warningHandler = warningHandler;
   }

   @Nullable
   private CodeContext replaceCodeContext(@Nullable CodeContext newCodeContext) {
      CodeContext oldCodeContext = this.codeContext;
      this.codeContext = newCodeContext;
      return oldCodeContext;
   }

   private void addLineNumberOffset(Java.Locatable locatable) {
      this.getCodeContext().addLineNumberOffset(locatable.getLocation().getLineNumber());
   }

   private void write(int v) {
      this.getCodeContext().write((byte)v);
   }

   private void writeByte(int v) {
      if (v > 255) {
         throw new InternalCompilerException("Byte value out of legal range");
      } else {
         this.getCodeContext().write((byte)v);
      }
   }

   private void writeShort(int v) {
      if (v >= -32768 && v <= 32767) {
         this.getCodeContext().write((byte)(v >> 8), (byte)v);
      } else {
         throw new InternalCompilerException("Short value out of legal range");
      }
   }

   private void writeUnsignedShort(int v) {
      if (v >= 0 && v <= 65535) {
         this.getCodeContext().write((byte)(v >> 8), (byte)v);
      } else {
         throw new InternalCompilerException("Unsigned short value out of legal range");
      }
   }

   private void writeInt(int v) {
      this.getCodeContext().write((byte)(v >> 24), (byte)(v >> 16), (byte)(v >> 8), (byte)v);
   }

   private void writeLdc(short constantPoolIndex) {
      if (constantPoolIndex >= 0 && constantPoolIndex <= 255) {
         this.write(18);
         this.write(constantPoolIndex);
      } else {
         this.write(19);
         this.writeShort(constantPoolIndex);
      }

   }

   private void writeLdc2(short constantPoolIndex) {
      this.write(20);
      this.getCodeContext().writeShort(constantPoolIndex);
   }

   private void invokeMethod(Java.Locatable locatable, IClass.IMethod iMethod) throws CompileException {
      if (this.getTargetVersion() < 8 && iMethod.isStatic() && iMethod.getDeclaringIClass().isInterface()) {
         this.compileError("Invocation of static interface methods only available for target version 8+", locatable.getLocation());
      }

      int opcode = iMethod.isStatic() ? 184 : (iMethod.getDeclaringIClass().isInterface() ? 185 : 182);
      boolean useInterfaceMethodref = iMethod.getDeclaringIClass().isInterface();
      this.invoke(locatable, opcode, iMethod.getDeclaringIClass(), iMethod.getName(), iMethod.getDescriptor(), useInterfaceMethodref);
   }

   private void invokeConstructor(Java.Locatable locatable, IClass.IConstructor iConstructor) throws CompileException {
      this.invoke(locatable, 183, iConstructor.getDeclaringIClass(), "<init>", iConstructor.getDescriptor(), false);
   }

   private void writeOffset(CodeContext.Offset src, CodeContext.Offset dst) {
      this.getCodeContext().writeOffset(src, dst);
   }

   private short addConstantStringInfo(String value) {
      return this.getCodeContext().getClassFile().addConstantStringInfo(value);
   }

   private short addConstantIntegerInfo(int value) {
      return this.getCodeContext().getClassFile().addConstantIntegerInfo(value);
   }

   private short addConstantLongInfo(long value) {
      return this.getCodeContext().getClassFile().addConstantLongInfo(value);
   }

   private short addConstantFloatInfo(float value) {
      return this.getCodeContext().getClassFile().addConstantFloatInfo(value);
   }

   private short addConstantDoubleInfo(double value) {
      return this.getCodeContext().getClassFile().addConstantDoubleInfo(value);
   }

   private short addConstantClassInfo(IClass iClass) {
      return this.getCodeContext().getClassFile().addConstantClassInfo(iClass.getDescriptor());
   }

   private short addConstantFieldrefInfo(IClass iClass, String fieldName, IClass fieldType) {
      return this.getCodeContext().getClassFile().addConstantFieldrefInfo(iClass.getDescriptor(), fieldName, fieldType.getDescriptor());
   }

   private short addConstantMethodrefInfo(IClass iClass, String methodName, String methodFd) {
      return this.getCodeContext().getClassFile().addConstantMethodrefInfo(iClass.getDescriptor(), methodName, methodFd);
   }

   private short addConstantInterfaceMethodrefInfo(IClass iClass, String methodName, String methodFd) {
      return this.getCodeContext().getClassFile().addConstantInterfaceMethodrefInfo(iClass.getDescriptor(), methodName, methodFd);
   }

   private void writeConstantClassInfo(IClass iClass) {
      this.writeShort(this.addConstantClassInfo(iClass));
   }

   private void writeConstantFieldrefInfo(IClass iClass, String fieldName, IClass fieldType) {
      this.writeShort(this.addConstantFieldrefInfo(iClass, fieldName, fieldType));
   }

   private void writeConstantMethodrefInfo(IClass iClass, String methodName, MethodDescriptor methodMd) {
      this.writeShort(this.addConstantMethodrefInfo(iClass, methodName, methodMd.toString()));
   }

   private void writeConstantInterfaceMethodrefInfo(IClass iClass, String methodName, MethodDescriptor methodMd) {
      this.writeShort(this.addConstantInterfaceMethodrefInfo(iClass, methodName, methodMd.toString()));
   }

   private CodeContext.Offset getWhereToBreak(Java.BreakableStatement bs) {
      CodeContext.Offset wtb = bs.whereToBreak;
      if (wtb != null) {
         StackMap saved = this.codeContext.currentInserter().getStackMap();
         wtb.setStackMap();
         this.codeContext.currentInserter().setStackMap(saved);
         return wtb;
      } else {
         CodeContext var10002 = this.getCodeContext();
         Objects.requireNonNull(var10002);
         wtb = var10002.new BasicBlock();
         wtb.setStackMap(this.codeContext.currentInserter().getStackMap());
         return bs.whereToBreak = wtb;
      }
   }

   private Java.TypeBodyDeclaration getDeclaringTypeBodyDeclaration(Java.QualifiedThisReference qtr) throws CompileException {
      if (qtr.declaringTypeBodyDeclaration != null) {
         return qtr.declaringTypeBodyDeclaration;
      } else {
         Java.Scope s;
         for(s = qtr.getEnclosingScope(); !(s instanceof Java.TypeBodyDeclaration); s = s.getEnclosingScope()) {
         }

         Java.TypeBodyDeclaration result = (Java.TypeBodyDeclaration)s;
         if (isStaticContext(result)) {
            this.compileError("No current instance available in static method", qtr.getLocation());
         }

         qtr.declaringClass = (Java.AbstractClassDeclaration)result.getDeclaringType();
         return qtr.declaringTypeBodyDeclaration = result;
      }
   }

   private Java.AbstractClassDeclaration getDeclaringClass(Java.QualifiedThisReference qtr) throws CompileException {
      if (qtr.declaringClass != null) {
         return qtr.declaringClass;
      } else {
         this.getDeclaringTypeBodyDeclaration(qtr);

         assert qtr.declaringClass != null;

         return qtr.declaringClass;
      }
   }

   private void referenceThis(Java.Locatable locatable, IClass currentIClass) {
      this.load(locatable, currentIClass, 0);
   }

   private IClass newArray(Java.Locatable locatable, int dimExprCount, int dims, IType componentType) {
      IClass rawComponentType = rawTypeOf(componentType);
      if (dimExprCount == 1 && dims == 0 && rawComponentType.isPrimitive()) {
         this.newarray(locatable, componentType);
      } else if (dimExprCount == 1) {
         this.anewarray(locatable, this.iClassLoader.getArrayIClass(rawComponentType, dims));
      } else {
         this.multianewarray(locatable, dimExprCount, dims, componentType);
      }

      return this.iClassLoader.getArrayIClass(rawComponentType, dimExprCount + dims);
   }

   private static String last(String[] sa) {
      if (sa.length == 0) {
         throw new IllegalArgumentException("SNO: Empty string array");
      } else {
         return sa[sa.length - 1];
      }
   }

   private static String[] allButLast(String[] sa) {
      if (sa.length == 0) {
         throw new IllegalArgumentException("SNO: Empty string array");
      } else {
         String[] tmp = new String[sa.length - 1];
         System.arraycopy(sa, 0, tmp, 0, tmp.length);
         return tmp;
      }
   }

   private static String[] concat(String[] sa, String s) {
      String[] tmp = new String[sa.length + 1];
      System.arraycopy(sa, 0, tmp, 0, sa.length);
      tmp[sa.length] = s;
      return tmp;
   }

   private static CompileException compileException(Java.Locatable locatable, String message) {
      return new CompileException(message, locatable.getLocation());
   }

   private static String unescape(String s, @Nullable Location location) throws CompileException {
      int i = s.indexOf(92);
      if (i == -1) {
         return s;
      } else {
         StringBuilder sb = (new StringBuilder()).append(s, 0, i);

         while(i < s.length()) {
            char c = s.charAt(i++);
            if (c != '\\') {
               sb.append(c);
            } else {
               c = s.charAt(i++);
               int idx = "btnfr\"'\\".indexOf(c);
               if (idx != -1) {
                  sb.append("\b\t\n\f\r\"'\\".charAt(idx));
               } else {
                  idx = Character.digit(c, 8);
                  if (idx == -1) {
                     throw new CompileException("Invalid escape sequence \"\\" + c + "\"", location);
                  }

                  if (i < s.length()) {
                     c = s.charAt(i);
                     int secondDigit = Character.digit(c, 8);
                     if (secondDigit != -1) {
                        idx = 8 * idx + secondDigit;
                        ++i;
                        if (i < s.length() && idx <= 31) {
                           c = s.charAt(i);
                           int thirdDigit = Character.digit(c, 8);
                           if (thirdDigit != -1) {
                              idx = 8 * idx + thirdDigit;
                              ++i;
                           }
                        }
                     }
                  }

                  sb.append((char)idx);
               }
            }
         }

         return sb.toString();
      }
   }

   private short accessFlags(Java.Modifier[] modifiers) throws CompileException {
      int result = 0;

      for(Java.Modifier m : modifiers) {
         if (m instanceof Java.AccessModifier) {
            String kw = ((Java.AccessModifier)m).keyword;
            if ("public".equals(kw)) {
               result |= 1;
            } else if ("private".equals(kw)) {
               result |= 2;
            } else if ("protected".equals(kw)) {
               result |= 4;
            } else if ("static".equals(kw)) {
               result |= 8;
            } else if ("final".equals(kw)) {
               result |= 16;
            } else if ("synchronized".equals(kw)) {
               result |= 32;
            } else if ("volatile".equals(kw)) {
               result |= 64;
            } else if ("transient".equals(kw)) {
               result |= 128;
            } else if ("native".equals(kw)) {
               result |= 256;
            } else if ("abstract".equals(kw)) {
               result |= 1024;
            } else if ("strictfp".equals(kw)) {
               result |= 2048;
            } else if (!"default".equals(kw)) {
               this.compileError("Invalid modifier \"" + kw + "\"");
            }
         }
      }

      return (short)result;
   }

   private static Java.Modifier[] accessModifiers(Location location, String... keywords) {
      Java.Modifier[] result = new Java.Modifier[keywords.length];

      for(int i = 0; i < keywords.length; ++i) {
         result[i] = new Java.AccessModifier(keywords[i], location);
      }

      return result;
   }

   private static short changeAccessibility(short accessFlags, short newAccessibility) {
      return (short)(accessFlags & -8 | newAccessibility);
   }

   private ClassFile.StackMapTableAttribute.VerificationTypeInfo getLocalVariableTypeInfo(short lvIndex) {
      StackMap cism = this.getCodeContext().currentInserter().getStackMap();

      assert cism != null;

      int nextLvIndex = 0;

      for(ClassFile.StackMapTableAttribute.VerificationTypeInfo vti : cism.locals()) {
         if (nextLvIndex == lvIndex) {
            return vti;
         }

         nextLvIndex += vti.category();
      }

      throw new InternalCompilerException("Invalid local variable index " + lvIndex);
   }

   private void updateLocalVariableInCurrentStackMap(short lvIndex, ClassFile.StackMapTableAttribute.VerificationTypeInfo vti) {
      CodeContext.Inserter ci = this.getCodeContext().currentInserter();
      ClassFile.StackMapTableAttribute.VerificationTypeInfo[] locals = ci.getStackMap().locals();
      int nextLvIndex = 0;

      for(int i = 0; i < locals.length; ++i) {
         ClassFile.StackMapTableAttribute.VerificationTypeInfo vti2 = locals[i];
         if (nextLvIndex == lvIndex) {
            if (vti.equals(vti2)) {
               return;
            }

            if (vti2.category() == vti.category()) {
               locals[i] = vti;
            } else if (vti2.category() == 1 && vti.category() == 2) {
               assert locals[i + 1].category() == 1;

               locals[i] = vti;
               System.arraycopy(locals, i + 2, locals, i + 1, locals.length - i - 2);
               locals = (ClassFile.StackMapTableAttribute.VerificationTypeInfo[])Arrays.copyOf(locals, locals.length - 1);
            } else {
               if (vti2.category() != 2 || vti.category() != 1) {
                  throw new AssertionError(vti2.category() + " vs. " + vti.category());
               }

               locals = (ClassFile.StackMapTableAttribute.VerificationTypeInfo[])Arrays.copyOf(locals, locals.length + 1);
               System.arraycopy(locals, i + 1, locals, i + 2, locals.length - i - 2);
               locals[i] = vti;
               locals[i + 1] = ClassFile.StackMapTableAttribute.TOP_VARIABLE_INFO;
            }

            ci.setStackMap(new StackMap(locals, ci.getStackMap().operands()));
            return;
         }

         nextLvIndex += vti2.category();
      }

      assert nextLvIndex <= lvIndex;

      while(nextLvIndex < lvIndex) {
         ci.setStackMap(ci.getStackMap().pushLocal(ClassFile.StackMapTableAttribute.TOP_VARIABLE_INFO));
         ++nextLvIndex;
      }

      ci.setStackMap(ci.getStackMap().pushLocal(vti));
   }

   static {
      NOT_CONSTANT = IClass.NOT_CONSTANT;
      TWO_E_31_INTEGER = Pattern.compile("2_*1_*4_*7_*4_*8_*3_*6_*4_*8");
      TWO_E_63_LONG = Pattern.compile("9_*2_*2_*3_*3_*7_*2_*0_*3_*6_*8_*5_*4_*7_*7_*5_*8_*0_*8[lL]");
      PRIMITIVE_WIDENING_CONVERSIONS = new HashMap();
      fillConversionMap(new Object[]{new int[0], "BS", "BI", "SI", "CI", new int[]{133}, "BJ", "SJ", "CJ", "IJ", new int[]{134}, "BF", "SF", "CF", "IF", new int[]{137}, "JF", new int[]{135}, "BD", "SD", "CD", "ID", new int[]{138}, "JD", new int[]{141}, "FD"}, PRIMITIVE_WIDENING_CONVERSIONS);
      PRIMITIVE_NARROWING_CONVERSIONS = new HashMap();
      fillConversionMap(new Object[]{new int[0], "BC", "SC", "CS", new int[]{145}, "SB", "CB", "IB", new int[]{147}, "IS", new int[]{146}, "IC", new int[]{136, 145}, "JB", new int[]{136, 147}, "JS", "JC", new int[]{136}, "JI", new int[]{139, 145}, "FB", new int[]{139, 147}, "FS", "FC", new int[]{139}, "FI", new int[]{140}, "FJ", new int[]{142, 145}, "DB", new int[]{142, 147}, "DS", "DC", new int[]{142}, "DI", new int[]{143}, "DJ", new int[]{144}, "DF"}, PRIMITIVE_NARROWING_CONVERSIONS);
   }

   private static enum SwitchKind {
      INT,
      ENUM,
      STRING;

      // $FF: synthetic method
      private static SwitchKind[] $values() {
         return new SwitchKind[]{INT, ENUM, STRING};
      }
   }

   public static class SimpleIField extends IClass.IField {
      private final String name;
      private final IClass type;

      public SimpleIField(IClass declaringIClass, String name, IClass type) {
         Objects.requireNonNull(declaringIClass);
         super();
         this.name = name;
         this.type = type;
      }

      public Object getConstantValue() {
         return UnitCompiler.NOT_CONSTANT;
      }

      public String getName() {
         return this.name;
      }

      public IClass getType() {
         return this.type;
      }

      public boolean isStatic() {
         return false;
      }

      public Access getAccess() {
         return Access.DEFAULT;
      }

      public IClass.IAnnotation[] getAnnotations() {
         return new IClass.IAnnotation[0];
      }
   }

   public interface ClassFileConsumer {
      void consume(ClassFile var1) throws IOException;
   }

   interface Compilable {
      void compile() throws CompileException;
   }

   interface Compilable2 {
      boolean compile() throws CompileException;
   }
}
