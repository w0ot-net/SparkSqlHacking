package org.apache.spark.sql.catalyst;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoder;
import org.apache.spark.sql.errors.ExecutionErrors$;
import org.apache.spark.sql.types.DataType;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Mirror;
import scala.reflect.api.Names;
import scala.reflect.api.Symbols;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.reflect.runtime.JavaUniverse;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Nothing;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\rev!\u0002\u001f>\u0011\u0003Ae!\u0002&>\u0011\u0003Y\u0005bBAG\u0003\u0011\u0005\u0011q\u0012\u0005\t?\u0006\u0011\r\u0011\"\u0001\u0002\u0012\"A\u0011\u0011V\u0001!\u0002\u0013\t\u0019\n\u0003\u0004j\u0003\u0011\u0005\u00131\u0016\u0005\t\u0003s\u000bA\u0011A\u001f\u0002<\"9\u0011qY\u0001\u0005\n\u0005%\u0007bBAg\u0003\u0011\u0005\u0011q\u001a\u0005\b\u0003K\fA\u0011BAt\u0011%\u0011Y!AI\u0001\n\u0013\u0011i\u0001C\u0004\u0003 \u0005!IA!\t\t\u000f\t\u0015\u0012\u0001\"\u0001\u0003(!9!1F\u0001\u0005\u0002\t5bA\u0002B\u001e\u0003\u0001\u0013i\u0004\u0003\u0006\u0003L9\u0011)\u001a!C\u0001\u0005\u001bB!Ba\u0017\u000f\u0005#\u0005\u000b\u0011\u0002B(\u0011)\u0011iF\u0004BK\u0002\u0013\u0005!q\f\u0005\u000b\u0005Cr!\u0011#Q\u0001\n\u0005E\u0002bBAG\u001d\u0011\u0005!1\r\u0005\n\u0005Wr\u0011\u0011!C\u0001\u0005[B\u0011Ba\u001d\u000f#\u0003%\tA!\u001e\t\u0013\ted\"%A\u0005\u0002\tm\u0004\"\u0003B@\u001d\u0005\u0005I\u0011\tBA\u0011%\u0011\tJDA\u0001\n\u0003\u0011\u0019\nC\u0005\u0003\u0016:\t\t\u0011\"\u0001\u0003\u0018\"I!Q\u0014\b\u0002\u0002\u0013\u0005#q\u0014\u0005\n\u0005[s\u0011\u0011!C\u0001\u0005_C\u0011Ba-\u000f\u0003\u0003%\tE!.\t\u0013\tef\"!A\u0005B\tm\u0006\"\u0003B_\u001d\u0005\u0005I\u0011\tB`\u0011%\u0011\tMDA\u0001\n\u0003\u0012\u0019mB\u0005\u0003H\u0006\t\t\u0011#\u0001\u0003J\u001aI!1H\u0001\u0002\u0002#\u0005!1\u001a\u0005\b\u0003\u001b\u000bC\u0011\u0001Bq\u0011%\u0011i,IA\u0001\n\u000b\u0012y\fC\u0005\u0003d\u0006\n\t\u0011\"!\u0003f\"I!1^\u0011\u0002\u0002\u0013\u0005%Q\u001e\u0005\n\u0005w\f\u0013\u0011!C\u0005\u0005{Dqa!\u0002\u0002\t\u0003\u00199\u0001C\u0004\u0004\u0006\u0005!\ta!\u0006\t\u000f\re\u0011\u0001\"\u0001\u0004\u001c!911I\u0001\u0005\u0002\r\u0015\u0003bBB%\u0003\u0011\u000511\n\u0005\b\u0007#\nA\u0011AB*\u0011\u001d\u0019y'\u0001C\u0001\u0007cBqa!\u0015\u0002\t\u0003\u0019\t\tC\u0005\u0004\u0014\u0006\t\n\u0011\"\u0001\u0003|!91\u0011K\u0001\u0005\n\rUea\u0002&>!\u0003\r\ta\u0015\u0005\u00065F\"\ta\u0017\u0005\b?F\u0012\rQ\"\u0001a\u0011\u0015I\u0017G\"\u0001k\u0011\u0015\u0011\u0018\u0007\"\u0001t\u0011\u001d\tY!\rC\u0001\u0003\u001bAq!!\f2\t\u0013\ty\u0003C\u0004\u0002<E\"I!!\u0010\t\u000f\u0005\u0005\u0013\u0007\"\u0001\u0002D!9\u0011QO\u0019\u0005\n\u0005]\u0004bBACc\u0011E\u0011qQ\u0001\u0010'\u000e\fG.\u0019*fM2,7\r^5p]*\u0011ahP\u0001\tG\u0006$\u0018\r\\=ti*\u0011\u0001)Q\u0001\u0004gFd'B\u0001\"D\u0003\u0015\u0019\b/\u0019:l\u0015\t!U)\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\r\u0006\u0019qN]4\u0004\u0001A\u0011\u0011*A\u0007\u0002{\ty1kY1mCJ+g\r\\3di&|gnE\u0002\u0002\u0019J\u0003\"!\u0014)\u000e\u00039S\u0011aT\u0001\u0006g\u000e\fG.Y\u0005\u0003#:\u0013a!\u00118z%\u00164\u0007CA%2'\r\tD\n\u0016\t\u0003+bk\u0011A\u0016\u0006\u0003/\u0006\u000b\u0001\"\u001b8uKJt\u0017\r\\\u0005\u00033Z\u0013q\u0001T8hO&tw-\u0001\u0004%S:LG\u000f\n\u000b\u00029B\u0011Q*X\u0005\u0003=:\u0013A!\u00168ji\u0006AQO\\5wKJ\u001cX-F\u0001b!\t\u0011w-D\u0001d\u0015\t!W-A\u0002ba&T!A\u001a(\u0002\u000fI,g\r\\3di&\u0011\u0001n\u0019\u0002\t+:Lg/\u001a:tK\u00061Q.\u001b:s_J,\u0012a\u001b\t\u0003Y:t!!\\\u001a\u000e\u0003EJ!a\u001c9\u0003\r5K'O]8s\u0013\t\t8MA\u0004NSJ\u0014xN]:\u00021\rdW-\u00198VaJ+g\r\\3di&|gn\u00142kK\u000e$8/\u0006\u0002uoR\u0019Q/!\u0001\u0011\u0005Y<H\u0002\u0001\u0003\u0006qV\u0012\r!\u001f\u0002\u0002)F\u0011!0 \t\u0003\u001bnL!\u0001 (\u0003\u000f9{G\u000f[5oOB\u0011QJ`\u0005\u0003\u007f:\u00131!\u00118z\u0011!\t\u0019!\u000eCA\u0002\u0005\u0015\u0011\u0001\u00024v]\u000e\u0004B!TA\u0004k&\u0019\u0011\u0011\u0002(\u0003\u0011q\u0012\u0017P\\1nKz\n1\u0002\\8dC2$\u0016\u0010]3PMV!\u0011qBA\u0016)\u0011\t\t\"a\u0007\u0011\u00071\f\u0019\"\u0003\u0003\u0002\u0016\u0005]!\u0001\u0002+za\u0016L1!!\u0007d\u0005\u0015!\u0016\u0010]3t\u0011%\tiBNA\u0001\u0002\b\ty\"\u0001\u0006fm&$WM\\2fIQ\u0002R\u0001\\A\u0011\u0003SIA!a\t\u0002&\t9A+\u001f9f)\u0006<\u0017bAA\u0014G\nAA+\u001f9f)\u0006<7\u000fE\u0002w\u0003W!Q\u0001\u001f\u001cC\u0002e\fA\"[:WC2,Xm\u00117bgN$B!!\r\u00028A\u0019Q*a\r\n\u0007\u0005UbJA\u0004C_>dW-\u00198\t\u000f\u0005er\u00071\u0001\u0002\u0012\u0005\u0019A\u000f]3\u0002;\u001d,G/\u00168eKJd\u00170\u001b8h)f\u0004Xm\u00144WC2,Xm\u00117bgN$B!!\u0005\u0002@!9\u0011\u0011\b\u001dA\u0002\u0005E\u0011\u0001G4fi\u000e{gn\u001d;sk\u000e$xN\u001d)be\u0006lW\r^3sgR!\u0011QIA:!\u0019\t9%a\u0016\u0002^9!\u0011\u0011JA*\u001d\u0011\tY%!\u0015\u000e\u0005\u00055#bAA(\u000f\u00061AH]8pizJ\u0011aT\u0005\u0004\u0003+r\u0015a\u00029bG.\fw-Z\u0005\u0005\u00033\nYFA\u0002TKFT1!!\u0016O!\u001di\u0015qLA2\u0003#I1!!\u0019O\u0005\u0019!V\u000f\u001d7feA!\u0011QMA7\u001d\u0011\t9'!\u001b\u0011\u0007\u0005-c*C\u0002\u0002l9\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA8\u0003c\u0012aa\u0015;sS:<'bAA6\u001d\"9\u0011\u0011H\u001dA\u0002\u0005E\u0011aF4fi\u000e{W\u000e]1oS>t7i\u001c8tiJ,8\r^8s)\u0011\tI(a!\u0011\u00071\fY(\u0003\u0003\u0002~\u0005}$AB*z[\n|G.C\u0002\u0002\u0002\u000e\u0014qaU=nE>d7\u000fC\u0004\u0002:i\u0002\r!!\u0005\u0002\u001f\r|gn\u001d;sk\u000e$\b+\u0019:b[N$B!!#\u0002\fB1\u0011qIA,\u0003sBq!!\u000f<\u0001\u0004\t\t\"\u0001\u0004=S:LGO\u0010\u000b\u0002\u0011V\u0011\u00111\u0013\b\u0005\u0003+\u000b)K\u0004\u0003\u0002\u0018\u0006\u0005f\u0002BAM\u0003;sA!!\u0013\u0002\u001c&\u0011aMT\u0005\u0004\u0003?+\u0017a\u0002:v]RLW.Z\u0005\u0005\u0003+\n\u0019KC\u0002\u0002 \u0016L1aXAT\u0015\u0011\t)&a)\u0002\u0013Ut\u0017N^3sg\u0016\u0004SCAAW!\u0011\ty+a-\u000f\u0007\u0005E6!D\u0001\u0002\u0013\ry\u0017QW\u0005\u0004\u0003o\u001b'\u0001\u0004&bm\u0006,f.\u001b<feN,\u0017!C5t'V\u0014G/\u001f9f)\u0019\t\t$!0\u0002D\"9\u0011q\u0018\u0004A\u0002\u0005\u0005\u0017\u0001\u0002;qKF\u0002B!a,\u0002\u0014!9\u0011Q\u0019\u0004A\u0002\u0005\u0005\u0017\u0001\u0002;qKJ\n\u0001BY1tKRK\b/\u001a\u000b\u0005\u0003\u0003\fY\rC\u0004\u0002:\u001d\u0001\r!!1\u00029\u001d,GoQ8ogR\u0014Xo\u0019;peB\u000b'/Y7fi\u0016\u0014h*Y7fgR!\u0011\u0011[Aj!\u0019\t9%a\u0016\u0002d!9\u0011Q\u001b\u0005A\u0002\u0005]\u0017aA2mgB\"\u0011\u0011\\Aq!\u0019\t)'a7\u0002`&!\u0011Q\\A9\u0005\u0015\u0019E.Y:t!\r1\u0018\u0011\u001d\u0003\f\u0003G\f\u0019.!A\u0001\u0002\u000b\u0005\u0011PA\u0002`IE\n\u0001b]3mMRK\b/\u001a\u000b\u0007\u0003\u0003\fI/a=\t\u000f\u0005-\u0018\u00021\u0001\u0002n\u0006I1\r\\:Ts6\u0014w\u000e\u001c\t\u0005\u0003_\u000by/\u0003\u0003\u0002r\u0006}$aC\"mCN\u001c8+_7c_2D\u0011\"!>\n!\u0003\u0005\r!a>\u0002\u000bQ\u0014\u0018.Z:\u0011\u00075\u000bI0C\u0002\u0002|:\u00131!\u00138uQ\rI\u0011q \t\u0005\u0005\u0003\u00119!\u0004\u0002\u0003\u0004)\u0019!Q\u0001(\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003\n\t\r!a\u0002;bS2\u0014XmY\u0001\u0013g\u0016dg\rV=qK\u0012\"WMZ1vYR$#'\u0006\u0002\u0003\u0010)\"\u0011q\u001fB\tW\t\u0011\u0019\u0002\u0005\u0003\u0003\u0016\tmQB\u0001B\f\u0015\u0011\u0011IBa\u0001\u0002\u0013Ut7\r[3dW\u0016$\u0017\u0002\u0002B\u000f\u0005/\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003\u001d)'/Y:ve\u0016$B!!1\u0003$!9\u0011\u0011H\u0006A\u0002\u0005\u0005\u0017\u0001F4fi\u000ec\u0017m]:OC6,gI]8n)f\u0004X\r\u0006\u0003\u0002d\t%\u0002bBA\u001d\u0019\u0001\u0007\u0011\u0011Y\u0001\u0011O\u0016$8\t\\1tg\u001a\u0013x.\u001c+za\u0016$BAa\f\u0003:A\"!\u0011\u0007B\u001b!\u0019\t)'a7\u00034A\u0019aO!\u000e\u0005\u0015\t]R\"!A\u0001\u0002\u000b\u0005\u0011PA\u0002`IIBq!!\u000f\u000e\u0001\u0004\t\tM\u0001\u0004TG\",W.Y\n\u0007\u001d1\u0013yD!\u0012\u0011\u00075\u0013\t%C\u0002\u0003D9\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0002H\t\u001d\u0013\u0002\u0002B%\u00037\u0012AbU3sS\u0006d\u0017N_1cY\u0016\f\u0001\u0002Z1uCRK\b/Z\u000b\u0003\u0005\u001f\u0002BA!\u0015\u0003X5\u0011!1\u000b\u0006\u0004\u0005+z\u0014!\u0002;za\u0016\u001c\u0018\u0002\u0002B-\u0005'\u0012\u0001\u0002R1uCRK\b/Z\u0001\nI\u0006$\u0018\rV=qK\u0002\n\u0001B\\;mY\u0006\u0014G.Z\u000b\u0003\u0003c\t\u0011B\\;mY\u0006\u0014G.\u001a\u0011\u0015\r\t\u0015$q\rB5!\r\t\tL\u0004\u0005\b\u0005\u0017\u001a\u0002\u0019\u0001B(\u0011\u001d\u0011if\u0005a\u0001\u0003c\tAaY8qsR1!Q\rB8\u0005cB\u0011Ba\u0013\u0015!\u0003\u0005\rAa\u0014\t\u0013\tuC\u0003%AA\u0002\u0005E\u0012AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0005oRCAa\u0014\u0003\u0012\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TC\u0001B?U\u0011\t\tD!\u0005\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\u0011\u0019\t\u0005\u0003\u0003\u0006\n=UB\u0001BD\u0015\u0011\u0011IIa#\u0002\t1\fgn\u001a\u0006\u0003\u0005\u001b\u000bAA[1wC&!\u0011q\u000eBD\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t90\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007u\u0014I\nC\u0005\u0003\u001cf\t\t\u00111\u0001\u0002x\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!)\u0011\u000b\t\r&\u0011V?\u000e\u0005\t\u0015&b\u0001BT\u001d\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\t-&Q\u0015\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u00022\tE\u0006\u0002\u0003BN7\u0005\u0005\t\u0019A?\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005\u0007\u00139\fC\u0005\u0003\u001cr\t\t\u00111\u0001\u0002x\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002x\u0006AAo\\*ue&tw\r\u0006\u0002\u0003\u0004\u00061Q-];bYN$B!!\r\u0003F\"A!1T\u0010\u0002\u0002\u0003\u0007Q0\u0001\u0004TG\",W.\u0019\t\u0004\u0003c\u000b3#B\u0011\u0003N\n]\u0007C\u0003Bh\u0005'\u0014y%!\r\u0003f5\u0011!\u0011\u001b\u0006\u0004\u0003?s\u0015\u0002\u0002Bk\u0005#\u0014\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\u0011INa8\u000e\u0005\tm'\u0002\u0002Bo\u0005\u0017\u000b!![8\n\t\t%#1\u001c\u000b\u0003\u0005\u0013\fQ!\u00199qYf$bA!\u001a\u0003h\n%\bb\u0002B&I\u0001\u0007!q\n\u0005\b\u0005;\"\u0003\u0019AA\u0019\u0003\u001d)h.\u00199qYf$BAa<\u0003xB)QJ!=\u0003v&\u0019!1\u001f(\u0003\r=\u0003H/[8o!\u001di\u0015q\fB(\u0003cA\u0011B!?&\u0003\u0003\u0005\rA!\u001a\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003\u0000B!!QQB\u0001\u0013\u0011\u0019\u0019Aa\"\u0003\r=\u0013'.Z2u\u0003%\u00198\r[3nC\u001a{'/\u0006\u0003\u0004\n\rMA\u0003\u0002B3\u0007\u0017A\u0011b!\u0004(\u0003\u0003\u0005\u001daa\u0004\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u00020\u0006\u00052\u0011\u0003\t\u0004m\u000eMA!\u0002=(\u0005\u0004IH\u0003\u0002B3\u0007/Aq!!\u000f)\u0001\u0004\t\t-A\bgS:$7i\u001c8tiJ,8\r^8s+\u0011\u0019iba\u000b\u0015\r\r}1QFB\u0019!\u0015i%\u0011_B\u0011!\u001di51EB\u0014\u0007SI1a!\nO\u0005%1UO\\2uS>t\u0017\u0007E\u0003\u0002H\u0005]C\nE\u0002w\u0007W!Q\u0001_\u0015C\u0002eDq!!6*\u0001\u0004\u0019y\u0003\u0005\u0004\u0002f\u0005m7\u0011\u0006\u0005\b\u0007gI\u0003\u0019AB\u001b\u0003)\u0001\u0018M]1n)f\u0004Xm\u001d\t\u0007\u0003\u000f\n9fa\u000e1\t\re2Q\b\t\u0007\u0003K\nYna\u000f\u0011\u0007Y\u001ci\u0004B\u0006\u0004@\r\u0005\u0013\u0011!A\u0001\u0006\u0003I(aA0%g!911G\u0015A\u0002\rU\u0012A\u00073fM&tW\r\u001a\"z\u0007>t7\u000f\u001e:vGR|'\u000fU1sC6\u001cH\u0003BA\u0019\u0007\u000fBq!!\u000f+\u0001\u0004\t\t-A\u000ef]\u000e|G-\u001a$jK2$g*Y7f)>LE-\u001a8uS\u001aLWM\u001d\u000b\u0005\u0003G\u001ai\u0005C\u0004\u0004P-\u0002\r!a\u0019\u0002\u0013\u0019LW\r\u001c3OC6,\u0017AC3oG>$WM\u001d$peV!1QKB3)\u0011\u00199f!\u001b\u0011\r\re3qLB2\u001b\t\u0019YFC\u0002\u0004^u\n\u0001\"\u001a8d_\u0012,'o]\u0005\u0005\u0007C\u001aYFA\bBO:|7\u000f^5d\u000b:\u001cw\u000eZ3s!\r18Q\r\u0003\u0007\u0007Ob#\u0019A=\u0003\u0003\u0015C\u0011ba\u001b-\u0003\u0003\u0005\u001da!\u001c\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007\u0005\u0004\u00020\u0006\u000521M\u0001 K:\u001cw\u000eZ3s\r>\u0014x+\u001b;i%><XI\\2pI\u0016\u00148+\u001e9q_J$X\u0003BB:\u0007s\"Ba!\u001e\u0004|A11\u0011LB0\u0007o\u00022A^B=\t\u0019\u00199'\fb\u0001s\"I1QP\u0017\u0002\u0002\u0003\u000f1qP\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004CBAX\u0003C\u00199\b\u0006\u0004\u0004\u0004\u000e55q\u0012\u0019\u0005\u0007\u000b\u001bI\t\u0005\u0004\u0004Z\r}3q\u0011\t\u0004m\u000e%EACBF]\u0005\u0005\t\u0011!B\u0001s\n\u0019q\fJ\u001b\t\u000f\u0005eb\u00061\u0001\u0002B\"I1\u0011\u0013\u0018\u0011\u0002\u0003\u0007\u0011\u0011G\u0001\u0016SN\u0014vn^#oG>$WM]*vaB|'\u000f^3e\u0003Q)gnY8eKJ4uN\u001d\u0013eK\u001a\fW\u000f\u001c;%eQQ1qSBQ\u0007G\u001bika.1\t\re5Q\u0014\t\u0007\u00073\u001ayfa'\u0011\u0007Y\u001ci\n\u0002\u0006\u0004 B\n\t\u0011!A\u0003\u0002e\u00141a\u0018\u00137\u0011\u001d\tI\u0004\ra\u0001\u0003\u0003Dqa!*1\u0001\u0004\u00199+A\u0006tK\u0016tG+\u001f9f'\u0016$\bCBA3\u0007S\u000b\t-\u0003\u0003\u0004,\u0006E$aA*fi\"91q\u0016\u0019A\u0002\rE\u0016\u0001\u00029bi\"\u00042!SBZ\u0013\r\u0019),\u0010\u0002\u000f/\u0006d7.\u001a3UsB,\u0007+\u0019;i\u0011\u001d\u0019\t\n\ra\u0001\u0003c\u0001"
)
public interface ScalaReflection extends Logging {
   static boolean encoderFor$default$2() {
      return ScalaReflection$.MODULE$.encoderFor$default$2();
   }

