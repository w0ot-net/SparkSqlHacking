package org.json4s;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=g\u0001B\u0012%\u0001&B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005\u0003\"A!\n\u0001BK\u0002\u0013\u0005\u0001\t\u0003\u0005L\u0001\tE\t\u0015!\u0003B\u0011!a\u0005A!f\u0001\n\u0003\u0001\u0005\u0002C'\u0001\u0005#\u0005\u000b\u0011B!\t\u000b9\u0003A\u0011A(\t\u000bQ\u0003A\u0011A+\t\rm\u0003A\u0011\u0001\u0013]\u0011\u001d9\u0007!!A\u0005\u0002!Dq\u0001\u001c\u0001\u0012\u0002\u0013\u0005Q\u000eC\u0004y\u0001E\u0005I\u0011A7\t\u000fe\u0004\u0011\u0013!C\u0001[\"9!\u0010AA\u0001\n\u0003Z\b\"CA\u0004\u0001\u0005\u0005I\u0011AA\u0005\u0011%\t\t\u0002AA\u0001\n\u0003\t\u0019\u0002C\u0005\u0002 \u0001\t\t\u0011\"\u0011\u0002\"!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0007\u0005\n\u0003w\u0001\u0011\u0011!C!\u0003{A\u0011\"!\u0011\u0001\u0003\u0003%\t%a\u0011\t\u0013\u0005\u0015\u0003!!A\u0005B\u0005\u001d\u0003\"CA%\u0001\u0005\u0005I\u0011IA&\u000f\u001d\ty\u0005\nE\u0001\u0003#2aa\t\u0013\t\u0002\u0005M\u0003B\u0002(\u0019\t\u0003\ty\u0006C\u0004\u0002ba!\t!a\u0019\t\u000f\u00055\u0004\u0004\"\u0003\u0002p!9\u0011Q\u0011\r\u0005\n\u0005\u001de\u0001DAH1A\u0005\u0019\u0011\u0001\u0013\u0002\u0012\u0006\r\u0006bBAJ;\u0011\u0005\u0011Q\u0013\u0005\b\u0003CjB\u0011AAO\u0011%\t9\u000bGA\u0001\n\u0003\u000bI\u000bC\u0005\u00022b\t\t\u0011\"!\u00024\"I\u0011Q\u0019\r\u0002\u0002\u0013%\u0011q\u0019\u0002\u0005\t&4gM\u0003\u0002&M\u00051!n]8oiMT\u0011aJ\u0001\u0004_J<7\u0001A\n\u0005\u0001)\u00024\u0007\u0005\u0002,]5\tAFC\u0001.\u0003\u0015\u00198-\u00197b\u0013\tyCF\u0001\u0004B]f\u0014VM\u001a\t\u0003WEJ!A\r\u0017\u0003\u000fA\u0013x\u000eZ;diB\u0011A\u0007\u0010\b\u0003kir!AN\u001d\u000e\u0003]R!\u0001\u000f\u0015\u0002\rq\u0012xn\u001c;?\u0013\u0005i\u0013BA\u001e-\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0010 \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005mb\u0013aB2iC:<W\rZ\u000b\u0002\u0003B\u0011!I\u0012\b\u0003\u0007\u0012k\u0011\u0001J\u0005\u0003\u000b\u0012\nqAS:p]\u0006\u001bF+\u0003\u0002H\u0011\n1!JV1mk\u0016T!!\u0012\u0013\u0002\u0011\rD\u0017M\\4fI\u0002\nQ!\u00193eK\u0012\fa!\u00193eK\u0012\u0004\u0013a\u00023fY\u0016$X\rZ\u0001\tI\u0016dW\r^3eA\u00051A(\u001b8jiz\"B\u0001U)S'B\u00111\t\u0001\u0005\u0006\u007f\u001d\u0001\r!\u0011\u0005\u0006\u0015\u001e\u0001\r!\u0011\u0005\u0006\u0019\u001e\u0001\r!Q\u0001\u0004[\u0006\u0004HC\u0001)W\u0011\u00159\u0006\u00021\u0001Y\u0003\u00051\u0007\u0003B\u0016Z\u0003\u0006K!A\u0017\u0017\u0003\u0013\u0019+hn\u0019;j_:\f\u0014a\u0002;p\r&,G\u000e\u001a\u000b\u0003!vCQAX\u0005A\u0002}\u000bAA\\1nKB\u0011\u0001\r\u001a\b\u0003C\n\u0004\"A\u000e\u0017\n\u0005\rd\u0013A\u0002)sK\u0012,g-\u0003\u0002fM\n11\u000b\u001e:j]\u001eT!a\u0019\u0017\u0002\t\r|\u0007/\u001f\u000b\u0005!&T7\u000eC\u0004@\u0015A\u0005\t\u0019A!\t\u000f)S\u0001\u0013!a\u0001\u0003\"9AJ\u0003I\u0001\u0002\u0004\t\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002]*\u0012\u0011i\\\u0016\u0002aB\u0011\u0011O^\u0007\u0002e*\u00111\u000f^\u0001\nk:\u001c\u0007.Z2lK\u0012T!!\u001e\u0017\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002xe\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001a\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001}!\ri\u0018QA\u0007\u0002}*\u0019q0!\u0001\u0002\t1\fgn\u001a\u0006\u0003\u0003\u0007\tAA[1wC&\u0011QM`\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003\u0017\u00012aKA\u0007\u0013\r\ty\u0001\f\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003+\tY\u0002E\u0002,\u0003/I1!!\u0007-\u0005\r\te.\u001f\u0005\n\u0003;\u0001\u0012\u0011!a\u0001\u0003\u0017\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0012!\u0019\t)#a\u000b\u0002\u00165\u0011\u0011q\u0005\u0006\u0004\u0003Sa\u0013AC2pY2,7\r^5p]&!\u0011QFA\u0014\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005M\u0012\u0011\b\t\u0004W\u0005U\u0012bAA\u001cY\t9!i\\8mK\u0006t\u0007\"CA\u000f%\u0005\u0005\t\u0019AA\u000b\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007q\fy\u0004C\u0005\u0002\u001eM\t\t\u00111\u0001\u0002\f\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\f\u0005AAo\\*ue&tw\rF\u0001}\u0003\u0019)\u0017/^1mgR!\u00111GA'\u0011%\tiBFA\u0001\u0002\u0004\t)\"\u0001\u0003ES\u001a4\u0007CA\"\u0019'\u0011A\"&!\u0016\u0011\t\u0005]\u0013QL\u0007\u0003\u00033RA!a\u0017\u0002\u0002\u0005\u0011\u0011n\\\u0005\u0004{\u0005eCCAA)\u0003\u0011!\u0017N\u001a4\u0015\u000bA\u000b)'!\u001b\t\r\u0005\u001d$\u00041\u0001B\u0003\u00111\u0018\r\\\u0019\t\r\u0005-$\u00041\u0001B\u0003\u00111\u0018\r\u001c\u001a\u0002\u0015\u0011LgM\u001a$jK2$7\u000fF\u0003Q\u0003c\n\t\tC\u0004\u0002tm\u0001\r!!\u001e\u0002\u0007Y\u001c\u0018\u0007E\u00035\u0003o\nY(C\u0002\u0002zy\u0012A\u0001T5tiB\u0019!)! \n\u0007\u0005}\u0004J\u0001\u0004K\r&,G\u000e\u001a\u0005\b\u0003\u0007[\u0002\u0019AA;\u0003\r18OM\u0001\tI&4gMV1mgR)\u0001+!#\u0002\u000e\"9\u00111\u000f\u000fA\u0002\u0005-\u0005\u0003\u0002\u001b\u0002x\u0005Cq!a!\u001d\u0001\u0004\tYI\u0001\u0005ES\u001a4\u0017M\u00197f'\ti\"&\u0001\u0004%S:LG\u000f\n\u000b\u0003\u0003/\u00032aKAM\u0013\r\tY\n\f\u0002\u0005+:LG\u000fF\u0002Q\u0003?Ca!!) \u0001\u0004\t\u0015!B8uQ\u0016\u0014\bcA\"\u0002&&\u0011q\tJ\u0001\u0006CB\u0004H.\u001f\u000b\b!\u0006-\u0016QVAX\u0011\u0015y\u0004\u00051\u0001B\u0011\u0015Q\u0005\u00051\u0001B\u0011\u0015a\u0005\u00051\u0001B\u0003\u001d)h.\u00199qYf$B!!.\u0002BB)1&a.\u0002<&\u0019\u0011\u0011\u0018\u0017\u0003\r=\u0003H/[8o!\u0019Y\u0013QX!B\u0003&\u0019\u0011q\u0018\u0017\u0003\rQ+\b\u000f\\34\u0011!\t\u0019-IA\u0001\u0002\u0004\u0001\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\u001a\t\u0004{\u0006-\u0017bAAg}\n1qJ\u00196fGR\u0004"
)
public class Diff implements Product, Serializable {
   private final JValue changed;
   private final JValue added;
   private final JValue deleted;

