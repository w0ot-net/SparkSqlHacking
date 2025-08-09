package scala.reflect.internal.util;

import java.io.Serializable;
import scala.Function0;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dg\u0001\u0002\u0011\"\u0001*B\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u0011\u0002\u0011\t\u0012)A\u0005\u0001\"A\u0011\n\u0001BK\u0002\u0013\u0005!\n\u0003\u0005O\u0001\tE\t\u0015!\u0003L\u0011!y\u0005A!f\u0001\n\u0003\u0001\u0006\u0002C-\u0001\u0005#\u0005\u000b\u0011B)\t\u000bi\u0003A\u0011A.\t\u000f\u0001\u0004\u0011\u0011!C\u0001C\"9Q\rAI\u0001\n\u00031\u0007bB9\u0001#\u0003%\tA\u001d\u0005\bi\u0002\t\n\u0011\"\u0001v\u0011\u001d9\b!!A\u0005BaD\u0011\"!\u0001\u0001\u0003\u0003%\t!a\u0001\t\u0013\u0005-\u0001!!A\u0005\u0002\u00055\u0001\"CA\r\u0001\u0005\u0005I\u0011IA\u000e\u0011%\tI\u0003AA\u0001\n\u0003\tY\u0003C\u0005\u00026\u0001\t\t\u0011\"\u0011\u00028!I\u00111\b\u0001\u0002\u0002\u0013\u0005\u0013Q\b\u0005\n\u0003\u007f\u0001\u0011\u0011!C!\u0003\u0003B\u0011\"a\u0011\u0001\u0003\u0003%\t%!\u0012\b\u000f\u0005%\u0013\u0005#\u0001\u0002L\u00191\u0001%\tE\u0001\u0003\u001bBaA\u0017\f\u0005\u0002\u0005e\u0003bBA.-\u0011\u0005\u0011Q\f\u0005\n\u0003\u007f2\u0012\u0013!C\u0001\u0003\u0003C!\"!\"\u0017\u0011\u000b\u0007I\u0011BAD\u0011\u001d\t9J\u0006C\u0001\u00033Cq!a(\u0017\t\u0003\t\t\u000bC\u0005\u0002\\Y\t\t\u0011\"!\u0002&\"I\u0011Q\u0016\f\u0002\u0002\u0013\u0005\u0015q\u0016\u0005\n\u0003{3\u0012\u0011!C\u0005\u0003\u007f\u0013!bQ8eK\u0006\u001bG/[8o\u0015\t\u00113%\u0001\u0003vi&d'B\u0001\u0013&\u0003!Ig\u000e^3s]\u0006d'B\u0001\u0014(\u0003\u001d\u0011XM\u001a7fGRT\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0002\u0001'\u0011\u00011f\f\u001a\u0011\u00051jS\"A\u0014\n\u00059:#AB!osJ+g\r\u0005\u0002-a%\u0011\u0011g\n\u0002\b!J|G-^2u!\t\u00194H\u0004\u00025s9\u0011Q\u0007O\u0007\u0002m)\u0011q'K\u0001\u0007yI|w\u000e\u001e \n\u0003!J!AO\u0014\u0002\u000fA\f7m[1hK&\u0011A(\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003u\u001d\nQ\u0001^5uY\u0016,\u0012\u0001\u0011\t\u0003\u0003\u0016s!AQ\"\u0011\u0005U:\u0013B\u0001#(\u0003\u0019\u0001&/\u001a3fM&\u0011ai\u0012\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0011;\u0013A\u0002;ji2,\u0007%A\u0006eKN\u001c'/\u001b9uS>tW#A&\u0011\u00071b\u0005)\u0003\u0002NO\t1q\n\u001d;j_:\fA\u0002Z3tGJL\u0007\u000f^5p]\u0002\nQ!\u001a3jiN,\u0012!\u0015\t\u0004%N+fB\u0001\u0017:\u0013\t!VH\u0001\u0003MSN$\bC\u0001,X\u001b\u0005\t\u0013B\u0001-\"\u0005!!V\r\u001f;FI&$\u0018AB3eSR\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u00059vsv\f\u0005\u0002W\u0001!)ah\u0002a\u0001\u0001\")\u0011j\u0002a\u0001\u0017\")qj\u0002a\u0001#\u0006!1m\u001c9z)\u0011a&m\u00193\t\u000fyB\u0001\u0013!a\u0001\u0001\"9\u0011\n\u0003I\u0001\u0002\u0004Y\u0005bB(\t!\u0003\u0005\r!U\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u00059'F\u0001!iW\u0005I\u0007C\u00016p\u001b\u0005Y'B\u00017n\u0003%)hn\u00195fG.,GM\u0003\u0002oO\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005A\\'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A:+\u0005-C\u0017AD2paf$C-\u001a4bk2$HeM\u000b\u0002m*\u0012\u0011\u000b[\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003e\u0004\"A_@\u000e\u0003mT!\u0001`?\u0002\t1\fgn\u001a\u0006\u0002}\u0006!!.\u0019<b\u0013\t150\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\u0006A\u0019A&a\u0002\n\u0007\u0005%qEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u0010\u0005U\u0001c\u0001\u0017\u0002\u0012%\u0019\u00111C\u0014\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002\u00189\t\t\u00111\u0001\u0002\u0006\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\b\u0011\r\u0005}\u0011QEA\b\u001b\t\t\tCC\u0002\u0002$\u001d\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t9#!\t\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003[\t\u0019\u0004E\u0002-\u0003_I1!!\r(\u0005\u001d\u0011un\u001c7fC:D\u0011\"a\u0006\u0011\u0003\u0003\u0005\r!a\u0004\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004s\u0006e\u0002\"CA\f#\u0005\u0005\t\u0019AA\u0003\u0003!A\u0017m\u001d5D_\u0012,GCAA\u0003\u0003!!xn\u0015;sS:<G#A=\u0002\r\u0015\fX/\u00197t)\u0011\ti#a\u0012\t\u0013\u0005]A#!AA\u0002\u0005=\u0011AC\"pI\u0016\f5\r^5p]B\u0011aKF\n\u0005--\ny\u0005\u0005\u0003\u0002R\u0005]SBAA*\u0015\r\t)&`\u0001\u0003S>L1\u0001PA*)\t\tY%A\u0003baBd\u0017\u0010\u0006\u0007\u0002`\u0005\u0005\u00141MA7\u0003c\n)\bE\u0002S'rCQA\u0010\rA\u0002\u0001Cq!!\u001a\u0019\u0001\u0004\t9'A\u0002q_N\u00042AVA5\u0013\r\tY'\t\u0002\t!>\u001c\u0018\u000e^5p]\"1\u0011q\u000e\rA\u0002\u0001\u000bqA\\3x)\u0016DH\u000f\u0003\u0004\u0002ta\u0001\r\u0001Q\u0001\u0005I\u0016\u001c8\rC\u0005\u0002xa\u0001J\u00111\u0001\u0002z\u0005)1\r[3dWB)A&a\u001f\u0002.%\u0019\u0011QP\u0014\u0003\u0011q\u0012\u0017P\\1nKz\nq\"\u00199qYf$C-\u001a4bk2$H%N\u000b\u0003\u0003\u0007S3!!\fi\u0003\u0019\u0001\u0018M]3ogV\u0011\u0011\u0011\u0012\t\u0005\u0003\u0017\u000b\u0019*\u0004\u0002\u0002\u000e*!\u0011qRAI\u0003!i\u0017\r^2iS:<'B\u0001\u0012(\u0013\u0011\t)*!$\u0003\u000bI+w-\u001a=\u0002#5\f\u0017PY3Xe\u0006\u0004\u0018J\u001c)be\u0016t7\u000fF\u0002z\u00037Ca!!(\u001c\u0001\u0004\u0001\u0015!A:\u0002\u0019]\u0014\u0018\r]%o!\u0006\u0014XM\\:\u0015\u0007e\f\u0019\u000b\u0003\u0004\u0002\u001er\u0001\r\u0001\u0011\u000b\b9\u0006\u001d\u0016\u0011VAV\u0011\u0015qT\u00041\u0001A\u0011\u0015IU\u00041\u0001L\u0011\u0015yU\u00041\u0001R\u0003\u001d)h.\u00199qYf$B!!-\u0002:B!A\u0006TAZ!\u0019a\u0013Q\u0017!L#&\u0019\u0011qW\u0014\u0003\rQ+\b\u000f\\34\u0011!\tYLHA\u0001\u0002\u0004a\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\u0019\t\u0004u\u0006\r\u0017bAAcw\n1qJ\u00196fGR\u0004"
)
public class CodeAction implements Product, Serializable {
   private final String title;
   private final Option description;
   private final List edits;

