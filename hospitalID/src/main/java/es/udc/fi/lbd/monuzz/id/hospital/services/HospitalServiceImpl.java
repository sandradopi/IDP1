package es.udc.fi.lbd.monuzz.id.hospital.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fi.lbd.monuzz.id.hospital.daos.CitaDAO;
import es.udc.fi.lbd.monuzz.id.hospital.daos.PacienteDAO;
import es.udc.fi.lbd.monuzz.id.hospital.model.*;
@Service
public class HospitalServiceImpl implements HospitalService {


static Logger log= Logger.getLogger("hospital");
@Autowired
private CitaDAO citaDAO;

	@Override
	public void altaNovoMedicoBD(Medico meuMedico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borradoMedicoBD(Medico meuMedico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificacionMedicoBD(Medico meuMedico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Medico recuperarMedicoBDPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medico recuperarMedicoBDPorNumColexiado(String numColexiado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medico> recuperarTodosMedicosBD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void altaNovoTipoDoenzaBD(TipoDoenza minhaDoenza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borradoTipoDoenzaBD(TipoDoenza minhaDoenza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificacionTipoDoenzaBD(TipoDoenza minhaDoenza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TipoDoenza recuperarTipoDoenzaBDPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoDoenza recuperarTipoDoenzaBDPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoDoenza> recuperarTodosTiposDoenzasBD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void altaNovoTipoProbaBD(TipoProba minhaProba) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borradoTipoProbaBD(TipoProba minhaProba) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificacionTipoProbaBD(TipoProba minhaProba) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TipoProba recuperarTipoProbaBDPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoProba recuperarTipoProbaBDPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoProba> recuperarTodosTiposProbasBD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void altaNovaCitaBD(Cita minhaCita) {
		try{
			citaDAO.create(minhaCita);
			log.info("Gravados os datos de " + minhaCita.toString());
			}catch(DataAccessException e){
			log.error("Erro gravando os datos de "+ minhaCita.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void borradoCitaBD(Cita minhaCita) {
		try{
			citaDAO.remove(minhaCita);
			log.info("Borrados os datos de " + minhaCita.toString());
			}catch(DataAccessException e){
			log.error("Erro borrando os datos de "+ minhaCita.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void modificacionCitaBD(Cita minhaCita) {
		try{
			citaDAO.update(minhaCita);
			log.info("Actualizados os datos de " + minhaCita.toString());
			}catch(DataAccessException e){
			log.error("Erro actualizando os datos de "+ minhaCita.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager")
	public Cita recuperarCitaBDPorId(Long id) {
		try{
			Cita cita =citaDAO.findCitaById(id);
			log.info("Encontrado os datos da cita " +cita.toString()+" con id: " + id);
			return cita;
			}catch(DataAccessException e){
			log.error("Erro encontrando a cita de id: "+ id);
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager")
	public Cita recuperarCitaBDPorCodigo(String codigo) {
		try{
			Cita cita =citaDAO.findCitaByCodigo(codigo);
			log.info("Encontrado os datos da cita " +cita.toString()+" con codigo: " + codigo);
			return cita;
			}catch(DataAccessException e){
			log.error("Erro encontrando a cita de codigo: "+ codigo);
			throw e;
			}
		}	

	@Override
	@Transactional(value="myTransactionManager")
	public List<Consulta> recuperarTodasConsultasMedicoData(Medico meuMedico, LocalDate minhaData) {
		try{
			List<Consulta> consultas =citaDAO.findAllConsultasMedicoData(meuMedico, minhaData);
			log.info("Encontrados datos de todas las consultas: " +consultas.toString() + "atendidas por el medico: "+ meuMedico+ ", na fecha: "+ minhaData);
			return consultas;
			}catch(DataAccessException e){
			log.error("Erro encontrando os datos de todos as consultas atendidas por el medico: "+ meuMedico+ ", na fecha: "+ minhaData);
			throw e;
			}	
	}
	

	@Override
	@Transactional(value="myTransactionManager")
	public List<Proba> recuperarTodasProbasData(LocalDate minhaData) {
		try{
			List<Proba> probas =citaDAO.findAllProbasData(minhaData);
			log.info("Encontrados datos de todas las probas: " +probas.toString() +" na fecha: "+ minhaData);
			return probas;
			}catch(DataAccessException e){
			log.error("Erro encontrando os datos de todos as probas do dia: " + minhaData);
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager")
	public List<Consulta> recuperarTodasConsultasPaciente(Paciente meuPaciente) {
		try{
			List<Consulta> consultas =citaDAO.findAllConsultasPaciente(meuPaciente);
			log.info("Encontrados datos de todas las consultas: " +consultas.toString() +" do paciente: "+ meuPaciente);
			return consultas;
			}catch(DataAccessException e){
			log.error("Erro encontrando os datos de todos as consultas do paciente: " + meuPaciente);
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager")
	public List<Proba> recuperarTodasProbasPaciente(Paciente meuPaciente) {
		try{
			List<Proba> probas =citaDAO.findAllProbasPaciente(meuPaciente);
			log.info("Encontrados datos de todas las probas: " +probas.toString() +" do paciente: "+ meuPaciente);
			return probas;
			}catch(DataAccessException e){
			log.error("Erro encontrando os datos de todos as probas  do paciente: " + meuPaciente);
			throw e;
			}	
	}

	@Override
	public SortedSet<Cita> recuperarTodasCitasPaciente(Paciente meuPaciente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paciente recuperarPacienteCita(Cita minhaCita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medico recuperarMedicoConsulta(Consulta minhaConsulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TipoDoenza> recuperarDoenzasConsulta(Consulta minhaConsulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoProba recuperarTipoProba(Proba minhaProba) {
		// TODO Auto-generated method stub
		return null;
	}


}
