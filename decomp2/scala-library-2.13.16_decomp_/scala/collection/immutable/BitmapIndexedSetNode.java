package scala.collection.immutable;

import java.util.Arrays;
import scala.Array$;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.collection.Hashing$;
import scala.collection.StringOps$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(
   bytes = "\u0006\u0005\t]g\u0001\u0002\u001d:\r\u0001C\u0001B\u0015\u0001\u0003\u0002\u0004%\ta\u0015\u0005\t/\u0002\u0011\t\u0019!C\u00011\"Aa\f\u0001B\u0001B\u0003&A\u000b\u0003\u0005`\u0001\t\u0005\r\u0011\"\u0001T\u0011!\u0001\u0007A!a\u0001\n\u0003\t\u0007\u0002C2\u0001\u0005\u0003\u0005\u000b\u0015\u0002+\t\u0011\u0011\u0004!\u00111A\u0005\u0002\u0015D\u0001\"\u001b\u0001\u0003\u0002\u0004%\tA\u001b\u0005\tY\u0002\u0011\t\u0011)Q\u0005M\"AQ\u000e\u0001BA\u0002\u0013\u0005a\u000e\u0003\u0005q\u0001\t\u0005\r\u0011\"\u0001r\u0011!\u0019\bA!A!B\u0013y\u0007\u0002\u0003;\u0001\u0005\u0003\u0007I\u0011A*\t\u0011U\u0004!\u00111A\u0005\u0002YD\u0001\u0002\u001f\u0001\u0003\u0002\u0003\u0006K\u0001\u0016\u0005\ts\u0002\u0011\t\u0019!C\u0001'\"A!\u0010\u0001BA\u0002\u0013\u00051\u0010\u0003\u0005~\u0001\t\u0005\t\u0015)\u0003U\u0011\u0015q\b\u0001\"\u0001\u0000\u0011\u001d\ty\u0001\u0001C\u0001\u0003#Aq!a\u0006\u0001\t\u0003\nI\u0002C\u0004\u0002\u001e\u0001!\t!a\b\t\u000f\u0005\r\u0002\u0001\"\u0001\u0002&!9\u0011Q\b\u0001\u0005\u0002\u0005}\u0002bBA%\u0001\u0011\u0005\u00111\n\u0005\b\u00033\u0002A\u0011AA.\u0011\u001d\t)\u0007\u0001C\u0001\u0003OBq!!\u001d\u0001\t\u0003\t\u0019\bC\u0004\u0002\u0010\u0002!\t!!%\t\r\u0005M\u0005\u0001\"\u0001T\u0011\u001d\t)\n\u0001C\u0001\u0003#Ca!a&\u0001\t\u0003\u0019\u0006bBAM\u0001\u0011\u0005\u00111\u0014\u0005\b\u0003C\u0003A\u0011AAR\u0011\u001d\t9\u000b\u0001C\u0001\u0003SCq!!.\u0001\t\u0003\t9\fC\u0004\u0002D\u0002!\t!!2\t\u000f\u0005=\u0007\u0001\"\u0001\u0002R\"9\u0011q\u001b\u0001\u0005\u0002\u0005e\u0007bBAr\u0001\u0011\u0005\u0011Q\u001d\u0005\b\u0003_\u0004A\u0011AAy\u0011\u001d\tY\u0010\u0001C\u0001\u0003{DqA!\u0003\u0001\t\u0003\u0011Y\u0001C\u0004\u0003 \u0001!\tA!\t\t\u000f\t%\u0002\u0001\"\u0011\u0003,!9!q\u0007\u0001\u0005B\te\u0002\u0002\u0003B \u0001\u0001&IA!\u0011\t\u000f\tm\u0004\u0001\"\u0011\u0003~!9!\u0011\u0011\u0001\u0005\n\t\r\u0005b\u0002BM\u0001\u0011\u0005#1\u0014\u0005\b\u0005;\u0003A\u0011\tBP\u0011\u001d\u00119\f\u0001C!\u0005sCqAa/\u0001\t\u0003\u0012i\fC\u0004\u0003D\u0002!\tE!2\t\u000f\t=\u0007\u0001\"\u0011\u0003R\n!\")\u001b;nCBLe\u000eZ3yK\u0012\u001cV\r\u001e(pI\u0016T!AO\u001e\u0002\u0013%lW.\u001e;bE2,'B\u0001\u001f>\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002}\u0005)1oY1mC\u000e\u0001QCA!I'\t\u0001!\tE\u0002D\t\u001ak\u0011!O\u0005\u0003\u000bf\u0012qaU3u\u001d>$W\r\u0005\u0002H\u00112\u0001A!B%\u0001\u0005\u0004Q%!A!\u0012\u0005-{\u0005C\u0001'N\u001b\u0005i\u0014B\u0001(>\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0014)\n\u0005Ek$aA!os\u00069A-\u0019;b\u001b\u0006\u0004X#\u0001+\u0011\u00051+\u0016B\u0001,>\u0005\rIe\u000e^\u0001\fI\u0006$\u0018-T1q?\u0012*\u0017\u000f\u0006\u0002Z9B\u0011AJW\u0005\u00037v\u0012A!\u00168ji\"9QLAA\u0001\u0002\u0004!\u0016a\u0001=%c\u0005AA-\u0019;b\u001b\u0006\u0004\b%A\u0004o_\u0012,W*\u00199\u0002\u00179|G-Z'ba~#S-\u001d\u000b\u00033\nDq!X\u0003\u0002\u0002\u0003\u0007A+\u0001\u0005o_\u0012,W*\u00199!\u0003\u001d\u0019wN\u001c;f]R,\u0012A\u001a\t\u0004\u0019\u001e|\u0015B\u00015>\u0005\u0015\t%O]1z\u0003-\u0019wN\u001c;f]R|F%Z9\u0015\u0005e[\u0007bB/\t\u0003\u0003\u0005\rAZ\u0001\tG>tG/\u001a8uA\u0005qqN]5hS:\fG\u000eS1tQ\u0016\u001cX#A8\u0011\u00071;G+\u0001\npe&<\u0017N\\1m\u0011\u0006\u001c\b.Z:`I\u0015\fHCA-s\u0011\u001di6\"!AA\u0002=\fqb\u001c:jO&t\u0017\r\u001c%bg\",7\u000fI\u0001\u0005g&TX-\u0001\u0005tSj,w\fJ3r)\tIv\u000fC\u0004^\u001d\u0005\u0005\t\u0019\u0001+\u0002\u000bML'0\u001a\u0011\u00021\r\f7\r[3e\u0015\u00064\u0018mS3z'\u0016$\b*Y:i\u0007>$W-\u0001\u000fdC\u000eDW\r\u001a&bm\u0006\\U-_*fi\"\u000b7\u000f[\"pI\u0016|F%Z9\u0015\u0005ec\bbB/\u0012\u0003\u0003\u0005\r\u0001V\u0001\u001aG\u0006\u001c\u0007.\u001a3KCZ\f7*Z=TKRD\u0015m\u001d5D_\u0012,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u000f\u0003\u0003\t\u0019!!\u0002\u0002\b\u0005%\u00111BA\u0007!\r\u0019\u0005A\u0012\u0005\u0006%N\u0001\r\u0001\u0016\u0005\u0006?N\u0001\r\u0001\u0016\u0005\u0006IN\u0001\rA\u001a\u0005\u0006[N\u0001\ra\u001c\u0005\u0006iN\u0001\r\u0001\u0016\u0005\u0006sN\u0001\r\u0001V\u0001\u000bO\u0016$\b+Y=m_\u0006$Gc\u0001$\u0002\u0014!1\u0011Q\u0003\u000bA\u0002Q\u000bQ!\u001b8eKb\fqaZ3u\u0011\u0006\u001c\b\u000eF\u0002U\u00037Aa!!\u0006\u0016\u0001\u0004!\u0016aB4fi:{G-\u001a\u000b\u0004\u0005\u0006\u0005\u0002BBA\u000b-\u0001\u0007A+\u0001\u0005d_:$\u0018-\u001b8t))\t9#!\f\u00022\u0005U\u0012\u0011\b\t\u0004\u0019\u0006%\u0012bAA\u0016{\t9!i\\8mK\u0006t\u0007BBA\u0018/\u0001\u0007a)A\u0004fY\u0016lWM\u001c;\t\r\u0005Mr\u00031\u0001U\u00031y'/[4j]\u0006d\u0007*Y:i\u0011\u0019\t9d\u0006a\u0001)\u0006YQ\r\\3nK:$\b*Y:i\u0011\u0019\tYd\u0006a\u0001)\u0006)1\u000f[5gi\u00069Q\u000f\u001d3bi\u0016$GCCA\u0001\u0003\u0003\n\u0019%!\u0012\u0002H!1\u0011q\u0006\rA\u0002\u0019Ca!a\r\u0019\u0001\u0004!\u0006BBA\u001c1\u0001\u0007A\u000b\u0003\u0004\u0002<a\u0001\r\u0001V\u0001\u001bkB$\u0017\r^3XSRD7\u000b[1mY><X*\u001e;bi&|gn\u001d\u000b\f)\u00065\u0013qJA)\u0003'\n)\u0006\u0003\u0004\u00020e\u0001\rA\u0012\u0005\u0007\u0003gI\u0002\u0019\u0001+\t\r\u0005]\u0012\u00041\u0001U\u0011\u0019\tY$\u0007a\u0001)\"1\u0011qK\rA\u0002Q\u000bqc\u001d5bY2|w\u000f\\=NkR\f'\r\\3O_\u0012,W*\u00199\u0002\u000fI,Wn\u001c<fIRQ\u0011\u0011AA/\u0003?\n\t'a\u0019\t\r\u0005=\"\u00041\u0001G\u0011\u0019\t\u0019D\u0007a\u0001)\"1\u0011q\u0007\u000eA\u0002QCa!a\u000f\u001b\u0001\u0004!\u0016A\u0007:f[>4XmV5uQNC\u0017\r\u001c7po6+H/\u0019;j_:\u001cH\u0003CA5\u0003W\ni'a\u001c\u000e\u0003\u0001Aa!a\f\u001c\u0001\u00041\u0005BBA\u001a7\u0001\u0007A\u000b\u0003\u0004\u00028m\u0001\r\u0001V\u0001\u0014[\u0016\u0014x-\u001a+x_.+\u0017PV1m!\u0006L'o\u001d\u000b\u0010\u0005\u0006U\u0014\u0011PA?\u0003\u0003\u000b))!#\u0002\u000e\"1\u0011q\u000f\u000fA\u0002\u0019\u000bAa[3za!1\u00111\u0010\u000fA\u0002Q\u000b\u0001c\u001c:jO&t\u0017\r\\&fs\"\u000b7\u000f\u001b\u0019\t\r\u0005}D\u00041\u0001U\u0003!YW-\u001f%bg\"\u0004\u0004BBAB9\u0001\u0007a)\u0001\u0003lKf\f\u0004BBAD9\u0001\u0007A+\u0001\tpe&<\u0017N\\1m\u0017\u0016L\b*Y:ic!1\u00111\u0012\u000fA\u0002Q\u000b\u0001b[3z\u0011\u0006\u001c\b.\r\u0005\u0007\u0003wa\u0002\u0019\u0001+\u0002\u0015!\f7\u000fU1zY>\fG-\u0006\u0002\u0002(\u0005a\u0001/Y=m_\u0006$\u0017I]5us\u0006A\u0001.Y:O_\u0012,7/A\u0005o_\u0012,\u0017I]5us\u0006IA-\u0019;b\u0013:$W\r\u001f\u000b\u0004)\u0006u\u0005BBAPC\u0001\u0007A+\u0001\u0004cSR\u0004xn]\u0001\n]>$W-\u00138eKb$2\u0001VAS\u0011\u0019\tyJ\ta\u0001)\u0006q1m\u001c9z\u0003:$7+\u001a;O_\u0012,G\u0003CA\u0001\u0003W\u000bi+!-\t\r\u0005}5\u00051\u0001U\u0011\u0019\tyk\ta\u0001\u0005\u00069q\u000e\u001c3O_\u0012,\u0007BBAZG\u0001\u0007!)A\u0004oK^tu\u000eZ3\u0002%\r|\u0007/_!oI&s7/\u001a:u-\u0006dW/\u001a\u000b\u000b\u0003\u0003\tI,a/\u0002@\u0006\u0005\u0007BBAPI\u0001\u0007A\u000b\u0003\u0004\u0002>\u0012\u0002\rAR\u0001\u0004W\u0016L\bBBA\u001aI\u0001\u0007A\u000b\u0003\u0004\u00028\u0011\u0002\r\u0001V\u0001\u0010G>\u0004\u00180\u00118e'\u0016$h+\u00197vKRQ\u0011\u0011AAd\u0003\u0013\fY-!4\t\r\u0005}U\u00051\u0001U\u0011\u0019\ti,\na\u0001\r\"1\u00111G\u0013A\u0002QCa!a\u000e&\u0001\u0004!\u0016AE2paf\fe\u000e\u001a*f[>4XMV1mk\u0016$b!!\u0001\u0002T\u0006U\u0007BBAPM\u0001\u0007A\u000b\u0003\u0004\u00028\u0019\u0002\r\u0001V\u0001\u001fG>\u0004\u00180\u00118e\u001b&<'/\u0019;f\rJ|W.\u00138mS:,Gk\u001c(pI\u0016$\u0002\"!\u0001\u0002\\\u0006u\u0017q\u001c\u0005\u0007\u0003?;\u0003\u0019\u0001+\t\r\u0005]r\u00051\u0001U\u0011\u0019\t\to\na\u0001\u0005\u0006!an\u001c3f\u0003yi\u0017n\u001a:bi\u00164%o\\7J]2Lg.\u001a+p\u001d>$W-\u00138QY\u0006\u001cW\r\u0006\u0005\u0002j\u0005\u001d\u0018\u0011^Aw\u0011\u0019\ty\n\u000ba\u0001)\"1\u00111\u001e\u0015A\u0002Q\u000bqa[3z\u0011\u0006\u001c\b\u000e\u0003\u0004\u0002b\"\u0002\rAQ\u0001\u001fG>\u0004\u00180\u00118e\u001b&<'/\u0019;f\rJ|WNT8eKR{\u0017J\u001c7j]\u0016$\"\"!\u0001\u0002t\u0006U\u0018q_A}\u0011\u0019\ty*\u000ba\u0001)\"1\u0011qG\u0015A\u0002QCa!a,*\u0001\u0004\u0011\u0005BBAqS\u0001\u0007!)\u0001\u0010nS\u001e\u0014\u0018\r^3Ge>lgj\u001c3f)>Le\u000e\\5oK&s\u0007\u000b\\1dKRY\u0011,a@\u0003\u0002\t\r!Q\u0001B\u0004\u0011\u0019\tyJ\u000ba\u0001)\"1\u00111\u0007\u0016A\u0002QCa!a\u000e+\u0001\u0004!\u0006BBAXU\u0001\u0007!\t\u0003\u0004\u0002b*\u0002\rAQ\u0001\bM>\u0014X-Y2i+\u0011\u0011iAa\u0007\u0015\u0007e\u0013y\u0001C\u0004\u0003\u0012-\u0002\rAa\u0005\u0002\u0003\u0019\u0004b\u0001\u0014B\u000b\r\ne\u0011b\u0001B\f{\tIa)\u001e8di&|g.\r\t\u0004\u000f\nmAA\u0002B\u000fW\t\u0007!JA\u0001V\u0003!\u0019XOY:fi>3GCBA\u0014\u0005G\u00119\u0003\u0003\u0004\u0003&1\u0002\rAQ\u0001\u0005i\"\fG\u000f\u0003\u0004\u0002<1\u0002\r\u0001V\u0001\u000bM&dG/\u001a:J[BdGCBA\u0001\u0005[\u0011\u0019\u0004C\u0004\u000305\u0002\rA!\r\u0002\tA\u0014X\r\u001a\t\u0007\u0019\nUa)a\n\t\u000f\tUR\u00061\u0001\u0002(\u00059a\r\\5qa\u0016$\u0017\u0001\u00023jM\u001a$b!!\u0001\u0003<\tu\u0002B\u0002B\u0013]\u0001\u0007!\t\u0003\u0004\u0002<9\u0002\r\u0001V\u0001\f]\u0016<hj\u001c3f\rJ|W\u000e\u0006\r\u0002\u0002\t\r#q\tB&\u0005\u001f\u0012\u0019Fa\u0016\u0003\\\t}#q\u000eB:\u0005oBaA!\u00120\u0001\u0004!\u0016a\u00028foNK'0\u001a\u0005\u0007\u0005\u0013z\u0003\u0019\u0001+\u0002\u00159,w\u000fR1uC6\u000b\u0007\u000f\u0003\u0004\u0003N=\u0002\r\u0001V\u0001\u000b]\u0016<hj\u001c3f\u001b\u0006\u0004\bB\u0002B)_\u0001\u0007A+\u0001\u0007nS:LW.^7J]\u0012,\u0007\u0010\u0003\u0004\u0003V=\u0002\r\u0001V\u0001\u0013_2$G)\u0019;b!\u0006\u001c8\u000f\u00165s_V<\u0007\u000e\u0003\u0004\u0003Z=\u0002\r\u0001V\u0001\u0016]>$Wm\u001d+p!\u0006\u001c8\u000f\u00165s_V<\u0007.T1q\u0011\u0019\u0011if\fa\u0001)\u0006Qbn\u001c3f\u001b&<'/\u0019;f)>$\u0015\r^1UCJ<W\r^'ba\"9!\u0011M\u0018A\u0002\t\r\u0014\u0001\u00068pI\u0016\u001cHk\\'jOJ\fG/\u001a+p\t\u0006$\u0018\rE\u0003\u0003f\t-$)\u0004\u0002\u0003h)\u0019!\u0011N\u001e\u0002\u000f5,H/\u00192mK&!!Q\u000eB4\u0005\u0015\tV/Z;f\u0011\u0019\u0011\th\fa\u0001)\u0006iQ.\u00199PM:+wOT8eKNDqA!\u001e0\u0001\u0004\u0011\u0019'\u0001\u0005oK^tu\u000eZ3t\u0011\u0019\u0011Ih\fa\u0001)\u0006\tb.Z<DC\u000eDW\r\u001a%bg\"\u001cu\u000eZ3\u0002\r\u0015\fX/\u00197t)\u0011\t9Ca \t\r\t\u0015\u0002\u00071\u0001P\u0003M!W-\u001a9D_:$XM\u001c;FcV\fG.\u001b;z)!\t9C!\"\u0003\n\n5\u0005B\u0002BDc\u0001\u0007a-\u0001\u0002bc!1!1R\u0019A\u0002\u0019\f!!\u0019\u001a\t\r\t=\u0015\u00071\u0001U\u0003\u0019aWM\\4uQ\"\u001a\u0011Ga%\u0011\u00071\u0013)*C\u0002\u0003\u0018v\u0012a!\u001b8mS:,\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003Q\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005C\u0003BAa)\u00032:!!Q\u0015BW!\r\u00119+P\u0007\u0003\u0005SS1Aa+@\u0003\u0019a$o\\8u}%\u0019!qV\u001f\u0002\rA\u0013X\rZ3g\u0013\u0011\u0011\u0019L!.\u0003\rM#(/\u001b8h\u0015\r\u0011y+P\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002\u0002\u0002\u000511m\u001c8dCR$b!!\u0001\u0003@\n\u0005\u0007B\u0002B\u0013k\u0001\u0007!\t\u0003\u0004\u0002<U\u0002\r\u0001V\u0001\u0010M>\u0014X-Y2i/&$\b\u000eS1tQR\u0019\u0011La2\t\u000f\tEa\u00071\u0001\u0003JB1AJa3G)fK1A!4>\u0005%1UO\\2uS>t''\u0001\u000bg_J,\u0017m\u00195XSRD\u0007*Y:i/\"LG.\u001a\u000b\u0005\u0003O\u0011\u0019\u000eC\u0004\u0003\u0012]\u0002\rA!6\u0011\u000f1\u0013YM\u0012+\u0002(\u0001"
)
public final class BitmapIndexedSetNode extends SetNode {
   private int dataMap;
   private int nodeMap;
   private Object[] content;
   private int[] originalHashes;
   private int size;
   private int cachedJavaKeySetHashCode;

