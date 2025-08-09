package scala.util.hashing;

import java.lang.reflect.Array;
import scala.Product;
import scala.collection.IndexedSeq;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime$;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tEh!B\u001e=\u0001q\u0012\u0005\"B$\u0001\t\u0003I\u0005\"\u0002'\u0001\t\u000bi\u0005\"B+\u0001\t\u000b1\u0006\"B-\u0001\t\u000bQ\u0006\"\u00020\u0001\t\u001by\u0006BB1\u0001\t\u0003\u0001%\rC\u0003j\u0001\u0011\u0015!\u000eC\u0004v\u0001E\u0005IQ\u0001<\t\u000f\u0005\r\u0001\u0001\"\u0002\u0002\u0006!9\u00111\u0005\u0001\u0005\u0006\u0005\u0015\u0002bBA!\u0001\u0011\u0015\u00111\t\u0005\b\u0003\u0013\u0002AQAA&\u0011\u001d\t\u0019\b\u0001C\u0003\u0003kBq!!\"\u0001\t\u000b\t9\tC\u0004\u0002\u0016\u0002!)!a&\t\u000f\u0005%\u0006\u0001\"\u0002\u0002,\u001e9\u0011Q\u0019\u001f\t\u0002\u0005\u001dgAB\u001e=\u0011\u0003\tI\r\u0003\u0004H%\u0011\u0005\u00111\u001a\u0005\n\u0003\u001b\u0014\"\u0019!C\u0003\u0003\u001fD\u0001\"!6\u0013A\u00035\u0011\u0011\u001b\u0005\n\u0003/\u0014\"\u0019!C\u0003\u00033D\u0001\"a8\u0013A\u00035\u00111\u001c\u0005\n\u0003C\u0014\"\u0019!C\u0003\u0003GD\u0001\"!;\u0013A\u00035\u0011Q\u001d\u0005\n\u0003W\u0014\"\u0019!C\u0003\u0003[D\u0001\"a=\u0013A\u00035\u0011q\u001e\u0005\n\u0003k\u0014\"\u0019!C\u0003\u0003oD\u0001\"!@\u0013A\u00035\u0011\u0011 \u0005\n\u0003\u007f\u0014\"\u0019!C\u0003\u0005\u0003AqAa\u0001\u0013A\u00035a\nC\u0005\u0003\u0006I\u0011\r\u0011\"\u0002\u0003\u0002!9!q\u0001\n!\u0002\u001bq\u0005\"\u0003B\u0005%\t\u0007IQ\u0001B\u0001\u0011\u001d\u0011YA\u0005Q\u0001\u000e9Cq!!\u0013\u0013\t\u0003\u0011i\u0001C\u0004\u0002\u0006J!\tAa\u0007\t\u000f\u0005\u0005#\u0003\"\u0001\u0003 !1\u0011N\u0005C\u0001\u0005GAq!a\u0001\u0013\t\u0003\u00119\u0003C\u0004\u0002$I!\tAa\u000b\t\u000f\u0005M$\u0003\"\u0001\u00030!A!q\u0007\n\u0005\u0002\u0001\u0013I\u0004C\u0004b%\u0011\u0005\u0001Ia\u0012\t\u000f\t5#\u0003\"\u0001\u0003P!9!\u0011\r\n\u0005\u0002\t\r\u0004B\u0003B>%\t\u0007I\u0011\u0001!\u0003\u0002!9!Q\u0010\n!\u0002\u0013q\u0005b\u0002B@%\u0011\u0005!\u0011\u0011\u0004\u0007\u0005'\u0013\u0002A!&\t\r\u001d\u0013D\u0011\u0001BT\u0011\u0019\u0011&\u0007\"\u0001\u0003.\"9!\u0011\u0017\n\u0005\u0002\tM\u0006b\u0002B`%\u0011\u0005!\u0011\u0019\u0005\b\u0005\u0017\u0014B\u0011\u0001Bg\u0011\u001d\u0011)N\u0005C\u0001\u0005/DqAa8\u0013\t\u0003\u0011\t\u000fC\u0004\u0003jJ!\tAa;\u0003\u00175+(/\\;s\u0011\u0006\u001c\bn\r\u0006\u0003{y\nq\u0001[1tQ&twM\u0003\u0002@\u0001\u0006!Q\u000f^5m\u0015\u0005\t\u0015!B:dC2\f7C\u0001\u0001D!\t!U)D\u0001A\u0013\t1\u0005I\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t!\n\u0005\u0002L\u00015\tA(A\u0002nSb$2AT)T!\t!u*\u0003\u0002Q\u0001\n\u0019\u0011J\u001c;\t\u000bI\u0013\u0001\u0019\u0001(\u0002\t!\f7\u000f\u001b\u0005\u0006)\n\u0001\rAT\u0001\u0005I\u0006$\u0018-A\u0004nSbd\u0015m\u001d;\u0015\u00079;\u0006\fC\u0003S\u0007\u0001\u0007a\nC\u0003U\u0007\u0001\u0007a*\u0001\u0007gS:\fG.\u001b>f\u0011\u0006\u001c\b\u000eF\u0002O7rCQA\u0015\u0003A\u00029CQ!\u0018\u0003A\u00029\u000ba\u0001\\3oORD\u0017!C1wC2\fgn\u00195f)\tq\u0005\rC\u0003S\u000b\u0001\u0007a*\u0001\u0006ukBdWM\r%bg\"$BAT2fO\")AM\u0002a\u0001\u001d\u0006\t\u0001\u0010C\u0003g\r\u0001\u0007a*A\u0001z\u0011\u0015Ag\u00011\u0001O\u0003\u0011\u0019X-\u001a3\u0002\u0017A\u0014x\u000eZ;di\"\u000b7\u000f\u001b\u000b\u0005\u001d.|\u0007\u000fC\u0003e\u000f\u0001\u0007A\u000e\u0005\u0002E[&\u0011a\u000e\u0011\u0002\b!J|G-^2u\u0011\u0015Aw\u00011\u0001O\u0011\u001d\tx\u0001%AA\u0002I\fA\"[4o_J,\u0007K]3gSb\u0004\"\u0001R:\n\u0005Q\u0004%a\u0002\"p_2,\u0017M\\\u0001\u0016aJ|G-^2u\u0011\u0006\u001c\b\u000e\n3fM\u0006,H\u000e\u001e\u00134+\u00059(F\u0001:yW\u0005I\bC\u0001>\u0000\u001b\u0005Y(B\u0001?~\u0003%)hn\u00195fG.,GM\u0003\u0002\u007f\u0001\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0007\u0005\u00051PA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f!b\u001d;sS:<\u0007*Y:i)\u0015q\u0015qAA\u0011\u0011\u001d\tI!\u0003a\u0001\u0003\u0017\t1a\u001d;s!\u0011\ti!a\u0007\u000f\t\u0005=\u0011q\u0003\t\u0004\u0003#\u0001UBAA\n\u0015\r\t)\u0002S\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005e\u0001)\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003;\tyB\u0001\u0004TiJLgn\u001a\u0006\u0004\u00033\u0001\u0005\"\u00025\n\u0001\u0004q\u0015!D;o_J$WM]3e\u0011\u0006\u001c\b\u000eF\u0003O\u0003O\ty\u0004C\u0004\u0002*)\u0001\r!a\u000b\u0002\u0005a\u001c\bCBA\u0017\u0003g\tIDD\u0002E\u0003_I1!!\rA\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u000e\u00028\ta\u0011\n^3sC\ndWm\u00148dK*\u0019\u0011\u0011\u0007!\u0011\u0007\u0011\u000bY$C\u0002\u0002>\u0001\u00131!\u00118z\u0011\u0015A'\u00021\u0001O\u0003-y'\u000fZ3sK\u0012D\u0015m\u001d5\u0015\u000b9\u000b)%a\u0012\t\u000f\u0005%2\u00021\u0001\u0002,!)\u0001n\u0003a\u0001\u001d\u0006I\u0011M\u001d:bs\"\u000b7\u000f[\u000b\u0005\u0003\u001b\ni\u0006F\u0003O\u0003\u001f\n\t\bC\u0004\u0002R1\u0001\r!a\u0015\u0002\u0003\u0005\u0004R\u0001RA+\u00033J1!a\u0016A\u0005\u0015\t%O]1z!\u0011\tY&!\u0018\r\u0001\u0011Y\u0011q\f\u0007!\u0002\u0003\u0005)\u0019AA1\u0005\u0005!\u0016\u0003BA2\u0003s\u00012\u0001RA3\u0013\r\t9\u0007\u0011\u0002\b\u001d>$\b.\u001b8hQ\u0011\ti&a\u001b\u0011\u0007\u0011\u000bi'C\u0002\u0002p\u0001\u00131b\u001d9fG&\fG.\u001b>fI\")\u0001\u000e\u0004a\u0001\u001d\u0006I!/\u00198hK\"\u000b7\u000f\u001b\u000b\n\u001d\u0006]\u00141PA@\u0003\u0007Ca!!\u001f\u000e\u0001\u0004q\u0015!B:uCJ$\bBBA?\u001b\u0001\u0007a*\u0001\u0003ti\u0016\u0004\bBBAA\u001b\u0001\u0007a*\u0001\u0003mCN$\b\"\u00025\u000e\u0001\u0004q\u0015!\u00032zi\u0016\u001c\b*Y:i)\u0015q\u0015\u0011RAJ\u0011\u0019!f\u00021\u0001\u0002\fB)A)!\u0016\u0002\u000eB\u0019A)a$\n\u0007\u0005E\u0005I\u0001\u0003CsR,\u0007\"\u00025\u000f\u0001\u0004q\u0015AD5oI\u0016DX\rZ*fc\"\u000b7\u000f\u001b\u000b\u0006\u001d\u0006e\u0015q\u0015\u0005\b\u0003#z\u0001\u0019AAN!\u0019\ti*a)\u0002:5\u0011\u0011q\u0014\u0006\u0004\u0003C\u0003\u0015AC2pY2,7\r^5p]&!\u0011QUAP\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\u0005\u0006Q>\u0001\rAT\u0001\tY&\u001cH\u000fS1tQR)a*!,\u0002D\"9\u0011\u0011\u0006\tA\u0002\u0005=\u0006\u0007BAY\u0003\u007f\u0003b!a-\u0002:\u0006uVBAA[\u0015\u0011\t9,a(\u0002\u0013%lW.\u001e;bE2,\u0017\u0002BA^\u0003k\u0013A\u0001T5tiB!\u00111LA`\t1\t\t-!,\u0002\u0002\u0003\u0005)\u0011AA1\u0005\ryF%\r\u0005\u0006QB\u0001\rAT\u0001\f\u001bV\u0014X.\u001e:ICND7\u0007\u0005\u0002L%M\u0011!C\u0013\u000b\u0003\u0003\u000f\f\u0011\"\u0019:sCf\u001cV-\u001a3\u0016\u0005\u0005EwBAAj;\u0011atAS1\u0002\u0015\u0005\u0014(/Y=TK\u0016$\u0007%\u0001\u0006tiJLgnZ*fK\u0012,\"!a7\u0010\u0005\u0005uW\u0004B|K\u0000J\u000f1b\u001d;sS:<7+Z3eA\u0005Y\u0001O]8ek\u000e$8+Z3e+\t\t)o\u0004\u0002\u0002hv!!Z@^?\u00041\u0001(o\u001c3vGR\u001cV-\u001a3!\u00035\u0019\u00180\\7fiJL7mU3fIV\u0011\u0011q^\b\u0003\u0003clB!.Jx^\u0007q1/_7nKR\u0014\u0018nY*fK\u0012\u0004\u0013a\u0004;sCZ,'o]1cY\u0016\u001cV-\u001a3\u0016\u0005\u0005exBAA~;\u00119/hc\u000b\u0002!Q\u0014\u0018M^3sg\u0006\u0014G.Z*fK\u0012\u0004\u0013aB:fcN+W\rZ\u000b\u0002\u001d\u0006A1/Z9TK\u0016$\u0007%A\u0004nCB\u001cV-\u001a3\u0002\u00115\f\u0007oU3fI\u0002\nqa]3u'\u0016,G-\u0001\u0005tKR\u001cV-\u001a3!+\u0011\u0011yAa\u0006\u0015\u00079\u0013\t\u0002C\u0004\u0002R\u0011\u0002\rAa\u0005\u0011\u000b\u0011\u000b)F!\u0006\u0011\t\u0005m#q\u0003\u0003\f\u0003?\"\u0003\u0015!A\u0001\u0006\u0004\t\t\u0007\u000b\u0003\u0003\u0018\u0005-Dc\u0001(\u0003\u001e!1A+\na\u0001\u0003\u0017#2A\u0014B\u0011\u0011\u001d\tIC\na\u0001\u0003W!2A\u0014B\u0013\u0011\u0015!w\u00051\u0001m)\rq%\u0011\u0006\u0005\u0007I\"\u0002\r!a\u0003\u0015\u00079\u0013i\u0003C\u0004\u0002*%\u0002\r!a\u000b\u0015\u000f9\u0013\tDa\r\u00036!1\u0011\u0011\u0010\u0016A\u00029Ca!! +\u0001\u0004q\u0005BBAAU\u0001\u0007a*\u0001\u0007beJ\f\u0017pU3r\u0011\u0006\u001c\b.\u0006\u0003\u0003<\t\rCc\u0001(\u0003>!9\u0011\u0011K\u0016A\u0002\t}\u0002#\u0002#\u0002V\t\u0005\u0003\u0003BA.\u0005\u0007\"1\"a\u0018,A\u0003\u0005\tQ1\u0001\u0002b!\"!1IA6)\u0015q%\u0011\nB&\u0011\u0019!G\u00061\u0001\u0002:!1a\r\fa\u0001\u0003s\tqa]3r\u0011\u0006\u001c\b\u000eF\u0002O\u0005#Bq!!\u000b.\u0001\u0004\u0011\u0019\u0006\r\u0003\u0003V\tu\u0003CBAO\u0005/\u0012Y&\u0003\u0003\u0003Z\u0005}%aA*fcB!\u00111\fB/\t1\u0011yF!\u0015\u0002\u0002\u0003\u0005)\u0011AA1\u0005\ryFEM\u0001\b[\u0006\u0004\b*Y:i)\rq%Q\r\u0005\b\u0003Sq\u0003\u0019\u0001B4a\u0019\u0011IG!\u001d\u0003xAA\u0011Q\u0014B6\u0005_\u0012)(\u0003\u0003\u0003n\u0005}%aA'baB!\u00111\fB9\t1\u0011\u0019H!\u001a\u0002\u0002\u0003\u0005)\u0011AA1\u0005\ryFe\r\t\u0005\u00037\u00129\b\u0002\u0007\u0003z\t\u0015\u0014\u0011!A\u0001\u0006\u0003\t\tGA\u0002`IQ\nA\"Z7qifl\u0015\r\u001d%bg\"\fQ\"Z7qifl\u0015\r\u001d%bg\"\u0004\u0013aB:fi\"\u000b7\u000f\u001b\u000b\u0004\u001d\n\r\u0005bBA\u0015c\u0001\u0007!Q\u0011\u0019\u0005\u0005\u000f\u0013y\t\u0005\u0004\u0002\u001e\n%%QR\u0005\u0005\u0005\u0017\u000byJA\u0002TKR\u0004B!a\u0017\u0003\u0010\u0012a!\u0011\u0013BB\u0003\u0003\u0005\tQ!\u0001\u0002b\t\u0019q\fJ\u001b\u0003\u0019\u0005\u0013(/Y=ICND\u0017N\\4\u0016\t\t]%1U\n\u0005e\r\u0013I\nE\u0003L\u00057\u0013y*C\u0002\u0003\u001er\u0012q\u0001S1tQ&tw\rE\u0003E\u0003+\u0012\t\u000b\u0005\u0003\u0002\\\t\rFaCA0e\u0001\u0006\t\u0011!b\u0001\u0003CBCAa)\u0002lQ\u0011!\u0011\u0016\t\u0006\u0005W\u0013$\u0011U\u0007\u0002%Q\u0019aJa,\t\u000f\u0005EC\u00071\u0001\u0003 \u0006a\u0011M\u001d:bs\"\u000b7\u000f[5oOV!!Q\u0017B^+\t\u00119\fE\u0003\u0003,J\u0012I\f\u0005\u0003\u0002\\\tmFaCA0k\u0001\u0006\t\u0011!b\u0001\u0003CBCAa/\u0002l\u0005a!-\u001f;fg\"\u000b7\u000f[5oOV\u0011!1\u0019\n\u0006\u0005\u000b\u001c%\u0011\u001a\u0004\u0007\u0005\u000f4\u0004Aa1\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u000b-\u0013Y*a#\u0002\u001d=\u0014H-\u001a:fI\"\u000b7\u000f[5oOV\u0011!q\u001a\n\u0006\u0005#\u001c%1\u001b\u0004\u0007\u0005\u000f<\u0004Aa4\u0011\u000b-\u0013Y*a\u000b\u0002\u001dA\u0014x\u000eZ;di\"\u000b7\u000f[5oOV\u0011!\u0011\u001c\n\u0006\u00057\u001c%Q\u001c\u0004\u0007\u0005\u000fD\u0004A!7\u0011\t-\u0013Y\n\\\u0001\u000egR\u0014\u0018N\\4ICND\u0017N\\4\u0016\u0005\t\r(#\u0002Bs\u0007\n\u001dhA\u0002Bds\u0001\u0011\u0019\u000fE\u0003L\u00057\u000bY!\u0001\tv]>\u0014H-\u001a:fI\"\u000b7\u000f[5oOV\u0011!Q\u001e\n\u0006\u0005_\u001c%1\u001b\u0004\u0007\u0005\u000fT\u0004A!<"
)
public class MurmurHash3 {
   public static Hashing unorderedHashing() {
      MurmurHash3$ var10000 = MurmurHash3$.MODULE$;
      return new Hashing() {
         public int hash(final IterableOnce xs) {
            return MurmurHash3$.MODULE$.unorderedHash(xs, -415593707);
         }
      };
   }

