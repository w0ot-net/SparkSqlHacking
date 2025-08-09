package spire.algebra.free;

import cats.kernel.Group;
import scala.Function1;
import scala.collection.Iterator;
import scala.collection.immutable.Vector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055h\u0001B\u000e\u001d\u0005\rB\u0001b\u000b\u0001\u0003\u0006\u0004%\t\u0001\f\u0005\t\u000f\u0002\u0011\t\u0011)A\u0005[!a\u0001\n\u0001C\u0001\u0002\u0003\u0005\t\u0011!C\u0005\u0013\")Q\n\u0001C\u0001\u001d\")!\r\u0001C\u0001G\")a\r\u0001C\u0001O\")\u0011\u000e\u0001C\u0001U\")1\u000e\u0001C\u0005Y\")!\u000f\u0001C!g\"9A\u0010AA\u0001\n\u0003j\b\"CA\u0002\u0001\u0005\u0005I\u0011IA\u0003\u000f\u001d\t\t\u0002\bE\u0001\u0003'1aa\u0007\u000f\t\u0002\u0005U\u0001B\u0002%\u000e\t\u0003\ti\u0002C\u0004\u0002 5!)!!\t\t\u000f\u0005-R\u0002\"\u0002\u0002.!9\u00111H\u0007\u0005\u0006\u0005u\u0002bBA%\u001b\u0011\r\u00111\n\u0005\b\u0003/jAQAA-\u0011\u001d\t9(\u0004C\u0003\u0003sBq!!#\u000e\t\u000b\tY\tC\u0004\u0002\u001c6!)!!(\t\u000f\u0005%V\u0002\"\u0002\u0002,\"9\u0011qX\u0007\u0005\u0006\u0005\u0005\u0007\"CAg\u001b\u0005\u0005IQAAh\u0011%\tY.DA\u0001\n\u000b\tiNA\u0005Ge\u0016,wI]8va*\u0011QDH\u0001\u0005MJ,WM\u0003\u0002 A\u00059\u0011\r\\4fEJ\f'\"A\u0011\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011AEP\n\u0003\u0001\u0015\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012a!\u00118z-\u0006d\u0017!\u0002;fe6\u001cX#A\u0017\u0011\u000792\u0014H\u0004\u00020i9\u0011\u0001gM\u0007\u0002c)\u0011!GI\u0001\u0007yI|w\u000e\u001e \n\u0003!J!!N\u0014\u0002\u000fA\f7m[1hK&\u0011q\u0007\u000f\u0002\u0007-\u0016\u001cGo\u001c:\u000b\u0005U:\u0003\u0003\u0002\u0018;yqJ!a\u000f\u001d\u0003\r\u0015KG\u000f[3s!\tid\b\u0004\u0001\u0005\u000b}\u0002!\u0019\u0001!\u0003\u0003\u0005\u000b\"!\u0011#\u0011\u0005\u0019\u0012\u0015BA\"(\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"AJ#\n\u0005\u0019;#aA!os\u00061A/\u001a:ng\u0002\na\u0001P5oSRtDC\u0001&M!\rY\u0005\u0001P\u0007\u00029!)1f\u0001a\u0001[\u0005\u0019!/\u001e8\u0016\u0005=\u0013FC\u0001)^)\t\tF\u000b\u0005\u0002>%\u0012)1\u000b\u0002b\u0001\u0001\n\t!\tC\u0003V\t\u0001\u000fa+A\u0001C!\r9&,\u0015\b\u00031fk\u0011AH\u0005\u0003kyI!a\u0017/\u0003\u000b\u001d\u0013x.\u001e9\u000b\u0005Ur\u0002\"\u00020\u0005\u0001\u0004y\u0016!\u00014\u0011\t\u0019\u0002G(U\u0005\u0003C\u001e\u0012\u0011BR;oGRLwN\\\u0019\u0002\u001b\u0011\u0012\u0017M\u001d\u0013qYV\u001cHEY1s)\tQE\rC\u0003f\u000b\u0001\u0007!*A\u0002sQN\fa\u0002\n2be\u0012j\u0017N\\;tI\t\f'\u000f\u0006\u0002KQ\")QM\u0002a\u0001\u0015\u00069\u0011N\u001c<feN,W#\u0001&\u0002\rI,G-^2f)\tQU\u000eC\u0003o\u0011\u0001\u0007q.\u0001\u0002jiB\u0019a\u0006]\u001d\n\u0005ED$\u0001C%uKJ\fGo\u001c:\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001\u001e\t\u0003kft!A^<\u0011\u0005A:\u0013B\u0001=(\u0003\u0019\u0001&/\u001a3fM&\u0011!p\u001f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005a<\u0013\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003y\u0004\"AJ@\n\u0007\u0005\u0005qEA\u0002J]R\fa!Z9vC2\u001cH\u0003BA\u0004\u0003\u001b\u00012AJA\u0005\u0013\r\tYa\n\u0002\b\u0005>|G.Z1o\u0011!\tyaCA\u0001\u0002\u0004!\u0015a\u0001=%c\u0005IaI]3f\u000fJ|W\u000f\u001d\t\u0003\u00176\u00192!DA\f!\r1\u0013\u0011D\u0005\u0004\u000379#AB!osJ+g\r\u0006\u0002\u0002\u0014\u0005\u0011\u0011\u000eZ\u000b\u0005\u0003G\tI#\u0006\u0002\u0002&A!1\nAA\u0014!\ri\u0014\u0011\u0006\u0003\u0006\u007f=\u0011\r\u0001Q\u0001\u0006CB\u0004H._\u000b\u0005\u0003_\t)\u0004\u0006\u0003\u00022\u0005]\u0002\u0003B&\u0001\u0003g\u00012!PA\u001b\t\u0015y\u0004C1\u0001A\u0011\u001d\tI\u0004\u0005a\u0001\u0003g\t\u0011!Y\u0001\u0005Y&4G/\u0006\u0003\u0002@\u0005\u0015C\u0003BA!\u0003\u000f\u0002Ba\u0013\u0001\u0002DA\u0019Q(!\u0012\u0005\u000b}\n\"\u0019\u0001!\t\u000f\u0005e\u0012\u00031\u0001\u0002D\u0005qaI]3f\u000fJ|W\u000f]$s_V\u0004X\u0003BA'\u0003+*\"!a\u0014\u0011\t]S\u0016\u0011\u000b\t\u0005\u0017\u0002\t\u0019\u0006E\u0002>\u0003+\"Qa\u0010\nC\u0002\u0001\u000bQB];oI\u0015DH/\u001a8tS>tWCBA.\u0003G\ny\u0007\u0006\u0003\u0002^\u0005ED\u0003BA0\u0003S\"B!!\u0019\u0002fA\u0019Q(a\u0019\u0005\u000bM\u001b\"\u0019\u0001!\t\rU\u001b\u00029AA4!\u00119&,!\u0019\t\ry\u001b\u0002\u0019AA6!\u00191\u0003-!\u001c\u0002bA\u0019Q(a\u001c\u0005\u000b}\u001a\"\u0019\u0001!\t\u000f\u0005M4\u00031\u0001\u0002v\u0005)A\u0005\u001e5jgB!1\nAA7\u0003]!#-\u0019:%a2,8\u000f\n2be\u0012*\u0007\u0010^3og&|g.\u0006\u0003\u0002|\u0005\rE\u0003BA?\u0003\u000f#B!a \u0002\u0006B!1\nAAA!\ri\u00141\u0011\u0003\u0006\u007fQ\u0011\r\u0001\u0011\u0005\u0007KR\u0001\r!a \t\u000f\u0005MD\u00031\u0001\u0002\u0000\u0005ABEY1sI5Lg.^:%E\u0006\u0014H%\u001a=uK:\u001c\u0018n\u001c8\u0016\t\u00055\u0015Q\u0013\u000b\u0005\u0003\u001f\u000bI\n\u0006\u0003\u0002\u0012\u0006]\u0005\u0003B&\u0001\u0003'\u00032!PAK\t\u0015yTC1\u0001A\u0011\u0019)W\u00031\u0001\u0002\u0012\"9\u00111O\u000bA\u0002\u0005E\u0015!E5om\u0016\u00148/\u001a\u0013fqR,gn]5p]V!\u0011qTAS)\u0011\t\t+a*\u0011\t-\u0003\u00111\u0015\t\u0004{\u0005\u0015F!B \u0017\u0005\u0004\u0001\u0005bBA:-\u0001\u0007\u0011\u0011U\u0001\u0011e\u0016$WoY3%Kb$XM\\:j_:,B!!,\u00026R!\u0011qVA_)\u0011\t\t,a.\u0011\t-\u0003\u00111\u0017\t\u0004{\u0005UF!B \u0018\u0005\u0004\u0001\u0005B\u00028\u0018\u0001\u0004\tI\f\u0005\u0003/a\u0006m\u0006C\u0002\u0018;\u0003g\u000b\u0019\fC\u0004\u0002t]\u0001\r!!-\u0002%Q|7\u000b\u001e:j]\u001e$S\r\u001f;f]NLwN\\\u000b\u0005\u0003\u0007\fY\rF\u0002t\u0003\u000bDq!a\u001d\u0019\u0001\u0004\t9\r\u0005\u0003L\u0001\u0005%\u0007cA\u001f\u0002L\u0012)q\b\u0007b\u0001\u0001\u0006\u0011\u0002.Y:i\u0007>$W\rJ3yi\u0016t7/[8o+\u0011\t\t.!7\u0015\u0007u\f\u0019\u000eC\u0004\u0002te\u0001\r!!6\u0011\t-\u0003\u0011q\u001b\t\u0004{\u0005eG!B \u001a\u0005\u0004\u0001\u0015\u0001E3rk\u0006d7\u000fJ3yi\u0016t7/[8o+\u0011\ty.a;\u0015\t\u0005\u0005\u0018Q\u001d\u000b\u0005\u0003\u000f\t\u0019\u000f\u0003\u0005\u0002\u0010i\t\t\u00111\u0001E\u0011\u001d\t\u0019H\u0007a\u0001\u0003O\u0004Ba\u0013\u0001\u0002jB\u0019Q(a;\u0005\u000b}R\"\u0019\u0001!"
)
public final class FreeGroup {
   private final Vector terms;

