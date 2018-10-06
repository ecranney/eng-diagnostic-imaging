package dies.mappers;

import dies.models.Appointment;
import dies.models.IDomainObject;
import dies.models.Patient;
import dies.models.User;

import java.util.HashMap;
import java.util.Map;

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

    public abstract IDomainObject find(int id);
    
    public abstract void update(IDomainObject obj);

    public abstract void insert(IDomainObject obj);

    public abstract void delete(IDomainObject obj);
}
