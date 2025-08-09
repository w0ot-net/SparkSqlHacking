package org.codehaus.janino.util;

import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.Java;
import org.codehaus.janino.Visitor;

public class AbstractTraverser implements Traverser {
   private final Traverser delegate;
   private final Visitor.AbstractCompilationUnitVisitor abstractCompilationUnitTraverser = new Visitor.AbstractCompilationUnitVisitor() {
      @Nullable
      public Void visitCompilationUnit(Java.CompilationUnit cu) throws Throwable {
         AbstractTraverser.this.delegate.traverseCompilationUnit(cu);
         return null;
      }

      @Nullable
      public Void visitModularCompilationUnit(Java.ModularCompilationUnit mcu) throws Throwable {
         AbstractTraverser.this.delegate.traverseModularCompilationUnit(mcu);
         return null;
      }
   };
   private final Visitor.ImportVisitor importTraverser = new Visitor.ImportVisitor() {
      @Nullable
      public Void visitSingleTypeImportDeclaration(Java.AbstractCompilationUnit.SingleTypeImportDeclaration stid) throws Throwable {
         AbstractTraverser.this.delegate.traverseSingleTypeImportDeclaration(stid);
         return null;
      }

      @Nullable
      public Void visitTypeImportOnDemandDeclaration(Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration tiodd) throws Throwable {
         AbstractTraverser.this.delegate.traverseTypeImportOnDemandDeclaration(tiodd);
         return null;
      }

      @Nullable
      public Void visitSingleStaticImportDeclaration(Java.AbstractCompilationUnit.SingleStaticImportDeclaration ssid) throws Throwable {
         AbstractTraverser.this.delegate.traverseSingleStaticImportDeclaration(ssid);
         return null;
      }

      @Nullable
      public Void visitStaticImportOnDemandDeclaration(Java.AbstractCompilationUnit.StaticImportOnDemandDeclaration siodd) throws Throwable {
         AbstractTraverser.this.delegate.traverseStaticImportOnDemandDeclaration(siodd);
         return null;
      }
   };
   private final Visitor.TypeDeclarationVisitor typeDeclarationTraverser = new Visitor.TypeDeclarationVisitor() {
      @Nullable
      public Void visitAnonymousClassDeclaration(Java.AnonymousClassDeclaration acd) throws Throwable {
         AbstractTraverser.this.delegate.traverseAnonymousClassDeclaration(acd);
         return null;
      }

      @Nullable
      public Void visitLocalClassDeclaration(Java.LocalClassDeclaration lcd) throws Throwable {
         AbstractTraverser.this.delegate.traverseLocalClassDeclaration(lcd);
         return null;
      }

      @Nullable
      public Void visitPackageMemberClassDeclaration(Java.PackageMemberClassDeclaration pmcd) throws Throwable {
         AbstractTraverser.this.delegate.traversePackageMemberClassDeclaration(pmcd);
         return null;
      }

      @Nullable
      public Void visitPackageMemberInterfaceDeclaration(Java.PackageMemberInterfaceDeclaration pmid) throws Throwable {
         AbstractTraverser.this.delegate.traversePackageMemberInterfaceDeclaration(pmid);
         return null;
      }

      @Nullable
      public Void visitEnumConstant(Java.EnumConstant ec) throws Throwable {
         AbstractTraverser.this.delegate.traverseEnumConstant(ec);
         return null;
      }

      @Nullable
      public Void visitPackageMemberEnumDeclaration(Java.PackageMemberEnumDeclaration pmed) throws Throwable {
         AbstractTraverser.this.delegate.traversePackageMemberEnumDeclaration(pmed);
         return null;
      }

      @Nullable
      public Void visitMemberAnnotationTypeDeclaration(Java.MemberAnnotationTypeDeclaration matd) throws Throwable {
         AbstractTraverser.this.delegate.traverseMemberAnnotationTypeDeclaration(matd);
         return null;
      }

      @Nullable
      public Void visitPackageMemberAnnotationTypeDeclaration(Java.PackageMemberAnnotationTypeDeclaration pmatd) throws Throwable {
         AbstractTraverser.this.delegate.traversePackageMemberAnnotationTypeDeclaration(pmatd);
         return null;
      }

      @Nullable
      public Void visitMemberEnumDeclaration(Java.MemberEnumDeclaration med) throws Throwable {
         AbstractTraverser.this.delegate.traverseMemberEnumDeclaration(med);
         return null;
      }

      @Nullable
      public Void visitMemberInterfaceDeclaration(Java.MemberInterfaceDeclaration mid) throws Throwable {
         AbstractTraverser.this.delegate.traverseMemberInterfaceDeclaration(mid);
         return null;
      }

      @Nullable
      public Void visitMemberClassDeclaration(Java.MemberClassDeclaration mcd) throws Throwable {
         AbstractTraverser.this.delegate.traverseMemberClassDeclaration(mcd);
         return null;
      }
   };
   private final Visitor.RvalueVisitor rvalueTraverser = new Visitor.RvalueVisitor() {
      @Nullable
      public Void visitLvalue(Java.Lvalue lv) throws Throwable {
         lv.accept(new Visitor.LvalueVisitor() {
            @Nullable
            public Void visitAmbiguousName(Java.AmbiguousName an) throws Throwable {
               AbstractTraverser.this.delegate.traverseAmbiguousName(an);
               return null;
            }

            @Nullable
            public Void visitArrayAccessExpression(Java.ArrayAccessExpression aae) throws Throwable {
               AbstractTraverser.this.delegate.traverseArrayAccessExpression(aae);
               return null;
            }

            @Nullable
            public Void visitFieldAccess(Java.FieldAccess fa) throws Throwable {
               AbstractTraverser.this.delegate.traverseFieldAccess(fa);
               return null;
            }

            @Nullable
            public Void visitFieldAccessExpression(Java.FieldAccessExpression fae) throws Throwable {
               AbstractTraverser.this.delegate.traverseFieldAccessExpression(fae);
               return null;
            }

            @Nullable
            public Void visitSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression scfae) throws Throwable {
               AbstractTraverser.this.delegate.traverseSuperclassFieldAccessExpression(scfae);
               return null;
            }

            @Nullable
            public Void visitLocalVariableAccess(Java.LocalVariableAccess lva) throws Throwable {
               AbstractTraverser.this.delegate.traverseLocalVariableAccess(lva);
               return null;
            }

            @Nullable
            public Void visitParenthesizedExpression(Java.ParenthesizedExpression pe) throws Throwable {
               AbstractTraverser.this.delegate.traverseParenthesizedExpression(pe);
               return null;
            }
         });
         return null;
      }

