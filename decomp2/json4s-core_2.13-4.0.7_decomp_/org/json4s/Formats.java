package org.json4s;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import org.json4s.prefs.EmptyValueStrategy;
import org.json4s.prefs.ExtractionNullStrategy;
import org.json4s.reflect.ParameterNameReader;
import org.json4s.reflect.ParanamerReader$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.math.Ordering;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015\u0015r!\u0002+V\u0011\u0003Qf!\u0002/V\u0011\u0003i\u0006\"\u00027\u0002\t\u0003i\u0007\"\u00028\u0002\t\u0003y\u0007bBA\b\u0003\u0011\u0005\u0011\u0011\u0003\u0005\t\u0003S\tA\u0011A+\u0002,!AA1]\u0001\u0005\u0002U#)\u000f\u0003\u0005\u0005x\u0006!\t!\u0016C}\u0011!!Y*\u0001C\u0001+\u0016\u0005\u0001\u0002\u0003CZ\u0003\u0011\u0005Q+\"\u0003\t\u0011\u0011\r\u0017\u0001\"\u0001V\u000b#A\u0011\"\"\u0007\u0002\u0003\u0003%I!b\u0007\u0007\u0011q+\u0006\u0013aA\u0001\u0003wAq!a\u0015\r\t\u0003\t)\u0006C\u0004\u0002^11\t!a\u0018\t\u000f\u0005\u001dD\u0002\"\u0001\u0002j!9\u0011\u0011\u000f\u0007\u0005\u0002\u0005M\u0004bBAE\u0019\u0011\u0005\u00111\u0012\u0005\b\u0003;cA\u0011AAP\u0011\u001d\t\t\f\u0004C\u0001\u0003gCq!a9\r\t\u0003\t)\u000fC\u0004\u0002n2!\t!!:\t\u000f\u0005=H\u0002\"\u0001\u0002r\"9!\u0011\u0002\u0007\u0005\u0002\t-\u0001b\u0002B\u000e\u0019\u0011\u0005!Q\u0004\u0005\b\u0005WaA\u0011AAs\u0011\u001d\u0011i\u0003\u0004C\u0001\u0003KDqAa\f\r\t\u0003\t)\u000fC\u0004\u000321!\t!!:\t\u000f\tMB\u0002\"\u0001\u0002f\"9!Q\u0007\u0007\u0005\u0002\u0005\u0015\bb\u0002B\u001c\u0019\u0011\u0005!\u0011\b\u0005\b\u0005\u000bbA\u0011\u0001B$\u0011\u001d\u0011y\u0005\u0004C\u0005\u0005#B\u0011B!;\r#\u0003%IAa;\t\u0013\r\u0005A\"%A\u0005\n\r\r\u0001\"CB\u0004\u0019E\u0005I\u0011BB\u0005\u0011%\u0019i\u0001DI\u0001\n\u0013\u0019y\u0001C\u0005\u0004\u001e1\t\n\u0011\"\u0003\u0004 !I1Q\u0006\u0007\u0012\u0002\u0013%1q\u0006\u0005\n\u0007\u000fb\u0011\u0013!C\u0005\u0007\u0013B\u0011ba\u0016\r#\u0003%Ia!\u0017\t\u0013\ruC\"%A\u0005\n\re\u0003\"CB0\u0019E\u0005I\u0011BB1\u0011%\u0019)\u0007DI\u0001\n\u0013\u00199\u0007C\u0005\u0004x1\t\n\u0011\"\u0003\u0004z!I1Q\u0010\u0007\u0012\u0002\u0013%1\u0011\f\u0005\n\u0007\u007fb\u0011\u0013!C\u0005\u00073B\u0011b!!\r#\u0003%Ia!\u0017\t\u0013\r\rE\"%A\u0005\n\re\u0003\"CBC\u0019E\u0005I\u0011BB-\u0011%\u00199\tDI\u0001\n\u0013\u0019I\tC\u0005\u0004\u000e2\t\n\u0011\"\u0003\u0004Z!91q\u0012\u0007\u0005\u0002\rE\u0005bBBJ\u0019\u0011\u00051\u0011\u0013\u0005\b\u0007+cA\u0011ABI\u0011\u001d\u00199\n\u0004C\u0001\u0007#Cqa!'\r\t\u0003\u0019Y\nC\u0004\u000442!\ta!%\t\u000f\rUF\u0002\"\u0001\u0004\u0012\"91q\u0017\u0007\u0005\u0002\re\u0006bBB`\u0019\u0011\u00051\u0011\u0013\u0005\b\u0007\u0003dA\u0011ABI\u0011\u001d\u0019\u0019\r\u0004C\u0001\u0007#Cqa!2\r\t\u0003\u0019\t\nC\u0004\u0004H2!\ta!%\t\u000f\r%G\u0002\"\u0001\u0004\u0012\"911\u001a\u0007\u0005\u0002\rE\u0005bBBg\u0019\u0011\u00051\u0011\u0013\u0005\b\u0007GdA\u0011ABs\u0011\u001d\u0019I\u000f\u0004C\u0001\u0007#Cqaa;\r\t\u0003\u0019i\u000fC\u0004\u0004l2!\taa=\t\u000f\r-H\u0002\"\u0001\u0005\u0004!911\u001e\u0007\u0005\u0002\u0011E\u0001b\u0002C\u0010\u0019\u0011\u0005A\u0011\u0005\u0005\b\toaA\u0011\u0001C\u001d\u0011\u001d!I\u0005\u0004C\u0001\t\u0017Bqaa;\r\t\u0003!i\u0006\u0003\u0005\u0005l1!\t!\u0016C7\u0011\u001d\tI\u0003\u0004C\u0001\t\u001bCq\u0001b'\r\t\u0003!i\nC\u0004\u000542!\t\u0001\".\t\u000f\u0011\rG\u0002\"\u0001\u0005F\u00069ai\u001c:nCR\u001c(B\u0001,X\u0003\u0019Q7o\u001c85g*\t\u0001,A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\\\u00035\tQKA\u0004G_Jl\u0017\r^:\u0014\u0007\u0005qF\r\u0005\u0002`E6\t\u0001MC\u0001b\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0007M\u0001\u0004B]f\u0014VM\u001a\t\u0003K*l\u0011A\u001a\u0006\u0003O\"\f!![8\u000b\u0003%\fAA[1wC&\u00111N\u001a\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003i\u000bAA]3bIV\u0011\u0001\u000f\u001e\u000b\u0004c\u0006\u0015AC\u0001:~!\t\u0019H\u000f\u0004\u0001\u0005\u000bU\u001c!\u0019\u0001<\u0003\u0003Q\u000b\"a\u001e>\u0011\u0005}C\u0018BA=a\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aX>\n\u0005q\u0004'aA!os\")ap\u0001a\u0002\u007f\u00061!/Z1eKJ\u0004BaWA\u0001e&\u0019\u00111A+\u0003\rI+\u0017\rZ3s\u0011\u001d\t9a\u0001a\u0001\u0003\u0013\tAA[:p]B\u00191,a\u0003\n\u0007\u00055QK\u0001\u0004K-\u0006dW/Z\u0001\u0006oJLG/Z\u000b\u0005\u0003'\t\u0019\u0003\u0006\u0003\u0002\u0016\u0005\u0015B\u0003BA\u0005\u0003/Aq!!\u0007\u0005\u0001\b\tY\"\u0001\u0004xe&$XM\u001d\t\u00067\u0006u\u0011\u0011E\u0005\u0004\u0003?)&AB,sSR,'\u000fE\u0002t\u0003G!Q!\u001e\u0003C\u0002YDq!a\n\u0005\u0001\u0004\t\t#A\u0002pE*\f\u0001cY;ti>l7+\u001a:jC2L'0\u001a:\u0015\t\u00055Bq\u001c\u000b\u0005\u0003_\t)\u0004\u0005\u0004`\u0003cQ\u0018\u0011B\u0005\u0004\u0003g\u0001'a\u0004)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8\t\u000f\u0005]R\u0001q\u0001\u0002:\u00051am\u001c:nCR\u0004\"a\u0017\u0007\u0014\t1q\u0016Q\b\t\u0005\u0003\u007f\tyE\u0004\u0003\u0002B\u0005-c\u0002BA\"\u0003\u0013j!!!\u0012\u000b\u0007\u0005\u001d\u0013,\u0001\u0004=e>|GOP\u0005\u0002C&\u0019\u0011Q\n1\u0002\u000fA\f7m[1hK&\u00191.!\u0015\u000b\u0007\u00055\u0003-\u0001\u0004%S:LG\u000f\n\u000b\u0003\u0003/\u00022aXA-\u0013\r\tY\u0006\u0019\u0002\u0005+:LG/\u0001\u0006eCR,gi\u001c:nCR,\"!!\u0019\u0011\u0007m\u000b\u0019'C\u0002\u0002fU\u0013!\u0002R1uK\u001a{'/\\1u\u0003%!\u0018\u0010]3IS:$8/\u0006\u0002\u0002lA\u00191,!\u001c\n\u0007\u0005=TKA\u0005UsB,\u0007*\u001b8ug\u0006\t2-^:u_6\u001cVM]5bY&TXM]:\u0016\u0005\u0005U\u0004CBA \u0003o\nY(\u0003\u0003\u0002z\u0005E#\u0001\u0002'jgR\u0004D!! \u0002\u0006B)1,a \u0002\u0004&\u0019\u0011\u0011Q+\u0003\u0015M+'/[1mSj,'\u000fE\u0002t\u0003\u000b#!\"a\"\u0011\u0003\u0003\u0005\tQ!\u0001w\u0005\ryF%M\u0001\u0010e&\u001c\u0007nU3sS\u0006d\u0017N_3sgV\u0011\u0011Q\u0012\t\u0007\u0003\u007f\t9(a$1\t\u0005E\u0015\u0011\u0014\t\u00067\u0006M\u0015qS\u0005\u0004\u0003++&A\u0004*jG\"\u001cVM]5bY&TXM\u001d\t\u0004g\u0006eEACAN#\u0005\u0005\t\u0011!B\u0001m\n\u0019q\f\n\u001a\u0002)\r,8\u000f^8n\u0017\u0016L8+\u001a:jC2L'0\u001a:t+\t\t\t\u000b\u0005\u0004\u0002@\u0005]\u00141\u0015\u0019\u0005\u0003K\u000bi\u000bE\u0003\\\u0003O\u000bY+C\u0002\u0002*V\u0013QbS3z'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bcA:\u0002.\u0012Q\u0011q\u0016\n\u0002\u0002\u0003\u0005)\u0011\u0001<\u0003\u0007}#3'\u0001\tgS\u0016dGmU3sS\u0006d\u0017N_3sgV\u0011\u0011Q\u0017\t\u0007\u0003\u007f\t9(a.\u0011\u000f}\u000bI,!0\u0002V&\u0019\u00111\u00181\u0003\rQ+\b\u000f\\33a\u0011\ty,!5\u0011\r\u0005\u0005\u0017\u0011ZAh\u001d\u0011\t\u0019-!2\u0011\u0007\u0005\r\u0003-C\u0002\u0002H\u0002\fa\u0001\u0015:fI\u00164\u0017\u0002BAf\u0003\u001b\u0014Qa\u00117bgNT1!a2a!\r\u0019\u0018\u0011\u001b\u0003\u000b\u0003'\u001c\u0012\u0011!A\u0001\u0006\u00031(aA0%iA\"\u0011q[Ap!\u0015Y\u0016\u0011\\Ao\u0013\r\tY.\u0016\u0002\u0010\r&,G\u000eZ*fe&\fG.\u001b>feB\u00191/a8\u0005\u0015\u0005\u00058#!A\u0001\u0002\u000b\u0005aOA\u0002`IU\n1b^1oiN\u0014\u0015nZ%oiV\u0011\u0011q\u001d\t\u0004?\u0006%\u0018bAAvA\n9!i\\8mK\u0006t\u0017aD<b]R\u001c()[4EK\u000eLW.\u00197\u0002\u0015A\u0014\u0018.\\5uSZ,7/\u0006\u0002\u0002tB1\u0011\u0011YA{\u0003sLA!a>\u0002N\n\u00191+\u001a;\u0011\t\u0005m(QA\u0007\u0003\u0003{TA!a@\u0003\u0002\u00059!/\u001a4mK\u000e$(b\u0001B\u0002Q\u0006!A.\u00198h\u0013\u0011\u00119!!@\u0003\tQK\b/Z\u0001\u000bG>l\u0007/\u00198j_:\u001cXC\u0001B\u0007!\u0019\ty$a\u001e\u0003\u0010A1q,!/\u0003\u0012y\u0003DAa\u0005\u0003\u0018A1\u0011\u0011YAe\u0005+\u00012a\u001dB\f\t)\u0011IbFA\u0001\u0002\u0003\u0015\tA\u001e\u0002\u0004?\u00122\u0014AF3yiJ\f7\r^5p]:+H\u000e\\*ue\u0006$XmZ=\u0016\u0005\t}\u0001\u0003\u0002B\u0011\u0005Oi!Aa\t\u000b\u0007\t\u0015R+A\u0003qe\u001647/\u0003\u0003\u0003*\t\r\"AF#yiJ\f7\r^5p]:+H\u000e\\*ue\u0006$XmZ=\u0002'M$(/[2u\u001fB$\u0018n\u001c8QCJ\u001c\u0018N\\4\u0002+M$(/[2u\u0003J\u0014\u0018-_#yiJ\f7\r^5p]\u0006\u00192\u000f\u001e:jGRl\u0015\r]#yiJ\f7\r^5p]\u0006\u0019\u0012\r\\<bsN,5oY1qKVs\u0017nY8eK\u0006Q2\u000f\u001e:jGR4\u0015.\u001a7e\t\u0016\u001cXM]5bY&T\u0018\r^5p]\u0006i2m\u001c8tS\u0012,'oQ8na\u0006t\u0017n\u001c8D_:\u001cHO];di>\u00148/A\nqCJ\fW.\u001a;fe:\u000bW.\u001a*fC\u0012,'/\u0006\u0002\u0003<A!!Q\bB!\u001b\t\u0011yDC\u0002\u0002\u0000VKAAa\u0011\u0003@\t\u0019\u0002+\u0019:b[\u0016$XM\u001d(b[\u0016\u0014V-\u00193fe\u0006\u0011R-\u001c9usZ\u000bG.^3TiJ\fG/Z4z+\t\u0011I\u0005\u0005\u0003\u0003\"\t-\u0013\u0002\u0002B'\u0005G\u0011!#R7qif4\u0016\r\\;f'R\u0014\u0018\r^3hs\u0006!1m\u001c9z)!\nIDa\u0015\u0003X\tm#q\fB8\u0005\u007f\u0012YJa+\u00030\nM&q\u0017Be\u0005\u001b\u0014\tN!6\u0003Z\nu'\u0011\u001dBs\u0011%\u0011)&\tI\u0001\u0002\u0004\t\t'A\u0006x\t\u0006$XMR8s[\u0006$\b\"\u0003B-CA\u0005\t\u0019\u0001B\u001e\u0003Q9\b+\u0019:b[\u0016$XM\u001d(b[\u0016\u0014V-\u00193fe\"I!QL\u0011\u0011\u0002\u0003\u0007\u00111N\u0001\u000boRK\b/\u001a%j]R\u001c\b\"\u0003B1CA\u0005\t\u0019\u0001B2\u0003I98)^:u_6\u001cVM]5bY&TXM]:\u0011\r\u0005}\u0012q\u000fB3a\u0011\u00119Ga\u001b\u0011\u000bm\u000byH!\u001b\u0011\u0007M\u0014Y\u0007B\u0006\u0003n\t}\u0013\u0011!A\u0001\u0006\u00031(aA0%o!I!\u0011O\u0011\u0011\u0002\u0003\u0007!1O\u0001\u0016o\u000e+8\u000f^8n\u0017\u0016L8+\u001a:jC2L'0\u001a:t!\u0019\ty$a\u001e\u0003vA\"!q\u000fB>!\u0015Y\u0016q\u0015B=!\r\u0019(1\u0010\u0003\f\u0005{\u0012y'!A\u0001\u0002\u000b\u0005aOA\u0002`IaB\u0011B!!\"!\u0003\u0005\rAa!\u0002#]4\u0015.\u001a7e'\u0016\u0014\u0018.\u00197ju\u0016\u00148\u000f\u0005\u0004\u0002@\u0005]$Q\u0011\t\b?\u0006e&q\u0011BIa\u0011\u0011II!$\u0011\r\u0005\u0005\u0017\u0011\u001aBF!\r\u0019(Q\u0012\u0003\f\u0005\u001f\u0013y(!A\u0001\u0002\u000b\u0005aOA\u0002`Ie\u0002DAa%\u0003\u0018B)1,!7\u0003\u0016B\u00191Oa&\u0005\u0017\te%qPA\u0001\u0002\u0003\u0015\tA\u001e\u0002\u0005?\u0012\n\u0004\u0007C\u0005\u0003\u001e\u0006\u0002\n\u00111\u0001\u0003 \u0006\u0001rOU5dQN+'/[1mSj,'o\u001d\t\u0007\u0003\u007f\t9H!)1\t\t\r&q\u0015\t\u00067\u0006M%Q\u0015\t\u0004g\n\u001dFa\u0003BU\u00057\u000b\t\u0011!A\u0003\u0002Y\u0014Aa\u0018\u00132c!I!QV\u0011\u0011\u0002\u0003\u0007\u0011q]\u0001\ro^\u000bg\u000e^:CS\u001eLe\u000e\u001e\u0005\n\u0005c\u000b\u0003\u0013!a\u0001\u0003O\f\u0001c^,b]R\u001c()[4EK\u000eLW.\u00197\t\u0013\tU\u0016\u0005%AA\u0002\u0005M\u0018AD<ji\"\u0004&/[7ji&4Xm\u001d\u0005\n\u0005s\u000b\u0003\u0013!a\u0001\u0005w\u000b1b^\"p[B\fg.[8ogB1\u0011qHA<\u0005{\u0003baXA]\u0005\u007fs\u0006\u0007\u0002Ba\u0005\u000b\u0004b!!1\u0002J\n\r\u0007cA:\u0003F\u0012Y!q\u0019B\\\u0003\u0003\u0005\tQ!\u0001w\u0005\u0011yF%\r\u001a\t\u0013\t-\u0017\u0005%AA\u0002\t}\u0011aF<FqR\u0014\u0018m\u0019;j_:tU\u000f\u001c7TiJ\fG/Z4z\u0011%\u0011y-\tI\u0001\u0002\u0004\t9/\u0001\u000bx'R\u0014\u0018n\u0019;PaRLwN\u001c)beNLgn\u001a\u0005\n\u0005'\f\u0003\u0013!a\u0001\u0003O\fac^*ue&\u001cG/\u0011:sCf,\u0005\u0010\u001e:bGRLwN\u001c\u0005\n\u0005/\f\u0003\u0013!a\u0001\u0003O\fAc^*ue&\u001cG/T1q\u000bb$(/Y2uS>t\u0007\"\u0003BnCA\u0005\t\u0019AAt\u0003Q9\u0018\t\\<bsN,5oY1qKVs\u0017nY8eK\"I!q\\\u0011\u0011\u0002\u0003\u0007\u0011q]\u0001\u001fo\u000e{gn]5eKJ\u001cu.\u001c9b]&|gnQ8ogR\u0014Xo\u0019;peND\u0011Ba9\"!\u0003\u0005\rA!\u0013\u0002'],U\u000e\u001d;z-\u0006dW/Z*ue\u0006$XmZ=\t\u0013\t\u001d\u0018\u0005%AA\u0002\u0005\u001d\u0018aG<TiJL7\r\u001e$jK2$G)Z:fe&\fG.\u001b>bi&|g.\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\t5(\u0006BA1\u0005_\\#A!=\u0011\t\tM(Q`\u0007\u0003\u0005kTAAa>\u0003z\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0005w\u0004\u0017AC1o]>$\u0018\r^5p]&!!q B{\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\u0019)A\u000b\u0003\u0003<\t=\u0018AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0007\u0017QC!a\u001b\u0003p\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\"TCAB\tU\u0011\u0019\u0019Ba<\u0011\r\u0005}\u0012qOB\u000ba\u0011\u00199ba\u0007\u0011\u000bm\u000byh!\u0007\u0011\u0007M\u001cY\u0002\u0002\u0006\u0003n\u0015\n\t\u0011!A\u0003\u0002Y\fabY8qs\u0012\"WMZ1vYR$S'\u0006\u0002\u0004\")\"11\u0005Bx!\u0019\ty$a\u001e\u0004&A\"1qEB\u0016!\u0015Y\u0016qUB\u0015!\r\u001981\u0006\u0003\u000b\u0005{2\u0013\u0011!A\u0001\u0006\u00031\u0018AD2paf$C-\u001a4bk2$HEN\u000b\u0003\u0007cQCaa\r\u0003pB1\u0011qHA<\u0007k\u0001raXA]\u0007o\u0019y\u0004\r\u0003\u0004:\ru\u0002CBAa\u0003\u0013\u001cY\u0004E\u0002t\u0007{!!Ba$(\u0003\u0003\u0005\tQ!\u0001wa\u0011\u0019\te!\u0012\u0011\u000bm\u000bIna\u0011\u0011\u0007M\u001c)\u0005\u0002\u0006\u0003\u001a\u001e\n\t\u0011!A\u0003\u0002Y\fabY8qs\u0012\"WMZ1vYR$s'\u0006\u0002\u0004L)\"1Q\nBx!\u0019\ty$a\u001e\u0004PA\"1\u0011KB+!\u0015Y\u00161SB*!\r\u00198Q\u000b\u0003\u000b\u0005SC\u0013\u0011!A\u0001\u0006\u00031\u0018AD2paf$C-\u001a4bk2$H\u0005O\u000b\u0003\u00077RC!a:\u0003p\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012J\u0014aD2paf$C-\u001a4bk2$H%\r\u0019\u0016\u0005\r\r$\u0006BAz\u0005_\fqbY8qs\u0012\"WMZ1vYR$\u0013'M\u000b\u0003\u0007SRCaa\u001b\u0003pB1\u0011qHA<\u0007[\u0002baXA]\u0007_r\u0006\u0007BB9\u0007k\u0002b!!1\u0002J\u000eM\u0004cA:\u0004v\u0011Q!q\u0019\u0017\u0002\u0002\u0003\u0005)\u0011\u0001<\u0002\u001f\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cI*\"aa\u001f+\t\t}!q^\u0001\u0010G>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132g\u0005y1m\u001c9zI\u0011,g-Y;mi\u0012\nD'A\bd_BLH\u0005Z3gCVdG\u000fJ\u00196\u0003=\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE2\u0014aD2paf$C-\u001a4bk2$H%M\u001c\u0002\u001f\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%ca*\"aa#+\t\t%#q^\u0001\u0010G>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132s\u0005Qq/\u001b;i\u0005&<\u0017J\u001c;\u0016\u0005\u0005e\u0012\u0001C<ji\"duN\\4\u0002\u001d]LG\u000f\u001b\"jO\u0012+7-[7bY\u0006Qq/\u001b;i\t>,(\r\\3\u0002\u001d]LG\u000f[\"p[B\fg.[8ogR!\u0011\u0011HBO\u0011\u001d\u0019y*\u000fa\u0001\u0007C\u000bQaY8naN\u0004RaXBR\u0007OK1a!*a\u0005)a$/\u001a9fCR,GM\u0010\t\u0007?\u0006e6\u0011\u001601\t\r-6q\u0016\t\u0007\u0003\u0003\fIm!,\u0011\u0007M\u001cy\u000bB\u0006\u00042\u000eu\u0015\u0011!A\u0001\u0006\u00031(\u0001B0%ce\nQ\u0003\u001d:fg\u0016\u0014h/\u001b8h\u000b6\u0004H/\u001f,bYV,7/A\ntW&\u0004\b/\u001b8h\u000b6\u0004H/\u001f,bYV,7/\u0001\fxSRDW)\u001c9usZ\u000bG.^3TiJ\fG/Z4z)\u0011\tIda/\t\u000f\ruF\b1\u0001\u0003J\u0005A1\u000f\u001e:bi\u0016<\u00170A\txSRDWi]2ba\u0016,f.[2pI\u0016\fqc^5uQN#(/[2u\u001fB$\u0018n\u001c8QCJ\u001c\u0018N\\4\u00023]LG\u000f[*ue&\u001cG/\u0011:sCf,\u0005\u0010\u001e:bGRLwN\\\u0001\u0018o&$\bn\u0015;sS\u000e$X*\u00199FqR\u0014\u0018m\u0019;j_:\f\u0001e^5uQB\u0013Xm\r\u001cEKN,'/[1mSj\fG/[8o\u0005\u0016D\u0017M^5pe\u000611\u000f\u001e:jGR\f\u0011B\\8o'R\u0014\u0018n\u0019;\u0002\u0019\u0011L7/\u00197m_^tU\u000f\u001c7)\u0017\u0011\u001b\tna6\u0004Z\u000eu7q\u001c\t\u0004?\u000eM\u0017bABkA\nQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\u001211\\\u0001'+N,\u0007e^5uQ:+H\u000e\\#yiJ\f7\r^5p]N#(/\u0019;fOf\u0004\u0013N\\:uK\u0006$\u0017!B:j]\u000e,\u0017EABq\u0003\u0015\u0019df\u000e\u00181\u0003i9\u0018\u000e\u001e5FqR\u0014\u0018m\u0019;j_:tU\u000f\u001c7TiJ\fG/Z4z)\u0011\tIda:\t\u000f\ruV\t1\u0001\u0003 \u0005qr/\u001b;i'R\u0014\u0018n\u0019;GS\u0016dG\rR3tKJL\u0017\r\\5{CRLwN\\\u0001\u0006IAdWo\u001d\u000b\u0005\u0003s\u0019y\u000fC\u0004\u0004r\u001e\u0003\r!a\u001b\u0002\u0015\u0015DHO]1IS:$8\u000f\u0006\u0003\u0002:\rU\bbBB|\u0011\u0002\u00071\u0011`\u0001\u000e]\u0016<8+\u001a:jC2L'0\u001a:1\t\rm8q \t\u00067\u0006M5Q \t\u0004g\u000e}Ha\u0003C\u0001\u0007k\f\t\u0011!A\u0003\u0002Y\u0014Aa\u0018\u00133aQ!\u0011\u0011\bC\u0003\u0011\u001d\u001990\u0013a\u0001\t\u000f\u0001D\u0001\"\u0003\u0005\u000eA)1,a \u0005\fA\u00191\u000f\"\u0004\u0005\u0017\u0011=AQAA\u0001\u0002\u0003\u0015\tA\u001e\u0002\u0005?\u0012\u0012\u0014\u0007\u0006\u0003\u0002:\u0011M\u0001bBB|\u0015\u0002\u0007AQ\u0003\u0019\u0005\t/!Y\u0002E\u0003\\\u0003O#I\u0002E\u0002t\t7!1\u0002\"\b\u0005\u0014\u0005\u0005\t\u0011!B\u0001m\n!q\f\n\u001a3\u0003)!\u0003\u000f\\;tIAdWo\u001d\u000b\u0005\u0003s!\u0019\u0003C\u0004\u0005&-\u0003\r\u0001b\n\u0002\u001d9,woU3sS\u0006d\u0017N_3sgB1\u0011q\bC\u0015\t[IA\u0001b\u000b\u0002R\tA\u0011\n^3sC\ndW\r\r\u0003\u00050\u0011M\u0002#B.\u0002\u0000\u0011E\u0002cA:\u00054\u0011YAQ\u0007C\u0012\u0003\u0003\u0005\tQ!\u0001w\u0005\u0011yFEM\u001a\u0002\r\u0011j\u0017N\\;t)\u0011\tI\u0004b\u000f\t\u000f\u0011uB\n1\u0001\u0005@\u0005Q1/\u001a:jC2L'0\u001a:1\t\u0011\u0005CQ\t\t\u00067\u0006}D1\t\t\u0004g\u0012\u0015Ca\u0003C$\tw\t\t\u0011!A\u0003\u0002Y\u0014Aa\u0018\u00133i\u0005\t\u0012\r\u001a3LKf\u001cVM]5bY&TXM]:\u0015\t\u0005eBQ\n\u0005\b\t\u001fj\u0005\u0019\u0001C)\u0003EqWm^&fsN+'/[1mSj,'o\u001d\t\u0007\u0003\u007f!I\u0003b\u00151\t\u0011UC\u0011\f\t\u00067\u0006\u001dFq\u000b\t\u0004g\u0012eCa\u0003C.\t\u001b\n\t\u0011!A\u0003\u0002Y\u0014Aa\u0018\u00133kU!Aq\fC4)\u0011\tI\u0004\"\u0019\t\u000f\r]h\n1\u0001\u0005dA)1,!7\u0005fA\u00191\u000fb\u001a\u0005\r\u0011%dJ1\u0001w\u0005\u0005\t\u0015a\u00044jK2$7+\u001a:jC2L'0\u001a:\u0015\t\u0011=Dq\u0010\t\u0006?\u0012EDQO\u0005\u0004\tg\u0002'AB(qi&|g\u000e\r\u0003\u0005x\u0011m\u0004#B.\u0002Z\u0012e\u0004cA:\u0005|\u0011QAQP(\u0002\u0002\u0003\u0005)\u0011\u0001<\u0003\t}##g\u000e\u0005\b\t\u0003{\u0005\u0019\u0001CB\u0003\u0015\u0019G.\u0019>{a\u0011!)\t\"#\u0011\r\u0005\u0005\u0017\u0011\u001aCD!\r\u0019H\u0011\u0012\u0003\f\t\u0017#y(!A\u0001\u0002\u000b\u0005aO\u0001\u0003`II2D\u0003BA\u0018\t\u001fCq!a\u000eQ\u0001\b\tI\u0004K\u0006Q\u0007#\u001c9\u000eb%\u0004^\u0012]\u0015E\u0001CK\u0003e*6/\u001a\u0011uQ\u0016\u0004\u0013N\u001c;fe:\fG\u000eI7fi\"|Gm\u001d\u0011j]\u0002\"\b.\u001a\u0011d_6\u0004\u0018M\\5p]\u0002z'M[3di\u0002Jgn\u001d;fC\u0012t\u0013E\u0001CM\u0003\u0015\u0019dF\u000e\u00185\u0003I\u0019Wo\u001d;p[\u0012+7/\u001a:jC2L'0\u001a:\u0015\t\u0011}Eq\u0016\t\u0007?\u0006EB\u0011\u0015>\u0011\u000f}\u000bI\fb)\u0002\nA!AQ\u0015CU\u001d\rYFqU\u0005\u0004\u0003\u001b*\u0016\u0002\u0002CV\t[\u0013\u0001\u0002V=qK&sgm\u001c\u0006\u0004\u0003\u001b*\u0006bBA\u001c#\u0002\u000f\u0011\u0011\b\u0015\f#\u000eE7q\u001bCJ\u0007;$9*A\ndkN$x.\\&fsN+'/[1mSj,'\u000f\u0006\u0003\u00058\u0012}\u0006CB0\u00022i$I\f\u0005\u0003\u0002B\u0012m\u0016\u0002\u0002C_\u0003\u001b\u0014aa\u0015;sS:<\u0007bBA\u001c%\u0002\u000f\u0011\u0011\b\u0015\f%\u000eE7q\u001bCJ\u0007;$9*A\u000bdkN$x.\\&fs\u0012+7/\u001a:jC2L'0\u001a:\u0015\t\u0011\u001dG1\u001a\t\u0007?\u0006EB\u0011\u001a>\u0011\u000f}\u000bI\fb)\u0005:\"9\u0011qG*A\u0004\u0005e\u0002fC*\u0004R\u000e]G1SBo\t/Cs\u0001\u0004Ci\t3$Y\u000e\u0005\u0003\u0005T\u0012UWB\u0001B}\u0013\u0011!9N!?\u0003!%l\u0007\u000f\\5dSRtu\u000e\u001e$pk:$\u0017aA7tO\u0006\u0012AQ\\\u0001{\u001d>\u0004sN]4/UN|g\u000eN:/\r>\u0014X.\u0019;tA\u0019|WO\u001c3/AQ\u0013\u0018\u0010\t;pA\t\u0014\u0018N\\4!C:\u0004\u0013N\\:uC:\u001cW\rI8gA=\u0014xM\f6t_:$4O\f$pe6\fGo\u001d\u0011j]\u0002\u001a8m\u001c9fA=\u0014\b%^:fAQDW\rI8sO:R7o\u001c85g:\"UMZ1vYR4uN]7biNt\u0003B\u0002Cq\u000b\u0001\u0007!0A\u0001b\u0003Y\u0019Wo\u001d;p[JK7\r\u001b#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014H\u0003\u0002Ct\tk$B\u0001\";\u0005tB1q,!\r\u0005lj\u0004raXA]\t[\fI\u0001\u0005\u0003\u0003>\u0011=\u0018\u0002\u0002Cy\u0005\u007f\u0011\u0011bU2bY\u0006$\u0016\u0010]3\t\u000f\u0005]b\u0001q\u0001\u0002:!9A\u0011\u001d\u0004A\u0002\u0011-\u0018\u0001F2vgR|WNU5dQN+'/[1mSj,'\u000f\u0006\u0003\u0005|\u0012}H\u0003BA\u0018\t{Dq!a\u000e\b\u0001\b\tI\u0004\u0003\u0004\u0005b\u001e\u0001\rA\u001f\u000b\u0005\u000b\u0007)9\u0001\u0006\u0003\u0005 \u0016\u0015\u0001bBA\u001c\u0011\u0001\u000f\u0011\u0011\b\u0005\b\tCD\u0001\u0019\u0001CQ)\u0011)Y!b\u0004\u0015\t\u0011]VQ\u0002\u0005\b\u0003oI\u00019AA\u001d\u0011\u0019!\t/\u0003a\u0001uR!Q1CC\f)\u0011!9-\"\u0006\t\u000f\u0005]\"\u0002q\u0001\u0002:!9A\u0011\u001d\u0006A\u0002\u0011%\u0017\u0001D<sSR,'+\u001a9mC\u000e,GCAC\u000f!\u0011)y\"\"\t\u000e\u0005\t\u0005\u0011\u0002BC\u0012\u0005\u0003\u0011aa\u00142kK\u000e$\b"
)
public interface Formats extends Serializable {
   static JValue write(final Object obj, final Writer writer) {
      return Formats$.MODULE$.write(obj, writer);
   }

