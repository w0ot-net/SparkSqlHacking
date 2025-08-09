package spire.math;

import algebra.lattice.DeMorgan;
import cats.kernel.Eq;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.reflect.ScalaSignature;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\r]b\u0001B&M\u0005EC\u0001\u0002\u0017\u0001\u0003\u0006\u0004%\t!\u0017\u0005\t;\u0002\u0011\t\u0011)A\u00055\")a\f\u0001C\u0001?\")1\r\u0001C\u0001I\")\u0001\u000e\u0001C\u0001I\")\u0011\u000e\u0001C\u0001I\")!\u000e\u0001C\u0001I\")1\u000e\u0001C\u0001I\")A\u000e\u0001C\u0001I\")Q\u000e\u0001C\u0001]\"1\u0011Q\u0002\u0001\u0005\u0002\u0011Da!a\u0004\u0001\t\u0003!\u0007bBA\t\u0001\u0011\u0005\u00111\u0003\u0005\b\u00033\u0001A\u0011AA\u000e\u0011\u001d\t\t\u0003\u0001C\u0001\u0003GAq!a\u000b\u0001\t\u0003\ni\u0003C\u0004\u0002F\u0001!\t!a\u0012\t\u000f\u0005=\u0003\u0001\"\u0001\u0002R!9\u0011Q\u000b\u0001\u0005\u0002\u0005]\u0003bBA-\u0001\u0011\u0005\u00111\f\u0005\b\u0003?\u0002A\u0011AA1\u0011\u001d\t)\u0007\u0001C\u0001\u0003OBq!a\u001b\u0001\t\u0003\ti\u0007C\u0004\u0002r\u0001!\t!a\u001d\t\u000f\u0005]\u0004\u0001\"\u0001\u0002z!9\u0011Q\u0010\u0001\u0005\u0002\u0005}\u0004\"CAB\u0001\u0005\u0005I\u0011IAC\u0011%\t9\tAA\u0001\n\u0003\nIiB\u0004\u0002\u00102C\t!!%\u0007\r-c\u0005\u0012AAJ\u0011\u0019qf\u0004\"\u0001\u0002\u001c\"I\u0011Q\u0014\u0010C\u0002\u0013\u0015\u0011q\u000b\u0005\b\u0003?s\u0002\u0015!\u0004a\u0011%\t\tK\bb\u0001\n\u000b\t9\u0006C\u0004\u0002$z\u0001\u000bQ\u00021\t\u0013\u0005\u0015fD1A\u0005\u0006\u0005]\u0003bBAT=\u0001\u0006i\u0001\u0019\u0005\b\u0003SsBQAAV\u0011\u001d\tIK\bC\u0003\u0003_Cq!!+\u001f\t\u000b\t)\fC\u0004\u0002Hz!)!!3\t\u000f\u0005ug\u0004\"\u0002\u0002`\"9\u00111\u001f\u0010\u0005\u0006\u0005U\bb\u0002B\u0004=\u0011\u0015!\u0011\u0002\u0005\b\u00053qBQ\u0001B\u000e\u0011%\u0011\tC\bb\u0001\n\u0007\u0011\u0019\u0003\u0003\u0005\u0003Dy\u0001\u000b\u0011\u0002B\u0013\u0011%\u0011)E\bb\u0001\n\u0007\u00119\u0005\u0003\u0005\u0003\\y\u0001\u000b\u0011\u0002B%\u0011\u001d\u0011iF\bC\u0003\u0005?BqA!\u001a\u001f\t\u000b\u00119\u0007C\u0004\u0003ly!)A!\u001c\t\u000f\tEd\u0004\"\u0002\u0003t!9!q\u000f\u0010\u0005\u0006\te\u0004b\u0002B?=\u0011\u0015!q\u0010\u0005\b\u0005\u0007sBQ\u0001BC\u0011\u001d\u0011YJ\bC\u0003\u0005;CqA!)\u001f\t\u000b\u0011\u0019\u000bC\u0004\u0003(z!)A!+\t\u000f\tEf\u0004\"\u0002\u00034\"9!1\u0018\u0010\u0005\u0006\tu\u0006b\u0002Ba=\u0011\u0015!1\u0019\u0005\b\u0005\u000ftBQ\u0001Be\u0011\u001d\u0011\tN\bC\u0003\u0005'DqAa7\u001f\t\u000b\u0011i\u000eC\u0004\u0003bz!)Aa9\t\u000f\t-h\u0004\"\u0002\u0003n\"9!Q\u001f\u0010\u0005\u0006\t]\bb\u0002B\u0000=\u0011\u00151\u0011\u0001\u0005\b\u0007\u0013qBQAB\u0006\u0011\u001d\u0019\u0019B\bC\u0003\u0007+Aqa!\b\u001f\t\u000b\u0019y\u0002C\u0005\u0004(y\t\t\u0011\"\u0002\u0004*!I1Q\u0006\u0010\u0002\u0002\u0013\u00151q\u0006\u0002\b)JLG.Z1o\u0015\tie*\u0001\u0003nCRD'\"A(\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001A\u0015\t\u0003'Zk\u0011\u0001\u0016\u0006\u0002+\u0006)1oY1mC&\u0011q\u000b\u0016\u0002\u0007\u0003:Lh+\u00197\u0002\u000bY\fG.^3\u0016\u0003i\u0003\"aU.\n\u0005q#&aA%oi\u00061a/\u00197vK\u0002\na\u0001P5oSRtDC\u00011c!\t\t\u0007!D\u0001M\u0011\u0015A6\u00011\u0001[\u0003\u0019I7\u000f\u0016:vKV\tQ\r\u0005\u0002TM&\u0011q\r\u0016\u0002\b\u0005>|G.Z1o\u0003\u001dI7OR1mg\u0016\f\u0011\"[:V].twn\u001e8\u0002\u000f%\u001c8J\\8x]\u0006I\u0011n\u001d(piR\u0013X/Z\u0001\u000bSNtu\u000e\u001e$bYN,\u0017\u0001\u00024pY\u0012,\"a\\:\u0015\u0007A\f\u0019\u0001\u0006\u0002ryB\u0011!o\u001d\u0007\u0001\t\u0015!(B1\u0001v\u0005\u0005\t\u0015C\u0001<z!\t\u0019v/\u0003\u0002y)\n9aj\u001c;iS:<\u0007CA*{\u0013\tYHKA\u0002B]fDa! \u0006\u0005\u0002\u0004q\u0018aB;oW:|wO\u001c\t\u0004'~\f\u0018bAA\u0001)\nAAHY=oC6,g\bC\u0004\u0002\u0006)\u0001\r!a\u0002\u0002\u0003\u0019\u0004RaUA\u0005KFL1!a\u0003U\u0005%1UO\\2uS>t\u0017'\u0001\u0006bgN,X.\u001a+sk\u0016\f1\"Y:tk6,g)\u00197tK\u00061\u0011m]:v[\u0016$2!ZA\u000b\u0011\u0019\t9\"\u0004a\u0001K\u0006\t!-A\u0005u_\n{w\u000e\\3b]R\u0019Q-!\b\t\u0011\u0005]a\u0002\"a\u0001\u0003?\u00012aU@f\u0003!!xn\u00149uS>tWCAA\u0013!\u0011\u0019\u0016qE3\n\u0007\u0005%BK\u0001\u0004PaRLwN\\\u0001\ti>\u001cFO]5oOR\u0011\u0011q\u0006\t\u0005\u0003c\tyD\u0004\u0003\u00024\u0005m\u0002cAA\u001b)6\u0011\u0011q\u0007\u0006\u0004\u0003s\u0001\u0016A\u0002\u001fs_>$h(C\u0002\u0002>Q\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA!\u0003\u0007\u0012aa\u0015;sS:<'bAA\u001f)\u0006AA%Y7qI\u0005l\u0007\u000fF\u0002a\u0003\u0013B\u0001\"a\u0013\u0012\t\u0003\u0007\u0011QJ\u0001\u0004e\"\u001c\bcA*\u0000A\u0006AAEY1sI\t\f'\u000fF\u0002a\u0003'B\u0001\"a\u0013\u0013\t\u0003\u0007\u0011QJ\u0001\fk:\f'/_0%E\u0006tw-F\u0001a\u0003\u0011!\u0013-\u001c9\u0015\u0007\u0001\fi\u0006\u0003\u0004\u0002LQ\u0001\r\u0001Y\u0001\u0005I\t\f'\u000fF\u0002a\u0003GBa!a\u0013\u0016\u0001\u0004\u0001\u0017a\u0001\u0013vaR\u0019\u0001-!\u001b\t\r\u0005-c\u00031\u0001a\u0003\rIW\u000e\u001d\u000b\u0004A\u0006=\u0004BBA&/\u0001\u0007\u0001-\u0001\u0003oC:$Gc\u00011\u0002v!1\u00111\n\rA\u0002\u0001\f1A\\8s)\r\u0001\u00171\u0010\u0005\u0007\u0003\u0017J\u0002\u0019\u00011\u0002\t9DxN\u001d\u000b\u0004A\u0006\u0005\u0005BBA&5\u0001\u0007\u0001-\u0001\u0005iCND7i\u001c3f)\u0005Q\u0016AB3rk\u0006d7\u000fF\u0002f\u0003\u0017C\u0001\"!$\u001d\u0003\u0003\u0005\r!_\u0001\u0004q\u0012\n\u0014a\u0002+sS2,\u0017M\u001c\t\u0003Cz\u00192AHAK!\r\u0019\u0016qS\u0005\u0004\u00033#&AB!osJ+g\r\u0006\u0002\u0002\u0012\u0006!AK];f\u0003\u0015!&/^3!\u0003\u00151\u0015\r\\:f\u0003\u00191\u0015\r\\:fA\u00059QK\\6o_^t\u0017\u0001C+oW:|wO\u001c\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u0001\fi\u000b\u0003\u0004\u0002\u0018\u0019\u0002\r!\u001a\u000b\u0004A\u0006E\u0006bBAZO\u0001\u0007\u0011QE\u0001\u0002_R\u0019\u0001-a.\t\u000f\u0005e\u0006\u00061\u0001\u0002<\u0006\tA\u000fE\u0003\u0002>\u0006\rW-\u0004\u0002\u0002@*\u0019\u0011\u0011\u0019+\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003\u000b\fyLA\u0002Uef\fa\u0001\\5giB3W\u0003BAf\u0003#$B!!4\u0002TB11+!\u0003\u0002P\u0002\u00042A]Ai\t\u0015!\u0018F1\u0001v\u0011\u001d\t).\u000ba\u0001\u0003/\f!\u0001\u001d\u0019\u0011\rM\u000bI.a4f\u0013\r\tY\u000e\u0016\u0002\u0010!\u0006\u0014H/[1m\rVt7\r^5p]\u00069A/Z:u%\u00164W\u0003BAq\u0003W$B!a9\u0002pR\u0019\u0001-!:\t\u000f\u0005\u0015!\u00061\u0001\u0002hB11+!\u0003\u0002j\u0016\u00042A]Av\t\u0019!(F1\u0001\u0002nF\u0019a/!&\t\u000f\u0005E(\u00061\u0001\u0002j\u0006\t\u0011-A\u0005uKN$h\t\\8biR!\u0011q\u001fB\u0002)\r\u0001\u0017\u0011 \u0005\b\u0003\u000bY\u0003\u0019AA~!\u0019\u0019\u0016\u0011BA\u007fKB\u00191+a@\n\u0007\t\u0005AKA\u0003GY>\fG\u000fC\u0004\u0003\u0006-\u0002\r!!@\u0002\u00039\f!\u0002^3ti\u0012{WO\u00197f)\u0011\u0011YAa\u0006\u0015\u0007\u0001\u0014i\u0001C\u0004\u0002\u00061\u0002\rAa\u0004\u0011\rM\u000bIA!\u0005f!\r\u0019&1C\u0005\u0004\u0005+!&A\u0002#pk\ndW\rC\u0004\u0003\u00061\u0002\rA!\u0005\u0002\u0007I,h\u000eF\u0002a\u0005;A\u0001Ba\b.\t\u0003\u0007\u0011qD\u0001\u0005E>$\u00170A\u0004bY\u001e,'M]1\u0016\u0005\t\u0015\u0002#\u0002B\u0014\u0005{\u0001g\u0002\u0002B\u0015\u0005oqAAa\u000b\u000329!!Q\u0006B\u0018\u001b\u0005q\u0015b\u0001B\u0011\u001d&!!1\u0007B\u001b\u0003\u001da\u0017\r\u001e;jG\u0016T1A!\tO\u0013\u0011\u0011IDa\u000f\u0002\u000fA\f7m[1hK*!!1\u0007B\u001b\u0013\u0011\u0011yD!\u0011\u0003\u0011\u0011+Wj\u001c:hC:TAA!\u000f\u0003<\u0005A\u0011\r\\4fEJ\f\u0007%A\u0005ue&dW-\u00198FcV\u0011!\u0011\n\t\u0006\u0005\u0017\u0012)\u0006\u0019\b\u0005\u0005\u001b\u0012\u0019F\u0004\u0003\u0003P\t=b\u0002BA\u001b\u0005#J\u0011aT\u0005\u0005\u0005s\u0011)$\u0003\u0003\u0003X\te#AA#r\u0015\u0011\u0011ID!\u000e\u0002\u0015Q\u0014\u0018\u000e\\3b]\u0016\u000b\b%\u0001\tjgR\u0013X/\u001a\u0013fqR,gn]5p]R\u0019QM!\u0019\t\r\t\r$\u00071\u0001a\u0003\u0015!C\u000f[5t\u0003EI7OR1mg\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0004K\n%\u0004B\u0002B2g\u0001\u0007\u0001-A\njgVs7N\\8x]\u0012*\u0007\u0010^3og&|g\u000eF\u0002f\u0005_BaAa\u00195\u0001\u0004\u0001\u0017!E5t\u0017:|wO\u001c\u0013fqR,gn]5p]R\u0019QM!\u001e\t\r\t\rT\u00071\u0001a\u0003MI7OT8u)J,X\rJ3yi\u0016t7/[8o)\r)'1\u0010\u0005\u0007\u0005G2\u0004\u0019\u00011\u0002)%\u001chj\u001c;GC2\u001cX\rJ3yi\u0016t7/[8o)\r)'\u0011\u0011\u0005\u0007\u0005G:\u0004\u0019\u00011\u0002\u001d\u0019|G\u000e\u001a\u0013fqR,gn]5p]V!!q\u0011BH)\u0011\u0011II!'\u0015\t\t-%Q\u0013\u000b\u0005\u0005\u001b\u0013\t\nE\u0002s\u0005\u001f#Q\u0001\u001e\u001dC\u0002UDq! \u001d\u0005\u0002\u0004\u0011\u0019\n\u0005\u0003T\u007f\n5\u0005bBA\u0003q\u0001\u0007!q\u0013\t\u0007'\u0006%QM!$\t\r\t\r\u0004\b1\u0001a\u0003Q\t7o];nKR\u0013X/\u001a\u0013fqR,gn]5p]R\u0019QMa(\t\r\t\r\u0014\b1\u0001a\u0003U\t7o];nK\u001a\u000bGn]3%Kb$XM\\:j_:$2!\u001aBS\u0011\u0019\u0011\u0019G\u000fa\u0001A\u0006\u0001\u0012m]:v[\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0005W\u0013y\u000bF\u0002f\u0005[Ca!a\u0006<\u0001\u0004)\u0007B\u0002B2w\u0001\u0007\u0001-A\nu_\n{w\u000e\\3b]\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u00036\neFcA3\u00038\"A\u0011q\u0003\u001f\u0005\u0002\u0004\ty\u0002\u0003\u0004\u0003dq\u0002\r\u0001Y\u0001\u0013i>|\u0005\u000f^5p]\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002&\t}\u0006B\u0002B2{\u0001\u0007\u0001-\u0001\nu_N#(/\u001b8hI\u0015DH/\u001a8tS>tG\u0003BA\u0017\u0005\u000bDaAa\u0019?\u0001\u0004\u0001\u0017A\u0005\u0013b[B$\u0013-\u001c9%Kb$XM\\:j_:$BAa3\u0003PR\u0019\u0001M!4\t\u0011\u0005-s\b\"a\u0001\u0003\u001bBaAa\u0019@\u0001\u0004\u0001\u0017A\u0005\u0013cCJ$#-\u0019:%Kb$XM\\:j_:$BA!6\u0003ZR\u0019\u0001Ma6\t\u0011\u0005-\u0003\t\"a\u0001\u0003\u001bBaAa\u0019A\u0001\u0004\u0001\u0017!F;oCJLx\f\n2b]\u001e$S\r\u001f;f]NLwN\u001c\u000b\u0004A\n}\u0007B\u0002B2\u0003\u0002\u0007\u0001-\u0001\b%C6\u0004H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\t\u0015(\u0011\u001e\u000b\u0004A\n\u001d\bBBA&\u0005\u0002\u0007\u0001\r\u0003\u0004\u0003d\t\u0003\r\u0001Y\u0001\u000fI\t\f'\u000fJ3yi\u0016t7/[8o)\u0011\u0011yOa=\u0015\u0007\u0001\u0014\t\u0010\u0003\u0004\u0002L\r\u0003\r\u0001\u0019\u0005\u0007\u0005G\u001a\u0005\u0019\u00011\u0002\u001b\u0011*\b\u000fJ3yi\u0016t7/[8o)\u0011\u0011IP!@\u0015\u0007\u0001\u0014Y\u0010\u0003\u0004\u0002L\u0011\u0003\r\u0001\u0019\u0005\u0007\u0005G\"\u0005\u0019\u00011\u0002\u001b%l\u0007\u000fJ3yi\u0016t7/[8o)\u0011\u0019\u0019aa\u0002\u0015\u0007\u0001\u001c)\u0001\u0003\u0004\u0002L\u0015\u0003\r\u0001\u0019\u0005\u0007\u0005G*\u0005\u0019\u00011\u0002\u001d9\fg\u000e\u001a\u0013fqR,gn]5p]R!1QBB\t)\r\u00017q\u0002\u0005\u0007\u0003\u00172\u0005\u0019\u00011\t\r\t\rd\t1\u0001a\u00035qwN\u001d\u0013fqR,gn]5p]R!1qCB\u000e)\r\u00017\u0011\u0004\u0005\u0007\u0003\u0017:\u0005\u0019\u00011\t\r\t\rt\t1\u0001a\u00039q\u0007p\u001c:%Kb$XM\\:j_:$Ba!\t\u0004&Q\u0019\u0001ma\t\t\r\u0005-\u0003\n1\u0001a\u0011\u0019\u0011\u0019\u0007\u0013a\u0001A\u0006\u0011\u0002.Y:i\u0007>$W\rJ3yi\u0016t7/[8o)\u0011\t)ia\u000b\t\r\t\r\u0014\n1\u0001a\u0003A)\u0017/^1mg\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u00042\rUBcA3\u00044!A\u0011Q\u0012&\u0002\u0002\u0003\u0007\u0011\u0010\u0003\u0004\u0003d)\u0003\r\u0001\u0019"
)
public final class Trilean {
   private final int value;

