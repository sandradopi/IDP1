package es.udc.fi.lbd.monuzz.id.hospital.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.lbd.monuzz.id.hospital.daos.ExtraDAOHibImpl;
import es.udc.fi.lbd.monuzz.id.hospital.model.Cita;
import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

public class ExtraServiceImpl implements ExtraService{

	@Autowired
	private ExtraDAOHibImpl extraDAO;
	static Logger log= Logger.getLogger("hospital");
	
	
	@Override
	@Transactional(value="myTransactionManager")
	public List<Cita> CitasPacienteMedico(Long numPaciente,Long numColexiado){
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
		return null;
		
	}

	@Override
	@Transactional(value="myTransactionManager")
	public List<Paciente> findPacientesNoMedico(Medico medico){
		return null;
		
	}
}
