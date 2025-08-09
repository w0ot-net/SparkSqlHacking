package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.HashMap;
import scala.collection.immutable.HashMap$;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015mha\u00022d!\u0003\r\t\u0001\u001b\u0005\b\u0003\u001b\u0001A\u0011AA\b\u0011\u001d\t9\u0002\u0001D\u0001\u00033Aq!!\u000e\u0001\t\u000b\t9\u0004C\u0004\u0002R\u0001!\t%a\u0015\t\u000f\u0005m\u0003A\"\u0005\u0002^!9\u0011q\f\u0001\u0005\u0006\u0005u\u0003bBA4\u0001\u0019E\u0011\u0011\u000e\u0005\b\u0003\u0007\u0003a\u0011AAC\u0011\u001d\ti\t\u0001C\u0001\u0003\u000bCq!!*\u0001\r#\t9\u000bC\u0004\u00026\u0002!\t!!\u0018\t\u000f\u0005]\u0006\u0001\"\u0001\u0002:\"9\u00111\u0018\u0001\u0005\u0002\u0005u\u0006bBAc\u0001\u0011\u0005\u0011\u0011\u0018\u0005\b\u0003\u000f\u0004A\u0011AA_\u0011\u001d\tI\r\u0001C\u0001\u0003\u0017Dq!a5\u0001\t\u0003\t)\u000eC\u0004\u0002b\u0002!)!a9\t\u000f\u0005M\u0007\u0001\"\u0001\u0004n!9\u0011\u0011\u001a\u0001\u0005\u0002\ru\u0004bBBG\u0001\u0011\u00051q\u0012\u0005\b\u0007K\u0003A\u0011ABT\u0011\u001d\u0019y\u000b\u0001C\u0001\u0007cCqaa\u0016\u0001\t\u0003\u0019)\fC\u0004\u0004<\u0002!\ta!0\t\u000f\r\u001d\u0007\u0001\"\u0011\u0004J\"91q\u001a\u0001\u0005\u0002\rE\u0007bBBk\u0001\u0011\u00051q\u001b\u0005\b\u00077\u0004A\u0011ABo\u0011\u001d\u0019\t\u000f\u0001C\u0001\u0007GDqaa:\u0001\t\u0003\u0019I\u000fC\u0004\u0004n\u0002!\taa<\t\u000f\rM\b\u0001\"\u0001\u0004v\"91\u0011 \u0001\u0005\u0002\rm\bb\u0002C\u0003\u0001\u0011\u0005Aq\u0001\u0005\b\t\u000b\u0001A\u0011\u0001C\u0006\u0011\u001d!\u0019\u0002\u0001C\u0001\u0003;Bq\u0001\"\u0006\u0001\t\u0003\ti\u0006C\u0004\u0005\u0018\u0001!\t\u0001\"\u0007\t\u000f\u0011}\u0001\u0001\"\u0001\u0005\"!9A1\b\u0001\u0005\u0002\u0011u\u0002b\u0002C-\u0001\u0011\u0005A1\f\u0005\b\t\u007f\u0002A\u0011\u0001CA\u0011\u001d!I\n\u0001C\u0001\t7Cq\u0001\",\u0001\t\u0003!y\u000bC\u0004\u0004\"\u0001!\t\u0001\"1\t\u000f\rU\u0002\u0001\"\u0001\u0005P\"9Aq\u001c\u0001\u0005\u0002\u0011\u0005\bb\u0002Cy\u0001\u0011\u0005A1\u001f\u0005\b\u000b\u000f\u0001A\u0011AC\u0005\u0011\u001d)I\u0003\u0001C\u0001\u000bWAq!b\u000f\u0001\t\u000b)i\u0004C\u0004\u0006N\u0001!\t!b\u0014\t\u000f\u0015}\u0003\u0001\"\u0001\u0006b!9Qq\r\u0001\u0005\u0002\u0015%\u0004bBCC\u0001\u0011\u0005Qq\u0011\u0005\b\u000bC\u0003A\u0011ACR\u0011\u001d)I\r\u0001C\u0001\u000b\u0017Dq!\"4\u0001\t\u0003)Y\rC\u0004\u0006P\u0002!\t%\"5\t\u0011\u0015u\u0007\u0001)C\u0005\u000b?Dq!\":\u0001\t\u0003)9oB\u0004\u0002j\u000eD\t!a;\u0007\r\t\u001c\u0007\u0012AAw\u0011\u001d\t)\u0010\u0011C\u0001\u0003o4a!!?A\u0005\u0005m\bB\u0003B\u0002\u0005\n\u0015\r\u0011\"\u0001\u0003\u0006!Q!\u0011\u0003\"\u0003\u0002\u0003\u0006IAa\u0002\t\u0011\u0005U(\t\"\u0001d\u0005?AqAa\rC\t\u0003\u0011)\u0004C\u0004\u0003>\t#\tAa\u0010\t\u000f\t\u0015#\t\"\u0001\u0003H!9!Q\n\"\u0005\u0002\t=\u0003b\u0002B+\u0005\u0012\u0005!q\u000b\u0005\b\u0005;\u0012E\u0011\u0001B0\u0011%\u0011)GQA\u0001\n\u0003\u00129\u0007C\u0005\u0003j\t\u000b\t\u0011\"\u0011\u0003l\u001dI!\u0011\u000f!\u0002\u0002#\u0005!1\u000f\u0004\n\u0003s\u0004\u0015\u0011!E\u0001\u0005kBq!!>P\t\u0003\u00119\bC\u0004\u0003z=#)Aa\u001f\t\u000f\t\u001du\n\"\u0002\u0003\n\"9!1S(\u0005\u0006\tU\u0005b\u0002BP\u001f\u0012\u0015!\u0011\u0015\u0005\b\u0005W{EQ\u0001BW\u0011\u001d\u00119l\u0014C\u0003\u0005sC\u0011Ba1P\u0003\u0003%)A!2\t\u0013\t%w*!A\u0005\u0006\t-gA\u0002Bj\u0001\u0002\u0011)\u000e\u0003\u0006\u0003xf\u0013\t\u0011)A\u0005\u0005sD!ba\u0001Z\u0005\u0003\u0005\u000b\u0011BB\u0003\u0011\u001d\t)0\u0017C\u0001\u0007\u0017Aqaa\u0007Z\t#\u0019i\u0002C\u0004\u0004\"e#\taa\t\t\u000f\rU\u0012\f\"\u0001\u00048!91qI-\u0005\u0002\r%\u0003bBB,3\u0012\u00051\u0011\f\u0002\f\u0013R,'/\u00192mK>\u00038O\u0003\u0002eK\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u0019\fQa]2bY\u0006\u001c\u0001!F\u0003jiz\fIa\u0005\u0003\u0001U:T\bCA6m\u001b\u0005)\u0017BA7f\u0005\r\te.\u001f\t\u0004_B\u0014X\"A2\n\u0005E\u001c'\u0001D%uKJ\f'\r\\3P]\u000e,\u0007CA:u\u0019\u0001!a!\u001e\u0001\u0005\u0006\u00041(!A!\u0012\u0005]T\u0007CA6y\u0013\tIXMA\u0004O_RD\u0017N\\4\u0011\r=\\(/`A\u0004\u0013\ta8MA\bJi\u0016\u0014\u0018M\u00197f\u001f:\u001cWm\u00149t!\t\u0019h\u0010B\u0004\u0000\u0001\u0011\u0015\r!!\u0001\u0003\u0005\r\u001bUc\u0001<\u0002\u0004\u00111\u0011Q\u0001@C\u0002Y\u0014Aa\u0018\u0013%cA\u00191/!\u0003\u0005\u000f\u0005-\u0001\u0001\"b\u0001m\n\t1)\u0001\u0004%S:LG\u000f\n\u000b\u0003\u0003#\u00012a[A\n\u0013\r\t)\"\u001a\u0002\u0005+:LG/\u0001\u0006u_&#XM]1cY\u0016,\"!a\u0007\u0011\t=\fiB]\u0005\u0004\u0003?\u0019'\u0001C%uKJ\f'\r\\3)\u0017\t\t\u0019#!\u000b\u0002,\u0005=\u0012\u0011\u0007\t\u0004W\u0006\u0015\u0012bAA\u0014K\nQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\u0012\u0011QF\u0001\u0002\u001aQ|\u0017\n^3sC\ndW\rI5tA%tG/\u001a:oC2\u0004\u0013M\u001c3!o&dG\u000e\t2fA5\fG-\u001a\u0011qe>$Xm\u0019;fIn\u0002\u0013\u000e^:!]\u0006lW\rI5tAMLW.\u001b7be\u0002\"x\u000e\t1u_2K7\u000f\u001e1!_J\u0004\u0003\r^8TKF\u0004G\u0006\t2vi\u0002JG\u000f\t3pKNtw\u0005\u001e\u0011d_BL\bE\\8o[%lW.\u001e;bE2,\u0007eY8mY\u0016\u001cG/[8og\u0006)1/\u001b8dK\u0006\u0012\u00111G\u0001\u0007e9\n4GL\u001c\u0002\u001bQ|GK]1wKJ\u001c\u0018M\u00197f+\t\tI\u0004E\u0003\u0002<\u0005\u0005#OD\u0002p\u0003{I1!a\u0010d\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u0011\u0002F\tYAK]1wKJ\u001c\u0018M\u00197f\u0015\r\tyd\u0019\u0015\f\u0007\u0005\r\u0012\u0011FA%\u0003_\ti%\t\u0002\u0002L\u0005\ty\u0002^8Ue\u00064XM]:bE2,\u0007%[:!S:$XM\u001d8bY\u0002\ng\u000e\u001a\u0011xS2d\u0007EY3![\u0006$W\r\t9s_R,7\r^3ew\u0001JGo\u001d\u0011oC6,\u0007%[:!g&l\u0017\u000e\\1sAQ|\u0007\u0005\u0019;p\u0019&\u001cH\u000f\u0019\u0011pe\u0002\u0002Go\\*fc\u0002d\u0003EY;uA%$\b\u0005Z8fg:<C\u000fI2paf\u0004cn\u001c8.S6lW\u000f^1cY\u0016\u00043m\u001c7mK\u000e$\u0018n\u001c8tC\t\ty%\u0001\u00043]E\u001ad\u0006M\u0001\u0013SN$&/\u0019<feN\f'\r\\3BO\u0006Lg.\u0006\u0002\u0002VA\u00191.a\u0016\n\u0007\u0005eSMA\u0004C_>dW-\u00198\u0002\t\r|G\u000e\\\u000b\u0003\u0003\u000f\tAA]3qe\"Za!a\t\u0002*\u0005\r\u0014qFA'C\t\t)'A5Vg\u0016\u00043m\u001c7mA%t7\u000f^3bI\u0002zg\r\t:faJ\u0004\u0013N\u001c\u0011bA\r|G\u000e\\3di&|g\u000eI5na2,W.\u001a8uCRLwN\u001c\u0017!kN,\u0007\u0005\u001e5fA\r|G\u000e\\3di&|g\u000e\t<bYV,\u0007%\u001b;tK24\u0007E\u001a:p[\u0002\"\b.\u001a\u0011pkR\u001c\u0018\u000eZ3\u0002\u0019\u0019\u0014x.\\*qK\u000eLg-[2\u0015\t\u0005\u001d\u00111\u000e\u0005\b\u00037:\u0001\u0019AA7!\u0011y\u0007/a\u001c+\u0007I\f\th\u000b\u0002\u0002tA!\u0011QOA@\u001b\t\t9H\u0003\u0003\u0002z\u0005m\u0014!C;oG\",7m[3e\u0015\r\ti(Z\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAA\u0003o\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003=IG/\u001a:bE2,g)Y2u_JLXCAAD!\u0011y\u0017\u0011R?\n\u0007\u0005-5MA\bJi\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z\u0003%\u0019w.\u001c9b]&|g\u000eK\u0006\n\u0003G\tI#!%\u00020\u00055\u0013EAAJ\u0003m)6/\u001a\u0011ji\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:zA%t7\u000f^3bI\"Z\u0011\"a&\u0002*\u0005E\u0015qFA'!\rY\u0017\u0011T\u0005\u0004\u00037+'\u0001\u00063faJ,7-\u0019;fI>3XM\u001d:jI&tw\rK\u0002\n\u0003?\u00032a[AQ\u0013\r\t\u0019+\u001a\u0002\u0007S:d\u0017N\\3\u0002%9,wo\u00159fG&4\u0017n\u0019\"vS2$WM]\u000b\u0003\u0003S\u0003\u0002\"a+\u00022\u0006=\u0014qA\u0007\u0003\u0003[S1!a,d\u0003\u001diW\u000f^1cY\u0016LA!a-\u0002.\n9!)^5mI\u0016\u0014\u0018!B3naRL\u0018\u0001\u00025fC\u0012,\u0012A]\u0001\u000bQ\u0016\fGm\u00149uS>tWCAA`!\u0011Y\u0017\u0011\u0019:\n\u0007\u0005\rWM\u0001\u0004PaRLwN\\\u0001\u0005Y\u0006\u001cH/\u0001\u0006mCN$x\n\u001d;j_:\fAA^5foV\u0011\u0011Q\u001a\t\u0005_\u0006='/C\u0002\u0002R\u000e\u0014AAV5fo\u0006Y1/\u001b>f\u0007>l\u0007/\u0019:f)\u0011\t9.!8\u0011\u0007-\fI.C\u0002\u0002\\\u0016\u00141!\u00138u\u0011\u001d\ty.\u0005a\u0001\u0003/\f\u0011b\u001c;iKJ\u001c\u0016N_3\u0002\rML'0Z%t+\t\t)\u000fE\u0002\u0002h\ns!a\\ \u0002\u0017%#XM]1cY\u0016|\u0005o\u001d\t\u0003_\u0002\u001b2\u0001QAx!\rY\u0017\u0011_\u0005\u0004\u0003g,'AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003W\u0014abU5{K\u000e{W\u000e]1sK>\u00038oE\u0002C\u0003{\u00042a[A\u0000\u0013\r\u0011\t!\u001a\u0002\u0007\u0003:Lh+\u00197\u0002\u0005%$XC\u0001B\u0004a\u0019\u0011IA!\u0004\u0003\u001cAAq\u000e\u0001B\u0006\u0005'\u0011I\u0002E\u0002t\u0005\u001b!!Ba\u0004E\u0003\u0003\u0005\tQ!\u0001w\u0005\ryFEM\u0001\u0004SR\u0004\u0003\u0003BA\u001e\u0005+IAAa\u0006\u0002F\tI\u0011I\\=D_:\u001cHO\u001d\t\u0004g\nmAA\u0003B\u000f\t\u0006\u0005\t\u0011!B\u0001m\n\u0019q\fJ\u001a\u0015\t\t\u0005\"Q\u0005\t\u0004\u0005G\u0011U\"\u0001!\t\u000f\t\rQ\t1\u0001\u0003(A2!\u0011\u0006B\u0017\u0005c\u0001\u0002b\u001c\u0001\u0003,\tM!q\u0006\t\u0004g\n5Ba\u0003B\b\u0005K\t\t\u0011!A\u0003\u0002Y\u00042a\u001dB\u0019\t-\u0011iB!\n\u0002\u0002\u0003\u0005)\u0011\u0001<\u0002\u000b\u0011bWm]:\u0015\t\u0005U#q\u0007\u0005\b\u0005s1\u0005\u0019AAl\u0003\u0011\u0019\u0018N_3)\u0007\u0019\u000by*\u0001\u0005%Y\u0016\u001c8\u000fJ3r)\u0011\t)F!\u0011\t\u000f\ter\t1\u0001\u0002X\"\u001aq)a(\u0002\r\u0011*\u0017\u000fJ3r)\u0011\t)F!\u0013\t\u000f\te\u0002\n1\u0001\u0002X\"\u001a\u0001*a(\u0002\u0011\u0011\u0012\u0017M\\4%KF$B!!\u0016\u0003R!9!\u0011H%A\u0002\u0005]\u0007fA%\u0002 \u0006YAe\u001a:fCR,'\u000fJ3r)\u0011\t)F!\u0017\t\u000f\te\"\n1\u0001\u0002X\"\u001a!*a(\u0002\u0011\u0011:'/Z1uKJ$B!!\u0016\u0003b!9!\u0011H&A\u0002\u0005]\u0007fA&\u0002 \u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002X\u00061Q-];bYN$B!!\u0016\u0003n!A!qN'\u0002\u0002\u0003\u0007!.A\u0002yIE\nabU5{K\u000e{W\u000e]1sK>\u00038\u000fE\u0002\u0003$=\u001b2aTAx)\t\u0011\u0019(A\b%Y\u0016\u001c8\u000fJ3yi\u0016t7/[8o)\u0011\u0011iH!!\u0015\t\u0005U#q\u0010\u0005\b\u0005s\t\u0006\u0019AAl\u0011\u001d\u0011\u0019)\u0015a\u0001\u0005C\tQ\u0001\n;iSND3!UAP\u0003I!C.Z:tI\u0015\fH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\t-%q\u0012\u000b\u0005\u0003+\u0012i\tC\u0004\u0003:I\u0003\r!a6\t\u000f\t\r%\u000b1\u0001\u0003\"!\u001a!+a(\u0002!\u0011*\u0017\u000fJ3rI\u0015DH/\u001a8tS>tG\u0003\u0002BL\u00057#B!!\u0016\u0003\u001a\"9!\u0011H*A\u0002\u0005]\u0007b\u0002BB'\u0002\u0007!\u0011\u0005\u0015\u0004'\u0006}\u0015A\u0005\u0013cC:<G%Z9%Kb$XM\\:j_:$BAa)\u0003(R!\u0011Q\u000bBS\u0011\u001d\u0011I\u0004\u0016a\u0001\u0003/DqAa!U\u0001\u0004\u0011\t\u0003K\u0002U\u0003?\u000bQ\u0003J4sK\u0006$XM\u001d\u0013fc\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u00030\nMF\u0003BA+\u0005cCqA!\u000fV\u0001\u0004\t9\u000eC\u0004\u0003\u0004V\u0003\rA!\t)\u0007U\u000by*\u0001\n%OJ,\u0017\r^3sI\u0015DH/\u001a8tS>tG\u0003\u0002B^\u0005\u007f#B!!\u0016\u0003>\"9!\u0011\b,A\u0002\u0005]\u0007b\u0002BB-\u0002\u0007!\u0011\u0005\u0015\u0004-\u0006}\u0015A\u00055bg\"\u001cu\u000eZ3%Kb$XM\\:j_:$BAa\u001a\u0003H\"9!1Q,A\u0002\t\u0005\u0012\u0001E3rk\u0006d7\u000fJ3yi\u0016t7/[8o)\u0011\u0011iM!5\u0015\t\u0005U#q\u001a\u0005\t\u0005_B\u0016\u0011!a\u0001U\"9!1\u0011-A\u0002\t\u0005\"AC,ji\"4\u0015\u000e\u001c;feV1!q\u001bBp\u0005G\u001cR!\u0017Bm\u0005W\u0004ra\u001cBn\u0005;\u0014\t/C\u0002\u0003T\u000e\u00042a\u001dBp\t\u0019)\u0018\f\"b\u0001mB\u00191Oa9\u0005\u000f}LFQ1\u0001\u0003fV\u0019aOa:\u0005\u000f\t%(1\u001db\u0001m\n!q\f\n\u00133!\u0011\u0011iO!=\u000f\u0007-\u0014y/C\u0002\u0002@\u0015LAAa=\u0003v\na1+\u001a:jC2L'0\u00192mK*\u0019\u0011qH3\u0002\tM,GN\u001a\u0019\u0005\u0005w\u0014y\u0010\u0005\u0005p\u0001\tu'\u0011\u001dB\u007f!\r\u0019(q \u0003\u000b\u0007\u0003Q\u0016\u0011!A\u0001\u0006\u00031(aA0%i\u0005\t\u0001\u000fE\u0004l\u0007\u000f\u0011i.!\u0016\n\u0007\r%QMA\u0005Gk:\u001cG/[8ocQ11QBB\b\u00073\u0001rAa\tZ\u0005;\u0014\t\u000fC\u0004\u0003xr\u0003\ra!\u00051\t\rM1q\u0003\t\t_\u0002\u0011iN!9\u0004\u0016A\u00191oa\u0006\u0005\u0017\r\u00051qBA\u0001\u0002\u0003\u0015\tA\u001e\u0005\b\u0007\u0007a\u0006\u0019AB\u0003\u0003!1\u0017\u000e\u001c;fe\u0016$WCAB\u0010!\u0015y\u0017Q\u0004Bo\u0003\ri\u0017\r]\u000b\u0005\u0007K\u0019Y\u0003\u0006\u0003\u0004(\r=\u0002#B:\u0003d\u000e%\u0002cA:\u0004,\u001111Q\u00060C\u0002Y\u0014\u0011A\u0011\u0005\b\u0007cq\u0006\u0019AB\u001a\u0003\u00051\u0007cB6\u0004\b\tu7\u0011F\u0001\bM2\fG/T1q+\u0011\u0019Ida\u0010\u0015\t\rm2\u0011\t\t\u0006g\n\r8Q\b\t\u0004g\u000e}BABB\u0017?\n\u0007a\u000fC\u0004\u00042}\u0003\raa\u0011\u0011\u000f-\u001c9A!8\u0004FA!q\u000e]B\u001f\u0003\u001d1wN]3bG\",Baa\u0013\u0004TQ!\u0011\u0011CB'\u0011\u001d\u0019\t\u0004\u0019a\u0001\u0007\u001f\u0002ra[B\u0004\u0005;\u001c\t\u0006E\u0002t\u0007'\"aa!\u0016a\u0005\u00041(!A+\u0002\u0015]LG\u000f\u001b$jYR,'\u000f\u0006\u0003\u0004\u000e\rm\u0003bBB/C\u0002\u00071QA\u0001\u0002c\":\u0011l!\u0019\u0004h\r%\u0004cA6\u0004d%\u00191QM3\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$A\u0002)\u0007I\ty\n\u0006\u0003\u0002X\u000e=\u0004bBB9'\u0001\u000711O\u0001\u0005i\"\fG\u000f\r\u0003\u0004v\re\u0004#B8\u0002\u001e\r]\u0004cA:\u0004z\u0011Y11PB8\u0003\u0003\u0005\tQ!\u0001w\u0005\ryF%\r\u000b\u0007\u0003\u001b\u001cyha!\t\u000f\r\u0005E\u00031\u0001\u0002X\u0006!aM]8n\u0011\u001d\u0019)\t\u0006a\u0001\u0003/\fQ!\u001e8uS2D3\u0002FA\u0012\u0003S\u0019I)a\f\u0002N\u0005\u001211R\u0001;+N,\u0007E\f<jK^t3\u000f\\5dK\"2'o\\7-AUtG/\u001b7*A%t7\u000f^3bI\u0002zg\r\t\u0018wS\u0016<\bF\u001a:p[2\u0002SO\u001c;jY&\n\u0011\u0002\u001e:b]N\u0004xn]3\u0016\t\rE51\u0014\u000b\u0005\u0007'\u001bi\n\u0005\u0003t}\u000eU%\u0006BBL\u0003c\u0002Ba\u001d@\u0004\u001aB\u00191oa'\u0005\r\r5RC1\u0001w\u0011\u001d\u0019y*\u0006a\u0002\u0007C\u000b!\"Y:Ji\u0016\u0014\u0018M\u00197f!\u0019Y7q\u0001:\u0004$B)q.!\b\u0004\u001a\u00061a-\u001b7uKJ$B!a\u0002\u0004*\"911\u0016\fA\u0002\r5\u0016\u0001\u00029sK\u0012\u0004ba[B\u0004e\u0006U\u0013!\u00034jYR,'OT8u)\u0011\t9aa-\t\u000f\r-v\u00031\u0001\u0004.R!1qWB]!\u0015y'1\u001c:~\u0011\u001d\u0019\u0019\u0001\u0007a\u0001\u0007[\u000b\u0011\u0002]1si&$\u0018n\u001c8\u0015\t\r}6Q\u0019\t\bW\u000e\u0005\u0017qAA\u0004\u0013\r\u0019\u0019-\u001a\u0002\u0007)V\u0004H.\u001a\u001a\t\u000f\r\r\u0011\u00041\u0001\u0004.\u000691\u000f\u001d7ji\u0006#H\u0003BB`\u0007\u0017Dqa!4\u001b\u0001\u0004\t9.A\u0001o\u0003\u0011!\u0018m[3\u0015\t\u0005\u001d11\u001b\u0005\b\u0007\u001b\\\u0002\u0019AAl\u0003%!\u0018m[3SS\u001eDG\u000f\u0006\u0003\u0002\b\re\u0007bBBg9\u0001\u0007\u0011q[\u0001\ni\u0006\\Wm\u00165jY\u0016$B!a\u0002\u0004`\"911A\u000fA\u0002\r5\u0016\u0001B:qC:$Baa0\u0004f\"911\u0001\u0010A\u0002\r5\u0016\u0001\u00023s_B$B!a\u0002\u0004l\"91QZ\u0010A\u0002\u0005]\u0017!\u00033s_B\u0014\u0016n\u001a5u)\u0011\t9a!=\t\u000f\r5\u0007\u00051\u0001\u0002X\u0006IAM]8q/\"LG.\u001a\u000b\u0005\u0003\u000f\u00199\u0010C\u0004\u0004\u0004\u0005\u0002\ra!,\u0002\u000f\u001d\u0014x.\u001e9fIR!1Q C\u0002!\u0015y7q`A\u0004\u0013\r!\ta\u0019\u0002\t\u0013R,'/\u0019;pe\"9!\u0011\b\u0012A\u0002\u0005]\u0017aB:mS\u0012Lgn\u001a\u000b\u0005\u0007{$I\u0001C\u0004\u0003:\r\u0002\r!a6\u0015\r\ruHQ\u0002C\b\u0011\u001d\u0011I\u0004\na\u0001\u0003/Dq\u0001\"\u0005%\u0001\u0004\t9.\u0001\u0003ti\u0016\u0004\u0018\u0001\u0002;bS2\fA!\u001b8ji\u0006)1\u000f\\5dKR1\u0011q\u0001C\u000e\t;Aqa!!(\u0001\u0004\t9\u000eC\u0004\u0004\u0006\u001e\u0002\r!a6\u0002\u000f\u001d\u0014x.\u001e9CsV!A1\u0005C\u001a)\u0011!)\u0003b\u000e\u0011\u0011\u0011\u001dBQ\u0006C\u0019\u0003\u000fi!\u0001\"\u000b\u000b\u0007\u0011-2-A\u0005j[6,H/\u00192mK&!Aq\u0006C\u0015\u0005\ri\u0015\r\u001d\t\u0004g\u0012MBA\u0002C\u001bQ\t\u0007aOA\u0001L\u0011\u001d\u0019\t\u0004\u000ba\u0001\ts\u0001ba[B\u0004e\u0012E\u0012\u0001C4s_V\u0004X*\u00199\u0016\r\u0011}Bq\tC')\u0011!\t\u0005b\u0015\u0015\t\u0011\rCq\n\t\t\tO!i\u0003\"\u0012\u0005JA\u00191\u000fb\u0012\u0005\r\u0011U\u0012F1\u0001w!\u0011\u0019h\u0010b\u0013\u0011\u0007M$i\u0005\u0002\u0004\u0004.%\u0012\rA\u001e\u0005\b\u0007cI\u0003\u0019\u0001C)!\u0019Y7q\u0001:\u0005L!9AQK\u0015A\u0002\u0011]\u0013aA6fsB11na\u0002s\t\u000b\nab\u001a:pkBl\u0015\r\u001d*fIV\u001cW-\u0006\u0004\u0005^\u0011\u001dD1\u000e\u000b\u0005\t?\"Y\b\u0006\u0003\u0005b\u0011]D\u0003\u0002C2\t[\u0002\u0002\u0002b\n\u0005.\u0011\u0015D\u0011\u000e\t\u0004g\u0012\u001dDA\u0002C\u001bU\t\u0007a\u000fE\u0002t\tW\"aa!\f+\u0005\u00041\bb\u0002C8U\u0001\u0007A\u0011O\u0001\u0007e\u0016$WoY3\u0011\u0013-$\u0019\b\"\u001b\u0005j\u0011%\u0014b\u0001C;K\nIa)\u001e8di&|gN\r\u0005\b\u0007cQ\u0003\u0019\u0001C=!\u0019Y7q\u0001:\u0005j!9AQ\u000b\u0016A\u0002\u0011u\u0004CB6\u0004\bI$)'\u0001\u0003tG\u0006tW\u0003\u0002CB\t\u0017#B\u0001\"\"\u0005\u0016R!Aq\u0011CH!\u0011\u0019h\u0010\"#\u0011\u0007M$Y\tB\u0004\u0004.-\u0012\r\u0001\"$\u0012\u0005IT\u0007b\u0002CIW\u0001\u0007A1S\u0001\u0003_B\u0004\u0012b\u001bC:\t\u0013#I\t\"#\t\u000f\u0011]5\u00061\u0001\u0005\n\u0006\t!0\u0001\u0005tG\u0006tG*\u001a4u+\u0011!i\n\"*\u0015\t\u0011}E1\u0016\u000b\u0005\tC#9\u000b\u0005\u0003t}\u0012\r\u0006cA:\u0005&\u001211Q\u0006\u0017C\u0002YDq\u0001\"%-\u0001\u0004!I\u000b\u0005\u0005l\tg\"\u0019K\u001dCR\u0011\u001d!9\n\fa\u0001\tG\u000b\u0011b]2b]JKw\r\u001b;\u0016\t\u0011EF\u0011\u0018\u000b\u0005\tg#y\f\u0006\u0003\u00056\u0012m\u0006\u0003B:\u007f\to\u00032a\u001dC]\t\u0019\u0019i#\fb\u0001m\"9A\u0011S\u0017A\u0002\u0011u\u0006\u0003C6\u0005tI$9\fb.\t\u000f\u0011]U\u00061\u0001\u00058V!A1\u0019Ce)\u0011!)\rb3\u0011\tMtHq\u0019\t\u0004g\u0012%GABB\u0017]\t\u0007a\u000fC\u0004\u000429\u0002\r\u0001\"4\u0011\r-\u001c9A\u001dCd+\u0011!\t\u000eb6\u0015\t\u0011MG\u0011\u001c\t\u0005gz$)\u000eE\u0002t\t/$aa!\f0\u0005\u00041\bbBB\u0019_\u0001\u0007A1\u001c\t\u0007W\u000e\u001d!\u000f\"8\u0011\t=\u0004HQ[\u0001\bM2\fG\u000f^3o+\u0011!\u0019\u000f\";\u0015\t\u0011\u0015H1\u001e\t\u0005gz$9\u000fE\u0002t\tS$aa!\f1\u0005\u00041\bbBBPa\u0001\u000fAQ\u001e\t\u0007W\u000e\u001d!\u000fb<\u0011\t=\u0004Hq]\u0001\bG>dG.Z2u+\u0011!)\u0010b?\u0015\t\u0011]HQ \t\u0005gz$I\u0010E\u0002t\tw$aa!\f2\u0005\u00041\bb\u0002C\u0000c\u0001\u0007Q\u0011A\u0001\u0003a\u001a\u0004ba[C\u0002e\u0012e\u0018bAC\u0003K\ny\u0001+\u0019:uS\u0006dg)\u001e8di&|g.\u0001\u0007qCJ$\u0018\u000e^5p]6\u000b\u0007/\u0006\u0004\u0006\f\u0015MQ1\u0004\u000b\u0005\u000b\u001b)y\u0002E\u0004l\u0007\u0003,y!b\u0006\u0011\tMtX\u0011\u0003\t\u0004g\u0016MAABC\u000be\t\u0007aO\u0001\u0002BcA!1O`C\r!\r\u0019X1\u0004\u0003\u0007\u000b;\u0011$\u0019\u0001<\u0003\u0005\u0005\u0013\u0004bBB\u0019e\u0001\u0007Q\u0011\u0005\t\u0007W\u000e\u001d!/b\t\u0011\u0011\t5XQEC\t\u000b3IA!b\n\u0003v\n1Q)\u001b;iKJ\faaY8oG\u0006$X\u0003BC\u0017\u000bg!B!b\f\u00066A!1O`C\u0019!\r\u0019X1\u0007\u0003\b\u0007[\u0019$\u0019\u0001CG\u0011\u001d)9d\ra\u0001\u000bs\taa];gM&D\b\u0003B8q\u000bc\t!\u0002\n9mkN$\u0003\u000f\\;t+\u0011)y$\"\u0012\u0015\t\u0015\u0005Sq\t\t\u0005gz,\u0019\u0005E\u0002t\u000b\u000b\"qa!\f5\u0005\u0004!i\tC\u0004\u00068Q\u0002\r!\"\u0013\u0011\t=\u0004X1\t\u0015\u0004i\u0005}\u0015a\u0001>jaV!Q\u0011KC-)\u0011)\u0019&b\u0017\u0011\tMtXQ\u000b\t\bW\u000e\u0005\u0017qNC,!\r\u0019X\u0011\f\u0003\u0007\u0007[)$\u0019\u0001<\t\u000f\rET\u00071\u0001\u0006^A!q\u000e]C,\u00031Q\u0018\u000e],ji\"Le\u000eZ3y+\t)\u0019\u0007\u0005\u0003t}\u0016\u0015\u0004cB6\u0004B\u0006=\u0014q[\u0001\u0007u&\u0004\u0018\t\u001c7\u0016\r\u0015-T1OC<)!)i'\"\u001f\u0006~\u0015\u0005\u0005\u0003B:\u007f\u000b_\u0002ra[Ba\u000bc*)\bE\u0002t\u000bg\"q!\"\u00068\u0005\u0004!i\tE\u0002t\u000bo\"aa!\f8\u0005\u00041\bbBB9o\u0001\u0007Q1\u0010\t\u0006_\u0006uQQ\u000f\u0005\b\u000b\u007f:\u0004\u0019AC9\u0003!!\b.[:FY\u0016l\u0007bBCBo\u0001\u0007QQO\u0001\ti\"\fG/\u00127f[\u0006)QO\u001c>jaV1Q\u0011RCI\u000b/#B!b#\u0006\u001aB91n!1\u0006\u000e\u0016M\u0005\u0003B:\u007f\u000b\u001f\u00032a]CI\t\u0019))\u0002\u000fb\u0001mB!1O`CK!\r\u0019Xq\u0013\u0003\u0007\u000b;A$\u0019\u0001<\t\u000f\u0015m\u0005\bq\u0001\u0006\u001e\u00061\u0011m\u001d)bSJ\u0004ba[B\u0004e\u0016}\u0005cB6\u0004B\u0016=UQS\u0001\u0007k:T\u0018\u000e]\u001a\u0016\u0011\u0015\u0015V\u0011WC\\\u000b{#B!b*\u0006BBI1.\"+\u0006.\u0016MV\u0011X\u0005\u0004\u000bW+'A\u0002+va2,7\u0007\u0005\u0003t}\u0016=\u0006cA:\u00062\u00121QQC\u001dC\u0002Y\u0004Ba\u001d@\u00066B\u00191/b.\u0005\r\u0015u\u0011H1\u0001w!\u0011\u0019h0b/\u0011\u0007M,i\f\u0002\u0004\u0006@f\u0012\rA\u001e\u0002\u0003\u0003NBq!b1:\u0001\b))-\u0001\u0005bgR\u0013\u0018\u000e\u001d7f!\u0019Y7q\u0001:\u0006HBI1.\"+\u00060\u0016UV1X\u0001\u0006i\u0006LGn]\u000b\u0003\u0007{\fQ!\u001b8jiN\fq\u0001^1q\u000b\u0006\u001c\u0007.\u0006\u0003\u0006T\u0016mG\u0003BA\u0004\u000b+Dqa!\r=\u0001\u0004)9\u000e\u0005\u0004l\u0007\u000f\u0011X\u0011\u001c\t\u0004g\u0016mGABB+y\t\u0007a/A\tji\u0016\u0014\u0018\r^3V]RLG.R7qif$Ba!@\u0006b\"91\u0011G\u001fA\u0002\u0015\r\bcB6\u0004\b\u0005m\u00111D\u0001\u0011IAdWo\u001d\u0013qYV\u001cHeY8m_:,B!\";\u0006pR!Q1^Cy!\u0011\u0019h0\"<\u0011\u0007M,y\u000fB\u0004\u0004.y\u0012\r\u0001\"$\t\u000f\rEd\b1\u0001\u0006tB!q\u000e]CwQ-q\u00141EA\u0015\u000bo\fy#!\u0014\"\u0005\u0015e\u0018AN+tK\u0002Z3\u0006I5ogR,\u0017\r\u001a\u0011pM\u0002Z3F\u000f\u0011g_J\u00043m\u001c7mK\u000e$\u0018n\u001c8tA=4\u0007\u0005^=qK\u0002JE/\u001a:bE2,\u0007"
)
public interface IterableOps extends IterableOnce, IterableOnceOps {
   /** @deprecated */
   Iterable toIterable();

