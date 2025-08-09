package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import com.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import java.lang.invoke.SerializedLambda;
import java.util.AbstractMap;
import java.util.Set;
import scala.MatchError;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterable;
import scala.collection.Map;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r]f!\u0002\u0017.\u0003\u0003Q\u0004\"B$\u0001\t\u0003AU\u0001\u00025\u0001\u0001%,Aa\u001c\u0001\u0001a\u0016!\u0011\u000f\u0001\u0001s\u0011%\t\t\u0002\u0001b\u0001\n\u0013\t\u0019\u0002\u0003\u0005\u0002,\u0001\u0001\u000b\u0011BA\u000b\u0011%\ti\u0003\u0001b\u0001\u000e\u0003\ty\u0003C\u0005\u0002N\u0001\u0011\rQ\"\u0001\u0002P!9\u0011\u0011\u000f\u0001\u0007\u0002\u0005M\u0004bBA9\u0001\u0011\u0005\u0011Q\u0013\u0005\b\u0003k\u0003A\u0011IA\\\u0011\u001d\u0011\u0019\u0002\u0001C\t\u0005+AqA!\u0010\u0001\t\u0013\u0011yD\u0002\u0004\u0003V\u0001!!q\u000b\u0005\u000b\u0005or!Q1A\u0005\u0002\te\u0004B\u0003B?\u001d\t\u0005\t\u0015!\u0003\u0003|!Q!q\u0010\b\u0003\u0002\u0003\u0006IA!!\t\r\u001dsA\u0011\u0001BD\u0011%\u0011yI\u0004a\u0001\n\u0013\u0011\t\nC\u0005\u0003\u001c:\u0001\r\u0011\"\u0003\u0003\u001e\"A!\u0011\u0016\b!B\u0013\u0011\u0019\nC\u0004\u0003,:!\tE!,\t\u000f\t]f\u0002\"\u0011\u0003:\"9!q\u0018\b\u0005B\t\u0005\u0007b\u0002Bp\u001d\u0011\u0005!\u0011\u001d\u0004\u0007\u0005S\u0004AAa;\t\u0015\u0005e'D!A!\u0002\u0013\tY\u000e\u0003\u0006\u0003zj\u0011\t\u0011)A\u0005\u0003\u0017Daa\u0012\u000e\u0005\u0002\tm\bbBB\u00025\u0011\u00053Q\u0001\u0005\b\u0007\u000fQB\u0011IB\u0005\r\u0019\u00199\u0002\u0001\u0003\u0004\u001a!Q!\u0011 \u0011\u0003\u0002\u0003\u0006I!a3\t\u0015\rM\u0002E!A!\u0002\u0013\u0019)\u0004\u0003\u0004HA\u0011\u000511\b\u0005\u0007\u000f\u0002\"\taa\u0011\t\u000f\r\u001d\u0004\u0005\"\u0011\u0004j!911\u000e\u0011\u0005B\r5\u0004bBB9A\u0011\u000531\u000f\u0005\b\u0007\u0017\u0003C\u0011IBG\u0011\u001d\u0019Y\t\tC!\u0007CCqaa+!\t\u0003\u001ai\u000bC\u0004\u00042\u0002\"Iaa-\u0003K\u001d+g.\u001a:jG6\u000b\u0007OR1di>\u0014\u0018\u0010R3tKJL\u0017\r\\5{KJ\u0014Vm]8mm\u0016\u0014(B\u0001\u00180\u0003\u0015!Wm]3s\u0015\t\u0001\u0014'A\u0003tG\u0006d\u0017M\u0003\u00023g\u00051Qn\u001c3vY\u0016T!\u0001N\u001b\u0002\u000f)\f7m[:p]*\u0011agN\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011\u0001O\u0001\u0004G>l7\u0001A\u000b\u0004w5s6C\u0001\u0001=!\tiDI\u0004\u0002?\u00056\tqH\u0003\u0002/\u0001*\u0011\u0011iM\u0001\tI\u0006$\u0018MY5oI&\u00111iP\u0001\u000e\t\u0016\u001cXM]5bY&TXM]:\n\u0005\u00153%\u0001\u0002\"bg\u0016T!aQ \u0002\rqJg.\u001b;?)\u0005I\u0005\u0003\u0002&\u0001\u0017vk\u0011!\f\t\u0003\u00196c\u0001\u0001B\u0003O\u0001\t\u0007qJ\u0001\u0002D\u0007V\u0019\u0001+W.\u0012\u0005E3\u0006C\u0001*U\u001b\u0005\u0019&\"\u0001\u0019\n\u0005U\u001b&a\u0002(pi\"Lgn\u001a\t\u0003%^K!\u0001W*\u0003\u0007\u0005s\u0017\u0010B\u0003[\u001b\n\u0007\u0001KA\u0001L\t\u0015aVJ1\u0001Q\u0005\u00051\u0006C\u0001'_\t\u0015y\u0006A1\u0001a\u0005\t\u0019e)\u0006\u0002QC\u0012)!M\u0018b\u0001G\n\t\u0001,F\u0002QI\u001a$Q!Z1C\u0002A\u0013Aa\u0018\u0013%c\u0011)q-\u0019b\u0001!\n!q\f\n\u00133\u0005)\u0019u\u000e\u001c7fGRLwN\\\u000b\u0004U2t\u0007\u0003\u0002'NW6\u0004\"\u0001\u00147\u0005\u000bi\u0013!\u0019\u0001)\u0011\u00051sG!\u0002/\u0003\u0005\u0004\u0001&a\u0002$bGR|'/\u001f\t\u0004\u0019z[%a\u0002\"vS2$WM]\u000b\u0005g~\f\u0019\u0001M\u0002u\u0003\u000f\u0001R!\u001e>|\u0003\u000bi\u0011A\u001e\u0006\u0003ob\fq!\\;uC\ndWM\u0003\u0002z'\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005E4\b#\u0002*}}\u0006\u0005\u0011BA?T\u0005\u0019!V\u000f\u001d7feA\u0011Aj \u0003\u00065\u0012\u0011\r\u0001\u0015\t\u0004\u0019\u0006\rA!\u0002/\u0005\u0005\u0004\u0001\u0006c\u0001'\u0002\b\u0011Y\u0011\u0011\u0002\u0003\u0002\u0002\u0003\u0005)\u0011AA\u0006\u0005\ryF%M\t\u0004#\u00065\u0001CBA\b\u0005y\f\t!D\u0001\u0001\u0003!y'M[\"mCN\u001cXCAA\u000b!\u0019\t9\"!\t\u0002&5\u0011\u0011\u0011\u0004\u0006\u0005\u00037\ti\"\u0001\u0003mC:<'BAA\u0010\u0003\u0011Q\u0017M^1\n\t\u0005\r\u0012\u0011\u0004\u0002\u0006\u00072\f7o\u001d\t\u0005\u0003/\t9#\u0003\u0003\u0002*\u0005e!AB(cU\u0016\u001cG/A\u0005pE*\u001cE.Y:tA\u0005a1\tT!T'~#u*T!J\u001dV\u0011\u0011\u0011\u0007\u0019\u0005\u0003g\tI\u0005\u0005\u0004\u00026\u0005\r\u0013q\t\b\u0005\u0003o\ty\u0004E\u0002\u0002:Mk!!a\u000f\u000b\u0007\u0005u\u0012(\u0001\u0004=e>|GOP\u0005\u0004\u0003\u0003\u001a\u0016A\u0002)sK\u0012,g-\u0003\u0003\u0002$\u0005\u0015#bAA!'B\u0019A*!\u0013\u0005\u0015\u0005-s!!A\u0001\u0002\u000b\u0005\u0001KA\u0002`II\n\u0011BZ1di>\u0014\u0018.Z:\u0016\u0005\u0005E\u0003CBA*\u0003;\n\u0019G\u0004\u0003\u0002V\u0005ec\u0002BA\u001d\u0003/J\u0011\u0001M\u0005\u0004\u00037\u001a\u0016a\u00029bG.\fw-Z\u0005\u0005\u0003?\n\tG\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u0015\r\tYf\u0015\t\u0007%r\f)'a\u001c1\t\u0005\u001d\u00141\u000e\t\u0007\u0003k\t\u0019%!\u001b\u0011\u00071\u000bY\u0007\u0002\u0006\u0002n!\t\t\u0011!A\u0003\u0002A\u00131a\u0018\u00134!\r\tyaA\u0001\u000bEVLG\u000eZ3s\r>\u0014XCBA;\u0003w\ny\b\u0006\u0005\u0002x\u0005\u0005\u0015QQAI!\u001d\ty\u0001BA=\u0003{\u00022\u0001TA>\t\u0015Q\u0016B1\u0001Q!\ra\u0015q\u0010\u0003\u00069&\u0011\r\u0001\u0015\u0005\b\u0003\u0007K\u0001\u0019AA8\u0003\u001d1\u0017m\u0019;pefDq!a\"\n\u0001\u0004\tI)A\u0004lKf$\u0016\u0010]3\u0011\t\u0005-\u0015QR\u0007\u0002\u0001&\u0019\u0011q\u0012!\u0003\u0011)\u000bg/\u0019+za\u0016Dq!a%\n\u0001\u0004\tI)A\u0005wC2,X\rV=qKV1\u0011qSAO\u0003C#\u0002\"!'\u0002$\u0006E\u00161\u0017\t\b\u0003\u001f!\u00111TAP!\ra\u0015Q\u0014\u0003\u00065*\u0011\r\u0001\u0015\t\u0004\u0019\u0006\u0005F!\u0002/\u000b\u0005\u0004\u0001\u0006bBAS\u0015\u0001\u0007\u0011qU\u0001\u0004G2\u001c\b\u0007BAU\u0003[\u0003b!!\u000e\u0002D\u0005-\u0006c\u0001'\u0002.\u0012Y\u0011qVAR\u0003\u0003\u0005\tQ!\u0001Q\u0005\ryF\u0005\u000e\u0005\b\u0003\u000fS\u0001\u0019AAE\u0011\u001d\t\u0019J\u0003a\u0001\u0003\u0013\u000bqCZ5oI6\u000b\u0007\u000fT5lK\u0012+7/\u001a:jC2L'0\u001a:\u0015\u001d\u0005e\u0016qYAl\u0003C\fY/!>\u0003\u0006A\"\u00111XAb!\u0019\tY)!0\u0002B&\u0019\u0011q\u0018!\u0003!)\u001bxN\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\bc\u0001'\u0002D\u0012Q\u0011QY\u0006\u0002\u0002\u0003\u0005)\u0011\u0001)\u0003\u0007}#c\u0007C\u0004\u0002J.\u0001\r!a3\u0002\u000fQDW\rV=qKB!\u0011QZAj\u001b\t\tyMC\u0002\u0002R\u0002\u000bA\u0001^=qK&!\u0011Q[Ah\u0005-i\u0015\r\u001d'jW\u0016$\u0016\u0010]3\t\u000f\u0005e7\u00021\u0001\u0002\\\u000611m\u001c8gS\u001e\u0004B!a#\u0002^&\u0019\u0011q\u001c!\u0003+\u0011+7/\u001a:jC2L'0\u0019;j_:\u001cuN\u001c4jO\"9\u00111]\u0006A\u0002\u0005\u0015\u0018\u0001\u00032fC:$Um]2\u0011\t\u0005-\u0015q]\u0005\u0004\u0003S\u0004%a\u0004\"fC:$Um]2sSB$\u0018n\u001c8\t\u000f\u000558\u00021\u0001\u0002p\u0006y1.Z=EKN,'/[1mSj,'\u000f\u0005\u0003\u0002\f\u0006E\u0018bAAz\u0001\ny1*Z=EKN,'/[1mSj,'\u000fC\u0004\u0002x.\u0001\r!!?\u0002/\u0015dW-\\3oiRK\b/\u001a#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\u0003BA~\u0005\u0003i!!!@\u000b\u0007\u0005}\b)\u0001\u0005kg>tG/\u001f9f\u0013\u0011\u0011\u0019!!@\u0003!QK\b/\u001a#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\bb\u0002B\u0004\u0017\u0001\u0007!\u0011B\u0001\u0014K2,W.\u001a8u\t\u0016\u001cXM]5bY&TXM\u001d\u0019\u0005\u0005\u0017\u0011y\u0001\u0005\u0004\u0002\f\u0006u&Q\u0002\t\u0004\u0019\n=Aa\u0003B\t\u0005\u000b\t\t\u0011!A\u0003\u0002A\u00131a\u0018\u00136\u00035\u0019xN\u001d;GC\u000e$xN]5fgR!!q\u0003B\u0015!\u0019\t\u0019F!\u0007\u0003\u001e%!!1DA1\u0005\r\u0019V-\u001d\t\u0007%r\u0014y\"a\u001c1\t\t\u0005\"Q\u0005\t\u0007\u0003k\t\u0019Ea\t\u0011\u00071\u0013)\u0003\u0002\u0006\u0003(1\t\t\u0011!A\u0003\u0002A\u00131a\u0018\u00139\u0011\u001d\ti\u0005\u0004a\u0001\u0005W\u0001b!a\u0015\u0003.\tE\u0012\u0002\u0002B\u0018\u0003C\u0012!\"\u00138eKb,GmU3r!\u0019\u0011FPa\r\u0002pA\"!Q\u0007B\u001d!\u0019\t)$a\u0011\u00038A\u0019AJ!\u000f\u0005\u0017\tm\"\u0011FA\u0001\u0002\u0003\u0015\t\u0001\u0015\u0002\u0004?\u0012:\u0014A\u00033piB\u0013x\u000eZ;diR1!\u0011\tB$\u0005#\u00022A\u0015B\"\u0013\r\u0011)e\u0015\u0002\u0004\u0013:$\bb\u0002B%\u001b\u0001\u0007!1J\u0001\u0002CB)!K!\u0014\u0003B%\u0019!qJ*\u0003\u000b\u0005\u0013(/Y=\t\u000f\tMS\u00021\u0001\u0003L\u0005\t!M\u0001\bCk&dG-\u001a:Xe\u0006\u0004\b/\u001a:\u0016\r\te#\u0011\u000eB7'\rq!1\f\t\t\u0005;\u0012\u0019Ga\u001a\u0003l5\u0011!q\f\u0006\u0005\u0005C\ni\"\u0001\u0003vi&d\u0017\u0002\u0002B3\u0005?\u00121\"\u00112tiJ\f7\r^'baB\u0019AJ!\u001b\u0005\u000bis!\u0019\u0001)\u0011\u00071\u0013i\u0007\u0002\u0004]\u001d\t\u0007!qN\t\u0004\u0005c2\u0006c\u0001*\u0003t%\u0019!QO*\u0003\r\u0005s\u0017PU3g\u0003\u001d\u0011W/\u001b7eKJ,\"Aa\u001f\u0011\u000f\u0005=AAa\u001a\u0003l\u0005A!-^5mI\u0016\u0014\b%A\u0006ue\u0006\u001c7NV1mk\u0016\u001c\bc\u0001*\u0003\u0004&\u0019!QQ*\u0003\u000f\t{w\u000e\\3b]R1!\u0011\u0012BF\u0005\u001b\u0003r!a\u0004\u000f\u0005O\u0012Y\u0007C\u0004\u0003xI\u0001\rAa\u001f\t\u000f\t}$\u00031\u0001\u0003\u0002\u00069!-Y:f\u001b\u0006\u0004XC\u0001BJ!\u001d\u0011)Ja&W\u0005Wj\u0011\u0001_\u0005\u0004\u00053C(aA'ba\u0006Y!-Y:f\u001b\u0006\u0004x\fJ3r)\u0011\u0011yJ!*\u0011\u0007I\u0013\t+C\u0002\u0003$N\u0013A!\u00168ji\"I!q\u0015\u000b\u0002\u0002\u0003\u0007!1S\u0001\u0004q\u0012\n\u0014\u0001\u00032bg\u0016l\u0015\r\u001d\u0011\u0002\u0007A,H\u000f\u0006\u0004\u0003l\t=&1\u0017\u0005\b\u0005c3\u0002\u0019\u0001B4\u0003\u0005Y\u0007b\u0002B[-\u0001\u0007!1N\u0001\u0002m\u0006\u0019q-\u001a;\u0015\t\t-$1\u0018\u0005\u0007\u0005{;\u0002\u0019\u0001,\u0002\u0007-,\u00170\u0001\u0005f]R\u0014\u0018pU3u)\t\u0011\u0019\r\u0005\u0004\u0003^\t\u0015'\u0011Z\u0005\u0005\u0005\u000f\u0014yFA\u0002TKR\u0004\u0002Ba3\u0003Z\n\u001d$1\u000e\b\u0005\u0005\u001b\u0014)N\u0004\u0003\u0003P\nMg\u0002BA\u001d\u0005#L!!a\b\n\t\t\u0005\u0014QD\u0005\u0005\u0005/\u0014y&A\u0002NCBLAAa7\u0003^\n)QI\u001c;ss*!!q\u001bB0\u0003=\u0019X\r^%oSRL\u0017\r\u001c,bYV,G\u0003\u0002BP\u0005GDqA!:\u001a\u0001\u0004\u00119/\u0001\u0003j]&$\bcBA\b\u0005\t\u001d$1\u000e\u0002\r\u0013:\u001cH/\u00198uS\u0006$xN]\n\u00045\t5\b\u0003\u0002Bx\u0005kl!A!=\u000b\u0007\tMx(A\u0002ti\u0012LAAa>\u0003r\n!2\u000b\u001e3WC2,X-\u00138ti\u0006tG/[1u_J\fq!\\1q)f\u0004X\r\u0006\u0004\u0003~\n}8\u0011\u0001\t\u0004\u0003\u001fQ\u0002bBAm;\u0001\u0007\u00111\u001c\u0005\b\u0005sl\u0002\u0019AAf\u0003U\u0019\u0017M\\\"sK\u0006$X-V:j]\u001e$UMZ1vYR$\"A!!\u0002%\r\u0014X-\u0019;f+NLgn\u001a#fM\u0006,H\u000e\u001e\u000b\u0005\u0007\u0017\u0019i\u0001E\u0004\u0002\u00109\u0011\tH!\u001d\t\u000f\r=q\u00041\u0001\u0004\u0012\u0005!1\r\u001e=u!\u0011\tYia\u0005\n\u0007\rU\u0001I\u0001\fEKN,'/[1mSj\fG/[8o\u0007>tG/\u001a=u\u00051!Um]3sS\u0006d\u0017N_3s+\u0019\u0019Yba\n\u0004,M)\u0001e!\b\u0004.A1!q^B\u0010\u0007GIAa!\t\u0003r\nI2i\u001c8uC&tWM\u001d#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014()Y:f!\u0019aUj!\n\u0004*A\u0019Aja\n\u0005\u000bi\u0003#\u0019\u0001)\u0011\u00071\u001bY\u0003B\u0003]A\t\u0007\u0001\u000bE\u0002?\u0007_I1a!\r@\u0005Y\u0019uN\u001c;fqR,\u0018\r\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018!F2p]R\f\u0017N\\3s\t\u0016\u001cXM]5bY&TXM\u001d\t\u0005\u0005_\u001c9$\u0003\u0003\u0004:\tE(aD'ba\u0012+7/\u001a:jC2L'0\u001a:\u0015\r\ru2qHB!!\u001d\ty\u0001IB\u0013\u0007SAqA!?$\u0001\u0004\tY\rC\u0004\u00044\r\u0002\ra!\u000e\u0015\u0019\ru2QIB$\u0007#\u001a)fa\u0019\t\u000f\teH\u00051\u0001\u0002L\"91\u0011\n\u0013A\u0002\r-\u0013!\u0005<bYV,\u0017J\\:uC:$\u0018.\u0019;peB\u0019ah!\u0014\n\u0007\r=sHA\tWC2,X-\u00138ti\u0006tG/[1u_JDqaa\u0015%\u0001\u0004\ty/\u0001\u0005lKf$Um]3s\u0011\u001d\u00199\u0006\na\u0001\u00073\n!B^1mk\u0016$Um]3sa\u0011\u0019Yfa\u0018\u0011\r\u0005-\u0015QXB/!\ra5q\f\u0003\f\u0007C\u001a)&!A\u0001\u0002\u000b\u0005\u0001K\u0001\u0003`IE\u0002\u0004bBB3I\u0001\u0007\u0011\u0011`\u0001\u000fm\u0006dW/\u001a+za\u0016$Um]3s\u000399W\r^\"p]R,g\u000e\u001e+za\u0016$\"!!#\u0002-\u001d,GoQ8oi\u0016tG\u000fR3tKJL\u0017\r\\5{KJ$\"aa\u001c\u0011\r\u0005-\u0015Q\u0018B9\u0003A\u0019'/Z1uK\u000e{g\u000e^3yiV\fG\u000e\u0006\u0004\u0004v\r}4\u0011\u0011\u0019\u0005\u0007o\u001aY\b\u0005\u0004\u0002\f\u0006u6\u0011\u0010\t\u0004\u0019\u000emDACB?O\u0005\u0005\t\u0011!B\u0001!\n!q\fJ\u00192\u0011\u001d\u0019ya\na\u0001\u0007#Aqaa!(\u0001\u0004\u0019))\u0001\u0005qe>\u0004XM\u001d;z!\u0011\tYia\"\n\u0007\r%\u0005I\u0001\u0007CK\u0006t\u0007K]8qKJ$\u00180A\u0006eKN,'/[1mSj,GCBB\u0012\u0007\u001f\u001by\nC\u0004\u0004\u0012\"\u0002\raa%\u0002\u0005)\u0004\b\u0003BBK\u00077k!aa&\u000b\u0007\re5'\u0001\u0003d_J,\u0017\u0002BBO\u0007/\u0013!BS:p]B\u000b'o]3s\u0011\u001d\u0019y\u0001\u000ba\u0001\u0007#!\u0002ba\t\u0004$\u000e\u00156q\u0015\u0005\b\u0007#K\u0003\u0019ABJ\u0011\u001d\u0019y!\u000ba\u0001\u0007#Aqa!+*\u0001\u0004\u0019\u0019#A\u0005j]R|g+\u00197vK\u0006iq-\u001a;F[B$\u0018PV1mk\u0016$B!!\n\u00040\"91q\u0002\u0016A\u0002\rE\u0011!\u00058fo\n+\u0018\u000e\u001c3fe^\u0013\u0018\r\u001d9feR!11BB[\u0011\u001d\u0019ya\u000ba\u0001\u0007#\u0001"
)
public abstract class GenericMapFactoryDeserializerResolver extends Deserializers.Base {
   private final Class com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$$objClass = Object.class;

