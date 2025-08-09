package org.apache.spark.api.java;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.hashing.package.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dqA\u0002\u0007\u000e\u0011\u0003\trC\u0002\u0004\u001a\u001b!\u0005\u0011C\u0007\u0005\u0006C\u0005!\ta\t\u0005\u0006I\u0005!\t!\n\u0005\u0006u\u0005!\ta\u000f\u0004\u0005\u007f\u0005\u0001\u0001\t\u0003\u0005V\u000b\t\u0005\t\u0015!\u0003W\u0011\u0015\tS\u0001\"\u0001]\u0011\u0015yV\u0001\"\u0011a\u0011\u0015!W\u0001\"\u0011f\u0011\u0015YW\u0001\"\u0011m\u0011\u0015qW\u0001\"\u0011p\u0003%Q\u0015M^1Vi&d7O\u0003\u0002\u000f\u001f\u0005!!.\u0019<b\u0015\t\u0001\u0012#A\u0002ba&T!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'o\u001a\t\u00031\u0005i\u0011!\u0004\u0002\n\u0015\u00064\u0018-\u0016;jYN\u001c\"!A\u000e\u0011\u0005qyR\"A\u000f\u000b\u0003y\tQa]2bY\u0006L!\u0001I\u000f\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#A\f\u0002!=\u0004H/[8o)>|\u0005\u000f^5p]\u0006dWC\u0001\u0014-)\t9S\u0007E\u0002\u0019Q)J!!K\u0007\u0003\u0011=\u0003H/[8oC2\u0004\"a\u000b\u0017\r\u0001\u0011)Qf\u0001b\u0001]\t\tA+\u0005\u00020eA\u0011A\u0004M\u0005\u0003cu\u0011qAT8uQ&tw\r\u0005\u0002\u001dg%\u0011A'\b\u0002\u0004\u0003:L\b\"\u0002\u001c\u0004\u0001\u00049\u0014AB8qi&|g\u000eE\u0002\u001dq)J!!O\u000f\u0003\r=\u0003H/[8o\u0003ai\u0017\r]!t'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a&bm\u0006l\u0015\r]\u000b\u0005yy\f\t\u0001F\u0002>\u0003\u0007\u0001BAP\u0003~\u007f6\t\u0011A\u0001\fTKJL\u0017\r\\5{C\ndW-T1q/J\f\u0007\u000f]3s+\r\t%*T\n\u0004\u000b\t{\u0005\u0003B\"H\u00132k\u0011\u0001\u0012\u0006\u0003\u000b\u001a\u000bA!\u001e;jY*\ta\"\u0003\u0002I\t\nY\u0011IY:ue\u0006\u001cG/T1q!\tY#\nB\u0003L\u000b\t\u0007aFA\u0001B!\tYS\nB\u0003O\u000b\t\u0007aFA\u0001C!\t\u00016+D\u0001R\u0015\t\u0011f)\u0001\u0002j_&\u0011A+\u0015\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u000bk:$WM\u001d7zS:<\u0007\u0003B,[\u00132k\u0011\u0001\u0017\u0006\u00033v\t!bY8mY\u0016\u001cG/[8o\u0013\tY\u0006LA\u0002NCB$\"!\u00180\u0011\ty*\u0011\n\u0014\u0005\u0006+\u001e\u0001\rAV\u0001\u0005g&TX\rF\u0001b!\ta\"-\u0003\u0002d;\t\u0019\u0011J\u001c;\u0002\u0017\r|g\u000e^1j]N\\U-\u001f\u000b\u0003M&\u0004\"\u0001H4\n\u0005!l\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006U&\u0001\raG\u0001\u0004W\u0016L\u0018aA4fiR\u0011A*\u001c\u0005\u0006U*\u0001\raG\u0001\tK:$(/_*fiR\t\u0001\u000fE\u0002DcNL!A\u001d#\u0003\u0007M+G\u000f\u0005\u0003uu&ceBA;y\u001d\t1x/D\u0001G\u0013\t)e)\u0003\u0002z\t\u0006\u0019Q*\u00199\n\u0005md(!B#oiJL(BA=E!\tYc\u0010B\u0003L\t\t\u0007a\u0006E\u0002,\u0003\u0003!QA\u0014\u0003C\u00029Ba!\u0016\u0003A\u0002\u0005\u0015\u0001\u0003B,[{~\u0004"
)
public final class JavaUtils {
   public static SerializableMapWrapper mapAsSerializableJavaMap(final Map underlying) {
      return JavaUtils$.MODULE$.mapAsSerializableJavaMap(underlying);
   }

   public static Optional optionToOptional(final Option option) {
      return JavaUtils$.MODULE$.optionToOptional(option);
   }

   public static class SerializableMapWrapper extends AbstractMap implements Serializable {
      public final Map org$apache$spark$api$java$JavaUtils$SerializableMapWrapper$$underlying;

      public int size() {
         return this.org$apache$spark$api$java$JavaUtils$SerializableMapWrapper$$underlying.size();
      }

