package org.codehaus.janino.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.IType;
import org.codehaus.janino.Java;
import org.codehaus.janino.Visitor;

public class DeepCopier {
   private final Visitor.AbstractCompilationUnitVisitor abstractCompilationUnitCopier = new Visitor.AbstractCompilationUnitVisitor() {
      @Nullable
      public Java.AbstractCompilationUnit visitCompilationUnit(Java.CompilationUnit cu) throws CompileException {
         return DeepCopier.this.copyCompilationUnit(cu);
      }

      @Nullable
      public Java.AbstractCompilationUnit visitModularCompilationUnit(Java.ModularCompilationUnit mcu) throws CompileException {
         return DeepCopier.this.copyModularCompilationUnit(mcu);
      }
   };
   private final Visitor.ImportVisitor importCopier = new Visitor.ImportVisitor() {
      @Nullable
      public Java.AbstractCompilationUnit.ImportDeclaration visitSingleTypeImportDeclaration(Java.AbstractCompilationUnit.SingleTypeImportDeclaration stid) throws CompileException {
         return DeepCopier.this.copySingleTypeImportDeclaration(stid);
      }

      @Nullable
      public Java.AbstractCompilationUnit.ImportDeclaration visitTypeImportOnDemandDeclaration(Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration tiodd) throws CompileException {
         return DeepCopier.this.copyTypeImportOnDemandDeclaration(tiodd);
      }

      @Nullable
      public Java.AbstractCompilationUnit.ImportDeclaration visitSingleStaticImportDeclaration(Java.AbstractCompilationUnit.SingleStaticImportDeclaration ssid) throws CompileException {
         return DeepCopier.this.copySingleStaticImportDeclaration(ssid);
      }

      @Nullable
      public Java.AbstractCompilationUnit.ImportDeclaration visitStaticImportOnDemandDeclaration(Java.AbstractCompilationUnit.StaticImportOnDemandDeclaration siodd) throws CompileException {
         return DeepCopier.this.copyStaticImportOnDemandDeclaration(siodd);
      }
   };
   private final Visitor.TypeDeclarationVisitor typeDeclarationCopier = new Visitor.TypeDeclarationVisitor() {
      @Nullable
      public Java.TypeDeclaration visitAnonymousClassDeclaration(Java.AnonymousClassDeclaration acd) throws CompileException {
         return DeepCopier.this.copyAnonymousClassDeclaration(acd);
      }

      @Nullable
      public Java.TypeDeclaration visitLocalClassDeclaration(Java.LocalClassDeclaration lcd) throws CompileException {
         return DeepCopier.this.copyLocalClassDeclaration(lcd);
      }

      @Nullable
      public Java.TypeDeclaration visitPackageMemberClassDeclaration(Java.PackageMemberClassDeclaration pmcd) throws CompileException {
         return DeepCopier.this.copyPackageMemberClassDeclaration(pmcd);
      }

      @Nullable
      public Java.TypeDeclaration visitPackageMemberInterfaceDeclaration(Java.PackageMemberInterfaceDeclaration pmid) throws CompileException {
         return DeepCopier.this.copyPackageMemberInterfaceDeclaration(pmid);
      }

      @Nullable
      public Java.TypeDeclaration visitEnumConstant(Java.EnumConstant ec) throws CompileException {
         return DeepCopier.this.copyEnumConstant(ec);
      }

      @Nullable
      public Java.TypeDeclaration visitPackageMemberEnumDeclaration(Java.PackageMemberEnumDeclaration pmed) throws CompileException {
         return DeepCopier.this.copyPackageMemberEnumDeclaration(pmed);
      }

      @Nullable
      public Java.TypeDeclaration visitMemberAnnotationTypeDeclaration(Java.MemberAnnotationTypeDeclaration matd) throws CompileException {
         return DeepCopier.this.copyMemberAnnotationTypeDeclaration(matd);
      }

      @Nullable
      public Java.TypeDeclaration visitPackageMemberAnnotationTypeDeclaration(Java.PackageMemberAnnotationTypeDeclaration pmatd) throws CompileException {
         return DeepCopier.this.copyPackageMemberAnnotationTypeDeclaration(pmatd);
      }

      @Nullable
      public Java.TypeDeclaration visitMemberEnumDeclaration(Java.MemberEnumDeclaration med) throws CompileException {
         return DeepCopier.this.copyMemberEnumDeclaration(med);
      }

      @Nullable
      public Java.TypeDeclaration visitMemberInterfaceDeclaration(Java.MemberInterfaceDeclaration mid) throws CompileException {
         return DeepCopier.this.copyMemberInterfaceDeclaration(mid);
      }

      @Nullable
      public Java.TypeDeclaration visitMemberClassDeclaration(Java.MemberClassDeclaration mcd) throws CompileException {
         return DeepCopier.this.copyMemberClassDeclaration(mcd);
      }
   };
   private final Visitor.ArrayInitializerOrRvalueVisitor arrayInitializerOrRvalueCopier = new Visitor.ArrayInitializerOrRvalueVisitor() {
      public Java.ArrayInitializerOrRvalue visitArrayInitializer(Java.ArrayInitializer ai) throws CompileException {
         return DeepCopier.this.copyArrayInitializer(ai);
      }

      public Java.ArrayInitializerOrRvalue visitRvalue(Java.Rvalue rvalue) throws CompileException {
         return DeepCopier.this.copyRvalue(rvalue);
      }
   };
   private final Visitor.RvalueVisitor rvalueCopier = new Visitor.RvalueVisitor() {
      public Java.Rvalue visitLvalue(Java.Lvalue lv) throws CompileException {
         return DeepCopier.this.copyLvalue(lv);
      }

      public Java.Rvalue visitArrayLength(Java.ArrayLength al) throws CompileException {
         return DeepCopier.this.copyArrayLength(al);
      }

      public Java.Rvalue visitAssignment(Java.Assignment a) throws CompileException {
         return DeepCopier.this.copyAssignment(a);
      }

      public Java.Rvalue visitUnaryOperation(Java.UnaryOperation uo) throws CompileException {
         return DeepCopier.this.copyUnaryOperation(uo);
      }

      public Java.Rvalue visitBinaryOperation(Java.BinaryOperation bo) throws CompileException {
         return DeepCopier.this.copyBinaryOperation(bo);
      }

      public Java.Rvalue visitCast(Java.Cast c) throws CompileException {
         return DeepCopier.this.copyCast(c);
      }

      public Java.Rvalue visitClassLiteral(Java.ClassLiteral cl) throws CompileException {
         return DeepCopier.this.copyClassLiteral(cl);
      }

      public Java.Rvalue visitConditionalExpression(Java.ConditionalExpression ce) throws CompileException {
         return DeepCopier.this.copyConditionalExpression(ce);
      }

      public Java.Rvalue visitCrement(Java.Crement c) throws CompileException {
         return DeepCopier.this.copyCrement(c);
      }

      public Java.Rvalue visitInstanceof(Java.Instanceof io) throws CompileException {
         return DeepCopier.this.copyInstanceof(io);
      }

      public Java.Rvalue visitMethodInvocation(Java.MethodInvocation mi) throws CompileException {
         return DeepCopier.this.copyMethodInvocation(mi);
      }

      public Java.Rvalue visitSuperclassMethodInvocation(Java.SuperclassMethodInvocation smi) throws CompileException {
         return DeepCopier.this.copySuperclassMethodInvocation(smi);
      }

      public Java.Rvalue visitIntegerLiteral(Java.IntegerLiteral il) throws CompileException {
         return DeepCopier.this.copyIntegerLiteral(il);
      }

      public Java.Rvalue visitFloatingPointLiteral(Java.FloatingPointLiteral fpl) throws CompileException {
         return DeepCopier.this.copyFloatingPointLiteral(fpl);
      }

      public Java.Rvalue visitBooleanLiteral(Java.BooleanLiteral bl) throws CompileException {
         return DeepCopier.this.copyBooleanLiteral(bl);
      }

      public Java.Rvalue visitCharacterLiteral(Java.CharacterLiteral cl) throws CompileException {
         return DeepCopier.this.copyCharacterLiteral(cl);
      }

      public Java.Rvalue visitStringLiteral(Java.StringLiteral sl) throws CompileException {
         return DeepCopier.this.copyStringLiteral(sl);
      }

      public Java.Rvalue visitNullLiteral(Java.NullLiteral nl) throws CompileException {
         return DeepCopier.this.copyNullLiteral(nl);
      }

      public Java.Rvalue visitSimpleConstant(Java.SimpleConstant sl) throws CompileException {
         return DeepCopier.this.copySimpleLiteral(sl);
      }

      public Java.Rvalue visitNewAnonymousClassInstance(Java.NewAnonymousClassInstance naci) throws CompileException {
         return DeepCopier.this.copyNewAnonymousClassInstance(naci);
      }

      public Java.Rvalue visitNewArray(Java.NewArray na) throws CompileException {
         return DeepCopier.this.copyNewArray(na);
      }

      public Java.Rvalue visitNewInitializedArray(Java.NewInitializedArray nia) throws CompileException {
         return DeepCopier.this.copyNewInitializedArray(nia);
      }

      public Java.Rvalue visitNewClassInstance(Java.NewClassInstance nci) throws CompileException {
         return DeepCopier.this.copyNewClassInstance(nci);
      }

      public Java.Rvalue visitParameterAccess(Java.ParameterAccess pa) throws CompileException {
         return DeepCopier.this.copyParameterAccess(pa);
      }

      public Java.Rvalue visitQualifiedThisReference(Java.QualifiedThisReference qtr) throws CompileException {
         return DeepCopier.this.copyQualifiedThisReference(qtr);
      }

      public Java.Rvalue visitThisReference(Java.ThisReference tr) throws CompileException {
         return DeepCopier.this.copyThisReference(tr);
      }

      public Java.Rvalue visitLambdaExpression(Java.LambdaExpression le) throws CompileException {
         return DeepCopier.this.copyLambdaExpression(le);
      }

      public Java.Rvalue visitMethodReference(Java.MethodReference mr) throws CompileException {
         return DeepCopier.this.copyMethodReference(mr);
      }

      public Java.Rvalue visitInstanceCreationReference(Java.ClassInstanceCreationReference cicr) throws CompileException {
         return DeepCopier.this.copyClassInstanceCreationReference(cicr);
      }

      public Java.Rvalue visitArrayCreationReference(Java.ArrayCreationReference acr) throws CompileException {
         return DeepCopier.this.copyArrayCreationReference(acr);
      }
   };
   private final Visitor.LvalueVisitor lvalueCopier = new Visitor.LvalueVisitor() {
      @Nullable
      public Java.Lvalue visitAmbiguousName(Java.AmbiguousName an) throws CompileException {
         return DeepCopier.this.copyAmbiguousName(an);
      }

      @Nullable
      public Java.Lvalue visitArrayAccessExpression(Java.ArrayAccessExpression aae) throws CompileException {
         return DeepCopier.this.copyArrayAccessExpression(aae);
      }

      @Nullable
      public Java.Lvalue visitFieldAccess(Java.FieldAccess fa) throws CompileException {
         return DeepCopier.this.copyFieldAccess(fa);
      }

      @Nullable
      public Java.Lvalue visitFieldAccessExpression(Java.FieldAccessExpression fae) throws CompileException {
         return DeepCopier.this.copyFieldAccessExpression(fae);
      }

      @Nullable
      public Java.Lvalue visitSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression scfae) throws CompileException {
         return DeepCopier.this.copySuperclassFieldAccessExpression(scfae);
      }

