package org.json4s;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class FieldSerializer$ implements Serializable {
   public static final FieldSerializer$ MODULE$ = new FieldSerializer$();

   public PartialFunction $lessinit$greater$default$1() {
      return (PartialFunction).MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
   }

   public PartialFunction $lessinit$greater$default$2() {
      return (PartialFunction).MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
   }

   public boolean $lessinit$greater$default$3() {
      return false;
   }

   public PartialFunction renameFrom(final String name, final String newName) {
      return new Serializable(name, newName) {
         private static final long serialVersionUID = 0L;
         private final String name$1;
         private final String newName$1;

         public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
            Object var3;
            JValue x;
            label30: {
               if (x1 != null) {
                  Tuple2 var5 = org.json4s.JField..MODULE$.unapply(x1);
                  if (!org.json4s.SomeValue..MODULE$.isEmpty$extension(var5)) {
                     String var6 = (String)var5._1();
                     x = (JValue)var5._2();
                     String var10000 = this.name$1;
                     if (var10000 == null) {
                        if (var6 == null) {
                           break label30;
                        }
                     } else if (var10000.equals(var6)) {
                        break label30;
                     }
                  }
               }

               var3 = default.apply(x1);
               return var3;
            }

            var3 = org.json4s.JField..MODULE$.apply(this.newName$1, x);
            return var3;
         }

         public final boolean isDefinedAt(final Tuple2 x1) {
            boolean var2;
            label30: {
               if (x1 != null) {
                  Tuple2 var4 = org.json4s.JField..MODULE$.unapply(x1);
                  if (!org.json4s.SomeValue..MODULE$.isEmpty$extension(var4)) {
                     String var5 = (String)var4._1();
                     String var10000 = this.name$1;
                     if (var10000 == null) {
                        if (var5 == null) {
                           break label30;
                        }
                     } else if (var10000.equals(var5)) {
                        break label30;
                     }
                  }
               }

               var2 = false;
               return var2;
            }

            var2 = true;
            return var2;
         }

         public {
            this.name$1 = name$1;
            this.newName$1 = newName$1;
         }
      };
   }

   public PartialFunction ignore(final String name) {
      return new Serializable(name) {
         private static final long serialVersionUID = 0L;
         private final String name$2;

         public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
            Object var3;
            label27: {
               if (x1 != null) {
                  String var5 = (String)x1._1();
                  String var10000 = this.name$2;
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label27;
                     }
                  } else if (var10000.equals(var5)) {
                     break label27;
                  }
               }

               var3 = default.apply(x1);
               return var3;
            }

            var3 = scala.None..MODULE$;
            return var3;
         }

         public final boolean isDefinedAt(final Tuple2 x1) {
            boolean var2;
            label27: {
               if (x1 != null) {
                  String var4 = (String)x1._1();
                  String var10000 = this.name$2;
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label27;
                     }
                  } else if (var10000.equals(var4)) {
                     break label27;
                  }
               }

               var2 = false;
               return var2;
            }

            var2 = true;
            return var2;
         }

         public {
            this.name$2 = name$2;
         }
      };
   }

   public PartialFunction renameTo(final String name, final String newName) {
      return new Serializable(name, newName) {
         private static final long serialVersionUID = 0L;
         private final String name$3;
         private final String newName$2;

         public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
            Object var3;
            Object x;
            label27: {
               if (x1 != null) {
                  String var5 = (String)x1._1();
                  x = x1._2();
                  String var10000 = this.name$3;
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label27;
                     }
                  } else if (var10000.equals(var5)) {
                     break label27;
                  }
               }

               var3 = default.apply(x1);
               return var3;
            }

            var3 = new Some(new Tuple2(this.newName$2, x));
            return var3;
         }

         public final boolean isDefinedAt(final Tuple2 x1) {
            boolean var2;
            label27: {
               if (x1 != null) {
                  String var4 = (String)x1._1();
                  String var10000 = this.name$3;
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label27;
                     }
                  } else if (var10000.equals(var4)) {
                     break label27;
                  }
               }

               var2 = false;
               return var2;
            }

            var2 = true;
            return var2;
         }

         public {
            this.name$3 = name$3;
            this.newName$2 = newName$2;
         }
      };
   }

   public FieldSerializer apply(final PartialFunction serializer, final PartialFunction deserializer, final boolean includeLazyVal, final ClassTag mf) {
      return new FieldSerializer(serializer, deserializer, includeLazyVal, mf);
   }

   public PartialFunction apply$default$1() {
      return (PartialFunction).MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
   }

   public PartialFunction apply$default$2() {
      return (PartialFunction).MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
   }

   public boolean apply$default$3() {
      return false;
   }

   public Option unapply(final FieldSerializer x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.serializer(), x$0.deserializer(), BoxesRunTime.boxToBoolean(x$0.includeLazyVal()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FieldSerializer$.class);
   }

   private FieldSerializer$() {
   }
}
