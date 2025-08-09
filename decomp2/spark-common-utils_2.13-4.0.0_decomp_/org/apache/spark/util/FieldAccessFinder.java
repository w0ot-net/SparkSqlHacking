package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import org.apache.xbean.asm9.ClassVisitor;
import org.apache.xbean.asm9.MethodVisitor;
import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.collection.mutable.Growable;
import scala.collection.mutable.Map;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uc!\u0002\u0007\u000e\u00015)\u0002\u0002\u0003\u0010\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0011\t\u0011!\u0003!\u0011!Q\u0001\n%C\u0001\u0002\u0014\u0001\u0003\u0002\u0003\u0006I!\u0014\u0005\t1\u0002\u0011\t\u0011)A\u00053\")q\f\u0001C\u0001A\")Q\u000f\u0001C!m\u001eQ\u0011QC\u0007\u0002\u0002#\u0005Q\"a\u0006\u0007\u00131i\u0011\u0011!E\u0001\u001b\u0005e\u0001BB0\t\t\u0003\t\t\u0003C\u0005\u0002$!\t\n\u0011\"\u0001\u0002&!I\u0011Q\t\u0005\u0012\u0002\u0013\u0005\u0011q\t\u0002\u0012\r&,G\u000eZ!dG\u0016\u001c8OR5oI\u0016\u0014(B\u0001\b\u0010\u0003\u0011)H/\u001b7\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001c\"\u0001\u0001\f\u0011\u0005]aR\"\u0001\r\u000b\u0005eQ\u0012\u0001B1t[fR!aG\t\u0002\u000ba\u0014W-\u00198\n\u0005uA\"\u0001D\"mCN\u001ch+[:ji>\u0014\u0018A\u00024jK2$7o\u0001\u0001\u0011\t\u0005B#FQ\u0007\u0002E)\u00111\u0005J\u0001\b[V$\u0018M\u00197f\u0015\t)c%\u0001\u0006d_2dWm\u0019;j_:T\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\t\u00121!T1qa\tY\u0003\bE\u0002-gYr!!L\u0019\u0011\u000592S\"A\u0018\u000b\u0005Az\u0012A\u0002\u001fs_>$h(\u0003\u00023M\u00051\u0001K]3eK\u001aL!\u0001N\u001b\u0003\u000b\rc\u0017m]:\u000b\u0005I2\u0003CA\u001c9\u0019\u0001!\u0011\"O\u0001\u0002\u0002\u0003\u0005)\u0011\u0001\u001e\u0003\t}#3\u0007O\t\u0003w}\u0002\"\u0001P\u001f\u000e\u0003\u0019J!A\u0010\u0014\u0003\u000f9{G\u000f[5oOB\u0011A\bQ\u0005\u0003\u0003\u001a\u00121!\u00118z!\r\t3)R\u0005\u0003\t\n\u00121aU3u!\tac)\u0003\u0002Hk\t11\u000b\u001e:j]\u001e\f\u0001CZ5oIR\u0013\u0018M\\:ji&4X\r\\=\u0011\u0005qR\u0015BA&'\u0005\u001d\u0011un\u001c7fC:\fab\u001d9fG&4\u0017nY'fi\"|G\rE\u0002=\u001dBK!a\u0014\u0014\u0003\r=\u0003H/[8oa\t\tf\u000bE\u0002S'Vk\u0011!D\u0005\u0003)6\u0011\u0001#T3uQ>$\u0017\nZ3oi&4\u0017.\u001a:\u0011\u0005]2F!C,\u0004\u0003\u0003\u0005\tQ!\u0001;\u0005\u0011yFeM\u001d\u0002\u001dYL7/\u001b;fI6+G\u000f[8egB\u0019\u0011e\u0011.1\u0005mk\u0006c\u0001*T9B\u0011q'\u0018\u0003\n=\u0012\t\t\u0011!A\u0003\u0002i\u0012Aa\u0018\u00135a\u00051A(\u001b8jiz\"R!\u00192iS>\u0004\"A\u0015\u0001\t\u000by)\u0001\u0019A2\u0011\t\u0005BCM\u0011\u0019\u0003K\u001e\u00042\u0001L\u001ag!\t9t\rB\u0005:E\u0006\u0005\t\u0011!B\u0001u!)\u0001*\u0002a\u0001\u0013\"9A*\u0002I\u0001\u0002\u0004Q\u0007c\u0001\u001fOWB\u0012AN\u001c\t\u0004%Nk\u0007CA\u001co\t%9\u0016.!A\u0001\u0002\u000b\u0005!\bC\u0004Y\u000bA\u0005\t\u0019\u00019\u0011\u0007\u0005\u001a\u0015\u000f\r\u0002siB\u0019!kU:\u0011\u0005]\"H!\u00030p\u0003\u0003\u0005\tQ!\u0001;\u0003-1\u0018n]5u\u001b\u0016$\bn\u001c3\u0015\u0013]Tx0a\u0001\u0002\b\u0005-\u0001CA\fy\u0013\tI\bDA\u0007NKRDw\u000e\u001a,jg&$xN\u001d\u0005\u0006w\u001a\u0001\r\u0001`\u0001\u0007C\u000e\u001cWm]:\u0011\u0005qj\u0018B\u0001@'\u0005\rIe\u000e\u001e\u0005\u0007\u0003\u00031\u0001\u0019A#\u0002\t9\fW.\u001a\u0005\u0007\u0003\u000b1\u0001\u0019A#\u0002\t\u0011,7o\u0019\u0005\u0007\u0003\u00131\u0001\u0019A#\u0002\u0007MLw\rC\u0004\u0002\u000e\u0019\u0001\r!a\u0004\u0002\u0015\u0015D8-\u001a9uS>t7\u000f\u0005\u0003=\u0003#)\u0015bAA\nM\t)\u0011I\u001d:bs\u0006\tb)[3mI\u0006\u001b7-Z:t\r&tG-\u001a:\u0011\u0005IC1c\u0001\u0005\u0002\u001cA\u0019A(!\b\n\u0007\u0005}aE\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\u0003/\t1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aTCAA\u0014U\u0011\tI#a\r\u0011\tqr\u00151\u0006\u0019\u0005\u0003[\t\t\u0004\u0005\u0003S'\u0006=\u0002cA\u001c\u00022\u0011IqKCA\u0001\u0002\u0003\u0015\tAO\u0016\u0003\u0003k\u0001B!a\u000e\u0002B5\u0011\u0011\u0011\b\u0006\u0005\u0003w\ti$A\u0005v]\u000eDWmY6fI*\u0019\u0011q\b\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002D\u0005e\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIQ*\"!!\u0013+\t\u0005-\u00131\u0007\t\u0005C\r\u000bi\u0005\r\u0003\u0002P\u0005M\u0003\u0003\u0002*T\u0003#\u00022aNA*\t%q6\"!A\u0001\u0002\u000b\u0005!\b"
)
public class FieldAccessFinder extends ClassVisitor {
   public final Map org$apache$spark$util$FieldAccessFinder$$fields;
   public final boolean org$apache$spark$util$FieldAccessFinder$$findTransitively;
   private final Option specificMethod;
   public final Set org$apache$spark$util$FieldAccessFinder$$visitedMethods;