   public static boolean equals$extension(final int $this, final Object x$1) {
      return Trilean$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final int $this) {
      return Trilean$.MODULE$.hashCode$extension($this);
   }

   public static int nxor$extension(final int $this, final int rhs) {
      return Trilean$.MODULE$.nxor$extension($this, rhs);
   }

   public static int nor$extension(final int $this, final int rhs) {
      return Trilean$.MODULE$.nor$extension($this, rhs);
   }

   public static int nand$extension(final int $this, final int rhs) {
      return Trilean$.MODULE$.nand$extension($this, rhs);
   }

   public static int imp$extension(final int $this, final int rhs) {
      return Trilean$.MODULE$.imp$extension($this, rhs);
   }

   public static int $up$extension(final int $this, final int rhs) {
      return Trilean$.MODULE$.$up$extension($this, rhs);
   }

   public static int $bar$extension(final int $this, final int rhs) {
      return Trilean$.MODULE$.$bar$extension($this, rhs);
   }

   public static int $amp$extension(final int $this, final int rhs) {
      return Trilean$.MODULE$.$amp$extension($this, rhs);
   }

   public static int unary_$bang$extension(final int $this) {
      return Trilean$.MODULE$.unary_$bang$extension($this);
   }

   public static int $bar$bar$extension(final int $this, final Function0 rhs) {
      return Trilean$.MODULE$.$bar$bar$extension($this, rhs);
   }

