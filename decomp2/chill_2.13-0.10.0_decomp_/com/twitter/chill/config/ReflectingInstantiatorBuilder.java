package com.twitter.chill.config;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.JavaConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\rMg\u0001B\u001c9\u0001\u0006C\u0001b\u0016\u0001\u0003\u0016\u0004%\t\u0001\u0017\u0005\tM\u0002\u0011\t\u0012)A\u00053\"A1\u000f\u0001BK\u0002\u0013\u0005A\u000f\u0003\u0005{\u0001\tE\t\u0015!\u0003v\u0011)\ti\u0001\u0001BK\u0002\u0013\u0005\u0011q\u0002\u0005\u000b\u0003O\u0001!\u0011#Q\u0001\n\u0005E\u0001BCA\u0019\u0001\tU\r\u0011\"\u0001\u00024!Q\u0011Q\t\u0001\u0003\u0012\u0003\u0006I!!\u000e\t\u0015\u0005\u001d\u0003A!f\u0001\n\u0003\tI\u0005\u0003\u0006\u0002\\\u0001\u0011\t\u0012)A\u0005\u0003\u0017B!\"!\u0018\u0001\u0005+\u0007I\u0011AA0\u0011)\t9\u0007\u0001B\tB\u0003%\u0011\u0011\r\u0005\u000b\u0003S\u0002!Q3A\u0005\u0002\u0005}\u0003BCA6\u0001\tE\t\u0015!\u0003\u0002b!9\u0011Q\u000e\u0001\u0005\u0002\u0005=\u0004bBAY\u0001\u0011\u0005\u00111\u0017\u0005\n\u0003w\u0003\u0011\u0011!C\u0001\u0003{C\u0011\"!4\u0001#\u0003%\t!a4\t\u0013\u0005\u0015\b!%A\u0005\u0002\u0005\u001d\b\"CAy\u0001E\u0005I\u0011AAz\u0011%\u0011I\u0001AI\u0001\n\u0003\u0011Y\u0001C\u0005\u0003\u0010\u0001\t\n\u0011\"\u0001\u0003\u0012!I!Q\u0003\u0001\u0012\u0002\u0013\u0005!q\u0003\u0005\n\u00057\u0001\u0011\u0013!C\u0001\u0005/A\u0011B!\b\u0001\u0003\u0003%\tEa\b\t\u0013\t\u001d\u0002!!A\u0005\u0002\t%\u0002\"\u0003B\u0019\u0001\u0005\u0005I\u0011\u0001B\u001a\u0011%\u0011I\u0004AA\u0001\n\u0003\u0012Y\u0004C\u0005\u0003J\u0001\t\t\u0011\"\u0001\u0003L!I!q\n\u0001\u0002\u0002\u0013\u0005#\u0011\u000b\u0005\n\u0005+\u0002\u0011\u0011!C!\u0005/B\u0011B!\u0017\u0001\u0003\u0003%\tEa\u0017\t\u0013\tu\u0003!!A\u0005B\t}s!\u0003B2q\u0005\u0005\t\u0012\u0001B3\r!9\u0004(!A\t\u0002\t\u001d\u0004bBA7G\u0011\u0005!Q\u0016\u0005\n\u00053\u001a\u0013\u0011!C#\u00057B\u0011Ba,$\u0003\u0003%\tI!-\t\u0013\t=8%%A\u0005\u0002\tE\b\"\u0003B~GE\u0005I\u0011\u0001B\u007f\u0011%\u00199aII\u0001\n\u0003\u0019I\u0001C\u0005\u0004\u0018\r\n\n\u0011\"\u0001\u0004\u001a!I1qE\u0012\u0012\u0002\u0013\u00051\u0011\u0006\u0005\n\u0007o\u0019\u0013\u0013!C\u0001\u0005/A\u0011b!\u000f$#\u0003%\tAa\u0006\t\u0013\rm2%!A\u0005\u0002\u000eu\u0002\"CB?GE\u0005I\u0011AB@\u0011%\u0019IiII\u0001\n\u0003\u0019Y\tC\u0005\u0004\u0016\u000e\n\n\u0011\"\u0001\u0004\u0018\"I1QU\u0012\u0012\u0002\u0013\u00051q\u0015\u0005\n\u0007k\u001b\u0013\u0013!C\u0001\u0007oC\u0011b!2$#\u0003%\tAa\u0006\t\u0013\r\u001d7%%A\u0005\u0002\t]\u0001\"CBeG\u0005\u0005I\u0011BBf\u0005u\u0011VM\u001a7fGRLgnZ%ogR\fg\u000e^5bi>\u0014()^5mI\u0016\u0014(BA\u001d;\u0003\u0019\u0019wN\u001c4jO*\u00111\bP\u0001\u0006G\"LG\u000e\u001c\u0006\u0003{y\nq\u0001^<jiR,'OC\u0001@\u0003\r\u0019w.\\\u0002\u0001'\u0011\u0001!\tS&\u0011\u0005\r3U\"\u0001#\u000b\u0003\u0015\u000bQa]2bY\u0006L!a\u0012#\u0003\r\u0005s\u0017PU3g!\t\u0019\u0015*\u0003\u0002K\t\n9\u0001K]8ek\u000e$\bC\u0001'U\u001d\ti%K\u0004\u0002O#6\tqJ\u0003\u0002Q\u0001\u00061AH]8pizJ\u0011!R\u0005\u0003'\u0012\u000bq\u0001]1dW\u0006<W-\u0003\u0002V-\na1+\u001a:jC2L'0\u00192mK*\u00111\u000bR\u0001\nWJLxn\u00117bgN,\u0012!\u0017\u0019\u00035\u0012\u00042aW0c\u001d\taV\f\u0005\u0002O\t&\u0011a\fR\u0001\u0007!J,G-\u001a4\n\u0005\u0001\f'!B\"mCN\u001c(B\u00010E!\t\u0019G\r\u0004\u0001\u0005\u0013\u0015\u0014\u0011\u0011!A\u0001\u0006\u00039'aA0%c\u0005Q1N]=p\u00072\f7o\u001d\u0011\u0012\u0005!\\\u0007CA\"j\u0013\tQGIA\u0004O_RD\u0017N\\4\u0011\u00051\fX\"A7\u000b\u00059|\u0017\u0001B6ss>T!\u0001\u001d \u0002!\u0015\u001cx\u000e^3sS\u000e\u001cxN\u001a;xCJ,\u0017B\u0001:n\u0005\u0011Y%/_8\u00023%t7\u000f^1oi&\fGo\u001c:TiJ\fG/Z4z\u00072\f7o]\u000b\u0002kB\u0012a\u000f\u001f\t\u00047~;\bCA2y\t%IH!!A\u0001\u0002\u000b\u00051PA\u0002`II\n!$\u001b8ti\u0006tG/[1u_J\u001cFO]1uK\u001eL8\t\\1tg\u0002\n\"\u0001\u001b?\u0011\u0007u\fI!D\u0001\u007f\u0015\ry\u0018\u0011A\u0001\tgR\u0014\u0018\r^3hs*!\u00111AA\u0003\u0003%y'M[3oKNL7O\u0003\u0002\u0002\b\u0005\u0019qN]4\n\u0007\u0005-aP\u0001\u000bJ]N$\u0018M\u001c;jCR|'o\u0015;sCR,w-_\u0001\bG2\f7o]3t+\t\t\t\u0002E\u0003M\u0003'\t9\"C\u0002\u0002\u0016Y\u0013\u0001\"\u0013;fe\u0006\u0014G.\u001a\u0019\u0005\u00033\t\u0019\u0003\u0005\u0004\u0002\u001c\u0005u\u0011\u0011E\u0007\u0002u%\u0019\u0011q\u0004\u001e\u0003\u001d\rc\u0017m]:SK\u001eL7\u000f\u001e:beB\u00191-a\t\u0005\u0017\u0005\u0015b!!A\u0001\u0002\u000b\u0005\u0011\u0011\u0006\u0002\u0004?\u0012\u001a\u0014\u0001C2mCN\u001cXm\u001d\u0011\u0012\u0007!\fY\u0003E\u0002D\u0003[I1!a\fE\u0005\r\te._\u0001\fg\u0016\u0014\u0018.\u00197ju\u0016\u00148/\u0006\u0002\u00026A)A*a\u0005\u00028A\"\u0011\u0011HA!!\u0019\tY\"a\u000f\u0002@%\u0019\u0011Q\b\u001e\u0003'I+g\r\\3di&twMU3hSN$(/\u0019:\u0011\u0007\r\f\t\u0005B\u0006\u0002D!\t\t\u0011!A\u0003\u0002\u0005%\"aA0%i\u0005a1/\u001a:jC2L'0\u001a:tA\u0005AA-\u001a4bk2$8/\u0006\u0002\u0002LA)A*a\u0005\u0002NA\"\u0011qJA,!\u0019\tY\"!\u0015\u0002V%\u0019\u00111\u000b\u001e\u00035I+g\r\\3di&tw\rR3gCVdGOU3hSN$(/\u0019:\u0011\u0007\r\f9\u0006B\u0006\u0002Z)\t\t\u0011!A\u0003\u0002\u0005%\"aA0%k\u0005IA-\u001a4bk2$8\u000fI\u0001\u0015e\u0016<\u0017n\u001d;sCRLwN\u001c*fcVL'/\u001a3\u0016\u0005\u0005\u0005\u0004cA\"\u0002d%\u0019\u0011Q\r#\u0003\u000f\t{w\u000e\\3b]\u0006)\"/Z4jgR\u0014\u0018\r^5p]J+\u0017/^5sK\u0012\u0004\u0013aC:lSBl\u0015n]:j]\u001e\fAb]6ja6K7o]5oO\u0002\na\u0001P5oSRtD\u0003EA9\u0003k\ny(!#\u0002\u0016\u0006\u0005\u0016QVAX!\r\t\u0019\bA\u0007\u0002q!Aqk\u0004I\u0001\u0002\u0004\t9\b\r\u0003\u0002z\u0005u\u0004\u0003B.`\u0003w\u00022aYA?\t))\u0017QOA\u0001\u0002\u0003\u0015\ta\u001a\u0005\tg>\u0001\n\u00111\u0001\u0002\u0002B\"\u00111QAD!\u0011Yv,!\"\u0011\u0007\r\f9\t\u0002\u0006z\u0003\u007f\n\t\u0011!A\u0003\u0002mD\u0011\"!\u0004\u0010!\u0003\u0005\r!a#\u0011\u000b1\u000b\u0019\"!$1\t\u0005=\u00151\u0013\t\u0007\u00037\ti\"!%\u0011\u0007\r\f\u0019\n\u0002\u0007\u0002&\u0005%\u0015\u0011!A\u0001\u0006\u0003\tI\u0003C\u0005\u00022=\u0001\n\u00111\u0001\u0002\u0018B)A*a\u0005\u0002\u001aB\"\u00111TAP!\u0019\tY\"a\u000f\u0002\u001eB\u00191-a(\u0005\u0019\u0005\r\u0013QSA\u0001\u0002\u0003\u0015\t!!\u000b\t\u0013\u0005\u001ds\u0002%AA\u0002\u0005\r\u0006#\u0002'\u0002\u0014\u0005\u0015\u0006\u0007BAT\u0003W\u0003b!a\u0007\u0002R\u0005%\u0006cA2\u0002,\u0012a\u0011\u0011LAQ\u0003\u0003\u0005\tQ!\u0001\u0002*!I\u0011QL\b\u0011\u0002\u0003\u0007\u0011\u0011\r\u0005\n\u0003Sz\u0001\u0013!a\u0001\u0003C\nQAY;jY\u0012,\"!!.\u0011\t\u0005M\u0014qW\u0005\u0004\u0003sC$A\u0006*fM2,7\r^5oO&s7\u000f^1oi&\fGo\u001c:\u0002\t\r|\u0007/\u001f\u000b\u0011\u0003c\ny,!1\u0002D\u0006\u0015\u0017qYAe\u0003\u0017D\u0001bV\t\u0011\u0002\u0003\u0007\u0011q\u000f\u0005\tgF\u0001\n\u00111\u0001\u0002\u0002\"I\u0011QB\t\u0011\u0002\u0003\u0007\u00111\u0012\u0005\n\u0003c\t\u0002\u0013!a\u0001\u0003/C\u0011\"a\u0012\u0012!\u0003\u0005\r!a)\t\u0013\u0005u\u0013\u0003%AA\u0002\u0005\u0005\u0004\"CA5#A\u0005\t\u0019AA1\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!!51\t\u0005M\u00171\u001d\t\u0007\u0003+\fy.!9\u000e\u0005\u0005]'\u0002BAm\u00037\fA\u0001\\1oO*\u0011\u0011Q\\\u0001\u0005U\u00064\u0018-C\u0002a\u0003/\u00042aYAr\t%)'#!A\u0001\u0002\u000b\u0005q-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005%\b\u0007BAv\u0003_\u0004b!!6\u0002`\u00065\bcA2\u0002p\u0012I\u0011pEA\u0001\u0002\u0003\u0015\ta_\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\t)P\u000b\u0003\u0002\u0012\u0005]8FAA}!\u0011\tYP!\u0002\u000e\u0005\u0005u(\u0002BA\u0000\u0005\u0003\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\t\rA)\u0001\u0006b]:|G/\u0019;j_:LAAa\u0002\u0002~\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011!Q\u0002\u0016\u0005\u0003k\t90\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\tM!\u0006BA&\u0003o\fabY8qs\u0012\"WMZ1vYR$c'\u0006\u0002\u0003\u001a)\"\u0011\u0011MA|\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uI]\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXC\u0001B\u0011!\u0011\t)Na\t\n\t\t\u0015\u0012q\u001b\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\t-\u0002cA\"\u0003.%\u0019!q\u0006#\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005-\"Q\u0007\u0005\n\u0005oY\u0012\u0011!a\u0001\u0005W\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001B\u001f!\u0019\u0011yD!\u0012\u0002,5\u0011!\u0011\t\u0006\u0004\u0005\u0007\"\u0015AC2pY2,7\r^5p]&!!q\tB!\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\u0005$Q\n\u0005\n\u0005oi\u0012\u0011!a\u0001\u0003W\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!\u0011\u0005B*\u0011%\u00119DHA\u0001\u0002\u0004\u0011Y#\u0001\u0005iCND7i\u001c3f)\t\u0011Y#\u0001\u0005u_N#(/\u001b8h)\t\u0011\t#\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003C\u0012\t\u0007C\u0005\u00038\u0005\n\t\u00111\u0001\u0002,\u0005i\"+\u001a4mK\u000e$\u0018N\\4J]N$\u0018M\u001c;jCR|'OQ;jY\u0012,'\u000fE\u0002\u0002t\r\u001aRa\tB5\u0005G\u0003BCa\u001b\u0003r\tU$Q\u0010BC\u0005\u001f\u0013I*!\u0019\u0002b\u0005ETB\u0001B7\u0015\r\u0011y\u0007R\u0001\beVtG/[7f\u0013\u0011\u0011\u0019H!\u001c\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tw\u0007\r\u0003\u0003x\tm\u0004\u0003B.`\u0005s\u00022a\u0019B>\t%)7%!A\u0001\u0002\u000b\u0005q\r\r\u0003\u0003\u0000\t\r\u0005\u0003B.`\u0005\u0003\u00032a\u0019BB\t%I8%!A\u0001\u0002\u000b\u00051\u0010E\u0003M\u0003'\u00119\t\r\u0003\u0003\n\n5\u0005CBA\u000e\u0003;\u0011Y\tE\u0002d\u0005\u001b#1\"!\n$\u0003\u0003\u0005\tQ!\u0001\u0002*A)A*a\u0005\u0003\u0012B\"!1\u0013BL!\u0019\tY\"a\u000f\u0003\u0016B\u00191Ma&\u0005\u0017\u0005\r3%!A\u0001\u0002\u000b\u0005\u0011\u0011\u0006\t\u0006\u0019\u0006M!1\u0014\u0019\u0005\u0005;\u0013\t\u000b\u0005\u0004\u0002\u001c\u0005E#q\u0014\t\u0004G\n\u0005FaCA-G\u0005\u0005\t\u0011!B\u0001\u0003S\u0001BA!*\u0003,6\u0011!q\u0015\u0006\u0005\u0005S\u000bY.\u0001\u0002j_&\u0019QKa*\u0015\u0005\t\u0015\u0014!B1qa2LH\u0003EA9\u0005g\u0013iLa2\u0003T\n}'1\u001eBw\u0011!9f\u0005%AA\u0002\tU\u0006\u0007\u0002B\\\u0005w\u0003BaW0\u0003:B\u00191Ma/\u0005\u0015\u0015\u0014\u0019,!A\u0001\u0002\u000b\u0005q\r\u0003\u0005tMA\u0005\t\u0019\u0001B`a\u0011\u0011\tM!2\u0011\tm{&1\u0019\t\u0004G\n\u0015GAC=\u0003>\u0006\u0005\t\u0011!B\u0001w\"I\u0011Q\u0002\u0014\u0011\u0002\u0003\u0007!\u0011\u001a\t\u0006\u0019\u0006M!1\u001a\u0019\u0005\u0005\u001b\u0014\t\u000e\u0005\u0004\u0002\u001c\u0005u!q\u001a\t\u0004G\nEG\u0001DA\u0013\u0005\u000f\f\t\u0011!A\u0003\u0002\u0005%\u0002\"CA\u0019MA\u0005\t\u0019\u0001Bk!\u0015a\u00151\u0003Bla\u0011\u0011IN!8\u0011\r\u0005m\u00111\bBn!\r\u0019'Q\u001c\u0003\r\u0003\u0007\u0012\u0019.!A\u0001\u0002\u000b\u0005\u0011\u0011\u0006\u0005\n\u0003\u000f2\u0003\u0013!a\u0001\u0005C\u0004R\u0001TA\n\u0005G\u0004DA!:\u0003jB1\u00111DA)\u0005O\u00042a\u0019Bu\t1\tIFa8\u0002\u0002\u0003\u0005)\u0011AA\u0015\u0011%\tiF\nI\u0001\u0002\u0004\t\t\u0007C\u0005\u0002j\u0019\u0002\n\u00111\u0001\u0002b\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0003tB\"!Q\u001fB}!\u0011YvLa>\u0011\u0007\r\u0014I\u0010B\u0005fO\u0005\u0005\t\u0011!B\u0001O\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$#'\u0006\u0002\u0003\u0000B\"1\u0011AB\u0003!\u0011Yvla\u0001\u0011\u0007\r\u001c)\u0001B\u0005zQ\u0005\u0005\t\u0011!B\u0001w\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$3'\u0006\u0002\u0004\f)\"1QBA|!\u0015a\u00151CB\ba\u0011\u0019\tb!\u0006\u0011\r\u0005m\u0011QDB\n!\r\u00197Q\u0003\u0003\f\u0003KI\u0013\u0011!A\u0001\u0006\u0003\tI#A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\u0019YB\u000b\u0003\u0004\u001e\u0005]\b#\u0002'\u0002\u0014\r}\u0001\u0007BB\u0011\u0007K\u0001b!a\u0007\u0002<\r\r\u0002cA2\u0004&\u0011Y\u00111\t\u0016\u0002\u0002\u0003\u0005)\u0011AA\u0015\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012*TCAB\u0016U\u0011\u0019i#a>\u0011\u000b1\u000b\u0019ba\f1\t\rE2Q\u0007\t\u0007\u00037\t\tfa\r\u0011\u0007\r\u001c)\u0004B\u0006\u0002Z-\n\t\u0011!A\u0003\u0002\u0005%\u0012aD1qa2LH\u0005Z3gCVdG\u000f\n\u001c\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uI]\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0004@\re\u0004#B\"\u0004B\r\u0015\u0013bAB\"\t\n1q\n\u001d;j_:\u0004\u0012cQB$\u0007\u0017\u001a\u0019fa\u0017\u0004f\r=\u0014\u0011MA1\u0013\r\u0019I\u0005\u0012\u0002\u0007)V\u0004H.Z\u001c1\t\r53\u0011\u000b\t\u00057~\u001by\u0005E\u0002d\u0007#\"\u0011\"\u001a\u0018\u0002\u0002\u0003\u0005)\u0011A41\t\rU3\u0011\f\t\u00057~\u001b9\u0006E\u0002d\u00073\"\u0011\"\u001f\u0018\u0002\u0002\u0003\u0005)\u0011A>\u0011\u000b1\u000b\u0019b!\u00181\t\r}31\r\t\u0007\u00037\tib!\u0019\u0011\u0007\r\u001c\u0019\u0007B\u0006\u0002&9\n\t\u0011!A\u0003\u0002\u0005%\u0002#\u0002'\u0002\u0014\r\u001d\u0004\u0007BB5\u0007[\u0002b!a\u0007\u0002<\r-\u0004cA2\u0004n\u0011Y\u00111\t\u0018\u0002\u0002\u0003\u0005)\u0011AA\u0015!\u0015a\u00151CB9a\u0011\u0019\u0019ha\u001e\u0011\r\u0005m\u0011\u0011KB;!\r\u00197q\u000f\u0003\f\u00033r\u0013\u0011!A\u0001\u0006\u0003\tI\u0003C\u0005\u0004|9\n\t\u00111\u0001\u0002r\u0005\u0019\u0001\u0010\n\u0019\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132+\t\u0019\t\t\r\u0003\u0004\u0004\u000e\u001d\u0005\u0003B.`\u0007\u000b\u00032aYBD\t%)w&!A\u0001\u0002\u000b\u0005q-A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u000b\u0003\u0007\u001b\u0003Daa$\u0004\u0014B!1lXBI!\r\u001971\u0013\u0003\nsB\n\t\u0011!A\u0003\u0002m\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aTCABMU\u0011\u0019Y*a>\u0011\u000b1\u000b\u0019b!(1\t\r}51\u0015\t\u0007\u00037\tib!)\u0011\u0007\r\u001c\u0019\u000bB\u0006\u0002&E\n\t\u0011!A\u0003\u0002\u0005%\u0012a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'\u0006\u0002\u0004**\"11VA|!\u0015a\u00151CBWa\u0011\u0019yka-\u0011\r\u0005m\u00111HBY!\r\u001971\u0017\u0003\f\u0003\u0007\u0012\u0014\u0011!A\u0001\u0006\u0003\tI#A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u000b\u0003\u0007sSCaa/\u0002xB)A*a\u0005\u0004>B\"1qXBb!\u0019\tY\"!\u0015\u0004BB\u00191ma1\u0005\u0017\u0005e3'!A\u0001\u0002\u000b\u0005\u0011\u0011F\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001c\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00138\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0019i\r\u0005\u0003\u0002V\u000e=\u0017\u0002BBi\u0003/\u0014aa\u00142kK\u000e$\b"
)
public class ReflectingInstantiatorBuilder implements Product, Serializable {
   private final Class kryoClass;
   private final Class instantiatorStrategyClass;
   private final Iterable classes;
   private final Iterable serializers;
   private final Iterable defaults;
   private final boolean registrationRequired;
   private final boolean skipMissing;

