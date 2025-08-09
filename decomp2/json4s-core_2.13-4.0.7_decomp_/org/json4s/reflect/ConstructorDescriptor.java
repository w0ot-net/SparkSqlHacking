package org.json4s.reflect;

import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]d\u0001\u0002\u000f\u001e\u0001\u0012B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u0007\u0002\u0011\t\u0012)A\u0005{!AA\t\u0001BK\u0002\u0013\u0005Q\t\u0003\u0005J\u0001\tE\t\u0015!\u0003G\u0011!Q\u0005A!f\u0001\n\u0003Y\u0005\u0002C(\u0001\u0005#\u0005\u000b\u0011\u0002'\t\u000bA\u0003A\u0011A)\t\u000fY\u0003\u0011\u0011!C\u0001/\"91\fAI\u0001\n\u0003a\u0006bB4\u0001#\u0003%\t\u0001\u001b\u0005\bU\u0002\t\n\u0011\"\u0001l\u0011\u001di\u0007!!A\u0005B9Dqa\u001e\u0001\u0002\u0002\u0013\u0005\u0001\u0010C\u0004}\u0001\u0005\u0005I\u0011A?\t\u0013\u0005\u001d\u0001!!A\u0005B\u0005%\u0001\"CA\f\u0001\u0005\u0005I\u0011AA\r\u0011%\ti\u0002AA\u0001\n\u0003\ny\u0002C\u0005\u0002$\u0001\t\t\u0011\"\u0011\u0002&!I\u0011q\u0005\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0006\u0005\n\u0003W\u0001\u0011\u0011!C!\u0003[9\u0011\"!\r\u001e\u0003\u0003E\t!a\r\u0007\u0011qi\u0012\u0011!E\u0001\u0003kAa\u0001\u0015\f\u0005\u0002\u00055\u0003\"CA\u0014-\u0005\u0005IQIA\u0015\u0011%\tyEFA\u0001\n\u0003\u000b\t\u0006C\u0005\u0002ZY\t\t\u0011\"!\u0002\\!I\u0011Q\u000e\f\u0002\u0002\u0013%\u0011q\u000e\u0002\u0016\u0007>t7\u000f\u001e:vGR|'\u000fR3tGJL\u0007\u000f^8s\u0015\tqr$A\u0004sK\u001adWm\u0019;\u000b\u0005\u0001\n\u0013A\u00026t_:$4OC\u0001#\u0003\ry'oZ\u0002\u0001'\u0011\u0001Q%K\u0018\u0011\u0005\u0019:S\"A\u000f\n\u0005!j\"A\u0003#fg\u000e\u0014\u0018\u000e\u001d;peB\u0011!&L\u0007\u0002W)\tA&A\u0003tG\u0006d\u0017-\u0003\u0002/W\t9\u0001K]8ek\u000e$\bC\u0001\u00199\u001d\t\tdG\u0004\u00023k5\t1G\u0003\u00025G\u00051AH]8pizJ\u0011\u0001L\u0005\u0003o-\nq\u0001]1dW\u0006<W-\u0003\u0002:u\ta1+\u001a:jC2L'0\u00192mK*\u0011qgK\u0001\u0007a\u0006\u0014\u0018-\\:\u0016\u0003u\u00022\u0001\r A\u0013\ty$HA\u0002TKF\u0004\"AJ!\n\u0005\tk\"AG\"p]N$(/^2u_J\u0004\u0016M]1n\t\u0016\u001c8M]5qi>\u0014\u0018a\u00029be\u0006l7\u000fI\u0001\fG>t7\u000f\u001e:vGR|'/F\u0001G!\t1s)\u0003\u0002I;\tQQ\t_3dkR\f'\r\\3\u0002\u0019\r|gn\u001d;sk\u000e$xN\u001d\u0011\u0002\u0013%\u001c\bK]5nCJLX#\u0001'\u0011\u0005)j\u0015B\u0001(,\u0005\u001d\u0011un\u001c7fC:\f!\"[:Qe&l\u0017M]=!\u0003\u0019a\u0014N\\5u}Q!!k\u0015+V!\t1\u0003\u0001C\u0003<\u000f\u0001\u0007Q\bC\u0003E\u000f\u0001\u0007a\tC\u0003K\u000f\u0001\u0007A*\u0001\u0003d_BLH\u0003\u0002*Y3jCqa\u000f\u0005\u0011\u0002\u0003\u0007Q\bC\u0004E\u0011A\u0005\t\u0019\u0001$\t\u000f)C\u0001\u0013!a\u0001\u0019\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A/+\u0005ur6&A0\u0011\u0005\u0001,W\"A1\u000b\u0005\t\u001c\u0017!C;oG\",7m[3e\u0015\t!7&\u0001\u0006b]:|G/\u0019;j_:L!AZ1\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003%T#A\u00120\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\tAN\u000b\u0002M=\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012a\u001c\t\u0003aVl\u0011!\u001d\u0006\u0003eN\fA\u0001\\1oO*\tA/\u0001\u0003kCZ\f\u0017B\u0001<r\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t\u0011\u0010\u0005\u0002+u&\u00111p\u000b\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004}\u0006\r\u0001C\u0001\u0016\u0000\u0013\r\t\ta\u000b\u0002\u0004\u0003:L\b\u0002CA\u0003\u001d\u0005\u0005\t\u0019A=\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tY\u0001E\u0003\u0002\u000e\u0005Ma0\u0004\u0002\u0002\u0010)\u0019\u0011\u0011C\u0016\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0016\u0005=!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$2\u0001TA\u000e\u0011!\t)\u0001EA\u0001\u0002\u0004q\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2a\\A\u0011\u0011!\t)!EA\u0001\u0002\u0004I\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003e\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002_\u00061Q-];bYN$2\u0001TA\u0018\u0011!\t)\u0001FA\u0001\u0002\u0004q\u0018!F\"p]N$(/^2u_J$Um]2sSB$xN\u001d\t\u0003MY\u0019RAFA\u001c\u0003\u0007\u0002\u0002\"!\u000f\u0002@u2EJU\u0007\u0003\u0003wQ1!!\u0010,\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u0011\u0002<\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0011\t\u0005\u0015\u00131J\u0007\u0003\u0003\u000fR1!!\u0013t\u0003\tIw.C\u0002:\u0003\u000f\"\"!a\r\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000fI\u000b\u0019&!\u0016\u0002X!)1(\u0007a\u0001{!)A)\u0007a\u0001\r\")!*\u0007a\u0001\u0019\u00069QO\\1qa2LH\u0003BA/\u0003S\u0002RAKA0\u0003GJ1!!\u0019,\u0005\u0019y\u0005\u000f^5p]B1!&!\u001a>\r2K1!a\u001a,\u0005\u0019!V\u000f\u001d7fg!A\u00111\u000e\u000e\u0002\u0002\u0003\u0007!+A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u001d\u0011\u0007A\f\u0019(C\u0002\u0002vE\u0014aa\u00142kK\u000e$\b"
)
public class ConstructorDescriptor extends Descriptor {
   private final Seq params;
   private final Executable constructor;
   private final boolean isPrimary;

   public static Option unapply(final ConstructorDescriptor x$0) {
      return ConstructorDescriptor$.MODULE$.unapply(x$0);
   }

   public static ConstructorDescriptor apply(final Seq params, final Executable constructor, final boolean isPrimary) {
      return ConstructorDescriptor$.MODULE$.apply(params, constructor, isPrimary);
   }

   public static Function1 tupled() {
      return ConstructorDescriptor$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ConstructorDescriptor$.MODULE$.curried();
   }

   public Seq params() {
      return this.params;
   }

   public Executable constructor() {
      return this.constructor;
   }

   public boolean isPrimary() {
      return this.isPrimary;
   }

   public ConstructorDescriptor copy(final Seq params, final Executable constructor, final boolean isPrimary) {
      return new ConstructorDescriptor(params, constructor, isPrimary);
   }

   public Seq copy$default$1() {
      return this.params();
   }

   public Executable copy$default$2() {
      return this.constructor();
   }

   public boolean copy$default$3() {
      return this.isPrimary();
   }

   public String productPrefix() {
      return "ConstructorDescriptor";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.params();
            break;
         case 1:
            var10000 = this.constructor();
            break;
         case 2:
            var10000 = BoxesRunTime.boxToBoolean(this.isPrimary());
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
      return x$1 instanceof ConstructorDescriptor;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "params";
            break;
         case 1:
            var10000 = "constructor";
            break;
         case 2:
            var10000 = "isPrimary";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.params()));
      var1 = Statics.mix(var1, Statics.anyHash(this.constructor()));
      var1 = Statics.mix(var1, this.isPrimary() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label65: {
            boolean var2;
            if (x$1 instanceof ConstructorDescriptor) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label47: {
                  ConstructorDescriptor var4 = (ConstructorDescriptor)x$1;
                  if (this.isPrimary() == var4.isPrimary()) {
                     label56: {
                        Seq var10000 = this.params();
                        Seq var5 = var4.params();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label56;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label56;
                        }

                        Executable var7 = this.constructor();
                        Executable var6 = var4.constructor();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label56;
                           }
                        } else if (!var7.equals(var6)) {
                           break label56;
                        }

                        if (var4.canEqual(this)) {
                           var9 = true;
                           break label47;
                        }
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label65;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public ConstructorDescriptor(final Seq params, final Executable constructor, final boolean isPrimary) {
      this.params = params;
      this.constructor = constructor;
      this.isPrimary = isPrimary;
   }
}