   public static int $amp$amp$extension(final int $this, final Function0 rhs) {
      return Trilean$.MODULE$.$amp$amp$extension($this, rhs);
   }

   public static String toString$extension(final int $this) {
      return Trilean$.MODULE$.toString$extension($this);
   }

   public static Option toOption$extension(final int $this) {
      return Trilean$.MODULE$.toOption$extension($this);
   }

   public static boolean toBoolean$extension(final int $this, final Function0 b) {
      return Trilean$.MODULE$.toBoolean$extension($this, b);
   }

   public static boolean assume$extension(final int $this, final boolean b) {
      return Trilean$.MODULE$.assume$extension($this, b);
   }

   public static boolean assumeFalse$extension(final int $this) {
      return Trilean$.MODULE$.assumeFalse$extension($this);
   }

   public static boolean assumeTrue$extension(final int $this) {
      return Trilean$.MODULE$.assumeTrue$extension($this);
   }

   public static Object fold$extension(final int $this, final Function1 f, final Function0 unknown) {
      return Trilean$.MODULE$.fold$extension($this, f, unknown);
   }

   public static boolean isNotFalse$extension(final int $this) {
      return Trilean$.MODULE$.isNotFalse$extension($this);
   }

   public static boolean isNotTrue$extension(final int $this) {
      return Trilean$.MODULE$.isNotTrue$extension($this);
   }

