package org.sparkproject.jetty.server.session;

public class UnreadableSessionDataException extends Exception {
   private static final long serialVersionUID = 1806303483488966566L;
   private String _id;
   private SessionContext _sessionContext;

   public String getId() {
      return this._id;
   }

   public SessionContext getSessionContext() {
      return this._sessionContext;
   }

   public UnreadableSessionDataException(String id, SessionContext sessionContext, Throwable t) {
      super("Unreadable session " + id + " for " + String.valueOf(sessionContext), t);
      this._sessionContext = sessionContext;
      this._id = id;
   }
}
