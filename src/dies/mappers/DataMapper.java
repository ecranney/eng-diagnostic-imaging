package dies.mappers;

import dies.models.*;
import java.util.*;

public abstract class DataMapper {
	
	private static Map<Class<?>, DataMapper> maps = buildMaps();
	
	private static Map<Class<?>, DataMapper> buildMaps() {
		// create the maps object
		Map<Class<?>, DataMapper> maps = new HashMap<Class<?>, DataMapper>();
		
		// add each of the data mappers to the maps object
		maps.put(Appointment.class, new AppointmentMapper());
		maps.put(Patient.class, new PatientMapper());
		maps.put(User.class, new UserMapper());
		
		// return
		return maps;
	} 
	
	public static DataMapper getMapper(Class<?> c) {
		return maps.get(c);
	}
	
	// abstract methods, must be implemented by each DataMaper
	public abstract IDomainObject find(int id);
	public abstract List<IDomainObject> findAll();
	public abstract void update(IDomainObject obj);
	public abstract void insert(IDomainObject obj);
	public abstract void delete(IDomainObject obj);
}
