package shaded.parquet.com.fasterxml.jackson.databind.deser.impl;

import shaded.parquet.com.fasterxml.jackson.annotation.JacksonInject;
import shaded.parquet.com.fasterxml.jackson.databind.AnnotationIntrospector;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyName;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

public final class CreatorCandidate {
   protected final AnnotationIntrospector _intr;
   protected final AnnotatedWithParams _creator;
   protected final int _paramCount;
   protected final Param[] _params;

   protected CreatorCandidate(AnnotationIntrospector intr, AnnotatedWithParams ct, Param[] params, int count) {
      this._intr = intr;
      this._creator = ct;
      this._params = params;
      this._paramCount = count;
   }

   public static CreatorCandidate construct(AnnotationIntrospector intr, AnnotatedWithParams creator, BeanPropertyDefinition[] propDefs) {
      int pcount = creator.getParameterCount();
      Param[] params = new Param[pcount];

      for(int i = 0; i < pcount; ++i) {
         AnnotatedParameter annParam = creator.getParameter(i);
         JacksonInject.Value injectId = intr.findInjectableValue(annParam);
         params[i] = new Param(annParam, propDefs == null ? null : propDefs[i], injectId);
      }

      return new CreatorCandidate(intr, creator, params, pcount);
   }

   public AnnotatedWithParams creator() {
      return this._creator;
   }

   public int paramCount() {
      return this._paramCount;
   }

   public JacksonInject.Value injection(int i) {
      return this._params[i].injection;
   }

   public AnnotatedParameter parameter(int i) {
      return this._params[i].annotated;
   }

   public BeanPropertyDefinition propertyDef(int i) {
      return this._params[i].propDef;
   }

   public PropertyName paramName(int i) {
      BeanPropertyDefinition propDef = this._params[i].propDef;
      return propDef != null ? propDef.getFullName() : null;
   }

   public PropertyName explicitParamName(int i) {
      BeanPropertyDefinition propDef = this._params[i].propDef;
      return propDef != null && propDef.isExplicitlyNamed() ? propDef.getFullName() : null;
   }

   /** @deprecated */
   @Deprecated
   public PropertyName findImplicitParamName(int i) {
      String str = this._intr.findImplicitPropertyName(this._params[i].annotated);
      return str != null && !str.isEmpty() ? PropertyName.construct(str) : null;
   }

   /** @deprecated */
   @Deprecated
   public int findOnlyParamWithoutInjectionX() {
      int missing = -1;

      for(int i = 0; i < this._paramCount; ++i) {
         if (this._params[i].injection == null) {
            if (missing >= 0) {
               return -1;
            }

            missing = i;
         }
      }

      return missing;
   }

   public String toString() {
      return this._creator.toString();
   }

   public static final class Param {
      public final AnnotatedParameter annotated;
      public final BeanPropertyDefinition propDef;
      public final JacksonInject.Value injection;

      public Param(AnnotatedParameter p, BeanPropertyDefinition pd, JacksonInject.Value i) {
         this.annotated = p;
         this.propDef = pd;
         this.injection = i;
      }

      public PropertyName fullName() {
         return this.propDef == null ? null : this.propDef.getFullName();
      }

      public boolean hasFullName() {
         if (this.propDef == null) {
            return false;
         } else {
            PropertyName n = this.propDef.getFullName();
            return n.hasSimpleName();
         }
      }
   }
}