   public static Option unapply(final Diff x$0) {
      return Diff$.MODULE$.unapply(x$0);
   }

   public static Diff apply(final JValue changed, final JValue added, final JValue deleted) {
      return Diff$.MODULE$.apply(changed, added, deleted);
   }

   public static Diff diff(final JValue val1, final JValue val2) {
      return Diff$.MODULE$.diff(val1, val2);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public JValue changed() {
      return this.changed;
   }

   public JValue added() {
      return this.added;
   }

   public JValue deleted() {
      return this.deleted;
   }

   public Diff map(final Function1 f) {
      return new Diff(applyTo$1(this.changed(), f), applyTo$1(this.added(), f), applyTo$1(this.deleted(), f));
   }

   public Diff toField(final String name) {
      return new Diff(applyTo$2(this.changed(), name), applyTo$2(this.added(), name), applyTo$2(this.deleted(), name));
   }

   public Diff copy(final JValue changed, final JValue added, final JValue deleted) {
      return new Diff(changed, added, deleted);
   }

   public JValue copy$default$1() {
      return this.changed();
   }

   public JValue copy$default$2() {
      return this.added();
   }

   public JValue copy$default$3() {
      return this.deleted();
   }

   public String productPrefix() {
      return "Diff";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.changed();
            break;
         case 1:
            var10000 = this.added();
            break;
         case 2:
            var10000 = this.deleted();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Diff;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "changed";
            break;
         case 1:
            var10000 = "added";
            break;
         case 2:
            var10000 = "deleted";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var11;
      if (this != x$1) {
         label72: {
            boolean var2;
            if (x$1 instanceof Diff) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label54: {
                  label63: {
                     Diff var4 = (Diff)x$1;
                     JValue var10000 = this.changed();
                     JValue var5 = var4.changed();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label63;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label63;
                     }

                     var10000 = this.added();
                     JValue var6 = var4.added();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label63;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label63;
                     }

                     var10000 = this.deleted();
                     JValue var7 = var4.deleted();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label63;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label63;
                     }

                     if (var4.canEqual(this)) {
                        var11 = true;
                        break label54;
                     }
                  }

                  var11 = false;
               }

               if (var11) {
                  break label72;
               }
            }

