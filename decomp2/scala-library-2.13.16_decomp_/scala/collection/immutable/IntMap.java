package scala.collection.immutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.AbstractIterator;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.generic.BitOperations;
import scala.collection.generic.DefaultSerializationProxy;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ImmutableBuilder;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015}xA\u0002>|\u0011\u0003\t)AB\u0004\u0002\nmD\t!a\u0003\t\u000f\u0005\u0015\u0012\u0001\"\u0001\u0002(!9\u0011\u0011F\u0001\u0005\u0002\u0005-\u0002b\u0002CY\u0003\u0011\u0005A1\u0017\u0005\b\u0005K\u000bA\u0011\u0001Ca\u0011\u001d!9.\u0001C\u0001\t3<\u0001\u0002b;\u0002\u0011\u0003[H1\u000b\u0004\t\t\u0017\n\u0001\u0012Q>\u0005N!9\u0011Q\u0005\u0005\u0005\u0002\u0011E\u0003b\u0002C+\u0011\u0011\u0005Cq\u000b\u0005\n\tgA\u0011\u0011!C!\u0005wA\u0011\u0002\"\u000e\t\u0003\u0003%\tA!\u0016\t\u0013\u0011]\u0002\"!A\u0005\u0002\u0011m\u0003\"\u0003C \u0011\u0005\u0005I\u0011\tC!\r\u001d!y&\u0001!|\tCB!Ba \u0010\u0005+\u0007I\u0011\u0001B+\u0011)!Yg\u0004B\tB\u0003%\u00111\b\u0005\u000b\u0005\u001f|!Q3A\u0005\u0002\u00115\u0004B\u0003C8\u001f\tE\t\u0015!\u0003\u0005h!9\u0011QE\b\u0005\u0002\u0011E\u0004b\u0002C=\u001f\u0011\u0005A1\u0010\u0005\n\u0007{|\u0011\u0011!C\u0001\t\u0017C\u0011\u0002b\u0005\u0010#\u0003%\t\u0001\"'\t\u0013\u0011uq\"%A\u0005\u0002\u0011u\u0005\"\u0003C\u001a\u001f\u0005\u0005I\u0011\tB\u001e\u0011%!)dDA\u0001\n\u0003\u0011)\u0006C\u0005\u00058=\t\t\u0011\"\u0001\u0005&\"IAqH\b\u0002\u0002\u0013\u0005C\u0011\t\u0005\n\t\u000bz\u0011\u0011!C!\tS;!\u0002\"<\u0002\u0003\u0003E\ta\u001fCx\r)!y&AA\u0001\u0012\u0003YH\u0011\u001f\u0005\b\u0003KyB\u0011\u0001Cz\u0011%!)pHA\u0001\n\u000b\"9\u0010C\u0005\u0003&~\t\t\u0011\"!\u0005z\"IQqA\u0010\u0002\u0002\u0013\u0005U\u0011\u0002\u0005\n\u0007k{\u0012\u0011!C\u0005\u000b71qaa/\u0002\u0001n\u001ci\f\u0003\u0006\u0004N\u0016\u0012)\u001a!C\u0001\u0005+B!ba4&\u0005#\u0005\u000b\u0011BA\u001e\u0011)\u0019\t.\nBK\u0002\u0013\u0005!Q\u000b\u0005\u000b\u0007',#\u0011#Q\u0001\n\u0005m\u0002BCBkK\tU\r\u0011\"\u0001\u0004X\"Q1\u0011\\\u0013\u0003\u0012\u0003\u0006Ia!1\t\u0015\rmWE!f\u0001\n\u0003\u00199\u000e\u0003\u0006\u0004^\u0016\u0012\t\u0012)A\u0005\u0007\u0003Dq!!\n&\t\u0003\u0019y\u000eC\u0004\u0004n\u0016\"\taa<\t\u0013\ruX%!A\u0005\u0002\r}\b\"\u0003C\nKE\u0005I\u0011\u0001C\u000b\u0011%!i\"JI\u0001\n\u0003!y\u0002C\u0005\u0005$\u0015\n\n\u0011\"\u0001\u0005&!IAQF\u0013\u0012\u0002\u0013\u0005Aq\u0006\u0005\n\tg)\u0013\u0011!C!\u0005wA\u0011\u0002\"\u000e&\u0003\u0003%\tA!\u0016\t\u0013\u0011]R%!A\u0005\u0002\u0011e\u0002\"\u0003C K\u0005\u0005I\u0011\tC!\u0011%!)%JA\u0001\n\u0003\"9e\u0002\u0006\u0006$\u0005\t\t\u0011#\u0001|\u000bK1!ba/\u0002\u0003\u0003E\ta_C\u0014\u0011\u001d\t)c\u000fC\u0001\u000bSA\u0011\u0002\"><\u0003\u0003%)\u0005b>\t\u0013\t\u00156(!A\u0005\u0002\u0016-\u0002\"CC\u0004w\u0005\u0005I\u0011QC \u0011%\u0019)lOA\u0001\n\u0013)Y\u0002C\u0004\u0006V\u0005!\t!b\u0016\t\u000f\u0015\u0015\u0014\u0001b\u0001\u0006h\u001dAQQP\u0001!\u0012\u0013)yH\u0002\u0005\u0006\u0002\u0006\u0001\u000b\u0012BCB\u0011\u001d\t)\u0003\u0012C\u0001\u000b\u0017Cq!! E\t\u0003)i\tC\u0004\u0006V\u0011#\t!\"&\t\u0013\rUF)!A\u0005\n\u0015m\u0001bBCS\u0003\u0011\rQqU\u0004\t\u000b{\u000b\u0001\u0015#\u0003\u0006@\u001aAQ\u0011Y\u0001!\u0012\u0013)\u0019\rC\u0004\u0002&-#\t!b2\t\u000f\u0005u4\n\"\u0001\u0006J\"9QQK&\u0005\u0002\u0015E\u0007bBCk\u0003\u0011\rQq\u001b\u0005\b\u000bK\fA1ACt\u0011%\u0019),AA\u0001\n\u0013)YBB\u0004\u0002\nm\f\t#!\r\t\u000f\u0005\u0015\"\u000b\"\u0001\u0002|!9\u0011Q\u0010*\u0005R\u0005}\u0004bBAT%\u0012E\u0011\u0011\u0016\u0005\b\u0003w\u0013F\u0011KA_\u0011\u001d\tIC\u0015C!\u0003\u001bDq!a4S\t\u0003\n\t\u000eC\u0004\u0002ZJ#\t!a7\t\u000f\u0005\r(\u000b\"\u0012\u0002f\"9\u0011q *\u0005B\t\u0005\u0001b\u0002B\t%\u0012\u0005#1\u0003\u0005\b\u0005/\u0011FQ\u0001B\r\u0011\u001d\u0011)C\u0015C!\u0005OAqAa\u000bS\t\u000b\u0011i\u0003\u0003\u0005\u0003:I\u0003K\u0011\u000bB\u001e\u0011\u001d\u0011IE\u0015C!\u0005\u0017BqAa\u0015S\t\u0003\u0012)\u0006C\u0004\u0003XI#\tE!\u0017\t\u000f\t}#\u000b\"\u0011\u0003b!9!\u0011\u000f*\u0005F\tU\u0003b\u0002B:%\u0012\u0015!Q\u000f\u0005\b\u0005\u0017\u0013FQ\tBG\u0011\u001d\u0011)K\u0015C#\u0005OCqA!,S\t\u0003\u0012y\u000bC\u0004\u0003@J#\tE!1\t\u000f\tE'\u000b\"\u0001\u0003T\"9!1\u001d*\u0005\u0002\t\u0015\bb\u0002B|%\u0012\u0005#\u0011 \u0005\b\u0007\u001b\u0011F\u0011IB\b\u0011\u001d\u0019yB\u0015C\u0001\u0007CAqaa\u000eS\t\u0003\u0019I\u0004C\u0004\u0004LI#\ta!\u0014\t\u000f\rE#\u000b\"\u0001\u0004T!911\r*\u0005\u0002\r\u0015\u0004bBB=%\u0012\u000511\u0010\u0005\b\u0007'\u0013F\u0011ABK\u0011\u001d\u0019iA\u0015C\u0001\u0007CCqa!,S\t\u000b\u0011)\u0006C\u0004\u00042J#)A!\u0016\t\u0011\rU&\u000b)C\t\u0007o\u000ba!\u00138u\u001b\u0006\u0004(B\u0001?~\u0003%IW.\\;uC\ndWM\u0003\u0002\u007f\u007f\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0005\u0005\u0005\u0011!B:dC2\f7\u0001\u0001\t\u0004\u0003\u000f\tQ\"A>\u0003\r%sG/T1q'\u0015\t\u0011QBA\u000b!\u0011\ty!!\u0005\u000e\u0003}L1!a\u0005\u0000\u0005\u0019\te.\u001f*fMB!\u0011qCA\u0011\u001b\t\tIB\u0003\u0003\u0002\u001c\u0005u\u0011AA5p\u0015\t\ty\"\u0001\u0003kCZ\f\u0017\u0002BA\u0012\u00033\u0011AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtDCAA\u0003\u0003\u0015)W\u000e\u001d;z+\u0011\ti\u0003b,\u0016\u0005\u0005=\u0002#BA\u0004%\u00125V\u0003BA\u001a\u0003\u000b\u001arAUA\u001b\u0003/\n)\u0007\u0005\u0005\u0002\b\u0005]\u00121HA!\u0013\r\tId\u001f\u0002\f\u0003\n\u001cHO]1di6\u000b\u0007\u000f\u0005\u0003\u0002\u0010\u0005u\u0012bAA \u007f\n\u0019\u0011J\u001c;\u0011\t\u0005\r\u0013Q\t\u0007\u0001\t!\t9E\u0015CC\u0002\u0005%#!\u0001+\u0012\t\u0005-\u0013\u0011\u000b\t\u0005\u0003\u001f\ti%C\u0002\u0002P}\u0014qAT8uQ&tw\r\u0005\u0003\u0002\u0010\u0005M\u0013bAA+\u007f\n\u0019\u0011I\\=\u0011\u0019\u0005\u001d\u0011\u0011LA\u001e\u0003\u0003\ni&a\u0019\n\u0007\u0005m3PA\u000bTiJL7\r^(qi&l\u0017N_3e\u001b\u0006\u0004x\n]:\u0011\t\u0005\u001d\u0011qL\u0005\u0004\u0003CZ(aA'baB)\u0011q\u0001*\u0002BA!\u0011qMA<\u001d\u0011\tI'a\u001d\u000f\t\u0005-\u0014\u0011O\u0007\u0003\u0003[RA!a\u001c\u0002\u0004\u00051AH]8pizJ!!!\u0001\n\u0007\u0005Ut0A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\r\u0012\u0011\u0010\u0006\u0004\u0003kzHCAA2\u000311'o\\7Ta\u0016\u001c\u0017NZ5d)\u0011\t\u0019'!!\t\u000f\u0005\rE\u000b1\u0001\u0002\u0006\u0006!1m\u001c7m!\u0019\t9)!#\u0002\u000e6\tQ0C\u0002\u0002\fv\u0014A\"\u0013;fe\u0006\u0014G.Z(oG\u0016TC!a$\u0002\u0016BA\u0011qBAI\u0003w\t\t%C\u0002\u0002\u0014~\u0014a\u0001V;qY\u0016\u00144FAAL!\u0011\tI*a)\u000e\u0005\u0005m%\u0002BAO\u0003?\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u0005v0\u0001\u0006b]:|G/\u0019;j_:LA!!*\u0002\u001c\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u0015%tG/T1q\rJ|W.\u0006\u0003\u0002,\u0006EF\u0003BAW\u0003k\u0003R!a\u0002S\u0003_\u0003B!a\u0011\u00022\u00129\u00111W+C\u0002\u0005%#A\u0001,3\u0011\u001d\t\u0019)\u0016a\u0001\u0003o\u0003b!a\"\u0002\n\u0006e\u0006\u0003CA\b\u0003#\u000bY$a,\u0002%9,wo\u00159fG&4\u0017n\u0019\"vS2$WM]\u000b\u0003\u0003\u007fSC!!1\u0002\u0016BA\u00111YAe\u0003\u001f\u000b\u0019'\u0004\u0002\u0002F*\u0019\u0011qY?\u0002\u000f5,H/\u00192mK&!\u00111ZAc\u0005\u001d\u0011U/\u001b7eKJ,\"!a\u0019\u0002\rQ|G*[:u+\t\t\u0019\u000e\u0005\u0004\u0002\b\u0005U\u0017qR\u0005\u0004\u0003/\\(\u0001\u0002'jgR\f\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0003;\u0004b!a\"\u0002`\u0006=\u0015bAAq{\nA\u0011\n^3sCR|'/A\u0004g_J,\u0017m\u00195\u0016\t\u0005\u001d\u00181 \u000b\u0005\u0003S\fy\u000f\u0005\u0003\u0002\u0010\u0005-\u0018bAAw\u007f\n!QK\\5u\u0011\u001d\t\tP\u0017a\u0001\u0003g\f\u0011A\u001a\t\t\u0003\u001f\t)0a$\u0002z&\u0019\u0011q_@\u0003\u0013\u0019+hn\u0019;j_:\f\u0004\u0003BA\"\u0003w$q!!@[\u0005\u0004\tIEA\u0001V\u000311wN]3bG\",e\u000e\u001e:z+\u0011\u0011\u0019Aa\u0004\u0015\t\u0005%(Q\u0001\u0005\b\u0003c\\\u0006\u0019\u0001B\u0004!)\tyA!\u0003\u0002<\u0005\u0005#QB\u0005\u0004\u0005\u0017y(!\u0003$v]\u000e$\u0018n\u001c83!\u0011\t\u0019Ea\u0004\u0005\u000f\u0005u8L1\u0001\u0002J\u0005a1.Z=t\u0013R,'/\u0019;peV\u0011!Q\u0003\t\u0007\u0003\u000f\u000by.a\u000f\u0002\u0015\u0019|'/Z1dQ.+\u00170\u0006\u0003\u0003\u001c\t\rB\u0003BAu\u0005;Aq!!=^\u0001\u0004\u0011y\u0002\u0005\u0005\u0002\u0010\u0005U\u00181\bB\u0011!\u0011\t\u0019Ea\t\u0005\u000f\u0005uXL1\u0001\u0002J\u0005qa/\u00197vKNLE/\u001a:bi>\u0014XC\u0001B\u0015!\u0019\t9)a8\u0002B\u0005aam\u001c:fC\u000eDg+\u00197vKV!!q\u0006B\u001c)\u0011\tIO!\r\t\u000f\u0005Ex\f1\u0001\u00034AA\u0011qBA{\u0003\u0003\u0012)\u0004\u0005\u0003\u0002D\t]BaBA\u007f?\n\u0007\u0011\u0011J\u0001\nG2\f7o\u001d(b[\u0016,\"A!\u0010\u0011\t\t}\"QI\u0007\u0003\u0005\u0003RAAa\u0011\u0002\u001e\u0005!A.\u00198h\u0013\u0011\u00119E!\u0011\u0003\rM#(/\u001b8h\u0003\u001dI7/R7qif,\"A!\u0014\u0011\t\u0005=!qJ\u0005\u0004\u0005#z(a\u0002\"p_2,\u0017M\\\u0001\nW:|wO\\*ju\u0016,\"!a\u000f\u0002\r\u0019LG\u000e^3s)\u0011\t\u0019Ga\u0017\t\u000f\u0005E8\r1\u0001\u0003^AA\u0011qBA{\u0003\u001f\u0013i%A\u0005ue\u0006t7OZ8s[V!!1\rB5)\u0011\u0011)G!\u001c\u0011\u000b\u0005\u001d!Ka\u001a\u0011\t\u0005\r#\u0011\u000e\u0003\b\u0005W\"'\u0019AA%\u0005\u0005\u0019\u0006bBAyI\u0002\u0007!q\u000e\t\u000b\u0003\u001f\u0011I!a\u000f\u0002B\t\u001d\u0014\u0001B:ju\u0016\f1aZ3u)\u0011\u00119H! \u0011\r\u0005=!\u0011PA!\u0013\r\u0011Yh \u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\t}d\r1\u0001\u0002<\u0005\u00191.Z=)\u0007\u0019\u0014\u0019\t\u0005\u0003\u0003\u0006\n\u001dUBAAP\u0013\u0011\u0011I)a(\u0003\u000fQ\f\u0017\u000e\u001c:fG\u0006Iq-\u001a;Pe\u0016c7/Z\u000b\u0005\u0005\u001f\u0013\u0019\n\u0006\u0004\u0003\u0012\n]%\u0011\u0014\t\u0005\u0003\u0007\u0012\u0019\nB\u0004\u0003l\u001d\u0014\rA!&\u0012\t\u0005\u0005\u0013\u0011\u000b\u0005\b\u0005\u007f:\u0007\u0019AA\u001e\u0011!\u0011Yj\u001aCA\u0002\tu\u0015a\u00023fM\u0006,H\u000e\u001e\t\u0007\u0003\u001f\u0011yJ!%\n\u0007\t\u0005vP\u0001\u0005=Eft\u0017-\\3?Q\r9'1Q\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u0003\u0003\u0012I\u000bC\u0004\u0003\u0000!\u0004\r!a\u000f)\u0007!\u0014\u0019)A\u0003%a2,8/\u0006\u0003\u00032\n]F\u0003\u0002BZ\u0005s\u0003R!a\u0002S\u0005k\u0003B!a\u0011\u00038\u00129!1N5C\u0002\tU\u0005b\u0002B^S\u0002\u0007!QX\u0001\u0003WZ\u0004\u0002\"a\u0004\u0002\u0012\u0006m\"QW\u0001\bkB$\u0017\r^3e+\u0011\u0011\u0019M!3\u0015\r\t\u0015'1\u001aBg!\u0015\t9A\u0015Bd!\u0011\t\u0019E!3\u0005\u000f\t-$N1\u0001\u0003\u0016\"9!q\u00106A\u0002\u0005m\u0002b\u0002BhU\u0002\u0007!qY\u0001\u0006m\u0006dW/Z\u0001\u0004[\u0006\u0004X\u0003\u0002Bk\u00057$BAa6\u0003^B)\u0011q\u0001*\u0003ZB!\u00111\tBn\t\u001d\t\u0019l\u001bb\u0001\u0003\u0013Bq!!=l\u0001\u0004\u0011y\u000e\u0005\u0005\u0002\u0010\u0005U\u0018q\u0012Bq!!\ty!!%\u0002<\te\u0017a\u00024mCRl\u0015\r]\u000b\u0005\u0005O\u0014i\u000f\u0006\u0003\u0003j\n=\b#BA\u0004%\n-\b\u0003BA\"\u0005[$q!a-m\u0005\u0004\tI\u0005C\u0004\u0002r2\u0004\rA!=\u0011\u0011\u0005=\u0011Q_AH\u0005g\u0004b!a\"\u0002\n\nU\b\u0003CA\b\u0003#\u000bYDa;\u0002\r\r|gnY1u+\u0011\u0011Yp!\u0001\u0015\t\tu8Q\u0001\t\u0006\u0003\u000f\u0011&q \t\u0005\u0003\u0007\u001a\t\u0001B\u0004\u0004\u00045\u0014\rA!&\u0003\u0005Y\u000b\u0004bBB\u0004[\u0002\u00071\u0011B\u0001\u0005i\"\fG\u000f\u0005\u0004\u0002\b\u0006%51\u0002\t\t\u0003\u001f\t\t*a\u000f\u0003\u0000\u0006QA\u0005\u001d7vg\u0012\u0002H.^:\u0016\t\rE1q\u0003\u000b\u0005\u0007'\u0019I\u0002E\u0003\u0002\bI\u001b)\u0002\u0005\u0003\u0002D\r]AaBB\u0002]\n\u0007!Q\u0013\u0005\b\u0007\u000fq\u0007\u0019AB\u000e!\u0019\t9)!#\u0004\u001eAA\u0011qBAI\u0003w\u0019)\"A\u0004d_2dWm\u0019;\u0016\t\r\r2\u0011\u0006\u000b\u0005\u0007K\u0019Y\u0003E\u0003\u0002\bI\u001b9\u0003\u0005\u0003\u0002D\r%BaBAZ_\n\u0007\u0011\u0011\n\u0005\b\u0007[y\u0007\u0019AB\u0018\u0003\t\u0001h\r\u0005\u0005\u0002\u0010\rE\u0012qRB\u001b\u0013\r\u0019\u0019d \u0002\u0010!\u0006\u0014H/[1m\rVt7\r^5p]BA\u0011qBAI\u0003w\u00199#\u0001\u0006va\u0012\fG/Z,ji\",Baa\u000f\u0004BQA1QHB\"\u0007\u000b\u001a9\u0005E\u0003\u0002\bI\u001by\u0004\u0005\u0003\u0002D\r\u0005Ca\u0002B6a\n\u0007!Q\u0013\u0005\b\u0005\u007f\u0002\b\u0019AA\u001e\u0011\u001d\u0011y\r\u001da\u0001\u0007\u007fAq!!=q\u0001\u0004\u0019I\u0005\u0005\u0006\u0002\u0010\t%\u0011\u0011IB \u0007\u007f\tqA]3n_Z,G\r\u0006\u0003\u0002d\r=\u0003b\u0002B@c\u0002\u0007\u00111H\u0001\u000f[>$\u0017NZ=PeJ+Wn\u001c<f+\u0011\u0019)fa\u0017\u0015\t\r]3Q\f\t\u0006\u0003\u000f\u00116\u0011\f\t\u0005\u0003\u0007\u001aY\u0006B\u0004\u0003lI\u0014\r!!\u0013\t\u000f\u0005E(\u000f1\u0001\u0004`AQ\u0011q\u0002B\u0005\u0003w\t\te!\u0019\u0011\r\u0005=!\u0011PB-\u0003%)h.[8o/&$\b.\u0006\u0003\u0004h\r5DCBB5\u0007_\u001a\t\bE\u0003\u0002\bI\u001bY\u0007\u0005\u0003\u0002D\r5Da\u0002B6g\n\u0007!Q\u0013\u0005\b\u0007\u000f\u0019\b\u0019AB5\u0011\u001d\t\tp\u001da\u0001\u0007g\u0002B\"a\u0004\u0004v\u0005m21NB6\u0007WJ1aa\u001e\u0000\u0005%1UO\\2uS>t7'\u0001\tj]R,'o]3di&|gnV5uQV11QPBG\u0007\u0007#baa \u0004\b\u000e=\u0005#BA\u0004%\u000e\u0005\u0005\u0003BA\"\u0007\u0007#qa!\"u\u0005\u0004\tIEA\u0001S\u0011\u001d\u00199\u0001\u001ea\u0001\u0007\u0013\u0003R!a\u0002S\u0007\u0017\u0003B!a\u0011\u0004\u000e\u00129!1\u000e;C\u0002\u0005%\u0003bBAyi\u0002\u00071\u0011\u0013\t\r\u0003\u001f\u0019)(a\u000f\u0002B\r-5\u0011Q\u0001\rS:$XM]:fGRLwN\\\u000b\u0005\u0007/\u001by\n\u0006\u0003\u0002d\re\u0005bBB\u0004k\u0002\u000711\u0014\t\u0006\u0003\u000f\u00116Q\u0014\t\u0005\u0003\u0007\u001ay\nB\u0004\u0004\u0006V\u0014\r!!\u0013\u0016\t\r\r6\u0011\u0016\u000b\u0005\u0007K\u001bY\u000bE\u0003\u0002\bI\u001b9\u000b\u0005\u0003\u0002D\r%Fa\u0002B6m\n\u0007!Q\u0013\u0005\b\u0007\u000f1\b\u0019ABS\u0003!1\u0017N]:u\u0017\u0016L\bfA<\u0003\u0004\u00069A.Y:u\u0017\u0016L\bf\u0001=\u0003\u0004\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011QB\u0015\u0005%\u0016BqBA\u0002CS:,Baa0\u0004FN9Qe!1\u0004H\u0006\u0015\u0004#BA\u0004%\u000e\r\u0007\u0003BA\"\u0007\u000b$\u0001\"a\u0012&\t\u000b\u0007\u0011\u0011\n\t\u0005\u0003\u001f\u0019I-C\u0002\u0004L~\u0014q\u0001\u0015:pIV\u001cG/\u0001\u0004qe\u00164\u0017\u000e_\u0001\baJ,g-\u001b=!\u0003\u0011i\u0017m]6\u0002\u000b5\f7o\u001b\u0011\u0002\t1,g\r^\u000b\u0003\u0007\u0003\fQ\u0001\\3gi\u0002\nQA]5hQR\faA]5hQR\u0004CCCBq\u0007K\u001c9o!;\u0004lB)11]\u0013\u0004D6\t\u0011\u0001C\u0004\u0004N:\u0002\r!a\u000f\t\u000f\rEg\u00061\u0001\u0002<!91Q\u001b\u0018A\u0002\r\u0005\u0007bBBn]\u0001\u00071\u0011Y\u0001\u0004E&tW\u0003BBy\u0007o$baa=\u0004z\u000em\b#BA\u0004%\u000eU\b\u0003BA\"\u0007o$qAa\u001b0\u0005\u0004\tI\u0005C\u0004\u0004V>\u0002\raa=\t\u000f\rmw\u00061\u0001\u0004t\u0006!1m\u001c9z+\u0011!\t\u0001b\u0002\u0015\u0015\u0011\rA\u0011\u0002C\u0006\t\u001b!\t\u0002E\u0003\u0004d\u0016\")\u0001\u0005\u0003\u0002D\u0011\u001dAaBA$a\t\u0007\u0011\u0011\n\u0005\n\u0007\u001b\u0004\u0004\u0013!a\u0001\u0003wA\u0011b!51!\u0003\u0005\r!a\u000f\t\u0013\rU\u0007\u0007%AA\u0002\u0011=\u0001#BA\u0004%\u0012\u0015\u0001\"CBnaA\u0005\t\u0019\u0001C\b\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*B\u0001b\u0006\u0005\u001cU\u0011A\u0011\u0004\u0016\u0005\u0003w\t)\nB\u0004\u0002HE\u0012\r!!\u0013\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU!Aq\u0003C\u0011\t\u001d\t9E\rb\u0001\u0003\u0013\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0003\u0005(\u0011-RC\u0001C\u0015U\u0011\u0019\t-!&\u0005\u000f\u0005\u001d3G1\u0001\u0002J\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\"T\u0003\u0002C\u0014\tc!q!a\u00125\u0005\u0004\tI%A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t\t\u0006b\u000f\t\u0013\u0011ur'!AA\u0002\u0005m\u0012a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0005DA1\u0011qQAp\u0003#\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!Q\bC%\u0011%!i$OA\u0001\u0002\u0004\tYDA\u0002OS2\u001cr\u0001\u0003C(\u0007\u000f\f)\u0007E\u0003\u0002\bI\u000bY\u0005\u0006\u0002\u0005TA\u001911\u001d\u0005\u0002\r\u0015\fX/\u00197t)\u0011\u0011i\u0005\"\u0017\t\u000f\r\u001d!\u00021\u0001\u0002RQ!\u0011\u0011\u000bC/\u0011%!i$DA\u0001\u0002\u0004\tYDA\u0002USB,B\u0001b\u0019\u0005jM9q\u0002\"\u001a\u0004H\u0006\u0015\u0004#BA\u0004%\u0012\u001d\u0004\u0003BA\"\tS\"\u0001\"a\u0012\u0010\t\u000b\u0007\u0011\u0011J\u0001\u0005W\u0016L\b%\u0006\u0002\u0005h\u00051a/\u00197vK\u0002\"b\u0001b\u001d\u0005v\u0011]\u0004#BBr\u001f\u0011\u001d\u0004b\u0002B@)\u0001\u0007\u00111\b\u0005\b\u0005\u001f$\u0002\u0019\u0001C4\u0003%9\u0018\u000e\u001e5WC2,X-\u0006\u0003\u0005~\u0011\u0015E\u0003\u0002C@\t\u000f\u0003R\u0001\"!\u0010\t\u0007s1!a\u0002\u0001!\u0011\t\u0019\u0005\"\"\u0005\u000f\t-TC1\u0001\u0002J!9A\u0011R\u000bA\u0002\u0011\r\u0015!A:\u0016\t\u00115E1\u0013\u000b\u0007\t\u001f#)\nb&\u0011\u000b\r\rx\u0002\"%\u0011\t\u0005\rC1\u0013\u0003\b\u0003\u000f2\"\u0019AA%\u0011%\u0011yH\u0006I\u0001\u0002\u0004\tY\u0004C\u0005\u0003PZ\u0001\n\u00111\u0001\u0005\u0012V!Aq\u0003CN\t\u001d\t9e\u0006b\u0001\u0003\u0013*B\u0001b(\u0005$V\u0011A\u0011\u0015\u0016\u0005\tO\n)\nB\u0004\u0002Ha\u0011\r!!\u0013\u0015\t\u0005ECq\u0015\u0005\n\t{Y\u0012\u0011!a\u0001\u0003w!BA!\u0010\u0005,\"IAQH\u000f\u0002\u0002\u0003\u0007\u00111\b\t\u0005\u0003\u0007\"y\u000bB\u0004\u0002H\r\u0011\r!!\u0013\u0002\u0013MLgn\u001a7fi>tW\u0003\u0002C[\tw#b\u0001b.\u0005>\u0012}\u0006#BA\u0004%\u0012e\u0006\u0003BA\"\tw#q!a\u0012\u0005\u0005\u0004\tI\u0005C\u0004\u0003\u0000\u0011\u0001\r!a\u000f\t\u000f\t=G\u00011\u0001\u0005:V!A1\u0019Ce)\u0011!)\rb3\u0011\u000b\u0005\u001d!\u000bb2\u0011\t\u0005\rC\u0011\u001a\u0003\b\u0003\u000f*!\u0019AA%\u0011\u001d!i-\u0002a\u0001\t\u001f\fQ!\u001a7f[N\u0004b!a\u0004\u0005R\u0012U\u0017b\u0001Cj\u007f\nQAH]3qK\u0006$X\r\u001a \u0011\u0011\u0005=\u0011\u0011SA\u001e\t\u000f\fAA\u001a:p[V!A1\u001cCq)\u0011!i\u000e\":\u0011\u000b\u0005\u001d!\u000bb8\u0011\t\u0005\rC\u0011\u001d\u0003\b\tG4!\u0019AA%\u0005\u00051\u0006bBAB\r\u0001\u0007Aq\u001d\t\u0007\u0003\u000f\u000bI\t\";\u0011\u0011\u0005=\u0011\u0011SA\u001e\t?\f1AT5m\u0003\r!\u0016\u000e\u001d\t\u0004\u0007G|2#B\u0010\u0002\u000e\u0005UAC\u0001Cx\u0003!!xn\u0015;sS:<GC\u0001B\u001f+\u0011!Y0\"\u0001\u0015\r\u0011uX1AC\u0003!\u0015\u0019\u0019o\u0004C\u0000!\u0011\t\u0019%\"\u0001\u0005\u000f\u0005\u001d#E1\u0001\u0002J!9!q\u0010\u0012A\u0002\u0005m\u0002b\u0002BhE\u0001\u0007Aq`\u0001\bk:\f\u0007\u000f\u001d7z+\u0011)Y!b\u0005\u0015\t\u00155QQ\u0003\t\u0007\u0003\u001f\u0011I(b\u0004\u0011\u0011\u0005=\u0011\u0011SA\u001e\u000b#\u0001B!a\u0011\u0006\u0014\u00119\u0011qI\u0012C\u0002\u0005%\u0003\"CC\fG\u0005\u0005\t\u0019AC\r\u0003\rAH\u0005\r\t\u0006\u0007G|Q\u0011\u0003\u000b\u0003\u000b;\u0001BAa\u0010\u0006 %!Q\u0011\u0005B!\u0005\u0019y%M[3di\u0006\u0019!)\u001b8\u0011\u0007\r\r8hE\u0003<\u0003\u001b\t)\u0002\u0006\u0002\u0006&U!QQFC\u001a)))y#\"\u000e\u00068\u0015eRQ\b\t\u0006\u0007G,S\u0011\u0007\t\u0005\u0003\u0007*\u0019\u0004B\u0004\u0002Hy\u0012\r!!\u0013\t\u000f\r5g\b1\u0001\u0002<!91\u0011\u001b A\u0002\u0005m\u0002bBBk}\u0001\u0007Q1\b\t\u0006\u0003\u000f\u0011V\u0011\u0007\u0005\b\u00077t\u0004\u0019AC\u001e+\u0011)\t%b\u0014\u0015\t\u0015\rS\u0011\u000b\t\u0007\u0003\u001f\u0011I(\"\u0012\u0011\u0019\u0005=QqIA\u001e\u0003w)Y%b\u0013\n\u0007\u0015%sP\u0001\u0004UkBdW\r\u000e\t\u0006\u0003\u000f\u0011VQ\n\t\u0005\u0003\u0007*y\u0005B\u0004\u0002H}\u0012\r!!\u0013\t\u0013\u0015]q(!AA\u0002\u0015M\u0003#BBrK\u00155\u0013A\u00038fo\n+\u0018\u000e\u001c3feV!Q\u0011LC1+\t)Y\u0006\u0005\u0005\u0002D\u0006%WQLC2!!\ty!!%\u0002<\u0015}\u0003\u0003BA\"\u000bC\"q\u0001b9B\u0005\u0004\tI\u0005E\u0003\u0002\bI+y&A\u0005u_\u001a\u000b7\r^8ssV!Q\u0011NC;)\u0011)Y'\"\u001f\u0011\u0011\u0005\u001dUQNC9\u000boJ1!b\u001c~\u0005\u001d1\u0015m\u0019;pef\u0004\u0002\"a\u0004\u0002\u0012\u0006mR1\u000f\t\u0005\u0003\u0007*)\bB\u0004\u0005d\n\u0013\r!!\u0013\u0011\u000b\u0005\u001d!+b\u001d\t\u000f\u0015m$\t1\u0001\u0005\u0002\u0006)A-^7ns\u0006IAk\u001c$bGR|'/\u001f\t\u0004\u0007G$%!\u0003+p\r\u0006\u001cGo\u001c:z'\u001d!\u0015QBCC\u0003K\u0002\u0002\"a\"\u0006n\u0015\u001dU\u0011\u0012\t\t\u0003\u001f\t\t*a\u000f\u0002\u000eA)\u0011q\u0001*\u0002\u000eQ\u0011Qq\u0010\u000b\u0005\u000b\u0013+y\tC\u0004\u0006\u0012\u001a\u0003\r!b%\u0002\u0005%$\bCBAD\u0003\u0013+9)\u0006\u0002\u0006\u0018BA\u00111YAe\u000b\u000f+I\tK\u0004E\u000b7\u0013y-\")\u0011\t\u0005=QQT\u0005\u0004\u000b?{(\u0001E*fe&\fGNV3sg&|g.V%E=\u0005\u0019\u0001fB\"\u0006\u001c\n=W\u0011U\u0001\fi>\u0014U/\u001b7e\rJ|W.\u0006\u0003\u0006*\u0016UF\u0003BCV\u000bs\u0003\"\"a\"\u0006.\u0006ES\u0011WC\\\u0013\r)y+ \u0002\n\u0005VLG\u000e\u001a$s_6\u0004\u0002\"a\u0004\u0002\u0012\u0006mR1\u0017\t\u0005\u0003\u0007*)\fB\u0004\u0005d&\u0013\r!!\u0013\u0011\u000b\u0005\u001d!+b-\t\u000f\u0015m\u0016\n1\u0001\u0005\u0002\u00069a-Y2u_JL\u0018a\u0003+p\u0005VLG\u000e\u001a$s_6\u00042aa9L\u0005-!vNQ;jY\u00124%o\\7\u0014\u000b-\u000bi!\"2\u0011\u0015\u0005\u001dUQVA)\u000b\u000f+I\t\u0006\u0002\u0006@R!Q1ZCh)\u0011)I)\"4\t\u000f\u0015EU\n1\u0001\u0006\u0014\"9Aq['A\u0002\u0005EC\u0003BCL\u000b'Dq\u0001b6O\u0001\u0004\t\t&A\bji\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z+\u0011)I.\"9\u0016\u0005\u0015m\u0007\u0003CAD\u000b[*i.b9\u0011\u0011\u0005=\u0011\u0011SA\u001e\u000b?\u0004B!a\u0011\u0006b\u00129A1](C\u0002\u0005%\u0003#BA\u0004%\u0016}\u0017a\u00042vS2$gI]8n\u0013:$X*\u00199\u0016\t\u0015%X1`\u000b\u0003\u000bW\u0004\"\"a\"\u0006.\u00165Xq_C\u007fa\u0011)y/b=\u0011\u000b\u0005\u001d!+\"=\u0011\t\u0005\rS1\u001f\u0003\f\u000bk\u0004\u0016\u0011!A\u0001\u0006\u0003\tIEA\u0002`IE\u0002\u0002\"a\u0004\u0002\u0012\u0006mR\u0011 \t\u0005\u0003\u0007*Y\u0010B\u0004\u0005dB\u0013\r!!\u0013\u0011\u000b\u0005\u001d!+\"?"
)
public abstract class IntMap extends AbstractMap implements StrictOptimizedMapOps, Serializable {
   public static BuildFrom buildFromIntMap() {
      IntMap$ var10000 = IntMap$.MODULE$;
      return IntMap.ToBuildFrom$.MODULE$;
   }

