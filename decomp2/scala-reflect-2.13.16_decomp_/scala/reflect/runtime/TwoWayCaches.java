package scala.reflect.runtime;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.Option.;
import scala.collection.mutable.WeakHashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005}4\u0011BD\b\u0011\u0002\u0007\u0005q\"F>\t\u000bi\u0001A\u0011\u0001\u000f\u0007\t\u0001\u0002\u0001!\t\u0005\u0006G\t!\t\u0001\n\u0005\u0007k\t\u0001\u000b\u0011\u0002\u001c\t\r!\u0013\u0001\u0015!\u0003J\u0011\u0015Y%\u0001\"\u0001M\u000f\u0015\t&\u0001#\u0003S\r\u0015!&\u0001#\u0003V\u0011\u0015\u0019\u0003\u0002\"\u0001W\u0011\u00159\u0006\u0002\"\u0001Y\u0011\u0015!'\u0001\"\u0001f\u0011\u0015q'\u0001\"\u0001p\u0011\u0015!(\u0001\"\u0001v\u00051!vo\\,bs\u000e\u000b7\r[3t\u0015\t\u0001\u0012#A\u0004sk:$\u0018.\\3\u000b\u0005I\u0019\u0012a\u0002:fM2,7\r\u001e\u0006\u0002)\u0005)1oY1mCN\u0011\u0001A\u0006\t\u0003/ai\u0011aE\u0005\u00033M\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003u\u0001\"a\u0006\u0010\n\u0005}\u0019\"\u0001B+oSR\u00141\u0002V<p/\u0006L8)Y2iKV\u0019!%K\u001a\u0014\u0005\t1\u0012A\u0002\u001fj]&$h\bF\u0001&!\u00111#a\n\u001a\u000e\u0003\u0001\u0001\"\u0001K\u0015\r\u0001\u0011)!F\u0001b\u0001W\t\t!*\u0005\u0002-_A\u0011q#L\u0005\u0003]M\u0011qAT8uQ&tw\r\u0005\u0002\u0018a%\u0011\u0011g\u0005\u0002\u0004\u0003:L\bC\u0001\u00154\t\u0015!$A1\u0001,\u0005\u0005\u0019\u0016A\u0003;p'\u000e\fG.Y'baB!q\u0007P\u0014?\u001b\u0005A$BA\u001d;\u0003\u001diW\u000f^1cY\u0016T!aO\n\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002>q\tYq+Z1l\u0011\u0006\u001c\b.T1q!\rydIM\u0007\u0002\u0001*\u0011\u0011IQ\u0001\u0004e\u00164'BA\"E\u0003\u0011a\u0017M\\4\u000b\u0003\u0015\u000bAA[1wC&\u0011q\t\u0011\u0002\u000e/\u0016\f7NU3gKJ,gnY3\u0002\u0013Q|'*\u0019<b\u001b\u0006\u0004\b\u0003B\u001c=e)\u00032a\u0010$(\u0003\u0015)g\u000e^3s)\riRj\u0014\u0005\u0006\u001d\u001a\u0001\raJ\u0001\u0002U\")\u0001K\u0002a\u0001e\u0005\t1/A\u0004T_6,'+\u001a4\u0011\u0005MCQ\"\u0001\u0002\u0003\u000fM{W.\u001a*fMN\u0011\u0001B\u0006\u000b\u0002%\u00069QO\\1qa2LXCA-_)\tQ\u0006\rE\u0002\u00187vK!\u0001X\n\u0003\r=\u0003H/[8o!\tAc\fB\u0003`\u0015\t\u00071FA\u0001U\u0011\u0015\t'\u00021\u0001c\u0003\u0019y\u0007\u000f\u001e*fMB\u0019qcW2\u0011\u0007}2U,A\u0004u_N\u001b\u0017\r\\1\u0015\u0005\u0019dGC\u0001\u001ah\u0011\u0019A7\u0002\"a\u0001S\u0006!!m\u001c3z!\r9\"NM\u0005\u0003WN\u0011\u0001\u0002\u00102z]\u0006lWM\u0010\u0005\u0006[.\u0001\raJ\u0001\u0004W\u0016L\u0018A\u0002;p\u0015\u00064\u0018\r\u0006\u0002qgR\u0011q%\u001d\u0005\u0007Q2!\t\u0019\u0001:\u0011\u0007]Qw\u0005C\u0003n\u0019\u0001\u0007!'\u0001\u0007u_*\u000bg/Y(qi&|g\u000e\u0006\u0002wuR\u0011q\u000f\u001f\t\u0004/m;\u0003B\u00025\u000e\t\u0003\u0007\u0011\u0010E\u0002\u0018U^DQ!\\\u0007A\u0002I\u0002\"\u0001`?\u000e\u0003=I!A`\b\u0003\u0017MKXNY8m)\u0006\u0014G.\u001a"
)
public interface TwoWayCaches {
   static void $init$(final TwoWayCaches $this) {
   }

