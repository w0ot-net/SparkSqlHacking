package scala.reflect.internal;

import java.lang.reflect.Member;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tUe\u0001B)S\u0005eC\u0001B\u0018\u0001\u0003\u0006\u0004%\ta\u0018\u0005\tG\u0002\u0011\t\u0011)A\u0005A\"aA\r\u0001C\u0001\u0002\u0003\u0005\t\u0011!C\u0005K\")\u0011\u000e\u0001C\u0005U\")\u0001\u000f\u0001C\u0005?\")\u0011\u000f\u0001C\u0005?\")!\u000f\u0001C\u0001g\")A\u000f\u0001C\u0001g\")Q\u000f\u0001C\u0001g\")a\u000f\u0001C\u0001g\")q\u000f\u0001C\u0001g\")\u0001\u0010\u0001C\u0001g\")\u0011\u0010\u0001C\u0001g\")!\u0010\u0001C\u0001g\")1\u0010\u0001C\u0001g\")A\u0010\u0001C\u0001g\")Q\u0010\u0001C\u0001g\")a\u0010\u0001C\u0001g\")q\u0010\u0001C\u0001g\"1\u0011\u0011\u0001\u0001\u0005\u0002MDa!a\u0001\u0001\t\u0003\u0019\bBBA\u0003\u0001\u0011\u00051\u000f\u0003\u0004\u0002\b\u0001!\ta\u001d\u0005\u0007\u0003\u0013\u0001A\u0011A:\t\r\u0005-\u0001\u0001\"\u0001t\u0011\u0019\ti\u0001\u0001C\u0001g\"1\u0011q\u0002\u0001\u0005\u0002}Cq!!\u0005\u0001\t\u0003\t\u0019\u0002C\u0004\u0002\u001c\u0001!\t!!\b\t\u0013\u0005\u0015\u0003!!A\u0005B\u0005\u001d\u0003\"CA%\u0001\u0005\u0005I\u0011IA&\u000f\u001d\t9F\u0015E\u0001\u000332a!\u0015*\t\u0002\u0005m\u0003B\u00023\"\t\u0003\t\u0019\u0007\u0003\u0005\u0002f\u0005\u0012\r\u0011\"\u0003`\u0011\u001d\t9'\tQ\u0001\n\u0001D\u0001\"!\u001b\"\u0005\u0004%Ia\u0018\u0005\b\u0003W\n\u0003\u0015!\u0003a\u0011!\ti'\tb\u0001\n\u0013y\u0006bBA8C\u0001\u0006I\u0001\u0019\u0005\t\u0003c\n#\u0019!C\u0005?\"9\u00111O\u0011!\u0002\u0013\u0001\u0007\u0002CA;C\t\u0007I\u0011B0\t\u000f\u0005]\u0014\u0005)A\u0005A\"9\u0011\u0011P\u0011\u0005\n\u0005m\u0004bBACC\u0011\u0005\u0011q\u0011\u0005\b\u0003\u0017\u000bC\u0011AAG\u0011\u001d\t\t*\tC\u0001\u0003'Cq!a&\"\t\u0003\tI\nC\u0004\u0002\u001e\u0006\"\t!a(\t\u000f\u0005u\u0015\u0005\"\u0001\u0002$\"9\u0011QT\u0011\u0005\u0002\u0005%\u0007bBAmC\u0011\u0015\u00111\u001c\u0005\b\u0003K\fCQAAt\u0011\u001d\tY/\tC\u0003\u0003[Dq!!=\"\t\u000b\t\u0019\u0010C\u0004\u0002x\u0006\")!!?\t\u000f\u0005u\u0018\u0005\"\u0002\u0002\u0000\"9!1A\u0011\u0005\u0006\t\u0015\u0001b\u0002B\u0005C\u0011\u0015!1\u0002\u0005\b\u0005\u001f\tCQ\u0001B\t\u0011\u001d\u0011)\"\tC\u0003\u0005/AqAa\u0007\"\t\u000b\u0011i\u0002C\u0004\u0003\"\u0005\")Aa\t\t\u000f\t\u001d\u0012\u0005\"\u0002\u0003*!9!QF\u0011\u0005\u0006\t=\u0002b\u0002B\u001aC\u0011\u0015!Q\u0007\u0005\b\u0005s\tCQ\u0001B\u001e\u0011\u001d\u0011y$\tC\u0003\u0005\u0003BqA!\u0012\"\t\u000b\u00119\u0005C\u0004\u0003L\u0005\")A!\u0014\t\u000f\tE\u0013\u0005\"\u0002\u0003T!9!qK\u0011\u0005\u0006\te\u0003b\u0002B/C\u0011\u0015!q\f\u0005\b\u0005G\nCQ\u0001B3\u0011\u001d\u0011I'\tC\u0003\u0005WBqAa\u001c\"\t\u000b\u0011\t\bC\u0004\u0003v\u0005\")Aa\u001e\t\u0013\t\u0015\u0015%!A\u0005\u0006\t\u001d\u0005\"\u0003BFC\u0005\u0005IQ\u0001BG\u00051Q\u0015M^1BG\u000e4E.Y4t\u0015\t\u0019F+\u0001\u0005j]R,'O\\1m\u0015\t)f+A\u0004sK\u001adWm\u0019;\u000b\u0003]\u000bQa]2bY\u0006\u001c\u0001a\u0005\u0002\u00015B\u00111\fX\u0007\u0002-&\u0011QL\u0016\u0002\u0007\u0003:Lh+\u00197\u0002\u000b\r|G-\u001a3\u0016\u0003\u0001\u0004\"aW1\n\u0005\t4&aA%oi\u000611m\u001c3fI\u0002\na\u0001P5oSRtDC\u00014i!\t9\u0007!D\u0001S\u0011\u0015q6\u00011\u0001a\u0003\rA\u0017m\u001d\u000b\u0003W:\u0004\"a\u00177\n\u000554&a\u0002\"p_2,\u0017M\u001c\u0005\u0006_\u0012\u0001\r\u0001Y\u0001\u0005[\u0006\u001c8.A\u0007gY\u0006<7)\u0019:sS\u0016\u0014\u0018\nZ\u0001\u0006M2\fwm]\u0001\u000bSN\f%m\u001d;sC\u000e$X#A6\u0002\u0019%\u001c\u0018I\u001c8pi\u0006$\u0018n\u001c8\u0002\u0011%\u001c(I]5eO\u0016\fa![:F]Vl\u0017aB5t\r&t\u0017\r\\\u0001\fSNLe\u000e^3sM\u0006\u001cW-\u0001\u0005jg:\u000bG/\u001b<f\u0003%I7\u000f\u0015:jm\u0006$X-A\u0006jgB\u0013x\u000e^3di\u0016$\u0017\u0001C5t!V\u0014G.[2\u0002\u0011%\u001c8\u000b^1uS\u000e\f!\"[:TiJL7\r\u001e$q\u0003\u001dI7oU;qKJ\fa\"[:Ts:\u001c\u0007N]8oSj,G-A\u0006jgNKh\u000e\u001e5fi&\u001c\u0017aC5t)J\fgn]5f]R\f\u0011\"[:WCJ\f'oZ:\u0002\u0015%\u001chk\u001c7bi&dW-\u0001\riCN\u0004\u0016mY6bO\u0016\f5mY3tg\n{WO\u001c3bef\f!#[:QC\u000e\\\u0017mZ3Qe>$Xm\u0019;fI\u0006YAo\u001c&bm\u00064E.Y4t\u00031!xnU2bY\u00064E.Y4t+\t\t)\u0002E\u0002\\\u0003/I1!!\u0007W\u0005\u0011auN\\4\u0002%Q|7kY1mC\u0006sgn\u001c;bi&|gn\u001d\u000b\u0005\u0003?\t\u0019\u0004\u0005\u0004\u0002\"\u0005\u001d\u0012Q\u0006\b\u00047\u0006\r\u0012bAA\u0013-\u00069\u0001/Y2lC\u001e,\u0017\u0002BA\u0015\u0003W\u0011A\u0001T5ti*\u0019\u0011Q\u0005,\u0011\t\u0005=\u0012Q\b\b\u0005\u0003c\t\u0019\u0004\u0004\u0001\t\u000f\u0005UR\u00041\u0001\u00028\u0005!1/_7t!\r9\u0017\u0011H\u0005\u0004\u0003w\u0011&aC*z[\n|G\u000eV1cY\u0016LA!a\u0010\u0002B\tq\u0011I\u001c8pi\u0006$\u0018n\u001c8J]\u001a|\u0017bAA\"%\ny\u0011I\u001c8pi\u0006$\u0018n\u001c8J]\u001a|7/\u0001\u0005iCND7i\u001c3f)\u0005\u0001\u0017AB3rk\u0006d7\u000fF\u0002l\u0003\u001bB\u0011\"a\u0014 \u0003\u0003\u0005\r!!\u0015\u0002\u0007a$\u0013\u0007E\u0002\\\u0003'J1!!\u0016W\u0005\r\te._\u0001\r\u0015\u00064\u0018-Q2d\r2\fwm\u001d\t\u0003O\u0006\u001a2!IA/!\rY\u0016qL\u0005\u0004\u0003C2&AB!osJ+g\r\u0006\u0002\u0002Z\u00059QK\\6o_^t\u0017\u0001C+oW:|wO\u001c\u0011\u0002\u000b\rc\u0017m]:\u0002\r\rc\u0017m]:!\u0003\u00151\u0015.\u001a7e\u0003\u00191\u0015.\u001a7eA\u00051Q*\u001a;i_\u0012\fq!T3uQ>$\u0007%A\u0006D_:\u001cHO];di>\u0014\u0018\u0001D\"p]N$(/^2u_J\u0004\u0013AB2sK\u0006$X\rF\u0003g\u0003{\n\t\t\u0003\u0004\u0002\u00005\u0002\r\u0001Y\u0001\fM2\fwmQ1se&,'\u000f\u0003\u0004\u0002\u00046\u0002\r\u0001Y\u0001\rC\u000e\u001cWm]:`M2\fwm]\u0001\u000bG2\f7o\u001d$mC\u001e\u001cHc\u00014\u0002\n\")\u0011O\fa\u0001A\u0006YQ.\u001a;i_\u00124E.Y4t)\r1\u0017q\u0012\u0005\u0006c>\u0002\r\u0001Y\u0001\u000bM&,G\u000e\u001a$mC\u001e\u001cHc\u00014\u0002\u0016\")\u0011\u000f\ra\u0001A\u0006\u00012m\u001c8tiJ,8\r^8s\r2\fwm\u001d\u000b\u0004M\u0006m\u0005\"B92\u0001\u0004\u0001\u0017!B1qa2LHc\u00014\u0002\"\"1\u00111\u0011\u001aA\u0002\u0001$2AZAS\u0011\u001d\t9k\ra\u0001\u0003S\u000bQa\u00197buj\u0004D!a+\u0002>B1\u0011QVA\\\u0003wk!!a,\u000b\t\u0005E\u00161W\u0001\u0005Y\u0006twM\u0003\u0002\u00026\u0006!!.\u0019<b\u0013\u0011\tI,a,\u0003\u000b\rc\u0017m]:\u0011\t\u0005E\u0012Q\u0018\u0003\r\u0003\u007f\u000b)+!A\u0001\u0002\u000b\u0005\u0011\u0011\u0019\u0002\u0004?\u0012\n\u0014\u0003BAb\u0003#\u00022aWAc\u0013\r\t9M\u0016\u0002\b\u001d>$\b.\u001b8h)\r1\u00171\u001a\u0005\b\u0003\u001b$\u0004\u0019AAh\u0003\u0019iW-\u001c2feB!\u0011\u0011[Ak\u001b\t\t\u0019NC\u0002V\u0003_KA!a6\u0002T\n1Q*Z7cKJ\fQ\u0002[1tI\u0015DH/\u001a8tS>tG\u0003BAo\u0003C$2a[Ap\u0011\u0015yW\u00071\u0001a\u0011\u0019\t\u0019/\u000ea\u0001M\u0006)A\u0005\u001e5jg\u00069b\r\\1h\u0007\u0006\u0014(/[3s\u0013\u0012$S\r\u001f;f]NLwN\u001c\u000b\u0004A\u0006%\bBBArm\u0001\u0007a-A\bgY\u0006<7\u000fJ3yi\u0016t7/[8o)\r\u0001\u0017q\u001e\u0005\u0007\u0003G<\u0004\u0019\u00014\u0002)%\u001c\u0018IY:ue\u0006\u001cG\u000fJ3yi\u0016t7/[8o)\rY\u0017Q\u001f\u0005\u0007\u0003GD\u0004\u0019\u00014\u0002-%\u001c\u0018I\u001c8pi\u0006$\u0018n\u001c8%Kb$XM\\:j_:$2a[A~\u0011\u0019\t\u0019/\u000fa\u0001M\u0006\u0011\u0012n\u001d\"sS\u0012<W\rJ3yi\u0016t7/[8o)\rY'\u0011\u0001\u0005\u0007\u0003GT\u0004\u0019\u00014\u0002!%\u001cXI\\;nI\u0015DH/\u001a8tS>tGcA6\u0003\b!1\u00111]\u001eA\u0002\u0019\f\u0011#[:GS:\fG\u000eJ3yi\u0016t7/[8o)\rY'Q\u0002\u0005\u0007\u0003Gd\u0004\u0019\u00014\u0002+%\u001c\u0018J\u001c;fe\u001a\f7-\u001a\u0013fqR,gn]5p]R\u00191Na\u0005\t\r\u0005\rX\b1\u0001g\u0003II7OT1uSZ,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007-\u0014I\u0002\u0003\u0004\u0002dz\u0002\rAZ\u0001\u0014SN\u0004&/\u001b<bi\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0004W\n}\u0001BBAr\u007f\u0001\u0007a-A\u000bjgB\u0013x\u000e^3di\u0016$G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007-\u0014)\u0003\u0003\u0004\u0002d\u0002\u0003\rAZ\u0001\u0013SN\u0004VO\u00197jG\u0012*\u0007\u0010^3og&|g\u000eF\u0002l\u0005WAa!a9B\u0001\u00041\u0017AE5t'R\fG/[2%Kb$XM\\:j_:$2a\u001bB\u0019\u0011\u0019\t\u0019O\u0011a\u0001M\u0006!\u0012n]*ue&\u001cGO\u00129%Kb$XM\\:j_:$2a\u001bB\u001c\u0011\u0019\t\u0019o\u0011a\u0001M\u0006\t\u0012n]*va\u0016\u0014H%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007-\u0014i\u0004\u0003\u0004\u0002d\u0012\u0003\rAZ\u0001\u0019SN\u001c\u0016P\\2ie>t\u0017N_3eI\u0015DH/\u001a8tS>tGcA6\u0003D!1\u00111]#A\u0002\u0019\fQ#[:Ts:$\b.\u001a;jG\u0012*\u0007\u0010^3og&|g\u000eF\u0002l\u0005\u0013Ba!a9G\u0001\u00041\u0017!F5t)J\fgn]5f]R$S\r\u001f;f]NLwN\u001c\u000b\u0004W\n=\u0003BBAr\u000f\u0002\u0007a-A\njgZ\u000b'/\u0019:hg\u0012*\u0007\u0010^3og&|g\u000eF\u0002l\u0005+Ba!a9I\u0001\u00041\u0017\u0001F5t->d\u0017\r^5mK\u0012*\u0007\u0010^3og&|g\u000eF\u0002l\u00057Ba!a9J\u0001\u00041\u0017A\t5bgB\u000b7m[1hK\u0006\u001b7-Z:t\u0005>,h\u000eZ1ss\u0012*\u0007\u0010^3og&|g\u000eF\u0002l\u0005CBa!a9K\u0001\u00041\u0017\u0001H5t!\u0006\u001c7.Y4f!J|G/Z2uK\u0012$S\r\u001f;f]NLwN\u001c\u000b\u0004W\n\u001d\u0004BBAr\u0017\u0002\u0007a-A\u000bu_*\u000bg/\u0019$mC\u001e\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007\u0001\u0014i\u0007\u0003\u0004\u0002d2\u0003\rAZ\u0001\u0017i>\u001c6-\u00197b\r2\fwm\u001d\u0013fqR,gn]5p]R!\u0011Q\u0003B:\u0011\u0019\t\u0019/\u0014a\u0001M\u0006aBo\\*dC2\f\u0017I\u001c8pi\u0006$\u0018n\u001c8tI\u0015DH/\u001a8tS>tG\u0003\u0002B=\u0005\u0007#BAa\u001f\u0003\u0002B1\u0011\u0011EA\u0014\u0005{\u0002BAa \u0002>9!\u0011\u0011\u0007BA\u0011\u001d\t)D\u0014a\u0001\u0003oAa!a9O\u0001\u00041\u0017A\u00055bg\"\u001cu\u000eZ3%Kb$XM\\:j_:$B!a\u0012\u0003\n\"1\u00111](A\u0002\u0019\f\u0001#Z9vC2\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\t=%1\u0013\u000b\u0004W\nE\u0005\"CA(!\u0006\u0005\t\u0019AA)\u0011\u0019\t\u0019\u000f\u0015a\u0001M\u0002"
)
public final class JavaAccFlags {
   private final int coded;

