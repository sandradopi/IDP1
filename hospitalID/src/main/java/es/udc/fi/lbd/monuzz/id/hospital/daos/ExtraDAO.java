package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.util.List;

import es.udc.fi.lbd.monuzz.id.hospital.model.Cita;
import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

public interface ExtraDAO {
	
	//Devuelve las citas (consultas) de un paciente con el numPaciente indicado y 
	//con el medico con el numColexiado indicado
	public List<Cita> CitasPacienteMedico(Long numPaciente,Long numColexiado);
	//Devuelve el numero de citas que ha tenido un paciente en concreto
	public Long countAllCitasPaciente(Paciente paciente);
	//Devuelve los pacientes que no tienen ninguna cita con ese medico
	public List<Paciente> findPacientesNoMedico(Medico medico);
}