   public static Hashing stringHashing() {
      MurmurHash3$ var10000 = MurmurHash3$.MODULE$;
      return new Hashing() {
         public int hash(final String x) {
            return MurmurHash3$.MODULE$.stringHash(x, -137723950);
         }
      };
   }

   public static Hashing productHashing() {
      MurmurHash3$ var10000 = MurmurHash3$.MODULE$;
      return new Hashing() {
         public int hash(final Product x) {
            return MurmurHash3$.MODULE$.productHash(x);
         }
      };
   }

   public static Hashing orderedHashing() {
      MurmurHash3$ var10000 = MurmurHash3$.MODULE$;
      return new Hashing() {
         public int hash(final IterableOnce xs) {
            return MurmurHash3$.MODULE$.orderedHash(xs, -1248659538);
         }
      };
   }

   public static Hashing bytesHashing() {
      MurmurHash3$ var10000 = MurmurHash3$.MODULE$;
      return new Hashing() {
         public int hash(final byte[] data) {
            return MurmurHash3$.MODULE$.bytesHash(data, 1007110753);
         }
      };
   }

   public static ArrayHashing arrayHashing() {
      MurmurHash3$ var10000 = MurmurHash3$.MODULE$;
      return new ArrayHashing();
   }

   public static int setHash(final Set xs) {
      return MurmurHash3$.MODULE$.setHash(xs);
   }

