package org.apache.spark.deploy.master;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rhaB9s!\u0003\r\n#`\u0004\t\u0003C\u0011\b\u0012\u0001:\u0002$\u00199\u0011O\u001dE\u0001e\u0006\u001d\u0002bBA\u001c\u0005\u0011\u0005\u0011\u0011H\u0004\b\u0003w\u0011\u0001\u0012QA\u001f\r\u001d\t\tE\u0001EA\u0003\u0007Bq!a\u000e\u0006\t\u0003\tY\u0005C\u0005\u0002N\u0015\t\t\u0011\"\u0011\u0002P!I\u0011QL\u0003\u0002\u0002\u0013\u0005\u0011q\f\u0005\n\u0003O*\u0011\u0011!C\u0001\u0003SB\u0011\"!\u001e\u0006\u0003\u0003%\t%a\u001e\t\u0013\u0005\u0015U!!A\u0005\u0002\u0005\u001d\u0005\"CAI\u000b\u0005\u0005I\u0011IAJ\u0011%\t)*BA\u0001\n\u0003\n9\nC\u0005\u0002\u001a\u0016\t\t\u0011\"\u0003\u0002\u001c\u001e9\u00111\u0015\u0002\t\u0002\u0006\u0015faBAT\u0005!\u0005\u0015\u0011\u0016\u0005\b\u0003o\u0001B\u0011AAV\u0011%\ti\u0005EA\u0001\n\u0003\ny\u0005C\u0005\u0002^A\t\t\u0011\"\u0001\u0002`!I\u0011q\r\t\u0002\u0002\u0013\u0005\u0011Q\u0016\u0005\n\u0003k\u0002\u0012\u0011!C!\u0003oB\u0011\"!\"\u0011\u0003\u0003%\t!!-\t\u0013\u0005E\u0005#!A\u0005B\u0005M\u0005\"CAK!\u0005\u0005I\u0011IAL\u0011%\tI\nEA\u0001\n\u0013\tYjB\u0004\u00026\nA\t)a.\u0007\u000f\u0005e&\u0001#!\u0002<\"9\u0011qG\u000e\u0005\u0002\u0005u\u0006\"CA'7\u0005\u0005I\u0011IA(\u0011%\tifGA\u0001\n\u0003\ty\u0006C\u0005\u0002hm\t\t\u0011\"\u0001\u0002@\"I\u0011QO\u000e\u0002\u0002\u0013\u0005\u0013q\u000f\u0005\n\u0003\u000b[\u0012\u0011!C\u0001\u0003\u0007D\u0011\"!%\u001c\u0003\u0003%\t%a%\t\u0013\u0005U5$!A\u0005B\u0005]\u0005\"CAM7\u0005\u0005I\u0011BAN\r\u0019\t9M\u0001!\u0002J\"Q\u00111Z\u0013\u0003\u0016\u0004%\t!!4\t\u0015\u0005mWE!E!\u0002\u0013\ty\r\u0003\u0006\u0002^\u0016\u0012)\u001a!C\u0001\u0003?D!\"!;&\u0005#\u0005\u000b\u0011BAq\u0011\u001d\t9$\nC\u0001\u0003WD\u0011\"a=&\u0003\u0003%\t!!>\t\u0013\u0005mX%%A\u0005\u0002\u0005u\b\"\u0003B\nKE\u0005I\u0011\u0001B\u000b\u0011%\ti%JA\u0001\n\u0003\ny\u0005C\u0005\u0002^\u0015\n\t\u0011\"\u0001\u0002`!I\u0011qM\u0013\u0002\u0002\u0013\u0005!\u0011\u0004\u0005\n\u0003k*\u0013\u0011!C!\u0003oB\u0011\"!\"&\u0003\u0003%\tA!\b\t\u0013\t\u0005R%!A\u0005B\t\r\u0002\"CAIK\u0005\u0005I\u0011IAJ\u0011%\t)*JA\u0001\n\u0003\n9\nC\u0005\u0003(\u0015\n\t\u0011\"\u0011\u0003*\u001dI!Q\u0006\u0002\u0002\u0002#\u0005!q\u0006\u0004\n\u0003\u000f\u0014\u0011\u0011!E\u0001\u0005cAq!a\u000e9\t\u0003\u0011y\u0004C\u0005\u0002\u0016b\n\t\u0011\"\u0012\u0002\u0018\"I!\u0011\t\u001d\u0002\u0002\u0013\u0005%1\t\u0005\n\u0005\u0013B\u0014\u0011!CA\u0005\u0017B\u0011\"!'9\u0003\u0003%I!a'\b\u000f\tu#\u0001#!\u0003`\u00199!\u0011\r\u0002\t\u0002\n\r\u0004bBA\u001c\u007f\u0011\u0005!Q\r\u0005\n\u0003\u001bz\u0014\u0011!C!\u0003\u001fB\u0011\"!\u0018@\u0003\u0003%\t!a\u0018\t\u0013\u0005\u001dt(!A\u0005\u0002\t\u001d\u0004\"CA;\u007f\u0005\u0005I\u0011IA<\u0011%\t)iPA\u0001\n\u0003\u0011Y\u0007C\u0005\u0002\u0012~\n\t\u0011\"\u0011\u0002\u0014\"I\u0011QS \u0002\u0002\u0013\u0005\u0013q\u0013\u0005\n\u00033{\u0014\u0011!C\u0005\u00037;qAa\u001c\u0003\u0011\u0003\u0013\tHB\u0004\u0003t\tA\tI!\u001e\t\u000f\u0005]\"\n\"\u0001\u0003x!I\u0011Q\n&\u0002\u0002\u0013\u0005\u0013q\n\u0005\n\u0003;R\u0015\u0011!C\u0001\u0003?B\u0011\"a\u001aK\u0003\u0003%\tA!\u001f\t\u0013\u0005U$*!A\u0005B\u0005]\u0004\"CAC\u0015\u0006\u0005I\u0011\u0001B?\u0011%\t\tJSA\u0001\n\u0003\n\u0019\nC\u0005\u0002\u0016*\u000b\t\u0011\"\u0011\u0002\u0018\"I\u0011\u0011\u0014&\u0002\u0002\u0013%\u00111\u0014\u0004\u0007\u0005\u0003\u0013\u0001Ia!\t\u0015\t\u0015EK!f\u0001\n\u0003\ty\u0006\u0003\u0006\u0003\bR\u0013\t\u0012)A\u0005\u0003CB!B!#U\u0005+\u0007I\u0011AA0\u0011)\u0011Y\t\u0016B\tB\u0003%\u0011\u0011\r\u0005\u000b\u0005\u001b#&Q3A\u0005\u0002\t=\u0005B\u0003BJ)\nE\t\u0015!\u0003\u0003\u0012\"9\u0011q\u0007+\u0005\u0002\tU\u0005\"CAz)\u0006\u0005I\u0011\u0001BP\u0011%\tY\u0010VI\u0001\n\u0003\u00119\u000bC\u0005\u0003\u0014Q\u000b\n\u0011\"\u0001\u0003(\"I!1\u0016+\u0012\u0002\u0013\u0005!Q\u0016\u0005\n\u0003\u001b\"\u0016\u0011!C!\u0003\u001fB\u0011\"!\u0018U\u0003\u0003%\t!a\u0018\t\u0013\u0005\u001dD+!A\u0005\u0002\tE\u0006\"CA;)\u0006\u0005I\u0011IA<\u0011%\t)\tVA\u0001\n\u0003\u0011)\fC\u0005\u0003\"Q\u000b\t\u0011\"\u0011\u0003:\"I\u0011\u0011\u0013+\u0002\u0002\u0013\u0005\u00131\u0013\u0005\n\u0003+#\u0016\u0011!C!\u0003/C\u0011Ba\nU\u0003\u0003%\tE!0\b\u0013\t\u0005'!!A\t\u0002\t\rg!\u0003BA\u0005\u0005\u0005\t\u0012\u0001Bc\u0011\u001d\t9D\u001bC\u0001\u0005\u001bD\u0011\"!&k\u0003\u0003%)%a&\t\u0013\t\u0005#.!A\u0005\u0002\n=\u0007\"\u0003B%U\u0006\u0005I\u0011\u0011Bl\u0011%\tIJ[A\u0001\n\u0013\tY\nC\u0005\u0002\u001a\n\t\t\u0011\"\u0003\u0002\u001c\nqQ*Y:uKJlUm]:bO\u0016\u001c(BA:u\u0003\u0019i\u0017m\u001d;fe*\u0011QO^\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005]D\u0018!B:qCJ\\'BA={\u0003\u0019\t\u0007/Y2iK*\t10A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001}\u0006%\u0001cA@\u0002\u00065\u0011\u0011\u0011\u0001\u0006\u0003\u0003\u0007\tQa]2bY\u0006LA!a\u0002\u0002\u0002\t1\u0011I\\=SK\u001a\u0004B!a\u0003\u0002\u001c9!\u0011QBA\f\u001d\u0011\ty!!\u0006\u000e\u0005\u0005E!bAA\ny\u00061AH]8pizJ!!a\u0001\n\t\u0005e\u0011\u0011A\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\ti\"a\b\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\t\u0005e\u0011\u0011A\u0001\u000f\u001b\u0006\u001cH/\u001a:NKN\u001c\u0018mZ3t!\r\t)CA\u0007\u0002eN!!A`A\u0015!\u0011\tY#!\u000e\u000e\u0005\u00055\"\u0002BA\u0018\u0003c\t!![8\u000b\u0005\u0005M\u0012\u0001\u00026bm\u0006LA!!\b\u0002.\u00051A(\u001b8jiz\"\"!a\t\u0002\u001b\u0015cWm\u0019;fI2+\u0017\rZ3s!\r\ty$B\u0007\u0002\u0005\tiQ\t\\3di\u0016$G*Z1eKJ\u001cb!\u0002@\u0002F\u0005%\u0001cA@\u0002H%!\u0011\u0011JA\u0001\u0005\u001d\u0001&o\u001c3vGR$\"!!\u0010\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t\t\u0006\u0005\u0003\u0002T\u0005eSBAA+\u0015\u0011\t9&!\r\u0002\t1\fgnZ\u0005\u0005\u00037\n)F\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003C\u00022a`A2\u0013\u0011\t)'!\u0001\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005-\u0014\u0011\u000f\t\u0004\u007f\u00065\u0014\u0002BA8\u0003\u0003\u00111!\u00118z\u0011%\t\u0019(CA\u0001\u0002\u0004\t\t'A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003s\u0002b!a\u001f\u0002\u0002\u0006-TBAA?\u0015\u0011\ty(!\u0001\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0004\u0006u$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!#\u0002\u0010B\u0019q0a#\n\t\u00055\u0015\u0011\u0001\u0002\b\u0005>|G.Z1o\u0011%\t\u0019hCA\u0001\u0002\u0004\tY'\u0001\u0005iCND7i\u001c3f)\t\t\t'\u0001\u0005u_N#(/\u001b8h)\t\t\t&\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u001eB!\u00111KAP\u0013\u0011\t\t+!\u0016\u0003\r=\u0013'.Z2u\u0003E\u0011VM^8lK\u0012dU-\u00193feND\u0017\u000e\u001d\t\u0004\u0003\u007f\u0001\"!\u0005*fm>\\W\r\u001a'fC\u0012,'o\u001d5jaN1\u0001C`A#\u0003\u0013!\"!!*\u0015\t\u0005-\u0014q\u0016\u0005\n\u0003g\"\u0012\u0011!a\u0001\u0003C\"B!!#\u00024\"I\u00111\u000f\f\u0002\u0002\u0003\u0007\u00111N\u0001\u0016\u0007\",7m\u001b$pe^{'o[3s)&lWmT;u!\r\tyd\u0007\u0002\u0016\u0007\",7m\u001b$pe^{'o[3s)&lWmT;u'\u0019Yb0!\u0012\u0002\nQ\u0011\u0011q\u0017\u000b\u0005\u0003W\n\t\rC\u0005\u0002t}\t\t\u00111\u0001\u0002bQ!\u0011\u0011RAc\u0011%\t\u0019(IA\u0001\u0002\u0004\tYGA\u0007CK\u001eLgNU3d_Z,'/_\n\u0007Ky\f)%!\u0003\u0002\u0015M$xN]3e\u0003B\u00048/\u0006\u0002\u0002PB1\u00111BAi\u0003+LA!a5\u0002 \t\u00191+Z9\u0011\t\u0005\u0015\u0012q[\u0005\u0004\u00033\u0014(aD!qa2L7-\u0019;j_:LeNZ8\u0002\u0017M$xN]3e\u0003B\u00048\u000fI\u0001\u000egR|'/\u001a3X_J\\WM]:\u0016\u0005\u0005\u0005\bCBA\u0006\u0003#\f\u0019\u000f\u0005\u0003\u0002&\u0005\u0015\u0018bAAte\nQqk\u001c:lKJLeNZ8\u0002\u001dM$xN]3e/>\u00148.\u001a:tAQ1\u0011Q^Ax\u0003c\u00042!a\u0010&\u0011\u001d\tYM\u000ba\u0001\u0003\u001fDq!!8+\u0001\u0004\t\t/\u0001\u0003d_BLHCBAw\u0003o\fI\u0010C\u0005\u0002L.\u0002\n\u00111\u0001\u0002P\"I\u0011Q\\\u0016\u0011\u0002\u0003\u0007\u0011\u0011]\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tyP\u000b\u0003\u0002P\n\u00051F\u0001B\u0002!\u0011\u0011)Aa\u0004\u000e\u0005\t\u001d!\u0002\u0002B\u0005\u0005\u0017\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\t\t5\u0011\u0011A\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002B\t\u0005\u000f\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"Aa\u0006+\t\u0005\u0005(\u0011\u0001\u000b\u0005\u0003W\u0012Y\u0002C\u0005\u0002tA\n\t\u00111\u0001\u0002bQ!\u0011\u0011\u0012B\u0010\u0011%\t\u0019HMA\u0001\u0002\u0004\tY'\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA)\u0005KA\u0011\"a\u001d4\u0003\u0003\u0005\r!!\u0019\u0002\r\u0015\fX/\u00197t)\u0011\tIIa\u000b\t\u0013\u0005Md'!AA\u0002\u0005-\u0014!\u0004\"fO&t'+Z2pm\u0016\u0014\u0018\u0010E\u0002\u0002@a\u001aR\u0001\u000fB\u001a\u0003S\u0001\"B!\u000e\u0003<\u0005=\u0017\u0011]Aw\u001b\t\u00119D\u0003\u0003\u0003:\u0005\u0005\u0011a\u0002:v]RLW.Z\u0005\u0005\u0005{\u00119DA\tBEN$(/Y2u\rVt7\r^5p]J\"\"Aa\f\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\r\u00055(Q\tB$\u0011\u001d\tYm\u000fa\u0001\u0003\u001fDq!!8<\u0001\u0004\t\t/A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t5#\u0011\f\t\u0006\u007f\n=#1K\u0005\u0005\u0005#\n\tA\u0001\u0004PaRLwN\u001c\t\b\u007f\nU\u0013qZAq\u0013\u0011\u00119&!\u0001\u0003\rQ+\b\u000f\\33\u0011%\u0011Y\u0006PA\u0001\u0002\u0004\ti/A\u0002yIA\n\u0001cQ8na2,G/\u001a*fG>4XM]=\u0011\u0007\u0005}rH\u0001\tD_6\u0004H.\u001a;f%\u0016\u001cwN^3ssN1qH`A#\u0003\u0013!\"Aa\u0018\u0015\t\u0005-$\u0011\u000e\u0005\n\u0003g\u001a\u0015\u0011!a\u0001\u0003C\"B!!#\u0003n!I\u00111O#\u0002\u0002\u0003\u0007\u00111N\u0001\u0012\u0005>,h\u000e\u001a)peR\u001c(+Z9vKN$\bcAA \u0015\n\t\"i\\;oIB{'\u000f^:SKF,Xm\u001d;\u0014\r)s\u0018QIA\u0005)\t\u0011\t\b\u0006\u0003\u0002l\tm\u0004\"CA:\u001d\u0006\u0005\t\u0019AA1)\u0011\tIIa \t\u0013\u0005M\u0004+!AA\u0002\u0005-$A\u0005\"pk:$\u0007k\u001c:ugJ+7\u000f]8og\u0016\u001cb\u0001\u0016@\u0002F\u0005%\u0011a\u0004:qG\u0016sG\r]8j]R\u0004vN\u001d;\u0002!I\u00048-\u00128ea>Lg\u000e\u001e)peR\u0004\u0013!C<fEVK\u0005k\u001c:u\u0003)9XMY+J!>\u0014H\u000fI\u0001\te\u0016\u001cH\u000fU8siV\u0011!\u0011\u0013\t\u0006\u007f\n=\u0013\u0011M\u0001\ne\u0016\u001cH\u000fU8si\u0002\"\u0002Ba&\u0003\u001a\nm%Q\u0014\t\u0004\u0003\u007f!\u0006b\u0002BC7\u0002\u0007\u0011\u0011\r\u0005\b\u0005\u0013[\u0006\u0019AA1\u0011\u001d\u0011ii\u0017a\u0001\u0005##\u0002Ba&\u0003\"\n\r&Q\u0015\u0005\n\u0005\u000bc\u0006\u0013!a\u0001\u0003CB\u0011B!#]!\u0003\u0005\r!!\u0019\t\u0013\t5E\f%AA\u0002\tEUC\u0001BUU\u0011\t\tG!\u0001\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011!q\u0016\u0016\u0005\u0005#\u0013\t\u0001\u0006\u0003\u0002l\tM\u0006\"CA:E\u0006\u0005\t\u0019AA1)\u0011\tIIa.\t\u0013\u0005MD-!AA\u0002\u0005-D\u0003BA)\u0005wC\u0011\"a\u001df\u0003\u0003\u0005\r!!\u0019\u0015\t\u0005%%q\u0018\u0005\n\u0003gB\u0017\u0011!a\u0001\u0003W\n!CQ8v]\u0012\u0004vN\u001d;t%\u0016\u001c\bo\u001c8tKB\u0019\u0011q\b6\u0014\u000b)\u00149-!\u000b\u0011\u0019\tU\"\u0011ZA1\u0003C\u0012\tJa&\n\t\t-'q\u0007\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001cDC\u0001Bb)!\u00119J!5\u0003T\nU\u0007b\u0002BC[\u0002\u0007\u0011\u0011\r\u0005\b\u0005\u0013k\u0007\u0019AA1\u0011\u001d\u0011i)\u001ca\u0001\u0005##BA!7\u0003bB)qPa\u0014\u0003\\BIqP!8\u0002b\u0005\u0005$\u0011S\u0005\u0005\u0005?\f\tA\u0001\u0004UkBdWm\r\u0005\n\u00057r\u0017\u0011!a\u0001\u0005/\u0003"
)
public interface MasterMessages extends Serializable {
   public static class ElectedLeader$ implements Product, Serializable {
      public static final ElectedLeader$ MODULE$ = new ElectedLeader$();

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
         return "ElectedLeader";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ElectedLeader$;
      }

