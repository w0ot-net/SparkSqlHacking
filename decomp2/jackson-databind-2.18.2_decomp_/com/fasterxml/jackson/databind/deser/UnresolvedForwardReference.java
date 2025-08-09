package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UnresolvedForwardReference extends JsonMappingException {
   private static final long serialVersionUID = 1L;
   private ReadableObjectId _roid;
   private List _unresolvedIds;

   public UnresolvedForwardReference(JsonParser p, String msg, JsonLocation loc, ReadableObjectId roid) {
      super((Closeable)p, (String)msg, (JsonLocation)loc);
      this._roid = roid;
   }

   public UnresolvedForwardReference(JsonParser p, String msg) {
      super((Closeable)p, (String)msg);
      this._unresolvedIds = new ArrayList();
   }

   public ReadableObjectId getRoid() {
      return this._roid;
   }

   public Object getUnresolvedId() {
      return this._roid.getKey().key;
   }

   public void addUnresolvedId(Object id, Class type, JsonLocation where) {
      this._unresolvedIds.add(new UnresolvedId(id, type, where));
   }

   public List getUnresolvedIds() {
      return this._unresolvedIds;
   }

   public String getMessage() {
      String msg = super.getMessage();
      if (this._unresolvedIds == null) {
         return msg;
      } else {
         StringBuilder sb = new StringBuilder(msg);
         Iterator<UnresolvedId> iterator = this._unresolvedIds.iterator();

         while(iterator.hasNext()) {
            UnresolvedId unresolvedId = (UnresolvedId)iterator.next();
            sb.append(unresolvedId.toString());
            if (iterator.hasNext()) {
               sb.append(", ");
            }
         }

         sb.append('.');
         return sb.toString();
      }
   }

   public synchronized UnresolvedForwardReference fillInStackTrace() {
      return this;
   }

   public UnresolvedForwardReference withStackTrace() {
      super.fillInStackTrace();
      return this;
   }
}
