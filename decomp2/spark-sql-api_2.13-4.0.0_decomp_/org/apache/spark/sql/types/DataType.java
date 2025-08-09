package org.apache.spark.sql.types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.util.DataTypeJsonUtils;
import org.apache.spark.sql.internal.SqlApiConf$;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@Stable
@JsonSerialize(
   using = DataTypeJsonUtils.DataTypeJsonSerializer.class
)
@JsonDeserialize(
   using = DataTypeJsonUtils.DataTypeJsonDeserializer.class
)
@ScalaSignature(
   bytes = "\u0006\u0005\r}c!B\u001d;\u0003\u0003)\u0005\"\u0002&\u0001\t\u0003Y\u0005\"B'\u0001\r\u0003q\u0005\"B+\u0001\t\u00031\u0006B\u00022\u0001\t\u0003a4\rC\u0003r\u0001\u0011\u0005a\u000bC\u0003s\u0001\u0011\u0005a\u000bC\u0003t\u0001\u0011\u0005a\u000bC\u0003u\u0001\u0011\u0005a\u000b\u0003\u0004t\u0001\u0011\u0005A(\u001e\u0005\u0006{\u0001!\tA\u0016\u0005\u0007q\u0002!\tAP=\t\u000f}\u0004a\u0011\u0001 \u0002\u0002!A\u00111\u0001\u0001\u0005\u0002y\n)\u0001\u0003\u0005\u0002\u0012\u0001!\tAPA\n\u0011!\ti\u0002\u0001C#y\u0005\u0005\u0001\u0002CA\u0010\u0001\u0011\u0005C(!\t\b\u000f\u00055%\b#\u0001\u0002\u0010\u001a1\u0011H\u000fE\u0001\u0003#CaA\u0013\n\u0005\u0002\u0005e\u0005\"CAN%\t\u0007I\u0011BAO\u0011!\tiK\u0005Q\u0001\n\u0005}\u0005\"CAX%\t\u0007I\u0011BAO\u0011!\t\tL\u0005Q\u0001\n\u0005}\u0005\"CAZ%\t\u0007I\u0011BAO\u0011!\t)L\u0005Q\u0001\n\u0005}\u0005\"CA\\%\t\u0007I\u0011AA]\u0011!\tIM\u0005Q\u0001\n\u0005m\u0006bBAf%\u0011\u0005\u0011Q\u001a\u0005\b\u0003'\u0014B\u0011AAk\u0011\u001d\t)O\u0005C\u0001\u0003OD\u0011\"a;\u0013\u0005\u0004%I!!<\t\u0011\t]!\u0003)A\u0005\u0003_DqA!\u0007\u0013\t\u0013\u0011YbB\u0004\u0003\"IAIAa\t\u0007\u000f\t\u001d\"\u0003#\u0003\u0003*!1!j\tC\u0001\u0005WAqA!\f$\t\u0003\u0011y\u0003\u0003\u0005\u0003TI!\t\u0001\u0010B+\u0011)\u0011)GEI\u0001\n\u0003a$q\r\u0005\u000b\u0005w\u0012\u0012\u0013!C\u0001y\tu\u0004b\u0002BA%\u0011%!1\u0011\u0005\b\u0005\u001b\u0013B\u0011\u0002BH\u0011\u001d\u0011\tK\u0005C\u0005\u0005GCqA!,\u0013\t\u0013\u0011y\u000bC\u0004\u0003DJ!IA!2\t\u0011\tE'\u0003\"\u0005;\u0005'D\u0001B!<\u0013\t\u0003a$q\u001e\u0005\t\u0005s\u0014B\u0011\u0001\u001f\u0003|\"9!Q\u001e\n\u0005\n\r\u0005\u0001\"CB\u0006%E\u0005I\u0011BB\u0007\u0011!\u0019\tB\u0005C\u0001y\rM\u0001bBB\r%\u0011\u000511\u0004\u0005\n\u0007K\u0011\u0012\u0013!C\u0001\u0007\u001bAqaa\n\u0013\t\u0003\u0019I\u0003C\u0004\u0004HI!\ta!\u0013\t\u000f\rM#\u0003\"\u0001\u0004V\tAA)\u0019;b)f\u0004XM\u0003\u0002<y\u0005)A/\u001f9fg*\u0011QHP\u0001\u0004gFd'BA A\u0003\u0015\u0019\b/\u0019:l\u0015\t\t%)\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0007\u0006\u0019qN]4\u0004\u0001M\u0011\u0001A\u0012\t\u0003\u000f\"k\u0011AO\u0005\u0003\u0013j\u0012\u0001#\u00112tiJ\f7\r\u001e#bi\u0006$\u0016\u0010]3\u0002\rqJg.\u001b;?)\u0005a\u0005CA$\u0001\u0003-!WMZ1vYR\u001c\u0016N_3\u0016\u0003=\u0003\"\u0001U*\u000e\u0003ES\u0011AU\u0001\u0006g\u000e\fG.Y\u0005\u0003)F\u00131!\u00138u\u0003!!\u0018\u0010]3OC6,W#A,\u0011\u0005a{fBA-^!\tQ\u0016+D\u0001\\\u0015\taF)\u0001\u0004=e>|GOP\u0005\u0003=F\u000ba\u0001\u0015:fI\u00164\u0017B\u00011b\u0005\u0019\u0019FO]5oO*\u0011a,U\u0001\nUN|gNV1mk\u0016,\u0012\u0001\u001a\t\u0003K:t!AZ6\u000f\u0005\u001dLgB\u0001.i\u0013\u0005\u0019\u0015B\u00016C\u0003\u0019Q7o\u001c85g&\u0011A.\\\u0001\b\u0015N|g.Q*U\u0015\tQ')\u0003\u0002pa\n1!JV1mk\u0016T!\u0001\\7\u0002\t)\u001cxN\\\u0001\u000baJ,G\u000f^=Kg>t\u0017\u0001D:j[BdWm\u0015;sS:<\u0017!D2bi\u0006dwnZ*ue&tw\r\u0006\u0002Xm\")q/\u0003a\u0001\u001f\u0006yQ.\u0019=Ok6\u0014WM\u001d$jK2$7/\u0001\u0005tC6,G+\u001f9f)\tQX\u0010\u0005\u0002Qw&\u0011A0\u0015\u0002\b\u0005>|G.Z1o\u0011\u0015q8\u00021\u0001M\u0003\u0015yG\u000f[3s\u0003)\t7OT;mY\u0006\u0014G.Z\u000b\u0002\u0019\u0006\tR\r_5tiN\u0014VmY;sg&4X\r\\=\u0015\u0007i\f9\u0001C\u0004\u0002\n5\u0001\r!a\u0003\u0002\u0003\u0019\u0004R\u0001UA\u0007\u0019jL1!a\u0004R\u0005%1UO\\2uS>t\u0017'\u0001\u000bue\u0006t7OZ8s[J+7-\u001e:tSZ,G.\u001f\u000b\u0004\u0019\u0006U\u0001bBA\u0005\u001d\u0001\u0007\u0011q\u0003\t\u0006!\u0006eA\nT\u0005\u0004\u00037\t&a\u0004)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8\u0002'\u0011,g-Y;mi\u000e{gn\u0019:fi\u0016$\u0016\u0010]3\u0002\u0017\u0005\u001c7-\u001a9ugRK\b/\u001a\u000b\u0004u\u0006\r\u0002\"\u0002@\u0011\u0001\u0004a\u0005f\u0001\u0001\u0002(A!\u0011\u0011FA\u0018\u001b\t\tYCC\u0002\u0002.y\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\t$a\u000b\u0003\rM#\u0018M\u00197fQ\u001d\u0001\u0011QGA(\u0003#\u0002B!a\u000e\u0002L5\u0011\u0011\u0011\b\u0006\u0005\u0003[\tYD\u0003\u0003\u0002>\u0005}\u0012\u0001\u00033bi\u0006\u0014\u0017N\u001c3\u000b\t\u0005\u0005\u00131I\u0001\bU\u0006\u001c7n]8o\u0015\u0011\t)%a\u0012\u0002\u0013\u0019\f7\u000f^3sq6d'BAA%\u0003\r\u0019w.\\\u0005\u0005\u0003\u001b\nIDA\u0007Kg>t7+\u001a:jC2L'0Z\u0001\u0006kNLgnZ\u0012\u0003\u0003'\u0002B!!\u0016\u0002x9!\u0011qKA9\u001d\u0011\tI&a\u001b\u000f\t\u0005m\u0013q\r\b\u0005\u0003;\n)G\u0004\u0003\u0002`\u0005\rdbA4\u0002b%\u0011\u0011IQ\u0005\u0003\u007f\u0001K!!\u0010 \n\u0007\u0005%D(\u0001\u0005dCR\fG._:u\u0013\u0011\ti'a\u001c\u0002\tU$\u0018\u000e\u001c\u0006\u0004\u0003Sb\u0014\u0002BA:\u0003k\n\u0011\u0003R1uCRK\b/\u001a&t_:,F/\u001b7t\u0015\u0011\ti'a\u001c\n\t\u0005e\u00141\u0010\u0002\u0017\t\u0006$\u0018\rV=qK*\u001bxN\\*fe&\fG.\u001b>fe*!\u00111OA;Q\u001d\u0001\u0011qPA(\u0003\u000b\u0003B!a\u000e\u0002\u0002&!\u00111QA\u001d\u0005=Q5o\u001c8EKN,'/[1mSj,7EAAD!\u0011\t)&!#\n\t\u0005-\u00151\u0010\u0002\u0019\t\u0006$\u0018\rV=qK*\u001bxN\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018\u0001\u0003#bi\u0006$\u0016\u0010]3\u0011\u0005\u001d\u00132c\u0001\n\u0002\u0014B\u0019\u0001+!&\n\u0007\u0005]\u0015K\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\u0003\u001f\u000bQBR%Y\u000b\u0012{F)R\"J\u001b\u0006cUCAAP!\u0011\t\t+!+\u000e\u0005\u0005\r&\u0002BAS\u0003O\u000b\u0001\"\\1uG\"Lgn\u001a\u0006\u0004\u0003[\n\u0016\u0002BAV\u0003G\u0013QAU3hKb\faBR%Y\u000b\u0012{F)R\"J\u001b\u0006c\u0005%A\u0005D\u0011\u0006\u0013v\fV-Q\u000b\u0006Q1\tS!S?RK\u0006+\u0012\u0011\u0002\u0019Y\u000b%k\u0011%B%~#\u0016\fU#\u0002\u001bY\u000b%k\u0011%B%~#\u0016\fU#!\u0003]\u0019u\n\u0014'B)&{ejU0N\u000bR\u000bE)\u0011+B?.+\u0015,\u0006\u0002\u0002<B!\u0011QXAd\u001b\t\tyL\u0003\u0003\u0002B\u0006\r\u0017\u0001\u00027b]\u001eT!!!2\u0002\t)\fg/Y\u0005\u0004A\u0006}\u0016\u0001G\"P\u00192\u000bE+S(O'~kU\tV!E\u0003R\u000bulS#ZA\u00059aM]8n\t\u0012cEc\u0001'\u0002P\"1\u0011\u0011\u001b\u000fA\u0002]\u000b1\u0001\u001a3m\u0003U\u0001\u0018M]:f)f\u0004XmV5uQ\u001a\u000bG\u000e\u001c2bG.$r\u0001TAl\u00037\f\t\u000f\u0003\u0004\u0002Zv\u0001\raV\u0001\u0007g\u000eDW-\\1\t\u000f\u0005uW\u00041\u0001\u0002`\u00061\u0001/\u0019:tKJ\u0004R\u0001UA\u0007/2Cq!a9\u001e\u0001\u0004\ty.\u0001\bgC2d'-Y2l!\u0006\u00148/\u001a:\u0002\u0011\u0019\u0014x.\u001c&t_:$2\u0001TAu\u0011\u0015\th\u00041\u0001X\u0003)yG\u000f[3s)f\u0004Xm]\u000b\u0003\u0003_\u0004r!!=\u0002|^\u000by0\u0004\u0002\u0002t*!\u0011Q_A|\u0003%IW.\\;uC\ndWMC\u0002\u0002zF\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\ti0a=\u0003\u00075\u000b\u0007OE\u0004\u0003\u00021\u0013)Aa\u0003\u0007\r\t\r\u0001\u0001AA\u0000\u00051a$/\u001a4j]\u0016lWM\u001c;?!\r\u0001&qA\u0005\u0004\u0005\u0013\t&a\u0002)s_\u0012,8\r\u001e\t\u0005\u0005\u001b\u0011\u0019\"\u0004\u0002\u0003\u0010)!!\u0011CAb\u0003\tIw.\u0003\u0003\u0003\u0016\t=!\u0001D*fe&\fG.\u001b>bE2,\u0017aC8uQ\u0016\u0014H+\u001f9fg\u0002\n!B\\1nKR{G+\u001f9f)\ra%Q\u0004\u0005\u0007\u0005?\t\u0003\u0019A,\u0002\t9\fW.Z\u0001\u000e\u0015N{'\u000f^3e\u001f\nTWm\u0019;\u0011\u0007\t\u00152%D\u0001\u0013\u00055Q5k\u001c:uK\u0012|%M[3diN\u00191%a%\u0015\u0005\t\r\u0012AC;oCB\u0004H._*fcR!!\u0011\u0007B(!\u0015\u0001&1\u0007B\u001c\u0013\r\u0011)$\u0015\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r\te\"1\tB%\u001d\u0011\u0011YDa\u0010\u000f\u0007i\u0013i$C\u0001S\u0013\r\u0011\t%U\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\u0011)Ea\u0012\u0003\t1K7\u000f\u001e\u0006\u0004\u0005\u0003\n\u0006#\u0002)\u0003L]#\u0017b\u0001B'#\n1A+\u001e9mKJBaA!\u0015&\u0001\u0004!\u0017!\u0002<bYV,\u0017!\u00049beN,G)\u0019;b)f\u0004X\rF\u0004M\u0005/\u0012IF!\u0018\t\u000bE4\u0003\u0019\u00013\t\u0011\tmc\u0005%AA\u0002]\u000b\u0011BZ5fY\u0012\u0004\u0016\r\u001e5\t\u0013\t}c\u0005%AA\u0002\t\u0005\u0014!D2pY2\fG/[8og6\u000b\u0007\u000fE\u0003Y\u0005G:v+C\u0002\u0002~\u0006\fq\u0003]1sg\u0016$\u0015\r^1UsB,G\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\t%$fA,\u0003l-\u0012!Q\u000e\t\u0005\u0005_\u00129(\u0004\u0002\u0003r)!!1\u000fB;\u0003%)hn\u00195fG.,GMC\u0002\u0002.EKAA!\u001f\u0003r\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002/A\f'o]3ECR\fG+\u001f9fI\u0011,g-Y;mi\u0012\u001aTC\u0001B@U\u0011\u0011\tGa\u001b\u0002!A\f'o]3TiJ,8\r\u001e$jK2$G\u0003\u0002BC\u0005\u0017\u00032a\u0012BD\u0013\r\u0011II\u000f\u0002\f'R\u0014Xo\u0019;GS\u0016dG\rC\u0003rS\u0001\u0007A-\u0001\u000fbgN,'\u000f\u001e,bY&$G+\u001f9f\r>\u00148i\u001c7mCRLwN\\:\u0015\u0011\tE%q\u0013BM\u0005;\u00032\u0001\u0015BJ\u0013\r\u0011)*\u0015\u0002\u0005+:LG\u000f\u0003\u0004\u0003\\)\u0002\ra\u0016\u0005\u0007\u00057S\u0003\u0019A,\u0002\u0013\u0019LW\r\u001c3UsB,\u0007b\u0002BPU\u0001\u0007!\u0011M\u0001\rG>dG.\u0019;j_:l\u0015\r]\u0001\u0012CB\u0004XM\u001c3GS\u0016dG\rV8QCRDG#B,\u0003&\n%\u0006B\u0002BTW\u0001\u0007q+\u0001\u0005cCN,\u0007+\u0019;i\u0011\u0019\u0011Yk\u000ba\u0001/\u0006Ia-[3mI:\u000bW.Z\u0001\u0011O\u0016$8i\u001c7mCRLwN\\:NCB$BA!\u0019\u00032\"9!1\u0017\u0017A\u0002\tU\u0016AD7fi\u0006$\u0017\r^1GS\u0016dGm\u001d\t\u0007\u0005s\u0011\u0019Ea.\u0011\t\te&Q\u0018\b\u0004M\nm\u0016b\u0001B![&!!q\u0018Ba\u0005\u0019Qe)[3mI*\u0019!\u0011I7\u0002/M$(/\u001b8h)f\u0004XmV5uQ\u000e{G\u000e\\1uS>tG\u0003\u0002Bd\u0005\u001b\u00042a\u0012Be\u0013\r\u0011YM\u000f\u0002\u000b'R\u0014\u0018N\\4UsB,\u0007B\u0002Bh[\u0001\u0007q+A\u0007d_2d\u0017\r^5p]:\u000bW.Z\u0001\u0015EVLG\u000e\u001a$pe6\fG\u000f^3e'R\u0014\u0018N\\4\u0015\u0015\tE%Q\u001bBm\u0005;\u0014I\u000f\u0003\u0004\u0003X:\u0002\r\u0001T\u0001\tI\u0006$\u0018\rV=qK\"1!1\u001c\u0018A\u0002]\u000ba\u0001\u001d:fM&D\bb\u0002Bp]\u0001\u0007!\u0011]\u0001\rgR\u0014\u0018N\\4D_:\u001c\u0017\r\u001e\t\u0005\u0005G\u0014)/\u0004\u0002\u0002v%!!q]A;\u00051\u0019FO]5oO\u000e{gnY1u\u0011\u0019\u0011YO\fa\u0001\u001f\u0006AQ.\u0019=EKB$\b.A\u0011fcV\fGn]%h]>\u0014XmQ8na\u0006$\u0018N\u00197f\u001dVdG.\u00192jY&$\u0018\u0010F\u0003{\u0005c\u0014)\u0010\u0003\u0004\u0003t>\u0002\r\u0001T\u0001\u0005MJ|W\u000e\u0003\u0004\u0003x>\u0002\r\u0001T\u0001\u0003i>\f\u0001&Z9vC2\u001c\u0018j\u001a8pe\u0016t\u0015-\\3B]\u0012\u001cu.\u001c9bi&\u0014G.\u001a(vY2\f'-\u001b7jif$RA\u001fB\u007f\u0005\u007fDaAa=1\u0001\u0004a\u0005B\u0002B|a\u0001\u0007A\nF\u0004{\u0007\u0007\u0019)aa\u0002\t\r\tM\u0018\u00071\u0001M\u0011\u0019\u001190\ra\u0001\u0019\"A1\u0011B\u0019\u0011\u0002\u0003\u0007!0\u0001\u0006jO:|'/\u001a(b[\u0016\f1&Z9vC2\u001c\u0018j\u001a8pe\u0016\u001cu.\u001c9bi&\u0014G.\u001a(vY2\f'-\u001b7jif$C-\u001a4bk2$HeM\u000b\u0003\u0007\u001fQ3A\u001fB6\u0003})\u0017/^1mg&;gn\u001c:f\u0007>l\u0007/\u0019;jE2,7i\u001c7mCRLwN\u001c\u000b\u0006u\u000eU1q\u0003\u0005\u0007\u0005g\u001c\u0004\u0019\u0001'\t\r\t]8\u00071\u0001M\u0003I)\u0017/^1mgN#(/^2ukJ\fG\u000e\\=\u0015\u000fi\u001ciba\b\u0004\"!1!1\u001f\u001bA\u00021CaAa>5\u0001\u0004a\u0005\u0002CB\u0012iA\u0005\t\u0019\u0001>\u0002#%<gn\u001c:f\u001dVdG.\u00192jY&$\u00180\u0001\u000ffcV\fGn]*ueV\u001cG/\u001e:bY2LH\u0005Z3gCVdG\u000fJ\u001a\u00021\u0015\fX/\u00197t'R\u0014Xo\u0019;ve\u0006dG.\u001f\"z\u001d\u0006lW\rF\u0004{\u0007W\u0019ica\f\t\r\tMh\u00071\u0001M\u0011\u0019\u00119P\u000ea\u0001\u0019\"91\u0011\u0007\u001cA\u0002\rM\u0012\u0001\u0003:fg>dg/\u001a:\u0011\t\rU2\u0011\t\b\u0005\u0007o\u0019i$\u0004\u0002\u0004:)!11HA8\u0003!\tg.\u00197zg&\u001c\u0018\u0002BB \u0007s\tabU9m\u0003BL\u0017I\\1msNL7/\u0003\u0003\u0004D\r\u0015#\u0001\u0003*fg>dg/\u001a:\u000b\t\r}2\u0011H\u0001\u0018KF,\u0018\r\\:JO:|'/\u001a(vY2\f'-\u001b7jif$RA_B&\u0007\u001fBaa!\u00148\u0001\u0004a\u0015\u0001\u00027fMRDaa!\u00158\u0001\u0004a\u0015!\u0002:jO\"$\u0018AH3rk\u0006d7/S4o_J,7)Y:f\u0003:$g*\u001e7mC\nLG.\u001b;z)\u0015Q8qKB-\u0011\u0019\u0011\u0019\u0010\u000fa\u0001\u0019\"1!q\u001f\u001dA\u00021C3AEA\u0014Q\r\t\u0012q\u0005"
)
public abstract class DataType extends AbstractDataType {
   public static boolean equalsIgnoreCaseAndNullability(final DataType from, final DataType to) {
      return DataType$.MODULE$.equalsIgnoreCaseAndNullability(from, to);
   }