   public static BuildFrom toBuildFrom(final IntMap$ factory) {
      IntMap$ var10000 = IntMap$.MODULE$;
      return IntMap.ToBuildFrom$.MODULE$;
   }

   public static Factory toFactory(final IntMap$ dummy) {
      IntMap$ var10000 = IntMap$.MODULE$;
      return IntMap.ToFactory$.MODULE$;
   }

   public static Builder newBuilder() {
      IntMap$ var10000 = IntMap$.MODULE$;
      return new ImmutableBuilder() {
         public <undefinedtype> addOne(final Tuple2 elem) {
            this.elems_$eq(((IntMap)this.elems()).$plus(elem));
            return this;
         }

         public {
            IntMap$ var10001 = IntMap$.MODULE$;
         }
      };
   }

   public static IntMap from(final IterableOnce coll) {
      return IntMap$.MODULE$.from(coll);
   }

   public static IntMap singleton(final int key, final Object value) {
      IntMap$ var10000 = IntMap$.MODULE$;
      return new Tip(key, value);
   }

   public IterableOps map(final Function1 f) {
      return scala.collection.StrictOptimizedMapOps.map$(this, f);
   }

   public IterableOps flatMap(final Function1 f) {
      return scala.collection.StrictOptimizedMapOps.flatMap$(this, f);
   }

