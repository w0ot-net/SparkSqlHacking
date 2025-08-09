package scala.reflect.runtime;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.reflect.io.AbstractFile;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=x!\u0002\u0010 \u0011\u00031c!\u0002\u0015 \u0011\u0003I\u0003\"\u0002\u0018\u0002\t\u0003y\u0003\"\u0002\u0019\u0002\t\u0003\t\u0004\"\u0002\"\u0002\t\u0003\u0019\u0005\"B+\u0002\t\u00031\u0006\"\u00027\u0002\t\u0003i\u0007\"\u00027\u0002\t\u0003\t\b\"B>\u0002\t\u0003axaBA\u0001\u0003!\u0005\u00111\u0001\u0004\b\u0003\u000f\t\u0001\u0012AA\u0005\u0011\u0019q#\u0002\"\u0001\u0002\f!9\u0011Q\u0002\u0006\u0005\u0002\u0005=aABA\u0014\u0003\u0001\tI\u0003\u0003\u0006\u0002.5\u0011\t\u0011)A\u0005\u0003_AaAL\u0007\u0005\u0002\u0005\r\u0003bBA\u0007\u001b\u0011\u0005\u00111K\u0004\b\u0003O\n\u0001\u0012AA5\r\u001d\tY'\u0001E\u0001\u0003[BaA\f\n\u0005\u0002\u0005mtaBA?\u0003!\u0005\u0011q\u0010\u0004\b\u0003\u0003\u000b\u0001\u0012AAB\u0011\u0019qS\u0003\"\u0001\u0002(\u001e9\u0011\u0011V\u0001\t\u0002\u0005-faBAW\u0003!\u0005\u0011q\u0016\u0005\u0007]a!\t!a0\b\u000f\u0005\u0005\u0017\u0001#\u0001\u0002D\u001a9\u0011QY\u0001\t\u0002\u0005\u001d\u0007B\u0002\u0018\u001c\t\u0003\t\t\u000eC\u0004\u0002T\u0006!\t!!6\u0002\u001fI+g\r\\3di&|g.\u0016;jYNT!\u0001I\u0011\u0002\u000fI,h\u000e^5nK*\u0011!eI\u0001\be\u00164G.Z2u\u0015\u0005!\u0013!B:dC2\f7\u0001\u0001\t\u0003O\u0005i\u0011a\b\u0002\u0010%\u00164G.Z2uS>tW\u000b^5mgN\u0011\u0011A\u000b\t\u0003W1j\u0011aI\u0005\u0003[\r\u0012a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001'\u0003=)hn\u001e:baRC'o\\<bE2,GC\u0001\u001a:!\t\u0019dG\u0004\u0002,i%\u0011QgI\u0001\ba\u0006\u001c7.Y4f\u0013\t9\u0004HA\u0005UQJ|w/\u00192mK*\u0011Qg\t\u0005\u0006u\r\u0001\rAM\u0001\u0002q\"\u00121\u0001\u0010\t\u0003{\u0001k\u0011A\u0010\u0006\u0003\u007f\r\n!\"\u00198o_R\fG/[8o\u0013\t\teHA\u0004uC&d'/Z2\u0002\u001bUtwO]1q\u0011\u0006tG\r\\3s+\t!%\n\u0006\u0002F'B!1F\u0012\u001aI\u0013\t95EA\bQCJ$\u0018.\u00197Gk:\u001cG/[8o!\tI%\n\u0004\u0001\u0005\u000b-#!\u0019\u0001'\u0003\u0003Q\u000b\"!\u0014)\u0011\u0005-r\u0015BA($\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aK)\n\u0005I\u001b#aA!os\")A\u000b\u0002a\u0001\u000b\u0006\u0011\u0001OZ\u0001\u0005g\"|w\u000f\u0006\u0002XEB\u0011\u0001l\u0018\b\u00033v\u0003\"AW\u0012\u000e\u0003mS!\u0001X\u0013\u0002\rq\u0012xn\u001c;?\u0013\tq6%\u0001\u0004Qe\u0016$WMZ\u0005\u0003A\u0006\u0014aa\u0015;sS:<'B\u00010$\u0011\u0015\u0019W\u00011\u0001e\u0003\t\u0019G\u000e\u0005\u0002fU6\taM\u0003\u0002hQ\u0006!A.\u00198h\u0015\u0005I\u0017\u0001\u00026bm\u0006L!a\u001b4\u0003\u0017\rc\u0017m]:M_\u0006$WM]\u0001\u0018gR\fG/[2TS:<G.\u001a;p]&s7\u000f^1oG\u0016$2A\u000b8p\u0011\u0015\u0019g\u00011\u0001e\u0011\u0015\u0001h\u00011\u0001X\u0003%\u0019G.Y:t\u001d\u0006lW\r\u0006\u0002+e\")1o\u0002a\u0001i\u0006)1\r\\1{uB\u0012Q/\u001f\t\u00041ZD\u0018BA<b\u0005\u0015\u0019E.Y:t!\tI\u0015\u0010B\u0005{e\u0006\u0005\t\u0011!B\u0001\u0019\n\u0019q\f\n\u001a\u0002-%tg.\u001a:TS:<G.\u001a;p]&s7\u000f^1oG\u0016$2AK?\u0000\u0011\u0015q\b\u00021\u0001+\u0003\u0015yW\u000f^3s\u0011\u0015\u0001\b\u00021\u0001X\u0003A\u0001&/[7ji&4Xm\u0014:BeJ\f\u0017\u0010E\u0002\u0002\u0006)i\u0011!\u0001\u0002\u0011!JLW.\u001b;jm\u0016|%/\u0011:sCf\u001c\"A\u0003\u0016\u0015\u0005\u0005\r\u0011aB;oCB\u0004H.\u001f\u000b\u0005\u0003#\t9\u0002E\u0002,\u0003'I1!!\u0006$\u0005\u001d\u0011un\u001c7fC:Dq!!\u0007\r\u0001\u0004\tY\"\u0001\u0004kG2\f'P\u001f\u0019\u0005\u0003;\t\u0019\u0003E\u0003f\u0003?\t\t#\u0003\u0002xMB\u0019\u0011*a\t\u0005\u0017\u0005\u0015\u0012qCA\u0001\u0002\u0003\u0015\t\u0001\u0014\u0002\u0004?\u0012\"$AC#oG2|7/\u001a3J]V!\u00111FA!'\ti!&A\u0005f]\u000edwn];sKB91&!\r\u00026\u0005}\u0012bAA\u001aG\tIa)\u001e8di&|g.\r\u0019\u0005\u0003o\tY\u0004E\u0003f\u0003?\tI\u0004E\u0002J\u0003w!!\"!\u0010\u000f\u0003\u0003\u0005\tQ!\u0001M\u0005\ryF%\u000e\t\u0004\u0013\u0006\u0005C!B&\u000e\u0005\u0004aE\u0003BA#\u0003\u000f\u0002R!!\u0002\u000e\u0003\u007fAq!!\f\u0010\u0001\u0004\tI\u0005E\u0004,\u0003c\tY%a\u00101\t\u00055\u0013\u0011\u000b\t\u0006K\u0006}\u0011q\n\t\u0004\u0013\u0006ECaCA\u001f\u0003\u000f\n\t\u0011!A\u0003\u00021#B!!\u0016\u0002\\A)1&a\u0016\u0002@%\u0019\u0011\u0011L\u0012\u0003\r=\u0003H/[8o\u0011\u001d\tI\u0002\u0005a\u0001\u0003;\u0002D!a\u0018\u0002dA)Q-a\b\u0002bA\u0019\u0011*a\u0019\u0005\u0017\u0005\u0015\u00141LA\u0001\u0002\u0003\u0015\t\u0001\u0014\u0002\u0004?\u00122\u0014\u0001E#oG2|7/\u001a3J]6+G\u000f[8e!\r\t)A\u0005\u0002\u0011\u000b:\u001cGn\\:fI&sW*\u001a;i_\u0012\u001c2AEA8!\u0015\t)!DA9!\u0011\t\u0019(a\u001e\u000e\u0005\u0005U$B\u0001\u0012g\u0013\u0011\tI(!\u001e\u0003\r5+G\u000f[8e)\t\tI'A\u000bF]\u000edwn]3e\u0013:\u001cuN\\:ueV\u001cGo\u001c:\u0011\u0007\u0005\u0015QCA\u000bF]\u000edwn]3e\u0013:\u001cuN\\:ueV\u001cGo\u001c:\u0014\u0007U\t)\tE\u0003\u0002\u00065\t9\t\r\u0003\u0002\n\u0006E\u0005CBA:\u0003\u0017\u000by)\u0003\u0003\u0002\u000e\u0006U$aC\"p]N$(/^2u_J\u00042!SAI\t1\t\u0019*!&\u0002\u0002\u0003\u0005)\u0011AAP\u0005\ty\u0004\u0007\u0003\u0006\u0002\u0018\u0006e\u0015\u0011!A\u0001\u0003;\u000b\u0001\u0002J1o_:4WO\u001c\u0005\u0007\u00037\u000b\u0001!!(\u0002/qbwnY1mAI+g\r\\3di&|g.\u0016;jYNt4\u0002A\t\u0004\u001b\u0006\u0005\u0006cA3\u0002$&\u0019\u0011Q\u00154\u0003\r=\u0013'.Z2u)\t\ty(A\bF]\u000edwn]3e\u0013:\u001cE.Y:t!\r\t)\u0001\u0007\u0002\u0010\u000b:\u001cGn\\:fI&s7\t\\1tgN\u0019\u0001$!-\u0011\u000b\u0005\u0015Q\"a-1\t\u0005U\u0016\u0011\u0018\t\u0006K\u0006}\u0011q\u0017\t\u0004\u0013\u0006eF\u0001DAJ\u0003w\u000b\t\u0011!A\u0003\u0002\u0005}\u0005BCAL\u0003{\u000b\t\u0011!\u0001\u0002\u001e\"1\u00111T\u0001\u0001\u0003;#\"!a+\u0002#\u0015s7\r\\8tK\u0012Le\u000eU1dW\u0006<W\rE\u0002\u0002\u0006m\u0011\u0011#\u00128dY>\u001cX\rZ%o!\u0006\u001c7.Y4f'\rY\u0012\u0011\u001a\t\u0006\u0003\u000bi\u00111\u001a\t\u0004K\u00065\u0017bAAhM\n9\u0001+Y2lC\u001e,GCAAb\u00039\t7o]8dS\u0006$X\r\u001a$jY\u0016$B!a6\u0002dB!\u0011\u0011\\Ap\u001b\t\tYNC\u0002\u0002^\u0006\n!![8\n\t\u0005\u0005\u00181\u001c\u0002\r\u0003\n\u001cHO]1di\u001aKG.\u001a\u0005\u0007gv\u0001\r!!:1\t\u0005\u001d\u00181\u001e\t\u00051Z\fI\u000fE\u0002J\u0003W$1\"!<\u0002d\u0006\u0005\t\u0011!B\u0001\u0019\n\u0019q\fJ\u001c"
)
public final class ReflectionUtils {
   public static AbstractFile associatedFile(final Class clazz) {
      return ReflectionUtils$.MODULE$.associatedFile(clazz);
   }

