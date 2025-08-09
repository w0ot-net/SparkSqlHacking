package org.json4s.reflect;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import org.json4s.Formats;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.ListBuffer;
import scala.package.;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0015v!\u0002\u001c8\u0011\u0003qd!\u0002!8\u0011\u0003\t\u0005\"\u0002%\u0002\t\u0003I\u0005B\u0002&\u0002A\u0003%1\n\u0003\u0004h\u0003\u0001\u0006I\u0001\u001b\u0005\u0007i\u0006\u0001\u000b\u0011B;\t\rq\f\u0001\u0015!\u0003~\u0011\u001d\tY!\u0001C\u0001\u0003\u001bAq!!\u0006\u0002\t\u0003\t9\u0002C\u0005\u0002,\u0005\t\n\u0011\"\u0001\u0002.!9\u00111I\u0001\u0005\u0002\u0005\u0015\u0003bBA\"\u0003\u0011\u0005\u0011\u0011\f\u0005\b\u0003\u0007\nA\u0011AA6\u0011!\ty'\u0001Q\u0001\n\u0005E\u0004bBA\"\u0003\u0011\u0005\u0011\u0011\u0010\u0005\b\u0003\u007f\nA\u0011AAA\u0011%\tI*AI\u0001\n\u0003\tY\nC\u0004\u0002\u0000\u0005!\t!a)\t\u000f\u0005-\u0017\u0001\"\u0001\u0002N\"9\u0011q\\\u0001\u0005\u0002\u0005\u0005\b\"\u0003B\u000f\u0003E\u0005I\u0011\u0001B\u0010\u0011%\u0011\u0019#AI\u0001\n\u0003\u0011)\u0003C\u0004\u00036\u0005!\tAa\u000e\t\u0013\tE\u0013!%A\u0005\u0002\t}\u0001\"\u0003B*\u0003E\u0005I\u0011\u0001B+\r\u0019\u0011)'\u0001\u0003\u0003h!I\u0011Q]\r\u0003\u0002\u0003\u0006IA\u001e\u0005\u000b\u0003SL\"\u0011!Q\u0001\n\u0005-\bBCAz3\t\u0005\t\u0015!\u0003\u0003j!Q\u0011qR\r\u0003\u0002\u0003\u0006Y!!%\t\r!KB\u0011\u0001B<\u0011%\u0011\u0019*\u0007a\u0001\n\u0003\u0011)\nC\u0005\u0003 f\u0001\r\u0011\"\u0001\u0003\"\"A!qU\r!B\u0013\u00119\nC\u0005\u0003*f\u0001\r\u0011\"\u0001\u0003,\"I!QV\rA\u0002\u0013\u0005!q\u0016\u0005\t\u0005gK\u0002\u0015)\u0003\u0002\u001a!9!QW\r\u0005\u0002\t]\u0006b\u0002Bg3\u0011\u0005!q\u001a\u0005\b\u0005/LB\u0011\u0001Bm\u0011%\u0011i0GI\u0001\n\u0003\u0011y\u0010C\u0004\u0004\u0004e!\ta!\u0002\t\u000f\r=\u0011\u0004\"\u0001\u0004\u0012!911E\r\u0005\u0002\r\u0015\u0002bBB\u00163\u0011\u00051QF\u0004\n\u0007k\t\u0011\u0011!E\u0005\u0007o1\u0011B!\u001a\u0002\u0003\u0003EIa!\u000f\t\r!sC\u0011AB\u001e\u0011%\u0019iDLI\u0001\n\u0003\u0011y\u0002C\u0005\u0004@9\n\n\u0011\"\u0001\u0004B!91\u0011K\u0001\u0005\u0002\rM\u0003bBB?\u0003\u0011\u00051q\u0010\u0005\b\u0007\u001b\u000bA\u0011ABH\u0011\u001d\u0019\u0019*\u0001C\u0001\u0007+\u000b\u0011BU3gY\u0016\u001cGo\u001c:\u000b\u0005aJ\u0014a\u0002:fM2,7\r\u001e\u0006\u0003um\naA[:p]R\u001a(\"\u0001\u001f\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005}\nQ\"A\u001c\u0003\u0013I+g\r\\3di>\u00148CA\u0001C!\t\u0019e)D\u0001E\u0015\u0005)\u0015!B:dC2\f\u0017BA$E\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012AP\u0001\u000be\u0006<8\t\\1tg\u0016\u001c\b\u0003B M\u001d^K!!T\u001c\u0003\t5+Wn\u001c\t\u0003\u001fVk\u0011\u0001\u0015\u0006\u0003qES!AU*\u0002\t1\fgn\u001a\u0006\u0002)\u0006!!.\u0019<b\u0013\t1\u0006K\u0001\u0003UsB,\u0007G\u0001-_!\rI&\fX\u0007\u0002#&\u00111,\u0015\u0002\u0006\u00072\f7o\u001d\t\u0003;zc\u0001\u0001B\u0005`\u0007\u0005\u0005\t\u0011!B\u0001A\n\u0019q\fJ\u0019\u0012\u0005\u0005$\u0007CA\"c\u0013\t\u0019GIA\u0004O_RD\u0017N\\4\u0011\u0005\r+\u0017B\u00014E\u0005\r\te._\u0001\u000fk:l\u0017M\\4mK\u0012t\u0015-\\3t!\u0011yD*[5\u0011\u0005)\fhBA6p!\taG)D\u0001n\u0015\tqW(\u0001\u0004=e>|GOP\u0005\u0003a\u0012\u000ba\u0001\u0015:fI\u00164\u0017B\u0001:t\u0005\u0019\u0019FO]5oO*\u0011\u0001\u000fR\u0001\fI\u0016\u001c8M]5qi>\u00148\u000f\u0005\u0003@\u0019ZL\bCA x\u0013\tAxGA\u0005TG\u0006d\u0017\rV=qKB\u0011qH_\u0005\u0003w^\u0012\u0001c\u00142kK\u000e$H)Z:de&\u0004Ho\u001c:\u0002\u0015A\u0014\u0018.\\5uSZ,7\u000f\u0005\u0003\u007f\u0003\u000fqU\"A@\u000b\t\u0005\u0005\u00111A\u0001\nS6lW\u000f^1cY\u0016T1!!\u0002E\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0004\u0003\u0013y(aA*fi\u0006Y1\r\\3be\u000e\u000b7\r[3t)\t\ty\u0001E\u0002D\u0003#I1!a\u0005E\u0005\u0011)f.\u001b;\u0002\u0017%\u001c\bK]5nSRLg/\u001a\u000b\u0007\u00033\ty\"a\t\u0011\u0007\r\u000bY\"C\u0002\u0002\u001e\u0011\u0013qAQ8pY\u0016\fg\u000e\u0003\u0004\u0002\"!\u0001\rAT\u0001\u0002i\"I\u0011Q\u0005\u0005\u0011\u0002\u0003\u0007\u0011qE\u0001\u0006Kb$(/\u0019\t\u0005U\u0006%b*C\u0002\u0002\nM\fQ#[:Qe&l\u0017\u000e^5wK\u0012\"WMZ1vYR$#'\u0006\u0002\u00020)\"\u0011qEA\u0019W\t\t\u0019\u0004\u0005\u0003\u00026\u0005}RBAA\u001c\u0015\u0011\tI$a\u000f\u0002\u0013Ut7\r[3dW\u0016$'bAA\u001f\t\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\u0005\u0013q\u0007\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017aC:dC2\fG+\u001f9f\u001f\u001a,B!a\u0012\u0002VQ\u0019a/!\u0013\t\u000f\u0005-#\u0002q\u0001\u0002N\u0005\u0011QN\u001a\t\u0006U\u0006=\u00131K\u0005\u0004\u0003#\u001a(\u0001C'b]&4Wm\u001d;\u0011\u0007u\u000b)\u0006\u0002\u0004\u0002X)\u0011\r\u0001\u0019\u0002\u0002)R\u0019a/a\u0017\t\u000f\u0005u3\u00021\u0001\u0002`\u0005)1\r\\1{uB\"\u0011\u0011MA4!\u0015Q\u00171MA3\u0013\tY6\u000fE\u0002^\u0003O\"1\"!\u001b\u0002\\\u0005\u0005\t\u0011!B\u0001A\n\u0019q\f\n\u001a\u0015\u0007Y\fi\u0007\u0003\u0004\u0002\"1\u0001\rAT\u0001\fgR\u0014\u0018N\\4UsB,7\u000fE\u0003@\u0019&\f\u0019\b\u0005\u0003D\u0003k2\u0018bAA<\t\n1q\n\u001d;j_:$B!a\u001d\u0002|!1\u0011Q\u0010\bA\u0002%\fAA\\1nK\u0006AA-Z:de&\u0014W-\u0006\u0003\u0002\u0004\u0006-E#B=\u0002\u0006\u00065\u0005bBA&\u001f\u0001\u000f\u0011q\u0011\t\u0006U\u0006=\u0013\u0011\u0012\t\u0004;\u0006-EABA,\u001f\t\u0007\u0001\rC\u0005\u0002\u0010>\u0001\n\u0011q\u0001\u0002\u0012\u00069am\u001c:nCR\u001c\b\u0003BAJ\u0003+k\u0011!O\u0005\u0004\u0003/K$a\u0002$pe6\fGo]\u0001\u0013I\u0016\u001c8M]5cK\u0012\"WMZ1vYR$#'\u0006\u0003\u0002\u001e\u0006\u0005VCAAPU\u0011\t\t*!\r\u0005\r\u0005]\u0003C1\u0001a)\rI\u0018Q\u0015\u0005\b\u0003O\u000b\u0002\u0019AAU\u0003\t\u0019H\u000f\r\u0003\u0002,\u0006M\u0006#B \u0002.\u0006E\u0016bAAXo\t!\"+\u001a4mK\u000e$xN\u001d#fg\u000e\u0014\u0018NY1cY\u0016\u00042!XAZ\t-\t),!*\u0002\u0002\u0003\u0005)\u0011\u00011\u0003\u0007}#3\u0007K\u0006\u0012\u0003s\u000by,!1\u0002F\u0006\u001d\u0007cA\"\u0002<&\u0019\u0011Q\u0018#\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0005\r\u0017AL+tK\u0002\"Wm]2sS\n,7\fV/!_J\u0004C-Z:de&\u0014WmV5uQ\u001a{'/\\1ug\u0002Jgn\u001d;fC\u0012\fQa]5oG\u0016\f#!!3\u0002\u000bMrcG\f\u001d\u0002'\u0011,7o\u0019:jE\u0016<\u0016\u000e\u001e5G_Jl\u0017\r^:\u0015\t\u0005=\u00171\u001b\u000b\u0004s\u0006E\u0007bBAH%\u0001\u000f\u0011\u0011\u0013\u0005\b\u0003O\u0013\u0002\u0019AAka\u0011\t9.a7\u0011\u000b}\ni+!7\u0011\u0007u\u000bY\u000eB\u0006\u0002^\u0006M\u0017\u0011!A\u0001\u0006\u0003\u0001'aA0%i\u0005\u00012M]3bi\u0016$Um]2sSB$xN\u001d\u000b\bs\u0006\r\u0018q]Ay\u0011\u0019\t)o\u0005a\u0001m\u0006\u0019A\u000f]3\t\u0013\u0005%8\u0003%AA\u0002\u0005-\u0018a\u00049be\u0006lg*Y7f%\u0016\fG-\u001a:\u0011\u0007}\ni/C\u0002\u0002p^\u00121\u0003U1sC6,G/\u001a:OC6,'+Z1eKJD\u0011\"a=\u0014!\u0003\u0005\r!!>\u0002#\r|W\u000e]1oS>tW*\u00199qS:<7\u000f\u0005\u0004\u0002x\n\u0005!q\u0001\b\u0005\u0003s\fiPD\u0002m\u0003wL\u0011!R\u0005\u0004\u0003\u007f$\u0015a\u00029bG.\fw-Z\u0005\u0005\u0005\u0007\u0011)A\u0001\u0003MSN$(bAA\u0000\tB11I!\u0003\u0003\u000e\tK1Aa\u0003E\u0005\u0019!V\u000f\u001d7feA\"!q\u0002B\n!\u0015Q\u00171\rB\t!\ri&1\u0003\u0003\f\u0005+\t\t0!A\u0001\u0002\u000b\u0005\u0001MA\u0002`IUB3bEA]\u0003\u007f\u0013I\"!2\u0002H\u0006\u0012!1D\u0001 +N,\u0007e\u0019:fCR,G)Z:de&\u0004Ho\u001c:XSRDgi\u001c:nCR\u001c\u0018AG2sK\u0006$X\rR3tGJL\u0007\u000f^8sI\u0011,g-Y;mi\u0012\u0012TC\u0001B\u0011U\u0011\tY/!\r\u00025\r\u0014X-\u0019;f\t\u0016\u001c8M]5qi>\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\t\u001d\"\u0006\u0002B\u0015\u0003c\u0001b!a>\u0003\u0002\t-\u0002CB\"\u0003\n\t5\"\t\r\u0003\u00030\tM\u0002#\u00026\u0002d\tE\u0002cA/\u00034\u0011Q!QC\u000b\u0002\u0002\u0003\u0005)\u0011\u00011\u00027\r\u0014X-\u0019;f\t\u0016\u001c8M]5qi>\u0014x+\u001b;i\r>\u0014X.\u0019;t)!\u0011ID!\u0010\u0003@\t\u0005CcA=\u0003<!9\u0011q\u0012\fA\u0004\u0005E\u0005BBAs-\u0001\u0007a\u000fC\u0005\u0002jZ\u0001\n\u00111\u0001\u0002l\"I\u00111\u001f\f\u0011\u0002\u0003\u0007!1\t\t\u0007\u0003o\u0014\tA!\u0012\u0011\r\r\u0013IAa\u0012Ca\u0011\u0011IE!\u0014\u0011\u000b)\f\u0019Ga\u0013\u0011\u0007u\u0013i\u0005B\u0006\u0003P\t\u0005\u0013\u0011!A\u0001\u0006\u0003\u0001'aA0%m\u0005)3M]3bi\u0016$Um]2sSB$xN],ji\"4uN]7biN$C-\u001a4bk2$HEM\u0001&GJ,\u0017\r^3EKN\u001c'/\u001b9u_J<\u0016\u000e\u001e5G_Jl\u0017\r^:%I\u00164\u0017-\u001e7uIM*\"Aa\u0016+\t\te\u0013\u0011\u0007\t\u0007\u0003o\u0014\tAa\u0017\u0011\r\r\u0013IA!\u0018Ca\u0011\u0011yFa\u0019\u0011\u000b)\f\u0019G!\u0019\u0011\u0007u\u0013\u0019\u0007\u0002\u0006\u0003Pa\t\t\u0011!A\u0003\u0002\u0001\u0014ac\u00117bgN$Um]2sSB$xN\u001d\"vS2$WM]\n\u00033\t\u0003b!a>\u0003\u0002\t-\u0004CB\"\u0003\n\t5$\t\r\u0003\u0003p\tM\u0004#\u00026\u0002d\tE\u0004cA/\u0003t\u0011Q!Q\u000f\u000f\u0002\u0002\u0003\u0005)\u0011\u00011\u0003\u0007}#s\u0007\u0006\u0005\u0003z\t\u0005%1\u0011BC)\u0011\u0011YHa \u0011\u0007\tu\u0014$D\u0001\u0002\u0011\u001d\tyI\ba\u0002\u0003#Ca!!:\u001f\u0001\u00041\b\"CAu=A\u0005\t\u0019AAv\u0011%\t\u0019P\bI\u0001\u0002\u0004\u00119\t\u0005\u0004\u0002x\n\u0005!\u0011\u0012\t\u0007\u0007\n%!1\u0012\"1\t\t5%\u0011\u0013\t\u0006U\u0006\r$q\u0012\t\u0004;\nEEa\u0003B;\u0005\u000b\u000b\t\u0011!A\u0003\u0002\u0001\f\u0011bY8na\u0006t\u0017n\u001c8\u0016\u0005\t]\u0005#B\"\u0002v\te\u0005cA \u0003\u001c&\u0019!QT\u001c\u0003'MKgn\u001a7fi>tG)Z:de&\u0004Ho\u001c:\u0002\u001b\r|W\u000e]1oS>tw\fJ3r)\u0011\tyAa)\t\u0013\t\u0015\u0006%!AA\u0002\t]\u0015a\u0001=%c\u0005Q1m\\7qC:LwN\u001c\u0011\u0002\u001dQ\u0014\u0018.\u001a3D_6\u0004\u0018M\\5p]V\u0011\u0011\u0011D\u0001\u0013iJLW\rZ\"p[B\fg.[8o?\u0012*\u0017\u000f\u0006\u0003\u0002\u0010\tE\u0006\"\u0003BSG\u0005\u0005\t\u0019AA\r\u0003=!(/[3e\u0007>l\u0007/\u00198j_:\u0004\u0013A\u00024jK2$7\u000f\u0006\u0003\u0003:\n\u0005\u0007CBA|\u0005\u0003\u0011Y\fE\u0002@\u0005{K1Aa08\u0005I\u0001&o\u001c9feRLH)Z:de&\u0004Ho\u001c:\t\u000f\u0005uS\u00051\u0001\u0003DB\"!Q\u0019Be!\u0015Q\u00171\rBd!\ri&\u0011\u001a\u0003\f\u0005\u0017\u0014\t-!A\u0001\u0002\u000b\u0005\u0001MA\u0002`Ia\n!\u0002\u001d:pa\u0016\u0014H/[3t+\t\u0011\t\u000e\u0005\u0004\u0002x\nM'1X\u0005\u0005\u0005+\u0014)AA\u0002TKF\fQb\u0019;peB\u000b'/Y7UsB,G#\u0004<\u0003\\\nu'q\u001dBv\u0005c\u0014\u0019\u0010\u0003\u0004\u0002~\u001d\u0002\r!\u001b\u0005\b\u0005?<\u0003\u0019\u0001Bq\u0003\u0015Ig\u000eZ3y!\r\u0019%1]\u0005\u0004\u0005K$%aA%oi\"1!\u0011^\u0014A\u0002Y\fQa\\<oKJDqA!<(\u0001\u0004\u0011y/\u0001\ndi>\u0014\b+\u0019:b[\u0016$XM\u001d(b[\u0016\u001c\b#BA|\u0005\u0003I\u0007BBA\u0011O\u0001\u0007a\nC\u0005\u0003v\u001e\u0002\n\u00111\u0001\u0003x\u0006I1m\u001c8uC&tWM\u001d\t\u0006\u0007\u0006U$\u0011 \t\u0007\u0007\n%aOa?\u0011\r\u0005](\u0011\u0001Bq\u0003]\u0019Go\u001c:QCJ\fW\u000eV=qK\u0012\"WMZ1vYR$c'\u0006\u0002\u0004\u0002)\"!q_A\u0019\u0003a\u0019wN\\:ueV\u001cGo\u001c:t\u0003:$7i\\7qC:LwN\\\u000b\u0003\u0007\u000f\u0001b!a>\u0003T\u000e%\u0001cA \u0004\f%\u00191QB\u001c\u0003+\r{gn\u001d;sk\u000e$xN\u001d#fg\u000e\u0014\u0018\u000e\u001d;pe\u0006a2M]3bi\u0016\u001cuN\\:ueV\u001cGo\u001c:EKN\u001c'/\u001b9u_J\u001cH\u0003BB\u0004\u0007'Aqa!\u0006+\u0001\u0004\u00199\"A\u0002dGN\u0004b!a>\u0004\u001a\ru\u0011\u0002BB\u000e\u0005\u000b\u0011\u0001\"\u0013;fe\u0006\u0014G.\u001a\t\u0004\u007f\r}\u0011bAB\u0011o\tQQ\t_3dkR\f'\r\\3\u0002\u001b\u0019Lg\u000eZ\"p[B\fg.[8o)\u0011\u00119ja\n\t\u000f\r%2\u00061\u0001\u0002\u001a\u0005)2\r[3dW\u000e{W\u000e]1oS>tW*\u00199qS:<\u0017A\u0002:fgVdG/\u0006\u0002\u00040A\u0019qh!\r\n\u0007\rMrGA\bDY\u0006\u001c8\u000fR3tGJL\u0007\u000f^8s\u0003Y\u0019E.Y:t\t\u0016\u001c8M]5qi>\u0014()^5mI\u0016\u0014\bc\u0001B?]M\u0011aF\u0011\u000b\u0003\u0007o\t1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'\u0006\u0002\u0004D)\"1QIA\u0019!\u0019\t9P!\u0001\u0004HA11I!\u0003\u0004J\t\u0003Daa\u0013\u0004PA)!.a\u0019\u0004NA\u0019Qla\u0014\u0005\u0015\tU\u0014'!A\u0001\u0002\u000b\u0005\u0001-\u0001\u0007eK\u001a\fW\u000f\u001c;WC2,X\r\u0006\u0006\u0004V\r\r4\u0011OB;\u0007s\u0002RaQA;\u0007/\u0002RaQB-\u0007;J1aa\u0017E\u0005%1UO\\2uS>t\u0007\u0007E\u0002Z\u0007?J1a!\u0019R\u0005\u0019y%M[3di\"91Q\r\u001aA\u0002\r\u001d\u0014!C2p[B\u001cE.Y:ta\u0011\u0019Ig!\u001c\u0011\u000b)\f\u0019ga\u001b\u0011\u0007u\u001bi\u0007B\u0006\u0004p\r\r\u0014\u0011!A\u0001\u0006\u0003\u0001'aA0%s!111\u000f\u001aA\u0002\t\u000bqaY8na>\u0013'\u000eC\u0004\u0004xI\u0002\rA!9\u0002\u0011\u0005\u0014x-\u00138eKbDaaa\u001f3\u0001\u0004I\u0017a\u00029biR,'O\\\u0001\u000be\u0006<8\t\\1tg>3G\u0003BBA\u0007\u0017\u0003Daa!\u0004\bB)!.a\u0019\u0004\u0006B\u0019Qla\"\u0005\u0015\r%5'!A\u0001\u0002\u000b\u0005\u0001M\u0001\u0003`IE\u0002\u0004BBA\u0011g\u0001\u0007a*\u0001\u0007v]6\fgn\u001a7f\u001d\u0006lW\rF\u0002j\u0007#Ca!! 5\u0001\u0004I\u0017aE7l!\u0006\u0014\u0018-\\3uKJL'0\u001a3UsB,GCBBL\u0007;\u001by\nE\u0002P\u00073K1aa'Q\u0005E\u0001\u0016M]1nKR,'/\u001b>fIRK\b/\u001a\u0005\u0007\u0005S,\u0004\u0019\u0001(\t\u000f\r\u0005V\u00071\u0001\u0004$\u0006AA/\u001f9f\u0003J<7\u000fE\u0003\u0002x\nMg\n"
)
public final class Reflector {
   public static ParameterizedType mkParameterizedType(final Type owner, final Seq typeArgs) {
      return Reflector$.MODULE$.mkParameterizedType(owner, typeArgs);
   }

