package org.codehaus.janino;

import org.codehaus.commons.nullanalysis.Nullable;

public final class Visitor {
   private Visitor() {
   }

   public interface AbstractCompilationUnitVisitor {
      @Nullable
      Object visitCompilationUnit(Java.CompilationUnit var1) throws Throwable;

      @Nullable
      Object visitModularCompilationUnit(Java.ModularCompilationUnit var1) throws Throwable;
   }

   public interface AnnotationVisitor {
      @Nullable
      Object visitMarkerAnnotation(Java.MarkerAnnotation var1) throws Throwable;

      @Nullable
      Object visitNormalAnnotation(Java.NormalAnnotation var1) throws Throwable;

      @Nullable
      Object visitSingleElementAnnotation(Java.SingleElementAnnotation var1) throws Throwable;
   }

   public interface ArrayInitializerOrRvalueVisitor {
      @Nullable
      Object visitArrayInitializer(Java.ArrayInitializer var1) throws Throwable;

      @Nullable
      Object visitRvalue(Java.Rvalue var1) throws Throwable;
   }

   public interface AtomVisitor {
      @Nullable
      Object visitPackage(Java.Package var1) throws Throwable;

      @Nullable
      Object visitRvalue(Java.Rvalue var1) throws Throwable;

      @Nullable
      Object visitType(Java.Type var1) throws Throwable;

      @Nullable
      Object visitConstructorInvocation(Java.ConstructorInvocation var1) throws Throwable;
   }

   public interface BlockStatementVisitor extends FieldDeclarationOrInitializerVisitor {
      @Nullable
      Object visitLabeledStatement(Java.LabeledStatement var1) throws Throwable;

      @Nullable
      Object visitBlock(Java.Block var1) throws Throwable;

      @Nullable
      Object visitExpressionStatement(Java.ExpressionStatement var1) throws Throwable;

      @Nullable
      Object visitIfStatement(Java.IfStatement var1) throws Throwable;

      @Nullable
      Object visitForStatement(Java.ForStatement var1) throws Throwable;

      @Nullable
      Object visitForEachStatement(Java.ForEachStatement var1) throws Throwable;

      @Nullable
      Object visitWhileStatement(Java.WhileStatement var1) throws Throwable;

      @Nullable
      Object visitTryStatement(Java.TryStatement var1) throws Throwable;

      @Nullable
      Object visitSwitchStatement(Java.SwitchStatement var1) throws Throwable;

      @Nullable
      Object visitSynchronizedStatement(Java.SynchronizedStatement var1) throws Throwable;

      @Nullable
      Object visitDoStatement(Java.DoStatement var1) throws Throwable;

      @Nullable
      Object visitLocalVariableDeclarationStatement(Java.LocalVariableDeclarationStatement var1) throws Throwable;

      @Nullable
      Object visitReturnStatement(Java.ReturnStatement var1) throws Throwable;

      @Nullable
      Object visitThrowStatement(Java.ThrowStatement var1) throws Throwable;

      @Nullable
      Object visitBreakStatement(Java.BreakStatement var1) throws Throwable;

      @Nullable
      Object visitContinueStatement(Java.ContinueStatement var1) throws Throwable;

      @Nullable
      Object visitAssertStatement(Java.AssertStatement var1) throws Throwable;

      @Nullable
      Object visitEmptyStatement(Java.EmptyStatement var1) throws Throwable;

      @Nullable
      Object visitLocalClassDeclarationStatement(Java.LocalClassDeclarationStatement var1) throws Throwable;

      @Nullable
      Object visitAlternateConstructorInvocation(Java.AlternateConstructorInvocation var1) throws Throwable;

      @Nullable
      Object visitSuperConstructorInvocation(Java.SuperConstructorInvocation var1) throws Throwable;
   }

   public interface ConstructorInvocationVisitor {
      @Nullable
      Object visitAlternateConstructorInvocation(Java.AlternateConstructorInvocation var1) throws Throwable;

      @Nullable
      Object visitSuperConstructorInvocation(Java.SuperConstructorInvocation var1) throws Throwable;
   }

   public interface ElementValueVisitor {
      @Nullable
      Object visitRvalue(Java.Rvalue var1) throws Throwable;

      @Nullable
      Object visitAnnotation(Java.Annotation var1) throws Throwable;

      @Nullable
      Object visitElementValueArrayInitializer(Java.ElementValueArrayInitializer var1) throws Throwable;
   }

   public interface FieldDeclarationOrInitializerVisitor {
      @Nullable
      Object visitInitializer(Java.Initializer var1) throws Throwable;

      @Nullable
      Object visitFieldDeclaration(Java.FieldDeclaration var1) throws Throwable;
   }

   public interface FunctionDeclaratorVisitor {
      @Nullable
      Object visitConstructorDeclarator(Java.ConstructorDeclarator var1) throws Throwable;

