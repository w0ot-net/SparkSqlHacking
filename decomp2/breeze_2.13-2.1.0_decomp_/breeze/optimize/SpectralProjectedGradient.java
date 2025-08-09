package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.norm$;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.math.InnerProductModule;
import breeze.math.Module;
import breeze.math.MutableVectorField;
import breeze.util.Isomorphism;
import java.io.Serializable;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.IndexedSeqOps;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\rua\u0001B\"E\u0001%C\u0001\"\u001b\u0001\u0003\u0006\u0004%\tA\u001b\u0005\t]\u0002\u0011\t\u0011)A\u0005W\"Aq\u000e\u0001B\u0001B\u0003%\u0001\u000f\u0003\u0005t\u0001\t\u0005\t\u0015!\u0003q\u0011!!\bA!A!\u0002\u0013)\b\u0002\u0003=\u0001\u0005\u0003\u0005\u000b\u0011\u00029\t\u0011e\u0004!\u0011!Q\u0001\nAD\u0001B\u001f\u0001\u0003\u0002\u0003\u0006I!\u001e\u0005\tw\u0002\u0011\t\u0011)A\u0005k\"AA\u0010\u0001BC\u0002\u0013\u0005Q\u0010C\u0005\u0002\u0004\u0001\u0011\t\u0011)A\u0005}\"I\u0011Q\u0001\u0001\u0003\u0006\u0004%\t! \u0005\n\u0003\u000f\u0001!\u0011!Q\u0001\nyD!\"!\u0003\u0001\u0005\u000b\u0007I\u0011AA\u0006\u0011%\ti\u0001\u0001B\u0001B\u0003%Q\u000f\u0003\u0006\u0002\u0010\u0001\u0011)\u0019!C\u0001\u0003\u0017A\u0011\"!\u0005\u0001\u0005\u0003\u0005\u000b\u0011B;\t\u0015\u0005M\u0001A!A!\u0002\u0017\t)\u0002C\u0004\u0002\"\u0001!\t!a\t\u0007\r\u0005\r\u0003\u0001QA#\u0011)\tY\u0007\u0006BK\u0002\u0013\u0005\u0011Q\u000e\u0005\n\u0003_\"\"\u0011#Q\u0001\nAD!\"!\u001d\u0015\u0005+\u0007I\u0011AA:\u0011)\tY\b\u0006B\tB\u0003%\u0011Q\u000f\u0005\b\u0003C!B\u0011AA?\u0011%\t9\tFA\u0001\n\u0003\tI\tC\u0005\u0002\u0010R\t\n\u0011\"\u0001\u0002\u0012\"I\u0011q\u0015\u000b\u0012\u0002\u0013\u0005\u0011\u0011\u0016\u0005\n\u0003[#\u0012\u0011!C!\u0003_C\u0011\"!1\u0015\u0003\u0003%\t!a\u0003\t\u0013\u0005\rG#!A\u0005\u0002\u0005\u0015\u0007\"CAf)\u0005\u0005I\u0011IAg\u0011%\tY\u000eFA\u0001\n\u0003\ti\u000eC\u0005\u0002bR\t\t\u0011\"\u0011\u0002d\"I\u0011q\u001d\u000b\u0002\u0002\u0013\u0005\u0013\u0011\u001e\u0005\n\u0003W$\u0012\u0011!C!\u0003[D\u0011\"a<\u0015\u0003\u0003%\t%!=\b\u0013\u0005U\b!!A\t\u0002\u0005]h!CA\"\u0001\u0005\u0005\t\u0012AA}\u0011\u001d\t\tc\nC\u0001\u0005#A\u0011\"a;(\u0003\u0003%)%!<\t\u0013\tMq%!A\u0005\u0002\nU\u0001\"\u0003B\u000eO\u0005\u0005I\u0011\u0011B\u000f\u0011\u001d\u0011y\u0003\u0001C)\u0005cAqAa\u000f\u0001\t#\u0011i\u0004C\u0004\u0003H\u0001!\tF!\u0013\t\u000f\t\r\u0004\u0001\"\u0015\u0003f!9!1\u000f\u0001\u0005R\tU\u0004b\u0002B>\u0001\u0011E#Q\u0010\u0005\b\u0005\u000f\u0003A\u0011\u0002BE\u000f%\u0011\u0019\fRA\u0001\u0012\u0003\u0011)L\u0002\u0005D\t\u0006\u0005\t\u0012\u0001B\\\u0011\u001d\t\t\u0003\u000eC\u0001\u0005sC\u0011Ba/5#\u0003%\tA!0\t\u0013\t%G'%A\u0005\u0002\t-\u0007\"\u0003BhiE\u0005I\u0011\u0001Bi\u0011%\u0011)\u000eNI\u0001\n\u0003\u00119\u000eC\u0005\u0003`R\n\n\u0011\"\u0001\u0003b\"I!Q\u001d\u001b\u0012\u0002\u0013\u0005!q\u001d\u0005\n\u0005W$\u0014\u0013!C\u0001\u0005[D\u0011B!=5#\u0003%\tAa=\t\u0013\t]H'%A\u0005\u0002\te\b\"CB\u0001iE\u0005I\u0011AB\u0002\u0011%\u00199\u0001NI\u0001\n\u0003\u0019I\u0001C\u0005\u0004\u000eQ\n\n\u0011\"\u0001\u0004\u0010!I11\u0003\u001b\u0002\u0002\u0013%1Q\u0003\u0002\u001a'B,7\r\u001e:bYB\u0013xN[3di\u0016$wI]1eS\u0016tGO\u0003\u0002F\r\u0006Aq\u000e\u001d;j[&TXMC\u0001H\u0003\u0019\u0011'/Z3{K\u000e\u0001QC\u0001&R'\u0011\u00011\nY2\u0011\t1ku*X\u0007\u0002\t&\u0011a\n\u0012\u0002\u0014\r&\u00148\u000f^(sI\u0016\u0014X*\u001b8j[&TXM\u001d\t\u0003!Fc\u0001\u0001B\u0003S\u0001\t\u00071KA\u0001U#\t!&\f\u0005\u0002V16\taKC\u0001X\u0003\u0015\u00198-\u00197b\u0013\tIfKA\u0004O_RD\u0017N\\4\u0011\u0005U[\u0016B\u0001/W\u0005\r\te.\u001f\t\u0004\u0019z{\u0015BA0E\u00051!\u0015N\u001a4Gk:\u001cG/[8o!\ra\u0015mT\u0005\u0003E\u0012\u0013!\u0002\u0015:pU\u0016\u001cG/\u001b8h!\t!w-D\u0001f\u0015\t1g)\u0001\u0003vi&d\u0017B\u00015f\u0005M\u0019VM]5bY&T\u0018M\u00197f\u0019><w-\u001b8h\u0003)\u0001(o\u001c6fGRLwN\\\u000b\u0002WB!Q\u000b\\(P\u0013\tigKA\u0005Gk:\u001cG/[8oc\u0005Y\u0001O]8kK\u000e$\u0018n\u001c8!\u0003%!x\u000e\\3sC:\u001cW\r\u0005\u0002Vc&\u0011!O\u0016\u0002\u0007\t>,(\r\\3\u0002\u000fM,hM\u001a#fG\u0006QaM^1m\u001b\u0016lwN]=\u0011\u0005U3\u0018BA<W\u0005\rIe\u000e^\u0001\tC2\u0004\b.Y'bq\u0006A\u0011\r\u001c9iC6Kg.\u0001\u0005cE6+Wn\u001c:z\u0003\u001di\u0017\r_%uKJ\f\u0001\"\u001b8ji\u001a+\u0017m]\u000b\u0002}B\u0011Qk`\u0005\u0004\u0003\u00031&a\u0002\"p_2,\u0017M\\\u0001\nS:LGOR3bg\u0002\n1bY;sm&d\u0017N\\3be\u0006a1-\u001e:wS2Lg.Z1sA\u00051!M\u0019+za\u0016,\u0012!^\u0001\bE\n$\u0016\u0010]3!\u0003!i\u0017\r_*sG\"$\u0018!C7bqN\u00138\r\u001b;!\u0003\u0015\u0019\b/Y2f!\u0019\t9\"!\bPa6\u0011\u0011\u0011\u0004\u0006\u0004\u000371\u0015\u0001B7bi\"LA!a\b\u0002\u001a\t\u0011R*\u001e;bE2,g+Z2u_J4\u0015.\u001a7e\u0003\u0019a\u0014N\\5u}QQ\u0012QEA\u0016\u0003[\ty#!\r\u00024\u0005U\u0012qGA\u001d\u0003w\ti$a\u0010\u0002BQ!\u0011qEA\u0015!\ra\u0005a\u0014\u0005\b\u0003'\u0019\u00029AA\u000b\u0011\u001dI7\u0003%AA\u0002-Dqa\\\n\u0011\u0002\u0003\u0007\u0001\u000fC\u0004t'A\u0005\t\u0019\u00019\t\u000fQ\u001c\u0002\u0013!a\u0001k\"9\u0001p\u0005I\u0001\u0002\u0004\u0001\bbB=\u0014!\u0003\u0005\r\u0001\u001d\u0005\buN\u0001\n\u00111\u0001v\u0011\u001dY8\u0003%AA\u0002UDq\u0001`\n\u0011\u0002\u0003\u0007a\u0010\u0003\u0005\u0002\u0006M\u0001\n\u00111\u0001\u007f\u0011!\tIa\u0005I\u0001\u0002\u0004)\b\u0002CA\b'A\u0005\t\u0019A;\u0003\u000f!K7\u000f^8ssN9A#a\u0012\u0002N\u0005M\u0003cA+\u0002J%\u0019\u00111\n,\u0003\r\u0005s\u0017PU3g!\r)\u0016qJ\u0005\u0004\u0003#2&a\u0002)s_\u0012,8\r\u001e\t\u0005\u0003+\n)G\u0004\u0003\u0002X\u0005\u0005d\u0002BA-\u0003?j!!a\u0017\u000b\u0007\u0005u\u0003*\u0001\u0004=e>|GOP\u0005\u0002/&\u0019\u00111\r,\u0002\u000fA\f7m[1hK&!\u0011qMA5\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\r\t\u0019GV\u0001\bC2\u0004\b.\u0019\"C+\u0005\u0001\u0018\u0001C1ma\"\f'I\u0011\u0011\u0002\u000b\u00194\u0018\r\\:\u0016\u0005\u0005U\u0004#BA+\u0003o\u0002\u0018\u0002BA=\u0003S\u0012!\"\u00138eKb,GmU3r\u0003\u00191g/\u00197tAQ1\u0011qPAB\u0003\u000b\u00032!!!\u0015\u001b\u0005\u0001\u0001BBA63\u0001\u0007\u0001\u000fC\u0004\u0002re\u0001\r!!\u001e\u0002\t\r|\u0007/\u001f\u000b\u0007\u0003\u007f\nY)!$\t\u0011\u0005-$\u0004%AA\u0002AD\u0011\"!\u001d\u001b!\u0003\u0005\r!!\u001e\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u00111\u0013\u0016\u0004a\u0006U5FAAL!\u0011\tI*a)\u000e\u0005\u0005m%\u0002BAO\u0003?\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u0005f+\u0001\u0006b]:|G/\u0019;j_:LA!!*\u0002\u001c\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00111\u0016\u0016\u0005\u0003k\n)*A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003c\u0003B!a-\u0002>6\u0011\u0011Q\u0017\u0006\u0005\u0003o\u000bI,\u0001\u0003mC:<'BAA^\u0003\u0011Q\u0017M^1\n\t\u0005}\u0016Q\u0017\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019!,a2\t\u0011\u0005%w$!AA\u0002U\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAh!\u0015\t\t.a6[\u001b\t\t\u0019NC\u0002\u0002VZ\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\tI.a5\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004}\u0006}\u0007\u0002CAeC\u0005\u0005\t\u0019\u0001.\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003c\u000b)\u000f\u0003\u0005\u0002J\n\n\t\u00111\u0001v\u0003!A\u0017m\u001d5D_\u0012,G#A;\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!-\u0002\r\u0015\fX/\u00197t)\rq\u00181\u001f\u0005\t\u0003\u0013,\u0013\u0011!a\u00015\u00069\u0001*[:u_JL\bcAAAOM)q%a?\u0003\bAI\u0011Q B\u0002a\u0006U\u0014qP\u0007\u0003\u0003\u007fT1A!\u0001W\u0003\u001d\u0011XO\u001c;j[\u0016LAA!\u0002\u0002\u0000\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\t%!qB\u0007\u0003\u0005\u0017QAA!\u0004\u0002:\u0006\u0011\u0011n\\\u0005\u0005\u0003O\u0012Y\u0001\u0006\u0002\u0002x\u0006)\u0011\r\u001d9msR1\u0011q\u0010B\f\u00053Aa!a\u001b+\u0001\u0004\u0001\bbBA9U\u0001\u0007\u0011QO\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011yBa\u000b\u0011\u000bU\u0013\tC!\n\n\u0007\t\rbK\u0001\u0004PaRLwN\u001c\t\u0007+\n\u001d\u0002/!\u001e\n\u0007\t%bK\u0001\u0004UkBdWM\r\u0005\n\u0005[Y\u0013\u0011!a\u0001\u0003\u007f\n1\u0001\u001f\u00131\u00039Ig.\u001b;jC2D\u0015n\u001d;pef$b!a \u00034\t]\u0002B\u0002B\u001bY\u0001\u0007Q,A\u0001g\u0011\u0019\u0011I\u0004\fa\u0001\u001f\u0006!\u0011N\\5u\u0003\u001d\u0011'-\u00117qQ\u0006$R\u0001\u001dB \u0005\u0007BaA!\u0011.\u0001\u0004y\u0015!A:\t\r\t\u0015S\u00061\u0001P\u0003\u0005I\u0018!D;qI\u0006$X\rS5ti>\u0014\u0018\u0010\u0006\u0007\u0002\u0000\t-#q\nB*\u0005/\u0012I\u0006\u0003\u0004\u0003N9\u0002\raT\u0001\u0005]\u0016<\b\f\u0003\u0004\u0003R9\u0002\raT\u0001\b]\u0016<xI]1e\u0011\u0019\u0011)F\fa\u0001a\u00061a.Z<WC2DaA!\u000e/\u0001\u0004i\u0006b\u0002B.]\u0001\u0007!QL\u0001\t_2$7\u000b^1uKB!\u0011\u0011\u0011B0\u0013\r\u0011\t'\u0014\u0002\u0006'R\fG/Z\u0001\ti\u0006\\Wm\u0015;faR9qJa\u001a\u0003l\t=\u0004b\u0002B5_\u0001\u0007!QL\u0001\u0006gR\fG/\u001a\u0005\u0007\u0005[z\u0003\u0019A(\u0002\u0007\u0011L'\u000f\u0003\u0004\u0003r=\u0002\r\u0001]\u0001\tgR,\u0007oU5{K\u000612\r[8pg\u0016$Um]2f]R$\u0015N]3di&|g\u000eF\u0003P\u0005o\u0012I\bC\u0004\u0003jA\u0002\rA!\u0018\t\r\tU\u0002\u00071\u0001^\u0003E!W\r^3s[&tWm\u0015;faNK'0\u001a\u000b\ba\n}$\u0011\u0011BB\u0011\u001d\u0011I'\ra\u0001\u0005;BaA!\u000e2\u0001\u0004i\u0006B\u0002BCc\u0001\u0007q*A\u0005eSJ,7\r^5p]\u0006Yb-\u001e8di&|gN\u0012:p[N+\u0017M]2i\t&\u0014Xm\u0019;j_:,bAa#\u0003\u001e\n=FC\u0003BG\u0005?\u0013\u0019Ka*\u0003*R!!q\u0012BI!\rae\f\u001d\u0005\b\u0005'\u0013\u00049\u0001BK\u0003\u0011\u0001(o\u001c3\u0011\u000f\u0005]!q\u0013BNa&!!\u0011TA\r\u0005IIeN\\3s!J|G-^2u\u001b>$W\u000f\\3\u0011\u0007A\u0013i\nB\u0003Se\t\u00071\u000bC\u0004\u00036I\u0002\rA!)\u0011\t1s&1\u0014\u0005\b\u0005K\u0013\u0004\u0019\u0001BN\u0003\u0005A\bb\u0002BCe\u0001\u0007!1\u0014\u0005\b\u0005W\u0013\u0004\u0019\u0001BW\u0003\u001d\u0001(o\u001c6fGR\u0004b!\u00167\u0003\u001c\nmEA\u0002BYe\t\u00071KA\u0001J\u0003e\u0019\u0006/Z2ue\u0006d\u0007K]8kK\u000e$X\rZ$sC\u0012LWM\u001c;\u0011\u00051#4#\u0002\u001b\u0002H\t\u001dAC\u0001B[\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cU!!q\u0018Bd+\t\u0011\tM\u000b\u0003\u0003D\u0006U\u0005CB+m\u0005\u000b\u0014)\rE\u0002Q\u0005\u000f$QA\u0015\u001cC\u0002M\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012T\u0003BAI\u0005\u001b$QAU\u001cC\u0002M\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aT\u0003BAI\u0005'$QA\u0015\u001dC\u0002M\u000b1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\"T\u0003\u0002Bm\u0005;,\"Aa7+\u0007U\f)\nB\u0003Ss\t\u00071+A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u000b\u0005\u0003#\u0013\u0019\u000fB\u0003Su\t\u00071+A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEN\u000b\u0005\u0003#\u0013I\u000fB\u0003Sw\t\u00071+A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeN\u000b\u0005\u00053\u0014y\u000fB\u0003Sy\t\u00071+A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005O\u000b\u0005\u00053\u0014)\u0010B\u0003S{\t\u00071+A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%O\u000b\u0005\u0005w\u0014y0\u0006\u0002\u0003~*\u001aa0!&\u0005\u000bIs$\u0019A*\u00029\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132aU!!1`B\u0003\t\u0015\u0011vH1\u0001T\u0003q!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cE*BA!7\u0004\f\u0011)!\u000b\u0011b\u0001'\u0006aB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\u0012T\u0003\u0002Bm\u0007#!QAU!C\u0002M\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"aa\u0006\u0011\t\u0005M6\u0011D\u0005\u0005\u00077\t)L\u0001\u0004PE*,7\r\u001e"
)
public class SpectralProjectedGradient extends FirstOrderMinimizer implements Projecting {
   private volatile History$ History$module;
   private final Function1 projection;
   private final double alphaMax;
   private final double alphaMin;
   private final int bbMemory;
   private final boolean initFeas;
   private final boolean curvilinear;
   private final int bbType;
   private final int maxSrcht;
   private final MutableVectorField space;

