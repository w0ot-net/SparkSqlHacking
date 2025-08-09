package com.ibm.icu.message2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

class MFDataModelValidator {
   private final MFDataModel.Message message;
   private final Set declaredVars = new HashSet();

   MFDataModelValidator(MFDataModel.Message message) {
      this.message = message;
   }

   boolean validate() throws MFParseException {
      if (this.message instanceof MFDataModel.PatternMessage) {
         this.validateDeclarations(((MFDataModel.PatternMessage)this.message).declarations);
      } else if (this.message instanceof MFDataModel.SelectMessage) {
         MFDataModel.SelectMessage sm = (MFDataModel.SelectMessage)this.message;
         this.validateDeclarations(sm.declarations);
         this.validateSelectors(sm.selectors);
         int selectorCount = sm.selectors.size();
         this.validateVariants(sm.variants, selectorCount);
      }

      return true;
   }

   private boolean validateVariants(List variants, int selectorCount) throws MFParseException {
      if (variants == null || variants.isEmpty()) {
         this.error("Selection messages must have at least one variant");
      }

      boolean hasUltimateFallback = false;
      Set<String> fakeKeys = new HashSet();

      for(MFDataModel.Variant variant : variants) {
         if (variant.keys == null || variant.keys.isEmpty()) {
            this.error("Selection variants must have at least one key");
         }

         if (variant.keys.size() != selectorCount) {
            this.error("Selection variants must have the same number of variants as the selectors.");
         }

         int catchAllCount = 0;
         StringJoiner fakeKey = new StringJoiner("<<::>>");

         for(MFDataModel.LiteralOrCatchallKey key : variant.keys) {
            if (key instanceof MFDataModel.CatchallKey) {
               ++catchAllCount;
               fakeKey.add("*");
            } else if (key instanceof MFDataModel.Literal) {
               fakeKey.add(((MFDataModel.Literal)key).value);
            }
         }

         if (fakeKeys.contains(fakeKey.toString())) {
            this.error("Dumplicate combination of keys");
         } else {
            fakeKeys.add(fakeKey.toString());
         }

         if (catchAllCount == selectorCount) {
            hasUltimateFallback = true;
         }
      }

      if (!hasUltimateFallback) {
         this.error("There must be one variant with all the keys being '*'");
      }

      return true;
   }

   private boolean validateSelectors(List selectors) throws MFParseException {
      if (selectors == null || selectors.isEmpty()) {
         this.error("Selection messages must have selectors");
      }

      return true;
   }

   private boolean validateDeclarations(List declarations) throws MFParseException {
      if (declarations != null && !declarations.isEmpty()) {
         for(MFDataModel.Declaration declaration : declarations) {
            if (declaration instanceof MFDataModel.LocalDeclaration) {
               MFDataModel.LocalDeclaration ld = (MFDataModel.LocalDeclaration)declaration;
               this.validateExpression(ld.value, false);
               this.addVariableDeclaration(ld.name);
            } else if (declaration instanceof MFDataModel.InputDeclaration) {
               MFDataModel.InputDeclaration id = (MFDataModel.InputDeclaration)declaration;
               this.validateExpression(id.value, true);
            }
         }

         return true;
      } else {
         return true;
      }
   }

   private void validateExpression(MFDataModel.Expression expression, boolean fromInput) throws MFParseException {
      String argName = null;
      MFDataModel.Annotation annotation = null;
      if (!(expression instanceof MFDataModel.Literal)) {
         if (expression instanceof MFDataModel.LiteralExpression) {
            MFDataModel.LiteralExpression le = (MFDataModel.LiteralExpression)expression;
            argName = le.arg.value;
            annotation = le.annotation;
         } else if (expression instanceof MFDataModel.VariableExpression) {
            MFDataModel.VariableExpression ve = (MFDataModel.VariableExpression)expression;
            argName = ve.arg.name;
            annotation = ve.annotation;
         } else if (expression instanceof MFDataModel.FunctionExpression) {
            MFDataModel.FunctionExpression fe = (MFDataModel.FunctionExpression)expression;
            annotation = fe.annotation;
         }
      }

      if (annotation instanceof MFDataModel.FunctionAnnotation) {
         MFDataModel.FunctionAnnotation fa = (MFDataModel.FunctionAnnotation)annotation;
         if (fa.options != null) {
            for(MFDataModel.Option opt : fa.options.values()) {
               MFDataModel.LiteralOrVariableRef val = opt.value;
               if (val instanceof MFDataModel.VariableRef) {
                  this.addVariableDeclaration(((MFDataModel.VariableRef)val).name);
               }
            }
         }
      }

      if (argName != null) {
         if (fromInput) {
            this.addVariableDeclaration(argName);
         } else {
            this.declaredVars.add(argName);
         }
      }

   }

   private boolean addVariableDeclaration(String varName) throws MFParseException {
      if (this.declaredVars.contains(varName)) {
         this.error("Variable '" + varName + "' already declared");
         return false;
      } else {
         this.declaredVars.add(varName);
         return true;
      }
   }

   private void error(String text) throws MFParseException {
      throw new MFParseException(text, -1);
   }
}