   public int dataMap() {
      return this.dataMap;
   }

   public void dataMap_$eq(final int x$1) {
      this.dataMap = x$1;
   }

   public int nodeMap() {
      return this.nodeMap;
   }

   public void nodeMap_$eq(final int x$1) {
      this.nodeMap = x$1;
   }

   public Object[] content() {
      return this.content;
   }

   public void content_$eq(final Object[] x$1) {
      this.content = x$1;
   }

   public int[] originalHashes() {
      return this.originalHashes;
   }

   public void originalHashes_$eq(final int[] x$1) {
      this.originalHashes = x$1;
   }

   public int size() {
      return this.size;
   }

   public void size_$eq(final int x$1) {
      this.size = x$1;
   }

   public int cachedJavaKeySetHashCode() {
      return this.cachedJavaKeySetHashCode;
   }

   public void cachedJavaKeySetHashCode_$eq(final int x$1) {
      this.cachedJavaKeySetHashCode = x$1;
   }

   public Object getPayload(final int index) {
      return this.content()[index];
   }

   public int getHash(final int index) {
      return this.originalHashes()[index];
   }

   public SetNode getNode(final int index) {
      return (SetNode)this.content()[this.content().length - 1 - index];
   }

   public boolean contains(final Object element, final int originalHash, final int elementHash, final int shift) {
      Node$ var10000 = Node$.MODULE$;
      int mask = elementHash >>> shift & 31;
      var10000 = Node$.MODULE$;
      int bitpos = 1 << mask;
      if ((this.dataMap() & bitpos) != 0) {
         int index = Node$.MODULE$.indexFrom(this.dataMap(), mask, bitpos);
         return this.originalHashes()[index] == originalHash && BoxesRunTime.equals(element, this.content()[index]);
      } else if ((this.nodeMap() & bitpos) != 0) {
         int index = Node$.MODULE$.indexFrom(this.nodeMap(), mask, bitpos);
         return this.getNode(index).contains(element, originalHash, elementHash, shift + 5);
      } else {
         return false;
      }
   }