      @Nullable
      public Void visitArrayLength(Java.ArrayLength al) throws Throwable {
         AbstractTraverser.this.delegate.traverseArrayLength(al);
         return null;
      }

      @Nullable
      public Void visitAssignment(Java.Assignment a) throws Throwable {
         AbstractTraverser.this.delegate.traverseAssignment(a);
         return null;
      }

      @Nullable
      public Void visitUnaryOperation(Java.UnaryOperation uo) throws Throwable {
         AbstractTraverser.this.delegate.traverseUnaryOperation(uo);
         return null;
      }

      @Nullable
      public Void visitBinaryOperation(Java.BinaryOperation bo) throws Throwable {
         AbstractTraverser.this.delegate.traverseBinaryOperation(bo);
         return null;
      }

      @Nullable
      public Void visitCast(Java.Cast c) throws Throwable {
         AbstractTraverser.this.delegate.traverseCast(c);
         return null;
      }

      @Nullable
      public Void visitClassLiteral(Java.ClassLiteral cl) throws Throwable {
         AbstractTraverser.this.delegate.traverseClassLiteral(cl);
         return null;
      }

      @Nullable
      public Void visitConditionalExpression(Java.ConditionalExpression ce) throws Throwable {
         AbstractTraverser.this.delegate.traverseConditionalExpression(ce);
         return null;
      }

      @Nullable
      public Void visitCrement(Java.Crement c) throws Throwable {
         AbstractTraverser.this.delegate.traverseCrement(c);
         return null;
      }

      @Nullable
      public Void visitInstanceof(Java.Instanceof io) throws Throwable {
         AbstractTraverser.this.delegate.traverseInstanceof(io);
         return null;
      }

      @Nullable
      public Void visitMethodInvocation(Java.MethodInvocation mi) throws Throwable {
         AbstractTraverser.this.delegate.traverseMethodInvocation(mi);
         return null;
      }

      @Nullable
      public Void visitSuperclassMethodInvocation(Java.SuperclassMethodInvocation smi) throws Throwable {
         AbstractTraverser.this.delegate.traverseSuperclassMethodInvocation(smi);
         return null;
      }

      @Nullable
      public Void visitIntegerLiteral(Java.IntegerLiteral il) throws Throwable {
         AbstractTraverser.this.delegate.traverseIntegerLiteral(il);
         return null;
      }

      @Nullable
      public Void visitFloatingPointLiteral(Java.FloatingPointLiteral fpl) throws Throwable {
         AbstractTraverser.this.delegate.traverseFloatingPointLiteral(fpl);
         return null;
      }

      @Nullable
      public Void visitBooleanLiteral(Java.BooleanLiteral bl) throws Throwable {
         AbstractTraverser.this.delegate.traverseBooleanLiteral(bl);
         return null;
      }

      @Nullable
      public Void visitCharacterLiteral(Java.CharacterLiteral cl) throws Throwable {
         AbstractTraverser.this.delegate.traverseCharacterLiteral(cl);
         return null;
      }

      @Nullable
      public Void visitStringLiteral(Java.StringLiteral sl) throws Throwable {
         AbstractTraverser.this.delegate.traverseStringLiteral(sl);
         return null;
      }

      @Nullable
      public Void visitNullLiteral(Java.NullLiteral nl) throws Throwable {
         AbstractTraverser.this.delegate.traverseNullLiteral(nl);
         return null;
      }

      @Nullable
      public Void visitSimpleConstant(Java.SimpleConstant sl) throws Throwable {
         AbstractTraverser.this.delegate.traverseSimpleLiteral(sl);
         return null;
      }

      @Nullable
      public Void visitNewAnonymousClassInstance(Java.NewAnonymousClassInstance naci) throws Throwable {
         AbstractTraverser.this.delegate.traverseNewAnonymousClassInstance(naci);
         return null;
      }

      @Nullable
      public Void visitNewArray(Java.NewArray na) throws Throwable {
         AbstractTraverser.this.delegate.traverseNewArray(na);
         return null;
      }

      @Nullable
      public Void visitNewInitializedArray(Java.NewInitializedArray nia) throws Throwable {
         AbstractTraverser.this.delegate.traverseNewInitializedArray(nia);
         return null;
      }

      @Nullable
      public Void visitNewClassInstance(Java.NewClassInstance nci) throws Throwable {
         AbstractTraverser.this.delegate.traverseNewClassInstance(nci);
         return null;
      }

      @Nullable
      public Void visitParameterAccess(Java.ParameterAccess pa) throws Throwable {
         AbstractTraverser.this.delegate.traverseParameterAccess(pa);
         return null;
      }

      @Nullable
      public Void visitQualifiedThisReference(Java.QualifiedThisReference qtr) throws Throwable {
         AbstractTraverser.this.delegate.traverseQualifiedThisReference(qtr);
         return null;
      }

      @Nullable
      public Void visitThisReference(Java.ThisReference tr) throws Throwable {
         AbstractTraverser.this.delegate.traverseThisReference(tr);
         return null;
      }

      @Nullable
      public Void visitLambdaExpression(Java.LambdaExpression le) throws Throwable {
         AbstractTraverser.this.delegate.traverseLambdaExpression(le);
         return null;
      }

      @Nullable
      public Void visitMethodReference(Java.MethodReference mr) throws Throwable {
         AbstractTraverser.this.delegate.traverseMethodReference(mr);
         return null;
      }

      @Nullable
      public Void visitInstanceCreationReference(Java.ClassInstanceCreationReference cicr) throws Throwable {
         AbstractTraverser.this.delegate.traverseClassInstanceCreationReference(cicr);
         return null;
      }

