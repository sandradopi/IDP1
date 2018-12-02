package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import es.udc.fi.lbd.monuzz.id.hospital.model.Cita;
import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;

public class ExtraDAOHibImpl implements ExtraDAO{
	@Autowired
	private SessionFactory sessionFactory;

		@Override
		public List<Cita> CitasPacienteMedico(Long numPaciente,Long numColexiado){
			 return  sessionFactory.getCurrentSession().createQuery("select c from Cita c inner join c.paciente p inner join c.medico m where m.numColexiado :=numColexiado AND p.numPaciente:= numPaciente ").setParameter("numPaciente", numPaciente).setParameter("numColexiado", numColexiado).list();
				}
		
		@Override
		public Long countAllCitasPaciente(Paciente paciente){
			return null;
			}
	
		@Override
		public List<Paciente> findPacientesNoMedico(Medico medico){
		 return  sessionFactory.getCurrentSession().createQuery("from Paciente p where p not in (from Paciente p inner join p.citas c where c.medico:=medico)  ").setParameter("medico", medico).list();
		
	}
	


}
