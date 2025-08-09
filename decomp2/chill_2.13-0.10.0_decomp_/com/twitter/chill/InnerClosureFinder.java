package com.twitter.chill;

import org.apache.xbean.asm7.ClassVisitor;
import org.apache.xbean.asm7.MethodVisitor;
import org.apache.xbean.asm7.Type;
import scala.collection.ArrayOps.;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]4A\u0001C\u0005\u0001!!AQ\u0004\u0001B\u0001B\u0003%a\u0004C\u0003A\u0001\u0011\u0005\u0011\tC\u0005K\u0001\u0001\u0007\t\u0019!C\u0001\u0017\"Iq\n\u0001a\u0001\u0002\u0004%\t\u0001\u0015\u0005\n-\u0002\u0001\r\u0011!Q!\n1CQa\u0016\u0001\u0005BaCQa\u001b\u0001\u0005B1\u0014!#\u00138oKJ\u001cEn\\:ve\u00164\u0015N\u001c3fe*\u0011!bC\u0001\u0006G\"LG\u000e\u001c\u0006\u0003\u00195\tq\u0001^<jiR,'OC\u0001\u000f\u0003\r\u0019w.\\\u0002\u0001'\t\u0001\u0011\u0003\u0005\u0002\u001375\t1C\u0003\u0002\u0015+\u0005!\u0011m]78\u0015\t1r#A\u0003yE\u0016\fgN\u0003\u0002\u00193\u00051\u0011\r]1dQ\u0016T\u0011AG\u0001\u0004_J<\u0017B\u0001\u000f\u0014\u00051\u0019E.Y:t-&\u001c\u0018\u000e^8s\u0003\u0019yW\u000f\u001e9viB\u0019qD\n\u0015\u000e\u0003\u0001R!!\t\u0012\u0002\u000f5,H/\u00192mK*\u00111\u0005J\u0001\u000bG>dG.Z2uS>t'\"A\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u001d\u0002#aA*fiB\u0012\u0011F\u000e\t\u0004UE\"dBA\u00160!\taC%D\u0001.\u0015\tqs\"\u0001\u0004=e>|GOP\u0005\u0003a\u0011\na\u0001\u0015:fI\u00164\u0017B\u0001\u001a4\u0005\u0015\u0019E.Y:t\u0015\t\u0001D\u0005\u0005\u00026m1\u0001A!C\u001c\u0002\u0003\u0003\u0005\tQ!\u00019\u0005\u0011yFe\r\u0019\u0012\u0005ej\u0004C\u0001\u001e<\u001b\u0005!\u0013B\u0001\u001f%\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u000f \n\u0005}\"#aA!os\u00061A(\u001b8jiz\"\"A\u0011#\u0011\u0005\r\u0003Q\"A\u0005\t\u000bu\u0011\u0001\u0019A#\u0011\u0007}1c\t\r\u0002H\u0013B\u0019!&\r%\u0011\u0005UJE!C\u001cE\u0003\u0003\u0005\tQ!\u00019\u0003\u0019i\u0017PT1nKV\tA\n\u0005\u0002+\u001b&\u0011aj\r\u0002\u0007'R\u0014\u0018N\\4\u0002\u00155Lh*Y7f?\u0012*\u0017\u000f\u0006\u0002R)B\u0011!HU\u0005\u0003'\u0012\u0012A!\u00168ji\"9Q\u000bBA\u0001\u0002\u0004a\u0015a\u0001=%c\u00059Q.\u001f(b[\u0016\u0004\u0013!\u0002<jg&$HcB)Z=\u0002\u0014GM\u001a\u0005\u00065\u001a\u0001\raW\u0001\bm\u0016\u00148/[8o!\tQD,\u0003\u0002^I\t\u0019\u0011J\u001c;\t\u000b}3\u0001\u0019A.\u0002\r\u0005\u001c7-Z:t\u0011\u0015\tg\u00011\u0001M\u0003\u0011q\u0017-\\3\t\u000b\r4\u0001\u0019\u0001'\u0002\u0007MLw\rC\u0003f\r\u0001\u0007A*A\u0005tkB,'OT1nK\")qM\u0002a\u0001Q\u0006Q\u0011N\u001c;fe\u001a\f7-Z:\u0011\u0007iJG*\u0003\u0002kI\t)\u0011I\u001d:bs\u0006Ya/[:ji6+G\u000f[8e)\u0019i\u0007/\u001d:ukB\u0011!C\\\u0005\u0003_N\u0011Q\"T3uQ>$g+[:ji>\u0014\b\"B0\b\u0001\u0004Y\u0006\"B1\b\u0001\u0004a\u0005\"B:\b\u0001\u0004a\u0015\u0001\u00023fg\u000eDQaY\u0004A\u00021CQA^\u0004A\u0002!\f!\"\u001a=dKB$\u0018n\u001c8t\u0001"
)
public class InnerClosureFinder extends ClassVisitor {
   public final Set com$twitter$chill$InnerClosureFinder$$output;
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

               if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])argTypes)) && argTypes[0].toString().startsWith("L")) {
                  String var10000 = argTypes[0].getInternalName();
                  String var8 = this.$outer.myName();
                  if (var10000 == null) {
                     if (var8 != null) {
                        return;
                     }
                  } else if (!var10000.equals(var8)) {
                     return;
                  }

                  this.$outer.com$twitter$chill$InnerClosureFinder$$output.$plus$eq(Class.forName(owner.replace('/', '.'), false, Thread.currentThread().getContextClassLoader()));
               }
            }

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
      super(458752);
      this.com$twitter$chill$InnerClosureFinder$$output = output;
   }
}
