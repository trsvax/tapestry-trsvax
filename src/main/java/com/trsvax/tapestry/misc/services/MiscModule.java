package com.trsvax.tapestry.misc.services;

import java.util.Map;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.services.Coercion;
import org.apache.tapestry5.ioc.services.CoercionTuple;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.BindingFactory;
import org.apache.tapestry5.services.ComponentClassTransformWorker;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
import org.apache.tapestry5.services.PartialMarkupRenderer;
import org.apache.tapestry5.services.PartialMarkupRendererFilter;

import com.trsvax.tapestry.StyleSupport;
import com.trsvax.tapestry.StyleSupportImpl;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class MiscModule
{
	public final static String id = "MiscModule";
	
    public static void bind(ServiceBinder binder)
    {
        binder.bind(BindingFactory.class,DefaultBindingFactory.class).withId("DefaultBindingFactory");
    }
    
    @Contribute(ComponentClassTransformWorker.class)   
    public static void  provideWorkers(OrderedConfiguration<ComponentClassTransformWorker> workers) {    
        workers.addInstance("LinkWithContextWorker", LinkWithContextWorker.class);
    } 
    
    
    public static void contributeTypeCoercer(Configuration<CoercionTuple<Component,String>> configuration)
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
     
    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration)
    {
        configuration.add(new LibraryMapping("tm", "com.trsvax.tapestry.misc"));
    }
    
    public void contributeMarkupRenderer(OrderedConfiguration<MarkupRendererFilter> configuration, final Environment environment) {
		MarkupRendererFilter documentLinker = new MarkupRendererFilter() {
			
 	            public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer) {
 	                environment.push(StyleSupport.class, new StyleSupportImpl());   	 	
 	                renderer.renderMarkup(writer);   	 	
 	                environment.pop(StyleSupport.class);
    	 	    }
 	        };
 	       configuration.add("StyleSupport", documentLinker);
    }
    
    public void contributePartialMarkupRenderer(OrderedConfiguration<PartialMarkupRendererFilter> configuration, final Environment environment) {
    	PartialMarkupRendererFilter documentLinker = new PartialMarkupRendererFilter() {

			public void renderMarkup(MarkupWriter writer, JSONObject arg1, PartialMarkupRenderer renderer) {				    	 	
                environment.push(StyleSupport.class, new StyleSupportImpl());   	 	
                renderer.renderMarkup(writer, arg1);   	 	
                environment.pop(StyleSupport.class);
			}
        };
       configuration.add("StyleSupport", documentLinker);
    }
    
    public static void contributeBindingSource(MappedConfiguration<String, BindingFactory> configuration,

    		@InjectService("DefaultBindingFactory")
    		BindingFactory defaultBindingFactory
    		) {
        configuration.add("default", defaultBindingFactory);       
    }
    
    @Contribute(Defaults.class)
    public static void contributeDefautls(MappedConfiguration<String, StaticDefaults> configuration) {
    	configuration.add(id,new MyDefaults());
    }
    
  
}