   public BitmapIndexedSetNode updated(final Object element, final int originalHash, final int elementHash, final int shift) {
      Node$ var10000 = Node$.MODULE$;
      int mask = elementHash >>> shift & 31;
      var10000 = Node$.MODULE$;
      int bitpos = 1 << mask;
      if ((this.dataMap() & bitpos) != 0) {
         int index = Node$.MODULE$.indexFrom(this.dataMap(), mask, bitpos);
         Object element0 = this.content()[index];
         if (element0 == element) {
            return this;
         } else {
            int element0UnimprovedHash = this.originalHashes()[index];
            int element0Hash = Hashing$.MODULE$.improve(element0UnimprovedHash);
            if (originalHash == element0UnimprovedHash && BoxesRunTime.equals(element0, element)) {
               return this;
            } else {
               SetNode subNodeNew = this.mergeTwoKeyValPairs(element0, element0UnimprovedHash, element0Hash, element, originalHash, elementHash, shift + 5);
               return this.copyAndMigrateFromInlineToNode(bitpos, element0Hash, subNodeNew);
            }
         }
      } else if ((this.nodeMap() & bitpos) != 0) {
         int index = Node$.MODULE$.indexFrom(this.nodeMap(), mask, bitpos);
         SetNode subNode = this.getNode(index);
         SetNode subNodeNew = subNode.updated(element, originalHash, elementHash, shift + 5);
         return subNode == subNodeNew ? this : this.copyAndSetNode(bitpos, subNode, subNodeNew);
      } else {
         return this.copyAndInsertValue(bitpos, element, originalHash, elementHash);
      }
   }

   public int updateWithShallowMutations(final Object element, final int originalHash, final int elementHash, final int shift, final int shallowlyMutableNodeMap) {
      Node$ var10000 = Node$.MODULE$;
      int mask = elementHash >>> shift & 31;
      var10000 = Node$.MODULE$;
      int bitpos = 1 << mask;
      if ((this.dataMap() & bitpos) != 0) {
         int index = Node$.MODULE$.indexFrom(this.dataMap(), mask, bitpos);
         Object element0 = this.content()[index];
         int element0UnimprovedHash = this.originalHashes()[index];
         if (element0UnimprovedHash == originalHash && BoxesRunTime.equals(element0, element)) {
            return shallowlyMutableNodeMap;
         } else {
            int element0Hash = Hashing$.MODULE$.improve(element0UnimprovedHash);
            SetNode subNodeNew = this.mergeTwoKeyValPairs(element0, element0UnimprovedHash, element0Hash, element, originalHash, elementHash, shift + 5);
            this.migrateFromInlineToNodeInPlace(bitpos, element0Hash, subNodeNew);
            return shallowlyMutableNodeMap | bitpos;
         }
      } else if ((this.nodeMap() & bitpos) == 0) {
         int dataIx = this.dataIndex(bitpos);
         Object[] src = this.content();
         Object[] dst = new Object[src.length + 1];
         System.arraycopy(src, 0, dst, 0, dataIx);
         dst[dataIx] = element;
         System.arraycopy(src, dataIx, dst, dataIx + 1, src.length - dataIx);
         int[] dstHashes = this.insertElement(this.originalHashes(), dataIx, originalHash);
         this.dataMap_$eq(this.dataMap() | bitpos);
         this.content_$eq(dst);
         this.originalHashes_$eq(dstHashes);
         this.size_$eq(this.size() + 1);
         this.cachedJavaKeySetHashCode_$eq(this.cachedJavaKeySetHashCode() + elementHash);
         return shallowlyMutableNodeMap;
      } else {
         int subNodeSize;
         int subNodeCachedJavaKeySetHashCode;
         int returnNodeMap;
         label33: {
            int index = Node$.MODULE$.indexFrom(this.nodeMap(), mask, bitpos);
            SetNode subNode = this.getNode(index);
            subNodeSize = subNode.size();
            subNodeCachedJavaKeySetHashCode = subNode.cachedJavaKeySetHashCode();
            returnNodeMap = shallowlyMutableNodeMap;
            if (subNode instanceof BitmapIndexedSetNode) {
               BitmapIndexedSetNode var19 = (BitmapIndexedSetNode)subNode;
               if ((bitpos & shallowlyMutableNodeMap) != 0) {
                  var19.updateWithShallowMutations(element, originalHash, elementHash, shift + 5, 0);
                  var10000 = var19;
                  break label33;
               }
            }

            SetNode subNodeNew = subNode.updated(element, originalHash, elementHash, shift + 5);
            if (subNodeNew != subNode) {
               returnNodeMap = shallowlyMutableNodeMap | bitpos;
            }

            var10000 = subNodeNew;
         }

         SetNode subNodeNew = var10000;
         this.content()[this.content().length - 1 - this.nodeIndex(bitpos)] = subNodeNew;
         this.size_$eq(this.size() - subNodeSize + subNodeNew.size());
         this.cachedJavaKeySetHashCode_$eq(this.cachedJavaKeySetHashCode() - subNodeCachedJavaKeySetHashCode + subNodeNew.cachedJavaKeySetHashCode());
         return returnNodeMap;
      }
   }