   public static Object innerSingletonInstance(final Object outer, final String className) {
      return ReflectionUtils$.MODULE$.innerSingletonInstance(outer, className);
   }

   public static Object staticSingletonInstance(final Class clazz) {
      return ReflectionUtils$.MODULE$.staticSingletonInstance(clazz);
   }

   public static Object staticSingletonInstance(final ClassLoader cl, final String className) {
      return ReflectionUtils$.MODULE$.staticSingletonInstance(cl, className);
   }

   public static String show(final ClassLoader cl) {
      return ReflectionUtils$.MODULE$.show(cl);
   }

   public static PartialFunction unwrapHandler(final PartialFunction pf) {
      return ReflectionUtils$.MODULE$.unwrapHandler(pf);
   }

   public static Throwable unwrapThrowable(final Throwable x) {
      return ReflectionUtils$.MODULE$.unwrapThrowable(x);
   }

   public static class PrimitiveOrArray$ {
      public static final PrimitiveOrArray$ MODULE$ = new PrimitiveOrArray$();

      public boolean unapply(final Class jclazz) {
         return jclazz.isPrimitive() || jclazz.isArray();
      }
   }

   public static class EnclosedIn {
      private final Function1 enclosure;

      public Option unapply(final Class jclazz) {
         return .MODULE$.apply(this.enclosure.apply(jclazz));
      }

