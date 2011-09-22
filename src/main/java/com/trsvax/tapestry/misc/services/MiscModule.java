package com.trsvax.tapestry.misc.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.services.Coercion;
import org.apache.tapestry5.ioc.services.CoercionTuple;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ComponentClassTransformWorker;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class MiscModule
{
    public static void bind(ServiceBinder binder)
    {
        // binder.bind(MyServiceInterface.class, MyServiceImpl.class);
        
        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
    }
    
    @Contribute(ComponentClassTransformWorker.class)   
    public static void  provideWorkers(OrderedConfiguration<ComponentClassTransformWorker> workers) {    
        workers.addInstance("LinkWithContextWorker", LinkWithContextWorker.class);
    } 
    
    
    public static void contributeTypeCoercer(Configuration<CoercionTuple> configuration)
    {
        Coercion<Component, String> coercion = new Coercion<Component, String>()
        {
            public String coerce(Component input)
            {
               return input.getComponentResources().getPageName();
            }
        };
     
        configuration.add(new CoercionTuple<Component, String>(Component.class, String.class, coercion));     
    }
    
    
    
  
}
