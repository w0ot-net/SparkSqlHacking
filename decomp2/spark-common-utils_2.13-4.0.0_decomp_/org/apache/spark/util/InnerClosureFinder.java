package org.apache.spark.util;

import org.apache.xbean.asm9.ClassVisitor;
import org.apache.xbean.asm9.MethodVisitor;
import org.apache.xbean.asm9.Type;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U4A\u0001C\u0005\u0005%!A1\u0004\u0001B\u0001B\u0003%A\u0004C\u0003?\u0001\u0011\u0005q\bC\u0004I\u0001\u0001\u0007I\u0011A%\t\u000f5\u0003\u0001\u0019!C\u0001\u001d\"1A\u000b\u0001Q!\n)CQ!\u0016\u0001\u0005BYCQ!\u001b\u0001\u0005B)\u0014!#\u00138oKJ\u001cEn\\:ve\u00164\u0015N\u001c3fe*\u0011!bC\u0001\u0005kRLGN\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h\u0007\u0001\u0019\"\u0001A\n\u0011\u0005QIR\"A\u000b\u000b\u0005Y9\u0012\u0001B1t[fR!\u0001G\u0007\u0002\u000ba\u0014W-\u00198\n\u0005i)\"\u0001D\"mCN\u001ch+[:ji>\u0014\u0018AB8viB,H\u000fE\u0002\u001eI\u0019j\u0011A\b\u0006\u0003?\u0001\nq!\\;uC\ndWM\u0003\u0002\"E\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\r\nQa]2bY\u0006L!!\n\u0010\u0003\u0007M+G\u000f\r\u0002(iA\u0019\u0001f\f\u001a\u000f\u0005%j\u0003C\u0001\u0016#\u001b\u0005Y#B\u0001\u0017\u0012\u0003\u0019a$o\\8u}%\u0011aFI\u0001\u0007!J,G-\u001a4\n\u0005A\n$!B\"mCN\u001c(B\u0001\u0018#!\t\u0019D\u0007\u0004\u0001\u0005\u0013U\n\u0011\u0011!A\u0001\u0006\u00031$\u0001B0%iE\n\"aN\u001e\u0011\u0005aJT\"\u0001\u0012\n\u0005i\u0012#a\u0002(pi\"Lgn\u001a\t\u0003qqJ!!\u0010\u0012\u0003\u0007\u0005s\u00170\u0001\u0004=S:LGO\u0010\u000b\u0003\u0001\n\u0003\"!\u0011\u0001\u000e\u0003%AQa\u0007\u0002A\u0002\r\u00032!\b\u0013Ea\t)u\tE\u0002)_\u0019\u0003\"aM$\u0005\u0013U\u0012\u0015\u0011!A\u0001\u0006\u00031\u0014AB7z\u001d\u0006lW-F\u0001K!\tA3*\u0003\u0002Mc\t11\u000b\u001e:j]\u001e\f!\"\\=OC6,w\fJ3r)\ty%\u000b\u0005\u00029!&\u0011\u0011K\t\u0002\u0005+:LG\u000fC\u0004T\t\u0005\u0005\t\u0019\u0001&\u0002\u0007a$\u0013'A\u0004ns:\u000bW.\u001a\u0011\u0002\u000bYL7/\u001b;\u0015\u000f=;FL\u00181cI\")\u0001L\u0002a\u00013\u00069a/\u001a:tS>t\u0007C\u0001\u001d[\u0013\tY&EA\u0002J]RDQ!\u0018\u0004A\u0002e\u000ba!Y2dKN\u001c\b\"B0\u0007\u0001\u0004Q\u0015\u0001\u00028b[\u0016DQ!\u0019\u0004A\u0002)\u000b1a]5h\u0011\u0015\u0019g\u00011\u0001K\u0003%\u0019X\u000f]3s\u001d\u0006lW\rC\u0003f\r\u0001\u0007a-\u0001\u0006j]R,'OZ1dKN\u00042\u0001O4K\u0013\tA'EA\u0003BeJ\f\u00170A\u0006wSNLG/T3uQ>$GCB6o_B\u00148\u000f\u0005\u0002\u0015Y&\u0011Q.\u0006\u0002\u000e\u001b\u0016$\bn\u001c3WSNLGo\u001c:\t\u000bu;\u0001\u0019A-\t\u000b};\u0001\u0019\u0001&\t\u000bE<\u0001\u0019\u0001&\u0002\t\u0011,7o\u0019\u0005\u0006C\u001e\u0001\rA\u0013\u0005\u0006i\u001e\u0001\rAZ\u0001\u000bKb\u001cW\r\u001d;j_:\u001c\b"
)
public class InnerClosureFinder extends ClassVisitor {
   public final Set org$apache$spark$util$InnerClosureFinder$$output;
   private String myName;

   public String myName() {
      return this.myName;
   }

   public void myName_$eq(final String x$1) {
      this.myName = x$1;
   }

   public void visit(final int version, final int access, final String name, final String sig, final String superName, final String[] interfaces) {
      this.myName_$eq(name);
   }

   public MethodVisitor visitMethod(final int access, final String name, final String desc, final String sig, final String[] exceptions) {
      return new MethodVisitor() {
         // $FF: synthetic field
         private final InnerClosureFinder $outer;

         public void visitMethodInsn(final int op, final String owner, final String name, final String desc, final boolean itf) {
            label34: {
               Type[] argTypes = Type.getArgumentTypes(desc);
               if (op == 183) {
                  String var7 = "<init>";
                  if (name == null) {
                     if (var7 != null) {
                        return;
                     }
                  } else if (!name.equals(var7)) {
                     return;
                  }

                  if (argTypes.length > 0 && argTypes[0].toString().startsWith("L")) {
                     String var10000 = argTypes[0].getInternalName();
                     String var8 = this.$outer.myName();
                     if (var10000 == null) {
                        if (var8 == null) {
                           break label34;
                        }
                     } else if (var10000.equals(var8)) {
                        break label34;
                     }
                  }
               }

               return;
            }

            this.$outer.org$apache$spark$util$InnerClosureFinder$$output.$plus$eq(SparkClassUtils$.MODULE$.classForName(owner.replace('/', '.'), false, true));
         }

         public {
            if (InnerClosureFinder.this == null) {
               throw null;
            } else {
               this.$outer = InnerClosureFinder.this;
            }
         }
      };
   }

   public InnerClosureFinder(final Set output) {
      super(589824);
      this.org$apache$spark$util$InnerClosureFinder$$output = output;
      this.myName = null;
   }
}
