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
   bytes = "\u0006\u0005\u0005Uf\u0001\u0002\u0012$\u0001*B\u0001\"\u0011\u0001\u0003\u0016\u0004%\tA\u0011\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0007\"AA\n\u0001BK\u0002\u0013\u0005!\t\u0003\u0005N\u0001\tE\t\u0015!\u0003D\u0011!q\u0005A!f\u0001\n\u0003y\u0005\u0002C*\u0001\u0005#\u0005\u000b\u0011\u0002)\t\u0011Q\u0003!Q3A\u0005\u0002UC\u0001\"\u0017\u0001\u0003\u0012\u0003\u0006IA\u0016\u0005\t5\u0002\u0011)\u001a!C\u00017\"A!\r\u0001B\tB\u0003%A\fC\u0003d\u0001\u0011\u0005A\rC\u0004l\u0001\u0005\u0005I\u0011\u00017\t\u000fI\u0004\u0011\u0013!C\u0001g\"9a\u0010AI\u0001\n\u0003\u0019\b\u0002C@\u0001#\u0003%\t!!\u0001\t\u0013\u0005\u0015\u0001!%A\u0005\u0002\u0005\u001d\u0001\"CA\u0006\u0001E\u0005I\u0011AA\u0007\u0011%\t\t\u0002AA\u0001\n\u0003\n\u0019\u0002C\u0005\u0002$\u0001\t\t\u0011\"\u0001\u0002&!I\u0011Q\u0006\u0001\u0002\u0002\u0013\u0005\u0011q\u0006\u0005\n\u0003w\u0001\u0011\u0011!C!\u0003{A\u0011\"a\u0013\u0001\u0003\u0003%\t!!\u0014\t\u0013\u0005]\u0003!!A\u0005B\u0005e\u0003\"CA/\u0001\u0005\u0005I\u0011IA0\u0011%\t\t\u0007AA\u0001\n\u0003\n\u0019\u0007C\u0005\u0002f\u0001\t\t\u0011\"\u0011\u0002h\u001dI\u00111N\u0012\u0002\u0002#\u0005\u0011Q\u000e\u0004\tE\r\n\t\u0011#\u0001\u0002p!11\r\bC\u0001\u0003\u000fC\u0011\"!\u0019\u001d\u0003\u0003%)%a\u0019\t\u0013\u0005%E$!A\u0005\u0002\u0006-\u0005\"CAL9\u0005\u0005I\u0011QAM\u0011%\tY\u000bHA\u0001\n\u0013\tiKA\nTS:<G.\u001a;p]\u0012+7o\u0019:jaR|'O\u0003\u0002%K\u00059!/\u001a4mK\u000e$(B\u0001\u0014(\u0003\u0019Q7o\u001c85g*\t\u0001&A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001W=*\u0004C\u0001\u0017.\u001b\u0005\u0019\u0013B\u0001\u0018$\u0005)!Um]2sSB$xN\u001d\t\u0003aMj\u0011!\r\u0006\u0002e\u0005)1oY1mC&\u0011A'\r\u0002\b!J|G-^2u!\t1dH\u0004\u00028y9\u0011\u0001hO\u0007\u0002s)\u0011!(K\u0001\u0007yI|w\u000e\u001e \n\u0003IJ!!P\u0019\u0002\u000fA\f7m[1hK&\u0011q\b\u0011\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003{E\n!b]5na2,g*Y7f+\u0005\u0019\u0005C\u0001#I\u001d\t)e\t\u0005\u00029c%\u0011q)M\u0001\u0007!J,G-\u001a4\n\u0005%S%AB*ue&twM\u0003\u0002Hc\u0005Y1/[7qY\u0016t\u0015-\\3!\u0003!1W\u000f\u001c7OC6,\u0017!\u00034vY2t\u0015-\\3!\u0003\u001d)'/Y:ve\u0016,\u0012\u0001\u0015\t\u0003YEK!AU\u0012\u0003\u0013M\u001b\u0017\r\\1UsB,\u0017\u0001C3sCN,(/\u001a\u0011\u0002\u0011%t7\u000f^1oG\u0016,\u0012A\u0016\t\u0003a]K!\u0001W\u0019\u0003\r\u0005s\u0017PU3g\u0003%Ign\u001d;b]\u000e,\u0007%\u0001\u0006qe>\u0004XM\u001d;jKN,\u0012\u0001\u0018\t\u0004mu{\u0016B\u00010A\u0005\r\u0019V-\u001d\t\u0003Y\u0001L!!Y\u0012\u0003%A\u0013x\u000e]3sif$Um]2sSB$xN]\u0001\faJ|\u0007/\u001a:uS\u0016\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0007K\u001a<\u0007.\u001b6\u0011\u00051\u0002\u0001\"B!\f\u0001\u0004\u0019\u0005\"\u0002'\f\u0001\u0004\u0019\u0005\"\u0002(\f\u0001\u0004\u0001\u0006\"\u0002+\f\u0001\u00041\u0006\"\u0002.\f\u0001\u0004a\u0016\u0001B2paf$b!Z7o_B\f\bbB!\r!\u0003\u0005\ra\u0011\u0005\b\u00192\u0001\n\u00111\u0001D\u0011\u001dqE\u0002%AA\u0002ACq\u0001\u0016\u0007\u0011\u0002\u0003\u0007a\u000bC\u0004[\u0019A\u0005\t\u0019\u0001/\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tAO\u000b\u0002Dk.\na\u000f\u0005\u0002xy6\t\u0001P\u0003\u0002zu\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003wF\n!\"\u00198o_R\fG/[8o\u0013\ti\bPA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005\r!F\u0001)v\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*\"!!\u0003+\u0005Y+\u0018AD2paf$C-\u001a4bk2$H%N\u000b\u0003\u0003\u001fQ#\u0001X;\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t)\u0002\u0005\u0003\u0002\u0018\u0005\u0005RBAA\r\u0015\u0011\tY\"!\b\u0002\t1\fgn\u001a\u0006\u0003\u0003?\tAA[1wC&\u0019\u0011*!\u0007\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u001d\u0002c\u0001\u0019\u0002*%\u0019\u00111F\u0019\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005E\u0012q\u0007\t\u0004a\u0005M\u0012bAA\u001bc\t\u0019\u0011I\\=\t\u0013\u0005eB#!AA\u0002\u0005\u001d\u0012a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002@A1\u0011\u0011IA$\u0003ci!!a\u0011\u000b\u0007\u0005\u0015\u0013'\u0001\u0006d_2dWm\u0019;j_:LA!!\u0013\u0002D\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ty%!\u0016\u0011\u0007A\n\t&C\u0002\u0002TE\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002:Y\t\t\u00111\u0001\u00022\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t)\"a\u0017\t\u0013\u0005er#!AA\u0002\u0005\u001d\u0012\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005\u001d\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005U\u0011AB3rk\u0006d7\u000f\u0006\u0003\u0002P\u0005%\u0004\"CA\u001d5\u0005\u0005\t\u0019AA\u0019\u0003M\u0019\u0016N\\4mKR|g\u000eR3tGJL\u0007\u000f^8s!\taCdE\u0003\u001d\u0003c\ni\b\u0005\u0006\u0002t\u0005e4i\u0011)W9\u0016l!!!\u001e\u000b\u0007\u0005]\u0014'A\u0004sk:$\u0018.\\3\n\t\u0005m\u0014Q\u000f\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:,\u0004\u0003BA@\u0003\u000bk!!!!\u000b\t\u0005\r\u0015QD\u0001\u0003S>L1aPAA)\t\ti'A\u0003baBd\u0017\u0010F\u0006f\u0003\u001b\u000by)!%\u0002\u0014\u0006U\u0005\"B! \u0001\u0004\u0019\u0005\"\u0002' \u0001\u0004\u0019\u0005\"\u0002( \u0001\u0004\u0001\u0006\"\u0002+ \u0001\u00041\u0006\"\u0002. \u0001\u0004a\u0016aB;oCB\u0004H.\u001f\u000b\u0005\u00037\u000b9\u000bE\u00031\u0003;\u000b\t+C\u0002\u0002 F\u0012aa\u00149uS>t\u0007\u0003\u0003\u0019\u0002$\u000e\u001b\u0005K\u0016/\n\u0007\u0005\u0015\u0016G\u0001\u0004UkBdW-\u000e\u0005\t\u0003S\u0003\u0013\u0011!a\u0001K\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005=\u0006\u0003BA\f\u0003cKA!a-\u0002\u001a\t1qJ\u00196fGR\u0004"
)
public class SingletonDescriptor extends Descriptor {
   private final String simpleName;
   private final String fullName;
   private final ScalaType erasure;
   private final Object instance;
   private final Seq properties;

