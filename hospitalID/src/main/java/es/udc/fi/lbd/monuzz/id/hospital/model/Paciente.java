package es.udc.fi.lbd.monuzz.id.hospital.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;


import javax.persistence.Convert;
import es.udc.fi.lbd.monuzz.id.hospital.converters.LocalDateAttributeConverter;


public class Paciente {
	
	private Long idPaciente;	
	private String numPaciente;
	private String nomeCompleto;
	private LocalDate dataNacemento;
	private SortedSet<Cita> citas = new TreeSet<Cita>(); 

	// Clave surrogada: idPaciente
	// Clave natural: numPaciente
	// Atributos obrigatorios: numPaciente, nomeCompleto
	// Atributos unicos: numPaciente
	
	// OUTRAS RESTRICCIONS (A DEFINIR NA BD) 
	// 		A listaxe de citas ESTA ORDENADA por data (a mais RECENTE primeiro)

		
	protected Paciente() {}

	public Paciente(String numPaciente, String nomeCompleto, LocalDate dataNacemento) {
		this.idPaciente=null;
		this.numPaciente = numPaciente;
		this.nomeCompleto = nomeCompleto;
		this.dataNacemento = dataNacemento;
	}


	// GETTERS =============================================================================
	
	public Long getIdPaciente() {return this.idPaciente;}

	public String getNumPaciente() {return this.numPaciente;}

	public String getNomeCompleto() {return this.nomeCompleto;}

	@Convert(converter = LocalDateAttributeConverter.class)
	public LocalDate getDataNacemento() {return this.dataNacemento;}

	public SortedSet<Cita> getCitas() {return this.citas;}
	
	// SETTERS =============================================================================

	public void setIdPaciente(Long id) {this.idPaciente = id;}

	public void setNumPaciente(String num) {this.numPaciente = num;}

	public void setNomeCompleto(String nome) {this.nomeCompleto = nome;}

	public void setDataNacemento(LocalDate data) {this.dataNacemento = data;}

	public void setCitas(SortedSet<Cita> citas) {this.citas = citas;}

	
	// MÉTODOS DE CONVENIENCIA ==============================================================


	public Boolean dataOcupada (LocalDateTime data) {
		Iterator<Cita> itr = this.getCitas().iterator();
		while (itr.hasNext()) {
			if (data.equals(itr.next().getDataHora()))
				return true;
		}
		return false;
	}
	
	// OUTROS =============================================================================
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numPaciente == null) ? 0 : numPaciente.hashCode());
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
		Paciente other = (Paciente) obj;
		if (numPaciente == null) {
			if (other.numPaciente != null)
				return false;
		} else if (!numPaciente.equals(other.numPaciente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", numPaciente=" + numPaciente + ", nomeCompleto=" + nomeCompleto
				+ ", dataNacemento=" + dataNacemento + "]";
	}


	
}