   public static int mapHash(final Map xs) {
      return MurmurHash3$.MODULE$.mapHash(xs);
   }

   public static int seqHash(final Seq xs) {
      return MurmurHash3$.MODULE$.seqHash(xs);
   }

   public static int setSeed() {
      return MurmurHash3$.MODULE$.setSeed();
   }

   public static int mapSeed() {
      return MurmurHash3$.MODULE$.mapSeed();
   }

   public static int seqSeed() {
      return MurmurHash3$.MODULE$.seqSeed();
   }

   public static int traversableSeed() {
      MurmurHash3$ var10000 = MurmurHash3$.MODULE$;
      return -415593707;
   }

   public static int symmetricSeed() {
      MurmurHash3$ var10000 = MurmurHash3$.MODULE$;
      return -1248659538;
   }

   public static int productSeed() {
      MurmurHash3$ var10000 = MurmurHash3$.MODULE$;
      return -889275714;
   }

   public static int stringSeed() {
      MurmurHash3$ var10000 = MurmurHash3$.MODULE$;
      return -137723950;
   }

   public static int arraySeed() {
      MurmurHash3$ var10000 = MurmurHash3$.MODULE$;
      return 1007110753;
   }

   public final int mix(final int hash, final int data) {
      return Integer.rotateLeft(this.mixLast(hash, data), 13) * 5 + -430675100;
   }