   public static int $lessinit$greater$default$12() {
      return SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$12();
   }

   public static int $lessinit$greater$default$11() {
      return SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$11();
   }

   public static boolean $lessinit$greater$default$10() {
      return SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$10();
   }

   public static boolean $lessinit$greater$default$9() {
      return SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$9();
   }

   public static int $lessinit$greater$default$8() {
      return SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$8();
   }

   public static int $lessinit$greater$default$7() {
      return SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$7();
   }

   public static double $lessinit$greater$default$6() {
      return SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$6();
   }

   public static double $lessinit$greater$default$5() {
      return SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$5();
   }

   public static int $lessinit$greater$default$4() {
      return SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$4();
   }

   public static double $lessinit$greater$default$3() {
      return SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$3();
   }

   public static double $lessinit$greater$default$2() {
      return SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$2();
   }

   public static Function1 $lessinit$greater$default$1() {
      return SpectralProjectedGradient$.MODULE$.$lessinit$greater$default$1();
   }

   public Object projectedVector(final Object x, final Object g, final Module vspace) {
      return Projecting.projectedVector$(this, x, g, vspace);
   }

   public History$ History() {
      if (this.History$module == null) {
         this.History$lzycompute$1();
      }

      return this.History$module;
   }

   public Function1 projection() {
      return this.projection;
   }

