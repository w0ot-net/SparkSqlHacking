package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.norm$;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.math.MutableInnerProductModule;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.IndexedSeqOps;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0005c\u0001\u0002\u001f>\u0001\tC\u0011b\u0018\u0001\u0003\u0002\u0003\u0006I\u0001\u00198\t\u0011=\u0004!\u0011!Q\u0001\nAD\u0001b\u001d\u0001\u0003\u0002\u0003\u0006Y\u0001\u001e\u0005\u0006{\u0002!\tA \u0005\u0007{\u0002!\t!!\u0003\u0006\r\u0005e\u0001\u0001AA\u000e\u0011\u001d\u0011Y\u000f\u0001C)\u0005[DqAa=\u0001\t#\u0011)\u0010C\u0004\u0004\f\u0001!\tb!\u0004\t\u000f\r]\u0001\u0001\"\u0005\u0004\u001a!91\u0011\u0005\u0001\u0005\u0012\r\r\u0002bBB\u001c\u0001\u0011E1\u0011H\u0004\b\u0003?i\u0004\u0012AA\u0011\r\u0019aT\b#\u0001\u0002$!1QP\u0004C\u0001\u0003w1a!!\u0010\u000f\u0001\u0006}\u0002\"C8\u0011\u0005+\u0007I\u0011AA7\u0011%\ty\u0007\u0005B\tB\u0003%\u0001\u000fC\u0006\u0002rA\u0011)\u0019!C\u0001\u001d\u0005M\u0004BCA>!\tE\t\u0015!\u0003\u0002v!Y\u0011Q\u0010\t\u0003\u0006\u0004%\tADA:\u0011)\ty\b\u0005B\tB\u0003%\u0011Q\u000f\u0005\ngB\u0011\t\u0011)A\u0006\u0003\u0003Ca! \t\u0005\u0002\u0005\r\u0005bBAH!\u0011\u0005\u0011\u0011\u0013\u0005\b\u0003'\u0003B\u0011AAK\u0011\u001d\ty\n\u0005C\u0001\u0003[Bq!!)\u0011\t\u0003\t\u0019\u000bC\u0005\u0002*B\t\t\u0011\"\u0001\u0002,\"I\u00111\u0019\t\u0012\u0002\u0013\u0005\u0011Q\u0019\u0005\n\u0003?\u0004\u0012\u0013!C\u0001\u0003CD\u0011\"!;\u0011#\u0003%\t!a;\t\u0013\u0005=\bc#A\u0005\u0002\u0005M\u0004\"CAy!-\u0005I\u0011AA:\u0011%\t\u0019\u0010EA\u0001\n\u0003\n)\u0010C\u0005\u0003\u0004A\t\t\u0011\"\u0001\u0002n!I!Q\u0001\t\u0002\u0002\u0013\u0005!q\u0001\u0005\n\u0005\u001b\u0001\u0012\u0011!C!\u0005\u001fA\u0011B!\b\u0011\u0003\u0003%\tAa\b\t\u0013\t%\u0002#!A\u0005B\t-\u0002\"\u0003B\u0018!\u0005\u0005I\u0011\tB\u0019\u0011%\u0011\u0019\u0004EA\u0001\n\u0003\u0012)\u0004C\u0005\u00038A\t\t\u0011\"\u0011\u0003:\u001dI!Q\b\b\u0002\u0002#\u0005!q\b\u0004\n\u0003{q\u0011\u0011!E\u0001\u0005\u0003Ba!`\u0017\u0005\u0002\t\r\u0003\"\u0003B\u001a[\u0005\u0005IQ\tB\u001b\u0011%\u0011)%LA\u0001\n\u0003\u00139\u0005C\u0005\u0003`5\n\n\u0011\"\u0001\u0003b!I!1O\u0017\u0012\u0002\u0013\u0005!Q\u000f\u0005\n\u0005sj\u0013\u0011!CA\u0005wB\u0011Ba&.#\u0003%\tA!'\t\u0013\tuU&%A\u0005\u0002\t}\u0005\"\u0003BR[\u0005\u0005I\u0011\u0002BS\u0011\u001d\u0011iK\u0004C\u0002\u0005_C\u0011B!7\u000f#\u0003%\tAa7\t\u0013\t]e\"%A\u0005\u0002\t}\u0007\"\u0003BO\u001dE\u0005I\u0011\u0001Br\u0011%\u0011\u0019KDA\u0001\n\u0013\u0011)KA\u0003M\u0005\u001a;5K\u0003\u0002?\u007f\u0005Aq\u000e\u001d;j[&TXMC\u0001A\u0003\u0019\u0011'/Z3{K\u000e\u0001QCA\"K'\r\u0001A)\u0017\t\u0005\u000b\u001aCe+D\u0001>\u0013\t9UHA\nGSJ\u001cHo\u0014:eKJl\u0015N\\5nSj,'\u000f\u0005\u0002J\u00152\u0001A!B&\u0001\u0005\u0004a%!\u0001+\u0012\u00055\u001b\u0006C\u0001(R\u001b\u0005y%\"\u0001)\u0002\u000bM\u001c\u0017\r\\1\n\u0005I{%a\u0002(pi\"Lgn\u001a\t\u0003\u001dRK!!V(\u0003\u0007\u0005s\u0017\u0010E\u0002F/\"K!\u0001W\u001f\u0003\u0019\u0011KgM\u001a$v]\u000e$\u0018n\u001c8\u0011\u0005ikV\"A.\u000b\u0005q{\u0014\u0001B;uS2L!AX.\u0003'M+'/[1mSj\f'\r\\3M_\u001e<\u0017N\\4\u0002!\r|gN^3sO\u0016t7-Z\"iK\u000e\\\u0007cA1l\u0011:\u0011!-\u001b\b\u0003G\"t!\u0001Z4\u000e\u0003\u0015T!AZ!\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0015B\u0001 @\u0013\tQW(A\nGSJ\u001cHo\u0014:eKJl\u0015N\\5nSj,'/\u0003\u0002m[\n\u00012i\u001c8wKJ<WM\\2f\u0007\",7m\u001b\u0006\u0003UvJ!a\u0018$\u0002\u00035\u0004\"AT9\n\u0005I|%aA%oi\u0006)1\u000f]1dKB!Q\u000f\u001f%{\u001b\u00051(BA<@\u0003\u0011i\u0017\r\u001e5\n\u0005e4(!G'vi\u0006\u0014G.Z%o]\u0016\u0014\bK]8ek\u000e$Xj\u001c3vY\u0016\u0004\"AT>\n\u0005q|%A\u0002#pk\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0006\u007f\u0006\u0015\u0011q\u0001\u000b\u0005\u0003\u0003\t\u0019\u0001E\u0002F\u0001!CQa\u001d\u0003A\u0004QDQa\u0018\u0003A\u0002\u0001DQa\u001c\u0003A\u0002A$\u0002\"a\u0003\u0002\u0010\u0005M\u0011Q\u0003\u000b\u0005\u0003\u0003\ti\u0001C\u0003t\u000b\u0001\u000fA\u000f\u0003\u0005\u0002\u0012\u0015\u0001\n\u00111\u0001q\u0003\u001di\u0017\r_%uKJDqa\\\u0003\u0011\u0002\u0003\u0007\u0001\u000f\u0003\u0005\u0002\u0018\u0015\u0001\n\u00111\u0001{\u0003%!x\u000e\\3sC:\u001cWMA\u0004ISN$xN]=\u0011\t\u0005u\u0001\u0003\u0013\b\u0003\u000b6\tQ\u0001\u0014\"G\u000fN\u0003\"!\u0012\b\u0014\u000b9\t)#a\u000b\u0011\u00079\u000b9#C\u0002\u0002*=\u0013a!\u00118z%\u00164\u0007\u0003BA\u0017\u0003oi!!a\f\u000b\t\u0005E\u00121G\u0001\u0003S>T!!!\u000e\u0002\t)\fg/Y\u0005\u0005\u0003s\tyC\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002\"\tI\u0012\t\u001d9s_bLW.\u0019;f\u0013:4XM]:f\u0011\u0016\u001c8/[1o+\u0011\t\t%!\u0016\u0014\u0013A\t)#a\u0011\u0002X\u0005u\u0003CBA#\u0003\u0017\ny%\u0004\u0002\u0002H)\u0019\u0011\u0011J \u0002\r1Lg.\u00197h\u0013\u0011\ti%a\u0012\u0003\u00159+X.\u001a:jG>\u00038\u000fE\u0003\u0002RA\t\u0019&D\u0001\u000f!\rI\u0015Q\u000b\u0003\u0006\u0017B\u0011\r\u0001\u0014\t\u0004\u001d\u0006e\u0013bAA.\u001f\n9\u0001K]8ek\u000e$\b\u0003BA0\u0003SrA!!\u0019\u0002f9\u0019A-a\u0019\n\u0003AK1!a\u001aP\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u000f\u0002l)\u0019\u0011qM(\u0016\u0003A\f!!\u001c\u0011\u0002\u000f5,Wn\u0015;faV\u0011\u0011Q\u000f\t\u0007\u0003?\n9(a\u0015\n\t\u0005e\u00141\u000e\u0002\u000b\u0013:$W\r_3e'\u0016\f\u0018\u0001C7f[N#X\r\u001d\u0011\u0002\u00195,Wn\u0012:bI\u0012+G\u000e^1\u0002\u001b5,Wn\u0012:bI\u0012+G\u000e^1!!\u0015)\b0a\u0015{)!\t))!#\u0002\f\u00065E\u0003BA(\u0003\u000fCaa\u001d\rA\u0004\u0005\u0005\u0005\"B8\u0019\u0001\u0004\u0001\b\"CA91A\u0005\t\u0019AA;\u0011%\ti\b\u0007I\u0001\u0002\u0004\t)(\u0001\u0003sKB\u0014XCAA(\u0003\u001d)\b\u000fZ1uK\u0012$b!a\u0014\u0002\u0018\u0006m\u0005bBAM5\u0001\u0007\u00111K\u0001\u0005gR,\u0007\u000fC\u0004\u0002\u001ej\u0001\r!a\u0015\u0002\u0013\u001d\u0014\u0018\r\u001a#fYR\f\u0017!\u00045jgR|'/\u001f'f]\u001e$\b.\u0001\u0004%i&lWm\u001d\u000b\u0005\u0003'\n)\u000bC\u0004\u0002(r\u0001\r!a\u0015\u0002\t\u001d\u0014\u0018\rZ\u0001\u0005G>\u0004\u00180\u0006\u0003\u0002.\u0006UF\u0003CAX\u0003w\u000bi,!1\u0015\t\u0005E\u0016q\u0017\t\u0006\u0003#\u0002\u00121\u0017\t\u0004\u0013\u0006UF!B&\u001e\u0005\u0004a\u0005BB:\u001e\u0001\b\tI\fE\u0003vq\u0006M&\u0010C\u0004p;A\u0005\t\u0019\u00019\t\u0013\u0005ET\u0004%AA\u0002\u0005}\u0006CBA0\u0003o\n\u0019\fC\u0005\u0002~u\u0001\n\u00111\u0001\u0002@\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT\u0003BAd\u0003;,\"!!3+\u0007A\fYm\u000b\u0002\u0002NB!\u0011qZAm\u001b\t\t\tN\u0003\u0003\u0002T\u0006U\u0017!C;oG\",7m[3e\u0015\r\t9nT\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAn\u0003#\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0015YeD1\u0001M\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*B!a9\u0002hV\u0011\u0011Q\u001d\u0016\u0005\u0003k\nY\rB\u0003L?\t\u0007A*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\t\u0005\r\u0018Q\u001e\u0003\u0006\u0017\u0002\u0012\r\u0001T\u0001\u0011[\u0016l7\u000b^3qI\u0005\u001c7-Z:tIE\nQ#\\3n\u000fJ\fG\rR3mi\u0006$\u0013mY2fgN$#'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003o\u0004B!!?\u0002\u00006\u0011\u00111 \u0006\u0005\u0003{\f\u0019$\u0001\u0003mC:<\u0017\u0002\u0002B\u0001\u0003w\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004'\n%\u0001\u0002\u0003B\u0006K\u0005\u0005\t\u0019\u00019\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011\t\u0002E\u0003\u0003\u0014\te1+\u0004\u0002\u0003\u0016)\u0019!qC(\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003\u001c\tU!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$BA!\t\u0003(A\u0019aJa\t\n\u0007\t\u0015rJA\u0004C_>dW-\u00198\t\u0011\t-q%!AA\u0002M\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011q\u001fB\u0017\u0011!\u0011Y\u0001KA\u0001\u0002\u0004\u0001\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003A\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003o\fa!Z9vC2\u001cH\u0003\u0002B\u0011\u0005wA\u0001Ba\u0003,\u0003\u0003\u0005\raU\u0001\u001a\u0003B\u0004(o\u001c=j[\u0006$X-\u00138wKJ\u001cX\rS3tg&\fg\u000eE\u0002\u0002R5\u001aR!LA\u0013\u0003W!\"Aa\u0010\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\t%#\u0011\u000b\u000b\t\u0005\u0017\u00129F!\u0017\u0003^Q!!Q\nB*!\u0015\t\t\u0006\u0005B(!\rI%\u0011\u000b\u0003\u0006\u0017B\u0012\r\u0001\u0014\u0005\u0007gB\u0002\u001dA!\u0016\u0011\u000bUD(q\n>\t\u000b=\u0004\u0004\u0019\u00019\t\u0013\u0005E\u0004\u0007%AA\u0002\tm\u0003CBA0\u0003o\u0012y\u0005C\u0005\u0002~A\u0002\n\u00111\u0001\u0003\\\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$#'\u0006\u0003\u0003d\tETC\u0001B3U\u0011\u00119'a3\u0011\u000b\t%$qN'\u000e\u0005\t-$\u0002\u0002B7\u0005+\t\u0011\"[7nkR\f'\r\\3\n\t\u0005e$1\u000e\u0003\u0006\u0017F\u0012\r\u0001T\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%gU!!1\rB<\t\u0015Y%G1\u0001M\u0003\u001d)h.\u00199qYf,BA! \u0003\u0010R!!q\u0010BI!\u0015q%\u0011\u0011BC\u0013\r\u0011\u0019i\u0014\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u00119\u00139\t\u001dBF\u0005\u0017K1A!#P\u0005\u0019!V\u000f\u001d7fgA1\u0011qLA<\u0005\u001b\u00032!\u0013BH\t\u0015Y5G1\u0001M\u0011%\u0011\u0019jMA\u0001\u0002\u0004\u0011)*A\u0002yIA\u0002R!!\u0015\u0011\u0005\u001b\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012T\u0003\u0002B2\u00057#Qa\u0013\u001bC\u00021\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aT\u0003\u0002B2\u0005C#QaS\u001bC\u00021\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa*\u0011\t\u0005e(\u0011V\u0005\u0005\u0005W\u000bYP\u0001\u0004PE*,7\r^\u0001\u0017[VdG/\u001b9ms&sg/\u001a:tK\"+7o]5b]V!!\u0011\u0017Bi)\u0011\u0011\u0019La5\u0011\u0015\tU&\u0011\u0019Bg\u0005\u001f\u0014yM\u0004\u0003\u00038\nuVB\u0001B]\u0015\u0011\u0011Y,a\u0012\u0002\u0013=\u0004XM]1u_J\u001c\u0018\u0002\u0002B`\u0005s\u000b1b\u00149Nk2l\u0015\r\u001e:jq&!!1\u0019Bc\u0005\u0015IU\u000e\u001d73\u0013\u0011\u00119M!3\u0003\u000bU3UO\\2\u000b\u0007\t-w(A\u0004hK:,'/[2\u0011\u000b\u0005E\u0003Ca4\u0011\u0007%\u0013\t\u000eB\u0003Lo\t\u0007A\nC\u0004\u0003V^\u0002\u001dAa6\u0002\rY\u001c\b/Y2f!\u0015)\bPa4{\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cU!\u0011q\u0019Bo\t\u0015Y\u0005H1\u0001M+\u0011\t9M!9\u0005\u000b-K$\u0019\u0001'\u0016\t\t\u0015(\u0011^\u000b\u0003\u0005OT3A_Af\t\u0015Y%H1\u0001M\u00039\tGM[;ti\u001a+hn\u0019;j_:$2A\u0016Bx\u0011\u0019\u0011\tp\u0002a\u0001-\u0006\ta-\u0001\u0005uC.,7\u000b^3q)\u001dA%q_B\u0002\u0007\u000fAqA!?\t\u0001\u0004\u0011Y0A\u0003ti\u0006$X\r\u0005\u0003\u0003~\n}X\"\u0001\u0001\n\u0007\r\u0005aIA\u0003Ti\u0006$X\r\u0003\u0004\u0004\u0006!\u0001\r\u0001S\u0001\u0004I&\u0014\bBBB\u0005\u0011\u0001\u0007!0\u0001\u0005ti\u0016\u00048+\u001b>f\u00039Ig.\u001b;jC2D\u0015n\u001d;pef$baa\u0004\u0004\u0012\rM\u0001c\u0001B\u007f\r!1!\u0011_\u0005A\u0002YCaa!\u0006\n\u0001\u0004A\u0015!\u0001=\u0002-\rDwn\\:f\t\u0016\u001c8-\u001a8u\t&\u0014Xm\u0019;j_:$R\u0001SB\u000e\u0007;AqA!?\u000b\u0001\u0004\u0011Y\u0010\u0003\u0004\u0004 )\u0001\rAV\u0001\u0003M:\fQ\"\u001e9eCR,\u0007*[:u_JLH\u0003DB\b\u0007K\u0019Ic!\f\u00042\rM\u0002BBB\u0014\u0017\u0001\u0007\u0001*\u0001\u0003oK^D\u0006BBB\u0016\u0017\u0001\u0007\u0001*A\u0004oK^<%/\u00193\t\r\r=2\u00021\u0001{\u0003\u0019qWm\u001e,bY\"1!\u0011_\u0006A\u0002YCqa!\u000e\f\u0001\u0004\u0011Y0\u0001\u0005pY\u0012\u001cF/\u0019;f\u0003E!W\r^3s[&tWm\u0015;faNK'0\u001a\u000b\bu\u000em2QHB \u0011\u001d\u0011I\u0010\u0004a\u0001\u0005wDaA!=\r\u0001\u00041\u0006BBB\u0003\u0019\u0001\u0007\u0001\n"
)
public class LBFGS extends FirstOrderMinimizer {
   private final int m;
   private final MutableInnerProductModule space;

   public static double $lessinit$greater$default$3() {
      return LBFGS$.MODULE$.$lessinit$greater$default$3();
   }

   public static int $lessinit$greater$default$2() {
      return LBFGS$.MODULE$.$lessinit$greater$default$2();
   }

   public static int $lessinit$greater$default$1() {
      return LBFGS$.MODULE$.$lessinit$greater$default$1();
   }

   public static UFunc.UImpl2 multiplyInverseHessian(final MutableInnerProductModule vspace) {
      return LBFGS$.MODULE$.multiplyInverseHessian(vspace);
   }

   public DiffFunction adjustFunction(final DiffFunction f) {
      return f.cached(this.space.copy());
   }

   public Object takeStep(final FirstOrderMinimizer.State state, final Object dir, final double stepSize) {
      Object newX = ((ImmutableNumericOps)this.space.hasOps().apply(dir)).$times(BoxesRunTime.boxToDouble(stepSize), this.space.mulVS_M());
      ((NumericOps)this.space.hasOps().apply(newX)).$colon$plus$eq(state.x(), this.space.addIntoVV());
      return newX;
   }

   public ApproximateInverseHessian initialHistory(final DiffFunction f, final Object x) {
      return new ApproximateInverseHessian(this.m, LBFGS.ApproximateInverseHessian$.MODULE$.$lessinit$greater$default$2(), LBFGS.ApproximateInverseHessian$.MODULE$.$lessinit$greater$default$3(), this.space);
   }

   public Object chooseDescentDirection(final FirstOrderMinimizer.State state, final DiffFunction fn) {
      return ((ApproximateInverseHessian)state.history()).$times(state.grad());
   }

   public ApproximateInverseHessian updateHistory(final Object newX, final Object newGrad, final double newVal, final DiffFunction f, final FirstOrderMinimizer.State oldState) {
      return ((ApproximateInverseHessian)oldState.history()).updated(((ImmutableNumericOps)this.space.hasOps().apply(newX)).$minus(oldState.x(), this.space.subVV()), ((ImmutableNumericOps)this.space.hasOps().apply(newGrad)).$minus$colon$minus(oldState.grad(), this.space.subVV()));
   }

   public double determineStepSize(final FirstOrderMinimizer.State state, final DiffFunction f, final Object dir) {
      Object x = state.x();
      Object grad = state.grad();
      DiffFunction ff = LineSearch$.MODULE$.functionFromSearchDirection(f, x, dir, this.space);
      StrongWolfeLineSearch search = new StrongWolfeLineSearch(10, 10);
      double alpha = search.minimize(ff, (double)state.iter() == (double)0.0F ? (double)1.0F / BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(dir, this.space.normImpl())) : (double)1.0F);
      if (alpha * BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(grad, this.space.normImpl())) < 1.0E-10) {
         throw new StepSizeUnderflow();
      } else {
         return alpha;
      }
   }

   public LBFGS(final FirstOrderMinimizer.ConvergenceCheck convergenceCheck, final int m, final MutableInnerProductModule space) {
      super(convergenceCheck, space);
      this.m = m;
      this.space = space;
      .MODULE$.require(m > 0);
   }

   public LBFGS(final int maxIter, final int m, final double tolerance, final MutableInnerProductModule space) {
      this(FirstOrderMinimizer$.MODULE$.defaultConvergenceCheck(maxIter, tolerance, FirstOrderMinimizer$.MODULE$.defaultConvergenceCheck$default$3(), FirstOrderMinimizer$.MODULE$.defaultConvergenceCheck$default$4(), space), m, space);
   }

   public static class ApproximateInverseHessian implements NumericOps, Product, Serializable {
      private final int m;
      private final IndexedSeq memStep;
      private final IndexedSeq memGradDelta;
      private final MutableInnerProductModule space;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public final Object $plus(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$plus$(this, b, op);
      }

      public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$eq$(this, b, op);
      }

      public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$plus$eq$(this, b, op);
      }

      public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$times$eq$(this, b, op);
      }

      public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$plus$eq$(this, b, op);
      }

      public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$times$eq$(this, b, op);
      }

      public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$minus$eq$(this, b, op);
      }

      public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$percent$eq$(this, b, op);
      }

      public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$percent$eq$(this, b, op);
      }

      public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$minus$eq$(this, b, op);
      }

      public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$div$eq$(this, b, op);
      }

      public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$up$eq$(this, b, op);
      }

      public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$div$eq$(this, b, op);
      }

      public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$less$colon$less$(this, b, op);
      }

      public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$less$colon$eq$(this, b, op);
      }

      public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$greater$colon$greater$(this, b, op);
      }

      public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$greater$colon$eq$(this, b, op);
      }

      public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$amp$eq$(this, b, op);
      }

      public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$bar$eq$(this, b, op);
      }

      public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$up$up$eq$(this, b, op);
      }

      public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$amp$eq$(this, b, op);
      }

      public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$bar$eq$(this, b, op);
      }

      public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$up$up$eq$(this, b, op);
      }

      public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
      }

      public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$colon$times$(this, b, op);
      }

      public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
      }

      public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
      }

      public final Object unary_$minus(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$minus$(this, op);
      }

      public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
      }

      public final Object $minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$(this, b, op);
      }

      public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
      }

      public final Object $percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$(this, b, op);
      }

      public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$colon$div$(this, b, op);
      }

      public final Object $div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$(this, b, op);
      }

      public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$colon$up$(this, b, op);
      }

      public final Object dot(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.dot$(this, b, op);
      }

      public final Object unary_$bang(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$bang$(this, op);
      }

      public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
      }

      public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
      }

      public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
      }

      public final Object $amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$(this, b, op);
      }

      public final Object $bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$(this, b, op);
      }

      public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$(this, b, op);
      }

      public final Object $times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$(this, b, op);
      }

      public final Object t(final CanTranspose op) {
         return ImmutableNumericOps.t$(this, op);
      }

      public Object $bslash(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bslash$(this, b, op);
      }

      public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
         return ImmutableNumericOps.t$(this, a, b, op, canSlice);
      }

      public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
         return ImmutableNumericOps.t$(this, a, op, canSlice);
      }

      public IndexedSeq memStep$access$1() {
         return this.memStep;
      }

      public IndexedSeq memGradDelta$access$2() {
         return this.memGradDelta;
      }

      public int m() {
         return this.m;
      }

      public IndexedSeq memStep() {
         return this.memStep;
      }

      public IndexedSeq memGradDelta() {
         return this.memGradDelta;
      }

      public ApproximateInverseHessian repr() {
         return this;
      }

      public ApproximateInverseHessian updated(final Object step, final Object gradDelta) {
         IndexedSeq memStep = (IndexedSeq)((IndexedSeqOps)this.memStep().$plus$colon(step)).take(this.m());
         IndexedSeq memGradDelta = (IndexedSeq)((IndexedSeqOps)this.memGradDelta().$plus$colon(gradDelta)).take(this.m());
         return new ApproximateInverseHessian(this.m(), memStep, memGradDelta, this.space);
      }

      public int historyLength() {
         return this.memStep().length();
      }

      public Object $times(final Object grad) {
         double var10000;
         if (this.historyLength() > 0) {
            Object prevStep = this.memStep().head();
            Object prevGradStep = this.memGradDelta().head();
            double sy = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.space.hasOps().apply(prevStep)).dot(prevGradStep, this.space.dotVV()));
            double yy = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.space.hasOps().apply(prevGradStep)).dot(prevGradStep, this.space.dotVV()));
            if (sy < (double)0 || Double.isNaN(sy)) {
               throw new NaNHistory();
            }

            var10000 = sy / yy;
         } else {
            var10000 = (double)1.0F;
         }

         double diag = var10000;
         Object dir = this.space.copy().apply(grad);
         double[] as = new double[this.m()];
         double[] rho = new double[this.m()];
         scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.historyLength()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            rho[i] = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.space.hasOps().apply(this.memStep().apply(i))).dot(this.memGradDelta().apply(i), this.space.dotVV()));
            as[i] = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.space.hasOps().apply(this.memStep().apply(i))).dot(dir, this.space.dotVV())) / rho[i];
            if (Double.isNaN(as[i])) {
               throw new NaNHistory();
            } else {
               breeze.linalg.package$.MODULE$.axpy(BoxesRunTime.boxToDouble(-as[i]), this.memGradDelta().apply(i), dir, this.space.scaleAddVV());
            }
         });
         ((NumericOps)this.space.hasOps().apply(dir)).$times$eq(BoxesRunTime.boxToDouble(diag), this.space.mulIntoVS());
         scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(this.historyLength() - 1), 0).by(-1).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            double beta = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.space.hasOps().apply(this.memGradDelta().apply(i))).dot(dir, this.space.dotVV())) / rho[i];
            breeze.linalg.package$.MODULE$.axpy(BoxesRunTime.boxToDouble(as[i] - beta), this.memStep().apply(i), dir, this.space.scaleAddVV());
         });
         ((NumericOps)this.space.hasOps().apply(dir)).$times$eq(BoxesRunTime.boxToDouble((double)-1.0F), this.space.mulIntoVS());
         return dir;
      }

      public ApproximateInverseHessian copy(final int m, final IndexedSeq memStep, final IndexedSeq memGradDelta, final MutableInnerProductModule space) {
         return new ApproximateInverseHessian(m, memStep, memGradDelta, space);
      }

      public int copy$default$1() {
         return this.m();
      }

      public IndexedSeq copy$default$2() {
         return this.memStep();
      }

      public IndexedSeq copy$default$3() {
         return this.memGradDelta();
      }

      public String productPrefix() {
         return "ApproximateInverseHessian";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.m());
               break;
            case 1:
               var10000 = this.memStep$access$1();
               break;
            case 2:
               var10000 = this.memGradDelta$access$2();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ApproximateInverseHessian;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "m";
               break;
            case 1:
               var10000 = "memStep";
               break;
            case 2:
               var10000 = "memGradDelta";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.m());
         var1 = Statics.mix(var1, Statics.anyHash(this.memStep$access$1()));
         var1 = Statics.mix(var1, Statics.anyHash(this.memGradDelta$access$2()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var9;
         if (this != x$1) {
            label65: {
               boolean var2;
               if (x$1 instanceof ApproximateInverseHessian) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label47: {
                     ApproximateInverseHessian var4 = (ApproximateInverseHessian)x$1;
                     if (this.m() == var4.m()) {
                        label56: {
                           IndexedSeq var10000 = this.memStep$access$1();
                           IndexedSeq var5 = var4.memStep$access$1();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label56;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label56;
                           }

                           var10000 = this.memGradDelta$access$2();
                           IndexedSeq var6 = var4.memGradDelta$access$2();
                           if (var10000 == null) {
                              if (var6 != null) {
                                 break label56;
                              }
                           } else if (!var10000.equals(var6)) {
                              break label56;
                           }

                           if (var4.canEqual(this)) {
                              var9 = true;
                              break label47;
                           }
                        }
                     }

                     var9 = false;
                  }

                  if (var9) {
                     break label65;
                  }
               }

               var9 = false;
               return var9;
            }
         }

         var9 = true;
         return var9;
      }

      public ApproximateInverseHessian(final int m, final IndexedSeq memStep, final IndexedSeq memGradDelta, final MutableInnerProductModule space) {
         this.m = m;
         this.memStep = memStep;
         this.memGradDelta = memGradDelta;
         this.space = space;
         ImmutableNumericOps.$init$(this);
         NumericOps.$init$(this);
         Product.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class ApproximateInverseHessian$ implements Serializable {
      public static final ApproximateInverseHessian$ MODULE$ = new ApproximateInverseHessian$();

      public IndexedSeq $lessinit$greater$default$2() {
         return (IndexedSeq)scala.package..MODULE$.IndexedSeq().empty();
      }

      public IndexedSeq $lessinit$greater$default$3() {
         return (IndexedSeq)scala.package..MODULE$.IndexedSeq().empty();
      }

      public final String toString() {
         return "ApproximateInverseHessian";
      }

      public ApproximateInverseHessian apply(final int m, final IndexedSeq memStep, final IndexedSeq memGradDelta, final MutableInnerProductModule space) {
         return new ApproximateInverseHessian(m, memStep, memGradDelta, space);
      }

      public IndexedSeq apply$default$2() {
         return (IndexedSeq)scala.package..MODULE$.IndexedSeq().empty();
      }

      public IndexedSeq apply$default$3() {
         return (IndexedSeq)scala.package..MODULE$.IndexedSeq().empty();
      }

      public Option unapply(final ApproximateInverseHessian x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.m()), x$0.memStep(), x$0.memGradDelta())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ApproximateInverseHessian$.class);
      }
   }
}
