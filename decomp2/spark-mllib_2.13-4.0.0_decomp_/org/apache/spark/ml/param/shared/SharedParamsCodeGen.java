package org.apache.spark.ml.param.shared;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple7;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001dxA\u0002\u001f>\u0011\u0003i\u0014J\u0002\u0004L{!\u0005Q\b\u0014\u0005\u0006'\u0006!\t!\u0016\u0005\u0006-\u0006!\ta\u0016\u0004\u0005W\u0006!E\u000e\u0003\u0005{\t\tU\r\u0011\"\u0001|\u0011!aHA!E!\u0002\u0013\u0001\u0007\u0002C?\u0005\u0005+\u0007I\u0011A>\t\u0011y$!\u0011#Q\u0001\n\u0001D\u0011b \u0003\u0003\u0016\u0004%\t!!\u0001\t\u0015\u0005%AA!E!\u0002\u0013\t\u0019\u0001C\u0005\u0002\f\u0011\u0011)\u001a!C\u0001w\"I\u0011Q\u0002\u0003\u0003\u0012\u0003\u0006I\u0001\u0019\u0005\u000b\u0003\u001f!!Q3A\u0005\u0002\u0005E\u0001BCA\r\t\tE\t\u0015!\u0003\u0002\u0014!Q\u00111\u0004\u0003\u0003\u0016\u0004%\t!!\u0005\t\u0015\u0005uAA!E!\u0002\u0013\t\u0019\u0002\u0003\u0006\u0002 \u0011\u0011)\u001a!C\u0001\u0003#A!\"!\t\u0005\u0005#\u0005\u000b\u0011BA\n\u0011)\t\u0019\u0003\u0002B\u0002B\u0003-\u0011Q\u0005\u0005\u0007'\u0012!\t!a\u0012\t\r\u0005}C\u0001\"\u0001|\u0011\u0019\t\t\u0007\u0002C\u0001w\"9\u00111\r\u0003\u0005\n\u0005\u0015\u0004\"CA=\t\u0005\u0005I\u0011AA>\u0011%\tI\nBI\u0001\n\u0003\tY\nC\u0005\u00026\u0012\t\n\u0011\"\u0001\u00028\"I\u00111\u0018\u0003\u0012\u0002\u0013\u0005\u0011Q\u0018\u0005\n\u0003\u000b$\u0011\u0013!C\u0001\u0003\u000fD\u0011\"a3\u0005#\u0003%\t!!4\t\u0013\u0005UG!%A\u0005\u0002\u0005]\u0007\"CAn\tE\u0005I\u0011AAo\u0011%\t\t\u000fBA\u0001\n\u0003\n\u0019\u000fC\u0005\u0002t\u0012\t\t\u0011\"\u0001\u0002v\"I\u0011Q \u0003\u0002\u0002\u0013\u0005\u0011q \u0005\n\u0005\u000b!\u0011\u0011!C!\u0005\u000fA\u0011B!\u0006\u0005\u0003\u0003%\tAa\u0006\t\u0013\tmA!!A\u0005B\tu\u0001\"\u0003B\u0011\t\u0005\u0005I\u0011\tB\u0012\u0011%\u0011)\u0003BA\u0001\n\u0003\u00129\u0003C\u0005\u0003*\u0011\t\t\u0011\"\u0011\u0003,\u001dI!qF\u0001\u0002\u0002#%!\u0011\u0007\u0004\tW\u0006\t\t\u0011#\u0003\u00034!11K\u000bC\u0001\u0005\u007fA\u0011B!\n+\u0003\u0003%)Ea\n\t\u0013\t\u0005#&!A\u0005\u0002\n\r\u0003\"\u0003B1UE\u0005I\u0011\u0001B2\u0011%\u00119GKI\u0001\n\u0003\u0011I\u0007C\u0005\u0003n)\n\n\u0011\"\u0001\u0003p!I!1\u000f\u0016\u0012\u0002\u0013\u0005!Q\u000f\u0005\n\u0005sR\u0013\u0013!C\u0001\u0005wB\u0011Ba +\u0003\u0003%\tI!!\t\u0013\t]%&%A\u0005\u0002\te\u0005\"\u0003BOUE\u0005I\u0011\u0001BP\u0011%\u0011\u0019KKI\u0001\n\u0003\u0011)\u000bC\u0005\u0003**\n\n\u0011\"\u0001\u0003,\"I!q\u0016\u0016\u0012\u0002\u0013\u0005!\u0011\u0017\u0005\n\u0005kS\u0013\u0011!C\u0005\u0005oCqAa0\u0002\t\u0013\u0011\t\rC\u0004\u0003P\u0006!IA!5\u0002'MC\u0017M]3e!\u0006\u0014\u0018-\\:D_\u0012,w)\u001a8\u000b\u0005yz\u0014AB:iCJ,GM\u0003\u0002A\u0003\u0006)\u0001/\u0019:b[*\u0011!iQ\u0001\u0003[2T!\u0001R#\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0019;\u0015AB1qC\u000eDWMC\u0001I\u0003\ry'o\u001a\t\u0003\u0015\u0006i\u0011!\u0010\u0002\u0014'\"\f'/\u001a3QCJ\fWn]\"pI\u0016<UM\\\n\u0003\u00035\u0003\"AT)\u000e\u0003=S\u0011\u0001U\u0001\u0006g\u000e\fG.Y\u0005\u0003%>\u0013a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003%\u000bA!\\1j]R\u0011\u0001l\u0017\t\u0003\u001dfK!AW(\u0003\tUs\u0017\u000e\u001e\u0005\u00069\u000e\u0001\r!X\u0001\u0005CJ<7\u000fE\u0002O=\u0002L!aX(\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0005DgB\u00012g!\t\u0019w*D\u0001e\u0015\t)G+\u0001\u0004=e>|GOP\u0005\u0003O>\u000ba\u0001\u0015:fI\u00164\u0017BA5k\u0005\u0019\u0019FO]5oO*\u0011qm\u0014\u0002\n!\u0006\u0014\u0018-\u001c#fg\u000e,2!\\A\u001b'\u0011!QJ\\9\u0011\u00059{\u0017B\u00019P\u0005\u001d\u0001&o\u001c3vGR\u0004\"A]<\u000f\u0005M,hBA2u\u0013\u0005\u0001\u0016B\u0001<P\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001_=\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Y|\u0015\u0001\u00028b[\u0016,\u0012\u0001Y\u0001\u0006]\u0006lW\rI\u0001\u0004I>\u001c\u0017\u0001\u00023pG\u0002\nq\u0002Z3gCVdGOV1mk\u0016\u001cFO]\u000b\u0003\u0003\u0007\u0001BATA\u0003A&\u0019\u0011qA(\u0003\r=\u0003H/[8o\u0003A!WMZ1vYR4\u0016\r\\;f'R\u0014\b%A\u0004jgZ\u000bG.\u001b3\u0002\u0011%\u001ch+\u00197jI\u0002\nABZ5oC2lU\r\u001e5pIN,\"!a\u0005\u0011\u00079\u000b)\"C\u0002\u0002\u0018=\u0013qAQ8pY\u0016\fg.A\u0007gS:\fG.T3uQ>$7\u000fI\u0001\fM&t\u0017\r\u001c$jK2$7/\u0001\u0007gS:\fGNR5fY\u0012\u001c\b%A\u0007jg\u0016C\b/\u001a:u!\u0006\u0014\u0018-\\\u0001\u000fSN,\u0005\u0010]3siB\u000b'/Y7!\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u0003O\ti#!\r\u000e\u0005\u0005%\"bAA\u0016\u001f\u00069!/\u001a4mK\u000e$\u0018\u0002BA\u0018\u0003S\u0011\u0001b\u00117bgN$\u0016m\u001a\t\u0005\u0003g\t)\u0004\u0004\u0001\u0005\u000f\u0005]BA1\u0001\u0002:\t\tA+\u0005\u0003\u0002<\u0005\u0005\u0003c\u0001(\u0002>%\u0019\u0011qH(\u0003\u000f9{G\u000f[5oOB\u0019a*a\u0011\n\u0007\u0005\u0015sJA\u0002B]f$\u0002#!\u0013\u0002R\u0005M\u0013QKA,\u00033\nY&!\u0018\u0015\t\u0005-\u0013q\n\t\u0006\u0003\u001b\"\u0011\u0011G\u0007\u0002\u0003!9\u00111\u0005\u000bA\u0004\u0005\u0015\u0002\"\u0002>\u0015\u0001\u0004\u0001\u0007\"B?\u0015\u0001\u0004\u0001\u0007\u0002C@\u0015!\u0003\u0005\r!a\u0001\t\u0011\u0005-A\u0003%AA\u0002\u0001D\u0011\"a\u0004\u0015!\u0003\u0005\r!a\u0005\t\u0013\u0005mA\u0003%AA\u0002\u0005M\u0001\"CA\u0010)A\u0005\t\u0019AA\n\u00035\u0001\u0018M]1n)f\u0004XMT1nK\u0006ia/\u00197vKRK\b/\u001a(b[\u0016\fQbZ3u)f\u0004Xm\u0015;sS:<Gc\u00011\u0002h!9\u0011\u0011N\fA\u0002\u0005-\u0014!A21\t\u00055\u0014Q\u000f\t\u0006C\u0006=\u00141O\u0005\u0004\u0003cR'!B\"mCN\u001c\b\u0003BA\u001a\u0003k\"A\"a\u001e\u0002h\u0005\u0005\t\u0011!B\u0001\u0003s\u00111a\u0018\u00132\u0003\u0011\u0019w\u000e]=\u0016\t\u0005u\u0014Q\u0011\u000b\u0011\u0003\u007f\nY)!$\u0002\u0010\u0006E\u00151SAK\u0003/#B!!!\u0002\bB)\u0011Q\n\u0003\u0002\u0004B!\u00111GAC\t\u001d\t9\u0004\u0007b\u0001\u0003sAq!a\t\u0019\u0001\b\tI\t\u0005\u0004\u0002(\u00055\u00121\u0011\u0005\bub\u0001\n\u00111\u0001a\u0011\u001di\b\u0004%AA\u0002\u0001D\u0001b \r\u0011\u0002\u0003\u0007\u00111\u0001\u0005\t\u0003\u0017A\u0002\u0013!a\u0001A\"I\u0011q\u0002\r\u0011\u0002\u0003\u0007\u00111\u0003\u0005\n\u00037A\u0002\u0013!a\u0001\u0003'A\u0011\"a\b\u0019!\u0003\u0005\r!a\u0005\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU!\u0011QTAZ+\t\tyJK\u0002a\u0003C[#!a)\u0011\t\u0005\u0015\u0016qV\u0007\u0003\u0003OSA!!+\u0002,\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003[{\u0015AC1o]>$\u0018\r^5p]&!\u0011\u0011WAT\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\b\u0003oI\"\u0019AA\u001d\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*B!!(\u0002:\u00129\u0011q\u0007\u000eC\u0002\u0005e\u0012AD2paf$C-\u001a4bk2$HeM\u000b\u0005\u0003\u007f\u000b\u0019-\u0006\u0002\u0002B*\"\u00111AAQ\t\u001d\t9d\u0007b\u0001\u0003s\tabY8qs\u0012\"WMZ1vYR$C'\u0006\u0003\u0002\u001e\u0006%GaBA\u001c9\t\u0007\u0011\u0011H\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00136+\u0011\ty-a5\u0016\u0005\u0005E'\u0006BA\n\u0003C#q!a\u000e\u001e\u0005\u0004\tI$\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001c\u0016\t\u0005=\u0017\u0011\u001c\u0003\b\u0003oq\"\u0019AA\u001d\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uI]*B!a4\u0002`\u00129\u0011qG\u0010C\u0002\u0005e\u0012!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002fB!\u0011q]Ay\u001b\t\tIO\u0003\u0003\u0002l\u00065\u0018\u0001\u00027b]\u001eT!!a<\u0002\t)\fg/Y\u0005\u0004S\u0006%\u0018\u0001\u00049s_\u0012,8\r^!sSRLXCAA|!\rq\u0015\u0011`\u0005\u0004\u0003w|%aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA!\u0005\u0003A\u0011Ba\u0001#\u0003\u0003\u0005\r!a>\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011I\u0001\u0005\u0004\u0003\f\tE\u0011\u0011I\u0007\u0003\u0005\u001bQ1Aa\u0004P\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005'\u0011iA\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\n\u00053A\u0011Ba\u0001%\u0003\u0003\u0005\r!!\u0011\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003K\u0014y\u0002C\u0005\u0003\u0004\u0015\n\t\u00111\u0001\u0002x\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002x\u0006AAo\\*ue&tw\r\u0006\u0002\u0002f\u00061Q-];bYN$B!a\u0005\u0003.!I!1\u0001\u0015\u0002\u0002\u0003\u0007\u0011\u0011I\u0001\n!\u0006\u0014\u0018-\u001c#fg\u000e\u00042!!\u0014+'\u0011QSJ!\u000e\u0011\t\t]\"QH\u0007\u0003\u0005sQAAa\u000f\u0002n\u0006\u0011\u0011n\\\u0005\u0004q\neBC\u0001B\u0019\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\u0011)E!\u0014\u0015!\t\u001d#1\u000bB+\u0005/\u0012IFa\u0017\u0003^\t}C\u0003\u0002B%\u0005\u001f\u0002R!!\u0014\u0005\u0005\u0017\u0002B!a\r\u0003N\u00119\u0011qG\u0017C\u0002\u0005e\u0002bBA\u0012[\u0001\u000f!\u0011\u000b\t\u0007\u0003O\tiCa\u0013\t\u000bil\u0003\u0019\u00011\t\u000bul\u0003\u0019\u00011\t\u0011}l\u0003\u0013!a\u0001\u0003\u0007A\u0001\"a\u0003.!\u0003\u0005\r\u0001\u0019\u0005\n\u0003\u001fi\u0003\u0013!a\u0001\u0003'A\u0011\"a\u0007.!\u0003\u0005\r!a\u0005\t\u0013\u0005}Q\u0006%AA\u0002\u0005M\u0011aD1qa2LH\u0005Z3gCVdG\u000fJ\u001a\u0016\t\u0005}&Q\r\u0003\b\u0003oq#\u0019AA\u001d\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\"T\u0003BAO\u0005W\"q!a\u000e0\u0005\u0004\tI$A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00136+\u0011\tyM!\u001d\u0005\u000f\u0005]\u0002G1\u0001\u0002:\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$c'\u0006\u0003\u0002P\n]DaBA\u001cc\t\u0007\u0011\u0011H\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%oU!\u0011q\u001aB?\t\u001d\t9D\rb\u0001\u0003s\tq!\u001e8baBd\u00170\u0006\u0003\u0003\u0004\nUE\u0003\u0002BC\u0005\u001b\u0003RATA\u0003\u0005\u000f\u0003bB\u0014BEA\u0002\f\u0019\u0001YA\n\u0003'\t\u0019\"C\u0002\u0003\f>\u0013a\u0001V;qY\u0016<\u0004\"\u0003BHg\u0005\u0005\t\u0019\u0001BI\u0003\rAH\u0005\r\t\u0006\u0003\u001b\"!1\u0013\t\u0005\u0003g\u0011)\nB\u0004\u00028M\u0012\r!!\u000f\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134+\u0011\tyLa'\u0005\u000f\u0005]BG1\u0001\u0002:\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIQ*B!!(\u0003\"\u00129\u0011qG\u001bC\u0002\u0005e\u0012a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$S'\u0006\u0003\u0002P\n\u001dFaBA\u001cm\t\u0007\u0011\u0011H\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001c\u0016\t\u0005='Q\u0016\u0003\b\u0003o9$\u0019AA\u001d\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%oU!\u0011q\u001aBZ\t\u001d\t9\u0004\u000fb\u0001\u0003s\tAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"A!/\u0011\t\u0005\u001d(1X\u0005\u0005\u0005{\u000bIO\u0001\u0004PE*,7\r^\u0001\u0011O\u0016t\u0007*Y:QCJ\fW\u000e\u0016:bSR$2\u0001\u0019Bb\u0011\u0019\u0001%\b1\u0001\u0003FB\"!q\u0019Bf!\u0015\ti\u0005\u0002Be!\u0011\t\u0019Da3\u0005\u0019\t5'1YA\u0001\u0002\u0003\u0015\t!!\u000f\u0003\u0007}##'A\bhK:\u001c\u0006.\u0019:fIB\u000b'/Y7t)\r\u0001'1\u001b\u0005\b\u0005+\\\u0004\u0019\u0001Bl\u0003\u0019\u0001\u0018M]1ngB)!O!7\u0003^&\u0019!1\\=\u0003\u0007M+\u0017\u000f\r\u0003\u0003`\n\r\b#BA'\t\t\u0005\b\u0003BA\u001a\u0005G$AB!:\u0003T\u0006\u0005\t\u0011!B\u0001\u0003s\u00111a\u0018\u00134\u0001"
)
public final class SharedParamsCodeGen {
   public static void main(final String[] args) {
      SharedParamsCodeGen$.MODULE$.main(args);
   }

   private static class ParamDesc implements Product, Serializable {
      private final String name;
      private final String doc;
      private final Option defaultValueStr;
      private final String isValid;
      private final boolean finalMethods;
      private final boolean finalFields;
      private final boolean isExpertParam;
      private final ClassTag evidence$1;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String name() {
         return this.name;
      }

      public String doc() {
         return this.doc;
      }

      public Option defaultValueStr() {
         return this.defaultValueStr;
      }

      public String isValid() {
         return this.isValid;
      }

      public boolean finalMethods() {
         return this.finalMethods;
      }

      public boolean finalFields() {
         return this.finalFields;
      }

      public boolean isExpertParam() {
         return this.isExpertParam;
      }

      public String paramTypeName() {
         Class c = ((ClassTag).MODULE$.implicitly(this.evidence$1)).runtimeClass();
         Class var4 = Integer.TYPE;
         if (c == null) {
            if (var4 == null) {
               return "IntParam";
            }
         } else if (c.equals(var4)) {
            return "IntParam";
         }

         Class var5 = Long.TYPE;
         if (c == null) {
            if (var5 == null) {
               return "LongParam";
            }
         } else if (c.equals(var5)) {
            return "LongParam";
         }

         Class var6 = Float.TYPE;
         if (c == null) {
            if (var6 == null) {
               return "FloatParam";
            }
         } else if (c.equals(var6)) {
            return "FloatParam";
         }

         Class var7 = Double.TYPE;
         if (c == null) {
            if (var7 == null) {
               return "DoubleParam";
            }
         } else if (c.equals(var7)) {
            return "DoubleParam";
         }

         Class var8 = Boolean.TYPE;
         if (c == null) {
            if (var8 == null) {
               return "BooleanParam";
            }
         } else if (c.equals(var8)) {
            return "BooleanParam";
         }

         if (c.isArray()) {
            Class var10000 = c.getComponentType();
            Class var9 = String.class;
            if (var10000 == null) {
               if (var9 == null) {
                  return "StringArrayParam";
               }
            } else if (var10000.equals(var9)) {
               return "StringArrayParam";
            }
         }

         if (c.isArray()) {
            Class var11 = c.getComponentType();
            Class var10 = Double.TYPE;
            if (var11 == null) {
               if (var10 == null) {
                  return "DoubleArrayParam";
               }
            } else if (var11.equals(var10)) {
               return "DoubleArrayParam";
            }
         }

         String var12 = this.getTypeString(c);
         return "Param[" + var12 + "]";
      }

      public String valueTypeName() {
         Class c = ((ClassTag).MODULE$.implicitly(this.evidence$1)).runtimeClass();
         return this.getTypeString(c);
      }

      private String getTypeString(final Class c) {
         Class var4 = Integer.TYPE;
         if (c == null) {
            if (var4 == null) {
               return "Int";
            }
         } else if (c.equals(var4)) {
            return "Int";
         }

         Class var5 = Long.TYPE;
         if (c == null) {
            if (var5 == null) {
               return "Long";
            }
         } else if (c.equals(var5)) {
            return "Long";
         }

         Class var6 = Float.TYPE;
         if (c == null) {
            if (var6 == null) {
               return "Float";
            }
         } else if (c.equals(var6)) {
            return "Float";
         }

         Class var7 = Double.TYPE;
         if (c == null) {
            if (var7 == null) {
               return "Double";
            }
         } else if (c.equals(var7)) {
            return "Double";
         }

         Class var8 = Boolean.TYPE;
         if (c == null) {
            if (var8 == null) {
               return "Boolean";
            }
         } else if (c.equals(var8)) {
            return "Boolean";
         }

         Class var9 = String.class;
         if (c == null) {
            if (var9 == null) {
               return "String";
            }
         } else if (c.equals(var9)) {
            return "String";
         }

         if (c.isArray()) {
            String var10000 = this.getTypeString(c.getComponentType());
            return "Array[" + var10000 + "]";
         } else {
            throw new MatchError(c);
         }
      }

      public ParamDesc copy(final String name, final String doc, final Option defaultValueStr, final String isValid, final boolean finalMethods, final boolean finalFields, final boolean isExpertParam, final ClassTag evidence$1) {
         return new ParamDesc(name, doc, defaultValueStr, isValid, finalMethods, finalFields, isExpertParam, evidence$1);
      }

      public String copy$default$1() {
         return this.name();
      }

      public String copy$default$2() {
         return this.doc();
      }

      public Option copy$default$3() {
         return this.defaultValueStr();
      }

      public String copy$default$4() {
         return this.isValid();
      }

      public boolean copy$default$5() {
         return this.finalMethods();
      }

      public boolean copy$default$6() {
         return this.finalFields();
      }

      public boolean copy$default$7() {
         return this.isExpertParam();
      }

      public String productPrefix() {
         return "ParamDesc";
      }

      public int productArity() {
         return 7;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.name();
            }
            case 1 -> {
               return this.doc();
            }
            case 2 -> {
               return this.defaultValueStr();
            }
            case 3 -> {
               return this.isValid();
            }
            case 4 -> {
               return BoxesRunTime.boxToBoolean(this.finalMethods());
            }
            case 5 -> {
               return BoxesRunTime.boxToBoolean(this.finalFields());
            }
            case 6 -> {
               return BoxesRunTime.boxToBoolean(this.isExpertParam());
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ParamDesc;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "name";
            }
            case 1 -> {
               return "doc";
            }
            case 2 -> {
               return "defaultValueStr";
            }
            case 3 -> {
               return "isValid";
            }
            case 4 -> {
               return "finalMethods";
            }
            case 5 -> {
               return "finalFields";
            }
            case 6 -> {
               return "isExpertParam";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.name()));
         var1 = Statics.mix(var1, Statics.anyHash(this.doc()));
         var1 = Statics.mix(var1, Statics.anyHash(this.defaultValueStr()));
         var1 = Statics.mix(var1, Statics.anyHash(this.isValid()));
         var1 = Statics.mix(var1, this.finalMethods() ? 1231 : 1237);
         var1 = Statics.mix(var1, this.finalFields() ? 1231 : 1237);
         var1 = Statics.mix(var1, this.isExpertParam() ? 1231 : 1237);
         return Statics.finalizeHash(var1, 7);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var12;
         if (this != x$1) {
            label83: {
               if (x$1 instanceof ParamDesc) {
                  ParamDesc var4 = (ParamDesc)x$1;
                  if (this.finalMethods() == var4.finalMethods() && this.finalFields() == var4.finalFields() && this.isExpertParam() == var4.isExpertParam()) {
                     label76: {
                        String var10000 = this.name();
                        String var5 = var4.name();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label76;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label76;
                        }

                        var10000 = this.doc();
                        String var6 = var4.doc();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label76;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label76;
                        }

                        Option var10 = this.defaultValueStr();
                        Option var7 = var4.defaultValueStr();
                        if (var10 == null) {
                           if (var7 != null) {
                              break label76;
                           }
                        } else if (!var10.equals(var7)) {
                           break label76;
                        }

                        String var11 = this.isValid();
                        String var8 = var4.isValid();
                        if (var11 == null) {
                           if (var8 != null) {
                              break label76;
                           }
                        } else if (!var11.equals(var8)) {
                           break label76;
                        }

                        if (var4.canEqual(this)) {
                           break label83;
                        }
                     }
                  }
               }

               var12 = false;
               return var12;
            }
         }

         var12 = true;
         return var12;
      }

      public ParamDesc(final String name, final String doc, final Option defaultValueStr, final String isValid, final boolean finalMethods, final boolean finalFields, final boolean isExpertParam, final ClassTag evidence$1) {
         this.name = name;
         this.doc = doc;
         this.defaultValueStr = defaultValueStr;
         this.isValid = isValid;
         this.finalMethods = finalMethods;
         this.finalFields = finalFields;
         this.isExpertParam = isExpertParam;
         this.evidence$1 = evidence$1;
         Product.$init$(this);
         .MODULE$.require(name.matches("[a-z][a-zA-Z0-9]*"), () -> "Param name " + this.name() + " is invalid.");
         .MODULE$.require(scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(doc)));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class ParamDesc$ implements Serializable {
      public static final ParamDesc$ MODULE$ = new ParamDesc$();

      public Option $lessinit$greater$default$3() {
         return scala.None..MODULE$;
      }

      public String $lessinit$greater$default$4() {
         return "";
      }

      public boolean $lessinit$greater$default$5() {
         return true;
      }

      public boolean $lessinit$greater$default$6() {
         return true;
      }

      public boolean $lessinit$greater$default$7() {
         return false;
      }

      public final String toString() {
         return "ParamDesc";
      }

      public ParamDesc apply(final String name, final String doc, final Option defaultValueStr, final String isValid, final boolean finalMethods, final boolean finalFields, final boolean isExpertParam, final ClassTag evidence$1) {
         return new ParamDesc(name, doc, defaultValueStr, isValid, finalMethods, finalFields, isExpertParam, evidence$1);
      }

      public Option apply$default$3() {
         return scala.None..MODULE$;
      }

      public String apply$default$4() {
         return "";
      }

      public boolean apply$default$5() {
         return true;
      }

      public boolean apply$default$6() {
         return true;
      }

      public boolean apply$default$7() {
         return false;
      }

      public Option unapply(final ParamDesc x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple7(x$0.name(), x$0.doc(), x$0.defaultValueStr(), x$0.isValid(), BoxesRunTime.boxToBoolean(x$0.finalMethods()), BoxesRunTime.boxToBoolean(x$0.finalFields()), BoxesRunTime.boxToBoolean(x$0.isExpertParam()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ParamDesc$.class);
      }

      public ParamDesc$() {
      }
   }
}
