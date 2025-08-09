package org.datanucleus.api.jdo;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import javax.jdo.JDOUserCallbackException;
import javax.jdo.listener.AttachCallback;
import javax.jdo.listener.AttachLifecycleListener;
import javax.jdo.listener.ClearCallback;
import javax.jdo.listener.ClearLifecycleListener;
import javax.jdo.listener.CreateLifecycleListener;
import javax.jdo.listener.DeleteCallback;
import javax.jdo.listener.DeleteLifecycleListener;
import javax.jdo.listener.DetachCallback;
import javax.jdo.listener.DetachLifecycleListener;
import javax.jdo.listener.DirtyLifecycleListener;
import javax.jdo.listener.InstanceLifecycleEvent;
import javax.jdo.listener.InstanceLifecycleListener;
import javax.jdo.listener.LoadCallback;
import javax.jdo.listener.LoadLifecycleListener;
import javax.jdo.listener.StoreCallback;
import javax.jdo.listener.StoreLifecycleListener;
import org.datanucleus.ExecutionContext;
import org.datanucleus.NucleusContext;
import org.datanucleus.state.CallbackHandler;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.util.Localiser;

public class JDOCallbackHandler implements CallbackHandler {
   NucleusContext nucleusCtx;
   private final Map listeners = new IdentityHashMap(1);
   private List listenersWorkingCopy = null;
   CallbackHandler beanValidationHandler;

   public JDOCallbackHandler(NucleusContext nucleusCtx) {
      this.nucleusCtx = nucleusCtx;
   }

   public void setValidationListener(CallbackHandler handler) {
      this.beanValidationHandler = handler;
   }