   public final int mixLast(final int hash, final int data) {
      int k = data * -862048943;
      k = Integer.rotateLeft(k, 15);
      k *= 461845907;
      return hash ^ k;
   }

   public final int finalizeHash(final int hash, final int length) {
      return this.scala$util$hashing$MurmurHash3$$avalanche(hash ^ length);
   }

   public final int scala$util$hashing$MurmurHash3$$avalanche(final int hash) {
      int h = hash ^ hash >>> 16;
      h *= -2048144789;
      h ^= h >>> 13;
      h *= -1028477387;
      h ^= h >>> 16;
      return h;
   }

   public int tuple2Hash(final int x, final int y, final int seed) {
      int h = this.mix(seed, "Tuple2".hashCode());
      h = this.mix(h, x);
      h = this.mix(h, y);
      int finalizeHash_length = 2;
      return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ finalizeHash_length);
   }

   public final int productHash(final Product x, final int seed, final boolean ignorePrefix) {
      int arr = x.productArity();
      if (arr == 0) {
         return x.productPrefix().hashCode();
      } else {
         int h = seed;
         if (!ignorePrefix) {
            h = this.mix(seed, x.productPrefix().hashCode());
         }

         for(int i = 0; i < arr; ++i) {
            h = this.mix(h, Statics.anyHash(x.productElement(i)));
         }

         return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ arr);
      }
   }

   public final boolean productHash$default$3() {
      return false;
   }

   public final int stringHash(final String str, final int seed) {
      int h = seed;

      int i;
      for(i = 0; i + 1 < str.length(); i += 2) {
         int data = (str.charAt(i) << 16) + str.charAt(i + 1);
         h = this.mix(h, data);
      }

      if (i < str.length()) {
         h = this.mixLast(h, str.charAt(i));
      }

      int finalizeHash_length = str.length();
      return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ finalizeHash_length);
   }

   public final int unorderedHash(final IterableOnce xs, final int seed) {
      int a = 0;
      int b = 0;
      int n = 0;
      int c = 1;

      for(Iterator iterator = xs.iterator(); iterator.hasNext(); ++n) {
         int h = Statics.anyHash(iterator.next());
         a += h;
         b ^= h;
         c *= h | 1;
      }

      int h = this.mix(seed, a);
      h = this.mix(h, b);
      h = this.mixLast(h, c);
      return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ n);
   }

   public final int orderedHash(final IterableOnce xs, final int seed) {
      Iterator it = xs.iterator();
      if (!it.hasNext()) {
         int finalizeHash_length = 0;
         return this.scala$util$hashing$MurmurHash3$$avalanche(seed ^ finalizeHash_length);
      } else {
         Object x0 = it.next();
         if (!it.hasNext()) {
            int var10000 = this.mix(seed, Statics.anyHash(x0));
            byte finalizeHash_length = 1;
            int finalizeHash_hash = var10000;
            return this.scala$util$hashing$MurmurHash3$$avalanche(finalizeHash_hash ^ finalizeHash_length);
         } else {
            Object x1 = it.next();
            int initial = Statics.anyHash(x0);
            int h = this.mix(seed, initial);
            int h0 = h;
            int prev = Statics.anyHash(x1);
            int rangeDiff = prev - initial;

            for(int i = 2; it.hasNext(); ++i) {
               h = this.mix(h, prev);
               int hash = Statics.anyHash(it.next());
               if (rangeDiff != hash - prev || rangeDiff == 0) {
                  h = this.mix(h, hash);
                  ++i;

                  while(it.hasNext()) {
                     h = this.mix(h, Statics.anyHash(it.next()));
                     ++i;
                  }

                  return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ i);
               }

               prev = hash;
            }

            return this.scala$util$hashing$MurmurHash3$$avalanche(this.mix(this.mix(h0, rangeDiff), prev));
         }
      }
   }

   public final int arrayHash(final Object a, final int seed) {
      int l = Array.getLength(a);
      switch (l) {
         case 0:
            int finalizeHash_length = 0;
            return this.scala$util$hashing$MurmurHash3$$avalanche(seed ^ finalizeHash_length);
         case 1:
            int var10000 = this.mix(seed, Statics.anyHash(ScalaRunTime$.MODULE$.array_apply(a, 0)));
            byte finalizeHash_length = 1;
            int finalizeHash_hash = var10000;
            return this.scala$util$hashing$MurmurHash3$$avalanche(finalizeHash_hash ^ finalizeHash_length);
         default:
            int initial = Statics.anyHash(ScalaRunTime$.MODULE$.array_apply(a, 0));
            int h = this.mix(seed, initial);
            int h0 = h;
            int prev = Statics.anyHash(ScalaRunTime$.MODULE$.array_apply(a, 1));
            int rangeDiff = prev - initial;

            for(int i = 2; i < l; ++i) {
               h = this.mix(h, prev);
               int hash = Statics.anyHash(ScalaRunTime$.MODULE$.array_apply(a, i));
               if (rangeDiff != hash - prev || rangeDiff == 0) {
                  h = this.mix(h, hash);
                  ++i;

                  while(i < l) {
                     h = this.mix(h, Statics.anyHash(ScalaRunTime$.MODULE$.array_apply(a, i)));
                     ++i;
                  }

                  return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ l);
               }

               prev = hash;
            }

            return this.scala$util$hashing$MurmurHash3$$avalanche(this.mix(this.mix(h0, rangeDiff), prev));
      }
   }

   public final int rangeHash(final int start, final int step, final int last, final int seed) {
      return this.scala$util$hashing$MurmurHash3$$avalanche(this.mix(this.mix(this.mix(seed, start), step), last));
   }

   public final int bytesHash(final byte[] data, final int seed) {
      int len = data.length;
      int h = seed;

      int i;
      for(i = 0; len >= 4; len -= 4) {
         int k = data[i + 0] & 255;
         k |= (data[i + 1] & 255) << 8;
         k |= (data[i + 2] & 255) << 16;
         k |= (data[i + 3] & 255) << 24;
         h = this.mix(h, k);
         i += 4;
      }

      int k = 0;
      if (len == 3) {
         k ^= (data[i + 2] & 255) << 16;
      }

      if (len >= 2) {
         k ^= (data[i + 1] & 255) << 8;
      }

      if (len >= 1) {
         k ^= data[i + 0] & 255;
         h = this.mixLast(h, k);
      }

      int finalizeHash_length = data.length;
      return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ finalizeHash_length);
   }

   public final int indexedSeqHash(final IndexedSeq a, final int seed) {
      int l = a.length();
      switch (l) {
         case 0:
            int finalizeHash_length = 0;
            return this.scala$util$hashing$MurmurHash3$$avalanche(seed ^ finalizeHash_length);
         case 1:
            int var10000 = this.mix(seed, Statics.anyHash(a.apply(0)));
            byte finalizeHash_length = 1;
            int finalizeHash_hash = var10000;
            return this.scala$util$hashing$MurmurHash3$$avalanche(finalizeHash_hash ^ finalizeHash_length);
         default:
            int initial = Statics.anyHash(a.apply(0));
            int h = this.mix(seed, initial);
            int h0 = h;
            int prev = Statics.anyHash(a.apply(1));
            int rangeDiff = prev - initial;

            for(int i = 2; i < l; ++i) {
               h = this.mix(h, prev);
               int hash = Statics.anyHash(a.apply(i));
               if (rangeDiff != hash - prev || rangeDiff == 0) {
                  h = this.mix(h, hash);
                  ++i;

                  while(i < l) {
                     h = this.mix(h, Statics.anyHash(a.apply(i)));
                     ++i;
                  }

                  return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ l);
               }

               prev = hash;
            }

            return this.scala$util$hashing$MurmurHash3$$avalanche(this.mix(this.mix(h0, rangeDiff), prev));
      }
   }

   public final int listHash(final List xs, final int seed) {
      int n = 0;
      int h = seed;
      int rangeState = 0;
      int rangeDiff = 0;
      int prev = 0;
      int initial = 0;

      List tail;
      for(List elems = xs; !elems.isEmpty(); elems = tail) {
         Object head = elems.head();
         tail = (List)elems.tail();
         int hash = Statics.anyHash(head);
         h = this.mix(h, hash);
         switch (rangeState) {
            case 0:
               initial = hash;
               rangeState = 1;
               break;
            case 1:
               rangeDiff = hash - prev;
               rangeState = 2;
               break;
            case 2:
               if (rangeDiff != hash - prev || rangeDiff == 0) {
                  rangeState = 3;
               }
         }

         prev = hash;
         ++n;
      }

      if (rangeState == 2) {
         return this.rangeHash(initial, rangeDiff, prev, seed);
      } else {
         return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ n);
      }
   }

   public final int arrayHash$mZc$sp(final boolean[] a, final int seed) {
      int l = a.length;
      switch (l) {
         case 0:
            int finalizeHash_length = 0;
            return this.scala$util$hashing$MurmurHash3$$avalanche(seed ^ finalizeHash_length);
         case 1:
            int var10000 = this.mix(seed, a[0] ? 1231 : 1237);
            byte finalizeHash_length = 1;
            int finalizeHash_hash = var10000;
            return this.scala$util$hashing$MurmurHash3$$avalanche(finalizeHash_hash ^ finalizeHash_length);
         default:
            int initial = a[0] ? 1231 : 1237;
            int h = this.mix(seed, initial);
            int h0 = h;
            int prev = a[1] ? 1231 : 1237;
            int rangeDiff = prev - initial;

            for(int i = 2; i < l; ++i) {
               h = this.mix(h, prev);
               int hash = a[i] ? 1231 : 1237;
               if (rangeDiff != hash - prev || rangeDiff == 0) {
                  h = this.mix(h, hash);
                  ++i;

                  while(i < l) {
                     h = this.mix(h, a[i] ? 1231 : 1237);
                     ++i;
                  }

                  return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ l);
               }

               prev = hash;
            }

            return this.scala$util$hashing$MurmurHash3$$avalanche(this.mix(this.mix(h0, rangeDiff), prev));
      }
   }

   public final int arrayHash$mBc$sp(final byte[] a, final int seed) {
      int l = a.length;
      switch (l) {
         case 0:
            int finalizeHash_length = 0;
            return this.scala$util$hashing$MurmurHash3$$avalanche(seed ^ finalizeHash_length);
         case 1:
            int var10000 = this.mix(seed, a[0]);
            byte finalizeHash_length = 1;
            int finalizeHash_hash = var10000;
            return this.scala$util$hashing$MurmurHash3$$avalanche(finalizeHash_hash ^ finalizeHash_length);
         default:
            int initial = a[0];
            int h = this.mix(seed, initial);
            int h0 = h;
            int prev = a[1];
            int rangeDiff = prev - initial;

            for(int i = 2; i < l; ++i) {
               h = this.mix(h, prev);
               int hash = a[i];
               if (rangeDiff != hash - prev || rangeDiff == 0) {
                  h = this.mix(h, hash);
                  ++i;

                  while(i < l) {
                     h = this.mix(h, a[i]);
                     ++i;
                  }

                  return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ l);
               }

               prev = hash;
            }

            return this.scala$util$hashing$MurmurHash3$$avalanche(this.mix(this.mix(h0, rangeDiff), prev));
      }
   }

   public final int arrayHash$mCc$sp(final char[] a, final int seed) {
      int l = a.length;
      switch (l) {
         case 0:
            int finalizeHash_length = 0;
            return this.scala$util$hashing$MurmurHash3$$avalanche(seed ^ finalizeHash_length);
         case 1:
            int var10000 = this.mix(seed, a[0]);
            byte finalizeHash_length = 1;
            int finalizeHash_hash = var10000;
            return this.scala$util$hashing$MurmurHash3$$avalanche(finalizeHash_hash ^ finalizeHash_length);
         default:
            int initial = a[0];
            int h = this.mix(seed, initial);
            int h0 = h;
            int prev = a[1];
            int rangeDiff = prev - initial;

            for(int i = 2; i < l; ++i) {
               h = this.mix(h, prev);
               int hash = a[i];
               if (rangeDiff != hash - prev || rangeDiff == 0) {
                  h = this.mix(h, hash);
                  ++i;

                  while(i < l) {
                     h = this.mix(h, a[i]);
                     ++i;
                  }

                  return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ l);
               }

               prev = hash;
            }

            return this.scala$util$hashing$MurmurHash3$$avalanche(this.mix(this.mix(h0, rangeDiff), prev));
      }
   }

   public final int arrayHash$mDc$sp(final double[] a, final int seed) {
      int l = a.length;
      switch (l) {
         case 0:
            int finalizeHash_length = 0;
            return this.scala$util$hashing$MurmurHash3$$avalanche(seed ^ finalizeHash_length);
         case 1:
            int var10000 = this.mix(seed, Statics.doubleHash(a[0]));
            byte finalizeHash_length = 1;
            int finalizeHash_hash = var10000;
            return this.scala$util$hashing$MurmurHash3$$avalanche(finalizeHash_hash ^ finalizeHash_length);
         default:
            int initial = Statics.doubleHash(a[0]);
            int h = this.mix(seed, initial);
            int h0 = h;
            int prev = Statics.doubleHash(a[1]);
            int rangeDiff = prev - initial;

            for(int i = 2; i < l; ++i) {
               h = this.mix(h, prev);
               int hash = Statics.doubleHash(a[i]);
               if (rangeDiff != hash - prev || rangeDiff == 0) {
                  h = this.mix(h, hash);
                  ++i;

                  while(i < l) {
                     h = this.mix(h, Statics.doubleHash(a[i]));
                     ++i;
                  }

                  return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ l);
               }

               prev = hash;
            }

            return this.scala$util$hashing$MurmurHash3$$avalanche(this.mix(this.mix(h0, rangeDiff), prev));
      }
   }

   public final int arrayHash$mFc$sp(final float[] a, final int seed) {
      int l = a.length;
      switch (l) {
         case 0:
            int finalizeHash_length = 0;
            return this.scala$util$hashing$MurmurHash3$$avalanche(seed ^ finalizeHash_length);
         case 1:
            int var10000 = this.mix(seed, Statics.floatHash(a[0]));
            byte finalizeHash_length = 1;
            int finalizeHash_hash = var10000;
            return this.scala$util$hashing$MurmurHash3$$avalanche(finalizeHash_hash ^ finalizeHash_length);
         default:
            int initial = Statics.floatHash(a[0]);
            int h = this.mix(seed, initial);
            int h0 = h;
            int prev = Statics.floatHash(a[1]);
            int rangeDiff = prev - initial;

            for(int i = 2; i < l; ++i) {
               h = this.mix(h, prev);
               int hash = Statics.floatHash(a[i]);
               if (rangeDiff != hash - prev || rangeDiff == 0) {
                  h = this.mix(h, hash);
                  ++i;

                  while(i < l) {
                     h = this.mix(h, Statics.floatHash(a[i]));
                     ++i;
                  }

                  return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ l);
               }

               prev = hash;
            }

            return this.scala$util$hashing$MurmurHash3$$avalanche(this.mix(this.mix(h0, rangeDiff), prev));
      }
   }

   public final int arrayHash$mIc$sp(final int[] a, final int seed) {
      int l = a.length;
      switch (l) {
         case 0:
            int finalizeHash_length = 0;
            return this.scala$util$hashing$MurmurHash3$$avalanche(seed ^ finalizeHash_length);
         case 1:
            int var10000 = this.mix(seed, a[0]);
            byte finalizeHash_length = 1;
            int finalizeHash_hash = var10000;
            return this.scala$util$hashing$MurmurHash3$$avalanche(finalizeHash_hash ^ finalizeHash_length);
         default:
            int initial = a[0];
            int h = this.mix(seed, initial);
            int h0 = h;
            int prev = a[1];
            int rangeDiff = prev - initial;

            for(int i = 2; i < l; ++i) {
               h = this.mix(h, prev);
               int hash = a[i];
               if (rangeDiff != hash - prev || rangeDiff == 0) {
                  h = this.mix(h, hash);
                  ++i;

                  while(i < l) {
                     h = this.mix(h, a[i]);
                     ++i;
                  }

                  return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ l);
               }

               prev = hash;
            }

            return this.scala$util$hashing$MurmurHash3$$avalanche(this.mix(this.mix(h0, rangeDiff), prev));
      }
   }

   public final int arrayHash$mJc$sp(final long[] a, final int seed) {
      int l = a.length;
      switch (l) {
         case 0:
            int finalizeHash_length = 0;
            return this.scala$util$hashing$MurmurHash3$$avalanche(seed ^ finalizeHash_length);
         case 1:
            int var10000 = this.mix(seed, Statics.longHash(a[0]));
            byte finalizeHash_length = 1;
            int finalizeHash_hash = var10000;
            return this.scala$util$hashing$MurmurHash3$$avalanche(finalizeHash_hash ^ finalizeHash_length);
         default:
            int initial = Statics.longHash(a[0]);
            int h = this.mix(seed, initial);
            int h0 = h;
            int prev = Statics.longHash(a[1]);
            int rangeDiff = prev - initial;

            for(int i = 2; i < l; ++i) {
               h = this.mix(h, prev);
               int hash = Statics.longHash(a[i]);
               if (rangeDiff != hash - prev || rangeDiff == 0) {
                  h = this.mix(h, hash);
                  ++i;

                  while(i < l) {
                     h = this.mix(h, Statics.longHash(a[i]));
                     ++i;
                  }

                  return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ l);
               }

               prev = hash;
            }

            return this.scala$util$hashing$MurmurHash3$$avalanche(this.mix(this.mix(h0, rangeDiff), prev));
      }
   }

   public final int arrayHash$mSc$sp(final short[] a, final int seed) {
      int l = a.length;
      switch (l) {
         case 0:
            int finalizeHash_length = 0;
            return this.scala$util$hashing$MurmurHash3$$avalanche(seed ^ finalizeHash_length);
         case 1:
            int var10000 = this.mix(seed, a[0]);
            byte finalizeHash_length = 1;
            int finalizeHash_hash = var10000;
            return this.scala$util$hashing$MurmurHash3$$avalanche(finalizeHash_hash ^ finalizeHash_length);
         default:
            int initial = a[0];
            int h = this.mix(seed, initial);
            int h0 = h;
            int prev = a[1];
            int rangeDiff = prev - initial;

            for(int i = 2; i < l; ++i) {
               h = this.mix(h, prev);
               int hash = a[i];
               if (rangeDiff != hash - prev || rangeDiff == 0) {
                  h = this.mix(h, hash);
                  ++i;

                  while(i < l) {
                     h = this.mix(h, a[i]);
                     ++i;
                  }

                  return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ l);
               }

               prev = hash;
            }

            return this.scala$util$hashing$MurmurHash3$$avalanche(this.mix(this.mix(h0, rangeDiff), prev));
      }
   }

   public final int arrayHash$mVc$sp(final BoxedUnit[] a, final int seed) {
      int l = a.length;
      switch (l) {
         case 0:
            int finalizeHash_length = 0;
            return this.scala$util$hashing$MurmurHash3$$avalanche(seed ^ finalizeHash_length);
         case 1:
            int var10000 = this.mix(seed, 0);
            byte finalizeHash_length = 1;
            int finalizeHash_hash = var10000;
            return this.scala$util$hashing$MurmurHash3$$avalanche(finalizeHash_hash ^ finalizeHash_length);
         default:
            int initial = 0;
            int h = this.mix(seed, initial);
            int h0 = h;
            int prev = 0;
            int rangeDiff = prev - initial;

            for(int i = 2; i < l; ++i) {
               h = this.mix(h, prev);
               int hash = 0;
               if (rangeDiff != hash - prev || rangeDiff == 0) {
                  h = this.mix(h, hash);
                  ++i;

                  while(i < l) {
                     h = this.mix(h, 0);
                     ++i;
                  }

                  return this.scala$util$hashing$MurmurHash3$$avalanche(h ^ l);
               }

               prev = hash;
            }

            return this.scala$util$hashing$MurmurHash3$$avalanche(this.mix(this.mix(h0, rangeDiff), prev));
      }
   }

   public static class ArrayHashing implements Hashing {
      public int hash(final Object a) {
         return MurmurHash3$.MODULE$.arrayHash(a, 1007110753);
      }

      public int hash$mcZ$sp(final boolean[] a) {
         return this.hash(a);
      }

      public int hash$mcB$sp(final byte[] a) {
         return this.hash(a);
      }

      public int hash$mcC$sp(final char[] a) {
         return this.hash(a);
      }

      public int hash$mcD$sp(final double[] a) {
         return this.hash(a);
      }

      public int hash$mcF$sp(final float[] a) {
         return this.hash(a);
      }

      public int hash$mcI$sp(final int[] a) {
         return this.hash(a);
      }

      public int hash$mcJ$sp(final long[] a) {
         return this.hash(a);
      }

      public int hash$mcS$sp(final short[] a) {
         return this.hash(a);
      }

      public int hash$mcV$sp(final BoxedUnit[] a) {
         return this.hash(a);
      }
   }
}
