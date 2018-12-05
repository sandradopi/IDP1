package es.udc.fi.lbd.monuzz.id.hospital.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.lbd.monuzz.id.hospital.daos.ExtraDAO;
import es.udc.fi.lbd.monuzz.id.hospital.daos.ExtraDAOHibImpl;
import es.udc.fi.lbd.monuzz.id.hospital.model.Cita;
import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

@Service
public class ExtraServiceImpl implements ExtraService{

	@Autowired
	private ExtraDAO extraDAO;
	static Logger log= Logger.getLogger("hospital");
	
	
	@Override
	@Transactional(value="myTransactionManager")
	public List<Cita> CitasPacienteMedico(String numPaciente,String numColexiado){
		try{
			List<Cita> citas= extraDAO.CitasPacienteMedico(numPaciente, numColexiado);
			log.info("Encontrados os datos da citas: "+ citas +" del paciente con el numero: " + numPaciente+ "con el medico con el numero: "+ numColexiado);
			return citas;
			}catch(DataAccessException e){
			log.error("Erro encontrando as citas do medico co numero: "+ numColexiado + "e o paciente co numero: "+numPaciente);
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager")
	public Long countAllCitasPaciente(Paciente paciente){
		try {
			Long numeroCitas = extraDAO.countAllCitasPaciente(paciente);
			log.info("Encontradas "+ numeroCitas + " citas do paciente: " + paciente);
			return numeroCitas;
		}catch (DataAccessException e) {
			log.error("Erro encontrando o número de citas que tuvo o paciente: " + paciente);
			throw e;
		}
		
	}
	
	@Override
	@Transactional(value="myTransactionManager")
	public List<Paciente> findPacientesNoCita(Medico medico){
		try {
			List<Paciente> pacientes = extraDAO.findPacientesNoCita(medico);
			log.info("Encontrados os pacientes: "+ pacientes +" que non teñen ningunha cita con el medico:" + medico);
			return pacientes;
		}catch (DataAccessException e) {
			log.error("Erro encontrando os pacientes que non teñen ningunha cita con el medico:" + medico);
			throw e;
		}
		
	}
}