      @Nullable
      public Void visitArrayCreationReference(Java.ArrayCreationReference acr) throws Throwable {
         AbstractTraverser.this.delegate.traverseArrayCreationReference(acr);
         return null;
      }
   };
   private final Visitor.TypeBodyDeclarationVisitor typeBodyDeclarationTraverser = new Visitor.TypeBodyDeclarationVisitor() {
      @Nullable
      public Void visitFunctionDeclarator(Java.FunctionDeclarator fd) throws Throwable {
         fd.accept(new Visitor.FunctionDeclaratorVisitor() {
            @Nullable
            public Void visitConstructorDeclarator(Java.ConstructorDeclarator cd) throws Throwable {
               AbstractTraverser.this.delegate.traverseConstructorDeclarator(cd);
               return null;
            }

            @Nullable
            public Void visitMethodDeclarator(Java.MethodDeclarator md) throws Throwable {
               AbstractTraverser.this.delegate.traverseMethodDeclarator(md);
               return null;
            }
         });
         return null;
      }

      @Nullable
      public Void visitMemberAnnotationTypeDeclaration(Java.MemberAnnotationTypeDeclaration matd) throws Throwable {
         AbstractTraverser.this.delegate.traverseMemberAnnotationTypeDeclaration(matd);
         return null;
      }

      @Nullable
      public Void visitMemberInterfaceDeclaration(Java.MemberInterfaceDeclaration mid) throws Throwable {
         AbstractTraverser.this.delegate.traverseMemberInterfaceDeclaration(mid);
         return null;
      }

      @Nullable
      public Void visitMemberClassDeclaration(Java.MemberClassDeclaration mcd) throws Throwable {
         AbstractTraverser.this.delegate.traverseMemberClassDeclaration(mcd);
         return null;
      }

      @Nullable
      public Void visitMemberEnumDeclaration(Java.MemberEnumDeclaration med) throws Throwable {
         AbstractTraverser.this.delegate.traverseMemberEnumDeclaration(med);
         return null;
      }

      @Nullable
      public Void visitInitializer(Java.Initializer i) throws Throwable {
         AbstractTraverser.this.delegate.traverseInitializer(i);
         return null;
      }

      @Nullable
      public Void visitFieldDeclaration(Java.FieldDeclaration fd) throws Throwable {
         AbstractTraverser.this.delegate.traverseFieldDeclaration(fd);
         return null;
      }
   };
   private final Visitor.BlockStatementVisitor blockStatementTraverser = new Visitor.BlockStatementVisitor() {
      @Nullable
      public Void visitInitializer(Java.Initializer i) throws Throwable {
         AbstractTraverser.this.delegate.traverseInitializer(i);
         return null;
      }

      @Nullable
      public Void visitFieldDeclaration(Java.FieldDeclaration fd) throws Throwable {
         AbstractTraverser.this.delegate.traverseFieldDeclaration(fd);
         return null;
      }

      @Nullable
      public Void visitLabeledStatement(Java.LabeledStatement ls) throws Throwable {
         AbstractTraverser.this.delegate.traverseLabeledStatement(ls);
         return null;
      }

      @Nullable
      public Void visitBlock(Java.Block b) throws Throwable {
         AbstractTraverser.this.delegate.traverseBlock(b);
         return null;
      }

      @Nullable
      public Void visitExpressionStatement(Java.ExpressionStatement es) throws Throwable {
         AbstractTraverser.this.delegate.traverseExpressionStatement(es);
         return null;
      }

      @Nullable
      public Void visitIfStatement(Java.IfStatement is) throws Throwable {
         AbstractTraverser.this.delegate.traverseIfStatement(is);
         return null;
      }

      @Nullable
      public Void visitForStatement(Java.ForStatement fs) throws Throwable {
         AbstractTraverser.this.delegate.traverseForStatement(fs);
         return null;
      }

      @Nullable
      public Void visitForEachStatement(Java.ForEachStatement fes) throws Throwable {
         AbstractTraverser.this.delegate.traverseForEachStatement(fes);
         return null;
      }

      @Nullable
      public Void visitWhileStatement(Java.WhileStatement ws) throws Throwable {
         AbstractTraverser.this.delegate.traverseWhileStatement(ws);
         return null;
      }

      @Nullable
      public Void visitTryStatement(Java.TryStatement ts) throws Throwable {
         AbstractTraverser.this.delegate.traverseTryStatement(ts);
         return null;
      }

      @Nullable
      public Void visitSwitchStatement(Java.SwitchStatement ss) throws Throwable {
         AbstractTraverser.this.delegate.traverseSwitchStatement(ss);
         return null;
      }

      @Nullable
      public Void visitSynchronizedStatement(Java.SynchronizedStatement ss) throws Throwable {
         AbstractTraverser.this.delegate.traverseSynchronizedStatement(ss);
         return null;
      }

      @Nullable
      public Void visitDoStatement(Java.DoStatement ds) throws Throwable {
         AbstractTraverser.this.delegate.traverseDoStatement(ds);
         return null;
      }

      @Nullable
      public Void visitLocalVariableDeclarationStatement(Java.LocalVariableDeclarationStatement lvds) throws Throwable {
         AbstractTraverser.this.delegate.traverseLocalVariableDeclarationStatement(lvds);
         return null;
      }

      @Nullable
      public Void visitReturnStatement(Java.ReturnStatement rs) throws Throwable {
         AbstractTraverser.this.delegate.traverseReturnStatement(rs);
         return null;
      }

      @Nullable
      public Void visitThrowStatement(Java.ThrowStatement ts) throws Throwable {
         AbstractTraverser.this.delegate.traverseThrowStatement(ts);
         return null;
      }

      @Nullable
      public Void visitBreakStatement(Java.BreakStatement bs) throws Throwable {
         AbstractTraverser.this.delegate.traverseBreakStatement(bs);
         return null;
      }

      @Nullable
      public Void visitContinueStatement(Java.ContinueStatement cs) throws Throwable {
         AbstractTraverser.this.delegate.traverseContinueStatement(cs);
         return null;
      }

      @Nullable
      public Void visitAssertStatement(Java.AssertStatement as) throws Throwable {
         AbstractTraverser.this.delegate.traverseAssertStatement(as);
         return null;
      }

      @Nullable
      public Void visitEmptyStatement(Java.EmptyStatement es) throws Throwable {
         AbstractTraverser.this.delegate.traverseEmptyStatement(es);
         return null;
      }

      @Nullable
      public Void visitLocalClassDeclarationStatement(Java.LocalClassDeclarationStatement lcds) throws Throwable {
         AbstractTraverser.this.delegate.traverseLocalClassDeclarationStatement(lcds);
         return null;
      }

      @Nullable
      public Void visitAlternateConstructorInvocation(Java.AlternateConstructorInvocation aci) throws Throwable {
         AbstractTraverser.this.delegate.traverseAlternateConstructorInvocation(aci);
         return null;
      }

      @Nullable
      public Void visitSuperConstructorInvocation(Java.SuperConstructorInvocation sci) throws Throwable {
         AbstractTraverser.this.delegate.traverseSuperConstructorInvocation(sci);
         return null;
      }
   };
   private final Visitor.AtomVisitor atomTraverser = new Visitor.AtomVisitor() {
      @Nullable
      public Void visitRvalue(Java.Rvalue rv) throws Throwable {
         rv.accept(AbstractTraverser.this.rvalueTraverser);
         return null;
      }

      @Nullable
      public Void visitPackage(Java.Package p) throws Throwable {
         AbstractTraverser.this.delegate.traversePackage(p);
         return null;
      }

      @Nullable
      public Void visitType(Java.Type t) throws Throwable {
         t.accept(new Visitor.TypeVisitor() {
            @Nullable
            public Void visitArrayType(Java.ArrayType at) throws Throwable {
               AbstractTraverser.this.delegate.traverseArrayType(at);
               return null;
            }

            @Nullable
            public Void visitPrimitiveType(Java.PrimitiveType bt) throws Throwable {
               AbstractTraverser.this.delegate.traversePrimitiveType(bt);
               return null;
            }

            @Nullable
            public Void visitReferenceType(Java.ReferenceType rt) throws Throwable {
               AbstractTraverser.this.delegate.traverseReferenceType(rt);
               return null;
            }

            @Nullable
            public Void visitRvalueMemberType(Java.RvalueMemberType rmt) throws Throwable {
               AbstractTraverser.this.delegate.traverseRvalueMemberType(rmt);
               return null;
            }

            @Nullable
            public Void visitSimpleType(Java.SimpleType st) throws Throwable {
               AbstractTraverser.this.delegate.traverseSimpleType(st);
               return null;
            }
         });
         return null;
      }

      @Nullable
      public Void visitConstructorInvocation(Java.ConstructorInvocation ci) throws Throwable {
         ci.accept(new Visitor.ConstructorInvocationVisitor() {
            @Nullable
            public Void visitAlternateConstructorInvocation(Java.AlternateConstructorInvocation aci) throws Throwable {
               AbstractTraverser.this.delegate.traverseAlternateConstructorInvocation(aci);
               return null;
            }

            @Nullable
            public Void visitSuperConstructorInvocation(Java.SuperConstructorInvocation sci) throws Throwable {
               AbstractTraverser.this.delegate.traverseSuperConstructorInvocation(sci);
               return null;
            }
         });
         return null;
      }
   };
   private final Visitor.ArrayInitializerOrRvalueVisitor arrayInitializerOrRvalueTraverser = new Visitor.ArrayInitializerOrRvalueVisitor() {
      @Nullable
      public Void visitArrayInitializer(Java.ArrayInitializer ai) throws Throwable {
         for(Java.ArrayInitializerOrRvalue value : ai.values) {
            AbstractTraverser.this.traverseArrayInitializerOrRvalue(value);
         }

         return null;
      }

      @Nullable
      public Void visitRvalue(Java.Rvalue rvalue) throws Throwable {
         AbstractTraverser.this.traverseRvalue(rvalue);
         return null;
      }
   };
   private final Visitor.ElementValueVisitor elementValueTraverser = new Visitor.ElementValueVisitor() {
      @Nullable
      public Void visitRvalue(Java.Rvalue rv) throws Throwable {
         rv.accept(AbstractTraverser.this.rvalueTraverser);
         return null;
      }

      @Nullable
      public Void visitElementValueArrayInitializer(Java.ElementValueArrayInitializer evai) throws Throwable {
         AbstractTraverser.this.delegate.traverseElementValueArrayInitializer(evai);
         return null;
      }

      @Nullable
      public Void visitAnnotation(Java.Annotation a) throws Throwable {
         AbstractTraverser.this.delegate.traverseAnnotation(a);
         return null;
      }
   };
   private final Visitor.AnnotationVisitor annotationTraverser = new Visitor.AnnotationVisitor() {
      @Nullable
      public Void visitMarkerAnnotation(Java.MarkerAnnotation ma) throws Throwable {
         AbstractTraverser.this.delegate.traverseMarkerAnnotation(ma);
         return null;
      }

      @Nullable
      public Void visitNormalAnnotation(Java.NormalAnnotation na) throws Throwable {
         AbstractTraverser.this.delegate.traverseNormalAnnotation(na);
         return null;
      }

      @Nullable
      public Void visitSingleElementAnnotation(Java.SingleElementAnnotation sea) throws Throwable {
         AbstractTraverser.this.delegate.traverseSingleElementAnnotation(sea);
         return null;
      }
   };
   private final Visitor.TryStatementResourceVisitor resourceTraverser = new Visitor.TryStatementResourceVisitor() {
      @Nullable
      public Void visitLocalVariableDeclaratorResource(Java.TryStatement.LocalVariableDeclaratorResource lvdr) throws Throwable {
         AbstractTraverser.this.delegate.traverseLocalVariableDeclaratorResource(lvdr);
         return null;
      }

      @Nullable
      public Void visitVariableAccessResource(Java.TryStatement.VariableAccessResource var) throws Throwable {
         AbstractTraverser.this.delegate.traverseVariableAccessResource(var);
         return null;
      }
   };

   public AbstractTraverser() {
      this.delegate = this;
   }

   public AbstractTraverser(Traverser delegate) {
      this.delegate = delegate;
   }

   public void visitAbstractCompilationUnit(Java.AbstractCompilationUnit acu) throws Throwable {
      acu.accept(this.abstractCompilationUnitTraverser);
   }

   public void visitImportDeclaration(Java.AbstractCompilationUnit.ImportDeclaration id) throws Throwable {
      id.accept(this.importTraverser);
   }

   public void visitTypeDeclaration(Java.TypeDeclaration td) throws Throwable {
      td.accept(this.typeDeclarationTraverser);
   }

   public void visitTypeBodyDeclaration(Java.TypeBodyDeclaration tbd) throws Throwable {
      tbd.accept(this.typeBodyDeclarationTraverser);
   }

   public void visitBlockStatement(Java.BlockStatement bs) throws Throwable {
      bs.accept(this.blockStatementTraverser);
   }

   public void visitAtom(Java.Atom a) throws Throwable {
      a.accept(this.atomTraverser);
   }

   public void visitElementValue(Java.ElementValue ev) throws Throwable {
      ev.accept(this.elementValueTraverser);
   }

   public void visitAnnotation(Java.Annotation a) throws Throwable {
      a.accept(this.annotationTraverser);
   }

   public void traverseAbstractCompilationUnit(Java.AbstractCompilationUnit acu) throws Throwable {
   }

   public void traverseCompilationUnit(Java.CompilationUnit cu) throws Throwable {
      for(Java.AbstractCompilationUnit.ImportDeclaration id : cu.importDeclarations) {
         id.accept(this.importTraverser);
      }

      for(Java.PackageMemberTypeDeclaration pmtd : cu.packageMemberTypeDeclarations) {
         pmtd.accept(this.typeDeclarationTraverser);
      }

   }

   public void traverseModularCompilationUnit(Java.ModularCompilationUnit mcu) throws Throwable {
      for(Java.AbstractCompilationUnit.ImportDeclaration id : mcu.importDeclarations) {
         id.accept(this.importTraverser);
      }

   }

   public void traverseSingleTypeImportDeclaration(Java.AbstractCompilationUnit.SingleTypeImportDeclaration stid) throws Throwable {
      this.traverseImportDeclaration(stid);
   }

   public void traverseTypeImportOnDemandDeclaration(Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration tiodd) throws Throwable {
      this.traverseImportDeclaration(tiodd);
   }

   public void traverseSingleStaticImportDeclaration(Java.AbstractCompilationUnit.SingleStaticImportDeclaration stid) throws Throwable {
      this.traverseImportDeclaration(stid);
   }

   public void traverseStaticImportOnDemandDeclaration(Java.AbstractCompilationUnit.StaticImportOnDemandDeclaration siodd) throws Throwable {
      this.traverseImportDeclaration(siodd);
   }

   public void traverseImportDeclaration(Java.AbstractCompilationUnit.ImportDeclaration id) throws Throwable {
      this.traverseLocated(id);
   }

   public void traverseAnonymousClassDeclaration(Java.AnonymousClassDeclaration acd) throws Throwable {
      acd.baseType.accept(this.atomTraverser);
      this.traverseClassDeclaration(acd);
   }

   public void traverseLocalClassDeclaration(Java.LocalClassDeclaration lcd) throws Throwable {
      this.traverseNamedClassDeclaration(lcd);
   }

   public void traversePackageMemberClassDeclaration(Java.PackageMemberClassDeclaration pmcd) throws Throwable {
      this.traverseNamedClassDeclaration(pmcd);
   }

   public void traverseMemberInterfaceDeclaration(Java.MemberInterfaceDeclaration mid) throws Throwable {
      this.traverseInterfaceDeclaration(mid);
   }

   public void traversePackageMemberInterfaceDeclaration(Java.PackageMemberInterfaceDeclaration pmid) throws Throwable {
      this.traverseInterfaceDeclaration(pmid);
   }

   public void traverseMemberClassDeclaration(Java.MemberClassDeclaration mcd) throws Throwable {
      this.traverseNamedClassDeclaration(mcd);
   }

   public void traverseConstructorDeclarator(Java.ConstructorDeclarator cd) throws Throwable {
      if (cd.constructorInvocation != null) {
         cd.constructorInvocation.accept((Visitor.BlockStatementVisitor)this.blockStatementTraverser);
      }

      this.traverseFunctionDeclarator(cd);
   }

   public void traverseInitializer(Java.Initializer i) throws Throwable {
      i.block.accept(this.blockStatementTraverser);
      this.traverseAbstractTypeBodyDeclaration(i);
   }

   public void traverseMethodDeclarator(Java.MethodDeclarator md) throws Throwable {
      this.traverseFunctionDeclarator(md);
   }

   public void traverseFieldDeclaration(Java.FieldDeclaration fd) throws Throwable {
      fd.type.accept(this.atomTraverser);

      for(Java.VariableDeclarator vd : fd.variableDeclarators) {
         Java.ArrayInitializerOrRvalue initializer = vd.initializer;
         if (initializer != null) {
            this.traverseArrayInitializerOrRvalue(initializer);
         }
      }

      this.traverseStatement(fd);
   }

   public void traverseLabeledStatement(Java.LabeledStatement ls) throws Throwable {
      ls.body.accept(this.blockStatementTraverser);
      this.traverseBreakableStatement(ls);
   }

   public void traverseBlock(Java.Block b) throws Throwable {
      for(Java.BlockStatement bs : b.statements) {
         bs.accept(this.blockStatementTraverser);
      }

      this.traverseStatement(b);
   }

   public void traverseExpressionStatement(Java.ExpressionStatement es) throws Throwable {
      es.rvalue.accept(this.rvalueTraverser);
      this.traverseStatement(es);
   }

   public void traverseIfStatement(Java.IfStatement is) throws Throwable {
      is.condition.accept(this.rvalueTraverser);
      is.thenStatement.accept(this.blockStatementTraverser);
      if (is.elseStatement != null) {
         is.elseStatement.accept(this.blockStatementTraverser);
      }

      this.traverseStatement(is);
   }

   public void traverseForStatement(Java.ForStatement fs) throws Throwable {
      if (fs.init != null) {
         fs.init.accept(this.blockStatementTraverser);
      }

      if (fs.condition != null) {
         fs.condition.accept(this.rvalueTraverser);
      }

      if (fs.update != null) {
         for(Java.Rvalue rv : fs.update) {
            rv.accept(this.rvalueTraverser);
         }
      }

      fs.body.accept(this.blockStatementTraverser);
      this.traverseContinuableStatement(fs);
   }

   public void traverseForEachStatement(Java.ForEachStatement fes) throws Throwable {
      this.traverseFormalParameter(fes.currentElement);
      fes.expression.accept(this.rvalueTraverser);
      fes.body.accept(this.blockStatementTraverser);
      this.traverseContinuableStatement(fes);
   }

   public void traverseWhileStatement(Java.WhileStatement ws) throws Throwable {
      ws.condition.accept(this.rvalueTraverser);
      ws.body.accept(this.blockStatementTraverser);
      this.traverseContinuableStatement(ws);
   }

   public void traverseTryStatement(Java.TryStatement ts) throws Throwable {
      for(Java.TryStatement.Resource r : ts.resources) {
         r.accept(this.resourceTraverser);
      }

      ts.body.accept(this.blockStatementTraverser);

      for(Java.CatchClause cc : ts.catchClauses) {
         cc.body.accept(this.blockStatementTraverser);
      }

      if (ts.finallY != null) {
         ts.finallY.accept(this.blockStatementTraverser);
      }

      this.traverseStatement(ts);
   }

   public void traverseSwitchStatement(Java.SwitchStatement ss) throws Throwable {
      ss.condition.accept(this.rvalueTraverser);

      for(Java.SwitchStatement.SwitchBlockStatementGroup sbsg : ss.sbsgs) {
         for(Java.Rvalue cl : sbsg.caseLabels) {
            cl.accept(this.rvalueTraverser);
         }

         for(Java.BlockStatement bs : sbsg.blockStatements) {
            bs.accept(this.blockStatementTraverser);
         }

         this.traverseLocated(sbsg);
      }

      this.traverseBreakableStatement(ss);
   }

   public void traverseSynchronizedStatement(Java.SynchronizedStatement ss) throws Throwable {
      ss.expression.accept(this.rvalueTraverser);
      ss.body.accept(this.blockStatementTraverser);
      this.traverseStatement(ss);
   }

   public void traverseDoStatement(Java.DoStatement ds) throws Throwable {
      ds.body.accept(this.blockStatementTraverser);
      ds.condition.accept(this.rvalueTraverser);
      this.traverseContinuableStatement(ds);
   }

   public void traverseLocalVariableDeclarationStatement(Java.LocalVariableDeclarationStatement lvds) throws Throwable {
      lvds.type.accept(this.atomTraverser);

      for(Java.VariableDeclarator vd : lvds.variableDeclarators) {
         Java.ArrayInitializerOrRvalue initializer = vd.initializer;
         if (initializer != null) {
            this.traverseArrayInitializerOrRvalue(initializer);
         }
      }

      this.traverseStatement(lvds);
   }

   public void traverseReturnStatement(Java.ReturnStatement rs) throws Throwable {
      if (rs.returnValue != null) {
         rs.returnValue.accept(this.rvalueTraverser);
      }

      this.traverseStatement(rs);
   }

   public void traverseThrowStatement(Java.ThrowStatement ts) throws Throwable {
      ts.expression.accept(this.rvalueTraverser);
      this.traverseStatement(ts);
   }

   public void traverseBreakStatement(Java.BreakStatement bs) throws Throwable {
      this.traverseStatement(bs);
   }

   public void traverseContinueStatement(Java.ContinueStatement cs) throws Throwable {
      this.traverseStatement(cs);
   }

   public void traverseAssertStatement(Java.AssertStatement as) throws Throwable {
      as.expression1.accept(this.rvalueTraverser);
      if (as.expression2 != null) {
         as.expression2.accept(this.rvalueTraverser);
      }

      this.traverseStatement(as);
   }

   public void traverseEmptyStatement(Java.EmptyStatement es) throws Throwable {
      this.traverseStatement(es);
   }

   public void traverseLocalClassDeclarationStatement(Java.LocalClassDeclarationStatement lcds) throws Throwable {
      lcds.lcd.accept(this.typeDeclarationTraverser);
      this.traverseStatement(lcds);
   }

   public void traversePackage(Java.Package p) throws Throwable {
      this.traverseAtom(p);
   }

   public void traverseArrayLength(Java.ArrayLength al) throws Throwable {
      al.lhs.accept(this.rvalueTraverser);
      this.traverseRvalue(al);
   }

   public void traverseAssignment(Java.Assignment a) throws Throwable {
      a.lhs.accept(this.rvalueTraverser);
      a.rhs.accept(this.rvalueTraverser);
      this.traverseRvalue(a);
   }

   public void traverseUnaryOperation(Java.UnaryOperation uo) throws Throwable {
      uo.operand.accept(this.rvalueTraverser);
      this.traverseBooleanRvalue(uo);
   }

   public void traverseBinaryOperation(Java.BinaryOperation bo) throws Throwable {
      bo.lhs.accept(this.rvalueTraverser);
      bo.rhs.accept(this.rvalueTraverser);
      this.traverseBooleanRvalue(bo);
   }

   public void traverseCast(Java.Cast c) throws Throwable {
      c.targetType.accept(this.atomTraverser);
      c.value.accept(this.rvalueTraverser);
      this.traverseRvalue(c);
   }

   public void traverseClassLiteral(Java.ClassLiteral cl) throws Throwable {
      cl.type.accept(this.atomTraverser);
      this.traverseRvalue(cl);
   }

   public void traverseConditionalExpression(Java.ConditionalExpression ce) throws Throwable {
      ce.lhs.accept(this.rvalueTraverser);
      ce.mhs.accept(this.rvalueTraverser);
      ce.rhs.accept(this.rvalueTraverser);
      this.traverseRvalue(ce);
   }

   public void traverseCrement(Java.Crement c) throws Throwable {
      c.operand.accept(this.rvalueTraverser);
      this.traverseRvalue(c);
   }

   public void traverseInstanceof(Java.Instanceof io) throws Throwable {
      io.lhs.accept(this.rvalueTraverser);
      io.rhs.accept(this.atomTraverser);
      this.traverseRvalue(io);
   }

   public void traverseMethodInvocation(Java.MethodInvocation mi) throws Throwable {
      if (mi.target != null) {
         mi.target.accept(this.atomTraverser);
      }

      this.traverseInvocation(mi);
   }

   public void traverseSuperclassMethodInvocation(Java.SuperclassMethodInvocation smi) throws Throwable {
      this.traverseInvocation(smi);
   }

   public void traverseLiteral(Java.Literal l) throws Throwable {
      this.traverseRvalue(l);
   }

   public void traverseIntegerLiteral(Java.IntegerLiteral il) throws Throwable {
      this.traverseLiteral(il);
   }

   public void traverseFloatingPointLiteral(Java.FloatingPointLiteral fpl) throws Throwable {
      this.traverseLiteral(fpl);
   }

   public void traverseBooleanLiteral(Java.BooleanLiteral bl) throws Throwable {
      this.traverseLiteral(bl);
   }

   public void traverseCharacterLiteral(Java.CharacterLiteral cl) throws Throwable {
      this.traverseLiteral(cl);
   }

   public void traverseStringLiteral(Java.StringLiteral sl) throws Throwable {
      this.traverseLiteral(sl);
   }

   public void traverseNullLiteral(Java.NullLiteral nl) throws Throwable {
      this.traverseLiteral(nl);
   }

   public void traverseSimpleLiteral(Java.SimpleConstant sl) throws Throwable {
      this.traverseRvalue(sl);
   }

   public void traverseNewAnonymousClassInstance(Java.NewAnonymousClassInstance naci) throws Throwable {
      if (naci.qualification != null) {
         naci.qualification.accept(this.rvalueTraverser);
      }

      naci.anonymousClassDeclaration.accept(this.typeDeclarationTraverser);

      for(Java.Rvalue argument : naci.arguments) {
         argument.accept(this.rvalueTraverser);
      }

      this.traverseRvalue(naci);
   }

   public void traverseNewArray(Java.NewArray na) throws Throwable {
      na.type.accept(this.atomTraverser);

      for(Java.Rvalue dimExpr : na.dimExprs) {
         dimExpr.accept(this.rvalueTraverser);
      }

      this.traverseRvalue(na);
   }

   public void traverseNewInitializedArray(Java.NewInitializedArray nia) throws Throwable {
      assert nia.arrayType != null;

      nia.arrayType.accept(this.atomTraverser);
      this.traverseArrayInitializerOrRvalue(nia.arrayInitializer);
   }

   public void traverseArrayInitializerOrRvalue(Java.ArrayInitializerOrRvalue aiorv) throws Throwable {
      aiorv.accept(this.arrayInitializerOrRvalueTraverser);
   }

   public void traverseNewClassInstance(Java.NewClassInstance nci) throws Throwable {
      if (nci.qualification != null) {
         nci.qualification.accept(this.rvalueTraverser);
      }

      if (nci.type != null) {
         nci.type.accept(this.atomTraverser);
      }

      for(Java.Rvalue argument : nci.arguments) {
         argument.accept(this.rvalueTraverser);
      }

      this.traverseRvalue(nci);
   }

   public void traverseParameterAccess(Java.ParameterAccess pa) throws Throwable {
      this.traverseRvalue(pa);
   }

   public void traverseQualifiedThisReference(Java.QualifiedThisReference qtr) throws Throwable {
      qtr.qualification.accept(this.atomTraverser);
      this.traverseRvalue(qtr);
   }

   public void traverseThisReference(Java.ThisReference tr) throws Throwable {
      this.traverseRvalue(tr);
   }

   public void traverseLambdaExpression(Java.LambdaExpression le) throws Throwable {
      this.traverseRvalue(le);
   }

   public void traverseMethodReference(Java.MethodReference mr) throws Throwable {
      this.traverseRvalue(mr);
   }

   public void traverseClassInstanceCreationReference(Java.ClassInstanceCreationReference cicr) throws Throwable {
      this.traverseRvalue(cicr);
   }

   public void traverseArrayCreationReference(Java.ArrayCreationReference acr) throws Throwable {
      this.traverseRvalue(acr);
   }

   public void traverseArrayType(Java.ArrayType at) throws Throwable {
      at.componentType.accept(this.atomTraverser);
      this.traverseType(at);
   }

   public void traversePrimitiveType(Java.PrimitiveType bt) throws Throwable {
      this.traverseType(bt);
   }

   public void traverseReferenceType(Java.ReferenceType rt) throws Throwable {
      for(Java.Annotation a : rt.annotations) {
         this.visitAnnotation(a);
      }

      this.traverseType(rt);
   }

   public void traverseRvalueMemberType(Java.RvalueMemberType rmt) throws Throwable {
      rmt.rvalue.accept(this.rvalueTraverser);
      this.traverseType(rmt);
   }

   public void traverseSimpleType(Java.SimpleType st) throws Throwable {
      this.traverseType(st);
   }

   public void traverseAlternateConstructorInvocation(Java.AlternateConstructorInvocation aci) throws Throwable {
      this.traverseConstructorInvocation(aci);
   }

   public void traverseSuperConstructorInvocation(Java.SuperConstructorInvocation sci) throws Throwable {
      if (sci.qualification != null) {
         sci.qualification.accept(this.rvalueTraverser);
      }

      this.traverseConstructorInvocation(sci);
   }

   public void traverseAmbiguousName(Java.AmbiguousName an) throws Throwable {
      this.traverseLvalue(an);
   }

   public void traverseArrayAccessExpression(Java.ArrayAccessExpression aae) throws Throwable {
      aae.lhs.accept(this.rvalueTraverser);
      aae.index.accept(this.atomTraverser);
      this.traverseLvalue(aae);
   }

   public void traverseFieldAccess(Java.FieldAccess fa) throws Throwable {
      fa.lhs.accept(this.atomTraverser);
      this.traverseLvalue(fa);
   }

   public void traverseFieldAccessExpression(Java.FieldAccessExpression fae) throws Throwable {
      fae.lhs.accept(this.atomTraverser);
      this.traverseLvalue(fae);
   }

   public void traverseSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression scfae) throws Throwable {
      if (scfae.qualification != null) {
         scfae.qualification.accept(this.atomTraverser);
      }

      this.traverseLvalue(scfae);
   }

   public void traverseLocalVariableAccess(Java.LocalVariableAccess lva) throws Throwable {
      this.traverseLvalue(lva);
   }

   public void traverseParenthesizedExpression(Java.ParenthesizedExpression pe) throws Throwable {
      pe.value.accept(this.rvalueTraverser);
      this.traverseLvalue(pe);
   }

   public void traverseElementValueArrayInitializer(Java.ElementValueArrayInitializer evai) throws Throwable {
      for(Java.ElementValue elementValue : evai.elementValues) {
         elementValue.accept(this.elementValueTraverser);
      }

      this.traverseElementValue(evai);
   }

   public void traverseElementValue(Java.ElementValue ev) throws Throwable {
   }

   public void traverseSingleElementAnnotation(Java.SingleElementAnnotation sea) throws Throwable {
      sea.type.accept(this.atomTraverser);
      sea.elementValue.accept(this.elementValueTraverser);
      this.traverseAnnotation(sea);
   }

   public void traverseAnnotation(Java.Annotation a) throws Throwable {
   }

   public void traverseNormalAnnotation(Java.NormalAnnotation na) throws Throwable {
      na.type.accept(this.atomTraverser);

      for(Java.ElementValuePair elementValuePair : na.elementValuePairs) {
         elementValuePair.elementValue.accept(this.elementValueTraverser);
      }

      this.traverseAnnotation(na);
   }

   public void traverseMarkerAnnotation(Java.MarkerAnnotation ma) throws Throwable {
      ma.type.accept(this.atomTraverser);
      this.traverseAnnotation(ma);
   }

   public void traverseClassDeclaration(Java.AbstractClassDeclaration cd) throws Throwable {
      for(Java.ConstructorDeclarator ctord : cd.constructors) {
         ctord.accept(this.typeBodyDeclarationTraverser);
      }

      for(Java.BlockStatement fdoi : cd.fieldDeclarationsAndInitializers) {
         fdoi.accept(this.blockStatementTraverser);
      }

      this.traverseAbstractTypeDeclaration(cd);
   }

   public void traverseAbstractTypeDeclaration(Java.AbstractTypeDeclaration atd) throws Throwable {
      for(Java.Annotation a : atd.getAnnotations()) {
         this.traverseAnnotation(a);
      }

      for(Java.NamedTypeDeclaration mtd : atd.getMemberTypeDeclarations()) {
         mtd.accept(this.typeDeclarationTraverser);
      }

      for(Java.MethodDeclarator md : atd.getMethodDeclarations()) {
         this.traverseMethodDeclarator(md);
      }

   }

   public void traverseNamedClassDeclaration(Java.NamedClassDeclaration ncd) throws Throwable {
      for(Java.Type implementedType : ncd.implementedTypes) {
         implementedType.accept(this.atomTraverser);
      }

      if (ncd.extendedType != null) {
         ncd.extendedType.accept(this.atomTraverser);
      }

      this.traverseClassDeclaration(ncd);
   }

   public void traverseInterfaceDeclaration(Java.InterfaceDeclaration id) throws Throwable {
      for(Java.TypeBodyDeclaration cd : id.constantDeclarations) {
         cd.accept(this.typeBodyDeclarationTraverser);
      }

      for(Java.Type extendedType : id.extendedTypes) {
         extendedType.accept(this.atomTraverser);
      }

      this.traverseAbstractTypeDeclaration(id);
   }

   public void traverseFunctionDeclarator(Java.FunctionDeclarator fd) throws Throwable {
      this.traverseFormalParameters(fd.formalParameters);
      if (fd.statements != null) {
         for(Java.BlockStatement bs : fd.statements) {
            bs.accept(this.blockStatementTraverser);
         }
      }

   }

   public void traverseFormalParameters(Java.FunctionDeclarator.FormalParameters formalParameters) throws Throwable {
      for(Java.FunctionDeclarator.FormalParameter formalParameter : formalParameters.parameters) {
         this.traverseFormalParameter(formalParameter);
      }

   }

   public void traverseFormalParameter(Java.FunctionDeclarator.FormalParameter formalParameter) throws Throwable {
      formalParameter.type.accept(this.atomTraverser);
   }

   public void traverseAbstractTypeBodyDeclaration(Java.AbstractTypeBodyDeclaration atbd) throws Throwable {
      this.traverseLocated(atbd);
   }

   public void traverseStatement(Java.Statement s) throws Throwable {
      this.traverseLocated(s);
   }

   public void traverseBreakableStatement(Java.BreakableStatement bs) throws Throwable {
      this.traverseStatement(bs);
   }

   public void traverseContinuableStatement(Java.ContinuableStatement cs) throws Throwable {
      this.traverseBreakableStatement(cs);
   }

   public void traverseRvalue(Java.Rvalue rv) throws Throwable {
      this.traverseAtom(rv);
   }

   public void traverseBooleanRvalue(Java.BooleanRvalue brv) throws Throwable {
      this.traverseRvalue(brv);
   }

   public void traverseInvocation(Java.Invocation i) throws Throwable {
      for(Java.Rvalue argument : i.arguments) {
         argument.accept(this.rvalueTraverser);
      }

      this.traverseRvalue(i);
   }

   public void traverseConstructorInvocation(Java.ConstructorInvocation ci) throws Throwable {
      for(Java.Rvalue argument : ci.arguments) {
         argument.accept(this.rvalueTraverser);
      }

      this.traverseAtom(ci);
   }

   public void traverseEnumConstant(Java.EnumConstant ec) throws Throwable {
      for(Java.ConstructorDeclarator cd : ec.constructors) {
         this.traverseConstructorDeclarator(cd);
      }

      if (ec.arguments != null) {
         for(Java.Rvalue a : ec.arguments) {
            this.traverseRvalue(a);
         }
      }

      this.traverseAbstractTypeDeclaration(ec);
   }

   public void traversePackageMemberEnumDeclaration(Java.PackageMemberEnumDeclaration pmed) throws Throwable {
      this.traversePackageMemberClassDeclaration(pmed);
   }

   public void traverseMemberEnumDeclaration(Java.MemberEnumDeclaration med) throws Throwable {
      this.traverseMemberClassDeclaration(med);
   }

   public void traversePackageMemberAnnotationTypeDeclaration(Java.PackageMemberAnnotationTypeDeclaration pmatd) throws Throwable {
      this.traversePackageMemberInterfaceDeclaration(pmatd);
   }

   public void traverseMemberAnnotationTypeDeclaration(Java.MemberAnnotationTypeDeclaration matd) throws Throwable {
      this.traverseMemberInterfaceDeclaration(matd);
   }

   public void traverseLvalue(Java.Lvalue lv) throws Throwable {
      this.traverseRvalue(lv);
   }

   public void traverseType(Java.Type t) throws Throwable {
      this.traverseAtom(t);
   }

   public void traverseAtom(Java.Atom a) throws Throwable {
      this.traverseLocated(a);
   }

   public void traverseLocated(Java.Located l) throws Throwable {
   }

   public void traverseLocalVariableDeclaratorResource(Java.TryStatement.LocalVariableDeclaratorResource lvdr) throws Throwable {
      lvdr.type.accept(this.atomTraverser);
      Java.ArrayInitializerOrRvalue i = lvdr.variableDeclarator.initializer;
      if (i != null) {
         this.traverseArrayInitializerOrRvalue(i);
      }

   }

   public void traverseVariableAccessResource(Java.TryStatement.VariableAccessResource var) throws Throwable {
      var.variableAccess.accept(this.rvalueTraverser);
   }
}