      public int hashCode() {
         return 454631671;
      }

      public String toString() {
         return "ElectedLeader";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ElectedLeader$.class);
      }
   }

   public static class RevokedLeadership$ implements Product, Serializable {
      public static final RevokedLeadership$ MODULE$ = new RevokedLeadership$();

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
         return "RevokedLeadership";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof RevokedLeadership$;
      }

      public int hashCode() {
         return -799595549;
      }

      public String toString() {
         return "RevokedLeadership";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(RevokedLeadership$.class);
      }
   }

   public static class CheckForWorkerTimeOut$ implements Product, Serializable {
      public static final CheckForWorkerTimeOut$ MODULE$ = new CheckForWorkerTimeOut$();

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
         return "CheckForWorkerTimeOut";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof CheckForWorkerTimeOut$;
      }

      public int hashCode() {
         return 561556130;
      }

      public String toString() {
         return "CheckForWorkerTimeOut";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(CheckForWorkerTimeOut$.class);
      }
   }

   public static class BeginRecovery implements Product, Serializable {
      private final Seq storedApps;
      private final Seq storedWorkers;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Seq storedApps() {
         return this.storedApps;
      }

      public Seq storedWorkers() {
         return this.storedWorkers;
      }

      public BeginRecovery copy(final Seq storedApps, final Seq storedWorkers) {
         return new BeginRecovery(storedApps, storedWorkers);
      }

      public Seq copy$default$1() {
         return this.storedApps();
      }

      public Seq copy$default$2() {
         return this.storedWorkers();
      }

      public String productPrefix() {
         return "BeginRecovery";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.storedApps();
            }
            case 1 -> {
               return this.storedWorkers();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof BeginRecovery;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "storedApps";
            }
            case 1 -> {
               return "storedWorkers";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var8;
         if (this != x$1) {
            label55: {
               if (x$1 instanceof BeginRecovery) {
                  label48: {
                     BeginRecovery var4 = (BeginRecovery)x$1;
                     Seq var10000 = this.storedApps();
                     Seq var5 = var4.storedApps();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     var10000 = this.storedWorkers();
                     Seq var6 = var4.storedWorkers();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label48;
                     }

                     if (var4.canEqual(this)) {
                        break label55;
                     }
                  }
               }

               var8 = false;
               return var8;
            }
         }

         var8 = true;
         return var8;
      }

      public BeginRecovery(final Seq storedApps, final Seq storedWorkers) {
         this.storedApps = storedApps;
         this.storedWorkers = storedWorkers;
         Product.$init$(this);
      }
   }

   public static class BeginRecovery$ extends AbstractFunction2 implements Serializable {
      public static final BeginRecovery$ MODULE$ = new BeginRecovery$();

      public final String toString() {
         return "BeginRecovery";
      }

      public BeginRecovery apply(final Seq storedApps, final Seq storedWorkers) {
         return new BeginRecovery(storedApps, storedWorkers);
      }

      public Option unapply(final BeginRecovery x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.storedApps(), x$0.storedWorkers())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(BeginRecovery$.class);
      }
   }

   public static class CompleteRecovery$ implements Product, Serializable {
      public static final CompleteRecovery$ MODULE$ = new CompleteRecovery$();

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
         return "CompleteRecovery";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof CompleteRecovery$;
      }

      public int hashCode() {
         return -2051141618;
      }

      public String toString() {
         return "CompleteRecovery";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(CompleteRecovery$.class);
      }
   }

   public static class BoundPortsRequest$ implements Product, Serializable {
      public static final BoundPortsRequest$ MODULE$ = new BoundPortsRequest$();

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
         return "BoundPortsRequest";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof BoundPortsRequest$;
      }

      public int hashCode() {
         return 551533211;
      }

      public String toString() {
         return "BoundPortsRequest";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(BoundPortsRequest$.class);
      }
   }

   public static class BoundPortsResponse implements Product, Serializable {
      private final int rpcEndpointPort;
      private final int webUIPort;
      private final Option restPort;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int rpcEndpointPort() {
         return this.rpcEndpointPort;
      }

      public int webUIPort() {
         return this.webUIPort;
      }

      public Option restPort() {
         return this.restPort;
      }

      public BoundPortsResponse copy(final int rpcEndpointPort, final int webUIPort, final Option restPort) {
         return new BoundPortsResponse(rpcEndpointPort, webUIPort, restPort);
      }

      public int copy$default$1() {
         return this.rpcEndpointPort();
      }

      public int copy$default$2() {
         return this.webUIPort();
      }

      public Option copy$default$3() {
         return this.restPort();
      }

      public String productPrefix() {
         return "BoundPortsResponse";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToInteger(this.rpcEndpointPort());
            }
            case 1 -> {
               return BoxesRunTime.boxToInteger(this.webUIPort());
            }
            case 2 -> {
               return this.restPort();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof BoundPortsResponse;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "rpcEndpointPort";
            }
            case 1 -> {
               return "webUIPort";
            }
            case 2 -> {
               return "restPort";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.rpcEndpointPort());
         var1 = Statics.mix(var1, this.webUIPort());
         var1 = Statics.mix(var1, Statics.anyHash(this.restPort()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label55: {
               if (x$1 instanceof BoundPortsResponse) {
                  BoundPortsResponse var4 = (BoundPortsResponse)x$1;
                  if (this.rpcEndpointPort() == var4.rpcEndpointPort() && this.webUIPort() == var4.webUIPort()) {
                     label48: {
                        Option var10000 = this.restPort();
                        Option var5 = var4.restPort();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label48;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label48;
                        }

                        if (var4.canEqual(this)) {
                           break label55;
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

      public BoundPortsResponse(final int rpcEndpointPort, final int webUIPort, final Option restPort) {
         this.rpcEndpointPort = rpcEndpointPort;
         this.webUIPort = webUIPort;
         this.restPort = restPort;
         Product.$init$(this);
      }
   }

   public static class BoundPortsResponse$ extends AbstractFunction3 implements Serializable {
      public static final BoundPortsResponse$ MODULE$ = new BoundPortsResponse$();

      public final String toString() {
         return "BoundPortsResponse";
      }

      public BoundPortsResponse apply(final int rpcEndpointPort, final int webUIPort, final Option restPort) {
         return new BoundPortsResponse(rpcEndpointPort, webUIPort, restPort);
      }

      public Option unapply(final BoundPortsResponse x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.rpcEndpointPort()), BoxesRunTime.boxToInteger(x$0.webUIPort()), x$0.restPort())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(BoundPortsResponse$.class);
      }
   }
}