   public static Option unapply(final SingletonDescriptor x$0) {
      return SingletonDescriptor$.MODULE$.unapply(x$0);
   }

   public static SingletonDescriptor apply(final String simpleName, final String fullName, final ScalaType erasure, final Object instance, final Seq properties) {
      return SingletonDescriptor$.MODULE$.apply(simpleName, fullName, erasure, instance, properties);
   }

   public static Function1 tupled() {
      return SingletonDescriptor$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SingletonDescriptor$.MODULE$.curried();
   }

   public String simpleName() {
      return this.simpleName;
   }

   public String fullName() {
      return this.fullName;
   }

   public ScalaType erasure() {
      return this.erasure;
   }

   public Object instance() {
      return this.instance;
   }

   public Seq properties() {
      return this.properties;
   }

   public SingletonDescriptor copy(final String simpleName, final String fullName, final ScalaType erasure, final Object instance, final Seq properties) {
      return new SingletonDescriptor(simpleName, fullName, erasure, instance, properties);
   }

   public String copy$default$1() {
      return this.simpleName();
   }

   public String copy$default$2() {
      return this.fullName();
   }

   public ScalaType copy$default$3() {
      return this.erasure();
   }

   public Object copy$default$4() {
      return this.instance();
   }

   public Seq copy$default$5() {
      return this.properties();
   }

