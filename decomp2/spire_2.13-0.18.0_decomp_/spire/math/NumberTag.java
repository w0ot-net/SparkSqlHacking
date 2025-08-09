package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\rEr\u0001CA\u0016\u0003[A\t!a\u000e\u0007\u0011\u0005m\u0012Q\u0006E\u0001\u0003{Aq!a\u0013\u0002\t\u0003\ti\u0005C\u0004\u0002P\u0005!\t!!\u0015\u0007\u0013\u00055\u0014\u0001%A\u0012\"\u0005=ta\u0002B1\u0003!\u0005%Q\u0002\u0004\b\u0005\u000f\t\u0001\u0012\u0011B\u0005\u0011\u001d\tYE\u0002C\u0001\u0005\u0017A\u0011\"!(\u0007\u0003\u0003%\t%a(\t\u0013\u0005Ef!!A\u0005\u0002\u0005M\u0006\"CA^\r\u0005\u0005I\u0011\u0001B\b\u0011%\tIMBA\u0001\n\u0003\nY\rC\u0005\u0002Z\u001a\t\t\u0011\"\u0001\u0003\u0014!I\u0011Q\u001d\u0004\u0002\u0002\u0013\u0005\u0013q\u001d\u0005\n\u0003S4\u0011\u0011!C!\u0003WD\u0011\"!<\u0007\u0003\u0003%I!a<\b\u000f\t\r\u0014\u0001#!\u0002\u001c\u001a9\u00111O\u0001\t\u0002\u0006U\u0004bBA&#\u0011\u0005\u0011\u0011\u0014\u0005\n\u0003;\u000b\u0012\u0011!C!\u0003?C\u0011\"!-\u0012\u0003\u0003%\t!a-\t\u0013\u0005m\u0016#!A\u0005\u0002\u0005u\u0006\"CAe#\u0005\u0005I\u0011IAf\u0011%\tI.EA\u0001\n\u0003\tY\u000eC\u0005\u0002fF\t\t\u0011\"\u0011\u0002h\"I\u0011\u0011^\t\u0002\u0002\u0013\u0005\u00131\u001e\u0005\n\u0003[\f\u0012\u0011!C\u0005\u0003_<qA!\u001a\u0002\u0011\u0003\u000biPB\u0004\u0002x\u0006A\t)!?\t\u000f\u0005-C\u0004\"\u0001\u0002|\"I\u0011Q\u0014\u000f\u0002\u0002\u0013\u0005\u0013q\u0014\u0005\n\u0003cc\u0012\u0011!C\u0001\u0003gC\u0011\"a/\u001d\u0003\u0003%\t!a@\t\u0013\u0005%G$!A\u0005B\u0005-\u0007\"CAm9\u0005\u0005I\u0011\u0001B\u0002\u0011%\t)\u000fHA\u0001\n\u0003\n9\u000fC\u0005\u0002jr\t\t\u0011\"\u0011\u0002l\"I\u0011Q\u001e\u000f\u0002\u0002\u0013%\u0011q\u001e\u0004\u0007\u0005O\n\u0001A!\u001b\t\u0015\tMdE!A!\u0002\u0013\u0011y\u0007\u0003\u0006\u0003v\u0019\u0012\t\u0011)A\u0005\u0005_B!Ba\u001e'\u0005\u0003\u0005\u000b\u0011\u0002B8\u0011\u001d\tYE\nC\u0001\u0005sBq!!\u001a'\t\u0003\u0011\u0019\tC\u0005\u00032\u0019\u0012\r\u0011\"\u0001\u0003\u0006\"A!\u0011\u0012\u0014!\u0002\u0013\u00119\tC\u0005\u0003\u0018\u0019\u0012\r\u0011\"\u0001\u0003\u0006\"A!1\u0012\u0014!\u0002\u0013\u00119\tC\u0005\u00034\u0019\u0012\r\u0011\"\u0001\u0003\u0006\"A!Q\u0012\u0014!\u0002\u0013\u00119\tC\u0004\u00036\u0019\"\tA!\"\t\u000f\t]b\u0005\"\u0001\u0003\u0006\"9!\u0011\b\u0014\u0005\u0002\t\u0015\u0005b\u0002B\u001eM\u0011\u0005!Q\b\u0005\b\u0005\u007f1C\u0011\u0001B\u001f\u0011\u001d\u0011)E\nC\u0001\u0005\u001fCqA!\u0014'\t\u0003\u0011\u0019J\u0002\u0004\u0003\u0018\u0006\u0001!\u0011\u0014\u0005\u000b\u0005gJ$\u0011!Q\u0001\n\t}\u0005B\u0003B<s\t\u0005\t\u0015!\u0003\u0003 \"9\u00111J\u001d\u0005\u0002\t\r\u0006bBA3s\u0011\u0005!1\u0011\u0005\n\u0005cI$\u0019!C\u0001\u0005WC\u0001B!#:A\u0003%!Q\u0016\u0005\n\u0005/I$\u0019!C\u0001\u0005WC\u0001Ba#:A\u0003%!Q\u0016\u0005\n\u0005gI$\u0019!C\u0001\u0005WC\u0001B!$:A\u0003%!Q\u0016\u0005\b\u0005kID\u0011\u0001BV\u0011\u001d\u00119$\u000fC\u0001\u0005WCqA!\u000f:\t\u0003\u0011Y\u000bC\u0004\u0003<e\"\tA!\u0010\t\u000f\t}\u0012\b\"\u0001\u0003>!9!QI\u001d\u0005\u0002\t=\u0006b\u0002B's\u0011\u0005!1\u0017\u0004\b\u0005o\u000b\u0011\u0011\u0001B]\u0011)\u0011\u0019h\u0013B\u0001B\u0003%!q\u0018\u0005\u000b\u0005kZ%\u0011!Q\u0001\n\t}\u0006B\u0003B<\u0017\n\u0005\t\u0015!\u0003\u0003@\"Q!1Y&\u0003\u0002\u0003\u0006IAa0\t\u0015\t\u00157J!A!\u0002\u0013\u0011y\f\u0003\u0006\u0003H.\u0013\t\u0011)A\u0005\u0005\u007fCq!a\u0013L\t\u0003\u0011I\rC\u0004\u0002f-#\tAa!\t\u0013\tE2J1A\u0005\u0002\te\u0007\u0002\u0003BE\u0017\u0002\u0006IAa7\t\u0013\t]1J1A\u0005\u0002\te\u0007\u0002\u0003BF\u0017\u0002\u0006IAa7\t\u0013\tM2J1A\u0005\u0002\te\u0007\u0002\u0003BG\u0017\u0002\u0006IAa7\t\u0013\tU2J1A\u0005\u0002\te\u0007\u0002\u0003Bo\u0017\u0002\u0006IAa7\t\u0013\t]2J1A\u0005\u0002\te\u0007\u0002\u0003Bp\u0017\u0002\u0006IAa7\t\u0013\te2J1A\u0005\u0002\te\u0007\u0002\u0003Bq\u0017\u0002\u0006IAa7\t\u000f\tm2\n\"\u0001\u0003>!9!qH&\u0005\u0002\tubA\u0002Br\u0003\u0001\u0011)\u000f\u0003\u0006\u0002f\t\u0014)\u0019!C\u0001\u0005\u0007C!Ba<c\u0005\u0003\u0005\u000b\u0011BA<\u0011)\u0011\u0019H\u0019B\u0001B\u0003%!1\u001e\u0005\b\u0003\u0017\u0012G\u0011\u0001By\u0011%\u0011\tD\u0019b\u0001\n\u0003\u0011I\u0010\u0003\u0005\u0003\n\n\u0004\u000b\u0011\u0002B~\u0011\u001d\u00119B\u0019C\u0001\u0005sDqAa\rc\t\u0003\u0011I\u0010C\u0004\u00036\t$\tA!?\t\u000f\t]\"\r\"\u0001\u0003z\"9!\u0011\b2\u0005\u0002\te\bb\u0002B\u001eE\u0012\u0005!Q\b\u0005\b\u0005\u007f\u0011G\u0011\u0001B\u001f\u0011\u001d\u0011)E\u0019C\u0001\u0005{DqA!\u0014c\t\u0003\u0019\tA\u0002\u0004\u0004\u0006\u0005\u00011q\u0001\u0005\u000b\u0003K\u0012(Q1A\u0005\u0002\t\r\u0005B\u0003Bxe\n\u0005\t\u0015!\u0003\u0002x!Q!\u0011\u0007:\u0003\u0006\u0004%\ta!\u0005\t\u0015\t%%O!A!\u0002\u0013\u0019\u0019\u0002\u0003\u0006\u0003\u0018I\u0014)\u0019!C\u0001\u0007#A!Ba#s\u0005\u0003\u0005\u000b\u0011BB\n\u0011)\u0011\u0019D\u001dBC\u0002\u0013\u00051\u0011\u0003\u0005\u000b\u0005\u001b\u0013(\u0011!Q\u0001\n\rM\u0001B\u0003B\u001ee\n\u0015\r\u0011\"\u0001\u0003>!Q1Q\u0003:\u0003\u0002\u0003\u0006I!!8\t\u0015\t}\"O!b\u0001\n\u0003\u0011i\u0004\u0003\u0006\u0004\u0018I\u0014\t\u0011)A\u0005\u0003;Dq!a\u0013s\t\u0003\u0019I\u0002C\u0004\u00036I$\ta!\u0005\t\u000f\t]\"\u000f\"\u0001\u0004\u0012!9!\u0011\b:\u0005\u0002\rE\u0001b\u0002B#e\u0012\u00051\u0011\u0006\u0005\b\u0005\u001b\u0012H\u0011AB\u0017\r)\tY$!\f\u0011\u0002\u0007\u0005\u0011q\u000b\u0005\t\u00037\nY\u0001\"\u0001\u0002^!A\u0011QMA\u0006\r\u0003\t9\u0007\u0003\u0005\u0003\u0018\u0005-a\u0011\u0001B\r\u0011!\u0011\t$a\u0003\u0007\u0002\te\u0001\u0002\u0003B\u001a\u0003\u00171\tA!\u0007\t\u0011\tU\u00121\u0002D\u0001\u00053A\u0001Ba\u000e\u0002\f\u0019\u0005!\u0011\u0004\u0005\t\u0005s\tYA\"\u0001\u0003\u001a!A!1HA\u0006\r\u0003\u0011i\u0004\u0003\u0005\u0003@\u0005-a\u0011\u0001B\u001f\u0011!\u0011\t%a\u0003\u0005\u0002\tu\u0002\u0002\u0003B\"\u0003\u0017!\tA!\u0010\t\u0011\t\u0015\u00131\u0002D\u0001\u0005\u000fB\u0001B!\u0014\u0002\f\u0019\u0005!q\n\u0005\t\u0005'\nY\u0001\"\u0001\u0003V\u0005Ia*^7cKJ$\u0016m\u001a\u0006\u0005\u0003_\t\t$\u0001\u0003nCRD'BAA\u001a\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u00012!!\u000f\u0002\u001b\t\tiCA\u0005Ok6\u0014WM\u001d+bON\u0019\u0011!a\u0010\u0011\t\u0005\u0005\u0013qI\u0007\u0003\u0003\u0007R!!!\u0012\u0002\u000bM\u001c\u0017\r\\1\n\t\u0005%\u00131\t\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\t\t9$A\u0003baBd\u00170\u0006\u0003\u0002T\tmC\u0003BA+\u0005;\u0002b!!\u000f\u0002\f\teS\u0003BA-\u0005K\u0019B!a\u0003\u0002@\u00051A%\u001b8ji\u0012\"\"!a\u0018\u0011\t\u0005\u0005\u0013\u0011M\u0005\u0005\u0003G\n\u0019E\u0001\u0003V]&$\u0018A\u0003:fg>dW\u000f^5p]V\u0011\u0011\u0011\u000e\t\u0004\u0003W\"abAA\u001d\u0001\tQ!+Z:pYV$\u0018n\u001c8\u0014\u0007\u0011\ty$\u000b\u0003\u0005#q1!aC!qaJ|\u00070[7bi\u0016\u001c\u0012\"EA \u0003o\nY(!!\u0011\u0007\u0005eD!D\u0001\u0002!\u0011\t\t%! \n\t\u0005}\u00141\t\u0002\b!J|G-^2u!\u0011\t\u0019)a%\u000f\t\u0005\u0015\u0015q\u0012\b\u0005\u0003\u000f\u000bi)\u0004\u0002\u0002\n*!\u00111RA\u001b\u0003\u0019a$o\\8u}%\u0011\u0011QI\u0005\u0005\u0003#\u000b\u0019%A\u0004qC\u000e\\\u0017mZ3\n\t\u0005U\u0015q\u0013\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0005\u0003#\u000b\u0019\u0005\u0006\u0002\u0002\u001cB\u0019\u0011\u0011P\t\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t\t\u000b\u0005\u0003\u0002$\u00065VBAAS\u0015\u0011\t9+!+\u0002\t1\fgn\u001a\u0006\u0003\u0003W\u000bAA[1wC&!\u0011qVAS\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011Q\u0017\t\u0005\u0003\u0003\n9,\u0003\u0003\u0002:\u0006\r#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA`\u0003\u000b\u0004B!!\u0011\u0002B&!\u00111YA\"\u0005\r\te.\u001f\u0005\n\u0003\u000f,\u0012\u0011!a\u0001\u0003k\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAg!\u0019\ty-!6\u0002@6\u0011\u0011\u0011\u001b\u0006\u0005\u0003'\f\u0019%\u0001\u0006d_2dWm\u0019;j_:LA!a6\u0002R\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ti.a9\u0011\t\u0005\u0005\u0013q\\\u0005\u0005\u0003C\f\u0019EA\u0004C_>dW-\u00198\t\u0013\u0005\u001dw#!AA\u0002\u0005}\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005U\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005\u0005\u0016\u0001D<sSR,'+\u001a9mC\u000e,GCAAy!\u0011\t\u0019+a=\n\t\u0005U\u0018Q\u0015\u0002\u0007\u001f\nTWm\u0019;\u0003\u000b\u0015C\u0018m\u0019;\u0014\u0013q\ty$a\u001e\u0002|\u0005\u0005ECAA\u007f!\r\tI\b\b\u000b\u0005\u0003\u007f\u0013\t\u0001C\u0005\u0002H\u0002\n\t\u00111\u0001\u00026R!\u0011Q\u001cB\u0003\u0011%\t9MIA\u0001\u0002\u0004\tyL\u0001\u0005J]R,wM]1m'%1\u0011qHA<\u0003w\n\t\t\u0006\u0002\u0003\u000eA\u0019\u0011\u0011\u0010\u0004\u0015\t\u0005}&\u0011\u0003\u0005\n\u0003\u000fT\u0011\u0011!a\u0001\u0003k#B!!8\u0003\u0016!I\u0011q\u0019\u0007\u0002\u0002\u0003\u0007\u0011qX\u0001\fQ\u0006\u001cX*\u001b8WC2,X-\u0006\u0002\u0003\u001cA1\u0011\u0011\tB\u000f\u0005CIAAa\b\u0002D\t1q\n\u001d;j_:\u0004BAa\t\u0003&1\u0001A\u0001\u0003B\u0014\u0003\u0017\u0011\rA!\u000b\u0003\u0003\u0005\u000bBAa\u000b\u0002@B!\u0011\u0011\tB\u0017\u0013\u0011\u0011y#a\u0011\u0003\u000f9{G\u000f[5oO\u00069\u0001.Y:[KJ|\u0017a\u00035bg6\u000b\u0007PV1mk\u0016\fa\u0001[1t\u001d\u0006t\u0015a\u00055bgB{7/\u001b;jm\u0016LeNZ5oSRL\u0018a\u00055bg:+w-\u0019;jm\u0016LeNZ5oSRL\u0018!C8wKJ4Gn\\<t+\t\ti.\u0001\u0005jgNKwM\\3e\u0003\u00191\u0017N\\5uK\u0006A\u0011N\u001c4j]&$X-\u0001\u0006jg&sg-\u001b8ji\u0016$B!!8\u0003J!A!1JA\u0013\u0001\u0004\u0011\t#A\u0001b\u0003\u0015I7OT1O)\u0011\tiN!\u0015\t\u0011\t-\u0013q\u0005a\u0001\u0005C\t\u0001\"[:GS:LG/\u001a\u000b\u0005\u0003;\u00149\u0006\u0003\u0005\u0003L\u0005%\u0002\u0019\u0001B\u0011!\u0011\u0011\u0019Ca\u0017\u0005\u000f\t\u001d2A1\u0001\u0003*!9!qL\u0002A\u0004\u0005U\u0013AA3w\u0003!Ie\u000e^3he\u0006d\u0017aC!qaJ|\u00070[7bi\u0016\fQ!\u0012=bGR\u0014QBQ;jYRLg.\u00138u)\u0006<W\u0003\u0002B6\u0005c\u001aRAJA \u0005[\u0002b!!\u000f\u0002\f\t=\u0004\u0003\u0002B\u0012\u0005c\"qAa\n'\u0005\u0004\u0011I#\u0001\u0003{KJ|\u0017aA7j]\u0006\u0019Q.\u0019=\u0015\u0011\tm$Q\u0010B@\u0005\u0003\u0003R!!\u001f'\u0005_BqAa\u001d+\u0001\u0004\u0011y\u0007C\u0004\u0003v)\u0002\rAa\u001c\t\u000f\t]$\u00061\u0001\u0003pU\u0011\u0011qO\u000b\u0003\u0005\u000f\u0003b!!\u0011\u0003\u001e\t=\u0014\u0001\u00035bgj+'o\u001c\u0011\u0002\u0019!\f7/T5o-\u0006dW/\u001a\u0011\u0002\u0019!\f7/T1y-\u0006dW/\u001a\u0011\u0015\t\u0005u'\u0011\u0013\u0005\b\u0005\u0017:\u0004\u0019\u0001B8)\u0011\tiN!&\t\u000f\t-\u0003\b1\u0001\u0003p\tqQK\\:jO:,G-\u00138u)\u0006<W\u0003\u0002BN\u0005C\u001bR!OA \u0005;\u0003b!!\u000f\u0002\f\t}\u0005\u0003\u0002B\u0012\u0005C#qAa\n:\u0005\u0004\u0011I\u0003\u0006\u0004\u0003&\n\u001d&\u0011\u0016\t\u0006\u0003sJ$q\u0014\u0005\b\u0005gb\u0004\u0019\u0001BP\u0011\u001d\u00119\b\u0010a\u0001\u0005?+\"A!,\u0011\r\u0005\u0005#Q\u0004BP)\u0011\tiN!-\t\u000f\t-\u0013\n1\u0001\u0003 R!\u0011Q\u001cB[\u0011\u001d\u0011YE\u0013a\u0001\u0005?\u0013qBQ;jYRLgN\u00127pCR$\u0016mZ\u000b\u0005\u0005w\u0013\tmE\u0003L\u0003\u007f\u0011i\f\u0005\u0004\u0002:\u0005-!q\u0018\t\u0005\u0005G\u0011\t\rB\u0004\u0003(-\u0013\rA!\u000b\u0002\u00079\fg.\u0001\u0004q_NLeNZ\u0001\u0007]\u0016<\u0017J\u001c4\u0015\u001d\t-'Q\u001aBh\u0005#\u0014\u0019N!6\u0003XB)\u0011\u0011P&\u0003@\"9!1\u000f*A\u0002\t}\u0006b\u0002B;%\u0002\u0007!q\u0018\u0005\b\u0005o\u0012\u0006\u0019\u0001B`\u0011\u001d\u0011\u0019M\u0015a\u0001\u0005\u007fCqA!2S\u0001\u0004\u0011y\fC\u0004\u0003HJ\u0003\rAa0\u0016\u0005\tm\u0007CBA!\u0005;\u0011y,A\u0004iCNt\u0015M\u0014\u0011\u0002)!\f7\u000fU8tSRLg/Z%oM&t\u0017\u000e^=!\u0003QA\u0017m\u001d(fO\u0006$\u0018N^3J]\u001aLg.\u001b;zA\tAA*\u0019:hKR\u000bw-\u0006\u0003\u0003h\n58#\u00022\u0002@\t%\bCBA\u001d\u0003\u0017\u0011Y\u000f\u0005\u0003\u0003$\t5Ha\u0002B\u0014E\n\u0007!\u0011F\u0001\fe\u0016\u001cx\u000e\\;uS>t\u0007\u0005\u0006\u0004\u0003t\nU(q\u001f\t\u0006\u0003s\u0012'1\u001e\u0005\b\u0003K2\u0007\u0019AA<\u0011\u001d\u0011\u0019H\u001aa\u0001\u0005W,\"Aa?\u0011\r\u0005\u0005#Q\u0004Bv)\u0011\tiNa@\t\u000f\t-\u0003\u000f1\u0001\u0003lR!\u0011Q\\B\u0002\u0011\u001d\u0011Y%\u001da\u0001\u0005W\u0014\u0011bQ;ti>lG+Y4\u0016\t\r%1qB\n\u0006e\u0006}21\u0002\t\u0007\u0003s\tYa!\u0004\u0011\t\t\r2q\u0002\u0003\b\u0005O\u0011(\u0019\u0001B\u0015+\t\u0019\u0019\u0002\u0005\u0004\u0002B\tu1QB\u0001\u000b_Z,'O\u001a7poN\u0004\u0013!C5t'&<g.\u001a3!)9\u0019Yb!\b\u0004 \r\u000521EB\u0013\u0007O\u0001R!!\u001fs\u0007\u001bAq!!\u001a\u0000\u0001\u0004\t9\bC\u0004\u00032}\u0004\raa\u0005\t\u000f\t]q\u00101\u0001\u0004\u0014!9!1G@A\u0002\rM\u0001b\u0002B\u001e\u007f\u0002\u0007\u0011Q\u001c\u0005\b\u0005\u007fy\b\u0019AAo)\u0011\tina\u000b\t\u0011\t-\u0013q\u0001a\u0001\u0007\u001b!B!!8\u00040!A!1JA\u0005\u0001\u0004\u0019i\u0001"
)
public interface NumberTag {
   static NumberTag apply(final NumberTag ev) {
      return NumberTag$.MODULE$.apply(ev);
   }

   Resolution resolution();

   Option hasMinValue();

   Option hasZero();

   Option hasMaxValue();

   Option hasNaN();

   Option hasPositiveInfinity();

   Option hasNegativeInfinity();

   boolean overflows();

   boolean isSigned();

   default boolean finite() {
      return this.hasMinValue().isDefined() && this.hasMaxValue().isDefined();
   }

   default boolean infinite() {
      return this.hasMinValue().isEmpty() || this.hasMaxValue().isEmpty();
   }

   boolean isInfinite(final Object a);

   boolean isNaN(final Object a);

   default boolean isFinite(final Object a) {
      return !this.isInfinite(a) && !this.isNaN(a);
   }

   static void $init$(final NumberTag $this) {
   }

   public static class Integral$ implements Resolution, Product, Serializable {
      public static final Integral$ MODULE$ = new Integral$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Integral";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Integral$;
      }

      public int hashCode() {
         return 634730956;
      }

      public String toString() {
         return "Integral";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Integral$.class);
      }
   }

   public static class Approximate$ implements Resolution, Product, Serializable {
      public static final Approximate$ MODULE$ = new Approximate$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Approximate";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Approximate$;
      }

      public int hashCode() {
         return -1862446348;
      }

      public String toString() {
         return "Approximate";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Approximate$.class);
      }
   }

   public static class Exact$ implements Resolution, Product, Serializable {
      public static final Exact$ MODULE$ = new Exact$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Exact";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Exact$;
      }

      public int hashCode() {
         return 67394271;
      }

      public String toString() {
         return "Exact";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Exact$.class);
      }
   }

   public static class BuiltinIntTag implements NumberTag {
      private final Option hasZero;
      private final Option hasMinValue;
      private final Option hasMaxValue;

      public boolean finite() {
         return NumberTag.super.finite();
      }

      public boolean infinite() {
         return NumberTag.super.infinite();
      }

      public boolean isFinite(final Object a) {
         return NumberTag.super.isFinite(a);
      }

      public Resolution resolution() {
         return NumberTag.Integral$.MODULE$;
      }

      public Option hasZero() {
         return this.hasZero;
      }

      public Option hasMinValue() {
         return this.hasMinValue;
      }

      public Option hasMaxValue() {
         return this.hasMaxValue;
      }

      public Option hasNaN() {
         return scala.None..MODULE$;
      }

      public Option hasPositiveInfinity() {
         return scala.None..MODULE$;
      }

      public Option hasNegativeInfinity() {
         return scala.None..MODULE$;
      }

      public boolean overflows() {
         return true;
      }

      public boolean isSigned() {
         return true;
      }

      public boolean isInfinite(final Object a) {
         return false;
      }

      public boolean isNaN(final Object a) {
         return false;
      }

      public BuiltinIntTag(final Object zero, final Object min, final Object max) {
         NumberTag.$init$(this);
         this.hasZero = new Some(zero);
         this.hasMinValue = new Some(min);
         this.hasMaxValue = new Some(max);
      }
   }

   public static class UnsignedIntTag implements NumberTag {
      private final Option hasZero;
      private final Option hasMinValue;
      private final Option hasMaxValue;

      public boolean finite() {
         return NumberTag.super.finite();
      }

      public boolean infinite() {
         return NumberTag.super.infinite();
      }

      public boolean isFinite(final Object a) {
         return NumberTag.super.isFinite(a);
      }

      public Resolution resolution() {
         return NumberTag.Integral$.MODULE$;
      }

      public Option hasZero() {
         return this.hasZero;
      }

      public Option hasMinValue() {
         return this.hasMinValue;
      }

      public Option hasMaxValue() {
         return this.hasMaxValue;
      }

      public Option hasNaN() {
         return scala.None..MODULE$;
      }

      public Option hasPositiveInfinity() {
         return scala.None..MODULE$;
      }

      public Option hasNegativeInfinity() {
         return scala.None..MODULE$;
      }

      public boolean overflows() {
         return true;
      }

      public boolean isSigned() {
         return false;
      }

      public boolean isInfinite(final Object a) {
         return false;
      }

      public boolean isNaN(final Object a) {
         return false;
      }

      public UnsignedIntTag(final Object zero, final Object max) {
         NumberTag.$init$(this);
         this.hasZero = new Some(zero);
         this.hasMinValue = new Some(zero);
         this.hasMaxValue = new Some(max);
      }
   }

   public abstract static class BuiltinFloatTag implements NumberTag {
      private final Option hasZero;
      private final Option hasMinValue;
      private final Option hasMaxValue;
      private final Option hasNaN;
      private final Option hasPositiveInfinity;
      private final Option hasNegativeInfinity;

      public boolean finite() {
         return NumberTag.super.finite();
      }

      public boolean infinite() {
         return NumberTag.super.infinite();
      }

      public boolean isFinite(final Object a) {
         return NumberTag.super.isFinite(a);
      }

      public Resolution resolution() {
         return NumberTag.Approximate$.MODULE$;
      }

      public Option hasZero() {
         return this.hasZero;
      }

      public Option hasMinValue() {
         return this.hasMinValue;
      }

      public Option hasMaxValue() {
         return this.hasMaxValue;
      }

      public Option hasNaN() {
         return this.hasNaN;
      }

      public Option hasPositiveInfinity() {
         return this.hasPositiveInfinity;
      }

      public Option hasNegativeInfinity() {
         return this.hasNegativeInfinity;
      }

      public boolean overflows() {
         return false;
      }

      public boolean isSigned() {
         return true;
      }

      public BuiltinFloatTag(final Object zero, final Object min, final Object max, final Object nan, final Object posInf, final Object negInf) {
         NumberTag.$init$(this);
         this.hasZero = new Some(zero);
         this.hasMinValue = new Some(min);
         this.hasMaxValue = new Some(max);
         this.hasNaN = new Some(nan);
         this.hasPositiveInfinity = new Some(posInf);
         this.hasNegativeInfinity = new Some(negInf);
      }
   }

   public static class LargeTag implements NumberTag {
      private final Resolution resolution;
      private final Option hasZero;

      public boolean finite() {
         return NumberTag.super.finite();
      }

      public boolean infinite() {
         return NumberTag.super.infinite();
      }

      public boolean isFinite(final Object a) {
         return NumberTag.super.isFinite(a);
      }

      public Resolution resolution() {
         return this.resolution;
      }

      public Option hasZero() {
         return this.hasZero;
      }

      public Option hasMinValue() {
         return scala.None..MODULE$;
      }

      public Option hasMaxValue() {
         return scala.None..MODULE$;
      }

      public Option hasNaN() {
         return scala.None..MODULE$;
      }

      public Option hasPositiveInfinity() {
         return scala.None..MODULE$;
      }

      public Option hasNegativeInfinity() {
         return scala.None..MODULE$;
      }

      public boolean overflows() {
         return false;
      }

      public boolean isSigned() {
         return true;
      }

      public boolean isInfinite(final Object a) {
         return false;
      }

      public boolean isNaN(final Object a) {
         return false;
      }

      public LargeTag(final Resolution resolution, final Object zero) {
         this.resolution = resolution;
         NumberTag.$init$(this);
         this.hasZero = new Some(zero);
      }
   }

   public static class CustomTag implements NumberTag {
      private final Resolution resolution;
      private final Option hasZero;
      private final Option hasMinValue;
      private final Option hasMaxValue;
      private final boolean overflows;
      private final boolean isSigned;

      public boolean finite() {
         return NumberTag.super.finite();
      }

      public boolean infinite() {
         return NumberTag.super.infinite();
      }

      public boolean isFinite(final Object a) {
         return NumberTag.super.isFinite(a);
      }

      public Resolution resolution() {
         return this.resolution;
      }

      public Option hasZero() {
         return this.hasZero;
      }

      public Option hasMinValue() {
         return this.hasMinValue;
      }

      public Option hasMaxValue() {
         return this.hasMaxValue;
      }

      public boolean overflows() {
         return this.overflows;
      }

      public boolean isSigned() {
         return this.isSigned;
      }

      public Option hasNaN() {
         return scala.None..MODULE$;
      }

      public Option hasPositiveInfinity() {
         return scala.None..MODULE$;
      }

      public Option hasNegativeInfinity() {
         return scala.None..MODULE$;
      }

      public boolean isInfinite(final Object a) {
         return false;
      }

      public boolean isNaN(final Object a) {
         return false;
      }

      public CustomTag(final Resolution resolution, final Option hasZero, final Option hasMinValue, final Option hasMaxValue, final boolean overflows, final boolean isSigned) {
         this.resolution = resolution;
         this.hasZero = hasZero;
         this.hasMinValue = hasMinValue;
         this.hasMaxValue = hasMaxValue;
         this.overflows = overflows;
         this.isSigned = isSigned;
         NumberTag.$init$(this);
      }
   }

   public interface Resolution {
   }
}
