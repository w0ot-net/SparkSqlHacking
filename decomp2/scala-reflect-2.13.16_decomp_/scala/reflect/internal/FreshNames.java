package scala.reflect.internal;

import java.util.regex.Pattern;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.LinearSeqOps;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.FreshNameCreator;
import scala.reflect.internal.util.FreshNameCreator$;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001da\u0001C\t\u0013!\u0003\r\t!\u0007=\t\u000by\u0001A\u0011A\u0010\t\u000f\r\u0002!\u0019!C\u0001I!)1\u0006\u0001D\u0001I!)A\u0006\u0001C\u0001[!9A\tAI\u0001\n\u0003)\u0005\"\u0002)\u0001\t\u0003\tf\u0001\u0002-\u0001\u0001eC\u0001BW\u0004\u0003\u0002\u0003\u0006I!\u000f\u0005\u00067\u001e!\t\u0001\u0018\u0005\b?\u001e\u0011\r\u0011\"\u0001a\u0011\u0019Aw\u0001)A\u0005C\")\u0011n\u0002C\u0001U\u001e91\u000fAA\u0001\u0012\u0003!ha\u0002-\u0001\u0003\u0003E\t!\u001e\u0005\u00067:!\tA\u001e\u0005\bo:\t\n\u0011\"\u0001F\u0005)1%/Z:i\u001d\u0006lWm\u001d\u0006\u0003'Q\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003+Y\tqA]3gY\u0016\u001cGOC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u000e\u0011\u0005maR\"\u0001\f\n\u0005u1\"AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002AA\u00111$I\u0005\u0003EY\u0011A!\u00168ji\u00061r\r\\8cC24%/Z:i\u001d\u0006lWm\u0011:fCR|'/F\u0001&!\t1\u0013&D\u0001(\u0015\tA##\u0001\u0003vi&d\u0017B\u0001\u0016(\u0005A1%/Z:i\u001d\u0006lWm\u0011:fCR|'/A\fdkJ\u0014XM\u001c;Ge\u0016\u001c\bNT1nK\u000e\u0013X-\u0019;pe\u0006iaM]3tQR+'/\u001c(b[\u0016$\"AL\u001c\u0015\u0005=*\u0004C\u0001\u00192\u001b\u0005\u0001\u0011B\u0001\u001a4\u0005!!VM]7OC6,\u0017B\u0001\u001b\u0013\u0005\u0015q\u0015-\\3t\u0011\u00151D\u0001q\u0001&\u0003\u001d\u0019'/Z1u_JDq\u0001\u000f\u0003\u0011\u0002\u0003\u0007\u0011(\u0001\u0004qe\u00164\u0017\u000e\u001f\t\u0003u\u0005s!aO \u0011\u0005q2R\"A\u001f\u000b\u0005yB\u0012A\u0002\u001fs_>$h(\u0003\u0002A-\u00051\u0001K]3eK\u001aL!AQ\"\u0003\rM#(/\u001b8h\u0015\t\u0001e#A\fge\u0016\u001c\b\u000eV3s[:\u000bW.\u001a\u0013eK\u001a\fW\u000f\u001c;%cU\taI\u000b\u0002:\u000f.\n\u0001\n\u0005\u0002J\u001d6\t!J\u0003\u0002L\u0019\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\u001bZ\t!\"\u00198o_R\fG/[8o\u0013\ty%JA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQB\u001a:fg\"$\u0016\u0010]3OC6,GC\u0001*X)\t\u0019f\u000b\u0005\u00021)&\u0011Qk\r\u0002\t)f\u0004XMT1nK\")aG\u0002a\u0002K!)\u0001H\u0002a\u0001s\t\u0011bI]3tQ:\u000bW.Z#yiJ\f7\r^8s'\t9!$A\u0007de\u0016\fGo\u001c:Qe\u00164\u0017\u000e_\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005us\u0006C\u0001\u0019\b\u0011\u001dQ\u0016\u0002%AA\u0002e\nAB\u001a:fg\"d\u0017PT1nK\u0012,\u0012!\u0019\t\u0003E\u001al\u0011a\u0019\u0006\u0003I\u0016\f\u0001\"\\1uG\"Lgn\u001a\u0006\u0003QYI!aZ2\u0003\u000bI+w-\u001a=\u0002\u001b\u0019\u0014Xm\u001d5ms:\u000bW.\u001a3!\u0003\u001d)h.\u00199qYf$\"a\u001b8\u0011\u0007ma\u0017(\u0003\u0002n-\t1q\n\u001d;j_:DQa\u001c\u0007A\u0002A\fAA\\1nKB\u0011\u0001']\u0005\u0003eN\u0012AAT1nK\u0006\u0011bI]3tQ:\u000bW.Z#yiJ\f7\r^8s!\t\u0001db\u0005\u0002\u000f5Q\tA/A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%\r\n\u0004snlh\u0001\u0002>\u0001\u0001a\u0014A\u0002\u0010:fM&tW-\\3oiz\u0002\"\u0001 \u0001\u000e\u0003I\u0011BA`@\u0002\u0002\u0019!!\u0010\u0001\u0001~!\ta8\u0007E\u0002}\u0003\u0007I1!!\u0002\u0013\u0005!\u0019F\u000f\u001a(b[\u0016\u001c\b"
)
public interface FreshNames {
   FreshNameExtractor$ FreshNameExtractor();

