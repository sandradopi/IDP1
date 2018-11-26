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
import es.udc.fi.lbd.monuzz.id.hospital.daos.HospitalDAO;
import es.udc.fi.lbd.monuzz.id.hospital.daos.PacienteDAO;
import es.udc.fi.lbd.monuzz.id.hospital.model.*;
@Service
public class HospitalServiceImpl implements HospitalService {


static Logger log= Logger.getLogger("hospital");
@Autowired
private CitaDAO citaDAO;

@Autowired
private HospitalDAO hospitalDAO;

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void altaNovoMedicoBD(Medico meuMedico) {
		try{
			hospitalDAO.create(meuMedico);
			log.info("Gravados os datos do medico: " + meuMedico.toString());
			}catch(DataAccessException e){
			log.error("Erro gravando os datos do medico: "+ meuMedico.toString());
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void borradoMedicoBD(Medico meuMedico) {
		try{
			hospitalDAO.remove(meuMedico);
			log.info("Borrados os datos do medico: " + meuMedico.toString());
			}catch(DataAccessException e){
			log.error("Erro borrando os datos do medico: "+ meuMedico.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void modificacionMedicoBD(Medico meuMedico) {
		try{
			hospitalDAO.update(meuMedico);
			log.info("Actualizados os datos do medico: " + meuMedico.toString());
			}catch(DataAccessException e){
			log.error("Erro actualizando os datos do medico: "+ meuMedico.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager")
	public Medico recuperarMedicoBDPorId(Long id) {
		try{
			Medico medico =hospitalDAO.findMedicoById(id);
			log.info("Encontrado os datos do medico " +medico.toString()+" con id: " + id);
			return medico;
			}catch(DataAccessException e){
			log.error("Erro encontrando o medico de id: "+ id);
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager")
	public Medico recuperarMedicoBDPorNumColexiado(String numColexiado) {
		try{
			Medico medico =hospitalDAO.findMedicoByNumColexiado(numColexiado);
			log.info("Encontrado os datos do medico " +medico+" con numColexiado: " + numColexiado);
			return medico;
			}catch(DataAccessException e){
			log.error("Erro encontrando o medico de numColexiado: "+ numColexiado);
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager")
	public List<Medico> recuperarTodosMedicosBD() {
		try{
			List<Medico> medicos =hospitalDAO.findAllMedicos();
			log.info("Encontrados datos de todas los medicos: " + medicos.toString());
			return medicos;
			}catch(DataAccessException e){
			log.error("Erro encontrando os datos de todos os medicos");
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void altaNovoTipoDoenzaBD(TipoDoenza minhaDoenza) {
		try{
			hospitalDAO.create(minhaDoenza);
			log.info("Gravados os datos da doenza: " + minhaDoenza.toString());
			}catch(DataAccessException e){
			log.error("Erro gravando os datos da doenza: "+ minhaDoenza.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void borradoTipoDoenzaBD(TipoDoenza minhaDoenza) {
		try{
			hospitalDAO.remove(minhaDoenza);
			log.info("Borrados os datos da doenza: " + minhaDoenza.toString());
			}catch(DataAccessException e){
			log.error("Erro borrando os datos da doenza: "+ minhaDoenza.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void modificacionTipoDoenzaBD(TipoDoenza minhaDoenza) {
		try{
			hospitalDAO.update(minhaDoenza);
			log.info("Actualizados os datos da doenza: " +minhaDoenza.toString());
			}catch(DataAccessException e){
			log.error("Erro actualizando os datos da doenza: "+ minhaDoenza.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager")
	public TipoDoenza recuperarTipoDoenzaBDPorId(Long id) {
		try{
			TipoDoenza doenza =hospitalDAO.findTipoDoenzaById(id);
			log.info("Encontrado os datos da doenza " +doenza.toString()+" con id: " + id);
			return doenza;
			}catch(DataAccessException e){
			log.error("Erro encontrando a doenza de id: "+ id);
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager")
	public TipoDoenza recuperarTipoDoenzaBDPorCodigo(String codigo) {
		try{
			TipoDoenza doenza =hospitalDAO.findTipoDoenzaByCodigo(codigo);
			log.info("Encontrado os datos da doenza " +doenza+" con codigo: " + codigo);
			return doenza;
			}catch(DataAccessException e){
			log.error("Erro encontrando a doenza de codigo: "+ codigo);
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager")
	public List<TipoDoenza> recuperarTodosTiposDoenzasBD() {
		try{
			List<TipoDoenza> doenzas =hospitalDAO.findAllTiposDoenzas();
			log.info("Encontrados datos de todas las doenzas: " + doenzas.toString());
			return doenzas;
			}catch(DataAccessException e){
			log.error("Erro encontrando os datos de todos as doenzas");
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void altaNovoTipoProbaBD(TipoProba minhaProba) {
		try{
			hospitalDAO.create(minhaProba);
			log.info("Gravados os datos da proba: " + minhaProba.toString());
			}catch(DataAccessException e){
			log.error("Erro gravando os datos da proba: "+ minhaProba.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void borradoTipoProbaBD(TipoProba minhaProba) {
		try{
			hospitalDAO.remove(minhaProba);
			log.info("Borrados os datos da proba: " + minhaProba.toString());
			}catch(DataAccessException e){
			log.error("Erro borrando os datos da proba: "+ minhaProba.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void modificacionTipoProbaBD(TipoProba minhaProba) {
		try{
			hospitalDAO.update(minhaProba);
			log.info("Actualizados os datos da proba: " +minhaProba.toString());
			}catch(DataAccessException e){
			log.error("Erro actualizando os datos da proba: "+ minhaProba.toString());
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager")
	public TipoProba recuperarTipoProbaBDPorId(Long id) {
		try{
			TipoProba proba =hospitalDAO.findTipoProbaById(id);
			log.info("Encontrado os datos da proba " +proba.toString()+" con id: " + id);
			return proba;
			}catch(DataAccessException e){
			log.error("Erro encontrando a proba de id: "+ id);
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager")
	public TipoProba recuperarTipoProbaBDPorCodigo(String codigo) {
		try{
			TipoProba proba =hospitalDAO.findTipoProbaByCodigo(codigo);
			log.info("Encontrado os datos da proba " +proba+" con codigo: " + codigo);
			return proba;
			}catch(DataAccessException e){
			log.error("Erro encontrando a proba de codigo: "+ codigo);
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager")
	public List<TipoProba> recuperarTodosTiposProbasBD() {
		try{
			List<TipoProba> probas =hospitalDAO.findAllTiposProbas();
			log.info("Encontrados datos de todas las probas: " + probas.toString());
			return probas;
			}catch(DataAccessException e){
			log.error("Erro encontrando os datos de todos as probas");
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void altaNovaCitaBD(Cita minhaCita) {
		try{
			citaDAO.create(minhaCita);
			log.info("Gravados os datos da cita: " + minhaCita.toString());
			}catch(DataAccessException e){
			log.error("Erro gravando os datos da cita: "+ minhaCita.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void borradoCitaBD(Cita minhaCita) {
		try{
			citaDAO.remove(minhaCita);
			log.info("Borrados os datos da cita: " + minhaCita.toString());
			}catch(DataAccessException e){
			log.error("Erro borrando os datos da cita: "+ minhaCita.toString());
			throw e;
			}	
		
	}

	@Override
	@Transactional(value="myTransactionManager", readOnly=false)
	public void modificacionCitaBD(Cita minhaCita) {
		try{
			citaDAO.update(minhaCita);
			log.info("Actualizados os datos da cita: " + minhaCita.toString());
			}catch(DataAccessException e){
			log.error("Erro actualizando os datos da cita: "+ minhaCita.toString());
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
	@Transactional(value="myTransactionManager")
	public SortedSet<Cita> recuperarTodasCitasPaciente(Paciente meuPaciente) {
		try{
			SortedSet<Cita> citas = citaDAO.findAllCitasPaciente(meuPaciente);
			log.info("Encontrados datos de todas las citas: " +citas.toString() +" do paciente: "+ meuPaciente);
			return citas;
			}catch(DataAccessException e){
			log.error("Erro encontrando os datos de todos as citas  do paciente: " + meuPaciente);
			throw e;
			}	
	}

	@Override
	@Transactional(value="myTransactionManager")
	public Paciente recuperarPacienteCita(Cita minhaCita) {
		try{
			Paciente paciente =citaDAO.findPacienteCita(minhaCita);
			log.info("Encontrado os datos do paciente: " +paciente+" da cita: " + minhaCita);
			return paciente;
			}catch(DataAccessException e){
			log.error("Erro encontrando o paciente da cita: "+ minhaCita);
			throw e;
			}
	}

	@Override
	@Transactional(value="myTransactionManager")
	public Medico recuperarMedicoConsulta(Consulta minhaConsulta) {
		try{
			Medico medico =citaDAO.findMedicoConsulta(minhaConsulta);
			log.info("Encontrado os datos do medico: " +medico+" da consulta: " + minhaConsulta);
			return medico;
			}catch(DataAccessException e){
			log.error("Erro encontrando o medico da consulta: "+ minhaConsulta);
			throw e;
			}
	}

	@Override
	@Transactional(value="myTransactionManager")
	public Set<TipoDoenza> recuperarDoenzasConsulta(Consulta minhaConsulta) {
		try{
			Set<TipoDoenza> doenzas =citaDAO.findAllDoenzasConsulta(minhaConsulta);
			log.info("Encontrado os datos das doenzas: " + doenzas+" da consulta: " + minhaConsulta);
			return doenzas;
			}catch(DataAccessException e){
			log.error("Erro encontrando as doenzas da consulta: "+ minhaConsulta);
			throw e;
			}
	}

	@Override
	@Transactional(value="myTransactionManager")
	public TipoProba recuperarTipoProba(Proba minhaProba) {
		try{
			TipoProba  tipoProba  =citaDAO.findTipoProba(minhaProba);
			log.info("Encontrado os datos do tipo da proba: " +tipoProba+" da proba: " + minhaProba);
			return tipoProba;
			}catch(DataAccessException e){
			log.error("Erro encontrando o tipo da proba da proba: "+ minhaProba);
			throw e;
			}
	}


}
