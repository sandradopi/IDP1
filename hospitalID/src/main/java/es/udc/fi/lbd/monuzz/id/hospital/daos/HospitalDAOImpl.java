/**
 * 
 */
package es.udc.fi.lbd.monuzz.id.hospital.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.fi.lbd.monuzz.id.hospital.model.Medico;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoDoenza;
import es.udc.fi.lbd.monuzz.id.hospital.model.TipoProba;

@Repository
public class HospitalDAOImpl implements HospitalDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	// MEDICOS ============================================================================================================
	
	@Override
	public Long create(Medico meuMedico) {
		if (meuMedico.getIdMedico()!=null) {
			throw new RuntimeException("Intento de alta de medico xa persistente" + meuMedico.toString());
		}
		Long id = (Long) sessionFactory.getCurrentSession().save(meuMedico);
		sessionFactory.getCurrentSession().flush();
		return id;
	}
		
	@Override
	public void update(Medico meuMedico) {
		if (meuMedico.getIdMedico()==null) {
			throw new RuntimeException("Intento de modificación de medico non persistente" + meuMedico.toString());
		}
		sessionFactory.getCurrentSession().update(meuMedico);
		sessionFactory.getCurrentSession().flush();
		
	}

	@Override
	public void remove(Medico meuMedico) {
		if (meuMedico.getIdMedico()==null) {
			throw new RuntimeException("Intento de borrado de medico non persistente" + meuMedico.toString());
		}
		sessionFactory.getCurrentSession().delete(meuMedico);
		sessionFactory.getCurrentSession().flush();
		
	}

	@Override
	public Medico findMedicoById(Long id) {
		return (Medico) sessionFactory.getCurrentSession().createQuery("from Medico m where m.idMedico = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public Medico findMedicoByNumColexiado(String numColexiado) {
		return (Medico) sessionFactory.getCurrentSession().createQuery("from Medico m where m.numColexiado = :numColexiado").setParameter("numColexiado", numColexiado).uniqueResult();
	}

	@Override
	public List<Medico> findAllMedicos() {
		return sessionFactory.getCurrentSession().createQuery("from Medico order by especialidade desc, numColexiado asc ").list();
	}

	// DOENZAS ============================================================================================================

	@Override
	public Long create(TipoDoenza minhaDoenza) {
		if (minhaDoenza.getIdTipoDoenza()!=null) {
			throw new RuntimeException("Intento de alta de tipo doenza xa persistente" + minhaDoenza.toString());
		}
		Long id = (Long) sessionFactory.getCurrentSession().save(minhaDoenza);
		sessionFactory.getCurrentSession().flush();
		return id;
	}

	@Override
	public void update(TipoDoenza minhaDoenza) {
		if (minhaDoenza.getIdTipoDoenza()==null) {
			throw new RuntimeException("Intento de modificación de tipo doenza non persistente" + minhaDoenza.toString());
		}
		sessionFactory.getCurrentSession().update(minhaDoenza);
		sessionFactory.getCurrentSession().flush();
		
	}

	@Override
	public void remove(TipoDoenza minhaDoenza) {
		if (minhaDoenza.getIdTipoDoenza()==null) {
			throw new RuntimeException("Intento de borrado de tipo doenza non persistente" + minhaDoenza.toString());
		}
		sessionFactory.getCurrentSession().delete(minhaDoenza);
		sessionFactory.getCurrentSession().flush();
		
	}

	@Override
	public TipoDoenza findTipoDoenzaById(Long id) {
		return (TipoDoenza) sessionFactory.getCurrentSession().createQuery("from TipoDoenza td where td.idTipoDoenza = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public TipoDoenza findTipoDoenzaByCodigo(String codigo) {
		return (TipoDoenza) sessionFactory.getCurrentSession().createQuery("from TipoDoenza td where td.codigo = :codigo").setParameter("codigo", codigo).uniqueResult();
	}

	@Override
	public List<TipoDoenza> findAllTiposDoenzas() {
		return sessionFactory.getCurrentSession().createQuery("from TipoDoenza order by nome asc ").list();
	
	}
	// PROBAS ============================================================================================================
	@Override
	public Long create(TipoProba minhaProba) {
		if (minhaProba.getIdTipoProba()!=null) {
			throw new RuntimeException("Intento de alta de tipo proba xa persistente" + minhaProba.toString());
		}
		Long id = (Long) sessionFactory.getCurrentSession().save(minhaProba);
		sessionFactory.getCurrentSession().flush();
		return id;
	}

	@Override
	public void update(TipoProba minhaProba) {
		if (minhaProba.getIdTipoProba()==null) {
			throw new RuntimeException("Intento de modificación de tipo proba non persistente" + minhaProba.toString());
		}
		sessionFactory.getCurrentSession().update(minhaProba);
		sessionFactory.getCurrentSession().flush();
		
	}

	@Override
	public void remove(TipoProba minhaProba) {
		if (minhaProba.getIdTipoProba()==null) {
			throw new RuntimeException("Intento de borrado de tipo proba non persistente" + minhaProba.toString());
		}
		sessionFactory.getCurrentSession().delete(minhaProba);
		sessionFactory.getCurrentSession().flush();
		
	}

	@Override
	public TipoProba findTipoProbaById(Long id) {
		return (TipoProba) sessionFactory.getCurrentSession().createQuery("from TipoProba tp where tp.idTipoProba = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public TipoProba findTipoProbaByCodigo(String codigo) {
		return (TipoProba) sessionFactory.getCurrentSession().createQuery("from TipoProba tp where tp.codigo = :codigo").setParameter("codigo", codigo).uniqueResult();
	}

	@Override
	public List<TipoProba> findAllTiposProbas() {
		return sessionFactory.getCurrentSession().createQuery("from TipoProba order by nome asc ").list();
	
	}

}