   public static boolean isKnown$extension(final int $this) {
      return Trilean$.MODULE$.isKnown$extension($this);
   }

   public static boolean isUnknown$extension(final int $this) {
      return Trilean$.MODULE$.isUnknown$extension($this);
   }

   public static boolean isFalse$extension(final int $this) {
      return Trilean$.MODULE$.isFalse$extension($this);
   }

   public static boolean isTrue$extension(final int $this) {
      return Trilean$.MODULE$.isTrue$extension($this);
   }

   public static Eq trileanEq() {
      return Trilean$.MODULE$.trileanEq();
   }

   public static DeMorgan algebra() {
      return Trilean$.MODULE$.algebra();
   }

   public static int run(final Function0 body) {
      return Trilean$.MODULE$.run(body);
   }

   public static int testDouble(final double n, final Function1 f) {
      return Trilean$.MODULE$.testDouble(n, f);
   }

   public static int testFloat(final float n, final Function1 f) {
      return Trilean$.MODULE$.testFloat(n, f);
   }

   public static int testRef(final Object a, final Function1 f) {
      return Trilean$.MODULE$.testRef(a, f);
   }

   public static Function1 liftPf(final PartialFunction p0) {
      return Trilean$.MODULE$.liftPf(p0);
   }

   public static int apply(final Try t) {
      return Trilean$.MODULE$.apply(t);
   }

