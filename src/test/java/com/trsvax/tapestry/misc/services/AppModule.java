package com.trsvax.tapestry.misc.services;

import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.SubModule;


@SubModule(MiscModule.class)
public class AppModule {
	
    public static void bind(ServiceBinder binder) {
    	binder.bind(Defaults.class,DefaultsImpl.class);
    }
    
    public void contributeApplicationDefaults(MappedConfiguration<String, String> configuration)
    {
      configuration.add("MiscModule:defaultKey:abc", "WackyCollaborator");
    }
    
   

}