   public BitmapIndexedSetNode removed(final Object element, final int originalHash, final int elementHash, final int shift) {
      Node$ var10000 = Node$.MODULE$;
      int mask = elementHash >>> shift & 31;
      var10000 = Node$.MODULE$;
      int bitpos = 1 << mask;
      if ((this.dataMap() & bitpos) != 0) {
         int index = Node$.MODULE$.indexFrom(this.dataMap(), mask, bitpos);
         if (BoxesRunTime.equals(this.content()[index], element)) {
            if (this.payloadArity() == 2 && this.nodeArity() == 0) {
               int var18;
               if (shift == 0) {
                  var18 = this.dataMap() ^ bitpos;
               } else {
                  Node$ var19 = Node$.MODULE$;
                  var19 = Node$.MODULE$;
                  int maskFrom_shift = 0;
                  int bitposFrom_mask = elementHash >>> maskFrom_shift & 31;
                  var18 = 1 << bitposFrom_mask;
               }

               int newDataMap = var18;
               if (index == 0) {
                  Object[] var21 = new Object[1];
                  int getPayload_index = 1;
                  var21[0] = this.content()[getPayload_index];
                  return new BitmapIndexedSetNode(newDataMap, 0, var21, new int[]{this.originalHashes()[1]}, this.size() - 1, Hashing$.MODULE$.improve(this.originalHashes()[1]));
               } else {
                  Object[] var10004 = new Object[1];
                  int getPayload_index = 0;
                  var10004[0] = this.content()[getPayload_index];
                  return new BitmapIndexedSetNode(newDataMap, 0, var10004, new int[]{this.originalHashes()[0]}, this.size() - 1, Hashing$.MODULE$.improve(this.originalHashes()[0]));
               }
            } else {
               return this.copyAndRemoveValue(bitpos, elementHash);
            }
         } else {
            return this;
         }
      } else if ((this.nodeMap() & bitpos) != 0) {
         int index = Node$.MODULE$.indexFrom(this.nodeMap(), mask, bitpos);
         SetNode subNode = this.getNode(index);
         SetNode subNodeNew = subNode.removed(element, originalHash, elementHash, shift + 5);
         if (subNodeNew == subNode) {
            return this;
         } else {
            int var12 = subNodeNew.size();
            switch (var12) {
               case 1:
                  if (this.size() == subNode.size()) {
                     return (BitmapIndexedSetNode)subNodeNew;
                  }

                  return this.copyAndMigrateFromNodeToInline(bitpos, elementHash, subNode, subNodeNew);
               default:
                  return var12 > 1 ? this.copyAndSetNode(bitpos, subNode, subNodeNew) : this;
            }
         }
      } else {
         return this;
      }
   }

   public BitmapIndexedSetNode removeWithShallowMutations(final Object element, final int originalHash, final int elementHash) {
      Node$ var10000 = Node$.MODULE$;
      int maskFrom_shift = 0;
      int mask = elementHash >>> maskFrom_shift & 31;
      var10000 = Node$.MODULE$;
      int bitpos = 1 << mask;
      if ((this.dataMap() & bitpos) != 0) {
         int index = Node$.MODULE$.indexFrom(this.dataMap(), mask, bitpos);
         if (BoxesRunTime.equals(this.content()[index], element)) {
            if (this.payloadArity() == 2 && this.nodeArity() == 0) {
               int newDataMap = this.dataMap() ^ bitpos;
               if (index == 0) {
                  var10000 = new Object[1];
                  int getPayload_index = 1;
                  ((Object[])var10000)[0] = (Node$)this.content()[getPayload_index];
                  Object[] newContent = var10000;
                  int[] newOriginalHashes = new int[]{this.originalHashes()[1]};
                  int getHash_index = 1;
                  int newCachedJavaKeySetHashCode = Hashing$.MODULE$.improve(this.originalHashes()[getHash_index]);
                  this.content_$eq(newContent);
                  this.originalHashes_$eq(newOriginalHashes);
                  this.cachedJavaKeySetHashCode_$eq(newCachedJavaKeySetHashCode);
               } else {
                  var10000 = new Object[1];
                  int getPayload_index = 0;
                  ((Object[])var10000)[0] = (Node$)this.content()[getPayload_index];
                  Object[] newContent = var10000;
                  int[] newOriginalHashes = new int[]{this.originalHashes()[0]};
                  int getHash_index = 0;
                  int newCachedJavaKeySetHashCode = Hashing$.MODULE$.improve(this.originalHashes()[getHash_index]);
                  this.content_$eq(newContent);
                  this.originalHashes_$eq(newOriginalHashes);
                  this.cachedJavaKeySetHashCode_$eq(newCachedJavaKeySetHashCode);
               }

               this.dataMap_$eq(newDataMap);
               this.nodeMap_$eq(0);
               this.size_$eq(1);
               return this;
            } else {
               int dataIx = this.dataIndex(bitpos);
               int idx = 1 * dataIx;
               Object[] src = this.content();
               Object[] dst = new Object[src.length - 1];
               System.arraycopy(src, 0, dst, 0, idx);
               System.arraycopy(src, idx + 1, dst, idx, src.length - idx - 1);
               int[] dstHashes = this.removeElement(this.originalHashes(), dataIx);
               this.dataMap_$eq(this.dataMap() ^ bitpos);
               this.content_$eq(dst);
               this.originalHashes_$eq(dstHashes);
               this.size_$eq(this.size() - 1);
               this.cachedJavaKeySetHashCode_$eq(this.cachedJavaKeySetHashCode() - elementHash);
               return this;
            }
         } else {
            return this;
         }
      } else if ((this.nodeMap() & bitpos) != 0) {
         int index = Node$.MODULE$.indexFrom(this.nodeMap(), mask, bitpos);
         SetNode subNode = this.getNode(index);
         BitmapIndexedSetNode subNodeNew = (BitmapIndexedSetNode)subNode.removed(element, originalHash, elementHash, 5);
         if (subNodeNew == subNode) {
            return this;
         } else if (subNodeNew.size() == 1) {
            if (this.payloadArity() == 0 && this.nodeArity() == 1) {
               this.dataMap_$eq(subNodeNew.dataMap());
               this.nodeMap_$eq(subNodeNew.nodeMap());
               this.content_$eq(subNodeNew.content());
               this.originalHashes_$eq(subNodeNew.originalHashes());
               this.size_$eq(subNodeNew.size());
               this.cachedJavaKeySetHashCode_$eq(subNodeNew.cachedJavaKeySetHashCode());
               return this;
            } else {
               this.migrateFromNodeToInlineInPlace(bitpos, originalHash, elementHash, subNode, subNodeNew);
               return this;
            }
         } else {
            this.content()[this.content().length - 1 - this.nodeIndex(bitpos)] = subNodeNew;
            this.size_$eq(this.size() - 1);
            this.cachedJavaKeySetHashCode_$eq(this.cachedJavaKeySetHashCode() - subNode.cachedJavaKeySetHashCode() + subNodeNew.cachedJavaKeySetHashCode());
            return this;
         }
      } else {
         return this;
      }
   }

   public SetNode mergeTwoKeyValPairs(final Object key0, final int originalKeyHash0, final int keyHash0, final Object key1, final int originalKeyHash1, final int keyHash1, final int shift) {
      if (shift >= 32) {
         return new HashCollisionSetNode(originalKeyHash0, keyHash0, Vector$.MODULE$.from(ScalaRunTime$.MODULE$.genericWrapArray(new Object[]{key0, key1})));
      } else {
         Node$ var10000 = Node$.MODULE$;
         int mask0 = keyHash0 >>> shift & 31;
         var10000 = Node$.MODULE$;
         int mask1 = keyHash1 >>> shift & 31;
         if (mask0 != mask1) {
            var10000 = Node$.MODULE$;
            int var17 = 1 << mask0;
            Node$ var10001 = Node$.MODULE$;
            int dataMap = var17 | 1 << mask1;
            int newCachedHashCode = keyHash0 + keyHash1;
            return mask0 < mask1 ? new BitmapIndexedSetNode(dataMap, 0, new Object[]{key0, key1}, new int[]{originalKeyHash0, originalKeyHash1}, 2, newCachedHashCode) : new BitmapIndexedSetNode(dataMap, 0, new Object[]{key1, key0}, new int[]{originalKeyHash1, originalKeyHash0}, 2, newCachedHashCode);
         } else {
            var10000 = Node$.MODULE$;
            int nodeMap = 1 << mask0;
            SetNode node = this.mergeTwoKeyValPairs(key0, originalKeyHash0, keyHash0, key1, originalKeyHash1, keyHash1, shift + 5);
            return new BitmapIndexedSetNode(0, nodeMap, new Object[]{node}, Array$.MODULE$.emptyIntArray(), node.size(), node.cachedJavaKeySetHashCode());
         }
      }
   }

   public boolean hasPayload() {
      return this.dataMap() != 0;
   }

   public int payloadArity() {
      return Integer.bitCount(this.dataMap());
   }

   public boolean hasNodes() {
      return this.nodeMap() != 0;
   }

   public int nodeArity() {
      return Integer.bitCount(this.nodeMap());
   }

   public int dataIndex(final int bitpos) {
      return Integer.bitCount(this.dataMap() & bitpos - 1);
   }

