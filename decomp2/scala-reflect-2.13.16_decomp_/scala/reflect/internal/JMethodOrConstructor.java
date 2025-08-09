package scala.reflect.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import scala.MatchError;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u000594Q\u0001D\u0007\u0002\"QAQ!\u0007\u0001\u0005\u0002iAQ!\b\u0001\u0005\u0002yAQA\t\u0001\u0005\u0002\rBQA\u0011\u0001\u0005\u0002\rCQ\u0001\u0013\u0001\u0005\u0002%CQA\u0015\u0001\u0005\u0002M;Q!W\u0007\t\u0002i3Q\u0001D\u0007\t\u0002mCQ!\u0007\u0005\u0005\u0002qCQ!\u0018\u0005\u0005\u0004yCQ\u0001\u001a\u0005\u0005\u0004\u0015\u0014ACS'fi\"|Gm\u0014:D_:\u001cHO];di>\u0014(B\u0001\b\u0010\u0003!Ig\u000e^3s]\u0006d'B\u0001\t\u0012\u0003\u001d\u0011XM\u001a7fGRT\u0011AE\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001Q\u0003\u0005\u0002\u0017/5\t\u0011#\u0003\u0002\u0019#\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#A\u000e\u0011\u0005q\u0001Q\"A\u0007\u0002\u0013%\u001ch+\u0019:Be\u001e\u001cX#A\u0010\u0011\u0005Y\u0001\u0013BA\u0011\u0012\u0005\u001d\u0011un\u001c7fC:\f!\u0002^=qKB\u000b'/Y7t+\u0005!\u0003GA\u0013+!\r1b\u0005K\u0005\u0003OE\u0011Q!\u0011:sCf\u0004\"!\u000b\u0016\r\u0001\u0011I1fAA\u0001\u0002\u0003\u0015\t\u0001\f\u0002\u0004?\u0012\n\u0014CA\u00171!\t1b&\u0003\u00020#\t9aj\u001c;iS:<\u0007GA\u0019<!\r\u0011\u0004HO\u0007\u0002g)\u0011\u0001\u0003\u000e\u0006\u0003kY\nA\u0001\\1oO*\tq'\u0001\u0003kCZ\f\u0017BA\u001d4\u00051!\u0016\u0010]3WCJL\u0017M\u00197f!\tI3\bB\u0005={\u0005\u0005\t\u0011!B\u0001}\t\u0019q\f\n\u001a\u0005\u0013-\u001a\u0011\u0011aA\u0001\u0006\u0003a\u0013CA\u0017@!\t1\u0002)\u0003\u0002B#\t\u0019\u0011I\\=\u0002\u0015A\f'/Y7UsB,7/F\u0001E!\r1b%\u0012\t\u0003e\u0019K!aR\u001a\u0003\tQK\b/Z\u0001\u0011a\u0006\u0014\u0018-\\!o]>$\u0018\r^5p]N,\u0012A\u0013\t\u0004-\u0019Z\u0005c\u0001\f'\u0019B\u0011Q\nU\u0007\u0002\u001d*\u0011q\nN\u0001\u000bC:tw\u000e^1uS>t\u0017BA)O\u0005)\teN\\8uCRLwN\\\u0001\u000be\u0016\u001cX\u000f\u001c;UsB,W#A#*\u0007\u0001)v+\u0003\u0002W\u001b\ta!jQ8ogR\u0014Xo\u0019;pe&\u0011\u0001,\u0004\u0002\b\u00156+G\u000f[8e\u0003QQU*\u001a;i_\u0012|%oQ8ogR\u0014Xo\u0019;peB\u0011A\u0004C\n\u0003\u0011U!\u0012AW\u0001\u0011Y&4G/T3uQ>$Gk\u001c&n_\u000e$\"aG0\t\u000b\u0001T\u0001\u0019A1\u0002\u00035\u0004\"A\r2\n\u0005\r\u001c$AB'fi\"|G-A\u000bmS\u001a$8i\u001c8tiJ,8\r^8s)>TUn\\2\u0015\u0005m1\u0007\"\u00021\f\u0001\u00049\u0007G\u00015m!\r\u0011\u0014n[\u0005\u0003UN\u00121bQ8ogR\u0014Xo\u0019;peB\u0011\u0011\u0006\u001c\u0003\n[\u001a\f\t\u0011!A\u0003\u0002y\u00121a\u0018\u00134\u0001"
)
public abstract class JMethodOrConstructor {
   public static JMethodOrConstructor liftConstructorToJmoc(final Constructor m) {
      JMethodOrConstructor$ var10000 = JMethodOrConstructor$.MODULE$;
      return new JConstructor(m);
   }

   public static JMethodOrConstructor liftMethodToJmoc(final Method m) {
      JMethodOrConstructor$ var10000 = JMethodOrConstructor$.MODULE$;
      return new JMethod(m);
   }

   public boolean isVarArgs() {
      if (this instanceof JMethod) {
         return ((JMethod)this).m().isVarArgs();
      } else if (this instanceof JConstructor) {
         return ((JConstructor)this).m().isVarArgs();
      } else {
         throw new MatchError(this);
      }
   }

   public TypeVariable[] typeParams() {
      if (this instanceof JMethod) {
         return ((JMethod)this).m().getTypeParameters();
      } else if (this instanceof JConstructor) {
         return ((JConstructor)this).m().getTypeParameters();
      } else {
         throw new MatchError(this);
      }
   }

   public Type[] paramTypes() {
      if (this instanceof JMethod) {
         return ((JMethod)this).m().getGenericParameterTypes();
      } else if (this instanceof JConstructor) {
         return ((JConstructor)this).m().getGenericParameterTypes();
      } else {
         throw new MatchError(this);
      }
   }

   public Annotation[][] paramAnnotations() {
      if (this instanceof JMethod) {
         return ((JMethod)this).m().getParameterAnnotations();
      } else if (this instanceof JConstructor) {
         return ((JConstructor)this).m().getParameterAnnotations();
      } else {
         throw new MatchError(this);
      }
   }

   public Type resultType() {
      if (this instanceof JMethod) {
         return ((JMethod)this).m().getGenericReturnType();
      } else if (this instanceof JConstructor) {
         return BoxedUnit.TYPE;
      } else {
         throw new MatchError(this);
      }
   }
}