   public static Set $lessinit$greater$default$4() {
      return FieldAccessFinder$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option $lessinit$greater$default$3() {
      return FieldAccessFinder$.MODULE$.$lessinit$greater$default$3();
   }

   public MethodVisitor visitMethod(final int access, final String name, final String desc, final String sig, final String[] exceptions) {
      if (this.specificMethod.isDefined()) {
         String var10000 = ((MethodIdentifier)this.specificMethod.get()).name();
         if (var10000 == null) {
            if (name != null) {
               return null;
            }
         } else if (!var10000.equals(name)) {
            return null;
         }

         var10000 = ((MethodIdentifier)this.specificMethod.get()).desc();
         if (var10000 == null) {
            if (desc != null) {
               return null;
            }
         } else if (!var10000.equals(desc)) {
            return null;
         }
      }

      return new MethodVisitor() {
         // $FF: synthetic field
         private final FieldAccessFinder $outer;

         public void visitFieldInsn(final int op, final String owner, final String name, final String desc) {
            if (op == 180) {
               this.$outer.org$apache$spark$util$FieldAccessFinder$$fields.keys().withFilter((cl) -> BoxesRunTime.boxToBoolean($anonfun$visitFieldInsn$7(owner, cl))).foreach((cl) -> (Set)((Growable)this.$outer.org$apache$spark$util$FieldAccessFinder$$fields.apply(cl)).$plus$eq(name));
            }
         }

         public void visitMethodInsn(final int op, final String owner, final String name, final String desc, final boolean itf) {
            this.$outer.org$apache$spark$util$FieldAccessFinder$$fields.keys().withFilter((cl) -> BoxesRunTime.boxToBoolean($anonfun$visitMethodInsn$7(owner, cl))).foreach((cl) -> {
               $anonfun$visitMethodInsn$8(this, op, owner, name, desc, cl);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final boolean $anonfun$visitFieldInsn$7(final String owner$1, final Class cl) {
            boolean var3;
            label23: {
               String var10000 = cl.getName();
               String var2 = owner$1.replace('/', '.');
               if (var10000 == null) {
                  if (var2 == null) {
                     break label23;
                  }
               } else if (var10000.equals(var2)) {
                  break label23;
               }

               var3 = false;
               return var3;
            }

            var3 = true;
            return var3;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$visitMethodInsn$7(final String owner$2, final Class cl) {
            boolean var3;
            label23: {
               String var10000 = cl.getName();
               String var2 = owner$2.replace('/', '.');
               if (var10000 == null) {
                  if (var2 == null) {
                     break label23;
                  }
               } else if (var10000.equals(var2)) {
                  break label23;
               }

               var3 = false;
               return var3;
            }

            var3 = true;
            return var3;
         }

         // $FF: synthetic method
         public static final void $anonfun$visitMethodInsn$8(final Object $this, final int op$1, final String owner$2, final String name$5, final String desc$3, final Class cl) {
            if (op$1 == 182 && owner$2.endsWith("$iwC") && !name$5.endsWith("$outer")) {
               ((Growable)$this.$outer.org$apache$spark$util$FieldAccessFinder$$fields.apply(cl)).$plus$eq(name$5);
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            if ($this.$outer.org$apache$spark$util$FieldAccessFinder$$findTransitively) {
               MethodIdentifier m = new MethodIdentifier(cl, name$5, desc$3);
               if (!$this.$outer.org$apache$spark$util$FieldAccessFinder$$visitedMethods.contains(m)) {
                  $this.$outer.org$apache$spark$util$FieldAccessFinder$$visitedMethods.$plus$eq(m);
                  Class currentClass = cl;
                  .MODULE$.assert(cl != null, () -> "The outer class can't be null.");

                  while(currentClass != null) {
                     ClosureCleaner$.MODULE$.getClassReader(currentClass).accept(new FieldAccessFinder($this.$outer.org$apache$spark$util$FieldAccessFinder$$fields, $this.$outer.org$apache$spark$util$FieldAccessFinder$$findTransitively, new Some(m), $this.$outer.org$apache$spark$util$FieldAccessFinder$$visitedMethods), 0);
                     currentClass = currentClass.getSuperclass();
                  }

               }
            }
         }

         public {
            if (FieldAccessFinder.this == null) {
               throw null;
            } else {
               this.$outer = FieldAccessFinder.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public FieldAccessFinder(final Map fields, final boolean findTransitively, final Option specificMethod, final Set visitedMethods) {
      super(589824);
      this.org$apache$spark$util$FieldAccessFinder$$fields = fields;
      this.org$apache$spark$util$FieldAccessFinder$$findTransitively = findTransitively;
      this.specificMethod = specificMethod;
      this.org$apache$spark$util$FieldAccessFinder$$visitedMethods = visitedMethods;
   }
}