   public int nodeIndex(final int bitpos) {
      return Integer.bitCount(this.nodeMap() & bitpos - 1);
   }

   public BitmapIndexedSetNode copyAndSetNode(final int bitpos, final SetNode oldNode, final SetNode newNode) {
      int idx = this.content().length - 1 - this.nodeIndex(bitpos);
      Object[] src = this.content();
      Object[] dst = new Object[src.length];
      System.arraycopy(src, 0, dst, 0, src.length);
      dst[idx] = newNode;
      return new BitmapIndexedSetNode(this.dataMap(), this.nodeMap(), dst, this.originalHashes(), this.size() - oldNode.size() + newNode.size(), this.cachedJavaKeySetHashCode() - oldNode.cachedJavaKeySetHashCode() + newNode.cachedJavaKeySetHashCode());
   }

   public BitmapIndexedSetNode copyAndInsertValue(final int bitpos, final Object key, final int originalHash, final int elementHash) {
      int dataIx = this.dataIndex(bitpos);
      int idx = 1 * dataIx;
      Object[] src = this.content();
      Object[] dst = new Object[src.length + 1];
      System.arraycopy(src, 0, dst, 0, idx);
      dst[idx] = key;
      System.arraycopy(src, idx, dst, idx + 1, src.length - idx);
      int[] dstHashes = this.insertElement(this.originalHashes(), dataIx, originalHash);
      return new BitmapIndexedSetNode(this.dataMap() | bitpos, this.nodeMap(), dst, dstHashes, this.size() + 1, this.cachedJavaKeySetHashCode() + elementHash);
   }

   public BitmapIndexedSetNode copyAndSetValue(final int bitpos, final Object key, final int originalHash, final int elementHash) {
      int dataIx = this.dataIndex(bitpos);
      int idx = 1 * dataIx;
      Object[] src = this.content();
      Object[] dst = new Object[src.length];
      System.arraycopy(src, 0, dst, 0, src.length);
      dst[idx] = key;
      return new BitmapIndexedSetNode(this.dataMap() | bitpos, this.nodeMap(), dst, this.originalHashes(), this.size(), this.cachedJavaKeySetHashCode());
   }

   public BitmapIndexedSetNode copyAndRemoveValue(final int bitpos, final int elementHash) {
      int dataIx = this.dataIndex(bitpos);
      int idx = 1 * dataIx;
      Object[] src = this.content();
      Object[] dst = new Object[src.length - 1];
      System.arraycopy(src, 0, dst, 0, idx);
      System.arraycopy(src, idx + 1, dst, idx, src.length - idx - 1);
      int[] dstHashes = this.removeElement(this.originalHashes(), dataIx);
      return new BitmapIndexedSetNode(this.dataMap() ^ bitpos, this.nodeMap(), dst, dstHashes, this.size() - 1, this.cachedJavaKeySetHashCode() - elementHash);
   }

   public BitmapIndexedSetNode copyAndMigrateFromInlineToNode(final int bitpos, final int elementHash, final SetNode node) {
      int dataIx = this.dataIndex(bitpos);
      int idxOld = 1 * dataIx;
      int idxNew = this.content().length - 1 - this.nodeIndex(bitpos);
      Object[] src = this.content();
      Object[] dst = new Object[src.length - 1 + 1];
      System.arraycopy(src, 0, dst, 0, idxOld);
      System.arraycopy(src, idxOld + 1, dst, idxOld, idxNew - idxOld);
      dst[idxNew] = node;
      System.arraycopy(src, idxNew + 1, dst, idxNew + 1, src.length - idxNew - 1);
      int[] dstHashes = this.removeElement(this.originalHashes(), dataIx);
      return new BitmapIndexedSetNode(this.dataMap() ^ bitpos, this.nodeMap() | bitpos, dst, dstHashes, this.size() - 1 + node.size(), this.cachedJavaKeySetHashCode() - elementHash + node.cachedJavaKeySetHashCode());
   }

   public BitmapIndexedSetNode migrateFromInlineToNodeInPlace(final int bitpos, final int keyHash, final SetNode node) {
      int dataIx = this.dataIndex(bitpos);
      int idxOld = 1 * dataIx;
      int idxNew = this.content().length - 1 - this.nodeIndex(bitpos);
      System.arraycopy(this.content(), idxOld + 1, this.content(), idxOld, idxNew - idxOld);
      this.content()[idxNew] = node;
      this.dataMap_$eq(this.dataMap() ^ bitpos);
      this.nodeMap_$eq(this.nodeMap() | bitpos);
      this.originalHashes_$eq(this.removeElement(this.originalHashes(), dataIx));
      this.size_$eq(this.size() - 1 + node.size());
      this.cachedJavaKeySetHashCode_$eq(this.cachedJavaKeySetHashCode() - keyHash + node.cachedJavaKeySetHashCode());
      return this;
   }

   public BitmapIndexedSetNode copyAndMigrateFromNodeToInline(final int bitpos, final int elementHash, final SetNode oldNode, final SetNode node) {
      int idxOld = this.content().length - 1 - this.nodeIndex(bitpos);
      int dataIxNew = this.dataIndex(bitpos);
      int idxNew = 1 * dataIxNew;
      Object[] src = this.content();
      Object[] dst = new Object[src.length - 1 + 1];
      System.arraycopy(src, 0, dst, 0, idxNew);
      dst[idxNew] = node.getPayload(0);
      System.arraycopy(src, idxNew, dst, idxNew + 1, idxOld - idxNew);
      System.arraycopy(src, idxOld + 1, dst, idxOld + 1, src.length - idxOld - 1);
      int hash = node.getHash(0);
      int[] dstHashes = this.insertElement(this.originalHashes(), dataIxNew, hash);
      return new BitmapIndexedSetNode(this.dataMap() | bitpos, this.nodeMap() ^ bitpos, dst, dstHashes, this.size() - oldNode.size() + 1, this.cachedJavaKeySetHashCode() - oldNode.cachedJavaKeySetHashCode() + node.cachedJavaKeySetHashCode());
   }

   public void migrateFromNodeToInlineInPlace(final int bitpos, final int originalHash, final int elementHash, final SetNode oldNode, final SetNode node) {
      int idxOld = this.content().length - 1 - this.nodeIndex(bitpos);
      int dataIxNew = this.dataIndex(bitpos);
      Object element = node.getPayload(0);
      System.arraycopy(this.content(), dataIxNew, this.content(), dataIxNew + 1, idxOld - dataIxNew);
      this.content()[dataIxNew] = element;
      int hash = node.getHash(0);
      int[] dstHashes = this.insertElement(this.originalHashes(), dataIxNew, hash);
      this.dataMap_$eq(this.dataMap() | bitpos);
      this.nodeMap_$eq(this.nodeMap() ^ bitpos);
      this.originalHashes_$eq(dstHashes);
      this.size_$eq(this.size() - oldNode.size() + 1);
      this.cachedJavaKeySetHashCode_$eq(this.cachedJavaKeySetHashCode() - oldNode.cachedJavaKeySetHashCode() + node.cachedJavaKeySetHashCode());
   }

   public void foreach(final Function1 f) {
      int thisPayloadArity = this.payloadArity();

      for(int i = 0; i < thisPayloadArity; ++i) {
         f.apply(this.content()[i]);
      }

      int thisNodeArity = this.nodeArity();

      for(int j = 0; j < thisNodeArity; ++j) {
         this.getNode(j).foreach(f);
      }

   }

   public boolean subsetOf(final SetNode that, final int shift) {
      if (this == that) {
         return true;
      } else if (that instanceof HashCollisionSetNode) {
         return false;
      } else if (!(that instanceof BitmapIndexedSetNode)) {
         throw new MatchError(that);
      } else {
         BitmapIndexedSetNode var3 = (BitmapIndexedSetNode)that;
         int thisBitmap = this.dataMap() | this.nodeMap();
         int nodeBitmap = var3.dataMap() | var3.nodeMap();
         if ((thisBitmap | nodeBitmap) != nodeBitmap) {
            return false;
         } else {
            int bitmap = thisBitmap & nodeBitmap;
            int bitsToSkip = Integer.numberOfTrailingZeros(bitmap);

            boolean isValidSubset;
            int newBitmap;
            for(isValidSubset = true; isValidSubset && bitsToSkip < 32; bitsToSkip = Integer.numberOfTrailingZeros(newBitmap)) {
               Node$ var10000 = Node$.MODULE$;
               int bitpos = 1 << bitsToSkip;
               boolean var24;
               if ((this.dataMap() & bitpos) != 0) {
                  if ((var3.dataMap() & bitpos) != 0) {
                     Node$ var22 = Node$.MODULE$;
                     int getPayload_index = Integer.bitCount(this.dataMap() & bitpos - 1);
                     Object payload0 = this.content()[getPayload_index];
                     var22 = Node$.MODULE$;
                     int getPayload_index = Integer.bitCount(var3.dataMap() & bitpos - 1);
                     Object payload1 = var3.content()[getPayload_index];
                     var24 = BoxesRunTime.equals(payload0, payload1);
                  } else {
                     Node$ var25 = Node$.MODULE$;
                     int thisDataIndex = Integer.bitCount(this.dataMap() & bitpos - 1);
                     Object payload = this.content()[thisDataIndex];
                     Node$ var10001 = Node$.MODULE$;
                     SetNode subNode = that.getNode(Integer.bitCount(var3.nodeMap() & bitpos - 1));
                     int elementUnimprovedHash = this.originalHashes()[thisDataIndex];
                     int elementHash = Hashing$.MODULE$.improve(elementUnimprovedHash);
                     var24 = subNode.contains(payload, elementUnimprovedHash, elementHash, shift + 5);
                  }
               } else {
                  label44: {
                     if ((var3.dataMap() & bitpos) == 0) {
                        Node$ var26 = Node$.MODULE$;
                        SetNode subNode0 = this.getNode(Integer.bitCount(this.nodeMap() & bitpos - 1));
                        var26 = Node$.MODULE$;
                        SetNode subNode1 = var3.getNode(Integer.bitCount(var3.nodeMap() & bitpos - 1));
                        if (subNode0.subsetOf(subNode1, shift + 5)) {
                           var24 = true;
                           break label44;
                        }
                     }

                     var24 = false;
                  }
               }

               isValidSubset = var24;
               newBitmap = bitmap ^ bitpos;
               bitmap = newBitmap;
            }

            return isValidSubset;
         }
      }
   }

