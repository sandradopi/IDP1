package es.udc.fi.lbd.monuzz.id.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TipoDoenza")
public class TipoDoenza {
	
	@Id
	@SequenceGenerator(name="tipoDoenzaId",sequenceName="id_tipodoenza_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tipoDoenzaId")
	@Column(name="idTipoDoenza")
	private Long idTipoDoenza;	
	
	@Column(name="codigo", nullable=false, unique=true)
	private String codigo;
	
	@Column(name="nome", nullable=false, unique=false)
	private String nome;
	
	@Column(name="descricion")
	private String descricion;
	
	// Clave surrogada: idTipoDoenza
	// Clave natural: codigo, nome
	// Atributos obrigatorios: codigo, nome
	// Atributos unicos: codigo, nome
	
		
	protected TipoDoenza() {}

	public TipoDoenza(String codigo, String nome, String descricion) {
		this.idTipoDoenza=null;
		this.codigo = codigo;
		this.nome = nome;
		this.descricion = descricion;
	}


	// GETTERS =============================================================================
	
	public Long getIdTipoDoenza() {return this.idTipoDoenza;}

	public String getCodigo() {return this.codigo;}

	public String getNome() {return this.nome;}

	public String getDescricion() {return this.descricion;}

	
	// SETTERS =============================================================================


	public void setIdTipoDoenza(Long id) {this.idTipoDoenza = id;}

	public void setCodigo(String num) {this.codigo = num;}

	public void setNome(String nome) {this.nome = nome;}

	public void setDescricion(String descricion) {this.descricion = descricion;	}

	
	
	// OUTROS =============================================================================
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoDoenza other = (TipoDoenza) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoDoenza [idTipoDolencia=" + idTipoDoenza + ", codigo=" + codigo + ", nome=" + nome
				+ ", descricion=" + descricion + "]";
	}


	
}