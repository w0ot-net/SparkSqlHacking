package scala.xml.parsing;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.xml.dtd.ContentModel;

@ScalaSignature(
   bytes = "\u0006\u0005\u00155xA\u0003B\t\u0005'A\tAa\u0005\u0003 \u0019Q!1\u0005B\n\u0011\u0003\u0011\u0019B!\n\t\u000f\t=\u0012\u0001\"\u0001\u00034!9!QG\u0001\u0005\u0002\t]\u0002b\u0002B0\u0003\u0011%!\u0011\r\u0005\b\t#\fA\u0011\u0002Cj\r%!I.\u0001I\u0001$C!YnB\u0004\u00064\u0006A\t\u0001\":\u0007\u000f\u0011e\u0017\u0001#\u0001\u0005b\"9!q\u0006\u0005\u0005\u0002\u0011\rh!\u0003Ct\u0011A\u0005\u0019\u0011\u0005Cu\u0011\u001d\u00199G\u0003C\u0001\u0007SBqAa/\u000b\t\u000b\u001a\t\bC\u0005\u0005n*\u0011\rQ\"\u0001\u0004v\u001d9QQ\u0003\u0005\t\u0002\u0016-aaBC\u0003\u0011!\u0005Uq\u0001\u0005\b\u0005_yA\u0011AC\u0005\u0011%!io\u0004b\u0001\n\u0003\u001a)\b\u0003\u0005\u0005|>\u0001\u000b\u0011\u0002B%\u0011%\u0019\u0019iDA\u0001\n\u0003\u001a)\tC\u0005\u0004\b>\t\t\u0011\"\u0001\u0004\n\"I1\u0011S\b\u0002\u0002\u0013\u0005QQ\u0002\u0005\n\u0007?{\u0011\u0011!C!\u0007CC\u0011ba,\u0010\u0003\u0003%\t!\"\u0005\t\u0013\rmv\"!A\u0005B\ru\u0006\"\u0003Bp\u001f\u0005\u0005I\u0011\u0002Bq\u000f\u001d)9\u0002\u0003EA\ts4qa!'\t\u0011\u0003#\t\u0010C\u0004\u00030m!\t\u0001b>\t\u0013\u001158D1A\u0005B\rU\u0004\u0002\u0003C~7\u0001\u0006IA!\u0013\t\u0013\r\r5$!A\u0005B\r\u0015\u0005\"CBD7\u0005\u0005I\u0011ABE\u0011%\u0019\tjGA\u0001\n\u0003!i\u0010C\u0005\u0004 n\t\t\u0011\"\u0011\u0004\"\"I1qV\u000e\u0002\u0002\u0013\u0005Q\u0011\u0001\u0005\n\u0007w[\u0012\u0011!C!\u0007{C\u0011Ba8\u001c\u0003\u0003%IA!9\b\u000f\u0015e\u0001\u0002#!\u0006\u001c\u00199QQ\u0004\u0005\t\u0002\u0016}\u0001b\u0002B\u0018O\u0011\u0005Q\u0011\u0005\u0005\b\u0005w;C\u0011IB9\u0011%!io\nb\u0001\n\u0003\u0019)\b\u0003\u0005\u0005|\u001e\u0002\u000b\u0011\u0002B%\u0011%\u0019\u0019iJA\u0001\n\u0003\u001a)\tC\u0005\u0004\b\u001e\n\t\u0011\"\u0001\u0004\n\"I1\u0011S\u0014\u0002\u0002\u0013\u0005Q1\u0005\u0005\n\u0007?;\u0013\u0011!C!\u0007CC\u0011ba,(\u0003\u0003%\t!b\n\t\u0013\rmv%!A\u0005B\ru\u0006\"\u0003BpO\u0005\u0005I\u0011\u0002Bq\r\u0019)Y\u0003\u0003\"\u0006.!Q!qP\u001a\u0003\u0016\u0004%\t!b\f\t\u0015\r\u00153G!E!\u0002\u0013)\t\u0004C\u0004\u00030M\"\t!\"\u000f\t\u000f\tm6\u0007\"\u0011\u0004r!IAQA\u001a\u0002\u0002\u0013\u0005Qq\b\u0005\n\t\u001b\u0019\u0014\u0013!C\u0001\u000b\u0007B\u0011ba!4\u0003\u0003%\te!\"\t\u0013\r\u001d5'!A\u0005\u0002\r%\u0005\"CBIg\u0005\u0005I\u0011AC$\u0011%\u0019yjMA\u0001\n\u0003\u001a\t\u000bC\u0005\u00040N\n\t\u0011\"\u0001\u0006L!IA1G\u001a\u0002\u0002\u0013\u0005Sq\n\u0005\n\u0007w\u001b\u0014\u0011!C!\u0007{C\u0011\u0002\"\u000f4\u0003\u0003%\t%b\u0015\b\u0013\u0015]\u0003\"!A\t\u0002\u0015ec!CC\u0016\u0011\u0005\u0005\t\u0012AC.\u0011\u001d\u0011yc\u0011C\u0001\u000b?B\u0011Ba/D\u0003\u0003%)E!0\t\u0013\t%7)!A\u0005\u0002\u0016\u0005\u0004\"\u0003Bi\u0007\u0006\u0005I\u0011QC3\u0011%\u0011ynQA\u0001\n\u0013\u0011\tO\u0002\u0004\u0005`\"\u0011Uq\u0012\u0005\u000b\u0005\u007fJ%Q3A\u0005\u0002\u0015E\u0005BCB#\u0013\nE\t\u0015!\u0003\u0006\u0000!Q1qI%\u0003\u0016\u0004%\ta!\u0013\t\u0015\ru\u0018J!E!\u0002\u0013\u0019Y\u0005C\u0004\u00030%#\t!b%\t\u000f\tm\u0016\n\"\u0011\u0004r!IAQA%\u0002\u0002\u0013\u0005Q\u0011\u0014\u0005\n\t\u001bI\u0015\u0013!C\u0001\u000b?C\u0011\u0002\"\nJ#\u0003%\t\u0001b\n\t\u0013\r\r\u0015*!A\u0005B\r\u0015\u0005\"CBD\u0013\u0006\u0005I\u0011ABE\u0011%\u0019\t*SA\u0001\n\u0003)\u0019\u000bC\u0005\u0004 &\u000b\t\u0011\"\u0011\u0004\"\"I1qV%\u0002\u0002\u0013\u0005Qq\u0015\u0005\n\tgI\u0015\u0011!C!\u000bWC\u0011ba/J\u0003\u0003%\te!0\t\u0013\u0011e\u0012*!A\u0005B\u0015=vaBC6\u0011!\u0005QQ\u000e\u0004\b\t?D\u0001\u0012AC8\u0011\u001d\u0011y\u0003\u0018C\u0001\u000bcBqa!:]\t\u0003)\u0019\bC\u0005\u0003Jr\u000b\t\u0011\"!\u0006|!I!\u0011\u001b/\u0002\u0002\u0013\u0005U1\u0011\u0005\n\u0005?d\u0016\u0011!C\u0005\u0005CDqa!:\t\t\u0003)YIB\u0005\u0003\u0006\u0006\u0001\n1%\t\u0003\b\u001e9QqG\u0001\t\u0002\tEea\u0002BC\u0003!\u0005!Q\u0012\u0005\b\u0005_)G\u0011\u0001BH\r\u0019\u0011Y)\u001a\"\u00052\"Q!qZ4\u0003\u0016\u0004%\ta!\u001e\t\u0015\u0011MvM!E!\u0002\u0013\u0011I\u0005C\u0004\u00030\u001d$\t\u0001\".\t\u000f\tmv\r\"\u0011\u0004r!IAQA4\u0002\u0002\u0013\u0005A\u0011\u0018\u0005\n\t\u001b9\u0017\u0013!C\u0001\t{C\u0011ba!h\u0003\u0003%\te!\"\t\u0013\r\u001du-!A\u0005\u0002\r%\u0005\"CBIO\u0006\u0005I\u0011\u0001Ca\u0011%\u0019yjZA\u0001\n\u0003\u001a\t\u000bC\u0005\u00040\u001e\f\t\u0011\"\u0001\u0005F\"IA1G4\u0002\u0002\u0013\u0005C\u0011\u001a\u0005\n\u0007w;\u0017\u0011!C!\u0007{C\u0011\u0002\"\u000fh\u0003\u0003%\t\u0005\"4\b\u0013\tMU-!A\t\u0002\tUe!\u0003BFK\u0006\u0005\t\u0012\u0001BM\u0011\u001d\u0011yc\u001eC\u0001\u0005sC\u0011Ba/x\u0003\u0003%)E!0\t\u0013\t%w/!A\u0005\u0002\n-\u0007\"\u0003Bio\u0006\u0005I\u0011\u0011Bj\u0011%\u0011yn^A\u0001\n\u0013\u0011\tOB\u0004\u0003j\u0016\f\tCa;\t\u0015\t5XP!b\u0001\n\u0003\u0011y\u000f\u0003\u0006\u0003xv\u0014\t\u0011)A\u0005\u0005cDqAa\f~\t\u0003\u0011I\u0010C\u0004\u0003\u0000v$)a!\u0001\u0007\u000f\r-R-!\t\u0004.!Y1qFA\u0003\u0005\u0003\u0005\u000b\u0011BB\u0019\u0011!\u0011y#!\u0002\u0005\u0002\u0011}\u0002\u0002\u0003B^\u0003\u000b!)e!\u001d\t\u0011\u0011\r\u0013Q\u0001D\u0001\t\u000b:q\u0001\"(f\u0011\u0003\u0019\tCB\u0004\u0004\u001c\u0015D\ta!\b\t\u0011\t=\u0012\u0011\u0003C\u0001\u0007?A!B!3\u0002\u0012\u0005\u0005I\u0011QB\u0012\u0011)\u0011\t.!\u0005\u0002\u0002\u0013\u0005E\u0011\u0012\u0005\u000b\u0005?\f\t\"!A\u0005\n\t\u0005hABB\u000eK\n\u001b9\u0003C\u0006\u00040\u0005m!Q3A\u0005\u0002\u00115\u0003b\u0003C(\u00037\u0011\t\u0012)A\u0005\u0007cA\u0001Ba\f\u0002\u001c\u0011\u0005Aq\u000e\u0005\t\t\u0007\nY\u0002\"\u0011\u0005F!QAQAA\u000e\u0003\u0003%\t\u0001b\u001d\t\u0015\u00115\u00111DI\u0001\n\u0003!Y\u0006\u0003\u0006\u0004\u0004\u0006m\u0011\u0011!C!\u0007\u000bC!ba\"\u0002\u001c\u0005\u0005I\u0011ABE\u0011)\u0019\t*a\u0007\u0002\u0002\u0013\u0005Aq\u000f\u0005\u000b\u0007?\u000bY\"!A\u0005B\r\u0005\u0006BCBX\u00037\t\t\u0011\"\u0001\u0005|!QA1GA\u000e\u0003\u0003%\t\u0005b \t\u0015\rm\u00161DA\u0001\n\u0003\u001ai\f\u0003\u0006\u0005:\u0005m\u0011\u0011!C!\t\u0007;q\u0001b(f\u0011\u0003!\u0019JB\u0004\u0005J\u0015D\t\u0001b$\t\u0011\t=\u00121\bC\u0001\t#C!B!3\u0002<\u0005\u0005I\u0011\u0011CK\u0011)\u0011\t.a\u000f\u0002\u0002\u0013\u0005E\u0011\u0014\u0005\u000b\u0005?\fY$!A\u0005\n\t\u0005hA\u0002C%K\n#Y\u0005C\u0006\u00040\u0005\u0015#Q3A\u0005\u0002\u00115\u0003b\u0003C(\u0003\u000b\u0012\t\u0012)A\u0005\u0007cA\u0001Ba\f\u0002F\u0011\u0005A\u0011\u000b\u0005\t\t\u0007\n)\u0005\"\u0011\u0005F!QAQAA#\u0003\u0003%\t\u0001b\u0016\t\u0015\u00115\u0011QII\u0001\n\u0003!Y\u0006\u0003\u0006\u0004\u0004\u0006\u0015\u0013\u0011!C!\u0007\u000bC!ba\"\u0002F\u0005\u0005I\u0011ABE\u0011)\u0019\t*!\u0012\u0002\u0002\u0013\u0005Aq\f\u0005\u000b\u0007?\u000b)%!A\u0005B\r\u0005\u0006BCBX\u0003\u000b\n\t\u0011\"\u0001\u0005d!QA1GA#\u0003\u0003%\t\u0005b\u001a\t\u0015\rm\u0016QIA\u0001\n\u0003\u001ai\f\u0003\u0006\u0005:\u0005\u0015\u0013\u0011!C!\tW:q\u0001\")f\u0011\u0003!\u0019KB\u0004\u0004,\u0015D\t\u0001\"*\t\u0011\t=\u0012Q\rC\u0001\tOC\u0001b!:\u0002f\u0011\u0005A\u0011\u0016\u0005\b\u0007K,G\u0011\u0001CW\r\u0019\u0019)$\u0001\"\u00048!Y!qPA7\u0005+\u0007I\u0011AB\"\u0011-\u0019)%!\u001c\u0003\u0012\u0003\u0006IA!!\t\u0017\r\u001d\u0013Q\u000eBK\u0002\u0013\u00051\u0011\n\u0005\f\u0007{\fiG!E!\u0002\u0013\u0019Y\u0005\u0003\u0005\u00030\u00055D\u0011AB\u0000\u0011!\u0011Y,!\u001c\u0005B\rE\u0004B\u0003C\u0003\u0003[\n\t\u0011\"\u0001\u0005\b!QAQBA7#\u0003%\t\u0001b\u0004\t\u0015\u0011\u0015\u0012QNI\u0001\n\u0003!9\u0003\u0003\u0006\u0004\u0004\u00065\u0014\u0011!C!\u0007\u000bC!ba\"\u0002n\u0005\u0005I\u0011ABE\u0011)\u0019\t*!\u001c\u0002\u0002\u0013\u0005A1\u0006\u0005\u000b\u0007?\u000bi'!A\u0005B\r\u0005\u0006BCBX\u0003[\n\t\u0011\"\u0001\u00050!QA1GA7\u0003\u0003%\t\u0005\"\u000e\t\u0015\rm\u0016QNA\u0001\n\u0003\u001ai\f\u0003\u0006\u0005:\u00055\u0014\u0011!C!\tw9q!\".\u0002\u0011\u0003)9LB\u0004\u00046\u0005A\t!\"/\t\u0011\t=\u00121\u0013C\u0001\u000bwC\u0001b!:\u0002\u0014\u0012\u0005QQ\u0018\u0005\u000b\u0005\u0013\f\u0019*!A\u0005\u0002\u0016\u0005\u0007B\u0003Bi\u0003'\u000b\t\u0011\"!\u0006H\"Q!q\\AJ\u0003\u0003%IA!9\u0007\r\r5\u0013\u0001EB(\u0011!\u0011y#a(\u0005\u0002\rEsaBCh\u0003!\u000511\f\u0004\b\u0007\u001b\n\u0001\u0012AB,\u0011!\u0011y#!*\u0005\u0002\res\u0001CB/\u0003KC\tia\u0018\u0007\u0011\rU\u0013Q\u0015EA\u0007cD\u0001Ba\f\u0002,\u0012\u000511\u001f\u0005\t\u0005w\u000bY\u000b\"\u0011\u0004r!Q11QAV\u0003\u0003%\te!\"\t\u0015\r\u001d\u00151VA\u0001\n\u0003\u0019I\t\u0003\u0006\u0004\u0012\u0006-\u0016\u0011!C\u0001\u0007kD!ba(\u0002,\u0006\u0005I\u0011IBQ\u0011)\u0019y+a+\u0002\u0002\u0013\u00051\u0011 \u0005\u000b\u0007w\u000bY+!A\u0005B\ru\u0006B\u0003Bp\u0003W\u000b\t\u0011\"\u0003\u0003b\u001aQ11MAS!\u0003\r\tc!\u001a\t\u0011\r\u001d\u0014q\u0018C\u0001\u0007SB\u0001Ba/\u0002@\u0012\u00153\u0011\u000f\u0005\t\u0007g\nyL\"\u0001\u0004v\u001dA1q\\AS\u0011\u0003\u001b\tI\u0002\u0005\u0004z\u0005\u0015\u0006\u0012QB>\u0011!\u0011y#!3\u0005\u0002\r}\u0004\u0002CB:\u0003\u0013$\te!\u001e\t\u0015\r\r\u0015\u0011ZA\u0001\n\u0003\u001a)\t\u0003\u0006\u0004\b\u0006%\u0017\u0011!C\u0001\u0007\u0013C!b!%\u0002J\u0006\u0005I\u0011ABJ\u0011)\u0019y*!3\u0002\u0002\u0013\u00053\u0011\u0015\u0005\u000b\u0007_\u000bI-!A\u0005\u0002\rE\u0006BCB^\u0003\u0013\f\t\u0011\"\u0011\u0004>\"Q!q\\Ae\u0003\u0003%IA!9\b\u0011\r\u0005\u0018Q\u0015EA\u0007\u000b4\u0001ba0\u0002&\"\u00055\u0011\u0019\u0005\t\u0005_\ty\u000e\"\u0001\u0004D\"A11OAp\t\u0003\u001a)\b\u0003\u0006\u0004\u0004\u0006}\u0017\u0011!C!\u0007\u000bC!ba\"\u0002`\u0006\u0005I\u0011ABE\u0011)\u0019\t*a8\u0002\u0002\u0013\u00051q\u0019\u0005\u000b\u0007?\u000by.!A\u0005B\r\u0005\u0006BCBX\u0003?\f\t\u0011\"\u0001\u0004L\"Q11XAp\u0003\u0003%\te!0\t\u0015\t}\u0017q\\A\u0001\n\u0013\u0011\to\u0002\u0005\u0004d\u0006\u0015\u0006\u0012QBk\r!\u0019y-!*\t\u0002\u000eE\u0007\u0002\u0003B\u0018\u0003k$\taa5\t\u0011\rM\u0014Q\u001fC!\u0007kB!ba!\u0002v\u0006\u0005I\u0011IBC\u0011)\u00199)!>\u0002\u0002\u0013\u00051\u0011\u0012\u0005\u000b\u0007#\u000b)0!A\u0005\u0002\r]\u0007BCBP\u0003k\f\t\u0011\"\u0011\u0004\"\"Q1qVA{\u0003\u0003%\taa7\t\u0015\rm\u0016Q_A\u0001\n\u0003\u001ai\f\u0003\u0006\u0003`\u0006U\u0018\u0011!C\u0005\u0005CD\u0001b!:\u0002&\u0012\u00051q\u001d\u0005\b\u000b#\fA\u0011BCj\u0011\u001d)9.\u0001C\u0005\u000b3DqAa@\u0002\t\u0013)9/A\nFY\u0016lWM\u001c;D_:$XM\u001c;N_\u0012,GN\u0003\u0003\u0003\u0016\t]\u0011a\u00029beNLgn\u001a\u0006\u0005\u00053\u0011Y\"A\u0002y[2T!A!\b\u0002\u000bM\u001c\u0017\r\\1\u0011\u0007\t\u0005\u0012!\u0004\u0002\u0003\u0014\t\u0019R\t\\3nK:$8i\u001c8uK:$Xj\u001c3fYN\u0019\u0011Aa\n\u0011\t\t%\"1F\u0007\u0003\u00057IAA!\f\u0003\u001c\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0003\u0005?\t\u0011\u0003]1sg\u0016\u001cuN\u001c;f]Rlu\u000eZ3m)\u0011\u0011ID!\u0012\u0011\t\tm\"\u0011I\u0007\u0003\u0005{QAAa\u0010\u0003\u0018\u0005\u0019A\r\u001e3\n\t\t\r#Q\b\u0002\r\u0007>tG/\u001a8u\u001b>$W\r\u001c\u0005\b\u0005\u000f\u001a\u0001\u0019\u0001B%\u0003\u0015iw\u000eZ3m!\u0011\u0011YE!\u0017\u000f\t\t5#Q\u000b\t\u0005\u0005\u001f\u0012Y\"\u0004\u0002\u0003R)!!1\u000bB\u0019\u0003\u0019a$o\\8u}%!!q\u000bB\u000e\u0003\u0019\u0001&/\u001a3fM&!!1\fB/\u0005\u0019\u0019FO]5oO*!!q\u000bB\u000e\u0003=\u0019wN\u001c<feR,E.Z7f]R\u001cH\u0003\u0002B2\u0005{\u0002BA!\u001a\u0003r9!!q\rB7\u001d\u0011\u0011IGa\u001b\u000e\u0005\t]\u0011\u0002\u0002B \u0005/IAAa\u001c\u0003>\u0005a1i\u001c8uK:$Xj\u001c3fY&!!1\u000fB;\u0005\u0019\u0011VmZ#ya&!!q\u000fB=\u0005\u0011\u0011\u0015m]3\u000b\t\tm$QH\u0001\u0005S6\u0004H\u000eC\u0004\u0003\u0000\u0011\u0001\rA!!\u0002\u0011\u0015dW-\\3oiN\u00042Aa!d\u001b\u0005\t!\u0001C#mK6,g\u000e^:\u0014\u0007\r\u00149#\u000b\u0003dO\u0006\u0015!aB#mK6,g\u000e^\n\u0004K\n\u001dBC\u0001BI!\r\u0011\u0019)Z\u0001\b\u000b2,W.\u001a8u!\r\u00119j^\u0007\u0002KN)qOa'\u0003*BA!Q\u0014BR\u0005\u0013\u00129+\u0004\u0002\u0003 *!!\u0011\u0015B\u000e\u0003\u001d\u0011XO\u001c;j[\u0016LAA!*\u0003 \n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\u0007\t]u\r\u0005\u0003\u0003,\nUVB\u0001BW\u0015\u0011\u0011yK!-\u0002\u0005%|'B\u0001BZ\u0003\u0011Q\u0017M^1\n\t\t]&Q\u0016\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0005+\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005\u007f\u0003BA!1\u0003H6\u0011!1\u0019\u0006\u0005\u0005\u000b\u0014\t,\u0001\u0003mC:<\u0017\u0002\u0002B.\u0005\u0007\fQ!\u00199qYf$BAa*\u0003N\"9!q\u001a>A\u0002\t%\u0013\u0001\u00028b[\u0016\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003V\nm\u0007C\u0002B\u0015\u0005/\u0014I%\u0003\u0003\u0003Z\nm!AB(qi&|g\u000eC\u0005\u0003^n\f\t\u00111\u0001\u0003(\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t\r\b\u0003\u0002Ba\u0005KLAAa:\u0003D\n1qJ\u00196fGR\u0014Q\"T1os\u000e{W\u000e]1oS>t7cA?\u0003(\u0005I1/\u001a9be\u0006$xN]\u000b\u0003\u0005c\u0004BA!\u000b\u0003t&!!Q\u001fB\u000e\u0005\u0011\u0019\u0005.\u0019:\u0002\u0015M,\u0007/\u0019:bi>\u0014\b\u0005\u0006\u0003\u0003|\nu\bc\u0001BL{\"A!Q^A\u0001\u0001\u0004\u0011\t0A\u0003ta2LG\u000f\u0006\u0003\u0004\u0004\rU\u0001CBB\u0003\u0007\u001f\u0011IE\u0004\u0003\u0004\b\r-a\u0002\u0002B(\u0007\u0013I!A!\b\n\t\r5!1D\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\u0019\tba\u0005\u0003\t1K7\u000f\u001e\u0006\u0005\u0007\u001b\u0011Y\u0002\u0003\u0005\u0004\u0018\u0005\r\u0001\u0019\u0001B%\u0003\u0019\u0019HO]5oO&*Q0!\u0005\u0002<\t11\t[8jG\u0016\u001cb!!\u0005\u0003|\n%FCAB\u0011!\u0011\u00119*!\u0005\u0015\t\r\u0015Bq\u0011\t\u0005\u0005/\u000bYb\u0005\u0005\u0002\u001c\r%2\u0011HB !\u0011\u00119*!\u0002\u0003\t5\u000bg._\n\u0007\u0003\u000b\u00119C!!\u0002\u0011\rD\u0017\u000e\u001c3sK:\u0004ba!\u0002\u0004\u0010\rM\u0002\u0003\u0002BB\u0003[\u0012!a\u00119\u0014\u0011\u00055$qEB\u001d\u0007\u007f\u0001BA!\u000b\u0004<%!1Q\bB\u000e\u0005\u001d\u0001&o\u001c3vGR\u0004Ba!\u0002\u0004B%!!qWB\n+\t\u0011\t)A\u0005fY\u0016lWM\u001c;tA\u0005QqnY2veJ,gnY3\u0016\u0005\r-\u0003\u0003\u0002BB\u0003?\u0013!bT2dkJ\u0014XM\\2f'\u0011\tyJa\n\u0015\u0005\r-\u0013\u0006DAP\u0003W\u000bI-a8\u0002v\u0006}&\u0001B(oG\u0016\u001cB!!*\u0003(Q\u001111\f\t\u0005\u0005\u0007\u000b)+\u0001\u0003P]\u000e,\u0007\u0003BB1\u0003Wk!!!*\u0003\rMKwM\\3e'\u0011\tyla\u0013\u0002\r\u0011Jg.\u001b;%)\t\u0019Y\u0007\u0005\u0003\u0003*\r5\u0014\u0002BB8\u00057\u0011A!\u00168jiR\u0011!\u0011J\u0001\u0005g&<g.\u0006\u0002\u0003J%B\u0011qXAe\u0003?\f)P\u0001\u0007P]\u000e,w\n\u001d;j_:\fGn\u0005\u0006\u0002J\u000e-3QPB\u001d\u0007\u007f\u0001Ba!\u0019\u0002@R\u00111\u0011\u0011\t\u0005\u0007C\nI-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005\u007f\u000bA\u0002\u001d:pIV\u001cG/\u0011:jif,\"aa#\u0011\t\t%2QR\u0005\u0005\u0007\u001f\u0013YBA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0004\u0016\u000em\u0005\u0003\u0002B\u0015\u0007/KAa!'\u0003\u001c\t\u0019\u0011I\\=\t\u0015\ru\u00151[A\u0001\u0002\u0004\u0019Y)A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0007G\u0003ba!*\u0004,\u000eUUBABT\u0015\u0011\u0019IKa\u0007\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0004.\u000e\u001d&\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$Baa-\u0004:B!!\u0011FB[\u0013\u0011\u00199La\u0007\u0003\u000f\t{w\u000e\\3b]\"Q1QTAl\u0003\u0003\u0005\ra!&\u0002\u0011!\f7\u000f[\"pI\u0016$\"aa#\u0003\rI+\u0007/Z1u')\tyna\u0013\u0004~\re2q\b\u000b\u0003\u0007\u000b\u0004Ba!\u0019\u0002`R!1QSBe\u0011)\u0019i*!;\u0002\u0002\u0003\u000711\u0012\u000b\u0005\u0007g\u001bi\r\u0003\u0006\u0004\u001e\u00065\u0018\u0011!a\u0001\u0007+\u0013aBU3qK\u0006$x\n\u001d;j_:\fGn\u0005\u0006\u0002v\u000e-3QPB\u001d\u0007\u007f!\"a!6\u0011\t\r\u0005\u0014Q\u001f\u000b\u0005\u0007+\u001bI\u000e\u0003\u0006\u0004\u001e\u0006}\u0018\u0011!a\u0001\u0007\u0017#Baa-\u0004^\"Q1Q\u0014B\u0002\u0003\u0003\u0005\ra!&\u0002\u0019=s7-Z(qi&|g.\u00197\u0002\rI+\u0007/Z1u\u00039\u0011V\r]3bi>\u0003H/[8oC2\fQ\u0001]1sg\u0016$Ba!;\u0004pBA!\u0011FBv\u0005\u0013\u001aY%\u0003\u0003\u0004n\nm!A\u0002+va2,'\u0007\u0003\u0005\u0004\u0018\t%\u0001\u0019\u0001B%'!\tYka\u0013\u0004:\r}BCAB0)\u0011\u0019)ja>\t\u0015\ru\u0015QWA\u0001\u0002\u0004\u0019Y\t\u0006\u0003\u00044\u000em\bBCBO\u0003s\u000b\t\u00111\u0001\u0004\u0016\u0006YqnY2veJ,gnY3!)\u0019\u0019\u0019\u0004\"\u0001\u0005\u0004!A!qPA<\u0001\u0004\u0011\t\t\u0003\u0005\u0004H\u0005]\u0004\u0019AB&\u0003\u0011\u0019w\u000e]=\u0015\r\rMB\u0011\u0002C\u0006\u0011)\u0011y(a\u001f\u0011\u0002\u0003\u0007!\u0011\u0011\u0005\u000b\u0007\u000f\nY\b%AA\u0002\r-\u0013AD2paf$C-\u001a4bk2$H%M\u000b\u0003\t#QCA!!\u0005\u0014-\u0012AQ\u0003\t\u0005\t/!\t#\u0004\u0002\u0005\u001a)!A1\u0004C\u000f\u0003%)hn\u00195fG.,GM\u0003\u0003\u0005 \tm\u0011AC1o]>$\u0018\r^5p]&!A1\u0005C\r\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t!IC\u000b\u0003\u0004L\u0011MA\u0003BBK\t[A!b!(\u0002\u0006\u0006\u0005\t\u0019ABF)\u0011\u0019\u0019\f\"\r\t\u0015\ru\u0015\u0011RA\u0001\u0002\u0004\u0019)*\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003\u0002B`\toA!b!(\u0002\f\u0006\u0005\t\u0019ABF\u0003\u0019)\u0017/^1mgR!11\u0017C\u001f\u0011)\u0019i*a$\u0002\u0002\u0003\u00071Q\u0013\u000b\u0005\u0007S!\t\u0005\u0003\u0005\u00040\u0005%\u0001\u0019AB\u0019\u0003%\u0019w.\u001c9b]&|g.\u0006\u0002\u0003|&2\u0011QAA\u000e\u0003\u000b\u0012\u0001bU3rk\u0016t7-Z\n\t\u0003\u000b\u001aIc!\u000f\u0004@U\u00111\u0011G\u0001\nG\"LG\u000e\u001a:f]\u0002\"B\u0001b\u0015\u0005VA!!qSA#\u0011!\u0019y#a\u0013A\u0002\rEB\u0003\u0002C*\t3B!ba\f\u0002PA\u0005\t\u0019AB\u0019+\t!iF\u000b\u0003\u00042\u0011MA\u0003BBK\tCB!b!(\u0002X\u0005\u0005\t\u0019ABF)\u0011\u0019\u0019\f\"\u001a\t\u0015\ru\u00151LA\u0001\u0002\u0004\u0019)\n\u0006\u0003\u0003@\u0012%\u0004BCBO\u0003;\n\t\u00111\u0001\u0004\fR!11\u0017C7\u0011)\u0019i*!\u0019\u0002\u0002\u0003\u00071Q\u0013\u000b\u0005\u0007K!\t\b\u0003\u0005\u00040\u0005\u0005\u0002\u0019AB\u0019)\u0011\u0019)\u0003\"\u001e\t\u0015\r=\u0012Q\u0005I\u0001\u0002\u0004\u0019\t\u0004\u0006\u0003\u0004\u0016\u0012e\u0004BCBO\u0003[\t\t\u00111\u0001\u0004\fR!11\u0017C?\u0011)\u0019i*!\r\u0002\u0002\u0003\u00071Q\u0013\u000b\u0005\u0005\u007f#\t\t\u0003\u0006\u0004\u001e\u0006M\u0012\u0011!a\u0001\u0007\u0017#Baa-\u0005\u0006\"Q1QTA\u001c\u0003\u0003\u0005\ra!&\t\u0011\r=\u0012Q\u0003a\u0001\u0007c!B\u0001b#\u0005\u000eB1!\u0011\u0006Bl\u0007cA!B!8\u0002\u0018\u0005\u0005\t\u0019AB\u0013'\u0019\tYDa?\u0003*R\u0011A1\u0013\t\u0005\u0005/\u000bY\u0004\u0006\u0003\u0005T\u0011]\u0005\u0002CB\u0018\u0003\u007f\u0001\ra!\r\u0015\t\u0011-E1\u0014\u0005\u000b\u0005;\f\t%!AA\u0002\u0011M\u0013AB\"i_&\u001cW-\u0001\u0005TKF,XM\\2f\u0003\u0011i\u0015M\\=\u0011\t\t]\u0015QM\n\u0005\u0003K\u00129\u0003\u0006\u0002\u0005$R!1\u0011\u0006CV\u0011!\u00199\"!\u001bA\u0002\t%C\u0003\u0002BA\t_C\u0001ba\u0006\u0002l\u0001\u0007!\u0011J\n\nO\n\u001d\"\u0011QB\u001d\u0007\u007f\tQA\\1nK\u0002\"BAa*\u00058\"9!q\u001a6A\u0002\t%C\u0003\u0002BT\twC\u0011Ba4m!\u0003\u0005\rA!\u0013\u0016\u0005\u0011}&\u0006\u0002B%\t'!Ba!&\u0005D\"I1Q\u00149\u0002\u0002\u0003\u000711\u0012\u000b\u0005\u0007g#9\rC\u0005\u0004\u001eJ\f\t\u00111\u0001\u0004\u0016R!!q\u0018Cf\u0011%\u0019ij]A\u0001\u0002\u0004\u0019Y\t\u0006\u0003\u00044\u0012=\u0007\"CBOk\u0006\u0005\t\u0019ABK\u0003E\u0019wN\u001c<feR|5mY;se\u0016t7-\u001a\u000b\u0007\u0005G\")\u000eb6\t\u000f\t}T\u00011\u0001\u0003\u0002\"91qI\u0003A\u0002\r-#aC\"p]R,g\u000e^*qK\u000e\u001c2A\u0002B\u0014S\u00151\u0011jM\u0014\u000b\u0005!\u0019\u0005.\u001b7ee\u0016t7c\u0001\u0005\u0003(Q\u0011AQ\u001d\t\u0004\u0005\u0007C!AB*j[BdWmE\u0003\u000b\u0005O!Y\u000fE\u0002\u0003\u0004\u001a\tQA^1mk\u0016L3AC\u000e\u0010'%Y\"q\u0005Cz\u0007s\u0019y\u0004E\u0002\u0005v*i\u0011\u0001\u0003\u000b\u0003\ts\u00042\u0001\">\u001c\u0003\u00191\u0018\r\\;fAQ!1Q\u0013C\u0000\u0011%\u0019i*IA\u0001\u0002\u0004\u0019Y\t\u0006\u0003\u00044\u0016\r\u0001\"CBOG\u0005\u0005\t\u0019ABK\u0005\u0015)U\u000e\u001d;z'%y!q\u0005Cz\u0007s\u0019y\u0004\u0006\u0002\u0006\fA\u0019AQ_\b\u0015\t\rUUq\u0002\u0005\n\u0007;+\u0012\u0011!a\u0001\u0007\u0017#Baa-\u0006\u0014!I1QT\f\u0002\u0002\u0003\u00071QS\u0001\u0006\u000b6\u0004H/_\u0001\u0004\u0003:L\u0018A\u0002)D\t\u0006$\u0018\rE\u0002\u0005v\u001e\u0012a\u0001U\"ECR\f7#C\u0014\u0003(\u0011-8\u0011HB )\t)Y\u0002\u0006\u0003\u0004\u0016\u0016\u0015\u0002\"CBO]\u0005\u0005\t\u0019ABF)\u0011\u0019\u0019,\"\u000b\t\u0013\ru\u0005'!AA\u0002\rU%!B'jq\u0016$7#C\u001a\u0003(\u0011-8\u0011HB +\t)\t\u0004\u0005\u0004\u0004\u0006\r=Q1\u0007\t\u0004\u000bk9gb\u0001BBI\u0006AQ\t\\3nK:$8\u000f\u0006\u0003\u0006<\u0015u\u0002c\u0001C{g!9!q\u0010\u001cA\u0002\u0015EB\u0003BC\u001e\u000b\u0003B\u0011Ba 9!\u0003\u0005\r!\"\r\u0016\u0005\u0015\u0015#\u0006BC\u0019\t'!Ba!&\u0006J!I1Q\u0014\u001f\u0002\u0002\u0003\u000711\u0012\u000b\u0005\u0007g+i\u0005C\u0005\u0004\u001ez\n\t\u00111\u0001\u0004\u0016R!!qXC)\u0011%\u0019ijPA\u0001\u0002\u0004\u0019Y\t\u0006\u0003\u00044\u0016U\u0003\"CBO\u0003\u0006\u0005\t\u0019ABK\u0003\u0015i\u0015\u000e_3e!\r!)pQ\n\u0006\u0007\u0016u#\u0011\u0016\t\t\u0005;\u0013\u0019+\"\r\u0006<Q\u0011Q\u0011\f\u000b\u0005\u000bw)\u0019\u0007C\u0004\u0003\u0000\u0019\u0003\r!\"\r\u0015\t\u0015\u001dT\u0011\u000e\t\u0007\u0005S\u00119.\"\r\t\u0013\tuw)!AA\u0002\u0015m\u0012\u0001C\"iS2$'/\u001a8\u0011\u0007\u0011UHlE\u0003]\u0005O\u0011I\u000b\u0006\u0002\u0006nQ1QQOC<\u000bs\u00022\u0001\">J\u0011\u001d\u00199B\u0018a\u0001\u0005\u0013Bqaa\u0012_\u0001\u0004\u0019Y\u0005\u0006\u0004\u0006v\u0015uT\u0011\u0011\u0005\b\u0005\u007fz\u0006\u0019AC@!\u0011))$!\u0002\t\u000f\r\u001ds\f1\u0001\u0004LQ!QQQCE!\u0019\u0011ICa6\u0006\bBA!\u0011FBv\u000b\u007f\u001aY\u0005C\u0005\u0003^\u0002\f\t\u00111\u0001\u0006vQ!A1^CG\u0011\u001d\u00119E\u0019a\u0001\u0005\u0013\u001a\u0012\"\u0013B\u0014\tW\u001cIda\u0010\u0016\u0005\u0015}DCBC;\u000b++9\nC\u0004\u0003\u00009\u0003\r!b \t\u000f\r\u001dc\n1\u0001\u0004LQ1QQOCN\u000b;C\u0011Ba Q!\u0003\u0005\r!b \t\u0013\r\u001d\u0003\u000b%AA\u0002\r-SCACQU\u0011)y\bb\u0005\u0015\t\rUUQ\u0015\u0005\n\u0007;+\u0016\u0011!a\u0001\u0007\u0017#Baa-\u0006*\"I1QT,\u0002\u0002\u0003\u00071Q\u0013\u000b\u0005\u0005\u007f+i\u000bC\u0005\u0004\u001eb\u000b\t\u00111\u0001\u0004\fR!11WCY\u0011%\u0019iJWA\u0001\u0002\u0004\u0019)*A\u0006D_:$XM\u001c;Ta\u0016\u001c\u0017AA\"q!\u0011\u0011\u0019)a%\u0014\r\u0005M%q\u0005BU)\t)9\f\u0006\u0003\u00044\u0015}\u0006\u0002CB\f\u0003/\u0003\rA!\u0013\u0015\r\rMR1YCc\u0011!\u0011y(!'A\u0002\t\u0005\u0005\u0002CB$\u00033\u0003\raa\u0013\u0015\t\u0015%WQ\u001a\t\u0007\u0005S\u00119.b3\u0011\u0011\t%21\u001eBA\u0007\u0017B!B!8\u0002\u001c\u0006\u0005\t\u0019AB\u001a\u0003)y5mY;se\u0016t7-Z\u0001\u0010SN\u0004\u0016M]3oi\",7/\u001b>fIR!11WCk\u0011!\u00199Ba\u0003A\u0002\t%\u0013!\u0005:f[>4X\rU1sK:$\b.Z:fgR!!\u0011JCn\u0011!\u00199B!\u0004A\u0002\t%\u0003\u0006\u0002B\u0007\u000b?\u0004B!\"9\u0006d6\u0011AQD\u0005\u0005\u000bK$iBA\u0004uC&d'/Z2\u0015\r\r\rQ\u0011^Cv\u0011!\u00199Ba\u0004A\u0002\t%\u0003\u0002\u0003Bw\u0005\u001f\u0001\rA!="
)
public final class ElementContentModel {
   public static ContentModel parseContentModel(final String model) {
      return ElementContentModel$.MODULE$.parseContentModel(model);
   }