   // $FF: synthetic method
   static Iterable toTraversable$(final IterableOps $this) {
      return $this.toTraversable();
   }

   /** @deprecated */
   default Iterable toTraversable() {
      return this.toIterable();
   }

   // $FF: synthetic method
   static boolean isTraversableAgain$(final IterableOps $this) {
      return $this.isTraversableAgain();
   }

   default boolean isTraversableAgain() {
      return true;
   }

   Object coll();

   // $FF: synthetic method
   static Object repr$(final IterableOps $this) {
      return $this.repr();
   }

   /** @deprecated */
   default Object repr() {
      return this.coll();
   }

   Object fromSpecific(final IterableOnce coll);

   IterableFactory iterableFactory();

   // $FF: synthetic method
   static IterableFactory companion$(final IterableOps $this) {
      return $this.companion();
   }

   /** @deprecated */
   default IterableFactory companion() {
      return this.iterableFactory();
   }

   Builder newSpecificBuilder();

   // $FF: synthetic method
   static Object empty$(final IterableOps $this) {
      return $this.empty();
   }

   default Object empty() {
      return this.fromSpecific(Nil$.MODULE$);
   }

   // $FF: synthetic method
   static Object head$(final IterableOps $this) {
      return $this.head();
   }

   default Object head() {
      return this.iterator().next();
   }

