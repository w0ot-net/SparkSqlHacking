package scala.collection.mutable;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import scala.Array$;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.generic.CommonErrors$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=aa\u0002\b\u0010!\u0003\r\tA\u0006\u0005\u0006k\u0001!\tA\u000e\u0005\u0006u\u00011\tb\u000f\u0005\u0006\u007f\u0001!)\u0005\u0011\u0005\u0006\u0003\u00021\t\u0002\u0011\u0005\u0006\u0005\u00021\tb\u0011\u0005\u0006\u0015\u00021\tb\u0013\u0005\u0006\u001d\u0002!)b\u0014\u0005\b/\u0002\t\n\u0011\"\u0006Y\u0011\u0015\u0019\u0007\u0001\"\u0001e\u0011\u0015\u0019\b\u0001\"\u0011u\u0011\u0015)\b\u0001\"\u0011w\u0011\u0015Q\b\u0001\"\u0011|\u0011\u001d\t9\u0001\u0001C!\u0003\u0013\u0011Q\"\u0011:sCf$U-];f\u001fB\u001c(B\u0001\t\u0012\u0003\u001diW\u000f^1cY\u0016T!AE\n\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0007\u0001)Ba\u0006\u0012-eM\u0019\u0001\u0001\u0007\u000f\u0011\u0005eQR\"A\n\n\u0005m\u0019\"AB!osJ+g\rE\u0003\u001e=\u0001Z\u0013'D\u0001\u0012\u0013\ty\u0012CA\u000bTiJL7\r^(qi&l\u0017N_3e'\u0016\fx\n]:\u0011\u0005\u0005\u0012C\u0002\u0001\u0003\u0006G\u0001\u0011\r\u0001\n\u0002\u0002\u0003F\u0011Q\u0005\u000b\t\u00033\u0019J!aJ\n\u0003\u000f9{G\u000f[5oOB\u0011\u0011$K\u0005\u0003UM\u00111!\u00118z!\t\tC\u0006\u0002\u0004.\u0001\u0011\u0015\rA\f\u0002\u0003\u0007\u000e+\"\u0001J\u0018\u0005\u000bAb#\u0019\u0001\u0013\u0003\t}#C%\r\t\u0003CI\"aa\r\u0001\u0005\u0006\u0004!$!A\"\u0012\u0005\u0015B\u0012A\u0002\u0013j]&$H\u0005F\u00018!\tI\u0002(\u0003\u0002:'\t!QK\\5u\u0003\u0015\t'O]1z+\u0005a\u0004cA\r>1%\u0011ah\u0005\u0002\u0006\u0003J\u0014\u0018-_\u0001\u0006G2|g.\u001a\u000b\u0002c\u0005)1\u000e\\8oK\u00069qNZ!se\u0006LHcA\u0019E\u000b\")!(\u0002a\u0001y!)a)\u0002a\u0001\u000f\u0006\u0019QM\u001c3\u0011\u0005eA\u0015BA%\u0014\u0005\rIe\u000e^\u0001\fgR\f'\u000f^0%a2,8\u000f\u0006\u0002H\u0019\")QJ\u0002a\u0001\u000f\u0006\u0019\u0011\u000e\u001a=\u0002\u001bI,\u0017/^5sK\n{WO\u001c3t)\r9\u0004+\u0015\u0005\u0006\u001b\u001e\u0001\ra\u0012\u0005\b%\u001e\u0001\n\u00111\u0001H\u0003\u0015)h\u000e^5mQ\t9A\u000b\u0005\u0002\u001a+&\u0011ak\u0005\u0002\u0007S:d\u0017N\\3\u0002/I,\u0017/^5sK\n{WO\u001c3tI\u0011,g-Y;mi\u0012\u0012T#A-+\u0005\u001dS6&A.\u0011\u0005q\u000bW\"A/\u000b\u0005y{\u0016!C;oG\",7m[3e\u0015\t\u00017#\u0001\u0006b]:|G/\u0019;j_:L!AY/\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\td_BL8\u000b\\5dKR{\u0017I\u001d:bsR)Q-\u001c4pc:\u0011\u0011E\u001a\u0005\u0006O&\u0001\r\u0001[\u0001\u0005I\u0016\u001cH\u000f\r\u0002jWB\u0019\u0011$\u00106\u0011\u0005\u0005ZG!\u00037g\u0003\u0003\u0005\tQ!\u0001%\u0005\ryFE\r\u0005\u0006]&\u0001\raR\u0001\tgJ\u001c7\u000b^1si\")\u0001/\u0003a\u0001\u000f\u0006IA-Z:u'R\f'\u000f\u001e\u0005\u0006e&\u0001\raR\u0001\t[\u0006D\u0018\n^3ng\u00069!/\u001a<feN,W#A\u0019\u0002\u000bMd\u0017nY3\u0015\u0007E:\u0018\u0010C\u0003y\u0017\u0001\u0007q)\u0001\u0003ge>l\u0007\"\u0002*\f\u0001\u00049\u0015aB:mS\u0012Lgn\u001a\u000b\u0005y~\f\u0019\u0001E\u0002\u001e{FJ!A`\t\u0003\u0011%#XM]1u_JDa!!\u0001\r\u0001\u00049\u0015AB<j]\u0012|w\u000f\u0003\u0004\u0002\u00061\u0001\raR\u0001\u0005gR,\u0007/A\u0004he>,\b/\u001a3\u0015\u0007q\fY\u0001\u0003\u0004\u0002\u000e5\u0001\raR\u0001\u0002]\u0002"
)
public interface ArrayDequeOps extends StrictOptimizedSeqOps {
   Object[] array();