   public static String unmangleName(final String name) {
      return Reflector$.MODULE$.unmangleName(name);
   }

   public static Class rawClassOf(final Type t) {
      return Reflector$.MODULE$.rawClassOf(t);
   }

   public static Option defaultValue(final Class compClass, final Object compObj, final int argIndex, final String pattern) {
      return Reflector$.MODULE$.defaultValue(compClass, compObj, argIndex, pattern);
   }

   public static List createDescriptorWithFormats$default$3() {
      return Reflector$.MODULE$.createDescriptorWithFormats$default$3();
   }

   public static ParameterNameReader createDescriptorWithFormats$default$2() {
      return Reflector$.MODULE$.createDescriptorWithFormats$default$2();
   }

   public static ObjectDescriptor createDescriptorWithFormats(final ScalaType tpe, final ParameterNameReader paramNameReader, final List companionMappings, final Formats formats) {
      return Reflector$.MODULE$.createDescriptorWithFormats(tpe, paramNameReader, companionMappings, formats);
   }

   public static List createDescriptor$default$3() {
      return Reflector$.MODULE$.createDescriptor$default$3();
   }

   public static ParameterNameReader createDescriptor$default$2() {
      return Reflector$.MODULE$.createDescriptor$default$2();
   }

   /** @deprecated */
   public static ObjectDescriptor createDescriptor(final ScalaType tpe, final ParameterNameReader paramNameReader, final List companionMappings) {
      return Reflector$.MODULE$.createDescriptor(tpe, paramNameReader, companionMappings);
   }

