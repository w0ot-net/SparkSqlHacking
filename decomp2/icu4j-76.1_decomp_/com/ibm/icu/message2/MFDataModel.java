package com.ibm.icu.message2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** @deprecated */
@Deprecated
public class MFDataModel {
   private MFDataModel() {
   }

   /** @deprecated */
   @Deprecated
   public static class PatternMessage implements Message {
      public final List declarations;
      public final Pattern pattern;

      /** @deprecated */
      @Deprecated
      public PatternMessage(List declarations, Pattern pattern) {
         this.declarations = declarations;
         this.pattern = pattern;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class SelectMessage implements Message {
      public final List declarations;
      public final List selectors;
      public final List variants;

      /** @deprecated */
      @Deprecated
      public SelectMessage(List declarations, List selectors, List variants) {
         this.declarations = declarations;
         this.selectors = selectors;
         this.variants = variants;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class InputDeclaration implements Declaration {
      public final String name;
      public final VariableExpression value;

      /** @deprecated */
      @Deprecated
      public InputDeclaration(String name, VariableExpression value) {
         this.name = name;
         this.value = value;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class LocalDeclaration implements Declaration {
      public final String name;
      public final Expression value;

      /** @deprecated */
      @Deprecated
      public LocalDeclaration(String name, Expression value) {
         this.name = name;
         this.value = value;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class Variant implements LiteralOrCatchallKey {
      public final List keys;
      public final Pattern value;

      /** @deprecated */
      @Deprecated
      public Variant(List keys, Pattern value) {
         this.keys = keys;
         this.value = value;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class CatchallKey implements LiteralOrCatchallKey {
   }

   /** @deprecated */
   @Deprecated
   public static class Pattern {
      public final List parts = new ArrayList();

      Pattern() {
      }
   }

   /** @deprecated */
   @Deprecated
   public static class StringPart implements PatternPart {
      public final String value;

      StringPart(String value) {
         this.value = value;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class LiteralExpression implements Expression {
      public final Literal arg;
      public final Annotation annotation;
      public final List attributes;

      /** @deprecated */
      @Deprecated
      public LiteralExpression(Literal arg, Annotation annotation, List attributes) {
         this.arg = arg;
         this.annotation = annotation;
         this.attributes = attributes;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class VariableExpression implements Expression {
      public final VariableRef arg;
      public final Annotation annotation;
      public final List attributes;

      /** @deprecated */
      @Deprecated
      public VariableExpression(VariableRef arg, Annotation annotation, List attributes) {
         this.arg = arg;
         this.annotation = annotation;
         this.attributes = attributes;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class FunctionExpression implements Expression {
      public final FunctionAnnotation annotation;
      public final List attributes;

      /** @deprecated */
      @Deprecated
      public FunctionExpression(FunctionAnnotation annotation, List attributes) {
         this.annotation = annotation;
         this.attributes = attributes;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class Attribute {
      public final String name;
      public final LiteralOrVariableRef value;

      /** @deprecated */
      @Deprecated
      public Attribute(String name, LiteralOrVariableRef value) {
         this.name = name;
         this.value = value;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class Literal implements LiteralOrVariableRef, LiteralOrCatchallKey {
      public final String value;

      /** @deprecated */
      @Deprecated
      public Literal(String value) {
         this.value = value;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class VariableRef implements LiteralOrVariableRef {
      public final String name;

      /** @deprecated */
      @Deprecated
      public VariableRef(String name) {
         this.name = name;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class FunctionAnnotation implements Annotation {
      public final String name;
      public final Map options;

      /** @deprecated */
      @Deprecated
      public FunctionAnnotation(String name, Map options) {
         this.name = name;
         this.options = options;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class Option {
      public final String name;
      public final LiteralOrVariableRef value;

      /** @deprecated */
      @Deprecated
      public Option(String name, LiteralOrVariableRef value) {
         this.name = name;
         this.value = value;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class Markup implements Expression {
      public final Kind kind;
      public final String name;
      public final Map options;
      public final List attributes;

      /** @deprecated */
      @Deprecated
      public Markup(Kind kind, String name, Map options, List attributes) {
         this.kind = kind;
         this.name = name;
         this.options = options;
         this.attributes = attributes;
      }

      static enum Kind {
         OPEN,
         CLOSE,
         STANDALONE;
      }
   }

   /** @deprecated */
   @Deprecated
   public interface Annotation {
   }

   /** @deprecated */
   @Deprecated
   public interface Declaration {
   }

   /** @deprecated */
   @Deprecated
   public interface Expression extends PatternPart {
   }

   /** @deprecated */
   @Deprecated
   public interface LiteralOrCatchallKey {
   }

   /** @deprecated */
   @Deprecated
   public interface LiteralOrVariableRef {
   }

   /** @deprecated */
   @Deprecated
   public interface Message {
   }

   /** @deprecated */
   @Deprecated
   public interface PatternPart {
   }
}