   public static boolean equals$extension(final int $this, final Object x$1) {
      return JavaAccFlags$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final int $this) {
      JavaAccFlags$ var10000 = JavaAccFlags$.MODULE$;
      return Integer.hashCode($this);
   }

   public static List toScalaAnnotations$extension(final int $this, final SymbolTable syms) {
      return JavaAccFlags$.MODULE$.toScalaAnnotations$extension($this, syms);
   }

   public static long toScalaFlags$extension(final int $this) {
      return JavaAccFlags$.MODULE$.toScalaFlags$extension($this);
   }

   public static int toJavaFlags$extension(final int $this) {
      JavaAccFlags$ var10000 = JavaAccFlags$.MODULE$;
      return $this & '\uffff';
   }

   public static boolean isPackageProtected$extension(final int $this) {
      return JavaAccFlags$.MODULE$.isPackageProtected$extension($this);
   }

   public static boolean hasPackageAccessBoundary$extension(final int $this) {
      return JavaAccFlags$.MODULE$.hasPackageAccessBoundary$extension($this);
   }

   public static boolean isVolatile$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 64);
   }

   public static boolean isVarargs$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 128);
   }

   public static boolean isTransient$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 128);
   }

   public static boolean isSynthetic$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 4096);
   }

   public static boolean isSynchronized$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 32);
   }

   public static boolean isSuper$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 32);
   }

   public static boolean isStrictFp$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 2048);
   }

   public static boolean isStatic$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 8);
   }

   public static boolean isPublic$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 1);
   }

   public static boolean isProtected$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 4);
   }

   public static boolean isPrivate$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 2);
   }

   public static boolean isNative$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 256);
   }

   public static boolean isInterface$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 512);
   }

   public static boolean isFinal$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 16);
   }

   public static boolean isEnum$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 16384);
   }

   public static boolean isBridge$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 64);
   }

   public static boolean isAnnotation$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 8192);
   }

   public static boolean isAbstract$extension(final int $this) {
      return JavaAccFlags$.MODULE$.has$extension($this, 1024);
   }

   public static int flags$extension(final int $this) {
      JavaAccFlags$ var10000 = JavaAccFlags$.MODULE$;
      return $this & '\uffff';
   }

   public static int flagCarrierId$extension(final int $this) {
      JavaAccFlags$ var10000 = JavaAccFlags$.MODULE$;
      return $this >>> 16;
   }

   public static boolean has$extension(final int $this, final int mask) {
      return JavaAccFlags$.MODULE$.has$extension($this, mask);
   }

   public static int apply(final Member member) {
      return JavaAccFlags$.MODULE$.apply(member);
   }

   public static int apply(final Class clazz) {
      return JavaAccFlags$.MODULE$.apply(clazz);
   }

   public static int apply(final int access_flags) {
      return JavaAccFlags$.MODULE$.apply(access_flags);
   }

   public static int constructorFlags(final int flags) {
      return JavaAccFlags$.MODULE$.constructorFlags(flags);
   }

   public static int fieldFlags(final int flags) {
      return JavaAccFlags$.MODULE$.fieldFlags(flags);
   }

   public static int methodFlags(final int flags) {
      return JavaAccFlags$.MODULE$.methodFlags(flags);
   }

   public static int classFlags(final int flags) {
      return JavaAccFlags$.MODULE$.classFlags(flags);
   }

   public int coded() {
      return this.coded;
   }

   private boolean has(final int mask) {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), mask);
   }

   private int flagCarrierId() {
      JavaAccFlags$ var10000 = JavaAccFlags$.MODULE$;
      return this.coded() >>> 16;
   }

   private int flags() {
      JavaAccFlags$ var10000 = JavaAccFlags$.MODULE$;
      return this.coded() & '\uffff';
   }

   public boolean isAbstract() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 1024);
   }

   public boolean isAnnotation() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 8192);
   }

   public boolean isBridge() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 64);
   }

   public boolean isEnum() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 16384);
   }

   public boolean isFinal() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 16);
   }

   public boolean isInterface() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 512);
   }

   public boolean isNative() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 256);
   }

   public boolean isPrivate() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 2);
   }

   public boolean isProtected() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 4);
   }

   public boolean isPublic() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 1);
   }

   public boolean isStatic() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 8);
   }

   public boolean isStrictFp() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 2048);
   }

   public boolean isSuper() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 32);
   }

   public boolean isSynchronized() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 32);
   }

   public boolean isSynthetic() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 4096);
   }

   public boolean isTransient() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 128);
   }

   public boolean isVarargs() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 128);
   }

   public boolean isVolatile() {
      return JavaAccFlags$.MODULE$.has$extension(this.coded(), 64);
   }

   public boolean hasPackageAccessBoundary() {
      return JavaAccFlags$.MODULE$.hasPackageAccessBoundary$extension(this.coded());
   }

   public boolean isPackageProtected() {
      return JavaAccFlags$.MODULE$.isPackageProtected$extension(this.coded());
   }

   public int toJavaFlags() {
      JavaAccFlags$ var10000 = JavaAccFlags$.MODULE$;
      return this.coded() & '\uffff';
   }

   public long toScalaFlags() {
      return JavaAccFlags$.MODULE$.toScalaFlags$extension(this.coded());
   }

   public List toScalaAnnotations(final SymbolTable syms) {
      return JavaAccFlags$.MODULE$.toScalaAnnotations$extension(this.coded(), syms);
   }

   public int hashCode() {
      JavaAccFlags$ var10000 = JavaAccFlags$.MODULE$;
      return Integer.hashCode(this.coded());
   }

   public boolean equals(final Object x$1) {
      return JavaAccFlags$.MODULE$.equals$extension(this.coded(), x$1);
   }

   public JavaAccFlags(final int coded) {
      this.coded = coded;
   }
}