      @Nullable
      Object visitMethodDeclarator(Java.MethodDeclarator var1) throws Throwable;
   }

   public interface ImportVisitor {
      @Nullable
      Object visitSingleTypeImportDeclaration(Java.AbstractCompilationUnit.SingleTypeImportDeclaration var1) throws Throwable;

      @Nullable
      Object visitTypeImportOnDemandDeclaration(Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration var1) throws Throwable;

      @Nullable
      Object visitSingleStaticImportDeclaration(Java.AbstractCompilationUnit.SingleStaticImportDeclaration var1) throws Throwable;

      @Nullable
      Object visitStaticImportOnDemandDeclaration(Java.AbstractCompilationUnit.StaticImportOnDemandDeclaration var1) throws Throwable;
   }

   public interface LambdaBodyVisitor {
      @Nullable
      Object visitBlockLambdaBody(Java.BlockLambdaBody var1) throws Throwable;

      @Nullable
      Object visitExpressionLambdaBody(Java.ExpressionLambdaBody var1) throws Throwable;
   }

   public interface LambdaParametersVisitor {
      @Nullable
      Object visitIdentifierLambdaParameters(Java.IdentifierLambdaParameters var1) throws Throwable;

      @Nullable
      Object visitFormalLambdaParameters(Java.FormalLambdaParameters var1) throws Throwable;

      @Nullable
      Object visitInferredLambdaParameters(Java.InferredLambdaParameters var1) throws Throwable;
   }

   public interface LvalueVisitor {
      @Nullable
      Object visitAmbiguousName(Java.AmbiguousName var1) throws Throwable;

      @Nullable
      Object visitArrayAccessExpression(Java.ArrayAccessExpression var1) throws Throwable;

      @Nullable
      Object visitFieldAccess(Java.FieldAccess var1) throws Throwable;

      @Nullable
      Object visitFieldAccessExpression(Java.FieldAccessExpression var1) throws Throwable;

      @Nullable
      Object visitSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression var1) throws Throwable;

      @Nullable
      Object visitLocalVariableAccess(Java.LocalVariableAccess var1) throws Throwable;

