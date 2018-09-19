package servlet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dies.models.Address;
import dies.models.Appointment;
import dies.models.Patient;
import dies.models.Technician;
import dies.models.Appointment.State;
import dies.models.Machine;

public class ServletDetails {
	public int getAppointmentId(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("appointmentid"));
	}

	public Appointment getAppointmentDetails(HttpServletRequest request, Patient patient, Technician technician,
			List<Machine> machines) {
		int appointment_id = 0;
		
		if (request.getParameterMap().containsKey("appointmentid")) {
			appointment_id = Integer.parseInt(request.getParameter("appointmentid"));
		}
		
		String appointmentDateTime = request.getParameter("appointmentDateTime").replace("T", " ");
		LocalDateTime appointmentDate = LocalDateTime.parse(appointmentDateTime,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

		return new Appointment(appointment_id, appointmentDate, patient, technician, machines,
				State.valueOf(request.getParameter("appointmentStatus")));
	}

	public List<Machine> getMachineDetails(HttpServletRequest request) {
		// TODO This method need refactoring after implementing it
		String machineType = request.getParameter("machineType");
		Machine.Type machineCastType = Machine.Type.valueOf(machineType);
		Machine machine = new Machine(0, 0, machineCastType);
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
		int technicianId = (int) request.getSession().getAttribute("userid");
		String technicianUsername = (String) request.getSession().getAttribute("username");
		String technicianFirstname = (String) request.getSession().getAttribute("firstname");
		String technicianLastName = (String) request.getSession().getAttribute("lastname");

		return new Technician(technicianId, technicianUsername, "", technicianFirstname, technicianLastName);
	}
}
