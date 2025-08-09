package jakarta.ws.rs;

import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NotAuthorizedException extends ClientErrorException {
   private static final long serialVersionUID = -3156040750581929702L;
   private transient List challenges;

   public NotAuthorizedException(Object challenge, Object... moreChallenges) {
      super(createUnauthorizedResponse(challenge, moreChallenges));
      this.challenges = cacheChallenges(challenge, moreChallenges);
   }

   public NotAuthorizedException(String message, Object challenge, Object... moreChallenges) {
      super(message, createUnauthorizedResponse(challenge, moreChallenges));
      this.challenges = cacheChallenges(challenge, moreChallenges);
   }

   public NotAuthorizedException(Response response) {
      super(validate(response, Response.Status.UNAUTHORIZED));
   }

   public NotAuthorizedException(String message, Response response) {
      super(message, validate(response, Response.Status.UNAUTHORIZED));
   }

   public NotAuthorizedException(Throwable cause, Object challenge, Object... moreChallenges) {
      super(createUnauthorizedResponse(challenge, moreChallenges), cause);
      this.challenges = cacheChallenges(challenge, moreChallenges);
   }

   public NotAuthorizedException(String message, Throwable cause, Object challenge, Object... moreChallenges) {
      super(message, createUnauthorizedResponse(challenge, moreChallenges), cause);
      this.challenges = cacheChallenges(challenge, moreChallenges);
   }

   public NotAuthorizedException(Response response, Throwable cause) {
      super(validate(response, Response.Status.UNAUTHORIZED), cause);
   }

   public NotAuthorizedException(String message, Response response, Throwable cause) {
      super(message, validate(response, Response.Status.UNAUTHORIZED), cause);
   }

   public List getChallenges() {
      if (this.challenges == null) {
         this.challenges = (List)this.getResponse().getHeaders().get("WWW-Authenticate");
      }

      return this.challenges;
   }

   private static Response createUnauthorizedResponse(Object challenge, Object[] otherChallenges) {
      if (challenge == null) {
         throw new NullPointerException("Primary challenge parameter must not be null.");
      } else {
         Response.ResponseBuilder builder = Response.status(Response.Status.UNAUTHORIZED).header("WWW-Authenticate", challenge);
         if (otherChallenges != null) {
            for(Object oc : otherChallenges) {
               builder.header("WWW-Authenticate", oc);
            }
         }

         return builder.build();
      }
   }

   private static List cacheChallenges(Object challenge, Object[] moreChallenges) {
      List<Object> temp = new ArrayList(1 + (moreChallenges == null ? 0 : moreChallenges.length));
      temp.add(challenge);
      if (moreChallenges != null) {
         temp.addAll(Arrays.asList(moreChallenges));
      }

      return Collections.unmodifiableList(temp);
   }
}