   public static ObjectDescriptor describeWithFormats(final ReflectorDescribable st, final Formats formats) {
      return Reflector$.MODULE$.describeWithFormats(st, formats);
   }

   /** @deprecated */
   public static ObjectDescriptor describe(final ReflectorDescribable st) {
      return Reflector$.MODULE$.describe(st);
   }

   public static Formats describe$default$2() {
      return Reflector$.MODULE$.describe$default$2();
   }

   public static ObjectDescriptor describe(final Manifest mf, final Formats formats) {
      return Reflector$.MODULE$.describe(mf, formats);
   }

   public static Option scalaTypeOf(final String name) {
      return Reflector$.MODULE$.scalaTypeOf(name);
   }

   public static ScalaType scalaTypeOf(final Type t) {
      return Reflector$.MODULE$.scalaTypeOf(t);
   }

   public static ScalaType scalaTypeOf(final Class clazz) {
      return Reflector$.MODULE$.scalaTypeOf(clazz);
   }

   public static ScalaType scalaTypeOf(final Manifest mf) {
      return Reflector$.MODULE$.scalaTypeOf(mf);
   }

   public static Set isPrimitive$default$2() {
      return Reflector$.MODULE$.isPrimitive$default$2();
   }

   public static boolean isPrimitive(final Type t, final Set extra) {
      return Reflector$.MODULE$.isPrimitive(t, extra);
   }

