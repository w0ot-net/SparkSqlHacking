package org.apache.spark.deploy.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005u2A!\u0002\u0004\u0005#!)a\u0003\u0001C\u0001/!9\u0011\u0004\u0001b\u0001\n\u0013Q\u0002BB\u0012\u0001A\u0003%1\u0004C\u0003%\u0001\u0011ESE\u0001\u0007FeJ|'oU3sm2,GO\u0003\u0002\b\u0011\u0005!!/Z:u\u0015\tI!\"\u0001\u0004eKBdw.\u001f\u0006\u0003\u00171\tQa\u001d9be.T!!\u0004\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0011aA8sO\u000e\u00011C\u0001\u0001\u0013!\t\u0019B#D\u0001\u0007\u0013\t)bAA\u0006SKN$8+\u001a:wY\u0016$\u0018A\u0002\u001fj]&$h\bF\u0001\u0019!\t\u0019\u0002!A\u0007tKJ4XM\u001d,feNLwN\\\u000b\u00027A\u0011A$I\u0007\u0002;)\u0011adH\u0001\u0005Y\u0006twMC\u0001!\u0003\u0011Q\u0017M^1\n\u0005\tj\"AB*ue&tw-\u0001\btKJ4XM\u001d,feNLwN\u001c\u0011\u0002\u000fM,'O^5dKR\u0019a\u0005\f\u001d\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0003%\nQa]2bY\u0006L!a\u000b\u0015\u0003\tUs\u0017\u000e\u001e\u0005\u0006[\u0011\u0001\rAL\u0001\be\u0016\fX/Z:u!\tyc'D\u00011\u0015\t\t$'\u0001\u0003iiR\u0004(BA\u001a5\u0003\u001d\u0019XM\u001d<mKRT\u0011!N\u0001\bU\u0006\\\u0017M\u001d;b\u0013\t9\u0004G\u0001\nIiR\u00048+\u001a:wY\u0016$(+Z9vKN$\b\"B\u001d\u0005\u0001\u0004Q\u0014\u0001\u0003:fgB|gn]3\u0011\u0005=Z\u0014B\u0001\u001f1\u0005MAE\u000f\u001e9TKJ4H.\u001a;SKN\u0004xN\\:f\u0001"
)
public class ErrorServlet extends RestServlet {
   private final String serverVersion;

   private String serverVersion() {
      return this.serverVersion;
   }

   public void service(final HttpServletRequest request, final HttpServletResponse response) {
      String path = request.getPathInfo();
      List parts = .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps((Object[])scala.collection.StringOps..MODULE$.stripPrefix$extension(.MODULE$.augmentString(path), "/").split("/")), (x$5) -> BoxesRunTime.boxToBoolean($anonfun$service$1(x$5)))).toList();
      boolean versionMismatch = false;
      boolean var8 = false;
      scala.collection.immutable..colon.colon var9 = null;
      String var10000;
      if (scala.collection.immutable.Nil..MODULE$.equals(parts)) {
         var10000 = "Missing protocol version.";
      } else {
         label60: {
            if (parts instanceof scala.collection.immutable..colon.colon) {
               label54: {
                  var8 = true;
                  var9 = (scala.collection.immutable..colon.colon)parts;
                  String var11 = (String)var9.head();
                  List var12 = var9.next$access$1();
                  var10000 = this.serverVersion();
                  if (var10000 == null) {
                     if (var11 != null) {
                        break label54;
                     }
                  } else if (!var10000.equals(var11)) {
                     break label54;
                  }

                  if (scala.collection.immutable.Nil..MODULE$.equals(var12)) {
                     var10000 = "Missing the /submissions prefix.";
                     break label60;
                  }
               }
            }

            if (var8) {
               label45: {
                  String var14 = (String)var9.head();
                  List var15 = var9.next$access$1();
                  var10000 = this.serverVersion();
                  if (var10000 == null) {
                     if (var14 != null) {
                        break label45;
                     }
                  } else if (!var10000.equals(var14)) {
                     break label45;
                  }

                  if (var15 instanceof scala.collection.immutable..colon.colon) {
                     scala.collection.immutable..colon.colon var17 = (scala.collection.immutable..colon.colon)var15;
                     String var18 = (String)var17.head();
                     if ("submissions".equals(var18)) {
                        var10000 = "Missing an action: please specify one of /create, /kill, /killall, /clear, /status, or /readyz.";
                        break label60;
                     }
                  }
               }
            }

            if (var8) {
               String unknownVersion = (String)var9.head();
               versionMismatch = true;
               var10000 = "Unknown protocol version '" + unknownVersion + "'.";
            } else {
               var10000 = "Malformed path.";
            }
         }
      }

      String msg = var10000;
      msg = msg + " Please submit requests through http://[host]:[port]/" + this.serverVersion() + "/submissions/...";
      ErrorResponse error = this.handleError(msg);
      if (versionMismatch) {
         error.highestProtocolVersion_$eq(this.serverVersion());
         response.setStatus(RestSubmissionServer$.MODULE$.SC_UNKNOWN_PROTOCOL_VERSION());
      } else {
         response.setStatus(400);
      }

      this.sendResponse(error, response);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$service$1(final String x$5) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(x$5));
   }

   public ErrorServlet() {
      this.serverVersion = RestSubmissionServer$.MODULE$.PROTOCOL_VERSION();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