   public static boolean equals$extension(final Vector $this, final Object x$1) {
      return FreeGroup$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final Vector $this) {
      return FreeGroup$.MODULE$.hashCode$extension($this);
   }

   public static String toString$extension(final Vector $this) {
      return FreeGroup$.MODULE$.toString$extension($this);
   }

   public static Vector reduce$extension(final Vector $this, final Iterator it) {
      return FreeGroup$.MODULE$.reduce$extension($this, it);
   }

   public static Vector inverse$extension(final Vector $this) {
      return FreeGroup$.MODULE$.inverse$extension($this);
   }

   public static Vector $bar$minus$bar$extension(final Vector $this, final Vector rhs) {
      return FreeGroup$.MODULE$.$bar$minus$bar$extension($this, rhs);
   }

   public static Vector $bar$plus$bar$extension(final Vector $this, final Vector rhs) {
      return FreeGroup$.MODULE$.$bar$plus$bar$extension($this, rhs);
   }

   public static Object run$extension(final Vector $this, final Function1 f, final Group B) {
      return FreeGroup$.MODULE$.run$extension($this, f, B);
   }

   public static Group FreeGroupGroup() {
      return FreeGroup$.MODULE$.FreeGroupGroup();
   }

   public static Vector lift(final Object a) {
      return FreeGroup$.MODULE$.lift(a);
   }

