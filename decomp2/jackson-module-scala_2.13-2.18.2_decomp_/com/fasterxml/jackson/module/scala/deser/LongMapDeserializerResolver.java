package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import com.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.immutable.LongMap;
import scala.collection.immutable.LongMap.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0015qA\u0002\u001e<\u0011\u0003YtI\u0002\u0004Jw!\u00051H\u0013\u0005\u0006-\u0006!\t\u0001\u0017\u0005\b3\u0006\u0011\r\u0011\"\u0003[\u0011\u0019\t\u0018\u0001)A\u00057\"9!0\u0001b\u0001\n\u0013Y\bbBA\u0007\u0003\u0001\u0006I\u0001 \u0005\b\u0003\u001f\tA\u0011IA\t\r\u0019\ty'\u0001\u0003\u0002r!Q\u0011q\u0012\u0005\u0003\u0002\u0003\u0006I!a\n\t\u0015\u0005E\u0005B!A!\u0002\u0013\t\u0019\n\u0003\u0004W\u0011\u0011\u0005\u0011\u0011\u0014\u0005\b\u0003GCA\u0011IAS\u0011\u001d\ti\u000b\u0003C!\u0003_Cq!!/\t\t\u0003\nY\fC\u0004\u0002\\\"!\t%!8\t\u000f\u0005m\u0007\u0002\"\u0011\u0002r\"9\u00111 \u0005\u0005B\u0005uhA\u0002B\u0004\u0003\u0011\u0011I\u0001\u0003\u0006\u0002\u0010J\u0011\t\u0011)A\u0005\u0003OA!\"!%\u0013\u0005\u0003\u0005\u000b\u0011BAJ\u0011\u00191&\u0003\"\u0001\u0003\u0016!9\u00111\u0015\n\u0005B\u0005\u0015\u0006bBAW%\u0011\u0005\u0013q\u0016\u0005\b\u0003s\u0013B\u0011\tB\u000f\u0011\u001d\tYN\u0005C!\u0005[Aq!a7\u0013\t\u0003\u0012\u0019\u0004C\u0004\u0002|J!\tEa\u000f\u0007\r\t}\u0012\u0001\u0002B!\u0011)\t)\u0004\bB\u0001B\u0003%\u0011q\u0007\u0005\u000b\u0003\u001fc\"\u0011!Q\u0001\n\u0005\u001d\u0002B\u0002,\u001d\t\u0003\u0011I\u0005C\u0004\u0003Rq!\tEa\u0015\t\u000f\tmC\u0004\"\u0011\u0003^\u00191!\u0011[\u0001\u0005\u0005'D!\"!\u000e#\u0005\u0003\u0005\u000b\u0011BA\u001c\u0011)\tyI\tB\u0001B\u0003%\u0011q\u0005\u0005\u0007-\n\"\tA!6\t\u000f\tE#\u0005\"\u0011\u0003T!9!1\f\u0012\u0005B\tugA\u0002B1\u0003\u0011\u0011\u0019\u0007\u0003\u0004WQ\u0011\u0005!\u0011\u000f\u0005\n\u0005gB\u0003\u0019!C\u0001\u0005kB\u0011B!\u001f)\u0001\u0004%\tAa\u001f\t\u0011\t\u001d\u0005\u0006)Q\u0005\u0005oBqA!#)\t\u0003\u0012Y\tC\u0004\u0003\u0016\"\"\tEa&\t\u000f\tu\u0005\u0006\"\u0011\u0003 \"9!1\u0019\u0015\u0005\u0002\t\u0015gA\u0002Bq\u0003\u0011\u0011\u0019\u000f\u0003\u0004Wc\u0011\u0005!Q\u001d\u0005\n\u0005g\n\u0004\u0019!C\u0001\u0005OD\u0011B!\u001f2\u0001\u0004%\tAa;\t\u0011\t\u001d\u0015\u0007)Q\u0005\u0005SDqA!#2\t\u0003\u0012y\u000fC\u0004\u0003\u0016F\"\tE!>\t\u000f\tu\u0015\u0007\"\u0011\u0003 \"9!1Y\u0019\u0005\u0002\te\u0018a\u0007'p]\u001el\u0015\r\u001d#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014(+Z:pYZ,'O\u0003\u0002={\u0005)A-Z:fe*\u0011ahP\u0001\u0006g\u000e\fG.\u0019\u0006\u0003\u0001\u0006\u000ba!\\8ek2,'B\u0001\"D\u0003\u001dQ\u0017mY6t_:T!\u0001R#\u0002\u0013\u0019\f7\u000f^3sq6d'\"\u0001$\u0002\u0007\r|W\u000e\u0005\u0002I\u00035\t1HA\u000eM_:<W*\u00199EKN,'/[1mSj,'OU3t_24XM]\n\u0003\u0003-\u0003\"\u0001T*\u000f\u00055\u000bV\"\u0001(\u000b\u0005qz%B\u0001)B\u0003!!\u0017\r^1cS:$\u0017B\u0001*O\u00035!Um]3sS\u0006d\u0017N_3sg&\u0011A+\u0016\u0002\u0005\u0005\u0006\u001cXM\u0003\u0002S\u001d\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001H\u0003UIW.\\;uC\ndW\rT8oO6\u000b\u0007o\u00117bgN,\u0012a\u0017\t\u00049\u0006\u001cW\"A/\u000b\u0005y{\u0016\u0001\u00027b]\u001eT\u0011\u0001Y\u0001\u0005U\u00064\u0018-\u0003\u0002c;\n)1\t\\1tgB\u0012Am\u001c\t\u0004K.lW\"\u00014\u000b\u0005\u001dD\u0017!C5n[V$\u0018M\u00197f\u0015\tI'.\u0001\u0006d_2dWm\u0019;j_:T\u0011AP\u0005\u0003Y\u001a\u0014q\u0001T8oO6\u000b\u0007\u000f\u0005\u0002o_2\u0001A!\u00039\u0005\u0003\u0003\u0005\tQ!\u0001s\u0005\ryF%M\u0001\u0017S6lW\u000f^1cY\u0016duN\\4NCB\u001cE.Y:tAE\u00111o\u001e\t\u0003iVl\u0011A[\u0005\u0003m*\u0014qAT8uQ&tw\r\u0005\u0002uq&\u0011\u0011P\u001b\u0002\u0004\u0003:L\u0018aE7vi\u0006\u0014G.\u001a'p]\u001el\u0015\r]\"mCN\u001cX#\u0001?\u0011\u0007q\u000bW\u0010M\u0002\u007f\u0003\u0013\u0001Ra`A\u0003\u0003\u000fi!!!\u0001\u000b\u0007\u0005\r\u0001.A\u0004nkR\f'\r\\3\n\u00071\f\t\u0001E\u0002o\u0003\u0013!!\"a\u0003\u0007\u0003\u0003\u0005\tQ!\u0001s\u0005\ryFEM\u0001\u0015[V$\u0018M\u00197f\u0019>tw-T1q\u00072\f7o\u001d\u0011\u0002/\u0019Lg\u000eZ'ba2K7.\u001a#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014HCDA\n\u0003G\t\u0019$!\u0010\u0002H\u0005E\u0013\u0011\r\u0019\u0005\u0003+\ty\u0002\u0005\u0004\u0002\u0018\u0005e\u0011QD\u0007\u0002\u001f&\u0019\u00111D(\u0003!)\u001bxN\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\bc\u00018\u0002 \u0011Q\u0011\u0011E\u0004\u0002\u0002\u0003\u0005)\u0011\u0001:\u0003\u0007}#C\u0007C\u0004\u0002&\u001d\u0001\r!a\n\u0002\u000fQDW\rV=qKB!\u0011\u0011FA\u0018\u001b\t\tYCC\u0002\u0002.=\u000bA\u0001^=qK&!\u0011\u0011GA\u0016\u0005-i\u0015\r\u001d'jW\u0016$\u0016\u0010]3\t\u000f\u0005Ur\u00011\u0001\u00028\u000511m\u001c8gS\u001e\u0004B!a\u0006\u0002:%\u0019\u00111H(\u0003+\u0011+7/\u001a:jC2L'0\u0019;j_:\u001cuN\u001c4jO\"9\u0011qH\u0004A\u0002\u0005\u0005\u0013\u0001\u00032fC:$Um]2\u0011\t\u0005]\u00111I\u0005\u0004\u0003\u000bz%a\u0004\"fC:$Um]2sSB$\u0018n\u001c8\t\u000f\u0005%s\u00011\u0001\u0002L\u0005y1.Z=EKN,'/[1mSj,'\u000f\u0005\u0003\u0002\u0018\u00055\u0013bAA(\u001f\ny1*Z=EKN,'/[1mSj,'\u000fC\u0004\u0002T\u001d\u0001\r!!\u0016\u0002/\u0015dW-\\3oiRK\b/\u001a#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\u0003BA,\u0003;j!!!\u0017\u000b\u0007\u0005ms*\u0001\u0005kg>tG/\u001f9f\u0013\u0011\ty&!\u0017\u0003!QK\b/\u001a#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\bbBA2\u000f\u0001\u0007\u0011QM\u0001\u0014K2,W.\u001a8u\t\u0016\u001cXM]5bY&TXM\u001d\u0019\u0005\u0003O\nY\u0007\u0005\u0004\u0002\u0018\u0005e\u0011\u0011\u000e\t\u0004]\u0006-DaCA7\u0003C\n\t\u0011!A\u0003\u0002I\u00141a\u0018\u00134\u0005qIU.\\;uC\ndW\rT8oO6\u000b\u0007\u000fR3tKJL\u0017\r\\5{KJ,B!a\u001d\u0002\u0006N)\u0001\"!\u001e\u0002\nB1\u0011qOA?\u0003\u0003k!!!\u001f\u000b\u0007\u0005md*A\u0002ti\u0012LA!a \u0002z\tI2i\u001c8uC&tWM\u001d#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014()Y:f!\u0011)7.a!\u0011\u00079\f)\t\u0002\u0004\u0002\b\"\u0011\rA\u001d\u0002\u0002-B\u0019Q*a#\n\u0007\u00055eJ\u0001\fD_:$X\r\u001f;vC2$Um]3sS\u0006d\u0017N_3s\u0003\u001di\u0017\r\u001d+za\u0016\fQcY8oi\u0006Lg.\u001a:EKN,'/[1mSj,'\u000f\u0005\u0003\u0002x\u0005U\u0015\u0002BAL\u0003s\u0012q\"T1q\t\u0016\u001cXM]5bY&TXM\u001d\u000b\u0007\u00037\u000by*!)\u0011\u000b\u0005u\u0005\"a!\u000e\u0003\u0005Aq!a$\f\u0001\u0004\t9\u0003C\u0004\u0002\u0012.\u0001\r!a%\u0002\u001d\u001d,GoQ8oi\u0016tG\u000fV=qKR\u0011\u0011q\u0015\t\u0005\u0003/\tI+C\u0002\u0002,>\u0013\u0001BS1wCRK\b/Z\u0001\u0017O\u0016$8i\u001c8uK:$H)Z:fe&\fG.\u001b>feR\u0011\u0011\u0011\u0017\t\u0007\u0003/\tI\"a-\u0011\u0007Q\f),C\u0002\u00028*\u0014a!\u00118z%\u00164\u0017\u0001E2sK\u0006$XmQ8oi\u0016DH/^1m)\u0019\ti,a2\u0002RB\"\u0011qXAb!\u0019\t9\"!\u0007\u0002BB\u0019a.a1\u0005\u0015\u0005\u0015g\"!A\u0001\u0002\u000b\u0005!OA\u0002`IUBq!!3\u000f\u0001\u0004\tY-\u0001\u0003dib$\b\u0003BA\f\u0003\u001bL1!a4P\u0005Y!Um]3sS\u0006d\u0017N_1uS>t7i\u001c8uKb$\bbBAj\u001d\u0001\u0007\u0011Q[\u0001\taJ|\u0007/\u001a:usB!\u0011qCAl\u0013\r\tIn\u0014\u0002\r\u0005\u0016\fg\u000e\u0015:pa\u0016\u0014H/_\u0001\fI\u0016\u001cXM]5bY&TX\r\u0006\u0004\u0002\u0002\u0006}\u0017q\u001e\u0005\b\u0003C|\u0001\u0019AAr\u0003\tQ\u0007\u000f\u0005\u0003\u0002f\u0006-XBAAt\u0015\r\tI/Q\u0001\u0005G>\u0014X-\u0003\u0003\u0002n\u0006\u001d(A\u0003&t_:\u0004\u0016M]:fe\"9\u0011\u0011Z\bA\u0002\u0005-G\u0003CAA\u0003g\f)0a>\t\u000f\u0005\u0005\b\u00031\u0001\u0002d\"9\u0011\u0011\u001a\tA\u0002\u0005-\u0007bBA}!\u0001\u0007\u0011\u0011Q\u0001\nS:$xNV1mk\u0016\fQbZ3u\u000b6\u0004H/\u001f,bYV,G\u0003BA\u0000\u0005\u000b\u00012\u0001\u0018B\u0001\u0013\r\u0011\u0019!\u0018\u0002\u0007\u001f\nTWm\u0019;\t\u000f\u0005%\u0017\u00031\u0001\u0002L\nQR*\u001e;bE2,Gj\u001c8h\u001b\u0006\u0004H)Z:fe&\fG.\u001b>feV!!1\u0002B\n'\u0015\u0011\"QBAE!\u0019\t9(! \u0003\u0010A)q0!\u0002\u0003\u0012A\u0019aNa\u0005\u0005\r\u0005\u001d%C1\u0001s)\u0019\u00119B!\u0007\u0003\u001cA)\u0011Q\u0014\n\u0003\u0012!9\u0011qR\u000bA\u0002\u0005\u001d\u0002bBAI+\u0001\u0007\u00111\u0013\u000b\u0007\u0005?\u0011ICa\u000b1\t\t\u0005\"Q\u0005\t\u0007\u0003/\tIBa\t\u0011\u00079\u0014)\u0003\u0002\u0006\u0003(a\t\t\u0011!A\u0003\u0002I\u00141a\u0018\u00137\u0011\u001d\tI\r\u0007a\u0001\u0003\u0017Dq!a5\u0019\u0001\u0004\t)\u000e\u0006\u0004\u0003\u0010\t=\"\u0011\u0007\u0005\b\u0003CL\u0002\u0019AAr\u0011\u001d\tI-\u0007a\u0001\u0003\u0017$\u0002Ba\u0004\u00036\t]\"\u0011\b\u0005\b\u0003CT\u0002\u0019AAr\u0011\u001d\tIM\u0007a\u0001\u0003\u0017Dq!!?\u001b\u0001\u0004\u0011y\u0001\u0006\u0003\u0002\u0000\nu\u0002bBAe7\u0001\u0007\u00111\u001a\u0002\u001d\u00136lW\u000f^1cY\u0016duN\\4NCBLen\u001d;b]RL\u0017\r^8s'\ra\"1\t\t\u0005\u0003o\u0012)%\u0003\u0003\u0003H\u0005e$\u0001F*uIZ\u000bG.^3J]N$\u0018M\u001c;jCR|'\u000f\u0006\u0004\u0003L\t5#q\n\t\u0004\u0003;c\u0002bBA\u001b?\u0001\u0007\u0011q\u0007\u0005\b\u0003\u001f{\u0002\u0019AA\u0014\u0003U\u0019\u0017M\\\"sK\u0006$X-V:j]\u001e$UMZ1vYR$\"A!\u0016\u0011\u0007Q\u00149&C\u0002\u0003Z)\u0014qAQ8pY\u0016\fg.\u0001\nde\u0016\fG/Z+tS:<G)\u001a4bk2$H\u0003\u0002B0\u0005\u001f\u00042!!()\u0005MIU.\\;uC\ndW-T1q/J\f\u0007\u000f]3s'\rA#Q\r\t\t\u0005O\u0012i'a@\u0002\u00006\u0011!\u0011\u000e\u0006\u0004\u0005Wz\u0016\u0001B;uS2LAAa\u001c\u0003j\tY\u0011IY:ue\u0006\u001cG/T1q)\t\u0011y&A\u0004cCN,W*\u00199\u0016\u0005\t]\u0004\u0003B3l\u0003\u007f\f1BY1tK6\u000b\u0007o\u0018\u0013fcR!!Q\u0010BB!\r!(qP\u0005\u0004\u0005\u0003S'\u0001B+oSRD\u0011B!\",\u0003\u0003\u0005\rAa\u001e\u0002\u0007a$\u0013'\u0001\u0005cCN,W*\u00199!\u0003\r\u0001X\u000f\u001e\u000b\u0007\u0003\u007f\u0014iI!%\t\u000f\t=U\u00061\u0001\u0002\u0000\u0006\t1\u000eC\u0004\u0003\u00146\u0002\r!a@\u0002\u0003Y\f1aZ3u)\u0011\tyP!'\t\u000f\tme\u00061\u0001\u0002\u0000\u0006\u00191.Z=\u0002\u0011\u0015tGO]=TKR$\"A!)\u0011\r\t\u001d$1\u0015BT\u0013\u0011\u0011)K!\u001b\u0003\u0007M+G\u000f\u0005\u0005\u0003*\nu\u0016q`A\u0000\u001d\u0011\u0011YK!/\u000f\t\t5&q\u0017\b\u0005\u0005_\u0013),\u0004\u0002\u00032*\u0019!1W,\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0017b\u0001B6?&!!1\u0018B5\u0003\ri\u0015\r]\u0005\u0005\u0005\u007f\u0013\tMA\u0003F]R\u0014\u0018P\u0003\u0003\u0003<\n%\u0014!C1t\u0019>tw-T1q+\u0011\u00119M!4\u0015\u0005\t%\u0007\u0003B3l\u0005\u0017\u00042A\u001cBg\t\u0019\t9\t\rb\u0001e\"9\u0011\u0011Z\u0011A\u0002\u0005-'AG'vi\u0006\u0014G.\u001a'p]\u001el\u0015\r]%ogR\fg\u000e^5bi>\u00148c\u0001\u0012\u0003DQ1!q\u001bBm\u00057\u00042!!(#\u0011\u001d\t)$\na\u0001\u0003oAq!a$&\u0001\u0004\t9\u0003\u0006\u0003\u0003`\u000e\r\u0001cAAOc\t\tR*\u001e;bE2,W*\u00199Xe\u0006\u0004\b/\u001a:\u0014\u0007E\u0012)\u0007\u0006\u0002\u0003`V\u0011!\u0011\u001e\t\u0006\u007f\u0006\u0015\u0011q \u000b\u0005\u0005{\u0012i\u000fC\u0005\u0003\u0006R\n\t\u00111\u0001\u0003jR1\u0011q By\u0005gDqAa$7\u0001\u0004\ty\u0010C\u0004\u0003\u0014Z\u0002\r!a@\u0015\t\u0005}(q\u001f\u0005\b\u00057;\u0004\u0019AA\u0000+\u0011\u0011Yp!\u0001\u0015\u0005\tu\b#B@\u0002\u0006\t}\bc\u00018\u0004\u0002\u00111\u0011qQ\u001dC\u0002IDq!!3(\u0001\u0004\tY\r"
)
public final class LongMapDeserializerResolver {
   public static JsonDeserializer findMapLikeDeserializer(final MapLikeType theType, final DeserializationConfig config, final BeanDescription beanDesc, final KeyDeserializer keyDeserializer, final TypeDeserializer elementTypeDeserializer, final JsonDeserializer elementDeserializer) {
      return LongMapDeserializerResolver$.MODULE$.findMapLikeDeserializer(theType, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
   }

   public static JsonDeserializer findMapDeserializer(final MapType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return LongMapDeserializerResolver$.MODULE$.findMapDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return LongMapDeserializerResolver$.MODULE$.findCollectionLikeDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findCollectionDeserializer(final CollectionType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return LongMapDeserializerResolver$.MODULE$.findCollectionDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findArrayDeserializer(final ArrayType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return LongMapDeserializerResolver$.MODULE$.findArrayDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findBeanDeserializer(final JavaType x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return LongMapDeserializerResolver$.MODULE$.findBeanDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findReferenceDeserializer(final ReferenceType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return LongMapDeserializerResolver$.MODULE$.findReferenceDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findTreeNodeDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return LongMapDeserializerResolver$.MODULE$.findTreeNodeDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findEnumDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return LongMapDeserializerResolver$.MODULE$.findEnumDeserializer(x$1, x$2, x$3);
   }

   public static boolean hasDeserializerFor(final DeserializationConfig x$1, final Class x$2) {
      return LongMapDeserializerResolver$.MODULE$.hasDeserializerFor(x$1, x$2);
   }

   private static class ImmutableLongMapDeserializer extends ContainerDeserializerBase implements ContextualDeserializer {
      private final MapLikeType mapType;
      private final MapDeserializer containerDeserializer;

      public JavaType getContentType() {
         return this.containerDeserializer.getContentType();
      }

      public JsonDeserializer getContentDeserializer() {
         return this.containerDeserializer.getContentDeserializer();
      }

      public JsonDeserializer createContextual(final DeserializationContext ctxt, final BeanProperty property) {
         MapDeserializer newDelegate = (MapDeserializer)this.containerDeserializer.createContextual(ctxt, property);
         return new ImmutableLongMapDeserializer(this.mapType, newDelegate);
      }

      public LongMap deserialize(final JsonParser jp, final DeserializationContext ctxt) {
         Map var4 = this.containerDeserializer.deserialize(jp, ctxt);
         if (var4 instanceof ImmutableMapWrapper) {
            ImmutableMapWrapper var5 = (ImmutableMapWrapper)var4;
            return var5.asLongMap();
         } else {
            throw new MatchError(var4);
         }
      }

      public LongMap deserialize(final JsonParser jp, final DeserializationContext ctxt, final LongMap intoValue) {
         LongMap newMap = this.deserialize(jp, ctxt);
         return newMap.isEmpty() ? intoValue : intoValue.$plus$plus(newMap);
      }

      public Object getEmptyValue(final DeserializationContext ctxt) {
         return .MODULE$.empty();
      }

      public ImmutableLongMapDeserializer(final MapLikeType mapType, final MapDeserializer containerDeserializer) {
         super(mapType);
         this.mapType = mapType;
         this.containerDeserializer = containerDeserializer;
      }
   }

   private static class MutableLongMapDeserializer extends ContainerDeserializerBase implements ContextualDeserializer {
      private final MapLikeType mapType;
      private final MapDeserializer containerDeserializer;

      public JavaType getContentType() {
         return this.containerDeserializer.getContentType();
      }

      public JsonDeserializer getContentDeserializer() {
         return this.containerDeserializer.getContentDeserializer();
      }

      public JsonDeserializer createContextual(final DeserializationContext ctxt, final BeanProperty property) {
         MapDeserializer newDelegate = (MapDeserializer)this.containerDeserializer.createContextual(ctxt, property);
         return new MutableLongMapDeserializer(this.mapType, newDelegate);
      }

      public scala.collection.mutable.LongMap deserialize(final JsonParser jp, final DeserializationContext ctxt) {
         Map var4 = this.containerDeserializer.deserialize(jp, ctxt);
         if (var4 instanceof MutableMapWrapper) {
            MutableMapWrapper var5 = (MutableMapWrapper)var4;
            return var5.asLongMap();
         } else {
            throw new MatchError(var4);
         }
      }

      public scala.collection.mutable.LongMap deserialize(final JsonParser jp, final DeserializationContext ctxt, final scala.collection.mutable.LongMap intoValue) {
         scala.collection.mutable.LongMap newMap = this.deserialize(jp, ctxt);
         return newMap.isEmpty() ? intoValue : intoValue.$plus$plus(newMap);
      }

      public Object getEmptyValue(final DeserializationContext ctxt) {
         return scala.collection.mutable.LongMap..MODULE$.empty();
      }

      public MutableLongMapDeserializer(final MapLikeType mapType, final MapDeserializer containerDeserializer) {
         super(mapType);
         this.mapType = mapType;
         this.containerDeserializer = containerDeserializer;
      }
   }

   private static class ImmutableLongMapInstantiator extends StdValueInstantiator {
      public boolean canCreateUsingDefault() {
         return true;
      }

      public ImmutableMapWrapper createUsingDefault(final DeserializationContext ctxt) {
         return new ImmutableMapWrapper();
      }

      public ImmutableLongMapInstantiator(final DeserializationConfig config, final MapLikeType mapType) {
         super(config, mapType);
      }
   }

   private static class MutableLongMapInstantiator extends StdValueInstantiator {
      public boolean canCreateUsingDefault() {
         return true;
      }

      public MutableMapWrapper createUsingDefault(final DeserializationContext ctxt) {
         return new MutableMapWrapper();
      }

      public MutableLongMapInstantiator(final DeserializationConfig config, final MapLikeType mapType) {
         super(config, mapType);
      }
   }

   private static class ImmutableMapWrapper extends AbstractMap {
      private LongMap baseMap;

      public LongMap baseMap() {
         return this.baseMap;
      }

      public void baseMap_$eq(final LongMap x$1) {
         this.baseMap = x$1;
      }

      public Object put(final Object k, final Object v) {
         if (k instanceof Number) {
            Number var6 = (Number)k;
            long l = var6.longValue();
            Option oldValue = this.baseMap().get(l);
            this.baseMap_$eq(this.baseMap().$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(l)), v)));
            return oldValue.orNull(scala..less.colon.less..MODULE$.refl());
         } else if (k instanceof String) {
            String var10 = (String)k;
            long l = scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(var10));
            Option oldValue = this.baseMap().get(l);
            this.baseMap_$eq(this.baseMap().$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(l)), v)));
            return oldValue.orNull(scala..less.colon.less..MODULE$.refl());
         } else {
            Option var15 = scala.Option..MODULE$.apply(k);
            String var10000;
            if (var15 instanceof Some) {
               Some var16 = (Some)var15;
               Object n = var16.value();
               var10000 = n.getClass().getName();
            } else {
               var10000 = "null";
            }

            String typeName = var10000;
            throw new IllegalArgumentException((new StringBuilder(38)).append("LongMap does not support keys of type ").append(typeName).toString());
         }
      }

      public Object get(final Object key) {
         if (key instanceof Number) {
            Number var4 = (Number)key;
            return this.baseMap().get(var4.longValue()).orNull(scala..less.colon.less..MODULE$.refl());
         } else if (key instanceof String) {
            String var5 = (String)key;
            return this.baseMap().get((long)scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(var5))).orNull(scala..less.colon.less..MODULE$.refl());
         } else {
            return scala.None..MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
         }
      }

      public Set entrySet() {
         return ((Map)scala.collection.JavaConverters..MODULE$.mapAsJavaMapConverter(this.baseMap()).asJava()).entrySet();
      }

      public LongMap asLongMap() {
         return this.baseMap();
      }

      public ImmutableMapWrapper() {
         this.baseMap = .MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      }
   }

   private static class MutableMapWrapper extends AbstractMap {
      private scala.collection.mutable.LongMap baseMap;

      public scala.collection.mutable.LongMap baseMap() {
         return this.baseMap;
      }

      public void baseMap_$eq(final scala.collection.mutable.LongMap x$1) {
         this.baseMap = x$1;
      }

      public Object put(final Object k, final Object v) {
         if (k instanceof Number) {
            Number var6 = (Number)k;
            long l = var6.longValue();
            Option oldValue = this.baseMap().get(l);
            this.baseMap().$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(l)), v));
            return oldValue.orNull(scala..less.colon.less..MODULE$.refl());
         } else if (k instanceof String) {
            String var10 = (String)k;
            long l = scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(var10));
            Option oldValue = this.baseMap().get(l);
            this.baseMap().$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToLong(l)), v));
            return oldValue.orNull(scala..less.colon.less..MODULE$.refl());
         } else {
            Option var15 = scala.Option..MODULE$.apply(k);
            String var10000;
            if (var15 instanceof Some) {
               Some var16 = (Some)var15;
               Object n = var16.value();
               var10000 = n.getClass().getName();
            } else {
               var10000 = "null";
            }

            String typeName = var10000;
            throw new IllegalArgumentException((new StringBuilder(38)).append("LongMap does not support keys of type ").append(typeName).toString());
         }
      }

      public Object get(final Object key) {
         if (key instanceof Number) {
            Number var4 = (Number)key;
            return this.baseMap().get(var4.longValue()).orNull(scala..less.colon.less..MODULE$.refl());
         } else if (key instanceof String) {
            String var5 = (String)key;
            return this.baseMap().get((long)scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(var5))).orNull(scala..less.colon.less..MODULE$.refl());
         } else {
            return scala.None..MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
         }
      }

      public Set entrySet() {
         return ((Map)scala.collection.JavaConverters..MODULE$.mutableMapAsJavaMapConverter(this.baseMap()).asJava()).entrySet();
      }

      public scala.collection.mutable.LongMap asLongMap() {
         return this.baseMap();
      }

      public MutableMapWrapper() {
         this.baseMap = scala.collection.mutable.LongMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      }
   }
}