   static AgnosticEncoder encoderFor(final Types.TypeApi tpe, final boolean isRowEncoderSupported) {
      return ScalaReflection$.MODULE$.encoderFor(tpe, isRowEncoderSupported);
   }

   static AgnosticEncoder encoderForWithRowEncoderSupport(final TypeTags.TypeTag evidence$3) {
      return ScalaReflection$.MODULE$.encoderForWithRowEncoderSupport(evidence$3);
   }

   static AgnosticEncoder encoderFor(final TypeTags.TypeTag evidence$2) {
      return ScalaReflection$.MODULE$.encoderFor(evidence$2);
   }

   static String encodeFieldNameToIdentifier(final String fieldName) {
      return ScalaReflection$.MODULE$.encodeFieldNameToIdentifier(fieldName);
   }

   static boolean definedByConstructorParams(final Types.TypeApi tpe) {
      return ScalaReflection$.MODULE$.definedByConstructorParams(tpe);
   }

   static Option findConstructor(final Class cls, final Seq paramTypes) {
      return ScalaReflection$.MODULE$.findConstructor(cls, paramTypes);
   }

   static Schema schemaFor(final Types.TypeApi tpe) {
      return ScalaReflection$.MODULE$.schemaFor(tpe);
   }

   static Schema schemaFor(final TypeTags.TypeTag evidence$1) {
      return ScalaReflection$.MODULE$.schemaFor(evidence$1);
   }