   public static void clearCaches() {
      Reflector$.MODULE$.clearCaches();
   }

   private static class ClassDescriptorBuilder$ {
      public static final ClassDescriptorBuilder$ MODULE$ = new ClassDescriptorBuilder$();

      public ParameterNameReader $lessinit$greater$default$2() {
         return ParanamerReader$.MODULE$;
      }

      public List $lessinit$greater$default$3() {
         return .MODULE$.Nil();
      }

      public ClassDescriptorBuilder$() {
      }
   }

   private static class ClassDescriptorBuilder {
      private final ScalaType tpe;
      private final ParameterNameReader paramNameReader;
      private final List companionMappings;
      private final Formats formats;
      private Option companion;
      private boolean triedCompanion;

      public Option companion() {
         return this.companion;
      }

      public void companion_$eq(final Option x$1) {
         this.companion = x$1;
      }

      public boolean triedCompanion() {
         return this.triedCompanion;
      }

      public void triedCompanion_$eq(final boolean x$1) {
         this.triedCompanion = x$1;
      }

      public List fields(final Class clazz) {
         ListBuffer lb = new ListBuffer();
         Iterator ls = (Iterator)scala.util.control.Exception..MODULE$.allCatch().withApply((x$4) -> package$.MODULE$.fail("Case classes defined in function bodies are not supported.", package$.MODULE$.fail$default$2())).apply(() -> scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps((Object[])clazz.getDeclaredFields())));