   public static int apply(final Option o) {
      return Trilean$.MODULE$.apply(o);
   }

   public static int apply(final boolean b) {
      return Trilean$.MODULE$.apply(b);
   }

   public static int Unknown() {
      return Trilean$.MODULE$.Unknown();
   }

   public static int False() {
      return Trilean$.MODULE$.False();
   }

   public static int True() {
      return Trilean$.MODULE$.True();
   }

   public int value() {
      return this.value;
   }

   public boolean isTrue() {
      return Trilean$.MODULE$.isTrue$extension(this.value());
   }

   public boolean isFalse() {
      return Trilean$.MODULE$.isFalse$extension(this.value());
   }

   public boolean isUnknown() {
      return Trilean$.MODULE$.isUnknown$extension(this.value());
   }

   public boolean isKnown() {
      return Trilean$.MODULE$.isKnown$extension(this.value());
   }

   public boolean isNotTrue() {
      return Trilean$.MODULE$.isNotTrue$extension(this.value());
   }

   public boolean isNotFalse() {
      return Trilean$.MODULE$.isNotFalse$extension(this.value());
   }

   public Object fold(final Function1 f, final Function0 unknown) {
      return Trilean$.MODULE$.fold$extension(this.value(), f, unknown);
   }

   public boolean assumeTrue() {
      return Trilean$.MODULE$.assumeTrue$extension(this.value());
   }