   public static boolean equalsIgnoreNullability(final DataType left, final DataType right) {
      return DataType$.MODULE$.equalsIgnoreNullability(left, right);
   }

   public static boolean equalsStructurallyByName(final DataType from, final DataType to, final Function2 resolver) {
      return DataType$.MODULE$.equalsStructurallyByName(from, to, resolver);
   }

   public static boolean equalsStructurally$default$3() {
      return DataType$.MODULE$.equalsStructurally$default$3();
   }

   public static boolean equalsStructurally(final DataType from, final DataType to, final boolean ignoreNullability) {
      return DataType$.MODULE$.equalsStructurally(from, to, ignoreNullability);
   }

   public static DataType fromJson(final String json) {
      return DataType$.MODULE$.fromJson(json);
   }

   public static DataType parseTypeWithFallback(final String schema, final Function1 parser, final Function1 fallbackParser) {
      return DataType$.MODULE$.parseTypeWithFallback(schema, parser, fallbackParser);
   }

   public static DataType fromDDL(final String ddl) {
      return DataType$.MODULE$.fromDDL(ddl);
   }

   public static String COLLATIONS_METADATA_KEY() {
      return DataType$.MODULE$.COLLATIONS_METADATA_KEY();
   }

   public abstract int defaultSize();

   public String typeName() {
      return .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(.MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(.MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(this.getClass().getSimpleName()), "$")), "Type")), "UDT").toLowerCase(Locale.ROOT);
   }

   public JValue jsonValue() {
      return org.json4s.JsonDSL..MODULE$.string2jvalue(this.typeName());
   }

   public String json() {
      return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(this.jsonValue(), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
   }

   public String prettyJson() {
      return org.json4s.jackson.JsonMethods..MODULE$.pretty(org.json4s.jackson.JsonMethods..MODULE$.render(this.jsonValue(), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
   }

   public String simpleString() {
      return this.typeName();
   }

   public String catalogString() {
      return this.simpleString();
   }

   public String simpleString(final int maxNumberFields) {
      return this.simpleString();
   }

   public String sql() {
      return this.simpleString().toUpperCase(Locale.ROOT);
   }

   public boolean sameType(final DataType other) {
      return SqlApiConf$.MODULE$.get().caseSensitiveAnalysis() ? DataType$.MODULE$.equalsIgnoreNullability(this, other) : DataType$.MODULE$.equalsIgnoreCaseAndNullability(this, other);
   }

   public abstract DataType asNullable();

   public boolean existsRecursively(final Function1 f) {
      return BoxesRunTime.unboxToBoolean(f.apply(this));
   }

   public DataType transformRecursively(final PartialFunction f) {
      return f.isDefinedAt(this) ? (DataType)f.apply(this) : this;
   }

   public final DataType defaultConcreteType() {
      return this;
   }

   public boolean acceptsType(final DataType other) {
      return this.sameType(other);
   }

   private static class JSortedObject$ {
      public static final JSortedObject$ MODULE$ = new JSortedObject$();

      public Option unapplySeq(final JValue value) {
         if (value instanceof JObject var4) {
            List seq = var4.obj();
            return new Some(seq.sortBy((x$1) -> (String)x$1._1(), scala.math.Ordering.String..MODULE$));
         } else {
            return scala.None..MODULE$;
         }
      }

      public JSortedObject$() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