         while(ls.hasNext()) {
            Field f = (Field)ls.next();
            int mod = f.getModifiers();
            if (!Modifier.isStatic(mod) && !Modifier.isTransient(mod) && !Modifier.isVolatile(mod) && !f.isSynthetic()) {
               ScalaType$ var12 = ScalaType$.MODULE$;
               Class var10001 = f.getType();
               Type var8 = f.getGenericType();
               Object var2;
               if (var8 instanceof ParameterizedType) {
                  ParameterizedType var9 = (ParameterizedType)var8;
                  var2 = (Seq)((IterableOps)scala.collection.ArrayOps..MODULE$.toSeq$extension(scala.Predef..MODULE$.refArrayOps((Object[])var9.getActualTypeArguments())).zipWithIndex()).map((x0$1) -> {
                     if (x0$1 == null) {
                        throw new MatchError(x0$1);
                     } else {
                        ScalaType var10000;
                        label21: {
                           int i;
                           label20: {
                              Type cc = (Type)x0$1._1();
                              i = x0$1._2$mcI$sp();
                              Class var7 = Object.class;
                              if (cc == null) {
                                 if (var7 == null) {
                                    break label20;
                                 }
                              } else if (cc.equals(var7)) {
                                 break label20;
                              }

                              var10000 = Reflector$.MODULE$.scalaTypeOf(cc);
                              break label21;
                           }

                           var10000 = Reflector$.MODULE$.scalaTypeOf(ScalaSigReader$.MODULE$.readField(f.getName(), clazz, i));
                        }

                        ScalaType var3 = var10000;
                        return var3;
                     }
                  });
               } else {
                  var2 = .MODULE$.Nil();
               }

               ScalaType st;
               label37: {
                  st = var12.apply(var10001, (Seq)var2);
                  String var13 = f.getName();
                  String var10 = ScalaSigReader$.MODULE$.OuterFieldName();
                  if (var13 == null) {
                     if (var10 != null) {
                        break label37;
                     }
                  } else if (!var13.equals(var10)) {
                     break label37;
                  }

                  BoxedUnit var14 = BoxedUnit.UNIT;
                  continue;
               }

               String decoded = Reflector$.MODULE$.unmangleName(f.getName());
               f.setAccessible(true);
               lb.$plus$eq(new PropertyDescriptor(decoded, f.getName(), st, f));
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }

         if (clazz.getSuperclass() != null) {
            lb.$plus$plus$eq(this.fields(clazz.getSuperclass()));
         } else {
            BoxedUnit var15 = BoxedUnit.UNIT;
         }

         return lb.toList();
      }

