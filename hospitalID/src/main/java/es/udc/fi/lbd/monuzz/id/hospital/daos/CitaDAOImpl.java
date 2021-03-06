/**
 * 
 */
package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
		if (minhaCita.getIdCita()!=null) {
			throw new RuntimeException("Intento de alta de cita xa persistente" + minhaCita.toString());
		}
		Long id = (Long) sessionFactory.getCurrentSession().save(minhaCita);
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
		LocalDateTime minhaData1 = minhaData.atStartOfDay();
		LocalDateTime minhaData2 = minhaData.atTime(23,59);
		return sessionFactory.getCurrentSession().createQuery("from Consulta c where c.medico = :meuMedico AND (c.dataHora >= :minhaData1 AND c.dataHora <= :minhaData2) order by c.dataHora ").setParameter("meuMedico", meuMedico).setParameter("minhaData1", minhaData1).setParameter("minhaData2", minhaData2).list();
		
	}

	@Override
	public List<Proba> findAllProbasData(LocalDate minhaData) {
		LocalDateTime minhaData1 = minhaData.atStartOfDay();
		LocalDateTime minhaData2 = minhaData.atTime(23,59);
		
		return sessionFactory.getCurrentSession().createQuery("from Proba c where c.dataHora >= :minhaData1 AND c.dataHora <= :minhaData2 order by c.dataHora ").setParameter("minhaData1", minhaData1).setParameter("minhaData2", minhaData2).list();
	}

	@Override
	public List<Consulta> findAllConsultasPaciente(Paciente meuPaciente) {
		return sessionFactory.getCurrentSession().createQuery("from Consulta c where c.paciente = :meuPaciente order by c.dataHora desc").setParameter("meuPaciente", meuPaciente).list();
	}

	@Override
	public List<Proba> findAllProbasPaciente(Paciente meuPaciente) {
		return sessionFactory.getCurrentSession().createQuery("from Proba c where c.paciente = :meuPaciente order by c.dataHora desc").setParameter("meuPaciente", meuPaciente).list();
	}

	@Override
	public SortedSet<Cita> findAllCitasPaciente(Paciente meuPaciente) {
		
       
		List<Cita> citas= sessionFactory.getCurrentSession().createQuery("from Cita c where c.paciente = :meuPaciente order by c.dataHora").setParameter("meuPaciente", meuPaciente).list();
		TreeSet set =new TreeSet(citas);
		return set;
	}
	

	@Override
	public Paciente findPacienteCita(Cita minhaCita) {
		return (Paciente) sessionFactory.getCurrentSession().createQuery("Select p from Paciente p inner join p.citas c where c = :minhaCita").setParameter("minhaCita", minhaCita).uniqueResult();
	 
	}

	@Override
	public Medico findMedicoConsulta(Consulta minhaConsulta) {
		return null;
				//(Medico) sessionFactory.getCurrentSession().createQuery("Select c.medico from Consulta c where c = :minhaConsulta").setParameter("minhaConsulta", minhaConsulta).uniqueResult();
		
	}

	@Override
	public Set<TipoDoenza> findAllDoenzasConsulta(Consulta minhaCita) {
		List<TipoDoenza> doenzas= sessionFactory.getCurrentSession().createQuery("select c.doenzas from Consulta c where c = :minhaCita").setParameter("minhaCita", minhaCita).list();
		Set<TipoDoenza> set = new HashSet<TipoDoenza>(doenzas);
		return set;
	}

	@Override
	public TipoProba findTipoProba(Proba minhaCita) {
		return null;
				//(TipoProba) sessionFactory.getCurrentSession().createQuery("Select c.tipoProba from Proba c where c = :minhaCita").setParameter("minhaCita", minhaCita).uniqueResult();
	}

}