   public static Option unapply(final CodeAction x$0) {
      return CodeAction$.MODULE$.unapply(x$0);
   }

   public static CodeAction apply(final String title, final Option description, final List edits) {
      CodeAction$ var10000 = CodeAction$.MODULE$;
      return new CodeAction(title, description, edits);
   }

   public static String wrapInParens(final String s) {
      return CodeAction$.MODULE$.wrapInParens(s);
   }

   public static String maybeWrapInParens(final String s) {
      return CodeAction$.MODULE$.maybeWrapInParens(s);
   }

   public static boolean apply$default$5() {
      CodeAction$ var10000 = CodeAction$.MODULE$;
      return true;
   }

   public static List apply(final String title, final Position pos, final String newText, final String desc, final Function0 check) {
      CodeAction$ var10000 = CodeAction$.MODULE$;
      return (List)(check.apply$mcZ$sp() ? new .colon.colon(new CodeAction(title, new Some(desc), new .colon.colon(new TextEdit(pos, newText), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$) : scala.collection.immutable.Nil..MODULE$);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String title() {
      return this.title;
   }

   public Option description() {
      return this.description;
   }

   public List edits() {
      return this.edits;
   }

   public CodeAction copy(final String title, final Option description, final List edits) {
      return new CodeAction(title, description, edits);
   }

   public String copy$default$1() {
      return this.title();
   }

   public Option copy$default$2() {
      return this.description();
   }

   public List copy$default$3() {
      return this.edits();
   }

   public String productPrefix() {
      return "CodeAction";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.title();
         case 1:
            return this.description();
         case 2:
            return this.edits();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return new ScalaRunTime..anon.1(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof CodeAction;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "title";
         case 1:
            return "description";
         case 2:
            return "edits";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return scala.util.hashing.MurmurHash3..MODULE$.productHash(this, -889275714, false);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof CodeAction) {
            CodeAction var2 = (CodeAction)x$1;
            String var10000 = this.title();
            String var3 = var2.title();
            if (var10000 == null) {
               if (var3 != null) {
                  return false;
               }
            } else if (!var10000.equals(var3)) {
               return false;
            }

            Option var6 = this.description();
            Option var4 = var2.description();
            if (var6 == null) {
               if (var4 != null) {
                  return false;
               }
            } else if (!var6.equals(var4)) {
               return false;
            }

            List var7 = this.edits();
            List var5 = var2.edits();
            if (var7 == null) {
               if (var5 != null) {
                  return false;
               }
            } else if (!var7.equals(var5)) {
               return false;
            }

            if (var2.canEqual(this)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public CodeAction(final String title, final Option description, final List edits) {
      this.title = title;
      this.description = description;
      this.edits = edits;
   }
}