   public String productPrefix() {
      return "SingletonDescriptor";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.simpleName();
            break;
         case 1:
            var10000 = this.fullName();
            break;
         case 2:
            var10000 = this.erasure();
            break;
         case 3:
            var10000 = this.instance();
            break;
         case 4:
            var10000 = this.properties();
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
      return x$1 instanceof SingletonDescriptor;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "simpleName";
            break;
         case 1:
            var10000 = "fullName";
            break;
         case 2:
            var10000 = "erasure";
            break;
         case 3:
            var10000 = "instance";
            break;
         case 4:
            var10000 = "properties";
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
      boolean var13;
      if (this != x$1) {
         label83: {
            boolean var2;
            if (x$1 instanceof SingletonDescriptor) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label65: {
                  label74: {
                     SingletonDescriptor var4 = (SingletonDescriptor)x$1;
                     String var10000 = this.simpleName();
                     String var5 = var4.simpleName();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label74;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label74;
                     }

                     var10000 = this.fullName();
                     String var6 = var4.fullName();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label74;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label74;
                     }

                     ScalaType var10 = this.erasure();
                     ScalaType var7 = var4.erasure();
                     if (var10 == null) {
                        if (var7 != null) {
                           break label74;
                        }
                     } else if (!var10.equals(var7)) {
                        break label74;
                     }

                     if (BoxesRunTime.equals(this.instance(), var4.instance())) {
                        label48: {
                           Seq var11 = this.properties();
                           Seq var8 = var4.properties();
                           if (var11 == null) {
                              if (var8 != null) {
                                 break label48;
                              }
                           } else if (!var11.equals(var8)) {
                              break label48;
                           }

                           if (var4.canEqual(this)) {
                              var13 = true;
                              break label65;
                           }
                        }
                     }
                  }

                  var13 = false;
               }

               if (var13) {
                  break label83;
               }
            }

            var13 = false;
            return var13;
         }
      }

      var13 = true;
      return var13;
   }

   public SingletonDescriptor(final String simpleName, final String fullName, final ScalaType erasure, final Object instance, final Seq properties) {
      this.simpleName = simpleName;
      this.fullName = fullName;
      this.erasure = erasure;
      this.instance = instance;
      this.properties = properties;
   }
}
