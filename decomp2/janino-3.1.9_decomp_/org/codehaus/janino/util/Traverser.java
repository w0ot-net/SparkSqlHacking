package org.codehaus.janino.util;

import org.codehaus.janino.Java;

public interface Traverser {
   void visitAbstractCompilationUnit(Java.AbstractCompilationUnit var1) throws Throwable;

   void visitImportDeclaration(Java.AbstractCompilationUnit.ImportDeclaration var1) throws Throwable;

   void visitTypeDeclaration(Java.TypeDeclaration var1) throws Throwable;

   void visitTypeBodyDeclaration(Java.TypeBodyDeclaration var1) throws Throwable;

   void visitBlockStatement(Java.BlockStatement var1) throws Throwable;

   void visitAtom(Java.Atom var1) throws Throwable;

   void visitElementValue(Java.ElementValue var1) throws Throwable;

   void visitAnnotation(Java.Annotation var1) throws Throwable;

   void traverseAbstractCompilationUnit(Java.AbstractCompilationUnit var1) throws Throwable;

   void traverseCompilationUnit(Java.CompilationUnit var1) throws Throwable;

   void traverseModularCompilationUnit(Java.ModularCompilationUnit var1) throws Throwable;

   void traverseSingleTypeImportDeclaration(Java.AbstractCompilationUnit.SingleTypeImportDeclaration var1) throws Throwable;

   void traverseTypeImportOnDemandDeclaration(Java.AbstractCompilationUnit.TypeImportOnDemandDeclaration var1) throws Throwable;

   void traverseSingleStaticImportDeclaration(Java.AbstractCompilationUnit.SingleStaticImportDeclaration var1) throws Throwable;

   void traverseStaticImportOnDemandDeclaration(Java.AbstractCompilationUnit.StaticImportOnDemandDeclaration var1) throws Throwable;

   void traverseImportDeclaration(Java.AbstractCompilationUnit.ImportDeclaration var1) throws Throwable;

   void traverseAnonymousClassDeclaration(Java.AnonymousClassDeclaration var1) throws Throwable;

   void traverseLocalClassDeclaration(Java.LocalClassDeclaration var1) throws Throwable;

   void traversePackageMemberClassDeclaration(Java.PackageMemberClassDeclaration var1) throws Throwable;

   void traverseMemberInterfaceDeclaration(Java.MemberInterfaceDeclaration var1) throws Throwable;

   void traversePackageMemberInterfaceDeclaration(Java.PackageMemberInterfaceDeclaration var1) throws Throwable;

   void traverseMemberClassDeclaration(Java.MemberClassDeclaration var1) throws Throwable;

   void traverseConstructorDeclarator(Java.ConstructorDeclarator var1) throws Throwable;

   void traverseInitializer(Java.Initializer var1) throws Throwable;

   void traverseMethodDeclarator(Java.MethodDeclarator var1) throws Throwable;

   void traverseFieldDeclaration(Java.FieldDeclaration var1) throws Throwable;

   void traverseLabeledStatement(Java.LabeledStatement var1) throws Throwable;

   void traverseBlock(Java.Block var1) throws Throwable;

   void traverseExpressionStatement(Java.ExpressionStatement var1) throws Throwable;

   void traverseIfStatement(Java.IfStatement var1) throws Throwable;

   void traverseForStatement(Java.ForStatement var1) throws Throwable;

   void traverseForEachStatement(Java.ForEachStatement var1) throws Throwable;

   void traverseWhileStatement(Java.WhileStatement var1) throws Throwable;

   void traverseTryStatement(Java.TryStatement var1) throws Throwable;

   void traverseSwitchStatement(Java.SwitchStatement var1) throws Throwable;

   void traverseSynchronizedStatement(Java.SynchronizedStatement var1) throws Throwable;

   void traverseDoStatement(Java.DoStatement var1) throws Throwable;

