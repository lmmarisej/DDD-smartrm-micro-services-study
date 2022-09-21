package com.smartrm.infracore.aggregate;

import com.smartrm.infracore.api.CommonError;
import com.smartrm.infracore.event.DomainEvent;
import com.smartrm.infracore.event.DomainEventBus;
import com.smartrm.infracore.exception.DomainException;

/**
 * @author: yoda
 * @description:
 */
public abstract class AggregateBase {
    
    protected long version;
    protected DomainEventBus eventBus;
    private boolean versionInc = false;
    
    protected void incVersion() {
        version++;
        versionInc = true;
    }
    
    public long getVersion() {
        return version;
    }
    
    public boolean isVersionInc() {
        return versionInc;
    }
    
    protected void setEventBus(DomainEventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    protected void emitEvent(DomainEvent event) {
        if (eventBus != null) {
            eventBus.post(event);
        } else {
            throw new DomainException(CommonError.NoEventBus);
        }
    }
    
    
}
