package com.project.sigte2.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.sigte2.domain.AgendaTransporte;
import com.project.sigte2.domain.Aluno;
import com.project.sigte2.util.PaginacaoUtil;

@Repository
public class AgendaTransporteDaoImpl extends AbstractDao<AgendaTransporte, Long> implements AgendaTransporteDao {

	@Override
	public PaginacaoUtil<AgendaTransporte> buscaPaginada(int pagina) {

		int tamanho = 5;

		// como nossas paginas são definidos pelo indice do array, faremos essa conta
		// matematica para
		// validar que nossas paginas comece nos indices certos que sao, 0, 5 e 10.
		int inicio = (pagina - 1) * tamanho;

		// criarmos uma consulta atraves da entitymanager ,pelo nome ascendente, do tipo
		// Cargo.class
		// o metedo setFirstResult tras para nos o primeiro registro de uma consulta, ou
		// seja, um array de consulta retorna o indice 0
		// quando trabalhamos com paginação agente não o numero da pagina que queremos
		// trabalhar , mas sim o numero do indice do primeiro registro
		// ex: suposmos que temos tres paginas de 5 registro , a pagina 1, o primeiro
		// registro esta no indice 0
		// a pagina 2, o primeiro registro indice 5, pagina tres o primeiro registro
		// indice 10
		// ex :pagina 1 -1 = 0 * 5 = 0, pagina 2-1 = 1 * 5 = 1, pagina 3 -1 = 2 *5 = 10;

		List<AgendaTransporte> agendasTransportes = getEntityManager()
				.createQuery("select a from AgendaTransporte a order by a.id asc", AgendaTransporte.class)
				.setFirstResult(inicio).setMaxResults(tamanho).getResultList();

		// retorna total registro
		long totalRegistros = contador();
		// aqui teremos a seguinte lógica por usarmos uma variavel long abaixo, nao
		// podemo usar numeros reais
		// pois o java arredondaria o resultado para baixo perdendo a pagina seguinte
		// exemplo 15 registro divido por 5, daria 3 paginas ok, mas quando for 16 ou
		// outro numero que tras um resultado real
		// 16/5 = 3, .. o ultimo registro estaria perdido pois o java arredondaria o
		// resultado para 3, entao seria 3 paginas de 5 registros
		// e ultimo registro estaria perdido,alc entao faremos a seguinta conta para não
		// termos esse problema.
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, agendasTransportes);
	}

	// para sabermos o total de paginas precisamos, saber o total de resgistros no
	// bd, assim dividirmos por paginas
	// entao usaremos um metodo para trazer essa informação.
	public long contador() {
		return getEntityManager().createQuery("select count(*) from Cargo", Long.class).getSingleResult();
	}

	@Override
	public List<AgendaTransporte> findAlunosByIdDiaIdEscola(Long idDia, Long idEscola) {
		return createQuery("select a from AgendaTransporte a where a.diaSemana.id = ?1 and "
				+ "a.aluno.escola.id= ?2", idDia, idEscola);
	}

	@Override
	public List<AgendaTransporte> findAlunosByIdDiaIdEscola(Long id) {
		return createQuery("select a from AgendaTransporte a where a.aluno.id= ?1", id);
	}

}