   public class TwoWayCache {
      private volatile SomeRef$ SomeRef$module;
      private final WeakHashMap toScalaMap;
      private final WeakHashMap toJavaMap;
      // $FF: synthetic field
      public final SymbolTable $outer;

      private SomeRef$ SomeRef() {
         if (this.SomeRef$module == null) {
            this.SomeRef$lzycompute$1();
         }

         return this.SomeRef$module;
      }

      public void enter(final Object j, final Object s) {
         SymbolTable var10000 = this.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (gilSynchronized_this.isCompilerUniverse()) {
               $anonfun$enter$1(this, j, s);
            } else {
               try {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  $anonfun$enter$1(this, j, s);
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

            }
         }
      }

      public Object toScala(final Object key, final Function0 body) {
         SymbolTable var10000 = this.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (gilSynchronized_this.isCompilerUniverse()) {
               Option var5 = this.toScalaMap.get(key);
               if (var5 != null) {
                  Option var6 = this.SomeRef().unapply(var5);
                  if (!var6.isEmpty()) {
                     return var6.get();
                  }
               }

               Object $anonfun$toScala$1_result = body.apply();
               this.enter(key, $anonfun$toScala$1_result);
               return $anonfun$toScala$1_result;
            } else {
               try {
                  label59: {
                     gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                     Option var8 = this.toScalaMap.get(key);
                     if (var8 != null) {
                        Option var9 = this.SomeRef().unapply(var8);
                        if (!var9.isEmpty()) {
                           var10000 = (SymbolTable)var9.get();
                           break label59;
                        }
                     }

                     Object $anonfun$toScala$1_result = body.apply();
                     this.enter(key, $anonfun$toScala$1_result);
                     var10000 = (SymbolTable)$anonfun$toScala$1_result;
                  }

                  Object var13 = null;
                  Object var14 = null;
                  Object var15 = null;
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var10000;
            }
         }
      }

      public Object toJava(final Object key, final Function0 body) {
         SymbolTable var10000 = this.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (gilSynchronized_this.isCompilerUniverse()) {
               Option var5 = this.toJavaMap.get(key);
               if (var5 != null) {
                  Option var6 = this.SomeRef().unapply(var5);
                  if (!var6.isEmpty()) {
                     return var6.get();
                  }
               }

               Object $anonfun$toJava$1_result = body.apply();
               this.enter($anonfun$toJava$1_result, key);
               return $anonfun$toJava$1_result;
            } else {
               try {
                  label59: {
                     gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                     Option var8 = this.toJavaMap.get(key);
                     if (var8 != null) {
                        Option var9 = this.SomeRef().unapply(var8);
                        if (!var9.isEmpty()) {
                           var10000 = (SymbolTable)var9.get();
                           break label59;
                        }
                     }

                     Object $anonfun$toJava$1_result = body.apply();
                     this.enter($anonfun$toJava$1_result, key);
                     var10000 = (SymbolTable)$anonfun$toJava$1_result;
                  }

                  Object var13 = null;
                  Object var14 = null;
                  Object var15 = null;
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var10000;
            }
         }
      }