      public boolean containsKey(final Object key) {
         boolean var10000;
         try {
            var10000 = this.org$apache$spark$api$java$JavaUtils$SerializableMapWrapper$$underlying.contains(key);
         } catch (ClassCastException var2) {
            var10000 = false;
         }

         return var10000;
      }

      public Object get(final Object key) {
         Object var10000;
         try {
            var10000 = this.org$apache$spark$api$java$JavaUtils$SerializableMapWrapper$$underlying.getOrElse(key, () -> null);
         } catch (ClassCastException var2) {
            var10000 = null;
         }

         return var10000;
      }

      public Set entrySet() {
         return new AbstractSet() {
            // $FF: synthetic field
            private final SerializableMapWrapper $outer;

            public int size() {
               return this.$outer.size();
            }

            public Iterator iterator() {
               return new Iterator() {
                  private final scala.collection.Iterator ui;
                  private Option prev;
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public void forEachRemaining(final Consumer x$1) {
                     super.forEachRemaining(x$1);
                  }

                  private scala.collection.Iterator ui() {
                     return this.ui;
                  }

                  private Option prev() {
                     return this.prev;
                  }

                  private void prev_$eq(final Option x$1) {
                     this.prev = x$1;
                  }

                  public boolean hasNext() {
                     return this.ui().hasNext();
                  }

                  public java.util.Map.Entry next() {
                     Tuple2 var3 = (Tuple2)this.ui().next();
                     if (var3 != null) {
                        Object k = var3._1();
                        Object v = var3._2();
                        Tuple2 var2 = new Tuple2(k, v);
                        Object k = var2._1();
                        Object v = var2._2();
                        this.prev_$eq(new Some(k));
                        return new java.util.Map.Entry(k, v) {
                           // $FF: synthetic field
                           private final <undefinedtype> $outer;
                           private final Object k$1;
                           private final Object v$1;

                           public Object getKey() {
                              return this.k$1;
                           }

                           public Object getValue() {
                              return this.v$1;
                           }

                           public Object setValue(final Object v1) {
                              return this.$outer.org$apache$spark$api$java$JavaUtils$SerializableMapWrapper$$anon$$anon$$$outer().org$apache$spark$api$java$JavaUtils$SerializableMapWrapper$$anon$$$outer().put(this.k$1, v1);
                           }

                           public int hashCode() {
                              return .MODULE$.byteswap32(this.k$1.hashCode()) + (.MODULE$.byteswap32(this.v$1.hashCode()) << 16);
                           }

                           public boolean equals(final Object other) {
                              if (!(other instanceof java.util.Map.Entry var4)) {
                                 return false;
                              } else {
                                 return BoxesRunTime.equals(this.k$1, var4.getKey()) && BoxesRunTime.equals(this.v$1, var4.getValue());
                              }
                           }

                           public {
                              if (<VAR_NAMELESS_ENCLOSURE> == null) {
                                 throw null;
                              } else {
                                 this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                                 this.k$1 = k$1;
                                 this.v$1 = v$1;
                              }
                           }
                        };
                     } else {
                        throw new MatchError(var3);
                     }
                  }

                  public void remove() {
                     Option var3 = this.prev();
                     if (var3 instanceof Some var4) {
                        Object k = var4.value();
                        Map var6 = this.$outer.org$apache$spark$api$java$JavaUtils$SerializableMapWrapper$$anon$$$outer().org$apache$spark$api$java$JavaUtils$SerializableMapWrapper$$underlying;
                        if (var6 instanceof scala.collection.mutable.Map var7) {
                           var7.remove(k);
                           this.prev_$eq(scala.None..MODULE$);
                           BoxedUnit var10000 = BoxedUnit.UNIT;
                           var10000 = BoxedUnit.UNIT;
                        } else {
                           throw new UnsupportedOperationException("remove");
                        }
                     } else {
                        throw new IllegalStateException("next must be called at least once before remove");
                     }
                  }

                  // $FF: synthetic method
                  public <undefinedtype> org$apache$spark$api$java$JavaUtils$SerializableMapWrapper$$anon$$anon$$$outer() {
                     return this.$outer;
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                        this.ui = org$apache$spark$api$java$JavaUtils$SerializableMapWrapper$$anon$$$outer().org$apache$spark$api$java$JavaUtils$SerializableMapWrapper$$underlying.iterator();
                        this.prev = scala.None..MODULE$;
                     }
                  }
               };
            }

            // $FF: synthetic method
            public SerializableMapWrapper org$apache$spark$api$java$JavaUtils$SerializableMapWrapper$$anon$$$outer() {
               return this.$outer;
            }

            public {
               if (SerializableMapWrapper.this == null) {
                  throw null;
               } else {
                  this.$outer = SerializableMapWrapper.this;
               }
            }
         };
      }

      public SerializableMapWrapper(final Map underlying) {
         this.org$apache$spark$api$java$JavaUtils$SerializableMapWrapper$$underlying = underlying;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