   // $FF: synthetic method
   static Option headOption$(final IterableOps $this) {
      return $this.headOption();
   }

   default Option headOption() {
      Iterator it = this.iterator();
      return (Option)(it.hasNext() ? new Some(it.next()) : None$.MODULE$);
   }

   // $FF: synthetic method
   static Object last$(final IterableOps $this) {
      return $this.last();
   }

   default Object last() {
      Iterator it = this.iterator();

      Object lst;
      for(lst = it.next(); it.hasNext(); lst = it.next()) {
      }

      return lst;
   }

   // $FF: synthetic method
   static Option lastOption$(final IterableOps $this) {
      return $this.lastOption();
   }

   default Option lastOption() {
      return (Option)(this.isEmpty() ? None$.MODULE$ : new Some(this.last()));
   }

   // $FF: synthetic method
   static View view$(final IterableOps $this) {
      return $this.view();
   }

   default View view() {
      View$ var10000 = View$.MODULE$;
      Function0 fromIteratorProvider_it = () -> this.iterator();
      return new AbstractView(fromIteratorProvider_it) {
         private final Function0 it$1;

         public Iterator iterator() {
            return (Iterator)this.it$1.apply();
         }

         public {
            this.it$1 = it$1;
         }
      };
   }

   // $FF: synthetic method
   static int sizeCompare$(final IterableOps $this, final int otherSize) {
      return $this.sizeCompare(otherSize);
   }