   public BitmapIndexedSetNode filterImpl(final Function1 pred, final boolean flipped) {
      if (this.size() == 0) {
         return this;
      } else if (this.size() == 1) {
         int getPayload_index = 0;
         return BoxesRunTime.unboxToBoolean(pred.apply(this.content()[getPayload_index])) != flipped ? this : SetNode$.MODULE$.empty();
      } else if (this.nodeMap() == 0) {
         int minimumIndex = Integer.numberOfTrailingZeros(this.dataMap());
         int maximumIndex = 32 - Integer.numberOfLeadingZeros(this.dataMap());
         int newDataMap = 0;
         int newCachedHashCode = 0;
         int dataIndex = 0;

         for(int i = minimumIndex; i < maximumIndex; ++i) {
            Node$ var40 = Node$.MODULE$;
            int bitpos = 1 << i;
            if ((bitpos & this.dataMap()) != 0) {
               Object payload = this.content()[dataIndex];
               if (BoxesRunTime.unboxToBoolean(pred.apply(payload)) != flipped) {
                  newDataMap |= bitpos;
                  newCachedHashCode += Hashing$.MODULE$.improve(this.originalHashes()[dataIndex]);
               }

               ++dataIndex;
            }
         }

         if (newDataMap == 0) {
            return SetNode$.MODULE$.empty();
         } else if (newDataMap == this.dataMap()) {
            return this;
         } else {
            int newSize = Integer.bitCount(newDataMap);
            Object[] newContent = new Object[newSize];
            int[] newOriginalHashCodes = new int[newSize];
            int newMaximumIndex = 32 - Integer.numberOfLeadingZeros(newDataMap);
            int j = Integer.numberOfTrailingZeros(newDataMap);

            for(int newDataIndex = 0; j < newMaximumIndex; ++j) {
               Node$ var41 = Node$.MODULE$;
               int bitpos = 1 << j;
               if ((bitpos & newDataMap) != 0) {
                  var41 = Node$.MODULE$;
                  int oldIndex = Integer.bitCount(this.dataMap() & bitpos - 1);
                  newContent[newDataIndex] = this.content()[oldIndex];
                  newOriginalHashCodes[newDataIndex] = this.originalHashes()[oldIndex];
                  ++newDataIndex;
               }
            }

            return new BitmapIndexedSetNode(newDataMap, 0, newContent, newOriginalHashCodes, newSize, newCachedHashCode);
         }
      } else {
         int allMap = this.dataMap() | this.nodeMap();
         int minimumIndex = Integer.numberOfTrailingZeros(allMap);
         int maximumIndex = 32 - Integer.numberOfLeadingZeros(allMap);
         int oldDataPassThrough = 0;
         int nodeMigrateToDataTargetMap = 0;
         scala.collection.mutable.Queue nodesToMigrateToData = null;
         int nodesToPassThroughMap = 0;
         int mapOfNewNodes = 0;
         scala.collection.mutable.Queue newNodes = null;
         int newDataMap = 0;
         int newNodeMap = 0;
         int newSize = 0;
         int newCachedHashCode = 0;
         int dataIndex = 0;
         int nodeIndex = 0;

         for(int i = minimumIndex; i < maximumIndex; ++i) {
            Node$ var10000 = Node$.MODULE$;
            int bitpos = 1 << i;
            if ((bitpos & this.dataMap()) != 0) {
               Object payload = this.content()[dataIndex];
               if (BoxesRunTime.unboxToBoolean(pred.apply(payload)) != flipped) {
                  newDataMap |= bitpos;
                  oldDataPassThrough |= bitpos;
                  ++newSize;
                  newCachedHashCode += Hashing$.MODULE$.improve(this.originalHashes()[dataIndex]);
               }

               ++dataIndex;
            } else if ((bitpos & this.nodeMap()) != 0) {
               SetNode oldSubNode = this.getNode(nodeIndex);
               SetNode newSubNode = oldSubNode.filterImpl(pred, flipped);
               newSize += newSubNode.size();
               newCachedHashCode += newSubNode.cachedJavaKeySetHashCode();
               if (newSubNode.size() > 1) {
                  newNodeMap |= bitpos;
                  if (oldSubNode == newSubNode) {
                     nodesToPassThroughMap |= bitpos;
                  } else {
                     mapOfNewNodes |= bitpos;
                     if (newNodes == null) {
                        newNodes = scala.collection.mutable.Queue$.MODULE$.empty();
                     }

                     newNodes.$plus$eq(newSubNode);
                  }
               } else if (newSubNode.size() == 1) {
                  newDataMap |= bitpos;
                  nodeMigrateToDataTargetMap |= bitpos;
                  if (nodesToMigrateToData == null) {
                     nodesToMigrateToData = scala.collection.mutable.Queue$.MODULE$.empty();
                  }

                  nodesToMigrateToData.$plus$eq(newSubNode);
               }

               ++nodeIndex;
            }
         }

         return this.newNodeFrom(newSize, newDataMap, newNodeMap, minimumIndex, oldDataPassThrough, nodesToPassThroughMap, nodeMigrateToDataTargetMap, nodesToMigrateToData, mapOfNewNodes, newNodes, newCachedHashCode);
      }
   }

   public BitmapIndexedSetNode diff(final SetNode that, final int shift) {
      if (that instanceof BitmapIndexedSetNode) {
         BitmapIndexedSetNode var3 = (BitmapIndexedSetNode)that;
         if (this.size() == 0) {
            return this;
         } else if (this.size() == 1) {
            int getHash_index = 0;
            int h = this.originalHashes()[getHash_index];
            int getPayload_index = 0;
            return that.contains(this.content()[getPayload_index], h, Hashing$.MODULE$.improve(h), shift) ? SetNode$.MODULE$.empty() : this;
         } else {
            int allMap = this.dataMap() | this.nodeMap();
            int minimumIndex = Integer.numberOfTrailingZeros(allMap);
            int maximumIndex = 32 - Integer.numberOfLeadingZeros(allMap);
            int oldDataPassThrough = 0;
            int nodeMigrateToDataTargetMap = 0;
            scala.collection.mutable.Queue nodesToMigrateToData = null;
            int nodesToPassThroughMap = 0;
            int mapOfNewNodes = 0;
            scala.collection.mutable.Queue newNodes = null;
            int newDataMap = 0;
            int newNodeMap = 0;
            int newSize = 0;
            int newCachedHashCode = 0;
            int dataIndex = 0;
            int nodeIndex = 0;

            for(int i = minimumIndex; i < maximumIndex; ++i) {
               Node$ var10000 = Node$.MODULE$;
               int bitpos = 1 << i;
               if ((bitpos & this.dataMap()) != 0) {
                  Object payload = this.content()[dataIndex];
                  int originalHash = this.originalHashes()[dataIndex];
                  int hash = Hashing$.MODULE$.improve(originalHash);
                  if (!var3.contains(payload, originalHash, hash, shift)) {
                     newDataMap |= bitpos;
                     oldDataPassThrough |= bitpos;
                     ++newSize;
                     newCachedHashCode += hash;
                  }

                  ++dataIndex;
               } else if ((bitpos & this.nodeMap()) != 0) {
                  SetNode oldSubNode = this.getNode(nodeIndex);
                  SetNode var34;
                  if ((bitpos & var3.dataMap()) != 0) {
                     Node$ var33 = Node$.MODULE$;
                     int thatDataIndex = Integer.bitCount(var3.dataMap() & bitpos - 1);
                     Object thatPayload = var3.content()[thatDataIndex];
                     int thatOriginalHash = var3.originalHashes()[thatDataIndex];
                     int thatHash = Hashing$.MODULE$.improve(thatOriginalHash);
                     var34 = oldSubNode.removed(thatPayload, thatOriginalHash, thatHash, shift + 5);
                  } else if ((bitpos & var3.nodeMap()) != 0) {
                     Node$ var10002 = Node$.MODULE$;
                     var34 = oldSubNode.diff(var3.getNode(Integer.bitCount(var3.nodeMap() & bitpos - 1)), shift + 5);
                  } else {
                     var34 = oldSubNode;
                  }

                  SetNode newSubNode = var34;
                  newSize += newSubNode.size();
                  newCachedHashCode += newSubNode.cachedJavaKeySetHashCode();
                  if (newSubNode.size() > 1) {
                     newNodeMap |= bitpos;
                     if (oldSubNode == newSubNode) {
                        nodesToPassThroughMap |= bitpos;
                     } else {
                        mapOfNewNodes |= bitpos;
                        if (newNodes == null) {
                           newNodes = scala.collection.mutable.Queue$.MODULE$.empty();
                        }

                        newNodes.$plus$eq(newSubNode);
                     }
                  } else if (newSubNode.size() == 1) {
                     newDataMap |= bitpos;
                     nodeMigrateToDataTargetMap |= bitpos;
                     if (nodesToMigrateToData == null) {
                        nodesToMigrateToData = scala.collection.mutable.Queue$.MODULE$.empty();
                     }

                     nodesToMigrateToData.$plus$eq(newSubNode);
                  }

                  ++nodeIndex;
               }
            }

            return this.newNodeFrom(newSize, newDataMap, newNodeMap, minimumIndex, oldDataPassThrough, nodesToPassThroughMap, nodeMigrateToDataTargetMap, nodesToMigrateToData, mapOfNewNodes, newNodes, newCachedHashCode);
         }
      } else if (that instanceof HashCollisionSetNode) {
         throw new RuntimeException("BitmapIndexedSetNode diff HashCollisionSetNode");
      } else {
         throw new MatchError(that);
      }
   }