   void traverseLocalVariableDeclarationStatement(Java.LocalVariableDeclarationStatement var1) throws Throwable;

   void traverseReturnStatement(Java.ReturnStatement var1) throws Throwable;

   void traverseThrowStatement(Java.ThrowStatement var1) throws Throwable;

   void traverseBreakStatement(Java.BreakStatement var1) throws Throwable;

   void traverseContinueStatement(Java.ContinueStatement var1) throws Throwable;

   void traverseAssertStatement(Java.AssertStatement var1) throws Throwable;

   void traverseEmptyStatement(Java.EmptyStatement var1) throws Throwable;

   void traverseLocalClassDeclarationStatement(Java.LocalClassDeclarationStatement var1) throws Throwable;

   void traversePackage(Java.Package var1) throws Throwable;

   void traverseArrayLength(Java.ArrayLength var1) throws Throwable;

   void traverseAssignment(Java.Assignment var1) throws Throwable;

   void traverseUnaryOperation(Java.UnaryOperation var1) throws Throwable;

   void traverseBinaryOperation(Java.BinaryOperation var1) throws Throwable;

   void traverseCast(Java.Cast var1) throws Throwable;

   void traverseClassLiteral(Java.ClassLiteral var1) throws Throwable;

   void traverseConditionalExpression(Java.ConditionalExpression var1) throws Throwable;

   void traverseCrement(Java.Crement var1) throws Throwable;

   void traverseInstanceof(Java.Instanceof var1) throws Throwable;

   void traverseMethodInvocation(Java.MethodInvocation var1) throws Throwable;

   void traverseSuperclassMethodInvocation(Java.SuperclassMethodInvocation var1) throws Throwable;

   void traverseLiteral(Java.Literal var1) throws Throwable;

   void traverseIntegerLiteral(Java.IntegerLiteral var1) throws Throwable;

   void traverseFloatingPointLiteral(Java.FloatingPointLiteral var1) throws Throwable;

   void traverseBooleanLiteral(Java.BooleanLiteral var1) throws Throwable;

   void traverseCharacterLiteral(Java.CharacterLiteral var1) throws Throwable;

   void traverseStringLiteral(Java.StringLiteral var1) throws Throwable;

   void traverseNullLiteral(Java.NullLiteral var1) throws Throwable;

   void traverseSimpleLiteral(Java.SimpleConstant var1) throws Throwable;

   void traverseNewAnonymousClassInstance(Java.NewAnonymousClassInstance var1) throws Throwable;

   void traverseNewArray(Java.NewArray var1) throws Throwable;

   void traverseNewInitializedArray(Java.NewInitializedArray var1) throws Throwable;

   void traverseArrayInitializerOrRvalue(Java.ArrayInitializerOrRvalue var1) throws Throwable;

   void traverseNewClassInstance(Java.NewClassInstance var1) throws Throwable;

   void traverseParameterAccess(Java.ParameterAccess var1) throws Throwable;

   void traverseQualifiedThisReference(Java.QualifiedThisReference var1) throws Throwable;

   void traverseThisReference(Java.ThisReference var1) throws Throwable;

   void traverseLambdaExpression(Java.LambdaExpression var1) throws Throwable;

   void traverseMethodReference(Java.MethodReference var1) throws Throwable;

   void traverseClassInstanceCreationReference(Java.ClassInstanceCreationReference var1) throws Throwable;

   void traverseArrayCreationReference(Java.ArrayCreationReference var1) throws Throwable;

   void traverseArrayType(Java.ArrayType var1) throws Throwable;

   void traversePrimitiveType(Java.PrimitiveType var1) throws Throwable;

   void traverseReferenceType(Java.ReferenceType var1) throws Throwable;

   void traverseRvalueMemberType(Java.RvalueMemberType var1) throws Throwable;

   void traverseSimpleType(Java.SimpleType var1) throws Throwable;