   default int sizeCompare(final int otherSize) {
      if (otherSize < 0) {
         return 1;
      } else {
         int known = this.knownSize();
         if (known >= 0) {
            return Integer.compare(known, otherSize);
         } else {
            int i = 0;

            for(Iterator it = this.iterator(); it.hasNext(); ++i) {
               if (i == otherSize) {
                  return 1;
               }

               it.next();
            }

            return i - otherSize;
         }
      }
   }

   // $FF: synthetic method
   static IterableOps sizeIs$(final IterableOps $this) {
      return $this.sizeIs();
   }

   default IterableOps sizeIs() {
      return this;
   }

   // $FF: synthetic method
   static int sizeCompare$(final IterableOps $this, final Iterable that) {
      return $this.sizeCompare(that);
   }

   default int sizeCompare(final Iterable that) {
      int thatKnownSize = that.knownSize();
      if (thatKnownSize >= 0) {
         return this.sizeCompare(thatKnownSize);
      } else {
         int thisKnownSize = this.knownSize();
         if (thisKnownSize >= 0) {
            int res = that.sizeCompare(thisKnownSize);
            return res == Integer.MIN_VALUE ? 1 : -res;
         } else {
            Iterator thisIt = this.iterator();
            Iterator thatIt = that.iterator();

            while(thisIt.hasNext() && thatIt.hasNext()) {
               thisIt.next();
               thatIt.next();
            }

            return Boolean.compare(thisIt.hasNext(), thatIt.hasNext());
         }
      }
   }