   public static Vector apply(final Object a) {
      return FreeGroup$.MODULE$.apply(a);
   }

   public static Vector id() {
      return FreeGroup$.MODULE$.id();
   }

   public Vector terms() {
      return this.terms;
   }

   public Object run(final Function1 f, final Group B) {
      return FreeGroup$.MODULE$.run$extension(this.terms(), f, B);
   }

   public Vector $bar$plus$bar(final Vector rhs) {
      return FreeGroup$.MODULE$.$bar$plus$bar$extension(this.terms(), rhs);
   }

   public Vector $bar$minus$bar(final Vector rhs) {
      return FreeGroup$.MODULE$.$bar$minus$bar$extension(this.terms(), rhs);
   }

   public Vector inverse() {
      return FreeGroup$.MODULE$.inverse$extension(this.terms());
   }

   private Vector reduce(final Iterator it) {
      return FreeGroup$.MODULE$.reduce$extension(this.terms(), it);
   }

   public String toString() {
      return FreeGroup$.MODULE$.toString$extension(this.terms());
   }

   public int hashCode() {
      return FreeGroup$.MODULE$.hashCode$extension(this.terms());
   }

   public boolean equals(final Object x$1) {
      return FreeGroup$.MODULE$.equals$extension(this.terms(), x$1);
   }

   public FreeGroup(final Vector terms) {
      this.terms = terms;
   }
}
