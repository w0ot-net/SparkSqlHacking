package org.json4s;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.PartialFunction;
import scala.Tuple2;
import scala.PartialFunction.;
import scala.runtime.ModuleSerializationProxy;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

public final class Formats$ implements Serializable {
   public static final Formats$ MODULE$ = new Formats$();

   public Object read(final JValue json, final Reader reader) {
      Either var4 = reader.readEither(json);
      if (var4 instanceof Right) {
         Right var5 = (Right)var4;
         Object x = var5.value();
         return x;
      } else if (var4 instanceof Left) {
         Left var7 = (Left)var4;
         MappingException x = (MappingException)var7.value();
         throw x;
      } else {
         throw new MatchError(var4);
      }
   }

   public JValue write(final Object obj, final Writer writer) {
      return writer.write(obj);
   }

   public PartialFunction customSerializer(final Object a, final Formats format) {
      return (PartialFunction)format.customSerializers().collectFirst(new Serializable(format, a) {
         private static final long serialVersionUID = 0L;
         private final Formats format$1;
         private final Object a$1;

         public final Object applyOrElse(final Serializer x1, final Function1 default) {
            Object var3;
            if (x1.serialize(this.format$1).isDefinedAt(this.a$1)) {
               var3 = x1.serialize(this.format$1);
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final Serializer x1) {
            boolean var2;
            if (x1.serialize(this.format$1).isDefinedAt(this.a$1)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }

         public {
            this.format$1 = format$1;
            this.a$1 = a$1;
         }
      }).getOrElse(() -> .MODULE$.empty());
   }

   public PartialFunction customRichDeserializer(final Tuple2 a, final Formats format) {
      return (PartialFunction)format.richSerializers().collectFirst(new Serializable(format, a) {
         private static final long serialVersionUID = 0L;
         private final Formats format$2;
         private final Tuple2 a$2;

         public final Object applyOrElse(final RichSerializer x1, final Function1 default) {
            Object var3;
            if (x1.deserialize(this.format$2).isDefinedAt(this.a$2)) {
               var3 = x1.deserialize(this.format$2);
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final RichSerializer x1) {
            boolean var2;
            if (x1.deserialize(this.format$2).isDefinedAt(this.a$2)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }

         public {
            this.format$2 = format$2;
            this.a$2 = a$2;
         }
      }).getOrElse(() -> .MODULE$.empty());
   }

   public PartialFunction customRichSerializer(final Object a, final Formats format) {
      return (PartialFunction)format.richSerializers().collectFirst(new Serializable(format, a) {
         private static final long serialVersionUID = 0L;
         private final Formats format$3;
         private final Object a$3;

         public final Object applyOrElse(final RichSerializer x1, final Function1 default) {
            Object var3;
            if (x1.serialize(this.format$3).isDefinedAt(this.a$3)) {
               var3 = x1.serialize(this.format$3);
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final RichSerializer x1) {
            boolean var2;
            if (x1.serialize(this.format$3).isDefinedAt(this.a$3)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }

         public {
            this.format$3 = format$3;
            this.a$3 = a$3;
         }
      }).getOrElse(() -> .MODULE$.empty());
   }

   public PartialFunction customDeserializer(final Tuple2 a, final Formats format) {
      return (PartialFunction)format.customSerializers().collectFirst(new Serializable(format, a) {
         private static final long serialVersionUID = 0L;
         private final Formats format$4;
         private final Tuple2 a$4;

         public final Object applyOrElse(final Serializer x1, final Function1 default) {
            Object var3;
            if (x1.deserialize(this.format$4).isDefinedAt(this.a$4)) {
               var3 = x1.deserialize(this.format$4);
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final Serializer x1) {
            boolean var2;
            if (x1.deserialize(this.format$4).isDefinedAt(this.a$4)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }

         public {
            this.format$4 = format$4;
            this.a$4 = a$4;
         }
      }).getOrElse(() -> .MODULE$.empty());
   }

   public PartialFunction customKeySerializer(final Object a, final Formats format) {
      return (PartialFunction)format.customKeySerializers().collectFirst(new Serializable(format, a) {
         private static final long serialVersionUID = 0L;
         private final Formats format$5;
         private final Object a$5;

         public final Object applyOrElse(final KeySerializer x1, final Function1 default) {
            Object var3;
            if (x1.serialize(this.format$5).isDefinedAt(this.a$5)) {
               var3 = x1.serialize(this.format$5);
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final KeySerializer x1) {
            boolean var2;
            if (x1.serialize(this.format$5).isDefinedAt(this.a$5)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }

         public {
            this.format$5 = format$5;
            this.a$5 = a$5;
         }
      }).getOrElse(() -> .MODULE$.empty());
   }

   public PartialFunction customKeyDeserializer(final Tuple2 a, final Formats format) {
      return (PartialFunction)format.customKeySerializers().collectFirst(new Serializable(format, a) {
         private static final long serialVersionUID = 0L;
         private final Formats format$6;
         private final Tuple2 a$6;

         public final Object applyOrElse(final KeySerializer x1, final Function1 default) {
            Object var3;
            if (x1.deserialize(this.format$6).isDefinedAt(this.a$6)) {
               var3 = x1.deserialize(this.format$6);
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final KeySerializer x1) {
            boolean var2;
            if (x1.deserialize(this.format$6).isDefinedAt(this.a$6)) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }

         public {
            this.format$6 = format$6;
            this.a$6 = a$6;
         }
      }).getOrElse(() -> .MODULE$.empty());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Formats$.class);
   }

   private Formats$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
