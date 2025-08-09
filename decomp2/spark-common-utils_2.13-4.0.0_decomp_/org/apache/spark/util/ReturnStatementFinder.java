package org.apache.spark.util;

import org.apache.xbean.asm9.ClassVisitor;
import org.apache.xbean.asm9.MethodVisitor;
import scala.Option;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i3A\u0001C\u0005\u0005%!A1\u0004\u0001B\u0001B\u0003%A\u0004C\u0003.\u0001\u0011\u0005a\u0006C\u00033\u0001\u0011\u00053gB\u0004H\u0013\u0005\u0005\t\u0012\u0002%\u0007\u000f!I\u0011\u0011!E\u0005\u0013\")Q&\u0002C\u0001\u001b\"9a*BI\u0001\n\u0003y%!\u0006*fiV\u0014hn\u0015;bi\u0016lWM\u001c;GS:$WM\u001d\u0006\u0003\u0015-\tA!\u001e;jY*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001\u0019\u0002C\u0001\u000b\u001a\u001b\u0005)\"B\u0001\f\u0018\u0003\u0011\t7/\\\u001d\u000b\u0005ai\u0011!\u0002=cK\u0006t\u0017B\u0001\u000e\u0016\u00051\u0019E.Y:t-&\u001c\u0018\u000e^8s\u0003A!\u0018M]4fi6+G\u000f[8e\u001d\u0006lW\rE\u0002\u001eA\tj\u0011A\b\u0006\u0002?\u0005)1oY1mC&\u0011\u0011E\b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005\rRcB\u0001\u0013)!\t)c$D\u0001'\u0015\t9\u0013#\u0001\u0004=e>|GOP\u0005\u0003Sy\ta\u0001\u0015:fI\u00164\u0017BA\u0016-\u0005\u0019\u0019FO]5oO*\u0011\u0011FH\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005=\n\u0004C\u0001\u0019\u0001\u001b\u0005I\u0001bB\u000e\u0003!\u0003\u0005\r\u0001H\u0001\fm&\u001c\u0018\u000e^'fi\"|G\r\u0006\u00045oqr\u0004I\u0011\t\u0003)UJ!AN\u000b\u0003\u001b5+G\u000f[8e-&\u001c\u0018\u000e^8s\u0011\u0015A4\u00011\u0001:\u0003\u0019\t7mY3tgB\u0011QDO\u0005\u0003wy\u00111!\u00138u\u0011\u0015i4\u00011\u0001#\u0003\u0011q\u0017-\\3\t\u000b}\u001a\u0001\u0019\u0001\u0012\u0002\t\u0011,7o\u0019\u0005\u0006\u0003\u000e\u0001\rAI\u0001\u0004g&<\u0007\"B\"\u0004\u0001\u0004!\u0015AC3yG\u0016\u0004H/[8ogB\u0019Q$\u0012\u0012\n\u0005\u0019s\"!B!se\u0006L\u0018!\u0006*fiV\u0014hn\u0015;bi\u0016lWM\u001c;GS:$WM\u001d\t\u0003a\u0015\u0019\"!\u0002&\u0011\u0005uY\u0015B\u0001'\u001f\u0005\u0019\te.\u001f*fMR\t\u0001*A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%M\u000b\u0002!*\u0012A$U\u0016\u0002%B\u00111\u000bW\u0007\u0002)*\u0011QKV\u0001\nk:\u001c\u0007.Z2lK\u0012T!a\u0016\u0010\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002Z)\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3"
)
public class ReturnStatementFinder extends ClassVisitor {
   private final Option targetMethodName;

   public static Option $lessinit$greater$default$1() {
      return ReturnStatementFinder$.MODULE$.$lessinit$greater$default$1();
   }

   public MethodVisitor visitMethod(final int access, final String name, final String desc, final String sig, final String[] exceptions) {
      if (!name.contains("apply") && !name.contains("$anonfun$")) {
         return new MethodVisitor() {
         };
      } else {
         boolean var10000;
         label39: {
            if (!this.targetMethodName.isEmpty()) {
               label38: {
                  Object var7 = this.targetMethodName.get();
                  if (name == null) {
                     if (var7 == null) {
                        break label38;
                     }
                  } else if (name.equals(var7)) {
                     break label38;
                  }

                  String var8 = .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString((String)this.targetMethodName.get()), "$adapted");
                  if (name == null) {
                     if (var8 == null) {
                        break label38;
                     }
                  } else if (name.equals(var8)) {
                     break label38;
                  }

                  var10000 = false;
                  break label39;
               }
            }

            var10000 = true;
         }

         boolean isTargetMethod = var10000;
         return new MethodVisitor(isTargetMethod) {
            private final boolean isTargetMethod$1;

            public void visitTypeInsn(final int op, final String tp) {
               if (op == 187 && tp.contains("scala/runtime/NonLocalReturnControl") && this.isTargetMethod$1) {
                  throw new ReturnStatementInClosureException();
               }
            }

            public {
               this.isTargetMethod$1 = isTargetMethod$1;
            }
         };
      }
   }

   public ReturnStatementFinder(final Option targetMethodName) {
      super(589824);
      this.targetMethodName = targetMethodName;
   }
}
