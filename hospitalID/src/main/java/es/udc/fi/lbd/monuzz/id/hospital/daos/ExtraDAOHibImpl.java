package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.fi.lbd.monuzz.id.hospital.model.Cita;
import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

@Repository
public class ExtraDAOHibImpl implements ExtraDAO{
	@Autowired
	private SessionFactory sessionFactory;

		@Override
		public List<Cita> CitasPacienteMedico(String numPaciente,String numColexiado){
			 return  sessionFactory.getCurrentSession().createQuery("select c from Consulta c inner join c.paciente p inner join c.medico m where m.numColexiado =:numColexiado AND p.numPaciente =:numPaciente ").setParameter("numPaciente", numPaciente).setParameter("numColexiado", numColexiado).list();
				}
		
		@Override
		public Long countAllCitasPaciente(Paciente paciente){
			return (Long) sessionFactory.getCurrentSession().createQuery("select count(*) from Cita c where c.paciente=:paciente").setParameter("paciente", paciente).uniqueResult();
			}
	
		@Override
		public List<Paciente> findPacientesNoMedico(Medico medico){
		 return  sessionFactory.getCurrentSession().createQuery("from Paciente p where p not in (from Paciente p inner join p.citas c where c.medico=:medico)  ").setParameter("medico", medico).list();
		
	}
	


}