   // $FF: synthetic method
   static View view$(final IterableOps $this, final int from, final int until) {
      return $this.view(from, until);
   }

   /** @deprecated */
   default View view(final int from, final int until) {
      return (View)this.view().slice(from, until);
   }

   // $FF: synthetic method
   static Object transpose$(final IterableOps $this, final Function1 asIterable) {
      return $this.transpose(asIterable);
   }

   default Object transpose(final Function1 asIterable) {
      if (this.isEmpty()) {
         return this.iterableFactory().empty();
      } else {
         int headSize = ((IterableOnceOps)asIterable.apply(this.head())).size();
         scala.collection.immutable.IndexedSeq$ var10000 = scala.collection.immutable.IndexedSeq$.MODULE$;
         Function0 fill_elem = () -> this.iterableFactory().newBuilder();
         SeqFactory.Delegate fill_this = var10000;
         IterableOnce from_source = new View.Fill(headSize, fill_elem);
         scala.collection.immutable.IndexedSeq var10 = ((scala.collection.immutable.IndexedSeq$)fill_this).from(from_source);
         from_source = null;
         fill_this = null;
         fill_elem = null;
         scala.collection.immutable.IndexedSeq bs = var10;
         this.iterator().foreach((xs) -> {
            $anonfun$transpose$2(asIterable, headSize, bs, xs);
            return BoxedUnit.UNIT;
         });
         return this.iterableFactory().from((IterableOnce)bs.map((x$1) -> x$1.result()));
      }
   }

   // $FF: synthetic method
   static Object filter$(final IterableOps $this, final Function1 pred) {
      return $this.filter(pred);
   }

   default Object filter(final Function1 pred) {
      return this.fromSpecific(new View.Filter(this, pred, false));
   }

   // $FF: synthetic method
   static Object filterNot$(final IterableOps $this, final Function1 pred) {
      return $this.filterNot(pred);
   }

   default Object filterNot(final Function1 pred) {
      return this.fromSpecific(new View.Filter(this, pred, true));
   }

   // $FF: synthetic method
   static scala.collection.WithFilter withFilter$(final IterableOps $this, final Function1 p) {
      return $this.withFilter(p);
   }

   default scala.collection.WithFilter withFilter(final Function1 p) {
      return new WithFilter(this, p);
   }

   // $FF: synthetic method
   static Tuple2 partition$(final IterableOps $this, final Function1 p) {
      return $this.partition(p);
   }