   public IterableOps collect(final PartialFunction pf) {
      return scala.collection.StrictOptimizedMapOps.collect$(this, pf);
   }

   /** @deprecated */
   public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
      return scala.collection.StrictOptimizedMapOps.$plus$(this, elem1, elem2, elems);
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public Object map(final Function1 f) {
      return StrictOptimizedIterableOps.map$(this, f);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public Object flatMap(final Function1 f) {
      return StrictOptimizedIterableOps.flatMap$(this, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
   }

   public Object collect(final PartialFunction pf) {
      return StrictOptimizedIterableOps.collect$(this, pf);
   }

   public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
   }

   public Object flatten(final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
   }

   public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
   }

   public Object zip(final IterableOnce that) {
      return StrictOptimizedIterableOps.zip$(this, that);
   }

   public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
   }

   public Object zipWithIndex() {
      return StrictOptimizedIterableOps.zipWithIndex$(this);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return StrictOptimizedIterableOps.scanLeft$(this, z, op);
   }

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Object takeRight(final int n) {
      return StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public Object dropRight(final int n) {
      return StrictOptimizedIterableOps.dropRight$(this, n);
   }

   public IntMap fromSpecific(final IterableOnce coll) {
      return this.intMapFrom(coll);
   }

   public IntMap intMapFrom(final IterableOnce coll) {
      IntMap$ var10000 = IntMap$.MODULE$;
      Builder b = new ImmutableBuilder() {
         public <undefinedtype> addOne(final Tuple2 elem) {
            this.elems_$eq(((IntMap)this.elems()).$plus(elem));
            return this;
         }

         public {
            IntMap$ var10001 = IntMap$.MODULE$;
         }
      };
      int sizeHint_delta = 0;
      Builder.sizeHint$(b, coll, sizeHint_delta);
      b.addAll(coll);
      return (IntMap)b.result();
   }

   public Builder newSpecificBuilder() {
      return new ImmutableBuilder() {
         public <undefinedtype> addOne(final Tuple2 elem) {
            this.elems_$eq(((IntMap)this.elems()).$plus(elem));
            return this;
         }
      };
   }

   public IntMap empty() {
      return IntMap.Nil$.MODULE$;
   }

   public List toList() {
      ListBuffer buffer = new ListBuffer();
      Function1 foreach_f = (x$1) -> (ListBuffer)buffer.$plus$eq(x$1);

      IntMap foreach_this;
      IntMap foreach_right;
      for(foreach_this = this; foreach_this instanceof Bin; foreach_this = foreach_right) {
         Bin var4 = (Bin)foreach_this;
         IntMap foreach_left = var4.left();
         foreach_right = var4.right();
         foreach_left.foreach(foreach_f);
         foreach_f = foreach_f;
      }

      if (foreach_this instanceof Tip) {
         Tip var7 = (Tip)foreach_this;
         int foreach_key = var7.key();
         Object foreach_value = var7.value();
         Tuple2 var10 = new Tuple2(foreach_key, foreach_value);
         ListBuffer var10000 = (ListBuffer)buffer.addOne(var10);
      } else if (!IntMap.Nil$.MODULE$.equals(foreach_this)) {
         throw new MatchError(foreach_this);
      }

      foreach_this = null;
      foreach_f = null;
      Object var13 = null;
      Object var14 = null;
      foreach_right = null;
      Object var16 = null;
      Object var17 = null;
      return buffer.toList();
   }

   public Iterator iterator() {
      if (IntMap.Nil$.MODULE$.equals(this)) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new IntMapEntryIterator(this);
      }
   }

   public final void foreach(final Function1 f) {
      while(this instanceof Bin) {
         Bin var2 = (Bin)this;
         IntMap left = var2.left();
         IntMap right = var2.right();
         left.foreach(f);
         f = f;
         this = right;
      }

      if (this instanceof Tip) {
         Tip var5 = (Tip)this;
         int key = var5.key();
         Object value = var5.value();
         f.apply(new Tuple2(key, value));
      } else if (!IntMap.Nil$.MODULE$.equals(this)) {
         throw new MatchError(this);
      }
   }

   public void foreachEntry(final Function2 f) {
      if (this instanceof Bin) {
         Bin var2 = (Bin)this;
         IntMap left = var2.left();
         IntMap right = var2.right();
         left.foreachEntry(f);
         right.foreachEntry(f);
      } else if (this instanceof Tip) {
         Tip var5 = (Tip)this;
         int key = var5.key();
         Object value = var5.value();
         f.apply(key, value);
      } else if (!IntMap.Nil$.MODULE$.equals(this)) {
         throw new MatchError(this);
      }
   }

   public Iterator keysIterator() {
      if (IntMap.Nil$.MODULE$.equals(this)) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new IntMapKeyIterator(this);
      }
   }

   public final void foreachKey(final Function1 f) {
      while(this instanceof Bin) {
         Bin var2 = (Bin)this;
         IntMap left = var2.left();
         IntMap right = var2.right();
         left.foreachKey(f);
         f = f;
         this = right;
      }

      if (this instanceof Tip) {
         int key = ((Tip)this).key();
         f.apply(key);
      } else if (!IntMap.Nil$.MODULE$.equals(this)) {
         throw new MatchError(this);
      }
   }

   public Iterator valuesIterator() {
      if (IntMap.Nil$.MODULE$.equals(this)) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new IntMapValueIterator(this);
      }
   }

   public final void foreachValue(final Function1 f) {
      while(this instanceof Bin) {
         Bin var2 = (Bin)this;
         IntMap left = var2.left();
         IntMap right = var2.right();
         left.foreachValue(f);
         f = f;
         this = right;
      }

      if (this instanceof Tip) {
         Object value = ((Tip)this).value();
         f.apply(value);
      } else if (!IntMap.Nil$.MODULE$.equals(this)) {
         throw new MatchError(this);
      }
   }

   public String className() {
      return "IntMap";
   }

   public boolean isEmpty() {
      return this == IntMap.Nil$.MODULE$;
   }

   public int knownSize() {
      return this.isEmpty() ? 0 : -1;
   }

   public IntMap filter(final Function1 f) {
      if (this instanceof Bin) {
         Bin var2 = (Bin)this;
         int prefix = var2.prefix();
         int mask = var2.mask();
         IntMap left = var2.left();
         IntMap right = var2.right();
         IntMap var10000 = left.filter(f);
         IntMap var11 = right.filter(f);
         IntMap newleft = var10000;
         return left == newleft && right == var11 ? this : IntMapUtils$.MODULE$.bin(prefix, mask, newleft, var11);
      } else if (this instanceof Tip) {
         Tip var8 = (Tip)this;
         int key = var8.key();
         Object value = var8.value();
         return (IntMap)(BoxesRunTime.unboxToBoolean(f.apply(new Tuple2(key, value))) ? this : IntMap.Nil$.MODULE$);
      } else if (IntMap.Nil$.MODULE$.equals(this)) {
         return IntMap.Nil$.MODULE$;
      } else {
         throw new MatchError(this);
      }
   }

   public IntMap transform(final Function2 f) {
      if (this instanceof Bin) {
         Bin var2 = (Bin)this;
         IntMap left = var2.left();
         IntMap right = var2.right();
         return var2.bin(left.transform(f), right.transform(f));
      } else if (this instanceof Tip) {
         Tip var5 = (Tip)this;
         int key = var5.key();
         Object value = var5.value();
         return var5.withValue(f.apply(key, value));
      } else if (IntMap.Nil$.MODULE$.equals(this)) {
         return IntMap.Nil$.MODULE$;
      } else {
         throw new MatchError(this);
      }
   }

   public final int size() {
      if (IntMap.Nil$.MODULE$.equals(this)) {
         return 0;
      } else if (this instanceof Tip) {
         return 1;
      } else if (this instanceof Bin) {
         Bin var1 = (Bin)this;
         IntMap left = var1.left();
         IntMap right = var1.right();
         return left.size() + right.size();
      } else {
         throw new MatchError(this);
      }
   }

   public final Option get(final int key) {
      while(this instanceof Bin) {
         Bin var2 = (Bin)this;
         int mask = var2.mask();
         IntMap left = var2.left();
         IntMap right = var2.right();
         if (BitOperations.Int.zero$(IntMapUtils$.MODULE$, key, mask)) {
            key = key;
            this = left;
         } else {
            key = key;
            this = right;
         }
      }

      if (this instanceof Tip) {
         Tip var6 = (Tip)this;
         int key2 = var6.key();
         Object value = var6.value();
         if (key == key2) {
            return new Some(value);
         } else {
            return None$.MODULE$;
         }
      } else if (IntMap.Nil$.MODULE$.equals(this)) {
         return None$.MODULE$;
      } else {
         throw new MatchError(this);
      }
   }

   public final Object getOrElse(final int key, final Function0 default) {
      while(!IntMap.Nil$.MODULE$.equals(this)) {
         if (this instanceof Tip) {
            Tip var3 = (Tip)this;
            int key2 = var3.key();
            Object value = var3.value();
            if (key == key2) {
               return value;
            }

            return default.apply();
         }

         if (!(this instanceof Bin)) {
            throw new MatchError(this);
         }

         Bin var6 = (Bin)this;
         int mask = var6.mask();
         IntMap left = var6.left();
         IntMap right = var6.right();
         if (BitOperations.Int.zero$(IntMapUtils$.MODULE$, key, mask)) {
            default = default;
            key = key;
            this = left;
         } else {
            default = default;
            key = key;
            this = right;
         }
      }

      return default.apply();
   }

   public final Object apply(final int key) {
      while(this instanceof Bin) {
         Bin var2 = (Bin)this;
         int mask = var2.mask();
         IntMap left = var2.left();
         IntMap right = var2.right();
         if (BitOperations.Int.zero$(IntMapUtils$.MODULE$, key, mask)) {
            key = key;
            this = left;
         } else {
            key = key;
            this = right;
         }
      }

      if (this instanceof Tip) {
         Tip var6 = (Tip)this;
         int key2 = var6.key();
         Object value = var6.value();
         if (key == key2) {
            return value;
         } else {
            throw new IllegalArgumentException("Key not found");
         }
      } else if (IntMap.Nil$.MODULE$.equals(this)) {
         throw new IllegalArgumentException("key not found");
      } else {
         throw new MatchError(this);
      }
   }

   public IntMap $plus(final Tuple2 kv) {
      return this.updated(kv._1$mcI$sp(), kv._2());
   }

   public IntMap updated(final int key, final Object value) {
      if (this instanceof Bin) {
         Bin var3 = (Bin)this;
         int prefix = var3.prefix();
         int mask = var3.mask();
         IntMap left = var3.left();
         IntMap right = var3.right();
         if (!BitOperations.Int.hasMatch$(IntMapUtils$.MODULE$, key, prefix, mask)) {
            return IntMapUtils$.MODULE$.join(key, new Tip(key, value), prefix, this);
         } else {
            return BitOperations.Int.zero$(IntMapUtils$.MODULE$, key, mask) ? new Bin(prefix, mask, left.updated(key, value), right) : new Bin(prefix, mask, left, right.updated(key, value));
         }
      } else if (this instanceof Tip) {
         int key2 = ((Tip)this).key();
         return (IntMap)(key == key2 ? new Tip(key, value) : IntMapUtils$.MODULE$.join(key, new Tip(key, value), key2, this));
      } else if (IntMap.Nil$.MODULE$.equals(this)) {
         return new Tip(key, value);
      } else {
         throw new MatchError(this);
      }
   }

   public IntMap map(final Function1 f) {
      return this.intMapFrom(new View.Map(this, f));
   }

   public IntMap flatMap(final Function1 f) {
      return this.intMapFrom(new View.FlatMap(this, f));
   }

   public IntMap concat(final IterableOnce that) {
      return (IntMap)StrictOptimizedMapOps.concat$(this, that);
   }

   public IntMap $plus$plus(final IterableOnce that) {
      return this.concat(that);
   }

   public IntMap collect(final PartialFunction pf) {
      IntMap$ var10000 = IntMap$.MODULE$;
      Builder strictOptimizedCollect_b = new ImmutableBuilder() {
         public <undefinedtype> addOne(final Tuple2 elem) {
            this.elems_$eq(((IntMap)this.elems()).$plus(elem));
            return this;
         }

         public {
            IntMap$ var10001 = IntMap$.MODULE$;
         }
      };
      Object strictOptimizedCollect_marker = Statics.pfMarker;
      Iterator strictOptimizedCollect_it = this.iterator();

      while(strictOptimizedCollect_it.hasNext()) {
         Object strictOptimizedCollect_elem = strictOptimizedCollect_it.next();
         Object strictOptimizedCollect_v = pf.applyOrElse(strictOptimizedCollect_elem, StrictOptimizedIterableOps::$anonfun$strictOptimizedCollect$1);
         if (strictOptimizedCollect_marker != strictOptimizedCollect_v) {
            ((<undefinedtype>)strictOptimizedCollect_b).addOne((Tuple2)strictOptimizedCollect_v);
         }
      }

      return (IntMap)strictOptimizedCollect_b.result();
   }

   public IntMap updateWith(final int key, final Object value, final Function2 f) {
      if (this instanceof Bin) {
         Bin var4 = (Bin)this;
         int prefix = var4.prefix();
         int mask = var4.mask();
         IntMap left = var4.left();
         IntMap right = var4.right();
         if (!BitOperations.Int.hasMatch$(IntMapUtils$.MODULE$, key, prefix, mask)) {
            return IntMapUtils$.MODULE$.join(key, new Tip(key, value), prefix, this);
         } else {
            return BitOperations.Int.zero$(IntMapUtils$.MODULE$, key, mask) ? new Bin(prefix, mask, left.updateWith(key, value, f), right) : new Bin(prefix, mask, left, right.updateWith(key, value, f));
         }
      } else if (this instanceof Tip) {
         Tip var9 = (Tip)this;
         int key2 = var9.key();
         Object value2 = var9.value();
         return (IntMap)(key == key2 ? new Tip(key, f.apply(value2, value)) : IntMapUtils$.MODULE$.join(key, new Tip(key, value), key2, this));
      } else if (IntMap.Nil$.MODULE$.equals(this)) {
         return new Tip(key, value);
      } else {
         throw new MatchError(this);
      }
   }

   public IntMap removed(final int key) {
      if (this instanceof Bin) {
         Bin var2 = (Bin)this;
         int prefix = var2.prefix();
         int mask = var2.mask();
         IntMap left = var2.left();
         IntMap right = var2.right();
         if (!BitOperations.Int.hasMatch$(IntMapUtils$.MODULE$, key, prefix, mask)) {
            return this;
         } else if (BitOperations.Int.zero$(IntMapUtils$.MODULE$, key, mask)) {
            IntMapUtils$ var12 = IntMapUtils$.MODULE$;
            Integer $minus_key = key;
            if (left == null) {
               throw null;
            } else {
               MapOps var10003 = left.removed($minus_key);
               Object var10 = null;
               return var12.bin(prefix, mask, (IntMap)var10003, right);
            }
         } else {
            IntMapUtils$ var10000 = IntMapUtils$.MODULE$;
            Integer $minus_key = key;
            if (right == null) {
               throw null;
            } else {
               MapOps var10004 = right.removed($minus_key);
               Object var11 = null;
               return var10000.bin(prefix, mask, left, (IntMap)var10004);
            }
         }
      } else if (this instanceof Tip) {
         int key2 = ((Tip)this).key();
         return (IntMap)(key == key2 ? IntMap.Nil$.MODULE$ : this);
      } else if (IntMap.Nil$.MODULE$.equals(this)) {
         return IntMap.Nil$.MODULE$;
      } else {
         throw new MatchError(this);
      }
   }

   public IntMap modifyOrRemove(final Function2 f) {
      if (this instanceof Bin) {
         Bin var2 = (Bin)this;
         int prefix = var2.prefix();
         int mask = var2.mask();
         IntMap left = var2.left();
         IntMap right = var2.right();
         IntMap newleft = left.modifyOrRemove(f);
         IntMap newright = right.modifyOrRemove(f);
         return left == newleft && right == newright ? this : IntMapUtils$.MODULE$.bin(prefix, mask, newleft, newright);
      } else if (this instanceof Tip) {
         Tip var9 = (Tip)this;
         int key = var9.key();
         Object value = var9.value();
         Option var12 = (Option)f.apply(key, value);
         if (None$.MODULE$.equals(var12)) {
            return IntMap.Nil$.MODULE$;
         } else if (var12 instanceof Some) {
            Object value2 = ((Some)var12).value();
            return (IntMap)(value == value2 ? this : new Tip(key, value2));
         } else {
            throw new MatchError(var12);
         }
      } else if (IntMap.Nil$.MODULE$.equals(this)) {
         return IntMap.Nil$.MODULE$;
      } else {
         throw new MatchError(this);
      }
   }

   public IntMap unionWith(final IntMap that, final Function3 f) {
      Tuple2 var3 = new Tuple2(this, that);
      if (this instanceof Bin) {
         Bin var4 = (Bin)this;
         int p1 = var4.prefix();
         int m1 = var4.mask();
         IntMap l1 = var4.left();
         IntMap r1 = var4.right();
         if (that instanceof Bin) {
            Bin var9 = (Bin)that;
            int p2 = var9.prefix();
            int m2 = var9.mask();
            IntMap l2 = var9.left();
            IntMap r2 = var9.right();
            if (BitOperations.Int.unsignedCompare$(IntMapUtils$.MODULE$, m2, m1)) {
               if (!BitOperations.Int.hasMatch$(IntMapUtils$.MODULE$, p2, p1, m1)) {
                  return IntMapUtils$.MODULE$.join(p1, this, p2, var9);
               }

               if (BitOperations.Int.zero$(IntMapUtils$.MODULE$, p2, m1)) {
                  return new Bin(p1, m1, l1.unionWith(var9, f), r1);
               }

               return new Bin(p1, m1, l1, r1.unionWith(var9, f));
            }

            if (BitOperations.Int.unsignedCompare$(IntMapUtils$.MODULE$, m1, m2)) {
               if (!BitOperations.Int.hasMatch$(IntMapUtils$.MODULE$, p1, p2, m2)) {
                  return IntMapUtils$.MODULE$.join(p1, this, p2, var9);
               }

               if (BitOperations.Int.zero$(IntMapUtils$.MODULE$, p1, m2)) {
                  return new Bin(p2, m2, this.unionWith(l2, f), r2);
               }

               return new Bin(p2, m2, l2, this.unionWith(r2, f));
            }

            if (p1 == p2) {
               return new Bin(p1, m1, l1.unionWith(l2, f), r1.unionWith(r2, f));
            }

            return IntMapUtils$.MODULE$.join(p1, this, p2, var9);
         }
      }

      if (this instanceof Tip) {
         Tip var14 = (Tip)this;
         int key = var14.key();
         Object value = var14.value();
         return that.updateWith(key, value, (x, y) -> f.apply(BoxesRunTime.boxToInteger(key), y, x));
      } else if (that instanceof Tip) {
         Tip var17 = (Tip)that;
         int key = var17.key();
         Object value = var17.value();
         return this.updateWith(key, value, (x, y) -> f.apply(BoxesRunTime.boxToInteger(key), x, y));
      } else if (IntMap.Nil$.MODULE$.equals(this)) {
         return that;
      } else if (IntMap.Nil$.MODULE$.equals(that)) {
         return this;
      } else {
         throw new MatchError(var3);
      }
   }

   public IntMap intersectionWith(final IntMap that, final Function3 f) {
      if (this instanceof Bin) {
         Bin var3 = (Bin)this;
         int p1 = var3.prefix();
         int m1 = var3.mask();
         IntMap l1 = var3.left();
         IntMap r1 = var3.right();
         if (that instanceof Bin) {
            Bin var8 = (Bin)that;
            int p2 = var8.prefix();
            int m2 = var8.mask();
            IntMap l2 = var8.left();
            IntMap r2 = var8.right();
            if (BitOperations.Int.unsignedCompare$(IntMapUtils$.MODULE$, m2, m1)) {
               if (!BitOperations.Int.hasMatch$(IntMapUtils$.MODULE$, p2, p1, m1)) {
                  return IntMap.Nil$.MODULE$;
               }

               if (BitOperations.Int.zero$(IntMapUtils$.MODULE$, p2, m1)) {
                  return l1.intersectionWith(var8, f);
               }

               return r1.intersectionWith(var8, f);
            }

            if (m1 == m2) {
               return IntMapUtils$.MODULE$.bin(p1, m1, l1.intersectionWith(l2, f), r1.intersectionWith(r2, f));
            }

            if (!BitOperations.Int.hasMatch$(IntMapUtils$.MODULE$, p1, p2, m2)) {
               return IntMap.Nil$.MODULE$;
            }

            if (BitOperations.Int.zero$(IntMapUtils$.MODULE$, p1, m2)) {
               return this.intersectionWith(l2, f);
            }

            return this.intersectionWith(r2, f);
         }
      }

      if (this instanceof Tip) {
         Tip var13 = (Tip)this;
         int key = var13.key();
         Object value = var13.value();
         Option var16 = that.get(key);
         if (None$.MODULE$.equals(var16)) {
            return IntMap.Nil$.MODULE$;
         } else if (var16 instanceof Some) {
            Object value2 = ((Some)var16).value();
            return new Tip(key, f.apply(key, value, value2));
         } else {
            throw new MatchError(var16);
         }
      } else if (that instanceof Tip) {
         Tip var18 = (Tip)that;
         int key = var18.key();
         Object value = var18.value();
         Option var21 = this.get(key);
         if (None$.MODULE$.equals(var21)) {
            return IntMap.Nil$.MODULE$;
         } else if (var21 instanceof Some) {
            Object value2 = ((Some)var21).value();
            return new Tip(key, f.apply(key, value2, value));
         } else {
            throw new MatchError(var21);
         }
      } else {
         return IntMap.Nil$.MODULE$;
      }
   }

   public IntMap intersection(final IntMap that) {
      return this.intersectionWith(that, (key, value, value2) -> $anonfun$intersection$1(BoxesRunTime.unboxToInt(key), value, value2));
   }

   public IntMap $plus$plus(final IntMap that) {
      return this.unionWith(that, (key, x, y) -> $anonfun$$plus$plus$1(BoxesRunTime.unboxToInt(key), x, y));
   }

   public final int firstKey() {
      while(this instanceof Bin) {
         this = ((Bin)this).left();
      }

      if (this instanceof Tip) {
         return ((Tip)this).key();
      } else if (IntMap.Nil$.MODULE$.equals(this)) {
         throw new IllegalStateException("Empty set");
      } else {
         throw new MatchError(this);
      }
   }

   public final int lastKey() {
      while(this instanceof Bin) {
         this = ((Bin)this).right();
      }

      if (this instanceof Tip) {
         return ((Tip)this).key();
      } else if (IntMap.Nil$.MODULE$.equals(this)) {
         throw new IllegalStateException("Empty set");
      } else {
         throw new MatchError(this);
      }
   }

   public Object writeReplace() {
      IntMap$ var10002 = IntMap$.MODULE$;
      var10002 = IntMap$.MODULE$;
      return new DefaultSerializationProxy(IntMap.ToFactory$.MODULE$, this);
   }

   // $FF: synthetic method
   public static final Object $anonfun$intersection$1(final int key, final Object value, final Object value2) {
      return value;
   }

   // $FF: synthetic method
   public static final Object $anonfun$$plus$plus$1(final int key, final Object x, final Object y) {
      return y;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Nil$ extends IntMap implements Product {
      public static final Nil$ MODULE$ = new Nil$();

      static {
         Nil$ var10000 = MODULE$;
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public boolean equals(final Object that) {
         if (that == this) {
            return true;
         } else {
            return that instanceof IntMap ? false : scala.collection.Map.equals$(this, that);
         }
      }

      public String productPrefix() {
         return "Nil";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return new AbstractIterator(this) {
            private int c;
            private final int cmax;
            private final Product x$2;

            public boolean hasNext() {
               return this.c < this.cmax;
            }

            public Object next() {
               Object result = this.x$2.productElement(this.c);
               ++this.c;
               return result;
            }

            public {
               this.x$2 = x$2;
               this.c = 0;
               this.cmax = x$2.productArity();
            }
         };
      }
   }

   public static class Tip extends IntMap implements Product {
      private final int key;
      private final Object value;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int key() {
         return this.key;
      }

      public Object value() {
         return this.value;
      }

      public Tip withValue(final Object s) {
         return s == this.value() ? this : new Tip(this.key(), s);
      }

      public Tip copy(final int key, final Object value) {
         return new Tip(key, value);
      }

      public int copy$default$1() {
         return this.key();
      }

      public Object copy$default$2() {
         return this.value();
      }

      public String productPrefix() {
         return "Tip";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.key();
            case 1:
               return this.value();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new AbstractIterator(this) {
            private int c;
            private final int cmax;
            private final Product x$2;

            public boolean hasNext() {
               return this.c < this.cmax;
            }

            public Object next() {
               Object result = this.x$2.productElement(this.c);
               ++this.c;
               return result;
            }

            public {
               this.x$2 = x$2;
               this.c = 0;
               this.cmax = x$2.productArity();
            }
         };
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "key";
            case 1:
               return "value";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public Tip(final int key, final Object value) {
         this.key = key;
         this.value = value;
      }
   }

   public static class Tip$ implements Serializable {
      public static final Tip$ MODULE$ = new Tip$();

      public final String toString() {
         return "Tip";
      }

      public Tip apply(final int key, final Object value) {
         return new Tip(key, value);
      }

      public Option unapply(final Tip x$0) {
         return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple2(x$0.key(), x$0.value())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Tip$.class);
      }
   }

   public static class Bin extends IntMap implements Product {
      private final int prefix;
      private final int mask;
      private final IntMap left;
      private final IntMap right;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int prefix() {
         return this.prefix;
      }

      public int mask() {
         return this.mask;
      }

      public IntMap left() {
         return this.left;
      }

      public IntMap right() {
         return this.right;
      }

      public IntMap bin(final IntMap left, final IntMap right) {
         return this.left() == left && this.right() == right ? this : new Bin(this.prefix(), this.mask(), left, right);
      }

      public Bin copy(final int prefix, final int mask, final IntMap left, final IntMap right) {
         return new Bin(prefix, mask, left, right);
      }

      public int copy$default$1() {
         return this.prefix();
      }

      public int copy$default$2() {
         return this.mask();
      }

      public IntMap copy$default$3() {
         return this.left();
      }

      public IntMap copy$default$4() {
         return this.right();
      }

      public String productPrefix() {
         return "Bin";
      }

      public int productArity() {
         return 4;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.prefix();
            case 1:
               return this.mask();
            case 2:
               return this.left();
            case 3:
               return this.right();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new AbstractIterator(this) {
            private int c;
            private final int cmax;
            private final Product x$2;

            public boolean hasNext() {
               return this.c < this.cmax;
            }

            public Object next() {
               Object result = this.x$2.productElement(this.c);
               ++this.c;
               return result;
            }

            public {
               this.x$2 = x$2;
               this.c = 0;
               this.cmax = x$2.productArity();
            }
         };
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "prefix";
            case 1:
               return "mask";
            case 2:
               return "left";
            case 3:
               return "right";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public Bin(final int prefix, final int mask, final IntMap left, final IntMap right) {
         this.prefix = prefix;
         this.mask = mask;
         this.left = left;
         this.right = right;
      }
   }

   public static class Bin$ implements Serializable {
      public static final Bin$ MODULE$ = new Bin$();

      public final String toString() {
         return "Bin";
      }

      public Bin apply(final int prefix, final int mask, final IntMap left, final IntMap right) {
         return new Bin(prefix, mask, left, right);
      }

      public Option unapply(final Bin x$0) {
         return (Option)(x$0 == null ? None$.MODULE$ : new Some(new Tuple4(x$0.prefix(), x$0.mask(), x$0.left(), x$0.right())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Bin$.class);
      }
   }

   private static class ToFactory$ implements Factory, Serializable {
      public static final ToFactory$ MODULE$ = new ToFactory$();
      private static final long serialVersionUID = 3L;

      public IntMap fromSpecific(final IterableOnce it) {
         return IntMap$.MODULE$.from(it);
      }

      public Builder newBuilder() {
         IntMap$ var10000 = IntMap$.MODULE$;
         return new ImmutableBuilder() {
            public <undefinedtype> addOne(final Tuple2 elem) {
               this.elems_$eq(((IntMap)this.elems()).$plus(elem));
               return this;
            }

            public {
               IntMap$ var10001 = IntMap$.MODULE$;
            }
         };
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ToFactory$.class);
      }

      public ToFactory$() {
      }
   }

   private static class ToBuildFrom$ implements BuildFrom {
      public static final ToBuildFrom$ MODULE$ = new ToBuildFrom$();

      static {
         ToBuildFrom$ var10000 = MODULE$;
      }

      /** @deprecated */
      public Builder apply(final Object from) {
         return BuildFrom.apply$(this, from);
      }

      public Factory toFactory(final Object from) {
         return BuildFrom.toFactory$(this, from);
      }

      public IntMap fromSpecific(final Object from, final IterableOnce it) {
         return IntMap$.MODULE$.from(it);
      }

      public Builder newBuilder(final Object from) {
         IntMap$ var10000 = IntMap$.MODULE$;
         return new ImmutableBuilder() {
            public <undefinedtype> addOne(final Tuple2 elem) {
               this.elems_$eq(((IntMap)this.elems()).$plus(elem));
               return this;
            }

            public {
               IntMap$ var10001 = IntMap$.MODULE$;
            }
         };
      }

      public ToBuildFrom$() {
      }
   }
}
