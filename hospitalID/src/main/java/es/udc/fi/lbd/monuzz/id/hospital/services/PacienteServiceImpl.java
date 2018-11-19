package es.udc.fi.lbd.monuzz.id.hospital.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.lbd.monuzz.id.hospital.daos.PacienteDAO;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

@Service
public class PacienteServiceImpl implements PacienteService {

static Logger log= Logger.getLogger("hospital");
@Autowired
private PacienteDAO pacienteDAO;


	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void altaNovoPacienteBD(Paciente meuPaciente) {
		try{
			pacienteDAO.create(meuPaciente);
			log.info("Gravados os datos de " + meuPaciente.toString());
			}catch(DataAccessException e){
			log.error("Erro gravando os datos de "+ meuPaciente.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void borradoPacienteBD(Paciente meuPaciente) {
		try{
			pacienteDAO.remove(meuPaciente);
			log.info("Borrados os datos de " + meuPaciente.toString());
			}catch(DataAccessException e){
			log.error("Erro borrando os datos de "+ meuPaciente.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void modificacionPacienteBD(Paciente meuPaciente) {
		try{
			pacienteDAO.update(meuPaciente);
			log.info("Actualizados os datos de " + meuPaciente.toString());
			}catch(DataAccessException e){
			log.error("Erro actualizando os datos de "+ meuPaciente.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager")
	public Paciente recuperarPacienteBDPorId(Long id) {
		try{
			Paciente paciente =pacienteDAO.findPacienteById(id);
			log.info("Encontrado os datos do paciente " +paciente.toString()+" con id: " + id);
			return paciente;
			}catch(DataAccessException e){
			log.error("Erro encontrando o paciente de id: "+ id);
			throw e;
			}	
	}

	@Transactional(value="myTransactionManager")
	@Override
	public Paciente recuperarPacienteBDPorNumPaciente(String num) {
		try{
			Paciente paciente =pacienteDAO.findPacienteByNum(num);
			log.info("Encontrado os datos do paciente " +paciente.toString()+" con numPaciente: " + num);
			return paciente;
			}catch(DataAccessException e){
			log.error("Erro encontrando o paciente de numPaciente: "+ num);
			throw e;
			}	
	}

	@Transactional(value="myTransactionManager")
	@Override
	public List<Paciente> recuperarTodosPacientesBD() {
		try{
			List<Paciente> pacientes =pacienteDAO.findAllPacientes();
			log.info("Encontrados datos de todos os pacientes: " +pacientes.toString());
			return pacientes;
			}catch(DataAccessException e){
			log.error("Erro encontrando os datos de todos os pacientes");
			throw e;
			}	
	}



}