   default Tuple2 partition(final Function1 p) {
      View.Filter first = new View.Filter(this, p, false);
      View.Filter second = new View.Filter(this, p, true);
      return new Tuple2(this.fromSpecific(first), this.fromSpecific(second));
   }

   // $FF: synthetic method
   static Tuple2 splitAt$(final IterableOps $this, final int n) {
      return $this.splitAt(n);
   }

   default Tuple2 splitAt(final int n) {
      return new Tuple2(this.take(n), this.drop(n));
   }

   // $FF: synthetic method
   static Object take$(final IterableOps $this, final int n) {
      return $this.take(n);
   }

   default Object take(final int n) {
      return this.fromSpecific(new View.Take(this, n));
   }

   // $FF: synthetic method
   static Object takeRight$(final IterableOps $this, final int n) {
      return $this.takeRight(n);
   }

   default Object takeRight(final int n) {
      return this.fromSpecific(new View.TakeRight(this, n));
   }

   // $FF: synthetic method
   static Object takeWhile$(final IterableOps $this, final Function1 p) {
      return $this.takeWhile(p);
   }

   default Object takeWhile(final Function1 p) {
      return this.fromSpecific(new View.TakeWhile(this, p));
   }

   // $FF: synthetic method
   static Tuple2 span$(final IterableOps $this, final Function1 p) {
      return $this.span(p);
   }

   default Tuple2 span(final Function1 p) {
      return new Tuple2(this.takeWhile(p), this.dropWhile(p));
   }

   // $FF: synthetic method
   static Object drop$(final IterableOps $this, final int n) {
      return $this.drop(n);
   }

   default Object drop(final int n) {
      return this.fromSpecific(new View.Drop(this, n));
   }

   // $FF: synthetic method
   static Object dropRight$(final IterableOps $this, final int n) {
      return $this.dropRight(n);
   }

   default Object dropRight(final int n) {
      return this.fromSpecific(new View.DropRight(this, n));
   }

   // $FF: synthetic method
   static Object dropWhile$(final IterableOps $this, final Function1 p) {
      return $this.dropWhile(p);
   }

   default Object dropWhile(final Function1 p) {
      return this.fromSpecific(new View.DropWhile(this, p));
   }

   // $FF: synthetic method
   static Iterator grouped$(final IterableOps $this, final int size) {
      return $this.grouped(size);
   }

   default Iterator grouped(final int size) {
      return this.iterator().grouped(size).map((coll) -> this.fromSpecific(coll));
   }

   // $FF: synthetic method
   static Iterator sliding$(final IterableOps $this, final int size) {
      return $this.sliding(size);
   }

   default Iterator sliding(final int size) {
      return this.sliding(size, 1);
   }

   // $FF: synthetic method
   static Iterator sliding$(final IterableOps $this, final int size, final int step) {
      return $this.sliding(size, step);
   }

   default Iterator sliding(final int size, final int step) {
      return this.iterator().sliding(size, step).map((coll) -> this.fromSpecific(coll));
   }

   // $FF: synthetic method
   static Object tail$(final IterableOps $this) {
      return $this.tail();
   }

   default Object tail() {
      if (this.isEmpty()) {
         throw new UnsupportedOperationException();
      } else {
         return this.drop(1);
      }
   }

   // $FF: synthetic method
   static Object init$(final IterableOps $this) {
      return $this.init();
   }

   default Object init() {
      if (this.isEmpty()) {
         throw new UnsupportedOperationException();
      } else {
         return this.dropRight(1);
      }
   }

   // $FF: synthetic method
   static Object slice$(final IterableOps $this, final int from, final int until) {
      return $this.slice(from, until);
   }

   default Object slice(final int from, final int until) {
      return this.fromSpecific(new View.Drop(new View.Take(this, until), from));
   }

   // $FF: synthetic method
   static scala.collection.immutable.Map groupBy$(final IterableOps $this, final Function1 f) {
      return $this.groupBy(f);
   }

   default scala.collection.immutable.Map groupBy(final Function1 f) {
      scala.collection.mutable.Map m = (scala.collection.mutable.Map)scala.collection.mutable.Map$.MODULE$.empty();
      Iterator it = this.iterator();

      while(it.hasNext()) {
         Object elem = it.next();
         Object key = f.apply(elem);
         Builder bldr = (Builder)m.getOrElseUpdate(key, () -> this.newSpecificBuilder());
         if (bldr == null) {
            throw null;
         }

         bldr.addOne(elem);
      }

      HashMap result = HashMap$.MODULE$.empty();

      Object k;
      Builder v;
      for(Iterator mapIt = m.iterator(); mapIt.hasNext(); result = result.updated(k, v.result())) {
         Tuple2 var9 = (Tuple2)mapIt.next();
         if (var9 == null) {
            throw new MatchError((Object)null);
         }

         k = var9._1();
         v = (Builder)var9._2();
      }

      return result;
   }

   // $FF: synthetic method
   static scala.collection.immutable.Map groupMap$(final IterableOps $this, final Function1 key, final Function1 f) {
      return $this.groupMap(key, f);
   }

   default scala.collection.immutable.Map groupMap(final Function1 key, final Function1 f) {
      scala.collection.mutable.Map m = (scala.collection.mutable.Map)scala.collection.mutable.Map$.MODULE$.empty();
      this.foreach((elem) -> {
         Object k = key.apply(elem);
         Builder bldr = (Builder)m.getOrElseUpdate(k, () -> this.iterableFactory().newBuilder());
         Object $plus$eq_elem = f.apply(elem);
         if (bldr == null) {
            throw null;
         } else {
            return (Builder)bldr.addOne($plus$eq_elem);
         }
      });

      class Result$1 extends AbstractFunction1 {
         private scala.collection.immutable.Map built;

         public scala.collection.immutable.Map built() {
            return this.built;
         }

         public void built_$eq(final scala.collection.immutable.Map x$1) {
            this.built = x$1;
         }

         public void apply(final Tuple2 kv) {
            this.built_$eq((scala.collection.immutable.Map)this.built().updated(kv._1(), ((Builder)kv._2()).result()));
         }

         public Result$1() {
            scala.collection.immutable.Map$ var10001 = scala.collection.immutable.Map$.MODULE$;
            this.built = scala.collection.immutable.Map.EmptyMap$.MODULE$;
         }
      }

      Result$1 result = new Result$1();
      m.foreach(result);
      return result.built();
   }

   // $FF: synthetic method
   static scala.collection.immutable.Map groupMapReduce$(final IterableOps $this, final Function1 key, final Function1 f, final Function2 reduce) {
      return $this.groupMapReduce(key, f, reduce);
   }