      public Seq properties() {
         return this.fields(this.tpe.erasure());
      }

      public ScalaType ctorParamType(final String name, final int index, final ScalaType owner, final List ctorParameterNames, final Type t, final Option container) {
         Option idxes = container.map((x$5) -> ((List)x$5._2()).reverse());
         ScalaType var7;
         if (t instanceof TypeVariable) {
            ScalaType var24;
            label52: {
               label51: {
                  TypeVariable var10 = (TypeVariable)t;
                  ScalaType a = (ScalaType)owner.typeVars().getOrElse(var10.getName(), () -> Reflector$.MODULE$.scalaTypeOf((Type)var10));
                  Class var10000 = a.erasure();
                  Class var12 = Object.class;
                  if (var10000 == null) {
                     if (var12 == null) {
                        break label51;
                     }
                  } else if (var10000.equals(var12)) {
                     break label51;
                  }

                  var24 = a;
                  break label52;
               }

               Class r = ScalaSigReader$.MODULE$.readConstructor(name, owner, index, ctorParameterNames);
               var24 = Reflector$.MODULE$.scalaTypeOf(r);
            }

            var7 = var24;
         } else if (t instanceof ParameterizedType) {
            ParameterizedType var14 = (ParameterizedType)t;
            ScalaType st = Reflector$.MODULE$.scalaTypeOf((Type)var14);
            List actualArgs = ((List)scala.Predef..MODULE$.wrapRefArray((Object[])var14.getActualTypeArguments()).toList().zipWithIndex()).map((x0$1) -> {
               if (x0$1 != null) {
                  Type ct = (Type)x0$1._1();
                  int idx = x0$1._2$mcI$sp();
                  List prev = (List)container.map((x$6) -> (List)x$6._2()).getOrElse(() -> .MODULE$.Nil());
                  ScalaType var8 = this.ctorParamType(name, index, owner, ctorParameterNames, ct, new Some(new Tuple2(st, prev.$colon$colon(BoxesRunTime.boxToInteger(idx)))));
                  return var8;
               } else {
                  throw new MatchError(x0$1);
               }
            });
            Class x$2 = st.copy$default$1();
            Map x$3 = st.copy$default$3();
            var7 = st.copy(x$2, actualArgs, x$3);
         } else if (t instanceof WildcardType) {
            WildcardType var20 = (WildcardType)t;
            Type[] upper = var20.getUpperBounds();
            var7 = upper != null && upper.length > 0 ? Reflector$.MODULE$.scalaTypeOf(upper[0]) : Reflector$.MODULE$.scalaTypeOf(scala.Predef..MODULE$.Manifest().AnyRef());
         } else {
            ScalaType var26;
            label37: {
               label36: {
                  ScalaType st = Reflector$.MODULE$.scalaTypeOf(t);
                  Class var25 = st.erasure();
                  Class var23 = Object.class;
                  if (var25 == null) {
                     if (var23 == null) {
                        break label36;
                     }
                  } else if (var25.equals(var23)) {
                     break label36;
                  }

                  var26 = st;
                  break label37;
               }

               var26 = Reflector$.MODULE$.scalaTypeOf(ScalaSigReader$.MODULE$.readConstructor(name, owner, (List)idxes.getOrElse(() -> (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{index}))), ctorParameterNames));
            }

            var7 = var26;
         }

         return var7;
      }

      public Option ctorParamType$default$6() {
         return scala.None..MODULE$;
      }

