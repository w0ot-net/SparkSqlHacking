package scala.reflect.runtime;

import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.reflect.internal.Names;
import scala.reflect.internal.Symbols;

public class JavaMirrors$JavaMirror$toAnnotArg$ConstantArg$ {
   // $FF: synthetic field
   private final JavaMirrors.JavaMirror.toAnnotArg$ $outer;

   public Symbols.Symbol enumToSymbol(final Enum enum) {
      return this.$outer.scala$reflect$runtime$JavaMirrors$JavaMirror$toAnnotArg$$$outer().classToScala(enum.getClass()).companionSymbol().info().declaration(((Names)this.$outer.scala$reflect$runtime$JavaMirrors$JavaMirror$toAnnotArg$$$outer().scala$reflect$runtime$JavaMirrors$JavaMirror$$$outer()).TermName().apply(enum.name()));
   }

   public Option unapply(final Tuple2 schemaAndValue) {
      if (schemaAndValue != null) {
         Object value;
         boolean var10;
         label60: {
            label65: {
               Class var2 = (Class)schemaAndValue._1();
               value = schemaAndValue._2();
               Class var10000 = this.$outer.StringClass();
               if (var10000 == null) {
                  if (var2 == null) {
                     break label65;
                  }
               } else if (var10000.equals(var2)) {
                  break label65;
               }

               if (var2 != null) {
                  if (this.$outer.PrimitiveClass() == null) {
                     throw null;
                  }

                  if (var2.isPrimitive()) {
                     var10 = true;
                     break label60;
                  }
               }

               var10 = false;
               break label60;
            }

            var10 = true;
         }

         if (var10) {
            return new Some(value);
         }
      }

      if (schemaAndValue != null) {
         label47: {
            Class var4 = (Class)schemaAndValue._1();
            Object value = schemaAndValue._2();
            Class var11 = this.$outer.ClassClass();
            if (var11 == null) {
               if (var4 != null) {
                  break label47;
               }
            } else if (!var11.equals(var4)) {
               break label47;
            }

            if (value instanceof Class) {
               Class var6 = (Class)value;
               return new Some(this.$outer.scala$reflect$runtime$JavaMirrors$JavaMirror$toAnnotArg$$$outer().classToScala(var6).toType());
            }
         }
      }

      if (schemaAndValue != null) {
         Class var7 = (Class)schemaAndValue._1();
         Object value = schemaAndValue._2();
         if (var7 != null) {
            if (this.$outer.EnumClass() == null) {
               throw null;
            }

            if (var7.isEnum() && value instanceof Enum) {
               Enum var9 = (Enum)value;
               return new Some(this.enumToSymbol(var9));
            }
         }
      }

      return .MODULE$;
   }

   public JavaMirrors$JavaMirror$toAnnotArg$ConstantArg$(final JavaMirrors.JavaMirror.toAnnotArg$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
