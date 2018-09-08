package dies.mappers;

import dies.models.*;
import java.util.Map;
import java.util.HashMap;

public abstract class DataMapper {
	
	private static Map<Class<?>, DataMapper> map = map();
	private static Map<Class<?>, DataMapper> map() {
		Map<Class<?>, DataMapper> map = new HashMap<Class<?>, DataMapper>();
		map.put(Appointment.class, new AppointmentMapper());
		map.put(Patient.class, new PatientMapper());
		map.put(User.class, new UserMapper());
		return map;
	} 
	
	public static DataMapper getMapper(Class<?> c) {
		return map.get(c);
	}
	
	public abstract void update(DomainObject obj);
	public abstract void insert(DomainObject obj);
	public abstract void delete(DomainObject obj);
}
