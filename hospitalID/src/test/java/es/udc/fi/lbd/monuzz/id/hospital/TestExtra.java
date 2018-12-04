package es.udc.fi.lbd.monuzz.id.hospital;



import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.fi.lbd.monuzz.id.hospital.model.Cita;
import es.udc.fi.lbd.monuzz.id.hospital.model.Consulta;
import es.udc.fi.lbd.monuzz.id.hospital.services.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml"})
public class TestExtra {
	
	private Logger log = Logger.getLogger("hospital");

	@Autowired
	private TestUtils testUtils;

	@Autowired
	private ExtraService extraService;
	
	@Autowired
	private PacienteService pacienteService;

	@Before
	public void setUp() throws Exception { 
		//Creamos datos de proba iniciais
		log.info ("Creando datos iniciais para caso de proba: " + this.getClass().getName() + " ===========================================");
		testUtils.creaSetMedicosProba(); 
		testUtils.creaSetDoenzasProba();
		testUtils.creaSetTiposProbasProba();
		testUtils.creaSetPacientesProba();
		testUtils.creaSetCitasProba();
		log.info ("Foron creados con éxito os datos iniciais para o caso de proba: " + this.getClass().getName() + " =============");
	}

	@After
	public void tearDown() throws Exception {
								
		log.info ("Eliminando datos iniciais para caso de proba: " + this.getClass().getName() + " ========================================");
		
		/* Ao eliminar un paciente deben desaparecer as súas citas      */
		/* Descomentar a primeira liña só se non se consegue facer funcionar tal e como está */
		
		//testUtils.eliminaSetCitasProba();
		testUtils.eliminaSetPacientesProba();
		testUtils.eliminaSetTiposProbasProba();
		testUtils.eliminaSetDoenzasProba(); 
		testUtils.eliminaSetMedicosProba(); 
		log.info ("Foron eliminados con éxito os datos iniciais para o caso de proba: " + this.getClass().getName() + " ==========");
	}
	
	@Test
	public void testCompleto() {

		log.info ("INICIANDO [Test_Extra] en: " + this.getClass().getName() + " ===========================================================");
		a_Test();


	}
	
	
	
	private void a_Test() {
		List<Cita> citasPacMed;
		Cita cita_EXW1;
		
		
	// Recuperacion de todas las citas que tiene un paciente con un médico---------------------------------------------------------------------------
		
		//Pacientes que si que tienen citas con ese medico
		citasPacMed = extraService.CitasPacienteMedico(testUtils.paciente_W.getNumPaciente(),testUtils.medico_A.getNumColexiado());
		assertEquals (1,citasPacMed.size());
		assertEquals (citasPacMed.get(0), testUtils.cita_W1);
		citasPacMed = extraService.CitasPacienteMedico(testUtils.paciente_W.getNumPaciente(),testUtils.medico_B.getNumColexiado());
		assertEquals (1,citasPacMed.size());
		assertEquals (citasPacMed.get(0), testUtils.cita_W3);
		//Médicos sin Consultas
		citasPacMed = extraService.CitasPacienteMedico(testUtils.paciente_W.getNumPaciente(),testUtils.medico_C.getNumColexiado());
		assertEquals (0,citasPacMed.size());
		citasPacMed = extraService.CitasPacienteMedico(testUtils.paciente_W.getNumPaciente(),testUtils.medico_D.getNumColexiado());
		assertEquals (0,citasPacMed.size());
		//Pacientes sin Consultas
		citasPacMed = extraService.CitasPacienteMedico(testUtils.paciente_Y.getNumPaciente(),testUtils.medico_C.getNumColexiado());
		assertEquals (0,citasPacMed.size());
		citasPacMed = extraService.CitasPacienteMedico(testUtils.paciente_Z.getNumPaciente(),testUtils.medico_D.getNumColexiado());
		assertEquals (0,citasPacMed.size());
		
		//si creamos una nueva consulta
		cita_EXW1 = new Consulta("cita_EXW1", LocalDateTime.of(2018, Month.FEBRUARY, 1, 8, 45), testUtils.paciente_W, "Motivo_W3", testUtils.medico_B);
		assertEquals (2,citasPacMed.size());
		assertEquals (citasPacMed.get(0), testUtils.cita_W3);
		assertEquals (citasPacMed.get(1), cita_EXW1);
		
		
		//si borramos un paciente
		/*pacienteService.borradoPacienteBD(testUtils.paciente_W);
		citasPacMed = extraService.CitasPacienteMedico(testUtils.paciente_W.getNumPaciente(),testUtils.medico_A.getNumColexiado());
		assertEquals (0,citasPacMed.size());
		citasPacMed = extraService.CitasPacienteMedico(testUtils.paciente_W.getNumPaciente(),testUtils.medico_B.getNumColexiado());
		assertEquals (0,citasPacMed.size());*/
	}
	

}