   public static class ContentSpec$ {
      public static final ContentSpec$ MODULE$ = new ContentSpec$();

      public ContentSpec parse(final String model) {
         String var10000 = ElementContentModel$ContentSpec$Empty$.MODULE$.value();
         if (var10000 == null) {
            if (model == null) {
               return ElementContentModel$ContentSpec$Empty$.MODULE$;
            }
         } else if (var10000.equals(model)) {
            return ElementContentModel$ContentSpec$Empty$.MODULE$;
         }

         var10000 = ElementContentModel$ContentSpec$Any$.MODULE$.value();
         if (var10000 == null) {
            if (model == null) {
               return ElementContentModel$ContentSpec$Any$.MODULE$;
            }
         } else if (var10000.equals(model)) {
            return ElementContentModel$ContentSpec$Any$.MODULE$;
         }

         Tuple2 var8 = ElementContentModel.Occurrence$.MODULE$.parse(model);
         if (var8 != null) {
            String parenthesized = (String)var8._1();
            Occurrence occurrence = (Occurrence)var8._2();
            if (parenthesized != null && occurrence != null) {
               Occurrence occurrence;
               String string;
               label69: {
                  Tuple2 var7 = new Tuple2(parenthesized, occurrence);
                  String parenthesized = (String)var7._1();
                  occurrence = (Occurrence)var7._2();
                  .MODULE$.require(ElementContentModel$.MODULE$.scala$xml$parsing$ElementContentModel$$isParenthesized(parenthesized));
                  string = ElementContentModel$.MODULE$.scala$xml$parsing$ElementContentModel$$removeParentheses(parenthesized);
                  ElementContentModel$Occurrence$Once$ var16 = ElementContentModel$Occurrence$Once$.MODULE$;
                  if (occurrence == null) {
                     if (var16 != null) {
                        break label69;
                     }
                  } else if (!occurrence.equals(var16)) {
                     break label69;
                  }

                  String var17 = ElementContentModel$ContentSpec$PCData$.MODULE$.value();
                  if (string == null) {
                     if (var17 == null) {
                        return ElementContentModel$ContentSpec$PCData$.MODULE$;
                     }
                  } else if (string.equals(var17)) {
                     return ElementContentModel$ContentSpec$PCData$.MODULE$;
                  }
               }

               ElementContentModel$Occurrence$RepeatOptional$ var18 = ElementContentModel$Occurrence$RepeatOptional$.MODULE$;
               if (occurrence == null) {
                  if (var18 != null) {
                     return ElementContentModel$ContentSpec$Children$.MODULE$.parse(string, occurrence);
                  }
               } else if (!occurrence.equals(var18)) {
                  return ElementContentModel$ContentSpec$Children$.MODULE$.parse(string, occurrence);
               }

               List choice = ElementContentModel$Elements$Choice$.MODULE$.split(string);
               if (choice.length() > 1) {
                  Object var22 = choice.head();
                  String var20 = ElementContentModel$ContentSpec$PCData$.MODULE$.value();
                  if (var22 == null) {
                     if (var20 == null) {
                        return new ElementContentModel$ContentSpec$Mixed(((List)choice.tail()).map((name) -> new ElementContentModel$Elements$Element(name)));
                     }
                  } else if (var22.equals(var20)) {
                     return new ElementContentModel$ContentSpec$Mixed(((List)choice.tail()).map((name) -> new ElementContentModel$Elements$Element(name)));
                  }
               }

               return ElementContentModel$ContentSpec$Children$.MODULE$.parse(string, occurrence);
            }
         }

         throw new MatchError(var8);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Elements$ {
      public static final Elements$ MODULE$ = new Elements$();

      public Elements parse(final String string) {
         return (Elements)(!ElementContentModel$.MODULE$.scala$xml$parsing$ElementContentModel$$isParenthesized(string) ? new ElementContentModel$Elements$Element(string) : ElementContentModel$Elements$Many$.MODULE$.parse(ElementContentModel$.MODULE$.scala$xml$parsing$ElementContentModel$$removeParentheses(string)));
      }
   }

   public static final class Cp implements Product, Serializable {
      private final Elements elements;
      private final Occurrence occurrence;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Elements elements() {
         return this.elements;
      }

      public Occurrence occurrence() {
         return this.occurrence;
      }

      public String toString() {
         return (new StringBuilder(0)).append(this.elements()).append(this.occurrence()).toString();
      }

      public Cp copy(final Elements elements, final Occurrence occurrence) {
         return new Cp(elements, occurrence);
      }

      public Elements copy$default$1() {
         return this.elements();
      }

      public Occurrence copy$default$2() {
         return this.occurrence();
      }

      public String productPrefix() {
         return "Cp";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.elements();
            case 1:
               return this.occurrence();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Cp;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "elements";
            case 1:
               return "occurrence";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public boolean equals(final Object x$1) {
         boolean var8;
         if (this != x$1) {
            label52: {
               if (x$1 instanceof Cp) {
                  label45: {
                     Cp var4 = (Cp)x$1;
                     Elements var10000 = this.elements();
                     Elements var5 = var4.elements();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label45;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label45;
                     }

                     Occurrence var7 = this.occurrence();
                     Occurrence var6 = var4.occurrence();
                     if (var7 == null) {
                        if (var6 == null) {
                           break label52;
                        }
                     } else if (var7.equals(var6)) {
                        break label52;
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

      public Cp(final Elements elements, final Occurrence occurrence) {
         this.elements = elements;
         this.occurrence = occurrence;
         Product.$init$(this);
      }
   }

   public static class Cp$ implements Serializable {
      public static final Cp$ MODULE$ = new Cp$();

      public Cp parse(final String string) {
         Tuple2 var4 = ElementContentModel.Occurrence$.MODULE$.parse(string);
         if (var4 != null) {
            String maybeParenthesized = (String)var4._1();
            Occurrence occurrence = (Occurrence)var4._2();
            if (maybeParenthesized != null && occurrence != null) {
               Tuple2 var3 = new Tuple2(maybeParenthesized, occurrence);
               String maybeParenthesized = (String)var3._1();
               Occurrence occurrence = (Occurrence)var3._2();
               return new Cp(ElementContentModel.Elements$.MODULE$.parse(maybeParenthesized), occurrence);
            }
         }

         throw new MatchError(var4);
      }

      public Cp apply(final Elements elements, final Occurrence occurrence) {
         return new Cp(elements, occurrence);
      }

      public Option unapply(final Cp x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.elements(), x$0.occurrence())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Cp$.class);
      }
   }

   public static class Occurrence {
   }

   public static class Occurrence$ {
      public static final Occurrence$ MODULE$ = new Occurrence$();

      public Tuple2 parse(final String string) {
         if (string.endsWith(ElementContentModel$Occurrence$OnceOptional$.MODULE$.sign())) {
            return new Tuple2(scala.collection.StringOps..MODULE$.init$extension(.MODULE$.augmentString(string)), ElementContentModel$Occurrence$OnceOptional$.MODULE$);
         } else if (string.endsWith(ElementContentModel$Occurrence$RepeatOptional$.MODULE$.sign())) {
            return new Tuple2(scala.collection.StringOps..MODULE$.init$extension(.MODULE$.augmentString(string)), ElementContentModel$Occurrence$RepeatOptional$.MODULE$);
         } else {
            return string.endsWith(ElementContentModel$Occurrence$Repeat$.MODULE$.sign()) ? new Tuple2(scala.collection.StringOps..MODULE$.init$extension(.MODULE$.augmentString(string)), ElementContentModel$Occurrence$Repeat$.MODULE$) : new Tuple2(string, ElementContentModel$Occurrence$Once$.MODULE$);
         }
      }
   }

   public interface ContentSpec {
   }

   public interface Elements {
   }
}