      public EnclosedIn(final Function1 enclosure) {
         this.enclosure = enclosure;
      }
   }

   public static class EnclosedInMethod$ extends EnclosedIn {
      public static final EnclosedInMethod$ MODULE$ = new EnclosedInMethod$();

      public EnclosedInMethod$() {
         super(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Method apply(final Class x$2) {
               return x$2.getEnclosingMethod();
            }
         });
      }
   }

   public static class EnclosedInConstructor$ extends EnclosedIn {
      public static final EnclosedInConstructor$ MODULE$ = new EnclosedInConstructor$();

      public EnclosedInConstructor$() {
         super(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Constructor apply(final Class x$3) {
               return x$3.getEnclosingConstructor();
            }
         });
      }
   }

   public static class EnclosedInClass$ extends EnclosedIn {
      public static final EnclosedInClass$ MODULE$ = new EnclosedInClass$();

      public EnclosedInClass$() {
         super(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Class apply(final Class x$4) {
               return x$4.getEnclosingClass();
            }
         });
      }
   }

   public static class EnclosedInPackage$ extends EnclosedIn {
      public static final EnclosedInPackage$ MODULE$ = new EnclosedInPackage$();

      public EnclosedInPackage$() {
         super(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Package apply(final Class x$5) {
               return x$5.getPackage();
            }
         });
      }
   }
}