   private BitmapIndexedSetNode newNodeFrom(final int newSize, final int newDataMap, final int newNodeMap, final int minimumIndex, final int oldDataPassThrough, final int nodesToPassThroughMap, final int nodeMigrateToDataTargetMap, final scala.collection.mutable.Queue nodesToMigrateToData, final int mapOfNewNodes, final scala.collection.mutable.Queue newNodes, final int newCachedHashCode) {
      if (newSize == 0) {
         return SetNode$.MODULE$.empty();
      } else if (newSize == this.size()) {
         return this;
      } else {
         int newDataSize = Integer.bitCount(newDataMap);
         int newContentSize = newDataSize + Integer.bitCount(newNodeMap);
         Object[] newContent = new Object[newContentSize];
         int[] newOriginalHashes = new int[newDataSize];
         int newAllMap = newDataMap | newNodeMap;
         int maxIndex = 32 - Integer.numberOfLeadingZeros(newAllMap);
         int i = minimumIndex;
         int oldDataIndex = 0;
         int oldNodeIndex = 0;
         int newDataIndex = 0;

         for(int newNodeIndex = 0; i < maxIndex; ++i) {
            Node$ var10000 = Node$.MODULE$;
            int bitpos = 1 << i;
            if ((bitpos & oldDataPassThrough) != 0) {
               newContent[newDataIndex] = this.content()[oldDataIndex];
               newOriginalHashes[newDataIndex] = this.originalHashes()[oldDataIndex];
               ++newDataIndex;
               ++oldDataIndex;
            } else if ((bitpos & nodesToPassThroughMap) != 0) {
               newContent[newContentSize - newNodeIndex - 1] = this.getNode(oldNodeIndex);
               ++newNodeIndex;
               ++oldNodeIndex;
            } else if ((bitpos & nodeMigrateToDataTargetMap) != 0) {
               SetNode node = (SetNode)nodesToMigrateToData.dequeue();
               newContent[newDataIndex] = node.getPayload(0);
               newOriginalHashes[newDataIndex] = node.getHash(0);
               ++newDataIndex;
               ++oldNodeIndex;
            } else if ((bitpos & mapOfNewNodes) != 0) {
               newContent[newContentSize - newNodeIndex - 1] = newNodes.dequeue();
               ++newNodeIndex;
               ++oldNodeIndex;
            } else if ((bitpos & this.dataMap()) != 0) {
               ++oldDataIndex;
            } else if ((bitpos & this.nodeMap()) != 0) {
               ++oldNodeIndex;
            }
         }

         return new BitmapIndexedSetNode(newDataMap, newNodeMap, newContent, newOriginalHashes, newSize, newCachedHashCode);
      }
   }

