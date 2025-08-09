package org.json4s;

import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000592\u0001b\u0001\u0003\u0011\u0002\u0007\u0005A\u0001\u0003\u0005\u0006\u001f\u0001!\t!\u0005\u0005\u0006+\u0001!\u0019A\u0006\u0002\u0014\u0019><\bK]5pe&$\u00180T3sO\u0016$U\r\u001d\u0006\u0003\u000b\u0019\taA[:p]R\u001a(\"A\u0004\u0002\u0007=\u0014xm\u0005\u0002\u0001\u0013A\u0011!\"D\u0007\u0002\u0017)\tA\"A\u0003tG\u0006d\u0017-\u0003\u0002\u000f\u0017\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002%A\u0011!bE\u0005\u0003)-\u0011A!\u00168ji\u0006\u0019!N\u001b6\u0016\u0007]qB&F\u0001\u0019!\u0015I\"\u0004H\u0016%\u001b\u0005!\u0011BA\u000e\u0005\u0005!iUM]4f\t\u0016\u0004\bCA\u000f\u001f\u0019\u0001!Qa\b\u0002C\u0002\u0001\u0012\u0011!Q\t\u0003C\u0011\u0002\"A\u0003\u0012\n\u0005\rZ!a\u0002(pi\"Lgn\u001a\t\u0003K!r!!\u0007\u0014\n\u0005\u001d\"\u0011a\u0002&t_:\f5\u000bV\u0005\u0003S)\u0012aA\u0013,bYV,'BA\u0014\u0005!\tiB\u0006B\u0003.\u0005\t\u0007\u0001EA\u0001C\u0001"
)
public interface LowPriorityMergeDep {
   // $FF: synthetic method
   static MergeDep jjj$(final LowPriorityMergeDep $this) {
      return $this.jjj();
   }

   default MergeDep jjj() {
      return new MergeDep() {
         public JValue apply(final JValue val1, final JValue val2) {
            return this.merge(val1, val2);
         }

         private JValue merge(final JValue val1, final JValue val2) {
            Tuple2 var4 = new Tuple2(val1, val2);
            Object var3;
            if (var4 != null) {
               JValue var5 = (JValue)var4._1();
               JValue var6 = (JValue)var4._2();
               if (var5 instanceof JObject) {
                  JObject var7 = (JObject)var5;
                  List xs = var7.obj();
                  if (var6 instanceof JObject) {
                     JObject var9 = (JObject)var6;
                     List ys = var9.obj();
                     var3 = JsonAST$.MODULE$.JObject().apply(Merge$.MODULE$.mergeFields(xs, ys));
                     return (JValue)var3;
                  }
               }
            }

            if (var4 != null) {
               JValue var11 = (JValue)var4._1();
               JValue var12 = (JValue)var4._2();
               if (var11 instanceof JArray) {
                  JArray var13 = (JArray)var11;
                  List xs = var13.arr();
                  if (var12 instanceof JArray) {
                     JArray var15 = (JArray)var12;
                     List ys = var15.arr();
                     var3 = JsonAST$.MODULE$.JArray().apply(Merge$.MODULE$.mergeVals(xs, ys));
                     return (JValue)var3;
                  }
               }
            }

            JValue x;
            label84: {
               if (var4 != null) {
                  JValue var17 = (JValue)var4._1();
                  x = (JValue)var4._2();
                  JNothing$ var10000 = JsonAST$.MODULE$.JNothing();
                  if (var10000 == null) {
                     if (var17 == null) {
                        break label84;
                     }
                  } else if (var10000.equals(var17)) {
                     break label84;
                  }
               }

               JValue x;
               label85: {
                  if (var4 != null) {
                     x = (JValue)var4._1();
                     JValue var21 = (JValue)var4._2();
                     JNothing$ var24 = JsonAST$.MODULE$.JNothing();
                     if (var24 == null) {
                        if (var21 == null) {
                           break label85;
                        }
                     } else if (var24.equals(var21)) {
                        break label85;
                     }
                  }

                  if (var4 == null) {
                     throw new MatchError(var4);
                  }

                  JValue y = (JValue)var4._2();
                  var3 = y;
                  return (JValue)var3;
               }

               var3 = x;
               return (JValue)var3;
            }

            var3 = x;
            return (JValue)var3;
         }
      };
   }

   static void $init$(final LowPriorityMergeDep $this) {
   }
}
