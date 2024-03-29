package dies.controllers;

import dies.auth.LoginSession;
import dies.models.*;
import dies.models.Appointment.State;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServletParam {
    public int getAppointmentId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("appointmentid"));
    }

    public Appointment getAppointmentDetails(HttpServletRequest request, Patient patient, Technician technician,
                                             List<Machine> machines, List<Image> images) {
        int appointment_id = 0;

        if (request.getParameterMap().containsKey("appointmentid")) {
            appointment_id = Integer.parseInt(request.getParameter("appointmentid"));
        }

        LocalDateTime appointmentDate = getAppointmentDateTime(request);

        return new Appointment(appointment_id, appointmentDate, patient, technician, machines,
                State.valueOf(request.getParameter("appointmentStatus")), images);
    }

    public LocalDateTime getAppointmentDateTime(HttpServletRequest request) {
        String appointmentDateTime = request.getParameter("appointmentDateTime").replace("T", " ");
        LocalDateTime appointmentDate = LocalDateTime.parse(appointmentDateTime,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return appointmentDate;
    }

    public List<Machine> getMachineDetails(HttpServletRequest request) {
        String machineType = request.getParameter("machineType");
        Machine.Type machineCastType = Machine.Type.valueOf(machineType);
        Machine machine = new Machine(0, "", machineCastType);
        List<Machine> machines = new ArrayList<Machine>();
        machines.add(machine);
        return machines;
    }

    public Patient getPatientDetails(HttpServletRequest request, Address patientAddress) {
        String patientFirstName = request.getParameter("patientFirstName");
        String patientLastName = request.getParameter("patientLastName");
        String patientMobile = request.getParameter("patientMobile");
        String patientMedicareNo = request.getParameter("patientMedicareNo");
        String patientEmail = request.getParameter("patientEmail");
        Integer patientId = Integer.valueOf(request.getParameter("patientid"));
        return new Patient(patientId, patientFirstName, patientLastName, patientAddress, patientMobile,
                patientMedicareNo, patientEmail);
    }

    public Address getAddressDetails(HttpServletRequest request) {
        Integer patientUnitNo = Integer.valueOf(Integer.parseInt(request.getParameter("patientUnitNo")));
        Integer patientStreetNo = 0;
        String patientStreetName = request.getParameter("patientStreet");
        String patientCity = request.getParameter("patientCity");
        String patientState = request.getParameter("patientState");
        Integer patientPostCode = Integer.valueOf(Integer.parseInt(request.getParameter("patientPostalCode")));
        Integer patientAddressidId = Integer.valueOf(Integer.parseInt(request.getParameter("patientAddressid")));

        return new Address(patientAddressidId, patientUnitNo, patientStreetNo, patientStreetName, patientCity,
                patientState, patientPostCode);
    }

    public Technician getTechnicianDetails(HttpServletRequest request, String mode) {
        int technicianId = LoginSession.getUser().getId();
        String technicianUsername = LoginSession.getUser().getUsername();
        String technicianFirstname = LoginSession.getUser().getFirstName();
        String technicianLastName = LoginSession.getUser().getLastName();
        String technicianGroup = LoginSession.getUser().getGroup();

        return new Technician(technicianId, technicianUsername, "", technicianFirstname, technicianLastName, technicianGroup, "");
    }

    public List<Machine> getMachineDetails(HttpServletRequest request, String mode) {
        List<Machine> appointment_machines = new ArrayList<Machine>();
        String[] machines = request.getParameterValues("machineType");
        for (String machine : machines) {
            Machine m = null;
            if (Machine.Type.valueOf(machine) == Machine.Type.XRAY) {
                m = new Machine(1, null, Machine.Type.valueOf(machine));
            } else if (Machine.Type.valueOf(machine) == Machine.Type.CAT) {
                m = new Machine(2, null, Machine.Type.valueOf(machine));
            } else if (Machine.Type.valueOf(machine) == Machine.Type.MRI) {
                m = new Machine(3, null, Machine.Type.valueOf(machine));
            }
            appointment_machines.add(m);
        }
        return appointment_machines;
    }

    public List<Machine> getAvailableMachines(List<Machine> appointmentMachines) {
        List<Machine> available_machines = new ArrayList<Machine>();

        for (Machine.Type machine_type : Machine.Type.values()) {
            available_machines.add(new Machine(0, null, machine_type));
        }

        if (appointmentMachines != null) {
            for (Machine machine : appointmentMachines) {
                available_machines.removeIf(obj -> obj.getType() == machine.getType());
            }
        }
        return available_machines;
    }
    
    public String getPatientReport(HttpServletRequest request) {
		String patientReport = request.getParameter("patientreport").replaceAll(" ", "_");
		String trimmedPatientReport = patientReport.replaceAll("\\s+","");
		return trimmedPatientReport;
	}
}

