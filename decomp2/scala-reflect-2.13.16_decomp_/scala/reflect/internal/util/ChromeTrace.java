package scala.reflect.internal.util;

import java.io.Closeable;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.IterableFactory;
import scala.collection.Iterator;
import scala.collection.mutable.Stack;
import scala.collection.mutable.Stack.;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011\u0005qAB>}\u0011\u0003\tYAB\u0004\u0002\u0010qD\t!!\u0005\t\u000f\u0005m\u0011\u0001\"\u0001\u0002\u001e\u001d9\u0011qD\u0001\t\n\u0005\u0005baBA\u0013\u0003!%\u0011q\u0005\u0005\b\u00037!A\u0011AA\u0015\u0011%\tY\u0003\u0002b\u0001\n\u000b\ti\u0003\u0003\u0005\u00026\u0011\u0001\u000bQBA\u0018\u0011%\t9\u0004\u0002b\u0001\n\u000b\tI\u0004\u0003\u0005\u0002B\u0011\u0001\u000bQBA\u001e\u0011%\t\u0019\u0005\u0002b\u0001\n\u000b\t)\u0005\u0003\u0005\u0002N\u0011\u0001\u000bQBA$\u0011%\ty\u0005\u0002b\u0001\n\u000b\t\t\u0006\u0003\u0005\u0002Z\u0011\u0001\u000bQBA*\u0011%\tY\u0006\u0002b\u0001\n\u000b\ti\u0006\u0003\u0005\u0002f\u0011\u0001\u000bQBA0\u0011%\t9\u0007\u0002b\u0001\n\u000b\tI\u0007\u0003\u0005\u0002r\u0011\u0001\u000bQBA6\u0011%\t\u0019\b\u0002b\u0001\n\u000b\t)\b\u0003\u0005\u0002~\u0011\u0001\u000bQBA<\u0011%\ty\b\u0002b\u0001\n\u000b\t\t\t\u0003\u0005\u0002\n\u0012\u0001\u000bQBAB\r\u0019\ty\u0001 \u0002\u0002\u001c\"Q\u0011\u0011\u0018\f\u0003\u0002\u0003\u0006I!a/\t\u000f\u0005ma\u0003\"\u0001\u0002L\"I\u0011\u0011\u001b\fC\u0002\u0013%\u00111\u001b\u0005\t\u0003G4\u0002\u0015!\u0003\u0002V\"I\u0011Q\u001d\fC\u0002\u0013%\u0011q\u001d\u0005\t\u0005S4\u0002\u0015!\u0003\u0002j\"I!1\u001e\fC\u0002\u0013%!Q\u001e\u0005\t\u0007\u00071\u0002\u0015!\u0003\u0003p\"I1Q\u0001\fC\u0002\u0013%1q\u0001\u0005\t\u0007\u00131\u0002\u0015!\u0003\u0003v\"911\u0002\f\u0005B\r5\u0001bBB\b-\u0011\u00051\u0011\u0003\u0005\n\u0007[1\u0012\u0013!C\u0001\u0007_A\u0011ba\r\u0017#\u0003%\taa\f\t\u000f\rUb\u0003\"\u0003\u00048!911\b\f\u0005\u0002\ru\u0002bBB'-\u0011\u00051q\n\u0005\n\u0007;2\u0012\u0013!C\u0001\u0007_A\u0011ba\u0018\u0017#\u0003%\taa\f\t\u000f\r\u0005d\u0003\"\u0001\u0004d!I1Q\u000e\f\u0012\u0002\u0013\u00051q\u0006\u0005\n\u0007_2\u0012\u0013!C\u0001\u0007_Aqa!\u001d\u0017\t\u0013\u0019\u0019\bC\u0004\u0004(Y!Ia!!\t\u000f\r\re\u0003\"\u0003\u0004\u0006\"911\u0012\f\u0005\n\r5eaBA\u007f-\u0005\u0005\u0012q \u0005\b\u00037\tD\u0011\u0001B\u0001\r\u0019\u0011)A\u0006!\u0003\b!Q!qE\u001a\u0003\u0012\u0004%\tA!\u000b\t\u0015\tE2G!a\u0001\n\u0003\u0011\u0019\u0004\u0003\u0006\u0003@M\u0012\t\u0012)Q\u0005\u0005WAq!a\u00074\t\u0003\u0011\t\u0005C\u0005\u0003HM\n\t\u0011\"\u0001\u0003J!I!QJ\u001a\u0012\u0002\u0013\u0005!q\n\u0005\n\u0005C\u001a\u0014\u0011!C!\u0005GB\u0011Ba\u001b4\u0003\u0003%\tA!\u001c\t\u0013\tU4'!A\u0005\u0002\t]\u0004\"\u0003BAg\u0005\u0005I\u0011\tBB\u0011%\u0011iiMA\u0001\n\u0003\u0011y\tC\u0005\u0003\u0014N\n\t\u0011\"\u0011\u0003\u0016\"I!\u0011T\u001a\u0002\u0002\u0013\u0005#1\u0014\u0005\n\u0005;\u001b\u0014\u0011!C!\u0005?C\u0011B!)4\u0003\u0003%\tEa)\b\u0013\r=e#!A\t\u0002\rEe!\u0003B\u0003-\u0005\u0005\t\u0012ABJ\u0011\u001d\tY\u0002\u0012C\u0001\u0007KC\u0011B!(E\u0003\u0003%)Ea(\t\u0013\r\u001dF)!A\u0005\u0002\u000e%\u0006\"CBW\t\u0006\u0005I\u0011QBX\r\u0019\u00119K\u0006!\u0003*\"Q!qE%\u0003\u0012\u0004%\tA!\u000b\t\u0015\tE\u0012J!a\u0001\n\u0003\u0011Y\u000b\u0003\u0006\u0003@%\u0013\t\u0012)Q\u0005\u0005WAq!a\u0007J\t\u0003\u0011y\u000bC\u0005\u0003H%\u000b\t\u0011\"\u0001\u00036\"I!QJ%\u0012\u0002\u0013\u0005!q\n\u0005\n\u0005CJ\u0015\u0011!C!\u0005GB\u0011Ba\u001bJ\u0003\u0003%\tA!\u001c\t\u0013\tU\u0014*!A\u0005\u0002\te\u0006\"\u0003BA\u0013\u0006\u0005I\u0011\tBB\u0011%\u0011i)SA\u0001\n\u0003\u0011i\fC\u0005\u0003\u0014&\u000b\t\u0011\"\u0011\u0003B\"I!\u0011T%\u0002\u0002\u0013\u0005#1\u0014\u0005\n\u0005;K\u0015\u0011!C!\u0005?C\u0011B!)J\u0003\u0003%\tE!2\b\u0013\rmf#!A\t\u0002\ruf!\u0003BT-\u0005\u0005\t\u0012AB`\u0011\u001d\tYB\u0017C\u0001\u0007\u0007D\u0011B!([\u0003\u0003%)Ea(\t\u0013\r\u001d&,!A\u0005\u0002\u000e\u0015\u0007\"CBW5\u0006\u0005I\u0011QBe\u000f\u001d\u0019iM\u0006EA\u0005?4qA!7\u0017\u0011\u0003\u0013Y\u000eC\u0004\u0002\u001c\u0001$\tA!8\t\u0013\t\u0005\u0004-!A\u0005B\t\r\u0004\"\u0003B6A\u0006\u0005I\u0011\u0001B7\u0011%\u0011)\bYA\u0001\n\u0003\u0011\t\u000fC\u0005\u0003\u0002\u0002\f\t\u0011\"\u0011\u0003\u0004\"I!Q\u00121\u0002\u0002\u0013\u0005!Q\u001d\u0005\n\u00053\u0003\u0017\u0011!C!\u00057C\u0011B!(a\u0003\u0003%\tEa(\b\u000f\r=g\u0003#!\u0003P\u001a9!\u0011\u001a\f\t\u0002\n-\u0007bBA\u000eU\u0012\u0005!Q\u001a\u0005\n\u0005CR\u0017\u0011!C!\u0005GB\u0011Ba\u001bk\u0003\u0003%\tA!\u001c\t\u0013\tU$.!A\u0005\u0002\tE\u0007\"\u0003BAU\u0006\u0005I\u0011\tBB\u0011%\u0011iI[A\u0001\n\u0003\u0011)\u000eC\u0005\u0003\u001a*\f\t\u0011\"\u0011\u0003\u001c\"I!Q\u00146\u0002\u0002\u0013\u0005#q\u0014\u0005\b\u0007#4B\u0011BBj\u0011\u001d\u0019YN\u0006C\u0005\u0007;Dqaa;\u0017\t\u0013\u0019i\u000fC\u0004\u0004tZ!Ia!\u0004\t\u000f\rUh\u0003\"\u0003\u0004\u000e!91q\u001f\f\u0005\n\r5\u0001bBB}-\u0011%1Q\u0002\u0005\b\u0007w4B\u0011BB\u007f\u0003-\u0019\u0005N]8nKR\u0013\u0018mY3\u000b\u0005ut\u0018\u0001B;uS2T1a`A\u0001\u0003!Ig\u000e^3s]\u0006d'\u0002BA\u0002\u0003\u000b\tqA]3gY\u0016\u001cGO\u0003\u0002\u0002\b\u0005)1oY1mC\u000e\u0001\u0001cAA\u0007\u00035\tAPA\u0006DQJ|W.\u001a+sC\u000e,7cA\u0001\u0002\u0014A!\u0011QCA\f\u001b\t\t)!\u0003\u0003\u0002\u001a\u0005\u0015!AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003\u0017\t\u0011\"\u0012<f]R$\u0016\u0010]3\u0011\u0007\u0005\rB!D\u0001\u0002\u0005%)e/\u001a8u)f\u0004XmE\u0002\u0005\u0003'!\"!!\t\u0002\u000bM#\u0018M\u001d;\u0016\u0005\u0005=rBAA\u0019C\t\t\u0019$A\u0001C\u0003\u0019\u0019F/\u0019:uA\u00059\u0011J\\:uC:$XCAA\u001e\u001f\t\ti$\t\u0002\u0002@\u0005\t\u0011*\u0001\u0005J]N$\u0018M\u001c;!\u0003\r)e\u000eZ\u000b\u0003\u0003\u000fz!!!\u0013\"\u0005\u0005-\u0013!A#\u0002\t\u0015sG\rI\u0001\t\u0007>l\u0007\u000f\\3uKV\u0011\u00111K\b\u0003\u0003+\n#!a\u0016\u0002\u0003a\u000b\u0011bQ8na2,G/\u001a\u0011\u0002\u000f\r{WO\u001c;feV\u0011\u0011qL\b\u0003\u0003C\n#!a\u0019\u0002\u0003\r\u000b\u0001bQ8v]R,'\u000fI\u0001\u000b\u0003NLhnY*uCJ$XCAA6\u001f\t\ti'\t\u0002\u0002p\u0005\t!-A\u0006Bgft7m\u0015;beR\u0004\u0013\u0001D!ts:\u001c\u0017J\\:uC:$XCAA<\u001f\t\tI(\t\u0002\u0002|\u0005\ta.A\u0007Bgft7-\u00138ti\u0006tG\u000fI\u0001\t\u0003NLhnY#oIV\u0011\u00111Q\b\u0003\u0003\u000b\u000b#!a\"\u0002\u0003\u0015\f\u0011\"Q:z]\u000e,e\u000e\u001a\u0011)\u0007\u0011\ti\t\u0005\u0003\u0002\u0010\u0006UUBAAI\u0015\u0011\t\u0019*!\u0002\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u0018\u0006E%AB;okN,G\rK\u0002\u0004\u0003\u001b\u001bRAFAO\u0003[\u0003B!a(\u0002*6\u0011\u0011\u0011\u0015\u0006\u0005\u0003G\u000b)+\u0001\u0003mC:<'BAAT\u0003\u0011Q\u0017M^1\n\t\u0005-\u0016\u0011\u0015\u0002\u0007\u001f\nTWm\u0019;\u0011\t\u0005=\u0016QW\u0007\u0003\u0003cSA!a-\u0002&\u0006\u0011\u0011n\\\u0005\u0005\u0003o\u000b\tLA\u0005DY>\u001cX-\u00192mK\u0006\ta\r\u0005\u0003\u0002>\u0006\u001dWBAA`\u0015\u0011\t\t-a1\u0002\t\u0019LG.\u001a\u0006\u0005\u0003\u000b\f)+A\u0002oS>LA!!3\u0002@\n!\u0001+\u0019;i)\u0011\ti-a4\u0011\u0007\u00055a\u0003C\u0004\u0002:b\u0001\r!a/\u0002\u0017Q\u0014\u0018mY3Xe&$XM]\u000b\u0003\u0003+\u0004B!a6\u0002^:!\u0011QBAm\u0013\r\tY\u000e`\u0001\n\r&dW-\u0016;jYNLA!a8\u0002b\nQA*\u001b8f/JLG/\u001a:\u000b\u0007\u0005mG0\u0001\u0007ue\u0006\u001cWm\u0016:ji\u0016\u0014\b%A\u0004d_:$X\r\u001f;\u0016\u0005\u0005%\bCBAv\u0003k\fI0\u0004\u0002\u0002n*!\u0011q^Ay\u0003\u001diW\u000f^1cY\u0016TA!a=\u0002\u0006\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005]\u0018Q\u001e\u0002\u0006'R\f7m\u001b\t\u0004\u0003w\fT\"\u0001\f\u0003\u0017)\u001bxN\\\"p]R,\u0007\u0010^\n\u0004c\u0005MACAA}S\u0015\t4'\u00136a\u00051\t%O]1z\u0007>tG/\u001a=u'\u001d\u0019\u0014\u0011 B\u0005\u0005\u001f\u0001B!!\u0006\u0003\f%!!QBA\u0003\u0005\u001d\u0001&o\u001c3vGR\u0004BA!\u0005\u0003\"9!!1\u0003B\u000f\u001d\u0011\u0011)Ba\u0007\u000e\u0005\t]!\u0002\u0002B\r\u0003\u0013\ta\u0001\u0010:p_Rt\u0014BAA\u0004\u0013\u0011\u0011y\"!\u0002\u0002\u000fA\f7m[1hK&!!1\u0005B\u0013\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\u0011\u0011y\"!\u0002\u0002\u000b\u0019L'o\u001d;\u0016\u0005\t-\u0002\u0003BA\u000b\u0005[IAAa\f\u0002\u0006\t9!i\\8mK\u0006t\u0017!\u00034jeN$x\fJ3r)\u0011\u0011)Da\u000f\u0011\t\u0005U!qG\u0005\u0005\u0005s\t)A\u0001\u0003V]&$\b\"\u0003B\u001fk\u0005\u0005\t\u0019\u0001B\u0016\u0003\rAH%M\u0001\u0007M&\u00148\u000f\u001e\u0011\u0015\t\t\r#Q\t\t\u0004\u0003w\u001c\u0004b\u0002B\u0014o\u0001\u0007!1F\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003\u0003D\t-\u0003\"\u0003B\u0014qA\u0005\t\u0019\u0001B\u0016\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"A!\u0015+\t\t-\"1K\u0016\u0003\u0005+\u0002BAa\u0016\u0003^5\u0011!\u0011\f\u0006\u0005\u00057\n\t*A\u0005v]\u000eDWmY6fI&!!q\fB-\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\t\u0015\u0004\u0003BAP\u0005OJAA!\u001b\u0002\"\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"Aa\u001c\u0011\t\u0005U!\u0011O\u0005\u0005\u0005g\n)AA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0003z\t}\u0004\u0003BA\u000b\u0005wJAA! \u0002\u0006\t\u0019\u0011I\\=\t\u0013\tuB(!AA\u0002\t=\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\t\u0015\u0005C\u0002BD\u0005\u0013\u0013I(\u0004\u0002\u0002r&!!1RAy\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\t-\"\u0011\u0013\u0005\n\u0005{q\u0014\u0011!a\u0001\u0005s\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!Q\rBL\u0011%\u0011idPA\u0001\u0002\u0004\u0011y'\u0001\u0005iCND7i\u001c3f)\t\u0011y'\u0001\u0005u_N#(/\u001b8h)\t\u0011)'\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0005W\u0011)\u000bC\u0005\u0003>\t\u000b\t\u00111\u0001\u0003z\tiqJ\u00196fGR\u001cuN\u001c;fqR\u001cr!SA}\u0005\u0013\u0011y\u0001\u0006\u0003\u00036\t5\u0006\"\u0003B\u001f\u0017\u0006\u0005\t\u0019\u0001B\u0016)\u0011\u0011\tLa-\u0011\u0007\u0005m\u0018\nC\u0004\u0003(5\u0003\rAa\u000b\u0015\t\tE&q\u0017\u0005\n\u0005Oq\u0005\u0013!a\u0001\u0005W!BA!\u001f\u0003<\"I!Q\b*\u0002\u0002\u0003\u0007!q\u000e\u000b\u0005\u0005W\u0011y\fC\u0005\u0003>Q\u000b\t\u00111\u0001\u0003zQ!!Q\rBb\u0011%\u0011i$VA\u0001\u0002\u0004\u0011y\u0007\u0006\u0003\u0003,\t\u001d\u0007\"\u0003B\u001f1\u0006\u0005\t\u0019\u0001B=\u0005)!v\u000e]\"p]R,\u0007\u0010^\n\bU\u0006e(\u0011\u0002B\b)\t\u0011y\rE\u0002\u0002|*$BA!\u001f\u0003T\"I!Q\b8\u0002\u0002\u0003\u0007!q\u000e\u000b\u0005\u0005W\u00119\u000eC\u0005\u0003>A\f\t\u00111\u0001\u0003z\taa+\u00197vK\u000e{g\u000e^3yiN9\u0001-!?\u0003\n\t=AC\u0001Bp!\r\tY\u0010\u0019\u000b\u0005\u0005s\u0012\u0019\u000fC\u0005\u0003>\u0011\f\t\u00111\u0001\u0003pQ!!1\u0006Bt\u0011%\u0011iDZA\u0001\u0002\u0004\u0011I(\u0001\u0005d_:$X\r\u001f;!\u0003!!\u0018\u000eZ\"bG\",WC\u0001Bx!\u0019\tyJ!=\u0003v&!!1_AQ\u0005-!\u0006N]3bI2{7-\u00197\u0011\t\t](q \b\u0005\u0005s\u0014Y\u0010\u0005\u0003\u0003\u0016\u0005\u0015\u0011\u0002\u0002B\u007f\u0003\u000b\ta\u0001\u0015:fI\u00164\u0017\u0002\u0002B5\u0007\u0003QAA!@\u0002\u0006\u0005IA/\u001b3DC\u000eDW\rI\u0001\u0004a&$WC\u0001B{\u0003\u0011\u0001\u0018\u000e\u001a\u0011\u0002\u000b\rdwn]3\u0015\u0005\tU\u0012A\u0005;sC\u000e,G)\u001e:bi&|g.\u0012<f]R$BB!\u000e\u0004\u0014\r]1\u0011EB\u0013\u0007SAqa!\u0006#\u0001\u0004\u0011)0\u0001\u0003oC6,\u0007bBB\rE\u0001\u000711D\u0001\u000bgR\f'\u000f\u001e(b]>\u001c\b\u0003BA\u000b\u0007;IAaa\b\u0002\u0006\t!Aj\u001c8h\u0011\u001d\u0019\u0019C\ta\u0001\u00077\tQ\u0002Z;sCRLwN\u001c(b]>\u001c\b\"CB\u0014EA\u0005\t\u0019\u0001B{\u0003\r!\u0018\u000e\u001a\u0005\n\u0007W\u0011\u0003\u0013!a\u0001\u0005k\f\u0011\u0002]5e'V4g-\u001b=\u00029Q\u0014\u0018mY3EkJ\fG/[8o\u000bZ,g\u000e\u001e\u0013eK\u001a\fW\u000f\u001c;%iU\u00111\u0011\u0007\u0016\u0005\u0005k\u0014\u0019&\u0001\u000fue\u0006\u001cW\rR;sCRLwN\\#wK:$H\u0005Z3gCVdG\u000fJ\u001b\u0002\u0011]\u0014\u0018\u000e^3QS\u0012$BA!\u000e\u0004:!911F\u0013A\u0002\tU\u0018!\u0005;sC\u000e,7i\\;oi\u0016\u0014XI^3oiRQ!QGB \u0007\u0003\u001a)e!\u0013\t\u000f\rUa\u00051\u0001\u0003v\"911\t\u0014A\u0002\tU\u0018aC2pk:$XM\u001d(b[\u0016Dqaa\u0012'\u0001\u0004\u0019Y\"A\u0003d_VtG\u000fC\u0004\u0004L\u0019\u0002\rAa\u000b\u0002\u0017A\u0014xnY3tg^KG-Z\u0001\u0018iJ\f7-\u001a#ve\u0006$\u0018n\u001c8Fm\u0016tGo\u0015;beR$\"B!\u000e\u0004R\rU3qKB.\u0011\u001d\u0019\u0019f\na\u0001\u0005k\f1aY1u\u0011\u001d\u0019)b\na\u0001\u0005kD\u0011b!\u0017(!\u0003\u0005\rA!>\u0002\r\r|Gn\\;s\u0011%\u0019Yc\nI\u0001\u0002\u0004\u0011)0A\u0011ue\u0006\u001cW\rR;sCRLwN\\#wK:$8\u000b^1si\u0012\"WMZ1vYR$3'A\u0011ue\u0006\u001cW\rR;sCRLwN\\#wK:$8\u000b^1si\u0012\"WMZ1vYR$C'A\u000bue\u0006\u001cW\rR;sCRLwN\\#wK:$XI\u001c3\u0015\u0015\tU2QMB4\u0007S\u001aY\u0007C\u0004\u0004T)\u0002\rA!>\t\u000f\rU!\u00061\u0001\u0003v\"I1\u0011\f\u0016\u0011\u0002\u0003\u0007!Q\u001f\u0005\n\u0007WQ\u0003\u0013!a\u0001\u0005k\fq\u0004\u001e:bG\u0016$UO]1uS>tWI^3oi\u0016sG\r\n3fM\u0006,H\u000e\u001e\u00134\u0003}!(/Y2f\tV\u0014\u0018\r^5p]\u00163XM\u001c;F]\u0012$C-\u001a4bk2$H\u0005N\u0001\u001biJ\f7-\u001a#ve\u0006$\u0018n\u001c8Fm\u0016tGo\u0015;beR,e\u000e\u001a\u000b\r\u0005k\u0019)h!\u001f\u0004|\ru4q\u0010\u0005\b\u0007oj\u0003\u0019\u0001B{\u0003%)g/\u001a8u)f\u0004X\rC\u0004\u0004T5\u0002\rA!>\t\u000f\rUQ\u00061\u0001\u0003v\"91\u0011L\u0017A\u0002\tU\bbBB\u0016[\u0001\u0007!Q\u001f\u000b\u0003\u0005k\fQB\\1o_N$v.T5de>\u001cH\u0003BB\u000e\u0007\u000fCqa!#0\u0001\u0004\u0019Y\"A\u0001u\u0003%i\u0017n\u0019:p)&lW\r\u0006\u0002\u0004\u001c\u0005a\u0011I\u001d:bs\u000e{g\u000e^3yiB\u0019\u00111 #\u0014\u000b\u0011\u001b)j!)\u0011\u0011\r]5Q\u0014B\u0016\u0005\u0007j!a!'\u000b\t\rm\u0015QA\u0001\beVtG/[7f\u0013\u0011\u0019yj!'\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u00020\u000e\r\u0016\u0002\u0002B\u0012\u0003c#\"a!%\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\t\r31\u0016\u0005\b\u0005O9\u0005\u0019\u0001B\u0016\u0003\u001d)h.\u00199qYf$Ba!-\u00048B1\u0011QCBZ\u0005WIAa!.\u0002\u0006\t1q\n\u001d;j_:D\u0011b!/I\u0003\u0003\u0005\rAa\u0011\u0002\u0007a$\u0003'A\u0007PE*,7\r^\"p]R,\u0007\u0010\u001e\t\u0004\u0003wT6#\u0002.\u0004B\u000e\u0005\u0006\u0003CBL\u0007;\u0013YC!-\u0015\u0005\ruF\u0003\u0002BY\u0007\u000fDqAa\n^\u0001\u0004\u0011Y\u0003\u0006\u0003\u00042\u000e-\u0007\"CB]=\u0006\u0005\t\u0019\u0001BY\u000311\u0016\r\\;f\u0007>tG/\u001a=u\u0003)!v\u000e]\"p]R,\u0007\u0010^\u0001\u0004gR\u0014HC\u0002B\u001b\u0007+\u001c9\u000eC\u0004\u0004\u0016M\u0004\rA!>\t\u000f\re7\u000f1\u0001\u0003v\u0006)a/\u00197vK\u0006!1\u000f\u001e:3))\u0011)da8\u0004b\u000e\r8q\u001d\u0005\b\u0007+!\b\u0019\u0001B{\u0011\u001d\u0019I\u000e\u001ea\u0001\u0005kDqa!:u\u0001\u0004\u0011)0A\bwC2,XmQ8oi&tW/\u001a32\u0011\u001d\u0019I\u000f\u001ea\u0001\u0005k\fqB^1mk\u0016\u001cuN\u001c;j]V,GMM\u0001\u0004Y:<GC\u0002B\u001b\u0007_\u001c\t\u0010C\u0004\u0004\u0016U\u0004\rA!>\t\u000f\reW\u000f1\u0001\u0004\u001c\u0005AqN\u00196Ti\u0006\u0014H/\u0001\u0004pE*,e\u000eZ\u0001\tCJ\u00148\u000b^1si\u00061\u0011M\u001d:F]\u0012\f1A\u001a7e)\u0011\u0011)da@\t\u000f\rU!\u00101\u0001\u0003v\u0002"
)
public final class ChromeTrace implements Closeable {
   private volatile ArrayContext$ ArrayContext$module;
   private volatile ObjectContext$ ObjectContext$module;
   private volatile ValueContext$ ValueContext$module;
   private volatile TopContext$ TopContext$module;
   private final FileUtils.LineWriter traceWriter;
   private final Stack context;
   private final ThreadLocal tidCache;
   private final String pid;

   public ArrayContext$ ArrayContext() {
      if (this.ArrayContext$module == null) {
         this.ArrayContext$lzycompute$1();
      }

      return this.ArrayContext$module;
   }

   public ObjectContext$ ObjectContext() {
      if (this.ObjectContext$module == null) {
         this.ObjectContext$lzycompute$1();
      }

      return this.ObjectContext$module;
   }

   public ValueContext$ ValueContext() {
      if (this.ValueContext$module == null) {
         this.ValueContext$lzycompute$1();
      }

      return this.ValueContext$module;
   }

   public TopContext$ TopContext() {
      if (this.TopContext$module == null) {
         this.TopContext$lzycompute$1();
      }

      return this.TopContext$module;
   }

   private FileUtils.LineWriter traceWriter() {
      return this.traceWriter;
   }

   private Stack context() {
      return this.context;
   }

   private ThreadLocal tidCache() {
      return this.tidCache;
   }

   private String pid() {
      return this.pid;
   }

   public void close() {
      this.arrEnd();
      this.objEnd();
      this.context().pop();
      this.tidCache().remove();
      this.traceWriter().close();
   }

   public void traceDurationEvent(final String name, final long startNanos, final long durationNanos, final String tid, final String pidSuffix) {
      long durationMicros = TimeUnit.NANOSECONDS.toMicros(durationNanos);
      long startMicros = TimeUnit.NANOSECONDS.toMicros(startNanos);
      this.objStart();
      this.str("cat", "scalac");
      this.str("name", name);
      this.str("ph", "X");
      this.str("tid", tid);
      this.writePid(pidSuffix);
      this.lng("ts", startMicros);
      this.lng("dur", durationMicros);
      this.objEnd();
      this.traceWriter().newLine();
   }

   public String traceDurationEvent$default$4() {
      return this.tid();
   }

   public String traceDurationEvent$default$5() {
      return "";
   }

   private void writePid(final String pidSuffix) {
      String var2 = "";
      if (pidSuffix != null) {
         if (pidSuffix.equals(var2)) {
            this.str("pid", this.pid());
            return;
         }
      }

      this.str2("pid", this.pid(), "-", pidSuffix);
   }

   public void traceCounterEvent(final String name, final String counterName, final long count, final boolean processWide) {
      this.objStart();
      this.str("cat", "scalac");
      this.str("name", name);
      this.str("ph", "C");
      this.str("tid", this.tid());
      this.writePid(processWide ? "" : this.tid());
      this.lng("ts", this.microTime());
      this.fld("args");
      this.objStart();
      this.lng(counterName, count);
      this.objEnd();
      this.objEnd();
      this.traceWriter().newLine();
   }

   public void traceDurationEventStart(final String cat, final String name, final String colour, final String pidSuffix) {
      this.traceDurationEventStartEnd("B", cat, name, colour, pidSuffix);
   }

   public String traceDurationEventStart$default$3() {
      return "";
   }

   public String traceDurationEventStart$default$4() {
      return this.tid();
   }

   public void traceDurationEventEnd(final String cat, final String name, final String colour, final String pidSuffix) {
      this.traceDurationEventStartEnd("E", cat, name, colour, pidSuffix);
   }

   public String traceDurationEventEnd$default$3() {
      return "";
   }

   public String traceDurationEventEnd$default$4() {
      return this.tid();
   }

   private void traceDurationEventStartEnd(final String eventType, final String cat, final String name, final String colour, final String pidSuffix) {
      label12: {
         this.objStart();
         this.str("cat", cat);
         this.str("name", name);
         this.str("ph", eventType);
         this.writePid(pidSuffix);
         this.str("tid", this.tid());
         this.lng("ts", this.microTime());
         String var6 = "";
         if (colour != null) {
            if (colour.equals(var6)) {
               break label12;
            }
         }

         this.str("cname", colour);
      }

      this.objEnd();
      this.traceWriter().newLine();
   }

   private String tid() {
      return (String)this.tidCache().get();
   }

   private long nanosToMicros(final long t) {
      return TimeUnit.NANOSECONDS.toMicros(t);
   }

   private long microTime() {
      long nanosToMicros_t = System.nanoTime();
      return TimeUnit.NANOSECONDS.toMicros(nanosToMicros_t);
   }

   private void str(final String name, final String value) {
      this.fld(name);
      this.traceWriter().write("\"");
      this.traceWriter().write(value);
      this.traceWriter().write("\"");
   }

   private void str2(final String name, final String value, final String valueContinued1, final String valueContinued2) {
      this.fld(name);
      this.traceWriter().write("\"");
      this.traceWriter().write(value);
      this.traceWriter().write(valueContinued1);
      this.traceWriter().write(valueContinued2);
      this.traceWriter().write("\"");
   }

   private void lng(final String name, final long value) {
      this.fld(name);
      this.traceWriter().write(String.valueOf(value));
      this.traceWriter().write("");
   }

   private void objStart() {
      Stack var10000 = this.context();
      if (var10000 == null) {
         throw null;
      } else {
         JsonContext var1 = (JsonContext)var10000.head();
         if (var1 instanceof ArrayContext) {
            ArrayContext var2 = (ArrayContext)var1;
            if (var2.first()) {
               var2.first_$eq(false);
            } else {
               this.traceWriter().write(",");
            }
         }

         this.context().push(new ObjectContext(true));
         this.traceWriter().write("{");
      }
   }

   private void objEnd() {
      this.traceWriter().write("}");
      this.context().pop();
   }

   private void arrStart() {
      this.traceWriter().write("[");
      this.context().push(new ArrayContext(true));
   }

   private void arrEnd() {
      this.traceWriter().write("]");
      this.context().pop();
   }

   private void fld(final String name) {
      Stack var10000 = this.context();
      if (var10000 == null) {
         throw null;
      } else {
         JsonContext topContext = (JsonContext)var10000.head();
         if (topContext instanceof ObjectContext) {
            ObjectContext var3 = (ObjectContext)topContext;
            if (var3.first()) {
               var3.first_$eq(false);
            } else {
               this.traceWriter().write(",");
            }

            this.traceWriter().write("\"");
            this.traceWriter().write(name);
            this.traceWriter().write("\"");
            this.traceWriter().write(":");
         } else {
            throw new IllegalStateException((new StringBuilder(15)).append("Wrong context: ").append(topContext).toString());
         }
      }
   }

   private final void ArrayContext$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ArrayContext$module == null) {
            this.ArrayContext$module = new ArrayContext$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ObjectContext$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ObjectContext$module == null) {
            this.ObjectContext$module = new ObjectContext$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ValueContext$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ValueContext$module == null) {
            this.ValueContext$module = new ValueContext$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TopContext$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TopContext$module == null) {
            this.TopContext$module = new TopContext$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   // $FF: synthetic method
   private static final String liftedTree1$1() {
      try {
         Object runtimeMXBean = Class.forName("java.lang.management.ManagementFactory").getMethod("getRuntimeMXBean").invoke((Object)null);
         return ((String)Class.forName("java.lang.management.RuntimeMXBean").getMethod("getName").invoke(runtimeMXBean)).replaceAll("@.*", "");
      } catch (Throwable var1) {
         return "0";
      }
   }

   public ChromeTrace(final Path f) {
      FileUtils$ var10001 = FileUtils$.MODULE$;
      FileUtils$ var10003 = FileUtils$.MODULE$;
      Charset var2 = StandardCharsets.UTF_8;
      OpenOption[] var10004 = FileUtils$.MODULE$.newAsyncBufferedWriter$default$3();
      FileUtils$ var10005 = FileUtils$.MODULE$;
      this.traceWriter = var10001.newAsyncBufferedWriter(f, var2, var10004, false);
      this.context = (Stack)IterableFactory.apply$(.MODULE$, scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new JsonContext[]{this.TopContext()}));
      this.tidCache = new ThreadLocal() {
         public String initialValue() {
            return scala.collection.StringOps..MODULE$.format$extension("%05d", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{Thread.currentThread().getId()}));
         }
      };
      this.objStart();
      this.fld("traceEvents");
      this.context().push(this.ValueContext());
      this.arrStart();
      this.traceWriter().newLine();
      this.pid = liftedTree1$1();
   }

   private static class EventType$ {
      public static final EventType$ MODULE$ = new EventType$();

      public final String Start() {
         return "B";
      }

      public final String Instant() {
         return "I";
      }

      public final String End() {
         return "E";
      }

      public final String Complete() {
         return "X";
      }

      public final String Counter() {
         return "C";
      }

      public final String AsyncStart() {
         return "b";
      }

      public final String AsyncInstant() {
         return "n";
      }

      public final String AsyncEnd() {
         return "e";
      }

      public EventType$() {
      }
   }

   public abstract class JsonContext {
      // $FF: synthetic field
      public final ChromeTrace $outer;

      // $FF: synthetic method
      public ChromeTrace scala$reflect$internal$util$ChromeTrace$JsonContext$$$outer() {
         return this.$outer;
      }

      public JsonContext() {
         if (ChromeTrace.this == null) {
            throw null;
         } else {
            this.$outer = ChromeTrace.this;
            super();
         }
      }
   }

   public class ArrayContext extends JsonContext implements Product, Serializable {
      private boolean first;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public boolean first() {
         return this.first;
      }

      public void first_$eq(final boolean x$1) {
         this.first = x$1;
      }

      public ArrayContext copy(final boolean first) {
         return this.scala$reflect$internal$util$ChromeTrace$ArrayContext$$$outer().new ArrayContext(first);
      }

      public boolean copy$default$1() {
         return this.first();
      }

      public String productPrefix() {
         return "ArrayContext";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.first();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ArrayContext;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "first";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.first() ? 1231 : 1237);
         int finalizeHash_length = 1;
         return Statics.avalanche(var1 ^ finalizeHash_length);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof ArrayContext && ((ArrayContext)x$1).scala$reflect$internal$util$ChromeTrace$ArrayContext$$$outer() == this.scala$reflect$internal$util$ChromeTrace$ArrayContext$$$outer()) {
               ArrayContext var2 = (ArrayContext)x$1;
               if (this.first() == var2.first() && var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public ChromeTrace scala$reflect$internal$util$ChromeTrace$ArrayContext$$$outer() {
         return this.$outer;
      }

      public ArrayContext(final boolean first) {
         this.first = first;
         super();
      }
   }

   public class ArrayContext$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final ChromeTrace $outer;

      public final String toString() {
         return "ArrayContext";
      }

      public ArrayContext apply(final boolean first) {
         return this.$outer.new ArrayContext(first);
      }

      public Option unapply(final ArrayContext x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.first()));
      }

      public ArrayContext$() {
         if (ChromeTrace.this == null) {
            throw null;
         } else {
            this.$outer = ChromeTrace.this;
            super();
         }
      }
   }

   public class ObjectContext extends JsonContext implements Product, Serializable {
      private boolean first;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public boolean first() {
         return this.first;
      }

      public void first_$eq(final boolean x$1) {
         this.first = x$1;
      }

      public ObjectContext copy(final boolean first) {
         return this.scala$reflect$internal$util$ChromeTrace$ObjectContext$$$outer().new ObjectContext(first);
      }

      public boolean copy$default$1() {
         return this.first();
      }

      public String productPrefix() {
         return "ObjectContext";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.first();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ObjectContext;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "first";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.first() ? 1231 : 1237);
         int finalizeHash_length = 1;
         return Statics.avalanche(var1 ^ finalizeHash_length);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof ObjectContext && ((ObjectContext)x$1).scala$reflect$internal$util$ChromeTrace$ObjectContext$$$outer() == this.scala$reflect$internal$util$ChromeTrace$ObjectContext$$$outer()) {
               ObjectContext var2 = (ObjectContext)x$1;
               if (this.first() == var2.first() && var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public ChromeTrace scala$reflect$internal$util$ChromeTrace$ObjectContext$$$outer() {
         return this.$outer;
      }

      public ObjectContext(final boolean first) {
         this.first = first;
         super();
      }
   }

   public class ObjectContext$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final ChromeTrace $outer;

      public final String toString() {
         return "ObjectContext";
      }

      public ObjectContext apply(final boolean first) {
         return this.$outer.new ObjectContext(first);
      }

      public Option unapply(final ObjectContext x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.first()));
      }

      public ObjectContext$() {
         if (ChromeTrace.this == null) {
            throw null;
         } else {
            this.$outer = ChromeTrace.this;
            super();
         }
      }
   }

   public class ValueContext$ extends JsonContext implements Product, Serializable {
      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "ValueContext";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ValueContext$;
      }

      public int hashCode() {
         return 1784561438;
      }

      public String toString() {
         return "ValueContext";
      }
   }

   public class TopContext$ extends JsonContext implements Product, Serializable {
      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "TopContext";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof TopContext$;
      }

      public int hashCode() {
         return -2143768902;
      }

      public String toString() {
         return "TopContext";
      }
   }
}
