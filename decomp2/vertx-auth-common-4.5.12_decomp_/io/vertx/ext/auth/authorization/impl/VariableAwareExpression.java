package io.vertx.ext.auth.authorization.impl;

import io.vertx.ext.auth.authorization.AuthorizationContext;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

class VariableAwareExpression {
   private final String value;
   private final transient Function[] parts;
   private transient boolean hasVariable = false;

   public VariableAwareExpression(String value) {
      this.value = ((String)Objects.requireNonNull(value)).trim();
      List<Function<AuthorizationContext, String>> tmpParts = new ArrayList();

      int closingCurlyBracePos;
      for(int currentPos = 0; currentPos != -1; currentPos = closingCurlyBracePos + 1) {
         int openingCurlyBracePos = value.indexOf("{", currentPos);
         if (openingCurlyBracePos == -1) {
            if (currentPos < value.length()) {
               String authorizationPart = value.substring(currentPos);
               tmpParts.add((Function)(ctx) -> authorizationPart);
            }
            break;
         }

         if (openingCurlyBracePos > currentPos) {
            String authorizationPart = value.substring(currentPos, openingCurlyBracePos);
            tmpParts.add((Function)(ctx) -> authorizationPart);
         }

         closingCurlyBracePos = value.indexOf("}", currentPos + 1);
         if (closingCurlyBracePos == -1) {
            throw new IllegalArgumentException("opening '{' without corresponding closing '}'");
         }

         if (closingCurlyBracePos - openingCurlyBracePos == 1) {
            throw new IllegalArgumentException("empty '{}' is not allowed");
         }

         String part = value.substring(openingCurlyBracePos, closingCurlyBracePos + 1);
         String variableName = value.substring(openingCurlyBracePos + 1, closingCurlyBracePos);
         this.hasVariable = true;
         tmpParts.add((Function)(ctx) -> {
            String result = ctx.variables().get(variableName);
            return result != null ? result : part;
         });
      }

      this.parts = (Function[])Array.newInstance(Function.class, tmpParts.size());

      for(int i = 0; i < tmpParts.size(); ++i) {
         this.parts[i] = (Function)tmpParts.get(i);
      }

   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof VariableAwareExpression)) {
         return false;
      } else {
         VariableAwareExpression other = (VariableAwareExpression)obj;
         return Objects.equals(this.value, other.value);
      }
   }

   public boolean hasVariable() {
      return this.hasVariable;
   }

   public String getValue() {
      return this.value;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.value});
   }

   public Function[] parts() {
      return this.parts;
   }

   public String resolve(AuthorizationContext context) {
      if (this.parts.length == 1) {
         return (String)this.parts[0].apply(context);
      } else if (this.parts.length <= 1) {
         return "";
      } else {
         StringBuilder result = new StringBuilder();

         for(Function part : this.parts) {
            result.append((String)part.apply(context));
         }

         return result.toString();
      }
   }
}