   // $FF: synthetic method
   static Object clone$(final ArrayDequeOps $this) {
      return $this.clone();
   }

   default Object clone() {
      return this.klone();
   }

   Object klone();

   Object ofArray(final Object[] array, final int end);

   int start_$plus(final int idx);

   // $FF: synthetic method
   static void requireBounds$(final ArrayDequeOps $this, final int idx, final int until) {
      $this.requireBounds(idx, until);
   }

   default void requireBounds(final int idx, final int until) {
      if (idx < 0 || idx >= until) {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, until - 1);
      }
   }

   // $FF: synthetic method
   static int requireBounds$default$2$(final ArrayDequeOps $this) {
      return $this.requireBounds$default$2();
   }

   default int requireBounds$default$2() {
      return this.length();
   }

   // $FF: synthetic method
   static Object copySliceToArray$(final ArrayDequeOps $this, final int srcStart, final Object dest, final int destStart, final int maxItems) {
      return $this.copySliceToArray(srcStart, dest, destStart, maxItems);
   }

   default Object copySliceToArray(final int srcStart, final Object dest, final int destStart, final int maxItems) {
      int requireBounds_until = Array.getLength(dest) + 1;
      if (destStart >= 0 && destStart < requireBounds_until) {
         int toCopy = Math.min(maxItems, Math.min(this.length() - srcStart, Array.getLength(dest) - destStart));
         if (toCopy > 0) {
            int requireBounds_until = this.length();
            if (srcStart < 0 || srcStart >= requireBounds_until) {
               throw CommonErrors$.MODULE$.indexOutOfBounds(srcStart, requireBounds_until - 1);
            }

            int startIdx = this.start_$plus(srcStart);
            int block1 = Math.min(toCopy, this.array().length - startIdx);
            Array$.MODULE$.copy(this.array(), startIdx, dest, destStart, block1);
            int block2 = toCopy - block1;
            if (block2 > 0) {
               Array$.MODULE$.copy(this.array(), 0, dest, destStart + block1, block2);
            }
         }

         return dest;
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(destStart, requireBounds_until - 1);
      }
   }

   // $FF: synthetic method
   static Object reverse$(final ArrayDequeOps $this) {
      return $this.reverse();
   }

   default Object reverse() {
      int n = this.length();
      Object[] arr = ArrayDeque$.MODULE$.alloc(n);

      for(int i = 0; i < n; ++i) {
         arr[i] = this.apply(n - i - 1);
      }

      return this.ofArray(arr, n);
   }

   // $FF: synthetic method
   static Object slice$(final ArrayDequeOps $this, final int from, final int until) {
      return $this.slice(from, until);
   }

   default Object slice(final int from, final int until) {
      int n = this.length();
      int left = Math.max(0, Math.min(n, from));
      int len = Math.max(0, Math.min(n, until)) - left;
      if (len <= 0) {
         return this.empty();
      } else if (len >= n) {
         return this.klone();
      } else {
         Object[] array2 = this.copySliceToArray(left, ArrayDeque$.MODULE$.alloc(len), 0, len);
         return this.ofArray(array2, len);
      }
   }

   // $FF: synthetic method
   static Iterator sliding$(final ArrayDequeOps $this, final int window, final int step) {
      return $this.sliding(window, step);
   }

   default Iterator sliding(final int window, final int step) {
      if (window <= 0 || step <= 0) {
         throw new IllegalArgumentException((new java.lang.StringBuilder(20)).append("requirement failed: ").append($anonfun$sliding$1(window, step)).toString());
      } else {
         int var3 = this.length();
         switch (var3) {
            case 0:
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty;
            default:
               if (var3 <= window) {
                  Iterator$ var9 = Iterator$.MODULE$;
                  Object single_a = this.slice(0, this.length());
                  return new AbstractIterator(single_a) {
                     private boolean consumed;
                     private final Object a$1;

                     public boolean hasNext() {
                        return !this.consumed;
                     }

                     public Object next() {
                        if (this.consumed) {
                           Iterator$ var10000 = Iterator$.MODULE$;
                           return Iterator$.scala$collection$Iterator$$_empty.next();
                        } else {
                           this.consumed = true;
                           return this.a$1;
                        }
                     }

                     public Iterator sliceIterator(final int from, final int until) {
                        if (!this.consumed && from <= 0 && until != 0) {
                           return this;
                        } else {
                           Iterator$ var10000 = Iterator$.MODULE$;
                           return Iterator$.scala$collection$Iterator$$_empty;
                        }
                     }

                     public {
                        this.a$1 = a$1;
                        this.consumed = false;
                     }
                  };
               } else {
                  int lag = window > step ? window - step : 0;
                  Iterator$ var8 = Iterator$.MODULE$;
                  int range_end = var3 - lag;
                  int range_start = 0;
                  return (new AbstractIterator(step, range_start, range_end) {
                     private int i;
                     private boolean hasOverflowed;
                     private final int end$2;
                     private final int step$2;

                     public int knownSize() {
                        scala.math.package$ var10000 = scala.math.package$.MODULE$;
                        double size = Math.ceil((double)((long)this.end$2 - (long)this.i) / (double)this.step$2);
                        if (size < (double)0) {
                           return 0;
                        } else {
                           return size > (double)Integer.MAX_VALUE ? -1 : (int)size;
                        }
                     }

                     public boolean hasNext() {
                        return (this.step$2 <= 0 || this.i < this.end$2) && (this.step$2 >= 0 || this.i > this.end$2) && !this.hasOverflowed;
                     }

                     public int next() {
                        if (this.hasNext()) {
                           int result = this.i;
                           int nextValue = this.i + this.step$2;
                           this.hasOverflowed = this.step$2 > 0 == nextValue < this.i;
                           this.i = nextValue;
                           return result;
                        } else {
                           Iterator$ var10000 = Iterator$.MODULE$;
                           return BoxesRunTime.unboxToInt(Iterator$.scala$collection$Iterator$$_empty.next());
                        }
                     }

                     public {
                        this.end$2 = end$2;
                        this.step$2 = step$2;
                        if (step$2 == 0) {
                           throw new IllegalArgumentException("zero step");
                        } else {
                           this.i = start$2;
                           this.hasOverflowed = false;
                        }
                     }
                  }).map((i) -> $anonfun$sliding$2(this, window, BoxesRunTime.unboxToInt(i)));
               }
         }
      }
   }

   // $FF: synthetic method
   static Iterator grouped$(final ArrayDequeOps $this, final int n) {
      return $this.grouped(n);
   }

   default Iterator grouped(final int n) {
      return this.sliding(n, n);
   }

   // $FF: synthetic method
   static String $anonfun$sliding$1(final int window$1, final int step$1) {
      return (new java.lang.StringBuilder(44)).append("window=").append(window$1).append(" and step=").append(step$1).append(", but both must be positive").toString();
   }

   // $FF: synthetic method
   static Object $anonfun$sliding$2(final ArrayDequeOps $this, final int window$1, final int i) {
      return $this.slice(i, i + window$1);
   }

   static void $init$(final ArrayDequeOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