      @Nullable
      Object visitParenthesizedExpression(Java.ParenthesizedExpression var1) throws Throwable;
   }

   public interface ModifierVisitor extends AnnotationVisitor {
      @Nullable
      Object visitAccessModifier(Java.AccessModifier var1) throws Throwable;
   }

   public interface ModuleDirectiveVisitor {
      @Nullable
      Object visitRequiresModuleDirective(Java.RequiresModuleDirective var1) throws Throwable;

      @Nullable
      Object visitExportsModuleDirective(Java.ExportsModuleDirective var1) throws Throwable;

      @Nullable
      Object visitOpensModuleDirective(Java.OpensModuleDirective var1) throws Throwable;

      @Nullable
      Object visitUsesModuleDirective(Java.UsesModuleDirective var1) throws Throwable;

      @Nullable
      Object visitProvidesModuleDirective(Java.ProvidesModuleDirective var1) throws Throwable;
   }

   public interface RvalueVisitor {
      @Nullable
      Object visitLvalue(Java.Lvalue var1) throws Throwable;

      @Nullable
      Object visitArrayLength(Java.ArrayLength var1) throws Throwable;

      @Nullable
      Object visitAssignment(Java.Assignment var1) throws Throwable;

      @Nullable
      Object visitUnaryOperation(Java.UnaryOperation var1) throws Throwable;

      @Nullable
      Object visitBinaryOperation(Java.BinaryOperation var1) throws Throwable;

      @Nullable
      Object visitCast(Java.Cast var1) throws Throwable;

      @Nullable
      Object visitClassLiteral(Java.ClassLiteral var1) throws Throwable;

      @Nullable
      Object visitConditionalExpression(Java.ConditionalExpression var1) throws Throwable;

      @Nullable
      Object visitCrement(Java.Crement var1) throws Throwable;

      @Nullable
      Object visitInstanceof(Java.Instanceof var1) throws Throwable;

      @Nullable
      Object visitMethodInvocation(Java.MethodInvocation var1) throws Throwable;

      @Nullable
      Object visitSuperclassMethodInvocation(Java.SuperclassMethodInvocation var1) throws Throwable;

      @Nullable
      Object visitIntegerLiteral(Java.IntegerLiteral var1) throws Throwable;

      @Nullable
      Object visitFloatingPointLiteral(Java.FloatingPointLiteral var1) throws Throwable;

      @Nullable
      Object visitBooleanLiteral(Java.BooleanLiteral var1) throws Throwable;

      @Nullable
      Object visitCharacterLiteral(Java.CharacterLiteral var1) throws Throwable;

      @Nullable
      Object visitStringLiteral(Java.StringLiteral var1) throws Throwable;

      @Nullable
      Object visitNullLiteral(Java.NullLiteral var1) throws Throwable;

      @Nullable
      Object visitSimpleConstant(Java.SimpleConstant var1) throws Throwable;

      @Nullable
      Object visitNewAnonymousClassInstance(Java.NewAnonymousClassInstance var1) throws Throwable;

      @Nullable
      Object visitNewArray(Java.NewArray var1) throws Throwable;

      @Nullable
      Object visitNewInitializedArray(Java.NewInitializedArray var1) throws Throwable;

      @Nullable
      Object visitNewClassInstance(Java.NewClassInstance var1) throws Throwable;

      @Nullable
      Object visitParameterAccess(Java.ParameterAccess var1) throws Throwable;

      @Nullable
      Object visitQualifiedThisReference(Java.QualifiedThisReference var1) throws Throwable;

      @Nullable
      Object visitThisReference(Java.ThisReference var1) throws Throwable;

      @Nullable
      Object visitLambdaExpression(Java.LambdaExpression var1) throws Throwable;

      @Nullable
      Object visitMethodReference(Java.MethodReference var1) throws Throwable;

      @Nullable
      Object visitInstanceCreationReference(Java.ClassInstanceCreationReference var1) throws Throwable;

      @Nullable
      Object visitArrayCreationReference(Java.ArrayCreationReference var1) throws Throwable;
   }

   public interface TryStatementResourceVisitor {
      @Nullable
      Object visitLocalVariableDeclaratorResource(Java.TryStatement.LocalVariableDeclaratorResource var1) throws Throwable;

      @Nullable
      Object visitVariableAccessResource(Java.TryStatement.VariableAccessResource var1) throws Throwable;
   }

   public interface TypeArgumentVisitor {
      @Nullable
      Object visitWildcard(Java.Wildcard var1) throws Throwable;

      @Nullable
      Object visitReferenceType(Java.ReferenceType var1) throws Throwable;

      @Nullable
      Object visitArrayType(Java.ArrayType var1) throws Throwable;
   }

   public interface TypeBodyDeclarationVisitor {
      @Nullable
      Object visitMemberInterfaceDeclaration(Java.MemberInterfaceDeclaration var1) throws Throwable;

      @Nullable
      Object visitMemberClassDeclaration(Java.MemberClassDeclaration var1) throws Throwable;

      @Nullable
      Object visitInitializer(Java.Initializer var1) throws Throwable;

      @Nullable
      Object visitFieldDeclaration(Java.FieldDeclaration var1) throws Throwable;

      @Nullable
      Object visitMemberEnumDeclaration(Java.MemberEnumDeclaration var1) throws Throwable;

      @Nullable
      Object visitFunctionDeclarator(Java.FunctionDeclarator var1) throws Throwable;

      @Nullable
      Object visitMemberAnnotationTypeDeclaration(Java.MemberAnnotationTypeDeclaration var1) throws Throwable;
   }

   public interface TypeDeclarationVisitor {
      @Nullable
      Object visitAnonymousClassDeclaration(Java.AnonymousClassDeclaration var1) throws Throwable;

      @Nullable
      Object visitLocalClassDeclaration(Java.LocalClassDeclaration var1) throws Throwable;

      @Nullable
      Object visitPackageMemberClassDeclaration(Java.PackageMemberClassDeclaration var1) throws Throwable;

      @Nullable
      Object visitMemberInterfaceDeclaration(Java.MemberInterfaceDeclaration var1) throws Throwable;

      @Nullable
      Object visitPackageMemberInterfaceDeclaration(Java.PackageMemberInterfaceDeclaration var1) throws Throwable;

      @Nullable
      Object visitMemberClassDeclaration(Java.MemberClassDeclaration var1) throws Throwable;

      @Nullable
      Object visitEnumConstant(Java.EnumConstant var1) throws Throwable;

      @Nullable
      Object visitMemberEnumDeclaration(Java.MemberEnumDeclaration var1) throws Throwable;

      @Nullable
      Object visitPackageMemberEnumDeclaration(Java.PackageMemberEnumDeclaration var1) throws Throwable;

      @Nullable
      Object visitMemberAnnotationTypeDeclaration(Java.MemberAnnotationTypeDeclaration var1) throws Throwable;

      @Nullable
      Object visitPackageMemberAnnotationTypeDeclaration(Java.PackageMemberAnnotationTypeDeclaration var1) throws Throwable;
   }

   public interface TypeVisitor {
      @Nullable
      Object visitArrayType(Java.ArrayType var1) throws Throwable;

      @Nullable
      Object visitPrimitiveType(Java.PrimitiveType var1) throws Throwable;

      @Nullable
      Object visitReferenceType(Java.ReferenceType var1) throws Throwable;

      @Nullable
      Object visitRvalueMemberType(Java.RvalueMemberType var1) throws Throwable;

      @Nullable
      Object visitSimpleType(Java.SimpleType var1) throws Throwable;
   }
}