      public Seq constructorsAndCompanion() {
         Class er = this.tpe.erasure();
         Iterable ccs = (Iterable)scala.util.control.Exception..MODULE$.allCatch().withApply((e) -> package$.MODULE$.fail((new StringBuilder(59)).append(e.getMessage()).append(" Case classes defined in function bodies are not supported.").toString(), package$.MODULE$.fail$default$2())).apply(() -> {
            Constructor[] ctors = er.getConstructors();
            Option primaryCtor = scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps((Object[])ctors), (x$7) -> BoxesRunTime.boxToBoolean($anonfun$constructorsAndCompanion$3(x$7)));
            boolean var4 = false;
            Object var5 = null;
            ArraySeq.ofRef var1;
            if (primaryCtor instanceof Some) {
               Some var7 = (Some)primaryCtor;
               Constructor ctor = (Constructor)var7.value();
               var1 = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])ctors), (c) -> new Executable(c, c == ctor), scala.reflect.ClassTag..MODULE$.apply(Executable.class)));
            } else {
               if (scala.None..MODULE$.equals(primaryCtor)) {
                  var4 = true;
                  if (ctors.length == 1) {
                     var1 = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])ctors), (c) -> new Executable(c, true), scala.reflect.ClassTag..MODULE$.apply(Executable.class)));
                     return var1;
                  }
               }

               if (!var4) {
                  throw new MatchError(primaryCtor);
               }

               var1 = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])ctors), (x$8) -> new Executable(x$8, false), scala.reflect.ClassTag..MODULE$.apply(Executable.class)));
            }

            return var1;
         });
         Seq constructorDescriptors = this.createConstructorDescriptors(ccs);
         Seq var10000;
         if (!constructorDescriptors.isEmpty() && !this.formats.considerCompanionConstructors()) {
            var10000 = constructorDescriptors;
         } else {
            this.companion_$eq(this.findCompanion(false));
            Option var7 = this.companion();
            Method[] applyMethods;
            if (var7 instanceof Some) {
               Some var8 = (Some)var7;
               SingletonDescriptor singletonDescriptor = (SingletonDescriptor)var8.value();
               applyMethods = (Method[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])singletonDescriptor.instance().getClass().getMethods()), (method) -> BoxesRunTime.boxToBoolean($anonfun$constructorsAndCompanion$7(er, method)));
            } else {
               if (!scala.None..MODULE$.equals(var7)) {
                  throw new MatchError(var7);
               }

               applyMethods = (Method[])scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(Method.class));
            }

            Executable[] applyExecutables = (Executable[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])applyMethods), (m) -> new Executable(m), scala.reflect.ClassTag..MODULE$.apply(Executable.class));
            var10000 = (Seq)constructorDescriptors.$plus$plus(this.createConstructorDescriptors(scala.Predef..MODULE$.wrapRefArray(applyExecutables)));
         }

         Seq all = var10000;
         return (Seq)all.toList().sortBy((c) -> new Tuple4(BoxesRunTime.boxToBoolean(!c.isPrimary()), BoxesRunTime.boxToBoolean(c.constructor().constructor() == null), BoxesRunTime.boxToInteger(-c.params().size()), c.toString()), scala.math.Ordering..MODULE$.Tuple4(scala.math.Ordering.Boolean..MODULE$, scala.math.Ordering.Boolean..MODULE$, scala.math.Ordering.Int..MODULE$, scala.math.Ordering.String..MODULE$));
      }

      public Seq createConstructorDescriptors(final Iterable ccs) {
         return (Seq)((IterableOps)scala.Option..MODULE$.apply(ccs).map((x$9) -> x$9.toSeq()).getOrElse(() -> .MODULE$.Nil())).map((ctor) -> {
            Seq ctorParameterNames = (Seq)(Modifier.isPublic(ctor.getModifiers()) && ctor.getParameterTypes().length > 0 ? (Seq)scala.util.control.Exception..MODULE$.allCatch().opt(() -> this.paramNameReader.lookupParameterNames(ctor)).getOrElse(() -> .MODULE$.Nil()) : .MODULE$.Nil());
            Type[] types = ctor.getGenericParameterTypes();
            int diff = ctorParameterNames.length() - types.length;
            Seq genParams = (Seq)(diff == 0 ? scala.Predef..MODULE$.wrapRefArray((Object[])types).toVector() : (diff > 0 ? (Seq).MODULE$.Vector().fill(diff, () -> null).$plus$plus(scala.Predef..MODULE$.wrapRefArray((Object[])types).toVector()) : .MODULE$.Vector().empty()));
            Seq ctorParams = (Seq)((IterableOps)ctorParameterNames.zipWithIndex()).map((x0$1) -> {
               ConstructorParamDescriptor var5;
               int index;
               label52: {
                  if (x0$1 != null) {
                     String var8 = (String)x0$1._1();
                     index = x0$1._2$mcI$sp();
                     String var10000 = ScalaSigReader$.MODULE$.OuterFieldName();
                     if (var10000 == null) {
                        if (var8 == null) {
                           break label52;
                        }
                     } else if (var10000.equals(var8)) {
                        break label52;
                     }
                  }

                  if (x0$1 == null) {
                     throw new MatchError(x0$1);
                  }

                  Object var16_1;
                  String paramName;
                  int index;
                  String decoded;
                  label34: {
                     paramName = (String)x0$1._1();
                     index = x0$1._2$mcI$sp();
                     this.companion_$eq(this.findCompanion(false));
                     decoded = Reflector$.MODULE$.unmangleName(paramName);
                     Tuple2 var17 = new Tuple2(this.companion(), ctor.defaultValuePattern());
                     if (var17 != null) {
                        Option var18 = (Option)var17._1();
                        Option var19 = (Option)var17._2();
                        if (var18 instanceof Some) {
                           Some var20 = (Some)var18;
                           SingletonDescriptor comp = (SingletonDescriptor)var20.value();
                           if (var19 instanceof Some) {
                              Some var22 = (Some)var19;
                              String pattern = (String)var22.value();
                              var16_1 = Reflector$.MODULE$.defaultValue(comp.erasure().erasure(), comp.instance(), index, pattern);
                              break label34;
                           }
                        }
                     }

                     var16_1 = scala.None..MODULE$;
                  }

                  ScalaType theType = this.ctorParamType(paramName, index, this.tpe, ((IterableOnceOps)ctorParameterNames.filterNot((x$13) -> BoxesRunTime.boxToBoolean($anonfun$createConstructorDescriptors$12(x$13)))).toList(), (Type)genParams.apply(index), this.ctorParamType$default$6());
                  var5 = new ConstructorParamDescriptor(decoded, paramName, index, theType, (Option)var16_1);
                  return var5;
               }

               if (this.tpe.erasure().getDeclaringClass() == null) {
                  throw package$.MODULE$.fail("Classes defined in method bodies are not supported.", package$.MODULE$.fail$default$2());
               } else {
                  this.companion_$eq(this.findCompanion(true));
                  Option var11 = this.companionMappings.find((x$10) -> BoxesRunTime.boxToBoolean($anonfun$createConstructorDescriptors$8(this, x$10))).map((x$11) -> x$11._2()).map((x$12) -> () -> x$12);
                  ScalaType tt = Reflector$.MODULE$.scalaTypeOf(this.tpe.erasure().getDeclaringClass());
                  var5 = new ConstructorParamDescriptor(ScalaSigReader$.MODULE$.OuterFieldName(), ScalaSigReader$.MODULE$.OuterFieldName(), index, tt, var11);
                  return var5;
               }
            });
            return new ConstructorDescriptor(ctorParams, ctor, ctor.getMarkedAsPrimary());
         });
      }

      public Option findCompanion(final boolean checkCompanionMapping) {
         Option var10000;
         if (checkCompanionMapping) {
            Option mapping = this.companionMappings.find((x$14) -> BoxesRunTime.boxToBoolean($anonfun$findCompanion$1(this, x$14))).map((x$15) -> x$15._2());
            var10000 = mapping.map((m) -> {
               Object inst = m.getClass().getMethod(this.tpe.simpleName()).invoke(m);
               Class kl = inst.getClass();
               return new SingletonDescriptor(package$.MODULE$.safeSimpleName(kl), kl.getName(), Reflector$.MODULE$.scalaTypeOf(kl), inst, (Seq).MODULE$.Seq().empty());
            });
         } else if (this.companion().isEmpty() && !this.triedCompanion()) {
            this.triedCompanion_$eq(true);
            var10000 = ScalaSigReader$.MODULE$.companions(this.tpe.rawFullName(), ScalaSigReader$.MODULE$.companions$default$2(), ScalaSigReader$.MODULE$.companions$default$3()).collect(new Serializable() {
               private static final long serialVersionUID = 0L;

               public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
                  Object var3;
                  if (x1 != null) {
                     Class kl = (Class)x1._1();
                     Option var6 = (Option)x1._2();
                     if (var6 instanceof Some) {
                        Some var7 = (Some)var6;
                        Object cOpt = var7.value();
                        var3 = new SingletonDescriptor(package$.MODULE$.safeSimpleName(kl), kl.getName(), Reflector$.MODULE$.scalaTypeOf(kl), cOpt, (Seq).MODULE$.Seq().empty());
                        return var3;
                     }
                  }

                  var3 = default.apply(x1);
                  return var3;
               }

               public final boolean isDefinedAt(final Tuple2 x1) {
                  boolean var2;
                  if (x1 != null) {
                     Option var4 = (Option)x1._2();
                     if (var4 instanceof Some) {
                        var2 = true;
                        return var2;
                     }
                  }

                  var2 = false;
                  return var2;
               }
            });
         } else {
            var10000 = this.companion();
         }

         return var10000;
      }

      public ClassDescriptor result() {
         Seq constructors = this.constructorsAndCompanion();
         return new ClassDescriptor(this.tpe.simpleName(), this.tpe.fullName(), this.tpe, this.companion(), constructors, this.properties());
      }

      // $FF: synthetic method
      public static final boolean $anonfun$constructorsAndCompanion$3(final Constructor x$7) {
         return (new Executable(x$7, false)).getMarkedAsPrimary();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$constructorsAndCompanion$7(final Class er$1, final Method method) {
         boolean var5;
         label32: {
            label24: {
               String var10000 = method.getName();
               String var2 = "apply";
               if (var10000 == null) {
                  if (var2 != null) {
                     break label24;
                  }
               } else if (!var10000.equals(var2)) {
                  break label24;
               }

               Class var4 = method.getReturnType();
               if (var4 == null) {
                  if (er$1 == null) {
                     break label32;
                  }
               } else if (var4.equals(er$1)) {
                  break label32;
               }
            }

            var5 = false;
            return var5;
         }

         var5 = true;
         return var5;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$createConstructorDescriptors$8(final ClassDescriptorBuilder $this, final Tuple2 x$10) {
         boolean var3;
         label23: {
            Object var10000 = x$10._1();
            Class var2 = $this.tpe.erasure();
            if (var10000 == null) {
               if (var2 == null) {
                  break label23;
               }
            } else if (var10000.equals(var2)) {
               break label23;
            }

            var3 = false;
            return var3;
         }

         var3 = true;
         return var3;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$createConstructorDescriptors$12(final String x$13) {
         boolean var10000;
         label23: {
            String var1 = ScalaSigReader$.MODULE$.OuterFieldName();
            if (x$13 == null) {
               if (var1 == null) {
                  break label23;
               }
            } else if (x$13.equals(var1)) {
               break label23;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$findCompanion$1(final ClassDescriptorBuilder $this, final Tuple2 x$14) {
         boolean var3;
         label23: {
            Object var10000 = x$14._1();
            Class var2 = $this.tpe.erasure();
            if (var10000 == null) {
               if (var2 == null) {
                  break label23;
               }
            } else if (var10000.equals(var2)) {
               break label23;
            }

            var3 = false;
            return var3;
         }

         var3 = true;
         return var3;
      }

      public ClassDescriptorBuilder(final ScalaType tpe, final ParameterNameReader paramNameReader, final List companionMappings, final Formats formats) {
         this.tpe = tpe;
         this.paramNameReader = paramNameReader;
         this.companionMappings = companionMappings;
         this.formats = formats;
         this.companion = scala.None..MODULE$;
         this.triedCompanion = false;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