   default scala.collection.immutable.Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      scala.collection.mutable.Map m = (scala.collection.mutable.Map)scala.collection.mutable.Map$.MODULE$.empty();
      this.foreach((elem) -> {
         Object k = key.apply(elem);
         Option var7 = m.get(k);
         Object var10000;
         if (var7 instanceof Some) {
            Object b = ((Some)var7).value();
            var10000 = reduce.apply(b, f.apply(elem));
         } else {
            if (!None$.MODULE$.equals(var7)) {
               throw new MatchError(var7);
            }

            var10000 = f.apply(elem);
         }

         Object v = var10000;
         return m.put(k, v);
      });
      MapFactory$ var10001 = MapFactory$.MODULE$;
      MapFactory toFactory_factory = scala.collection.immutable.Map$.MODULE$;
      MapFactory.ToFactory var7 = new MapFactory.ToFactory(toFactory_factory);
      toFactory_factory = null;
      return (scala.collection.immutable.Map)m.to(var7);
   }

   // $FF: synthetic method
   static Object scan$(final IterableOps $this, final Object z, final Function2 op) {
      return $this.scan(z, op);
   }

   default Object scan(final Object z, final Function2 op) {
      return this.scanLeft(z, op);
   }

   // $FF: synthetic method
   static Object scanLeft$(final IterableOps $this, final Object z, final Function2 op) {
      return $this.scanLeft(z, op);
   }

   default Object scanLeft(final Object z, final Function2 op) {
      return this.iterableFactory().from(new View.ScanLeft(this, z, op));
   }

   // $FF: synthetic method
   static Object scanRight$(final IterableOps $this, final Object z, final Function2 op) {
      return $this.scanRight(z, op);
   }

   default Object scanRight(final Object z, final Function2 op) {
      class Scanner$1 extends AbstractFunction1 {
         private Object acc;
         private List scanned;
         private final Function2 op$1;

         public Object acc() {
            return this.acc;
         }

         public void acc_$eq(final Object x$1) {
            this.acc = x$1;
         }

         public List scanned() {
            return this.scanned;
         }

         public void scanned_$eq(final List x$1) {
            this.scanned = x$1;
         }

         public void apply(final Object x) {
            this.acc_$eq(this.op$1.apply(x, this.acc()));
            List var10001 = this.scanned();
            Object $colon$colon_elem = this.acc();
            if (var10001 == null) {
               throw null;
            } else {
               List $colon$colon_this = var10001;
               $colon$colon var6 = new $colon$colon($colon$colon_elem, $colon$colon_this);
               $colon$colon_this = null;
               $colon$colon_elem = null;
               this.scanned_$eq(var6);
            }
         }

         public Scanner$1(final Object z$1, final Function2 op$1) {
            this.op$1 = op$1;
            this.acc = z$1;
            Object var4 = this.acc();
            List $colon$colon_this = Nil$.MODULE$;
            this.scanned = new $colon$colon(var4, $colon$colon_this);
         }
      }

      Scanner$1 scanner = new Scanner$1(z, op);
      this.reversed().foreach(scanner);
      return this.iterableFactory().from(scanner.scanned());
   }

   // $FF: synthetic method
   static Object map$(final IterableOps $this, final Function1 f) {
      return $this.map(f);
   }

   default Object map(final Function1 f) {
      return this.iterableFactory().from(new View.Map(this, f));
   }

   // $FF: synthetic method
   static Object flatMap$(final IterableOps $this, final Function1 f) {
      return $this.flatMap(f);
   }

   default Object flatMap(final Function1 f) {
      return this.iterableFactory().from(new View.FlatMap(this, f));
   }

   // $FF: synthetic method
   static Object flatten$(final IterableOps $this, final Function1 asIterable) {
      return $this.flatten(asIterable);
   }

   default Object flatten(final Function1 asIterable) {
      return this.flatMap(asIterable);
   }

   // $FF: synthetic method
   static Object collect$(final IterableOps $this, final PartialFunction pf) {
      return $this.collect(pf);
   }

   default Object collect(final PartialFunction pf) {
      return this.iterableFactory().from(new View.Collect(this, pf));
   }

   // $FF: synthetic method
   static Tuple2 partitionMap$(final IterableOps $this, final Function1 f) {
      return $this.partitionMap(f);
   }

   default Tuple2 partitionMap(final Function1 f) {
      View left = new View.LeftPartitionMapped(this, f);
      View right = new View.RightPartitionMapped(this, f);
      return new Tuple2(this.iterableFactory().from(left), this.iterableFactory().from(right));
   }

   // $FF: synthetic method
   static Object concat$(final IterableOps $this, final IterableOnce suffix) {
      return $this.concat(suffix);
   }

   default Object concat(final IterableOnce suffix) {
      IterableFactory var10000 = this.iterableFactory();
      Object var10001;
      if (suffix instanceof Iterable) {
         Iterable var2 = (Iterable)suffix;
         var10001 = new View.Concat(this, var2);
      } else {
         var10001 = this.iterator();
         Function0 $plus$plus_xs = () -> suffix.iterator();
         if (var10001 == null) {
            throw null;
         }

         var10001 = var10001.concat($plus$plus_xs);
         $plus$plus_xs = null;
      }

      return var10000.from(var10001);
   }

   // $FF: synthetic method
   static Object $plus$plus$(final IterableOps $this, final IterableOnce suffix) {
      return $this.$plus$plus(suffix);
   }

   default Object $plus$plus(final IterableOnce suffix) {
      return this.concat(suffix);
   }

   // $FF: synthetic method
   static Object zip$(final IterableOps $this, final IterableOnce that) {
      return $this.zip(that);
   }

   default Object zip(final IterableOnce that) {
      IterableFactory var10000 = this.iterableFactory();
      Object var10001;
      if (that instanceof Iterable) {
         Iterable var2 = (Iterable)that;
         var10001 = new View.Zip(this, var2);
      } else {
         var10001 = this.iterator().zip(that);
      }

      return var10000.from((IterableOnce)var10001);
   }

   // $FF: synthetic method
   static Object zipWithIndex$(final IterableOps $this) {
      return $this.zipWithIndex();
   }

   default Object zipWithIndex() {
      return this.iterableFactory().from(new View.ZipWithIndex(this));
   }

   // $FF: synthetic method
   static Object zipAll$(final IterableOps $this, final Iterable that, final Object thisElem, final Object thatElem) {
      return $this.zipAll(that, thisElem, thatElem);
   }

   default Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
      return this.iterableFactory().from(new View.ZipAll(this, that, thisElem, thatElem));
   }

   // $FF: synthetic method
   static Tuple2 unzip$(final IterableOps $this, final Function1 asPair) {
      return $this.unzip(asPair);
   }

   default Tuple2 unzip(final Function1 asPair) {
      View first = new View.Map(this, (x$3) -> ((Tuple2)asPair.apply(x$3))._1());
      View second = new View.Map(this, (x$4) -> ((Tuple2)asPair.apply(x$4))._2());
      return new Tuple2(this.iterableFactory().from(first), this.iterableFactory().from(second));
   }

   // $FF: synthetic method
   static Tuple3 unzip3$(final IterableOps $this, final Function1 asTriple) {
      return $this.unzip3(asTriple);
   }

   default Tuple3 unzip3(final Function1 asTriple) {
      View first = new View.Map(this, (x$5) -> ((Tuple3)asTriple.apply(x$5))._1());
      View second = new View.Map(this, (x$6) -> ((Tuple3)asTriple.apply(x$6))._2());
      View third = new View.Map(this, (x$7) -> ((Tuple3)asTriple.apply(x$7))._3());
      return new Tuple3(this.iterableFactory().from(first), this.iterableFactory().from(second), this.iterableFactory().from(third));
   }

   // $FF: synthetic method
   static Iterator tails$(final IterableOps $this) {
      return $this.tails();
   }

   default Iterator tails() {
      Function1 iterateUntilEmpty_f = (x$8) -> (Iterable)x$8.tail();
      Iterator$ var10000 = Iterator$.MODULE$;
      Object iterateUntilEmpty_iterate_start = this.toIterable();
      Iterator var7 = new AbstractIterator(iterateUntilEmpty_iterate_start, iterateUntilEmpty_f) {
         private boolean first;
         private Object acc;
         private final Function1 f$6;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            if (this.first) {
               this.first = false;
            } else {
               this.acc = this.f$6.apply(this.acc);
            }

            return this.acc;
         }

         public {
            this.f$6 = f$6;
            this.first = true;
            this.acc = start$3;
         }
      };
      iterateUntilEmpty_iterate_start = null;
      Iterator iterateUntilEmpty_it = var7.takeWhile((x$10) -> BoxesRunTime.boxToBoolean($anonfun$iterateUntilEmpty$1(x$10)));
      Function0 iterateUntilEmpty_$plus$plus_xs = () -> {
         Iterator$ var10000 = Iterator$.MODULE$;
         Object single_a = Iterable$.MODULE$.empty();
         return new AbstractIterator(single_a) {
            private boolean consumed;
            private final Object a$1;

            public boolean hasNext() {
               return !this.consumed;
            }

            public Object next() {
               if (this.consumed) {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty.next();
               } else {
                  this.consumed = true;
                  return this.a$1;
               }
            }

            public Iterator sliceIterator(final int from, final int until) {
               if (!this.consumed && from <= 0 && until != 0) {
                  return this;
               } else {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty;
               }
            }

            public {
               this.a$1 = a$1;
               this.consumed = false;
            }
         };
      };
      if (iterateUntilEmpty_it == null) {
         throw null;
      } else {
         var7 = iterateUntilEmpty_it.concat(iterateUntilEmpty_$plus$plus_xs);
         iterateUntilEmpty_$plus$plus_xs = null;
         return var7.map((coll) -> this.fromSpecific(coll));
      }
   }

   // $FF: synthetic method
   static Iterator inits$(final IterableOps $this) {
      return $this.inits();
   }

   default Iterator inits() {
      Function1 iterateUntilEmpty_f = (x$9) -> (Iterable)x$9.init();
      Iterator$ var10000 = Iterator$.MODULE$;
      Object iterateUntilEmpty_iterate_start = this.toIterable();
      Iterator var7 = new AbstractIterator(iterateUntilEmpty_iterate_start, iterateUntilEmpty_f) {
         private boolean first;
         private Object acc;
         private final Function1 f$6;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            if (this.first) {
               this.first = false;
            } else {
               this.acc = this.f$6.apply(this.acc);
            }

            return this.acc;
         }

         public {
            this.f$6 = f$6;
            this.first = true;
            this.acc = start$3;
         }
      };
      iterateUntilEmpty_iterate_start = null;
      Iterator iterateUntilEmpty_it = var7.takeWhile((x$10) -> BoxesRunTime.boxToBoolean($anonfun$iterateUntilEmpty$1(x$10)));
      Function0 iterateUntilEmpty_$plus$plus_xs = () -> {
         Iterator$ var10000 = Iterator$.MODULE$;
         Object single_a = Iterable$.MODULE$.empty();
         return new AbstractIterator(single_a) {
            private boolean consumed;
            private final Object a$1;

            public boolean hasNext() {
               return !this.consumed;
            }

            public Object next() {
               if (this.consumed) {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty.next();
               } else {
                  this.consumed = true;
                  return this.a$1;
               }
            }

            public Iterator sliceIterator(final int from, final int until) {
               if (!this.consumed && from <= 0 && until != 0) {
                  return this;
               } else {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty;
               }
            }

            public {
               this.a$1 = a$1;
               this.consumed = false;
            }
         };
      };
      if (iterateUntilEmpty_it == null) {
         throw null;
      } else {
         var7 = iterateUntilEmpty_it.concat(iterateUntilEmpty_$plus$plus_xs);
         iterateUntilEmpty_$plus$plus_xs = null;
         return var7.map((coll) -> this.fromSpecific(coll));
      }
   }

   // $FF: synthetic method
   static Object tapEach$(final IterableOps $this, final Function1 f) {
      return $this.tapEach(f);
   }

   default Object tapEach(final Function1 f) {
      return this.fromSpecific(new View.Map(this, (a) -> {
         f.apply(a);
         return a;
      }));
   }

   private Iterator iterateUntilEmpty(final Function1 f) {
      Iterator$ var10000 = Iterator$.MODULE$;
      Object iterate_start = this.toIterable();
      Iterator var7 = new AbstractIterator(iterate_start, f) {
         private boolean first;
         private Object acc;
         private final Function1 f$6;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            if (this.first) {
               this.first = false;
            } else {
               this.acc = this.f$6.apply(this.acc);
            }

            return this.acc;
         }

         public {
            this.f$6 = f$6;
            this.first = true;
            this.acc = start$3;
         }
      };
      iterate_start = null;
      Iterator it = var7.takeWhile((x$10) -> BoxesRunTime.boxToBoolean($anonfun$iterateUntilEmpty$1(x$10)));
      Function0 $plus$plus_xs = () -> {
         Iterator$ var10000 = Iterator$.MODULE$;
         Object single_a = Iterable$.MODULE$.empty();
         return new AbstractIterator(single_a) {
            private boolean consumed;
            private final Object a$1;

            public boolean hasNext() {
               return !this.consumed;
            }

            public Object next() {
               if (this.consumed) {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty.next();
               } else {
                  this.consumed = true;
                  return this.a$1;
               }
            }

            public Iterator sliceIterator(final int from, final int until) {
               if (!this.consumed && from <= 0 && until != 0) {
                  return this;
               } else {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty;
               }
            }

            public {
               this.a$1 = a$1;
               this.consumed = false;
            }
         };
      };
      if (it == null) {
         throw null;
      } else {
         var7 = it.concat($plus$plus_xs);
         $plus$plus_xs = null;
         return var7.map((coll) -> this.fromSpecific(coll));
      }
   }

   // $FF: synthetic method
   static Object $plus$plus$colon$(final IterableOps $this, final IterableOnce that) {
      return $this.$plus$plus$colon(that);
   }

   /** @deprecated */
   default Object $plus$plus$colon(final IterableOnce that) {
      IterableFactory var10000 = this.iterableFactory();
      Object var10001;
      if (that instanceof Iterable) {
         Iterable var2 = (Iterable)that;
         var10001 = new View.Concat(var2, this);
      } else {
         var10001 = that.iterator();
         Function0 $plus$plus_xs = () -> this.iterator();
         if (var10001 == null) {
            throw null;
         }

         var10001 = var10001.concat($plus$plus_xs);
         $plus$plus_xs = null;
      }

      return var10000.from(var10001);
   }

   private static Nothing$ fail$1() {
      throw new IllegalArgumentException("transpose requires all collections have the same size");
   }

   // $FF: synthetic method
   static void $anonfun$transpose$3(final IntRef i$1, final int headSize$1, final scala.collection.immutable.IndexedSeq bs$1, final Object x) {
      if (i$1.elem >= headSize$1) {
         throw new IllegalArgumentException("transpose requires all collections have the same size");
      } else {
         Growable var10000 = (Growable)bs$1.apply(i$1.elem);
         if (var10000 == null) {
            throw null;
         } else {
            var10000.addOne(x);
            ++i$1.elem;
         }
      }
   }

   // $FF: synthetic method
   static void $anonfun$transpose$2(final Function1 asIterable$1, final int headSize$1, final scala.collection.immutable.IndexedSeq bs$1, final Object xs) {
      int create_e = 0;
      IntRef i = new IntRef(create_e);
      ((IterableOnceOps)asIterable$1.apply(xs)).foreach((x) -> {
         $anonfun$transpose$3(i, headSize$1, bs$1, x);
         return BoxedUnit.UNIT;
      });
      if (i.elem != headSize$1) {
         throw new IllegalArgumentException("transpose requires all collections have the same size");
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$iterateUntilEmpty$1(final Iterable x$10) {
      return x$10.nonEmpty();
   }

   static void $init$(final IterableOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static final class SizeCompareOps {
      private final IterableOps it;

      public IterableOps it() {
         return this.it;
      }

      public boolean $less(final int size) {
         SizeCompareOps$ var10000 = IterableOps.SizeCompareOps$.MODULE$;
         return this.it().sizeCompare(size) < 0;
      }

      public boolean $less$eq(final int size) {
         SizeCompareOps$ var10000 = IterableOps.SizeCompareOps$.MODULE$;
         return this.it().sizeCompare(size) <= 0;
      }

      public boolean $eq$eq(final int size) {
         SizeCompareOps$ var10000 = IterableOps.SizeCompareOps$.MODULE$;
         return this.it().sizeCompare(size) == 0;
      }

      public boolean $bang$eq(final int size) {
         SizeCompareOps$ var10000 = IterableOps.SizeCompareOps$.MODULE$;
         return this.it().sizeCompare(size) != 0;
      }

      public boolean $greater$eq(final int size) {
         SizeCompareOps$ var10000 = IterableOps.SizeCompareOps$.MODULE$;
         return this.it().sizeCompare(size) >= 0;
      }

      public boolean $greater(final int size) {
         SizeCompareOps$ var10000 = IterableOps.SizeCompareOps$.MODULE$;
         return this.it().sizeCompare(size) > 0;
      }

      public int hashCode() {
         SizeCompareOps$ var10000 = IterableOps.SizeCompareOps$.MODULE$;
         return this.it().hashCode();
      }

      public boolean equals(final Object x$1) {
         return IterableOps.SizeCompareOps$.MODULE$.equals$extension(this.it(), x$1);
      }

      public SizeCompareOps(final IterableOps it) {
         this.it = it;
      }
   }

   public static class SizeCompareOps$ {
      public static final SizeCompareOps$ MODULE$ = new SizeCompareOps$();

      public final boolean $less$extension(final IterableOps $this, final int size) {
         return $this.sizeCompare(size) < 0;
      }

      public final boolean $less$eq$extension(final IterableOps $this, final int size) {
         return $this.sizeCompare(size) <= 0;
      }

      public final boolean $eq$eq$extension(final IterableOps $this, final int size) {
         return $this.sizeCompare(size) == 0;
      }

      public final boolean $bang$eq$extension(final IterableOps $this, final int size) {
         return $this.sizeCompare(size) != 0;
      }

      public final boolean $greater$eq$extension(final IterableOps $this, final int size) {
         return $this.sizeCompare(size) >= 0;
      }

      public final boolean $greater$extension(final IterableOps $this, final int size) {
         return $this.sizeCompare(size) > 0;
      }

      public final int hashCode$extension(final IterableOps $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final IterableOps $this, final Object x$1) {
         if (x$1 instanceof SizeCompareOps) {
            IterableOps var3 = x$1 == null ? null : ((SizeCompareOps)x$1).it();
            if ($this == null) {
               if (var3 == null) {
                  return true;
               }
            } else if ($this.equals(var3)) {
               return true;
            }
         }

         return false;
      }
   }

   public static class WithFilter extends scala.collection.WithFilter {
      private static final long serialVersionUID = 3L;
      private final IterableOps self;
      private final Function1 p;

      public Iterable filtered() {
         return new View.Filter(this.self, this.p, false);
      }

      public Object map(final Function1 f) {
         return this.self.iterableFactory().from(new View.Map(this.filtered(), f));
      }

      public Object flatMap(final Function1 f) {
         return this.self.iterableFactory().from(new View.FlatMap(this.filtered(), f));
      }

      public void foreach(final Function1 f) {
         this.filtered().foreach(f);
      }

      public WithFilter withFilter(final Function1 q) {
         return new WithFilter(this.self, (a) -> BoxesRunTime.boxToBoolean($anonfun$withFilter$1(this, q, a)));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$withFilter$1(final WithFilter $this, final Function1 q$1, final Object a) {
         return BoxesRunTime.unboxToBoolean($this.p.apply(a)) && BoxesRunTime.unboxToBoolean(q$1.apply(a));
      }

      public WithFilter(final IterableOps self, final Function1 p) {
         this.self = self;
         this.p = p;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
