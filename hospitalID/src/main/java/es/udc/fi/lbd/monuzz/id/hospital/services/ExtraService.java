package es.udc.fi.lbd.monuzz.id.hospital.services;

import java.util.List;

import es.udc.fi.lbd.monuzz.id.hospital.model.Cita;
import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

public interface ExtraService  {
	/* NOTA: Todos os métodos DEBEN: 
	 * 		Se exito: 
	 * 			-Rexistrar mensaxe informativa no log
	 * 		Se error ou excepción:
	 * 			-Capturar excepción
	 * 			-Rexistrar mensaxe de erro no log
	 * 			-Propagar excepción
	 * 
	 * Todos os servizos deben utilizar exclusivamente métodos dos DAOS para acceder á BD
	 * 
	 * Cada método se corresponde con UNHA TRANSACCIÓN
	 * 
	 * Para cada método abriremos e pecharemos UNHA SESION
	 * 
	 */
	
	//Devuelve las citas (consultas) de un paciente con el numPaciente indicado y 
	//con el medico con el numColexiado indicado
	public List<Cita> CitasPacienteMedico(Long numPaciente,Long numColexiado);
	//Devuelve el numero de citas que ha tenido un paciente en concreto
	public Long countAllCitasPaciente(Paciente paciente);
	//Devuelve los pacientes que no tienen ninguna cita con ese medico
	public List<Paciente> findPacientesNoMedico(Medico medico);
	
}
