package com.twitter.chill;

import java.lang.invoke.SerializedLambda;
import org.apache.xbean.asm7.ClassReader;
import org.apache.xbean.asm7.ClassVisitor;
import org.apache.xbean.asm7.MethodVisitor;
import scala.Option;
import scala.Some;
import scala.collection.mutable.Growable;
import scala.collection.mutable.Map;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055c\u0001B\u0006\r\u0001MA\u0001\u0002\t\u0001\u0003\u0002\u0003\u0006I!\t\u0005\t\u0013\u0002\u0011\t\u0011)A\u0005\u0015\"AQ\u000b\u0001B\u0001B\u0003%a\u000bC\u0003]\u0001\u0011\u0005Q\fC\u0003r\u0001\u0011\u0005#oB\u0005\u0002\u000e1\t\t\u0011#\u0001\u0002\u0010\u0019A1\u0002DA\u0001\u0012\u0003\t\t\u0002\u0003\u0004]\u000f\u0011\u0005\u0011\u0011\u0004\u0005\n\u000379\u0011\u0013!C\u0001\u0003;A\u0011\"!\u0010\b#\u0003%\t!a\u0010\u0003#\u0019KW\r\u001c3BG\u000e,7o\u001d$j]\u0012,'O\u0003\u0002\u000e\u001d\u0005)1\r[5mY*\u0011q\u0002E\u0001\bi^LG\u000f^3s\u0015\u0005\t\u0012aA2p[\u000e\u00011C\u0001\u0001\u0015!\t)b$D\u0001\u0017\u0015\t9\u0002$\u0001\u0003bg6<$BA\r\u001b\u0003\u0015A(-Z1o\u0015\tYB$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002;\u0005\u0019qN]4\n\u0005}1\"\u0001D\"mCN\u001ch+[:ji>\u0014\u0018AB8viB,H\u000f\u0005\u0003#S-\u001aU\"A\u0012\u000b\u0005\u0011*\u0013aB7vi\u0006\u0014G.\u001a\u0006\u0003M\u001d\n!bY8mY\u0016\u001cG/[8o\u0015\u0005A\u0013!B:dC2\f\u0017B\u0001\u0016$\u0005\ri\u0015\r\u001d\u0019\u0003Ye\u00022!\f\u001b8\u001d\tq#\u0007\u0005\u00020O5\t\u0001G\u0003\u00022%\u00051AH]8pizJ!aM\u0014\u0002\rA\u0013X\rZ3g\u0013\t)dGA\u0003DY\u0006\u001c8O\u0003\u00024OA\u0011\u0001(\u000f\u0007\u0001\t%Q\u0014!!A\u0001\u0002\u000b\u00051H\u0001\u0003`II:\u0014C\u0001\u001fA!\tid(D\u0001(\u0013\tytEA\u0004O_RD\u0017N\\4\u0011\u0005u\n\u0015B\u0001\"(\u0005\r\te.\u001f\t\u0004E\u00113\u0015BA#$\u0005\r\u0019V\r\u001e\t\u0003[\u001dK!\u0001\u0013\u001c\u0003\rM#(/\u001b8h\u00039\u0019\b/Z2jM&\u001cW*\u001a;i_\u0012\u00042!P&N\u0013\tauE\u0001\u0004PaRLwN\u001c\u0019\u0003\u001dN\u00032a\u0014)S\u001b\u0005a\u0011BA)\r\u0005AiU\r\u001e5pI&#WM\u001c;jM&,'\u000f\u0005\u00029'\u0012IAKAA\u0001\u0002\u0003\u0015\ta\u000f\u0002\u0005?\u0012\u0012\u0004(\u0001\bwSNLG/\u001a3NKRDw\u000eZ:\u0011\u0007\t\"u\u000b\r\u0002Y5B\u0019q\nU-\u0011\u0005aRF!C.\u0004\u0003\u0003\u0005\tQ!\u0001<\u0005\u0011yFEM\u001d\u0002\rqJg.\u001b;?)\u0011qv,Z6\u0011\u0005=\u0003\u0001\"\u0002\u0011\u0005\u0001\u0004\u0001\u0007\u0003\u0002\u0012*C\u000e\u0003$A\u00193\u0011\u00075\"4\r\u0005\u00029I\u0012I!hXA\u0001\u0002\u0003\u0015\ta\u000f\u0005\b\u0013\u0012\u0001\n\u00111\u0001g!\ri4j\u001a\u0019\u0003Q*\u00042a\u0014)j!\tA$\u000eB\u0005UK\u0006\u0005\t\u0011!B\u0001w!9Q\u000b\u0002I\u0001\u0002\u0004a\u0007c\u0001\u0012E[B\u0012a\u000e\u001d\t\u0004\u001fB{\u0007C\u0001\u001dq\t%Y6.!A\u0001\u0002\u000b\u00051(A\u0006wSNLG/T3uQ>$GcB:wwv|\u00181\u0001\t\u0003+QL!!\u001e\f\u0003\u001b5+G\u000f[8e-&\u001c\u0018\u000e^8s\u0011\u00159X\u00011\u0001y\u0003\u0019\t7mY3tgB\u0011Q(_\u0005\u0003u\u001e\u00121!\u00138u\u0011\u0015aX\u00011\u0001G\u0003\u0011q\u0017-\\3\t\u000by,\u0001\u0019\u0001$\u0002\t\u0011,7o\u0019\u0005\u0007\u0003\u0003)\u0001\u0019\u0001$\u0002\u0007MLw\rC\u0004\u0002\u0006\u0015\u0001\r!a\u0002\u0002\u0015\u0015D8-\u001a9uS>t7\u000f\u0005\u0003>\u0003\u00131\u0015bAA\u0006O\t)\u0011I\u001d:bs\u0006\tb)[3mI\u0006\u001b7-Z:t\r&tG-\u001a:\u0011\u0005=;1cA\u0004\u0002\u0014A\u0019Q(!\u0006\n\u0007\u0005]qE\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\u0003\u001f\t1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012TCAA\u0010U\u0011\t\t#a\u000b\u0011\tuZ\u00151\u0005\u0019\u0005\u0003K\tI\u0003\u0005\u0003P!\u0006\u001d\u0002c\u0001\u001d\u0002*\u0011IA+CA\u0001\u0002\u0003\u0015\taO\u0016\u0003\u0003[\u0001B!a\f\u0002:5\u0011\u0011\u0011\u0007\u0006\u0005\u0003g\t)$A\u0005v]\u000eDWmY6fI*\u0019\u0011qG\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002<\u0005E\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM*\"!!\u0011+\t\u0005\r\u00131\u0006\t\u0005E\u0011\u000b)\u0005\r\u0003\u0002H\u0005-\u0003\u0003B(Q\u0003\u0013\u00022\u0001OA&\t%Y&\"!A\u0001\u0002\u000b\u00051\b"
)
public class FieldAccessFinder extends ClassVisitor {
   public final Map com$twitter$chill$FieldAccessFinder$$output;
   private final Option specificMethod;
   public final Set com$twitter$chill$FieldAccessFinder$$visitedMethods;