   public static boolean $lessinit$greater$default$7() {
      return ReflectingInstantiatorBuilder$.MODULE$.$lessinit$greater$default$7();
   }

   public static boolean $lessinit$greater$default$6() {
      return ReflectingInstantiatorBuilder$.MODULE$.$lessinit$greater$default$6();
   }

   public static Iterable $lessinit$greater$default$5() {
      return ReflectingInstantiatorBuilder$.MODULE$.$lessinit$greater$default$5();
   }

   public static Iterable $lessinit$greater$default$4() {
      return ReflectingInstantiatorBuilder$.MODULE$.$lessinit$greater$default$4();
   }

   public static Iterable $lessinit$greater$default$3() {
      return ReflectingInstantiatorBuilder$.MODULE$.$lessinit$greater$default$3();
   }

   public static Class $lessinit$greater$default$2() {
      return ReflectingInstantiatorBuilder$.MODULE$.$lessinit$greater$default$2();
   }

   public static Class $lessinit$greater$default$1() {
      return ReflectingInstantiatorBuilder$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final ReflectingInstantiatorBuilder x$0) {
      return ReflectingInstantiatorBuilder$.MODULE$.unapply(x$0);
   }

   public static boolean apply$default$7() {
      return ReflectingInstantiatorBuilder$.MODULE$.apply$default$7();
   }