   void scala$reflect$internal$FreshNames$_setter_$globalFreshNameCreator_$eq(final FreshNameCreator x$1);

   FreshNameCreator globalFreshNameCreator();

   FreshNameCreator currentFreshNameCreator();

   // $FF: synthetic method
   static Names.TermName freshTermName$(final FreshNames $this, final String prefix, final FreshNameCreator creator) {
      return $this.freshTermName(prefix, creator);
   }

   default Names.TermName freshTermName(final String prefix, final FreshNameCreator creator) {
      return ((Names)this).newTermName(creator.newName(prefix));
   }

   // $FF: synthetic method
   static String freshTermName$default$1$(final FreshNames $this) {
      return $this.freshTermName$default$1();
   }

   default String freshTermName$default$1() {
      return ((StdNames)this).nme().FRESH_TERM_NAME_PREFIX();
   }

   // $FF: synthetic method
   static Names.TypeName freshTypeName$(final FreshNames $this, final String prefix, final FreshNameCreator creator) {
      return $this.freshTypeName(prefix, creator);
   }

   default Names.TypeName freshTypeName(final String prefix, final FreshNameCreator creator) {
      return ((Names)this).newTypeName(creator.newName(prefix));
   }

   static void $init$(final FreshNames $this) {
      FreshNameCreator$ var10003 = FreshNameCreator$.MODULE$;
      $this.scala$reflect$internal$FreshNames$_setter_$globalFreshNameCreator_$eq(new FreshNameCreator(""));
   }

   public class FreshNameExtractor$ {
      public String $lessinit$greater$default$1() {
         return "";
      }
   }

   public class FreshNameExtractor {
      private final Regex freshlyNamed;
      // $FF: synthetic field
      public final FreshNames $outer;

      public Regex freshlyNamed() {
         return this.freshlyNamed;
      }

      public Option unapply(final Names.Name name) {
         String var2 = name.toString();
         if (var2 != null) {
            Option var3 = this.freshlyNamed().unapplySeq(var2);
            if (!var3.isEmpty() && var3.get() != null && ((List)var3.get()).lengthCompare(1) == 0) {
               String prefix = (String)((LinearSeqOps)var3.get()).apply(0);
               return new Some(prefix);
            }
         }

         return .MODULE$;
      }

      // $FF: synthetic method
      public FreshNames scala$reflect$internal$FreshNames$FreshNameExtractor$$$outer() {
         return this.$outer;
      }

      public FreshNameExtractor(final String creatorPrefix) {
         if (FreshNames.this == null) {
            throw null;
         } else {
            this.$outer = FreshNames.this;
            super();
            String var7;
            if (!creatorPrefix.isEmpty()) {
               Regex var10001 = scala.util.matching.Regex..MODULE$;
               var7 = Pattern.quote(creatorPrefix);
            } else {
               var7 = "";
            }

            String pre = var7;
            String augmentString_x = (new StringBuilder(8)).append(pre).append("(.*?)\\d*").toString();
            var7 = augmentString_x;
            Object var6 = null;
            String r$extension_$this = var7;
            this.freshlyNamed = new Regex(r$extension_$this, scala.collection.immutable.Nil..MODULE$);
         }
      }
   }
}
