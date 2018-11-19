package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;


@Repository
public class PacienteDAOHibImpl implements PacienteDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long create(Paciente meuPaciente) {
		if(meuPaciente.getIdPaciente()!=null){
			throw new RuntimeException("Intento de alta de paciente xa persistente"+ meuPaciente.toString());
		}
		Long id= (Long) sessionFactory.getCurrentSession().save(meuPaciente);
		sessionFactory.getCurrentSession().flush();
		return id;
	}

	@Override
	public void update(Paciente meuPaciente) {
		if(meuPaciente.getIdPaciente()==null){
			throw new RuntimeException("Intento de modificación de paciente non persistente"+ meuPaciente.toString());
		}
		sessionFactory.getCurrentSession().update(meuPaciente);
		sessionFactory.getCurrentSession().flush();
		
		
	}

	@Override
	public void remove(Paciente meuPaciente) {
		if(meuPaciente.getIdPaciente()==null){
			throw new RuntimeException("Intento de borrado de paciente non persistente"+ meuPaciente.toString());
		}
		sessionFactory.getCurrentSession().delete(meuPaciente);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public Paciente findPacienteById(Long id) {
		 return (Paciente) sessionFactory.getCurrentSession().createQuery("from Paciente p where p.idPaciente = :id").setParameter("id", id).uniqueResult();
				
	}

	@Override
	public Paciente findPacienteByNum(String numPaciente) {
		 return (Paciente) sessionFactory.getCurrentSession().createQuery("from Paciente p where p.numPaciente = :numPaciente").setParameter("numPaciente", numPaciente).uniqueResult();
	}

	@Override
	public List<Paciente> findAllPacientes() {
		 return sessionFactory.getCurrentSession().createQuery("from Paciente  p order by p.numPaciente desc ").list();
	}

}