   public static boolean apply$default$6() {
      return ReflectingInstantiatorBuilder$.MODULE$.apply$default$6();
   }

   public static Iterable apply$default$5() {
      return ReflectingInstantiatorBuilder$.MODULE$.apply$default$5();
   }

   public static Iterable apply$default$4() {
      return ReflectingInstantiatorBuilder$.MODULE$.apply$default$4();
   }

   public static Iterable apply$default$3() {
      return ReflectingInstantiatorBuilder$.MODULE$.apply$default$3();
   }

   public static Class apply$default$2() {
      return ReflectingInstantiatorBuilder$.MODULE$.apply$default$2();
   }

   public static Class apply$default$1() {
      return ReflectingInstantiatorBuilder$.MODULE$.apply$default$1();
   }

   public static ReflectingInstantiatorBuilder apply(final Class kryoClass, final Class instantiatorStrategyClass, final Iterable classes, final Iterable serializers, final Iterable defaults, final boolean registrationRequired, final boolean skipMissing) {
      return ReflectingInstantiatorBuilder$.MODULE$.apply(kryoClass, instantiatorStrategyClass, classes, serializers, defaults, registrationRequired, skipMissing);
   }

   public static Function1 tupled() {
      return ReflectingInstantiatorBuilder$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ReflectingInstantiatorBuilder$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Class kryoClass() {
      return this.kryoClass;
   }

   public Class instantiatorStrategyClass() {
      return this.instantiatorStrategyClass;
   }

   public Iterable classes() {
      return this.classes;
   }

   public Iterable serializers() {
      return this.serializers;
   }

   public Iterable defaults() {
      return this.defaults;
   }

   public boolean registrationRequired() {
      return this.registrationRequired;
   }

   public boolean skipMissing() {
      return this.skipMissing;
   }

   public ReflectingInstantiator build() {
      return new ReflectingInstantiator(this.kryoClass(), this.instantiatorStrategyClass(), (java.lang.Iterable).MODULE$.asJavaIterableConverter(this.classes()).asJava(), (java.lang.Iterable).MODULE$.asJavaIterableConverter(this.serializers()).asJava(), (java.lang.Iterable).MODULE$.asJavaIterableConverter(this.defaults()).asJava(), this.registrationRequired(), this.skipMissing());
   }

   public ReflectingInstantiatorBuilder copy(final Class kryoClass, final Class instantiatorStrategyClass, final Iterable classes, final Iterable serializers, final Iterable defaults, final boolean registrationRequired, final boolean skipMissing) {
      return new ReflectingInstantiatorBuilder(kryoClass, instantiatorStrategyClass, classes, serializers, defaults, registrationRequired, skipMissing);
   }

   public Class copy$default$1() {
      return this.kryoClass();
   }

   public Class copy$default$2() {
      return this.instantiatorStrategyClass();
   }

   public Iterable copy$default$3() {
      return this.classes();
   }

   public Iterable copy$default$4() {
      return this.serializers();
   }

   public Iterable copy$default$5() {
      return this.defaults();
   }

   public boolean copy$default$6() {
      return this.registrationRequired();
   }

   public boolean copy$default$7() {
      return this.skipMissing();
   }

   public String productPrefix() {
      return "ReflectingInstantiatorBuilder";
   }

   public int productArity() {
      return 7;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.kryoClass();
            break;
         case 1:
            var10000 = this.instantiatorStrategyClass();
            break;
         case 2:
            var10000 = this.classes();
            break;
         case 3:
            var10000 = this.serializers();
            break;
         case 4:
            var10000 = this.defaults();
            break;
         case 5:
            var10000 = BoxesRunTime.boxToBoolean(this.registrationRequired());
            break;
         case 6:
            var10000 = BoxesRunTime.boxToBoolean(this.skipMissing());
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
      return x$1 instanceof ReflectingInstantiatorBuilder;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "kryoClass";
            break;
         case 1:
            var10000 = "instantiatorStrategyClass";
            break;
         case 2:
            var10000 = "classes";
            break;
         case 3:
            var10000 = "serializers";
            break;
         case 4:
            var10000 = "defaults";
            break;
         case 5:
            var10000 = "registrationRequired";
            break;
         case 6:
            var10000 = "skipMissing";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.kryoClass()));
      var1 = Statics.mix(var1, Statics.anyHash(this.instantiatorStrategyClass()));
      var1 = Statics.mix(var1, Statics.anyHash(this.classes()));
      var1 = Statics.mix(var1, Statics.anyHash(this.serializers()));
      var1 = Statics.mix(var1, Statics.anyHash(this.defaults()));
      var1 = Statics.mix(var1, this.registrationRequired() ? 1231 : 1237);
      var1 = Statics.mix(var1, this.skipMissing() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 7);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var15;
      if (this != x$1) {
         label94: {
            boolean var2;
            if (x$1 instanceof ReflectingInstantiatorBuilder) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label76: {
                  ReflectingInstantiatorBuilder var4 = (ReflectingInstantiatorBuilder)x$1;
                  if (this.registrationRequired() == var4.registrationRequired() && this.skipMissing() == var4.skipMissing()) {
                     label85: {
                        Class var10000 = this.kryoClass();
                        Class var5 = var4.kryoClass();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label85;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label85;
                        }

                        var10000 = this.instantiatorStrategyClass();
                        Class var6 = var4.instantiatorStrategyClass();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label85;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label85;
                        }

                        Iterable var11 = this.classes();
                        Iterable var7 = var4.classes();
                        if (var11 == null) {
                           if (var7 != null) {
                              break label85;
                           }
                        } else if (!var11.equals(var7)) {
                           break label85;
                        }

                        var11 = this.serializers();
                        Iterable var8 = var4.serializers();
                        if (var11 == null) {
                           if (var8 != null) {
                              break label85;
                           }
                        } else if (!var11.equals(var8)) {
                           break label85;
                        }

                        var11 = this.defaults();
                        Iterable var9 = var4.defaults();
                        if (var11 == null) {
                           if (var9 != null) {
                              break label85;
                           }
                        } else if (!var11.equals(var9)) {
                           break label85;
                        }

                        if (var4.canEqual(this)) {
                           var15 = true;
                           break label76;
                        }
                     }
                  }

                  var15 = false;
               }

               if (var15) {
                  break label94;
               }
            }

            var15 = false;
            return var15;
         }
      }

      var15 = true;
      return var15;
   }

   public ReflectingInstantiatorBuilder(final Class kryoClass, final Class instantiatorStrategyClass, final Iterable classes, final Iterable serializers, final Iterable defaults, final boolean registrationRequired, final boolean skipMissing) {
      this.kryoClass = kryoClass;
      this.instantiatorStrategyClass = instantiatorStrategyClass;
      this.classes = classes;
      this.serializers = serializers;
      this.defaults = defaults;
      this.registrationRequired = registrationRequired;
      this.skipMissing = skipMissing;
      Product.$init$(this);
   }
}