   public static Set $lessinit$greater$default$3() {
      return FieldAccessFinder$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option $lessinit$greater$default$2() {
      return FieldAccessFinder$.MODULE$.$lessinit$greater$default$2();
   }

   public MethodVisitor visitMethod(final int access, final String name, final String desc, final String sig, final String[] exceptions) {
      MethodVisitor var9;
      label33: {
         if (this.specificMethod.isDefined()) {
            String var10000 = ((MethodIdentifier)this.specificMethod.get()).name();
            if (var10000 == null) {
               if (name != null) {
                  break label33;
               }
            } else if (!var10000.equals(name)) {
               break label33;
            }

            var10000 = ((MethodIdentifier)this.specificMethod.get()).desc();
            if (var10000 == null) {
               if (desc != null) {
                  break label33;
               }
            } else if (!var10000.equals(desc)) {
               break label33;
            }
         }

         var9 = new MethodVisitor() {
            // $FF: synthetic field
            private final FieldAccessFinder $outer;

            public void visitFieldInsn(final int op, final String owner, final String name, final String desc) {
               if (op == 180) {
                  String ownerName = owner.replace('/', '.');
                  this.$outer.com$twitter$chill$FieldAccessFinder$$output.keys().iterator().filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$visitFieldInsn$1(ownerName, x$6))).foreach((cl) -> (Set)((Growable)this.$outer.com$twitter$chill$FieldAccessFinder$$output.apply(cl)).$plus$eq(name));
               }

            }

            public void visitMethodInsn(final int op, final String owner, final String name, final String desc, final boolean itf) {
               String ownerName = owner.replace('/', '.');
               this.$outer.com$twitter$chill$FieldAccessFinder$$output.keys().iterator().filter((x$7) -> BoxesRunTime.boxToBoolean($anonfun$visitMethodInsn$1(ownerName, x$7))).foreach((cl) -> {
                  $anonfun$visitMethodInsn$2(this, op, owner, name, desc, cl);
                  return BoxedUnit.UNIT;
               });
            }

            // $FF: synthetic method
            public static final boolean $anonfun$visitFieldInsn$1(final String ownerName$1, final Class x$6) {
               boolean var3;
               label23: {
                  String var10000 = x$6.getName();
                  if (var10000 == null) {
                     if (ownerName$1 == null) {
                        break label23;
                     }
                  } else if (var10000.equals(ownerName$1)) {
                     break label23;
                  }

                  var3 = false;
                  return var3;
               }

               var3 = true;
               return var3;
            }

            // $FF: synthetic method
            public static final boolean $anonfun$visitMethodInsn$1(final String ownerName$2, final Class x$7) {
               boolean var3;
               label23: {
                  String var10000 = x$7.getName();
                  if (var10000 == null) {
                     if (ownerName$2 == null) {
                        break label23;
                     }
                  } else if (var10000.equals(ownerName$2)) {
                     break label23;
                  }

                  var3 = false;
                  return var3;
               }

               var3 = true;
               return var3;
            }

            // $FF: synthetic method
            public static final void $anonfun$visitMethodInsn$3(final Object $this, final MethodIdentifier m$1, final ClassReader cr) {
               cr.accept(new FieldAccessFinder($this.$outer.com$twitter$chill$FieldAccessFinder$$output, new Some(m$1), $this.$outer.com$twitter$chill$FieldAccessFinder$$visitedMethods), 0);
            }

            // $FF: synthetic method
            public static final void $anonfun$visitMethodInsn$2(final Object $this, final int op$1, final String owner$1, final String name$2, final String desc$1, final Class cl) {
               if (op$1 == 182 && owner$1.endsWith("$iwC") && !name$2.endsWith("$outer")) {
                  ((Growable)$this.$outer.com$twitter$chill$FieldAccessFinder$$output.apply(cl)).$plus$eq(name$2);
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               MethodIdentifier m = new MethodIdentifier(cl, name$2, desc$1);
               if (!$this.$outer.com$twitter$chill$FieldAccessFinder$$visitedMethods.contains(m)) {
                  $this.$outer.com$twitter$chill$FieldAccessFinder$$visitedMethods.$plus$eq(m);
                  AsmUtil$.MODULE$.classReader(cl).foreach((cr) -> {
                     $anonfun$visitMethodInsn$3($this, m, cr);
                     return BoxedUnit.UNIT;
                  });
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
         return var9;
      }

      var9 = null;
      return var9;
   }

   public FieldAccessFinder(final Map output, final Option specificMethod, final Set visitedMethods) {
      super(458752);
      this.com$twitter$chill$FieldAccessFinder$$output = output;
      this.specificMethod = specificMethod;
      this.com$twitter$chill$FieldAccessFinder$$visitedMethods = visitedMethods;
   }
}