            var11 = false;
            return var11;
         }
      }

      var11 = true;
      return var11;
   }

   private static final JValue applyTo$1(final JValue x, final Function1 f$1) {
      Object var2;
      label24: {
         JNothing$ var10000 = JsonAST$.MODULE$.JNothing();
         if (var10000 == null) {
            if (x == null) {
               break label24;
            }
         } else if (var10000.equals(x)) {
            break label24;
         }

         var2 = (JValue)f$1.apply(x);
         return (JValue)var2;
      }

      var2 = JsonAST$.MODULE$.JNothing();
      return (JValue)var2;
   }

   private static final JValue applyTo$2(final JValue x, final String name$1) {
      Object var2;
      label24: {
         JNothing$ var10000 = JsonAST$.MODULE$.JNothing();
         if (var10000 == null) {
            if (x == null) {
               break label24;
            }
         } else if (var10000.equals(x)) {
            break label24;
         }

         var2 = JsonAST$.MODULE$.JObject().apply((Seq).MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(name$1, x)})));
         return (JValue)var2;
      }

      var2 = JsonAST$.MODULE$.JNothing();
      return (JValue)var2;
   }

   public Diff(final JValue changed, final JValue added, final JValue deleted) {
      this.changed = changed;
      this.added = added;
      this.deleted = deleted;
      Product.$init$(this);
   }

   public interface Diffable {
      // $FF: synthetic method
      static Diff diff$(final Diffable $this, final JValue other) {
         return $this.diff(other);
      }

      default Diff diff(final JValue other) {
         return Diff$.MODULE$.diff((JValue)this, other);
      }

      static void $init$(final Diffable $this) {
      }
   }
}
