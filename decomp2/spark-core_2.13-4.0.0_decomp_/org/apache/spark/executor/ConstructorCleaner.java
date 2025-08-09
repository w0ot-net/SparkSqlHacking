package org.apache.spark.executor;

import org.apache.xbean.asm9.ClassVisitor;
import org.apache.xbean.asm9.MethodVisitor;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r3A!\u0002\u0004\u0001\u001f!A\u0001\u0004\u0001B\u0001B\u0003%\u0011\u0004\u0003\u0005'\u0001\t\u0005\t\u0015!\u0003\u0011\u0011\u00159\u0003\u0001\"\u0001)\u0011\u0015i\u0003\u0001\"\u0011/\u0005I\u0019uN\\:ueV\u001cGo\u001c:DY\u0016\fg.\u001a:\u000b\u0005\u001dA\u0011\u0001C3yK\u000e,Ho\u001c:\u000b\u0005%Q\u0011!B:qCJ\\'BA\u0006\r\u0003\u0019\t\u0007/Y2iK*\tQ\"A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001!A\u0011\u0011CF\u0007\u0002%)\u00111\u0003F\u0001\u0005CNl\u0017H\u0003\u0002\u0016\u0015\u0005)\u0001PY3b]&\u0011qC\u0005\u0002\r\u00072\f7o\u001d,jg&$xN]\u0001\nG2\f7o\u001d(b[\u0016\u0004\"AG\u0012\u000f\u0005m\t\u0003C\u0001\u000f \u001b\u0005i\"B\u0001\u0010\u000f\u0003\u0019a$o\\8u})\t\u0001%A\u0003tG\u0006d\u0017-\u0003\u0002#?\u00051\u0001K]3eK\u001aL!\u0001J\u0013\u0003\rM#(/\u001b8h\u0015\t\u0011s$\u0001\u0002dm\u00061A(\u001b8jiz\"2!K\u0016-!\tQ\u0003!D\u0001\u0007\u0011\u0015A2\u00011\u0001\u001a\u0011\u001513\u00011\u0001\u0011\u0003-1\u0018n]5u\u001b\u0016$\bn\u001c3\u0015\r=\u0012\u0004H\u000f\u001f?!\t\t\u0002'\u0003\u00022%\tiQ*\u001a;i_\u00124\u0016n]5u_JDQa\r\u0003A\u0002Q\na!Y2dKN\u001c\bCA\u001b7\u001b\u0005y\u0012BA\u001c \u0005\rIe\u000e\u001e\u0005\u0006s\u0011\u0001\r!G\u0001\u0005]\u0006lW\rC\u0003<\t\u0001\u0007\u0011$\u0001\u0003eKN\u001c\u0007\"B\u001f\u0005\u0001\u0004I\u0012aA:jO\")q\b\u0002a\u0001\u0001\u0006QQ\r_2faRLwN\\:\u0011\u0007U\n\u0015$\u0003\u0002C?\t)\u0011I\u001d:bs\u0002"
)
public class ConstructorCleaner extends ClassVisitor {
   private final ClassVisitor cv;

   public MethodVisitor visitMethod(final int access, final String name, final String desc, final String sig, final String[] exceptions) {
      MethodVisitor mv = this.cv.visitMethod(access, name, desc, sig, exceptions);
      String var7 = "<init>";
      if (name == null) {
         if (var7 != null) {
            return mv;
         }
      } else if (!name.equals(var7)) {
         return mv;
      }

      if ((access & 8) == 0) {
         mv.visitCode();
         mv.visitVarInsn(25, 0);
         mv.visitMethodInsn(183, "java/lang/Object", "<init>", "()V", false);
         mv.visitVarInsn(25, 0);
         mv.visitInsn(177);
         mv.visitMaxs(-1, -1);
         mv.visitEnd();
         return null;
      } else {
         return mv;
      }
   }

   public ConstructorCleaner(final String className, final ClassVisitor cv) {
      super(589824, cv);
      this.cv = cv;
   }
}
