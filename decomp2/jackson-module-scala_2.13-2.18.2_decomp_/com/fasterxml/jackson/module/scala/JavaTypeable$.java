package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import scala.Option;
import scala.Array.;
import scala.collection.immutable.IntMap;
import scala.collection.immutable.LongMap;
import scala.reflect.ClassTag;

public final class JavaTypeable$ {
   public static final JavaTypeable$ MODULE$ = new JavaTypeable$();
   private static final Class com$fasterxml$jackson$module$scala$JavaTypeable$$intClass;
   private static final Class com$fasterxml$jackson$module$scala$JavaTypeable$$intMapClass;
   private static final Class com$fasterxml$jackson$module$scala$JavaTypeable$$longClass;
   private static final Class com$fasterxml$jackson$module$scala$JavaTypeable$$immutableLongMapClass;
   private static final Class com$fasterxml$jackson$module$scala$JavaTypeable$$mutableLongMapClass;
   private static final JavaTypeable anyJavaTypeable;

   static {
      com$fasterxml$jackson$module$scala$JavaTypeable$$intClass = Integer.TYPE;
      com$fasterxml$jackson$module$scala$JavaTypeable$$intMapClass = IntMap.class;
      com$fasterxml$jackson$module$scala$JavaTypeable$$longClass = Long.TYPE;
      com$fasterxml$jackson$module$scala$JavaTypeable$$immutableLongMapClass = LongMap.class;
      com$fasterxml$jackson$module$scala$JavaTypeable$$mutableLongMapClass = scala.collection.mutable.LongMap.class;
      anyJavaTypeable = new JavaTypeable() {
         public JavaType asJavaType(final TypeFactory typeFactory) {
            JavaType[] typeArgs = (JavaType[]).MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(JavaType.class));
            return typeFactory.constructParametricType(Object.class, typeArgs);
         }
      };
   }

   public Class com$fasterxml$jackson$module$scala$JavaTypeable$$intClass() {
      return com$fasterxml$jackson$module$scala$JavaTypeable$$intClass;
   }

   public Class com$fasterxml$jackson$module$scala$JavaTypeable$$intMapClass() {
      return com$fasterxml$jackson$module$scala$JavaTypeable$$intMapClass;
   }

   public Class com$fasterxml$jackson$module$scala$JavaTypeable$$longClass() {
      return com$fasterxml$jackson$module$scala$JavaTypeable$$longClass;
   }

   public Class com$fasterxml$jackson$module$scala$JavaTypeable$$immutableLongMapClass() {
      return com$fasterxml$jackson$module$scala$JavaTypeable$$immutableLongMapClass;
   }

   public Class com$fasterxml$jackson$module$scala$JavaTypeable$$mutableLongMapClass() {
      return com$fasterxml$jackson$module$scala$JavaTypeable$$mutableLongMapClass;
   }

   public JavaTypeable gen5JavaTypeable(final JavaTypeable evidence$29, final JavaTypeable evidence$30, final JavaTypeable evidence$31, final JavaTypeable evidence$32, final JavaTypeable evidence$33, final ClassTag ct) {
      return new JavaTypeable(evidence$29, evidence$30, evidence$31, evidence$32, evidence$33, ct) {
         private final JavaTypeable evidence$29$1;
         private final JavaTypeable evidence$30$1;
         private final JavaTypeable evidence$31$1;
         private final JavaTypeable evidence$32$1;
         private final JavaTypeable evidence$33$1;
         private final ClassTag ct$1;

         public JavaType asJavaType(final TypeFactory typeFactory) {
            JavaType[] typeArgs = (JavaType[])((Object[])(new JavaType[]{((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$29$1)).asJavaType(typeFactory), ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$30$1)).asJavaType(typeFactory), ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$31$1)).asJavaType(typeFactory), ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$32$1)).asJavaType(typeFactory), ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$33$1)).asJavaType(typeFactory)}));
            return typeFactory.constructParametricType(this.ct$1.runtimeClass(), typeArgs);
         }

         public {
            this.evidence$29$1 = evidence$29$1;
            this.evidence$30$1 = evidence$30$1;
            this.evidence$31$1 = evidence$31$1;
            this.evidence$32$1 = evidence$32$1;
            this.evidence$33$1 = evidence$33$1;
            this.ct$1 = ct$1;
         }
      };
   }

   public JavaTypeable gen4JavaTypeable(final JavaTypeable evidence$34, final JavaTypeable evidence$35, final JavaTypeable evidence$36, final JavaTypeable evidence$37, final ClassTag ct) {
      return new JavaTypeable(evidence$34, evidence$35, evidence$36, evidence$37, ct) {
         private final JavaTypeable evidence$34$1;
         private final JavaTypeable evidence$35$1;
         private final JavaTypeable evidence$36$1;
         private final JavaTypeable evidence$37$1;
         private final ClassTag ct$2;

         public JavaType asJavaType(final TypeFactory typeFactory) {
            JavaType[] typeArgs = (JavaType[])((Object[])(new JavaType[]{((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$34$1)).asJavaType(typeFactory), ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$35$1)).asJavaType(typeFactory), ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$36$1)).asJavaType(typeFactory), ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$37$1)).asJavaType(typeFactory)}));
            return typeFactory.constructParametricType(this.ct$2.runtimeClass(), typeArgs);
         }

         public {
            this.evidence$34$1 = evidence$34$1;
            this.evidence$35$1 = evidence$35$1;
            this.evidence$36$1 = evidence$36$1;
            this.evidence$37$1 = evidence$37$1;
            this.ct$2 = ct$2;
         }
      };
   }

   public JavaTypeable gen3JavaTypeable(final JavaTypeable evidence$38, final JavaTypeable evidence$39, final JavaTypeable evidence$40, final ClassTag ct) {
      return new JavaTypeable(evidence$38, evidence$39, evidence$40, ct) {
         private final JavaTypeable evidence$38$1;
         private final JavaTypeable evidence$39$1;
         private final JavaTypeable evidence$40$1;
         private final ClassTag ct$3;

         public JavaType asJavaType(final TypeFactory typeFactory) {
            JavaType[] typeArgs = (JavaType[])((Object[])(new JavaType[]{((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$38$1)).asJavaType(typeFactory), ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$39$1)).asJavaType(typeFactory), ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$40$1)).asJavaType(typeFactory)}));
            return typeFactory.constructParametricType(this.ct$3.runtimeClass(), typeArgs);
         }

         public {
            this.evidence$38$1 = evidence$38$1;
            this.evidence$39$1 = evidence$39$1;
            this.evidence$40$1 = evidence$40$1;
            this.ct$3 = ct$3;
         }
      };
   }

   public JavaTypeable gen2JavaTypeable(final JavaTypeable evidence$41, final JavaTypeable evidence$42, final ClassTag ct) {
      return new JavaTypeable(evidence$41, evidence$42, ct) {
         private final JavaTypeable evidence$41$1;
         private final JavaTypeable evidence$42$1;
         private final ClassTag ct$4;

         public JavaType asJavaType(final TypeFactory typeFactory) {
            JavaType[] typeArgs = (JavaType[])((Object[])(new JavaType[]{((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$41$1)).asJavaType(typeFactory), ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$42$1)).asJavaType(typeFactory)}));
            return typeFactory.constructParametricType(this.ct$4.runtimeClass(), typeArgs);
         }

         public {
            this.evidence$41$1 = evidence$41$1;
            this.evidence$42$1 = evidence$42$1;
            this.ct$4 = ct$4;
         }
      };
   }

   public JavaTypeable gen1JavaTypeable(final JavaTypeable evidence$43, final ClassTag ct) {
      return new JavaTypeable(evidence$43, ct) {
         private final JavaTypeable evidence$43$1;
         private final ClassTag ct$5;

         public JavaType asJavaType(final TypeFactory typeFactory) {
            JavaType[] typeArgs = (JavaType[])((Object[])(new JavaType[]{((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$43$1)).asJavaType(typeFactory)}));
            return typeFactory.constructParametricType(this.ct$5.runtimeClass(), typeArgs);
         }

         public {
            this.evidence$43$1 = evidence$43$1;
            this.ct$5 = ct$5;
         }
      };
   }

   public JavaTypeable gen0JavaTypeable(final ClassTag ct) {
      return new JavaTypeable(ct) {
         private final ClassTag ct$6;

         public JavaType asJavaType(final TypeFactory typeFactory) {
            JavaType[] typeArgs = (JavaType[]).MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(JavaType.class));
            return typeFactory.constructParametricType(this.ct$6.runtimeClass(), typeArgs);
         }

         public {
            this.ct$6 = ct$6;
         }
      };
   }

   public JavaTypeable arrayJavaTypeable(final JavaTypeable evidence$44) {
      return new JavaTypeable(evidence$44) {
         private final JavaTypeable evidence$44$1;

         public JavaType asJavaType(final TypeFactory typeFactory) {
            JavaType typeArg0 = ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$44$1)).asJavaType(typeFactory);
            return typeFactory.constructArrayType(typeArg0);
         }

         public {
            this.evidence$44$1 = evidence$44$1;
         }
      };
   }

   public JavaTypeable mapJavaTypeable(final JavaTypeable evidence$45, final JavaTypeable evidence$46, final ClassTag ct) {
      return new JavaTypeable(evidence$45, evidence$46, ct) {
         private final JavaTypeable evidence$45$1;
         private final JavaTypeable evidence$46$1;
         private final ClassTag ct$7;

         public JavaType asJavaType(final TypeFactory typeFactory) {
            JavaType typeArg0 = ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$45$1)).asJavaType(typeFactory);
            JavaType typeArg1 = ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$46$1)).asJavaType(typeFactory);
            return typeFactory.constructMapLikeType(this.ct$7.runtimeClass(), typeArg0, typeArg1);
         }

         public {
            this.evidence$45$1 = evidence$45$1;
            this.evidence$46$1 = evidence$46$1;
            this.ct$7 = ct$7;
         }
      };
   }

   public JavaTypeable collectionJavaTypeable(final JavaTypeable evidence$47, final ClassTag ct) {
      return new JavaTypeable(evidence$47, ct) {
         private final JavaTypeable evidence$47$1;
         private final ClassTag ct$8;

         public JavaType asJavaType(final TypeFactory typeFactory) {
            JavaType typeArg0 = ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$47$1)).asJavaType(typeFactory);
            if (JavaTypeable$.MODULE$.com$fasterxml$jackson$module$scala$JavaTypeable$$intMapClass().isAssignableFrom(this.ct$8.runtimeClass())) {
               return MapLikeType.upgradeFrom(typeFactory.constructType(JavaTypeable$.MODULE$.com$fasterxml$jackson$module$scala$JavaTypeable$$intMapClass()), typeFactory.constructType(JavaTypeable$.MODULE$.com$fasterxml$jackson$module$scala$JavaTypeable$$intClass()), typeArg0);
            } else if (JavaTypeable$.MODULE$.com$fasterxml$jackson$module$scala$JavaTypeable$$immutableLongMapClass().isAssignableFrom(this.ct$8.runtimeClass())) {
               return MapLikeType.upgradeFrom(typeFactory.constructType(JavaTypeable$.MODULE$.com$fasterxml$jackson$module$scala$JavaTypeable$$immutableLongMapClass()), typeFactory.constructType(JavaTypeable$.MODULE$.com$fasterxml$jackson$module$scala$JavaTypeable$$longClass()), typeArg0);
            } else {
               return (JavaType)(JavaTypeable$.MODULE$.com$fasterxml$jackson$module$scala$JavaTypeable$$mutableLongMapClass().isAssignableFrom(this.ct$8.runtimeClass()) ? MapLikeType.upgradeFrom(typeFactory.constructType(JavaTypeable$.MODULE$.com$fasterxml$jackson$module$scala$JavaTypeable$$mutableLongMapClass()), typeFactory.constructType(JavaTypeable$.MODULE$.com$fasterxml$jackson$module$scala$JavaTypeable$$longClass()), typeArg0) : typeFactory.constructCollectionLikeType(this.ct$8.runtimeClass(), typeArg0));
            }
         }

         public {
            this.evidence$47$1 = evidence$47$1;
            this.ct$8 = ct$8;
         }
      };
   }

   public JavaTypeable optionJavaTypeable(final JavaTypeable evidence$48) {
      return new JavaTypeable(evidence$48) {
         private final JavaTypeable evidence$48$1;

         public JavaType asJavaType(final TypeFactory typeFactory) {
            JavaType typeArg0 = ((JavaTypeable)scala.Predef..MODULE$.implicitly(this.evidence$48$1)).asJavaType(typeFactory);
            return typeFactory.constructReferenceType(Option.class, typeArg0);
         }

         public {
            this.evidence$48$1 = evidence$48$1;
         }
      };
   }

   public JavaTypeable anyJavaTypeable() {
      return anyJavaTypeable;
   }

   private JavaTypeable$() {
   }
}
