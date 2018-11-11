/**
 * 
 */
package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.fi.lbd.monuzz.id.hospital.model.Cita;
import es.udc.fi.lbd.monuzz.id.hospital.model.Consulta;
import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.Paciente;
import es.udc.fi.lbd.monuzz.id.hospital.model.Proba;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoDoenza;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoProba;

@Repository
public class CitaDAOImpl implements CitaDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long create(Cita minhaCita) {
		if(minhaCita.getIdCita()!=null){
			throw new RuntimeException("Intento de alta de cita xa persistente"+ minhaCita.toString());
		}
		Long id= (Long) sessionFactory.getCurrentSession().save(minhaCita);
		sessionFactory.getCurrentSession().flush();
		return id;
	}

	@Override
	public void update(Cita minhaCita) {
		if(minhaCita.getIdCita()==null){
			throw new RuntimeException("Intento de modificación decita non persistente"+ minhaCita.toString());
		}
		sessionFactory.getCurrentSession().update(minhaCita);
		sessionFactory.getCurrentSession().flush();
		
	}

	@Override
	public void remove(Cita minhaCita) {
		if(minhaCita.getIdCita()==null){
			throw new RuntimeException("Intento de borrado de cita non persistente"+  minhaCita.toString());
		}
		sessionFactory.getCurrentSession().delete(minhaCita);
		sessionFactory.getCurrentSession().flush();
		
	}

	@Override
	public Cita findCitaById(Long id) {
		 return (Cita) sessionFactory.getCurrentSession().createQuery("from Cita p where p.idCita = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public Cita findCitaByCodigo(String codigoCita) {
		 return (Cita) sessionFactory.getCurrentSession().createQuery("from Cita p where p.codigo = :codigoCita").setParameter("codigoCita", codigoCita).uniqueResult();
	}

	@Override
	public List<Consulta> findAllConsultasMedicoData(Medico meuMedico, LocalDate minhaData) {
		return sessionFactory.getCurrentSession().createQuery("from Consulta c where c.medico = :meuMedico AND c.data = :minhaData order by c.data ").setParameter("meuMedico", meuMedico).setParameter("minhaData", minhaData).list();
		
	}

	@Override
	public List<Proba> findAllProbasData(LocalDate minhaData) {
		return sessionFactory.getCurrentSession().createQuery("from Proba c where c.data = :minhaData order by c.data ").setParameter("minhaData", minhaData).list();
	}

	@Override
	public List<Consulta> findAllConsultasPaciente(Paciente meuPaciente) {
		return sessionFactory.getCurrentSession().createQuery("from Consulta c where c.paciente = :meuPaciente").setParameter("meuPaciente", meuPaciente).list();
	}

	@Override
	public List<Proba> findAllProbasPaciente(Paciente meuPaciente) {
		return sessionFactory.getCurrentSession().createQuery("from Proba c where c.paciente = :meuPaciente").setParameter("meuPaciente", meuPaciente).list();
	}

	@Override
	public SortedSet<Cita> findAllCitasPaciente(Paciente meuPaciente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paciente findPacienteCita(Cita minhaCita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medico findMedicoConsulta(Consulta minhaConsulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TipoDoenza> findAllDoenzasConsulta(Consulta minhaCita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoProba findTipoProba(Proba minhaCita) {
		// TODO Auto-generated method stub
		return null;
	}

}