      public Option toJavaOption(final Object key, final Function0 body) {
         SymbolTable var10000 = this.scala$reflect$runtime$TwoWayCaches$TwoWayCache$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (gilSynchronized_this.isCompilerUniverse()) {
               label107: {
                  Option var5 = this.toJavaMap.get(key);
                  if (var5 != null) {
                     Option var6 = this.SomeRef().unapply(var5);
                     if (!var6.isEmpty()) {
                        Object $anonfun$toJavaOption$1_v = var6.get();
                        var10000 = new Some($anonfun$toJavaOption$1_v);
                        break label107;
                     }
                  }

                  Option $anonfun$toJavaOption$1_result = (Option)body.apply();
                  if ($anonfun$toJavaOption$1_result == null) {
                     throw null;
                  }

                  if (!$anonfun$toJavaOption$1_result.isEmpty()) {
                     Object var9 = $anonfun$toJavaOption$1_result.get();
                     this.enter(var9, key);
                  }

                  var10000 = $anonfun$toJavaOption$1_result;
               }

               Object var17 = null;
               Object var18 = null;
               Object var19 = null;
               Object var20 = null;
               Object var21 = null;
            } else {
               try {
                  label108: {
                     gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                     Option var10 = this.toJavaMap.get(key);
                     if (var10 != null) {
                        Option var11 = this.SomeRef().unapply(var10);
                        if (!var11.isEmpty()) {
                           Object $anonfun$toJavaOption$1_v = var11.get();
                           var10000 = new Some($anonfun$toJavaOption$1_v);
                           break label108;
                        }
                     }

                     Option $anonfun$toJavaOption$1_result = (Option)body.apply();
                     if ($anonfun$toJavaOption$1_result == null) {
                        throw null;
                     }

                     if (!$anonfun$toJavaOption$1_result.isEmpty()) {
                        Object var14 = $anonfun$toJavaOption$1_result.get();
                        this.enter(var14, key);
                     }

                     var10000 = $anonfun$toJavaOption$1_result;
                  }

                  Object var22 = null;
                  Object var23 = null;
                  Object var24 = null;
                  Object var25 = null;
                  Object var26 = null;
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }
            }

            return (Option)var10000;
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$runtime$TwoWayCaches$TwoWayCache$$$outer() {
         return this.$outer;
      }

      private final void SomeRef$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.SomeRef$module == null) {
               this.SomeRef$module = new SomeRef$();
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      // $FF: synthetic method
      public static final void $anonfun$enter$1(final TwoWayCache $this, final Object j$1, final Object s$1) {
         $this.toScalaMap.update(j$1, new WeakReference(s$1));
         $this.toJavaMap.update(s$1, new WeakReference(j$1));
      }

      // $FF: synthetic method
      public static final Object $anonfun$toScala$1(final TwoWayCache $this, final Object key$1, final Function0 body$1) {
         Option var3 = $this.toScalaMap.get(key$1);
         if (var3 != null) {
            Option var4 = $this.SomeRef().unapply(var3);
            if (!var4.isEmpty()) {
               return var4.get();
            }
         }

         Object result = body$1.apply();
         $this.enter(key$1, result);
         return result;
      }

      // $FF: synthetic method
      public static final Object $anonfun$toJava$1(final TwoWayCache $this, final Object key$2, final Function0 body$2) {
         Option var3 = $this.toJavaMap.get(key$2);
         if (var3 != null) {
            Option var4 = $this.SomeRef().unapply(var3);
            if (!var4.isEmpty()) {
               return var4.get();
            }
         }

         Object result = body$2.apply();
         $this.enter(result, key$2);
         return result;
      }

      // $FF: synthetic method
      public static final void $anonfun$toJavaOption$2(final TwoWayCache $this, final Object key$3, final Object value) {
         $this.enter(value, key$3);
      }

      // $FF: synthetic method
      public static final Option $anonfun$toJavaOption$1(final TwoWayCache $this, final Object key$3, final Function0 body$3) {
         Option var3 = $this.toJavaMap.get(key$3);
         if (var3 != null) {
            Option var4 = $this.SomeRef().unapply(var3);
            if (!var4.isEmpty()) {
               Object v = var4.get();
               return new Some(v);
            }
         }

         Option result = (Option)body$3.apply();
         if (result == null) {
            throw null;
         } else {
            if (!result.isEmpty()) {
               Object var7 = result.get();
               $this.enter(var7, key$3);
            }

            return result;
         }
      }

      public TwoWayCache() {
         if (TwoWayCaches.this == null) {
            throw null;
         } else {
            this.$outer = TwoWayCaches.this;
            super();
            this.toScalaMap = new WeakHashMap();
            this.toJavaMap = new WeakHashMap();
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$toJavaOption$2$adapted(final TwoWayCache $this, final Object key$3, final Object value) {
         $anonfun$toJavaOption$2($this, key$3, value);
         return BoxedUnit.UNIT;
      }

      private class SomeRef$ {
         public Option unapply(final Option optRef) {
            if (optRef == null) {
               throw null;
            } else {
               return (Option)(optRef.isDefined() ? .MODULE$.apply(((Reference)optRef.get()).get()) : scala.None..MODULE$);
            }
         }

         public SomeRef$() {
         }
      }
   }
}