   static Object read(final JValue json, final Reader reader) {
      return Formats$.MODULE$.read(json, reader);
   }

   DateFormat dateFormat();

   // $FF: synthetic method
   static TypeHints typeHints$(final Formats $this) {
      return $this.typeHints();
   }

   default TypeHints typeHints() {
      return NoTypeHints$.MODULE$;
   }

   // $FF: synthetic method
   static List customSerializers$(final Formats $this) {
      return $this.customSerializers();
   }

   default List customSerializers() {
      return .MODULE$.Nil();
   }

   // $FF: synthetic method
   static List richSerializers$(final Formats $this) {
      return $this.richSerializers();
   }

   default List richSerializers() {
      return .MODULE$.Nil();
   }

   // $FF: synthetic method
   static List customKeySerializers$(final Formats $this) {
      return $this.customKeySerializers();
   }

   default List customKeySerializers() {
      return .MODULE$.Nil();
   }

   // $FF: synthetic method
   static List fieldSerializers$(final Formats $this) {
      return $this.fieldSerializers();
   }

   default List fieldSerializers() {
      return .MODULE$.Nil();
   }

   // $FF: synthetic method
   static boolean wantsBigInt$(final Formats $this) {
      return $this.wantsBigInt();
   }

   default boolean wantsBigInt() {
      return true;
   }