   static Class getClassFromType(final Types.TypeApi tpe) {
      return ScalaReflection$.MODULE$.getClassFromType(tpe);
   }

   static String getClassNameFromType(final Types.TypeApi tpe) {
      return ScalaReflection$.MODULE$.getClassNameFromType(tpe);
   }

   static Seq getConstructorParameterNames(final Class cls) {
      return ScalaReflection$.MODULE$.getConstructorParameterNames(cls);
   }

   Universe universe();

   Mirror mirror();

   // $FF: synthetic method
   static Object cleanUpReflectionObjects$(final ScalaReflection $this, final Function0 func) {
      return $this.cleanUpReflectionObjects(func);
   }

   default Object cleanUpReflectionObjects(final Function0 func) {
      return ((JavaUniverse)this.universe()).undoLog().undo(func);
   }

   // $FF: synthetic method
   static Types.TypeApi localTypeOf$(final ScalaReflection $this, final TypeTags.TypeTag evidence$4) {
      return $this.localTypeOf(evidence$4);
   }

   default Types.TypeApi localTypeOf(final TypeTags.TypeTag evidence$4) {
      TypeTags.TypeTag tag = (TypeTags.TypeTag).MODULE$.implicitly(evidence$4);
      return tag.in(this.mirror()).tpe().dealias();
   }

