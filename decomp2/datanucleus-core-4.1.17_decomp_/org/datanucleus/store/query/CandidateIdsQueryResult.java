package org.datanucleus.store.query;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.SoftValueMap;
import org.datanucleus.util.StringUtils;
import org.datanucleus.util.WeakValueMap;

public class CandidateIdsQueryResult extends AbstractQueryResult {
   final List ids;
   Map results = null;
   boolean validateObjects = true;

   public CandidateIdsQueryResult(Query query, List ids) {
      super(query);
      this.ids = ids;
      this.size = ids != null ? ids.size() : 0;
      this.validateObjects = query.getBooleanExtensionProperty("datanucleus.query.resultCache.validateObjects", true);
      String ext = (String)query.getExtension("datanucleus.query.resultCache.type");
      if (ext != null) {
         if (ext.equalsIgnoreCase("soft")) {
            this.results = new SoftValueMap();
         } else if (ext.equalsIgnoreCase("strong")) {
            this.results = new HashMap();
         } else if (ext.equalsIgnoreCase("weak")) {
            this.results = new WeakValueMap();
         } else if (ext.equalsIgnoreCase("none")) {
            this.results = null;
         } else {
            this.results = new HashMap();
         }
      } else {
         this.results = new HashMap();
      }

   }

   protected void closeResults() {
   }

   protected void closingConnection() {
      if (this.loadResultsAtCommit && this.isOpen()) {
         for(int i = 0; i < this.size; ++i) {
            this.getObjectForIndex(i);
         }
      }

   }

   public boolean equals(Object o) {
      if (o != null && o instanceof CandidateIdsQueryResult) {
         CandidateIdsQueryResult other = (CandidateIdsQueryResult)o;
         if (this.query != null) {
            return other.query == this.query;
         } else {
            return StringUtils.toJVMIDString(other).equals(StringUtils.toJVMIDString(this));
         }
      } else {
         return false;
      }
   }

   public Object get(int index) {
      if (index >= 0 && index < this.size) {
         return this.getObjectForIndex(index);
      } else {
         throw new ArrayIndexOutOfBoundsException("Index should be between 0 and " + (this.size - 1));
      }
   }

   public Iterator iterator() {
      return new ResultIterator();
   }

   public ListIterator listIterator() {
      return new ResultIterator();
   }

   protected Object getObjectForIndex(int index) {
      if (this.ids == null) {
         return null;
      } else {
         Object id = this.ids.get(index);
         if (this.results != null) {
            E obj = (E)this.results.get(index);
            if (obj != null) {
               return obj;
            }
         }

         if (this.query == null) {
            throw new NucleusUserException("Query has already been closed");
         } else if (this.query.getExecutionContext() != null && !this.query.getExecutionContext().isClosed()) {
            ExecutionContext ec = this.query.getExecutionContext();
            E obj = (E)ec.findObject(id, this.validateObjects, false, (String)null);
            if (this.results != null) {
               this.results.put(index, obj);
            }

            return obj;
         } else {
            throw new NucleusUserException("ExecutionContext has already been closed");
         }
      }
   }

   protected Object writeReplace() throws ObjectStreamException {
      this.disconnect();
      List results = new ArrayList(this.results.size());

      for(int i = 0; i < this.results.size(); ++i) {
         results.add(this.results.get(i));
      }

      return results;
   }

   public class ResultIterator extends AbstractQueryResultIterator {
      int next = 0;

      public boolean hasNext() {
         if (!CandidateIdsQueryResult.this.isOpen()) {
            return false;
         } else {
            return CandidateIdsQueryResult.this.size - this.next > 0;
         }
      }

      public boolean hasPrevious() {
         return this.next >= 1;
      }

      public Object next() {
         if (this.next == CandidateIdsQueryResult.this.size) {
            throw new NoSuchElementException("Already at end of List");
         } else {
            E obj = (E)CandidateIdsQueryResult.this.getObjectForIndex(this.next);
            ++this.next;
            return obj;
         }
      }

      public int nextIndex() {
         return this.next;
      }

      public Object previous() {
         if (this.next == 0) {
            throw new NoSuchElementException("Already at start of List");
         } else {
            E obj = (E)CandidateIdsQueryResult.this.getObjectForIndex(this.next - 1);
            --this.next;
            return obj;
         }
      }

      public int previousIndex() {
         return this.next - 1;
      }
   }
}
