package scala.xml;

import java.lang.invoke.SerializedLambda;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005c\u0001B\f\u0019\u0001vA\u0001B\r\u0001\u0003\u0016\u0004%\ta\r\u0005\ty\u0001\u0011\t\u0012)A\u0005i!)Q\b\u0001C\u0001}!)\u0011\t\u0001C#\u0005\")a\t\u0001C#\u0005\")q\t\u0001C!g!)\u0001\n\u0001C!g!)\u0011\n\u0001C!\u0015\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006b\u0002+\u0001#\u0003%\t!\u0016\u0005\bA\u0002\t\t\u0011\"\u0011b\u0011\u001dI\u0007!!A\u0005\u0002)DqA\u001c\u0001\u0002\u0002\u0013\u0005q\u000eC\u0004v\u0001\u0005\u0005I\u0011\t<\t\u000fu\u0004\u0011\u0011!C!}\u001eI\u0011\u0011\u0001\r\u0002\u0002#\u0005\u00111\u0001\u0004\t/a\t\t\u0011#\u0001\u0002\u0006!1Q(\u0005C\u0001\u0003;A\u0011\"a\b\u0012\u0003\u0003%)%!\t\t\u0013\u0005\r\u0012#!A\u0005\u0002\u0006\u0015\u0002\"CA\u0015#\u0005\u0005I\u0011QA\u0016\u0011%\t9$EA\u0001\n\u0013\tIDA\u0005F]RLG/\u001f*fM*\u0011\u0011DG\u0001\u0004q6d'\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M!\u0001A\b\u0012'!\ty\u0002%D\u0001\u0019\u0013\t\t\u0003DA\u0006Ta\u0016\u001c\u0017.\u00197O_\u0012,\u0007CA\u0012%\u001b\u0005Q\u0012BA\u0013\u001b\u0005\u001d\u0001&o\u001c3vGR\u0004\"aJ\u0018\u000f\u0005!jcBA\u0015-\u001b\u0005Q#BA\u0016\u001d\u0003\u0019a$o\\8u}%\t1$\u0003\u0002/5\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u00192\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tq#$\u0001\u0006f]RLG/\u001f(b[\u0016,\u0012\u0001\u000e\t\u0003ker!AN\u001c\u0011\u0005%R\u0012B\u0001\u001d\u001b\u0003\u0019\u0001&/\u001a3fM&\u0011!h\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005aR\u0012aC3oi&$\u0018PT1nK\u0002\na\u0001P5oSRtDCA A!\ty\u0002\u0001C\u00033\u0007\u0001\u0007A'A\ne_\u000e{G\u000e\\3di:\u000bW.Z:qC\u000e,7/F\u0001D!\t\u0019C)\u0003\u0002F5\t9!i\\8mK\u0006t\u0017a\u00033p)J\fgn\u001d4pe6\fQ\u0001\\1cK2\fA\u0001^3yi\u0006Y!-^5mIN#(/\u001b8h)\tYu\n\u0005\u0002M\u001b:\u00111%L\u0005\u0003\u001dF\u0012Qb\u0015;sS:<')^5mI\u0016\u0014\b\"\u0002)\t\u0001\u0004Y\u0015AA:c\u0003\u0011\u0019w\u000e]=\u0015\u0005}\u001a\u0006b\u0002\u001a\n!\u0003\u0005\r\u0001N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u00051&F\u0001\u001bXW\u0005A\u0006CA-_\u001b\u0005Q&BA.]\u0003%)hn\u00195fG.,GM\u0003\u0002^5\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005}S&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u0019\t\u0003G\"l\u0011\u0001\u001a\u0006\u0003K\u001a\fA\u0001\\1oO*\tq-\u0001\u0003kCZ\f\u0017B\u0001\u001ee\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005Y\u0007CA\u0012m\u0013\ti'DA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002qgB\u00111%]\u0005\u0003ej\u00111!\u00118z\u0011\u001d!X\"!AA\u0002-\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A<\u0011\u0007a\\\b/D\u0001z\u0015\tQ($\u0001\u0006d_2dWm\u0019;j_:L!\u0001`=\u0003\u0011%#XM]1u_J\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0011!m \u0005\bi>\t\t\u00111\u0001l\u0003%)e\u000e^5usJ+g\r\u0005\u0002 #M)\u0011#a\u0002\u0002\u0014A1\u0011\u0011BA\bi}j!!a\u0003\u000b\u0007\u00055!$A\u0004sk:$\u0018.\\3\n\t\u0005E\u00111\u0002\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\u000b\u00037i!!a\u0006\u000b\u0007\u0005ea-\u0001\u0002j_&\u0019\u0001'a\u0006\u0015\u0005\u0005\r\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\t\fQ!\u00199qYf$2aPA\u0014\u0011\u0015\u0011D\u00031\u00015\u0003\u001d)h.\u00199qYf$B!!\f\u00024A!1%a\f5\u0013\r\t\tD\u0007\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005UR#!AA\u0002}\n1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tY\u0004E\u0002d\u0003{I1!a\u0010e\u0005\u0019y%M[3di\u0002"
)
public class EntityRef extends SpecialNode implements Product {
   private final String entityName;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String entityName() {
      return this.entityName;
   }

   public final boolean doCollectNamespaces() {
      return false;
   }

   public final boolean doTransform() {
      return false;
   }

   public String label() {
      return "#ENTITY";
   }

   public String text() {
      String var2 = this.entityName();
      switch (var2 == null ? 0 : var2.hashCode()) {
         case 3309:
            if ("gt".equals(var2)) {
               return ">";
            }
            break;
         case 3464:
            if ("lt".equals(var2)) {
               return "<";
            }
            break;
         case 96708:
            if ("amp".equals(var2)) {
               return "&";
            }
            break;
         case 3000915:
            if ("apos".equals(var2)) {
               return "'";
            }
            break;
         case 3482377:
            if ("quot".equals(var2)) {
               return "\"";
            }
      }

      return Utility$.MODULE$.sbToString((sb) -> {
         $anonfun$text$1(this, sb);
         return BoxedUnit.UNIT;
      });
   }

   public StringBuilder buildString(final StringBuilder sb) {
      return sb.append((new java.lang.StringBuilder(2)).append("&").append(this.entityName()).append(";").toString());
   }

   public EntityRef copy(final String entityName) {
      return new EntityRef(entityName);
   }

   public String copy$default$1() {
      return this.entityName();
   }

   public String productPrefix() {
      return "EntityRef";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.entityName();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "entityName";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$text$1(final EntityRef $this, final StringBuilder sb) {
      $this.buildString(sb);
   }

   public EntityRef(final String entityName) {
      this.entityName = entityName;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
