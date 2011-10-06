package com.trsvax.tapestry.misc.services;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.services.ValueEncoderFactory;

import com.trsvax.tapestry.misc.implementations.PersonImpl;
import com.trsvax.tapestry.misc.interfaces.Person;


@SubModule(MiscModule.class)
public class AppModule {
	
    public static void bind(ServiceBinder binder) {
    	binder.bind(Defaults.class,DefaultsImpl.class);
    	binder.bind(PeopleDAO.class);
    }
    
    public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
      configuration.add(MiscDefaults.defaultKey, "WackyCollaborator");
    }
    
    @Contribute(NVLService.class)
    public static void contributeFactories(MappedConfiguration<Class, NVLFactory> configuration) {
    	configuration.add(Person.class,new ConstructorFactory(PersonImpl.class));
    }
    
   
    public static void contributeValueEncoderSource(MappedConfiguration<Class, ValueEncoderFactory> configuration,
    		
    		final PeopleDAO peopleDAO)
    {
    	configuration.add(Person.class, new ValueEncoderFactory<Person>() {

			public ValueEncoder<Person> create(Class<Person> type) {
				return new ValueEncoder<Person>() {

					public String toClient(Person entity) {
						return entity.getName();
					}

					public Person toValue(String key) {
						return peopleDAO.get( key );
					}
				};
			}
		});
    }

}