   public boolean assumeFalse() {
      return Trilean$.MODULE$.assumeFalse$extension(this.value());
   }

   public boolean assume(final boolean b) {
      return Trilean$.MODULE$.assume$extension(this.value(), b);
   }

   public boolean toBoolean(final Function0 b) {
      return Trilean$.MODULE$.toBoolean$extension(this.value(), b);
   }

   public Option toOption() {
      return Trilean$.MODULE$.toOption$extension(this.value());
   }

   public String toString() {
      return Trilean$.MODULE$.toString$extension(this.value());
   }

   public int $amp$amp(final Function0 rhs) {
      return Trilean$.MODULE$.$amp$amp$extension(this.value(), rhs);
   }

   public int $bar$bar(final Function0 rhs) {
      return Trilean$.MODULE$.$bar$bar$extension(this.value(), rhs);
   }

   public int unary_$bang() {
      return Trilean$.MODULE$.unary_$bang$extension(this.value());
   }

   public int $amp(final int rhs) {
      return Trilean$.MODULE$.$amp$extension(this.value(), rhs);
   }

   public int $bar(final int rhs) {
      return Trilean$.MODULE$.$bar$extension(this.value(), rhs);
   }

   public int $up(final int rhs) {
      return Trilean$.MODULE$.$up$extension(this.value(), rhs);
   }

   public int imp(final int rhs) {
      return Trilean$.MODULE$.imp$extension(this.value(), rhs);
   }

   public int nand(final int rhs) {
      return Trilean$.MODULE$.nand$extension(this.value(), rhs);
   }

   public int nor(final int rhs) {
      return Trilean$.MODULE$.nor$extension(this.value(), rhs);
   }

   public int nxor(final int rhs) {
      return Trilean$.MODULE$.nxor$extension(this.value(), rhs);
   }

   public int hashCode() {
      return Trilean$.MODULE$.hashCode$extension(this.value());
   }

   public boolean equals(final Object x$1) {
      return Trilean$.MODULE$.equals$extension(this.value(), x$1);
   }

   public Trilean(final int value) {
      this.value = value;
   }
}