   // $FF: synthetic method
   static boolean wantsBigDecimal$(final Formats $this) {
      return $this.wantsBigDecimal();
   }

   default boolean wantsBigDecimal() {
      return false;
   }

   // $FF: synthetic method
   static Set primitives$(final Formats $this) {
      return $this.primitives();
   }

   default Set primitives() {
      return (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Type[]{JValue.class, JObject.class, JArray.class})));
   }

   // $FF: synthetic method
   static List companions$(final Formats $this) {
      return $this.companions();
   }

   default List companions() {
      return .MODULE$.Nil();
   }

   // $FF: synthetic method
   static ExtractionNullStrategy extractionNullStrategy$(final Formats $this) {
      return $this.extractionNullStrategy();
   }

   default ExtractionNullStrategy extractionNullStrategy() {
      return ExtractionNullStrategy.Keep$.MODULE$;
   }

   // $FF: synthetic method
   static boolean strictOptionParsing$(final Formats $this) {
      return $this.strictOptionParsing();
   }

   default boolean strictOptionParsing() {
      return false;
   }

   // $FF: synthetic method
   static boolean strictArrayExtraction$(final Formats $this) {
      return $this.strictArrayExtraction();
   }

   default boolean strictArrayExtraction() {
      return false;
   }

   // $FF: synthetic method
   static boolean strictMapExtraction$(final Formats $this) {
      return $this.strictMapExtraction();
   }

   default boolean strictMapExtraction() {
      return false;
   }

   // $FF: synthetic method
   static boolean alwaysEscapeUnicode$(final Formats $this) {
      return $this.alwaysEscapeUnicode();
   }

   default boolean alwaysEscapeUnicode() {
      return false;
   }

   // $FF: synthetic method
   static boolean strictFieldDeserialization$(final Formats $this) {
      return $this.strictFieldDeserialization();
   }

   default boolean strictFieldDeserialization() {
      return false;
   }

   // $FF: synthetic method
   static boolean considerCompanionConstructors$(final Formats $this) {
      return $this.considerCompanionConstructors();
   }

   default boolean considerCompanionConstructors() {
      return true;
   }

   // $FF: synthetic method
   static ParameterNameReader parameterNameReader$(final Formats $this) {
      return $this.parameterNameReader();
   }

   default ParameterNameReader parameterNameReader() {
      return ParanamerReader$.MODULE$;
   }

   // $FF: synthetic method
   static EmptyValueStrategy emptyValueStrategy$(final Formats $this) {
      return $this.emptyValueStrategy();
   }

   default EmptyValueStrategy emptyValueStrategy() {
      return org.json4s.prefs.EmptyValueStrategy..MODULE$.default();
   }

   private Formats copy(final DateFormat wDateFormat, final ParameterNameReader wParameterNameReader, final TypeHints wTypeHints, final List wCustomSerializers, final List wCustomKeySerializers, final List wFieldSerializers, final List wRichSerializers, final boolean wWantsBigInt, final boolean wWantsBigDecimal, final Set withPrimitives, final List wCompanions, final ExtractionNullStrategy wExtractionNullStrategy, final boolean wStrictOptionParsing, final boolean wStrictArrayExtraction, final boolean wStrictMapExtraction, final boolean wAlwaysEscapeUnicode, final boolean wConsiderCompanionConstructors, final EmptyValueStrategy wEmptyValueStrategy, final boolean wStrictFieldDeserialization) {
      return new Formats(wDateFormat, wParameterNameReader, wTypeHints, wCustomSerializers, wRichSerializers, wCustomKeySerializers, wFieldSerializers, wWantsBigInt, wWantsBigDecimal, withPrimitives, wCompanions, wExtractionNullStrategy, wStrictOptionParsing, wStrictArrayExtraction, wStrictMapExtraction, wAlwaysEscapeUnicode, wConsiderCompanionConstructors, wEmptyValueStrategy, wStrictFieldDeserialization) {
         private final List customKeySerializers;
         private final DateFormat wDateFormat$1;
         private final ParameterNameReader wParameterNameReader$1;
         private final TypeHints wTypeHints$1;
         private final List wCustomSerializers$1;
         private final List wRichSerializers$1;
         private final List wFieldSerializers$1;
         private final boolean wWantsBigInt$1;
         private final boolean wWantsBigDecimal$1;
         private final Set withPrimitives$1;
         private final List wCompanions$1;
         private final ExtractionNullStrategy wExtractionNullStrategy$1;
         private final boolean wStrictOptionParsing$1;
         private final boolean wStrictArrayExtraction$1;
         private final boolean wStrictMapExtraction$1;
         private final boolean wAlwaysEscapeUnicode$1;
         private final boolean wConsiderCompanionConstructors$1;
         private final EmptyValueStrategy wEmptyValueStrategy$1;
         private final boolean wStrictFieldDeserialization$1;

         public Formats withBigInt() {
            return Formats.super.withBigInt();
         }

         public Formats withLong() {
            return Formats.super.withLong();
         }

         public Formats withBigDecimal() {
            return Formats.super.withBigDecimal();
         }

         public Formats withDouble() {
            return Formats.super.withDouble();
         }

         public Formats withCompanions(final Seq comps) {
            return Formats.super.withCompanions(comps);
         }

         public Formats preservingEmptyValues() {
            return Formats.super.preservingEmptyValues();
         }

         public Formats skippingEmptyValues() {
            return Formats.super.skippingEmptyValues();
         }

         public Formats withEmptyValueStrategy(final EmptyValueStrategy strategy) {
            return Formats.super.withEmptyValueStrategy(strategy);
         }

         public Formats withEscapeUnicode() {
            return Formats.super.withEscapeUnicode();
         }

         public Formats withStrictOptionParsing() {
            return Formats.super.withStrictOptionParsing();
         }

         public Formats withStrictArrayExtraction() {
            return Formats.super.withStrictArrayExtraction();
         }

         public Formats withStrictMapExtraction() {
            return Formats.super.withStrictMapExtraction();
         }

         public Formats withPre36DeserializationBehavior() {
            return Formats.super.withPre36DeserializationBehavior();
         }

         public Formats strict() {
            return Formats.super.strict();
         }

         public Formats nonStrict() {
            return Formats.super.nonStrict();
         }

         /** @deprecated */
         public Formats disallowNull() {
            return Formats.super.disallowNull();
         }

         public Formats withExtractionNullStrategy(final ExtractionNullStrategy strategy) {
            return Formats.super.withExtractionNullStrategy(strategy);
         }

         public Formats withStrictFieldDeserialization() {
            return Formats.super.withStrictFieldDeserialization();
         }

         public Formats $plus(final TypeHints extraHints) {
            return Formats.super.$plus(extraHints);
         }

         public Formats $plus(final RichSerializer newSerializer) {
            return Formats.super.$plus(newSerializer);
         }

         public Formats $plus(final Serializer newSerializer) {
            return Formats.super.$plus(newSerializer);
         }

         public Formats $plus(final KeySerializer newSerializer) {
            return Formats.super.$plus(newSerializer);
         }

         public Formats $plus$plus(final Iterable newSerializers) {
            return Formats.super.$plus$plus(newSerializers);
         }

         public Formats $minus(final Serializer serializer) {
            return Formats.super.$minus(serializer);
         }

         public Formats addKeySerializers(final Iterable newKeySerializers) {
            return Formats.super.addKeySerializers(newKeySerializers);
         }

         public Formats $plus(final FieldSerializer newSerializer) {
            return Formats.super.$plus(newSerializer);
         }

         public Option fieldSerializer(final Class clazz) {
            return Formats.super.fieldSerializer(clazz);
         }

         /** @deprecated */
         public PartialFunction customSerializer(final Formats format) {
            return Formats.super.customSerializer(format);
         }

         /** @deprecated */
         public PartialFunction customDeserializer(final Formats format) {
            return Formats.super.customDeserializer(format);
         }

         /** @deprecated */
         public PartialFunction customKeySerializer(final Formats format) {
            return Formats.super.customKeySerializer(format);
         }

         /** @deprecated */
         public PartialFunction customKeyDeserializer(final Formats format) {
            return Formats.super.customKeyDeserializer(format);
         }

         public DateFormat dateFormat() {
            return this.wDateFormat$1;
         }

         public ParameterNameReader parameterNameReader() {
            return this.wParameterNameReader$1;
         }

         public TypeHints typeHints() {
            return this.wTypeHints$1;
         }

         public List customSerializers() {
            return this.wCustomSerializers$1;
         }

         public List richSerializers() {
            return this.wRichSerializers$1;
         }

         public List customKeySerializers() {
            return this.customKeySerializers;
         }

         public List fieldSerializers() {
            return this.wFieldSerializers$1;
         }

         public boolean wantsBigInt() {
            return this.wWantsBigInt$1;
         }

         public boolean wantsBigDecimal() {
            return this.wWantsBigDecimal$1;
         }

         public Set primitives() {
            return this.withPrimitives$1;
         }

         public List companions() {
            return this.wCompanions$1;
         }

         public ExtractionNullStrategy extractionNullStrategy() {
            return this.wExtractionNullStrategy$1;
         }

         public boolean strictOptionParsing() {
            return this.wStrictOptionParsing$1;
         }

         public boolean strictArrayExtraction() {
            return this.wStrictArrayExtraction$1;
         }

         public boolean strictMapExtraction() {
            return this.wStrictMapExtraction$1;
         }

         public boolean alwaysEscapeUnicode() {
            return this.wAlwaysEscapeUnicode$1;
         }

         public boolean considerCompanionConstructors() {
            return this.wConsiderCompanionConstructors$1;
         }

         public EmptyValueStrategy emptyValueStrategy() {
            return this.wEmptyValueStrategy$1;
         }

         public boolean strictFieldDeserialization() {
            return this.wStrictFieldDeserialization$1;
         }

         public {
            this.wDateFormat$1 = wDateFormat$1;
            this.wParameterNameReader$1 = wParameterNameReader$1;
            this.wTypeHints$1 = wTypeHints$1;
            this.wCustomSerializers$1 = wCustomSerializers$1;
            this.wRichSerializers$1 = wRichSerializers$1;
            this.wFieldSerializers$1 = wFieldSerializers$1;
            this.wWantsBigInt$1 = wWantsBigInt$1;
            this.wWantsBigDecimal$1 = wWantsBigDecimal$1;
            this.withPrimitives$1 = withPrimitives$1;
            this.wCompanions$1 = wCompanions$1;
            this.wExtractionNullStrategy$1 = wExtractionNullStrategy$1;
            this.wStrictOptionParsing$1 = wStrictOptionParsing$1;
            this.wStrictArrayExtraction$1 = wStrictArrayExtraction$1;
            this.wStrictMapExtraction$1 = wStrictMapExtraction$1;
            this.wAlwaysEscapeUnicode$1 = wAlwaysEscapeUnicode$1;
            this.wConsiderCompanionConstructors$1 = wConsiderCompanionConstructors$1;
            this.wEmptyValueStrategy$1 = wEmptyValueStrategy$1;
            this.wStrictFieldDeserialization$1 = wStrictFieldDeserialization$1;
            Formats.$init$(this);
            this.customKeySerializers = wCustomKeySerializers$1;
         }
      };
   }

   private DateFormat copy$default$1() {
      return this.dateFormat();
   }

   private ParameterNameReader copy$default$2() {
      return this.parameterNameReader();
   }

   private TypeHints copy$default$3() {
      return this.typeHints();
   }

   private List copy$default$4() {
      return this.customSerializers();
   }

   private List copy$default$5() {
      return this.customKeySerializers();
   }

   private List copy$default$6() {
      return this.fieldSerializers();
   }

   private List copy$default$7() {
      return this.richSerializers();
   }

   private boolean copy$default$8() {
      return this.wantsBigInt();
   }

   private boolean copy$default$9() {
      return this.wantsBigDecimal();
   }

   private Set copy$default$10() {
      return this.primitives();
   }

   private List copy$default$11() {
      return this.companions();
   }

   private ExtractionNullStrategy copy$default$12() {
      return this.extractionNullStrategy();
   }

   private boolean copy$default$13() {
      return this.strictOptionParsing();
   }

   private boolean copy$default$14() {
      return this.strictArrayExtraction();
   }

   private boolean copy$default$15() {
      return this.strictMapExtraction();
   }

   private boolean copy$default$16() {
      return this.alwaysEscapeUnicode();
   }

   private boolean copy$default$17() {
      return this.considerCompanionConstructors();
   }

   private EmptyValueStrategy copy$default$18() {
      return this.emptyValueStrategy();
   }

   private boolean copy$default$19() {
      return this.strictFieldDeserialization();
   }

   default Formats withBigInt() {
      boolean x$1 = true;
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$9();
      Set x$10 = this.copy$default$10();
      List x$11 = this.copy$default$11();
      ExtractionNullStrategy x$12 = this.copy$default$12();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, true, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats withLong() {
      boolean x$1 = false;
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$9();
      Set x$10 = this.copy$default$10();
      List x$11 = this.copy$default$11();
      ExtractionNullStrategy x$12 = this.copy$default$12();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, false, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats withBigDecimal() {
      boolean x$1 = true;
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$8();
      Set x$10 = this.copy$default$10();
      List x$11 = this.copy$default$11();
      ExtractionNullStrategy x$12 = this.copy$default$12();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, true, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats withDouble() {
      boolean x$1 = false;
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$8();
      Set x$10 = this.copy$default$10();
      List x$11 = this.copy$default$11();
      ExtractionNullStrategy x$12 = this.copy$default$12();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, false, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats withCompanions(final Seq comps) {
      List var3 = comps.toList();
      List x$1 = this.companions().$colon$colon$colon(var3);
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$8();
      boolean x$10 = this.copy$default$9();
      Set x$11 = this.copy$default$10();
      ExtractionNullStrategy x$12 = this.copy$default$12();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$1, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats preservingEmptyValues() {
      return this.withEmptyValueStrategy(org.json4s.prefs.EmptyValueStrategy..MODULE$.preserve());
   }

   default Formats skippingEmptyValues() {
      return this.withEmptyValueStrategy(org.json4s.prefs.EmptyValueStrategy..MODULE$.skip());
   }

   default Formats withEmptyValueStrategy(final EmptyValueStrategy strategy) {
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$8();
      boolean x$10 = this.copy$default$9();
      Set x$11 = this.copy$default$10();
      List x$12 = this.copy$default$11();
      ExtractionNullStrategy x$13 = this.copy$default$12();
      boolean x$14 = this.copy$default$13();
      boolean x$15 = this.copy$default$14();
      boolean x$16 = this.copy$default$15();
      boolean x$17 = this.copy$default$16();
      boolean x$18 = this.copy$default$17();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, strategy, x$19);
   }

   default Formats withEscapeUnicode() {
      boolean x$1 = true;
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$8();
      boolean x$10 = this.copy$default$9();
      Set x$11 = this.copy$default$10();
      List x$12 = this.copy$default$11();
      ExtractionNullStrategy x$13 = this.copy$default$12();
      boolean x$14 = this.copy$default$13();
      boolean x$15 = this.copy$default$14();
      boolean x$16 = this.copy$default$15();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, true, x$17, x$18, x$19);
   }

   default Formats withStrictOptionParsing() {
      boolean x$1 = true;
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$8();
      boolean x$10 = this.copy$default$9();
      Set x$11 = this.copy$default$10();
      List x$12 = this.copy$default$11();
      ExtractionNullStrategy x$13 = this.copy$default$12();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, true, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats withStrictArrayExtraction() {
      boolean x$1 = true;
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$8();
      boolean x$10 = this.copy$default$9();
      Set x$11 = this.copy$default$10();
      List x$12 = this.copy$default$11();
      ExtractionNullStrategy x$13 = this.copy$default$12();
      boolean x$14 = this.copy$default$13();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, true, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats withStrictMapExtraction() {
      boolean x$1 = true;
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$8();
      boolean x$10 = this.copy$default$9();
      Set x$11 = this.copy$default$10();
      List x$12 = this.copy$default$11();
      ExtractionNullStrategy x$13 = this.copy$default$12();
      boolean x$14 = this.copy$default$13();
      boolean x$15 = this.copy$default$14();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, true, x$16, x$17, x$18, x$19);
   }

   default Formats withPre36DeserializationBehavior() {
      boolean x$1 = false;
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$8();
      boolean x$10 = this.copy$default$9();
      Set x$11 = this.copy$default$10();
      List x$12 = this.copy$default$11();
      ExtractionNullStrategy x$13 = this.copy$default$12();
      boolean x$14 = this.copy$default$13();
      boolean x$15 = this.copy$default$14();
      boolean x$16 = this.copy$default$15();
      boolean x$17 = this.copy$default$16();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, false, x$18, x$19);
   }

   default Formats strict() {
      boolean x$1 = true;
      boolean x$2 = true;
      boolean x$3 = true;
      DateFormat x$4 = this.copy$default$1();
      ParameterNameReader x$5 = this.copy$default$2();
      TypeHints x$6 = this.copy$default$3();
      List x$7 = this.copy$default$4();
      List x$8 = this.copy$default$5();
      List x$9 = this.copy$default$6();
      List x$10 = this.copy$default$7();
      boolean x$11 = this.copy$default$8();
      boolean x$12 = this.copy$default$9();
      Set x$13 = this.copy$default$10();
      List x$14 = this.copy$default$11();
      ExtractionNullStrategy x$15 = this.copy$default$12();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, true, true, true, x$16, x$17, x$18, x$19);
   }

   default Formats nonStrict() {
      boolean x$1 = false;
      boolean x$2 = false;
      boolean x$3 = false;
      DateFormat x$4 = this.copy$default$1();
      ParameterNameReader x$5 = this.copy$default$2();
      TypeHints x$6 = this.copy$default$3();
      List x$7 = this.copy$default$4();
      List x$8 = this.copy$default$5();
      List x$9 = this.copy$default$6();
      List x$10 = this.copy$default$7();
      boolean x$11 = this.copy$default$8();
      boolean x$12 = this.copy$default$9();
      Set x$13 = this.copy$default$10();
      List x$14 = this.copy$default$11();
      ExtractionNullStrategy x$15 = this.copy$default$12();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, false, false, false, x$16, x$17, x$18, x$19);
   }

   /** @deprecated */
   default Formats disallowNull() {
      ExtractionNullStrategy.Disallow$ x$1 = ExtractionNullStrategy.Disallow$.MODULE$;
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$8();
      boolean x$10 = this.copy$default$9();
      Set x$11 = this.copy$default$10();
      List x$12 = this.copy$default$11();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$1, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats withExtractionNullStrategy(final ExtractionNullStrategy strategy) {
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$8();
      boolean x$10 = this.copy$default$9();
      Set x$11 = this.copy$default$10();
      List x$12 = this.copy$default$11();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, strategy, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats withStrictFieldDeserialization() {
      boolean x$1 = true;
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      List x$8 = this.copy$default$7();
      boolean x$9 = this.copy$default$8();
      boolean x$10 = this.copy$default$9();
      Set x$11 = this.copy$default$10();
      List x$12 = this.copy$default$11();
      ExtractionNullStrategy x$13 = this.copy$default$12();
      boolean x$14 = this.copy$default$13();
      boolean x$15 = this.copy$default$14();
      boolean x$16 = this.copy$default$15();
      boolean x$17 = this.copy$default$16();
      boolean x$18 = this.copy$default$17();
      EmptyValueStrategy x$19 = this.copy$default$18();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19, true);
   }

   default Formats $plus(final TypeHints extraHints) {
      TypeHints x$1 = this.typeHints().$plus(extraHints);
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      List x$4 = this.copy$default$4();
      List x$5 = this.copy$default$5();
      List x$6 = this.copy$default$6();
      List x$7 = this.copy$default$7();
      boolean x$8 = this.copy$default$8();
      boolean x$9 = this.copy$default$9();
      Set x$10 = this.copy$default$10();
      List x$11 = this.copy$default$11();
      ExtractionNullStrategy x$12 = this.copy$default$12();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$1, x$4, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats $plus(final RichSerializer newSerializer) {
      List x$1 = this.richSerializers().$colon$colon(newSerializer);
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$6();
      boolean x$8 = this.copy$default$8();
      boolean x$9 = this.copy$default$9();
      Set x$10 = this.copy$default$10();
      List x$11 = this.copy$default$11();
      ExtractionNullStrategy x$12 = this.copy$default$12();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$1, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats $plus(final Serializer newSerializer) {
      List x$1 = this.customSerializers().$colon$colon(newSerializer);
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$5();
      List x$6 = this.copy$default$6();
      List x$7 = this.copy$default$7();
      boolean x$8 = this.copy$default$8();
      boolean x$9 = this.copy$default$9();
      Set x$10 = this.copy$default$10();
      List x$11 = this.copy$default$11();
      ExtractionNullStrategy x$12 = this.copy$default$12();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$1, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats $plus(final KeySerializer newSerializer) {
      List x$1 = this.customKeySerializers().$colon$colon(newSerializer);
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$6();
      List x$7 = this.copy$default$7();
      boolean x$8 = this.copy$default$8();
      boolean x$9 = this.copy$default$9();
      Set x$10 = this.copy$default$10();
      List x$11 = this.copy$default$11();
      ExtractionNullStrategy x$12 = this.copy$default$12();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$1, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats $plus$plus(final Iterable newSerializers) {
      List x$1 = (List)newSerializers.foldRight(this.customSerializers(), (x$1x, x$2x) -> x$2x.$colon$colon(x$1x));
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$5();
      List x$6 = this.copy$default$6();
      List x$7 = this.copy$default$7();
      boolean x$8 = this.copy$default$8();
      boolean x$9 = this.copy$default$9();
      Set x$10 = this.copy$default$10();
      List x$11 = this.copy$default$11();
      ExtractionNullStrategy x$12 = this.copy$default$12();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$1, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats $minus(final Serializer serializer) {
      List x$1 = this.customSerializers().filterNot((x$3x) -> BoxesRunTime.boxToBoolean($anonfun$$minus$1(serializer, x$3x)));
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$5();
      List x$6 = this.copy$default$6();
      List x$7 = this.copy$default$7();
      boolean x$8 = this.copy$default$8();
      boolean x$9 = this.copy$default$9();
      Set x$10 = this.copy$default$10();
      List x$11 = this.copy$default$11();
      ExtractionNullStrategy x$12 = this.copy$default$12();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$1, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Formats addKeySerializers(final Iterable newKeySerializers) {
      return (Formats)newKeySerializers.foldLeft(this, (x$4, x$5) -> x$4.$plus(x$5));
   }

   default Formats $plus(final FieldSerializer newSerializer) {
      Tuple2 var3 = scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(newSerializer.mf().runtimeClass()), newSerializer);
      List x$1 = this.fieldSerializers().$colon$colon(var3);
      DateFormat x$2 = this.copy$default$1();
      ParameterNameReader x$3 = this.copy$default$2();
      TypeHints x$4 = this.copy$default$3();
      List x$5 = this.copy$default$4();
      List x$6 = this.copy$default$5();
      List x$7 = this.copy$default$7();
      boolean x$8 = this.copy$default$8();
      boolean x$9 = this.copy$default$9();
      Set x$10 = this.copy$default$10();
      List x$11 = this.copy$default$11();
      ExtractionNullStrategy x$12 = this.copy$default$12();
      boolean x$13 = this.copy$default$13();
      boolean x$14 = this.copy$default$14();
      boolean x$15 = this.copy$default$15();
      boolean x$16 = this.copy$default$16();
      boolean x$17 = this.copy$default$17();
      EmptyValueStrategy x$18 = this.copy$default$18();
      boolean x$19 = this.copy$default$19();
      return this.copy(x$2, x$3, x$4, x$5, x$6, x$1, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19);
   }

   default Option fieldSerializer(final Class clazz) {
      Object var2;
      label24: {
         Ordering ord = .MODULE$.Ordering().apply(scala.math.Ordering.Int..MODULE$).on((x) -> BoxesRunTime.boxToInteger($anonfun$fieldSerializer$1(clazz, x)));
         List var4 = this.fieldSerializers().filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$fieldSerializer$2(clazz, x$6)));
         Nil var10000 = .MODULE$.Nil();
         if (var10000 == null) {
            if (var4 == null) {
               break label24;
            }
         } else if (var10000.equals(var4)) {
            break label24;
         }

         var2 = new Some(((Tuple2)var4.min(ord))._2());
         return (Option)var2;
      }

      var2 = scala.None..MODULE$;
      return (Option)var2;
   }

   /** @deprecated */
   default PartialFunction customSerializer(final Formats format) {
      return (PartialFunction)this.customSerializers().foldLeft((PartialFunction)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$), (acc, x) -> acc.orElse(x.serialize(format)));
   }

   /** @deprecated */
   default PartialFunction customDeserializer(final Formats format) {
      return (PartialFunction)this.customSerializers().foldLeft((PartialFunction)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$), (acc, x) -> acc.orElse(x.deserialize(format)));
   }

   /** @deprecated */
   default PartialFunction customKeySerializer(final Formats format) {
      return (PartialFunction)this.customKeySerializers().foldLeft((PartialFunction)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$), (acc, x) -> acc.orElse(x.serialize(format)));
   }

   /** @deprecated */
   default PartialFunction customKeyDeserializer(final Formats format) {
      return (PartialFunction)this.customKeySerializers().foldLeft((PartialFunction)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$), (acc, x) -> acc.orElse(x.deserialize(format)));
   }

   // $FF: synthetic method
   static boolean $anonfun$$minus$1(final Serializer serializer$1, final Serializer x$3) {
      boolean var10000;
      label23: {
         if (x$3 == null) {
            if (serializer$1 == null) {
               break label23;
            }
         } else if (x$3.equals(serializer$1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   static int $anonfun$fieldSerializer$1(final Class clazz$1, final Tuple2 x) {
      return ClassDelta$.MODULE$.delta((Class)x._1(), clazz$1);
   }

   // $FF: synthetic method
   static boolean $anonfun$fieldSerializer$2(final Class clazz$1, final Tuple2 x$6) {
      return ((Class)x$6._1()).isAssignableFrom(clazz$1);
   }

   static void $init$(final Formats $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
