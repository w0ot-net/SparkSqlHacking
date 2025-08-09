package org.datanucleus.api.jdo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.jdo.Extent;
import javax.jdo.FetchGroup;
import javax.jdo.FetchPlan;
import javax.jdo.JDOException;
import javax.jdo.JDOFatalUserException;
import javax.jdo.JDONullIdentityException;
import javax.jdo.JDOOptimisticVerificationException;
import javax.jdo.JDOQLTypedQuery;
import javax.jdo.JDOUnsupportedOptionException;
import javax.jdo.JDOUserException;
import javax.jdo.ObjectState;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.jdo.datastore.JDOConnection;
import javax.jdo.datastore.Sequence;
import javax.jdo.identity.SingleFieldIdentity;
import javax.jdo.listener.InstanceLifecycleListener;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.Configuration;
import org.datanucleus.ExecutionContext;
import org.datanucleus.TransactionEventListener;
import org.datanucleus.api.jdo.exceptions.TransactionNotActiveException;
import org.datanucleus.api.jdo.exceptions.TransactionNotWritableException;
import org.datanucleus.api.jdo.query.JDOQLTypedQueryImpl;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusOptimisticException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.exceptions.TransactionActiveOnCloseException;
import org.datanucleus.identity.SCOID;
import org.datanucleus.identity.SingleFieldId;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.ExtensionMetaData;
import org.datanucleus.metadata.FetchGroupMetaData;
import org.datanucleus.metadata.FetchPlanMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.QueryLanguage;
import org.datanucleus.metadata.QueryMetaData;
import org.datanucleus.metadata.SequenceMetaData;
import org.datanucleus.state.CallbackHandler;
import org.datanucleus.state.DetachState;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.store.NucleusConnection;
import org.datanucleus.store.NucleusSequence;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class JDOPersistenceManager implements PersistenceManager {
   public static final NucleusLogger LOGGER = NucleusLogger.getLoggerInstance("DataNucleus.JDO");
   private boolean closed = false;
   private Map userObjectMap = null;
   private Object userObject = null;
   protected ExecutionContext ec;
   protected Transaction jdotx;
   protected JDOPersistenceManagerFactory pmf;
   protected JDOFetchPlan fetchPlan = null;
   private Set jdoFetchGroups = null;

   public JDOPersistenceManager(JDOPersistenceManagerFactory pmf, String userName, String password) {
      Map<String, Object> options = new HashMap();
      options.put("user", userName);
      options.put("password", password);
      this.ec = pmf.getNucleusContext().getExecutionContext(this, options);
      this.pmf = pmf;
      this.fetchPlan = new JDOFetchPlan(this.ec.getFetchPlan());
      this.jdotx = new JDOTransaction(this, this.ec.getTransaction());
      CallbackHandler beanValidator = pmf.getNucleusContext().getValidationHandler(this.ec);
      if (beanValidator != null) {
         this.ec.getCallbackHandler().setValidationListener(beanValidator);
      }

   }

   public void close() {
      if (this.pmf != null) {
         this.pmf.releasePersistenceManager(this);
      }

   }

   public boolean isClosed() {
      return this.closed;
   }

   protected void internalClose() {
      if (!this.closed) {
         try {
            this.ec.close();
         } catch (TransactionActiveOnCloseException tae) {
            throw new JDOUserException(tae.getMessage(), this);
         } catch (NucleusException ne) {
            throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
         }

         this.userObject = null;
         this.userObjectMap = null;
         this.fetchPlan = null;
         this.jdoFetchGroups = null;
         this.jdotx = null;
         this.ec = null;
         this.pmf = null;
         this.closed = true;
      }
   }

   public ExecutionContext getExecutionContext() {
      return this.ec;
   }

   public JDOPersistenceManagerFactory getPersistenceManagerFactory() {
      return this.pmf;
   }

   public boolean getDetachAllOnCommit() {
      this.assertIsOpen();
      return this.ec.getBooleanProperty("datanucleus.DetachAllOnCommit");
   }

   public boolean getCopyOnAttach() {
      this.assertIsOpen();
      return this.ec.getBooleanProperty("datanucleus.CopyOnAttach");
   }

   public FetchPlan getFetchPlan() {
      return this.fetchPlan;
   }

   public boolean getIgnoreCache() {
      this.assertIsOpen();
      return this.ec.getBooleanProperty("datanucleus.IgnoreCache");
   }

   public boolean getMultithreaded() {
      this.assertIsOpen();
      return this.ec.getBooleanProperty("datanucleus.Multithreaded");
   }

   public void setDetachAllOnCommit(boolean flag) {
      this.assertIsOpen();
      this.ec.setProperty("datanucleus.DetachAllOnCommit", flag);
   }

   public void setCopyOnAttach(boolean flag) {
      this.assertIsOpen();
      this.ec.setProperty("datanucleus.CopyOnAttach", flag);
   }

   public void setIgnoreCache(boolean flag) {
      this.assertIsOpen();
      this.ec.setProperty("datanucleus.IgnoreCache", flag);
   }

   public void setMultithreaded(boolean flag) {
      this.assertIsOpen();
      this.ec.setProperty("datanucleus.Multithreaded", flag);
   }

   public void setDatastoreReadTimeoutMillis(Integer timeout) {
      this.assertIsOpen();
      if (!this.ec.getStoreManager().getSupportedOptions().contains("Datastore.Timeout")) {
         throw new JDOUnsupportedOptionException("This datastore doesn't support read timeouts");
      } else {
         this.ec.setProperty("datanucleus.datastoreReadTimeout", timeout);
      }
   }

   public Integer getDatastoreReadTimeoutMillis() {
      this.assertIsOpen();
      return this.ec.getIntProperty("datanucleus.datastoreReadTimeout");
   }

   public void setDatastoreWriteTimeoutMillis(Integer timeout) {
      this.assertIsOpen();
      if (!this.ec.getStoreManager().getSupportedOptions().contains("Datastore.Timeout")) {
         throw new JDOUnsupportedOptionException("This datastore doesn't support write timeouts");
      } else {
         this.ec.setProperty("datanucleus.datastoreWriteTimeout", timeout);
      }
   }

   public Integer getDatastoreWriteTimeoutMillis() {
      this.assertIsOpen();
      return this.ec.getIntProperty("datanucleus.datastoreWriteTimeout");
   }

   public Date getServerDate() {
      this.assertIsOpen();

      try {
         return this.ec.getStoreManager().getDatastoreDate();
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public Transaction currentTransaction() {
      this.assertIsOpen();
      return this.jdotx;
   }

   private void jdoEvict(Object obj) {
      try {
         this.ec.evictObject(obj);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public void evict(Object obj) {
      this.assertIsOpen();
      this.jdoEvict(obj);
   }

   public void evictAll(boolean subclasses, Class cls) {
      this.assertIsOpen();

      try {
         this.ec.evictObjects(cls, subclasses);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public void evictAll(Object... pcs) {
      this.evictAll((Collection)Arrays.asList(pcs));
   }

   public void evictAll(Collection pcs) {
      this.assertIsOpen();
      ArrayList failures = new ArrayList();
      Iterator i = pcs.iterator();

      while(i.hasNext()) {
         try {
            this.jdoEvict(i.next());
         } catch (JDOException e) {
            failures.add(e);
         }
      }

      if (!failures.isEmpty()) {
         throw new JDOUserException(Localiser.msg("010036"), (Exception[])failures.toArray(new Exception[failures.size()]));
      }
   }

   public void evictAll() {
      this.assertIsOpen();
      this.ec.evictAllObjects();
   }

   private void jdoRefresh(Object obj) {
      try {
         this.ec.refreshObject(obj);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public void refresh(Object obj) {
      this.assertIsOpen();
      this.jdoRefresh(obj);
   }

   public void refreshAll(Object... pcs) {
      this.refreshAll((Collection)Arrays.asList(pcs));
   }

   public void refreshAll(Collection pcs) {
      this.assertIsOpen();
      ArrayList failures = new ArrayList();
      Iterator iter = pcs.iterator();

      while(iter.hasNext()) {
         try {
            this.jdoRefresh(iter.next());
         } catch (JDOException e) {
            failures.add(e);
         }
      }

      if (!failures.isEmpty()) {
         throw new JDOUserException(Localiser.msg("010037"), (Exception[])failures.toArray(new Exception[failures.size()]));
      }
   }

   public void refreshAll() {
      this.assertIsOpen();

      try {
         this.ec.refreshAllObjects();
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public void refreshAll(JDOException exc) {
      Object obj = exc.getFailedObject();
      if (obj != null) {
         this.refresh(obj);
      }

      Throwable[] nested_excs = exc.getNestedExceptions();
      if (nested_excs != null) {
         for(int i = 0; i < nested_excs.length; ++i) {
            if (nested_excs[i] instanceof JDOException) {
               this.refreshAll((JDOException)nested_excs[i]);
            }
         }
      }

   }

   private void jdoRetrieve(Object obj, boolean useFetchPlan) {
      try {
         this.ec.retrieveObject(obj, useFetchPlan);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public void retrieve(Object pc, boolean useFetchPlan) {
      this.assertIsOpen();
      this.jdoRetrieve(pc, useFetchPlan);
   }

   public void retrieve(Object pc) {
      this.retrieve(pc, false);
   }

   public void retrieveAll(Object... pcs) {
      this.retrieveAll(Arrays.asList(pcs), false);
   }

   public void retrieveAll(boolean useFetchPlan, Object... pcs) {
      this.retrieveAll(Arrays.asList(pcs), useFetchPlan);
   }

   public void retrieveAll(Collection pcs, boolean useFetchPlan) {
      this.assertIsOpen();
      ArrayList failures = new ArrayList();
      Iterator i = pcs.iterator();

      while(i.hasNext()) {
         try {
            this.jdoRetrieve(i.next(), useFetchPlan);
         } catch (RuntimeException e) {
            failures.add(e);
         }
      }

      if (!failures.isEmpty()) {
         throw new JDOUserException(Localiser.msg("010038"), (Exception[])failures.toArray(new Exception[failures.size()]));
      }
   }

   public void retrieveAll(Collection pcs) {
      this.retrieveAll(pcs, false);
   }

   private Object jdoMakePersistent(Object obj) {
      try {
         return this.ec.persistObject(obj, false);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public Object makePersistent(Object obj) {
      this.assertIsOpen();
      this.assertWritable();
      return obj == null ? null : this.jdoMakePersistent(obj);
   }

   public Object[] makePersistentAll(Object... pcs) {
      return this.makePersistentAll((Collection)Arrays.asList(pcs)).toArray();
   }

   public Collection makePersistentAll(Collection pcs) {
      this.assertIsOpen();
      this.assertWritable();

      try {
         Object[] persistedPcs = this.ec.persistObjects(pcs.toArray());
         Collection persisted = new ArrayList();

         for(int i = 0; i < persistedPcs.length; ++i) {
            persisted.add(persistedPcs[i]);
         }

         return persisted;
      } catch (NucleusUserException nue) {
         Throwable[] failures = nue.getNestedExceptions();
         throw new JDOUserException(Localiser.msg("010039"), failures);
      }
   }

   private void jdoDeletePersistent(Object obj) {
      try {
         this.ec.deleteObject(obj);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public void deletePersistent(Object obj) {
      this.assertIsOpen();
      this.assertWritable();
      this.jdoDeletePersistent(obj);
   }

   public void deletePersistentAll(Object... pcs) {
      this.deletePersistentAll((Collection)Arrays.asList(pcs));
   }

   public void deletePersistentAll(Collection pcs) {
      this.assertIsOpen();
      this.assertWritable();

      try {
         this.ec.deleteObjects(pcs.toArray());
      } catch (NucleusUserException nue) {
         Throwable[] failures = nue.getNestedExceptions();
         throw new JDOUserException(Localiser.msg("010040"), failures);
      }
   }

   private void jdoMakeTransient(Object pc, FetchPlanState state) {
      try {
         this.ec.makeObjectTransient(pc, state);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public void makeTransient(Object pc, boolean useFetchPlan) {
      this.assertIsOpen();
      FetchPlanState state = null;
      if (useFetchPlan) {
         state = new FetchPlanState();
      }

      this.jdoMakeTransient(pc, state);
   }

   public void makeTransient(Object pc) {
      this.makeTransient(pc, false);
   }

   public void makeTransientAll(Object... pcs) {
      this.makeTransientAll((Collection)Arrays.asList(pcs));
   }

   public void makeTransientAll(boolean includeFetchPlan, Object... pcs) {
      this.makeTransientAll(Arrays.asList(pcs), includeFetchPlan);
   }

   public void makeTransientAll(Collection pcs, boolean useFetchPlan) {
      this.assertIsOpen();
      ArrayList failures = new ArrayList();
      Iterator i = pcs.iterator();
      FetchPlanState state = null;
      if (useFetchPlan) {
         state = new FetchPlanState();
      }

      while(i.hasNext()) {
         try {
            this.jdoMakeTransient(i.next(), state);
         } catch (RuntimeException e) {
            failures.add(e);
         }
      }

      if (!failures.isEmpty()) {
         throw new JDOUserException(Localiser.msg("010041"), (Exception[])failures.toArray(new Exception[failures.size()]));
      }
   }

   public void makeTransientAll(Collection pcs) {
      this.makeTransientAll(pcs, false);
   }

   private void jdoMakeTransactional(Object pc) {
      try {
         this.ec.makeObjectTransactional(pc);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public void makeTransactional(Object pc) {
      this.assertIsOpen();
      this.jdoMakeTransactional(pc);
   }

   public void makeTransactionalAll(Object... pcs) {
      this.makeTransactionalAll((Collection)Arrays.asList(pcs));
   }

   public void makeTransactionalAll(Collection pcs) {
      this.assertIsOpen();
      this.assertActiveTransaction();
      ArrayList failures = new ArrayList();
      Iterator i = pcs.iterator();

      while(i.hasNext()) {
         try {
            this.jdoMakeTransactional(i.next());
         } catch (RuntimeException e) {
            failures.add(e);
         }
      }

      if (!failures.isEmpty()) {
         throw new JDOUserException(Localiser.msg("010042"), (Exception[])failures.toArray(new Exception[failures.size()]));
      }
   }

   private void jdoMakeNontransactional(Object obj) {
      try {
         this.ec.makeObjectNontransactional(obj);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public void makeNontransactional(Object pc) {
      this.assertIsOpen();
      if (pc != null) {
         if (!((Persistable)pc).dnIsTransactional() && !((Persistable)pc).dnIsPersistent()) {
            throw new JDOUserException(Localiser.msg("011004"));
         } else if (((Persistable)pc).dnIsTransactional() || !((Persistable)pc).dnIsPersistent()) {
            this.jdoMakeNontransactional(pc);
         }
      }
   }

   public void makeNontransactionalAll(Object... pcs) {
      this.makeNontransactionalAll((Collection)Arrays.asList(pcs));
   }

   public void makeNontransactionalAll(Collection pcs) {
      this.assertIsOpen();
      this.assertActiveTransaction();
      ArrayList failures = new ArrayList();
      Iterator i = pcs.iterator();

      while(i.hasNext()) {
         try {
            this.jdoMakeNontransactional(i.next());
         } catch (RuntimeException e) {
            failures.add(e);
         }
      }

      if (!failures.isEmpty()) {
         throw new JDOUserException(Localiser.msg("010043"), (Exception[])failures.toArray(new Exception[failures.size()]));
      }
   }

   private Object jdoDetachCopy(Object obj, FetchPlanState state) {
      this.ec.assertClassPersistable(obj.getClass());

      try {
         return this.ec.detachObjectCopy(obj, state);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public Object detachCopy(Object pc) {
      this.assertIsOpen();
      if (pc == null) {
         return null;
      } else {
         try {
            this.ec.assertClassPersistable(pc.getClass());
            this.assertReadable("detachCopy");
            return this.jdoDetachCopy(pc, new DetachState(this.ec.getApiAdapter()));
         } catch (NucleusException ne) {
            throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
         }
      }
   }

   public Object[] detachCopyAll(Object... pcs) {
      return this.detachCopyAll((Collection)Arrays.asList(pcs)).toArray();
   }

   public Collection detachCopyAll(Collection pcs) {
      this.assertIsOpen();
      this.assertReadable("detachCopyAll");
      FetchPlanState state = new DetachState(this.ec.getApiAdapter());
      List detacheds = new ArrayList();

      for(Object obj : pcs) {
         if (obj == null) {
            detacheds.add((Object)null);
         } else {
            detacheds.add(this.jdoDetachCopy(obj, state));
         }
      }

      return detacheds;
   }

   public Query newQuery() {
      return this.newQuery((String)"javax.jdo.query.JDOQL", (Object)null);
   }

   public Query newQuery(Object obj) {
      if (obj != null && obj instanceof JDOQuery) {
         String language = ((JDOQuery)obj).getLanguage();
         return this.newQuery(language, obj);
      } else {
         return this.newQuery((String)null, (Object)obj);
      }
   }

   public Query newQuery(String query) {
      return this.newQuery((String)"javax.jdo.query.JDOQL", (Object)query);
   }

   public Query newQuery(String language, Object query) {
      this.assertIsOpen();
      String queryLanguage = language;
      if (language == null) {
         queryLanguage = "JDOQL";
      } else if (language.equals("javax.jdo.query.JDOQL")) {
         queryLanguage = "JDOQL";
      } else if (language.equals("javax.jdo.query.SQL")) {
         queryLanguage = "SQL";
      } else if (language.equals("javax.jdo.query.JPQL")) {
         queryLanguage = "JPQL";
      }

      if (!this.ec.getStoreManager().supportsQueryLanguage(queryLanguage)) {
         throw new JDOUserException(Localiser.msg("011006", new Object[]{queryLanguage}));
      } else {
         org.datanucleus.store.query.Query internalQuery = null;

         try {
            if (query != null && query instanceof JDOQuery) {
               internalQuery = this.ec.getStoreManager().getQueryManager().newQuery(queryLanguage, this.ec, ((JDOQuery)query).getInternalQuery());
            } else if (query instanceof String && StringUtils.isWhitespace((String)query)) {
               internalQuery = this.ec.getStoreManager().getQueryManager().newQuery(queryLanguage, this.ec, (Object)null);
            } else {
               internalQuery = this.ec.getStoreManager().getQueryManager().newQuery(queryLanguage, this.ec, query);
            }
         } catch (NucleusException ne) {
            throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
         }

         return new JDOQuery(this, internalQuery, queryLanguage);
      }
   }

   public Query newQuery(Class cls) {
      Query query = this.newQuery();
      query.setClass(cls);
      return query;
   }

   public Query newQuery(Extent cln) {
      Query query = this.newQuery();
      query.setClass(cln.getCandidateClass());
      query.setCandidates(cln);
      return query;
   }

   public Query newQuery(Class cls, Collection cln) {
      Query query = this.newQuery();
      query.setClass(cls);
      query.setCandidates(cln);
      return query;
   }

   public Query newQuery(Class cls, String filter) {
      Query query = this.newQuery();
      query.setClass(cls);
      query.setFilter(filter);
      return query;
   }

   public Query newQuery(Class cls, Collection cln, String filter) {
      Query query = this.newQuery();
      query.setClass(cls);
      query.setCandidates(cln);
      query.setFilter(filter);
      return query;
   }

   public Query newQuery(Extent cln, String filter) {
      Query query = this.newQuery();
      query.setClass(cln.getCandidateClass());
      query.setCandidates(cln);
      query.setFilter(filter);
      return query;
   }

   public JDOQLTypedQuery newJDOQLTypedQuery(Class cls) {
      return new JDOQLTypedQueryImpl(this, cls);
   }

   public Query newNamedQuery(Class cls, String queryName) {
      this.assertIsOpen();
      if (queryName == null) {
         throw new JDOUserException(Localiser.msg("011005", new Object[]{null, cls}));
      } else {
         ClassLoaderResolver clr = this.ec.getClassLoaderResolver();
         QueryMetaData qmd = this.ec.getMetaDataManager().getMetaDataForQuery(cls, clr, queryName);
         if (qmd == null) {
            throw new JDOUserException(Localiser.msg("011005", new Object[]{queryName, cls}));
         } else {
            Query query = this.newQuery((String)qmd.getLanguage(), (Object)qmd.getQuery());
            if (cls != null) {
               query.setClass(cls);
               if (!this.ec.getStoreManager().managesClass(cls.getName())) {
                  this.ec.getStoreManager().manageClasses(clr, new String[]{cls.getName()});
               }
            }

            if (!qmd.getLanguage().equals(QueryLanguage.JDOQL.toString()) || !qmd.isUnique() && qmd.getResultClass() == null) {
               if (qmd.isUnique()) {
                  query.setUnique(true);
               }

               if (qmd.getResultClass() != null) {
                  Class resultCls = null;

                  try {
                     resultCls = clr.classForName(qmd.getResultClass());
                  } catch (ClassNotResolvedException var12) {
                     if (cls != null) {
                        try {
                           String resultClassName = cls.getPackage().getName() + "." + qmd.getResultClass();
                           resultCls = clr.classForName(resultClassName);
                        } catch (ClassNotResolvedException var11) {
                           throw new JDOUserException(Localiser.msg("011008", new Object[]{queryName, qmd.getResultClass()}));
                        }
                     }
                  }

                  query.setResultClass(resultCls);
               }

               ExtensionMetaData[] extmds = qmd.getExtensions();
               if (extmds != null) {
                  for(int i = 0; i < extmds.length; ++i) {
                     if (extmds[i].getVendorName().equals("datanucleus")) {
                        query.addExtension(extmds[i].getKey(), extmds[i].getValue());
                     }
                  }
               }

               if (qmd.isUnmodifiable()) {
                  query.setUnmodifiable();
               }

               if (qmd.getFetchPlanName() != null) {
                  FetchPlanMetaData fpmd = this.ec.getMetaDataManager().getMetaDataForFetchPlan(qmd.getFetchPlanName());
                  if (fpmd != null) {
                     org.datanucleus.FetchPlan fp = new org.datanucleus.FetchPlan(this.ec, clr);
                     fp.removeGroup("default");
                     FetchGroupMetaData[] fgmds = fpmd.getFetchGroupMetaData();

                     for(int i = 0; i < fgmds.length; ++i) {
                        fp.addGroup(fgmds[i].getName());
                     }

                     fp.setMaxFetchDepth(fpmd.getMaxFetchDepth());
                     fp.setFetchSize(fpmd.getFetchSize());
                     ((JDOQuery)query).getInternalQuery().setFetchPlan(fp);
                  }
               }

               return query;
            } else {
               throw new JDOUserException(Localiser.msg("011007", new Object[]{queryName}));
            }
         }
      }
   }

   public Extent getExtent(Class pcClass, boolean subclasses) {
      this.assertIsOpen();

      try {
         return new JDOExtent(this, this.ec.getExtent(pcClass, subclasses));
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public Extent getExtent(Class pcClass) {
      return this.getExtent(pcClass, true);
   }

   public Object newInstance(Class pc) {
      this.assertIsOpen();

      try {
         return this.ec.newInstance(pc);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public Object newObjectIdInstance(Class pcClass, Object key) {
      this.assertIsOpen();

      try {
         return this.ec.newObjectId(pcClass, key);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public Set getManagedObjects() {
      return this.ec.getManagedObjects();
   }

   public Set getManagedObjects(Class... classes) {
      return this.ec.getManagedObjects(classes);
   }

   public Set getManagedObjects(EnumSet states) {
      if (states == null) {
         return null;
      } else {
         String[] stateNames = new String[states.size()];
         Iterator iter = states.iterator();

         ObjectState state;
         for(int i = 0; iter.hasNext(); stateNames[i++] = state.toString()) {
            state = (ObjectState)iter.next();
         }

         return this.ec.getManagedObjects(stateNames);
      }
   }

   public Set getManagedObjects(EnumSet states, Class... classes) {
      if (states == null) {
         return null;
      } else {
         String[] stateNames = new String[states.size()];
         Iterator iter = states.iterator();

         ObjectState state;
         for(int i = 0; iter.hasNext(); stateNames[i++] = state.toString()) {
            state = (ObjectState)iter.next();
         }

         return this.ec.getManagedObjects(stateNames, classes);
      }
   }

   public Object getObjectById(Object id) {
      return this.getObjectById(id, true);
   }

   public Object getObjectById(Object id, boolean validate) {
      this.assertIsOpen();
      if (id == null) {
         throw new JDONullIdentityException(Localiser.msg("010044"));
      } else {
         try {
            Object theId = id;
            if (id instanceof SingleFieldIdentity) {
               theId = NucleusJDOHelper.getDataNucleusIdentityForSingleFieldIdentity((SingleFieldIdentity)id);
            }

            return this.ec.findObject(theId, validate, validate, (String)null);
         } catch (NucleusException ne) {
            throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
         }
      }
   }

   public Object getObjectById(Class cls, Object key) {
      return this.getObjectById(this.newObjectIdInstance(cls, key), true);
   }

   public Object[] getObjectsById(boolean validate, Object... oids) {
      this.assertIsOpen();
      if (oids == null) {
         throw new JDOUserException(Localiser.msg("011002"));
      } else {
         Object[] theOids = new Object[oids.length];

         for(int i = 0; i < oids.length; ++i) {
            if (oids[i] != null) {
               if (oids[i] instanceof SingleFieldIdentity) {
                  theOids[i] = NucleusJDOHelper.getDataNucleusIdentityForSingleFieldIdentity((SingleFieldIdentity)oids[i]);
               } else {
                  theOids[i] = oids[i];
               }
            } else {
               theOids[i] = null;
            }
         }

         return this.ec.findObjects(theOids, validate);
      }
   }

   public Object[] getObjectsById(Object... oids) {
      return this.getObjectsById(true, oids);
   }

   public Collection getObjectsById(Collection oids) {
      return this.getObjectsById(oids, true);
   }

   public Collection getObjectsById(Collection oids, boolean validate) {
      this.assertIsOpen();
      if (oids == null) {
         throw new JDOUserException(Localiser.msg("011002"));
      } else if (oids.size() == 0) {
         return Collections.EMPTY_LIST;
      } else {
         Object[] oidArray = new Object[oids.size()];
         int j = 0;

         for(Object oid : oids) {
            Object id = oid;
            if (oid != null && oid instanceof SingleFieldIdentity) {
               id = NucleusJDOHelper.getDataNucleusIdentityForSingleFieldIdentity((SingleFieldIdentity)oid);
            }

            oidArray[j++] = id;
         }

         Object[] objs = this.ec.findObjects(oidArray, validate);
         Collection objects = new ArrayList(oids.size());

         for(int i = 0; i < objs.length; ++i) {
            objects.add(objs[i]);
         }

         return objects;
      }
   }

   public Object getObjectId(Object pc) {
      this.assertIsOpen();
      if (pc != null && pc instanceof Persistable) {
         Persistable p = (Persistable)pc;
         if (p.dnIsPersistent() || p.dnIsDetached()) {
            Object id = p.dnGetObjectId();
            if (id != null && id instanceof SingleFieldId) {
               id = NucleusJDOHelper.getSingleFieldIdentityForDataNucleusIdentity((SingleFieldId)id, pc.getClass());
            }

            return id;
         }
      }

      return null;
   }

   public Object getTransactionalObjectId(Object pc) {
      this.assertIsOpen();
      return ((Persistable)pc).dnGetTransactionalObjectId();
   }

   public Class getObjectIdClass(Class cls) {
      this.assertIsOpen();
      if (this.ec.getNucleusContext().getApiAdapter().isPersistable(cls) && this.hasPersistenceInformationForClass(cls)) {
         ClassLoaderResolver clr = this.ec.getClassLoaderResolver();
         AbstractClassMetaData cmd = this.ec.getMetaDataManager().getMetaDataForClass(cls, clr);
         if (cmd.getIdentityType() == IdentityType.DATASTORE) {
            return this.ec.getNucleusContext().getIdentityManager().getDatastoreIdClass();
         } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
            try {
               return this.ec.getClassLoaderResolver().classForName(this.ec.getMetaDataManager().getMetaDataForClass(cls, clr).getObjectidClass(), (ClassLoader)null);
            } catch (ClassNotResolvedException var6) {
               String msg = Localiser.msg("011009", new Object[]{cls.getName()});
               LOGGER.error(msg);
               throw new JDOException(msg);
            }
         } else {
            return cmd.isRequiresExtent() ? this.ec.getNucleusContext().getIdentityManager().getDatastoreIdClass() : SCOID.class;
         }
      } else {
         return null;
      }
   }

   public Object putUserObject(Object key, Object value) {
      this.assertIsOpen();
      if (key == null) {
         return null;
      } else {
         if (this.userObjectMap == null) {
            this.userObjectMap = new HashMap();
         }

         return value == null ? this.userObjectMap.remove(key) : this.userObjectMap.put(key, value);
      }
   }

   public Object getUserObject(Object key) {
      this.assertIsOpen();
      if (key == null) {
         return null;
      } else {
         return this.userObjectMap == null ? null : this.userObjectMap.get(key);
      }
   }

   public Object removeUserObject(Object key) {
      this.assertIsOpen();
      if (key == null) {
         return null;
      } else {
         return this.userObjectMap == null ? null : this.userObjectMap.remove(key);
      }
   }

   public void setUserObject(Object userObject) {
      this.assertIsOpen();
      this.userObject = userObject;
   }

   public Object getUserObject() {
      this.assertIsOpen();
      return this.userObject;
   }

   public void flush() {
      this.assertIsOpen();

      try {
         this.ec.flush();
      } catch (NucleusException ne) {
         if (ne instanceof NucleusOptimisticException) {
            Throwable[] nested = ne.getNestedExceptions();
            JDOOptimisticVerificationException[] jdoNested = new JDOOptimisticVerificationException[nested.length];

            for(int i = 0; i < nested.length; ++i) {
               jdoNested[i] = (JDOOptimisticVerificationException)NucleusJDOHelper.getJDOExceptionForNucleusException((NucleusException)nested[i]);
            }

            throw new JDOOptimisticVerificationException(ne.getMessage(), jdoNested);
         } else {
            throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
         }
      }
   }

   public void checkConsistency() {
      this.assertIsOpen();
      if (this.ec.getTransaction().isActive()) {
         if (this.ec.getTransaction().getOptimistic()) {
            throw new JDOUserException("checkConsistency() not yet implemented for optimistic transactions");
         } else {
            this.flush();
         }
      }
   }

   public Sequence getSequence(String sequenceName) {
      this.assertIsOpen();
      SequenceMetaData seqmd = this.ec.getMetaDataManager().getMetaDataForSequence(this.ec.getClassLoaderResolver(), sequenceName);
      if (seqmd == null) {
         throw new JDOUserException(Localiser.msg("017000", new Object[]{sequenceName}));
      } else {
         Sequence seq = null;
         if (seqmd.getFactoryClass() != null) {
            seq = this.pmf.getSequenceForFactoryClass(seqmd.getFactoryClass());
            if (seq == null) {
               Class factory = this.ec.getClassLoaderResolver().classForName(seqmd.getFactoryClass());
               if (factory == null) {
                  throw new JDOUserException(Localiser.msg("017001", new Object[]{sequenceName, seqmd.getFactoryClass()}));
               }

               Class[] argTypes = null;
               Object[] arguments = null;
               if (seqmd.getStrategy() != null) {
                  argTypes = new Class[]{String.class, String.class};
                  arguments = new Object[]{seqmd.getName(), seqmd.getStrategy().toString()};
               } else {
                  argTypes = new Class[]{String.class};
                  arguments = new Object[]{seqmd.getName()};
               }

               try {
                  Method newInstanceMethod = factory.getMethod("newInstance", argTypes);
                  seq = (Sequence)newInstanceMethod.invoke((Object)null, arguments);
               } catch (Exception e) {
                  throw new JDOUserException(Localiser.msg("017002", new Object[]{seqmd.getFactoryClass(), e.getMessage()}));
               }

               this.pmf.addSequenceForFactoryClass(seqmd.getFactoryClass(), seq);
            }
         } else {
            NucleusSequence nucSeq = this.ec.getStoreManager().getNucleusSequence(this.ec, seqmd);
            seq = new JDOSequence(nucSeq);
         }

         return seq;
      }
   }

   public void addInstanceLifecycleListener(InstanceLifecycleListener listener, Class... classes) {
      this.assertIsOpen();
      if (listener != null) {
         classes = LifecycleListenerForClass.canonicaliseClasses(classes);
         if (classes == null || classes.length != 0) {
            this.ec.getCallbackHandler().addListener(listener, classes);
         }
      }
   }

   public void removeInstanceLifecycleListener(InstanceLifecycleListener listener) {
      this.assertIsOpen();
      this.ec.getCallbackHandler().removeListener(listener);
   }

   protected void assertIsOpen() {
      if (this.isClosed()) {
         throw new JDOFatalUserException(Localiser.msg("011000"));
      }
   }

   protected void assertActiveTransaction() {
      if (!this.ec.getTransaction().isActive()) {
         throw new TransactionNotActiveException();
      }
   }

   protected void assertWritable() {
      if (!this.ec.getTransaction().isActive() && !this.ec.getTransaction().getNontransactionalWrite()) {
         throw new TransactionNotWritableException();
      }
   }

   protected void assertReadable(String operation) {
      if (!this.ec.getTransaction().isActive() && !this.ec.getTransaction().getNontransactionalRead()) {
         throw new JDOUserException(Localiser.msg("011001", new Object[]{operation}));
      }
   }

   protected boolean hasPersistenceInformationForClass(Class cls) {
      return this.ec.hasPersistenceInformationForClass(cls);
   }

   public JDOConnection getDataStoreConnection() {
      try {
         NucleusConnection nconn = this.ec.getStoreManager().getNucleusConnection(this.ec);
         return (JDOConnection)(this.ec.getStoreManager().isJdbcStore() ? new JDOConnectionJDBCImpl(nconn) : new JDOConnectionImpl(nconn));
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public FetchGroup getFetchGroup(Class cls, String name) {
      if (this.jdoFetchGroups == null) {
         this.jdoFetchGroups = new HashSet();
      }

      for(JDOFetchGroup jdoGrp : this.jdoFetchGroups) {
         if (jdoGrp.getName().equals(name) && jdoGrp.getType() == cls && !jdoGrp.isUnmodifiable()) {
            return jdoGrp;
         }
      }

      JDOFetchGroup jdoGrp = (JDOFetchGroup)this.getPersistenceManagerFactory().getFetchGroup(cls, name);
      if (jdoGrp != null) {
         org.datanucleus.FetchGroup internalGrp = jdoGrp.getInternalFetchGroup();
         org.datanucleus.FetchGroup internalCopy = new org.datanucleus.FetchGroup(internalGrp);
         jdoGrp = new JDOFetchGroup(internalCopy);
         this.ec.addInternalFetchGroup(internalCopy);
         this.jdoFetchGroups.add(jdoGrp);
         return jdoGrp;
      } else {
         return null;
      }
   }

   public void setProperty(String propertyName, Object value) {
      this.assertIsOpen();

      try {
         this.ec.setProperty(propertyName, value);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public Map getProperties() {
      this.assertIsOpen();
      Map<String, Object> pmProps = new HashMap();
      Map<String, Object> ecProps = this.ec.getProperties();
      Iterator<Map.Entry<String, Object>> propertiesIter = ecProps.entrySet().iterator();
      Configuration conf = this.ec.getNucleusContext().getConfiguration();

      while(propertiesIter.hasNext()) {
         Map.Entry<String, Object> entry = (Map.Entry)propertiesIter.next();
         String ecPropName = (String)entry.getKey();
         String pmPropName = conf.getPropertyNameWithInternalPropertyName(ecPropName, "javax.jdo");
         pmProps.put(pmPropName != null ? pmPropName : ecPropName, entry.getValue());
      }

      return pmProps;
   }

   public Set getSupportedProperties() {
      this.assertIsOpen();
      return this.ec.getSupportedProperties();
   }

   public void addTransactionEventListener(TransactionEventListener listener) {
      this.assertIsOpen();
      this.ec.getTransaction().bindTransactionEventListener(listener);
   }

   public void removeTransactionEventListener(TransactionEventListener listener) {
      this.assertIsOpen();
      this.ec.getTransaction().removeTransactionEventListener(listener);
   }
}