      @Nullable
      public Java.Lvalue visitLocalVariableAccess(Java.LocalVariableAccess lva) throws CompileException {
         return DeepCopier.this.copyLocalVariableAccess(lva);
      }

      @Nullable
      public Java.Lvalue visitParenthesizedExpression(Java.ParenthesizedExpression pe) throws CompileException {
         return DeepCopier.this.copyParenthesizedExpression(pe);
      }
   };
   private final Visitor.TypeBodyDeclarationVisitor typeBodyDeclarationCopier = new Visitor.TypeBodyDeclarationVisitor() {
      public Java.TypeBodyDeclaration visitFunctionDeclarator(Java.FunctionDeclarator fd) throws CompileException {
         return DeepCopier.this.copyFunctionDeclarator(fd);
      }

      public Java.TypeBodyDeclaration visitMemberAnnotationTypeDeclaration(Java.MemberAnnotationTypeDeclaration matd) throws CompileException {
         return DeepCopier.this.copyMemberAnnotationTypeDeclaration(matd);
      }

      public Java.TypeBodyDeclaration visitMemberInterfaceDeclaration(Java.MemberInterfaceDeclaration mid) throws CompileException {
         return DeepCopier.this.copyMemberInterfaceDeclaration(mid);
      }

      public Java.TypeBodyDeclaration visitMemberClassDeclaration(Java.MemberClassDeclaration mcd) throws CompileException {
         return DeepCopier.this.copyMemberClassDeclaration(mcd);
      }

      public Java.TypeBodyDeclaration visitMemberEnumDeclaration(Java.MemberEnumDeclaration med) throws CompileException {
         return DeepCopier.this.copyMemberEnumDeclaration(med);
      }

      public Java.TypeBodyDeclaration visitInitializer(Java.Initializer i) throws CompileException {
         return DeepCopier.this.copyInitializer(i);
      }

      public Java.TypeBodyDeclaration visitFieldDeclaration(Java.FieldDeclaration fd) throws CompileException {
         return DeepCopier.this.copyFieldDeclaration(fd);
      }
   };
   private final Visitor.FunctionDeclaratorVisitor functionDeclaratorCopier = new Visitor.FunctionDeclaratorVisitor() {
      public Java.FunctionDeclarator visitConstructorDeclarator(Java.ConstructorDeclarator cd) throws CompileException {
         return DeepCopier.this.copyConstructorDeclarator(cd);
      }

      public Java.FunctionDeclarator visitMethodDeclarator(Java.MethodDeclarator md) throws CompileException {
         return DeepCopier.this.copyMethodDeclarator(md);
      }
   };
   private final Visitor.BlockStatementVisitor blockStatementCopier = new Visitor.BlockStatementVisitor() {
      public Java.BlockStatement visitInitializer(Java.Initializer i) throws CompileException {
         return DeepCopier.this.copyInitializer(i);
      }

      public Java.BlockStatement visitFieldDeclaration(Java.FieldDeclaration fd) throws CompileException {
         return DeepCopier.this.copyFieldDeclaration(fd);
      }

      public Java.BlockStatement visitLabeledStatement(Java.LabeledStatement ls) throws CompileException {
         return DeepCopier.this.copyLabeledStatement(ls);
      }

      public Java.BlockStatement visitBlock(Java.Block b) throws CompileException {
         return DeepCopier.this.copyBlock(b);
      }

      public Java.BlockStatement visitExpressionStatement(Java.ExpressionStatement es) throws CompileException {
         return DeepCopier.this.copyExpressionStatement(es);
      }

      public Java.BlockStatement visitIfStatement(Java.IfStatement is) throws CompileException {
         return DeepCopier.this.copyIfStatement(is);
      }

      public Java.BlockStatement visitForStatement(Java.ForStatement fs) throws CompileException {
         return DeepCopier.this.copyForStatement(fs);
      }

      public Java.BlockStatement visitForEachStatement(Java.ForEachStatement fes) throws CompileException {
         return DeepCopier.this.copyForEachStatement(fes);
      }

      public Java.BlockStatement visitWhileStatement(Java.WhileStatement ws) throws CompileException {
         return DeepCopier.this.copyWhileStatement(ws);
      }

      public Java.BlockStatement visitTryStatement(Java.TryStatement ts) throws CompileException {
         return DeepCopier.this.copyTryStatement(ts);
      }

      public Java.BlockStatement visitSwitchStatement(Java.SwitchStatement ss) throws CompileException {
         return DeepCopier.this.copySwitchStatement(ss);
      }

      public Java.BlockStatement visitSynchronizedStatement(Java.SynchronizedStatement ss) throws CompileException {
         return DeepCopier.this.copySynchronizedStatement(ss);
      }

      public Java.BlockStatement visitDoStatement(Java.DoStatement ds) throws CompileException {
         return DeepCopier.this.copyDoStatement(ds);
      }

      public Java.BlockStatement visitLocalVariableDeclarationStatement(Java.LocalVariableDeclarationStatement lvds) throws CompileException {
         return DeepCopier.this.copyLocalVariableDeclarationStatement(lvds);
      }

      public Java.BlockStatement visitReturnStatement(Java.ReturnStatement rs) throws CompileException {
         return DeepCopier.this.copyReturnStatement(rs);
      }

      public Java.BlockStatement visitThrowStatement(Java.ThrowStatement ts) throws CompileException {
         return DeepCopier.this.copyThrowStatement(ts);
      }

      public Java.BlockStatement visitBreakStatement(Java.BreakStatement bs) throws CompileException {
         return DeepCopier.this.copyBreakStatement(bs);
      }

      public Java.BlockStatement visitContinueStatement(Java.ContinueStatement cs) throws CompileException {
         return DeepCopier.this.copyContinueStatement(cs);
      }

      public Java.BlockStatement visitAssertStatement(Java.AssertStatement as) throws CompileException {
         return DeepCopier.this.copyAssertStatement(as);
      }

      public Java.BlockStatement visitEmptyStatement(Java.EmptyStatement es) throws CompileException {
         return DeepCopier.this.copyEmptyStatement(es);
      }

      public Java.BlockStatement visitLocalClassDeclarationStatement(Java.LocalClassDeclarationStatement lcds) throws CompileException {
         return DeepCopier.this.copyLocalClassDeclarationStatement(lcds);
      }

      public Java.BlockStatement visitAlternateConstructorInvocation(Java.AlternateConstructorInvocation aci) throws CompileException {
         return DeepCopier.this.copyAlternateConstructorInvocation(aci);
      }

      public Java.BlockStatement visitSuperConstructorInvocation(Java.SuperConstructorInvocation sci) throws CompileException {
         return DeepCopier.this.copySuperConstructorInvocation(sci);
      }
   };
   private final Visitor.FieldDeclarationOrInitializerVisitor fieldDeclarationOrInitializerCopier = new Visitor.FieldDeclarationOrInitializerVisitor() {
      public Java.FieldDeclarationOrInitializer visitInitializer(Java.Initializer i) throws CompileException {
         return DeepCopier.this.copyInitializer(i);
      }

      public Java.FieldDeclarationOrInitializer visitFieldDeclaration(Java.FieldDeclaration fd) throws CompileException {
         return DeepCopier.this.copyFieldDeclaration(fd);
      }
   };
   private final Visitor.TypeVisitor typeCopier = new Visitor.TypeVisitor() {
      public Java.Type visitArrayType(Java.ArrayType at) throws CompileException {
         return DeepCopier.this.copyArrayType(at);
      }

      public Java.Type visitPrimitiveType(Java.PrimitiveType bt) throws CompileException {
         return DeepCopier.this.copyPrimitiveType(bt);
      }

      public Java.Type visitReferenceType(Java.ReferenceType rt) throws CompileException {
         return DeepCopier.this.copyReferenceType(rt);
      }

      public Java.Type visitRvalueMemberType(Java.RvalueMemberType rmt) throws CompileException {
         return DeepCopier.this.copyRvalueMemberType(rmt);
      }

      public Java.Type visitSimpleType(Java.SimpleType st) throws CompileException {
         return DeepCopier.this.copySimpleType(st);
      }
   };
   private final Visitor.AtomVisitor atomCopier = new Visitor.AtomVisitor() {
      public Java.Atom visitRvalue(Java.Rvalue rv) throws CompileException {
         return DeepCopier.this.copyRvalue(rv);
      }

      public Java.Atom visitPackage(Java.Package p) throws CompileException {
         return DeepCopier.this.copyPackage(p);
      }

      public Java.Atom visitType(Java.Type t) throws CompileException {
         return DeepCopier.this.copyType(t);
      }

      public Java.Atom visitConstructorInvocation(Java.ConstructorInvocation ci) throws CompileException {
         return DeepCopier.this.copyConstructorInvocation(ci);
      }
   };
   private final Visitor.ConstructorInvocationVisitor constructorInvocationCopier = new Visitor.ConstructorInvocationVisitor() {
      public Java.ConstructorInvocation visitAlternateConstructorInvocation(Java.AlternateConstructorInvocation aci) throws CompileException {
         return DeepCopier.this.copyAlternateConstructorInvocation(aci);
      }

      public Java.ConstructorInvocation visitSuperConstructorInvocation(Java.SuperConstructorInvocation sci) throws CompileException {
         return DeepCopier.this.copySuperConstructorInvocation(sci);
      }
   };
   private final Visitor.ElementValueVisitor elementValueCopier = new Visitor.ElementValueVisitor() {
      public Java.ElementValue visitRvalue(Java.Rvalue rv) throws CompileException {
         return DeepCopier.this.copyRvalue(rv);
      }

      public Java.ElementValue visitElementValueArrayInitializer(Java.ElementValueArrayInitializer evai) throws CompileException {
         return DeepCopier.this.copyElementValueArrayInitializer(evai);
      }

      public Java.ElementValue visitAnnotation(Java.Annotation a) throws CompileException {
         return DeepCopier.this.copyAnnotation(a);
      }
   };
   private final Visitor.AnnotationVisitor annotationCopier = new Visitor.AnnotationVisitor() {
      public Java.Annotation visitMarkerAnnotation(Java.MarkerAnnotation ma) throws CompileException {
         return DeepCopier.this.copyMarkerAnnotation(ma);
      }

      public Java.Annotation visitNormalAnnotation(Java.NormalAnnotation na) throws CompileException {
         return DeepCopier.this.copyNormalAnnotation(na);
      }

      public Java.Annotation visitSingleElementAnnotation(Java.SingleElementAnnotation sea) throws CompileException {
         return DeepCopier.this.copySingleElementAnnotation(sea);
      }
   };
   private final Visitor.ModifierVisitor modifierCopier = new Visitor.ModifierVisitor() {
      @Nullable
      public Java.Modifier visitMarkerAnnotation(Java.MarkerAnnotation ma) throws CompileException {
         return DeepCopier.this.copyMarkerAnnotation(ma);
      }

      @Nullable
      public Java.Modifier visitNormalAnnotation(Java.NormalAnnotation na) throws CompileException {
         return DeepCopier.this.copyNormalAnnotation(na);
      }

      @Nullable
      public Java.Modifier visitSingleElementAnnotation(Java.SingleElementAnnotation sea) throws CompileException {
         return DeepCopier.this.copySingleElementAnnotation(sea);
      }

      @Nullable
      public Java.Modifier visitAccessModifier(Java.AccessModifier am) throws CompileException {
         return DeepCopier.this.copyAccessModifier(am);
      }
   };
   private final Visitor.TryStatementResourceVisitor resourceCopier = new Visitor.TryStatementResourceVisitor() {
      public Java.TryStatement.Resource visitLocalVariableDeclaratorResource(Java.TryStatement.LocalVariableDeclaratorResource lvdr) throws CompileException {
         return DeepCopier.this.copyLocalVariableDeclaratorResource(lvdr);
      }

      public Java.TryStatement.Resource visitVariableAccessResource(Java.TryStatement.VariableAccessResource var) throws CompileException {
         return DeepCopier.this.copyVariableAccessResource(var);
      }
   };
   private final Visitor.TypeArgumentVisitor typeArgumentCopier = new Visitor.TypeArgumentVisitor() {
      @Nullable
      public Java.TypeArgument visitWildcard(Java.Wildcard w) throws CompileException {
         return DeepCopier.this.copyWildcard(w);
      }

      @Nullable
      public Java.TypeArgument visitReferenceType(Java.ReferenceType rt) throws CompileException {
         return DeepCopier.this.copyReferenceType(rt);
      }

      @Nullable
      public Java.TypeArgument visitArrayType(Java.ArrayType at) throws CompileException {
         return DeepCopier.this.copyArrayType(at);
      }
   };

   public Java.AbstractCompilationUnit copyAbstractCompilationUnit(Java.AbstractCompilationUnit subject) throws CompileException {
      return (Java.AbstractCompilationUnit)assertNotNull(subject.accept(this.abstractCompilationUnitCopier));
   }

   public Java.AbstractCompilationUnit.ImportDeclaration copyImportDeclaration(Java.AbstractCompilationUnit.ImportDeclaration subject) throws CompileException {
      return (Java.AbstractCompilationUnit.ImportDeclaration)assertNotNull(subject.accept(this.importCopier));
   }

   public Java.TypeDeclaration copyTypeDeclaration(Java.TypeDeclaration subject) throws CompileException {
      return (Java.TypeDeclaration)assertNotNull(subject.accept(this.typeDeclarationCopier));
   }

   public Java.TypeBodyDeclaration copyTypeBodyDeclaration(Java.TypeBodyDeclaration subject) throws CompileException {
      return (Java.TypeBodyDeclaration)assertNotNull(subject.accept(this.typeBodyDeclarationCopier));
   }

   public Java.FunctionDeclarator copyFunctionDeclarator(Java.FunctionDeclarator subject) throws CompileException {
      return (Java.FunctionDeclarator)assertNotNull(subject.accept(this.functionDeclaratorCopier));
   }

   public Java.BlockStatement copyBlockStatement(Java.BlockStatement subject) throws CompileException {
      return (Java.BlockStatement)assertNotNull(subject.accept(this.blockStatementCopier));
   }

   public Java.FieldDeclarationOrInitializer copyFieldDeclarationOrInitializer(Java.FieldDeclarationOrInitializer subject) throws CompileException {
      return (Java.FieldDeclarationOrInitializer)assertNotNull(subject.accept(this.fieldDeclarationOrInitializerCopier));
   }

   public Java.TryStatement.Resource copyResource(Java.TryStatement.Resource subject) throws CompileException {
      return (Java.TryStatement.Resource)assertNotNull(subject.accept(this.resourceCopier));
   }

   public Java.TypeArgument copyTypeArgument(Java.TypeArgument subject) throws CompileException {
      return (Java.TypeArgument)assertNotNull(subject.accept(this.typeArgumentCopier));
   }

   public Java.ConstructorInvocation copyConstructorInvocation(Java.ConstructorInvocation subject) throws CompileException {
      return (Java.ConstructorInvocation)assertNotNull(subject.accept(this.constructorInvocationCopier));
   }

   public Java.ElementValue copyElementValue(Java.ElementValue subject) throws CompileException {
      return (Java.ElementValue)assertNotNull(subject.accept(this.elementValueCopier));
   }

   public Java.Annotation copyAnnotation(Java.Annotation subject) throws CompileException {
      return (Java.Annotation)assertNotNull(subject.accept(this.annotationCopier));
   }

   public Java.Rvalue copyRvalue(Java.Rvalue subject) throws CompileException {
      return (Java.Rvalue)assertNotNull(subject.accept(this.rvalueCopier));
   }

   public Java.Lvalue copyLvalue(Java.Lvalue subject) throws CompileException {
      return (Java.Lvalue)assertNotNull(subject.accept(this.lvalueCopier));
   }

   public Java.Type copyType(Java.Type subject) throws CompileException {
      return (Java.Type)assertNotNull(subject.accept(this.typeCopier));
   }

   public Java.Atom copyAtom(Java.Atom subject) throws CompileException {
      return (Java.Atom)assertNotNull(subject.accept(this.atomCopier));
   }

   public Java.ArrayInitializerOrRvalue copyArrayInitializerOrRvalue(Java.ArrayInitializerOrRvalue subject) throws CompileException {
      return (Java.ArrayInitializerOrRvalue)assertNotNull(subject.accept(this.arrayInitializerOrRvalueCopier));
   }

   public Java.PackageMemberTypeDeclaration copyPackageMemberTypeDeclaration(Java.PackageMemberTypeDeclaration subject) throws CompileException {
      return (Java.PackageMemberTypeDeclaration)this.copyTypeDeclaration(subject);
   }

   public Java.MemberTypeDeclaration copyMemberTypeDeclaration(Java.MemberTypeDeclaration subject) throws CompileException {
      return (Java.MemberTypeDeclaration)this.copyTypeDeclaration(subject);
   }

   public Java.Statement copyStatement(Java.Statement subject) throws CompileException {
      return (Java.Statement)this.copyBlockStatement(subject);
   }

   @Nullable
   public Java.PackageDeclaration copyOptionalPackageDeclaration(@Nullable Java.PackageDeclaration subject) throws CompileException {
      return subject == null ? null : this.copyPackageDeclaration(subject);
   }

   @Nullable
   public Java.BlockStatement copyOptionalBlockStatement(@Nullable Java.BlockStatement subject) throws CompileException {
      return subject == null ? null : this.copyBlockStatement(subject);
   }

   @Nullable
   public Java.Block copyOptionalBlock(@Nullable Java.Block subject) throws CompileException {
      return subject == null ? null : this.copyBlock(subject);
   }

   @Nullable
   public Java.ArrayInitializer copyOptionalArrayInitializer(@Nullable Java.ArrayInitializer subject) throws CompileException {
      return subject == null ? null : this.copyArrayInitializer(subject);
   }

   @Nullable
   public Java.ArrayType copyOptionalArrayType(@Nullable Java.ArrayType subject) throws CompileException {
      return subject == null ? null : this.copyArrayType(subject);
   }

   @Nullable
   public Java.ReferenceType copyOptionalReferenceType(@Nullable Java.ReferenceType subject) throws CompileException {
      return subject == null ? null : this.copyReferenceType(subject);
   }

   @Nullable
   public Java.ConstructorInvocation copyOptionalConstructorInvocation(@Nullable Java.ConstructorInvocation subject) throws CompileException {
      return subject == null ? null : this.copyConstructorInvocation(subject);
   }

   @Nullable
   public Java.ElementValue copyOptionalElementValue(@Nullable Java.ElementValue subject) throws CompileException {
      return subject == null ? null : this.copyElementValue(subject);
   }

   @Nullable
   public Java.Rvalue copyOptionalRvalue(@Nullable Java.Rvalue subject) throws CompileException {
      return subject == null ? null : this.copyRvalue(subject);
   }

   @Nullable
   public Java.Type copyOptionalType(@Nullable Java.Type subject) throws CompileException {
      return subject == null ? null : this.copyType(subject);
   }

   @Nullable
   public Java.Atom copyOptionalAtom(@Nullable Java.Atom subject) throws CompileException {
      return subject == null ? null : this.copyAtom(subject);
   }

   @Nullable
   public Java.ArrayInitializerOrRvalue copyOptionalArrayInitializerOrRvalue(@Nullable Java.ArrayInitializerOrRvalue subject) throws CompileException {
      return subject == null ? null : this.copyArrayInitializerOrRvalue(subject);
   }

   @Nullable
   public Java.ReferenceType[] copyOptionalReferenceTypes(@Nullable Java.ReferenceType[] subject) throws CompileException {
      return subject == null ? null : this.copyReferenceTypes(subject);
   }

   @Nullable
   public Java.TypeArgument[] copyOptionalTypeArguments(@Nullable Java.TypeArgument[] subject) throws CompileException {
      return subject == null ? null : this.copyTypeArguments(subject);
   }

   @Nullable
   public Java.Rvalue[] copyOptionalRvalues(@Nullable Java.Rvalue[] subject) throws CompileException {
      return subject == null ? null : this.copyRvalues(subject);
   }

   @Nullable
   public Java.TypeParameter[] copyOptionalTypeParameters(@Nullable Java.TypeParameter[] subject) throws CompileException {
      return subject == null ? null : this.copyTypeParameters(subject);
   }

   @Nullable
   public List copyOptionalStatements(@Nullable Collection subject) throws CompileException {
      return subject == null ? null : this.copyStatements(subject);
   }

   public Java.AbstractCompilationUnit.ImportDeclaration[] copyImportDeclarations(Java.AbstractCompilationUnit.ImportDeclaration[] subject) throws CompileException {
      Java.AbstractCompilationUnit.ImportDeclaration[] result = new Java.AbstractCompilationUnit.ImportDeclaration[subject.length];

      for(int i = 0; i < subject.length; ++i) {
         result[i] = this.copyImportDeclaration(subject[i]);
      }

      return result;
   }

   public Java.TypeArgument[] copyTypeArguments(Java.TypeArgument[] subject) throws CompileException {
      Java.TypeArgument[] result = new Java.TypeArgument[subject.length];

      for(int i = 0; i < subject.length; ++i) {
         result[i] = this.copyTypeArgument(subject[i]);
      }

      return result;
   }

   public Java.VariableDeclarator[] copyVariableDeclarators(Java.VariableDeclarator[] subject) throws CompileException {
      Java.VariableDeclarator[] result = new Java.VariableDeclarator[subject.length];

      for(int i = 0; i < subject.length; ++i) {
         result[i] = this.copyVariableDeclarator(subject[i]);
      }

      return result;
   }

   public Java.ArrayInitializerOrRvalue[] copyArrayInitializerOrRvalues(Java.ArrayInitializerOrRvalue[] subject) throws CompileException {
      Java.ArrayInitializerOrRvalue[] result = new Java.ArrayInitializerOrRvalue[subject.length];

      for(int i = 0; i < subject.length; ++i) {
         result[i] = this.copyOptionalArrayInitializerOrRvalue(subject[i]);
      }

      return result;
   }

   public Java.ReferenceType[] copyReferenceTypes(Java.ReferenceType[] subject) throws CompileException {
      Java.ReferenceType[] result = new Java.ReferenceType[subject.length];

      for(int i = 0; i < subject.length; ++i) {
         result[i] = this.copyReferenceType(subject[i]);
      }

      return result;
   }

   public Java.ElementValue[] copyElementValues(Java.ElementValue[] subject) throws CompileException {
      Java.ElementValue[] result = new Java.ElementValue[subject.length];

      for(int i = 0; i < subject.length; ++i) {
         result[i] = this.copyOptionalElementValue(subject[i]);
      }

      return result;
   }

   public Java.ElementValuePair[] copyElementValuePairs(Java.ElementValuePair[] subject) throws CompileException {
      Java.ElementValuePair[] result = new Java.ElementValuePair[subject.length];

      for(int i = 0; i < subject.length; ++i) {
         result[i] = this.copyElementValuePair(subject[i]);
      }

      return result;
   }

   public Java.Type[] copyTypes(Java.Type[] subject) throws CompileException {
      Java.Type[] result = new Java.Type[subject.length];

      for(int i = 0; i < subject.length; ++i) {
         result[i] = this.copyType(subject[i]);
      }

      return result;
   }

   public Java.TypeParameter[] copyTypeParameters(Java.TypeParameter[] subject) throws CompileException {
      Java.TypeParameter[] result = new Java.TypeParameter[subject.length];

      for(int i = 0; i < subject.length; ++i) {
         result[i] = this.copyTypeParameter(subject[i]);
      }

      return result;
   }

   public Java.FunctionDeclarator.FormalParameter[] copyFormalParameters(Java.FunctionDeclarator.FormalParameter[] subject) throws CompileException {
      Java.FunctionDeclarator.FormalParameter[] result = new Java.FunctionDeclarator.FormalParameter[subject.length];

      for(int i = 0; i < result.length; ++i) {
         result[i] = this.copyFormalParameter(subject[i]);
      }

      return result;
   }

   public Java.Annotation[] copyAnnotations(Java.Annotation[] subject) throws CompileException {
      Java.Annotation[] result = new Java.Annotation[subject.length];

      for(int i = 0; i < result.length; ++i) {
         result[i] = this.copyAnnotation(subject[i]);
      }

      return result;
   }

   public Java.Rvalue[] copyRvalues(Java.Rvalue[] subject) throws CompileException {
      return (Java.Rvalue[])this.copyRvalues((Collection)Arrays.asList(subject)).toArray(new Java.Rvalue[0]);
   }

   public List copyBlockStatements(Collection subject) throws CompileException {
      List<Java.BlockStatement> result = new ArrayList(subject.size());

      for(Java.BlockStatement bs : subject) {
         result.add(this.copyBlockStatement(bs));
      }

      return result;
   }

   public List copyResources(Collection subject) throws CompileException {
      List<Java.TryStatement.Resource> result = new ArrayList(subject.size());

      for(Java.TryStatement.Resource r : subject) {
         result.add(this.copyResource(r));
      }

      return result;
   }

   public List copyCatchClauses(Collection subject) throws CompileException {
      List<Java.CatchClause> result = new ArrayList(subject.size());

      for(Java.CatchClause sbgs : subject) {
         result.add(this.copyCatchClause(sbgs));
      }

      return result;
   }

   public List copySwitchBlockStatementGroups(Collection subject) throws CompileException {
      List<Java.SwitchStatement.SwitchBlockStatementGroup> result = new ArrayList(subject.size());

      for(Java.SwitchStatement.SwitchBlockStatementGroup sbgs : subject) {
         result.add(this.copySwitchBlockStatementGroup(sbgs));
      }

      return result;
   }

   public List copyStatements(Collection subject) throws CompileException {
      List<Java.BlockStatement> result = new ArrayList(subject.size());

      for(Java.BlockStatement bs : subject) {
         result.add(this.copyBlockStatement(bs));
      }

      return result;
   }

   public List copyRvalues(Collection subject) throws CompileException {
      List<Java.Rvalue> result = new ArrayList(subject.size());

      for(Java.Rvalue rv : subject) {
         result.add(this.copyRvalue(rv));
      }

      return result;
   }

   public Java.CompilationUnit copyCompilationUnit(Java.CompilationUnit subject) throws CompileException {
      Java.CompilationUnit result = new Java.CompilationUnit(subject.fileName, this.copyImportDeclarations(subject.importDeclarations));
      result.setPackageDeclaration(this.copyOptionalPackageDeclaration(subject.packageDeclaration));

      for(Java.PackageMemberTypeDeclaration pmtd : subject.packageMemberTypeDeclarations) {
         result.addPackageMemberTypeDeclaration(this.copyPackageMemberTypeDeclaration(pmtd));
      }

      return result;
   }

   public Java.ModularCompilationUnit copyModularCompilationUnit(Java.ModularCompilationUnit subject) throws CompileException {
      return new Java.ModularCompilationUnit(subject.fileName, this.copyImportDeclarations(subject.importDeclarations), subject.moduleDeclaration);
   }

   public Java.TypeArgument copyWildcard(Java.Wildcard subject) throws CompileException {
      return new Java.Wildcard(subject.bounds, this.copyOptionalReferenceType(subject.referenceType));
   }

   public Java.PackageDeclaration copyPackageDeclaration(Java.PackageDeclaration subject) throws CompileException {
      return new Java.PackageDeclaration(subject.getLocation(), subject.packageName);
   }

   public Java.AbstractCompilationUnit.ImportDeclaration copySingleTypeImportDeclaration(Java.AbstractCompilationUnit.SingleTypeImportDeclaration stid) throws CompileException {
      return new Java.AbstractCompilationUnit.SingleTypeImportDeclaration(stid.getLocation(), (String[])stid.identifiers.clone());
   }

   public Java.AbstractCompilationUnit.ImportDeclaration copyTypeImportOnDemandDeclaration(Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration tiodd) throws CompileException {
      return new Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration(tiodd.getLocation(), (String[])tiodd.identifiers.clone());
   }

   public Java.AbstractCompilationUnit.ImportDeclaration copySingleStaticImportDeclaration(Java.AbstractCompilationUnit.SingleStaticImportDeclaration stid) throws CompileException {
      return new Java.AbstractCompilationUnit.SingleStaticImportDeclaration(stid.getLocation(), (String[])stid.identifiers.clone());
   }

   public Java.AbstractCompilationUnit.ImportDeclaration copyStaticImportOnDemandDeclaration(Java.AbstractCompilationUnit.StaticImportOnDemandDeclaration siodd) throws CompileException {
      return new Java.AbstractCompilationUnit.StaticImportOnDemandDeclaration(siodd.getLocation(), (String[])siodd.identifiers.clone());
   }

   public Java.AnonymousClassDeclaration copyAnonymousClassDeclaration(Java.AnonymousClassDeclaration subject) throws CompileException {
      Java.AnonymousClassDeclaration result = new Java.AnonymousClassDeclaration(subject.getLocation(), this.copyType(subject.baseType));

      for(Java.FieldDeclarationOrInitializer fdoi : subject.fieldDeclarationsAndInitializers) {
         result.addFieldDeclarationOrInitializer(this.copyFieldDeclarationOrInitializer(fdoi));
      }

      for(Java.ConstructorDeclarator cd : subject.constructors) {
         result.addConstructor(this.copyConstructorDeclarator(cd));
      }

      for(Java.MethodDeclarator md : subject.getMethodDeclarations()) {
         result.addDeclaredMethod(this.copyMethodDeclarator(md));
      }

      for(Java.MemberTypeDeclaration mtd : subject.getMemberTypeDeclarations()) {
         result.addMemberTypeDeclaration(this.copyMemberTypeDeclaration(mtd));
      }

      return result;
   }

   public Java.LocalClassDeclaration copyLocalClassDeclaration(Java.LocalClassDeclaration subject) throws CompileException {
      Java.LocalClassDeclaration result = new Java.LocalClassDeclaration(subject.getLocation(), subject.getDocComment(), this.copyModifiers(subject.getModifiers()), subject.name, subject.getOptionalTypeParameters(), subject.extendedType, this.copyTypes(subject.implementedTypes));

      for(Java.FieldDeclarationOrInitializer fdoi : subject.fieldDeclarationsAndInitializers) {
         result.addFieldDeclarationOrInitializer(this.copyFieldDeclarationOrInitializer(fdoi));
      }

      for(Java.ConstructorDeclarator cd : subject.constructors) {
         result.addConstructor(this.copyConstructorDeclarator(cd));
      }

      for(Java.MethodDeclarator md : subject.getMethodDeclarations()) {
         result.addDeclaredMethod(this.copyMethodDeclarator(md));
      }

      for(Java.MemberTypeDeclaration mtd : subject.getMemberTypeDeclarations()) {
         result.addMemberTypeDeclaration(this.copyMemberTypeDeclaration(mtd));
      }

      return result;
   }

   public Java.TypeDeclaration copyPackageMemberClassDeclaration(Java.PackageMemberClassDeclaration subject) throws CompileException {
      Java.PackageMemberClassDeclaration result = new Java.PackageMemberClassDeclaration(subject.getLocation(), subject.getDocComment(), this.copyModifiers(subject.getModifiers()), subject.name, this.copyOptionalTypeParameters(subject.getOptionalTypeParameters()), this.copyOptionalType(subject.extendedType), this.copyTypes(subject.implementedTypes));

      for(Java.FieldDeclarationOrInitializer fdoi : subject.fieldDeclarationsAndInitializers) {
         result.addFieldDeclarationOrInitializer(this.copyFieldDeclarationOrInitializer(fdoi));
      }

      for(Java.ConstructorDeclarator cd : subject.constructors) {
         result.addConstructor(this.copyConstructorDeclarator(cd));
      }

      for(Java.MethodDeclarator md : subject.getMethodDeclarations()) {
         result.addDeclaredMethod(this.copyMethodDeclarator(md));
      }

      for(Java.MemberTypeDeclaration mtd : subject.getMemberTypeDeclarations()) {
         result.addMemberTypeDeclaration(this.copyMemberTypeDeclaration(mtd));
      }

      return result;
   }

   public Java.MemberTypeDeclaration copyMemberInterfaceDeclaration(Java.MemberInterfaceDeclaration subject) throws CompileException {
      Java.MemberInterfaceDeclaration result = new Java.MemberInterfaceDeclaration(subject.getLocation(), subject.getDocComment(), this.copyModifiers(subject.getModifiers()), subject.name, this.copyOptionalTypeParameters(subject.getOptionalTypeParameters()), this.copyTypes(subject.extendedTypes));

      for(Java.MethodDeclarator md : subject.getMethodDeclarations()) {
         result.addDeclaredMethod(this.copyMethodDeclarator(md));
      }

      for(Java.MemberTypeDeclaration mtd : subject.getMemberTypeDeclarations()) {
         result.addMemberTypeDeclaration(this.copyMemberTypeDeclaration(mtd));
      }

      return result;
   }

   public Java.TypeDeclaration copyPackageMemberInterfaceDeclaration(Java.PackageMemberInterfaceDeclaration subject) throws CompileException {
      Java.PackageMemberInterfaceDeclaration result = new Java.PackageMemberInterfaceDeclaration(subject.getLocation(), subject.getDocComment(), this.copyModifiers(subject.getModifiers()), subject.name, this.copyOptionalTypeParameters(subject.getOptionalTypeParameters()), this.copyTypes(subject.extendedTypes));

      for(Java.MethodDeclarator md : subject.getMethodDeclarations()) {
         result.addDeclaredMethod(this.copyMethodDeclarator(md));
      }

      for(Java.MemberTypeDeclaration mtd : subject.getMemberTypeDeclarations()) {
         result.addMemberTypeDeclaration(this.copyMemberTypeDeclaration(mtd));
      }

      for(Java.FieldDeclaration cd : subject.constantDeclarations) {
         result.addConstantDeclaration(this.copyFieldDeclaration(cd));
      }

      return result;
   }

   public Java.MemberTypeDeclaration copyMemberClassDeclaration(Java.MemberClassDeclaration subject) throws CompileException {
      Java.MemberClassDeclaration result = new Java.MemberClassDeclaration(subject.getLocation(), subject.getDocComment(), this.copyModifiers(subject.getModifiers()), subject.name, this.copyOptionalTypeParameters(subject.getOptionalTypeParameters()), this.copyOptionalType(subject.extendedType), this.copyTypes(subject.implementedTypes));

      for(Java.FieldDeclarationOrInitializer fdoi : subject.fieldDeclarationsAndInitializers) {
         result.addFieldDeclarationOrInitializer(this.copyFieldDeclarationOrInitializer(fdoi));
      }

      for(Java.ConstructorDeclarator cd : subject.constructors) {
         result.addConstructor(this.copyConstructorDeclarator(cd));
      }

      for(Java.MethodDeclarator md : subject.getMethodDeclarations()) {
         result.addDeclaredMethod(this.copyMethodDeclarator(md));
      }

      for(Java.MemberTypeDeclaration mtd : subject.getMemberTypeDeclarations()) {
         result.addMemberTypeDeclaration(this.copyMemberTypeDeclaration(mtd));
      }

      return result;
   }

   public Java.ConstructorDeclarator copyConstructorDeclarator(Java.ConstructorDeclarator subject) throws CompileException {
      return new Java.ConstructorDeclarator(subject.getLocation(), subject.getDocComment(), this.copyModifiers(subject.getModifiers()), this.copyFormalParameters(subject.formalParameters), this.copyTypes(subject.thrownExceptions), this.copyOptionalConstructorInvocation(subject.constructorInvocation), this.copyBlockStatements((Collection)assertNotNull(subject.statements)));
   }

   public Java.Initializer copyInitializer(Java.Initializer subject) throws CompileException {
      return new Java.Initializer(subject.getLocation(), this.copyModifiers(subject.modifiers), this.copyBlock(subject.block));
   }

   public Java.MethodDeclarator copyMethodDeclarator(Java.MethodDeclarator subject) throws CompileException {
      return new Java.MethodDeclarator(subject.getLocation(), subject.getDocComment(), this.copyModifiers(subject.getModifiers()), this.copyOptionalTypeParameters(subject.typeParameters), this.copyType(subject.type), subject.name, this.copyFormalParameters(subject.formalParameters), this.copyTypes(subject.thrownExceptions), this.copyOptionalElementValue(subject.defaultValue), this.copyOptionalStatements(subject.statements));
   }

   public Java.FieldDeclaration copyFieldDeclaration(Java.FieldDeclaration subject) throws CompileException {
      return new Java.FieldDeclaration(subject.getLocation(), subject.getDocComment(), this.copyModifiers(subject.modifiers), this.copyType(subject.type), this.copyVariableDeclarators(subject.variableDeclarators));
   }

   public Java.VariableDeclarator copyVariableDeclarator(Java.VariableDeclarator subject) throws CompileException {
      return new Java.VariableDeclarator(subject.getLocation(), subject.name, subject.brackets, this.copyOptionalArrayInitializerOrRvalue(subject.initializer));
   }

   public Java.BlockStatement copyLabeledStatement(Java.LabeledStatement ls) throws CompileException {
      return new Java.LabeledStatement(ls.getLocation(), ls.label, this.copyStatement(ls.body));
   }

   public Java.Block copyBlock(Java.Block b) throws CompileException {
      Java.Block result = new Java.Block(b.getLocation());

      for(Java.BlockStatement bs : b.statements) {
         result.addStatement(this.copyBlockStatement(bs));
      }

      return result;
   }

   public Java.BlockStatement copyExpressionStatement(Java.ExpressionStatement es) throws CompileException {
      return new Java.ExpressionStatement(this.copyRvalue(es.rvalue));
   }

   public Java.BlockStatement copyIfStatement(Java.IfStatement is) throws CompileException {
      return new Java.IfStatement(is.getLocation(), this.copyRvalue(is.condition), this.copyBlockStatement(is.thenStatement), this.copyOptionalBlockStatement(is.elseStatement));
   }

   public Java.BlockStatement copyForStatement(Java.ForStatement fs) throws CompileException {
      return new Java.ForStatement(fs.getLocation(), this.copyOptionalBlockStatement(fs.init), this.copyOptionalRvalue(fs.condition), this.copyOptionalRvalues(fs.update), this.copyBlockStatement(fs.body));
   }

   public Java.BlockStatement copyForEachStatement(Java.ForEachStatement fes) throws CompileException {
      return new Java.ForEachStatement(fes.getLocation(), this.copyFormalParameter(fes.currentElement), this.copyRvalue(fes.expression), this.copyBlockStatement(fes.body));
   }

   public Java.BlockStatement copyWhileStatement(Java.WhileStatement ws) throws CompileException {
      return new Java.WhileStatement(ws.getLocation(), this.copyRvalue(ws.condition), this.copyBlockStatement(ws.body));
   }

   public Java.BlockStatement copyTryStatement(Java.TryStatement ts) throws CompileException {
      return new Java.TryStatement(ts.getLocation(), this.copyResources(ts.resources), this.copyBlockStatement(ts.body), this.copyCatchClauses(ts.catchClauses), this.copyOptionalBlock(ts.finallY));
   }

   public Java.CatchClause copyCatchClause(Java.CatchClause subject) throws CompileException {
      return new Java.CatchClause(subject.getLocation(), this.copyCatchParameter(subject.catchParameter), this.copyBlockStatement(subject.body));
   }

   public Java.BlockStatement copySwitchStatement(Java.SwitchStatement subject) throws CompileException {
      return new Java.SwitchStatement(subject.getLocation(), this.copyRvalue(subject.condition), this.copySwitchBlockStatementGroups(subject.sbsgs));
   }

   public Java.SwitchStatement.SwitchBlockStatementGroup copySwitchBlockStatementGroup(Java.SwitchStatement.SwitchBlockStatementGroup subject) throws CompileException {
      return new Java.SwitchStatement.SwitchBlockStatementGroup(subject.getLocation(), this.copyRvalues((Collection)subject.caseLabels), subject.hasDefaultLabel, this.copyBlockStatements(subject.blockStatements));
   }

   public Java.BlockStatement copySynchronizedStatement(Java.SynchronizedStatement subject) throws CompileException {
      return new Java.SynchronizedStatement(subject.getLocation(), this.copyRvalue(subject.expression), this.copyBlockStatement(subject.body));
   }

   public Java.BlockStatement copyDoStatement(Java.DoStatement subject) throws CompileException {
      return new Java.DoStatement(subject.getLocation(), this.copyBlockStatement(subject.body), this.copyRvalue(subject.condition));
   }

   public Java.BlockStatement copyLocalVariableDeclarationStatement(Java.LocalVariableDeclarationStatement subject) throws CompileException {
      return new Java.LocalVariableDeclarationStatement(subject.getLocation(), this.copyModifiers(subject.modifiers), this.copyType(subject.type), this.copyVariableDeclarators(subject.variableDeclarators));
   }

   public Java.BlockStatement copyReturnStatement(Java.ReturnStatement subject) throws CompileException {
      return new Java.ReturnStatement(subject.getLocation(), this.copyOptionalRvalue(subject.returnValue));
   }

   public Java.BlockStatement copyThrowStatement(Java.ThrowStatement subject) throws CompileException {
      return new Java.ThrowStatement(subject.getLocation(), this.copyRvalue(subject.expression));
   }

   public Java.BlockStatement copyBreakStatement(Java.BreakStatement subject) throws CompileException {
      return new Java.BreakStatement(subject.getLocation(), subject.label);
   }

   public Java.BlockStatement copyContinueStatement(Java.ContinueStatement subject) throws CompileException {
      return new Java.ContinueStatement(subject.getLocation(), subject.label);
   }

   public Java.BlockStatement copyAssertStatement(Java.AssertStatement subject) throws CompileException {
      return new Java.AssertStatement(subject.getLocation(), this.copyRvalue(subject.expression1), this.copyOptionalRvalue(subject.expression2));
   }

   public Java.BlockStatement copyEmptyStatement(Java.EmptyStatement subject) throws CompileException {
      return new Java.EmptyStatement(subject.getLocation());
   }

   public Java.BlockStatement copyLocalClassDeclarationStatement(Java.LocalClassDeclarationStatement subject) throws CompileException {
      return new Java.LocalClassDeclarationStatement(this.copyLocalClassDeclaration(subject.lcd));
   }

   public Java.Atom copyPackage(Java.Package subject) throws CompileException {
      return new Java.Package(subject.getLocation(), subject.name);
   }

   public Java.Rvalue copyArrayLength(Java.ArrayLength subject) throws CompileException {
      return new Java.ArrayLength(subject.getLocation(), this.copyRvalue(subject.lhs));
   }

   public Java.Rvalue copyAssignment(Java.Assignment subject) throws CompileException {
      return new Java.Assignment(subject.getLocation(), this.copyLvalue(subject.lhs), subject.operator, this.copyRvalue(subject.rhs));
   }

   public Java.Rvalue copyUnaryOperation(Java.UnaryOperation subject) throws CompileException {
      return new Java.UnaryOperation(subject.getLocation(), subject.operator, this.copyRvalue(subject.operand));
   }

   public Java.Rvalue copyBinaryOperation(Java.BinaryOperation subject) throws CompileException {
      return new Java.BinaryOperation(subject.getLocation(), this.copyRvalue(subject.lhs), subject.operator, this.copyRvalue(subject.rhs));
   }

   public Java.Rvalue copyCast(Java.Cast subject) throws CompileException {
      return new Java.Cast(subject.getLocation(), this.copyType(subject.targetType), this.copyRvalue(subject.value));
   }

   public Java.Rvalue copyClassLiteral(Java.ClassLiteral subject) throws CompileException {
      return new Java.ClassLiteral(subject.getLocation(), this.copyType(subject.type));
   }

   public Java.Rvalue copyConditionalExpression(Java.ConditionalExpression subject) throws CompileException {
      return new Java.ConditionalExpression(subject.getLocation(), this.copyRvalue(subject.lhs), this.copyRvalue(subject.mhs), this.copyRvalue(subject.rhs));
   }

   public Java.Rvalue copyCrement(Java.Crement subject) throws CompileException {
      return subject.pre ? new Java.Crement(subject.getLocation(), subject.operator, this.copyLvalue(subject.operand)) : new Java.Crement(subject.getLocation(), this.copyLvalue(subject.operand), subject.operator);
   }

   public Java.Rvalue copyInstanceof(Java.Instanceof subject) throws CompileException {
      return new Java.Instanceof(subject.getLocation(), this.copyRvalue(subject.lhs), this.copyType(subject.rhs));
   }

   public Java.Rvalue copyMethodInvocation(Java.MethodInvocation subject) throws CompileException {
      return new Java.MethodInvocation(subject.getLocation(), this.copyOptionalAtom(subject.target), subject.methodName, this.copyRvalues(subject.arguments));
   }

   public Java.Rvalue copySuperclassMethodInvocation(Java.SuperclassMethodInvocation subject) throws CompileException {
      return new Java.SuperclassMethodInvocation(subject.getLocation(), subject.methodName, this.copyRvalues(subject.arguments));
   }

   public Java.Rvalue copyIntegerLiteral(Java.IntegerLiteral subject) throws CompileException {
      return new Java.IntegerLiteral(subject.getLocation(), subject.value);
   }

   public Java.Rvalue copyFloatingPointLiteral(Java.FloatingPointLiteral subject) throws CompileException {
      return new Java.FloatingPointLiteral(subject.getLocation(), subject.value);
   }

   public Java.Rvalue copyBooleanLiteral(Java.BooleanLiteral subject) throws CompileException {
      return new Java.BooleanLiteral(subject.getLocation(), subject.value);
   }

   public Java.Rvalue copyCharacterLiteral(Java.CharacterLiteral subject) throws CompileException {
      return new Java.CharacterLiteral(subject.getLocation(), subject.value);
   }

   public Java.Rvalue copyStringLiteral(Java.StringLiteral subject) throws CompileException {
      return new Java.StringLiteral(subject.getLocation(), subject.value);
   }

   public Java.Rvalue copyNullLiteral(Java.NullLiteral subject) throws CompileException {
      return new Java.NullLiteral(subject.getLocation());
   }

   public Java.Rvalue copySimpleLiteral(Java.SimpleConstant subject) throws CompileException {
      throw new AssertionError();
   }

   public Java.Rvalue copyNewAnonymousClassInstance(Java.NewAnonymousClassInstance subject) throws CompileException {
      return new Java.NewAnonymousClassInstance(subject.getLocation(), this.copyOptionalRvalue(subject.qualification), this.copyAnonymousClassDeclaration(subject.anonymousClassDeclaration), this.copyRvalues(subject.arguments));
   }

   public Java.Rvalue copyNewArray(Java.NewArray subject) throws CompileException {
      return new Java.NewArray(subject.getLocation(), this.copyType(subject.type), this.copyRvalues(subject.dimExprs), subject.dims);
   }

   public Java.Rvalue copyNewInitializedArray(Java.NewInitializedArray subject) throws CompileException {
      return new Java.NewInitializedArray(subject.getLocation(), this.copyOptionalArrayType(subject.arrayType), this.copyArrayInitializer(subject.arrayInitializer));
   }

   public Java.ArrayInitializer copyArrayInitializer(Java.ArrayInitializer subject) throws CompileException {
      return new Java.ArrayInitializer(subject.getLocation(), this.copyArrayInitializerOrRvalues(subject.values));
   }

   public Java.Rvalue copyNewClassInstance(Java.NewClassInstance subject) throws CompileException {
      return subject.type != null ? new Java.NewClassInstance(subject.getLocation(), this.copyOptionalRvalue(subject.qualification), this.copyType((Java.Type)assertNotNull(subject.type)), this.copyRvalues(subject.arguments)) : new Java.NewClassInstance(subject.getLocation(), this.copyOptionalRvalue(subject.qualification), (IType)assertNotNull(subject.iType), this.copyRvalues(subject.arguments));
   }

   public Java.Rvalue copyParameterAccess(Java.ParameterAccess pa) throws CompileException {
      return this.copyRvalue(pa);
   }

   public Java.Rvalue copyQualifiedThisReference(Java.QualifiedThisReference subject) throws CompileException {
      return new Java.QualifiedThisReference(subject.getLocation(), this.copyType(subject.qualification));
   }

   public Java.Rvalue copyThisReference(Java.ThisReference subject) throws CompileException {
      return new Java.ThisReference(subject.getLocation());
   }

   public Java.Rvalue copyLambdaExpression(Java.LambdaExpression subject) {
      return new Java.LambdaExpression(subject.getLocation(), subject.parameters, subject.body);
   }

   public Java.Rvalue copyArrayCreationReference(Java.ArrayCreationReference subject) throws CompileException {
      return new Java.ArrayCreationReference(subject.getLocation(), this.copyArrayType(subject.type));
   }

   public Java.Rvalue copyClassInstanceCreationReference(Java.ClassInstanceCreationReference subject) throws CompileException {
      return new Java.ClassInstanceCreationReference(subject.getLocation(), this.copyType(subject.type), this.copyOptionalTypeArguments(subject.typeArguments));
   }

   public Java.Rvalue copyMethodReference(Java.MethodReference subject) throws CompileException {
      return new Java.MethodReference(subject.getLocation(), this.copyAtom(subject.lhs), subject.methodName);
   }

   public Java.ArrayType copyArrayType(Java.ArrayType subject) throws CompileException {
      return new Java.ArrayType(this.copyType(subject.componentType));
   }

   public Java.Type copyPrimitiveType(Java.PrimitiveType bt) throws CompileException {
      return new Java.PrimitiveType(bt.getLocation(), bt.primitive);
   }

   public Java.ReferenceType copyReferenceType(Java.ReferenceType subject) throws CompileException {
      return new Java.ReferenceType(subject.getLocation(), this.copyAnnotations(subject.annotations), subject.identifiers, this.copyOptionalTypeArguments(subject.typeArguments));
   }

   public Java.Type copyRvalueMemberType(Java.RvalueMemberType subject) throws CompileException {
      return new Java.RvalueMemberType(subject.getLocation(), this.copyRvalue(subject.rvalue), subject.identifier);
   }

   public Java.Type copySimpleType(Java.SimpleType st) throws CompileException {
      return new Java.SimpleType(st.getLocation(), st.iType);
   }

   public Java.ConstructorInvocation copyAlternateConstructorInvocation(Java.AlternateConstructorInvocation subject) throws CompileException {
      return new Java.AlternateConstructorInvocation(subject.getLocation(), this.copyRvalues(subject.arguments));
   }

   public Java.ConstructorInvocation copySuperConstructorInvocation(Java.SuperConstructorInvocation subject) throws CompileException {
      return new Java.SuperConstructorInvocation(subject.getLocation(), this.copyOptionalRvalue(subject.qualification), this.copyRvalues(subject.arguments));
   }

   public Java.Lvalue copyAmbiguousName(Java.AmbiguousName subject) throws CompileException {
      return new Java.AmbiguousName(subject.getLocation(), (String[])Arrays.copyOf(subject.identifiers, subject.n));
   }

   public Java.Lvalue copyArrayAccessExpression(Java.ArrayAccessExpression subject) throws CompileException {
      return new Java.ArrayAccessExpression(subject.getLocation(), this.copyRvalue(subject.lhs), this.copyRvalue(subject.index));
   }

   public Java.Lvalue copyFieldAccess(Java.FieldAccess subject) throws CompileException {
      return new Java.FieldAccess(subject.getLocation(), this.copyAtom(subject.lhs), subject.field);
   }

   public Java.Lvalue copyFieldAccessExpression(Java.FieldAccessExpression subject) throws CompileException {
      return new Java.FieldAccessExpression(subject.getLocation(), this.copyAtom(subject.lhs), subject.fieldName);
   }

   public Java.Lvalue copySuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression subject) throws CompileException {
      return new Java.SuperclassFieldAccessExpression(subject.getLocation(), this.copyOptionalType(subject.qualification), subject.fieldName);
   }

   public Java.Lvalue copyLocalVariableAccess(Java.LocalVariableAccess subject) throws CompileException {
      throw new AssertionError();
   }

   public Java.Lvalue copyParenthesizedExpression(Java.ParenthesizedExpression subject) throws CompileException {
      return new Java.ParenthesizedExpression(subject.getLocation(), this.copyRvalue(subject.value));
   }

   public Java.ElementValue copyElementValueArrayInitializer(Java.ElementValueArrayInitializer subject) throws CompileException {
      return new Java.ElementValueArrayInitializer(this.copyElementValues(subject.elementValues), subject.getLocation());
   }

   public Java.Annotation copySingleElementAnnotation(Java.SingleElementAnnotation subject) throws CompileException {
      return new Java.SingleElementAnnotation(this.copyReferenceType((Java.ReferenceType)subject.type), this.copyElementValue(subject.elementValue));
   }

   public Java.Annotation copyNormalAnnotation(Java.NormalAnnotation subject) throws CompileException {
      return new Java.NormalAnnotation(this.copyReferenceType((Java.ReferenceType)subject.type), this.copyElementValuePairs(subject.elementValuePairs));
   }

   public Java.ElementValuePair copyElementValuePair(Java.ElementValuePair subject) throws CompileException {
      return new Java.ElementValuePair(subject.identifier, this.copyElementValue(subject.elementValue));
   }

   public Java.Annotation copyMarkerAnnotation(Java.MarkerAnnotation subject) throws CompileException {
      return new Java.MarkerAnnotation(this.copyType(subject.type));
   }

   public Java.FunctionDeclarator.FormalParameters copyFormalParameters(Java.FunctionDeclarator.FormalParameters subject) throws CompileException {
      return new Java.FunctionDeclarator.FormalParameters(subject.getLocation(), this.copyFormalParameters(subject.parameters), subject.variableArity);
   }

   public Java.FunctionDeclarator.FormalParameter copyFormalParameter(Java.FunctionDeclarator.FormalParameter subject) throws CompileException {
      return new Java.FunctionDeclarator.FormalParameter(subject.getLocation(), this.copyModifiers(subject.modifiers), this.copyType(subject.type), subject.name);
   }

   public Java.CatchParameter copyCatchParameter(Java.CatchParameter subject) throws CompileException {
      return new Java.CatchParameter(subject.getLocation(), subject.finaL, this.copyTypes(subject.types), subject.name);
   }

   public Java.EnumConstant copyEnumConstant(Java.EnumConstant subject) throws CompileException {
      Java.EnumConstant result = new Java.EnumConstant(subject.getLocation(), subject.docComment, this.copyModifiers(subject.getModifiers()), subject.name, this.copyOptionalRvalues(subject.arguments));

      for(Java.FieldDeclarationOrInitializer fdoi : subject.fieldDeclarationsAndInitializers) {
         result.addFieldDeclarationOrInitializer(this.copyFieldDeclarationOrInitializer(fdoi));
      }

      return result;
   }

   public Java.TypeDeclaration copyPackageMemberEnumDeclaration(Java.PackageMemberEnumDeclaration subject) throws CompileException {
      Java.PackageMemberEnumDeclaration result = new Java.PackageMemberEnumDeclaration(subject.getLocation(), subject.getDocComment(), this.copyModifiers(subject.getModifiers()), subject.name, this.copyTypes(subject.implementedTypes));

      for(Java.EnumConstant ec : subject.getConstants()) {
         result.addConstant(this.copyEnumConstant(ec));
      }

      for(Java.ConstructorDeclarator cd : subject.constructors) {
         result.addConstructor(this.copyConstructorDeclarator(cd));
      }

      for(Java.MethodDeclarator md : subject.getMethodDeclarations()) {
         result.addDeclaredMethod(this.copyMethodDeclarator(md));
      }

      for(Java.MemberTypeDeclaration mtd : subject.getMemberTypeDeclarations()) {
         result.addMemberTypeDeclaration(this.copyMemberTypeDeclaration(mtd));
      }

      for(Java.FieldDeclarationOrInitializer fdoi : subject.fieldDeclarationsAndInitializers) {
         result.addFieldDeclarationOrInitializer(this.copyFieldDeclarationOrInitializer(fdoi));
      }

      return result;
   }

   public Java.MemberTypeDeclaration copyMemberEnumDeclaration(Java.MemberEnumDeclaration subject) throws CompileException {
      Java.MemberEnumDeclaration result = new Java.MemberEnumDeclaration(subject.getLocation(), subject.getDocComment(), this.copyModifiers(subject.getModifiers()), subject.name, this.copyTypes(subject.implementedTypes));

      for(Java.EnumConstant ec : subject.getConstants()) {
         result.addConstant(this.copyEnumConstant(ec));
      }

      for(Java.ConstructorDeclarator cd : subject.constructors) {
         result.addConstructor(this.copyConstructorDeclarator(cd));
      }

      for(Java.MethodDeclarator md : subject.getMethodDeclarations()) {
         result.addDeclaredMethod(this.copyMethodDeclarator(md));
      }

      for(Java.MemberTypeDeclaration mtd : subject.getMemberTypeDeclarations()) {
         result.addMemberTypeDeclaration(this.copyMemberTypeDeclaration(mtd));
      }

      for(Java.FieldDeclarationOrInitializer fdoi : subject.fieldDeclarationsAndInitializers) {
         result.addFieldDeclarationOrInitializer(this.copyFieldDeclarationOrInitializer(fdoi));
      }

      return result;
   }

   public Java.TypeDeclaration copyPackageMemberAnnotationTypeDeclaration(Java.PackageMemberAnnotationTypeDeclaration subject) throws CompileException {
      Java.PackageMemberAnnotationTypeDeclaration result = new Java.PackageMemberAnnotationTypeDeclaration(subject.getLocation(), subject.getDocComment(), this.copyModifiers(subject.getModifiers()), subject.name);

      for(Java.FieldDeclaration fd : subject.constantDeclarations) {
         result.addConstantDeclaration(this.copyFieldDeclaration(fd));
      }

      return result;
   }

   public Java.MemberTypeDeclaration copyMemberAnnotationTypeDeclaration(Java.MemberAnnotationTypeDeclaration subject) throws CompileException {
      Java.MemberAnnotationTypeDeclaration result = new Java.MemberAnnotationTypeDeclaration(subject.getLocation(), subject.getDocComment(), this.copyModifiers(subject.getModifiers()), subject.name);

      for(Java.FieldDeclaration cd : subject.constantDeclarations) {
         result.addConstantDeclaration(this.copyFieldDeclaration(cd));
      }

      for(Java.MethodDeclarator md : subject.getMethodDeclarations()) {
         result.addDeclaredMethod(this.copyMethodDeclarator(md));
      }

      for(Java.MemberTypeDeclaration mtd : subject.getMemberTypeDeclarations()) {
         result.addMemberTypeDeclaration(this.copyMemberTypeDeclaration(mtd));
      }

      return result;
   }

   public Java.TryStatement.Resource copyLocalVariableDeclaratorResource(Java.TryStatement.LocalVariableDeclaratorResource subject) throws CompileException {
      return new Java.TryStatement.LocalVariableDeclaratorResource(subject.getLocation(), this.copyModifiers(subject.modifiers), this.copyType(subject.type), this.copyVariableDeclarator(subject.variableDeclarator));
   }

   public Java.TryStatement.Resource copyVariableAccessResource(Java.TryStatement.VariableAccessResource subject) throws CompileException {
      return new Java.TryStatement.VariableAccessResource(subject.getLocation(), this.copyRvalue(subject.variableAccess));
   }

   public Java.Modifier[] copyModifiers(Java.Modifier[] subject) throws CompileException {
      Java.Modifier[] result = new Java.Modifier[subject.length];

      for(int i = 0; i < subject.length; ++i) {
         result[i] = this.copyModifier(subject[i]);
      }

      return result;
   }

   public Java.Modifier copyModifier(Java.Modifier modifier) throws CompileException {
      return (Java.Modifier)assertNotNull(modifier.accept(this.modifierCopier));
   }

   public Java.AccessModifier copyAccessModifier(Java.AccessModifier am) {
      return new Java.AccessModifier(am.keyword, am.getLocation());
   }

   public Java.TypeParameter copyTypeParameter(Java.TypeParameter subject) throws CompileException {
      return new Java.TypeParameter(subject.name, this.copyOptionalReferenceTypes(subject.bound));
   }

   private static Object assertNotNull(@Nullable Object subject) {
      assert subject != null;

      return subject;
   }
}