   public Class com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$$objClass() {
      return this.com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$$objClass;
   }

   public abstract Class CLASS_DOMAIN();

   public abstract Iterable factories();

   public abstract Builder builderFor(final Object factory, final JavaType keyType, final JavaType valueType);

   public Builder builderFor(final Class cls, final JavaType keyType, final JavaType valueType) {
      return (Builder)this.factories().find((x$1) -> BoxesRunTime.boxToBoolean($anonfun$builderFor$1(cls, x$1))).map((x$2) -> x$2._2()).map((x$3) -> this.builderFor(x$3, keyType, valueType)).getOrElse(() -> {
         throw new IllegalStateException((new StringBuilder(86)).append("Could not find deserializer for ").append(cls.getCanonicalName()).append(". File issue on github:fasterxml/jackson-scala-module.").toString());
      });
   }

   public JsonDeserializer findMapLikeDeserializer(final MapLikeType theType, final DeserializationConfig config, final BeanDescription beanDesc, final KeyDeserializer keyDeserializer, final TypeDeserializer elementTypeDeserializer, final JsonDeserializer elementDeserializer) {
      if (!this.CLASS_DOMAIN().isAssignableFrom(theType.getRawClass())) {
         return (JsonDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
      } else {
         Instantiator instantiator = new Instantiator(config, theType);
         return new Deserializer(theType, instantiator, keyDeserializer, elementDeserializer, elementTypeDeserializer);
      }
   }

   public Seq sortFactories(final IndexedSeq factories) {
      Tuple2[] cs = (Tuple2[])factories.toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      ListBuffer output = new ListBuffer();
      int[] remaining = (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])cs), (x$4) -> BoxesRunTime.boxToInteger($anonfun$sortFactories$1(x$4)), scala.reflect.ClassTag..MODULE$.Int());
      int[][] adjMatrix = (int[][])scala.Array..MODULE$.ofDim(cs.length, cs.length, scala.reflect.ClassTag..MODULE$.Int());
      scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps((Object[])cs)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps((Object[])cs)).foreach$mVc$sp((JFunction1.mcVI.sp)(j) -> {
            Tuple2 var7 = cs[i];
            if (var7 != null) {
               Class ic = (Class)var7._1();
               Tuple2 var10 = cs[j];
               if (var10 != null) {
                  Class jc = (Class)var10._1();
                  if (i != j && ic.isAssignableFrom(jc)) {
                     adjMatrix[i][j] = 1;
                  }
               } else {
                  throw new MatchError(var10);
               }
            } else {
               throw new MatchError(var7);
            }
         }));

      while(output.length() < cs.length) {
         int startLength = output.length();
         scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps((Object[])cs)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            if (remaining[i] == 1 && this.dotProduct(adjMatrix[i], remaining) == 0) {
               output.$plus$eq(factories.apply(i));
               remaining[i] = 0;
            }
         });
         if (output.length() == startLength) {
            throw new IllegalStateException("Companions contain a cycle.");
         }
      }

      return output.toSeq();
   }

   private int dotProduct(final int[] a, final int[] b) {
      if (a.length != b.length) {
         throw new IllegalArgumentException();
      } else {
         return BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.intArrayOps(a)).map((JFunction1.mcII.sp)(i) -> a[i] * b[i]).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$builderFor$1(final Class cls$1, final Tuple2 x$1) {
      return ((Class)x$1._1()).isAssignableFrom(cls$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$sortFactories$1(final Tuple2 x$4) {
      return 1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class BuilderWrapper extends AbstractMap {
      private final Builder builder;
      private final boolean trackValues;
      private Map baseMap;
      // $FF: synthetic field
      public final GenericMapFactoryDeserializerResolver $outer;

      public Builder builder() {
         return this.builder;
      }

      private Map baseMap() {
         return this.baseMap;
      }

      private void baseMap_$eq(final Map x$1) {
         this.baseMap = x$1;
      }

      public Object put(final Object k, final Object v) {
         this.builder().$plus$eq(new Tuple2(k, v));
         if (this.trackValues) {
            Object oldValue = this.get(k);
            this.baseMap_$eq((Map)this.baseMap().$plus(new Tuple2(k, v)));
            return oldValue;
         } else {
            return .MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
         }
      }

      public Object get(final Object key) {
         return this.baseMap().get(key).orNull(scala..less.colon.less..MODULE$.refl());
      }

      public Set entrySet() {
         throw new UnsupportedOperationException();
      }

      public void setInitialValue(final Object init) {
         ((Map)init).foreach(scala.Function..MODULE$.tupled((k, v) -> this.put(k, v)));
         this.baseMap_$eq((Map)init);
      }

      // $FF: synthetic method
      public GenericMapFactoryDeserializerResolver com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$BuilderWrapper$$$outer() {
         return this.$outer;
      }

      public BuilderWrapper(final Builder builder, final boolean trackValues) {
         this.builder = builder;
         this.trackValues = trackValues;
         if (GenericMapFactoryDeserializerResolver.this == null) {
            throw null;
         } else {
            this.$outer = GenericMapFactoryDeserializerResolver.this;
            super();
            this.baseMap = (Map)scala.collection.Map..MODULE$.empty();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private class Instantiator extends StdValueInstantiator {
      private final MapLikeType mapType;
      // $FF: synthetic field
      public final GenericMapFactoryDeserializerResolver $outer;

      public boolean canCreateUsingDefault() {
         return true;
      }

      public BuilderWrapper createUsingDefault(final DeserializationContext ctxt) {
         boolean var4;
         label19: {
            label18: {
               if (ctxt.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES)) {
                  Class var10000 = this.com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$Instantiator$$$outer().com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$$objClass();
                  Class var3 = this.mapType.getContentType().getRawClass();
                  if (var10000 == null) {
                     if (var3 == null) {
                        break label18;
                     }
                  } else if (var10000.equals(var3)) {
                     break label18;
                  }
               }

               var4 = false;
               break label19;
            }

            var4 = true;
         }

         boolean trackValues = var4;
         return this.com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$Instantiator$$$outer().new BuilderWrapper(this.com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$Instantiator$$$outer().builderFor(this.mapType.getRawClass(), this.mapType.getKeyType(), this.mapType.getContentType()), trackValues);
      }

      // $FF: synthetic method
      public GenericMapFactoryDeserializerResolver com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$Instantiator$$$outer() {
         return this.$outer;
      }

      public Instantiator(final DeserializationConfig config, final MapLikeType mapType) {
         this.mapType = mapType;
         if (GenericMapFactoryDeserializerResolver.this == null) {
            throw null;
         } else {
            this.$outer = GenericMapFactoryDeserializerResolver.this;
            super(config, mapType);
         }
      }
   }

   private class Deserializer extends ContainerDeserializerBase implements ContextualDeserializer {
      private final MapLikeType mapType;
      private final MapDeserializer containerDeserializer;
      // $FF: synthetic field
      public final GenericMapFactoryDeserializerResolver $outer;

      public JavaType getContentType() {
         return this.containerDeserializer.getContentType();
      }

      public JsonDeserializer getContentDeserializer() {
         return this.containerDeserializer.getContentDeserializer();
      }

      public JsonDeserializer createContextual(final DeserializationContext ctxt, final BeanProperty property) {
         MapDeserializer newDelegate = (MapDeserializer)this.containerDeserializer.createContextual(ctxt, property);
         return this.com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$Deserializer$$$outer().new Deserializer(this.mapType, newDelegate);
      }

      public Object deserialize(final JsonParser jp, final DeserializationContext ctxt) {
         java.util.Map var4 = this.containerDeserializer.deserialize(jp, ctxt);
         if (var4 instanceof BuilderWrapper && ((BuilderWrapper)var4).com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$BuilderWrapper$$$outer() == this.com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$Deserializer$$$outer()) {
            BuilderWrapper var5 = (BuilderWrapper)var4;
            return var5.builder().result();
         } else {
            throw new MatchError(var4);
         }
      }

      public Object deserialize(final JsonParser jp, final DeserializationContext ctxt, final Object intoValue) {
         BuilderWrapper bw = this.newBuilderWrapper(ctxt);
         bw.setInitialValue(intoValue);
         java.util.Map var6 = this.containerDeserializer.deserialize(jp, ctxt, bw);
         if (var6 instanceof BuilderWrapper && ((BuilderWrapper)var6).com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$BuilderWrapper$$$outer() == this.com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$Deserializer$$$outer()) {
            BuilderWrapper var7 = (BuilderWrapper)var6;
            return var7.builder().result();
         } else {
            throw new MatchError(var6);
         }
      }

      public Object getEmptyValue(final DeserializationContext ctxt) {
         BuilderWrapper bw = this.newBuilderWrapper(ctxt);
         return bw.builder().result();
      }

      private BuilderWrapper newBuilderWrapper(final DeserializationContext ctxt) {
         return (BuilderWrapper)this.containerDeserializer.getValueInstantiator().createUsingDefault(ctxt);
      }

      // $FF: synthetic method
      public GenericMapFactoryDeserializerResolver com$fasterxml$jackson$module$scala$deser$GenericMapFactoryDeserializerResolver$Deserializer$$$outer() {
         return this.$outer;
      }

      public Deserializer(final MapLikeType mapType, final MapDeserializer containerDeserializer) {
         this.mapType = mapType;
         this.containerDeserializer = containerDeserializer;
         if (GenericMapFactoryDeserializerResolver.this == null) {
            throw null;
         } else {
            this.$outer = GenericMapFactoryDeserializerResolver.this;
            super(mapType);
         }
      }

      public Deserializer(final MapLikeType mapType, final ValueInstantiator valueInstantiator, final KeyDeserializer keyDeser, final JsonDeserializer valueDeser, final TypeDeserializer valueTypeDeser) {
         this(mapType, new MapDeserializer(mapType, valueInstantiator, keyDeser, valueDeser, valueTypeDeser));
      }
   }
}