   public boolean equals(final Object that) {
      if (!(that instanceof BitmapIndexedSetNode)) {
         return false;
      } else {
         BitmapIndexedSetNode var2 = (BitmapIndexedSetNode)that;
         if (this != var2) {
            if (this.cachedJavaKeySetHashCode() == var2.cachedJavaKeySetHashCode() && this.nodeMap() == var2.nodeMap() && this.dataMap() == var2.dataMap() && this.size() == var2.size() && Arrays.equals(this.originalHashes(), var2.originalHashes())) {
               Object[] var10000 = this.content();
               Object[] var10001 = var2.content();
               int deepContentEquality_length = this.content().length;
               Object[] deepContentEquality_a2 = var10001;
               Object[] deepContentEquality_a1 = var10000;
               boolean var10;
               if (deepContentEquality_a1 == deepContentEquality_a2) {
                  var10 = true;
               } else {
                  boolean deepContentEquality_isEqual = true;

                  for(int deepContentEquality_i = 0; deepContentEquality_isEqual && deepContentEquality_i < deepContentEquality_length; ++deepContentEquality_i) {
                     deepContentEquality_isEqual = BoxesRunTime.equals(deepContentEquality_a1[deepContentEquality_i], deepContentEquality_a2[deepContentEquality_i]);
                  }

                  var10 = deepContentEquality_isEqual;
               }

               deepContentEquality_a1 = null;
               deepContentEquality_a2 = null;
               if (var10) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }
   }

   private boolean deepContentEquality(final Object[] a1, final Object[] a2, final int length) {
      if (a1 == a2) {
         return true;
      } else {
         boolean isEqual = true;

         for(int i = 0; isEqual && i < length; ++i) {
            isEqual = BoxesRunTime.equals(a1[i], a2[i]);
         }

         return isEqual;
      }
   }

   public int hashCode() {
      throw new UnsupportedOperationException("Trie nodes do not support hashing.");
   }

   public String toString() {
      return StringOps$.MODULE$.format$extension("BitmapIndexedSetNode(size=%s, dataMap=%x, nodeMap=%x)", ScalaRunTime$.MODULE$.genericWrapArray(new Object[]{this.size(), this.dataMap(), this.nodeMap()}));
   }

   public BitmapIndexedSetNode copy() {
      Object[] contentClone = this.content().clone();
      int contentLength = contentClone.length;

      for(int i = Integer.bitCount(this.dataMap()); i < contentLength; ++i) {
         contentClone[i] = ((SetNode)contentClone[i]).copy();
      }

      return new BitmapIndexedSetNode(this.dataMap(), this.nodeMap(), contentClone, (int[])this.originalHashes().clone(), this.size(), this.cachedJavaKeySetHashCode());
   }

   public BitmapIndexedSetNode concat(final SetNode that, final int shift) {
      if (that instanceof BitmapIndexedSetNode) {
         BitmapIndexedSetNode var3 = (BitmapIndexedSetNode)that;
         if (this.size() == 0) {
            return var3;
         } else if (var3.size() != 0 && var3 != this) {
            if (var3.size() == 1) {
               int originalHash = var3.getHash(0);
               return this.updated(var3.getPayload(0), originalHash, Hashing$.MODULE$.improve(originalHash), shift);
            } else {
               boolean anyChangesMadeSoFar = false;
               int allMap = this.dataMap() | var3.dataMap() | this.nodeMap() | var3.nodeMap();
               int minimumBitPos = Node$.MODULE$.bitposFrom(Integer.numberOfTrailingZeros(allMap));
               int maximumBitPos = Node$.MODULE$.bitposFrom(32 - Integer.numberOfLeadingZeros(allMap) - 1);
               int leftNodeRightNode = 0;
               int leftDataRightNode = 0;
               int leftNodeRightData = 0;
               int leftDataOnly = 0;
               int rightDataOnly = 0;
               int leftNodeOnly = 0;
               int rightNodeOnly = 0;
               int leftDataRightDataMigrateToNode = 0;
               int leftDataRightDataLeftOverwrites = 0;
               int dataToNodeMigrationTargets = 0;
               int bitpos = minimumBitPos;
               int leftIdx = 0;
               int rightIdx = 0;
               boolean finished = false;

               while(!finished) {
                  if ((bitpos & this.dataMap()) != 0) {
                     if ((bitpos & var3.dataMap()) == 0) {
                        if ((bitpos & var3.nodeMap()) != 0) {
                           leftDataRightNode |= bitpos;
                        } else {
                           leftDataOnly |= bitpos;
                        }
                     } else {
                        if (this.getHash(leftIdx) == var3.getHash(rightIdx) && BoxesRunTime.equals(this.getPayload(leftIdx), var3.getPayload(rightIdx))) {
                           leftDataRightDataLeftOverwrites |= bitpos;
                        } else {
                           leftDataRightDataMigrateToNode |= bitpos;
                           dataToNodeMigrationTargets |= Node$.MODULE$.bitposFrom(Node$.MODULE$.maskFrom(Hashing$.MODULE$.improve(this.getHash(leftIdx)), shift));
                        }

                        ++rightIdx;
                     }

                     ++leftIdx;
                  } else if ((bitpos & this.nodeMap()) != 0) {
                     if ((bitpos & var3.dataMap()) != 0) {
                        leftNodeRightData |= bitpos;
                        ++rightIdx;
                     } else if ((bitpos & var3.nodeMap()) != 0) {
                        leftNodeRightNode |= bitpos;
                     } else {
                        leftNodeOnly |= bitpos;
                     }
                  } else if ((bitpos & var3.dataMap()) != 0) {
                     rightDataOnly |= bitpos;
                     ++rightIdx;
                  } else if ((bitpos & var3.nodeMap()) != 0) {
                     rightNodeOnly |= bitpos;
                  }

                  if (bitpos == maximumBitPos) {
                     finished = true;
                  } else {
                     bitpos <<= 1;
                  }
               }

               int newDataMap = leftDataOnly | rightDataOnly | leftDataRightDataLeftOverwrites;
               int newNodeMap = leftNodeRightNode | leftDataRightNode | leftNodeRightData | leftNodeOnly | rightNodeOnly | dataToNodeMigrationTargets;
               if (newDataMap == (leftDataOnly | leftDataRightDataLeftOverwrites) && newNodeMap == leftNodeOnly) {
                  return this;
               } else {
                  int newDataSize = Integer.bitCount(newDataMap);
                  int newContentSize = newDataSize + Integer.bitCount(newNodeMap);
                  Object[] newContent = new Object[newContentSize];
                  int[] newOriginalHashes = new int[newDataSize];
                  int newSize = 0;
                  int newCachedHashCode = 0;
                  int leftDataIdx = 0;
                  int rightDataIdx = 0;
                  int leftNodeIdx = 0;
                  int rightNodeIdx = 0;
                  int nextShift = shift + 5;
                  int compressedDataIdx = 0;
                  int compressedNodeIdx = 0;
                  int bitpos = minimumBitPos;
                  boolean finished = false;

                  while(!finished) {
                     if ((bitpos & leftNodeRightNode) != 0) {
                        SetNode leftNode = this.getNode(leftNodeIdx);
                        SetNode newNode = leftNode.concat(var3.getNode(rightNodeIdx), nextShift);
                        if (leftNode != newNode) {
                           anyChangesMadeSoFar = true;
                        }

                        newContent[newContentSize - compressedNodeIdx - 1] = newNode;
                        ++compressedNodeIdx;
                        ++rightNodeIdx;
                        ++leftNodeIdx;
                        newSize += newNode.size();
                        newCachedHashCode += newNode.cachedJavaKeySetHashCode();
                     } else if ((bitpos & leftDataRightNode) != 0) {
                        anyChangesMadeSoFar = true;
                        SetNode n = var3.getNode(rightNodeIdx);
                        Object leftPayload = this.getPayload(leftDataIdx);
                        int leftOriginalHash = this.getHash(leftDataIdx);
                        int leftImproved = Hashing$.MODULE$.improve(leftOriginalHash);
                        SetNode newNode = n.updated(leftPayload, leftOriginalHash, leftImproved, nextShift);
                        newContent[newContentSize - compressedNodeIdx - 1] = newNode;
                        ++compressedNodeIdx;
                        ++rightNodeIdx;
                        ++leftDataIdx;
                        newSize += newNode.size();
                        newCachedHashCode += newNode.cachedJavaKeySetHashCode();
                     } else if ((bitpos & leftNodeRightData) != 0) {
                        int rightOriginalHash = var3.getHash(rightDataIdx);
                        SetNode leftNode = this.getNode(leftNodeIdx);
                        SetNode updated = leftNode.updated(var3.getPayload(rightDataIdx), var3.getHash(rightDataIdx), Hashing$.MODULE$.improve(rightOriginalHash), nextShift);
                        if (updated != leftNode) {
                           anyChangesMadeSoFar = true;
                        }

                        newContent[newContentSize - compressedNodeIdx - 1] = updated;
                        ++compressedNodeIdx;
                        ++leftNodeIdx;
                        ++rightDataIdx;
                        newSize += updated.size();
                        newCachedHashCode += updated.cachedJavaKeySetHashCode();
                     } else if ((bitpos & leftDataOnly) != 0) {
                        int originalHash = this.originalHashes()[leftDataIdx];
                        newContent[compressedDataIdx] = this.getPayload(leftDataIdx);
                        newOriginalHashes[compressedDataIdx] = originalHash;
                        ++compressedDataIdx;
                        ++leftDataIdx;
                        ++newSize;
                        newCachedHashCode += Hashing$.MODULE$.improve(originalHash);
                     } else if ((bitpos & rightDataOnly) != 0) {
                        anyChangesMadeSoFar = true;
                        int originalHash = var3.originalHashes()[rightDataIdx];
                        newContent[compressedDataIdx] = var3.getPayload(rightDataIdx);
                        newOriginalHashes[compressedDataIdx] = originalHash;
                        ++compressedDataIdx;
                        ++rightDataIdx;
                        ++newSize;
                        newCachedHashCode += Hashing$.MODULE$.improve(originalHash);
                     } else if ((bitpos & leftNodeOnly) != 0) {
                        SetNode newNode = this.getNode(leftNodeIdx);
                        newContent[newContentSize - compressedNodeIdx - 1] = newNode;
                        ++compressedNodeIdx;
                        ++leftNodeIdx;
                        newSize += newNode.size();
                        newCachedHashCode += newNode.cachedJavaKeySetHashCode();
                     } else if ((bitpos & rightNodeOnly) != 0) {
                        anyChangesMadeSoFar = true;
                        SetNode newNode = var3.getNode(rightNodeIdx);
                        newContent[newContentSize - compressedNodeIdx - 1] = newNode;
                        ++compressedNodeIdx;
                        ++rightNodeIdx;
                        newSize += newNode.size();
                        newCachedHashCode += newNode.cachedJavaKeySetHashCode();
                     } else if ((bitpos & leftDataRightDataMigrateToNode) != 0) {
                        anyChangesMadeSoFar = true;
                        int leftOriginalHash = this.getHash(leftDataIdx);
                        int rightOriginalHash = var3.getHash(rightDataIdx);
                        SetNode newNode = var3.mergeTwoKeyValPairs(this.getPayload(leftDataIdx), leftOriginalHash, Hashing$.MODULE$.improve(leftOriginalHash), var3.getPayload(rightDataIdx), rightOriginalHash, Hashing$.MODULE$.improve(rightOriginalHash), nextShift);
                        newContent[newContentSize - compressedNodeIdx - 1] = newNode;
                        ++compressedNodeIdx;
                        ++leftDataIdx;
                        ++rightDataIdx;
                        newSize += newNode.size();
                        newCachedHashCode += newNode.cachedJavaKeySetHashCode();
                     } else if ((bitpos & leftDataRightDataLeftOverwrites) != 0) {
                        int originalHash = var3.originalHashes()[rightDataIdx];
                        newContent[compressedDataIdx] = var3.getPayload(rightDataIdx);
                        newOriginalHashes[compressedDataIdx] = originalHash;
                        ++compressedDataIdx;
                        ++rightDataIdx;
                        ++newSize;
                        newCachedHashCode += Hashing$.MODULE$.improve(originalHash);
                        ++leftDataIdx;
                     }

                     if (bitpos == maximumBitPos) {
                        finished = true;
                     } else {
                        bitpos <<= 1;
                     }
                  }

                  if (anyChangesMadeSoFar) {
                     return new BitmapIndexedSetNode(newDataMap, newNodeMap, newContent, newOriginalHashes, newSize, newCachedHashCode);
                  } else {
                     return this;
                  }
               }
            }
         } else {
            return this;
         }
      } else {
         throw new UnsupportedOperationException("Cannot concatenate a HashCollisionSetNode with a BitmapIndexedSetNode");
      }
   }

   public void foreachWithHash(final Function2 f) {
      int iN = this.payloadArity();

      for(int i = 0; i < iN; ++i) {
         f.apply(this.content()[i], this.originalHashes()[i]);
      }

      int jN = this.nodeArity();

      for(int j = 0; j < jN; ++j) {
         this.getNode(j).foreachWithHash(f);
      }

   }

   public boolean foreachWithHashWhile(final Function2 f) {
      int thisPayloadArity = this.payloadArity();
      boolean pass = true;

      for(int i = 0; i < thisPayloadArity && pass; ++i) {
         pass = pass && BoxesRunTime.unboxToBoolean(f.apply(this.content()[i], this.originalHashes()[i]));
      }

      int thisNodeArity = this.nodeArity();

      for(int j = 0; j < thisNodeArity && pass; ++j) {
         pass = pass && this.getNode(j).foreachWithHashWhile(f);
      }

      return pass;
   }

   public BitmapIndexedSetNode(final int dataMap, final int nodeMap, final Object[] content, final int[] originalHashes, final int size, final int cachedJavaKeySetHashCode) {
      this.dataMap = dataMap;
      this.nodeMap = nodeMap;
      this.content = content;
      this.originalHashes = originalHashes;
      this.size = size;
      this.cachedJavaKeySetHashCode = cachedJavaKeySetHashCode;
      super();
   }
}