   public boolean initFeas() {
      return this.initFeas;
   }

   public boolean curvilinear() {
      return this.curvilinear;
   }

   public int bbType() {
      return this.bbType;
   }

   public int maxSrcht() {
      return this.maxSrcht;
   }

   public History initialHistory(final DiffFunction f, final Object init) {
      return new History(0.1, (IndexedSeq).MODULE$.IndexedSeq().empty());
   }

   public double bbAlpha(final Object s, final Object y) {
      double alpha = this.bbType() == 1 ? BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.space.hasOps().apply(s)).dot(s, this.space.dotVV())) / BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.space.hasOps().apply(s)).dot(y, this.space.dotVV())) : BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.space.hasOps().apply(s)).dot(y, this.space.dotVV())) / BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.space.hasOps().apply(y)).dot(y, this.space.dotVV()));
      if (alpha <= this.alphaMin || alpha > this.alphaMax) {
         alpha = (double)1.0F;
      }

      if (Double.isNaN(alpha)) {
         alpha = (double)1.0F;
      }

      return alpha;
   }

   public History updateHistory(final Object newX, final Object newGrad, final double newVal, final DiffFunction f, final FirstOrderMinimizer.State oldState) {
      Object s = ((ImmutableNumericOps)this.space.hasOps().apply(newX)).$minus(oldState.x(), this.space.subVV());
      Object y = ((ImmutableNumericOps)this.space.hasOps().apply(newGrad)).$minus(oldState.grad(), this.space.subVV());
      return new History(this.bbAlpha(s, y), (IndexedSeq)((IndexedSeqOps)((History)oldState.history()).fvals().$plus$colon(BoxesRunTime.boxToDouble(newVal))).take(this.bbMemory));
   }

   public Object takeStep(final FirstOrderMinimizer.State state, final Object dir, final double stepSize) {
      Object qq = this.projection().apply(((NumericOps)this.space.hasOps().apply(state.x())).$plus(((ImmutableNumericOps)this.space.hasOps().apply(dir)).$times(BoxesRunTime.boxToDouble(stepSize), this.space.mulVS_M()), this.space.addVV()));
      scala.Predef..MODULE$.assert(BoxesRunTime.equals(this.projection().apply(qq), qq));
      return qq;
   }

   public Object chooseDescentDirection(final FirstOrderMinimizer.State state, final DiffFunction f) {
      return this.curvilinear() ? ((ImmutableNumericOps)this.space.hasOps().apply(state.x())).$minus(((ImmutableNumericOps)this.space.hasOps().apply(state.grad())).$times(BoxesRunTime.boxToDouble(((History)state.history()).alphaBB()), this.space.mulVS_M()), this.space.subVV()) : ((ImmutableNumericOps)this.space.hasOps().apply(this.projection().apply(((ImmutableNumericOps)this.space.hasOps().apply(state.x())).$minus(((ImmutableNumericOps)this.space.hasOps().apply(state.grad())).$times(BoxesRunTime.boxToDouble(((History)state.history()).alphaBB()), this.space.mulVS_M()), this.space.subVV())))).$minus(state.x(), this.space.subVV());
   }

   public double determineStepSize(final FirstOrderMinimizer.State state, final DiffFunction f, final Object direction) {
      double fb = ((History)state.history()).fvals().isEmpty() ? state.value() : scala.runtime.RichDouble..MODULE$.max$extension(scala.Predef..MODULE$.doubleWrapper(state.value()), BoxesRunTime.unboxToDouble(((History)state.history()).fvals().max(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)));
      double normGradInDir = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.space.hasOps().apply(state.grad())).dot(direction, this.space.dotVV()));
      double gamma = state.iter() == 0 ? scala.math.package..MODULE$.min((double)1.0F, (double)1.0F / BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(state.grad(), this.space.normImpl()))) : (double)1.0F;
      DiffFunction searchFun = this.curvilinear() ? this.functionFromSearchDirection(f, state.x(), direction, this.projection(), this.space) : LineSearch$.MODULE$.functionFromSearchDirection(f, state.x(), direction, this.space);
      BacktrackingLineSearch search = new BacktrackingLineSearch(fb, this.maxSrcht(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$3(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$4(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$5(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$6(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$7(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$8(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$9(), BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$10());
      gamma = search.minimize(searchFun, gamma);
      if (gamma < 1.0E-10) {
         throw new LineSearchFailed(normGradInDir, BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(direction, this.space.normImpl())));
      } else {
         return gamma;
      }
   }

   private DiffFunction functionFromSearchDirection(final DiffFunction f, final Object x, final Object direction, final Function1 project, final InnerProductModule prod) {
      return new DiffFunction(f, project, prod, x, direction) {
         private final DiffFunction f$1;
         private final Function1 project$1;
         private final InnerProductModule prod$1;
         private final Object x$2;
         private final Object direction$1;

         public DiffFunction repr() {
            return DiffFunction.repr$(this);
         }

         public DiffFunction cached(final CanCopy copy) {
            return DiffFunction.cached$(this, copy);
         }

         public DiffFunction throughLens(final Isomorphism l) {
            return DiffFunction.throughLens$(this, l);
         }

         public final double apply(final Object x) {
            return StochasticDiffFunction.apply$(this, x);
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

         public boolean apply$mcZD$sp(final double v1) {
            return Function1.apply$mcZD$sp$(this, v1);
         }

         public double apply$mcDD$sp(final double v1) {
            return Function1.apply$mcDD$sp$(this, v1);
         }

         public float apply$mcFD$sp(final double v1) {
            return Function1.apply$mcFD$sp$(this, v1);
         }

         public int apply$mcID$sp(final double v1) {
            return Function1.apply$mcID$sp$(this, v1);
         }

         public long apply$mcJD$sp(final double v1) {
            return Function1.apply$mcJD$sp$(this, v1);
         }

         public void apply$mcVD$sp(final double v1) {
            Function1.apply$mcVD$sp$(this, v1);
         }

         public boolean apply$mcZF$sp(final float v1) {
            return Function1.apply$mcZF$sp$(this, v1);
         }

         public double apply$mcDF$sp(final float v1) {
            return Function1.apply$mcDF$sp$(this, v1);
         }

         public float apply$mcFF$sp(final float v1) {
            return Function1.apply$mcFF$sp$(this, v1);
         }

         public int apply$mcIF$sp(final float v1) {
            return Function1.apply$mcIF$sp$(this, v1);
         }

         public long apply$mcJF$sp(final float v1) {
            return Function1.apply$mcJF$sp$(this, v1);
         }

         public void apply$mcVF$sp(final float v1) {
            Function1.apply$mcVF$sp$(this, v1);
         }

         public boolean apply$mcZI$sp(final int v1) {
            return Function1.apply$mcZI$sp$(this, v1);
         }

         public double apply$mcDI$sp(final int v1) {
            return Function1.apply$mcDI$sp$(this, v1);
         }

         public float apply$mcFI$sp(final int v1) {
            return Function1.apply$mcFI$sp$(this, v1);
         }

         public int apply$mcII$sp(final int v1) {
            return Function1.apply$mcII$sp$(this, v1);
         }

         public long apply$mcJI$sp(final int v1) {
            return Function1.apply$mcJI$sp$(this, v1);
         }

         public void apply$mcVI$sp(final int v1) {
            Function1.apply$mcVI$sp$(this, v1);
         }

         public boolean apply$mcZJ$sp(final long v1) {
            return Function1.apply$mcZJ$sp$(this, v1);
         }

         public double apply$mcDJ$sp(final long v1) {
            return Function1.apply$mcDJ$sp$(this, v1);
         }

         public float apply$mcFJ$sp(final long v1) {
            return Function1.apply$mcFJ$sp$(this, v1);
         }

         public int apply$mcIJ$sp(final long v1) {
            return Function1.apply$mcIJ$sp$(this, v1);
         }

         public long apply$mcJJ$sp(final long v1) {
            return Function1.apply$mcJJ$sp$(this, v1);
         }

         public void apply$mcVJ$sp(final long v1) {
            Function1.apply$mcVJ$sp$(this, v1);
         }

         public Function1 compose(final Function1 g) {
            return Function1.compose$(this, g);
         }

         public Function1 andThen(final Function1 g) {
            return Function1.andThen$(this, g);
         }

         public String toString() {
            return Function1.toString$(this);
         }

         public double valueAt(final double alpha) {
            return this.f$1.valueAt(this.project$1.apply(((NumericOps)this.prod$1.hasOps().apply(this.x$2)).$plus(((ImmutableNumericOps)this.prod$1.hasOps().apply(this.direction$1)).$times(BoxesRunTime.boxToDouble(alpha), this.prod$1.mulVS_M()), this.prod$1.addVV())));
         }

         public double gradientAt(final double alpha) {
            return BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.prod$1.hasOps().apply(this.f$1.gradientAt(this.project$1.apply(((NumericOps)this.prod$1.hasOps().apply(this.x$2)).$plus(((ImmutableNumericOps)this.prod$1.hasOps().apply(this.direction$1)).$times(BoxesRunTime.boxToDouble(alpha), this.prod$1.mulVS_M()), this.prod$1.addVV()))))).dot(this.direction$1, this.prod$1.dotVV()));
         }

         public Tuple2 calculate(final double alpha) {
            Tuple2 var5 = this.f$1.calculate(((NumericOps)this.prod$1.hasOps().apply(this.x$2)).$plus(((ImmutableNumericOps)this.prod$1.hasOps().apply(this.direction$1)).$times(BoxesRunTime.boxToDouble(alpha), this.prod$1.mulVS_M()), this.prod$1.addVV()));
            if (var5 != null) {
               double ff = var5._1$mcD$sp();
               Object grad = var5._2();
               Tuple2 var3 = new Tuple2(BoxesRunTime.boxToDouble(ff), grad);
               double ffx = var3._1$mcD$sp();
               Object grad = var3._2();
               return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble(ffx)), ((ImmutableNumericOps)this.prod$1.hasOps().apply(grad)).dot(this.direction$1, this.prod$1.dotVV()));
            } else {
               throw new MatchError(var5);
            }
         }

         public {
            this.f$1 = f$1;
            this.project$1 = project$1;
            this.prod$1 = prod$1;
            this.x$2 = x$2;
            this.direction$1 = direction$1;
            Function1.$init$(this);
            ImmutableNumericOps.$init$(this);
            NumericOps.$init$(this);
            StochasticDiffFunction.$init$(this);
            DiffFunction.$init$(this);
         }
      };
   }

   private final void History$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.History$module == null) {
            this.History$module = new History$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public SpectralProjectedGradient(final Function1 projection, final double tolerance, final double suffDec, final int fvalMemory, final double alphaMax, final double alphaMin, final int bbMemory, final int maxIter, final boolean initFeas, final boolean curvilinear, final int bbType, final int maxSrcht, final MutableVectorField space) {
      this.projection = projection;
      this.alphaMax = alphaMax;
      this.alphaMin = alphaMin;
      this.bbMemory = bbMemory;
      this.initFeas = initFeas;
      this.curvilinear = curvilinear;
      this.bbType = bbType;
      this.maxSrcht = maxSrcht;
      this.space = space;
      boolean x$4 = FirstOrderMinimizer$.MODULE$.$lessinit$greater$default$4();
      super(maxIter, tolerance, fvalMemory, x$4, space);
      Projecting.$init$(this);
   }

   public class History implements Product, Serializable {
      private final double alphaBB;
      private final IndexedSeq fvals;
      // $FF: synthetic field
      public final SpectralProjectedGradient $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double alphaBB() {
         return this.alphaBB;
      }

      public IndexedSeq fvals() {
         return this.fvals;
      }

      public History copy(final double alphaBB, final IndexedSeq fvals) {
         return this.breeze$optimize$SpectralProjectedGradient$History$$$outer().new History(alphaBB, fvals);
      }

      public double copy$default$1() {
         return this.alphaBB();
      }

      public IndexedSeq copy$default$2() {
         return this.fvals();
      }

      public String productPrefix() {
         return "History";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.alphaBB());
               break;
            case 1:
               var10000 = this.fvals();
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
         return x$1 instanceof History;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "alphaBB";
               break;
            case 1:
               var10000 = "fvals";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.alphaBB()));
         var1 = Statics.mix(var1, Statics.anyHash(this.fvals()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label60: {
               boolean var2;
               if (x$1 instanceof History && ((History)x$1).breeze$optimize$SpectralProjectedGradient$History$$$outer() == this.breeze$optimize$SpectralProjectedGradient$History$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label37: {
                     History var4 = (History)x$1;
                     if (this.alphaBB() == var4.alphaBB()) {
                        label35: {
                           IndexedSeq var10000 = this.fvals();
                           IndexedSeq var5 = var4.fvals();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label35;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label35;
                           }

                           if (var4.canEqual(this)) {
                              var7 = true;
                              break label37;
                           }
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label60;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      // $FF: synthetic method
      public SpectralProjectedGradient breeze$optimize$SpectralProjectedGradient$History$$$outer() {
         return this.$outer;
      }

      public History(final double alphaBB, final IndexedSeq fvals) {
         this.alphaBB = alphaBB;
         this.fvals = fvals;
         if (SpectralProjectedGradient.this == null) {
            throw null;
         } else {
            this.$outer = SpectralProjectedGradient.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class History$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final SpectralProjectedGradient $outer;

      public final String toString() {
         return "History";
      }

      public History apply(final double alphaBB, final IndexedSeq fvals) {
         return this.$outer.new History(alphaBB, fvals);
      }

      public Option unapply(final History x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToDouble(x$0.alphaBB()), x$0.fvals())));
      }

      public History$() {
         if (SpectralProjectedGradient.this == null) {
            throw null;
         } else {
            this.$outer = SpectralProjectedGradient.this;
            super();
         }
      }
   }
}