   public void postCreate(Object pc) {
      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof CreateLifecycleListener) {
            ((CreateLifecycleListener)listener.getListener()).postCreate(new InstanceLifecycleEvent(pc, 0, (Object)null));
         }
      }

   }

   public void prePersist(Object pc) {
      if (this.beanValidationHandler != null) {
         this.beanValidationHandler.prePersist(pc);
      }

   }

   public void preStore(Object pc) {
      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof StoreLifecycleListener) {
            ExecutionContext ec = this.nucleusCtx.getApiAdapter().getExecutionContext(pc);
            String[] fieldNames = null;
            ObjectProvider op = ec.findObjectProvider(pc);
            fieldNames = op.getDirtyFieldNames();
            if (fieldNames == null) {
               fieldNames = op.getLoadedFieldNames();
            }

            ((StoreLifecycleListener)listener.getListener()).preStore(new FieldInstanceLifecycleEvent(pc, 2, (Object)null, fieldNames));
         }
      }

      if (pc instanceof StoreCallback) {
         try {
            ((StoreCallback)pc).jdoPreStore();
         } catch (Exception e) {
            throw new JDOUserCallbackException(Localiser.msg("025001", new Object[]{"jdoPreStore"}), e);
         }
      }

      if (this.beanValidationHandler != null) {
         ObjectProvider op = this.nucleusCtx.getApiAdapter().getExecutionContext(pc).findObjectProvider(pc);
         if (!op.getLifecycleState().isNew()) {
            this.beanValidationHandler.preStore(pc);
         }
      }

   }

   public void postStore(Object pc) {
      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof StoreLifecycleListener) {
            ((StoreLifecycleListener)listener.getListener()).postStore(new InstanceLifecycleEvent(pc, 2, (Object)null));
         }
      }

   }

   public void preClear(Object pc) {
      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof ClearLifecycleListener) {
            ((ClearLifecycleListener)listener.getListener()).preClear(new InstanceLifecycleEvent(pc, 3, (Object)null));
         }
      }

      if (pc instanceof ClearCallback) {
         try {
            ((ClearCallback)pc).jdoPreClear();
         } catch (Exception e) {
            throw new JDOUserCallbackException(Localiser.msg("025001", new Object[]{"jdoPreClear"}), e);
         }
      }

   }

   public void postClear(Object pc) {
      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof ClearLifecycleListener) {
            ((ClearLifecycleListener)listener.getListener()).postClear(new InstanceLifecycleEvent(pc, 3, (Object)null));
         }
      }

   }

   public void preDelete(Object pc) {
      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof DeleteLifecycleListener) {
            ((DeleteLifecycleListener)listener.getListener()).preDelete(new InstanceLifecycleEvent(pc, 4, (Object)null));
         }
      }

      if (pc instanceof DeleteCallback) {
         try {
            ((DeleteCallback)pc).jdoPreDelete();
         } catch (Exception e) {
            throw new JDOUserCallbackException(Localiser.msg("025001", new Object[]{"jdoPreDelete"}), e);
         }
      }

      if (this.beanValidationHandler != null) {
         this.beanValidationHandler.preDelete(pc);
      }

   }

   public void postDelete(Object pc) {
      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof DeleteLifecycleListener) {
            ((DeleteLifecycleListener)listener.getListener()).postDelete(new InstanceLifecycleEvent(pc, 4, (Object)null));
         }
      }

   }

   public void preDirty(Object pc) {
      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof DirtyLifecycleListener) {
            ((DirtyLifecycleListener)listener.getListener()).preDirty(new InstanceLifecycleEvent(pc, 5, (Object)null));
         }
      }

   }

   public void postDirty(Object pc) {
      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof DirtyLifecycleListener) {
            ((DirtyLifecycleListener)listener.getListener()).postDirty(new InstanceLifecycleEvent(pc, 5, (Object)null));
         }
      }

   }

   public void postLoad(Object pc) {
      if (pc instanceof LoadCallback) {
         try {
            ((LoadCallback)pc).jdoPostLoad();
         } catch (Exception e) {
            throw new JDOUserCallbackException(Localiser.msg("025001", new Object[]{"jdoPostLoad"}), e);
         }
      }

      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof LoadLifecycleListener) {
            ((LoadLifecycleListener)listener.getListener()).postLoad(new InstanceLifecycleEvent(pc, 1, (Object)null));
         }
      }

   }

   public void postRefresh(Object pc) {
   }

   public void preDetach(Object pc) {
      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof DetachLifecycleListener) {
            ((DetachLifecycleListener)listener.getListener()).preDetach(new InstanceLifecycleEvent(pc, 6, (Object)null));
         }
      }

      if (pc instanceof DetachCallback) {
         try {
            ((DetachCallback)pc).jdoPreDetach();
         } catch (Exception e) {
            throw new JDOUserCallbackException(Localiser.msg("025001", new Object[]{"jdoPreDetach"}), e);
         }
      }

   }

   public void postDetach(Object pc, Object detachedPC) {
      if (pc instanceof DetachCallback) {
         try {
            ((DetachCallback)detachedPC).jdoPostDetach(pc);
         } catch (Exception e) {
            throw new JDOUserCallbackException(Localiser.msg("025001", new Object[]{"jdoPostDetach"}), e);
         }
      }

      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof DetachLifecycleListener) {
            ((DetachLifecycleListener)listener.getListener()).postDetach(new InstanceLifecycleEvent(detachedPC, 6, pc));
         }
      }

   }

   public void preAttach(Object pc) {
      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof AttachLifecycleListener) {
            ((AttachLifecycleListener)listener.getListener()).preAttach(new InstanceLifecycleEvent(pc, 7, (Object)null));
         }
      }

      if (pc instanceof AttachCallback) {
         try {
            ((AttachCallback)pc).jdoPreAttach();
         } catch (Exception e) {
            throw new JDOUserCallbackException(Localiser.msg("025001", new Object[]{"jdoPreAttach"}), e);
         }
      }

   }

   public void postAttach(Object pc, Object detachedPC) {
      if (pc instanceof AttachCallback) {
         try {
            ((AttachCallback)pc).jdoPostAttach(detachedPC);
         } catch (Exception e) {
            throw new JDOUserCallbackException(Localiser.msg("025001", new Object[]{"jdoPostAttach"}), e);
         }
      }

      for(LifecycleListenerForClass listener : this.getListenersWorkingCopy()) {
         if (listener.forClass(pc.getClass()) && listener.getListener() instanceof AttachLifecycleListener) {
            ((AttachLifecycleListener)listener.getListener()).postAttach(new InstanceLifecycleEvent(pc, 7, detachedPC));
         }
      }

   }

   public void addListener(Object listener, Class[] classes) {
      if (listener != null) {
         InstanceLifecycleListener jdoListener = (InstanceLifecycleListener)listener;
         LifecycleListenerForClass entry;
         if (this.listeners.containsKey(jdoListener)) {
            entry = ((LifecycleListenerForClass)this.listeners.get(jdoListener)).mergeClasses(classes);
         } else {
            entry = new LifecycleListenerForClass(jdoListener, classes);
         }

         this.listeners.put(jdoListener, entry);
         this.listenersWorkingCopy = null;
      }
   }

   public void removeListener(Object listener) {
      if (this.listeners.remove(listener) != null) {
         this.listenersWorkingCopy = null;
      }

   }

   public void close() {
      this.listeners.clear();
      this.listenersWorkingCopy = null;
   }

   protected List getListenersWorkingCopy() {
      if (this.listenersWorkingCopy == null) {
         this.listenersWorkingCopy = new ArrayList(this.listeners.values());
      }

      return this.listenersWorkingCopy;
   }
}