   private boolean isValueClass(final Types.TypeApi tpe) {
      return tpe.typeSymbol().isClass() && tpe.typeSymbol().asClass().isDerivedValueClass();
   }

   private Types.TypeApi getUnderlyingTypeOfValueClass(final Types.TypeApi tpe) {
      return (Types.TypeApi)((Tuple2)this.getConstructorParameters(tpe).head())._2();
   }

   // $FF: synthetic method
   static Seq getConstructorParameters$(final ScalaReflection $this, final Types.TypeApi tpe) {
      return $this.getConstructorParameters(tpe);
   }

   default Seq getConstructorParameters(final Types.TypeApi tpe) {
      Types.TypeApi dealiasedTpe = tpe.dealias();
      List formalTypeArgs = dealiasedTpe.typeSymbol().asClass().typeParams();
      if (dealiasedTpe != null) {
         Option var7 = this.universe().TypeRefTag().unapply(dealiasedTpe);
         if (!var7.isEmpty()) {
            Types.TypeRefApi var8 = (Types.TypeRefApi)var7.get();
            if (var8 != null) {
               Option var9 = this.universe().TypeRef().unapply(var8);
               if (!var9.isEmpty()) {
                  List actualTypeArgs = (List)((Tuple3)var9.get())._3();
                  Seq params = this.constructParams(dealiasedTpe);
                  return (Seq)params.map((p) -> {
                     Types.TypeApi paramTpe = p.typeSignature();
                     return this.isValueClass(paramTpe) ? scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(p.name().decodedName().toString()), this.getUnderlyingTypeOfValueClass(paramTpe)) : scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(p.name().decodedName().toString()), paramTpe.substituteTypes(formalTypeArgs, actualTypeArgs));
                  });
               }
            }
         }
      }

      throw new MatchError(dealiasedTpe);
   }

   private Symbols.SymbolApi getCompanionConstructor(final Types.TypeApi tpe) {
      Symbols.SymbolApi var4 = tpe.typeSymbol().asClass().companion();
      Symbols.SymbolApi var10000 = this.universe().NoSymbol();
      if (var10000 == null) {
         if (var4 == null) {
            throw throwUnsupportedOperation$1(tpe);
         }
      } else if (var10000.equals(var4)) {
         throw throwUnsupportedOperation$1(tpe);
      }

      Symbols.SymbolApi var6 = var4.asTerm().typeSignature().member((Names.NameApi)this.universe().TermName().apply("apply"));
      var10000 = this.universe().NoSymbol();
      if (var10000 == null) {
         if (var6 == null) {
            throw throwUnsupportedOperation$1(tpe);
         }
      } else if (var10000.equals(var6)) {
         throw throwUnsupportedOperation$1(tpe);
      }

      return var6;
   }

   // $FF: synthetic method
   static Seq constructParams$(final ScalaReflection $this, final Types.TypeApi tpe) {
      return $this.constructParams(tpe);
   }

   default Seq constructParams(final Types.TypeApi tpe) {
      Symbols.SymbolApi var8;
      label27: {
         label26: {
            Symbols.SymbolApi var4 = tpe.member((Names.NameApi)this.universe().termNames().CONSTRUCTOR());
            var8 = this.universe().NoSymbol();
            if (var8 == null) {
               if (var4 == null) {
                  break label26;
               }
            } else if (var8.equals(var4)) {
               break label26;
            }

            var8 = var4;
            break label27;
         }

         var8 = this.getCompanionConstructor(tpe);
      }

      Symbols.SymbolApi constructorSymbol = var8;
      List var9;
      if (constructorSymbol.isMethod()) {
         var9 = constructorSymbol.asMethod().paramLists();
      } else {
         Option primaryConstructorSymbol = constructorSymbol.asTerm().alternatives().find((s) -> BoxesRunTime.boxToBoolean($anonfun$constructParams$1(s)));
         if (primaryConstructorSymbol.isEmpty()) {
            throw ExecutionErrors$.MODULE$.primaryConstructorNotFoundError(tpe.getClass());
         }

         var9 = ((Symbols.SymbolApi)primaryConstructorSymbol.get()).asMethod().paramLists();
      }

      List params = var9;
      return (Seq)params.flatten(.MODULE$.$conforms());
   }

   private static Nothing throwUnsupportedOperation$1(final Types.TypeApi tpe$3) {
      throw ExecutionErrors$.MODULE$.cannotFindConstructorForTypeError(tpe$3.toString());
   }

   // $FF: synthetic method
   static boolean $anonfun$constructParams$1(final Symbols.SymbolApi s) {
      return s.isMethod() && s.asMethod().isPrimaryConstructor();
   }

   static void $init$(final ScalaReflection $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Schema implements Product, Serializable {
      private final DataType dataType;
      private final boolean nullable;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public DataType dataType() {
         return this.dataType;
      }

      public boolean nullable() {
         return this.nullable;
      }

      public Schema copy(final DataType dataType, final boolean nullable) {
         return new Schema(dataType, nullable);
      }

      public DataType copy$default$1() {
         return this.dataType();
      }

      public boolean copy$default$2() {
         return this.nullable();
      }

      public String productPrefix() {
         return "Schema";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.dataType();
            }
            case 1 -> {
               return BoxesRunTime.boxToBoolean(this.nullable());
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
         return x$1 instanceof Schema;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "dataType";
            }
            case 1 -> {
               return "nullable";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.dataType()));
         var1 = Statics.mix(var1, this.nullable() ? 1231 : 1237);
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label51: {
               if (x$1 instanceof Schema) {
                  Schema var4 = (Schema)x$1;
                  if (this.nullable() == var4.nullable()) {
                     label44: {
                        DataType var10000 = this.dataType();
                        DataType var5 = var4.dataType();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label44;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label44;
                        }

                        if (var4.canEqual(this)) {
                           break label51;
                        }
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      public Schema(final DataType dataType, final boolean nullable) {
         this.dataType = dataType;
         this.nullable = nullable;
         Product.$init$(this);
      }
   }

   public static class Schema$ extends AbstractFunction2 implements Serializable {
      public static final Schema$ MODULE$ = new Schema$();

      public final String toString() {
         return "Schema";
      }

      public Schema apply(final DataType dataType, final boolean nullable) {
         return new Schema(dataType, nullable);
      }

      public Option unapply(final Schema x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.dataType(), BoxesRunTime.boxToBoolean(x$0.nullable()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Schema$.class);
      }
   }
}