   void traverseAlternateConstructorInvocation(Java.AlternateConstructorInvocation var1) throws Throwable;

   void traverseSuperConstructorInvocation(Java.SuperConstructorInvocation var1) throws Throwable;

   void traverseAmbiguousName(Java.AmbiguousName var1) throws Throwable;

   void traverseArrayAccessExpression(Java.ArrayAccessExpression var1) throws Throwable;

   void traverseFieldAccess(Java.FieldAccess var1) throws Throwable;

   void traverseFieldAccessExpression(Java.FieldAccessExpression var1) throws Throwable;

   void traverseSuperclassFieldAccessExpression(Java.SuperclassFieldAccessExpression var1) throws Throwable;

   void traverseLocalVariableAccess(Java.LocalVariableAccess var1) throws Throwable;

   void traverseParenthesizedExpression(Java.ParenthesizedExpression var1) throws Throwable;

   void traverseElementValueArrayInitializer(Java.ElementValueArrayInitializer var1) throws Throwable;

   void traverseElementValue(Java.ElementValue var1) throws Throwable;

   void traverseSingleElementAnnotation(Java.SingleElementAnnotation var1) throws Throwable;

   void traverseAnnotation(Java.Annotation var1) throws Throwable;

   void traverseNormalAnnotation(Java.NormalAnnotation var1) throws Throwable;

   void traverseMarkerAnnotation(Java.MarkerAnnotation var1) throws Throwable;

   void traverseClassDeclaration(Java.AbstractClassDeclaration var1) throws Throwable;

   void traverseAbstractTypeDeclaration(Java.AbstractTypeDeclaration var1) throws Throwable;

   void traverseNamedClassDeclaration(Java.NamedClassDeclaration var1) throws Throwable;

   void traverseInterfaceDeclaration(Java.InterfaceDeclaration var1) throws Throwable;

   void traverseFunctionDeclarator(Java.FunctionDeclarator var1) throws Throwable;

   void traverseFormalParameters(Java.FunctionDeclarator.FormalParameters var1) throws Throwable;

   void traverseFormalParameter(Java.FunctionDeclarator.FormalParameter var1) throws Throwable;

   void traverseAbstractTypeBodyDeclaration(Java.AbstractTypeBodyDeclaration var1) throws Throwable;

   void traverseStatement(Java.Statement var1) throws Throwable;

   void traverseBreakableStatement(Java.BreakableStatement var1) throws Throwable;

   void traverseContinuableStatement(Java.ContinuableStatement var1) throws Throwable;

   void traverseRvalue(Java.Rvalue var1) throws Throwable;

   void traverseBooleanRvalue(Java.BooleanRvalue var1) throws Throwable;

   void traverseInvocation(Java.Invocation var1) throws Throwable;

   void traverseConstructorInvocation(Java.ConstructorInvocation var1) throws Throwable;

   void traverseEnumConstant(Java.EnumConstant var1) throws Throwable;

   void traversePackageMemberEnumDeclaration(Java.PackageMemberEnumDeclaration var1) throws Throwable;

   void traverseMemberEnumDeclaration(Java.MemberEnumDeclaration var1) throws Throwable;

   void traversePackageMemberAnnotationTypeDeclaration(Java.PackageMemberAnnotationTypeDeclaration var1) throws Throwable;

   void traverseMemberAnnotationTypeDeclaration(Java.MemberAnnotationTypeDeclaration var1) throws Throwable;

   void traverseLvalue(Java.Lvalue var1) throws Throwable;

   void traverseType(Java.Type var1) throws Throwable;

   void traverseAtom(Java.Atom var1) throws Throwable;

   void traverseLocated(Java.Located var1) throws Throwable;

   void traverseLocalVariableDeclaratorResource(Java.TryStatement.LocalVariableDeclaratorResource var1) throws Throwable;

   void traverseVariableAccessResource(Java.TryStatement.VariableAccessResource var1) throws Throwable;
}
